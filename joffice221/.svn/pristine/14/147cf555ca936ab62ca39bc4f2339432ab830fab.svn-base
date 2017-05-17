Ext.ns('ProDefinitionManager');
/**
 * 全新流程定义管理
 * 
 * @class ProDefinitionManager
 * @extends Ext.Panel
 */
ProDefinitionManager = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		this.initUI();
		ProDefinitionManager.superclass.constructor.call(this, {
					id : 'ProDefinitionManager',
					title : Ext.isEmpty(this.title) ? '流程定义管理' : this.title,
					iconCls : 'menu-flowManager',
					layout : 'border',
					items : [this.typeTreePanel, this.outPanel]
				});
	},
	// 初始化组件
	initUI : function() {
		this.catKey = 'FLOW';// 流程分类
		this.row = 0;// 选中的行
		// 流程分类树
		this.typeTreePanel = new htsoft.ux.TreePanelEditor({
					layout : 'fit',
					region : 'west',
					collapsible : true,
					split : true,
					width : 200,
					title : '流程分类树',
					url : __ctxPath + '/system/flowTreeGlobalType.do?catKey='
							+ this.catKey,
					scope : this,
					autoScroll : true,
					contextMenuItems : [{
								text : '添加流程分类',
								iconCls : 'btn-add',
								scope : this,
								handler : this.addType
							}, {
								text : '编辑流程分类',
								iconCls : 'btn-edit',
								scope : this,
								handler : this.editType
							}, {
								text : '删除流程分类',
								iconCls : 'btn-del',
								scope : this,
								handler : this.delType
							}, '-', {
								text : '编辑分类权限',
								iconCls : 'btn-shared',
								scope : this,
								handler : this.editTypeRight
							}],
					// 点击分类树节点
					onclick : this.typeNodeClick
				});

		// 搜索面板
		this.searchPanel = new HT.SearchPanel({
					layout : 'form',
					region : 'north',
					colNums : 4,
					keys : {
						key : Ext.EventObject.ENTER,
						fn : this.search,
						scope : this
					},
					// labelAlign : 'right',
					labelWidth : 60,
					items : [{
						fieldLabel : '流程名称',
						xtype : 'textfield',
						name : 'Q_name_S_LK',
						maxLength : 150
							// labelWidth:55
						}, {
						fieldLabel : '流程描述',
						xtype : 'textfield',
						name : 'Q_description_S_LK',
						maxLength : 125,
						labelWidth : 55
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
						handler : this.reset
					}]
				});

		// 流程定义列表面板
		this.gridPanel = new HT.EditorGridPanel({
					region : 'center',
					tbar : [{
								text : '刷新',
								iconCls : 'btn-refresh',
								scope : this,
								handler : this.reloadGridPanel,
								hidden : !isGranted('_FlowAdd')
							}, '-', {
								text : '在线流程设计',
								iconCls : 'btn-flow-design',
								scope : this,
								handler : this.onlineFlowDesign,
								hidden : !isGranted('_FlowAdd')
							}, '-', {
								text : '发布 Jbpm Xml流程',
								iconCls : 'btn-add',
								scope : this,
								handler : this.deployJbpm,
								hidden : !isGranted('_FlowAdd')
							}, '-', {
								text : '删除流程',
								iconCls : 'btn-del',
								scope : this,
								handler : this.removeSelRs,
								hidden : !isGranted('_FlowDel')
							}],
					// 使用RowActions
					rowActions : true,
					url : __ctxPath + "/flow/listProDefinition.do",
					fields : ['defId', 'proType', 'name', 'createtime',
							'description', 'deployId', 'newVersion', 'status',
							'drawDefXml'],
					columns : [{
								header : '分类名称',
								dataIndex : 'proType',
								sortable : true,
								renderer : function(value) {
									if (value != null)
										return value.typeName;
									else
										return '<font color=\'red\'>未定义</font>';
								}
							}, {
								header : '流程的名称',
								dataIndex : 'name',
								sortable : true
							}, {
								header : '描述',
								dataIndex : 'description'
							}, {
								header : '创建时间',
								dataIndex : 'createtime',
								sortable : true
							}, {
								header : '工作流id',
								dataIndex : 'deployId',
								hidden : 'true'
							}, {
								header : '版本号',
								dataIndex : 'newVersion',
								sortable : true
							}, {
								header : '状态',
								dataIndex : 'status',
								sortable : true,
								renderer : function(value) {
									if (value != null && value == 1) {
										return '<font color="green">激活</font>';
									} else {
										return '<font color="red">禁用</font>';
									}
								},
								editor : new Ext.form.ComboBox({
											allowBlank : false,
											editable : false,
											mode : 'local',
											triggerAction : 'all',
											store : [['0', '禁用'], ['1', '激活']],
											listeners : {
												scope : this,
												'change' : this.changeStatus
											}
										})
							}, new Ext.ux.grid.RowActions({
										header : '管理',
										width : 200,
										actions : [{
											iconCls : 'btn-del',
											qtip : '删除',
											style : 'margin:0 2px 0 2px',
											fn : function(record) {
												if (isGranted('_FlowDel')
														&& record.data.isDefault != 1)
													return true;
												return false;
											}
										}, {
											iconCls : 'btn-flow-design',
											qtip : '编辑在线流程',
											style : 'margin: 0 2px 0 2px',
											fn : function(record) {
												if (record.data.drawDefXml != null
														&& isGranted('_FlowEdit'))
													return true;
												return false;
											}
										}, {
											iconCls : 'btn-edit',
											qtip : '编辑',
											style : 'margin:0 2px 0 2px',
											fn : function(record) {
												if (isGranted('_FlowEdit'))
													return true;
												return false;
											}
										}, {
											iconCls : 'btn-detail',
											qtip : '查看',
											style : 'margin: 0 2px 0 2px',
											fn : function(record) {
												if (record.data.deployId != null
														&& isGranted('_FlowQuery'))
													return true;
												return false;
											}
										}, {
											iconCls : 'btn-setting',
											qtip : '流程设置',
											style : 'margin: 0 2px 0 2px',
											fn : function(record) {
												if (record.data.deployId != null
														&& isGranted('_FlowSetting'))
													return true;
												return false;
											}
										}, {
											iconCls : 'btn-newFlow',
											qtip : '启动流程',
											style : 'margin: 0 2px 0 2px',
											fn : function(record) {
												if (record.data.deployId != null)
													return true;
												return false;
											}
										}, {
											iconCls : 'btn-shared',
											qtip : '设置权限',
											style : 'margin: 0 2px 0 2px',
											fn : function(record) {
												if (isGranted('_FlowSetting'))
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
					title : '流程定义管理',
					layout : 'border',
					items : [this.searchPanel, this.gridPanel]
				});
	},

	// 按条件搜索
	search : function() {
		$search({
					searchPanel : this.searchPanel,
					gridPanel : this.gridPanel
				});
	},
	// 重置(清空)查询表单
	reset : function() {
		this.searchPanel.getForm().reset();
	},
	// 重新加载分类树
	reloadType : function() {
		this.typeTreePanel.root.reload();
		// 刷新gridPanel
		this.gridPanel.getStore().reload();
	},
	// 添加分类
	addType : function() {
		var parentId = this.typeTreePanel.selectedNode.id;
		new GlobalTypeForm({
					parentId : parentId,
					catKey : this.catKey,
					scope : this,
					callback : this.reloadType
				}).show();
	},
	// 编辑分类
	editType : function() {
		var proTypeId = this.typeTreePanel.selectedNode.id;
		if (proTypeId == 0)
			return;
		new GlobalTypeForm({
					proTypeId : proTypeId,
					scope : this,
					callback : this.reloadType
				}).show();
	},
	// 删除分类
	delType : function() {
		var proTypeId = this.typeTreePanel.selectedNode.id;
		if (proTypeId == 0)
			return;
		$postDel({
					msg : '您确认要删除所选分类吗？',
					url : __ctxPath + '/system/multiDelGlobalType.do',
					ids : proTypeId,
					scope : this,
					callback : this.reloadType
				});
	},
	// 编辑分类权限
	editTypeRight : function() {
		var proTypeId = this.typeTreePanel.selectedNode.id;
		new ProDefRightsForm({
					proTypeId : proTypeId,
					scope : this
				}).show();
	},
	// 分类节点点击
	typeNodeClick : function() {
		var typeId = this.typeTreePanel.selectedNode.id;
		var store = this.gridPanel.getStore();
		// 带上查询条件
		var name = this.searchPanel.getCmpByName('Q_name_S_LK').getValue();
		var description = this.searchPanel.getCmpByName('Q_description_S_LK')
				.getValue();
		store.baseParams = {
			typeId : typeId,
			'Q_name_S_LK' : name,
			'Q_description_S_LK' : description
		};
		this.gridPanel.getBottomToolbar().moveFirst();
	},
	// 刷新gridPanel
	reloadGridPanel : function() {
		this.gridPanel.getStore().reload();
	},
	// 在线编辑流程
	onlineFlowDesign : function() {
		window.open(__ctxPath + '/bpm/bpmDesign.do', '_blank');
	},
	// 发布Jbpm 流程
	deployJbpm : function() {
		new ProDefinitionForm({
					defId : null,
					scope : this,
					callback : this.reloadGridPanel
				}).show();
	},
	// 把选中ID删除
	removeSelRs : function() {
		$delGridRs({
					url : __ctxPath + '/flow/multiDelProDefinition.do',
					grid : this.gridPanel,
					idName : 'defId'
				});
	},
	// 行双击事件
	rowClick : function(grid, rowindex, e) {
		this.row = rowindex;
		// grid.getSelectionModel().each(function(record) {
		// this.defId = record.data.defId;
		// this.editRs().call(this);
		// },this);
	},
	// 改变行的状态
	changeStatus : function(field, newValue, oldValue) {
		var record = this.gridPanel.getStore().getAt(this.row);
		if (newValue != oldValue) {
			Ext.Ajax.request({
						url : __ctxPath + '/flow/updateProDefinition.do',
						params : {
							'proDefinition.defId' : record.data.defId,
							'proDefinition.status' : newValue
						},
						method : 'POST',
						scope : this,
						success : function(response, options) {
							Ext.ux.Toast.msg('操作信息', '修改成功！');
							this.reloadGridPanel();
						},
						failure : function(response, options) {
							Ext.ux.Toast.msg('操作信息', '操作出错，请联系管理员！');
						}
					});
		}

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
		this.defId = record.data.defId;
		this.name = record.data.name;
		switch (action) {
			case 'btn-del' :
				this.removeRs.call(this);
				break;
			case 'btn-edit' :
				this.editRs.call(this);
				break;
			case 'btn-detail' :
				this.detailRs.call(this);
				break;
			case 'btn-flow-design' :
				this.flowDesignRs.call(this);
				break;
			case 'btn-setting' :
				this.settingRs.call(this);
				break;
			case 'btn-newFlow' :
				this.newFlowRs.call(this);
				break;
			case 'btn-shared' :
				this.sharedRs.call(this);
				break;
			default :
				break;
		}
	},
	// 删除流程定义
	removeRs : function() {
		$postDel({
					msg : '注意：删除该流程定义，该流程下的所有相关数据也一并删除，</br>并不能恢复，您确认要删除该记录吗？',
					url : __ctxPath + '/flow/multiDelProDefinition.do',
					ids : this.defId,
					scope : this,
					callback : this.reloadGridPanel
				});
	},
	// 编辑流程定义
	editRs : function() {
		new ProDefinitionForm({
					defId : this.defId,
					scope : this,
					callback : this.reloadGridPanel
				}).show();
	},
	// 查看明细
	detailRs : function() {
		App.clickTopTab('ProDefinitionDetail_' + this.defId, {
					defId : this.defId,
					name : this.name
				});
	},
	// 编辑在线流程
	flowDesignRs : function() {
		window.open(__ctxPath + '/bpm/bpmDesign.do?defId=' + this.defId,
				'flowDesign' + this.defId);
	},
	// 流程设置
	settingRs : function() {
		App.clickTopTab('ProDefinitionSetting_' + this.defId, {
					defId : this.defId,
					name : this.name
				});
	},
	// 新建流程
	newFlowRs : function() {
		App.clickTopTab('ProcessRunStart_' + this.defId, {
					defId : this.defId,
					flowName : this.name
				});
	},
	// Pro设置流程权限
	sharedRs : function() {
		new ProDefRightsForm({
					defId : this.defId
				}).show();
	}
});