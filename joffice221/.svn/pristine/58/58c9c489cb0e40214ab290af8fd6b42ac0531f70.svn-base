Ext.ns('PublicDocumentDetail');
/**
 * 共享文档详情
 * 
 * @class PublicDocumentDetail
 * @extends Ext.Panel
 */
PublicDocumentDetail = Ext.extend(Ext.Panel, {
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 初始化组件
				this.initUI();
				// 调用父类构造
				PublicDocumentDetail.superclass.constructor.call(this, {
							id : 'PulicDocumentDetail',
							iconCls : 'menu-find-doc',
							title : '' + this.docName + '-详细信息',
							autoHeight : true,
							modal : true,
							autoScroll : true,
							layout : 'anchor',
							plain : true,
							items : this.panel
						});
			},
			// 初始化组件
			initUI : function() {
				// 初始化工具栏
				var topbar = new Ext.Toolbar({
							height : 35,
							bodyStyle : 'text-align:center',
							items : []
						});
				// 面板
				this.panel = new Ext.Panel({
							modal : true,
							tbar : topbar,
							autoScroll : true,
							bodyStyle : 'padding-left:10%;padding-right:10%;',
							height : 450,
							autoLoad : {
								url : __ctxPath
										+ '/document/publicDetailDocument.do?docId='
										+ this.docId
							}
						});
			}

		});