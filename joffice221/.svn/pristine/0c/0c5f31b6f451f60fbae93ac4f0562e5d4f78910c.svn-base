/**
 * 为了兼容之前的版本暂时这样处理
 * 
 * @param {}
 *            _id
 * @return {}
 */
var NewsDetail = function(_id) {
	return new NewsDetailView({
				newsId : _id
			});
};
/**
 * 新闻详情
 * 
 * @class NewsDetailView
 * @extends Ext.Panel
 */
var NewsDetailView = Ext.extend(Ext.Panel, {
			// 构造函数
			constructor : function(conf) {
				Ext.applyIf(this, conf);
				// 初始化组件
				this.initUIComponents();
				// 调用父类构造
				NewsDetailView.superclass.constructor.call(this, {
							id : 'NewsDetailView',
							title : '新闻详情',
							region : 'center',
							iconCls : 'menu-news',
							tbar : this.topbar,
							autoScroll : true,
							autoWidth : true,
							defaults : {
								width : '80%'
							},
							items : [new NewsDetailPanel({
										newsId : this.newsId
									})]
						});
			},// end of constructor
			// 初始化组件
			initUIComponents : function() {
				// 顶部按钮组
				this.topbar = new Ext.Toolbar({
							items : [{
										iconCls : 'btn-mail_remove',
										text : '关闭',
										xtype : 'button',
										scope : this,
										handler : this.cancel
									}, {
										iconCls : 'btn-up',
										text : '上一条',
										xtype : 'button',
										scope : this,
										handler : this.prev
									}, {
										iconCls : 'btn-last',
										text : '下一条',
										xtype : 'button',
										scope : this,
										handler : this.next
									}]
						});

			},
			/**
			 * 关闭
			 */
			cancel : function() {
				var centerTabPanel = Ext.getCmp('centerTabPanel');
				var oldDetailPanel = centerTabPanel.getItem('NewsDetailView');
				centerTabPanel.remove(oldDetailPanel);
			},

			/**
			 * 上一条
			 */
			prev : function() {
				if (!Ext.isEmpty(this.newsId)) {
					Ext.Ajax.request({
								url : __ctxPath + '/info/prevNews.do',
								method : 'POST',
								params : {
									// 只取生效的新闻
									'Q_status_SN_EQ' : 1,
									// 点击上一条按钮,则取比当前ID小的下一条记录
									'Q_newsId_L_LT' : this.newsId
								},
								scope : this,
								success : function(response) {
									var res = Ext.util.JSON
											.decode(response.responseText);
									if (!Ext.isEmpty(res.newsId)) {
										this.newsId = res.newsId;
										this.doLayoutNews(this.newsId);
									} else {
										Ext.ux.Toast.msg('提示信息', '这里已经是第一条了');
									}
								},
								failure : function() {

								}
							});
				}
			},
			/**
			 * 下一条
			 */
			next : function() {
				if (!Ext.isEmpty(this.newsId)) {
					Ext.Ajax.request({
								url : __ctxPath + '/info/nextNews.do',
								method : 'POST',
								params : {
									// 只取生效的新闻
									'Q_status_SN_EQ' : 1,
									// 点击下一条按钮,则取比当前ID大的下一条记录
									'Q_newsId_L_GT' : this.newsId
								},
								scope : this,
								success : function(response) {
									var res = Ext.util.JSON
											.decode(response.responseText);
									if (!Ext.isEmpty(res.newsId)) {
										this.newsId = res.newsId;
										this.doLayoutNews(this.newsId);
									} else {
										Ext.ux.Toast.msg('提示信息', '这里已经是最后一条了.');
									}

								},
								failure : function() {

								}
							});
				}
			},
			/**
			 * 重新布局
			 * 
			 * @param {}
			 *            newsId
			 */
			doLayoutNews : function(newsId) {
				this.removeAll();
				this.add([new NewsDetailPanel({
							newsId : newsId
						})]);
				this.doLayout();
			}

		});

var NewsDetailPanel = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		NewsDetailPanel.superclass.constructor.call(this, {
					region : 'center',
					autoScroll : true,
					autoWidth : true,
					defaults : {
						width : '80%'
					},
					items : [this.homeNewsPanel, this.newsCommentGrid,
							this.myNewCommentForm]
				});

	},
	// 初始化组件
	initUIComponents : function() {
		// 新闻内容
		this.homeNewsPanel = new Ext.Panel({
					autoScroll : true,
					style : 'padding-left:10%;padding-top:10px;',
					autoHeight : true,
					border : false,
					autoLoad : {
						url : __ctxPath + '/pages/info/newsdetail.jsp?newsId='
								+ this.newsId,
						scripts : true
					}
				});
		// 查看评论
		this.newsCommentGrid = new HT.GridPanel({
			title : '查看评论',
			iconCls : 'menu-info',
			style : 'padding-left:10%;padding-top:10px;',
			autoHeight : true,
			autoWidth : true,
			url : __ctxPath + '/info/listNewsComment.do',
			baseParams : {
				'Q_news.newsId_L_EQ' : this.newsId
			},
			sort : [{
						field : "createtime",
						direction : "ASC"
					}],
			showChbCol : false,
			fields : [{
						name : 'commentId',
						type : 'int'
					}, 'content', 'createtime', 'fullname', 'start'],
			columns : [{
				width : 40,
				dataIndex : 'start',
				renderer : function(value, metadata, record, rowIndex, colIndex) {
					return parseInt(value) + rowIndex + 1 + '楼';
				}
			}, {
				dataIndex : 'commentId',
				hidden : true
			}, {
				width : 400,
				dataIndex : 'content',
				renderer : this.content
			}]
		});
		// 我要评论
		this.myNewCommentForm = new Ext.FormPanel({
					title : '我要评论',
					iconCls : 'menu-info',
					autoScroll : true,
					style : 'padding-left:10%;padding-top:10px;padding-bottom:25px;',
					autoHeight : true,
					defaultType : 'textfield',
					labelWidth : 55,
					defaults : {
						width : 550,
						anchor : '98%,98%'
					},
					layout : 'form',
					items : [{
								name : 'newsComment.newsId',
								xtype : 'hidden',
								value : this.newsId
							}, {
								name : 'newsComment.userId',
								xtype : 'hidden',
								value : curUserInfo.userId
							}, {
								fieldLabel : '用户',
								name : 'newsComment.fullname',
								readOnly : true,
								value : curUserInfo.fullname
							}, {
								fieldLabel : '内容',
								xtype : 'textarea',
								blankText : '评论内容为必填!',
								allowBlank : false,
								name : 'newsComment.content'
							}],
					buttonAlign : 'center',
					buttons : [{
								text : '提交',
								iconCls : 'btn-save',
								scope : this,
								handler : this.save
							}, {
								text : '清空',
								iconCls : 'reset',
								scope : this,
								handler : this.reset
							}]
				});
	},
	/**
	 * 评论内容
	 */
	content : function(value, metadata, record, rowIndex, colIndex) {
		html = '<table width="100%"><tr><td><font color="gray">评论人:'
				+ record.data.fullname
				+ '</font></td><td align="right"><font color="gray">'
				+ record.data.createtime
				+ '</font></td></tr><tr><td rowspan="2"><font style="font:13px 宋体;color: black;line-height:24px;">'
				+ value + '</font></td></tr></table>';
		return html;
	},
	/**
	 * 提交
	 */
	save : function() {

		$postForm({
					formPanel : this.myNewCommentForm,
					scope : this,
					url : __ctxPath + '/info/saveNewsComment.do',
					callback : function(fp, action) {
						this.reset();
						// 刷新评论列表
						this.newsCommentGrid.getStore().reload();
						// 更新回复数
						var replyCounts = document
								.getElementById("replyCounts");
						var reply = replyCounts.innerHTML.trim();
						if (Ext.isNumber(Number(reply))) {
							replyCounts.innerHTML = Number(reply) + 1;
						}
					}
				});
	},
	reset : function() {
		this.myNewCommentForm.getCmpByName('newsComment.content').setValue('');
	}
});