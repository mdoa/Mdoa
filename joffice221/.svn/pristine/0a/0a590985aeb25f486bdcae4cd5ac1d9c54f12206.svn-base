/**
 * 手机版 写邮件 by wdr
 */
Ext.define('mobile.myEmail.MyNewEmail', {
			extend : 'Ext.form.Panel',
			id : 'MyNewEmail',

			constructor : function(config) {

				config = config || {};
				title : '写邮件';
				var Myemail = config.data;
				var userId=config.userId;
				this.opts = config.opt;
				this.isout=config.isout;
//				var mailid = '';
				var mailAttach = '';
				var files = new Array();
				
				var content='';
				if(Myemail.content!=null){
					content=Myemail.content;
				}
				if (Myemail.fileIds != null) {
					files = !this.isout?Myemail.filenames.split(","):Myemail.fileNames.split(",");
					fileids=Myemail.fileIds.split(",");
					url=__ctxPath+'/file-download?fileId=';
					for (var i = 0; i < files.length - 1; i++) {
						mailAttach = mailAttach
						+ "<ul id='"+fileids[i]+"'><a style='float:left;color:black' href='" + url
						+ fileids[i] + "'>" + files[i] + "</a>"
						+ "&nbsp;&nbsp; <a style='float:left;' href="
						+ "'javascript:delMailAttach(" + fileids[i] + ","+1+")'>"
						+ "删除</a></ul></br>";
					};
				}
				Ext.define('receiver', {
					extend : 'Ext.data.Model',
					config : {
						fields : [{
									name : 'setId',
									type : 'string'
								}, {
									name : 'accountName',
									type : 'string'
								}]
					}
				});
				var genreStore = Ext.create('Ext.data.Store', {});

				if (this.isout) {
					genreStore = Ext.create('Ext.data.Store', {
								model : 'receiver',
								autoLoad : true,
								autoDestroy : true,
								proxy : {
									type : 'ajax',
									url : __ctxPath
											+ '/htmobile/getRecMyOutMail.do?userId='
											+ userId,
									reader : {
										type : 'json',
										rootProperty : 'data'
									}
								}
							});
	
					}
				var itemses = [{
							xtype : 'toolbar',
							itemId : 'topToolbar',
							docked : 'top',
							items : [{
										xtype : 'button',
										text : '立刻发送',
										itemId : 'tt_nowsend',
										scope : this,
										handler : this.sendMail
									}, {
										xtype : 'button',
										text : '存草稿',
										itemId : 'tt_save',
										scope : this,
										handler : this.save
									}, {
										xtype : 'button',
										text : '重置',
										itemId : 'tt_reset',
										scope : this,
										handler : function() {
											this.reset();
										}
									}]
						}, {
							xtype : 'textfield',
							label : '发送人名称',
							name : !this.isout?'':'outMail.senderName',
							value : !this.isout?'':Myemail.senderName,
							hidden : true
						},{
							xtype : 'textfield',
							label : '收件人ID列表用,分隔',
							itemId:'recipientId',
							name : !this.isout?'mail.recipientIDs':'outMail.receiverAddresses',
							value:  !this.isout?Myemail.recipientIDs:Myemail.receiverAddresses,
							hidden : true
						}, {
							xtype : 'textfield',
							label : '附件ID列表用,分隔',
							name : !this.isout?'mail.fileIds':'outMail.fileIds',
							id : 'fileIds',
							value:  Myemail.fileIds,
							hidden : true
						},{
							xtype : 'textfield',
							label : '附件名称列表用,分隔',
							name : !this.isout?'mail.filenames':'outMail.fileNames',
							id : 'filenames',
							value:  Myemail.filenames,
							hidden : true
						},{
							xtype : 'textfield',
							label : 'MailId',
							name : !this.isout?'mail.mailId':'outMail.mailId',
							value : Myemail.mailId,
							hidden : true
						},  {
							xtype : 'textfield',
							label : '抄送人ID列表用,分开',
							itemId: 'copyId',
							name : !this.isout?'mail.copyToIDs':'outMail.cCAddresses',
							value : Myemail.copyToIDs,
							hidden : true
						},{
							xtype : 'textfield',
							label : 'BOXID',
							name : 'boxId',
							value : config.boxId,
							hidden : true
						}, {
							xtype : 'textfield',
							label : '操作',
							name : 'replyBoxId',
							value : config.boxId,
							hidden : true
						}, {
							xtype : 'textfield',
							itemId:'mailstatus',
							name : 'mail.mailStatus',
							label : '邮件状态',
							labelWidth : '24%',
							value : 1,
							hidden : true
						}, {
							xtype : 'fieldset',
						    defaults: {
						        xtype: 'panel',
								border:1,
								style: 'border-color: silver; border-style: solid;',
								layout : {
									type : 'hbox',
									pack:'center',
									align : 'center'
								}
						    },
							items : [{
										xtype : 'panel',
										items:[{
											xtype: 'label',
											html : '&nbsp;&nbsp;主&nbsp;&nbsp;题:',
											width : 100,
											labelWrap: true,
											style:'font-size:12pt;'
										},{
											xtype : 'textfield',
											itemId:'theme',
											flex:0.8,
											name : !this.isout?'mail.subject':'outMail.title',
											value : !this.isout?Myemail.subject:Myemail.title
										}]
									},
									{
										xtype : 'panel',
										hidden:true,
										items:[{
											xtype : 'selectfield',
											itemId : 'sender',
											label : '&nbsp;&nbsp;发件人：',
											name : 'outMail.senderAddresses',
											store : genreStore,
											labelWrap: true,
											valueField : 'setId',
											displayField : 'accountName',
											labelWidth:100
										}]
									},
									{
										xtype : 'panel',
										itemId : 'reciver',
										items : [{
											xtype: 'label',
											html : '&nbsp;&nbsp;收件人:',
											width : 100,
											labelWrap: true,
											style:'font-size:12pt;'
										},{
											xtype : 'panel',
											itemId:'txtRecipient',
											html : !this.isout?Myemail.recipientNames:Myemail.receiverNames,
											flex:0.8,
											border:0
										},{
											xtype : 'textfield',
											itemId:'recipient',
											name : !this.isout?'mail.recipientNames':'outMail.receiverNames',
											value : !this.isout?Myemail.recipientNames:Myemail.receiverNames,
											hidden:true
										},{
											xtype : 'button',
											itemId : 'fd_recipient',
											width: 20,
											cls : 'mailrecipient',
											scope : this,
											handler : this.addCCPeople,
											border:0
										},{
											xtype : 'label',
											html:'&nbsp;',
											width: 10
										},{
											xtype : 'button',
											itemId : 'fd_copy',
											width: 20,
											cls : 'mailcopy',
											scope : this,
											border:0,
											handler : function() {
												var copyField = this.getCmpByName("copyField");
												copyField.show();
											}
										},{
											xtype : 'label',
											html:'&nbsp;',
											width: 10
										}]
									},
									{
										xtype : 'panel',
										itemId : 'copyField',
										hidden : true,
										layout : {
											type : 'hbox',
											pack:'center',
											align : 'center'
										},
										items : [{
													xtype: 'label',
													html : '&nbsp;&nbsp;抄送人:',
													width : 100,
													labelWrap: true,
													style:'font-size:12pt;'
												},{
													xtype : 'panel',
													itemId:'txtCopyNames',
													html : !this.isout?Myemail.copyToNames:Myemail.cCNames,
													flex:0.8,
													border:0
												},{
													xtype : 'textfield',
													itemId:'copyNames',
													name : !this.isout?'mail.copyToNames':'outMail.cCNames',
													value : !this.isout?Myemail.copyToNames:Myemail.cCNames,
													hidden:true
												},{
													xtype : 'button',
													name : 'copy_chooice',
													width: 20,
													cls : 'mailrecipient',
													scope : this,
													handler : this.addCopyPeople,
													border:0
												},{
													xtype : 'label',
													html:'&nbsp;',
													width: 10
												},{
													xtype : 'button',
													name : 'coyp_del',
													width: 20,
													cls : 'delcopy',
													scope : this,
													handler : this.delCarbonCopy,
													border:0
												},{
													xtype : 'label',
													html:'&nbsp;',
													width: 10
												}]
									},
									{
										xtype : 'panel',
										itemId : 'mailimportantFlag',
										hidden:true,
										items : [{
												xtype : 'selectfield',
												name : 'mail.importantFlag',
												label : '优先级',
												width : '100%',
												options : [{
															text : '一般',
															value : '1'
														}, {
															text : '重要',
															value : '2'
														}, {
															text : '非常重要',
															value : '3'
														}]
		
											}, {
												xtype : 'togglefield',
												itemId : 'sendMessage',
												label : '告诉他有信',
												width : '100%'
										}]
									},
									{
										xtype : 'panel',
										itemId : 'mailAttachPanel',
//										hidden:true,
										items : [{
													xtype: 'label',
													html: '&nbsp;&nbsp;附&nbsp;&nbsp;件:',
													width: 100,
													labelWrap: true,
													style:'font-size:12pt;'
												}, {
													xtype : 'panel',
													itemId : 'filename',
													html : mailAttach,
													flex:0.8
												}, {
													xtype:'panel',
													width: 40,
													layout : {
														type : 'vbox',
														pack:'center',
														align : 'center'
													},
													items:[{
														xtype : 'fileupload',
														text : '添加',
														itemId: 'imgUploadBtn',
														autoUpload: false,
														border: 0,
														width: 20,
														states: {
															browse: {
																cls: 'attachment',
																text: ''
															},
															ready: {
																cls: 'attachment',
																text: ''
															},
															uploading: {
																text: '',
																loading: true// Enable loading spinner on button
															}
														},
														url : __ctxPath + "/file-upload?isFlex=true&flexUserId="+userId+"&fileTypeId=0"													
													}]
												}]
									},
									{
										xtype : 'textareafield',
										itemId:'content',
										value:content,
										maxRows : content.split("\n").length,
										name : !this.isout?'mail.content':'outMail.content',
									}
								]}
						];

				Ext.apply(config, {
					title : '写邮件',
					scrollable : {
						direction : 'vertical'
					},
					enctype:'multipart/form-data',
					items : itemses
				});
				
				this.callParent([config]);
			},

			// 选择收件人
			addCCPeople : function() {
				var isout=this.isout;
				var recipientName = this.getCmpByName("recipient");
				var recipientID =this.getCmpByName("recipientId");
				var txtRecipient = this.getCmpByName("txtRecipient");
				mobileNavi.push(Ext.create('mobile.myEmail.MyaddPeople', {
					isOut:isout,
					recipientNames:recipientName,
					recipientIDs:recipientID,
					txtRecipient:txtRecipient
				}));
			},
			
			// 选择抄送人
			addCopyPeople : function() {
				var isout=this.isout;
				var recipientName = this.getCmpByName("copyNames");
				var recipientID =this.getCmpByName("copyId");
				var txtRecipient = this.getCmpByName("txtCopyNames");
				mobileNavi.push(Ext.create('mobile.myEmail.MyaddPeople', {
					isOut:isout,
					recipientNames:recipientName,
					recipientIDs:recipientID,
					txtRecipient:txtRecipient
				}));
			},

			// 发送邮件
			sendMail : function() {
				var mailform = this;// 获取panel
				var mailStatus = mailform.getCmpByName('mailstatus'); // 获取邮件状态控件
				var content = mailform.getCmpByName('content');// 获取邮件内容
//				var html = content.getHtml();// 获取html内容
				var url='';
				var setId='';
				if(this.isout){
					setId=mailform.getCmpByName('outMail.senderAddresses').getValue();
					url= __ctxPath + '/htmobile/saveMyOutMail.do';
				}else{
					url= __ctxPath + '/htmobile/saveEmail.do';
				}

				if (content.getValue() == '' || content.getValue() == null
						|| content.getValue() == 'undefinded') {
					Ext.Msg.alert('操作信息', '邮件内容不能为空！');
					return;
				}
				mailStatus.setValue(1);
				mailform.submit({
							url :url,
							params : {
								setId:setId,
								camopt:'1'
							},
							method : 'POST',

							success : function(form, action, response) {
								var obj = Ext.util.JSON.decode(response);
								if (obj.success) {
									mobileNavi.pop(this.opts).getStore().load();
									Ext.Msg.alert("提示", obj.msg);
								} else {
								} 
							},
							failure : function(form, action, response) {
								var obj = Ext.util.JSON.decode(response);
								Ext.Msg.alert('提示', obj.msg);
							}
						});
			},
			save : function() {
				var mailform = this;// 获取panel
				var mailStatus = mailform.getCmpByName('mailstatus'); // 获取邮件状态控件
				var content = mailform.getCmpByName('content');// 获取邮件内容
				var reciver = mailform.getCmpByName('recipient');
				var theme = mailform.getCmpByName('theme');
				var url='';
				var setId='';
				if(this.isout){
					setId=mailform.getCmpByName('outMail.senderAddresses').getValue();
					url= __ctxPath + '/htmobile/saveMyOutMail.do';
				}else{
					url= __ctxPath + '/htmobile/saveEmail.do';
				}

				if (theme.getValue() == '' || theme.getValue() == null
						|| theme.getValue() == 'undefinded') {
					Ext.Msg.alert('操作信息', '主题不能为空！');
					return;
				}
				if (reciver.getValue() == '' || reciver.getValue() == null
						|| reciver.getValue() == 'undefinded') {
					Ext.Msg.alert('操作信息', '收件人不能为空！');
					return;
				}
				if (content.getValue() == '' || content.getValue() == null
						|| content.getValue() == 'undefinded') {
					Ext.Msg.alert('操作信息', '邮件内容不能为空！');
					return;
				}
				mailStatus.setValue(0);

				mailform.submit({
							url : url,
							params : {
								setId:setId,
								camopt:'0'
							},
							method : 'POST',

							success : function(form, action, response) {
								var obj = Ext.util.JSON.decode(response);
								if (obj.success) {
									mobileNavi.pop(this.opts).getStore().load();// 跳回上opts层
									Ext.Msg.alert("提示", obj.msg);
								} else {
								}
							},
							failure : function(form, action, response) {
								var obj = Ext.util.JSON.decode(response);
								Ext.Msg.alert('提示', obj.msg);
							}
						});
			},// 取消抄送
			delCarbonCopy : function() {// 取消抄送时设置为空
				formPanel = this;
				var copyField = formPanel.getCmpByName('copyField');
				formPanel.getCmpByName('copyId').setValue('');
				var t = copyField.getCmpByName('copyNames').setHtml('');
				copyField.hide();
			}
			
		});