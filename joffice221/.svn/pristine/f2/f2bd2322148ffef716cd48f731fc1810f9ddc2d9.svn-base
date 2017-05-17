/**
 * @author:
 * @class TypeKeyView
 * @extends Ext.Panel
 * @description 分类类型Key管理
 * @company 杭州梦德软件有限公司
 * @createtime:2010-01-16
 */
TypeKeyView = Ext.extend(Ext.Panel, {
	/**
	 * 构造函数
	 * 
	 */
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		TypeKeyView.superclass.constructor.call(this, {
					id : 'TypeKeyView',
					title : '分类标识键管理',
					region : 'center',
					layout : 'border',
					iconCls : 'menu-typeKey',
					items : [this.searchPanel, this.gridPanel]
				});
	},// end of constructor
	/**
	 * 初始化组件
	 */
	initUIComponents : function() {
		/**
		 * 搜索面板
		 */
		this.searchPanel = new HT.SearchPanel({
					layout : 'form',
					region : 'north',
					colNums : 4,
					keys : {
						key : Ext.EventObject.ENTER,
						fn : this.search,
						scope : this
					},
					labelWidth : 160,
					items : [{
								fieldLabel : '请输入查询条件: 分类标识名',
								xtype : 'textfield',
								name : 'Q_typeName_S_LK',
								maxLength : 150
							}, {
								fieldLabel : '分类标识Key',
								xtype : 'textfield',
								name : 'Q_typeKey_S_LK',
								maxLength : 125,
								labelWidth : 75
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

		// 顶端栏目条
		this.topbar = new Ext.Toolbar({
					items : [{
								iconCls : 'btn-add',
								text : '添加分类标识',
								xtype : 'button',
								scope : this,
								handler : this.createRecord
							}, '-', {
								iconCls : 'btn-del',
								text : '删除分类标识',
								xtype : 'button',
								scope : this,
								handler : this.removeSelRs
							}]
				});

		// 列表面板
		this.gridPanel = new HT.EditorGridPanel({
					region : 'center',
					tbar : this.topbar,
					sort : [{
								field : "sn",
								direction : "ASC"
							}],
					// 使用RowActions
					rowActions : true,
					url : __ctxPath + "/system/listTypeKey.do",
					fields : ['typekeyId', 'typeName', 'typeKey', 'sn'],
					columns : [{
								header : 'typekeyId',
								dataIndex : 'typekeyId',
								hidden : true
							}, {
								header : '分类标识名',
								dataIndex : 'typeName'
							}, {
								header : '分类标识key',
								dataIndex : 'typeKey'
							}, {
								header : '排序',
								dataIndex : 'sn'
							}, new Ext.ux.grid.RowActions({
										header : '管理',
										width : 200,
										actions : [{
													iconCls : 'btn-del',
													qtip : '删除',
													style : 'margin:0 3px 0 3px',
													fn : function(record) {
														if (record.data.typekeyId > 12)
															return true;
														return false;
													}
												}, {
													iconCls : 'btn-edit',
													qtip : '编辑',
													style : 'margin:0 3px 0 3px',
													fn : function(record) {
														if (record.data.typekeyId > 12)
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
						'rowdblclick' : this.rowdblclick
					}
				});
	},// end of the initComponents()

	/**
	 * 查询
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
	reset : function() {
		this.searchPanel.getForm().reset();
	},
	/**
	 * 刷新gridPanel
	 */
	reloadGridPanel : function() {
		this.gridPanel.getStore().reload();
	},
	/**
	 * 添加记录
	 */
	createRecord : function() {
		new TypeKeyForm({
					scope : this,
					callback : this.reloadGridPanel
				}).show();
	},
	/**
	 * 按IDS删除记录
	 * 
	 * @param {}
	 *            ids
	 */
	removeSelRs : function(ids) {
		var store = this.gridPanel.getStore();
		if(store.getCount()>12){
			$delGridRs({
				url : __ctxPath + '/system/multiDelTypeKey.do',
				grid : this.gridPanel,
				idName : 'typekeyId'
			});
		}else{
			Ext.ux.Toast.msg('操作信息', '该选择的分类记录不能删除!');
		}
	},
	/**
	 * 删除多条记录
	 */
	delByIds : function(typekeyId) {
		$postDel({
					url : __ctxPath + '/system/multiDelTypeKey.do',
					ids : typekeyId,
					grid : this.gridPanel
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
	rowdblclick : function(grid, rowindex, e) {
		grid.getSelectionModel().each(function(record) {
					if (record.data.typekeyId > 12)
						this.editRecord.call(this, record.data.typekeyId);
				}, this);
	},
	/**
	 * 编辑记录
	 * 
	 * @param {}
	 *            record
	 */
	editRecord : function(typekeyId) {
		new TypeKeyForm({
					typekeyId : typekeyId,
					scope : this,
					callback : this.reloadGridPanel
				}).show();
	},
	/**
	 * 管理列中的事件处理
	 * 
	 * @param {}
	 *            grid
	 * @param {}
	 *            record
	 * @param {}
	 *            action
	 * @param {}
	 *            row
	 * @param {}
	 *            col
	 */
	onRowAction : function(gridPanel, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.delByIds(record.data.typekeyId);
				break;
			case 'btn-edit' :
				this.editRecord(record.data.typekeyId);
				break;
			default :
				break;
		}
	}
});
