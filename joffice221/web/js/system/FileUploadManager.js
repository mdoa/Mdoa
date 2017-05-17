/**
 * @description 文件分类上传管理
 * @author YHZ
 * @company www.jee-soft.cn
 * @datetime 2010-11-15 AM
 */
FileUploadManager = Ext.extend(Ext.Window, {
	// 构造器
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		this.fileType = this.permitted_extensions;// 
		this.isImage = this.judgeImage(this.fileType); // 判断附件类型[是否为图片]
		this.initUIComponent();
		FileUploadManager.superclass.constructor.call(this, {
					id : 'fileUploadManager',
					layout : 'fit',
					title : this.isImage == true ? '图片分类管理' : '附件分类管理',
					iconCls : 'menu-file',
					width : 720,
					minWidth : 720,
					height : 550,
					minHeight : 550,
					maximizable : true,
					border : false,
					modal : true,
					items : [this.panel],
					buttonAlign : 'center',
					buttons : [{
								text : '确定',
								iconCls : 'btn-ok',
								scope : this,
								handler : this.submit
							}, {
								text : '取消',
								iconCls : 'btn-cancel',
								scope : this,
								handler : this.close
							}]
				}); // end of this constructor
	},
	// 初始化组件
	initUIComponent : function() {
		// ##panel##//
		this.panel = new Ext.Panel({
					iconCls : 'menu-find-doc',
					layout : 'border',
					region : 'center',
					border : false
				});
		// /////////////////////##treePanel##//////////////////////////////
		// 分类树
		this.treePanel = new htsoft.ux.TreePanelEditor({
					region : 'west',
					title : this.isImage == true ? '图片分类' : '附件分类',
					collapsible : true,
					autoScroll : true,
					split : true,
					width : 180,
					url : __ctxPath
							+ '/system/treeGlobalType.do?catKey=ATTACHFILE_TYPE',
					scope : this,
					onclick : this.nodeClick
				});
		this.treePanel.on('contextmenu', this.onContextmenu, this);

		// 在panel增加左边树
		this.panel.add(this.treePanel);
		// //////////////end of this treePanel//////////////////////////

		// //////////////////////////##gridPanel##/////////////////////
		// 顶部按钮
		this.topbar = new Ext.Toolbar({
					height : 30,
					defaultType : 'button',
					items : [
							// {
							// text : 'Ext版上传',
							// iconCls : 'btn-upload',
							// handler : this.upLoadFile,
							// scope : this
							// },
							'-', {
								text : 'flex版上传',
								iconCls : 'btn-upload',
								handler : this.flexUploadFile,
								scope : this
							}, {
								xtype : 'hidden',
								id : 'fileIsImage', // 保存上传文件的格式[是图片true]
								value : this.isImage
							}, '-', {
								xtype : 'label',
								id : 'fileType',
								text : '总分类'
							}, '-', {
								xtype : 'hidden',
								id : 'fileCat',
								value : this.file_cat == null
										? 'others'
										: this.file_cat
							}]
				}); // end of this topbar
		//
		Ext.Ajax.request({
					url : __ctxPath
							+ "/system/getTypeNameGlobalType.do?nodeKey="
							+ this.file_cat,
					scope : this,
					success : function(result, request) {
						var res = Ext.util.JSON.decode(result.responseText);
						if (res.success == false) {
							Ext.getCmp('fileType').setText('总分类')
						} else {
							if (!Ext.isEmpty(res.typeName)) {
								Ext.getCmp('fileType').setText(res.typeName);
							} else {
								Ext.getCmp('fileType').setText('总分类');
							}
						}
					},
					failure : function(result, request) {
					}
				});

		if (!this.isImage) {// 列表展示附件
			this.gridPanel = new HT.GridPanel({
						id : 'fileUploadManagerGridPanel',
						region : 'center',
						tbar : this.topbar,
						rowActions : true,
						url : __ctxPath + '/system/listFileAttach.do',
						fields : [{
									name : 'fileId',
									type : 'int'
								}, {
									name : 'fileName',
									mapping : 'fileName'
								}, 'ext', 'note', 'fileType', 'filePath',
								'createtime', 'totalBytes'],
						columns : [{
									header : 'fileId',
									dataIndex : 'fileId',
									hidden : true
								},{
									header : 'fileType',
									dataIndex : 'fileType',
									hidden : true
								},{
									header : 'filePath',
									dataIndex : 'filePath',
									hidden : true
								},{
									header : '附件名称',
									dataIndex : 'fileName'
								}, {
									header : '上传时间',
									dataIndex : 'createtime',
									format : 'y-m-d'
								}, {
									header : '大小',
									dataIndex : 'note'
								}, new Ext.ux.grid.RowActions({
											header : '管理',
											width : 80,
											actions : [{
														iconCls : 'btn-showDetail',
														qtip : '查看',
														style : 'margin:0 3px 0 3px'
													}, {
														iconCls : 'btn-download',
														qtip : '下载',
														style : 'margin:0 3px 0 3px'
													}],
											listeners : {
												scope : this,
												'action' : this.onRowAction
											}
										})],
						listeners : {
							scope : this,
							'rowdblclick' : this.onRowdblclick
						}
					});
			this.panel.add(this.gridPanel); // 附件列表
		} else {// 图片附件
			// ///////////////##显示图片的dataView中相关组件##//////////////
			this.imageStore = new Ext.data.JsonStore({
						id : 'id',
						url : __ctxPath
								+ '/system/listFileAttach.do?type=image',
						root : 'result',
						totalProperty : 'totalCounts',
						fields : [{
									name : 'fileId',
									type : 'int'
								}, {
									name : 'fileName',
									mapping : 'fileName'
								}, {
									name : 'filePath',
									mapping : 'filePath'
								}]
					});
			this.imageStore.load({
						params : {
							start : 0,
							limit : 10
						}
					});

			this.tpl = new Ext.XTemplate(
					'<tpl for=".">',
					'<div style="width:105px; height : 105px;" class="thumb-wrap" id="{fileId}">',
					'<img align="middle" src="'
							+ __ctxPath
							+ '/attachFiles/{filePath}" style="width:90px;height:90px;margin-left:7px;" title="{fileName}"/>',
					'<center><span style="margin-top:3px;">{fileName}</span><center>',
					'</div>', '</tpl>'), this.dataView = new Ext.DataView({
						id : 'fileUploadManagerDataView',
						layout : 'form',
						region : 'center',
						store : this.imageStore,
						tpl : this.tpl,
						multiSelect : true,
						overClass : 'x-view-over',
						itemSelector : 'div.thumb-wrap',
						bodyStyle : 'padding:4px',
						emptyText : '目前尚无记录',
						listeners : {
							'dblclick' : {
								fn : this.imageDbClick.createCallback(this),
								scope : this
							}
						}
					}); // end of this dataView

			// 图片展示,添加显示数据组件
			this.dataPanel = new Ext.Panel({
						layout : 'form',
						region : 'center',
						tbar : this.topbar,
						layout : 'fit',
						defaults : {
							anchor : '96%,96%'
						},
						items : this.dataView,
						bbar : new Ext.PagingToolbar({
									pageSize : 10,
									store : this.imageStore,
									displayInfo : true,
									displayMsg : '当前显示从{0}至{1}， 共{2}条记录',
									emptyMsg : "当前没有记录"
								})
					}); // end of this dataPanel

			this.panel.add(this.dataPanel); // 图片展示
		}
		// 重新布局
		this.panel.doLayout();
	}, // end of this initUIComponent
	/**
	 * @desription 判断是否为图片，true:图片
	 * @remark 图片格式:jpg|gif|jpeg|png|bmp|JPG|GIF|JPEG|PNG|BPM
	 * @param {}
	 *            types
	 */
	judgeImage : function(types) {
		// var type = this.permitted_extensions;
		// 图片格式:jpg|gif|jpeg|png|bmp|JPG|GIF|JPEG|PNG|BPM
		if (!Ext.isEmpty(types)) {
			for (var i = 0; i < types.length; i++) {
				var type = types[i].toLowerCase();
				if (type == 'bmp' || type == 'png' || type == 'jpeg'
						|| type == 'jpg' || type == 'tiff' || type == 'gif') { // 上传image图片
					return true;
				}
			}
		}
		return false;
	},
	/**
	 * 节点单击事件
	 * 
	 * @param {}
	 *            node
	 */
	nodeClick : function(node) {
		if (node != null) {
			var file_type = '';
			if (node.getDepth() > 1 && node.attributes.nodeKey != 'file-type') {
				file_type = node.attributes.nodeKey;
			}
			var nodeLabel = '';
			var nodeKey = '';
			node.bubble(function(node) {
						if (node.text != undefined) {
							if (node.text == '总分类') {
								nodeLabel = '总分类/' + nodeLabel;
							} else {
								nodeLabel = node.text + '/' + nodeLabel;
								nodeKey = node.attributes.nodeKey + '/'
										+ nodeKey;
							}
						}
					});
			nodeLabel = nodeLabel.substring(0, nodeLabel.length - 1);
			Ext.getCmp('fileType').setText("/" + nodeLabel);
			nodeKey = nodeKey.substring(0, nodeKey.length - 1);
			Ext.getCmp('fileCat').setValue(nodeKey);
			Ext.getCmp('fileCat').setValue(file_type);
			// 刷新视图
			this.reloadView(file_type);
		}

	},
	/**
	 * 刷新视图
	 * 
	 * @param {}
	 *            fileType
	 */
	reloadView : function(fileType) {
		if (fileType != null && fileType == 'others') {
			fileType = null;
		}
		if (this.isImage) { // 图片
			var store = this.dataView.getStore();
			store.url = __ctxPath + '/system/listFileAttach.do?type=image';
			store.reload({
						params : {
							start : 0,
							limit : 10,
							type : 'image',
							fileType : fileType
						}
					});
		} else { // 非图片
			var store = this.gridPanel.getStore();
			store.url = __ctxPath + '/system/listFileAttach.do';
			store.reload({
						params : {
							start : 0,
							limit : 25,
							type : 'file',
							fileType : fileType
						}
					});
		}
	},
	/**
	 * 右键菜单
	 * 
	 * @param {}
	 *            node
	 * @param {}
	 *            e
	 */
	onContextmenu : function(node, e) {
		// 只有私有的才可以，修改，删除操作
		if (node.attributes.isPublic == 'false' || node.id == '0') {
			this.selectedNode = new Ext.tree.TreeNode({
						id : node.id,
						text : node.text
					});
			// 创建右键菜单
			var treeMenu = new Ext.menu.Menu({
						items : []
					});
			treeMenu.clearMons();
			treeMenu.add({
						text : '新增',
						iconCls : 'btn-add',
						scope : this,
						handler : this.addNode
					});
			if (node.id > 0) { // 总分类不能删除，和修改
				treeMenu.add({
							text : '修改',
							iconCls : 'btn-edit',
							scope : this,
							handler : this.editNode
						}, {
							text : '删除',
							iconCls : 'btn-del',
							scope : this,
							handler : this.delNode
						});
			}
			treeMenu.showAt(e.getXY());
		}
	},
	/**
	 * 分类树添加节点
	 */
	addNode : function() {
		var treePanel = this.treePanel;
		var parentId = this.selectedNode.id;
		var globalTypeForm = new GlobalTypeForm({
					parentId : parentId,
					catKey : 'ATTACHFILE_TYPE',
					callback : function() {
						treePanel.root.reload();
					}
				});
		globalTypeForm.show();
	},

	/**
	 * 分类树编辑节点
	 */
	editNode : function() {
		if (!Ext.isEmpty(this.selectedNode)) {
			var treePanel = this.treePanel;
			var proTypeId = this.selectedNode.id;
			var globalTypeForm = new GlobalTypeForm({
						proTypeId : proTypeId,
						callback : function() {
							treePanel.root.reload();
						}
					});
			globalTypeForm.show();
		}
	},

	/**
	 * 分类树删除节点
	 */
	delNode : function() {
		if (!Ext.isEmpty(this.selectedNode)) {
			var treePanel = this.treePanel;
			var proTypeId = this.selectedNode.id;
			Ext.Msg.confirm('操作提示', '你确定删除该数据?', function(btn) {
				if (btn == 'yes') {
					Ext.Ajax.request({
						url : __ctxPath
								+ '/system/delChildrensGlobalType.do?proTypeId='
								+ proTypeId,
						success : function(result, request) {
							var res = Ext.util.JSON.decode(result.responseText);
							if (res.success == false) {
								Ext.ux.Toast.msg('操作提示', res.message);
							} else {
								Ext.ux.Toast.msg('操作提示', '删除成功!');
							}
							treePanel.root.reload();
						},
						failure : function(result, request) {
						}
					});
				}
			}, this);
		} else {
			Ext.ux.Toast.msg('请选择对应数据！', '操作提示');
		}
	},

	/**
	 * 管理
	 * 
	 * @param {}
	 *            gridPanel
	 * @param {}
	 *            record
	 * @param {}
	 *            action
	 * @param {}
	 *            row
	 * @param {}
	 *            col
	 */
	onRowAction : function(gridPanel, record, action, row, col) {
		switch (action) {
			case 'btn-showDetail' :
				this.showDetail(record.data.fileId);
				break;
			case 'btn-download' :
				this.downLoad(record.data.fileId);
				break;
			default :
				break;
		}
	},
	/**
	 * 点击行
	 * 
	 * @param {}
	 *            grid
	 * @param {}
	 *            rowIndex
	 * @param {}
	 *            e
	 */
	onRowdblclick : function(grid, rowIndex, e) {
		grid.getSelectionModel().each(function(rec) {
					this.showDetail(rec.data.fileId);
				},this);
	},
	/**
	 * 表格查看详细信息
	 */
	showDetail : function(fileId) {
			FileAttachDetail.show(fileId);
	},
	/**
	 * 文件下载
	 */
	downLoad : function(fileId) {
		window.open(__ctxPath + "/file-download?fileId=" + fileId);
	},

	/**
	 * 文件上传[flex版本]
	 */
	flexUploadFile : function() {

		var treePanel = this.treePanel;
		var node = treePanel.getSelectionModel().getSelectedNode();
		var fileTypeId = 0;
		if (node != null && node.id > 0) {
			fileTypeId = node.id;
		}
		var file_cat = Ext.getCmp('fileCat').value;
		if (Ext.isEmpty(file_cat)) {
			file_cat = this.file_cat != null ? this.file_cat : 'others'
		}
		new FlexUploadDialog({
					file_cat : file_cat,
					fileTypeId : fileTypeId,
					scope : this,
					callback : function() {
						// 刷新视图
						this.reloadView(file_cat);
					}

				}).show();

	},

	/**
	 * 文件上传
	 */
	upLoadFile : function() {
		var callback = this.callback;
		var file_cat = Ext.getCmp('fileCat').value;
		if (Ext.isEmpty(file_cat)) {
			file_cat = this.file_cat != null ? this.file_cat : 'others'
		}
		var dialog = new Ext.ux.UploadDialog.Dialog({
					file_cat : file_cat,
					url : this.url,
					scope : this,
					callback : function(obj) {
						if (obj != null && obj.length > 0) {
							this.reloadView(this.file_cat);
							if (callback != null) {
								callback.call(this, obj);
							}
						}
					}
				});
		dialog.show('queryWindow');
	},

	/**
	 * 确定
	 */
	submit : function() {
		if (this.dialog) {
			this.dialog.close();
		}
		var scope = this.scope ? this.scope : this;
		var records = null;
		if (this.isImage) { // 上传image图片
			records = this.dataView.getSelectedRecords();
		} else {
			records = this.gridPanel.getSelectionModel().getSelections();
		}
		var arr = new Array();
		if (records != null && records.length > 0) {
			for (var i = 0; i < records.length; i++) {
				arr.push(records[i].data);
			}
		}
		if (this.callback != null) {
			this.callback.call(scope, arr);
		}
		this.close();
		// Ext.getCmp('fileUploadManager').close();
	},

	/**
	 * Image图片双击事件，显示图片信息
	 */
	imageDbClick : function(self) {
		var nodes = self.dataView.getSelectedNodes();
		if (nodes != '' && nodes != null && nodes != 'undefined') {
			new FileUploadImageDetail({
						fileId : nodes[0].id
					}).show();
		}
	}
});
