Ext.ns('BookTypeView');
/**
 * @author:
 * @class BookTypeView
 * @extends Ext.Panel
 * @description 图书类别列表
 * @company 杭州梦德软件有限公司
 * @createtime:2010-08-23
 */
var BookTypeView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		BookTypeView.superclass.constructor.call(this, {
					id : 'BookTypeView',
					title : '图书类别列表',
					iconCls : 'menu-book-type',
					region : 'center',
					layout : 'border',
					items : [this.searchPanel, this.gridPanel]
				});
	},// end of constructor

	// 初始化组件
	initUIComponents : function() {
		// 初始化搜索条件Panel
		this.searchPanel = new HT.SearchPanel({
					region : 'north',
					layout : 'form',
					colNums : 2,
					keys : {
						key : Ext.EventObject.ENTER,
						fn : this.search,
						scope : this
					},
					labelWidth : 155,
					items : [{
								fieldLabel : '请输入查询条件: 图书类别',
								xtype : 'textfield',
								name : 'Q_typeName_S_LK',
								maxLength : 150
							}, {
								xtype : 'button',
								text : '查询',
								iconCls : 'search',
								scope : this,
								handler : this.search
							}]
				});// end of the searchPanel
				
		//顶端栏目条
		this.topbar = new Ext.Toolbar({
			items : [{
						iconCls : 'btn-add',
						text : '添加图书类别',
						xtype : 'button',
						scope : this,
						handler : this.addBookType,
						hidden : !isGranted('_BookTypeAdd')
					}, '-', {
						iconCls : 'btn-del',
						text : '删除图书类别',
						xtype : 'button',
						scope : this,
						handler : this.removeSelRs,
						hidden : !isGranted('_BookTypeDel')
					}]
		});
		
		// 列表面板
		this.gridPanel = new HT.EditorGridPanel({
					region : 'center',
					tbar : this.topbar,
					sort : [{field:"typeId",direction:"DESC"}],
					// 使用RowActions
					rowActions : true,
					url : __ctxPath + '/admin/listBookType.do',
					fields : [{
								name : 'typeId',
								type : 'int'
							}, 'typeName'],
					columns : [{
								header : 'typeId',
								dataIndex : 'typeId',
								hidden : true
							}, {
								header : '图书类别',
								dataIndex : 'typeName'
							}, new Ext.ux.grid.RowActions({
										header : '管理',
										width : 200,
										actions : [{
													iconCls : 'btn-del',
													qtip : '删除',
													style : 'margin:0 3px 0 3px',
													fn : function(record) {
														if (isGranted('_BookTypeDel'))
															return true;
														return false;
													}
												}, {
													iconCls : 'btn-edit',
													qtip : '编辑',
													style : 'margin:0 3px 0 3px',
													fn : function(record) {
														if (isGranted('_BookTypeEdit'))
															return true;
														return false;
													}
												}],
										listeners : {
											scope : this,
											'action' : this.onRowAction
										}
									})],
					// 为Grid增加双击事件,双击行可编辑
					listeners : {
						scope : this,
						'rowdblclick' : this.rowdblclick
					}
				});

	},// end of the initUIComponents
	// 查询
	search : function() {
		$search({
					searchPanel : this.searchPanel,
					gridPanel : this.gridPanel
				});
	},
	// 重新加载列表
	reloadGrid : function() {
		// 刷新gridPanel
		this.gridPanel.getStore().reload();
	},
	// 新增图书类别
	addBookType : function() {
		new BookTypeForm({
					scope : this,
					callback : this.reloadGrid
				}).show();
	},
	// 删除选择图书类别
	removeSelRs : function() {
		$delGridRs({
					url : __ctxPath + '/admin/multiDelBookType.do',
					grid : this.gridPanel,
					idName : 'typeId'
				});
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
		this.typeId = record.data.typeId;
		this.typeName = record.data.typeName;
		switch (action) {
			case 'btn-del' :
				this.removeRs.call(this);
				break;
			case 'btn-edit' :
				this.editRs.call(this);
				break;
			default :
				break;
		}
	},

	// 删除图书类别
	removeRs : function() {
		$postDel({
					url : __ctxPath + '/admin/multiDelBookType.do',
					ids : this.typeId,
					grid : this.gridPanel
				});
	},
	// 编辑图书类别
	editRs : function() {
		new BookTypeForm({
					typeId : this.typeId,
					scope : this,
					callback : this.reloadGrid
				}).show();
	},
	// 为Grid增加双击事件,双击行可编辑
	rowdblclick : function(gridPanel, rowindex, e) {
		gridPanel.getSelectionModel().each(function(rec) {
					this.typeId = rec.data.typeId;
					if (isGranted('_BookTypeEdit')) {
						this.editRs();
					}
				}, this);
	}
});
