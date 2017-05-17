/**
 * 图书选择器
 * 
 * @class BookDialog
 * @extends Ext.Window
 *          @example
 * 
 * <pre>
 * new BookDialog({
 *  	title :'选择图书' //标题  默认是'选择图书'，也可以自定义标题
 * 		single: true,   //是否单选 默认是多选图书
 * 		scope:this,   //作用域
 * 		callback :function(ids,names){//回调函数,返回图书ids和图书名称
 * 
 * 		}	
 * 	}
 * </pre>
 */
BookDialog = Ext.extend(Ext.Window, {
			constructor : function(conf) {
				Ext.applyIf(this, conf);
				// 作用域
				this.scope = this.scope ? this.scope : this;
				// 默认为多单选择图书
				this.single = this.single != null ? this.single : false;
				// 初始化组件
				this.initUI();
				BookDialog.superclass.constructor.call(this, {
							title : this.title ? this.title : '选择图书',
							width : 630,
							height : 380,
							border : false,
							modal : true,
							iconCls : 'menu-book-manage',
							items : [this.treePanel, this.searchPanel,
									this.gridPanel],
							layout : 'border',
							buttonAlign : 'center',
							buttons : [{
										iconCls : 'btn-ok',
										text : '确定',
										scope : this,
										handler : this.confirm
									}, {
										text : '取消',
										iconCls : 'btn-cancel',
										scope : this,
										handler : function() {
											this.close();
										}
									}]
						});
			},
			/**
			 * 初始化UI
			 */
			initUI : function() {
				// 图书类别树
				this.treePanel = new htsoft.ux.TreePanelEditor({
							layout : 'fit',
							region : 'west',
							collapsible : true,
							split : true,
							width : 200,
							title : '图书类别',
							url : __ctxPath + '/admin/treeBookType.do',
							scope : this,
							autoScroll : true,
							// 点击类型树节点
							onclick : this.typeNodeClick
						});
				// 搜索面板
				this.searchPanel = new HT.SearchPanel({
							width : 400,
							layout : 'form',
							region : 'north',
							colNums : 3,
							keys : {
								key : Ext.EventObject.ENTER,
								fn : this.search,
								scope : this
							},
							labelWidth : 145,
							items : [{
										fieldLabel : '请输入查询条件: 图书名称',
										xtype : 'textfield',
										name : 'Q_bookName_S_LK',
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
										handler : this.reset
									}]
						});

				// --------start grid panel----------
				this.gridPanel = new HT.GridPanel({
							title : '图书列表',
							width : 400,
							height : 300,
							region : 'center',
							singleSelect : this.single,
							url : __ctxPath + "/admin/listBook.do",
							fields : [{
										name : 'bookId',
										type : 'int'
									}, 'bookName'],
							columns : [{
										header : 'bookId',
										dataIndex : 'bookId',
										hidden : true
									}, {
										header : '图书名称',
										dataIndex : 'bookName'
									}]
						});

			},// end of initUI function
			/**
			 * 分类节点点击
			 */
			typeNodeClick : function() {
				var searchPanel = this.searchPanel;
				var gridPanel = this.gridPanel;
				var node = this.treePanel.selectedNode;
				var store = gridPanel.getStore();
				// 带上查询条件
				var name = searchPanel.getCmpByName('Q_bookName_S_LK')
						.getValue();
				store.baseParams = {
					'Q_bookType.typeId_L_EQ' : node.id == 0 ? null : node.id,
					'Q_bookName_S_LK' : name
				};
				this.gridPanel.getBottomToolbar().moveFirst();
			},
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
			 * 选择图书
			 */
			confirm : function() {
				var rows = this.gridPanel.getSelectionModel().getSelections();
				var bookIds = '';
				var bookNames = '';
				for (var i = 0; i < rows.length; i++) {
					if (i > 0) {
						bookIds += ',';
						bookNames += ',';
					}
					bookIds += rows[i].data.bookId;
					bookNames += rows[i].data.bookName;
				}
				if (bookIds == "") {
					Ext.ux.Toast.msg('提示信息', '请选择图书');
					return;
				}
				if (this.callback) {
					this.callback.call(this.scope, bookIds, bookNames);
				}
				this.close();
			}
		});