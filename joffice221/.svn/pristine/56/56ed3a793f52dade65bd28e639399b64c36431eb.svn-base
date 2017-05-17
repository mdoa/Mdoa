Ext.ns('CalendarPlanForm');
/**
 * 日程详细信息
 * 
 * @class CalendarPlanForm
 * @extends Ext.Window
 */
CalendarPlanForm = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		//调用父类构造方法
		CalendarPlanForm.superclass.constructor.call(this, {
					id : 'CalendarPlanFormWin',
					layout : 'fit',
					items : this.formPanel,
					width : 660,
					minWidth : 450,
					height : 500,
					minHeight : 500,
					maximizable : true,
					modal : true,
					iconCls : 'menu-cal-plan-view',
					title : '日程详细信息',
					buttonAlign : 'center',
					buttons : [{
								text : '保存',
								iconCls : 'btn-save',
								scope : this,
								handler : this.save
							}, {
								text : '重置',
								iconCls : 'btn-reset',
								scope : this,
								handler : this.reset
							}, {
								text : '取消',
								iconCls : 'btn-cancel',
								scope : this,
								handler : this.cancel
							}]
				});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		//日程详细信息面板
		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			bodyStyle : 'padding:5px;',
			frame : false,
			border : 0,
			defaults : {
				anchor : '98%,98%'
			},
			items : [{
						name : 'calendarPlan.planId',
						xtype : 'hidden',
						value : this.planId == null ? '' : this.planId
					}, {
						xtype : 'radiogroup',
						fieldLabel : '紧急程度',
						autoHeight : true,
						columns : 3,
						items : [{
									boxLabel : '一般',
									name : 'calendarPlan.urgent',
									inputValue : 0,
									id : 'urgent0',
									checked : true
								}, {
									boxLabel : '重要',
									name : 'calendarPlan.urgent',
									id : 'urgent1',
									inputValue : 1
								}, {
									boxLabel : '紧急',
									name : 'calendarPlan.urgent',
									id : 'urgent2',
									inputValue : 2
								}]
					}, {
						fieldLabel : '概要',
						xtype : 'textfield',
						name : 'calendarPlan.summary',
						allowBlank : false
					}, {
						fieldLabel : '内容',
						xtype : 'htmleditor',
						height : 200,
						name : 'calendarPlan.content',
						allowBlank : false
					}, {
						fieldLabel : '员工ID',
						xtype : 'hidden',
						name : 'calendarPlan.userId',
						value : curUserInfo.userId
					}, {
						layout : 'column',
						autoHeight : true,
						border : false,
						items : [{
									columnWidth : .995,
									layout : 'form',
									border : false,
									style : 'padding-left:0px;',
									items : [{
												fieldLabel : '分配给',
												style : 'padding-left:0px;',
												xtype : 'textfield',
												anchor : '96%,96%',
												name : 'calendarPlan.fullname',
												value : curUserInfo.fullname
											}]

								}, {
									width : 100,
									xtype : 'button',
									border : false,
									iconCls : 'btn-user-sel',
									text : '选择员工',
									scope : this,
									handler : this.userSelect
								}]
					}, {
						xtype : 'radiogroup',
						name : 'planTypeGroup',
						fieldLabel : '任务类型',
						autoHeight : true,
						columns : 2,
						items : [{
							boxLabel : '限期任务',
							name : 'calendarPlan.taskType',
							inputValue : 1,
							checked : true,
							listeners : {
								scope : this,
								check : this.check1
							}
						}, {
							boxLabel : '非限期任务',
							name : 'calendarPlan.taskType',
							inputValue : 2,
							listeners : {
								scope : this,
								check : this.check2
							}
						}]
					}, {
						xtype : 'fieldset',
						border : false,
						layout : 'form',
						name : 'timeDuration',
						autoHeight : true,
						style : 'padding-left:0px;',
						items : [{
									fieldLabel : '开始时间',
									name : 'calendarPlan.startTime',
									xtype : 'datetimefield',
									anchor : '98%,98%',
									format : 'Y-m-d H:i:s',
									editable : false
								}, {
									fieldLabel : '结束时间',
									name : 'calendarPlan.endTime',
									xtype : 'datetimefield',
									anchor : '98%,98%',
									format : 'Y-m-d H:i:s',
									editable : false
								}]
					}, {
						xtype : 'fieldset',
						name : 'CalenderDisplayType',
						layout : 'form',
						autoHeight : true,
						border : false,
						style : 'padding-left:0px;',
						items : [{
									xtype : 'radiogroup',
									fieldLabel : '显示方式',
									autoHeight : true,
									columns : 2,
									items : [{
												boxLabel : '仅在任务中显示',
												name : 'calendarPlan.showStyle',
												inputValue : 1,
												id: 'showStyle1',
												checked : true
											}, {
												boxLabel : '在日程与任务中显示',
												name : 'calendarPlan.showStyle',
												id: 'showStyle2',
												inputValue : 2
											}]
								}]
					}]
		});
		// 加载表单对应的数据
		if (this.planId != null && this.planId != 'undefined') {
			var formPanel = this.formPanel;
			formPanel.loadData({
					url : __ctxPath + '/task/getCalendarPlan.do?planId='
							+ this.planId,
					root : 'data',
					preName : 'calendarPlan',
					success : function(result, request) {
					},
					failure : function(form, action) {
						
					}
				});
		};
		
	},// end of the initcomponents

	// 重置
	reset : function() {
		this.formPanel.getForm().reset();
	},
	// 取消
	cancel : function() {
		this.close();
	},
	//保存记录
	save : function() {
		var formPanel = this.formPanel;
		var startTime = formPanel.getCmpByName('calendarPlan.startTime').getValue();
		var endTime = formPanel.getCmpByName('calendarPlan.endTime').getValue();
		var type = formPanel.getCmpByName('calendarPlan.taskType').checked;
		var summary = formPanel.getCmpByName('calendarPlan.summary').getValue();
		var ctt = formPanel.getCmpByName('calendarPlan.content').getValue();
		var sd=Date.parse(startTime);
	    var ed=Date.parse(endTime);
		if ((!type) || (type && startTime != null && startTime != ''
						&& endTime != null && endTime != '')) {
			if(sd>ed){
	    		Ext.ux.Toast.msg('操作信息', '任务开始时间大于结束进间,不能保存!');
	    		return;
	    	}
			if (summary == null || summary.replace(/(^\s*)|(\s*$)/g, "") == '') {
				Ext.ux.Toast.msg('操作提示', '请输入日程概要！');
				return;
			}
			if (ctt == null || ctt == '' || ctt == ' ') {
				Ext.ux.Toast.msg('操作提示', '请输入日程内容！');
				return;
			}
			
			$postForm({
		        formPanel : formPanel,
				scope : this,
				url : __ctxPath + '/task/magSaveCalendarPlan.do',
				callback : function(fp, action) {
					if (this.callback) {
						this.callback.call(this.scope);
					}
					this.close();
				}
			});
		} else {
			Ext.ux.Toast.msg('操作信息', '请填写完整开始结束时间！');
		}
	},
	//员工选择
	userSelect : function() {
		var formPanel = this.formPanel;
		new UserDialog({
				scope : this,
				single : true,
				callback:function(userId, fullname) {
					formPanel.getCmpByName("calendarPlan.userId").setValue(userId);
					formPanel.getCmpByName("calendarPlan.fullname").setValue(fullname);
				}}, true).show();
	},
	//限期任务
	check1 : function(ck, bval) {
		var formPanel = this.formPanel;
		if (bval) {
			formPanel.getCmpByName("timeDuration").setVisible(true);
			formPanel.getCmpByName('CalenderDisplayType').setVisible(true);
		}
	},
	//非限期任务
	check2 : function(ck, bval) {
		var formPanel = this.formPanel;
		if (bval) {
			formPanel.getCmpByName("timeDuration").setVisible(false);
			formPanel.getCmpByName('CalenderDisplayType').setVisible(false);
			formPanel.getCmpByName("calendarPlan.startTime").setValue('');
			formPanel.getCmpByName("calendarPlan.endTime").setValue('');
		}
	}
});