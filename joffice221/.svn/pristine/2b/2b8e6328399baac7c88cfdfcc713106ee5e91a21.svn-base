Ext.ns('MyTaskView');
/**
 * 我的任务流程
 */
MyTaskView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		MyTaskView.superclass.constructor.call(this, {
					id:'MyTaskView',
					iconCls : 'menu-flowWait',
					bodyStyle : 'padding:2px 2px 2px 2px',
					layout : 'border',
					region : 'center',
					title : '待办事项',
					autoScroll : true,
					items : [this.gridPanel]
				});
	},// end of constructor
	/**
	 * 初始化组件
	 */
	initUIComponents : function() {
		// 初始化搜索条件Panel
		this.topbar = new Ext.Toolbar({
					height : 28,
					items : [{
								text : '刷新',
								scope : this,
								iconCls : 'btn-refresh',
								handler : this.myReload
							}]
				});
		this.gridPanel = new HT.GridPanel({
					region : 'center',
					tbar : this.topbar,
					// 使用RowActions
					rowActions : true,
					url : __ctxPath + '/flow/listTask.do',
					fields : ['taskName', 'activityName', 'assignee',
							'createTime', 'dueDate', 'executionId', 'pdId',
							'isDue', 'taskId', 'isMultipleTask'],
					columns : [{
								header : "userId",
								dataIndex : 'userId',
								width : 20,
								hidden : true,
								sortable : true
							}, {
								header : '事项名称',
								dataIndex : 'taskName',
								width : 120,
								renderer : this.taskNameControl
							}, {
								header : '执行人',
								dataIndex : 'assignee',
								width : 140,
								renderer : this.assigneeControl
							}, {
								header : '开始时间',
								dataIndex : 'createTime',
								width : 100
							}, {
								header : '到期时间',
								dataIndex : 'dueDate',
								width : 100,
								renderer : function(value) {
									if (value == '') {
										return '无限制';
									} else {
										return value;
									}
								}
							}, {
								hidden : true,
								dataIndex : 'executionId'
							}, {
								hidden : true,
								dataIndex : 'taskId'
							}, {
								hidden : 'true',
								dataIndex : 'isMultipleTask'
							}, new Ext.ux.grid.RowActions({
										header : '管理',
										width : 100,
										actions : [{
													iconCls : 'btn-lockTask',
													qtip : '锁定任务',
													style : 'margin:0 3px 0 3px',
													fn : this.lockTaskControl
												}, {
													iconCls : 'btn-approvalTask',
													qtip : '审核任务',
													style : 'margin:0 3px 0 3px',
													fn : this.actionTaskControl
												}, {
													iconCls : 'btn-changeTask',
													qtip : '任务代办',
													style : 'margin:0 3px 0 3px',
													fn : this.actionTaskControl
												}, {
													iconCls : 'btn-unlockTask',
													qtip : '解锁任务',
													style : 'margin:0 3px 0 3px',
													fn : this.unlockTaskControl
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
				var me=this;
				setHTInterval(function(){
					me.gridPanel.getStore().reload();
				},1000*60*5);
			
	},// end of the initComponents()
	/**
	 * 列表双击处理
	 */
	rowClick : function(grid, rowindex, e) {
		grid.getSelectionModel().each(function(rec) {
					this.nextStep(rec.data.taskId, rec.data.activityName);
				}, this);
	},
	/**
	 * 重新载入数据
	 */
	myReload : function() {
		this.gridPanel.getStore().reload();
	},
	/**
	 * 项目名称 列控制
	 */
	taskNameControl : function(val, metadata, record) {
		if (record.data.isDue == 1) {
			return '<font color="red">' + val + '</font>';
		}
		return val;
	},
	/**
	 * 执行人 列控制
	 */
	assigneeControl : function(value, metadata, record, rowIndex, colIndex) {
		var assignee = record.data.assignee;
		if (assignee == null || assignee == '') {
			return '<font color="red">暂无执行人</font>';
		} else {
			return assignee;
		}
	},
	/**
	 * 锁定任务控制
	 * @param {} record
	 * @return {Boolean}
	 */
	lockTaskControl : function(record) {
		if (record.data.assignee)
			return false;
		return true;
	},
	/**
	 * 任务代办控制
	 * @param {} record
	 * @return {Boolean}
	 */
	actionTaskControl : function(record) {
		if (!record.data.assignee)
			return false;
		return true;
	},
	/**
	 * 解锁任务控制
	 * @param {} record
	 * @return {Boolean}
	 */
	unlockTaskControl : function(record) {
		if (record.data.assignee && record.data.isMultipleTask == 1)
			return true;
		return false;
	},
	/**
	 * 锁定
	 * 
	 * @param {}
	 *            taskId
	 */
	lockTask : function(taskId) {
		Ext.Ajax.request({
					url : __ctxPath + '/flow/lockTask.do',
					params : {
						taskId : taskId
					},
					method : 'post',
					scope : this,
					success : function(result, response) {
						var grid = this.gridPanel;
						var resultObj = Ext.util.JSON
								.decode(result.responseText);
						if (resultObj.hasAssigned == true) {
							Ext.ux.Toast.msg("操作提示", "该任务已经被其他用户锁定执行！");
						} else {
							Ext.ux.Toast.msg("操作提示", "该任务已经成功锁定，请执行下一步操作！");
						}
						grid.getStore().reload();
					}
				});
	},
	/**
	 * 下一步的任务
	 * 
	 * @param {}
	 *            taskdbid
	 */
	nextStep : function(taskId, activityName) {
		var contentPanel = App.getContentPanel();
		var formView = contentPanel.getItem('ProcessNextForm' + taskId);
		if (formView == null) {
			formView = new ProcessNextForm({
						taskId : taskId,
						activityName : activityName
					});
			contentPanel.add(formView);
		}
		contentPanel.activate(formView);
	},
	/**
	 * 任务变更，则转由代办人来处理
	 * 
	 * @param {}
	 *            taskId
	 */
	changeTask : function(taskId, taskname) {
		new ChangeTaskView({
			taskId:taskId,
			taskname:taskname
		}).show();
	},
	/**
	 * 锁定任务，则表示自己退出执行该任务，其他人员可以申请执行该任务
	 * 
	 * @param {}
	 *            taskdbid
	 */
	unlockTask : function(taskId) {
		Ext.Ajax.request({
					url : __ctxPath + '/flow/unlockTask.do',
					params : {
						taskId : taskId
					},
					method : 'post',
					scope : this,
					success : function(result, response) {
						var grid = this.gridPanel;
						var resultObj = Ext.util.JSON
								.decode(result.responseText);
						if (resultObj.unlocked == true) {
							Ext.ux.Toast.msg("操作提示", "该任务已经成功解锁！");
						} else {
							Ext.ux.Toast.msg("操作提示", "该任务解锁失败(任务已经由其他人员执行完成)！");
						}
						grid.getStore().reload();
					}
				});
	},
	/**
	 * 行的Action
	 */
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-lockTask' :
				this.lockTask(record.data.taskId);
				break;
			case 'btn-approvalTask' :
				this.nextStep(record.data.taskId, record.data.activityName);
				break;
			case 'btn-changeTask' :
				this.changeTask(record.data.taskId, record.data.activityName);
				break;
			case 'btn-unlockTask' :
				this.unlockTask(record.data.taskId);
				break;
			default :
				break;
		}
	}
});