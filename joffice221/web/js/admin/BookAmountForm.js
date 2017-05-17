/**
 * 数据字典分类转移
 */
BookAmountForm = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		this.initUI();
		BookAmountForm.superclass.constructor.call(this, {
					layout : 'fit',
					items : [this.formPanel],
					modal : true,
					width : 250,
					height : 110,
					title : '增加图书数量',
					bodyStyle : 'padding:5px',
					buttonAlign : 'center',
					buttons : this.buttons
				});
	},
	// 初始化组件
	initUI : function() {
		// 按钮
		this.buttons = [{
							text : '保存',
							iconCls : 'btn-save',
							scope : this,
							handler : this.save
						}, {
							text : '关闭',
							iconCls : 'btn-close',
							scope : this,
							handler : function() {
								this.close();
							}
						}];

		// items
		this.items = [{
			fieldLabel : '增加的图书数量',
			name : 'addAmount',
			id : 'addAmount',
			allowBlank : false,
			blankText : '增加的图书数量不能为空'
		}];

		this.formPanel = new Ext.FormPanel({
					border : false,
					bodyStyle : 'padding : 5px;',
					defaults : {
						anchor : '90%,90%',
						allowBlank : false,
						selectOnFocus : true,
						msgTarget : 'side'
					},
					defaultType : 'textfield',
					items : this.items
				});
	},
	// 保存
	save : function() {
		$postForm({
			formPanel : this.formPanel,
			url : __ctxPath + '/admin/updateAmountBook.do?bookId='+this.bookId,
			scope : this,
			callback : function(fp, action) {
				if (this.callback) {
					this.callback.call(this.scope);
				}
				this.close();
			}
		});
	}
});