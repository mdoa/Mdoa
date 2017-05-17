Ext.ns('FixedAssetsManageView');
/**
 * 固定资产管理
 * 
 * @class FixedAssetsManageView
 * @extends Ext.Panel
 */
FixedAssetsManageView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUI();
		// 调用父类构造函数
		FixedAssetsManageView.superclass.constructor.call(this, {
					id : 'FixedAssetsManageView',
					title : '固定资产管理',
					iconCls : 'menu-assets',
					region : 'center',
					layout : 'border',
					items : [this.leftPanel, this.centerPanel]
				});
	},
	// 初始化组件
	initUI : function() {
		// 资产类型树
		this.assetsTreePanel = new htsoft.ux.TreePanelEditor({
					url : __ctxPath + '/admin/treeAssetsType.do',
					scope : this,
					autoScroll : true,
					split : true,
					onclick : this.nodeClick,
					enableDD : true
				});
		// 给树添加右击事件
		this.assetsTreePanel.on('contextmenu', this.contextmenu, this);
		// 左部面板
		this.leftPanel = new Ext.Panel({
					region : 'west',
					title : '资产类型',
					layout : 'fit',
					collapsible : true,
					split : true,
					border : false,
					width : 160,
					height : 800,
					items : [this.assetsTreePanel]
				});
		// 查询面板
		this.searchPanel = new HT.SearchPanel({
					layout : 'form',
					region : 'north',
					colNums : 4,
					keys : [{
								key : Ext.EventObject.ENTER,
								fn : this.search,
								scope : this
							}, {
								key : Ext.EventObject.ESC,
								fn : this.reset,
								scope : this
							}],
					labelWidth : 135,
					items : [{
								fieldLabel : '查询条件：资产名称 ',
								xtype : 'textfield',
								name : 'Q_assetsName_S_LK'
							}, {
								fieldLabel : '所属部门',
								xtype : 'textfield',
								name : 'Q_beDep_S_LK',
								labelWidth : 60
							}, {
								xtype : 'button',
								text : '查询',
								iconCls : 'search',
								scope : this,
								handler : this.search
							}, {
								iconCls : 'reset',
								xtype : 'button',
								text : '重置',
								scope : this,
								handler : this.reset
							}]
				});

		// 顶部菜单面板
		this.topbar = new Ext.Toolbar({
					items : [{
								xtype : 'button',
								text : '创建固定资产项',
								iconCls : 'btn-add',
								scope : this,
								handler : this.createRecord
							}, {
								xtype : 'button',
								text : '删除固定资产项',
								iconCls : 'btn-del',
								scope : this,
								handler : this.delRecord
							}]
				});
		// 固定资产列表面板
		this.gridPanel = new HT.GridPanel({
			region : 'center',
			tbar : this.topbar,
			// 使用RowActions
			rowActions : true,
			sort : [{
						field : "assetsId",
						direction : "DESC"
					}],
			url : __ctxPath + '/admin/listFixedAssets.do',
			fields : [{
						name : 'assetsId',
						type : 'int'
					}, {
						name : 'assetsType.typeName',
						mapping : 'assetsType.typeName'
					}, {
						name : 'depType',
						mapping : 'depreType'
					}, 'assetsNo', 'assetsName', 'model', 'manufacturer',
					'manuDate', 'buyDate', 'beDep', 'custodian', 'notes',
					'remainValRate', 'startDepre', 'intendTerm',
					'intendWorkGross', 'workGrossUnit', 'assetValue',
					'assetCurValue', 'depreRate'],
			columns : [{
						header : 'assetsId',
						dataIndex : 'assetsId',
						hidden : true
					}, {
						header : '资产编号',
						dataIndex : 'assetsNo'
					}, {
						header : '资产名称',
						dataIndex : 'assetsName'
					}, {
						header : '资产类别',
						dataIndex : 'assetsType.typeName'
					}, {
						header : '折旧类型',
						dataIndex : 'depType',
						sortable : false,
						renderer : function(value) {
							return value.typeName;
						}
					}, {
						header : '开始折旧日期',
						dataIndex : 'startDepre'
					}, {
						header : '资产值',
						dataIndex : 'assetValue'
					}, {
						header : '资产当前值',
						dataIndex : 'assetCurValue'
					}, new Ext.ux.grid.RowActions({
								header : '管理',
								width : 100,
								actions : [{
											iconCls : 'btn-pred',
											qtip : '开始折算',
											style : 'margin:0 3px 0 3px'
										}, {
											iconCls : 'btn-del',
											qtip : '删除',
											style : 'margin:0 3px 0 3px'
										}, {
											iconCls : 'btn-edit',
											qtip : '编辑',
											style : 'margin:0 3px 0 3px'
										}],
								listeners : {
									scope : this,
									'action' : this.onRowAction
								}
							})]
				// end of columns
			});
		// 添加gridPanel行双击事件
		this.gridPanel.addListener({
					scope : this,
					'rowdblclick' : this.rowdblclick
				});
		// 中部面板
		this.centerPanel = new Ext.Panel({
					title : '固定资产列表',
					layout : 'border',
					region : 'center',
					items : [this.searchPanel, this.gridPanel]
				});
	},
	// 重置查询表单
	reset : function() {
		this.searchPanel.getForm().reset();
	},
	// 查询
	search : function() {
		$search({
					searchPanel : this.searchPanel,
					gridPanel : this.gridPanel
				});
	},
	// 行双击事件
	rowdblclick : function(grid, rowindex, e) {
		var rec = grid.getStore().getAt(rowindex);
		this.editRecord.call(this, rec);
	},
	// 创建固定资产项
	createRecord : function() {
		new FixedAssetsForm({
					scope : this,
					callback : this.reload
				}).show();
	},
	// 折旧
	depreciate : function(record) {
		var id = record.data.assetsId;
		var method = record.data.depType.calMethod;
		if (method == 2) {
			new WorkGrossWin(id);
		} else if (method == 1 || method == 3 || method == 4) {
			Ext.Msg.confirm('操作提示', '你决定开始折算了吗？', function(btn) {
						if (btn == 'yes') {
							Ext.Ajax.request({
										url : __ctxPath
												+ '/admin/depreciateDepreRecord.do',
										params : {
											ids : id
										},
										method : 'post',
										success : function(response, options) {
											var result = Ext.util.JSON
													.decode(response.responseText);
											if (result.success) {
												Ext.ux.Toast.msg("信息提示",
														"成功产生折旧记录！");
											} else {
												Ext.ux.Toast.msg("信息提示",
														result.message);
											}
										},
										failure : function(response, options) {
											var result = Ext.util.JSON
													.decode(response.responseText);
											Ext.ux.Toast.msg("信息提示",
													result.message);
										}
									});
						}

					});

		} else {
			Ext.ux.Toast.msg("信息提示", "抱歉，该类型的折算方法待实现！");
		}
	},
	// 编辑
	editRecord : function(record) {
		new FixedAssetsForm({
					assetsId : record.data.assetsId,
					scope : this,
					callback : this.reload
				}).show();
	},
	// 把选中ID删除
	delRecord : function() {
		$delGridRs({
			        msg : '将折算记录一起删除，您确认要删除该记录吗？',
					url : __ctxPath + '/admin/multiDelFixedAssets.do',
					grid : this.gridPanel,
					idName : 'assetsId'
				});
	},
	// 按ID删除记录
	removeRs : function(id) {
		$postDel({
					msg : '将折算记录一起删除，您确认要删除该记录吗？',
					url : __ctxPath + '/admin/multiDelFixedAssets.do',
					ids : id,
					grid : this.gridPanel
				});
	},
	// 节点点击事件
	nodeClick : function(node) {
		this.selectNode = node;
		var assetsTypeId = node.id;
		var gridPanel = this.gridPanel;
		var store = gridPanel.getStore();
		if (assetsTypeId != null) {
			if (assetsTypeId == 0) {
				this.centerPanel.setTitle('所有类型固定资产列表');
			} else {
				this.centerPanel.setTitle('[' + node.text + ']' + '类型固定资产列表');
			}
			store.url = __ctxPath + '/admin/listFixedAssets.do';
			if (assetsTypeId != 0) {
				store.baseParams = {
					'Q_assetsType.assetsTypeId_L_EQ' : assetsTypeId
				};
			} else {
				store.baseParams = {
					'Q_assetsType.assetsTypeId_L_EQ' : null
				};
			}
			gridPanel.getBottomToolbar().moveFirst();
			store.reload();
		}
	},
	// 添加节点
	addNode : function() {
		new AssetsTypeForm({
					scope : this,
					callback : this.reload
				}).show();
	},
	// 编辑节点
	editNode : function() {
		var assetsTypeId = this.selectNode.id;
		new AssetsTypeForm({
					assetsTypeId : assetsTypeId,
					scope : this,
					callback : this.reload
				}).show();

	},
	// 删除节点
	delNode : function() {
		var assetsTypeId = this.selectNode.id;
		if (!Ext.isEmpty(assetsTypeId)) {
			$postDel({
						url : __ctxPath + '/admin/multiDelAssetsType.do',
						ids : assetsTypeId,
						scope : this,
						callback : this.reload
					});
		}
	},
	// 重新加载assetsTreePanel和gridPanel
	reload : function() {
		this.assetsTreePanel.root.reload();
		this.gridPanel.getStore().reload();
	},
	// 节点右击事件
	contextmenu : function(node, e) {
		this.nodeClick(node);
		selected = new Ext.tree.TreeNode({
					id : node.id,
					text : node.text
				});
		// 创建右键菜单
		var treeMenu = new Ext.menu.Menu({
					items : []
				});
		treeMenu.clearMons();
		treeMenu.add({
					text : '新建类型',
					iconCls : 'btn-add',
					handler : this.addNode,
					scope : this
				});
		if (node.id > 0) { // 资产类型不能删除，和修改
			treeMenu.add({
						text : '修改类型',
						iconCls : 'btn-edit',
						handler : this.editNode,
						scope : this
					}, {
						text : '删除类型',
						iconCls : 'btn-del',
						handler : this.delNode,
						scope : this
					});
		}
		treeMenu.showAt(e.getXY());

	},
	// 行的Action
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-pred' :
				this.depreciate.call(this, record);
				break;
			case 'btn-del' :
				this.removeRs.call(this, record.data.assetsId);
				break;
			case 'btn-edit' :
				this.editRecord.call(this, record);
				break;
			default :
				break;
		}
	}
});
