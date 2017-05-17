/**
 * 联系人详细信息
 * 
 * @author zqg
 * @class PhoneBookForm
 * @extends Window
 */
Ext.ns('PhoneBookForm')
PhoneBookForm = Ext.extend(Ext.Window, {
	// 构造方法
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化
		this.initUIComponents();
		// 调用父类构造方法
		PhoneBookForm.superclass.constructor.call(this, {
					id : 'PhoneBookForm',
					title : '联系人详细信息',
					iconCls : "menu-personal-phoneBook",
					width : 620,
					height : 340,
					layout : 'fit',
					defaults : {
						padding : '5'
					},
					plain : true,
					border : false,
					buttonAlign : 'center',
					items : this.formPanel,
					buttons : this.buttons
				});
	},
	// 初始化组件
	initUIComponents : function() {
		// 联系人信息编辑面板
		this.formPanel = new Ext.FormPanel({
			url : __ctxPath + '/communicate/savePhoneBook.do',
			layout : 'form',
			frame : false,
			items : [{
						xtype : 'hidden',
						name : 'phoneBook.phoneId'
					}, {
						layout : 'column',
						border : false,
						items : [{
							layout : 'form',
							columnWidth : .6,
							border : false,
							defaults : {
								width : 162
							},
							items : [{
								fieldLabel : '分组',
								hiddenId : 'groupId',
								hiddenName : 'phoneBook.phoneGroup.groupId',
								name : 'phoneBook.phoneGroup.groupName',
								xtype : 'combotree',
								url : __ctxPath
										+ '/communicate/listPhoneGroup.do?method=1&isPublic='
										+ this.isPublic
							}, {
								xtype : 'textfield',
								fieldLabel : '姓名*',
								allowBlank : false,
								width : 162,
								name : 'phoneBook.fullname'
							}, {
								xtype : 'datefield',
								fieldLabel : '出生时间',
								name : 'phoneBook.birthday',
								editable : false,
								format : 'Y-m-d',
								length : 50,
								width : 162

							}]
						}, {
							layout : 'form',
							columnWidth : .4,
							border : false,
							items : [{
										fieldLabel : '称谓*',
										xtype : 'combo',
										anchor : '95%',
										allowBlank : false,
										name : 'phoneBook.title',
										mode : 'local',
										editable : false,
										triggerAction : 'all',
										store : ['先生', '女士']
									}, {
										layout : 'form',
										border : false,
										hidden : this.isPublic,
										defaults : {
											anchor : '96%,96%'
										},
										items : [{
													xtype : 'combo',
													fieldLabel : '是否共享*',
													allowBlank : false,
													hiddenName : 'phoneBook.isShared',
													mode : 'local',
													value : '0',
													editable : false,
													triggerAction : 'all',
													store : [['0', '否'],
															['1', '是']],
													width : 80
												}]
									}, {
										xtype : 'textfield',
										fieldLabel : '昵称',
										anchor : '95%',
										name : 'phoneBook.nickName'
									}]
						}]
					}, {
						xtype : 'tabpanel',
						plain : true,
						activeTab : 0,
						height : 180,
						deferredRender : false,
						defaults : {
							bodyStyle : 'padding:10px'
						},
						items : [{
							title : '联系方式',
							layout : 'form',
							defaults : {
								width : 300
							},
							defaultType : 'textfield',
							items : [{
										fieldLabel : '手机号码',
										allowBlank : false,
										blankText : '手机号码不能为空!',
										name : 'phoneBook.mobile',
										maxLength : 12,
										regex : /(86)*0*1\d{10}/,
										regexText : '移动电话输入有误！'
									}, {
										fieldLabel : '固定电话',
										name : 'phoneBook.phone',
										maxLength : 12,
										regex : /(^(\d{3,4}-)?\d{7,8})$|(1[0-9]{10})/,
										regexText : '电话号码输入有误！'
									}, {
										fieldLabel : 'Email',
										name : 'phoneBook.email',
										vtype : 'email',
										vtypeText : '邮箱格式不正确!',
										allowBlank : false,
										blankText : '邮箱不能为空!',
										maxLength : 32
									}, {
										fieldLabel : 'QQ',
										name : 'phoneBook.qqNumber',
										maxLength : 32
									}, {
										fieldLabel : 'MSN',
										name : 'phoneBook.msn',
										maxLength : 128
									}]
						}, {
							title : '公司',
							layout : 'form',
							defaults : {
								width : 300
							},
							defaultType : 'textfield',
							items : [{
										fieldLabel : '职务',
										name : 'phoneBook.duty',
										maxLength : 50
									}, {
										fieldLabel : '单位名称',
										name : 'phoneBook.companyName',
										maxLength : 100
									}, {
										fieldLabel : '单位地址',
										name : 'phoneBook.companyAddress',
										maxLength : 128
									}, {
										fieldLabel : '单位电话',
										name : 'phoneBook.companyPhone',
										maxLength : 12
										// ,
									// regex :
									// /(^(\d{3,4}-)?\d{7,8})$|(13[0-9]{9})/,
									// regexText : '电话号码输入有误！'
								}	, {
										fieldLabel : '单位传真',
										name : 'phoneBook.companyFax',
										maxLength : 32
									}]
						}, {
							title : '家庭',
							layout : 'form',
							defaults : {
								width : 300
							},
							defaultType : 'textfield',
							items : [{
										fieldLabel : '家庭住址',
										name : 'phoneBook.homeAddress',
										maxLength : 128
									}, {
										fieldLabel : '家庭邮编',
										name : 'phoneBook.homeZip',
										maxLength : 12
									}, {
										fieldLabel : '配偶',
										name : 'phoneBook.spouse',
										maxLength : 32
									}, {
										fieldLabel : '子女',
										name : 'phoneBook.childs',
										maxLength : 40
									}]
						}, {
							cls : 'x-plain',
							title : '备注',
							layout : 'fit',
							items : {
								xtype : 'textarea',
								fieldLabel : '备注',
								name : 'phoneBook.note',
								maxLength : 500
							}
						}]
					}]
		});
		// 加载数据
		if (this.phoneId != null && this.phoneId != 'undefined') {
			var formPanel = this.formPanel;
			formPanel.loadData({
				url : __ctxPath + '/communicate/getPhoneBook.do?phoneId='
						+ this.phoneId,
				preName : 'phoneBook',
				root : 'data',
				scope : this,
				waitMsg : '正在载入数据...',
				success : function(response, action) {
					var json = Ext.util.JSON.decode(response.responseText);
					var phoneBook = json.data;
					if (phoneBook.phoneGroup) {// 初始化赋值
						Ext.getDom('groupId').value = phoneBook.phoneGroup.groupId;
					}
				},
				failure : function(form, action) {
					Ext.ux.Toast.msg('编辑', '载入失败');
				}
			});
		};

		// 底部菜单面板
		this.buttons = [{
					xtype : 'button',
					text : '保存',
					iconCls : 'btn-save',
					scope : this,
					handler : this.save
				}, {
					xtype : 'button',
					text : '取消',
					iconCls : 'btn-cancel',
					handler : this.cancel.createCallback(this)
				}];
	},
	// 保存
	save : function(fp, win) {
		var formPanel = this.formPanel;
		$postForm({
					formPanel : formPanel,
					scope : this,
					url : __ctxPath + '/communicate/savePhoneBook.do',
					callback : function(fp, action) {
						if (this.callback) {
							this.callback.call(this.scope);
						}
						this.close();
					}
				});
	},// save method end
	// 关闭
	cancel : function(self) {
		self.close();
	}// cancel method end
});
