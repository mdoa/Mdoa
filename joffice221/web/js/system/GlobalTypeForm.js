/**
 * @author
 * @createtime
 * @updateTime 2012-08-01
 * @updateAuthor zxh
 * @class GlobalTypeForm
 * @extends Ext.Window
 * @description GlobalType全局类型表单
 * @company 宏天软件
 */
GlobalTypeForm = Ext.extend(Ext.Window, {
			// 构造方法
			constructor : function(conf) {
				Ext.applyIf(this, conf);
				this.initUI();
				GlobalTypeForm.superclass.constructor.call(this, {
							layout : 'fit',
							items : [this.formPanel],
							modal : true,
							height : 180,
							width : 400,
							maximizable : true,
							title : '分类详细信息',
							iconCls : 'menu-globalType',
							buttonAlign : 'center',
							buttons : [{
										text : '保存',
										iconCls : 'btn-save',
										handler : this.save,
										scope : this
									}, {
										text : '重置',
										iconCls : 'btn-reset',
										handler : this.reset,
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
							buttonAlign : 'center',
							defaults : {
								anchor : '95%,95%',
								allowBlank : false,
								selectOnFocus : true,
								msgTarget : 'side'
							},
							defaultType : 'textfield',
							items : [{
								xtype : 'hidden',
								name : 'globalType.proTypeId',
								value : this.proTypeId == null
										? ''
										: this.proTypeId
							}, {
								xtype : 'hidden',
								name : 'isPublic',
								value : this.isPublic == null
										? 'false'
										: this.isPublic
							}, {
								fieldLabel : '名称',
								name : 'globalType.typeName',
								allowBlank : false,
								listeners : {
									scope : this,
									'change' : this.changeTypeName
								}
							}, {
								fieldLabel : '父节点',
								value : this.parentId,
								xtype : 'hidden',
								name : 'globalType.parentId'
							}, {
								fieldLabel : '节点Key',
								name : 'globalType.nodeKey',
								allowBlank : false
							}, {
								fieldLabel : '节点分类Key',
								name : 'globalType.catKey',
								allowBlank : false,
								xtype : 'hidden',
								value : this.catKey
							}]
						});
				// 加载表单对应的数据
				if (this.proTypeId != null && this.proTypeId != 'undefined') {
					this.formPanel.loadData({
								deferredRender : false,
								url : __ctxPath
										+ '/system/getGlobalType.do?proTypeId='
										+ this.proTypeId,
								waitMsg : '正在载入数据...',
								root : 'data',
								preName : 'globalType'
							});
				}
			},
			/**
			 * 保存信息
			 */
			save : function() {
				var catKey = this.formPanel.getCmpByName('globalType.catKey')
						.getValue();
				if (this.formPanel.getForm().isValid()) {
					this.formPanel.getForm().submit({
						scope : this,
						url : __ctxPath + '/system/saveGlobalType.do',
						method : 'post',
						waitMsg : '正在提交数据...',
						success : function(fp, action) {
							Ext.ux.Toast.msg('操作信息', '信息成功保存！');
							if (this.callback) {
								this.callback.call(this.scope);
							}
							this.close();
						},
						failure : function(fp, action) {
							Ext.MessageBox.show({
										title : '操作信息',
										msg : catKey == "DIC"
												? '信息保存出错，节点key已存在！'
												: '信息保存出错，请与管理员联系！',
										buttons : Ext.MessageBox.OK,
										icon : 'ext-mb-error'
									});
							if (catKey != "DIC") {
								if (this.callback) {
									this.callback.call(this.scope);
								}
								this.close();
							}
						}
					});
				}
			},
			/**
			 * 重置
			 */
			reset : function() {
				this.formPanel.getForm().reset();
			},
			/**
			 * 改变类型名称 动态获取名字拼音
			 * 
			 * @param field
			 * @param newValue
			 * @param oldValue
			 */
			changeTypeName : function(field, newValue, oldValue) {
				var formPanel = this.formPanel;
				var nodeKey = formPanel.getCmpByName('globalType.nodeKey')
						.getValue();
				if (nodeKey == "" && newValue != "") {
					Ext.Ajax.request({
								url : __ctxPath
										+ '/system/getPinyinGlobalType.do',
								params : {
									typeName : newValue
								},
								scope : this,
								success : function(resp, options) {
									var result = Ext.decode(resp.responseText);
									formPanel
											.getCmpByName('globalType.nodeKey')
											.setValue(result.nodeKey);
								},
								failure : function(resp, options) {
								}
							});
				}
			}
		});