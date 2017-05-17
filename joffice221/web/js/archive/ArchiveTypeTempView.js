Ext.ns('ArchiveTypeTempView');
/**
 * 公文分类及模板管理
 * 
 * @author csx
 * @createtime 2010年1月10日
 * @class ArchiveTypeTempView
 * @extends Ext.Panel
 * @description 公文分类及模板管理
 * @company 宏天软件
 */
ArchiveTypeTempView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		this.initUI();
		ArchiveTypeTempView.superclass.constructor.call(this, {
					id : 'ArchiveTypeTempView',
					title : '公文分类及模板管理',
					iconCls : 'menu-archive-template',
					layout : 'border',
					items : [this.treePanel, this.outPanel]
				});
	},
	// 初始化组件
	initUI : function() {
		this.catKey = 'ARC_TEM_TYPE';// 公文分类
		// 公文分类树
		this.treePanel = new htsoft.ux.TreePanelEditor({
					layout : 'fit',
					region : 'west',
					collapsible : true,
					split : true,
					width : 200,
					title : '公文分类',
					url : __ctxPath + '/system/treeGlobalType.do?catKey='
							+ this.catKey,
					scope : this,
					autoScroll : true,
					contextMenuItems : [{
								text : '添加分类',
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
								handler : this.delType
							}],
					// 点击分类树节点
					onclick : this.typeNodeClick
				});

		// 搜索面板
		this.searchPanel = new HT.SearchPanel({
					layout : 'form',
					region : 'north',
					colNums : 3,
					keys : {
						key : Ext.EventObject.ENTER,
						fn : this.search,
						scope : this
					},
					labelWidth : 60,
					items : [{
								fieldLabel : '模板名称',
								xtype : 'textfield',
								name : 'Q_tempName_S_LK',
								maxLength : 150
							}, {
								xtype : 'button',
								text : '查询',
								iconCls : 'search',
								scope : this,
								handler : this.search
							}, {
								xtype : 'button',
								text : '清空',
								iconCls : 'reset',
								scope : this,
								handler : this.resetSearchForm
							}]
				});

		// 列表面板
		this.gridPanel = new HT.GridPanel({
			region : 'center',
			tbar : [{
						text : '添加公文模板',
						iconCls : 'btn-add',
						scope : this,
						handler : this.createRecord,
						hidden : !isGranted('_ArchivesTempAdd')
					}, '-', {
						text : '删除公文模板',
						iconCls : 'btn-del',
						scope : this,
						handler : this.removeSelRs,
						hidden : !isGranted('_ArchviesTempDel')
					}],
			// 使用RowActions
			rowActions : true,
			url : __ctxPath + "/archive/listArchTemplate.do",
			fields : [{
						name : 'templateId',
						type : 'int'
					}, 'archivesType', 'archivesType.typeName', 'tempName',
					'tempPath', {
						name : 'fileId',
						mapping : 'fileAttach.fileId'
					}],
			columns : [{
						header : 'templateId',
						dataIndex : 'templateId',
						hidden : true
					}, {
						header : '所属类型',
						dataIndex : 'archivesType.typeName'
					}, {
						header : '模板名称',
						dataIndex : 'tempName'
					}, {
						header : '文件路径',
						hidden : true,
						dataIndex : 'tempPath'
					}, new Ext.ux.grid.RowActions({
								header : '管理',
								width : 200,
								actions : [{
											iconCls : 'btn-del',
											qtip : '删除',
											style : 'margin:0 2px 0 2px',
											fn : function(record) {
												if (isGranted('_ArchviesTempDel'))
													return true;
												return false;
											}
										}, {
											iconCls : 'btn-edit',
											qtip : '编辑',
											style : 'margin:0 2px 0 2px',
											fn : function(record) {
												if (isGranted('_ArchivesTempEdit'))
													return true;
												return false;
											}
										}, {
											iconCls : 'btn-readdocument',
											qtip : '查看在线模板',
											style : 'margin: 0 2px 0 2px',
											fn : function(record) {
												if (isGranted('_ArchiveTypeTempQuery'))
													return true;
												return false;
											}
										}],
								listeners : {
									scope : this,
									'action' : this.onRowAction
								}
							})],
			// 监听行点击事件
			listeners : {
				scope : this,
				'rowdblclick' : this.rowClick
			}
		});

		this.outPanel = new Ext.Panel({
					region : 'center',
					title : '公文模板管理',
					layout : 'border',
					items : [this.searchPanel, this.gridPanel]
				});
	},
	/**
	 * 按条件搜索
	 */
	search : function() {
		$search({
					searchPanel : this.searchPanel,
					gridPanel : this.gridPanel
				});
	},
	/**
	 * 重置(清空)查询表单
	 */
	resetSearchForm : function() {
		this.searchPanel.getForm().reset();
	},
	/**
	 * 重新加载分类树
	 */
	reloadType : function() {
		this.treePanel.root.reload();
		// 刷新gridPanel
		this.gridPanel.getStore().reload();
	},
	/**
	 * 添加分类
	 */
	addType : function() {
		var parentId = this.treePanel.selectedNode.id;
		new GlobalTypeForm({
					parentId : parentId,
					catKey : this.catKey,
					scope : this,
					callback : this.reloadType
				}).show();
	},
	/**
	 * 编辑分类
	 */
	editType : function() {
		var proTypeId = this.treePanel.selectedNode.id;
		if (proTypeId == 0)
			return;
		new GlobalTypeForm({
					proTypeId : proTypeId,
					scope : this,
					callback : this.reloadType
				}).show();
	},
	/**
	 * 删除分类
	 */
	delType : function() {
		var proTypeId = this.treePanel.selectedNode.id;
		if (proTypeId == 0)
			return;
		$postDel({
					msg : '您确认要删除所选分类吗？',
					url : __ctxPath + '/system/multiDelGlobalType.do',
					ids : proTypeId,
					scope : this,
					callback : this.reloadType
				});
	},// 分类节点点击
	typeNodeClick : function() {
		var proTypeId = this.treePanel.selectedNode.id;
		var store = this.gridPanel.getStore();
		// 带上查询条件
		var name = this.searchPanel.getCmpByName('Q_tempName_S_LK').getValue();
		store.baseParams = {
			'Q_archivesType.proTypeId_L_EQ' : proTypeId,
			'Q_tempName_S_LK' : name
		};
		this.gridPanel.getBottomToolbar().moveFirst();
	},
	/**
	 * 刷新gridPanel
	 */
	reloadGridPanel : function() {
		this.gridPanel.getStore().reload();
	},
	/**
	 * 添加公文模板
	 */
	createRecord : function() {
		new ArchTemplateForm({
					typeId : this.typeId,
					typeName : this.typeName,
					scope : this,
					callback : this.reloadGridPanel
				}).show();
	},
	/**
	 * 把选中ID删除
	 */
	removeSelRs : function() {
		$delGridRs({
					url : __ctxPath + '/archive/multiDelArchTemplate.do',
					grid : this.gridPanel,
					idName : 'templateId'
				});
	},
	/**
	 * 行双击事件
	 * 
	 * @param {}
	 *            grid
	 * @param {}
	 *            rowindex
	 * @param {}
	 *            e
	 */
	rowClick : function(grid, rowindex, e) {
		grid.getSelectionModel().each(function(record) {
					this.defId = record.data.defId;
					if (isGranted('_ArchivesTempEdit')) {
						this.editRs();
					}
				}, this);
	},
	/**
	 * 行的Action
	 * 
	 * @param grid
	 * @param record
	 * @param action
	 * @param row
	 * @param col
	 */
	onRowAction : function(grid, record, action, row, col) {
		this.templateId = record.data.templateId;
		switch (action) {
			case 'btn-del' :
				this.removeRs.call(this);
				break;
			case 'btn-edit' :
				this.editRs.call(this);
				break;
			case 'btn-readdocument' :
				this.readdocument.call(this, record);
				break;
			default :
				break;
		}
	},
	/**
	 * 删除公文模板
	 */
	removeRs : function() {
		$postDel({
					url : __ctxPath + '/archive/multiDelArchTemplate.do',
					ids : this.templateId,
					scope : this,
					callback : this.reloadGridPanel
				});
	},
	/**
	 * 编辑公文模板
	 */
	editRs : function() {
		new ArchTemplateForm({
					templateId : this.templateId,
					scope : this,
					callback : this.reloadGridPanel
				}).show();
	},
	/**
	 * 查看公文模板
	 * 
	 * @param {}
	 *            record
	 */
	readdocument : function(record) {
		new OfficeTemplateView({
					fileId : record.data.fileId
				}).show();
	}
});