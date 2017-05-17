/**
 * 发送信息
 * @class MessageForm
 * @extends Ext.Panel
 */
MessageForm = Ext.extend(Ext.Panel, {
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		this.initUIComponents();
		MessageForm.superclass.constructor.call(this, {
					id : 'MessageForm',
					flex:1,
					layout : 'form',
					autoScroll:true,
					border : false,
					items : [this.formPanel]
				});
	},// end of constructor
	initUIComponents : function() {
		this.formPanel = new Ext.FormPanel({
			title : '发送信息',
			iconCls : 'btn-sendM',
			frame : true,
			draggable : true,
			border : false,
			style : 'margin-top:5%;margin-left:20%;',
			width : 460,
			height : 260,
			defaultType : 'textarea',
			url : __ctxPath + '/info/sendShortMessage.do',
			method : 'post',
			reader : new Ext.data.JsonReader({
						root : 'data',
						id : 'messageId'
					}, [{
							name : 'userId',
							mapping : 'senderId'
						}, {
							name : 'userFullname',
							mapping : 'sender'
						}]),
			defaults : {
				allowBlank : false,
				selectOnFocus : true,
				msgTarget : 'side'
			},
			modal : true,
			layout : 'form',
			plain : true,
			scope : this,
			buttonAlign : 'center',
			items : [{
						xtype : 'hidden',
						name : 'userId'
					}, {
						xtype : 'fieldset',
						style : 'padding:0px',
						border : false,
						hight : 70,
						layout : 'column',
						items : [{
									xtype : 'label',
									text : '收信人:',
									width : 50
								}, {
									xtype : 'textarea',
									name : 'userFullname',
									allowBlank : false,
									readOnly : true,
									width : 290,
									height : 50
								}, {
									xtype : 'container',
									border : true,
									width : 100,
									heigth : 30,
									items : [{
										xtype : 'button',
										iconCls : 'btn-mail_recipient',
										text : '添加联系人 ',
										width : 80,
										scope : this,
										handler :this.addRecipient
									}, {
										xtype : 'button',
										text : '清除联系人',
										iconCls : 'btn-del',
										width : 80,
										scope : this,
										handler : this.delRecipient
									}]
								}]
					}, {
						xtype : 'fieldset',
						border : false,
						style : 'padding:0px',
						layout : 'column',
						height : 140,
						items : [{
									xtype : 'label',
									text : '内容:',
									width : 50
								}, {
									id : 'sendContent',
									xtype : 'textarea',
									name : 'content',
									width : 380,
									height:120,
									autoScroll:true,
									allowBlank : false
								}]
					}],
			buttons : [{
						text : '发送',
						iconCls : 'btn-mail_send',
						scope : this,
						handler :this.send

					}, {
						text : '重置',
						iconCls : 'reset',
						scope : this,
						handler :this.reset
					},{
						text:'关闭',
						iconCls : 'btn-cancel',
						scope:this,
						handler:this.cancel
					}]
		});

	},//end of initUIComponents
	//发送消息
	send:function(){
		var win = this;
	    var message =this.formPanel;
		if (message.getForm().isValid()) {
			message.getForm().submit({
						waitMsg : '正在 发送信息',
						success : function(message, o) {
							Ext.ux.Toast.msg('操作信息',
									'信息发送成功！');
							win.formPanel.getForm().reset();
						}
					});
		}
	},
	//添加联系人
	addRecipient : function(){
		var formPanel=this.formPanel;
		var userId = formPanel.getCmpByName('userId');
		var userFullname = formPanel.getCmpByName('userFullname');
		new UserDialog({
				userIds : userId.getValue(),
				userName : userFullname.getValue(),
				single : false,
				scope : this,
				callback : function(userIds,fullnames) {
					userId.setValue(userIds);
					userFullname.setValue(fullnames);
				}
		}).show();
	},
	//清除联系人
	delRecipient :function() {
		var name = this.formPanel.getCmpByName('userFullname');
		var id = this.formPanel.getCmpByName('userId');
		name.reset();
		id.reset();
	},
	//重置
	reset:function(){
	   this.formPanel.getForm().reset();
	},
	//关闭
	cancel : function() {
		Ext.getCmp('centerTabPanel').remove('MessageManageView');
	},
});
