/**
 * 分类管理
 * 
 * @class GlobalTypeManager
 * @extends Ext.Panel
 */
GlobalTypeManager = Ext.extend(Ext.Panel, {
	// 构造方法
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		this.initUI();
		GlobalTypeManager.superclass.constructor.call(this, {
					id : 'GlobalTypeManager',
					autoScroll : true,
					layout : 'border',
					title : '系统分类树管理',
					iconCls : 'menu-globalType',
					items : [this.leftPanel, this.gridPanel]
				});
	},
	// 初始化组件
	initUI : function() {
		// 分类下拉列表数据
		this.comboGlobalTypeStore = new Ext.data.SimpleStore({
					autoLoad : true,
					url : __ctxPath + '/system/comboTypeKey.do',
					fields : ['key', 'name'],
					listeners : {
						scope : this,
						// 初始化加载分类下拉列表
						'load' : this.loadComboType
					}
				});
		// 分类下拉列表
		this.comboGlobalType = new Ext.form.ComboBox({
					editable : false,
					hiddenName : 'comboGlobalTypeValue',
					displayField : 'name',
					valueField : 'key',
					mode : 'local',
					triggerAction : 'all',
					store : this.comboGlobalTypeStore,
					listeners : {
						scope : this,
						// 选择分类
						'select' : this.selectComboType
					}
				});
		// 顶部左边->分类管理下拉选择
		this.topLeftPanel = new Ext.Panel({
					layout : 'fit',
					region : 'north',
					border : false,
					height : 23,
					items : [this.comboGlobalType]
				});

		// 分类树
		this.typeTreePanel = new htsoft.ux.TreePanelEditor({
					layout : 'fit',
					region : 'center',
					split : true,
					width : 200,
					url : __ctxPath + '/system/treeGlobalType.do?catKey=DP',
					scope : this,
					contextMenuItems : [{
								text : '新建分类',
								iconCls : 'btn-add',
								scope : this,
								handler : this.addType
							}, {
								text : '编辑分类',
								iconCls : 'btn-edit',
								scope : this,
								handler : this.editType
							}, {
								text : '删除分类',
								iconCls : 'btn-del',
								scope : this,
								handler : this.delTreeType
							}],
					autoScroll : true,
					// 点击分类树节点
					onclick : this.typeNodeClick
				});
		// 左边panel ==》分类管理
		this.leftPanel = new Ext.Panel({
					region : 'west',
					title : '分类管理',
					layout : 'border',
					collapsible : true,
					split : true,
					width : 200,
					tbar : [{
						xtype : 'button',
						text : '新建类',
						iconCls : 'btn-add',
						scope : this,
						handler : function() {
							new TypeKeyForm({
										scope : this,
										callback : function() {
											this.comboGlobalType.getStore()
													.reload();
										}
									}).show();
						}
					}
//					, '-', {
//						xtype : 'button',
//						text : '分类管理',
//						iconCls : 'menu-globalType',
//						scope : this,
//						handler : function() {
//							var typeKeyView = new TypeKeyView();
//							AppUtil.addTab(typeKeyView);
//						}
//					}
					],
					scope : this,
					items : [this.topLeftPanel, this.typeTreePanel]
				}

		);
		var row = 0;
		// 分类列表
		this.gridPanel = new HT.EditorGridPanel({
					region : 'center',
					title : '分类列表',
					tbar : [{
								text : '新建分类',
								iconCls : 'btn-add',
								scope : this,
								handler : this.addType
							}, '-', {
								text : '删除',
								iconCls : 'btn-del',
								scope : this,
								handler : this.delType
							}, '-', {
								xtype : 'button',
								text : '保存',
								iconCls : 'btn-save',
								scope : this,
								handler : this.saveType
							}],
					clicksToEdit : 1,
					rowActions : true,
					url : __ctxPath + "/system/subGlobalType.do",
					baseParams : {
						parentId : 0
					},
					fields : ['proTypeId', 'typeName', 'nodeKey', 'sn'],
					columns : [{
								header : 'proTypeId',
								dataIndex : 'proTypeId',
								hidden : true
							}, {
								header : '名称',
								dataIndex : 'typeName',
								editor : new Ext.form.TextField({
											allowBlank : false
										})
							}, {
								header : '节点Key',
								dataIndex : 'nodeKey',
								editor : new Ext.form.TextField({
											allowBlank : false
										})
							}, {
								header : '序号',
								dataIndex : 'sn'
							}, new Ext.ux.grid.RowActions({
										header : '管理',
										width : 80,
										actions : [{
													iconCls : 'btn-last',
													qtip : '向下',
													style : 'margin:0 3px 0 3px'
												}, {
													iconCls : 'btn-up',
													qtip : '向上',
													style : 'margin:0 3px 0 3px'
												}],
										listeners : {
											scope : this,
											'action' : this.onRowAction
										}
									})],
					defaults : {
						sortable : true,
						menuDisabled : false,
						width : 100
					}
				});
		// 行选择监听
		this.gridPanel.on('cellclick',
				function(grid, rowIndex, columnIndex, e) {
					row = rowIndex;
				});

	},// end of initUIComponents
	// 重新加载树和列表
	reloadTreeAndGrid : function(catKey) {
		var typeTreePanel = this.typeTreePanel;// 分类树
		var gridPanel = this.gridPanel; // 分类列表
		typeTreePanel.loader = new Ext.tree.TreeLoader({
					baseParams : {
						catKey : catKey
					},
					dataUrl : __ctxPath + '/system/treeGlobalType.do',
					requestMethod : 'GET'
				});
		typeTreePanel.selectedNode = null;
		typeTreePanel.root.reload();

		if (gridPanel != null) {
			var store = gridPanel.getStore();
			store.url = __ctxPath + "/system/subGlobalType.do";
			store.baseParams = {
				parentId : 0,
				catKey : catKey
			};
			store.reload();
		}
	},
	// 初始化加载分类下拉列表
	loadComboType : function(store) {
		if (store.getCount() > 0) {
			var record = store.getAt(0);
			var catKey = record.data.key;
			this.comboGlobalType.setValue(catKey);
			// 重新加载树和列表
			this.reloadTreeAndGrid(catKey);
		}
	},
	// 选择下拉列表分类
	selectComboType : function(combo, record, index) {
		var catKey = combo.getValue();
		// 重新加载树和列表
		this.reloadTreeAndGrid(catKey);
	},
	// 重新加载分类信息
	reloadType : function() {
		this.typeTreePanel.root.reload();
		// 刷新gridPanel
		this.gridPanel.getStore().reload();
	},
	// 分类节点点击
	typeNodeClick : function() {
		var parentId = this.typeTreePanel.selectedNode.id;
		var catKey = this.comboGlobalType.getValue();
		var store = this.gridPanel.getStore();
		store.baseParams = {
			parentId : parentId,
			catKey : catKey
		};
		store.reload();
	},
	// 新增树的分类
	addType : function() {
		var catKey = this.comboGlobalType.getValue();
		var selectedNode = this.typeTreePanel.selectedNode;
		if (!selectedNode) {
			Ext.ux.Toast.msg('操作信息', '请选择左侧树中的分类！');
			return;
		}
		new GlobalTypeForm({
					parentId : selectedNode.id,
					catKey : catKey,
					scope : this,
					callback : this.reloadType
				}).show();
	},
	// 修改树的分类
	editType : function() {
		var selectedNode = this.typeTreePanel.selectedNode;
		if (!selectedNode) {
			Ext.ux.Toast.msg('操作信息', '请选择左侧树中的分类！');
			return;
		}
		new GlobalTypeForm({
					proTypeId : selectedNode.id,
					scope : this,
					callback : this.reloadType
				}).show();
	},
	// 删除树的分类
	delTreeType : function() {
		var proTypeId = this.typeTreePanel.selectedNode.id;
		if (proTypeId == 0)
			return;
		Ext.Msg.confirm('信息确认', '您确认要删除所选分类吗？', function(btn) {
					if (btn != 'yes')
						return;
					Ext.Ajax.request({
								url : __ctxPath
										+ '/system/multiDelGlobalType.do',
								method : 'POST',
								scope : this,
								params : {
									ids : proTypeId
								},
								success : function(response, options) {
									Ext.ux.Toast.msg('操作信息', '成功删除分类!');
									this.reloadType.call(this);
								},
								failure : function(response, options) {
									Ext.ux.Toast.msg('操作信息', '操作出错，请联系管理员！');
								}
							});
				}, this);

	},
	delType : function() {
		var selectRecords = this.gridPanel.getSelectionModel().getSelections();
		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
			return;
		}
		var ids = Array();
		for (var i = 0; i < selectRecords.length; i++) {
			ids.push(selectRecords[i].data.proTypeId);
		}
		var me = this;
		Ext.Msg.confirm('信息确认', '您确认要删除所选记录吗？', function(btn) {
					if (btn == 'yes') {
						Ext.Ajax.request({
									url : __ctxPath
											+ '/system/multiDelGlobalType.do',
									params : {
										ids : ids
									},
									method : 'POST',
									scope : this,
									success : function(response, options) {
										Ext.ux.Toast.msg('操作信息', '成功删除选中的分类！');
										me.reloadType.call(me);
									},
									failure : function(response, options) {
										Ext.ux.Toast
												.msg('操作信息', '操作出错，请联系管理员！');
									}
								});
					}
				});

	},
	// 保存分类
	saveType : function() {
		var gridPanel = this.gridPanel;
		var store = gridPanel.getStore();
		var params = [];
		for (var i = 0; i < store.getCount(); i += 1) {
			var record = store.getAt(i);
			params.push(record.data);
		}
		Ext.Ajax.request({
					method : 'post',
					url : __ctxPath + '/system/mulSaveGlobalType.do',
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
	// 列表管理 -》》上下移动
	onRowAction : function(grid, record, action, row, col) {
		var store = grid.getStore();
		switch (action) {
			case 'btn-up' :
				if (row == 0) {
					Ext.ux.Toast.msg('操作信息', '已经为第一条!');
					return;
				}

				var rd1 = store.getAt(row - 1);
				var rd2 = store.getAt(row);
				store.removeAt(row);
				store.removeAt(row - 1);
				store.insert(row - 1, rd2);
				store.insert(row, rd1);
				break;
			case 'btn-last' :
				if (row == store.getCount() - 1) {
					Ext.ux.Toast.msg('操作信息', '已经为最后一条!');
					return;
				}
				var rd1 = store.getAt(row);
				var rd2 = store.getAt(row + 1);

				store.removeAt(row + 1);
				store.removeAt(row);

				store.insert(row, rd2);
				store.insert(row + 1, rd1);

				break;
			default :
				break;
		}
	}
});