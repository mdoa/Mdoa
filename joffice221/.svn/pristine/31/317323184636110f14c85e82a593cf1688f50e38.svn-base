/**
 * @author
 * @createtime
 * @updateTime 2012-08-01
 * @updateAuthor zxh
 * @class ProDefinitionForm
 * @extends Ext.Window
 * @description 流程定义详细信息
 * @company 宏天软件
 */
ProDefinitionForm = Ext.extend(Ext.Window, {
	// 构造方法
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		this.initUI();
		ProDefinitionForm.superclass.constructor.call(this, {
					layout : 'fit',
					items : [this.formPanel],
					modal : true,
					height : 420,
					width : 500,
					maximizable : true,
					title : '流程定义详细信息',
					iconCls : 'menu-flowNew',
					buttonAlign : 'center',
					buttons : [{
								text : '保存并发布',
								iconCls : 'btn-save',
								handler : this.save,
								scope : this
							}, {
								text : '取消',
								iconCls : 'btn-cancel',
								scope : this,
								handler : this.close
							}],
					keys : {
						key : Ext.EventObject.ENTER,
						fn : this.save,
						scope : this
					}
				});
	},
	// 初始化组件
	initUI : function() {
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
			items : [{
						name : 'proDefinition.defId',
						xtype : 'hidden',
						value : this.defId == null ? '' : this.defId
					}, {
						fieldLabel : '流程类型',
						hiddenName : 'proDefinition.proType.proTypeId',
						name : 'proDefinition.proType.typeName',
						allowBlank : false,
						xtype : 'combotree',
						url : __ctxPath
								+ '/system/treeGlobalType.do?catKey=FLOW&method=1'// 不把根目录显示出来
					}, {
						fieldLabel : '流程状态',
						hiddenName : 'proDefinition.status',
						xtype : 'combo',
						allowBlank : false,
						editable : false,
						mode : 'local',
						triggerAction : 'all',
						store : [['0', '禁用'], ['1', '激活']],
						value : 1
					}, {// 创建时间丢失
						xtype : 'hidden',
						name : 'proDefinition.createtime'
					}, {// 在线流程编辑
						xtype : 'hidden',
						name : 'proDefinition.drawDefXml'
					}, {
						fieldLabel : '流程的名称',
						name : 'proDefinition.name'
					}, {
						fieldLabel : '描述',
						xtype : 'textarea',
						name : 'proDefinition.description'
					}, {
						fieldLabel : '流程定义的XML',
						name : 'proDefinition.defXml',
						xtype : 'textarea',
						height : 200
					}]
		});

		// 加载表单对应的数据
		if (!Ext.isEmpty(this.defId)) {

			this.formPanel.loadData({
						url : __ctxPath + '/flow/getProDefinition.do?defId='
								+ this.defId + '&proTypeId=' + this.typeId,
						root : 'data',
						preName : 'proDefinition',
						scope : this,
						success : function(response, option) {
							var result = Ext.util.JSON
									.decode(response.responseText);
							if (result) {
								var deployId = result.data.deployId;
								if (deployId) {
									this.formPanel
											.getCmpByName('proDefinition.name')
											.getEl().dom.readOnly = true;
								}
							}
						}
					});
		}
	},
	/**
	 * 保存并发布流程
	 */
	save : function() {
		$postForm({
					formPanel : this.formPanel,
					params : {
						deploy : true
					},
					scope : this,
					url : __ctxPath + '/flow/saveProDefinition.do',
					callback : function(fp, action) {
						if (this.callback) {
							this.callback.call(this.scope);
						}
						this.close();
					}
				});
	}
});