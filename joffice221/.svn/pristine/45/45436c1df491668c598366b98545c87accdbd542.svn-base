Ext.ns('SetPasswordForm');
/**
 * @author
 * @createtime
 * @class SetPasswordForm
 * @extends Ext.Window
 * @description 重设密码
 * @company 宏天软件
 */
SetPasswordForm = Ext.extend(Ext.Window, {
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 必须先初始化组件
				this.initUIComponents();
				SetPasswordForm.superclass.constructor.call(this, {
							layout : 'fit',
							items : this.formPanel,
							modal : true,
							id : 'SetPasswordForm',
							title : '重设密码',
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
										fieldLabel : '重设密码',
										name : 'newPassword',
										id:'newPassword',
										inputType : 'password',
										maxLength : 18,
										maxLengthText : '密码不能超过0—18位',
										regex : /[^\s]+/,
										regexText : '新密码不能包含空格！',
										allowBlank : false,
										blankText : '密码不能为空!'
										
									}, {
										fieldLabel : '确认密码',
										name : 'password',
										id : 'password',
										inputType : 'password',
										maxLength : 18,
										maxLengthText : '密码不能超过0—18位',
										regex : /[^\s]+/,
										regexText : '确认密码中不能包含空格！',
										allowBlank : false,
										blankText : '确认密码不能为空!',
										vtype: 'password',
										initialPassField: 'newPassword'
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
				$postForm({
							formPanel : this.formPanel,
							waitMsg : '正在提交数据...',
							scope : this,
							url : __ctxPath
									+ '/system/createPasswordAppUser.do',
							callback : function(fp, action) {
								if (this.callback) {
									this.callback.call(this.scope);
								}
								this.close();
							}
						});
			}
		});