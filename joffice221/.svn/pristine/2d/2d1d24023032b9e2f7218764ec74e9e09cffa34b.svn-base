/**
 * @author:
 * @class OutMailUserSetingForm
 * @extends Ext.Panel
 * @description [OutMailUserSeting]管理
 * @company 杭州梦德软件有限公司
 * @createtime:2010-01-16
 */
OutMailUserSetingForm = Ext.extend(Ext.Window, {
			// 内嵌FormPanel
			formPanel : null,
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 必须先初始化组件
				this.initUIComponents();
				OutMailUserSetingForm.superclass.constructor.call(this, {
							id : 'OutMailUserSetingFormWin',
							layout : 'hbox',
							layoutConfig : {
								padding : '5',
								pack : 'center',
								align : 'middle'
							},
							modal : true,
							width : 450,
							items : this.formPanel,
							autoScroll : false,
							maximizable : true,
							bodyBorder : true,
							border : true,
							title : '邮箱配置'
						});
			},// end of the constructor
			// 初始化组件
			initUIComponents : function() {
				this.formPanel = new Ext.FormPanel({
							buttonAlign : 'center',
							layout : 'form',
							defaults : {
								anchor : '98%,98%'
							},
							bodyStyle : 'padding:10px',
							width : 450,
							autoScroll : false,
							id : 'OutMailUserSetingForm',
							defaultType : 'textfield',
							items : [{
										name : 'outMailUserSeting.setId',
										id : 'setId',
										xtype : 'hidden',
										value : this.setId == null
												? ''
												: this.setId
									}, {
										name : 'outMailUserSeting.userName',
										xtype : 'hidden'
									}, {
										name : 'outMailUserSeting.userId',
										xtype : 'hidden'
									}, {
										name : 'outMailUserSeting.isDefault',
										xtype : 'hidden'
									},
									/**
									 * { fieldLabel : '用户ID', name :
									 * 'outMailUserSeting.userId', id : 'userId' },
									 */
									{
										fieldLabel : '帐号名称',
										name : 'outMailUserSeting.accountName',
										allowBlank : false
									}, {
										fieldLabel : '外部邮件地址',
										allowBlank : false,
										name : 'outMailUserSeting.mailAddress',
										vtype : 'email'
									}, {
										fieldLabel : '外部邮件密码',
										name : 'outMailUserSeting.mailPass',
										allowBlank : false,
										inputType : 'password'
									}, {
										fieldLabel : 'smtp主机',
										allowBlank : false,
										name : 'outMailUserSeting.smtpHost'
									}, {
										fieldLabel : 'smtp端口',
										allowBlank : false,
										name : 'outMailUserSeting.smtpPort',
										vtype : 'alphanum'
									}, {
										fieldLabel : 'pop主机',
										allowBlank : false,
										name : 'outMailUserSeting.popHost'
									}, {
										fieldLabel : 'pop端口',
										vtype : 'alphanum',
										allowBlank : false,
										name : 'outMailUserSeting.popPort'
									}

							],
							// listeners : {
							// scope : this,
							// render : this.load
							//						
							// },
							buttons : [{
										text : '保存',
										scope : this,
										iconCls : 'btn-save',
										handler : this.save
									}, {
										text : '重置',
										iconCls : 'btn-reset',
										scope : this,
										handler : function() {
											this.formPanel.getForm().reset();
										}
									}, {
										text : '取消',
										iconCls : 'btn-cancel',
										scope : this,
										handler : function() {
											this.close();
										}
									}]

						});
				// 加载表单对应的数据
				if (this.setId != null && this.setId != 'undefined') {
					this.formPanel.loadData({
								url : __ctxPath
										+ '/communicate/getOutMailUserSeting.do?setId='
										+ this.setId,
								root : 'data',
								preName : 'outMailUserSeting'
							});
				}
			},// end of the initcomponents
			save : function() {
				$postForm({
							formPanel : this.formPanel,
							scope : this,
							url : __ctxPath
									+ '/communicate/saveOutMailUserSeting.do',
							callback : function() {
								if (this.callback) {
									this.callback.call(this.scope);
								}
							},
							success : function(fp, action) {
								this.close();
							}
						});
			}// end of save
		});
