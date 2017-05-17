Ext.ns('BookBorrowForm');
/**
 * @author
 * @createtime
 * @class BookBorrowForm
 * @extends Ext.Window
 * @description 图书借出详细记录
 * @company 宏天软件
 */
BookBorrowForm = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		BookBorrowForm.superclass.constructor.call(this, {
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					id : 'BookBorrowFormWin',
					title : '图书借出详细记录',
					iconCls : 'menu-book-borrow',
					width : 520,
					height : 220,
					buttonAlign : 'center',
					buttons : this.buttons
				});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		// 按钮组
		this.buttons = [{
					text : this.recordId == null ? '借出图书 ' : '保存',
					iconCls : 'btn-save',
					scope : this,
					handler : this.save
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
			bodyStyle : 'padding:5px;',
			defaults : {
				anchor : '96%,96%'
			},
			defaultType : 'textfield',
			items : [{
						name : 'bookBorRet.recordId',
						xtype : 'hidden',
						value : this.recordId == null ? '' : this.recordId
					}, {
						name : 'bookBorRet.bookSnId',
						xtype : 'hidden'
					}, {
						xtype : 'container',
						height : 32,
						layout : 'hbox',
						layoutConfigs : {
							align : 'middle'
						},
						defaults : {
							margins : '0 2 0 2'
						},
						items : [{
									xtype : 'label',
									text : '借出图书名称:',
									width : 100
								}, {
									xtype : 'textfield',
									name : 'bookBorRet.bookName',
									allowBlank : false,
									blankText : '借出图书名称不能为空',
									readOnly : true,
									width : 200
								}, {
									xtype : 'hidden',
									name : 'oldBookName'
								}, {
									xtype : 'button',
									text : '选择图书',
									iconCls : 'menu-book-manage',
									scope : this,
									handler : this.bookSelect,
									hidden : this.bookId != null ? true : false
								}, {
									xtype : 'button',
									text : ' 清除记录',
									iconCls : 'reset',
									scope : this,
									handler : this.bookClean,
									hidden : this.bookId != null ? true : false
								}]
					}, {
						xtype : 'container',
						height : 32,
						layout : 'hbox',
						layoutConfigs : {
							align : 'middle'
						},
						defaults : {
							margins : '0 2 0 2'
						},
						items : [{
									xtype : 'label',
									text : '借出图书的ISBN:',
									width : 100
								}, {
									name : 'bookBorRet.borrowIsbn',
									allowBlank : false,
									blankText : '借出图书的ISBN不能为空',
									width : 200,
									maxHeight : 200,
									xtype : 'combo',
									mode : 'local',
									editable : false,
									triggerAction : 'all',
									emptyText : '请选择图书ISBN',
									valueField : 'bookSnId',
									displayField : 'bookSn',
									store : new Ext.data.SimpleStore({
										// 借阅时只显示在库的图书标签（只有在库的图书才能选择被借阅）
										url : __ctxPath
												+ '/admin/getBorrowSnBookSn.do',
										fields : ['bookSnId', 'bookSn']
									}),
									scope : this,
									listeners : {
										scope : this,
										'select' : this.selectBookSn,
										'focus' : this.focusBookSn
									}
								}]
					}, {
						xtype : 'container',
						height : 32,
						layout : 'hbox',
						layoutConfigs : {
							align : 'middle'
						},
						defaults : {
							margins : '0 2 0 2'
						},
						items : [{
									xtype : 'label',
									text : '借阅人:',
									width : 100
								}, {
									name : 'bookBorRet.fullname',
									xtype : 'textfield',
									readOnly : true,
									allowBlank : false,
									width : 200
								}, {
									xtype : 'button',
									text : '选择人员',
									iconCls : 'btn-user-sel',
									scope : this,
									handler : this.userSelector
								}, {
									xtype : 'button',
									text : '清除记录',
									iconCls : 'reset',
									scope : this,
									handler : this.userReset
								}]
					}, {
						fieldLabel : '应还时间',
						name : 'bookBorRet.returnTime',
						xtype : 'datefield',
						format : 'Y-m-d',
						editable : false,
						allowBlank : false,
						width : 200,
						blankText : '应还时间不能为空'
					}]
		});
		// 加载数据
		if (this.recordId != null && this.recordId != 'undefined') {
			var formPanel = this.formPanel;
			formPanel.loadData({
						url : __ctxPath + '/admin/getBookBorRet.do?recordId='
								+ this.recordId,
						preName : 'bookBorRet',
						root : 'data',
						success:function(response,options){
							var res=Ext.util.JSON.decode(response.responseText).data;
							var oldBookName = res.bookName;
							formPanel.getCmpByName('oldBookName').setValue(oldBookName);
						}
					});
		}
		if (this.bookId != null && this.bookId != 'undefined') {
			// 图书和图书系列
			this.updateBookInfo(this);
		}

	},
	// 更新图书信息
	updateBookInfo : function() {
		Ext.Ajax.request({
					url : __ctxPath + '/admin/getBook.do',
					params : {
						bookId : this.bookId
					},
					method : 'post',
					scope : this,
					success : function(response) {
						var result = Ext.util.JSON
								.decode(response.responseText);
						this.setBookInfo(result.data.bookId,
								result.data.bookName, 'borrowIsbn', this);
					}
				});
	},
	// 选择图书
	bookSelect : function() {
		new BookDialog({
					single : true,
					scope : this,
					callback : function(ids, names) {
						this.setBookInfo(ids, names, 'borrowIsbn', this);
					}
				}).show();
	},

	// 清除
	bookClean : function() {
		this.setBookInfo(0, '', '', this);
	},
	// 设置图书的修改信息
	setBookInfo : function(bookId, bookName, borrowIsbn, scope) {
		var formPanel = this.formPanel;
		formPanel.getCmpByName('bookBorRet.bookName').setValue(bookName);
		var oldBookName = formPanel.getCmpByName('oldBookName').getValue();
		if(oldBookName != bookName){
			formPanel.getCmpByName('bookBorRet.borrowIsbn').setValue('');
		}
		if (borrowIsbn == '') {
			formPanel.getCmpByName('bookBorRet.borrowIsbn').setValue('');
		}
		var store = formPanel.getCmpByName('bookBorRet.borrowIsbn')
				.getStore();
		store.reload({
					params : {
						bookId : bookId
					}
				});
	},
	// 选择图书索引赋值
	selectBookSn : function(combo, record, index) {
		this.formPanel.getCmpByName('bookBorRet.bookSnId')
				.setValue(combo.value);
	},
	// 选择图书索引赋值
	focusBookSn : function(combo, record, index) {
		var bookName = this.formPanel.getCmpByName('bookBorRet.bookName')
				.getValue();
		if (bookName == '') {
			Ext.ux.Toast.msg('提示信息', '请先选择图书');
			this.bookSelect(this);
		}
	},
	// 人员选择
	userSelector : function() {
		new UserDialog({
			single : true,
			isForFlow : false,
			userIds : '',
			userName : this.formPanel
					.getCmpByName('bookBorRet.fullname').getValue(),
			scope : this,
			callback : function(ids, names) {
				this.formPanel.getCmpByName('bookBorRet.fullname')
						.setValue(names);
			}
		}).show();
	},
	// 人员清除
	userReset : function() {
		this.formPanel.getCmpByName('bookBorRet.fullname').setValue('');

	},
	// 保存
	save : function() {
		$postForm({
					formPanel : this.formPanel,
					waitMsg : '正在提交数据...',
					scope : this,
					url : __ctxPath + '/admin/saveBorrowBookBorRet.do',
					callback : function(fp, action) {
						if (this.callback) {
							this.callback.call(this.scope);
						}
						this.close();
					}
				});
	},// end of save
	// 取消
	cancel : function() {
		this.close();
	}
});