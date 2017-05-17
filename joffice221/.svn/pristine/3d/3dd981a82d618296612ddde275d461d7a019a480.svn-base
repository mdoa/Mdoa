/**
 * @author:
 * @class RollFileView
 * @extends Ext.Panel
 * @description [RollFile]管理
 * @company 杭州梦德软件有限公司
 * @createtime:
 */
RollFileView = Ext.extend(Ext.Panel, {
	viewConfig : [],
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		RollFileView.superclass.constructor.call(this, {
					title : '卷内文件管理',
					id : 'RollFileView',
					iconCls : "menu-rollFile",
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
					colNums : 3,
					items : [{
								fieldLabel : '全宗号',
								hiddenName : 'Q_archRoll.archFondId_L_EQ',
								flex : 1,
								xtype : 'combo',
								mode : 'local',
								editable : true,
								lazyInit : false,
								forceSelection : false,
								triggerAction : 'all',
								store : new Ext.data.JsonStore({
											url : __ctxPath
													+ "/arch/listArchFond.do",
											autoLoad : true,
											autoShow : true,
											root : 'result',
											fields : ['archFondId', 'afNo']
										}),
								valueField : 'archFondId',
								displayField : 'afNo',
								listeners : {
									scope : this,
									'select' : this.selectClick
								}
							}, {
								fieldLabel : '案卷号',
								name : 'Q_archRoll.rollNo_S_LK',
								allowBlank : true,
								flex : 1,
								xtype : 'combo',
								mode : 'local',
								editable : true,
								lazyInit : false,
								forceSelection : false,
								triggerAction : 'all',
								store : new Ext.data.JsonStore({
											url : __ctxPath
													+ "/arch/listArchRoll.do",
											autoLoad : true,
											autoShow : true,
											// reader configs
											root : 'result',
											idProperty : 'rollId',
											fields : ['rollId', 'rollNo',
													'afNo']
										}),
								valueField : 'rollId',
								displayField : 'rollNo',
								listeners : {}
							}, {
								fieldLabel : '文件编号',
								name : 'Q_fileNo_S_LK',
								flex : 1,
								xtype : 'textfield'
							}, {
								fieldLabel : '文件题名',
								name : 'Q_fileName_S_LK',
								flex : 1,
								xtype : 'textfield'
							}, {
								fieldLabel : '目录号',
								name : 'Q_catNo_S_LK',
								flex : 1,
								xtype : 'textfield'
							}, {
								fieldLabel : '密级',
								name : 'Q_secretLevel_S_LK',
								flex : 1,
								editable : true,
								lazyInit : false,
								forceSelection : false,
								xtype : 'diccombo',
								nodeKey : 'file_security'
							}, {
								fieldLabel : '保管期限',
								name : 'Q_timeLimit_S_LK',
								flex : 1,
								editable : true,
								lazyInit : false,
								forceSelection : false,
								xtype : 'diccombo',
								nodeKey : 'file_time_limit'
							}, {
								fieldLabel : '开放形式',
								name : 'Q_openStyle_S_LK',
								flex : 1,
								editable : true,
								lazyInit : false,
								forceSelection : false,
								xtype : 'diccombo',
								nodeKey : 'file_open_form'
							}, {
								fieldLabel : '归档状态',
								hiddenName : 'Q_archStatus_SN_EQ',
								flex : 1,
								xtype : 'combo',
								mode : 'local',
								editable : false,
								triggerAction : 'all',
								store : [['', '全部'], ['0', '未归档'], ['1', '已归档']]
							},

							{
								fieldLabel : '分类Id',
								name : 'Q_globalType.proTypeId_L_EQ',
								flex : 1,
								xtype : 'hidden'
							}, {
								fieldLabel : '分类Name',
								name : 'Q_globalType.typeName_S_EQ',
								flex : 1,
								xtype : 'hidden'
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

		this.leftPanel = new Ext.Panel({
					title : '卷内文件分类',
					region : 'west',
					layout : 'fit',
					collapsible : true,
					split : true,
					frame : false,
					width : 200,
					border : true,
					items : [{
						xtype : 'treePanelEditor',
						name : 'RollFileGlobalTypeTree',
						split : true,
						rootVisible : false,
						border : false,
						frame : false,
						autoScroll : true,
						scope : this,
						url : __ctxPath
								+ '/system/treeGlobalType.do?catKey=AR_RLF',
						onclick : this.treePanelClick,
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
								}]
					}]
				}

		);
		this.topbar = new Ext.Toolbar({
					items : [{
								iconCls : 'btn-add',
								text : '添加卷内文件',
								xtype : 'button',
								scope : this,
								handler : this.createRs
							}, {
								iconCls : 'btn-del',
								text : '删除卷内文件',
								xtype : 'button',
								scope : this,
								handler : this.removeSelRs
							}]
				});
		this.gridPanel = new HT.GridPanel({
			region : 'center',
			tbar : this.topbar,
			// 使用RowActions
			rowActions : true,
			id : 'RollFileGrid',
			url : __ctxPath + "/arch/listRollFile.do",
			fields : [{
						name : 'rollFileId',
						type : 'int'
					}, 'afNo', 'createTime', 'creatorName', 'creatorId',
					'archStatus', 'proTypeId', 'typeName', 'openStyle',
					'archRoll', 'rollNo', 'fileName', 'fileNo', 'fileNo',
					'catNo', 'seqNo', 'pageNo', 'pageNums', 'secretLevel',
					'timeLimit', 'keyWords', 'keyWords', 'content', 'fileTime',
					'notes', 'dutyPerson', 'globalType'],
			columns : [{
						header : 'rollFileId',
						dataIndex : 'rollFileId',
						hidden : true
					}, {
						header : '全宗号',
						dataIndex : 'archRoll',
						renderer : function(archRoll) {
							if (archRoll) {
								return archRoll.archFond.afNo;
							}
						}
					}, {
						header : '案卷号',
						dataIndex : 'archRoll',
						renderer : function(archRoll) {
							if (archRoll) {
								return archRoll.rollNo;
							}
						}
					}, {
						header : '所属分类',
						dataIndex : 'globalType',
						renderer : function(globalType) {
							if (globalType) {
								return globalType.typeName;
							}
						}
					}, {
						header : '文件题名',
						dataIndex : 'fileName'
					}, {
						header : '文件编号',
						dataIndex : 'fileNo'
					}, {
						header : '密级',
						dataIndex : 'secretLevel'
					}, {
						header : '保管期限',
						dataIndex : 'timeLimit'
					}, {
						header : '开放形式',
						dataIndex : 'openStyle'
					}, {
						header : '归档状态',
						dataIndex : 'archStatus',
						renderer : function(v) {
							switch (v) {
								case 0 :
									return '未归档';
									break;
								case 1 :
									return '已归档';
									break;
							}
						}
					}, new Ext.ux.grid.RowActions({
								header : '管理',
								width : 100,
								actions : [{
											iconCls : 'btn-readdocument',
											qtip : '预览附件',
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

		this.gridPanel.addListener('rowdblclick', this.rowClick);

	},// end of the initComponents()
	/**
	 * 全宗号选择
	 */
	selectClick : function(combo, record, index) {
		this.getCmpByName('Q_archRoll.rollNo_S_LK').getStore().load({
					params : {

						'Q_archFond.archFondId_L_EQ' : record.data.archFondId
					}
				});
		this.getCmpByName('Q_archRoll.rollNo_S_LK').reset();
	},
	/**
	 * treePanel单击事件
	 */
	treePanelClick : function(node) {
		var proTypeId = node.id;
		var typeName = node.text;

		if (proTypeId == '0') {
			this.getCmpByName('Q_globalType.proTypeId_L_EQ').setValue('');
			this.getCmpByName('Q_globalType.typeName_S_EQ').setValue('');
		} else {
			this.getCmpByName('Q_globalType.typeName_S_EQ').setValue(typeName);
		}

		this.search();

	},
	/**
	 * 新建分类处理函数
	 */
	treeAddClick : function() {
		var globalTypeTree = this.getCmpByName('RollFileGlobalTypeTree');
		var parentId = globalTypeTree.selectedNode.id;
		var globalTypeForm = new GlobalTypeForm({
					parentId : parentId,
					catKey : 'AR_RLF',
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
		var globalTypeTree = this.getCmpByName('RollFileGlobalTypeTree');
		var proTypeId = globalTypeTree.selectedNode.id;

		var globalTypeForm = new GlobalTypeForm({
					proTypeId : proTypeId,
					callback : function() {
						globalTypeTree.root.reload();
					}
				});
		globalTypeForm.show();
	},
	/**
	 * 删除分类
	 */
	treeDelClick : function() {
		var globalTypeTree = this.getCmpByName('RollFileGlobalTypeTree');
		var proTypeId = globalTypeTree.selectedNode.id;
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
									success : function(response, options) {
										Ext.ux.Toast.msg('操作信息', '成功删除该产品分类！');
										globalTypeTree.root.reload();
										this.gridPanel.getStore().reload();
									},
									failure : function(response, options) {
										Ext.ux.Toast
												.msg('操作信息', '操作出错，请联系管理员！');
									}
								});
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
					new RollFileForm({
								rollFileId : rec.data.rollFileId,
								callback : function() {
									grid.getStore().reload();
								}
							}).show();
				});
	},
	// 创建记录
	createRs : function() {
		new RollFileForm({
					proTypeId : this
							.getCmpByName('Q_globalType.proTypeId_L_EQ')
							.getValue(),
					typeName : this.getCmpByName('Q_globalType.typeName_S_EQ')
							.getValue(),
					callback : function() {
						grid.getStore().reload();
					}
				}).show();
	},
	// 按ID删除记录
	removeRs : function(id) {
		$postDel({
					url : __ctxPath + '/arch/multiDelRollFile.do',
					ids : id,
					grid : this.gridPanel
				});
	},
	// 把选中ID删除
	removeSelRs : function() {
		$delGridRs({
					url : __ctxPath + '/arch/multiDelRollFile.do',
					grid : this.gridPanel,
					idName : 'rollFileId'
				});
	},
	// 编辑Rs
	editRs : function(record) {
		var grid = this.gridPanel;
		new RollFileForm({
					rollFileId : record.data.rollFileId,
					callback : function() {
						grid.getStore().reload();
					}
				}).show();
	},
	viewFile : function(record) {
		Ext.Ajax.request({
					url : __ctxPath + '/arch/listRollFileList.do',
					params : {
						'Q_rollFile.rollFileId_L_EQ' : record.data.rollFileId,
						sort : 'sn',
						dir : 'ASC'
					},
					method : 'POST',
					success : function(response, opts) {
						var obj = Ext.decode(response.responseText);
						var viewConfig = [];
						for (var i = 0; i < obj.result.length; i++) {
							viewConfig.push({
										fileName : obj.result[i].fileAttach.fileName,
										filePath : obj.result[i].fileAttach.filePath
									});
						}
						new ViewFileWindow({
									viewConfig : viewConfig,
									startIndex : 0
								}).show();
					},
					failure : function(response, opts) {

					}

				});

	},
	// 行的Action
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-readdocument' :
				this.viewFile.call(this, record);
				break;
			case 'btn-edit' :
				this.editRs.call(this, record);
				break;
			default :
				break;
		}
	}
});
