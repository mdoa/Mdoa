/**
 * 班次选择器
 * 
 * @class DutySectionDialog
 * @extends Ext.Window
 */
DutySectionDialog = Ext.extend(Ext.Window, {
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		// 作用域
		this.scope = this.scope ? this.scope : this;
		//  默认为多单选择班次
		this.single = this.single != null ? this.single : false;
		//初始化组件
		this.initUI();
		DutySectionDialog.superclass.constructor.call(this, {
					title : this.title ? this.title : '班次选择器',
					width : 630,
					height : 380,
					iconCls : 'btn-clock',
					layout : 'fit',
					border : false,
					modal : true,
					buttonAlign : 'center',
					items : [this.gridPanel],
					buttons : [{
								iconCls : 'btn-ok',
								text : '确定',
								scope : this,
								handler : this.confirm
							}, {
								text : '取消',
								iconCls : 'btn-cancel',
								scope : this,
								handler : function() {
									this.close();
								}
							}, {
								text : '休息',
								iconCls : 'btn-relax',
								scope : this,
								handler : function() {
									if (this.callback) {
										this.callback.call(this.scope, '-', '休息');
									}
									this.close();
								}
							}]
				});
	},
	/**
	 * 初始化UI
	 */
	initUI : function() {
		// -------------start grid panel--------------------------------
		this.gridPanel = new HT.GridPanel({
					title : '班级列表',
					height : 360,
					width : 400,
					singleSelect : this.single,
					url : __ctxPath + '/personal/listDutySection.do',
					fields : [{
								name : 'sectionId',
								type : 'int'
							}, 'sectionName', 'startSignin', 'dutyStartTime',
							'endSignin', 'earlyOffTime', 'dutyEndTime',
							'signOutTime'],
					columns : [{
								header : 'sectionId',
								dataIndex : 'sectionId',
								hidden : true
							}, {
								header : '班次名称',
								dataIndex : 'sectionName'
							}, {
								header : '开始签到',
								dataIndex : 'startSignin'
							}, {
								header : '上班时间',
								dataIndex : 'dutyStartTime'
							}, {
								header : '签到结束时间',
								dataIndex : 'endSignin'
							}, {
								header : '早退计时',
								dataIndex : 'earlyOffTime'
							}, {
								header : '下班时间',
								dataIndex : 'dutyEndTime'
							}, {
								header : '签退结束',
								dataIndex : 'signOutTime'
							}]
				});

	},// end of initUI function
	/**
	 * 选择确认
	 */
	confirm : function() {
		var rows =  this.gridPanel.getSelectionModel().getSelections();
		var sectionIds = '';
		var sectionNames = '';
		for (var i = 0; i < rows.length; i++) {
			if (i > 0) {
				sectionIds += ',';
				sectionNames += ',';
			}
			sectionIds += rows[i].data.sectionId;
			sectionNames += rows[i].data.sectionName;
		}

		if (this.callback) {
			this.callback.call(this.scope, sectionIds, sectionNames);
		}
		this.close();
	}
});