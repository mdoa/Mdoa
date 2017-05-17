/**
 * 手机邮箱 by wdr 收件箱
 */

Ext.define('mobile.myEmail.MyEmail', {
	extend : 'mobile.List',

	id : 'myEmail',

	constructor : function(config) {
		config = config || {};
		this.username = config.username;
		this.mailType = config.mailType;
		this.mailBoxType=config.mailBoxType;
		this.userId=config.userId;
		this.isout=config.isout;
		var url = '';
		var geturl='';
		this.forword = '';
		if (this.mailBoxType=='inEmail') {
			geturl=__ctxPath + '/htmobile/getEmail.do';
			url = __ctxPath + '/htmobile/MyEmail.do?mailType='+this.mailType;
			if (this.mailType != 3) {
				this.forword = 'mobile.myEmail.MyEmailForm';
			} else {
				this.forword = 'mobile.myEmail.MyNewEmail';
			} 
		} else {
			geturl=__ctxPath + '/htmobile/getMyOutMail.do';
			url = __ctxPath + '/htmobile/listMyOutMail.do?mailType='+this.mailType+'&setId='+this.mailBoxType;
			if (this.mailType != 3) {
				
				this.forword = 'mobile.myEmail.MyEmailForm';
			} else {
				this.forword = 'mobile.myEmail.MyNewEmail';
			}
			
		}

		Ext.apply(config, {
					title : config.title,
					fields : [{
								name :!this.isout? 'boxId':'mailId',
								Type : 'string'
							}, {
								name : !this.isout?'note':'senderName',
								Type : 'string'
							}, {
								name : !this.isout?'mail':'title',
								Type : 'string'
							},{
								name : !this.isout?"sendTime":'mailDate',
								Type : 'auto'
							},{
								name : "isSelected",
								type : "auto"
							}
					],
					buttonText: '删除',
					url : url,
					root : 'result',
//					sorters : 'sendTime',
					searchCol : !this.isout?'Q_mail.subject_S_LK':'Q_title_S_LK',
					username : config.username,
					itemTpl : !this.isout? new Ext.XTemplate("<span class='itemTpl'>" +
							"<span style='font-size:18px;color:#412f1f;'>{mail.subject:ellipsis(10)} </span>" +
							"<span style='font-size:12px;color:#412f1f;float:right;'>{mail.sendTime}</span><br/>" +
							"<span style='font-size:12px;color:#412f1f;'>{mail.sender}</span>" +
							"<div style='float:right;'>{mail.img}</div></span>"):
							new Ext.XTemplate("<span class='itemTpl'>" +
							"<span style='font-size:18px;color:#412f1f;'>{title:ellipsis(10)}</span> " +
							"<span style='font-size:12px;color:#412f1f;float:right;'>{mailDate}</span><br/>" +
							"<span style='font-size:12px;color:#412f1f;'>{senderName}</span>" +
							"<div style='float:right;'>{mail.img}</div></span> "),
					useSelect : true,
					grouped: false,
					totalProperty: 'totalCounts',
					pullRefresh: true,
					listPaging: true,
					returnFileds:[!this.isout?'boxId':'mailId'],
					itemsingletap : function(obj, index, target, record) {
						var mailType = this.mailType;
						var userId = this.userId;
						var isout = this.isout;
						Ext.Ajax.request({
									url : geturl,
									params : {
										boxId : record.get('boxId'),
										mailId : record.get('mailId'),
										userId : userId
									},
		
									success : function(response) {
										var result = Ext.util.JSON
												.decode(response.responseText);
										var store = obj.getStore();
										mobileNavi.push(Ext.create(obj.forword, {
													username : obj.username,
													mailType : mailType,
													userId : userId,
													data : !isout
															? result.data.mail
															: result.data,
													mailBoxStore : store,
													boxId : record.get('boxId'),
													mailId : record.get('mailId'),
													isout : isout,
													
													callback : function() {
														obj.store.load();
													}
		
												}));
									}
		
								});
					},
					doneSuccess : function(values) {
						var isout=this.isout;
						var mailType=this.mailType;
						var setId=this.mailBoxType;
						var url='';
						if(isout){//是外部邮件
							url=__ctxPath+ '/htmobile/delMyOutMail.do?allmailIds='+values.mailId+'&mailType='+mailType+'&setId='+setId;
						}else{
							url=__ctxPath+ '/htmobile/delEmail.do?allboxIds='+values.boxId+'&mailType='+mailType;
						}
						Ext.Ajax.request({
							url :url,
							success : function(response) {
								var result = Ext.util.JSON.decode(response.responseText);
								if (result.success) {
									Ext.Msg.alert("提示", "删除数据成功");
									mobileNavi.pop().getStore().load();
								}
							}

						});

			}

		});
		this.callParent([config]);
	}
});