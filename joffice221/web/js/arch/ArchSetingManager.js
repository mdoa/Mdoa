/**
 * 档案系统设置
 * 
 * @class ArchSetingManager
 * @extends Ext.Panel
 */
ArchSetingManager = Ext.extend(Ext.Panel, {
			constructor : function(config) {
				Ext.applyIf(this, config);
				this.initUIComponents();
				ArchSetingManager.superclass.constructor.call(this, {
							id : 'ArchSetingManager',
							iconCls : "menu-archSet",
							height : 450,
							autoScroll : true,
							layout : 'border',
							title : '档案系统设置',
							items : [this.leftPanel, this.centerPanel]
						});
			},
			initUIComponents : function() {
				//左边的树
				this.leftPanel = new htsoft.ux.TreePanelEditor({
							region : 'west',
							title : '档案管理分类',
							collapsible : true,
							split : true,
							height : 450,
							width : 160,
							autoScroll : true,
							url : __ctxPath
									+ '/system/treeGlobalType.do?catKey=DIC',
							scope : this,
							onclick : this.leftPanelClick,
							contextMenuItems : [{
										text : '新建分类',
										scope : this,
										iconCls : 'btn-add',
										
										handler : this.treeAddClick
									}, {
										text : '修改分类',
										scope : this,
										iconCls : 'btn-edit',
										handler : this.treeEditClick
									}, {
										text : '添加设置项',
										scope : this,
										iconCls : 'btn-add',
										handler : this.treeAddSettingClick
									}]
						});
				this.centerPanel = new HT.EditorGridPanel({
							region : 'center',
							title : '档案管理设置',
							tbar : [{
										text : '添加设置',
										iconCls : 'btn-add',
										scope : this,
										handler : this.tbarAddSetting
									}, '-', {
										xtype : 'button',
										text : '保存',
										iconCls : 'btn-save',
										scope : this,
										handler : this.tbarSaveClick
									}, '-', {
										text : '删除',
										iconCls : 'btn-del',
										scope : this,
										handler : this.tbarDelClick
									}],
							clicksToEdit : 1,
							height : 450,
							url : __ctxPath + '/system/listDictionary.do',
							baseParams :{
								'Q_globalType.catKey_S_EQ ':'DIC'
							},
							fields : [{
										name : 'dicId',
										type : 'int'
									}, 'itemName', 'itemValue', 'descp', 'sn',
									'globalType'],
							sort : [{
										field : "dicId",
										direction : "DESC"
									}],
							columns : [{
										header : 'ID',
										dataIndex : 'dicId',
										hidden : true
									}, {
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
										dataIndex : 'itemName'
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
										editor : new Ext.form.TextField()
									}]
						});
			},// end of initUIComponents
			/**
			 * 树单击处理
			 */
			leftPanelClick : function(node) {
				this.selectedNode = node;
				var parentId = node.id;
				var grid = this.centerPanel;
				if (grid != null) {
					if (parentId == 0) {
						grid.setTitle('所有设置');
					} else {
						grid.setTitle(node.text + '-设置');
					}
					var store = grid.getStore();
					store.url = __ctxPath + "/system/listDictionary.do";
					store.baseParams = {
						parentId : parentId
					};
					store.reload();
				}

			},
			/**
			 * 树添加事件
			 */
			treeAddClick : function() {
				var archDicType = this.leftPanel;
				var parentId = archDicType.selectedNode.id;

				var globalTypeForm = new GlobalTypeForm({
							parentId : parentId,
							catKey : 'DIC',
							callback : function() {
								archDicType.root.reload();
							}
						});
				globalTypeForm.show();
			},
			/**
			 * 树的编辑处理
			 */
			treeEditClick : function() {
				var archDicType = this.leftPanel;
				var proTypeId = archDicType.selectedNode.id;
				if(proTypeId == 0) return;
				
				var globalTypeForm = new GlobalTypeForm({
							proTypeId : proTypeId,
							callback : function() {
								archDicType.root.reload();
							}
						});
				globalTypeForm.show();
			},
			/**
			 * 添加设置项
			 */
			treeAddSettingClick : function() {
				var gridPanel = this.centerPanel;
				var archDicType = this.leftPanel;
				var selectedNode = archDicType.selectedNode;
				var typeName = archDicType.selectedNode.text;
				var parentId = 0;
				if (selectedNode != null) {
					parentId = selectedNode.id;
				}
				if (parentId == 0) {
					Ext.ux.Toast.msg('操作信息', '请从左选择设置分类');
					return;
				}
				new DictionaryForm({
							parentId : parentId,
							typeName : typeName,
							callback : function() {
								gridPanel.getStore().reload();
							}
						}).show();
			},
			/**
			 * 设置添加
			 */
			tbarAddSetting : function() {
				var gridPanel = this.centerPanel;
				var archDicType = this.leftPanel;

				var selectedNode = archDicType.selectedNode;
				if (selectedNode == null) {
					Ext.ux.Toast.msg('操作信息', '请在左边选择字典分类');
					return;
				}
				var typeName = archDicType.selectedNode.text;

				var parentId = 0;
				if (selectedNode != null) {
					parentId = selectedNode.id;
				}
				if (parentId == 0) {
					Ext.ux.Toast.msg('操作信息', '请从左选择字典分类');
					return;
				}
				new DictionaryForm({
							parentId : parentId,
							typeName : typeName,
							callback : function() {
								gridPanel.getStore().reload();
							}
						}).show();
			},
			/**
			 * 保存
			 */
			tbarSaveClick : function() {
				var grid = this.centerPanel;

				var store = grid.getStore();
				var params = [];

				for (var i = 0; i < store.getCount(); i += 1) {
					var record = store.getAt(i);
					params.push(record.data);
				}
				Ext.Ajax.request({
							method : 'post',
							url : __ctxPath + '/system/mulSaveDictionary.do',
							params : {
								data : Ext.encode(params)
							},
							success : function(request) {
								Ext.ux.Toast.msg('操作信息', '成功设置');
								store.reload();
								grid.getView().refresh();
							},
							failure : function(request) {
								Ext.ux.Toast.msg('操作信息', '设置出错，请联系管理员!');
							}
						});

			},
			/**
			 * 删除
			 */
			tbarDelClick : function() {
				$delGridRs({
					url : __ctxPath+ '/system/multiDelDictionary.do',
					grid : this.centerPanel,
					idName : 'dicId'
				});
			}
		});