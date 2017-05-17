/**
 * @author:
 * @class SalaryPayoffView
 * @extends Ext.Panel
 * @description 薪酬发放管理
 * @company 杭州梦德软件有限公司
 * @createtime:2010-01-16
 */
Ext.ns('SalaryPayoffView');

SalaryPayoffView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		// 初始化组件
		this.initComponents();
		// 调用父类构造
		SalaryPayoffView.superclass.constructor.call(this, {
					id : 'SalaryPayoffView',
					title : '薪酬发放审核',
					region : 'center',
					iconCls : 'menu-check-salay',
					layout : 'border',
					items : [this.searchPanel, this.gridPanel]
				});
	},// end of constructor

	// 初始化组件
	initComponents : function() {
		// 搜索面板
		this.searchPanel = new HT.SearchPanel({
					layout : 'form',
					region : 'north',
					colNums : 6,
					keys : {
						key : Ext.EventObject.ENTER,
						fn : this.search,
						scope : this
					},
					labelWidth : 135, // 一个bug
					items : [{
								fieldLabel : '查询条件：员工姓名',
								xtype : 'textfield',
								name : 'Q_fullname_S_LK',
								maxLength : 150
							}, {
								fieldLabel : '审核状态',
								xtype : 'combo',
								hiddenName : 'Q_checkStatus_SN_EQ',
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
							}, {
								name : 'Q_delFlag_SN_EQ',
								width : 80,
								xtype : 'hidden',
								value : 0
							}]
				});
		this.gridPanel = new HT.GridPanel({
			region : 'center',
			stripeRows : true,
			tbar : [{
						iconCls : 'btn-add',
						text : '登记',
						scope : this,
						hidden : !isGranted('_SalaryPayoffAdd'),
						handler : this.createRecord
					}, {
						iconCls : 'btn-del',
						text : '删除',
						handler : this.delRecords,
						hidden : !isGranted('_SalaryPayoffDel'),
						scope : this
					}],
			rowActions : true,
			url : __ctxPath + "/hrm/listSalaryPayoff.do",
			fields : [{
						name : 'recordId',
						type : 'int'
					}, 'fullname', 'userId', 'profileNo', 'idNo',
					'standAmount', 'encourageAmount', 'deductAmount',
					'achieveAmount', 'encourageDesc', 'deductDesc', 'memo',
					'acutalAmount', 'regTime', 'register', 'checkName',
					'checkTime', 'checkStatus', 'startTime', 'endTime',
					'standardId'],
			columns : [{
						header : 'recordId',
						dataIndex : 'recordId',
						hidden : true
					}, {
						header : '员工姓名',
						dataIndex : 'fullname'
					}, {
						header : '档案编号',
						dataIndex : 'profileNo'
					}, {
						header : '身份证号',
						dataIndex : 'idNo'
					}, {
						header : '薪标金额',
						dataIndex : 'standAmount',
						renderer : function(value) {
							return '<img src="' + __ctxPath
									+ '/images/flag/customer/rmb.png"/>'
									+ value;
						}
					}, {
						header : '实发金额',
						dataIndex : 'acutalAmount',
						renderer : function(value) {
							return '<img src="' + __ctxPath
									+ '/images/flag/customer/rmb.png"/>'
									+ value;
						}
					}, {
						header : '登记时间',
						dataIndex : 'regTime',
						renderer : function(value) {
							return value.substring(0, 10);
						}
					}, {
						header : '审批状态',
						dataIndex : 'checkStatus',
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
										if (isGranted('_SalaryPayoffDel'))
											return true;
										return false;
									}
								}, {
									iconCls : 'btn-edit',
									qtip : '编辑',
									style : 'margin:0 3px 0 3px',
									fn : function(record) {
										if (isGranted('_EmpProfileEdit')
												&& record.data.checkStatus == 0)
											return true;
										return false;
									}
								}, {
									iconCls : 'btn-empProfile-check',
									qtip : '审核',
									style : 'margin:0 3px 0 3px',
									fn : function(record) {
										if (isGranted('_SalaryPayoffCheck')
												&& record.data.checkStatus == 0)
											return true;
										return false;
									}
								}, {
									iconCls : 'btn-readdocument',
									qtip : '查看',
									style : 'margin:0 3px 0 3px',
									fn : function(record) {
										if (isGranted('_SalaryPayoffQuery')
												&& record.data.checkStatus != 0)
											return true;
										return false;
									}
								}, {
									iconCls : 'btn-operation',
									qtip : '查看操作记录',
									style : 'margin:0 3px 0 3px',
									fn : function(record) {
										if (isGranted('_SalaryPayoffQuery'))
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
				'rowdblclick' : this.rowdblClick
			}
		});
	},// end of the initComponents()
	/**
	 * 行双击处理函数
	 * 
	 * @param grid
	 * @param rowindex
	 * @param e
	 */
	rowdblClick : function(grid, rowindex, e) {
		grid.getSelectionModel().each(function(rec) {
					var id = rec.data.recordId;
					if (rec.data.checkStatus == 0
							&& isGranted('_SalaryPayoffEdit')) {
						this.edit(id);
					} else {
						this.look(id);
					}
				}, this);
	},
	/**
	 * 
	 * @param {}
	 *            self 当前窗体对象
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
	 * 刷新gridPanel
	 */
	reloadGridPanel : function() {
		this.gridPanel.getStore().reload();
	},
	/**
	 * 添加记录
	 */
	createRecord : function() {
		var tabs = Ext.getCmp('centerTabPanel');
		var salaryPayoffForm = Ext.getCmp('SalaryPayoffForm');
		if (SalaryPayoffForm != null) {
			tabs.remove('SalaryPayoffForm');
		}
		salaryPayoffForm = new SalaryPayoffForm();
		tabs.add(salaryPayoffForm);
		tabs.activate(salaryPayoffForm);
	},
	/**
	 * 删除多条记录
	 */
	delRecords : function() {
		$delGridRs({
					url : __ctxPath + '/hrm/multiDelSalaryPayoff.do',
					grid : this.gridPanel,
					idName : 'recordId'
				});
	},
	/**
	 * 编辑
	 * 
	 * @param {}
	 *            id
	 */
	edit : function(id) {
		var tabs = Ext.getCmp('centerTabPanel');
		var salaryPayoffForm = Ext.getCmp('SalaryPayoffForm');
		if (salaryPayoffForm != null) {
			tabs.remove('SalaryPayoffForm');
		}
		salaryPayoffForm = new SalaryPayoffForm({
					recordId : id
				});
		tabs.add(salaryPayoffForm);
		tabs.activate(salaryPayoffForm);
	},
	/**
	 * 删除
	 * 
	 * @param {}
	 *            id
	 */
	remove : function(id) {
		$postDel({
					url : __ctxPath + '/hrm/multiDelSalaryPayoff.do',
					ids : id,
					grid : this.gridPanel
				});
	},

	/**
	 * 审核
	 * 
	 * @param {}
	 *            id
	 */
	check : function(id) {
		new CheckSalaryPayoffForm({
					recordId : id,
					scope : this,
					callback : this.reloadGridPanel
				}).show();
	},
	/**
	 * 查看操作记录
	 * 
	 * @param {}
	 *            id
	 */
	operation : function(id) {
		new OperationRecord({
			title : '薪酬发放操作纪录',
			url : __ctxPath
							+ '/pages/hrm/salaryPayoffOperation.jsp?recordId='
							+ id
		}).show();
	},

	/**
	 * 查看发放信息
	 * 
	 * @param {}
	 *            id
	 */
	look : function(id) {
		var win = new CheckSalaryPayoffForm({
					recordId : id
				}).show();
		win.setTitle('发放详细信息');
		win.formPanel.hide();
		Ext.getCmp('salaryPayoffbtnY').hide();
		Ext.getCmp('salaryPayoffbtnN').hide();
	},
	/**
	 * 行的Action
	 */
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.remove(record.data.recordId);
				break;
			case 'btn-edit' :
				this.edit(record.data.recordId);
				break;
			case 'btn-empProfile-check' :
				this.check(record.data.recordId);
				break;
			case 'btn-readdocument' :
				this.look(record.data.recordId);
				break;
			case 'btn-operation' :
				this.operation(record.data.recordId);
				break;
			default :
				break;
		}
	}
});
