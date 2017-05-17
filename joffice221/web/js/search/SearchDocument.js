Ext.ns('SearchDocument');
/**
 * 搜索文档
 * @class SearchNews
 * @extends Ext.Panel
 */
var SearchDocument = function(searchContent) {
	return new SearchDocumentView({
		searchContent : searchContent
	});
}
var SearchDocumentView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		SearchDocumentView.superclass.constructor.call(this, {
					id : 'SearchDocumentView',
					title : '搜索文档',
					iconCls : 'menu-document',
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
					}]
				});// end of the searchPanel
		
		// 新闻列表面板
		this.gridPanel = new HT.GridPanel({
					region : 'center',
					url : __ctxPath + '/document/searchDocument.do',
					sort : [{field:"docId",direction:"DESC"}],
					baseParams : {
								content:this.searchContent
							},
					fields : [{
								name : 'docId',
								type : 'int'
							}, 'docName', 'fullname', {
								name : 'isPublic',
								mapping : 'docFolder.isShared'
							}, 'content', 'createtime', 'haveAttach',
							'attachFiles', 'isShared'],
					columns : [{
								header : 'docId',
								dataIndex : 'docId',
								hidden : true
							}, {
								header : '文档名称',
								dataIndex : 'docName',
								width : 120
							}, {
								header : '创建人',
								dataIndex : 'fullname'
							}, {
								header : '创建时间',
								dataIndex : 'createtime'
							},{
								header : '属性',
								width : 40,
								dataIndex : 'isShared',
								renderer : this.isShared
							},{
								header : '附件',
								dataIndex : 'haveAttach',
								renderer : this.attachment
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
		var id = rec.data.docId;
		var docName = rec.data.docName;
		var tabs = Ext.getCmp('centerTabPanel');
		var panel = Ext.getCmp('PulicDocumentDetail');
		if (panel == null) {
			panel = new PublicDocumentDetail({docId:id, docName:docName});
			tabs.add(panel);
			tabs.activate(panel);
		} else {
			tabs.remove('PulicDocumentDetail');
			panel = new PublicDocumentDetail({docId:id, docName:docName});
			tabs.add(panel);
			tabs.activate(panel);
		}
		
	},
	/**
	 * 是否共享
	 * @param {} value
	 * @param {} metadata
	 * @param {} record
	 * @return {}
	 */
	isShared : function(value, metadata, record) {
		var isPublic = record.data.isPublic;
		if (value == 1) {
			return '<img src="'
					+ __ctxPath
					+ '/images/flag/shared.png" alt="共享" title="共享文档" />';
		} else {
			if (isPublic == '0') {
				return '<img src="'
						+ __ctxPath
						+ '/images/flag/lock.png" alt="私有" title="私有文档" />';
			} else {
				return '<img src="'
						+ __ctxPath
						+ '/images/btn/flow/unlockTask.png" alt="公共" title="公共文档" />';
			}
		}
	},
	/**
	 * 附件
	 * @param {} value
	 * @param {} metadata
	 * @param {} record
	 * @return {String}
	 */
	attachment : function(value, metadata, record) {
		if (value == '' || value == '0') {
			return '无附件';
		} else {
			var attachFiles = record.data.attachFiles;
			var str = '';
			for (var i = 0; i < attachFiles.length; i++) {
				str += '<a href="#" onclick="FileAttachDetail.show('
						+ attachFiles[i].fileId
						+ ');" class="attachment">'
						+ attachFiles[i].fileName + '</a>';
				str += '&nbsp;';
			}
			return str;
		}
	}
});

