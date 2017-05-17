Ext.ns('OnlineDocumentManageView');
/**
 * @author zxh
 * @class PrivateDocumentView
 * @extends Ext.Panel
 * @description 在线文档管理
 */
OnlineDocumentManageView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		// 初始化组件
		this.initUI();
		OnlineDocumentManageView.superclass.constructor.call(this, {
					title : '在线文档管理',
					id : 'OnlineDocumentManageView',
					iconCls : 'menu-onlinedoc',
					layout : 'border',
					region : 'center',
					items : [this.treePanel, this.rightPanel]

				});
	},
	// 初始化组件
	initUI : function() {

		// 左边的目录树
		this.treePanel = new htsoft.ux.TreePanelEditor({
					region : 'west',
					layout : 'fit',
					collapsible : true,
					split : true,
					width : 200,
					title : '在线文档目录',
					url : __ctxPath + '/document/onlineTreeDocFolder.do',
					scope : this,
					autoScroll : true,
					// 点击分类树节点
					onclick : this.clickNode
				});
		// 树的右键菜单的
		this.treePanel.on('contextmenu', this.treeContextMenu, this);

		// 查询面板
		this.searchPanel = new HT.SearchPanel({
					layout : 'form',
					region : 'north',
					colNums : 5,
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
								// fieldLabel : '作者',
								// xtype : 'textfield',
								// name : 'author'
								// }, {
								// fieldLabel : '关键字',
								// xtype : 'textfield',
								// name : 'keywords'
								// }, {
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
				'<li class="thumb-wrap" style="line-height: 2em" id="{fileId}" name="{fileName}" type="{isFolder}" fileType="{fileType}" rightmod="{rightMod}" rightdel="{rightDel}" ',
				'ext:qtip="',
				'<div>名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称:{fileName}</div>',
				'<div>类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型:{fileType}</div>',
				'{[(values.isFolder==0?("<div>文件大小:"+values.fileSize+"</div>',
				'<div>作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;者:"+values.author+"</div>',
				'<div>关&nbsp;键&nbsp;&nbsp;字:"+values.keywords+"</div>',
				'<div>更新时间:"+values.updateTime+"</div>"):(""))]}"',
				' onselect= "document.selection.empty()">',
				'<img width="64" height="64" src="'
						+ __ctxPath
						+ '/images/flag/document/{[(values.isFolder==0?(values.fileType+".jpg"):"folder.png")]}" />',
				'<div><strong style="font-size:15px;overflow:hidden;">{fileName}</strong></div>',
				// '{[values.isFolder == 0 ? "<span><a href=#>下载</a></span>"
				// : ""]}',
				'</li>', '</tpl>', '</ul>');
		// 数据展示
		this.dataView = new HT.DataView({
					id : 'phones',
					region : 'center',
					border : false,
					url : __ctxPath + '/document/onlineListDocFolder.do',
					fields : ['fileId', 'fileName', 'fileSize', 'fileType',
							'isFolder', {
								name : 'parentId',
								type : 'int'
							}, 'parentName', 'isShared', 'rightRead',
							'rightMod', 'rightDel', 'author', 'keywords',
							'updateTime'],

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
								id : 'NewOnlineFolderButton',
								handler : this.newFolder
							},'-', {
								xtype : 'button',
								text : '新建DOC文档',
								scope : this,
								id : 'NewOnlineDocumentButton',
								iconCls : 'btn-word',
								handler : this.newDocument.createDelegate(this,
										['doc'])
							},'-', {
								xtype : 'button',
								text : '新建EXL文档',
								scope : this,
								id : 'NewOnlineEXCELButton',
								iconCls : 'btn-excel',
								handler : this.newDocument.createDelegate(this,
										['xls'])
							},'-', {
								xtype : 'button',
								text : '新建PPT文档',
								scope : this,
								id : 'NewOnlinePPTButton',
								iconCls : 'btn-powerpoint',
								handler : this.newDocument.createDelegate(this,
										['ppt'])
							},'-', {
								xtype : 'button',
								text : '上一级目录',
								iconCls : 'btn-up',
								id : 'UpOnlineFolderButton',
								scope : this,
								handler : this.upLevel
							},'-', {
								xtype : 'button',
								text : '顶级目录',
								iconCls : 'btn-superior',
								id : 'TopOnlineFolderButton',
								scope : this,
								handler : this.topFolder
							},'-', {
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
								value : '/在线文档目录'
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
					title : '在线文档列表',
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
		var folderId = node.id;
		this.changePath();// 改变显示的路径
		var store = this.dataView.getStore();
		store.baseParams.isSearch = false;
		if (folderId != null) {
			if (folderId == 0) {
				this.rightPanel.setTitle('所有在线文档列表');
			} else {
				this.rightPanel.setTitle('[' + node.text + ']' + '在线文档列表');
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
	 * 树的右键菜单
	 * 
	 * @param {}
	 *            node
	 * @param {}
	 *            e
	 */
	treeContextMenu : function(node, e) {
		this.clickNode(node);
		Ext.Ajax.request({
			url : __ctxPath + '/document/knowledgeDocFolder.do',
			method : 'post',
			params : {
				folderId : node.id,
				sort : 'isFolder',
				dir : 'DESC'
			},
			scope : this,
			success : function(response, options) {
				var out = Ext.util.JSON.decode(response.responseText);
				if (out.success) {
					// 设置值
					var rightMod = 0;
					var rightDel = 0;
					var isAll = false;
					if (curUserInfo.rights.indexOf('__ALL') != -1)
						isAll = true;
					if (out.result[0]) {
						rightMod = out.result[0].rightMod;
						rightDel = out.result[0].rightDel;
					} else if (node.id == 0) {
						rightMod = 1;
						rightDel = 1;
					}
					// 设置右键菜单
					var menuItems = [
							{
								text : '新建',
								iconCls : 'btn-add',
								scope : this,
								hidden : (rightMod == 1 || isAll)
										? false
										: true,
								handler : this.newFolder
							},
							{
								text : '修改',
								iconCls : 'btn-edit',
								hidden : ((rightMod == 1 || isAll) && node.id != 0)
										? false
										: true,
								scope : this,
								handler : this.editFile.createDelegate(this,
										[true])
							},
							{
								text : '删除',
								iconCls : 'btn-delete',
								hidden : ((rightDel == 1 || isAll) && node.id != 0)
										? false
										: true,
								scope : this,
								handler : this.delFolder.createCallback(this,
										this.dataView, this.selectNode.id,
										this.treePanel, true)
							},
							(isGranted('_KnowledgeManageGrant') && node.id != 0)
									? '-'
									: '', {
								text : '授权',
								iconCls : 'btn-shared',
								scope : this,
								hidden : (isGranted('_KnowledgeManageGrant') && node.id != 0)
										? false
										: true,
								handler : this.rightFolder.createDelegate(this,
										[true])
							}, '-', {
								text : '属性',
								scope : this,
								iconCls : 'btn-detail',
								handler : this.folderDetail
							}]

					var menus = new Ext.menu.Menu({
								items : menuItems
							});
					menus.showAt(e.getXY());

				}

			}
		});

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
		Ext.getCmp('NewOnlineFolderButton').disable();
		Ext.getCmp('NewOnlineDocumentButton').disable();
		Ext.getCmp('NewOnlineEXCELButton').disable();
		Ext.getCmp('NewOnlinePPTButton').disable();
		Ext.getCmp('UpOnlineFolderButton').disable();
		Ext.getCmp('TopOnlineFolderButton').disable();
		this.isSearching = true;
	},
	/**
	 * 查询状态-恢复操作
	 */
	searchEnable : function() {
		Ext.getCmp('NewOnlineFolderButton').enable();
		Ext.getCmp('NewOnlineDocumentButton').enable();
		Ext.getCmp('NewOnlineEXCELButton').enable();

		Ext.getCmp('NewOnlinePPTButton').enable();
		Ext.getCmp('UpOnlineFolderButton').enable();
		Ext.getCmp('TopOnlineFolderButton').enable();
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
		new DocFolderForm({// 表示增加公共文件夹
			folderId : null,
			parentId : parentId,
			isShared : 2,
			scope : this,
			callback : this.refresh
		}).show();
	},
	/**
	 * 新建文档
	 */
	newDocument : function(docType) {
		//判断是不是ie
		if (!Ext.isIE&&!Ext.isChrome&&!Ext.isGecko&&!Ext.isGecko2&&!Ext.isGecko3) {
			setTimeout(function() {
						Ext.MessageBox.show({
									title : '操作信息',
									msg : 'office插件必需使用32位ie、firefox和chrome浏览器浏览！',
									buttons : Ext.MessageBox.OK,
									icon : 'ext-mb-warning',
									fn : function() {
										return;
									}
								});
					}, 500);
			return;
		}
		var nodes = this.dataView.getSelectedNodes();
		var folderId = null;
		var folderName = null;
		if (nodes != '' && nodes[0].type == 1) {
			folderId = nodes[0].id;
			folderName = nodes[0].getAttribute('name')
		} else if (nodes == '' && this.selectNode && this.selectNode.id != 0) {
			folderId = this.selectNode.id
			folderName = this.selectNode.text;
		}

		new OnlineDocumentForm({
					docType : docType,
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
		this.selectNodeMethod(this.selectNode);
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
		if (target.id != 'phones') {
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
					text : '新建DOC文档',
					scope : this,
					iconCls : 'btn-add',
					disabled : this.isSearching ? true : false,
					handler : this.newDocument.createDelegate(this,
							['doc'])
				}, {
					text : '新建EXL文档',
					scope : this,
					iconCls : 'btn-add',
					disabled : this.isSearching ? true : false,
					handler : this.newDocument.createDelegate(this,
							['xls'])
				}, {
					text : '新建PPT文档',
					scope : this,
					iconCls : 'btn-add',
					disabled : this.isSearching ? true : false,
					handler : this.newDocument.createDelegate(this,
							['ppt'])
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
		if (!Ext.isEmpty(node)) {
			var type = node.attributes.type.value;
			var fileId = node.attributes.id.value;
			var rightMod = node.attributes.rightmod.value;
			var rightDel = node.attributes.rightdel.value;
			var isAll = false;
			if (curUserInfo.rights.indexOf('__ALL') != -1)
				isAll = true;

			var menuItems = [{
						text : '打开',
						iconCls : 'btn-add',
						scope : this,
						handler : this.openFile
					}, {
						text : '修改',
						iconCls : 'btn-edit',
						hidden : (rightMod == 1 || isAll) ? false : true,// 有权限并且类型是目录的
						// 有修改
						scope : this,
						handler : this.editFile.createDelegate(this, [false])
					}, {
						text : '删除',
						scope : this,
						iconCls : 'btn-delete',
						hidden : (rightDel == 1 || isAll) ? false : true, // 文档有权限
						handler : (type == 1)
								? (this.delFolder.createCallback(this,
										dataview, fileId, treePanel, false))
								: (this.delDocument.createCallback(dataview,
										fileId))
					}, {
						text : '授权',
						scope : this,
						hidden : (isGranted('_KnowledgeManageGrant') && type == 1)
								? false
								: true, // 目录有授权
						iconCls : 'btn-shared',
						handler : this.rightFolder
								.createDelegate(this, [false])
					}, '-', {
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
					fileId : this.selectNode.id,
					isFolder : true,
					isOnline : true
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
					fileId : node.id,
					filePath : filePath.getValue(),
					isFolder : isFolder,
					isOnline : true
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
			if (!Ext.isIE&&!Ext.isChrome&&!Ext.isGecko&&!Ext.isGecko2&&!Ext.isGecko3) {
				setTimeout(function() {
							Ext.MessageBox.show({
										title : '操作信息',
										msg : 'office插件必需使用32位ie、firefox和chrome浏览器浏览！',
										buttons : Ext.MessageBox.OK,
										icon : 'ext-mb-warning',
										fn : function() {
											return;
										}
									});
						}, 500);
				return;
			}
			new OnlineDocumentDetail({
						docId : node.id
					}).show();
			// new OnlineDocumentForm({
			// docId : node.id,
			// docType : node.fileType,
			// scope : this,
			// callback : this.refresh
			// }).show();
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
	editFile : function(isTreePanel) {
		var nodes = this.dataView.getSelectedNodes();
		if ((nodes[0] && nodes[0].type == 1) || isTreePanel == true) {// 目录
			var folderId = 0;
			if (isTreePanel) {
				folderId = this.selectNode.id;
			} else {
				folderId = nodes[0].id;
			}
			new DocFolderForm({
						folderId : folderId,
						parentId : null,
						isShared : null,
						scope : this,
						callback : function() {
							this.refresh();
							if (isTreePanel) {
								this.selectNode.select();
								this.changePath();
							}
						}

					}).show();
		} else {// 文档
			if (!Ext.isIE&&!Ext.isChrome&&!Ext.isGecko&&!Ext.isGecko2&&!Ext.isGecko3) {
				setTimeout(function() {
							Ext.MessageBox.show({
										title : '操作信息',
										msg : 'office插件必需使用32位ie、firefox和chrome浏览器浏览！',
										buttons : Ext.MessageBox.OK,
										icon : 'ext-mb-warning',
										fn : function() {
											return;
										}
									});
						}, 500);
				return;
			}
			new OnlineDocumentForm({
						docId : nodes[0].id,
						docType : nodes[0].fileType,
						scope : this,
						callback : this.refresh
					}).show();
		}
	},
	/**
	 * 目录授权
	 */
	rightFolder : function(isTreePanel) {
		var folderId = 0;
		var folderName = null;
		if (isTreePanel) {
			var node = this.treePanel.getNodeById(this.selectNode.id);
			folderId = node.id;
			folderName = node.text;
		} else {
			var nodes = this.dataView.getSelectedNodes();
			folderId = nodes[0].id;
			folderName = nodes[0].attributes.name.value;
		}
		if (folderId > 0 && !Ext.isElement(folderName)) {
			new KnowledgePrivilegeWin({
						folderId : folderId,
						folderName : folderName
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
	delFolder : function(self, dataview, folderId, treePanel, isTreePanel) {
		var selNode = self.selectNode;
		Ext.Msg.confirm('删除操作', '你确定删除该目录吗?', function(btn) {
					if (btn == 'yes') {
						Ext.Ajax.request({
									url : __ctxPath
											+ '/document/removeDocFolder.do',
									params : {
										folderId : folderId
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
	delDocument : function(dataview, folderId) {
		Ext.Msg.confirm('信息确认', '您确认要删除该文档吗？', function(btn) {
					if (btn == 'yes') {
						Ext.Ajax.request({
									url : __ctxPath
											+ '/document/multiDelDocument.do',
									params : {
										ids : folderId
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
		// if (nodes[0].type == 1) {// 目录
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
		// } else {
		// new OnlineDocumentForm({
		// docId : nodes[0].id
		// }).show();

		// }
	}
});