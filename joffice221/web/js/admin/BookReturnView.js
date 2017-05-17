Ext.ns('BookReturnView');
/**
 * 图书归还列表
 * 
 * @class BookReturnView
 * @extends Ext.Panel
 */
BookReturnView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		this.initUI();
		BookReturnView.superclass.constructor.call(this, {
					id : 'BookReturnView',
					title : '图书归还列表',
					iconCls : 'menu-book-return',
					layout : 'border',
					items : [this.searchPanel, this.gridPanel]
				});
	},
	// 初始化组件
	initUI : function() {
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
					labelWidth : 170,
					items : [{
						fieldLabel : '请输入查询条件: 借出图书名称',
						xtype : 'textfield',
						name : 'Q_bookName_S_LK',
						maxLength : 150
						}, {
						fieldLabel : '借出图书的ISBN',
						xtype : 'textfield',
						name : 'Q_borrowIsbn_S_LK',
						maxLength : 125,
						labelWidth : 95
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

		// 列表面板
		this.gridPanel = new HT.EditorGridPanel({
			region : 'center',
			tbar : [{
						text : '添加归还记录',
						iconCls : 'btn-add',
						scope : this,
						handler : this.addBookReturn,
						hidden : !isGranted('_BookReturnAdd')
					}],
			// 使用RowActions
			rowActions : true,
			sort : [{field : "recordId",direction : "DESC"}],
			url : __ctxPath + '/admin/listReturnBookBorRet.do',
			baseParams : {
						'Q_bookSn.status_SN_EQ"' : 0
					},
			fields : [{
						name : 'recordId',
						type : 'int'
					}, {
						name : 'bookId',
						mapping : 'bookSn.bookId'
					}, 'borrowTime', 'returnTime', 'lastReturnTime',
					'borrowIsbn', 'bookName', 'registerName', 'fullname'],
			columns : [{
					header : 'recordId',
					dataIndex : 'recordId',
					hidden : true
				},{
					header : 'bookId',
					dataIndex : 'bookId',
					hidden : true
				},{
					header : '借出图书名称',
					dataIndex : 'bookName'
				}, {
					header : '借出图书ISBN',
					dataIndex : 'borrowIsbn'
				},{
				    header:'登记人',
				    dataIndex:'registerName'
				},{
				    header:'借阅人',
				    dataIndex:'fullname'
				}, {
					header : '借出时间',
					dataIndex : 'borrowTime'
				}, {
					header : '应还时间',
					dataIndex : 'returnTime',
					renderer:function(value){
					  return value.substring(0,10);
					}
				}, {
					header : '归还时间',
					dataIndex : 'lastReturnTime'
				}, new Ext.ux.grid.RowActions({
								header : '管理',
								width : 100,
								actions : [{
											iconCls : 'btn-edit',
											qtip : '编辑',
											style : 'margin:0 3px 0 3px',
											fn : function(record) {
												if (isGranted('_BookReturnEdit'))
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
	// 刷新gridPanel
	reloadGridPanel : function() {
		this.gridPanel.getStore().reload();
	},
	// 添加归还记录
	addBookReturn : function() {
		new BookReturnForm({
					recordId : null,
					scope : this,
					callback : this.reloadGridPanel
				}).show();
	},
	// 行双击事件
	rowdblclick : function(grid, rowindex, e) {
		if (isGranted('_BookReturnEdit')) {
			grid.getSelectionModel().each(function(record) {
						this.recordId = record.data.recordId;
						this.editRs.call(this);
					}, this);
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
		this.recordId = record.data.recordId;
		switch (action) {
			case 'btn-edit' :
				this.editRs.call(this,record);
				break;
			default :
				break;
		}
	},
	// 编辑归还记录
	editRs : function(record) {
		new BookReturnForm({
					bookId : record.data.bookId,
					recordId : this.recordId,
					scope : this,
					callback : this.reloadGridPanel
				}).show();
	}
});