Ext.ns('ErrandsRegisterView');
/**
 * 请假单列表
 * @class ErrandsRegisterView
 * @extends Ext.Panel
 */
ErrandsRegisterView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		ErrandsRegisterView.superclass.constructor.call(this, {
					id : 'ErrandsRegisterView',
					title : '请假单列表',
					region : 'center',
					iconCls : 'menu-holiday',
					layout : 'border',
					items : [this.searchPanel, this.gridPanel]
				});
	},

	// 初始化组件
	initUIComponents : function() {
		// 初始化搜索条件Panel
		this.searchPanel = new HT.SearchPanel({
					layout : 'form',
					region : 'north',
					colNums : 6,
					keys : [{
								key : Ext.EventObject.ENTER,
								fn : this.search,
								scope : this
							}, {
								key : Ext.EventObject.ESC,
								fn : this.reset,
								scope : this
							}],
					labelWidth : 135,
					items : [{
								fieldLabel : '查询条件: 开始时间: 从',
								xtype : 'datetimefield',
								format : 'Y-m-d H:i:s',
								name : 'Q_startTime_D_GE',
								editable : false,
								width : 200
							},{
								fieldLabel : '到',
								xtype : 'datetimefield',
								format : 'Y-m-d H:i:s',
								name : 'Q_endTime_D_LE',
								editable : false,
								labelWidth : 15,
								width : 200
							}, {
								fieldLabel : '审批状态',
								xtype : 'combo',
								hiddenName : 'Q_status_SN_EQ',
								mode : 'local',
								width : 100,
								labelWidth : 60,
								editable : false,
								triggerAction : 'all',
								store : [['0', '未审批'], ['1', '通过审批'],
										['2', '未通过审批']]
							}, {
								xtype : 'button',
								scope : this,
								text : '查询',
								iconCls : 'search',
								handler : this.search
							}, {
								xtype : 'button',
								scope : this,
								text : '重置',
								iconCls : 'btn-reseted',
								handler : this.reset
							}]
				});

		// 顶端栏目条
		this.topbar = new Ext.Toolbar({
					items : [{
								iconCls : 'btn-add',
								text : '添加请假单',
								xtype : 'button',
								scope : this,
								handler : this.createRs
							}, '-', {
								iconCls : 'btn-del',
								text : '删除请假单',
								xtype : 'button',
								scope : this,
								handler : this.removeSelRs
							}]
				});

		// 请假登记面板
		this.gridPanel = new HT.GridPanel({
			region : 'center',
			tbar : this.topbar,
			sort : [{field:"dateId",direction:"DESC"}],
			rowActions : true,
			url : __ctxPath + '/personal/listErrandsRegister.do',
			baseParams : {
						'Q_flag_SN_EQ' : 1
					},
			fields : [{
						name : 'dateId',
						type : 'int'
					}, {
						name : 'userName',
						mapping : 'appUser.fullname'
					}, 'descp', 'startTime', 'endTime', 'approvalId', 'status',
					'approvalOption', 'approvalName', 'flag', 'runId', 'tasks'],
			columns : [{
						header : 'dateId',
						dataIndex : 'dateId',
						hidden : true
					}, {
						header : '描述',
						dataIndex : 'descp'
					}, {
						header : '开始日期',
						dataIndex : 'startTime'
					}, {
						header : '结束日期',
						dataIndex : 'endTime'
					}, {
						header : '审批状态',
						dataIndex : 'status',
						renderer : this.statusRenderer
					}, {
						header : '审批意见',
						dataIndex : 'approvalOption'
					}, {
						header : '审批人',
						dataIndex : 'approvalName'
					}, {
						header : '工作流',
						dataIndex : 'tasks',
						sortable : false,
						renderer : this.tasks
					}, new Ext.ux.grid.RowActions({
								header : '管理',
								width : 100,
								actions : [{
											iconCls : 'btn-del',
											qtip : '删除',
											style : 'margin:0 3px 0 3px',
											fn : function(record) {
												if (record.data.status == 0)
													return true;
												return false;
											}
										}, {
											iconCls : 'btn-showDetail',
											qtip : '详细',
											style : 'margin:0 3px 0 3px'
										}],
								listeners : {
									scope : this,
									'action' : this.onRowAction
								}
							})],
			listeners : {
				scope : this,
				'rowdblclick' : this.rowClick
			}
		});

	},
	// 重置(清空)查询表单
	reset : function() {
		this.searchPanel.getForm().reset();
	},
	// 按条件搜索
	search : function() {
		$search({
					searchPanel : this.searchPanel,
					gridPanel : this.gridPanel
				});
	},
	// GridPanel行点击处理事件
	rowClick : function(grid, rowindex, e) {
		var rec = grid.getStore().getAt(rowindex);
		this.detai.call(this, rec);
	},
	// 审批状态
	statusRenderer : function(value) {
		switch (value) {
			case 0 :
				return '未审批';
			case 1 :
				return '<font color="green">通过审批</font>';
			case 2 :
				return '<font color="red">未通过审批</font>';
			default :
				return '';
		}
	},

	// 工作流
	tasks : function(tasks) {
		var reVal = '';
		if (tasks.length > 0) {
			for (var i = 0; i < tasks.length; i++) {
				reVal += tasks[i].taskName;
				if (tasks[i].userId) {
					reVal += '(';
					if (curUserInfo.userId == tasks[i].userId) {
						reVal += '<a href="#" onclick="App.MyDesktopClickTopTab(\'ProcessNextForm\',{taskId:'
								+ tasks[i].taskId
								+ ',activityName:\''
								+ tasks[i].taskName + '\'})">';
					}
					reVal += tasks[i].fullname;
					if (curUserInfo.userId == tasks[i].userId) {
						reVal += "</a>";
					}
					reVal += ')&nbsp;&nbsp;';
				}
			}
		}
		return reVal;
	},
	// 创建记录
	createRs : function() {
		new ErrandsRegisterForm({
					scope : this,
					callback : this.reloadGridPanel
				}).show();
	},
	// 刷新gridPanel
	reloadGridPanel : function() {
		this.gridPanel.getStore().reload();
	},
	// 按ID删除记录
	removeRs : function(id) {
		$postDel({
					url : __ctxPath + '/personal/multiDelErrandsRegister.do',
					ids : id,
					grid : this.gridPanel
				});
	},
	// 把选中ID删除
	removeSelRs : function() {
		$delGridRs({
					url : __ctxPath + '/personal/multiDelErrandsRegister.do',
					grid : this.gridPanel,
					idName : 'dateId'
				});
	},
	// 详细
	detai : function(record) {
		new ErrandsRegisterDetail({
					dateId : record.data.dateId,
					runId : record.data.runId
				}).show();
	},
	// 行action
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.removeRs.call(this, record.data.dateId);
				break;
			case 'btn-showDetail' :
				this.detai.call(this, record);
				break;
			default :
				break;
		}
	}

});