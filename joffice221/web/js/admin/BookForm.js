Ext.ns('BookForm');
/**
 * @author
 * @createtime
 * @class BookForm
 * @extends Ext.Window
 * @description 图书类别详细信息表单
 * @company 宏天软件
 */
BookForm = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		BookForm.superclass.constructor.call(this, {
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					id : 'BookFormWin',
					title : '图书详细信息',
					iconCls : 'menu-book-manage',
					width : 500,
					height : 350,
					buttonAlign : 'center',
					buttons : this.buttons
				});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		// 按钮组
		this.buttons = [{
					text : '保存',
					iconCls : 'btn-save',
					scope : this,
					handler : this.save
				}, {
					text : '重置',
					iconCls : 'btn-reset',
					scope : this,
					handler : this.reset
				}, {
					text : '取消',
					iconCls : 'btn-cancel',
					scope : this,
					handler : this.cancel
				}]
		// 图书详细信息表单
		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			border : false,
			autoScroll : true,
			bodyStyle : 'padding:5px;',
			defaults : {
				anchor : '96%,96%'
			},
			defaultType : 'textfield',
			items : [{
						name : 'book.bookId',
						xtype : 'hidden',
						value : this.bookId == null ? '' : this.bookId
					}, {
						id : 'bookLeftAmount',
						name : 'book.leftAmount',
						xtype : 'hidden'
					}, {
						xtype : 'label'
					}, {
						fieldLabel : '图书类别',
						hiddenId : 'bookTypeId',
						hiddenName : 'book.bookType.typeId',
						name : 'book.bookType.typeName',
						xtype : 'combotree',
						allowBlank : false,
						url : __ctxPath + '/admin/treeBookType.do?method=1'
					}, {
						fieldLabel : '书名',
						name : 'book.bookName',
						allowBlank : false,// 不允许为空
						blankText : '书名不能为空'
					}, {
						fieldLabel : '作者',
						name : 'book.author',
						allowBlank : false,// 不允许为空
						blankText : '作者不能为空'
					}, {
						fieldLabel : 'ISBN号',
						name : 'book.isbn',
						allowBlank : false,// 不允许为空
						blankText : 'ISBN号不能为空'
					}, {
						xtype : 'hidden',
						name : 'oldBookIsbn'
					}, {
						fieldLabel : '出版社',
						name : 'book.publisher'
					}, {
						fieldLabel : '图书价格',
						name : 'book.price',
						xtype : 'numberfield',// 价格只能输入数字，可以有小数点
						nanText : '只能输入数字',
						allowBlank : false,// 不允许为空
						blankText : '价格不能为空'
					}, {
						fieldLabel : '存放地点',
						name : 'book.location',
						allowBlank : false,// 不允许为空
						blankText : '存放地点不能为空'
					}, {
						xtype : 'container',
						layout : 'column',
						fieldLabel :'数量',
						items : [ {
									columnWidth : .99,
									id : 'bookAmount',
									name : 'book.amount',
									xtype : 'numberfield',// 数量只能输入数字
									allowDecimals : false,// 只允许输入整数
									nanText : '只能输入数字',
									allowBlank : false,// 不允许为空
									blankText : '数量不能为空',
									minValue : 1,
									minText : '图书数量必须大于0'
								}, {
									xtype : 'button',
									text : '增加数量',
									iconCls : 'btn-select',
									width : 80,
									scope : this,
									hidden : this.bookId == null ? true : false,
									handler : this.addBookAmount
								}]
					}, {
						xtype : 'container',
						layout : 'column',
						fieldLabel :'所属部门',
						items : [ {
									columnWidth : .99,
									name : 'book.department',
									xtype : 'textfield',
									allowBlank : false,// 不允许为空
									blankText : '所属部门不能为空'
								}, {
									xtype : 'button',
									text : '选择',
									iconCls : 'btn-select',
									iconAlign : 'left',
									width : 80,
									// 部门选择器
									scope : this,
									handler : this.depDialog
								}]

					}, {
						xtype : 'container',
						layout : 'form',
						hidden : this.bookId == null ? true : false,
						items : [{
									id : 'bookSnPanel',
									fieldLabel : '图书标签',
									xtype : 'panel',
									frame : false,
									height : 100,
									autoScroll : true,
									html : ''
								}]
					}]
		});
		// 加载数据
		if (this.bookId != null && this.bookId != 'undefined') {
			var formPanel = this.formPanel;
			formPanel.loadData({
				url : __ctxPath + '/admin/getBook.do?bookId=' + this.bookId,
				preName : 'book',
				root : 'data',
				scope : this,
				success : function(response, options) {
					var data = Ext.util.JSON.decode(response.responseText).data;
					// 加载图书索引
					var bookIsbn= data.isbn;
					formPanel.getCmpByName('oldBookIsbn').setValue(bookIsbn);
					this.loadBookSnPanel(this);
				}
			});
		}

	},
	// 加载图书索引
	loadBookSnPanel : function() {
		var booksnPanel = Ext.getCmp('bookSnPanel');
		var imgSrc = __ctxPath + '/images/system/delete.gif';
		var tpl = new Ext.XTemplate('<tpl for="."><p> {bookSN}&nbsp;<img class="img-delete" src="'
				+ imgSrc
				+ '"  alt="删除该本图书"'
				+ 'onclick="BookForm.prototype.removeBookSn(this,{bookSnId},'
				+ this.bookId + ')" /></p></tpl>');

		var store = new Ext.data.JsonStore({
					url : __ctxPath + '/admin/getSnBookSn.do?bookId='
							+ this.bookId,
					fields : [{
								name : 'bookSN',
								type : 'string'
							}, {
								name : 'bookSnId',
								type : 'int'
							}]
				});
		store.load();
		var panel = new Ext.Panel({
					layout : 'fit',
					border : false,
					items : new Ext.DataView({
								store : store,
								tpl : tpl,
								autoHeight : true
							})
				});
		return panel.render(booksnPanel.body);
	},
	// 增加图书数量
	addBookAmount : function() {
		var formPanel = this.formPanel;
		new BookAmountForm({
			bookId : this.bookId,
			scope : this,
			callback : function() {
				// 重新加载
				if (this.bookId != null && this.bookId != 'undefined') {
					this.formPanel.loadData({
						url : __ctxPath + '/admin/getBook.do?bookId='
								+ this.bookId,
						preName : 'book',
						root : 'data',
						scope : this,
						success : function(response, options) {
							var data = Ext.util.JSON
									.decode(response.responseText).data;
							if (data.bookType) {// 初始化赋值
								Ext.getDom('bookTypeId').value = data.bookType.typeId;
							}
							// 加载图书索引
							Ext.getCmp('bookSnPanel').body.update('');
							this.loadBookSnPanel(this);
						}
					});
				}
			}
		}).show();
	},
	//部门选择
	depDialog : function(){
		var formPanel = this.formPanel;	
		new DepDialog({
			single : true,
			socpe:this,
			callback: function(depIds,depNames){
				formPanel.getCmpByName('book.department').setValue(depNames);
			}
		}).show();
	},
	// 删除图书索引
	removeBookSn : function(obj, bookSnId, bookId) {
		Ext.Msg.confirm('信息确认', '把借阅归还记录一起删除，您确认要删除该书吗？', function(btn) {
			if (btn == 'yes') {
				// 删除节点
				Ext.get(obj.parentNode).remove();
				Ext.Ajax.request({
					url : __ctxPath + '/admin/multiDelBookSn.do',
					params : {
						ids : bookSnId
					},
					method : 'post',
					success : function() {
						Ext.ux.Toast.msg("信息提示", "成功删除所选记录！");
						// 删除图书标签成功后，将书的数量和未借出数量也同时修改
						Ext.Ajax.request({
							url : __ctxPath
									+ '/admin/updateAmountAndLeftAmountBook.do?bookId='
									+ bookId,
							method : 'post',
							success : function(response) {
								var result = Ext.util.JSON
										.decode(response.responseText);
								// 根据bookId修改书相应的数量和剩余数量
								Ext.getCmp('bookAmount')
										.setValue(result.data.amount);
								Ext.getCmp('bookLeftAmount')
										.setValue(result.data.leftAmount);
							}
						});
					}
				});
			}
		});
	},
	/**
	 * 保存
	 */
	save : function() {
		var formPanel = this.formPanel;
		var newBookIsbn = formPanel.getCmpByName('book.isbn').getValue();
		var oldBookIsbn = formPanel.getCmpByName('oldBookIsbn').getValue();
		$postForm({
					formPanel : formPanel,
					waitMsg : '正在提交数据...',
					scope : this,
					url : __ctxPath + '/admin/saveBook.do',
					params : {
						newBookIsbn : newBookIsbn,
						oldBookIsbn : oldBookIsbn
					},
					callback : function(fp, action) {
						if (this.callback) {
							this.callback.call(this.scope);
						}
						this.close();
					}
				});
	},// end of save
	/**
	 * 重置
	 * 
	 * @param {}
	 *            formPanel
	 */
	reset : function() {
		this.formPanel.getForm().reset();
		Ext.getCmp('bookSnPanel').body.update('');
	},
	/**
	 * 取消
	 */
	cancel : function() {
		// 回调
		if (this.callback) {
			this.callback.call(this.scope);
		}
		this.close();
	}
});