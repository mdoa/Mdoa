Ext.ns('SysConfigView');
/**
 * @author zxh
 * @createtime
 * @class SysConfigView
 * @extends Ext.Window
 * @description 系统配置
 * @company 宏天软件
 */
SysConfigView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		// 必须先初始化组件
		this.initUI();
		SysConfigView.superclass.constructor.call(this, {
					id : 'SysConfigView',
					title : '系统配置',
					iconCls : 'menu-system-setting',
					tbar : this.tbar,
					autoScroll : true,
					items : this.formPanel
				});
	},// end of the constructor
	// 初始化组件
	initUI : function() {
		// 按钮组
		this.tbar = [{
					text : '保存',
					iconCls : 'btn-save',
					scope : this,
					handler : this.save
				}, '-', {
					text : '重置',
					iconCls : 'btn-reseted',
					scope : this,
					handler : this.reset
				}, '-', {
					text : '关闭',
					iconCls : 'btn-cancel',
					scope : this,
					handler : this.cancel
				}]

		// 表单
		this.formPanel = new Ext.FormPanel({
					url : __ctxPath + '/system/saveSysConfig.do',
					defaultType : 'textfield',
					bodyStyle : 'padding-left:10%;',
					frame : false,
					border : false,
					layout : 'form',
					items : []
				});
		this.loadSysConfigData();
	},
	loadSysConfigData : function() {
		var formPanel = this.formPanel;
		Ext.Ajax.request({
			url : __ctxPath + "/system/loadSysConfig.do",
			success : function(response, options) {
				// alert(response.responseText);
				var object = Ext.util.JSON.decode(response.responseText)

				// // start of the mail config
				// var mailcon = object.data.mailConfig;
				// var mailConfigItems = [];
				// if (mailcon) {
				// for (var i = 0; i < mailcon.length; i++) {
				// mailConfigItems.push({
				// xtype : 'container',
				// style : 'padding-bottom:3px;',
				// layout : 'column',
				// items : [{
				// xtype : 'label',
				// style : 'font-weight:bold;',
				// text : mailcon[i].configName,
				// width : 100
				// }, {
				// xtype : 'textfield',
				// width : 300,
				// allowBlank : false,
				// id : mailcon[i].configKey,
				// name : mailcon[i].configKey,
				// value : mailcon[i].dataValue
				// }, {
				// xtype : 'label',
				// width : 200,
				// text : mailcon[i].configDesc
				// }]
				// })
				// }
				// }
				// var mailConfig = {
				// xtype : 'fieldset',
				// title : '系统邮件配置',
				// width : 650,
				// style : 'padding-bottom:3px;',
				// layout : 'form',
				// items : mailConfigItems
				// };

				// end of the mailConfig

				// start adminConfig
				var adminCon = object.data.adminConfig;
				var adminConfigItems = [];
				if (adminCon) {
					for (var i = 0; i < adminCon.length; i++) {
						adminConfigItems.push({
									xtype : 'container',
									style : 'padding-bottom:3px;',
									layout : 'column',
									items : [{
												xtype : 'label',
												style : 'font-weight:bold;',
												text : adminCon[i].configName,
												width : 100
											}, {
												xtype : 'textfield',
												width : 300,
												allowBlank : false,
												id : adminCon[i].configKey,
												name : adminCon[i].configKey,
												value : adminCon[i].dataValue
											}, {
												xtype : 'label',
												width : 200,
												text : adminCon[i].configDesc
											}]
								})
					}

				}
				var adminConfig = {
					xtype : 'fieldset',
					title : '行政管理配置',
					width : 650,
					style : 'padding-bottom:3px;',
					layout : 'form',
					items : adminConfigItems
				};
				// end of the admin config
				// start of the code config
				var codeCon = object.data.codeConfig;
				var codeConfigItems = [];
				if (codeCon) {
					for (var i = 0; i < codeCon.length; i++) {
						codeConfigItems.push({
							xtype : 'container',
							style : 'padding-bottom:3px;',
							layout : 'column',
							items : [{
										xtype : 'label',
										style : 'font-weight:bold;',
										text : codeCon[i].configName,
										width : 100
									}, {
										xtype : 'combo',
										mode : 'local',
										editable : false,
										triggerAction : 'all',
										store : [['1', '开启验证码'], ['2', '屏蔽验证码']],
										width : 300,
										allowBlank : false,
										hiddenName : codeCon[i].configKey,
										value : codeCon[i].dataValue
									}, {
										xtype : 'label',
										width : 200,
										text : codeCon[i].configDesc
									}]
						})
					}
				}
				var codeConfig = {
					xtype : 'fieldset',
					title : '验证码配置',
					width : 650,
					style : 'padding-bottom:3px;',
					layout : 'form',
					items : codeConfigItems
				};
				// end of the code config

				// start of the sms config
				var smsCon = object.data.smsConfig;
				var smsConfigItems = [];
				if (smsCon) {
					for (var i = 0; i < smsCon.length; i++) {
						if (smsCon[i].dataType == 2) {
							smsConfigItems.push({
										xtype : 'container',
										style : 'padding-bottom:3px;',
										layout : 'column',
										items : [{
													xtype : 'label',
													style : 'font-weight:bold;',
													text : smsCon[i].configName,
													width : 100
												}, {
													xtype : 'combo',
													mode : 'local',
													editable : false,
													triggerAction : 'all',
													store : [['1', '打开'],
															['2', '关闭']],
													width : 300,
													allowBlank : false,
													hiddenName : smsCon[i].configKey,
													value : smsCon[i].dataValue
												}, {
													xtype : 'label',
													width : 200,
													text : smsCon[i].configDesc
												}]
									})
						} else {
							smsConfigItems.push({
										xtype : 'container',
										style : 'padding-bottom:3px;',
										layout : 'column',
										items : [{
													xtype : 'label',
													style : 'font-weight:bold;',
													text : smsCon[i].configName,
													width : 100
												}, {
													xtype : 'textfield',
													width : 300,
													allowBlank : false,
													id : smsCon[i].configKey,
													name : smsCon[i].configKey,
													value : smsCon[i].dataValue
												}, {
													xtype : 'label',
													width : 200,
													text : smsCon[i].configDesc
												}]
									})
						}
					}
					smsConfigItems.push({
								xtype : 'container',
								style : 'padding-bottom:3px;',
								layout : 'column',
								items : [{
											xtype : 'button',
											iconCls : 'menu-mobile',
											text : '测试设备连接',
											handler : function() {
												new SmsMobileForm({

												}).show();
											}
										}]
							})

				}

				var smsConfig = {
					xtype : 'fieldset',
					title : '短信猫配置',
					width : 650,
					style : 'padding-bottom:3px;',
					layout : 'form',
					items : smsConfigItems
				};

				// end of the sms config

				// start of the sms config
				var smsPort = object.data.smsPortConfig;
				var smsPortConfigItems = [];
				if (smsPort) {
					for (var i = 0; i < smsPort.length; i++) {
						smsPortConfigItems.push({
									xtype : 'container',
									style : 'padding-bottom:3px;',
									layout : 'column',
									items : [{
												xtype : 'label',
												style : 'font-weight:bold;',
												text : smsPort[i].configName,
												width : 100
											}, {
												xtype : 'textfield',
												width : 300,
												id : smsPort[i].configKey,
												name : smsPort[i].configKey,
												value : smsPort[i].dataValue
											}, {
												xtype : 'label',
												width : 200,
												text : smsPort[i].configDesc
											}]
								})
					}
					smsPortConfigItems.push({
								xtype : 'container',
								style : 'padding-bottom:3px;',
								layout : 'column',
								items : [{
											xtype : 'button',
											iconCls : 'menu-mobile',
											text : '测试手机端口',
											handler : function() {
												new SmsMobileForm({

												}).show();
											}
										}]
							})
				}
				var smsPortConfig = {
					xtype : 'fieldset',
					title : '短信端口配置',
					width : 650,
					style : 'padding-bottom:3px;',
					layout : 'form',
					items : smsPortConfigItems
				};
				// end of the sms config

				// start of the suggest config
				var suggestCon = object.data.suggestConfig;
				var suggestConfigItems = [];
				if (suggestCon) {
					suggestConfigItems.push({
						xtype : 'container',
						style : 'padding-bottom:3px;',
						layout : 'column',
						items : [{
									xtype : 'label',
									style : 'font-weight:bold;',
									text : suggestCon[1].configName,
									width : 100
								}, {
									xtype : 'textfield',
									editable : false,
									width : 300,
									allowBlank : false,
									id : suggestCon[1].configKey,
									name : suggestCon[1].configKey,
									value : suggestCon[1].dataValue
								}, {
									xtype : 'button',
									text : '选择',
									iconCls : 'btn-add',
									scope : this,
									handler : function() {
										var fullname = Ext
												.getCmp(suggestCon[1].configKey);
										var userId = Ext
												.getCmp(suggestCon[0].configKey)
										new UserDialog({
													scope : this,
													isForFlow : false,
													callback : function(ids,
															names) {
														userId.setValue(ids);
														fullname
																.setValue(names);
													}
												}).show();
									}
								}, {
									xtype : 'label',
									width : 100,
									text : suggestCon[1].configDesc
								}, {
									xtype : 'hidden',
									editable : false,
									width : 300,
									allowBlank : false,
									id : suggestCon[0].configKey,
									name : suggestCon[0].configKey,
									value : suggestCon[0].dataValue
								}]
					});
				}
				var suggestConfig = {
					xtype : 'fieldset',
					title : '意见箱配置',
					width : 650,
					style : 'padding-bottom:3px;',
					layout : 'form',
					items : suggestConfigItems
				};
				// end of the suggest config
				// start of the dynamicPwd config
				var dynamicPwdCon = object.data.dynamicPwdConfig;
				var dynamicPwdConfigItems = [];
				if (dynamicPwdCon) {
					dynamicPwdConfigItems.push({
						xtype : 'container',
						style : 'padding-bottom:3px;',
						layout : 'column',
						items : [{
							xtype : 'container',
							style : 'padding-bottom:3px;',
							layout : 'column',
							items : [{
										xtype : 'label',
										style : 'font-weight:bold;',
										text : dynamicPwdCon[0].configName,
										width : 100
									}, {
										xtype : 'combo',
										mode : 'local',
										editable : false,
										triggerAction : 'all',
										store : [['1', '打开'], ['2', '关闭']],
										width : 300,
										allowBlank : false,
										hiddenName : dynamicPwdCon[0].configKey,
										value : dynamicPwdCon[0].dataValue
									}, {
										xtype : 'label',
										width : 200,
										text : dynamicPwdCon[0].configDesc
									}]
						}, {
							xtype : 'label',
							style : 'font-weight:bold;',
							text : dynamicPwdCon[1].configName,
							width : 100
						}, {
							xtype : 'textfield',
							width : 300,
							allowBlank : false,
							id : dynamicPwdCon[1].configKey,
							name : dynamicPwdCon[1].configKey,
							value : dynamicPwdCon[1].dataValue
						}, {
							xtype : 'label',
							width : 200,
							text : dynamicPwdCon[1].configDesc
						}]
					});
				}

				var dynamicPwdConfig = {
					xtype : 'fieldset',
					title : '动态密码配置',
					width : 650,
					style : 'padding-bottom:3px;',
					layout : 'form',
					items : dynamicPwdConfigItems
				};
				// end of the dynamicPwd config
				// formPanel.add(mailConfig);
				formPanel.add(adminConfig);
				formPanel.add(codeConfig);
				formPanel.add(smsConfig);
				formPanel.add(smsPortConfig);
				formPanel.add(suggestConfig);
				formPanel.add(dynamicPwdConfig);
				formPanel.doLayout();
			}
		});
	},
	/**
	 * 关闭
	 */
	cancel : function() {
		var tabs = Ext.getCmp('centerTabPanel');
		tabs.remove(this);
	},
	/**
	 * 重置
	 */
	reset : function() {
		this.formPanel.removeAll();
		this.loadSysConfigData();
	},
	/**
	 * 保存
	 */
	save : function() {
		var formPanel = this.formPanel;
		if (formPanel.getForm().isValid()) {
			formPanel.getForm().submit({
						method : 'post',
						waitMsg : '正在提交数据...',
						success : function(fp, action) {
							Ext.ux.Toast.msg('操作信息', '成功信息保存！');
							// var tabs = Ext.getCmp('centerTabPanel');
							// tabs.remove('SysConfigView');
						},
						failure : function(fp, action) {
							Ext.MessageBox.show({
										title : '操作信息',
										msg : '信息保存出错，请联系管理员！',
										buttons : Ext.MessageBox.OK,
										icon : 'ext-mb-error'
									});
						}
					});

		}
	}
});