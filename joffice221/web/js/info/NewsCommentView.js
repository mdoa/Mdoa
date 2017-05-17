Ext.ns('NewsCommentView');
/**
 * [NewsComment]列表
 */
NewsCommentView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		NewsCommentView.superclass.constructor.call(this, {
					id : 'NewsCommentView',
					title : '评论列表',
					iconCls : 'menu-info',
					layout : 'border',
					region : 'center',
					autoScroll : true,
					items : [this.searchPanel, this.gridPanel]
				});
	},
	// 初始化组件
	initUIComponents : function() {
		// 初始化搜索条件Panel
		this.searchPanel = new HT.SearchPanel({
					region : 'north',
					layout : 'form',
					colNums : 6,
					keys : [{
								key : Ext.EventObject.ENTER,
								fn : this.search,
								scope : this
							}, {
								key : Ext.EventObject.ESC,
								fn : this.reset,
								scope : this
							}],
					labelWidth : 65,
					items : [{
								fieldLabel : '所属新闻',
								xtype : 'textfield',
								width : '10%',
								name : 'Q_news.subject_S_LK'
							},{
								fieldLabel : '评论时间',
								xtype : 'datefield',
								width : '20%',
								format : 'Y-m-d',
								name : 'Q_createtime_D_GE'
							},{
								fieldLabel : '至',
								xtype : 'datefield',
								width : '20%',
								format : 'Y-m-d',
								name : 'Q_createtime_D_LE',
								labelWidth : 15
							},{
								fieldLabel : '评论人',
								xtype : 'textfield',
								width : '10%',
								name : 'Q_fullname_S_LK'
							}, {
								xtype : 'button',
								text : '查询',
								iconCls : 'search',
								handler : this.search,
								scope : this
							}, {
								xtype : 'button',
								text : '清空',
								iconCls : 'reset',
								handler : this.reset,
								scope : this
							}]
				});

		//顶端栏目条
		this.topbar = new Ext.Toolbar({
					items : [{
								iconCls : 'btn-del',
								text : '删除评论',
								xtype : 'button',
								scope : this,
								handler : this.removeSelRs
							}]
				});
				
		var expander = new Ext.ux.grid.RowExpander({
			tpl : new Ext.Template('<p style="padding:5px 5px 5px 62px;"><b>评论内容:</b> {content}</p>')
	
		});

		// 评论面板
		this.gridPanel = new HT.GridPanel({
			region : 'center',
			tbar : this.topbar,
			sort : [{field:"commentId",direction:"DESC"}],
			// 使用RowActions
			rowActions : true,
			expander : expander,
			plugins : expander,
			url : __ctxPath + '/info/listNewsComment.do',
			fields : [{
						name : 'commentId',
						type : 'int'
					}, 'news.subject', 'content', 'createtime', 'fullname', 'userId'],
			columns : [expander,{
						header : 'commentId',
						dataIndex : 'commentId',
						hidden : true
					}, {
						header : '所属新闻',
						dataIndex : 'news.subject',
						sortable : false,
						width : 250
					}, {
						header : '评论时间',
						dataIndex : 'createtime',
						width : 100,
						renderer : function(value) {
							return value.substring(0, 10);
						}
					}, {
						header : '评论人',
						dataIndex : 'fullname',
						width : 60
					}, new Ext.ux.grid.RowActions({
								header : '管理',
								width : 100,
								actions : [{
											iconCls : 'btn-del',qtip : '删除',style : 'margin:0 3px 0 3px'
										}],
								listeners : {
									scope : this,
									'action' : this.onRowAction
								}
					})]
		});
	},
	// 重置/清空查询表单
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
	// 按ID删除记录
	remove : function(id) {
		$postDel({
			url : __ctxPath + '/info/multiDelNewsComment.do',
			ids : id,
			grid : this.gridPanel
		});
	},
	// 把选中ID删除
	removeSelRs : function() {
		$delGridRs({
			url : __ctxPath + '/info/multiDelNewsComment.do',
			grid : this.gridPanel,
			idName : 'commentId'
		});
	},
	// 行的Action
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.remove.call(this, record.data.commentId);
				break;
		}
	}

});