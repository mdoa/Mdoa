/**
 * 流程任务管理
 * 
 * @class TaskManager
 * @extends Ext.Panel
 */
TaskManager = Ext.extend(Ext.Panel, {
	// 构造方法
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		this.initUI();
		TaskManager.superclass.constructor.call(this, {
					id : 'TaskManager',
					title : '任务管理',
					iconCls : 'menu-task-manage',
					layout : 'border',
					items : [this.searchPanel, this.gridPanel]
				});
	},
	// 初始化组件
	initUI : function() {
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
					labelWidth : 60,
					items : [{
								fieldLabel : '任务名称',
								xtype : 'textfield',
								name : 'taskName',
								maxLength : 150
							}, {
								xtype : 'button',
								text : '查询',
								iconCls : 'btn-search',
								scope : this,
								handler : this.search
							}]

				});
		// 列表顶部按钮
		this.toolbar = [{
					text : '刷新',
					iconCls : 'btn-refresh',
					scope : this,
					handler : this.refresh
				}, '-', {
					text : '设置到期时间',
					iconCls : 'btn-timeSetting',
					scope : this,
					handler : this.setDueDate
				}, '-', {
					text : '更改待办人',
					iconCls : 'btn-changeTask',
					scope : this,
					handler : this.setHandler
				}, '-', {
					text : '更改执行路径',
					iconCls : 'btn-changePath',
					scope : this,
					handler : this.setPath
				}, '-', {
					text : '任务代办',
					iconCls : 'btn-taskDo',
					scope : this,
					handler : this.handlerTask

				}, '-', {
					text : '任务恢复',
					iconCls : 'btn-empProfile-recovery',
					scope : this,
					handler : this.recover
				}];
		// 流程任务列表面板
		this.gridPanel = new HT.GridPanel({
					region : 'center',
					tbar : this.toolbar,
					// singleSelect : true,
					url : __ctxPath + '/flow/allTask.do',
					fields : ['taskName', 'activityName', 'assignee',
							'createTime', 'dueDate', 'executionId', 'pdId',
							'taskId', 'isMultipleTask', 'state'],
					columns : [{
								header : "taskId",
								dataIndex : 'taskId',
								hidden : true
							}, {
								header : '任务名称',
								dataIndex : 'taskName',
								width : 350
							}, {
								header : '待办人',
								dataIndex : 'assignee',
								renderer : function(value) {
									if (value == null || value == '')
										return '<font color="red">暂无执行人</font>';
									else
										return value;
								},
								width : 100
							}, {
								header : '开始时间',
								dataIndex : 'createTime',
								width : 150
							}, {
								header : '到期时间',
								dataIndex : 'dueDate',
								width : 150,
								renderer : function(value) {
									if (value == null || value == '') {
										return '无限制';
									} else {
										return value;
									}
								}
							}, {
								header : '任务状态',
								dataIndex : 'state',
								width : 150,
								renderer : function(value) {
									if (value && value == 'suspended') {
										return '<font color="red">已挂起</font>';
									} else {
										return '<font color="green">开放中</font>';
									}
								}
							}]
				});

	},// end of initUIComponents
	// 查询
	search : function() {
		$search({
					searchPanel : this.searchPanel,
					gridPanel : this.gridPanel
				});
	},
	// 刷新列表
	refresh : function() {
		this.gridPanel.store.reload();
	},
	// 为任务设置过期时间
	setDueDate : function() {
		var gridPanel = this.gridPanel;
		var rs = gridPanel.getSelectionModel().getSelections();
		if (rs.length == 0) {
			Ext.ux.Toast.msg('操作信息', '请选择任务记录!');
			return;
		}
		new TaskDueDateWindow({
					taskGrid : gridPanel,
					scope : this,
					callback : this.refresh
				}).show();
	},
	// 为任务设置待办人
	setHandler : function() {
		var gridPanel = this.gridPanel;
		var rs = gridPanel.getSelectionModel().getSelections();
		if (rs.length == 0) {
			Ext.ux.Toast.msg('操作信息', '请选择任务记录!');
			return;
		}
		new TaskHandlerWindow({
					taskGrid : gridPanel,
					scope : this,
					callback : this.refresh
				}).show();
	},
	//更改执行路径
	setPath : function() {
		var gridPanel = this.gridPanel;
		var rs = gridPanel.getSelectionModel().getSelections();
		if (rs.length == 0) {
			Ext.ux.Toast.msg('操作信息', '请选择任务记录!');
			return;
		}
		new PathChangeWindow({
					taskId : rs[0].data.taskId,
					taskGrid : gridPanel
				}).show();
	},
	//任务待办
	handlerTask : function() {
		var gridPanel = this.gridPanel;
		var rs = gridPanel.getSelectionModel().getSelections();
		if (rs.length == 0) {
			Ext.ux.Toast.msg('操作信息', '请选择任务记录!');
			return;
		}
		if (rs.length > 1) {
			Ext.ux.Toast.msg('操作信息', '只能选择一条任务记录!');
			return;
		}
		var record = rs[0];
		var contentPanel = App.getContentPanel();
		var formView = contentPanel.getItem('ProcessNextForm'
				+ record.data.taskId);
		if (formView == null) {
			formView = new ProcessNextForm({
						taskId : record.data.taskId,
						activityName : record.data.activityName,
						agentTask : true
					});
			contentPanel.add(formView);
		}
		contentPanel.activate(formView);
	},
	//任务恢复
	recover : function() {
		var gridPanel = this.gridPanel;
		var rs = gridPanel.getSelectionModel().getSelections();
		if (rs.length == 0) {
			Ext.ux.Toast.msg('操作信息', '请选择任务记录!');
			return;
		}
		if (rs.length > 1) {
			Ext.ux.Toast.msg('操作信息', '只能选择一条任务记录!');
			return;
		}
		var record = rs[0];
		Ext.Ajax.request({
					url : __ctxPath + '/flow/recoverTask.do',
					method : 'POST',
					params : {
						taskId : record.data.taskId
					},
					success : function(respose, opt) {
						gridPanel.getStore().reload();
						Ext.ux.Toast.msg('操作信息', '成功恢复流程任务!');
					}
				});
	}

});
