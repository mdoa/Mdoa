Ext.ns('SearchNotice');
/**
 * 搜索公告
 * @class SearchNews
 * @extends Ext.Panel
 */
var SearchNotice = function(searchContent) {
	return new SearchNoticeView({
		searchContent : searchContent
	});
};
var SearchNoticeView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		SearchNoticeView.superclass.constructor.call(this, {
					id : 'SearchNoticeView',
					title : '搜索公告',
					iconCls:'menu-news',
					region : 'center',
					layout : 'border',
					items : [this.searchPanel,this.gridPanel]
				});
	},// end of constructor
	// 初始化组件
	initUIComponents : function() {
		// 初始化搜索条件Panel
		this.searchPanel = new HT.SearchPanel({
					region : 'north',
					layout : 'form',
					colNums : 3,
					keys : [{
								key : Ext.EventObject.ENTER,
								fn : this.search,
								scope : this
							}, {
								key : Ext.EventObject.ESC,
								fn : this.reset,
								scope : this
							}],
					labelWidth : 80,
					items : [{
						fieldLabel : '请输入条件',
						xtype : 'textfield',
						name : 'searchContent'
					},{
						xtype : 'button',
						text : '查询',
						iconCls : 'search',
						scope : this,
						handler : this.search
					},{
						xtype : 'button',
						text : '清空',
						iconCls : 'reset',
						scope : this,
						handler : this.reset
					},{
						name : 'isNotice',
						value : 1,
						xtype : 'hidden'
					}]
				});// end of the searchPanel
		
		// 新闻列表面板
		this.gridPanel = new HT.GridPanel({
					region : 'center',
					url : __ctxPath + '/info/searchNews.do',
					sort : [{field:"newsId",direction:"DESC"}],
					baseParams : {
							   'Q_isNotice_SN_EQ' : 1,
								searchContent:this.searchContent
							},
					fields : [{
								name : 'newsId',
								type : 'int'
							},'sectionId', 'subjectIcon', 'subject', 'author',
							'createtime', 'expTime', 'replyCounts',
							'viewCounts', 'issuer', 'content', 'updateTime',
							'status', 'isDeskImage', 'isNotice', 'sn','section'],
					columns : [{
								header : 'newsId',
								dataIndex : 'newsId',
								hidden : true
							}, {
								header : '新闻标题',
								width : 120,
								dataIndex : 'subject'
							}, {
								header : '作者',
								width : 120,
								dataIndex : 'author'
							}, {
								header : '创建时间',
								dataIndex : 'createtime',
								renderer : function(value){
									if(value !=null){
										return value.substring(0,10);
									}
								}
							},{
								header : '回复次数',
								width : 120,
								dataIndex : 'replyCounts'
							},{
								header : '浏览数',
								width : 120,
								dataIndex : 'viewCounts'
							},{
								header : '状态',
								width : 120,
								dataIndex : 'status',
								renderer : function(value) {
									if(value !=null && value == 0){
										return '<font color="red">禁用</font>';
									}else if(value == 1){
										return '<font color="green">激活</font>';
									}
								}
							}]
				});
		this.gridPanel.addListener({scope:this,'rowdblclick': this.rowClick});
	},// end of the initUIComponents
	// 重置查询表单
	reset : function() {
		this.searchPanel.getForm().reset();
	},
	// 按条件搜索
	search : function() {
		$search({
			searchPanel : this.searchPanel,
			gridPanel : this.gridPanel
		});
	},
	// GridPanel行点击处理事件
	rowClick : function(grid, rowindex, e) {
		var rec = grid.getStore().getAt(rowindex);
		App.clickTopTab('NewsDetail',rec.data.newsId,function(){
			AppUtil.removeTab('NewsDetail');
		});
		
	}
});

