/**
 * @author:lyy
 * @class JobChangeView
 * @extends Ext.Panel
 * @description [JobChange]管理
 * @company 杭州梦德软件有限公司
 * @createtime:2010-01-16
 */
Ext.ns('JobChangeView');
JobChangeView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		if (_cfg == null) {
			_cfg = {};
		}
		Ext.apply(this, _cfg);
		// 初始化组件
		this.initComponents();
		// 调用父类构造
		JobChangeView.superclass.constructor.call(this, {
					id : 'JobChangeView',
					title : '职位调动管理',
					region : 'center',
					iconCls : 'menu-jobchange',
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
								fieldLabel : '姓名',
								xtype : 'textfield',
								name : 'Q_userName_S_LK',
								maxLength : 125,
								labelWidth : 70
							}, {
								fieldLabel : '原职位',
								xtype : 'textfield',
								name : 'Q_orgJobName_S_LK',
								maxLength : 125,
								labelWidth : 70
							}, {
								fieldLabel : '新职位',
								xtype : 'textfield',
								name : 'Q_newJobName_S_LK',
								maxLength : 125,
								labelWidth : 70
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
			region : 'center',
			stripeRows : true,
			tbar : [{
						iconCls : 'btn-add',
						text : '登记',
						scope : this,
						hidden : !isGranted('_JobChangeAdd'),
						handler : this.createRecord
					}, {
						iconCls : 'btn-del',
						text : '删除',
						handler : this.delRecords,
						hidden : !isGranted('_JobChangeDel'),
						scope : this
					}],
			trackMouseOver : true,
			disableSelection : false,
			loadMask : true,
			rowActions : true,
			url : __ctxPath + "/hrm/listJobChange.do",
			sort : [{
						field : "changeId",
						direction : "desc"
					}],
			fields : [{
						name : 'changeId',
						type : 'int'
					}, 'profileId', 'profileNo', 'userName', 'orgJobId',
					'orgJobName', 'newJobId', 'newJobName', 'orgStandardNo',
					'orgStandardName', 'orgDepId', 'orgDepName',
					'orgTotalMoney', 'newStandardNo', 'newStandardName',
					'newDepId', 'newDepName', 'newTotalMoney', 'changeReason',
					'regName', 'regTime', 'checkName', 'checkTime',
					'checkOpinion', 'status', 'memo'],
			columns : [{
						header : 'changeId',
						dataIndex : 'changeId',
						hidden : true
					}, {
						header : '档案编号',
						dataIndex : 'profileNo'
					}, {
						header : '姓名',
						dataIndex : 'userName'
					}, {
						header : '原职位名称',
						dataIndex : 'orgJobName'
					}, {
						header : '新职位名称',
						dataIndex : 'newJobName'
					}, {
						header : '原部门名称',
						dataIndex : 'orgDepName'
					}, {
						header : '新部门名称',
						dataIndex : 'newDepName'
					}, {
						header : '登记人',
						dataIndex : 'regName'
					}, {
						header : '登记时间',
						dataIndex : 'regTime'
					}, {
						header : '状态',
						dataIndex : 'status',
						renderer : function(value) {
							if (value == -1) {
								return '<font color="red">草稿</font>';
							} else if (value == 1) {
								return '<img title="通过审核" src="'
										+ __ctxPath
										+ '/images/flag/customer/effective.png"/>';
							} else if (value == 2) {
								return '<img title="没通过审核" src="'
										+ __ctxPath
										+ '/images/flag/customer/invalid.png"/>';
							} else {
								return '<font color="green">提交审核</font>';

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
												if (isGranted('_JobChangeDel'))
													return true;
												return false;
											}
										}, {
											iconCls : 'btn-edit',
											qtip : '编辑',
											style : 'margin:0 3px 0 3px',
											fn : function(record) {
												if (isGranted('_SalaryItemEdit')
														&& record.data.status != 1
														&& record.data.status != 2)
													return true;
												return false;
											}
										}, {
											iconCls : 'btn-empProfile-check',
											qtip : '审核',
											style : 'margin:0 3px 0 3px',
											fn : function(record) {
												if (record.data.status != -1
														&& record.data.status != 1
														&& record.data.status != 2)
													return true;
												return false;
											}
										}, {
											iconCls : 'btn-readdocument',
											qtip : '查看',
											style : 'margin:0 3px 0 3px',
											fn : function(record) {
												if (isGranted('_JobChangeQuery'))
													return true;
												return false;
											}
										}, {
											iconCls : 'btn-operation',
											qtip : '查看操作记录',
											style : 'margin:0 3px 0 3px',
											fn : function(record) {
												if (isGranted('_JobChangeQuery'))
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
	 * rowdblClick处理函数
	 */
	rowdblClick : function(grid, rowindex, e) {
		grid.getSelectionModel().each(function(rec) {
					var id = rec.data.changeId;
					var status = rec.data.status;
					if (status == 0 && isGranted('_JobChangeEdit')) {
						this.edit(id);
					} else {
						if (isGranted('_JobChangeQuery'))
							this.look(id);
					}
				}, this);
	},
	/**
	 * 
	 * @param {}
	 *            self 当前窗体对象
	 */
	search : function(self) {
		$search({
					searchPanel : this.searchPanel,
					gridPanel : this.gridPanel
				});
	},
	/**
	 * 添加记录
	 */
	createRecord : function() {
		// new JobChangeForm().show();
		var tabs = Ext.getCmp('centerTabPanel');
		var JobChangeFormPanel = Ext.getCmp('JobChangeForm');
		if (JobChangeFormPanel != null) {
			tabs.remove('JobChangeForm');
		}
		JobChangeFormPanel = new JobChangeForm();
		tabs.add(JobChangeFormPanel);
		tabs.activate(JobChangeFormPanel);
	},
	/**
	 * 删除多条记录
	 */
	delRecords : function() {
		$delGridRs({
					url : __ctxPath + '/hrm/multiDelJobChange.do',
					grid : this.gridPanel,
					idName : 'changeId'
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
		var JobChangeFormPanel = Ext.getCmp('JobChangeForm');
		if (JobChangeFormPanel != null) {
			tabs.remove('JobChangeForm');
		}
		JobChangeFormPanel = new JobChangeForm({
					changeId : id
				});
		tabs.add(JobChangeFormPanel);
		tabs.activate(JobChangeFormPanel);
	},
	/**
	 * 删除
	 * 
	 * @param {}
	 *            id
	 */
	remove : function(id) {
		$postDel({
					url : __ctxPath + '/hrm/multiDelJobChange.do',
					ids : id,
					grid : this.gridPanel
				});
	},
	/**
	 * 刷新gridPanel
	 */
	reloadGridPanel : function() {
		this.gridPanel.getStore().reload();
	},
	/**
	 * 审核
	 * 
	 * @param {}
	 *            id
	 */
	check : function(id) {
		new CheckJobChangeWin({
					changeId : id,
					check : false,
					scope : this,
					callback : this.reloadGridPanel
				}).show();
	},
	/**
	 * 操作记录
	 * 
	 * @param {}
	 *            id
	 */
	operation : function(id) {
		new OperationRecord({
					id : 'JobChangeViewOperationWin',
					title : '职位调动操作纪录',
					url : __ctxPath
							+ '/pages/hrm/jobChangeOperation.jsp?changeId='
							+ id
				}).show();
	},
	/**
	 * 查看详细信息
	 * 
	 * @param {}
	 *            id
	 */
	look : function(id) {
		new CheckJobChangeWin({
					changeId : id,
					check : true
				}).show();
	},
	/**
	 * 行的Action
	 */
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.remove(record.data.changeId);
				break;
			case 'btn-edit' :
				this.edit(record.data.changeId);
				break;
			case 'btn-empProfile-check' :
				this.check(record.data.changeId);
				break;
			case 'btn-readdocument' :
				this.look(record.data.changeId);
				break;
			case 'btn-operation' :
				this.operation(record.data.changeId);
				break;
			default :
				break;
		}
	}
});
