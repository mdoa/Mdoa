/**
 * @author:
 * @class EmpProfileView
 * @extends Ext.Panel
 * @description 招聘管理
 * @company 杭州梦德软件有限公司
 * @createtime:2010-01-16
 */
Ext.ns('HireIssueView');
HireIssueView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		// 初始化组件
		this.initComponents();
		// 调用父类构造
		HireIssueView.superclass.constructor.call(this, {
					id : 'HireIssueView',
					title : '招聘信息列表',
					iconCls : 'menu-hireIssue',
					layout : 'border',
					autoScroll : true,
					items : [this.searchPanel, this.gridPanel]
				});
	},// end of constructor

	// 初始化组件
	initComponents : function() {
		// 搜索面板
		this.searchPanel = new HT.SearchPanel({
					layout : 'form',
					region : 'north',
					colNums : 4,
					keys : {
						key : Ext.EventObject.ENTER,
						fn : this.search,
						scope : this
					},
					labelWidth : 165, // 一个bug
					items : [{
								fieldLabel : '请输入查询条件：招聘职位',
								xtype : 'textfield',
								name : 'Q_jobName_S_LK',
								maxLength : 150
							}, {
								fieldLabel : '登记人',
								xtype : 'textfield',
								name : 'Q_regFullname_S_LK',
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
			tbar : [{
						iconCls : 'btn-add',
						text : '添加招聘信息',
						scope : this,
						hidden : !isGranted('_HireIssueAdd'),
						handler : this.addHireIssue
					}, {
						iconCls : 'btn-del',
						text : '删除招聘信息',
						scope : this,
						hidden : !isGranted('_HireIssueDel'),
						handler : this.deleClick
					}],
			trackMouseOver : true,
			disableSelection : false,
			sort : [{
						field : "hireId",
						direction : "DESC"
					}],
			region : 'center',
			loadMask : true,
			rowActions : true,
			url : __ctxPath + '/hrm/listHireIssue.do',
			fields : [{
						name : 'hireId',
						type : 'int'
					}, 'title', 'startDate', 'endDate', 'hireCount', 'jobName',
					'jobCondition', 'regFullname', 'regDate', 'modifyFullname',
					'modifyDate', 'checkFullname', 'checkOpinion', 'checkDate',
					'status', 'memo'],
			columns : [{
						header : 'hireId',
						dataIndex : 'hireId',
						hidden : true
					}, {
						header : '标题',
						dataIndex : 'title'
					}, {
						header : '登记人',
						dataIndex : 'regFullname'
					}, {
						header : '招聘职位',
						dataIndex : 'jobName'
					}, {
						header : '登记时间',
						dataIndex : 'regDate'
					}, {
						header : '开始时间',
						dataIndex : 'startDate',
						renderer : function(value) {
							return value.replace(/00:00:00/, '');
						}
					}, {
						header : '结束时间',
						dataIndex : 'endDate',
						renderer : function(value) {
							return value.replace(/00:00:00/, '');
						}
					}, {
						header : '审核状态',
						dataIndex : 'status',
						renderer : function(value) {
							if (value == 0) {
								return '未审核';
							} else if (value == 1) {
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
									iconCls : 'menu-goods-apply',
									qtip : '审核',
									style : 'margin:0 3px 0 3px',
									fn : function(record) {
										if (isGranted('_HireIssueCheck')
												&& record.data.status == 0)
											return true;
										return false;
									}
								}, {
									iconCls : 'btn-readdocument',
									qtip : '查看',
									style : 'margin:0 3px 0 3px',
									fn : function(record) {
										if (isGranted('_HireIssueQuery')
												&& record.data.status != 0)
											return true;
										return false;
									}
								}, {
									iconCls : 'btn-edit',
									qtip : '编辑',
									style : 'margin:0 3px 0 3px',
									fn : function(record) {
										if (isGranted('_HireIssueEdit')
												&& record.data.status != 1)
											return true;
										return false;
									}
								}, {
									iconCls : 'btn-del',
									qtip : '删除',
									style : 'margin:0 3px 0 3px',
									fn : function(record) {
										if (isGranted('_HireIssueDel'))
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
	rowdblClick : function(grid, rowindex, e) {
		grid.getSelectionModel().each(function(rec) {
					var status = rec.data.status;
					if (status != 1) {
						if (isGranted('_HireIssueEdit')) {
							this.edit(rec.data.hireId);
						}
					} else {
						if (isGranted('_HireIssueQuery')) {
							this.display(rec.data.hireId);
						}
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
	 * 添加招聘信息
	 */
	addHireIssue : function() {
		new HireIssueForm({
					scope : this,
					callback : this.reloadGridPanel
				}).show();
	},
	/**
	 * 删除招聘信息
	 */
	deleClick : function() {
		$delGridRs({
					url : __ctxPath + '/hrm/multiDelHireIssue.do',
					grid : this.gridPanel,
					idName : 'hireId'
				});
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
	 * 删除单个记录
	 */
	remove : function(id) {
		$postDel({
					url : __ctxPath + '/hrm/multiDelHireIssue.do',
					ids : id,
					grid : this.gridPanel
				});
	},

	/**
	 * 修改
	 */
	edit : function(id) {
		new HireIssueForm({
					hireId : id,
					scope : this,
					callback : this.reloadGridPanel
				}).show();
	},
	/**
	 * 审核
	 */
	check : function(id) {
		new HireIssueCheckWin({
					hireId : id,
					check : false,
					scope : this,
					callback : this.reloadGridPanel
				}).show();
	},
	/**
	 * 查看详细信息
	 */
	display : function(id) {
		new HireIssueCheckWin({
					hireId : id,
					check : true
				}).show();
	},
	/**
	 * 行的Action
	 */
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.remove(record.data.hireId);
				break;
			case 'btn-edit' :
				this.edit(record.data.hireId);
				break;
			case 'btn-readdocument' :
				this.display(record.data.hireId);
				break;
			case 'menu-goods-apply' :
				this.check(record.data.hireId);
				break;
			default :
				break;
		}
	}
});