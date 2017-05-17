Ext.ns('CalendarPlanFinishForm');
/**
 * 完成任务
 * @class CalendarPlanFinishForm
 * @extends Ext.Window
 */
CalendarPlanFinishForm = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		//调用父类构造方法
		CalendarPlanFinishForm.superclass.constructor.call(this, {
					id : 'CalendarPlanFinishFormWin',
					layout : 'fit',
					width : 560,
					height:420,
					maximizable : true,
					modal : true,
					iconCls:'menu-cal-plan',
					title : '完成任务',
					buttonAlign : 'center',
					bodyStyle : 'padding:5px;',
					buttons : [{
								text : '完成任务',
								id:'calendarPlanFinishBtn',
								iconCls : 'btn-save',
								scope : this,
								handler : this.save
							}, {
								text : '取消',
								iconCls : 'btn-cancel',
								scope : this,
								handler : this.cancel
							}],
					items : [this.detailPanell,this.formPanel]
				});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		//计划明细
		this.detailPanell = new Ext.Panel({
			autoHeight : true,
			width:530,
			border : false,
			autoLoad : {
				url : __ctxPath
						+ '/pages/task/calendarplandetail.jsp?planId=' + this.planId
			}
		});
		//完成任务反馈面板
		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			frame : true,
			defaults : {
				anchor : '98%,98%'
			},
			items : [{
						name : 'calendarPlan.planId',
						xtype : 'hidden',
						value : this.planId == null ? '' : this.planId
					}, {
						xtype:'textarea',
						fieldLabel:'反馈',
						name:'calendarPlan.feedback'
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
						var respText = Ext.util.JSON.decode(result.responseText);
						if(respText.data.status==1){
							Ext.getCmp("calendarPlanFinishBtn").setDisabled(true);
						}
					},
					failure : function(form, action) {
						
					}
				});
		};
	},// end of the initcomponents
	/**
	 * 取消
	 * 
	 * @param {}
	 *            window
	 */
	cancel : function() {
		this.close();
	},
	/**
	 * 保存记录
	 */
	save : function() {
		var formPanel = this.formPanel;
		$postForm({
	        formPanel : formPanel,
			scope : this,
			params:{'calendarPlan.status':1},
			url : __ctxPath + '/task/magSaveCalendarPlan.do',
			callback : function(fp, action) {
				if (this.callback) {
					this.callback.call(this.scope);
				}
				this.close();
			}
		});
	}
});