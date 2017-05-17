// 首页的设置
Ext.ns('App.home');

/**
 * 通用Portlet
 * 
 * @class App.PortletView
 * @extends Ext.ux.Portlet
 */
App.PortletView = Ext.extend(Ext.ux.Portlet, {
			constructor : function(conf) {
				Ext.applyIf(this, conf);
				this.initTool();
				App.PortletView.superclass.constructor.call(this, {
							id : this.id,
							title : this.title,
							iconCls : this.iconCls,
							tools : this.tools,
							autoLoad : {
								url : this.url,
								scripts : true
							}
						});
			},
			initTool : function() {
				this.tools = [{
							id : 'refresh',
							scope : this,
							handler : function() {
								this.getUpdater().update(this.url);
							}
						}, {
							id : 'close',
							hidden : this.closed == null ? false : this.closed,
							scope : this,
							handler : function(e, target, panel) {
								Ext.Msg.confirm('提示信息', '确认删除此模块吧？', function(
												btn) {
											if (btn == 'yes') {
												panel.ownerCt.remove(panel,
														true);
											}
										});
							}
						}];
			}
		});
/**
 * 个人消息
 * 
 * @return {}
 */
MessagePanelView = function() {
	return new App.PortletView({
				id : 'MessagePanelView',
				title : '个人消息',
				iconCls : 'menu-message',
				url : __ctxPath + '/info/displayInMessage.do',
				closed : true
			});
};

/**
 * 待办事项
 * 
 */
TaskPanelView = function() {
	return new App.PortletView({
				id : 'TaskPanelView',
				title : '待办事项',
				iconCls : 'menu-flowWait',
				url : __ctxPath + '/flow/displayTask.do',
				closed : true
			});
};

/**
 * 日志模块
 * 
 * @return {}
 */
DiaryPanelView = function() {
	return new App.PortletView({
				id : 'DiaryPanelView',
				title : '我的日志',
				iconCls : 'menu-diary',
				url : __ctxPath + '/system/displayDiary.do?start=0&limit=8'
			});
};

/**
 * 我的约会
 * 
 */
AppointmentPanelView = function() {
	return new App.PortletView({
				id : 'AppointmentPanelView',
				title : '我的约会',
				iconCls : 'menu-appointment',
				url : __ctxPath + '/task/displayAppointment.do?start=0&limit=8'
			});
};

/**
 * 我的日程
 * 
 */
CalendarPlanPanelView = function() {
	return new App.PortletView({
				id : 'CalendarPlanPanelView',
				title : '我的日程',
				iconCls : 'menu-cal-plan-view',
				url : __ctxPath
						+ '/task/displayCalendarPlan.do?start=0&limit=8'
			});
};

/**
 * 我的计划
 * 
 */
MyPlanPanelView = function() {
	return new App.PortletView({
				id : 'MyPlanPanelView',
				title : '我的计划',
				iconCls : 'menu-myplan',
				url : __ctxPath + '/task/displayWorkPlan.do?start=0&limit=8'
			});
};

/**
 * 个人文档
 * 
 */
MyDocumentPanelView = function() {
	return new App.PortletView({
				id : 'MyDocumentPanelView',
				title : '我的文档',
				iconCls : 'menu-document',
				url : __ctxPath
						+ '/document/displayDocument.do?start=0&limit=8'
			});
};

/**
 * 我的邮件
 * 
 */
MyMailPanelView = function() {
	return new App.PortletView({
				id : 'MyMailPanelView',
				title : '我的邮件',
				iconCls : 'menu-mail',
				url : __ctxPath + '/communicate/displayMail.do?start=0&limit=8'
			});
};

/**
 * 部门计划
 * 
 */
DepPlanPanelView = function() {
	return new App.PortletView({
				id : 'DepPlanPanelView',
				title : '部门计划',
				iconCls : 'menu-depplan',
				url : __ctxPath + '/task/displayDepWorkPlan.do'
			});
};

/**
 * 添加
 * @returns {App.PortletView}
 */
NewTestView = function() {
	return new App.PortletView({
				id : 'NewTestView',
				title : '新闻信息',
				iconCls : 'menu-news',
				url : __ctxPath + '/info/displayNews.do'
			});
};

/**
 * 模板选择器
 * 
 * @class PanelSelectorWin
 * @extends Ext.Window
 */
PanelSelectorWin = Ext.extend(Ext.Window, {
	formPanel : null,
	buttons : null,
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		this.initUI();
		PanelSelectorWin.superclass.constructor.call(this, {
					id : 'PanelSelectorWin',
					title : '选择显示模块',
					layout : 'fit',
					height : 220,
					width : 300,
					modal : true,
					defaults : {
						padding : '5'
					},
					buttons : this.buttons,
					buttonAlign : 'center',
					items : this.formPanel
				});
	},
	initUI : function() {
		this.formPanel = new Ext.FormPanel({
					layout : 'column',
					items : [{
								layout : 'form',
								columnWidth : .5,
								border : false,
								items : [{
											xtype : 'checkbox',
											boxLabel : '待办事项',
											hideLabel : true,
											id : 'TaskPanelViewCheckBox',
											name : 'TaskPanelView'
										}, {
											xtype : 'checkbox',
											boxLabel : '我的日志',
											hideLabel : true,
											id : 'DiaryPanelViewCheckBox',
											name : 'DiaryPanelView'
										}, {
											xtype : 'checkbox',
											hideLabel : true,
											boxLabel : '我的约会',
											id : 'AppointmentPanelViewCheckBox',
											name : 'AppointmentPanelView'
										}, {
											xtype : 'checkbox',
											boxLabel : '我的日程',
											hideLabel : true,
											id : 'CalendarPlanPanelViewCheckBox',
											name : 'CalendarPlanPanelView'
										}, {
											xtype : 'checkbox',
											boxLabel : '部门计划',
											hideLabel : true,
											id : 'DepPlanPanelViewCheckBox',
											name : 'DepPlanPanelView'
										},{
											xtype : 'checkbox',
											boxLabel : '添加新闻',
											hideLabel : true,
											id : 'NewTestViewCheckBox',
											name : 'NewTestView'}]
							}, {
								layout : 'form',
								columnWidth : .5,
								border : false,
								items : [{
											xtype : 'checkbox',
											boxLabel : '个人消息',
											hideLabel : true,
											id : 'MessagePanelViewCheckBox',
											name : 'MessagePanelView'
										}, {
											xtype : 'checkbox',
											hideLabel : true,
											boxLabel : '我的计划',
											id : 'MyPlanPanelViewCheckBox',
											name : 'MyPlanPanelView'
										}, {
											xtype : 'checkbox',
											boxLabel : '我的文档',
											hideLabel : true,
											id : 'MyDocumentPanelViewCheckBox',
											name : 'MyDocumentPanelView'
										}, {
											xtype : 'checkbox',
											boxLabel : '我的邮件',
											hideLabel : true,
											id : 'MyMailPanelViewCheckBox',
											name : 'MyMailPanelView'
										}]
							}]
				});
		// 将已经显示的PORTALITEM勾上
		var portal = this.portal;
		curUserInfo.portalConfig = [];
		var items = portal.items;
		for (var i = 0; i < items.length; i++) {
			var v = items.itemAt(i);
			for (var j = 0; j < v.items.getCount(); j++) {
				var m = v.items.itemAt(j);
				var portalItem = new PortalItem(m.id, i, j);
				curUserInfo.portalConfig.push(portalItem);
			}
		}
		var confs = curUserInfo.portalConfig;
		for (var i = 0; i < confs.length; i++) {
			var panelView = confs[i].panelId;
			var panelCheck = Ext.getCmp(panelView + 'CheckBox');
			if (panelCheck != null) {
				panelCheck.setValue(true);
				panelCheck.disable();
			}
		}

		this.buttons = [{
					xtype : 'button',
					text : '确定',
					iconCls : 'btn-save',
					scope : this,
					handler : this.confirm
				}, {
					xtype : 'button',
					text : '取消',
					iconCls : 'btn-cancel',
					scope : this,
					handler : function() {
						this.close();
					}
				}];
	},
	/**
	 * 确定
	 */
	confirm : function() {
		var portalItem0 = Ext.getCmp('PortalItem0');
		var portalItem1 = Ext.getCmp('PortalItem1');
		var array0 = ['DiaryPanelView', 'TaskPanelView',
				'AppointmentPanelView', 'CalendarPlanPanelView',
				'DepPlanPanelView','NewTestView'];
		for (var v = 0; v < array0.length; v++) {
			var check = Ext.getCmp(array0[v] + 'CheckBox');
			if (check != null) {
				if (check.getValue() && Ext.getCmp(array0[v]) == null) {
					var panel = eval('new ' + array0[v] + '()');
					portalItem0.add(panel);
				}
			}
		}

		var array1 = ['MessagePanelView', 'MyPlanPanelView',
				'MyDocumentPanelView', 'MyMailPanelView'];
		for (var v = 0; v < array1.length; v++) {
			var check = Ext.getCmp(array1[v] + 'CheckBox');
			if (check != null) {
				if (check.getValue() && Ext.getCmp(array1[v]) == null) {
					var panel = eval('new ' + array1[v] + '()');
					portalItem1.add(panel);
				}
			}
		}

		var portal = this.portal;
		curUserInfo.portalConfig = [];
		var items = portal.items;
		for (var i = 0; i < items.length; i++) {
			var v = items.itemAt(i);
			for (var j = 0; j < v.items.getCount(); j++) {
				var m = v.items.itemAt(j);
				var portalItem = new PortalItem(m.id, i, j);
				curUserInfo.portalConfig.push(portalItem);
			}
		}
		
		Ext.Ajax.request({
			method : 'post',
			url : __ctxPath + '/system/saveIndexDisplay.do',
			params : {
				items : Ext.encode(curUserInfo.portalConfig)
			},
			success : function(request) {
				Ext.ux.Toast.msg('操作信息', '保存成功');
			},
			failure : function(request) {
				Ext.MessageBox.show({
							title : '操作信息',
							msg : '信息保存出错，请联系管理员！',
							buttons : Ext.MessageBox.OK,
							icon : 'ext-mb-error'
						});
			}

		});
		
		portalItem0.doLayout();
		portalItem1.doLayout();
		this.portal.doLayout();
		this.close();
	}
});
/**
 * 个人桌面
 * 
 * @class AppHome
 * @extends Ext.Panel
 */
AppHome = Ext.extend(Ext.Panel, {
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				this.initUIComponents();
				AppHome.superclass.constructor.call(this, {
							title : '个人桌面',
							closable : false,
							id : 'AppHome',
							border : false,
							iconCls : 'menu-desktop',
							layout : 'fit',
							defaults : {
								padding : '0 5 0 0'
							},
							tbar : this.toolbar,
							items : this.portalPanel
						});
			},
			initUIComponents : function() {
				// 当前用户的个人桌面配置信息
				var confs = curUserInfo.portalConfig;
				// 如果未空 默认配置
				if (confs == null || confs == undefined || confs.length < 1) {
					confs = new Array();
					var p1 = {
						panelId : 'MessagePanelView',
						column : 1,
						row : 0
					};
					var p2 = {
						panelId : 'CalendarPlanPanelView',
						column : 0,
						row : 1
					};
					var p3 = {
						panelId : 'TaskPanelView',
						column : 0,
						row : 0
					};
					confs.push(p3);
					confs.push(p2);
					confs.push(p1);
				}
				var column0 = [];
				var column1 = [];
				for (var v = 0; v < confs.length; v++) {
					if (confs[v].column == 0) {
						column0.push(eval('new ' + confs[v].panelId + '()'));
					} else {
						column1.push(eval('new ' + confs[v].panelId + '()'));
					}
				}
				//
				this.portalPanel = new Ext.ux.Portal({
							// id : 'Portal',
							// xtype : 'portal',
							border : false,
							region : 'center',
							margins : '35 5 5 0',
							items : [{
										columnWidth : .65,
										style : 'padding:10px 0 10px 10px',
										id : 'PortalItem0',
										items : column0
									}, {
										columnWidth : .35,
										style : 'padding:10px 10px 10px 10px',
										id : 'PortalItem1',
										items : column1
									}]
						});
				// 顶部按钮
				this.toolbar = new Ext.Toolbar({
							height : 30,
							items : ['->', {
										xtype : 'button',
										text : '添加模块',
										iconCls : 'btn-add',
										scope : this,
										handler : this.addModule
									}, '-', {
										xtype : 'button',
										text : '保存',
										iconCls : 'btn-save',
										scope : this,
										handler : this.save
									}]

						});

			},
			/**
			 * 添加模块
			 */
			addModule : function() {
				new PanelSelectorWin({
							portal : this.portalPanel
						}).show();
			},
			/**
			 * 保存
			 */
			save : function() {
				var portal = this.portalPanel;
				curUserInfo.portalConfig = [];
				var items = portal.items;
				for (var i = 0; i < items.length; i++) {
					var v = items.itemAt(i);
					for (var j = 0; j < v.items.getCount(); j++) {
						var m = v.items.itemAt(j);
						var portalItem = new PortalItem(m.id, i, j);
						curUserInfo.portalConfig.push(portalItem);
					}
				}
				Ext.Ajax.request({
							method : 'post',
							url : __ctxPath + '/system/saveIndexDisplay.do',
							params : {
								items : Ext.encode(curUserInfo.portalConfig)
							},
							success : function(request) {
								Ext.ux.Toast.msg('操作信息', '保存成功');
							},
							failure : function(request) {
								Ext.MessageBox.show({
											title : '操作信息',
											msg : '信息保存出错，请联系管理员！',
											buttons : Ext.MessageBox.OK,
											icon : 'ext-mb-error'
										});
							}

						});
			}
		});
