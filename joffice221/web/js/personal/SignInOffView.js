Ext.ns('SignInOffView');
/**
 * 考勤--签到、签退
 */
SignInOffView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		SignInOffView.superclass.constructor.call(this, {
					id : 'SignInOffView',
					title : '考勤--签到、签退',
					region : 'center',
					iconCls : 'menu-signInOff',
					layout : 'border',
					items : [this.mainPanel, this.gridPanel]
				});
	},
	// 初始化组件
	initUIComponents : function() {
		// 显示钟表图片的面板
		this.mainPanel = new Ext.Panel({
			layout : 'hbox',
			region : 'south',
			height : 250,
			border : false,
			bodyStyle : 'padding-top:20px;padding-left:20px;text-align:center;cursor:pointer;',
			html : '<h1>请按规定的时间进行上下班签到签退</h1><img src="' + __ctxPath
					+ '/images/clock.jpg"/>',
			listeners : {
				scope : this,
				'render' : function() {
					this.mainPanel.getEl().on('click', function() {
								this.signInOff();
							}, this)
				}
			}
		});
		// 顶端栏目
		this.topbar = new Ext.Toolbar({
					items : [{
								text : '刷新',
								xtype : 'button',
								iconCls : 'btn-refresh',
								scope : this,
								handler : this.refresh
							}, '-', {
								text : '请假登记',
								xtype : 'button',
								iconCls : 'menu-holiday',
								scope : this,
								handler : this.holiday
							}, '-', {
								text : '外出登记',
								xtype : 'button',
								iconCls : 'menu-errands',
								scope : this,
								handler : this.errands
							}, '-', {
								text : '个人考勤查询',
								xtype : 'button',
								iconCls : 'menu-person',
								scope : this,
								handler : this.person
							}]
				});

		// 上下班签到面板
		this.gridPanel = new HT.GridPanel({
					id : 'SignInOffGrid',
					region : 'center',
					margins : '0',
					border : false,
					tbar : this.topbar,// 顶端按钮组
					showChbCol : false,
					showPaging : false,
					url : __ctxPath + '/personal/todayDutyRegister.do',
					fields : ['sectionId', 'systemName', 'dutyStartTime',
							'signInTime', 'signInFlag', 'dutyEndTime',
							'signOffTime', 'signOffFlag', 'allowSignIn',
							'allowSignOff'],
					columns : [{
								header : "sectionId",
								dataIndex : 'sectionId',
								hidden : true
							}, {
								header : "signInFlag",
								dataIndex : 'signInFlag',
								hidden : true
							}, {
								header : "signOffFlag",
								dataIndex : 'signOffFlag',
								hidden : true
							}, {
								header : "allowSignIn",
								dataIndex : 'allowSignIn',
								hidden : true
							}, {
								header : "allowSignOff",
								dataIndex : 'allowSignOff',
								hidden : true
							}, {
								header : '班次名称',
								dataIndex : 'systemName'
							}, {
								header : "上班规定时间",
								dataIndex : 'dutyStartTime'
							}, {
								header : "签到时间",
								dataIndex : 'signInTime'
							}, {
								header : '签到',
								dataIndex : 'signInTime',
								renderer : this.signInTime
							}, {
								header : "下班规定时间",
								dataIndex : 'dutyEndTime'
							}, {
								header : "签退时间",
								dataIndex : 'signOffTime'
							}, {
								header : '签退',
								dataIndex : 'signOffTime',
								renderer : this.signOffTime
							}]
				});
	},
	// 刷新
	refresh : function() {
		this.gridPanel.getStore().reload();
	},
	// 请假登记
	holiday : function() {
		App.clickTopTab('ErrandsRegisterView');
	},
	// 外出登记
	errands : function() {
		App.clickTopTab('ErrandsRegisterOutView');
	},
	// 个人考勤查询
	person : function() {
		App.clickTopTab('DutyRegisterPersonView');
	},
	/**
	 * 签到
	 * 
	 * @param {}
	 *            val
	 * @param {}
	 *            metadata
	 * @param {}
	 *            record
	 * @return {}
	 */
	signInTime : function(val, metadata, record) {
		var sectionId = record.data.sectionId;
		var signInFlag = record.data.signInFlag;

		if (signInFlag != '') {
			// 显示签到的状态
			if (signInFlag == 1) {// 正常登记
				return '<img src="' + __ctxPath
						+ '/images/flag/personal/signNormal.gif" title="正常"/>';
			} else if (signInFlag == 2) {// 迟到
				return '<img src="'
						+ __ctxPath
						+ '/images/flag/personal/signLateEarly.gif" title="迟到"/>';
			}
		} else {
			var allowSignIn = record.data.allowSignIn;
			if (allowSignIn == '1') {
				return '<button class="btn-signIn" title="签到" onclick="SignInOffView.prototype.signIn('
						+ sectionId + ');">&nbsp;</button>';

			} else if (allowSignIn == '-1') {
				return "尚未到签到时间";
			} else if (allowSignIn == '0') {
				return "<font color='red'>已过签到时间</font>";
			} else {
				return "<font color='red'>用户今天排班休息</font>";
			}

		}
	},
	/**
	 * 签退
	 * 
	 * @param {}
	 *            val
	 * @param {}
	 *            metadata
	 * @param {}
	 *            record
	 * @return {}
	 */
	signOffTime : function(val, metadata, record) {
		var sectionId = record.data.sectionId;
		var signOffFlag = record.data.signOffFlag;

		if (signOffFlag != '') {
			// 显示签到的状态
			if (signOffFlag == 1) {// 正常登记
				return '<img src="' + __ctxPath
						+ '/images/flag/personal/signNormal.gif" title="正常"/>';
			} else if (signOffFlag == 3) {// 早退
				return '<img src="'
						+ __ctxPath
						+ '/images/flag/personal/signLateEarly.gif" title="早退"/>';
			}
		} else {
			var allowSignOff = record.data.allowSignOff;
			if (allowSignOff == '1') {
				return '<button class="btn-signOff" title="签退" onclick="SignInOffView.prototype.signOff('
						+ sectionId + ');">&nbsp;</button>';
			} else if (allowSignOff == '-1') {
				return "尚未到签退时间";
			} else if (allowSignOff == '0') {
				return "<font color='red'>已过签退时间</font>";
			}else {
				return "<font color='red'>用户今天排班休息</font>";
			}
		}
	},
	/**
	 * 点击签到
	 * 
	 * @param {}
	 *            sectionId
	 */
	signIn : function(sectionId) {
		Ext.Msg.confirm('信息确认', '您确定是否进行签到吗？', function(btn) {
			if (btn == 'yes') {
				Ext.Ajax.request({
							url : __ctxPath + '/personal/signInDutyRegister.do',
							method : 'POST',
							params : {
								sectionId : sectionId
							},
							scope : this,
							success : function(response, options) {
								Ext.getCmp('SignInOffGrid').getStore().reload();
								Ext.ux.Toast.msg('操作信息', '成功签到！');
							}
						});
			}
		});
	},
	/**
	 * 点击签退
	 * 
	 * @param {}
	 *            sectionId
	 */
	signOff : function(sectionId) {
		Ext.Msg.confirm('信息确认', '您确定是否进行签退吗？', function(btn) {
			if (btn == 'yes') {
				Ext.Ajax.request({
							url : __ctxPath
									+ '/personal/signOffDutyRegister.do',
							method : 'POST',
							params : {
								sectionId : sectionId
							},
							scope : this,
							success : function(response, options) {
								Ext.getCmp('SignInOffGrid').getStore().reload();
								Ext.ux.Toast.msg('操作信息', '成功签退！');
							}
						});
			}
		});
	},

	/**
	 * 点击图片签到或签退
	 */
	signInOff : function() {
		Ext.Ajax.request({
					url : __ctxPath + '/personal/signInOffDutyRegister.do',
					method : 'POST',
					scope : this,
					success : function(response, options) {
						var result = Ext.util.JSON
								.decode(response.responseText);
						if (result.success) {
							if (result.type == '1') {

								SignInOffView.prototype
										.signIn(result.sectionId);
							} else {

								SignInOffView.prototype
										.signOff(result.sectionId);

							}
						} else {
							Ext.ux.Toast.msg('操作信息', result.message);
						}
					},
					failure : function(response, options) {
						Ext.ux.Toast.msg('操作信息', '操作出错，请联系管理员！');
					}
				});
	}

});
