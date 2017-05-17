/**
 * 数据字典分类转移
 */
DicTypeChangeWin = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		this.initUI();
		DicTypeChangeWin.superclass.constructor.call(this, {
					layout : 'fit',
					items : [this.formPanel],
					modal : true,
					width : 350,
					height : 110,
					title : '数据字典分类转移',
					iconCls : 'btn-up',
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
					fieldLabel : '选择将转为的分类',
					hiddenName : 'dicTypeId',
					xtype : 'combotree',
					allowBlank : false,
					url : __ctxPath
							+ '/system/treeGlobalType.do?catKey=DIC'
				}];

		this.formPanel = new Ext.FormPanel({
					border : false,
					bodyStyle : 'padding : 5px;',
					defaults : {
						anchor : '95%,95%',
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
		var dicTypeId = this.formPanel.getCmpByName('dicTypeId')
				.getValue();
		if (dicTypeId == '' || dicTypeId == 0) {
			Ext.ux.Toast.msg('操作信息', '请选择将要转化的分类!');
			return;
		}
		$postForm({
					formPanel : this.formPanel,
					params : {
						dicIds : this.dicIds,
						dicTypeId : dicTypeId
					},
					scope : this,
					url : __ctxPath + '/system/typeChangeDictionary.do',
					callback : function(fp, action) {
						if (this.callback) {
							this.callback.call(this.scope);
						}
						this.close();
					}
				});
	}
});