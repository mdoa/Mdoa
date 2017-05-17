Ext.ns('BookManageView');
/**
 * 图书管理
 * 
 * @class BookManageView
 * @extends Ext.Panel
 */
BookManageView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		this.initUI();
		BookManageView.superclass.constructor.call(this, {
			id : 'BookManageView',
			title : '图书管理',
			iconCls : 'menu-book-manage',
			layout : 'border',
			items : [ this.leftPanel, this.outPanel ]
		});
	},
	// 初始化组件
	initUI : function() {
		// 图书类别树
		this.bookTreePanel = new htsoft.ux.TreePanelEditor({
			url : __ctxPath + '/admin/treeBookType.do',
			scope : this,
			autoScroll : true,
			split : true,
			onclick : this.typeNodeClick,
			enableDD : true
		});

		// 给树添加右击事件
		this.bookTreePanel.on('contextmenu', this.contextmenu, this);

		// 左部面板
		this.leftPanel = new Ext.Panel({
			region : 'west',
			title : '图书类型',
			layout : 'fit',
			collapsible : true,
			split : true,
			border : false,
			width : 200,
			items : [ this.bookTreePanel ]
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
			labelWidth : 135,
			items : [ {
				fieldLabel : '请输入查询条件: 书名',
				xtype : 'textfield',
				name : 'Q_bookName_S_LK',
				maxLength : 150
			}, {
				fieldLabel : '作者',
				xtype : 'textfield',
				name : 'Q_author_S_LK',
				maxLength : 125,
				labelWidth : 35
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
			} ]
		});
				
		//顶端栏目条
		this.topbar = new Ext.Toolbar({
			items : [{
						iconCls : 'btn-add',
						text : '添加图书',
						xtype : 'button',
						scope : this,
						handler : this.addBook,
						hidden : !isGranted('_BookAdd')
					}, '-', {
						iconCls : 'btn-del',
						text : '删除图书',
						xtype : 'button',
						scope : this,
						handler : this.removeSelRs,
						hidden : !isGranted('_BookDel')
					}]
		});

		// 图书列表面板
		this.gridPanel = new HT.EditorGridPanel({
			region : 'center',
			tbar : this.topbar,
			sort : [{field:"bookId",direction:"DESC"}],
			// 使用RowActions
			rowActions : true,
			url : __ctxPath + "/admin/listBook.do",
			fields : [ {
				name : 'bookId',
				type : 'int'
			}, {
				name : 'bookType.typeName',
				mapping : 'bookType.typeName'
			}, 'bookName', 'author', 'isbn', 'publisher', 'price', 'location',
					'department', 'amount', 'leftAmount' ],
			columns : [
					{
						header : 'bookId',
						dataIndex : 'bookId',
						hidden : true
					},
					{
						header : '类别',
						dataIndex : 'bookType.typeName'
					},
					{
						header : '书名',
						dataIndex : 'bookName'
					},
					{
						header : '作者',
						dataIndex : 'author'
					},
					{
						header : 'ISBN号',
						dataIndex : 'isbn'
					},
					{
						header : '存放地点',
						dataIndex : 'location'
					},
					{
						header : '所属部门',
						dataIndex : 'department'
					},
					{
						header : '数量',
						dataIndex : 'amount',
						width : 50
					},
					{
						header : '在库数',
						dataIndex : 'leftAmount',
						width : 60
					},
					new Ext.ux.grid.RowActions({
						header : '管理',
						width : 200,
						actions : [
								{
									iconCls : 'btn-del',
									qtip : '删除',
									style : 'margin:0 3px 0 3px',
									fn : function(record) {
										if (isGranted('_BookDel'))
											return true;
										return false;
									}
								},
								{
									iconCls : 'btn-edit',
									qtip : '编辑',
									style : 'margin:0 3px 0 3px',
									fn : function(record) {
										if (isGranted('_BookEdit'))
											return true;
										return false;
									}
								},
								{
									iconCls : 'menu-book-borrow',
									qtip : '借阅该书',
									style : 'margin: 0 2px 0 2px',
									fn : function(record) {
										if (record.data.leftAmount != 0
												&& isGranted('_BookBorrowAdd'))
											return true;
										return false;
									}
								} ],
						listeners : {
							scope : this,
							'action' : this.onRowAction
						}
					}) ],
			// 监听行点击事件
			listeners : {
				scope : this,
				'rowdblclick' : this.rowdblclick
			}
		});
		this.outPanel = new Ext.Panel({
			region : 'center',
			title : '所有图书列表',
			layout : 'border',
			items : [ this.searchPanel, this.gridPanel ]
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
	// 重新加载
	reload : function() {
		this.bookTreePanel.root.reload();
		// 刷新gridPanel
		this.gridPanel.getStore().reload();
	},
	// 添加图书类别
	addType : function() {
		new BookTypeForm({
			typeId : null,
			scope : this,
			callback : this.reload
		}).show();
	},
	// 编辑图书类别
	editType : function() {
		var typeId = this.selectNode.id;
		if (typeId == 0) {
			Ext.ux.Toast.msg('操作信息', '该处不能被修改!');
			return;
		}
		new BookTypeForm({
			typeId : typeId,
			scope : this,
			callback : this.reload
		}).show();
	},
	// 删除图书类别
	delType : function() {
		var typeId = this.selectNode.id;
		if (!Ext.isEmpty(typeId)) {
			$postDel({
						url : __ctxPath + '/admin/multiDelBookType.do',
						ids : typeId,
						scope : this,
						callback : this.reload
					});
		}
	},
	// 分类节点点击
	typeNodeClick : function(node) {
		var searchPanel = this.searchPanel;
		var gridPanel = this.gridPanel;

		this.selectNode = node;
		var store = gridPanel.getStore();
		var outPanel = this.outPanel;
		if (node.id == 0) {
			outPanel.setTitle('所有图书列表');
		} else {
			outPanel.setTitle('[' + node.text + ']列表');
		}

		// 带上查询条件
		var name = searchPanel.getCmpByName('Q_bookName_S_LK').getValue();
		var author = searchPanel.getCmpByName('Q_author_S_LK').getValue();
		store.baseParams = {
			'Q_bookType.typeId_L_EQ' : node.id == 0 ? null : node.id,
			'Q_bookName_S_LK' : name,
			'Q_author_S_LK' : author
		};
		this.gridPanel.getBottomToolbar().moveFirst();
	},
	// 节点右击事件
	contextmenu : function(node, e) {
		this.typeNodeClick(node);

		// 创建右键菜单
		var treeMenu = new Ext.menu.Menu({
					items : [{
								text : '新建类型',
								iconCls : 'btn-add',
								handler : this.addType,
								scope : this
							}, {
								text : '修改类型',
								iconCls : 'btn-edit',
								scope : this,
								handler : this.editType,
								hidden : node.id == 0 ? true : false

							}, {
								text : '删除类型',
								iconCls : 'btn-del',
								scope : this,
								handler : this.delType,
								hidden : node.id == 0 ? true : false
							}]
				});
		treeMenu.showAt(e.getXY());
	},
	// 刷新gridPanel
	reloadGridPanel : function() {
		this.gridPanel.getStore().reload();
	},
	// 新增图书
	addBook : function() {
		new BookForm({
			bookId : null,
			scope : this,
			callback : this.reloadGridPanel
		}).show();
	},
	// 把选中ID删除图书
	removeSelRs : function() {
		$delGridRs({
			msg : '会把图书的借阅归还记录和ISBN一起删除，<br/>您确认要删除所选的记录吗？',
			url : __ctxPath + '/admin/multiDelBook.do',
			grid : this.gridPanel,
			idName : 'bookId'
		});
	},
	// 行双击事件
	rowdblclick : function(grid, rowindex, e) {
		grid.getSelectionModel().each(function(record) {
			this.bookId = record.data.bookId;
			this.editRs.call(this);
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
		this.bookId = record.data.bookId;
		switch (action) {
		case 'btn-del':
			this.removeRs.call(this);
			break;
		case 'btn-edit':
			this.editRs.call(this);
			break;
		case 'menu-book-borrow':
			this.borrowRs.call(this);
			break;
		default:
			break;
		}
	},
	// 删除图书
	removeRs : function() {
		$postDel({
			msg : '会把图书的借阅归还记录和ISBN一起删除，<br/>您确认要删除所选的记录吗？',
			url : __ctxPath + '/admin/multiDelBook.do',
			ids : this.bookId,
			grid : this.gridPanel
		});
	},
	// 编辑图书
	editRs : function() {
		new BookForm({
			bookId : this.bookId,
			scope : this,
			callback : this.reloadGridPanel
		}).show();
	},
	// 借阅该书
	borrowRs : function() {
		// 选择借阅该书的时候根据bookId把书名自动赋上，并且把bookSn也重新加载出来
		new BookBorrowForm({
			bookId : this.bookId,
			scope : this,
			callback : this.reloadGridPanel
		}).show();
	}
});