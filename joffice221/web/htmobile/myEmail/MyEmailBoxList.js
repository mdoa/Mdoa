/**
 * 邮箱列表 By wdr
 */
Ext.define('mobile.myEmail.MyEmailBoxList', {
	extend : 'Ext.Panel',
	id : 'MyEmailBoxList',
	constructor : function(config) {
		config = config || {};
		var username = config.username;
		var userId = config.userId;
		var mailTypes = config.mailTypes;
		var selfItem = [];
		var rowPanel = null;
		var mailTypes = [];

		Ext.Ajax.request({
			url : __ctxPath + '/htmobile/treeMyOutMail.do',
			success : function(response) {
				var mailTypes = [{
					cls : 'mail',
					title : '内部邮件',
					view : 'mobile.myEmail.MyEmailBox',
					mailBoxType : "inEmail",
					notice:'/htmobile/getInNumEmail.do'
					}];
				var result = Ext.util.JSON.decode(response.responseText);
				if (result.success) {
					if (result.data != null) {
						for (var i = 0; i < result.data.length; i++) {
							var tempType = {
								cls : 'mail',
								title : result.data[i].accountName,
								view : 'mobile.myEmail.MyEmailBox',
								mailBoxType : result.data[i].setId,
								notice:'/htmobile/getOutNumMyOutMail.do?setId='+result.data[i].setId
							};
							mailTypes.push(tempType);
						}
					}
					var mailSize = mailTypes.length;
					if (mailTypes.length != 0 && mailTypes.length % 4 != 0) {
						while (mailSize % 4 != 0) {
							mailSize++;
						}
					}

					for (var idx = 0; idx < mailSize; idx++) {

						if (idx == 0 || idx % 4 == 0) {
							rowPanel = Ext.create('Ext.Panel', {
										layout : {
											type : 'hbox',
											align : 'middle'
										}
									});
						}

						var itemPanel = Ext.create('Ext.Panel', {
									layout : {
										type : 'vbox',
										align : 'middle'
									},
									style : {
										'padding-top' : '15px',
										'padding-bottom' : '15px'
									},
									flex : 1,
									height : 100
								});

						if (idx < mailTypes.length) {
							var item = mailTypes[idx];

							itemPanel.add({
								xtype : 'button',
								name : item.view,
								title : item.title,
								mailBoxType : item.mailBoxType,
								width : 50,
								height : 50,
								cls : item.cls,
								notice:item.notice,
								listeners : {
									tap : function() {
										mobileNavi.push(Ext.create(
												this.config.name, {
													username : username,
													userId : userId,
													title : this.config.title,
													mailBoxType : this.config.mailBoxType
												}));
									},
									initialize : function(obj, b) {
										var me = this;
										var notice = me.config.notice;
										if (typeof notice != 'undefined') {
											submitFormp();
											window.setInterval(submitFormp,1000*60*5);
											function submitFormp(){
												Ext.Ajax.request({
													url : __ctxPath + notice,
													success : function(response) {
														var result = Ext.util.JSON.decode(response.responseText);
														me.setBadgeText(result.totalCounts);
													}
												});
											}
										}
									}
								}
							});

							itemPanel.add({
										xtype : 'label',
										style : {
											'text-align' : 'center',
											'font-size' : '9pt'
										},
										html : item.title
									});
						}

						rowPanel.add(itemPanel);

						if (idx == 0 || idx % 4 == 0) {
							selfItem.push(rowPanel);
						}
					}
					var o = Ext.getCmp('MyEmailBoxList');
					o.add(selfItem);
				}
			}
		});

		Ext.apply(config, {
					title : '邮件菜单',
					name : 'mail',
					items : selfItem
				});
		this.callParent([config]);
	}
})