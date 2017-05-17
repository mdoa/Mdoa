/**
 * @author:
 * @class ArchFondView
 * @extends Ext.Panel
 * @description 档案管理-全宗管理
 * @company 杭州梦德软件有限公司
 * @createTime:
 */
ArchFondView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		ArchFondView.superclass.constructor.call(this, {
					id : 'ArchFondView',
					title : '全宗管理',
					iconCls : 'menu-archFond',
					region : 'center',
					layout : 'border',
					items : [this.searchPanel, this.treePanel, this.gridPanel]
				});
	},// end of constructor
	// 初始化组件
	initUIComponents : function() {
		// 初始化搜索条件Panel
		this.searchPanel = new HT.SearchPanel({
					layout : 'form',
					region : 'north',
					colNums : 4,
					items : [{
								fieldLabel : '全宗号',
								name : 'Q_afNo_S_LK',
								flex : 1,
								xtype : 'textfield'
							}, {
								fieldLabel : '全宗名',
								name : 'Q_afName_S_LK',
								flex : 1,
								xtype : 'textfield'
							}, {
								fieldLabel : '状态',
								hiddenName : 'Q_status_SN_EQ',
								flex : 1,
								xtype : 'combo',
								mode : 'local',
								editable : false,
								triggerAction : 'all',
								store : [['', '全部'], ['0', '草稿'], ['1', '启用'],
										['-1', '禁用']]
							}, {
								fieldLabel : '开放形式',
								name : 'Q_openStyle_S_EQ',
								flex : 1,
								editable : true,
								lazyInit : false,
								forceSelection : false,
								xtype : 'diccombo',
								nodeKey : 'fonds_open_form'
							}, {
								fieldLabel : '创建时间	从',
								name : 'Q_createTime_D_GE',
								flex : 1,
								xtype : 'datefield',
								format : 'Y-m-d'
							}, {
								fieldLabel : '至	',
								name : 'Q_createTime_D_LE',
								flex : 1,
								xtype : 'datefield',
								format : 'Y-m-d'
							}, {
								fieldLabel : '最后更新时间	从',
								name : 'Q_updateTime_D_GE',
								flex : 1,
								xtype : 'datefield',
								format : 'Y-m-d'
							}, {
								fieldLabel : '至',
								name : 'Q_updateTime_D_LE',
								flex : 1,
								xtype : 'datefield',
								format : 'Y-m-d'
							}, {
								fieldLabel : '分类ID',
								name : 'Q_globalType.proTypeId_L_EQ',
								xtype : 'hidden',
								hideLabel : true,
								flex : 1
							}, {
								fieldLabel : '分类名称',
								name : 'Q_globalType.typeName_L_EQ',
								xtype : 'hidden',
								hideLabel : true,
								flex : 1
							}],
					buttons : [{
								text : '查询',
								scope : this,
								iconCls : 'btn-search',
								handler : this.search
							}, {
								text : '重置',
								scope : this,
								iconCls : 'btn-reset',
								handler : this.reset
							}]
				});// end of searchPanel
		// 左边的树
		this.treePanel = new htsoft.ux.TreePanelEditor({
					layout : 'fit',
					region : 'west',
					collapsible : true,
					split : true,
					width : 200,
					title : '全宗类别',
					url : __ctxPath + '/system/treeGlobalType.do?catKey=AR_FD',
					scope : this,
					autoScroll : true,
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
								text : '删除分类',
								scope : this,
								iconCls : 'btn-del',
								handler : this.treeDelClick
							}],
					// 点击树节点
					onclick : this.treePanelClick
				});

		// 列表面板
		this.gridPanel = new HT.GridPanel({
					region : 'center',
					tbar : [{
								iconCls : 'btn-add',
								text : '添加全宗',
								scope : this,
								handler : this.createRs
							}, {
								iconCls : 'btn-del',
								text : '删除全宗',
								scope : this,
								handler : this.removeSelRs,
								hidden : !isGranted('_ArchFondViewDelete')
							}],
					// 使用RowActions
					rowActions : true,
					url : __ctxPath + "/arch/listArchFond.do",
					fields : [{
								name : 'archFondId',
								type : 'int'
							}, 'afNo', 'afName', 'shortDesc', 'descp',
							'clearupDesc', 'createTime', 'updateTime',
							'creatorName', 'creatorId', 'caseNums', 'status',
							'typeName', 'openStyle'],
					columns : [{
								header : 'archFondId',
								dataIndex : 'archFondId',
								hidden : true
							}, {
								header : '全宗号',
								dataIndex : 'afNo'
							}, {
								header : '全宗名',
								dataIndex : 'afName'
							}, {
								header : '案卷数',
								dataIndex : 'caseNums'
							}, {
								header : '状态',
								dataIndex : 'status',
								renderer : function(value, metadata, record,
										rowIndex, colIndex) {
									if (value == '0') {
										return '草稿';
									} else if (value == '1') {
										return '启用';
									} else {
										return '禁用';
									}
								}
							}, {
								header : '全宗分类',
								dataIndex : 'typeName'
							}, {
								header : '开放形式',
								dataIndex : 'openStyle'
							}, {
								header : '创建人',
								dataIndex : 'creatorName'
							}, {
								header : '创建时间',
								dataIndex : 'createTime'
							}, new Ext.ux.grid.RowActions({
										header : '管理',
										width : 100,
										actions : [{
											iconCls : 'btn-del',
											qtip : '删除',
											style : 'margin:0 3px 0 3px',
											fn : function(record) {
												if (isGranted('_ArchFondViewDelete')
														|| record.data.status == 0)
													return true;
												return false;
											}
										}, {
											iconCls : 'btn-edit',
											qtip : '编辑',
											style : 'margin:0 3px 0 3px',
											fn : function(record) {
												if (isGranted('_ArchFondViewEdit')
														|| record.data.status == 0)
													return true;
												return false;
											}
										}],
										listeners : {
											scope : this,
											'action' : this.onRowAction
										}
									})],
					// end of columns
					listeners : {
						scope : this,
						'rowdblclick' : this.rowClick
					}
				});

	},// end of the initComponents()
	/**
	 * treePanel单击事件
	 */
	treePanelClick : function(node) {
		this.getCmpByName('Q_globalType.proTypeId_L_EQ').setValue(node.id == 0
				? null
				: node.id);
		this.search();
	},
	/**
	 * 新建分类
	 */
	treeAddClick : function() {
		var globalTypeTree = this.treePanel;
		var parentId = globalTypeTree.selectedNode.id;
		var globalTypeForm = new GlobalTypeForm({
					parentId : parentId,
					catKey : 'AR_FD',
					callback : function() {
						globalTypeTree.root.reload();
					}
				});
		globalTypeForm.show();
	},
	/**
	 * 修改分类
	 */
	treeEditClick : function() {
		var globalTypeTree = this.treePanel;
		var proTypeId = globalTypeTree.selectedNode.id;
		new GlobalTypeForm({
					proTypeId : proTypeId,
					scope : this,
					callback : function() {
						globalTypeTree.root.reload();
					}
				}).show();
	},
	/**
	 * 删除分类
	 */
	treeDelClick : function() {
		var globalTypeTree = this.treePanel;
		var gridPanel = this.gridPanel;
		var proTypeId = globalTypeTree.selectedNode.id;
		var ids = Array();
		ids.push(proTypeId);
		$postDel({
					url : __ctxPath + '/system/multiDelGlobalType.do',
					ids : ids,
					scope : this,
					callback : function() {
						globalTypeTree.root.reload();
						gridPanel.getStore().reload();
					}
				});

	},
	// 重置查询表单
	reset : function() {
		this.searchPanel.getForm().reset();
	},
	// 按条件搜索
	search : function() {
		$search({
					searchPanel : this.searchPanel,
					gridPanel : this.gridPanel
				});
	},
	// GridPanel行点击处理事件
	rowClick : function(grid, rowindex, e) {
		grid.getSelectionModel().each(function(rec) {
			if(isGranted('_ArchFondViewEdit')||rec.data.status == 0){
					new ArchFondForm({
								archFondId : rec.data.archFondId,
								callback : function() {
									grid.getStore().reload();
								}
							}).show();
			}
		});
			
	},
	// 创建记录
	createRs : function() {
		var grid = this.gridPanel;
		var node = this.treePanel.selectedNode;
		var proTypeId = null;
		var typeName = null;
		if (node) {
			proTypeId = node.id == 0 ? null : node.id;
			typeName = node.text;
		}
		new ArchFondForm({
					proTypeId : proTypeId,
					typeName : typeName,
					archFondId : null,
					scope : this,
					callback : function() {
						grid.getStore().reload();
					}
				}).show();
	},
	// 把选中ID删除
	removeSelRs : function() {
		$delGridRs({
					url : __ctxPath + '/arch/multiDelArchFond.do',
					grid : this.gridPanel,
					idName : 'archFondId'
				});
	},
	// 按ID删除记录
	removeRs : function(id) {
		$postDel({
					url : __ctxPath + '/arch/multiDelArchFond.do',
					ids : id,
					grid : this.gridPanel
				});
	},
	// 编辑Rs
	editRs : function(record) {
		var grid = this.gridPanel;
		new ArchFondForm({
					archFondId : record.data.archFondId,
					callback : function() {
						grid.getStore().reload();
					}
				}).show();
	},
	// 行的Action
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.removeRs.call(this, record.data.archFondId);
				break;
			case 'btn-edit' :
				this.editRs.call(this, record);
				break;
			default :
				break;
		}
	}
});
