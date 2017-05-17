/**
 * @author
 * @createtime
 * @class TypeKeyForm
 * @extends Ext.Window
 * @description TypeKey表单
 * @company 宏天软件
 */
TypeKeyForm = Ext.extend(Ext.Window, {
			// 内嵌FormPanel
			formPanel : null,
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 必须先初始化组件
				this.initUIComponents();
				TypeKeyForm.superclass.constructor.call(this, {
							id : 'TypeKeyFormWin',
							layout : 'fit',
							items : this.formPanel,
							modal : true,
							height : 200,
							width : 400,
							maximizable : true,
							title : '分类标识键详细信息',
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
			initUIComponents : function() {
				this.formPanel = new HT.FormPanel({
							layout : 'form',
							bodyStyle : 'padding:10px 10px 10px 10px',
							border : false,
							url : __ctxPath + '/system/saveTypeKey.do',
							defaults : {
								anchor : '98%,98%'
							},
							defaultType : 'textfield',
							items : [{
								name : 'typeKey.typekeyId',
								xtype : 'hidden',
								value : this.typekeyId == null
										? ''
										: this.typekeyId
							}, {
								fieldLabel : '分类标识名',
								name : 'typeKey.typeName',
								allowBlank : false,
								listeners : {
									scope : this,
									'change' : this.changeTypeName
								}
							}, {
								fieldLabel : '分类标识KEY',
								name : 'typeKey.typeKey'
							}, {
								fieldLabel : '排序',
								name : 'typeKey.sn',
								//xtype : 'hidden',
								value : 1
							}

							]
						});
				// 加载表单对应的数据
				if (!Ext.isEmpty(this.typekeyId)) {
					this.formPanel.loadData({
								url : __ctxPath
										+ '/system/getTypeKey.do?typekeyId='
										+ this.typekeyId,
								preName : 'typeKey',
								root : 'data'
							});
				}
				// 初始化功能按钮
				this.buttons = [{
							text : '保存',
							iconCls : 'btn-save',
							scope : this,
							handler : this.save
						}, {
							text : '取消',
							iconCls : 'btn-cancel',
							scope : this,
							handler : this.cancel
						}];
			},// end of the initcomponents
			/**
			 * 改变类型名称 动态获取名字拼音
			 * 
			 * @param field
			 * @param newValue
			 * @param oldValue
			 */
			changeTypeName : function(field, newValue, oldValue) {
				var formPanel = this.formPanel;
				var typeKey = formPanel.getCmpByName('typeKey.typeKey')
				if (typeKey.getValue() == "" && newValue != "") {
					Ext.Ajax.request({
								url : __ctxPath
										+ '/system/getPinyinGlobalType.do',
								params : {
									typeName : newValue
								},
								scope : this,
								success : function(resp, options) {
									var result = Ext.decode(resp.responseText);
									typeKey.setValue(result.nodeKey);
								},
								failure : function(resp, options) {
								}
							});
				}
			},
			/**
			 * 取消
			 * 
			 * @param {}
			 *            window
			 */
			cancel : function() {
				this.close();
			},
			/**
			 * 保存记录
			 */
			save : function(formPanel, window) {
				$postForm({
							formPanel : this.formPanel,
							waitMsg : '正在提交数据...',
							scope : this,
							url : __ctxPath + '/system/saveTypeKey.do',
							callback : function(fp, action) {
								if (this.callback) {
									this.callback.call(this.scope);
								}
								this.close();
							}
						});
			}// end of save

		});