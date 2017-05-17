Ext.ns('DepWorkPlanView');
/**
 * 部门计划
 * 
 * @class DepWorkPlanView
 * @extends Ext.Panel
 */
DepWorkPlanView = Ext.extend(Ext.Panel, {
	// 构造方法
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造方法
		DepWorkPlanView.superclass.constructor.call(this, {
					id : 'DepWorkPlanView',
					title : '部门计划列表',
					iconCls : 'menu-depplan',
					region : 'center',
					layout : 'border',
					items : [this.searchPanel, this.gridPanel]
				});
	},// end of constructor
	// 初始化组件
	initUIComponents : function() {
		// 查询面板
		this.searchPanel = new HT.SearchPanel({
			layout : 'form',
			region : 'north',
			colNums : 5,
			labelWidth : 150,
			keys : [{
						key : Ext.EventObject.ENTER,
						fn : this.search,
						scope : this
					}, {
						key : Ext.EventObject.ESC,
						fn : this.reset,
						scope : this
					}],
			items : [{
						fieldLabel : '请输入查询条件:计划名称',
						xtype : 'textfield',
						name : 'workPlan.planName'
					}, {
						fieldLabel : '计划类型',
						hiddenName : 'workPlan.globalType.proTypeId',
						xtype : 'combotree',
						url : __ctxPath
								+ '/system/treeGlobalType.do?catKey=DEPWORKPLAN&method=1',
						labelWidth : 60
					}, {
						fieldLabel : '负责人',
						xtype : 'textfield',
						labelWidth : 50,
						name : 'workPlan.principal'
					}, {
						xtype : 'button',
						text : '查询',
						style : 'padding-left:5px;',
						iconCls : 'search',
						handler : this.search,
						scope : this
					}, {
						xtype : 'button',
						text : '重置',
						style : 'padding-left:5px;',
						iconCls : 'btn-reset',
						handler : this.reset,
						scope : this
					}]
		});// end of searchPanel
		// 顶部菜单条
		this.topbar = new Ext.Toolbar({
					height : 30,
					bodyStyle : 'text-align:left',
					items : [{
								iconCls : 'btn-add',
								text : '添加部门计划',
								xtype : 'button',
								scope : this,
								handler : this.createRs
							}, '-', {
								iconCls : 'btn-del',
								text : '删除',
								xtype : 'button',
								scope : this,
								handler : this.delRecords
							}]
				});
		// 日志列表面板
		this.gridPanel = new HT.GridPanel({
			region : 'center',
			// 使用RowActions
			rowActions : true,
			tbar : this.topbar,
			sort : [{
						field : "planId",
						direction : "DESC"
					}],
			url : __ctxPath + '/task/departmentWorkPlan.do',
			fields : [{
						name : 'planId',
						type : 'int'
					}, {
						name : 'appUser.fullname',
						mapping : 'appUser.fullname'
					}, 'planName', 'planContent', 'startTime', 'endTime',
					'globalType', 'appUser', 'issueScope', 'participants',
					'principal', 'note', 'status', 'isPersonal', 'icon'],
			columns : [{
						header : 'planId',
						dataIndex : 'planId',
						hidden : true
					}, {
						header : '标识',
						dataIndex : 'icon',
						renderer : function(value) {
							var tip = (value == 'ux-flag-blue'
									? '日常计划'
									: (value == 'ux-flag-orange'
											? '重要计划'
											: (value == 'ux-flag-green'
													? '特殊计划'
													: (value == 'ux-flag-pink'
															? '个人计划'
															: (value == 'ux-flag-red'
																	? '紧急计划'
																	: (value == 'ux-flag-purple'
																			? '部门计划'
																			: (value == 'ux-flag-yellow'
																					? '待定计划'
																					: '')))))));

							return '<div ext:qtip="	' + tip + '" class="'
									+ value + '"></div>';
						}
					}, {
						header : '计划名称',
						dataIndex : 'planName'
					}, {
						header : '开始日期',
						dataIndex : 'startTime'
					}, {
						header : '结束日期',
						dataIndex : 'endTime'
					}, {
						header : '计划类型',
						dataIndex : 'globalType',
						renderer : function(obj) {
							if (obj != null) {
								return obj.typeName;
							}
						}
					}, {
						header : '创建人',
						dataIndex : 'appUser.fullname'
					}, {
						header : '发布范围',
						dataIndex : 'issueScope'
					}, {
						header : '负责人',
						dataIndex : 'principal'
					}, {
						header : '是否生效',
						dataIndex : 'startTime',
						renderer : this.effice
					}, new Ext.ux.grid.RowActions({
								header : '管理',
								width : 100,
								actions : [{
											iconCls : 'btn-detail',
											qtip : '查看',
											style : 'margin:0 3px 0 3px'
										}, {
											iconCls : 'btn-del',
											qtip : '删除',
											style : 'margin:0 3px 0 3px',
											fn : this.judge
										}, {
											iconCls : 'btn-edit',
											qtip : '编辑',
											style : 'margin:0 3px 0 3px',
											fn : this.judge
										}],
								listeners : {
									scope : this,
									'action' : this.onRowAction
								}
							})]
				// end of columns
		});
		this.gridPanel.addListener({
					scope : this,
					'rowdblclick' : this.rowClick
				});
	},// end of the initComponents()
	// 重置查询表单
	reset : function() {
		this.searchPanel.getForm().reset();
		this.searchPanel.getCmpByName('workPlan.globalType.proTypeId').clearValue();
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
		var record = grid.getStore().getAt(rowindex);
		this.detail.call(this, record);
	},
	// 查看明细
	detail : function(record) {
		var planId = record.data.planId;
		var planName = record.data.planName;
		new WorkPlanDetail({
					planId : planId,
					planName : planName
				}).show();
	},
	// 判断是否为当前用户
	judge : function(record) {
		if (record.data.appUser.userId != curUserInfo.userId) {
			return false;
		}
		return true;
	},
	// 创建记录
	createRs : function() {
		new DepWorkPlanForm({
					scope : this,
					callback : this.reloadType
				}).show();
	},
	// 编辑
	editRecord : function(record) {
		new DepWorkPlanForm({
					planId : record.data.planId,
					planName : record.data.planName,
					scope : this,
					callback : this.reloadType
				}).show();
	},
	// 删除单个记录
	removeRs : function(rec) {
		$postDel({
					url : __ctxPath + '/task/multiDelWorkPlan.do',
					ids : rec.data.planId,
					grid : this.gridPanel
				});
	},
	// 删除
	delRecords : function() {
		var selRs = this.gridPanel.getSelectionModel().getSelections();
		for (var i = 0; i < selRs.length; i++) {
			var rec = selRs[i];
			if (rec.data.appUser.userId != curUserInfo.userId) {
				Ext.ux.Toast.msg("操作信息", "只有当前用户创建的记录才能被删除！");
				return;
			}
		}

		$delGridRs({
					url : __ctxPath + '/task/multiDelWorkPlan.do',
					grid : this.gridPanel,
					idName : 'planId'
				});
	},
	// 刷新gridPanel
	reloadType : function() {
		this.gridPanel.getStore().reload();
	},
	// 是否生效
	effice : function(value, metadata, record, rowIndex, colIndex) {
		var startTime = new Date(getDateFromFormat(value, "yyyy-MM-dd H:mm:ss"));
		var endTime = new Date(getDateFromFormat(record.data.endTime,
				"yyyy-MM-dd H:mm:ss"));
		var today = new Date();
		if (startTime > today) {
			return '<a style="color:blue;">未生效</a>';
		} else if (startTime <= today && endTime >= today) {
			return '<a style="color:green;">已生效</a>';
		} else if (endTime < today) {
			return '<a style="color:red;">已失效</a>';
		}
	},
	// 行的Action
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.removeRs.call(this, record);
				break;
			case 'btn-detail' :
				this.detail.call(this, record);
				break;
			case 'btn-edit' :
				this.editRecord.call(this, record);
				break;
			default :
				break;
		}
	}
});