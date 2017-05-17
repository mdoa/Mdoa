Ext.ns('DutySectionForm');
/**
 * 班次定义详细信息
 */
DutySectionForm = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		// 调用父类构造
		DutySectionForm.superclass.constructor.call(this, {
					id : 'DutySectionFormWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					width : 300,
					height : 280,
					maximizable : true,
					title : '班次定义详细信息',
					iconCls : 'menu-dutySection',
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
		this.formPanel = new Ext.FormPanel({
					layout : 'form',
					bodyStyle : 'padding:5px;',
					border : false,
					autoScroll : true,
					defaults : {
						width : 400,
						anchor : '98%,98%'
					},
					defaultType : 'textfield',
					items : [{
								name : 'dutySection.sectionId',
								xtype : 'hidden',
								value : this.sectionId == null
										? ''
										: this.sectionId
							}, {
								fieldLabel : '班次名称',
								name : 'dutySection.sectionName',
								allowBlank : false
							}, {
								fieldLabel : '开始签到',
								id : 'startSignin',
								vtype : 'timerange',
								minDateField : ['dutyStartTime', 'endSignin',
										'earlyOffTime', 'dutyEndTime',
										'signOutTime'],
								name : 'dutySection.startSignin',
								xtype : 'timefield',
								format : 'H:i',
								allowBlank : false
							}, {
								fieldLabel : '上班时间',
								id : 'dutyStartTime',
								vtype : 'timerange',
								minDateField : ['endSignin', 'earlyOffTime',
										'dutyEndTime', 'signOutTime'],
								//maxDateField : ['startSignin'],

								name : 'dutySection.dutyStartTime',
								xtype : 'timefield',
								format : 'H:i',
								allowBlank : false
							}, {
								fieldLabel : '签到结束时间',
								id : 'endSignin',
								vtype : 'timerange',
								minDateField : ['earlyOffTime', 'dutyEndTime',
										'signOutTime'],
								//maxDateField : ['startSignin', 'dutyStartTime'],

								name : 'dutySection.endSignin',
								xtype : 'timefield',
								format : 'H:i',
								allowBlank : false
							}, {
								fieldLabel : '早退计时',
								id : 'earlyOffTime',
								vtype : 'timerange',
								minDateField : ['dutyEndTime', 'signOutTime'],
								//maxDateField : ['startSignin', 'dutyStartTime','endSignin'],

								name : 'dutySection.earlyOffTime',
								xtype : 'timefield',
								format : 'H:i',
								allowBlank : false
							}, {
								fieldLabel : '下班时间',
								id : 'dutyEndTime',
								vtype : 'timerange',
								minDateField : ['signOutTime'],
								//maxDateField : ['startSignin', 'dutyStartTime',
								//		'endSignin', 'earlyOffTime'],

								name : 'dutySection.dutyEndTime',
								xtype : 'timefield',
								format : 'H:i',
								allowBlank : false
							}, {
								fieldLabel : '签退结束',
								id : 'signOutTime',
								vtype : 'timerange',
								maxDateField : ['startSignin', 'dutyStartTime',
										'endSignin', 'earlyOffTime',
										'dutyEndTime'],

								name : 'dutySection.signOutTime',
								xtype : 'timefield',
								format : 'H:i',
								allowBlank : false
							}]
				});
		// 加载表单对应的数据
		if (!Ext.isEmpty(this.sectionId)) {
			this.formPanel.loadData({
						url : __ctxPath
								+ '/personal/getDutySection.do?sectionId='
								+ this.sectionId,
						root : 'data',
						preName : 'dutySection'
					});
		};
	},// end of the initcomponents

	/**
	 * 重置
	 * 
	 * @param {}
	 *            formPanel
	 */
	reset : function() {
		this.formPanel.getForm().reset();
	},
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
		if (formPanel.getForm().isValid()) {
			// 开始签到
			var ss = formPanel.getCmpByName('dutySection.startSignin')
					.getValue();
			// 上班时间
			var dst = formPanel.getCmpByName('dutySection.dutyStartTime')
					.getValue();
			// 签到结束时间
			var es = formPanel.getCmpByName('dutySection.endSignin').getValue();
			// 早退计时
			var eot = formPanel.getCmpByName('dutySection.earlyOffTime')
					.getValue();
			// 下班时间
			var det = formPanel.getCmpByName('dutySection.dutyEndTime')
					.getValue();
			// 签退结束
			var sot = formPanel.getCmpByName('dutySection.signOutTime')
					.getValue();
			if (compareTime(ss, dst) > 0 || compareTime(ss, es) > 0
					|| compareTime(ss, eot) > 0 || compareTime(ss, det) > 0
					|| compareTime(ss, sot) > 0) {
				Ext.ux.Toast.msg('操作信息', '开始签到时间大于其他时间,不能保存!');
				return;
			} else if (compareTime(dst, eot) > 0 || compareTime(dst, det) > 0
					|| compareTime(dst, sot) > 0) {
				Ext.ux.Toast.msg('操作信息', '上班时间大于早退/下班/签退结束时间,不能保存!');
				return;
			} else if (compareTime(es, eot) > 0 || compareTime(es, det) > 0
					|| compareTime(es, sot) > 0) {
				Ext.ux.Toast.msg('操作信息', '签到结束时间大于早退/下班/签退结束时间,不能保存!');
				return;
			} else if (compareTime(eot, det) > 0 || compareTime(eot, sot) > 0) {
				Ext.ux.Toast.msg('操作信息', '早退计时时间大于下班/签退结束时间,不能保存!');
				return;
			} else if (compareTime(det, sot) > 0) {
				Ext.ux.Toast.msg('操作信息', '下班时间大于签退结束时间,不能保存!');
				return;
			}
			$postForm({
						formPanel : formPanel,
						scope : this,
						url : __ctxPath + '/personal/saveDutySection.do',
						callback : function(fp, action) {
							if (this.callback) {
								this.callback.call(this.scope);
							}
							this.close();
						}
					});
		}// end of save
	}
});