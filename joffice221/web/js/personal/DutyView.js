Ext.ns('DutyView');
/**
 * 排班列表
 */
DutyView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		DutyView.superclass.constructor.call(this, {
					id : 'DutyView',
					title : '排班列表',
					region : 'center',
					iconCls : 'menu-duty',
					layout : 'border',
					autoScroll : true,
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
					labelWidth : 120,
					items : [{
								fieldLabel : '请输入查询条件:    姓名',
								xtype : 'textfield',
								width : 100,
								name : 'Q_fullname_S_LK'
							},{
								fieldLabel : '班制',
								xtype : 'textfield',
								width : 100,
								labelWidth : 40,
								name : 'Q_dutySystem.systemName_S_LK'
							},{
								fieldLabel : '开始时间',
								xtype : 'datefield',
								width : 100,
								labelWidth : 60,
								format : 'Y-m-d',
								name : 'Q_startTime_D_GE'
							},{
								fieldLabel : '结束时间',
								xtype : 'datefield',
								width : 100,
								labelWidth : 60,
								format : 'Y-m-d',
								name : 'Q_endTime_D_LE'
							}, {
								xtype : 'button',
								text : '查询',
								iconCls : 'search',
								scope:this,
								handler : this.search
							}, {
								xtype : 'button',
								text : '清空',
								iconCls : 'reset',
								scope:this,
								handler : this.reset
							}]
				});

				//顶端栏目条
		this.topbar = new Ext.Toolbar({
					items : [{
								iconCls : 'btn-add',
								text : '添加排班',
								xtype : 'button',
								scope : this,
								handler : this.createRs
							}, '-', {
								iconCls : 'btn-del',
								text : '删除排班',
								xtype : 'button',
								scope : this,
								handler : this.removeSelRs
							}]
				});

		// 排班管理面板
		this.gridPanel = new HT.GridPanel({
					region : 'center',
					tbar : this.topbar,
					sort : [{field:"dutyId",direction:"DESC"}],
					rowActions : true,
					url : __ctxPath + '/personal/listDuty.do',
					fields : [{
								name : 'dutyId',
								type : 'int'
							}, 'userId', 'fullname', 'dutySystem', 'appUser',
							'startTime', 'endTime'],
					columns : [{
								header : 'dutyId',
								dataIndex : 'dutyId',
								hidden : true
							}, {
								header : '姓名 ',
								dataIndex : 'fullname'
							}, {
								header : '部门',
								dataIndex : 'appUser',
								renderer : function(val) {
									return val.department == null? '': val.department.depName;
								}
							}, {
								header : '班制',
								dataIndex : 'dutySystem',
								renderer : function(val) {
									return val.systemName;
								}
							}, {
								header : '开始时间',
								dataIndex : 'startTime'
							}, {
								header : '结束时间',
								dataIndex : 'endTime'
							}, new Ext.ux.grid.RowActions({
										header : '管理',
										width : 100,
										actions : [{
											iconCls : 'btn-del',
											qtip : '删除',
											style : 'margin:0 3px 0 3px',
											fn : function(record) {
												if (isGranted('_DutyDel'))
													return true;
												return false;
											}
										}, {
											iconCls : 'btn-edit',
											qtip : '详细',
											style : 'margin:0 3px 0 3px',
											fn : function(record) {
												if (isGranted('_DutyEdit'))
													return true;
												return false;
											}
										}],
										listeners : {
											scope : this,
											'action' : this.onRowAction
										}
									})]
				});

		this.gridPanel.addListener({scope:this,'rowdblclick': this.rowClick});

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
		this.editRs.call(this, rec);
	},

	// 创建记录
	createRs : function() {
		new DutyForm({
			scope : this,
			callback : this.reloadType
		}).show();
	},

	// 刷新gridPanel
	reloadType : function() {
		this.gridPanel.getStore().reload();
	},

	// 按ID删除记录
	removeRs : function(id) {
		$postDel({
			url : __ctxPath + '/personal/multiDelDuty.do',
			ids : id,
			grid : this.gridPanel
		});
	},

	// 把选中ID删除
	removeSelRs : function() {
		$delGridRs({
			url : __ctxPath + '/personal/multiDelDuty.do',
			grid : this.gridPanel,
			idName : 'dutyId'
		});
	},

	// 编辑Rs
	editRs : function(record) {
		new DutyForm({
			dutyId : record.data.dutyId,
			scope : this,
			callback : this.reloadType
		}).show();
	},

	// 行的Action
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.removeRs.call(this, record.data.dutyId);
				break;
			case 'btn-edit' :
				this.editRs.call(this, record);
				break;
			default :
				break;
		}
	}

});