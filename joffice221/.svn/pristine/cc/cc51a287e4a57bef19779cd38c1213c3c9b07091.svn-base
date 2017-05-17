
/**
 * 手机登录跳转 by cjj
 */

Ext.define('mobile.View', {
	extend : 'Ext.NavigationView',

	constructor : function(config) {

		config = config || {};
		var me = this;
		Ext.apply(config, {
			defaultBackButtonText : '返回',
			items : [Ext.create('mobile.Main', {
						username : config.username,
						userId : config.userId
					})],
			navigationBar : {
				ui : 'dark',
				docked : 'top',
				items : [{
					xtype : 'button',
					id : 'logout',
					text : '注销',
					align : 'right',
					handler : function() {
						Ext.Msg.show({
									message : '是否注销',
									width : 300,
									buttons : [{
												text : '确定',
												itemId : '1'
											}, {
												text : '取消',
												itemId : '0'
											}],
									fn : function(itemId) {
										if (itemId != '0') {
											mobileNavi.setMasked(true);
											Ext.Ajax.request({
														url : __ctxPath
																+ '/j_logout.do'
													});
											window.location.reload();
										}
									}
								});
					}
				}]
			},
			listeners : {
				back : function(view, eOpts) {
					var id = me.getActiveItem().getItemId();
					var formp = Ext.create('Ext.form.Panel');
					if (id == 'main') {
						var menuSize = menus.length;
						for (var idx = 0; idx < menuSize; idx++) {
							var item = menus[idx];
							if (typeof item.notice != 'undefined') {
								formp.submit({
									url : __ctxPath + item.notice,
									params : {
										id : item.id
									},
									method: 'POST',
									success : function(form,action,response) {
										var result = Ext.util.JSON.decode(response);
										var comp = Ext.getCmp(result.id);
										comp.setBadgeText(result.totalCounts);
										mobileView.setMasked(false);
									}
								});
							}
						}
					} else if (id == 'myEmail') {
						mobileView.setMasked(maskConfig);
//						var a = me.getActiveItem();
//						var itemTpl=me.getActiveItem().getItemTpl();
//						var mailType = me.getActiveItem().mailType;
//						var mailBoxType = me.getActiveItem().mailBoxType;
//						var isout = me.getActiveItem().isout;
//						var html='';
//						var url='';
//						if(mailBoxType=='inEmail'){
//							url = __ctxPath + '/htmobile/MyEmail.do?mailType='+mailType;
//							 html= new Ext.XTemplate("<span class='itemTpl'>{mail.subject}{mail.sender} {mail.sendTime} <div style='float:right;'>{mail.img}</div></span>")
//						}else{
//							url = __ctxPath + '/htmobile/listMyOutMail.do?mailType='+mailType+'&setId='+mailBoxType;
//						html= new Ext.XTemplate("<span class='itemTpl'>{title} {senderName} {mailDate}<div style='float:right;'>{mail.img}</div></span>");
//						}
//						formp.fubmit({
//									url :url,
//									method: 'POST',
//									success : function(form,action,response) {
////										var result = Ext.util.JSON.decode(response);
//										me.getActiveItem().setItemTpl(html);
//										
//										
//									}
//								});
								mobileView.setMasked(false);
					}
				}
			}
		});

		this.callParent([config]);
	}

});
