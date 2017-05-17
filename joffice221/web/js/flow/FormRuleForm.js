/**
 * @author
 * @createtime
 * @class FormRuleForm
 * @extends Ext.Window
 * @description 表单验证规则表单
 * @company 宏天软件
 */
FormRuleForm = Ext.extend(Ext.Window, {
			// 构造函数
			constructor : function(conf) {
				Ext.applyIf(this, conf);
				// 必须先初始化组件
				this.initUIComponents();
				FormRuleForm.superclass.constructor.call(this, {
							id : 'FormRuleFormWin',
							layout : 'fit',
							items : this.formPanel,
							modal : true,
							height : 250,
							width : 450,
							maximizable : true,
							title : '表单规则详细信息',
							buttonAlign : 'center',
							buttons : [{
										text : '保存',
										iconCls : 'btn-save',
										scope : this,
										handler : this.save
									}, {
										text : '重置',
										iconCls : 'btn-reset',
										scope : this,
										handler : this.reset
									}, {
										text : '取消',
										iconCls : 'btn-cancel',
										scope : this,
										handler : this.cancel
									}]
						});
			},// end of the constructor
			// 初始化组件
			initUIComponents : function() {
				this.formPanel = new Ext.FormPanel({
							layout : 'form',
							bodyStyle : 'padding:10px',
							border : false,
							autoScroll : true,
							defaults : {
								anchor : '96%,96%'
							},
							defaultType : 'textfield',
							items : [{
										name : 'formRule.ruleId',
										xtype : 'hidden',
										value : this.ruleId == null
												? ''
												: this.ruleId
									}, {
										fieldLabel : '规则名称',
										name : 'formRule.name',
										allowBlank : false,
										maxLength : 128
									}, {
										fieldLabel : '验证表达式',
										name : 'formRule.rule',
										allowBlank : false,
										blankText : '请输入正则表达式',
										maxLength : 128
									},  {
										fieldLabel : '提示信息',
										name : 'formRule.tipInfo',
										allowBlank : false,
										maxLength : 128
									}, {
										fieldLabel : '备注',
										name : 'formRule.memo',
										xtype : 'textarea',
										maxLength : 256
									},{
										xtype : 'container',
										layout : 'column',
										fieldLabel : '<font color="green">测试验证值</font>',
										items : [{
													columnWidth : .99,
													name : 'validValue',
													xtype : 'textfield'
												}, {
													xtype : 'button',
													text : '验证',
													iconCls : 'btn-ok',
													iconAlign : 'left',
													width : 75,
													scope : this,
													handler : this.valid
												}]

									}]
						});
				// 加载表单对应的数据
				if (!Ext.isEmpty(this.ruleId)) {
					this.formPanel.loadData({
								url : __ctxPath
										+ '/flow/getFormRule.do?ruleId='
										+ this.ruleId,
								root : 'data',
								preName : 'formRule'
							});
				}

			},// end of the initcomponents
			/**
			 * 验证
			 */
			valid : function() {
				var rule = this.formPanel.getCmpByName('formRule.rule');
				var valid = this.formPanel.getCmpByName('validValue');
				if (Ext.isEmpty(rule.getValue())) {
					Ext.ux.Toast.msg('操作信息', '请输入验证表达式！');
					rule.focus();
					return;
				}
				if (Ext.isEmpty(valid.getValue())) {
					Ext.ux.Toast.msg('操作信息', '请输入要验证值！');
					valid.focus();
					return;
				}
				var rtn = this.testReg(rule.getValue(), valid.getValue())
				if (rtn) {
					Ext.MessageBox.show({
								title : '提示信息',
								msg : '验证通过',
								buttons : Ext.MessageBox.OK,
								icon : 'ext-mb-info'
							});
					valid.focus();
					return;
				} else {
					Ext.MessageBox.show({
								title : '提示信息',
								msg :  '验证不通过',
								buttons : Ext.MessageBox.OK,
								icon : 'ext-mb-error'
							});
					valid.focus();
					return;
				}

			},
			/**
			 * 验证正则表达式
			 * 
			 * @param {}
			 *            rule 規則
			 * @param {}
			 *            toValid 驗證的值
			 * @return {}
			 */
			testReg : function(rule, toValid) {
				var reg = new RegExp(rule);
				var rtn = reg.test(toValid);
				return rtn;
			},
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
							url : __ctxPath + '/flow/saveFormRule.do',
							callback : function(fp, action) {
								if (this.callback) {
									this.callback.call(this.scope);
								}
								this.close();
							}
						});
			}// end of save

		});