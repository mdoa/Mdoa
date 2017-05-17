/**
 * 新建邮件
 * @author zqg
 * @class MailForm
 * @extends Ext.Panel
 */
 Ext.ns('MailForm');
MailForm = Ext.extend(Ext.Panel, {
	//构造方法
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		//必须先初始化
		this.initUIComponents();
		//调用父类的构造方法
		MailForm.superclass.constructor.call(this, {
					id : 'MailForm',
					title : '新建邮件',
					layout : 'fit',
					items : this.mailFormPanel,
					modal : true,
					width : 650,
					height : 450,
					maximizable : true
				});

	},// end of constructor
	// 初始化组件
	initUIComponents : function() {
		//顶部菜单面板
		this.toolbar = new Ext.Toolbar({
				height : 30,
				bodyStyle : 'text-align:left',
				items : [
						{
							text : '立即发送',
							iconCls : 'btn-mail_send',
							scope : this,
							handler : this.sendMail
						},
						{
							text : '存草稿',
							iconCls : 'btn-mail_save',
							scope : this,
							handler : this.save
						}, {
							text : '重置',
							iconCls : 'reset',
							scope : this,
							handler : function() {
								this.formPanel.getForm().reset();
							}
						}, {
							text : '取消',
							iconCls : 'btn-mail_remove',
							scope : this,
							handler : function() {
								var tabs = Ext.getCmp('centerTabPanel');
								tabs.remove('MailForm');
							}
						} ]
			});	
		//邮件详细信息面板	
		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			frame : false,
			border : false,
			bodyStyle : 'padding-left:20px;padding-top:10px;',
			defaultType : 'textfield',
			items : [{
						fieldLabel : '收件人ID列表用,分隔',
						name : 'mail.recipientIDs',
						xtype : 'hidden'
					}, {
						fieldLabel : '抄送人ID列表用,分开',
						name : 'mail.copyToIDs',
						xtype : 'hidden'
					}, {
						fieldLabel : '邮件状态',
						name : 'mail.mailStatus',
						xtype : 'hidden',
						value : 1
					}, {
						fieldLabel : '附件IDs',
						name : 'mail.fileIds',
						xtype : 'hidden'
					}, {
						fieldLabel : 'BOXID',
						name : 'boxId',
						xtype : 'hidden'
					}, {
						fieldLabel : 'MailId',
						name : 'mail.mailId',
						xtype : 'hidden'
					}, {
						fieldLabel : '操作',
						name : 'replyBoxId',
						xtype : 'hidden'
					}, {
						fieldLabel : '附件名称列表',
						name : 'mail.filenames',
						xtype : 'hidden'
					},{
						fieldLabel : '主题',
						xtype : 'textfield',
						name : 'mail.subject',
						allowBlank : false,
						width : 400,
						blankText : '邮件主题为必填'
					}, {
						xtype : 'compositefield',
						autoWidth : true,
						fieldLabel : '收件人',
						items : [{
									width : 400,
									height : 21,
									name : 'mail.recipientNames',
									xtype : 'textfield',
									allowBlank : false,
									blankText : '请选择收件人',
									readOnly : true
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
									handler : function() {
										var copyField = this.formPanel.getCmpByName('copyField');
										copyField.show();
									}
								}]
					}, {
						xtype : 'container',// 抄送人container
						name : 'copyField',
						layout : 'column',
						height : 32,
						hidden : true,
						defaultType : 'textfield',
						items : [ {
							xtype : 'label',
							text : '抄送人:',
							style : 'padding-left:0px;padding-top:3px;',
							width : 105
						}, {
							width : 405,
							fieldLabel : '抄送人姓名列表',
							name : 'mail.copyToNames',
							readOnly : true
						}, {
							xtype : 'button',
							text : '选择抄送人',
							style : 'padding-left:5px;',
							iconCls : 'btn-mail_recipient',
							scope : this,
							handler : this.addCCPeople
						}, {
							xtype : 'button',
							text : '取消抄送',
							style : 'padding-left:5px;',
							iconCls : 'btn-delete_copy',
							scope : this,
							handler : this.delCarbonCopy
						} ]
					}, {
						xtype : 'compositefield',
						autoWidth : true,
						fieldLabel : '优先级',
						items : [{
									width : 400,
									fieldLabel : '邮件优先级',
									hiddenName : 'mail.importantFlag',
									Name : 'mailImportantFlag',
									xtype : 'combo',
									mode : 'local',
									editable : false,
									value : '1',
									triggerAction : 'all',
									store : [['1', '一般'], ['2', '重要'],
											['3', '非常重要']]
								}, {
									xtype : 'checkbox',
									name : 'sendMessage',
									boxLabel : '告诉他有信'
								}]
					}, {
						xtype : 'attachpanel',
						name : 'mailAttachPanel',
						fieldLabel : '附件',
						leftWidth : 400,
						fileCat : 'mail'
					}, {
						fieldLabel : '内容',
						name : 'mail.content',
						allowBlank : false,
						xtype : 'ckeditor',
						height : 300,
						width : 620
					}]
		});
		//加载表单数据
		if (this.mailId != null && this.mailId != 'undefined') {
			var mailId = this.mailId;
			var opt = this.opt;
			var boxId = this.boxId;
			var formPanel = this.formPanel;
			var _mailId = formPanel.getCmpByName('mail.mailId');
			var mailAttachPanel = formPanel.getCmpByName('mailAttachPanel');
			if (opt == 'draft') {// 重载草稿
				_mailId.setValue(mailId);// 草稿才需要在表单记录邮件ID
				formPanel.loadData({
							url : __ctxPath + '/communicate/getMail.do',
							method : 'post',
							preName : 'mail',
							params : {
								mailId : mailId,
								folderId : 3,
								boxId : boxId
							},
							waitMsg : '正在载入数据...',
							success : function(response, option) {
								formPanel.getCmpByName('boxId').setValue(boxId);
								var copyToIDs =formPanel.getCmpByName('mail.copyToIDs');
								if (copyToIDs.getValue() != '') {// 假如草稿有抄送人,激活抄送人控件
									var copyField = formPanel.getCmpByName('copyField');
									copyField.show();
								}

								var results = Ext.util.JSON.decode(response.responseText).data;
								mailAttachPanel.loadByIds(results.fileIds);
							}
						});
			} else if (opt == 'reply') {// 回复
				formPanel.loadData({
							url : __ctxPath + '/communicate/optMail.do',
							method : 'post',
							preName : 'mail',
							params : {
								mailId : mailId,
								boxId : boxId,
								opt : '回复'
							},
							waitMsg : '正在载入数据...',
							success : function(response, option) {
								formPanel.getCmpByName('replyBoxId').setValue(boxId);
							}
						});
			} else if (opt == 'forward') {//转发
				formPanel.loadData({
							deferredRender : false,
							url : __ctxPath + '/communicate/optMail.do',
							method : 'post',
							preName : 'mail',
							params : {
								mailId : mailId,
								opt : '转发'
							},
							waitMsg : '正在载入数据...',
							success : function(response, option) {
								var results = Ext.util.JSON.decode(response.responseText).data;
								mailAttachPanel.loadByIds(results.fileIds);
							}
						});
			}
		}
		if (this.boxId != null && this.boxId != 'undefined') {
			var mailBoxId = this.formPanel.getCmpByName('boxId');
			mailBoxId.setValue(this.boxId);
		}
		//邮件中部菜单面板
		this.mailFormPanel = new Ext.Panel({
					title : '发送邮件',
					iconCls : 'menu-mail_send',
					autoScroll : true,
					tbar : this.toolbar,
					plain : true,
					bodyStyle : 'padding:5px;',
					items : [this.formPanel]
				});
	},
	//发送邮件
	sendMail : function() {
		var mailform = this.formPanel;
		var mailStatus = mailform.getCmpByName('mail.mailStatus');
		var content = mailform.getCmpByName('mail.content').getValue();
		var win = this;
		if(content == '' || content == null || content == 'undefinded'){
			Ext.ux.Toast.msg('操作信息', '邮件内容不能为空！');
			return ;
		}
		if (mailform.getForm().isValid()) {
			mailStatus.setValue(1);
			//附件处理
			var mailAttachPanel = mailform.getCmpByName('mailAttachPanel');
			mailform.getCmpByName('mail.fileIds').setValue(mailAttachPanel.getFileIds());
			mailform.getCmpByName('mail.filenames').setValue(mailAttachPanel.getFileNames());
			$postForm({
				formPanel : this.formPanel,
				scope : this,
				successMsg : "发送邮件成功",
				url : __ctxPath + '/communicate/saveMail.do',
				callback : function(fp, action) {
					var tabs = Ext.getCmp('centerTabPanel');
					tabs.remove('MailForm');
					App.clickTopTab('PersonalMailBoxView');
					if(this.callback){
						this.callback.call(this.scope);
					}
				}
			});
		}
	},
	//存为草稿
	save : function() {
		var mailform = this.formPanel;
		var mailStatus = mailform.getCmpByName('mail.mailStatus');
		mailStatus.setValue(0);
		if (mailform.getForm().isValid()) {
			//附件处理
			var mailAttachPanel = mailform
					.getCmpByName('mailAttachPanel');
			mailform.getCmpByName('mail.fileIds').setValue(
					mailAttachPanel.getFileIds());
			mailform.getCmpByName('mail.filenames').setValue(
					mailAttachPanel.getFileNames());
			$postForm({
				formPanel : this.formPanel,
				scope : this,
				successMsg : "保存草稿成功",
				url : __ctxPath + '/communicate/saveMail.do',
				callback : function(fp, action) {
					var tabs = Ext.getCmp('centerTabPanel');
					tabs.remove('MailForm');
					App.clickTopTab('PersonalMailBoxView');
					if(this.callback){
						this.callback.call(this.scope);
					}
				}
			});
		}
	},
	//选择收信人
	addRecipient : function() {
		formPanel = this.formPanel;
		new UserDialog({
			scope :this,
			single : false,
			callback : function(userIds,fullnames) {
				var recipientIDs = formPanel.getCmpByName('mail.recipientIDs');
				var recipientNames = formPanel.getCmpByName('mail.recipientNames');
				recipientIDs.setValue(userIds);
				recipientNames.setValue(fullnames);
			}
		}).show();
	},
	//选择抄送人
	addCCPeople  : function() {
		formPanel = this.formPanel;
		new UserDialog({
			single : false,
			callback : function(userIds,fullnames) {
				var copyToIDs =formPanel.getCmpByName('mail.copyToIDs');
				var copyToNames =formPanel.getCmpByName('mail.copyToNames');
				copyToIDs.setValue(userIds);
				copyToNames.setValue(fullnames);
			}
		}).show();
	},
	//取消抄送
 	delCarbonCopy : function() {// 取消抄送时设置为空
		formPanel = this.formPanel;
		var copyField = formPanel.getCmpByName('copyField');
		formPanel.getCmpByName('mail.copyToIDs').setValue('');
		formPanel.getCmpByName('mail.copyToNames').setValue('');
		copyField.hide();
	}

});

