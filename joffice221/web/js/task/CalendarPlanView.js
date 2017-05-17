Ext.ns('CalendarPlanView');
/**
 * 日程列表
 * 
 * @class CalendarPlanView
 * @extends Ext.Panel
 */
CalendarPlanView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		CalendarPlanView.superclass.constructor.call(this, {
					id : 'CalendarPlanView',
					iconCls : 'menu-cal-plan-view',
					title : '日程列表',
					region : 'center',
					layout : 'border',
					items : [this.searchPanel, this.gridPanel]
				});
	},// end of constructor
	// 初始化组件
	initUIComponents : function() {
		// 初始化搜索条件Panel
		this.searchPanel = new HT.SearchPanel({
					region : 'north',
					layout : 'form',
					colNums : 4,
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
								xtype : 'datetimefield',
								fieldLabel : '开始时间',
								name : 'Q_startTime_D_GE',
								format : 'Y-m-d H:i:s',
								width : 220
							}, {
								xtype : 'textfield',
								fieldLabel : '内容',
								name : 'Q_content_S_LK',
								width : 220
							}, {
								xtype : 'combo',
								fieldLabel : '紧急程度',
								triggerAction : 'all',
								hiddenName : 'Q_urgent_SN_EQ',
								editable : false,
								width : 220,
								store : [['0', '一般'], ['1', '重要'], ['2', '紧急']]
							}, {
								xtype : 'button',
								text : '查询',
								iconCls : 'search',
								scope : this,
								handler : this.search
							}, {
								xtype : 'datetimefield',
								fieldLabel : '结束时间',
								format : 'Y-m-d H:i:s',
								name : 'Q_endTime_D_LE',
								width : 220
							}, {
								xtype : 'textfield',
								fieldLabel : '分配人名',
								width : 220,
								name : 'Q_assignerName_S_LK'
							}, {
								xtype : 'combo',
								fieldLabel : '状态',
								triggerAction : 'all',
								hiddenName : 'Q_status_SN_EQ',
								editable : false,
								width : 220,
								store : [['0', '未完成 '], ['1', '完成']]
							}, {
								xtype : 'button',
								text : '清空',
								iconCls : 'btn-reset',
								scope : this,
								handler : this.reset
							}]
				});// end of the searchPanel

		// 顶端栏目条
		this.topbar = new Ext.Toolbar({
					items : [{
								iconCls : 'btn-add',
								text : '添加日程',
								xtype : 'button',
								scope : this,
								handler : this.createRs
							}, {
								iconCls : 'btn-del',
								text : '删除日程',
								xtype : 'button',
								scope : this,
								handler : this.removeSelRs
							}, {
								iconCls : 'btn-myAssign',
								text : '我的任务',
								xtype : 'button',
								scope : this,
								handler : this.myJob
							},{
								iconCls : 'btn-myAssign',
								text : '我分配的任务',
								xtype : 'button',
								scope : this,
								handler : this.myAssing
							}, {
								iconCls : 'menu-cal-plan',
								text : '今日常务',
								xtype : 'button',
								scope : this,
								handler : this.calendarPlan
							}]
				});

		// 日程列表面板
		this.gridPanel = new HT.GridPanel({
			region : 'center',
			tbar : this.topbar,
			// 使用RowActions
			rowActions : true,
			url : __ctxPath + '/task/listCalendarPlan.do',
			sort : [{
						field : "planId",
						direction : "DESC"
					}],
			fields : [{
						name : 'planId',
						type : 'int'
					}, 'startTime', 'endTime', 'urgent', 'content', 'status',
					'userId', 'fullname', 'assignerId', 'assignerName',
					'feedback', 'showStyle', 'taskType'],
			columns : [{
						header : 'planId',
						dataIndex : 'planId',
						hidden : true
					}, {
						header : '状态',
						width : 50,
						dataIndex : 'status',
						renderer : function(value) {
							switch (value) {
								case 1:
									return '<img src="'+ __ctxPath
										+ '/images/flag/task/finish.png" title="完成"/>';
								default :
									return '<img src="'+ __ctxPath
										+ '/images/flag/task/go.png" title="未完成"/>';
							}
						}
					}, {
						header : '开始时间',
						width : 120,
						dataIndex : 'startTime'
					}, {
						header : '结束时间',
						width : 120,
						dataIndex : 'endTime'
					}, {
						header : '紧急程度',
						width : 60,
						dataIndex : 'urgent',
						renderer : function(value) {
							switch (value) {
								case 0:
									return "一般";
								case 1:
									return "<font color='green'>重要</font>";
								default :
									return "<font color='red'>紧急</font>";
							}
						}
					}, {
						width : 250,
						header : '内容',
						dataIndex : 'content',
						renderer : function(value, metadata, record) {
							var status = record.data.status;
							switch (status){
								case 1:
									return '<font style="text-decoration:line-through;color:red;">'
										+ value + '</font>';
								default :
									return value;
							}
						}
					}, {
						header : '执行人',
						width : 60,
						dataIndex : 'fullname'
					}, {
						header : '分配人',
						width : 60,
						dataIndex : 'assignerName'
					}, {
						header : '任务类型',
						width : 60,
						dataIndex : 'taskType',
						renderer : function(value) {
							switch (value) {
								case 1:
									return "<font color='red'>限期任务</font>";
								default :
									return "<font color='green'>非限期任务</font>";
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
									style : 'margin:0 3px 0 3px',
									fn : function(record) {
										if (record.data.status == 0
												&& record.data.assignerId == curUserInfo.userId)// 本人分配的任务可以修改
											return true;
										return false;
									}
								}, {
									iconCls : 'btn-task',
									qtip : '完成任务',
									style : 'margin:0 3px 0 3px',
									fn : function(record) {
										if (record.data.status == 0)
											return true;
										return false;
									}
								},{
									iconCls : 'btn-detail',
									qtip : '查看',
									style : 'margin:0 3px 0 3px'
								}],
						listeners : {
							scope : this,
							'action' : this.onRowAction
						}
					})]
		});
		//添加行双击事件
		this.gridPanel.addListener({
					scope : this,
					'rowdblclick' : this.rowClick
				});
	},// end of the initUIComponents
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
	// GridPanel行点击处理事件
	rowClick : function(grid, rowindex, e) {
		var record = grid.getStore().getAt(rowindex);
		this.editRs.call(this, record);

	},
	// 创建记录
	createRs : function() {
		new CalendarPlanForm({
					scope : this,
					callback : this.reloadType
				}).show();
	},
	// 按ID删除记录
	removeRs : function(id) {
		$postDel({
					url : __ctxPath + '/task/multiDelCalendarPlan.do',
					ids : id,
					grid : this.gridPanel
				});
	},
	// 把选中ID删除
	removeSelRs : function() {
		$delGridRs({
					url : __ctxPath + '/task/multiDelCalendarPlan.do',
					grid : this.gridPanel,
					idName : 'planId'
				});
	},
	// 编辑Rs
	editRs : function(record) {
		if(record.data.status == 1){
			Ext.ux.Toast.msg('提示信息', '已完成的任务不能修改');
			return;
		}
		if(record.data.assignerId != curUserInfo.userId){
			Ext.ux.Toast.msg('提示信息', '只能修改自己发布的任务');
			return;
		}
		new CalendarPlanForm({
			planId : record.data.planId,
			scope : this,
			callback : this.reloadType
		}).show();
	},
	// 完成任务
	finished : function(record) {
		new CalendarPlanFinishForm({
					planId : record.data.planId,
					scope : this,
					callback : this.reloadType
				}).show();
	},
	// 查看明细
	detail : function(record) {
		var planId = record.data.planId;
		new CalendarPlanDetailView(planId);
	},
	// 刷新gridPanel
	reloadType : function() {
		this.gridPanel.getStore().reload();
	},
	// 行的Action
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.removeRs.call(this, record.data.planId);
				break;
			case 'btn-edit' :
				this.editRs.call(this, record);
				break;
			case 'btn-task' :
				this.finished.call(this, record);
				break;
			case 'btn-detail' :
				this.detail.call(this, record);
				break;
			default :
				break;
		}
	},
	// 我的任务
	myJob : function() {
		var form = this.searchPanel.getForm();
		var gridPanel = this.gridPanel;
		form.submit({
			waitMsg : '正在提交查询',
			url : __ctxPath + '/task/listCalendarPlan.do',
			success : function(formPanel, action) {
				var result = Ext.util.JSON.decode(action.response.responseText);
				gridPanel.getStore().loadData(result);
			}
		});
	},
	// 我分配的任务
	myAssing : function() {
		var form = this.searchPanel.getForm();
		var gridPanel = this.gridPanel;
		form.submit({
			waitMsg : '正在提交查询',
			url : __ctxPath + '/task/listCalendarPlan.do',
			params : {
				'Q_assignerId_L_EQ' : curUserInfo.userId
			},
			success : function(formPanel, action) {
				var result = Ext.util.JSON.decode(action.response.responseText);
				gridPanel.getStore().loadData(result);
			}
		});
	},
	// 今日常务
	calendarPlan : function() {
		var tabs = Ext.getCmp('centerTabPanel');
		tabs.add({
					xtype : 'iframepanel',
					title : '今日常务',
					id : 'myCalendarPlanId',
					loadMask : {
						msg : '正在加载...,请稍等...'
					},
					iconCls : 'menu-cal-plan',
					defaultSrc : __ctxPath
							+ '/pages/task/ILogCalendar/calendar.jsp?id='
							+ Math.random(),
					listeners : {
						domready : function(iframe) {
						}
					}
				});
		tabs.activate('myCalendarPlanId');
	}

});
