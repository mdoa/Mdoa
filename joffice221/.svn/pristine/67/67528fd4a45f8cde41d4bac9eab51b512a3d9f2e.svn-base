Ext.ns('ResetPasswordForm');
/**
 * @author
 * @createtime
 * @class ResetPasswordForm
 * @extends Ext.Window
 * @description 修改密码
 * @company 宏天软件
 */
ResetPasswordForm = Ext.extend(Ext.Window, {
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 必须先初始化组件
				this.initUIComponents();
				ResetPasswordForm.superclass.constructor.call(this, {
							layout : 'fit',
							items : this.formPanel,
							modal : true,
							id : 'ResetPasswordForm',
							title : '修改密码',
							iconCls : 'btn-password',
							width : 300,
							height : 155,
							minWidth : 300,
							minHeight : 155,
							buttonAlign : 'center',
							buttons : this.buttons
						});
			},// end of the constructor
			// 初始化组件
			initUIComponents : function() {
				// 表单
				this.formPanel = new Ext.FormPanel({
							layout : 'form',
							border : false,
							bodyStyle : 'padding:5px;',
							defaults : {
								anchor : '96%,96%'
							},
							defaultType : 'textfield',
							items : [{
										name : 'appUserUserId',
										xtype : 'hidden',
										value : this.userId
									}, {
										fieldLabel : '旧密码',
										name : 'oldPassword',
										inputType : 'password',
										allowBlank : false,
										regex : /[^\s]+/,
										regexText : '旧密码不能包含空格！'
									}, {
										fieldLabel : '新密码',
										id : 'newPassword',
										name : 'newPassword',
										inputType : 'password',
										allowBlank : false,
										regex : /[^\s]+/,
										regexText : '新密码不能包含空格！'
									}, {
										fieldLabel : '确认密码',
										name : 'againPassword',
										inputType : 'password',
										allowBlank : false,
										regex : /[^\s]+/,
										regexText : '确认密码中不能包含空格！',
										vtype : 'password',
										initialPassField : 'newPassword'

									}]
						});
				// 按钮组
				this.buttons = [{
							text : '保存',
							iconCls : 'btn-save',
							scope : this,
							handler : this.save
						}, {
							text : '取消',
							iconCls : 'btn-cancel',
							scope : this,
							handler : function() {
								this.close();
							}
						}]
			},
			// 保存
			save : function() {
				if (this.formPanel.getForm().isValid()) {
					Ext.Ajax.request({
								url : __ctxPath
										+ '/system/resetPasswordAppUser.do',
								params : {
									appUserUserId : this.formPanel
											.getCmpByName('appUserUserId').getValue(),
									oldPassword : this.formPanel
											.getCmpByName('oldPassword').getValue(),
									newPassword : this.formPanel
											.getCmpByName('newPassword').getValue(),
									againPassword : this.formPanel
											.getCmpByName('againPassword').getValue()
								},
								method : 'POST',
								waitMsg : '正在提交数据...',
								scope : this.scope ? this.scope : this,
								success : function(response, options) {
									var result = Ext.util.JSON
											.decode(response.responseText);
									if (result.success) {
										Ext.ux.Toast.msg('操作信息', '修改密码成功！');
										if (this.callback) {
											this.callback.call(this.scope);
										}
										this.close();
									} else {
										Ext.ux.Toast.msg('操作信息', result.msg);
									}
								},
								failure : function(response, options) {
									Ext.ux.Toast.msg('操作信息', '操作出错，请联系管理员！');
								}
							});
				}

			}
		});