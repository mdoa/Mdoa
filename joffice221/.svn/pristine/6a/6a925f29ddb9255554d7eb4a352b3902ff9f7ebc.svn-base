Ext.ns('HolidayRecordView');
/**
 * 假期设置列表
 */
HolidayRecordView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		HolidayRecordView.superclass.constructor.call(this, {
					id : 'HolidayRecordView',
					title : '假期设置列表',
					region : 'center',
					iconCls : 'menu-holidayRecord',
					layout : 'border',
					autoScroll : true,
					items : [this.searchPanel, this.gridPanel]
				});
	},// end of constructor
	// 初始化组件
	initUIComponents : function() {
		// 初始化搜索条件Panel
		this.searchPanel = new HT.SearchPanel({   
					region : 'north',
					layout : 'form',
					colNums : 7,
					keys : [{
								key : Ext.EventObject.ENTER,
								fn : this.search,
								scope : this
							}, {
								key : Ext.EventObject.ESC,
								fn : this.reset,
								scope : this
							}],
					labelWidth : 145,		
					items : [{
								fieldLabel : '请输入查询条件:开始日期',
								xtype : 'datefield',
								name : 'Q_startTime_D_GE',
								format : 'Y-m-d',
								editable : false
							}, {
								fieldLabel : '结束日期',
								xtype : 'datefield',
								name : 'Q_endTime_D_LE',
								format : 'Y-m-d',
								labelWidth : 55,	
								editable : false
							}, {
								fieldLabel : '所属用户',
								xtype : 'textfield',
								readOnly : true,
								name : 'HD_fullname',
								labelWidth : 55,	
								width : 80
							}, {
								xtype : 'button',
								text : '选择',
								iconCls : 'btn-select',
								width : 50,
								scope : this,
								handler : this.selector
							}, {
								fieldLabel : '全公司假期',
								hiddenName : 'Q_isAll_SN_EQ',
								xtype : 'combo',
								mode : 'local',
								editable : true,
								labelWidth : 75,
								width : 75,
								triggerAction : 'all',
								store : [['1', '是'], ['0', '否']]
							}, {
								xtype : 'button',
								text : '查询',
								iconCls : 'search',
								scope : this,
								handler : this.search
							}, {
								xtype : 'button',
								text : '清空',
								iconCls : 'reset',
								scope : this,
								handler : this.reset
							}, {
								xtype : 'hidden',
								name : 'Q_userId_L_EQ'
							}]
				});

		//顶端栏目条
		this.topbar = new Ext.Toolbar({
					items : [{
								iconCls : 'btn-add',
								text : '添加假期设置',
								xtype : 'button',
								scope : this,
								handler : this.createRs
							}, '-', {
								iconCls : 'btn-del',
								text : '删除假期设置',
								xtype : 'button',
								scope : this,
								handler : this.removeSelRs
							}]
				});

		// 假期设置面板
		this.gridPanel = new HT.GridPanel({
					region : 'center',
					tbar : this.topbar,
					sort : [{field:"recordId",direction:"DESC"}],
					// 使用RowActions
					rowActions : true,
					url : __ctxPath + '/personal/listHolidayRecord.do',
					fields : [{
								name : 'recordId',
								type : 'int'
							}, 'fullname', 'startTime', 'endTime', 'userId',
							'isAll'],
					columns : [{
								header : 'recordId',
								dataIndex : 'recordId',
								hidden : true
							}, {
								header : '开始日期',
								dataIndex : 'startTime'
							}, {
								header : '结束日期',
								dataIndex : 'endTime'
							}, {
								header : '所属用户',
								dataIndex : 'fullname'
							}, {
								header : '全公司假期',
								dataIndex : 'isAll',
								renderer : function(val) {
									if (val == 1) {
										return '是';
									} else {
										return '否';
									}
								}
							}, new Ext.ux.grid.RowActions({
										header : '管理',
										width : 100,
										actions : [{
													iconCls : 'btn-del',
													qtip : '删除',
													style : 'margin:0 3px 0 3px'
												}, {
													iconCls : 'btn-edit',
													qtip : '编辑',
													style : 'margin:0 3px 0 3px'
												}],
										listeners : {
											scope : this,
											'action' : this.onRowAction
										}
									})]
				});
		this.gridPanel.addListener({scope:this,'rowdblclick': this.rowClick});
	},// end of the initComponents()
	// 重置查询表单
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
	// 人员选择器
	selector : function() {
		var searchPanel = this.searchPanel;
		new UserDialog({
			scope : this,
			single : true,
		    callback : function(ids, names) {
				searchPanel.getCmpByName('Q_userId_L_EQ').setValue(ids);
				searchPanel.getCmpByName('HD_fullname').setValue(names);
		}}).show();// true表示单选
	},
	// GridPanel行点击处理事件
	rowClick : function(grid, rowindex, e) {
		var rec = grid.getStore().getAt(rowindex);
		this.editRs.call(this, rec);
		
	},
	// 创建记录
	createRs : function() {
		new HolidayRecordForm({
	        scope:this,
			callback : this.reloadType
		}).show();
	},

	// 按ID删除记录
	removeRs : function(id) {
		$postDel({
					url : __ctxPath + '/personal/multiDelHolidayRecord.do',
					ids : id,
					grid : this.gridPanel
				});
	},
	// 把选中ID删除
	removeSelRs : function() {
		$delGridRs({
					url : __ctxPath + '/personal/multiDelHolidayRecord.do',
					grid : this.gridPanel,
					idName : 'recordId'
				});
	},
	// 编辑Rs
	editRs : function(record) {
		new HolidayRecordForm({
					recordId : record.data.recordId,
					scope:this,
					callback : this.reloadType
				}).show();
	},
	// 刷新gridPanel
	reloadType : function() {
		this.gridPanel.getStore().reload();
	},
	// 行的Action
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.removeRs.call(this, record.data.recordId);
				break;
			case 'btn-edit' :
				this.editRs.call(this, record);
				break;
			default :
				break;
		}
	}
});
