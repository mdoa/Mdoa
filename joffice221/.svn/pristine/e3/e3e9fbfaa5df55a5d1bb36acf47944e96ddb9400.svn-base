Ext.ns('MyFileAttachView');
/**
 * @description MyFileAttachView我的附件信息
 * @extend Panel
 * @author YHZ
 * @createtime 2011-3-24AM 附件信息列表
 */
MyFileAttachView = Ext.extend(Ext.Panel, {
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		this.initUIComponent();
		MyFileAttachView.superclass.constructor.call(this, {
					id : 'MyFileAttachView',
					title : '我的附件',
					iconCls : 'menu-attachment',
					layout : 'border',
					region : 'center',
					autoScroll : true,
					items : [this.leftPanel, this.outPanel]
				});
	},// end of constructor
	// 初始化组件
	initUIComponent : function() {
		// 分类树
		this.treePanel = new htsoft.ux.TreePanelEditor({
					url : __ctxPath
							+ '/system/treeGlobalType.do?catKey=ATTACHFILE_TYPE',
					scope : this,
					autoScroll : true,
					onclick : this.nodeClick,
					enableDD : true
				});
		// 附件分类面板
		this.leftPanel = new Ext.Panel({
					region : 'west',
					title : '附件分类',
					layout : 'fit',
					collapsible : true,
					split : true,
					border : false,
					width : 200,
					items : [this.treePanel]
				});
		// 查询面板
		this.searchPanel = new HT.SearchPanel({
					layout : 'form',
					region : 'north',
					colNums : 7,
					keys : [{
								key : Ext.EventObject.ENTER,
								fn : this.search,
								scope : this
							}, {
								key : Ext.EventObject.ESC,
								fn : this.reset,
								scope : this
							}],
					labelWidth : 50,
					items : [{
								xtype : 'textfield',
								fieldLabel : '文件名',
								name : 'Q_fileName_S_LK',
								width : 80,
								maxLength : 125
							}, {
								xtype : 'datefield',
								fieldLabel : '创建时间',
								name : 'Q_createtime_D_GE',
								format : 'Y-m-d H:i:s',
								labelWidth : 60,
								width : 130,
								maxLength : 125
							}, {
								xtype : 'datefield',
								fieldLabel : '至：',
								name : 'Q_createtime_D_LE',
								format : 'Y-m-d H:m:s',
								labelWidth : 20,
								width : 130,
								maxLength : 125
							}, {
								xtype : 'textfield',
								fieldLabel : '扩展名',
								name : 'Q_ext_S_LK',
								labelWidth : 50,
								width : 80,
								maxLength : 125
							}, {
								xtype : 'textfield',
								fieldLabel : '上传者',
								name : 'Q_creator_S_LK',
								labelWidth : 50,
								width : 80,
								maxLength : 125
							}, {
								xtype : 'button',
								text : '查询',
								style : 'padding-left:5px;padding-right:5px;',
								iconCls : 'search',
								handler : this.search,
								scope : this
							}, {
								xtype : 'button',
								text : '重置',
								style : 'padding-left:5px;padding-right:5px;',
								iconCls : 'btn-reset',
								handler : this.reset,
								scope : this
							}]
				}); // end of this searchPanel
		// 附件列表面板
		this.gridPanel = new HT.GridPanel({
			region : 'center',
			tbar : [{
						iconCls : 'btn-del',
						text : '删除附件',
						xtype : 'button',
						scope : this,
						handler : this.removeAll
					}],
			// 使用RowActions
			rowActions : true,
			url : __ctxPath + "/system/listAllFileAttach.do",
			baseParams : {
				'Q_delFlag_N_EQ' : 0,// 查找未删除的
				'Q_creatorId_L_EQ' : curUserInfo.userId
			},
			fields : [{
						name : 'fileId',
						type : 'int'
					}, 'fileName', 'filePath', 'createtime', 'ext', 'type',
					'note', 'creator', 'fileType', 'globalType'],
			columns : [{
						header : 'fileId',
						dataIndex : 'fileId',
						hidden : true
					}, {
						header : '文件名',
						dataIndex : 'fileName'
					}, {
						header : '文件路径',
						dataIndex : 'filePath'
					}, {
						header : '创建时间',
						dataIndex : 'createtime'
					}, {
						header : '扩展名',
						dataIndex : 'ext'
					}, {
						header : '附件类型',
						dataIndex : 'fileType'
					}, {
						header : '类型名称',
						sortable : false,
						dataIndex : 'globalType',
						renderer : function(value) {
							if (value != null)
								return value.typeName;
							else
								return 'null';
						}
					}, {
						header : '说明',
						dataIndex : 'note'
					}, {
						header : '上传者',
						dataIndex : 'creator'
					}, new Ext.ux.grid.RowActions({
								header : '管理',
								width : 100,
								actions : [{
											iconCls : 'btn-del',
											qtip : '删除',
											style : 'margin:0 3px 0 3px'
										}, {
											iconCls : 'btn-detail',
											qtip : '查看',
											style : 'margin:0 3px 0 3px'
										}],
								listeners : {
									scope : this,
									'action' : this.onRowAction
								}
							})]
				// end of columns
			});// end of grid
		this.gridPanel.addListener({scope : this,'rowdblclick' : this.rowClick});
		// 我的附件列表面板
		this.outPanel = new Ext.Panel({
					region : 'center',
					title : '我的附件列表',
					layout : 'border',
					items : [this.searchPanel, this.gridPanel]
				});
	}, // end of initUIComponent
	
	/**
	 * GridPanel行点击处理事件
	 * @param {} grid
	 * @param {} rowindex
	 * @param {} e
	 */
	rowClick : function(grid, rowindex, e) {
		var rec = grid.getStore().getAt(rowindex);
		this.detail(rec.data.fileId);
	},

	/**
	 * 查看
	 */
	detail : function(id) {
		if (id != null && id != '' && id != 'undefined')
			FileAttachDetail.show(id);
	},

	/**
	 * 搜索
	 */
	search : function() {
		var searchPanel = this.searchPanel;
		var gridPanel = this.gridPanel;
		if (searchPanel.getForm().isValid()) {
			$search({
						searchPanel : searchPanel,
						gridPanel : gridPanel
					});
		}
	},

	/**
	 * 清空
	 */
	reset : function() {
		this.searchPanel.getForm().reset();
	},

	/**
	 * 删除多条记录操作
	 */
	removeAll : function() {
		$delGridRs({
					url : __ctxPath + '/system/multiDelFileAttach.do',
					grid : this.gridPanel,
					idName : 'fileId'
				});
	},
	/**
	 * 删除单个记录
	 */
	remove : function(id) {
		$postDel({
					url : __ctxPath + '/system/multiDelFileAttach.do',
					ids : id,
					grid : this.gridPanel
				});
	},

	/**
	 * 节点单击事件
	 * 
	 * @param {}
	 *            node
	 */
	nodeClick : function(node) {
		if (node != null) {
			var file_type = '';
			if (node.getDepth() > 1 && node.attributes.nodeKey != 'file-type') {
				file_type = node.attributes.nodeKey;
			}
			this.gridPanel.getStore().reload({
						params : {
							'Q_fileType_S_LK' : file_type,
							'Q_delFlag_N_EQ' : 0
							// 查找未删除的
						}
					});
		}
	},

	/**
	 * 管理列中的事件处理
	 */
	onRowAction : function(gridPanel, record, action, row, col) {
		switch (action) {
			case 'btn-detail' :
				this.detail(record.data.fileId);
				break;
			case 'btn-del' :
				this.remove(record.data.fileId);
				break;
			default :
				break;
		}
	}
});
