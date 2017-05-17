/**
 * 新建外部邮件
 * 
 * @author zqg
 * @class OutMailForm
 * @extends Ext.Panel
 */
Ext.ns('OutMailForm');
OutMailForm = Ext.extend(Ext.Panel, {
	// 构造方法
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化
		this.initUIComponents();
		// 调用父类的构造方法
		OutMailForm.superclass.constructor.call(this, {
					id : 'OutMailForm',
					title : '外部邮箱-' + (this.title ? this.title : '新建邮件'),
					layout : 'fit',
					items : this.outMailFormPanel,
					modal : true,
					width : 650,
					height : 450,
					maximizable : true
				});

	},// end of constructor
	// 初始化组件
	initUIComponents : function() {
		// 顶部菜单面板
		this.toolbar = new Ext.Toolbar({
					height : 30,
					bodyStyle : 'text-align:left',
					items : [{
								text : '立即发送',
								iconCls : 'btn-mail_send',
								scope : this,
								handler : this.sendMail
							}, '-', {
								text : '存草稿',
								iconCls : 'btn-mail_save',
								scope : this,
								handler : this.save
							}, '-', {
								text : '重置',
								iconCls : 'reset',
								scope : this,
								handler : function() {
									this.clearAttachment();
									this.formPanel.getForm().reset();
								}
							}, '-', {
								text : '取消',
								iconCls : 'btn-mail_remove',
								scope : this,
								handler : function() {
									var tabs = Ext.getCmp('centerTabPanel');
									tabs.remove('OutMailForm');
								}
							}]
				});
		// 邮件详细信息面板
		this.formPanel = new Ext.FormPanel({
			url : __ctxPath + '/communicate/saveOutMail_.do',
			layout : 'form',
			frame : false,
			border : false,
			bodyStyle : 'padding-left:20px;padding-top:10px;',
			defaultType : 'textfield',
			items : [
					// -----------------------------------------hidden 的属性
					{
				xtype : 'panel',
				layout : 'form',
				border : false,
				style : 'padding-left:10%;padding-top:20px;',
				defaultType : 'textfield',
				labelWidth : 60,
				width : 650,
				items : [{
							fieldLabel : '发送人名称',
							name : 'outMail.senderName',
							xtype : 'hidden'
						}, {
							fieldLabel : '接受人址',
							name : 'outMail.receiverAddresses',
							xtype : 'hidden'
						}, {
							fieldLabel : '抄送人地址',
							name : 'outMail.cCAddresses',
							value : '',
							xtype : 'hidden'
						}, {
							fieldLabel : '操作',
							name : 'opt',
							xtype : 'hidden',
							value : this.opt
						}, {
							fieldLabel : '附件IDs',
							name : 'outMail.fileIds',
							xtype : 'hidden',
							id : 'fileIds',
							value : ''
						}, {
							fieldLabel : 'MailId',
							name : 'outMail.mailId',
							xtype : 'hidden'
						}, {
							fieldLabel : '已回复',
							name : 'outMail.replyFlag',
							xtype : 'hidden'
						}, {
							fieldLabel : '附件名称列表',
							name : 'outMail.fileNames',
							xtype : 'hidden'
						}, {
							fieldLabel : 'setId',
							name : 'outMail.outMailUserSeting.setId',
							id : 'outMail.outMailUserSeting.setId',
							xtype : 'hidden'
						},
						// ------------------------------------------ hidden
						// end
						{
							fieldLabel : '发件人',
							width : 360,
							xtype : 'textfield',
							name : 'outMail.senderAddresses',
							allowBlank : false,
							xtype : 'combo',
							mode : 'local',
							editable : false,
							triggerAction : 'all',
							store : new Ext.data.JsonStore({
								url : __ctxPath
										+ "/communicate/listOutMailUserSeting.do",
								autoLoad : true,
								autoShow : true,
								root : 'result',
								idProperty : 'setId',
								fields : ['setId', 'mailAddress']
							}),
							valueField : 'setId',
							displayField : 'mailAddress',
							listeners : {
								scope : this,
								'select' : function(combo, record, index) {
									this.formPanel
											.getCmpByName('outMail.outMailUserSeting.setId')
											.setValue(record.get('setId'));
								}
							}
						}, {
							xtype : 'container',
							border : false,
							layout : 'column',
							height : 26,
							bodyStyle : 'padding-top:20px;',
							defaultType : 'textfield',
							items : [{
										xtype : 'label',
										text : '收件人:',
										style : 'padding-left:0px;padding-top:3px;',
										width : 65
									}, {
										width : 353,
										fieldLabel : '收件人姓名列表',
										name : 'outMail.receiverNames',
										allowBlank : false,
										blankText : '请选择收件人',
										readOnly : false
									}, {
										xtype : 'button',
										text : '选择收件人',
										iconCls : 'btn-mail_recipient',
										scope : this,
										handler : this.addRecipient
									}, {
										xtype : 'button',
										text : '我要抄送',
										iconCls : 'btn-mail_copy',
										scope : this,
										handler : this.selectCopy
									}]
						}, {
							xtype : 'container',// 抄送人container
							name : 'outMail.ccField',
							id : 'ccField',
							layout : 'column',
							style : 'padding-left:0px;',
							height : 26,
							hidden : true,
							defaultType : 'textfield',
							items : [{
										xtype : 'label',
										text : '抄送人:',
										style : 'padding-left:0px;padding-top:3px;',
										width : 65
									}, {
										width : 350,
										fieldLabel : '抄送人姓名列表',
										name : 'outMail.cCNames',
										value : '',
										emptyText : '',
										readOnly : false
									}, {
										xtype : 'button',
										text : '选择抄送人',
										iconCls : 'btn-mail_recipient',
										scope : this,
										handler : this.addCCPeople
									}, {
										xtype : 'button',
										text : '取消抄送',
										iconCls : 'btn-delete_copy',
										scope : this,
										handler : this.delCarbonCopy
									}]
						}, {
							width : 503,
							fieldLabel : '主题',
							xtype : 'textfield',
							name : 'outMail.title',
							allowBlank : false,
							blankText : '邮件主题为必填'
						}, {
							xtype : 'container',
							layout : 'column',
							height : 50,
							name : mailAttachPanel,
							border : false,
							fieldLabel : '附件',
							items : [{
										columnWidth : .75,
										layout : 'form',
										border : false,
										items : [{
													id : 'outMailFileNames.display',
													name : 'outMailFileNames.display',
													xtype : 'panel',
													items : '',
													height : 50,
													autoScroll : true,
													border : true
												}]
									}, {
										columnWidth : .25,
										layout : 'form',
										border : false,
										defaultType : 'button',
										items : [{
													iconCls : 'menu-attachment',
													xtype : 'button',
													text : '添加附件',
													scope : this,
													handler : this.addAttachment
												}, {
													text : '清除附件',
													iconCls : 'reset',
													scope : this,
													handler : this.clearAttachment
												}]
									}]
						}, {
							fieldLabel : '内容',
							name : 'outMail.content',
							xtype : 'htmleditor',
							height : 280
						}]
			}]
		});

		// 加载表单数据
		if (this.mailId != null && this.mailId != 'undefined') {
			var mailId = this.mailId;
			var opt = this.opt;
			var win = this;
			var formPanel = this.formPanel;
			var _mailId = formPanel.getCmpByName('outMail.mailId');
			var mailAttachPanel = formPanel.getCmpByName('mailAttachPanel');
			if (opt == 'draft') {// 重载草稿
				_mailId.setValue(mailId);// 草稿才需要在表单记录邮件ID
				formPanel.loadData({
							url : __ctxPath + '/communicate/getOutMail_.do',
							method : 'post',
							preName : 'outMail',
							params : {
								mailId : mailId
							},
							waitMsg : '正在载入数据...',
							success : function(response, option) {
								var outMail = Ext.util.JSON
										.decode(response.responseText).data;
								var attachFiles = outMail.outMailFiles;
								win.loadAttachment.call(win, attachFiles);
								var receiverAddresses = outMail.receiverAddresses;
								var receiverNames = outMail.receiverNames;
								var receiver = win.getStrToEmail.call(win,
										receiverAddresses, receiverNames);
								formPanel.getCmpByName('outMail.receiverNames')
										.setValue(receiver);
								var cCAddresses = outMail.cCAddresses;
								if (cCAddresses != '' && cCAddresses != 'null') {// 假如草稿有抄送人,激活抄送人控件
									receiver = win.getStrToEmail.call(win,
											outMail.cCAddresses,
											outMail.cCNames);
									formPanel.getCmpByName('outMail.cCNames')
											.setValue(receiver);
									var copyField = formPanel
											.getCmpByName('outMail.ccField');
									copyField.show();
								}

							}
						});
			} else if (opt == 'reply') {// 回复
				formPanel.loadData({
							url : __ctxPath + '/communicate/optOutMail_.do',
							method : 'post',
							preName : 'outMail',
							params : {
								mailId : mailId,
								opt : '回复'
							},
							waitMsg : '正在载入数据...',
							success : function(response, option) {
								formPanel.getCmpByName('outMail.mailId')
										.setValue(mailId);
								var outMail = Ext.util.JSON
										.decode(response.responseText).data;
								var receiverAddresses = outMail.receiverAddresses;
								var receiverNames = outMail.receiverNames;
								var receiver = win.getStrToEmail.call(win,
										receiverAddresses, receiverNames);
								formPanel.getCmpByName('outMail.receiverNames')
										.setValue(receiver);
							}
						});
			} else if (opt == 'forward') {// 转发
				formPanel.loadData({
							deferredRender : false,
							url : __ctxPath + '/communicate/optOutMail_.do',
							method : 'post',
							preName : 'outMail',
							params : {
								mailId : mailId,
								opt : '转发'
							},
							waitMsg : '正在载入数据...',
							success : function(response, option) {
								var results = Ext.util.JSON
										.decode(response.responseText).data;
								var attachFiles = results.outMailFiles;
								win.loadAttachment.call(win, attachFiles);
							}
						});
			}
		} else {
			var formPanel = this.formPanel;
			var url = __ctxPath + "/communicate/getDefaultOutMailUserSeting.do";
			if (this.setId != null && this.setId != 'undefined') {
				url = __ctxPath + "/communicate/getOutMailUserSeting.do?setId="
						+ this.setId;
			}
			formPanel.loadData({
				url : url,
				method : 'post',
				preName : 'outMailUserSeting',
				waitMsg : '正在载入数据...',
				success : function(response, option) {
					var results = Ext.util.JSON.decode(response.responseText).data;
					formPanel.getCmpByName('outMail.senderAddresses')
							.setValue(results.mailAddress);
					formPanel.getCmpByName('outMail.outMailUserSeting.setId')
							.setValue(results.setId);
				}
			});
		}
		// 邮件中部菜单面板
		this.outMailFormPanel = new Ext.Panel({
					title : '发送邮件',
					iconCls : 'menu-mail_send',
					autoScroll : true,
					tbar : this.toolbar,
					plain : true,
					bodyStyle : 'padding:5px;',
					items : [this.formPanel]
				});
	},
	// 发送邮件
	sendMail : function() {
		var formPanel = this.formPanel;
		var setId = formPanel.getCmpByName("outMail.outMailUserSeting.setId")
				.getValue();
		if (formPanel.getForm().isValid()) {
			formPanel.getForm().submit({
				waitMsg : '正在发送邮件,请稍候...',
				timeout : 120000,
				scope : this,
				success : function(formPanel, o) {
					Ext.Msg.confirm('操作信息', '邮件发送成功！继续发邮件?', function(btn) {
								if (btn == 'yes') {
									this.clearAttachment();
									formPanel.reset();
									Ext
											.getCmp("outMail.outMailUserSeting.setId")
											.setValue(setId);
								} else {
									var tabs = Ext.getCmp('centerTabPanel');
									tabs.remove('OutMailForm');
								}
							}, this);
				},
				failure : function(formPanel, o) {
					if (o.result != null && o.result != undefined) {
						Ext.ux.Toast.msg('错误信息', o.result.msg);
					}
				}
			});
		}
	},
	// 存草稿
	save : function() {
		var formPanel = this.formPanel;
		var setId = formPanel.getCmpByName("outMail.outMailUserSeting.setId")
				.getValue();
		if (formPanel.getForm().isValid()) {
			formPanel.getCmpByName("opt").setValue("attach");
			formPanel.getForm().submit({
				waitMsg : '正在保存草稿,请稍候...',
				success : function(formPanel, o) {
					Ext.Msg.confirm('操作信息', '草稿保存成功！继续发邮件?', function(btn) {
								if (btn == 'yes') {
									this.clearAttachment();
									formPanel.reset();
									Ext
											.getCmp("outMail.outMailUserSeting.setId")
											.setValue(setId);
								} else {
									var tabs = Ext.getCmp('centerTabPanel');
									tabs.remove('OutMailForm');
								}
							}, this);
				},
				failure : function(mailform, o) {
					if (o.result != null && o.result != undefined) {
						Ext.ux.Toast.msg('错误信息', o.result.msg);
					}
				}
			});
		}
	},

	// 选择联系人
	addRecipient : function() {
		var formPanel = this.formPanel;
		new EMailDialog({
					callback : function(fullnames) {
						var receiverNames = formPanel
								.getCmpByName('outMail.receiverNames');
						receiverNames.setValue(fullnames);
					}
				}).show();
	},
	// 选择我抄送
	selectCopy : function() {
		var cCValue = this.formPanel.getCmpByName('outMail.cCAddresses')
				.getValue();
		if (cCValue == "null" || cCValue == null) {
			this.formPanel.getCmpByName('outMail.cCNames').setValue('');
			this.formPanel.getCmpByName('outMail.cCAddresses').setValue('');
		}
		this.formPanel.getCmpByName('outMail.ccField').show();
	},
	// 选择抄送人
	addCCPeople : function() {
		var formPanel = this.formPanel;
		new EMailDialog({
					callback : function(fullnames) {
						var receiverNames = formPanel
								.getCmpByName('outMail.cCNames');
						receiverNames.setValue(fullnames);
					}
				}).show();
	},
	// 取消抄送
	delCarbonCopy : function() {// 取消抄送时设置为空
		formPanel = this.formPanel;
		var copyField = formPanel.getCmpByName('outMail.ccField');
		formPanel.getCmpByName('outMail.cCAddresses').setValue('');
		formPanel.getCmpByName('outMail.cCNames').setValue('');
		copyField.hide();
	},
	// 加载附件
	loadAttachment : function(attachFiles) {
		var fileIds = this.formPanel.getCmpByName("outMail.fileIds");
		var filePanel = this.formPanel.getCmpByName('outMailFileNames.display');
		for (var i = 0; i < attachFiles.length; i++) {
			if (fileIds.getValue() != '') {
				fileIds.setValue(fileIds.getValue() + ',');
			}
			var fileId = attachFiles[i].fileId;
			var fileName = attachFiles[i].fileName;
			fileIds.setValue(fileIds.getValue() + fileId);
			Ext.DomHelper
					.append(
							filePanel.body,
							'<span><a href="#" onclick="FileAttachDetail.show('
									+ fileId
									+ ')">'
									+ fileName
									+ '</a><img class="img-delete" src="'
									+ __ctxPath
									+ '/images/system/delete.gif" onclick="OutMailForm.prototype.removeFile(this,'
									+ fileId + ')"/>&nbsp;|&nbsp;</span>');
		}
	},
	// 添加附件
	addAttachment : function() {
		var dialog = App.createUploadDialog({
					file_cat : 'communication/outMail',
					scope : this,
					callback : function(data) {
						this.loadAttachment.call(this, data);
					}
				});
		dialog.show(this);
	},
	/**
	 * 清除附件
	 */
	clearAttachment : function() {
		this.formPanel.getCmpByName('outMail.fileIds').setValue('');
		this.formPanel.getCmpByName('outMail.fileNames').setValue('');
		this.formPanel.getCmpByName('outMailFileNames.display').update();
	},
	// 单个删除附件
	removeFile : function(obj, fileId) {
		var fileIds = Ext.getCmp('fileIds');
		var value = fileIds.getValue();
		if (value.indexOf(',') < 0) {// 仅有一个附件
			fileIds.setValue('');
		} else {
			value = value.replace(',' + fileId, '').replace(fileId + ',', '');
			fileIds.setValue(value);
		}
		var el = Ext.get(obj.parentNode);
		el.remove();
	},
	/**
	 * 
	 * @param address
	 * @param name
	 * @return 将字附串组成 "xx"<xxx@xx.com>;"aa"<aa@aa.com>;形式返回
	 */
	getStrToEmail : function(address, name) {
		var emailStr = "";
		if (address == null || address == 'null')
			address = "";
		if (name == null || name == 'null')
			name = "";
		if (address.length > 1) {
			var a = address.split(",");
			var n = name.split(",");
			if (a != null && a.length > 0) {
				if (n != null && n.length > 0) {
					for (var i = 0; i < a.length; i++) {
						if (n.length >= i) {
							emailStr += "" + n[i].replace("\"", "") + "" + "<"
									+ a[i] + ">" + ";";
						} else {
							emailStr += "" + a[i] + "" + "<" + a[i] + ">" + ";";
						}
					}
				} else {
					for (var i = 0; i < a.length; i++) {
						emailStr += "" + a[i] + "" + "<" + a[i] + ">" + ";";
					}
				}
			}
		}
		return emailStr;
	}
});
