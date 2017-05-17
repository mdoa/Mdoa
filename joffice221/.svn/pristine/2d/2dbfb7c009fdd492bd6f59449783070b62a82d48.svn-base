/**
 * 栏目视图
 * 
 * @class Portlet
 * @extends Ext.ux.Portlet
 */
Portlet = Ext.extend(Ext.ux.Portlet, {
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		Portlet.superclass.constructor.call(this, {
					title : this.title,
					iconCls : this.iconCls,
					tools : this.tools,
					autoLoad : {
						url : this.url,
						scripts : true
					}
				});
	},
	//初始化组件
	initUIComponents : function() {
		//设置url
		if (this.sectionType == 2) {
			this.url = __ctxPath
					+ '/pages/indexpages/deskNewsListPage.jsp?sectionId='
					+ this.sectionId;
		} else if (this.sectionType == 3) {
			this.url = __ctxPath
					+ '/pages/indexpages/noticeScrollPage.jsp?sectionId='
					+ this.sectionId;
		} else {
			this.url = __ctxPath
					+ '/pages/indexpages/newsListPage.jsp?sectionId='
					+ this.sectionId;
		}
		
		//顶部按钮
		this.tools = [{
					id : 'refresh',
					scope : this,
					handler : this.refreshPanel
				}, {
					id : 'close',
					scope : this,
					handler : this.disablePanel
				}];
	},
	// 刷新栏目
	refreshPanel : function() {
		this.getUpdater().update(this.url);
	},
	// 删除栏目模块
	disablePanel : function(e, target, panel) {
		Ext.Msg.confirm('提示信息', '确认删除此模块吧？', function(btn) {
			if (btn == 'yes') {
				panel.ownerCt.remove(panel, true);
				Ext.Ajax.request({
							url : __ctxPath + '/info/disableSection.do',
							method : 'POST',
							scope : this,
							params : {
								sectionId : this.sectionId
							},
							success : function() {
							},
							failure : function() {

							}
						});
			}
		}, this);
	}

});