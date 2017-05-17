/**
 * @author:
 * @class ArchivesMonitor
 * @extends Ext.Panel
 * @description 拟稿管理
 * @company 杭州梦德软件有限公司
 * @createtime:2010-01-16
 */
ArchivesMonitor = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		ArchivesMonitor.superclass.constructor.call(this, {
					id : 'ArchivesMonitor',
					iconCls : 'menu-archive-monitor',
					title : '流程监控',
					region : 'center',
					layout : 'border',
					items : [this.searchPanel, this.gridPanel,
							this.assigneeGrid]
				});
	},// end of constructor

	// 初始化组件
	initUIComponents : function() {
		// 初始化搜索条件Panel
		this.searchPanel = new HT.SearchPanel({
					region : 'north',
					layout : 'form',
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
					labelWidth : 60,
					items : [{
								fieldLabel : '公文类型',
								name : 'Q_typeName_S_LK',
								xtype : 'textfield',
								maxLength : 125
							}, {
								fieldLabel : '发文字号',
								name : 'Q_archivesNo_S_LK',
								xtype : 'textfield',
								labelWidth : 100,
								maxLength : 125
							}, {
								fieldLabel : '发文时间',
								name : 'Q_issueDate_D_GE',
								xtype : 'datefield',
								format : 'Y-m-d'
							}, {
								fieldLabel : '至',
								name : 'Q_issueDate_D_LE',
								xtype : 'datefield',
								labelWidth : 15,
								format : 'Y-m-d'
							}, {
								fieldLabel : '秘密等级',
								name : 'Q_privacyLevel_S_LK',
								xtype : 'textfield',
								maxLength : 125
							}, {
								xtype : 'button',
								text : '查询',
								iconCls : 'search',
								scope : this,
								handler : this.search
							}, {
								fieldLabel : '公文状态',
								name : 'Q_status_S_LK',
								xtype : 'textfield',
								maxLength : 125
							}, {
								fieldLabel : '发文机关或部门',
								name : 'Q_issueDep_S_LK',
								xtype : 'textfield',
								labelWidth : 100,
								maxLength : 125
							}, {
								fieldLabel : '文件标题',
								name : 'Q_subject_S_LK',
								xtype : 'textfield',
								width : 212,
								maxLength : 125
							}, {
								fieldLabel : '紧急程度',
								name : 'Q_urgentLevel_S_LK',
								xtype : 'textfield',
								maxLength : 125
							}, {
								xtype : 'button',
								text : '清空',
								iconCls : 'reset',
								scope : this,
								handler : this.reset
							}]

				}); // end of this searchPanel

		// 流程监控列表
		this.gridPanel = new HT.GridPanel({
			region : 'center',
			rowActions : true,
			sort : [{
						field : "createtime",
						direction : "DESC"
					}],
			url : __ctxPath + "/archive/listArchives.do",
			fields : [{
						name : 'archivesId',
						type : 'int'
					}, 'archivesType', 'archivesRecType', 'archivesNo',
					'issueDep', 'depId', 'subject', 'issueDate', 'status',
					'shortContent', 'fileCounts', 'privacyLevel',
					'urgentLevel', 'issuer', 'issuerId', 'keywords', 'sources',
					'archType', 'createtime', 'runId', 'tasks', 'archStatus',
					'handlerStatus'],
			columns : [{
						header : 'archivesId',
						dataIndex : 'archivesId',
						hidden : true
					}, {
						header : '公文类型名称',
						dataIndex : 'archivesType',
						renderer : this.archivesType
					}, {
						header : '发文字号',
						dataIndex : 'archivesNo'
					}, {
						header : '发文机关或部门',
						dataIndex : 'issueDep'
					}, {
						header : '文件标题',
						dataIndex : 'subject'
					}, {
						header : '公文状态',
						dataIndex : 'status'
					}, {
						header : '秘密等级',
						dataIndex : 'privacyLevel'
					}, {
						header : '紧急程度',
						dataIndex : 'urgentLevel'
					}, {
						header : '发文时间',
						dataIndex : 'createtime',
						renderer : function(value) {
							return value.substring(0, 10);
						}
					}, {
						header : '处理状态',
						dataIndex : 'handlerStatus',
						renderer : this.handlerStatus
					}, new Ext.ux.grid.RowActions({
								header : '管理',
								width : 100,
								actions : [{
											iconCls : 'btn-archives-detail',
											qtip : '查阅详情',
											style : 'margin:0 3px 0 3px'
										}, {
											iconCls : 'btn-archives-remind',
											qtip : '催办',
											style : 'margin:0 3px 0 3px',
											fn : function(rs) {
												if (isGranted('_ArchivesIssueHasten'))
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

		// 公文执行人信息
		this.assigneeGrid = new HT.EditorGridPanel({
			title : '公文执行人信息',
			region : 'south',
			height : 150,
			width : '100%',
			autoScroll : true,
			trackMouseOver : true,
			url : __ctxPath + '/archive/getHanlderUsersArchives.do',
			fields : [{
						name : 'userId',
						type : 'int'
					}, 'fullname', 'task'],
			columns : [{
						header : '编号',
						dataIndex : 'userId',
						hidden : true
					}, {
						header : '执行人名称',
						dataIndex : 'fullname'
					}, {
						header : '执行操作',
						dataIndex : 'task',
						renderer : this.task
					}]

		});

		this.gridPanel.addListener({scope : this,'rowdblclick': this.rowClick});
		this.gridPanel.addListener({scope : this,'rowclick': this.rowClicks});

	},// end of the initComponents()

	/**
	 * 双击
	 * @param {} grid
	 * @param {} rowindex
	 * @param {} e
	 */
	rowClick : function(grid, rowindex, e) {
		var rec = grid.getStore().getAt(rowindex);
		this.detail(rec);
	},
	/**
	 * 行单击事件
	 */
	rowClicks : function(grid, rowindex, e) {
		var selGrid = this.assigneeGrid;
		var selStore = selGrid.getStore();
		var rec = grid.getStore().getAt(rowindex);
		
		selGrid.setTitle(rec.data.status + " -- 公文执行人信息");
		selStore.load({
					params : {
						runId : rec.data.runId,
						taskStatus : rec.data.status
					}
				});
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
	 * 清空/重置
	 */
	reset : function() {
		this.searchPanel.getForm().reset();
	},
	/**
	 * 查阅详情
	 * 
	 * @param {}
	 *            record
	 */
	detail : function(record) {
		new ArchivesDetailWin({
					archivesId : record.data.archivesId,
					runId : record.data.runId
				}).show();
	},
	/**
	 * 催办功能
	 * 
	 * @param {}
	 *            record
	 */
	remind : function(record) {
		var status = record.data.status;
		var archStatus = record.data.archStatus;
		var activityName = '';

		if (archStatus == '1') {
			Ext.ux.Toast.msg('提示信息', '公文已归档!');
			return;
		} else {
			new ArchHastenForm({
						archivesId : record.data.archivesId,
						archivesNo : record.data.archivesNo,
						activityName : status
					}).show();
		}
	},
	/**
	 * 公文任务审核
	 * 
	 * @param {}
	 *            record
	 */
	approvalTask : function(record) {
		var contentPanel = App.getContentPanel();
		var runId = record.data.runId;
		var detailView = contentPanel.getItem('ProcessRunDetail' + runId);
		if (detailView == null) {
			detailView = new ProcessRunDetail(runId, null, null,
					record.data.subject);
			contentPanel.add(detailView);
		}
		contentPanel.activate(detailView);
	},
	/**
	 * 管理列中的事件处理
	 * 
	 * @param {}
	 *            grid
	 * @param {}
	 *            record
	 * @param {}
	 *            action
	 * @param {}
	 *            row
	 * @param {}
	 *            col
	 */
	onRowAction : function(gridPanel, record, action, row, col) {
		switch (action) {
			case 'btn-archives-detail' :
				this.detail(record);
				break;
			case 'btn-archives-remind' :
				this.remind(record);
				break;
			case 'btn-approvalTask' :
				this.approvalTask(record);
				break;
			default :
				break;
		}
	},
	/**
	 * 公文类型名称
	 * @param {} value
	 * @param {} metadata
	 * @param {} record
	 * @return {}
	 */
	archivesType : function(value, metadata, record) {
		if (value) {
			return value.typeName;
		} else if (record.data.archivesRecType) {
			return record.data.archivesRecType.typeName;
		}
	},
	/**
	 * 处理状态
	 * @param {} status
	 * @return {}
	 */
	handlerStatus : function(status) {
		return status == '1'
				? '<font color="red">流程结束</font>'
				: '<font color="green">处理中</font>';
	},
	/**
	 * 执行操作
	 * @param {} task
	 * @return {}
	 */
	task : function(task) {
		var reVal = task.taskName + '(';
		if (curUserInfo.userId == task.userId) {
			reVal += '<a href="#" onclick="App.MyDesktopClickTopTab(\'ProcessNextForm\',{taskId:'
					+ task.taskId
					+ ',activityName:\''
					+ task.taskName + '\'})">';
		}
		reVal += task.fullname;
		if (curUserInfo.userId == task.userId) {
			reVal += "</a>";
		}
		reVal += ')&nbsp;&nbsp;';
		return reVal;
	}
});

ArchivesMonitor.jumpFlowTask=function(taskId,taskName){
	App.MyDesktopClickTopTab('ProcessNextForm',{taskId:taskId,activityName:taskName});
};
