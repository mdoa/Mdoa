/**
 * 新建表单
 * 
 * @class FormDesignForm
 * @extends Ext.Window
 */
FormDesignForm = Ext.extend(Ext.Window, {
	constructor : function(config) {
		Ext.applyIf(this, config);
		this.initUIComponents();
		FormDesignForm.superclass.constructor.call(this, {
					title : '新建表单',
					height : 200,
					width : 400,
					modal : true,
					layout : 'fit',
					iconCls : 'btn-form-design',
					// maximizable : true,
					buttonAlign : 'center',
					items : [this.formPanel]
				});
	},
	/**
	 * 初始化组件
	 */
	initUIComponents : function() {
		this.buttons = [{
					text : '下一步',
					scope : this,
					iconCls : 'btn-save',
					handler : this.nextStep
				}, {
					text : '取消',
					scope : this,
					iconCls : 'btn-cancel',
					handler : function() {
						this.close();
					}
				}];

		this.formPanel = new Ext.FormPanel({
			border : false,
			bodyStyle : 'padding : 5px;',
			buttonAlign : 'center',
			defaults : {
				anchor : '95%,95%',
				allowBlank : false,
				selectOnFocus : true,
				msgTarget : 'side'
			},
			defaultType : 'textfield',
			items : [{
						fieldLabel : '表单标题',
						name : 'formTitle'
					}, {
						fieldLabel : '表单描述',
						name : 'formDesp',
						xtype : 'textarea',
						allowBlank : true
					}, {
						fieldLabel : '模板',
						xtype : 'combo',
						hiddenName : 'fomDef.tempalias',
						name : 'tempalias',
						editable : false,
						triggerAction : 'all',
						emptyText : '请选择模板...',
						store : new Ext.data.ArrayStore({
									autoLoad : true,
									url : __ctxPath
											+ '/flow/templateFormDef.do',
									fields : ['alias', 'name', 'templateDesc']
								}),
						tpl : '<tpl for="."><div ext:qtip="{name}. {templateDesc}" class="x-combo-list-item">{name}</div></tpl>',
						displayField : 'name',
						valueField : 'alias'
					}]
		});
	},
	nextStep : function() {
		var formPanel = this.formPanel;
		if (formPanel.getForm().isValid()) {
			var formTitle = formPanel.getCmpByName('formTitle').getValue();
			var formDesp = formPanel.getCmpByName('formDesp').getValue();
			var tempalias = formPanel.getCmpByName('tempalias').getValue();
			var url = __ctxPath + '/flow/formDesignFormDef.do?formTitle='
					+ formTitle + '&formDesp=' + formDesp + '&tempalias='
					+ tempalias;

			this.close();
			openFullWindow(url);
		}

	}
});