/**
 * 分类管理
 * 
 * @class ProModalManager
 * @extends Ext.Panel
 */
DicManager = Ext.extend(Ext.Panel, {
			// 构造方法
			constructor : function(conf) {
				Ext.applyIf(this, conf);
				this.initUI();
				DicManager.superclass.constructor.call(this, {
							id : 'DicManager',
							height : 450,
							autoScroll : true,
							layout : 'border',
							title : '数据字典分类管理',
							iconCls : "menu-dictionary",
							items : [this.leftPanel, this.gridPanel]
						});
			},
			// 初始化组件
			initUI : function() {
				this.treePanel = new htsoft.ux.TreePanelEditor({
							height : 450,
							width : 160,
							autoScroll : true,
							url : __ctxPath
									+ '/system/treeGlobalType.do?catKey=DIC',
							scope : this,
							onclick : this.treeNodeClick// 点击树
//							contextMenuItems : [{
//										text : '新建分类',
//										scope : this,
//										iconCls : 'btn-add',
//										handler : this.addType
//									}, {
//										text : '修改分类',
//										scope : this,
//										iconCls : 'btn-edit',
//										handler : this.editType
//									}, {
//										text : '添加字典项',
//										scope : this,
//										iconCls : 'btn-add',
//										handler : this.addDictionary,
//										hidden : !isGranted('_DictionaryAdd')
//									}]
						});
				// 给树添加右击事件
				this.treePanel.on('contextmenu', this.contextmenu, this);
				this.leftPanel = new Ext.Panel({
							region : 'west',
							title : '数据字典分类',
							layout : 'fit',
							collapsible : true,
							split : true,
							border : false,
							width : 200,
							items : [this.treePanel]
						});

				var row = 0;
				// 分类列表
				this.gridPanel = new HT.EditorGridPanel({
							region : 'center',
							title : '数字字典管理',
							tbar : [{
										text : '添加数字字典',
										iconCls : 'btn-add',
										scope : this,
										handler : this.addDictionary,
										hidden : !isGranted('_DictionaryAdd')
									}, '-', {
										xtype : 'button',
										text : '保存',
										iconCls : 'btn-save',
										scope : this,
										handler : this.saveDictionary,
										hidden : !isGranted('_DictionaryEdit')
									}, '-', {
										text : '删除',
										iconCls : 'btn-del',
										scope : this,
										handler : this.delDictionary,
										hidden : !isGranted('_DictionaryDel')
									}, '-', {
										text : '转移类型',
										iconCls : 'btn-up',
										scope : this,
										handler : this.dicTypeChange
									}],
							clicksToEdit : 1,
							// rowActions : true,
							url : __ctxPath + "/system/listDictionary.do",
							// baseParams : {
							// parentId : 0
							// },
							fields : ['dicId', 'itemName', 'itemValue',
									'descp', 'sn', 'globalType'],
							columns : [{
										header : 'parentId',
										dataIndex : 'globalType',
										hidden : true,
										renderer : function(globalType) {
											if (globalType)
												return globalType.parentId;
											else
												return 0;
										}
									}, {
										header : 'proTypeId',
										dataIndex : 'globalType',
										hidden : true,
										renderer : function(globalType) {
											if (globalType)
												return globalType.proTypeId;
										}
									}, {
										header : '分类名称',
										dataIndex : 'globalType',
										renderer : function(globalType) {
											if (globalType)
												return globalType.typeName;
										}
									}, {
										header : '值',
										dataIndex : 'itemValue',
										editor : new Ext.form.TextField({
													allowBlank : false
												})
									}, {
										header : '描述',
										dataIndex : 'descp',
										editor : new Ext.form.TextField()
									}, {
										header : '序号',
										dataIndex : 'sn',
										editor : new Ext.form.NumberField()
									}],
							defaults : {
								sortable : true,
								menuDisabled : false,
								width : 100
							}
						});
				// 行选择监听
				this.gridPanel.on('cellclick', function(grid, rowIndex,
								columnIndex, e) {
							row = rowIndex;
						});

			},// end of initUIComponents
			// 点击节点树
			treeNodeClick : function(node) {
				this.selectNode = node;;
				var selectNode = this.selectNode;
				var parentId = selectNode.id;
				var title = selectNode.text;
				var grid = this.gridPanel;
				if (grid == null)
					return false;
				var store = grid.getStore();
				if (parentId == 0)
					grid.setTitle('所有数据字典');
				else
					grid.setTitle(title + '-数据字典');
				store.baseParams = {
					parentId : parentId
				};
				this.gridPanel.getBottomToolbar().moveFirst();
			},
			// 重新加载分类信息
			reloadType : function() {
				this.treePanel.root.reload();
				// 刷新gridPanel
				this.gridPanel.getStore().reload();
			},
			// 增加分类
			addType : function() {
				var parentId = this.selectNode.id;
				new GlobalTypeForm({
							parentId : parentId,
							catKey : 'DIC',
							scope : this,
							callback : this.reloadType
						}).show();
			},
			// 修改分类
			editType : function() {
				var proTypeId = this.selectNode.id;
				new GlobalTypeForm({
							proTypeId : proTypeId,
							scope : this,
							callback : this.reloadType
						}).show();
			},
			// 新增数字字典
			addDictionary : function() {
				var selectedNode = this.selectNode;
				if (!selectedNode) {
					Ext.ux.Toast.msg('操作信息', '请选择左侧树中的字典分类！');
					return;
				}
				var parentId = 0;
				if (selectedNode != null) {
					parentId = selectedNode.id;
				}
				if (parentId == 0) {
					Ext.ux.Toast.msg('操作信息', '不能选择总分类，请重新选择字典分类！');
					return;
				}
				new DictionaryForm({
							parentId : parentId,
							typeName : selectedNode.text,
							scope : this,
							callback : this.reloadType
						}).show();
			},
			// 保存数字字典
			saveDictionary : function() {
				var gridPanel = this.gridPanel;
				var store = gridPanel.getStore();
				var params = [];
				for (var i = 0; i < store.getCount(); i += 1) {
					var record = store.getAt(i);
					if (record.dirty) // 得到所有修改过的数据
						params.push(record.data);
				}
				if (params.length == 0) {
					Ext.ux.Toast.msg('信息', '没有对数据进行任何更改');
					return;
				}
				Ext.Ajax.request({
							method : 'post',
							url : __ctxPath + '/system/mulSaveDictionary.do',
							params : {
								data : Ext.encode(params)
							},
							scope : this,
							success : function(request) {
								Ext.ux.Toast.msg('操作信息', '成功设置');
								gridPanel.getView().refresh();
								this.reloadType.call(this);
							},
							failure : function(request) {
								Ext.ux.Toast.msg('操作信息', '设置出错，请联系管理员!');
							}
						});

			},
			// 删除数字字典
			delDictionary : function() {
				var selectRecords = this.gridPanel.getSelectionModel()
						.getSelections();
				if (selectRecords.length == 0) {
					Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
					return;
				}
				var ids = Array();
				for (var i = 0; i < selectRecords.length; i++) {
					ids.push(selectRecords[i].data.dicId);
				}
				Ext.Msg.confirm('信息确认', '您确认要删除所选记录吗？', function(btn) {
							if (btn == 'yes') {
								Ext.Ajax.request({
											url : __ctxPath
													+ '/system/multiDelDictionary.do',
											params : {
												ids : ids
											},
											method : 'POST',
											scope : this,
											success : function(response,
													options) {
												Ext.ux.Toast.msg('操作信息',
														'成功删除选中的数字字典！');
												this.reloadType.call(this);
											},
											failure : function(response,
													options) {
												Ext.ux.Toast.msg('操作信息',
														'操作出错，请联系管理员！');
											}
										});
							}
						}, this);
			},
			// 转义类型
			dicTypeChange : function() {
				var gridPanel = this.gridPanel;
				var rows = gridPanel.getSelectionModel().getSelections();
				var dicIds = '';
				if (rows.length == 0) {
					Ext.ux.Toast.msg('操作信息', '请选择记录!');
					return;
				}
				for (var i = 0; i < rows.length; i++) {
					if (i > 0) {
						dicIds += ',';
					}
					dicIds += rows[i].data.dicId;
				}

				new DicTypeChangeWin({
							dicIds : dicIds,
							scope : this,
							callback : this.reloadType
						}).show();
			},
			// 节点右击事件
			contextmenu : function(node, e) {
					this.treeNodeClick(node);
					// 创建右键菜单
					var treeMenu = new Ext.menu.Menu({
								items : []
							});
					treeMenu.clearMons();
					treeMenu.add({
								text : '新增分类',
								iconCls : 'btn-add',
								handler : this.addType,
								scope : this
							});
					if (node.id > 2016) { // 总分类不能删除，和修改
						treeMenu.add({
									text : '修改分类',
									iconCls : 'btn-edit',
									handler : this.editType,
									scope : this
								}, {
									text : '删除分类',
									iconCls : 'btn-del',
									handler : this.delType,
									scope : this
								});
					}
					treeMenu.add({
									text : '添加字典项',
									scope : this,
									iconCls : 'btn-add',
									handler : this.addDictionary,
									hidden : !isGranted('_DictionaryAdd')
								});
					treeMenu.showAt(e.getXY());
			},
			delType : function(){
				var proTypeId = this.selectNode.id;
				$postDel({
					msg : '注意：删除该分类将会删除其下所有的子分类，您确认要删除所选分类吗？',
					url : __ctxPath + '/system/DelTypeDICGlobalType.do',
					ids : proTypeId,
					scope : this,
					callback :function(){
						var grid = this.gridPanel;
						grid.setTitle('所有数据字典');
						grid.getStore().baseParams = {
							parentId : "0"
						};
						grid.getBottomToolbar().moveFirst();
						this.treePanel.root.reload();
					}
				});
			}
		});