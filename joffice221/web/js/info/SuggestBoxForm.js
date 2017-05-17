/**
 * @author
 * @createtime
 * @class SuggestBoxForm
 * @extends Ext.Window
 * @description SuggestBox表单
 * @company 宏天软件
 */
SuggestBoxForm = Ext.extend(Ext.Window, {
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 必须先初始化组件
				this.initUIComponents();
				// 调用父类构造
				SuggestBoxForm.superclass.constructor.call(this, {
							id : 'SuggestBoxFormWin',
							layout : 'fit',
							items : this.formPanel,
							modal : true,
							height : 690,
							iconCls : 'menu-suggestbox',
							width : 800,
							maximizable : true,
							title : '意见详细信息',
							buttonAlign : 'center',
							buttons : this.buttons
						});
			},// end of the constructor
			// 初始化组件
			initUIComponents : function() {
				this.formPanel = new Ext.FormPanel({
							layout : 'form',
							bodyStyle : 'padding:10px 10px 10px 10px',
							border : false,
							defaults : {
								anchor : '95%,95%'
							},
							autoScroll : true,
							defaultType : 'textfield',
							items : [{
										name : 'suggestBox.boxId',
										xtype : 'hidden',
										value : this.boxId == null
												? ''
												: this.boxId
									}, {
										name : 'suggestBox.status',
										xtype : 'hidden',
										value : 0
									}, {
										xtype : 'radiogroup',
										fieldLabel : '签名方式',
										autoHeight : true,
										columns : 2,
										width : 520,
										items : [{
													boxLabel : '使用签名',
													name : 'SuggestBoxSign',
													inputValue : 1,
													checked : true,
													scope : this,
													handler : this.userSign
												}, {
													boxLabel : '匿名',
													name : 'SuggestBoxSign',
													inputValue : 0
												}]
									}, {
										xtype : 'fieldset',
										title : '个人信息',
										name : 'PersonalInfo',
										defaults : {
											anchor : '100%'
										},
										defaultType : 'textfield',
										layout : 'form',
										items : [{
											xtype : 'container',
											layout : 'column',
											height : 27,
											defaultType : 'textfield',
											fieldLabel : '发送人',
											items : [{
												columnWidth : .5,
												name : 'suggestBox.senderFullname',
												value : curUserInfo != null
														? curUserInfo.fullname
														: null
											}, {
												columnWidth : .15,
												xtype : 'label',
												text : '　　联系电话:',
												width : 103
											}, {
												columnWidth : .35,
												name : 'suggestBox.phone'
											}]
										}, {
											fieldLabel : 'Email',
											name : 'suggestBox.email'
										}]
									}, {
										fieldLabel : '意见标题',
										allowBlank : false,
										name : 'suggestBox.subject'
									}, {
										fieldLabel : '意见内容',
										name : 'suggestBox.content',
										allowBlank : false,
										height:360,
										xtype : 'ckeditor'
										
									}, {
										fieldLabel : '发送人ID',
										name : 'suggestBox.senderId',
										xtype : 'hidden',
										value : curUserInfo != null
												? curUserInfo.userId
												: null
									}, {
										xtype : 'radiogroup',
										fieldLabel : '是否公开',
										autoHeight : true,
										columns : 2,
										width : 520,
										items : [{
													boxLabel : '公开',
													name : 'suggestBox.isOpen',
													inputValue : 0,
													checked : true,
													scope : this,
													handler : this.isOpen
												}, {
													boxLabel : '不公开',
													name : 'suggestBox.isOpen',
													inputValue : 1
												}]
									}, {
										xtype : 'fieldset',
										title : '查询密码（选填）',
										hidden : true,
										name : 'SuggestBoxFormQueryPwd',
										defaults : {
											anchor : '100%'
										},
										defaultType : 'textfield',
										layout : 'form',
										items : [{
											xtype : 'container',
											layout : 'column',
											height : 27,
											defaultType : 'textfield',
											fieldLabel : '查询密码',
											items : [{
												columnWidth : .5,
												name : 'suggestBox.queryPwd',
												inputType : 'password'
											}, {
												columnWidth : .15,
												xtype : 'label',
												text : '　　密码确认:',
												width : 103
											}, {
												columnWidth : .35,
													name : 'SureQueryPwd',
													inputType : 'password',
													listeners : {
														scope : this,
														change : this.sureQueryPwd
													}
											}]
										}] 
									}

							]
						});
				// 加载表单对应的数据
				if (this.boxId != null && this.boxId != 'undefined') {
					this.formPanel.loadData({
								url : __ctxPath
										+ '/info/getSuggestBox.do?boxId='
										+ this.boxId,
								preName : 'suggestBox',
								root : 'data'
							});
				}
				// 初始化功能按钮
				this.buttons = [{
							text : '提交',
							iconCls : 'btn-ok',
							scope : this,
							handler : this.draft
						}, {
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
						}];
			},// end of the initcomponents

			/**
			 * 重置
			 * 
			 * @param {}
			 *            formPanel
			 */
			reset : function() {
				this.formPanel.getForm().reset();
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
			save : function() {
				var formPanel = this.formPanel;
				var content = formPanel.getCmpByName('suggestBox.content')
						.getValue();
				if (Ext.isEmpty(content)) {
					Ext.ux.Toast.msg('操作信息', '意见内容不能为空！');
					formPanel.getCmpByName('suggestBox.content').focus(true);
					return;
				}
				if (formPanel.getForm().isValid()) {
					$postForm({
								formPanel : formPanel,
								scope : this,
								url : __ctxPath + '/info/saveSuggestBox.do',
								callback : function(fp, action) {
									if (this.callback) {
										this.callback.call(this.scope);
									}
									this.close();
								}
							});
				}
			},// end of save
			/**
			 * 提交
			 */
			draft : function() {
				this.formPanel.getCmpByName('suggestBox.status').setValue('1');
				this.save();
			},// end of draft
			/**
			 * 使用签名
			 * 
			 * @param {}
			 *            value
			 */
			userSign : function(value) {
				var formPanel = this.formPanel;
				if (value.getValue() != true) {
					formPanel.getCmpByName('PersonalInfo').hide();
					formPanel.getCmpByName('suggestBox.senderFullname')
							.setValue('');
					formPanel.getCmpByName('suggestBox.senderId').setValue('');
				} else {
					formPanel.getCmpByName('PersonalInfo').show();
					if (curUserInfo != null) {
						formPanel.getCmpByName('suggestBox.senderFullname')
								.setValue(curUserInfo.fullname);
						formPanel.getCmpByName('suggestBox.senderId')
								.setValue(curUserInfo.userId);
					}
				}
			},
			// 是否公开
			isOpen : function(value) {
				var formPanel = this.formPanel;
				if (value.getValue() == true) {
					formPanel.getCmpByName('SuggestBoxFormQueryPwd').hide();
				} else {
					formPanel.getCmpByName('SuggestBoxFormQueryPwd').show();
				}
			},
			// 确认密码
			sureQueryPwd : function(field, newValue, oldValue) {
				var queryPwd = this.formPanel
						.getCmpByName('suggestBox.queryPwd');
				if (queryPwd.getValue() != field.getValue()) {
					Ext.Msg.alert('注意', '两次输入的密码不一致,请重新填写.');
					queryPwd.setValue('');
					field.setValue('');
					queryPwd.focus(true);

				}
			}
		});