/**
 * @author:
 * @class EmpProfileView
 * @extends Ext.Panel
 * @description 档案管理
 * @company 杭州梦德软件有限公司
 * @createtime:2010-01-16
 */
Ext.ns('EmpProfileView');
EmpProfileView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		// 初始化组件
		this.initComponents();
		// 调用父类构造
		EmpProfileView.superclass.constructor.call(this, {
					id : 'EmpProfileView',
					title : '档案管理',
					iconCls : 'menu-profile',
					region : 'center',
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
								fieldLabel : '查询条件：档案编号',
								xtype : 'textfield',
								name : 'Q_profileNo_S_LK',
								maxLength : 150
							}, {
								fieldLabel : '员工姓名',
								xtype : 'textfield',
								name : 'Q_fullname_S_LK',
								maxLength : 125,
								labelWidth : 70
							}, {
								fieldLabel : '身份证号',
								xtype : 'textfield',
								name : 'Q_idCard_S_LK',
								maxLength : 125,
								labelWidth : 70
							}, {
								fieldLabel : '审核状态',
								xtype : 'combo',
								hiddenName : 'Q_approvalStatus_SN_EQ',
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
			tbar : [{
						iconCls : 'btn-add',
						text : '添加档案',
						scope : this,
						handler : this.createRecord,
						hidden : !isGranted('_EmpProfileAdd')
					}, {
						iconCls : 'btn-del',
						text : '删除档案',
						handler : this.delRecords,
						scope : this,
						hidden : !isGranted('_EmpProfileDel')
					}, {
						iconCls : 'btn-empProfile-recovery',
						text : '恢复档案',
						scope : this,
						handler : this.recovery,
						hidden : !isGranted('_EmpProfileRec')
					}],
			url : __ctxPath + "/hrm/listEmpProfile.do",
			baseParams : {
				"Q_delFlag_SN_EQ" : 0
			},// 只查询未被删除的档案
			rowActions : true,
			fields : [{
						name : 'profileId',
						type : 'int'
					}, 'profileNo', 'fullname', 'designation', 'creator',
					'createtime', 'approvalStatus', 'memo', 'depName'],

			columns : [{
						header : 'profileId',
						dataIndex : 'profileId',
						hidden : true
					}, {
						header : '档案编号',
						dataIndex : 'profileNo'
					}, {
						header : '员工姓名',
						dataIndex : 'fullname'
					}, {
						header : '建档人',
						dataIndex : 'creator'
					}, {
						header : '建档时间',
						dataIndex : 'createtime',
						renderer : function(value) {
							return value.substring(0, 10);
						}
					}, {
						header : '部门或公司',
						dataIndex : 'depName'
					}, {
						header : '职称',
						dataIndex : 'designation'
					}, {
						header : '审核状态',// 0=未删除 1=删除',
						dataIndex : 'approvalStatus',
						renderer : function(value) {
							if (value == '0') {
								return '未审核';
							} else if (value == '1') {
								return '<img title="通过审核" src="'
										+ __ctxPath
										+ '/images/flag/customer/effective.png"/>';
							} else {
								return '<img title="没通过审核" src="'
										+ __ctxPath
										+ '/images/flag/customer/invalid.png"/>';
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
										if (isGranted('_EmpProfileDel'))
											return true;
										return false;
									}
								}, {
									iconCls : 'btn-edit',
									qtip : '编辑',
									style : 'margin:0 3px 0 3px',
									fn : function(record) {
										if (isGranted('_EmpProfileEdit')
												&& record.data.approvalStatus != 1)
											return true;
										return false;
									}
								}, {
									iconCls : 'btn-empProfile-check',
									qtip : '审核',
									style : 'margin:0 3px 0 3px',
									fn : function(record) {
										if (record.data.approvalStatus != 1
												&& record.data.approvalStatus != 2)
											return true;
										return false;
									}
								}, {
									iconCls : 'btn-readdocument',
									qtip : '查看',
									style : 'margin:0 3px 0 3px',
									fn : function(record) {
										if (isGranted('_EmpProfileQuery')
												&& !(record.data.approvalStatus != 1 && record.data.approvalStatus != 2))
											return true;
										return false;
									}
								}, {
									iconCls : 'btn-operation',
									qtip : '查看操作记录',
									style : 'margin:0 3px 0 3px',
									fn : function(record) {
										if (isGranted('_EmpProfileQuery'))
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
				'rowdblclick' : this.gridRowdblClick
			}
		});
	},// end of the initComponents()
	/**
	 * gridPanel双击处理函数
	 * 
	 * @param grid
	 * @param rowindex
	 * @param e
	 */
	gridRowdblClick : function(grid, rowindex, e) {
		grid.getSelectionModel().each(function(rec) {
					if (isGranted('_EmpProfileEdit')) {
						var id = rec.data.profileId;
						if (rec.data.approvalStatus == 0) {
							this.edit(id);
						} else {
							this.look(id);
						}
					}
				}, this);
	},
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
	 * 刷新gridPanel
	 */
	reloadGridPanel : function() {
		this.gridPanel.getStore().reload();
	},
	/**
	 * 添加记录
	 */
	createRecord : function() {
		// new EmpProfileForm().show();
		var tabs = Ext.getCmp('centerTabPanel');
		var empProfileForm = Ext.getCmp('EmpProfileForm');
		if (empProfileForm != null) {
			tabs.remove('EmpProfileForm');
		}
		empProfileForm = new EmpProfileForm();
		tabs.add(empProfileForm);
		tabs.activate(empProfileForm);
	},
	/**
	 * 删除记录
	 * 
	 * @param {}
	 *            record
	 */
	delRecords : function(record) {
		$delGridRs({
					url : __ctxPath + '/hrm/multiDelEmpProfile.do',
					grid : this.gridPanel,
					idName : 'profileId'
				});
	},
	/**
	 * 恢复档案
	 */
	recovery : function(record) {
		new RecoveryProfileWin({
					scope : this,
					callback : this.reloadGridPanel
				}).show();
	},
	/**
	 * 删除单个记录
	 */
	remove : function(id) {
		$postDel({
					url : __ctxPath + '/hrm/multiDelEmpProfile.do',
					ids : id,
					grid : this.gridPanel
				});
	},
	/**
	 * 编辑记录
	 * 
	 * @param {}
	 *            record
	 */
	edit : function(id) {
		// 只允许有一个编辑窗口
		var tabs = Ext.getCmp('centerTabPanel');
		var edit = Ext.getCmp('EmpProfileForm');
		if (edit == null) {
			edit = new EmpProfileForm({
						profileId : id
					});
			tabs.add(edit);
		} else {
			tabs.remove('EmpProfileForm');
			edit = new EmpProfileForm({
						profileId : id
					});
			tabs.add(edit);
		}
		tabs.activate(edit);

	},
	/**
	 * 审核档案
	 * 
	 * @param {}
	 *            record
	 */
	check : function(id) {
		new CheckEmpProfileForm({
					profileId : id,
					scope : this,
					callback : this.reloadGridPanel
				}).show();

	},
	/**
	 * 查看档案
	 * 
	 * @param {}
	 *            record
	 */
	look : function(id) {
		new CheckEmpProfileForm({
					profileId : id,
					title : '档案详细信息',
					check : true
				}).show();
	},
	/**
	 * 展示操作纪录
	 * 
	 * @param {}
	 *            record
	 */
	operation : function(id) {
		new OperationRecord({
					title : '档案操作记录',
					url : __ctxPath
							+ '/pages/hrm/empProfileOperation.jsp?profileId='
							+ id
				}).show();

	},
	/**
	 * 行的Action
	 */
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.remove(record.data.profileId);
				break;
			case 'btn-edit' :
				this.edit(record.data.profileId);
				break;
			case 'btn-empProfile-check' :
				this.check(record.data.profileId);
				break;
			case 'btn-readdocument' :
				this.look(record.data.profileId);
				break;
			case 'btn-operation' :
				this.operation(record.data.profileId);
				break;
			default :
				break;
		}
	}
});