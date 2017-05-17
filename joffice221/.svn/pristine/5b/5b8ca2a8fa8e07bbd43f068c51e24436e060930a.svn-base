Ext.ns('MessageDetail');
/**
 * 个人短信详情
 * @author lyy
 * @class MessageDetail
 * @extends Ext.Window
 */
MessageDetail = Ext.extend(Ext.Window, {
	//构造函数
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		// 必须先初始化组件
		this.initComponents();
		MessageDetail.superclass.constructor.call(this, {
			title : '个人短信详情',
			iconCls : 'menu-mail_box',
			height : 200,
			id : 'MessageDetail',
			// autoWidth :true,
			width : 350,
			modal:true,
			layout : 'fit',
			buttonAlign : 'center',
			items : this.displayPanel,
			buttons: this.buttons
		});
	},
	initComponents: function(){
		//显示窗口
		this.displayPanel = new Ext.Panel({
					border : false,
					autoLoad : {
						url : __ctxPath
								+ '/pages/info/messagedetail.jsp?receiveId='
								+ this.receiveId + '&isSend=' + this.isSend
					}
				});
		//底部菜单
		this.buttons= [{
			text : '上一条',
			iconCls : 'btn-previous-message',
			scope:this,
			handler : this.previous
		}, {
			text : '下一条',
			iconCls : 'btn-next-message',
			scope:this,
			handler : this.next
		}, {
			text : '回复',
			iconCls : 'btn-mail_reply',
			scope:this,
			handler : this.reply,
			hidden : this.isSend
		}, {
			text : '重发',
			iconCls : 'btn-mail_reply',
			scope:this,
			handler : this.reSend,
			hidden : this.isSend?false:true
		}, {
			text : '关闭',
			iconCls : 'btn-del',
			scope:this,
			handler : function() {
				this.close();
			}
		}]

	},
	//上一条消息
	previous : function() {
		var haveNextMessageFlag = document
				.getElementById('__haveNextMessageFlag');
		if (haveNextMessageFlag != null
				&& haveNextMessageFlag.value != 'endPre') {
			var userId = curUserInfo.userId;
			var receiveId = document.getElementById('__curReceiveId').value;
			if(this.isSend){
				this.displayPanel.load({
					url : __ctxPath
							+ '/pages/info/messagedetail.jsp?opt=_pre&receiveId='
							+ receiveId + '&userId=' + userId+'&isSend=true',
					scripts:true
				});
			}else{
				this.displayPanel.load({
					url : __ctxPath
							+ '/pages/info/messagedetail.jsp?opt=_pre&receiveId='
							+ receiveId + '&userId=' + userId+'&isSend=false',
					scripts:true
				});
			}
		} else {
			Ext.ux.Toast.msg('提示信息', '这里已经是第一条了');
		}
	},
	//下一条消息
	next : function() {			
		var haveNextMessageFlag = document
				.getElementById('__haveNextMessageFlag');
		if (haveNextMessageFlag != null
				&& haveNextMessageFlag.value != 'endNext') {
			var userId = curUserInfo.userId;
			var receiveId = document.getElementById('__curReceiveId').value;
			if(this.isSend){
				this.displayPanel.load({
					url : __ctxPath
							+ '/pages/info/messagedetail.jsp?opt=_next&receiveId='
							+ receiveId + '&userId=' + userId+'&isSend=true',
					scripts:true
				});
			}else{
				this.displayPanel.load({
					url : __ctxPath
							+ '/pages/info/messagedetail.jsp?opt=_next&receiveId='
							+ receiveId + '&userId=' + userId+'&isSend=false',
					scripts:true
				});
			}
		} else {
			Ext.ux.Toast.msg('提示信息', '这里已经是最后一条了.');
		}
	},
	//回复消息
	reply : function() {
		var type = document.getElementById('__ReplyFlag').value;
		var senderId=document.getElementById('__SENDID').value;
		var senderName=document.getElementById('__SENDERNAME').value;
		if (type == 1) {
			var replyWin = Ext.getCmp('ReplyWindow');
			if (replyWin != null) {
				replyWin.close();
				new ReMessageWin({id:senderId,name:senderName}).show();
			} else {
				new ReMessageWin({id:senderId,name:senderName}).show();
			}
			this.close();
		} else {
            Ext.ux.Toast.msg('提示信息', '系统信息不能回复.');
		}	
	},
	//重发消息
	reSend : function(){
		Ext.Ajax.request({
					scope : this,
					url : __ctxPath + '/info/sendShortMessage.do',
					params : {
						userId : this.userId,
						content : this.content
					},
					method : 'post',
					success : function() {
						Ext.ux.Toast.msg('操作信息', '重发成功！');
						if(this.callback){
							this.callback.call(this.scope);
						}
						this.close();
					}
				});
	}
})