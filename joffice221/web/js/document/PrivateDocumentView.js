Ext.ns('PrivateDocumentView');
/**
 * @author zxh
 * @class PrivateDocumentView
 * @extends Ext.Panel
 * @description 个人文档-我的文档
 */
PrivateDocumentView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		// 初始化组件
		this.initUI();
		PrivateDocumentView.superclass.constructor.call(this, {
					title : '我的文档',
					iconCls : 'menu-personal-doc',
					layout : 'border',
					region : 'center',
					id : 'PrivateDocumentView',
					height : 800,
					width : 800,
					items : [this.treePanel, this.rightPanel]

				});
	},
	// 初始化组件
	initUI : function() {
		this.selectNode = Ext.isEmpty(this.selectNode) ? 0 : this.selectNode;
		// 左边的目录树
		this.treePanel = new htsoft.ux.TreePanelEditor({
			layout : 'fit',
			region : 'west',
			title : '个人文档目录',
			collapsible : true,
			split : true,
			autoScroll : true,
			width : 200,
			height : 800,
			animate : true,
			enableDD : true,
			url : __ctxPath + '/document/listDocFolder.do',
			scope : this,
			onclick : this.clickNode,
			movenode : this.moveNode
				// nodedragover: this.nodedragover
			});
		// 树的右键菜单的
		this.treePanel.on('contextmenu', this.treeContextMenu, this);
		// 查询面板
		this.searchPanel = new HT.SearchPanel({
					layout : 'form',
					region : 'north',
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
					height : 50,
					labelWidth : 55,
					items : [{
								fieldLabel : '文件名',
								xtype : 'textfield',
								name : 'fileName'
							}, {
								xtype : 'button',
								text : '查询',
								iconCls : 'btn-search',
								scope : this,
								handler : this.search
							}, {
								xtype : 'button',
								text : '重置',
								iconCls : 'btn-reset',
								scope : this,
								handler : this.reset
							}]
				});
		this.tpl = new Ext.XTemplate(
				'<ul>',
				'<tpl for=".">',
				'<li class="thumb-wrap" style="line-height:2em" id="{fileId}" name="{fileName}" type="{isFolder}" ext:qtip="<div>名称：{fileName}</div><div>类型：{fileType}</div>" onselect= "document.selection.empty()">',
				'<img width="64" height="64" src="'
						+ __ctxPath
						+ '/images/flag/document/{[(values.isFolder==0?(values.isShared==0?"file.png":"share.png"):"folder.png")]}" />',
				'<strong style="font-size:15px;overflow:hidden;">{fileName}</strong>',
				// '{[values.isFolder == 0 ? "<span><a href=#>下载</a></span>"
				// : ""]}',
				'</li>', '</tpl>', '</ul>');
		// 数据展示
		this.dataView = new HT.DataView({
					id : 'private',
					region : 'center',
					border : false,
					url : __ctxPath + '/document/folderDocFolder.do',
					fields : ['fileId', 'fileName', 'fileSize', 'fileType',
							'isFolder', {
								name : 'parentId',
								type : 'int'
							}, 'parentName', 'isShared'],

					sort : [{
								field : 'isFolder',
								direction : 'DESC'
							}],
					tpl : this.tpl,
					overClass : 'x-view-over',
					itemSelector : 'li.thumb-wrap',
					bodyStyle : 'padding:4px',
					emptyText : '目前尚无记录',
					listeners : {
						'render' : {
							fn : this.bodyRender,
							scope : this
						},
						'dblclick' : {
							fn : this.openFile,
							scope : this
						},
						'contextmenu' : {
							fn : this.dbclickNode,
							scope : this
						},
						'click' : {
							fn : this.selDoc,
							scope : this
						}
					}
				});

		// 顶部按钮
		this.toolbar = new Ext.Toolbar({
					items : [{
								xtype : 'button',
								text : '新建目录',
								scope : this,
								iconCls : 'btn-add',
								id : 'NewPrivateFolderButton',
								handler : this.newFolder
							}, {
								xtype : 'button',
								text : '新建文档',
								scope : this,
								id : 'NewPrivateDocumentButton',
								iconCls : 'btn-add',
								handler : this.newDocument
							}, {
								xtype : 'button',
								text : '上一级目录',
								iconCls : 'btn-up',
								id : 'UpPrivateFolderButton',
								scope : this,
								handler : this.upLevel
							}, {
								xtype : 'button',
								text : '顶级目录',
								iconCls : 'btn-superior',
								id : 'TopOnlineFolderButton',
								scope : this,
								handler : this.topFolder
							},{
								xtype : 'button',
								text : '刷新',
								iconCls : 'btn-refresh',
								scope : this,
								handler : this.refresh
							}]
				});

		// 文件路径
		this.filePathPanel = new Ext.Panel({
					xtype : 'panel',
					height : 28,
					region : 'north',
					layout : 'fit',
					items : [{
								xtype : 'textfield',
								readOnly : true,
								style : 'padding-left:15px;',
								cls : 'text-file',
								name : 'filePath',
								value : '/我的文件夹'
							}]
				});
		// 展示目录
		this.showPanel = new Ext.Panel({
					region : 'center',
					tbar : this.toolbar,
					layout : 'border',
					items : [this.filePathPanel, this.dataView]
				});

		// 右边的面板
		this.rightPanel = new Ext.Panel({
					title:'个人文档列表',
					region : 'center',
					layout : 'border',
					items : [this.searchPanel, this.showPanel]
				});

	},
	// =====树的相关操作========
	/**
	 * 点击树的节点
	 * 
	 * @param {}
	 *            node
	 */
	clickNode : function(node) {
		this.selectNode = node;
		var folderId = node.id
		this.changePath();// 改变显示的路径
		var store = this.dataView.getStore();
		store.baseParams.isSearch = false;
		if (folderId != null) {
			if (folderId == 0) {
				this.rightPanel.setTitle('个人文档列表');
			} else {
				this.rightPanel.setTitle('[' + node.text + ']' + '文档列表');
			}
		}
		store.reload({
					params : {
						folderId : folderId
					}
				});
		this.searchEnable();
	},
	/**
	 * 移动树的节点
	 * 
	 * @param {}
	 *            tree
	 * @param {}
	 *            node
	 * @param {}
	 *            oldParent
	 * @param {}
	 *            newParent
	 * @param {}
	 *            index
	 * @return {Boolean}
	 */
	moveNode : function(tree, node, oldParent, newParent, index) {
		if (oldParent.id == newParent.id) {
			// this.clickNode(node);
			return false;
		}
		Ext.Msg.confirm('操作提示', '你确定移动该目录吗?', function(btn) {
					if (btn == 'yes') {
						Ext.Ajax.request({
									url : __ctxPath
											+ '/document/moveDocFolder.do',
									params : {
										folderIdOld : node.id,
										folderIdNew : newParent.id
									},
									scope : this,
									method : 'post',
									success : function(result, request) {
										this.refresh().call(this);
									},
									failure : function(result, request) {

									}
								});
						return true;
					} else {// 选择否 还原
						tree.root.reload();
					}
				}, this);
		return false;
	},
	/**
	 * 树的右键菜单
	 * 
	 * @param {}
	 *            node
	 * @param {}
	 *            e
	 */
	treeContextMenu : function(node, e) {
		this.clickNode(node);
		var menuItems = [{
					text : '新建',
					iconCls : 'btn-add',
					scope : this,
					handler : this.newFolder
				}, {
					text : '修改',
					iconCls : 'btn-edit',
					hidden : node.id == 0 ? true : false,
					scope : this,
					handler : this.editFile.createCallback(this, true)
				}, {
					text : '删除',
					iconCls : 'btn-delete',
					hidden : node.id == 0 ? true : false,
					scope : this,
					handler : this.delFolder.createCallback(this,
							this.dataView, this.selectNode.id, this.treePanel,
							true)
				}, {
					text : '属性',
					scope : this,
					iconCls : 'btn-detail',
					handler : this.folderDetail
				}]

		var menus = new Ext.menu.Menu({
					items : menuItems
				});
		menus.showAt(e.getXY());
	},
	// =====查询的相关操作========
	/**
	 * 查询
	 */
	search : function() {
		this.isSearching = true;
		this.searchDisable();
		var searchPanel = this.searchPanel;
		if (searchPanel.getForm().isValid()) {
			var baseParam = Ext.Ajax.serializeForm(searchPanel.getForm()
					.getEl());
			var deParams = Ext.urlDecode(baseParam);
			deParams.isSearch = true;
			deParams.limit = 10000;
			this.store = this.dataView.getStore();
			this.store.baseParams = deParams;
			this.store.reload();
		}
	},
	/**
	 * 查询-重置
	 */
	reset : function() {
		this.searchPanel.getForm().reset();
		this.isSearching = false;
		this.searchEnable();
	},
	/**
	 * 查询状态-禁止操作
	 */
	searchDisable : function() {
		Ext.getCmp('NewPrivateFolderButton').disable();
		Ext.getCmp('NewPrivateDocumentButton').disable();
		Ext.getCmp('UpPrivateFolderButton').disable();
		this.isSearching = true;
	},
	/**
	 * 查询状态-恢复操作
	 */
	searchEnable : function() {
		Ext.getCmp('NewPrivateFolderButton').enable();
		Ext.getCmp('NewPrivateDocumentButton').enable();
		Ext.getCmp('UpPrivateFolderButton').enable();
		this.isSearching = false;
	},
	// =====改变路径操作========
	/**
	 * 改变路径
	 */
	changePath : function() {
		var node = this.selectNode;
		var path = '';
		while (node != null && node.text != undefined) {
			path = '/' + node.text + path;
			node = node.parentNode;
		}
		this.filePathPanel.getCmpByName('filePath').setValue(path);
	},
	// ======顶部按钮相关操作===
	/**
	 * 新建目录
	 */
	newFolder : function() {
		var parentId = 0;
		var node = this.selectNode;
		if (node) {
			parentId = this.selectNode.id;
		}
		var treePanel = this.treePanel;
		new DocFolderForm({
					folderId : null,
					parentId : parentId,
					isShared : null,
					userId:curUserInfo.userId,
					scope: this,
					callback : this.refresh
				}).show();
	},
	/**
	 * 新建文档
	 */
	newDocument : function() {
		var nodes = this.dataView.getSelectedNodes();
		var folderId = null;
		var folderName = null;
		if (nodes != '' && nodes[0].type == 1) {
			folderId = nodes[0].id;
			folderName = nodes[0].getAttribute('name')
		} else if (nodes == '' && this.selectNode && this.selectNode.id != 0) {
			folderId = this.selectNode.id
			folderName = this.selectNode.text;
		} else {
		}
		new DocumentForm({
					folderId : folderId,
					folderName : folderName,
					scope : this,
					callback : this.refresh
				}).show();
	},
	/**
	 * 上级目录
	 */
	upLevel : function() {
		this.isSearching = false;
		var node = this.selectNode;
		if (Ext.isEmpty(node)) {
			Ext.ux.Toast.msg('提示信息', '已是最顶层!');
			return;
		} else if (node.id == 0) {
			Ext.ux.Toast.msg('提示信息', '已是最顶层!');
			return;
		}
		var parentNode = node.parentNode;
		var store = this.dataView.getStore();
		store.baseParams.isSearch = false;
		store.reload({
					params : {
						folderId : parentNode.id
					}
				});
		this.selectNode = parentNode;
		this.selectNodeMethod(parentNode);
		this.changePath();
	},
	/**
	 * 顶级目录
	 */
	topFolder : function() {
		var store = this.dataView.getStore();
		store.baseParams.isSearch = false;
		store.reload({
					params : {
						folderId : 0
					}
				});
		this.selectNode = this.treePanel.getNodeById(0);
		this.selectNodeMethod(this.selectNode);
		this.changePath();
	},
	/**
	 * 选中节点
	 */
	selectNodeMethod : function() {
		if (this.selectNode) {
			this.selectNode = this.treePanel.getNodeById(this.selectNode.id);
			this.selectNode.select();
		}
	},
	/**
	 * 刷新
	 */
	refresh : function() {
		this.treePanel.root.reload();
		this.dataView.getStore().reload();
	},
	// ======DataView相关操作===
	/**
	 * 在空白地区-右键菜单
	 * 
	 * @param {}
	 *            p
	 */
	bodyRender : function(p) {
		p.getEl().on('contextmenu', this.bodyContextClick, this);
	},
	bodyContextClick : function(e, target, o) {
		if (target.id != 'private') {
			return;
		}
		var menuItems = [{
					text : '上一级目录',
					scope : this,
					disabled : this.isSearching ? true : false,
					iconCls : 'btn-up',
					handler : this.upLevel
				},{
					text : '顶级目录',
					iconCls : 'btn-superior',
					scope : this,
					disabled : this.isSearching ? true : false,
					handler : this.topFolder
				}, '-', {
					text : '新建目录',
					scope : this,
					iconCls : 'btn-add',
					disabled : this.isSearching ? true : false,
					handler : this.newFolder
				}, {
					text : '新建文档',
					scope : this,
					iconCls : 'btn-add',
					disabled : this.isSearching ? true : false,
					handler : this.newDocument
				},
				// {
				// text : '粘贴',
				// scope : this,
				// disabled:this.cuting&&!this.isSearching?false:true,
				// iconCls : 'btn-add',
				// handler : this.moveFile
				// },
				'-', {
					text : '刷新',
					scope : this,
					iconCls : 'btn-refresh',
					handler : this.refresh
				}, '-', {
					text : '属性',
					scope : this,
					iconCls : 'btn-detail',
					disabled : this.isSearching ? true : false,
					handler : this.folderDetail
				}];

		var menus = new Ext.menu.Menu({
					items : menuItems
				});
		menus.showAt(e.getXY());
	},
	/**
	 * 目录或文档-右键菜单
	 * 
	 * @param {}
	 *            dataview
	 * @param {}
	 *            index
	 * @param {}
	 *            node
	 * @param {}
	 *            e
	 */
	dbclickNode : function(dataview, index, node, e) {
		dataview.all.each(function(el) {
					dataview.deselect(el);
				});
		dataview.select(index, true);
		var treePanel = this.treePanel;
		var nodes = dataview.getSelectedNodes();
		if (nodes != '' && nodes != null && nodes != 'undefined') {
			var type = nodes[0].type;
			var fileId = nodes[0].id;

			var menuItems = [{
						text : '打开',
						iconCls : 'btn-add',
						scope : this,
						handler : this.openFile
					}, {
						text : '修改',
						iconCls : 'btn-edit',
						hidden : type == 1 ? false : true,// 修改目录
						scope : this,
						handler : this.editFile.createCallback(this)
					}, {
						text : '共享',
						scope : this,
						hidden : type == 1 ? true : false, // 文档有共享权限
						iconCls : 'btn-shared',
						handler : this.shareDocument
					}, {
						text : '删除',
						scope : this,
						iconCls : 'btn-delete',
						handler : type == 1
								? (this.delFolder.createCallback(this,
										dataview, fileId, treePanel, false))
								: (this.delDocument.createCallback(dataview,
										fileId))
					}, {
						text : '属性',
						scope : this,
						iconCls : 'btn-detail',
						handler : this.fileDetail
					}];

			var menus = new Ext.menu.Menu({
						items : menuItems
					});
			menus.showAt(e.getXY());
		}
	},
	/**
	 * 剪切文件
	 */
	cutFile : function() {
		this.cuting = true;
	},
	/**
	 * 移动文件
	 */
	moveFile : function() {
		this.cuting = false;
	},
	/**
	 * 树的目录属性
	 */
	folderDetail : function() {
		if (!this.selectNode) {
			return;
		}
		new FileDetailShowWin({
					isPersonal : true,
					fileId : this.selectNode.id,
					isFolder : true
				}).show();
	},
	/**
	 * 文件的属性
	 */
	fileDetail : function() {
		var filePath = this.filePathPanel.getCmpByName('filePath');
		var nodes = this.dataView.getSelectedNodes();
		var node = nodes[0];
		var isFolder = false;
		if (node.type == 1) {// 是目录，还是文档
			isFolder = true;
		}
		new FileDetailShowWin({
					isPersonal : true,
					fileId : node.id,
					filePath : filePath.getValue(),
					isFolder : isFolder
				}).show();

	},
	/**
	 * 打开目录/文档
	 */
	openFile : function() {
		var nodes = this.dataView.getSelectedNodes();
		var node = nodes[0];// 当前选中的节点
		if (node.type == 1) {// 打开目录
			var store = this.dataView.getStore();
			store.baseParams.isSearch = false;
			store.reload({
						params : {
							folderId : node.id
						}
					});
			var treeNode = this.treePanel.getNodeById(node.id);
			treeNode.select();
			this.selectNode = treeNode;// 当前选中的目录
			this.changePath();
			this.searchEnable();
		} else {// 打开文档
			new DocumentForm({
						docId : node.id,
						scope : this,
						callback : this.refresh
					}).show();
		}

	},
	/**
	 * 编辑目录/文档
	 * 
	 * @param {}
	 *            self
	 * @param {}
	 *            isTreePanel
	 */
	editFile : function(me, isTreePanel) {
		var nodes = me.dataView.getSelectedNodes();
		if ((nodes[0] && nodes[0].type == 1) || isTreePanel == true) {// 目录
			var folderId = 0;
			var node = null;
			if (isTreePanel) {
				folderId = me.selectNode.id;
				node = me.selectNode;
			} else {
				folderId = nodes[0].id;
				node = me.selectNode;
			}
			new DocFolderForm({
						folderId : folderId,
						parentId : null,
						isShared : null,
						scope : me,
						callback : me.refresh
					}).show();
		} else {// 文档
			new DocumentForm({
						docId : nodes[0].id,
						scope : me,
						callback : me.refresh
					}).show();
		}
	},
	shareDocument : function() {
		var view = this.dataView;
		var nodes = view.getSelectedNodes();
		if (nodes[0].type != 1) {
			new DocumentSharedForm({
						docId : nodes[0].id,
						scope : this,
						callback : function(){
							view.getStore().reload()
						}
					}).show();
		}
	},
	/**
	 * 删除目录
	 * 
	 * @param {}
	 *            self
	 * @param {}
	 *            dataview
	 * @param {}
	 *            fileId
	 * @param {}
	 *            treePanel
	 * @param {}
	 *            isTreePanel
	 */
	delFolder : function(self, dataview, fileId, treePanel, isTreePanel) {
		var selNode = self.selectNode;
		Ext.Msg.confirm('删除操作', '你确定删除该目录吗?', function(btn) {
					if (btn == 'yes') {
						Ext.Ajax.request({
									url : __ctxPath
											+ '/document/removeDocFolder.do',
									params : {
										folderId : fileId
									},
									method : 'post',
									success : function(result, request) {
										var res = Ext.util.JSON
												.decode(result.responseText);
										if (res.success == false) {
											Ext.ux.Toast.msg('操作信息',
													res.message);
										} else {
											// dataview.getStore().reload();
											treePanel.root.reload();
											if (isTreePanel
													&& selNode.parentNode) {
												self
														.clickNode(selNode.parentNode);
											} else {
												self.clickNode(selNode);
											}
											Ext.ux.Toast.msg('操作信息', '成功删除目录！');
										}
									},

									failure : function(result, request) {
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
	},
	/**
	 * 删除文档
	 * 
	 * @param {}
	 *            dataview
	 * @param {}
	 *            fileId
	 */
	delDocument : function(dataview, fileId) {
		Ext.Msg.confirm('信息确认', '您确认要删除该文档吗？', function(btn) {
					if (btn == 'yes') {
						Ext.Ajax.request({
									url : __ctxPath
											+ '/document/multiDelDocument.do',
									params : {
										ids : fileId
									},
									method : 'post',
									success : function() {
										dataview.getStore().reload({
													params : {
										// isSearch:false
													}
												});
										Ext.ux.Toast.msg("信息提示", "成功删除所选文档！");
									}
								});
					}
				});
	},
	selDoc : function(self) {
		var view = this.dataView;
		var nodes = view.getSelectedNodes();
		// this.selectNode = nodes[0];
		//if (nodes[0].type == 1) {// 目录
			// var store = view.getStore();
			// store.baseParams.isSearch=false;
			// store.reload({
			// params : {
			// folderId : nodes[0].id
			// }
			// });
			// var node=this.treePanel.getNodeById(nodes[0].id);
			// node.select();
			// this.selectNode=node;
			// this.changePath();
			// this.searchEnable();
			// this.selectedDocId = nodes[0].id;
			// alert(this.selectedDocId);
		//} else {
			// new DocumentForm({
			// docId : nodes[0].id
			// }).show();

		//}
	}
});