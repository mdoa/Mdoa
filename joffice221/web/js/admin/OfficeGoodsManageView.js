Ext.ns('OfficeGoodsManageView');
/**
 * 办公用品管理
 * 
 * @class OfficeGoodsManageView
 * @extends Ext.Panel
 */
OfficeGoodsManageView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUI();
		// 调用父类构造函数
		OfficeGoodsManageView.superclass.constructor.call(this, {
					id : 'OfficeGoodsManageView',
					title : '办公用品管理',
					iconCls : 'menu-goods',
					height : 800,
					region : 'center',
					layout : 'border',
					items : [this.leftPanel, this.centerPanel]
				});
	},
	// 初始化组件
	initUI : function() {
		// 办公用品类型树
		this.officeGoodsTypeTreePanel = new htsoft.ux.TreePanelEditor({
					url : __ctxPath + '/admin/treeOfficeGoodsType.do',
					scope : this,
					autoScroll : true,
					split : true,
					onclick : this.nodeClick,
					enableDD : true
				});
		// 给树添加右击事件
		this.officeGoodsTypeTreePanel.on('contextmenu', this.contextmenu, this);
		// 左部面板
		this.leftPanel = new Ext.Panel({
					region : 'west',
					title : '办公用品类型',
					layout : 'fit',
					collapsible : true,
					split : true,
					border : false,
					width : 180,
					height : 800,
					items : [this.officeGoodsTypeTreePanel]
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
								fieldLabel : '查询条件：物品名称 ',
								xtype : 'textfield',
								name : 'Q_goodsName_S_LK'
							}, {
								fieldLabel : '所属分类',
								xtype : 'textfield',
								name : 'Q_officeGoodsType.typeName_S_LK',
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
								text : '添加办公用品',
								iconCls : 'btn-add',
								scope : this,
								handler : this.createRs
							}, {
								xtype : 'button',
								text : '删除办公用品',
								iconCls : 'btn-del',
								scope : this,
								handler : this.removeSelRs
							}]
				});
		// 固定资产列表面板
		this.gridPanel = new HT.GridPanel({
			region : 'center',
			tbar : this.topbar,
			// 使用RowActions
			rowActions : true,
			sort : [{
						field : "goodsId",
						direction : "DESC"
					}],
			url : __ctxPath + '/admin/listOfficeGoods.do',
			fields : [{
						name : 'goodsId',
						type : 'int'
					}, {
						name : 'officeGoodsType.typeName',
						mapping : 'officeGoodsType.typeName'
					}, 'goodsName', 'goodsNo', 'specifications', 'unit',
					'isWarning', 'warnCounts', 'notes', 'stockCounts'],
			columns : [{
						header : 'goodsId',
						dataIndex : 'goodsId',
						hidden : true
					}, {
						header : '所属分类',
						dataIndex : 'officeGoodsType.typeName'
					}, {
						header : '物品名称',
						dataIndex : 'goodsName'
					}, {
						header : '编号',
						dataIndex : 'goodsNo'
					}, {
						header : '规格',
						dataIndex : 'specifications'
					}, {
						header : '计量单位',
						dataIndex : 'unit'
					}, {
						header : '是否启用库存警示',
						dataIndex : 'isWarning',
						renderer : function(value) {
							if (value == '0') {
								return '未启动';
							}
							if (value == '1') {
								return '已启动';
							}
						}
					}, {
						header : '备注',
						dataIndex : 'notes'
					}, {
						header : '库存总数',
						dataIndex : 'stockCounts',
						renderer : this.stockCounts
					}, new Ext.ux.grid.RowActions({
								header : '管理',
								width : 100,
								actions : [{
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
					title : '所有用品列表',
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
	// 添加办公用品
	createRs : function() {
		new OfficeGoodsForm({
					scope : this,
					callback : this.reload
				}).show();
	},
	// 编辑
	editRecord : function(record) {
		new OfficeGoodsForm({
					goodsId : record.data.goodsId,
					scope : this,
					callback : this.reload
				}).show();
	},
	// 把选中ID删除
	removeSelRs : function() {
		$delGridRs({
					url : __ctxPath + '/admin/multiDelOfficeGoods.do',
					grid : this.gridPanel,
					idName : 'goodsId'
				});
	},
	// 按ID删除记录
	removeRs : function(id) {
		$postDel({
					url : __ctxPath + '/admin/multiDelOfficeGoods.do',
					ids : id,
					grid : this.gridPanel
				});
	},
	// 节点点击事件
	nodeClick : function(node) {
		this.selectNode = node;
		var officeGoodsTypeId = node.id;
		var gridPanel = this.gridPanel;
		var store = gridPanel.getStore();
		if (node != null) {
			if (officeGoodsTypeId == 0) {
				this.centerPanel.setTitle('所有用品列表');
			} else {
				this.centerPanel.setTitle('[' + node.text + ']' + '类型用品列表');
			}
			store.url = __ctxPath + '/admin/listOfficeGoods.do';
			if (officeGoodsTypeId != 0) {
				store.baseParams = {
					'Q_officeGoodsType.typeId_L_EQ' : officeGoodsTypeId
				};
			} else {
				store.baseParams = {
					'Q_officeGoodsType.typeId_L_EQ' : null
				};
			}
			gridPanel.getBottomToolbar().moveFirst();
			store.reload();
		}
	},
	// 添加节点
	addNode : function() {
		new OfficeGoodsTypeForm({
					scope : this,
					callback : this.reload
				}).show();
	},
	// 编辑节点
	editNode : function() {
		var officeGoodsTypeId = this.selectNode.id;
		new OfficeGoodsTypeForm({
					typeId : officeGoodsTypeId,
					scope : this,
					callback : this.reload
				}).show();

	},
	// 删除节点
	delNode : function() {
		var officeGoodsTypeId = this.selectNode.id;
		if (!Ext.isEmpty(officeGoodsTypeId)) {
			$postDel({
						url : __ctxPath + '/admin/multiDelOfficeGoodsType.do',
						ids : officeGoodsTypeId,
						scope : this,
						callback : this.reload
					});
		}
	},
	// 重新加载officeGoodsTypeTreePanel和gridPanel
	reload : function() {
		this.officeGoodsTypeTreePanel.root.reload();
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
		if (node.id > 0) { // 办公用品类型不能删除，和修改
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
	// 库存总数
	stockCounts : function(value, metadata, record, rowIndex, colIndex) {
		var warnCounts = record.data.warnCounts;
		var isWarning = record.data.isWarning;
		if (value <= warnCounts && isWarning == '1') {
			return '<a style="color:red;" title="已少于警报库存！">' + value + '</a>';
		} else {
			return value;
		}
	},
	// 行的Action
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.removeRs.call(this, record.data.goodsId);
				break;
			case 'btn-edit' :
				this.editRecord.call(this, record);
				break;
			default :
				break;
		}
	}
});
