Ext.ns('StandSalaryView');
/**
 * [StandSalary]列表
 */
StandSalaryView = Ext.extend(Ext.Panel, {
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		StandSalaryView.superclass.constructor.call(this, {
					id : 'StandSalaryView',
					title : '薪酬标准列表',
					iconCls : 'menu-salary',
					region : 'center',
					layout : 'border',
					items : [this.searchPanel, this.gridPanel]
				});
	},// end of constructor
	// 初始化组件
	initUIComponents : function() {
		// 搜索面板
		this.searchPanel = new HT.SearchPanel({
					layout : 'form',
					region : 'north',
					colNums : 5,
					keys : {
						key : Ext.EventObject.ENTER,
						fn : this.search,
						scope : this
					},
					labelWidth : 135, // 一个bug
					items : [{
								fieldLabel : '查询条件：标准编号',
								xtype : 'textfield',
								name : 'Q_standardNo_S_LK',
								maxLength : 150
							}, {
								fieldLabel : '标准名称',
								xtype : 'textfield',
								name : 'Q_standardName_S_LK',
								maxLength : 125,
								labelWidth : 70
							}, {
								fieldLabel : '状态',
								xtype : 'combo',
								hiddenName : 'Q_status_SN_EQ',
								maxLength : 125,
								labelWidth : 70,
								mode : 'local',
								triggerAction : 'all',
								store : [['', '　'], ['0', '未审核'],
										['1', '审核通过'], ['2', '审核未通过']]
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
							}]
				});
		this.gridPanel = new HT.GridPanel({
			tbar : [{
						iconCls : 'btn-add',
						text : '添加标准',
						scope : this,
						hidden : !isGranted('_StandSalaryAdd'),
						handler : this.toolbarAddClick
					}, {
						iconCls : 'btn-del',
						text : '删除标准',
						scope : this,
						hidden : !isGranted('_StandSalaryDel'),
						handler : this.toolbarDelClick
					}],
			trackMouseOver : true,
			disableSelection : false,
			rowActions : true,
			loadMask : true,
			region : 'center',
			url : __ctxPath + '/hrm/listStandSalary.do',
			fields : [{
						name : 'standardId',
						type : 'int'
					}, 'standardNo', 'standardName', 'totalMoney',
					'setdownTime', 'status'],
			columns : [{
						header : 'standardId',
						dataIndex : 'standardId',
						hidden : true
					}, {
						header : '标准编号',
						dataIndex : 'standardNo'
					}, {
						header : '标准名称',
						dataIndex : 'standardName'
					}, {
						header : '总金额',
						dataIndex : 'totalMoney',
						renderer : function(value) {
							return '<img src="' + __ctxPath
									+ '/images/flag/customer/rmb.png"/>'
									+ value;
						}
					}, {
						header : '制定日期',
						dataIndex : 'setdownTime',
						renderer : function(value) {
							return value.substring(0, 10);
						}
					}, {
						header : '状态',
						dataIndex : 'status',
						renderer : function(value) {
							switch (value) {
								case 0 :
									return '未审核';
								case 1 :
									return '<img title="通过审核" src="'
											+ __ctxPath
											+ '/images/flag/customer/effective.png"/>';
								case 2 :
									return '<img title="没通过审核" src="'
											+ __ctxPath
											+ '/images/flag/customer/invalid.png"/>';
								default :
									return '';

							}
						}
					}, new Ext.ux.grid.RowActions({
						header : '管理',
						width : 100,
						actions : [{
									iconCls : 'btn-del',
									qtip : '删除',
									style : 'margin:0 3px 0 3px',
									fn : function(record) {
										if (isGranted('_StandSalaryDel'))
											return true;
										return false;
									}
								}, {
									iconCls : 'btn-salary-apply',
									qtip : '审核',
									style : 'margin:0 3px 0 3px',
									fn : function(record) {
										if (record.data.status != 1
												&& isGranted('_StandSalaryCheck'))
											return true;
										return false;
									}
								}, {
									iconCls : 'btn-edit',
									qtip : '编辑',
									style : 'margin:0 3px 0 3px',
									fn : function(record) {
										if (record.data.status != 1
												&& isGranted('_StandSalaryEdit'))
											return true;
										return false;
									}
								}, {
									iconCls : 'btn-operation',
									qtip : '操作记录',
									style : 'margin:0 3px 0 3px',
									fn : function(record) {
										if (isGranted('_StandSalaryQuery'))
											return true;
										return false;
									}
								}],
						listeners : {
							scope : this,
							'action' : this.onRowAction
						}
					})],
			listeners : {
				scope : this,
				'rowdblclick' : this.rowdblclick
			}
		});
	},// end initUIComponents
	/**
	 * 查询
	 */
	search : function() {
		$search({
					searchPanel : this.searchPanel,
					gridPanel : this.gridPanel
				});
	},
	/**
	 * 重置(清空)查询表单
	 */
	reset : function() {
		this.searchPanel.getForm().reset();
	},
	/**
	 * grid双击事件
	 */
	rowdblclick : function(grid, rowindex, e) {
		grid.getSelectionModel().each(function(rec) {
					if (rec.data.status != 1 && isGranted('_StandSalaryEdit')) {
						this.edit(rec.data.standardId);
					}
				}, this);
	},
	/**
	 * 刷新gridPanel
	 */
	reloadGridPanel : function() {
		this.gridPanel.getStore().reload();
	},
	/**
	 * toolbar添加处理函数
	 */
	toolbarAddClick : function() {
		// 只允许有一个编辑窗口
		var tabs = Ext.getCmp('centerTabPanel');
		var edit = Ext.getCmp('StandSalaryForm');
		if (!Ext.isEmpty(edit)) {
			tabs.remove('StandSalaryForm');
		}
		edit = new StandSalaryForm();
		tabs.add(edit);
		tabs.activate(edit);
	},
	/**
	 * toolbar删除处理函数
	 */
	toolbarDelClick : function() {
		$delGridRs({
					url : __ctxPath + '/hrm/multiDelStandSalary.do',
					grid : this.gridPanel,
					idName : 'standardId'
				});
	},
	/**
	 * 删除单个记录
	 */
	remove : function(id) {
		$postDel({
					url : __ctxPath + '/hrm/multiDelStandSalary.do',
					ids : id,
					grid : this.gridPanel
				});
	},
	/**
	 * 编辑
	 */
	edit : function(id) {
		// 只允许有一个编辑窗口
		var tabs = Ext.getCmp('centerTabPanel');
		var edit = Ext.getCmp('StandSalaryForm');
		if (!Ext.isEmpty(edit)) {
			tabs.remove('StandSalaryForm');
		}
		edit = new StandSalaryForm({
					standardId : id
				});
		tabs.add(edit);
		tabs.activate(edit);
	},
	/**
	 * 标准审核
	 */
	check : function(id) {
		new CheckStandSalaryForm({
					standardId : id,
					scope : this,
					callback : this.reloadGridPanel
				}).show();
	},
	/**
	 * 显示薪酬标准的操作纪录
	 * 
	 * @param {}
	 *            id
	 */
	operation : function(id) {

		new OperationRecord({
					title : '标准操作记录',
					url : __ctxPath
							+ '/pages/hrm/standOperation.jsp?standardId=' + id
				}).show();
	},
	/**
	 * 行的Action
	 */
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.remove(record.data.standardId);
				break;
			case 'btn-edit' :
				this.edit(record.data.standardId);
				break;
			case 'btn-salary-apply' :
				this.check(record.data.standardId);
				break;
			case 'btn-operation' :
				this.operation(record.data.standardId);
				break;
			default :
				break;
		}
	}
});