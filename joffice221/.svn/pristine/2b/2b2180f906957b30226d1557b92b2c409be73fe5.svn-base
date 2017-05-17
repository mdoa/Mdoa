Ext.define('mobile.news.News', {
	extend : 'mobile.List',
	name : 'news',
	constructor : function(config) {
		config = config || {};
		var isNotice = config.title=='公司公告'?1:0;
		var currentConfig=this;
		Ext.apply(config, {
			title : config.title,
			fields : [
			            {name: "newsId",type: "string"},
						{name: "subject",  type: "string"},
		    			{name: "createtime",  type: "auto"},
						{name: "content",  type: "string"},
						{name: "author",  type: "string"},
						{name: "replyCounts", type: "Integer"},
						{name: "viewCounts", type: "Integer"},
						{name: "issuer", type: "string"},
						{name: "orgIds", type: "string"},
						{name: "orgNames", type: "string"}
					],
			url : __ctxPath + '/htmobile/listNews.do?Q_isNotice_SN_EQ='+isNotice,
			root : 'result',
			searchCol : 'Q_subject_S_LK',
			sorters: [{property:'createtime', direction: 'DESC'}],
			clearOnPageLoad : true,
			itemTpl : new Ext.XTemplate("<span style='font-size:18px;color:#412f1f;'>" +
					"{subject:ellipsis(10)}</span>&nbsp;<span style='font-size:12px;color:#a7573b;'>" +
					"作者({author})</span><br/>" +
					"<span style='font-size:12px;color:#a7573b;float:center;margin-left:12px'>" +
					"{[this.getContent(values.content)]}..." +
					"<span style='float:right;'>{createtime}</span></span><div id='hideHTML'></div>",
					{
					getContent : function(content){
							return Ext.String.ellipsis(content.replace(/<\/?.+?>/g,""),40);
						}
					}
					),
			grouped: false,
			totalProperty: 'totalCounts',
			pullRefresh: true,
			listPaging: true,
			listeners : {
				itemsingletap : this.itemsingletap
//				initialize : function(){
//					if (isNotice == 1) {
//						var me = this;
//						Ext.Ajax.request({
//									url : __ctxPath
//											+ '/htmobile/getUserOrgNamesNews.do',
//									success : function(response) {
//										var result = Ext.util.JSON
//												.decode(response.responseText);
//										me.add(currentConfig.getToolbar(
//												currentConfig, result));
//									}
//								})
//					}
//				}
					
			}
		});
		this.callParent([config]);

	},

	itemsingletap : function(obj, index, target, record) {
		Ext.Ajax.request({
			url : __ctxPath + '/htmobile/getNews.do',
			params : {
				newsId : record.get('newsId')
			},
			success : function(response) {
				var result = Ext.util.JSON.decode(response.responseText);
				mobileNavi.push(
						Ext.create('mobile.news.NewsDetail', {
							result: result,
							itemRecord : record,
							callback:function() {
								this.store.load();
							}
						})
					);
			}
		});
	},
	getToolbar : function(currentConfig,result) {
		var orgNamesLength = result.data.length;
		var flexSize = orgNamesLength % 2 == 0 ? 6 : 5;
		var spaceLength = (flexSize - orgNamesLength) >= 0
				? (flexSize - orgNamesLength) / 2
				: 0;
								
		var toolbar = Ext.create('Ext.Toolbar', {
					docked : 'bottom',
					scrollable : {
						direction : 'horizontal',
						directionLock : true
					}
				});
		for (var i = 0; i < spaceLength; i++) {
			toolbar.add(Ext.create('Ext.Spacer', {
						flex : 1
					}));
		}
		toolbar.add(Ext.create('Ext.Button', {
					text : "全部",
					handler : function(btn,e){
						currentConfig.getNewsByOrg(btn,e,currentConfig);
					}
				}));
		for (var i = 0, orgName; orgName = result.data[i]; i++) {
			toolbar.add(Ext.create('Ext.Button', {
						text : orgName,
						handler : function(btn,e){
							currentConfig.getNewsByOrg(btn,e,currentConfig);
						}
					}));
		}
		for (var i = 0; i < spaceLength; i++) {
			toolbar.add(Ext.create('Ext.Spacer', {
						flex : 1
					}));
		}
		return toolbar;
	},
	getNewsByOrg : function(btn, e,currentConfig) {
		var orgNames=btn.getText()=="全部"?"":btn.getText();
    	currentConfig.store.getProxy().setExtraParam("Q_orgNames_S_LK", orgNames); 
		currentConfig.store.loadPage(1);
	}

});
