/**
 * @author:
 * @class ArchRollView
 * @extends Ext.Panel
 * @description 案卷管理
 * @company 杭州梦德软件有限公司
 * @createtime:
 */
ArchRollView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		ArchRollView.superclass.constructor.call(this, {
					title : '案卷管理',
					id : 'ArchRollView',
					iconCls : "menu-archRoll",
					region : 'center',
					layout : 'border',
					items : [this.searchPanel, this.leftPanel, this.gridPanel]
				});
	},// end of constructor
	// 初始化组件
	initUIComponents : function() {
		// 初始化搜索条件Panel
		this.searchPanel = new HT.SearchPanel({
					layout : 'form',
					region : 'north',
					width : '100%',
					height : 80,
					keys : {
						key : Ext.EventObject.ENTER,
						fn : this.search.createCallback(this),
						scope : this
					},
					items : [{
						border : false,
						layout : 'column',
						layoutConfig : {
							padding : '5',
							align : 'middle'
						},
						defaults : {
							xtype : 'label'
						},
						items : [{
							columnWidth : .3,
							style : 'margin-top:8px;',
							xtype : 'container',
							layout : 'form',
							items : [{
								anchor : '99%',
								fieldLabel : '全宗号',
								name : 'Q_archFond.afNo_S_LK',
								flex : 1,
								xtype : 'combo',
								mode : 'local',
								editable : false,
								triggerAction : 'all',
								store : new Ext.data.JsonStore({
											url : __ctxPath
													+ "/arch/listArchFond.do",
											autoLoad : true,
											autoShow : true,
											root : 'result',

											fields : ['afNo', 'afNo']
										}),
								valueField : 'afNo',
								displayField : 'afNo'
							}, {
								anchor : '99%',
								fieldLabel : '案卷号',
								name : 'Q_rollNo_S_LK',
								flex : 1,
								xtype : 'textfield'
							}]
						}, {
							columnWidth : .3,
							style : 'margin-top:8px;',
							xtype : 'container',
							layout : 'form',
							items : [{
										anchor : '99%',
										fieldLabel : '案卷名称',
										name : 'Q_rolllName_S_LK',
										flex : 1,
										xtype : 'textfield'

									}, {
										anchor : '99%',
										fieldLabel : '状态',
										hiddenName : 'Q_status_SN_EQ',
										flex : 1,
										xtype : 'combo',
										mode : 'local',
										editable : false,
										triggerAction : 'all',
										store : [['', '全部'], ['1', '正常'],
												['0', '销毁']]
									}]
						}, {
							columnWidth : .3,
							style : 'margin-top:8px;',
							xtype : 'container',
							layout : 'form',
							items : [{
										anchor : '99%',
										fieldLabel : '保管期限',
										name : 'Q_timeLimit_S_LK',
										editable : true,
										lazyInit : false,
										forceSelection : false,
										xtype : 'diccombo',
										nodeKey : 'file_time_limit'
									}, {
										anchor : '99%',
										fieldLabel : '开放形式',
										name : 'Q_openStyle_S_LK',
										flex : 1,
										editable : true,
										lazyInit : false,
										forceSelection : false,
										xtype : 'diccombo',
										nodeKey : 'file_open_form'
									}]
						}, {
							style : 'margin-top:8px;',
							xtype : 'container',
							layout : 'form',
							items : [{
										fieldLabel : '案卷分类ID',
										name : 'Q_globalType.proTypeId_L_EQ',
										flex : 1,
										xtype : 'hidden'
									}]
						}, {
							fieldLabel : '案卷分类名称',
							name : 'Q_typeName_S_LK',
							flex : 1,
							xtype : 'hidden'
					},{
							style : 'margin-top:8px;margin-left:5px;',
							layout : 'form',
							xtype : 'container',
							defaultType : 'button',
							items : [{
										iconCls : 'search',
										text : '查询',
										scope : this,
										handler : this.search
									}, {
										iconCls : 'reset',
										style : 'margin-top:5px;',
										text : '重置',
										scope : this,
										handler : this.reset
									}]
						}]
					}]
				});// end of searchPanel
		// 左边树面板
		this.leftPanel = new htsoft.ux.TreePanelEditor({
					layout : 'fit',
					region : 'west',
					collapsible : true,
					split : true,
					width : 200,
					title : '案卷分类',
					url : __ctxPath + '/system/treeGlobalType.do?catKey=AR_RL',
					scope : this,
					autoScroll : true,
					contextMenuItems : [{
								text : '新建分类',
								iconCls : 'btn-add',
								scope : this,
								handler : this.treeAddClick
							}, {
								text : '修改类别',
								iconCls : 'btn-edit',
								scope : this,
								handler : this.treeEditClick
							}, {
								text : '删除类别',
								iconCls : 'btn-del',
								scope : this,
								handler : this.treeDelClick
							}],
					// 点击分类树节点
					onclick : this.leftTreeClick
				});
		// 列表面板
		this.gridPanel = new HT.GridPanel({
					region : 'center',
					tbar :  [{
						iconCls : 'btn-add',
						text : '添加案卷',
						scope : this,
						handler : this.createRs
					}, {
						iconCls : 'btn-del',
						text : '删除案卷',
						xtype : 'button',
						scope : this,
						handler : this.removeSelRs
					}],
					// 使用RowActions
					rowActions : true,
					url : __ctxPath + "/arch/listArchRoll.do",
					fields : [{
								name : 'rollId',
								type : 'int'
							}, 'createTime', 'updateTime', 'creatorName',
							'creatorId', 'status', 'proTypeId', 'typeName',
							'openStyle', 'archFond', 'rolllName', 'rollNo',
							'catNo', 'timeLimit', 'startTime', 'endTime',
							'author', 'setupTime', 'checker', 'keyWords',
							'editCompany', 'editDep', 'decp'],
					columns : [{
								header : 'rollId',
								dataIndex : 'rollId',
								hidden : true
							}, {
								header : '全宗号',
								dataIndex : 'archFond',
								renderer : function(v) {
									if (v)
										return v.afNo;
								}
							}, {
								header : '案卷号',
								dataIndex : 'rollNo'
							}, {
								header : '案卷名称',
								dataIndex : 'rolllName'
							}, {
								header : '案卷分类',
								dataIndex : 'typeName'
							}, {
								header : '目录号',
								dataIndex : 'catNo'
							}, {
								header : '保管期限',
								dataIndex : 'timeLimit'
							}, {
								header : '状态',
								dataIndex : 'status',
								renderer : function(v) {
									switch (v) {
										case 1 :
											return '正常';
											break;
										case 0 :
											return '销毁';
											break;
									}
								}
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
									})],
					// end of columns
					listeners : {
						scope : this,
						'rowdblclick' : this.rowClick
					}
				});

	},// end of the initComponents()
	/**
	 * 刷新左边的树
	 */
	reloadTree : function() {
		this.leftPanel.root.reload();
	},
	/**
	 * 左边分类树单击事件
	 */
	leftTreeClick : function(node) {

		var proTypeId = node.id;
		var typeName = node.text;
		if (proTypeId == '0') {
			this.getCmpByName('Q_globalType.proTypeId_L_EQ').setValue('');
		} else {
			this.getCmpByName('Q_globalType.proTypeId_L_EQ')
					.setValue(proTypeId);
		}
		this.search();
	},
	/**
	 * 新建分类 处理函数
	 */
	treeAddClick : function() {
		var parentId = this.leftPanel.selectedNode.id;
		new GlobalTypeForm({
					parentId : parentId,
					catKey : 'AR_RL',
					scope : this,
					callback : this.reloadTree
				}).show();

	},
	/**
	 * 修改分类
	 */
	treeEditClick : function() {
		var proTypeId = this.leftPanel.selectedNode.id;
		new GlobalTypeForm({
					proTypeId : proTypeId,
					scope : this,
					callback : this.reloadTree
				}).show();
	},
	/**
	 * 删除分类 处理函数
	 */
	treeDelClick : function() {
		var proTypeId = this.leftPanel.selectedNode.id;
		var ids = Array();
		ids.push(proTypeId);
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
										Ext.ux.Toast.msg('操作信息', '成功删除该产品分类！');
										this.leftPanel.root.reload();
										this.gridPanel.getStore().reload();
									},
									failure : function(response, options) {
										Ext.ux.Toast
												.msg('操作信息', '操作出错，请联系管理员！');
									}
								});
					}
				}, this);

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
					new ArchRollForm({
								rollId : rec.data.rollId,
								callback : function() {
									grid.getStore().reload();
								}
							}).show();
				});
	},
	// 创建记录
	createRs : function() {
		var grid = this.gridPanel;
		new ArchRollForm({
					proTypeId : this
							.getCmpByName('Q_globalType.proTypeId_L_EQ')
							.getValue(),
					typeName : this.getCmpByName('Q_typeName_S_LK').getValue(),
					callback : function() {
						grid.getStore().reload();
					}
				}).show();
	},
	// 按ID删除记录
	removeRs : function(id) {
		$postDel({
					url : __ctxPath + '/arch/multiDelArchRoll.do',
					ids : id,
					grid : this.gridPanel
				});
	},
	// 把选中ID删除
	removeSelRs : function() {
		$delGridRs({
					url : __ctxPath + '/arch/multiDelArchRoll.do',
					grid : this.gridPanel,
					idName : 'rollId'
				});
	},
	// 编辑Rs
	editRs : function(record) {
		var grid = this.gridPanel;
		new ArchRollForm({
					rollId : record.data.rollId,
					callback : function() {
						grid.getStore().reload();
					}
				}).show();
	},
	// 行的Action
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.removeRs.call(this, record.data.rollId);
				break;
			case 'btn-edit' :
				this.editRs.call(this, record);
				break;
			default :
				break;
		}
	}
});
