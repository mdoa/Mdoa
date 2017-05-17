/**
 * 邮件 by wdr
 */
Ext.define('mobile.myEmail.MyEmailForm', {
	extend : 'Ext.Panel',
	id : 'myEmailForm',
	constructor : function(config) {
		config = config || {};
		this.store = config.mailBoxStore;
		this.mail = config.data;
		var mailAttach = '';
		this.userId=config.userId;
		this.isout=config.isout;
		var files = new Array();
		var fileids = new Array();
		if (this.mail.fileIds != null) {
			files = !this.isout?this.mail.filenames.split(","):this.mail.fileNames.split(",");
			fileids = this.mail.fileIds.split(",");
			for (var i = 0; i < files.length - 1; i++) {
				mailAttach = mailAttach
						+ "<a style='float:none;color:black;font-size:18px;' href='"
						+ __ctxPath + "/file-download?fileId=" + fileids[i]
						+ "'>" + files[i] + "</a><br/> ";
			};
		}
		
		var toolbar = Ext.create('Ext.Toolbar', {
					docked : 'bottom',
					items : [
					{xtype:'spacer'},
					{
						xtype : 'button',
						text : '回复',
						iconMask : true,
						scope : this,
						handler : this.relayEmail
						}, {
						xtype : 'button',
						text : '转发',
						iconMask : true,
						scope : this,
						handler : this.forwardEmail
					}, {
						xtype : 'button',
						text : '删除',
						iconMask : true,
						scope : this,
						handler : this.del
					}, {
						xtype : 'spacer'
					}]
				});

		var toolbar1 = Ext.create('Ext.Toolbar', {
					docked : 'bottom',
					items : [{
								xtype : 'button',
								text : '彻底删除',
								iconMask : true,
								scope : this,
								flex : .1,
								handler : this.del
							}, {
								xtype : 'spacer'
							}]
				});

		var itemses = [];

		if (config.mailType == 1) {
			itemses.push(toolbar);
		} else if (config.mailType == 2) {
			itemses.push(toolbar);
		} else if (config.mailType == 4) {
			itemses.push(toolbar1);
		};

		Ext.apply(config, {
			title : !this.isout?this.mail.subject:this.mail.title,
			scrollable : {
				direction : 'vertical'
			},
			

			html : !this.isout?"<div style='width:100% ;font-size:18px;color:#121a2a;'>"
					+ "发件人:  <span style='color:black' >"
					+ "“"
					+ this.mail.sender
					+ "”"
					+ "</span><br/>"
					+ "收件人:  "
					+ this.mail.recipientNames
					+ "<br/>"
					+ "发件时间:  "
					+ this.mail.sendTime
					+ "<br/>"
					+ "附件:  "
					+ mailAttach
					+ "</div><hr><br/>"
					+ "<div  style='font-size:20px;background-color:#fffffb'>"
					+ this.mail.content + "</div>":
					"<div style='width:100% ;font-size:18px;color:gray;'>"
					+ "发件人:  <span style='color:black' >"
					+ "“"
					+ this.mail.senderName
					+ "”"
					+ "</span><br/>"
					+ "收件人:  "
					+ this.mail.receiverNames
					+ "<br/>"
					+ "发件时间:  "
					+ this.mail.mailDate
					+ "<br/>"
					+ "附件:  "
					+ mailAttach
					+ "</div><hr><br/>"
					+ "<div  style='font-size:20px;background-color:#fffffb'>"
					+ this.mail.content + "</div>",
			items : itemses
		});
		this.callParent([config]);
	},
	// 回复邮件
	relayEmail : function() {
		var isout = !this.config.isout;
		var mail = this.mail;
		
		
		var title = "<br/><hr><br/>----<strong>回复邮件</strong>----";
		var sender = isout?"<br/><strong>发件人</strong>:" + mail.sender:"<br/><strong>发件人</strong>:" + mail.senderName;
		var sendTime = isout?"<br/><strong>发送时间</strong>:" + mail.sendTime:"<br/><strong>发送时间</strong>:" + mail.mailDate;
		var receiver = isout?"<br/><strong>收件人</strong>:" + mail.recipientNames:"<br/><strong>收件人</strong>:" + mail.receiverNames;
		if (isout) {
			mail.subject = "回复:" + mail.subject;
			mail.recipientNames=mail.appSender.fullname;
			mail.recipientIDs=mail.appSender.userId;
		} else {
			mail.title = "回复:" + mail.title;
			mail.receiverNames=mail.senderName;
			mail.receiverAddresses=mail.senderAddresses;
		}
		var subject = isout?"<br/><strong>主题</strong>:" + mail.subject+"<br/><strong>内容</strong>:<br/><br/>":"<br/><strong>主题</strong>:" + mail.title+"<br/><strong>内容</strong>:<br/><br/>";
		var content = mail.content;
		mail.mailId="";
		
		mail.content=analysisHtml( title + sender + sendTime + receiver + subject+ content);
		mobileNavi.push(Ext.create('mobile.myEmail.MyNewEmail', {
					data : mail,
					opt : 2,
					isout:!isout,
					userId:this.userId,
					boxId : this.config.boxId,
					mailBoxStore : this.store
				}));
	},

	forwardEmail : function() {
		var isout =!this.config.isout;
		var mail = this.mail;
		
		var title = "<br/><hr><br/>----<strong>转发邮件</strong>----";
		var sender = isout?"<br/><strong>发件人</strong>:" + mail.sender:"<br/><strong>发件人</strong>:" + mail.senderName;
		var sendTime = isout?"<br/><strong>发送时间</strong>:" + mail.sendTime:"<br/><strong>发送时间</strong>:" + mail.mailDate;
		var receiver = isout?"<br/><strong>收件人</strong>:" + mail.recipientNames:"<br/><strong>收件人</strong>:" + mail.receiverNames;
		if (isout) {
			mail.subject = "转发:" + mail.subject;
			mail.recipientNames="";
			mail.recipientIDs="";
		} else {
			mail.title = "转发:" + mail.title;
			mail.receiverNames="";
			mail.receiverAddresses="";
		}
		var subject = isout?"<br/><strong>主题</strong>:" + mail.subject+"<br/><strong>内容</strong>:<br/><br/>":"<br/><strong>主题</strong>:" + mail.title+"<br/><strong>内容</strong>:<br/><br/>";
		var content = mail.content;
		mail.mailId="";
		mail.content = analysisHtml(title + sender + sendTime + receiver + subject+ content);
		mobileNavi.push(Ext.create('mobile.myEmail.MyNewEmail', {
					data : mail,
					opt : 2,
					isout:!isout,
					boxId : '',
					userId:this.userId,
					mailBoxStore : this.store
				}));
	},
	del:function(){
		var isout = !this.isout;
		var mailType=this.config.mailType;
		var boxId=this.config.boxId;
		var mailId=this.config.mailId;
		var url='';
		if (isout) {
			url=__ctxPath+ '/htmobile/delEmail.do?boxId='+boxId+'&mailType='+mailType;
		} else {
			url=__ctxPath+ '/htmobile/delMyOutMail.do?mailId='+mailId+'&mailType='+mailType;
		}
		Ext.Ajax.request({
			url:url,
			mothed:'POST',
			success : function(response) {
						var result = Ext.util.JSON
								.decode(response.responseText);
						if (result.success) {
							mobileNavi.pop().getStore().load();
							Ext.Msg.alert("提示", "删除数据成功");
						}
					}
		});
		
	}

});
