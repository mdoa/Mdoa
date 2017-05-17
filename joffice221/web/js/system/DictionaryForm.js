/**
 * @author
 * @createtime
 * @class DictionaryForm
 * @extends Ext.Window
 * @description DictionaryForm表单
 * @company 宏天软件
 */
DictionaryForm = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		// 必须先初始化组件
		this.initUI();
		DictionaryForm.superclass.constructor.call(this, {
			layout : 'fit',
			id : 'DictionaryFormWin',
			iconCls : 'menu-dictionary',
			items:this.formPanel,
			title : '字典详细信息',
			width : 380,
			height : 220,
			modal : true,
			buttonAlign : 'center',
			buttons : this.buttons,
			keys : {
				key : Ext.EventObject.ENTER,
				fn : this.save,
				scope : this
			}
		});
	},// end of the constructor
	// 初始化组件
	initUI : function() {
		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			bodyStyle:'padding:5px',
			border : false,
			formId : 'DictionaryFormId',
			defaultType : 'textfield',
			defaults:{
				anchor:'98%,98%'
			},
			items : [{
						name : 'dictionary.dicId',
						id : 'dicId',
						xtype : 'hidden',
						value : this.dicId == null ? '' : this.dicId
					}, {
						xtype:'hidden',
						value:this.parentId,
						name:'parentId'
					},{
						xtype:'hidden',
						id:'itemName',
						name:'dictionary.itemName',
						value:this.typeName
					},{
						xtype:'hidden',
						id:'sn',
						name:'dictionary.sn',
						value:0
					},
					{
						fieldLabel:'所属分类',
						xtype:'label',
						text:this.typeName
					},
					{
						fieldLabel : '数值',
						name : 'dictionary.itemValue',
						allowBlank : false,
						id : 'itemValue'
					}, {
						fieldLabel : '备注',
						name : 'dictionary.descp',
						id : 'descp',
						xtype : 'textarea'
					}

			]
		});

		// 加载表单对应的数据
		if (this.dicId != null && this.dicId != 'undefined') {
			this.formPanel.loadData({
				deferredRender : false,
				url : __ctxPath + '/system/getDictionary.do?dicId='
				+ this.dicId,
				waitMsg : '正在载入数据...',
				root : 'data',
				preName : 'dictionary',
				success : function(form, action) {
					// Ext.Msg.alert('编辑', '载入成功！');
				},
				failure : function(form, action) {
					// Ext.Msg.alert('编辑', '载入失败');
				}
			});
		}
		// 初始化功能按钮
		this.buttons = [{
					text : '保存',
					iconCls : 'btn-save',
					scope: this,
					handler : this.save
				}, {
					text : '重置',
					iconCls : 'btn-reset',
					scope: this,
					handler : this.reset
				}, {
					text : '取消',
					iconCls : 'btn-cancel',
					scope: this,
					handler : this.cancel
				}];
	},// end of the initcomponents
	/**
	 * 重置
	 * 
	 */
	reset : function() {
		this.formPanel.getForm().reset();
	},
	/**
	 * 取消
	 * 
	 */
	cancel : function() {
		this.close();
	},
	/**
	 * 保存记录
	 */
	save : function() {	
			$postForm({
				formPanel : this.formPanel,
				scope : this,
				url : __ctxPath + '/system/saveDictionary.do',
				callback : function(fp, action) {
					if (this.callback) {
						this.callback.call(this.scope);
					}
					this.close();
				}
			});
	}// end of save
});
