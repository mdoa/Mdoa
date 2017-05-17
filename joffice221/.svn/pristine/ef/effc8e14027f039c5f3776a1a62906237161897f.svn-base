Ext.ns('ProfileForm');
/**
 * @author
 * @createtime
 * @class ProfileForm
 * @extends Ext.Window
 * @description 修改个人资料表单
 * @company 宏天软件
 */
ProfileForm = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		// 必须先初始化组件
		this.initUIComponents();
		ProfileForm.superclass.constructor.call(this, {
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					id : 'ProfileForm',
					title : '个人资料',
					iconCls : 'menu-customer',
					buttonAlign : 'center',
					tbar : this.tbars
				});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		// 按钮组
		this.tbars = [{
					text : '保存',
					iconCls : 'btn-save',
					scope : this,
					handler : this.save
				}, '-', {
					text : '取消',
					iconCls : 'reset',
					scope : this,
					handler : this.reset
				}, '-', {
					text : '修改密码',
					iconCls : 'btn-password',
					scope : this,
					handler : this.resetPassword
				}]

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
				xtype : 'panel',
				frame : false,
				autoWidth : true,
				autoHeight : true,
				border : false,
				layout : 'table',
				bodyStyle : "margin-top:5px;margin-left: 17%; background-color: transparent;",
				layoutConfig : {
					columns : 2
				},
				items : [{
					id : 'displayProfilePhoto',
					xtype : "panel",
					width : 230,
					rowspan : 2,
					style : 'padding:3px 4px 25px 0px;',
					height : 320,
					title : "个人照片",
					html : '<img src="' + __ctxPath
							+ '/images/default_image_male.jpg"/>',
					tbar : new Ext.Toolbar({
								height : 30,
								items : [{
											text : '上传',
											iconCls : 'btn-upload',
											scope : this,
											handler : this.uploadPhoto

										}, '-', {
											text : '删除',
											iconCls : 'btn-delete',
											scope : this,
											handler : this.deletePhoto

										}]
							})
				}, {
					xtype : "panel",
					width : 305,
					height : 320,
					title : "个人资料",
					layout : 'form',
					style : 'padding:3px 4px 25px 0px;',
					defaultType : "textfield",
					defaults : {
						width : 203
					},
					labelWidth : 55,
					labelAlign : "right",
					hideLabels : false,
					items : [{
								xtype : 'hidden',
								fieldLabel : '员工ID',
								name : 'appUser.userId'
							}, {
								filedLabel : '照片',
								xtype : 'hidden',
								name : 'appUser.photo'
							}, {
								fieldLabel : '登录账号',
								name : 'appUser.username',
								readOnly : true
							}, {
								fieldLabel : '员工姓名',
								name : 'appUser.fullname',
								allowBlank : false,
								blankText : '员工姓名不能为空!'
							}, {
								fieldLabel : 'E-mail',
								name : 'appUser.email',
								vtype : 'email',
								allowBlank : false,
								blankText : '邮箱不能为空!',
								vtypeText : '邮箱格式不正确!'
							}, {
								fieldLabel : '性别',
								xtype : 'combo',
								hiddenName : 'appUser.title',
								mode : 'local',
								editable : false,
								triggerAction : 'all',
								store : [['1', '先生'], ['0', '女士']],
								value : '1',
								listeners : {
									scope : this,
									select : function(combo, record, index) {
										var photo = this.formPanel
												.getCmpByName('appUser.photo')
												.getValue();
										if (Ext.isEmpty(photo)) {
											this.setPhoto(photo);
										}
									}
								}
							}, {
								fieldLabel : '家庭电话',
								name : 'appUser.phone',
								maxLength : 12,
								regex : /(^(\d{3,4}-)?\d{7,8})$|(1[0-9]{10})/,
								regexText : '电话号码输入有误！'
							}, {
								fieldLabel : '移动电话',
								name : 'appUser.mobile',
								maxLength : 12,
								regex : /(86)*0*1\d{10}/,
								regexText : '移动电话输入有误！'
							}, {
								fieldLabel : '传真',
								// xtype : 'numberfield',
								name : 'appUser.fax',
								maxLength : 12,
								regex : /[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+/,
								regexText : '传真号码输入有误！'
							}, {
								fieldLabel : '家庭住址',
								name : 'appUser.address',
								maxLength : 64
							}, {
								fieldLabel : '邮编',
								xtype : 'numberfield',
								name : 'appUser.zip',
								maxLength : 6,
								regex : /[1-9]{1}(\d+){5}/,
								regexText : '邮政编号输入有误！'
							}]
				}]
			}]
		});

		// 初始化载入表单信息
		this.formPanel.loadData({
					url : __ctxPath + '/system/getAppUser.do',
					waitMsg : '正在载入数据...',
					preName : 'appUser',
					root : 'data',
					scope : this,
					success : function(response, options) {
						var data = Ext.util.JSON.decode(response.responseText).data;
						if (!data)
							return;
						// 当前用户id
						this.userId = data.userId;
						// 设置相片
						this.setPhoto(data.photo);
					},
					failure : function(response, options) {
						Ext.ux.Toast.msg('编辑', '载入失败');
					}
				});

	},
	/**
	 * 保存表单信息
	 */
	save : function() {
		$postForm({
					formPanel : this.formPanel,
					waitMsg : '正在提交数据...',
					url : __ctxPath + '/system/profileAppUser.do'
				});
	},
	/**
	 * 取消
	 */
	reset : function() {
		var tabs = Ext.getCmp('centerTabPanel');
		tabs.remove('ProfileForm');
	},
	/**
	 * 修改密码
	 */
	resetPassword : function() {
		new ResetPasswordForm({
					userId : this.userId
				}).show();
	},
	/**
	 * 设置相片
	 * 
	 * @param {}
	 *            filePath 当前相片路径
	 */
	setPhoto : function(filePath) {
		var photo = this.formPanel.getCmpByName('appUser.photo')
		var display = Ext.getCmp('displayProfilePhoto');
		if (!Ext.isEmpty(filePath)) {
			photo.setValue(filePath);
			var src = __ctxPath + '/attachFiles/' + filePath;
			display.body.update('<img src="' + src
					+ '"  width="100%" height="100%"/>');
		} else {// 默认相片
			photo.setValue('');
			var sex = this.formPanel.getCmpByName('appUser.title').getValue();
			if (sex == 1) {
				display.body.update('<img src="' + __ctxPath
						+ '/images/default_image_male.jpg" />');
			} else {
				display.body.update('<img src="' + __ctxPath
						+ '/images/default_image_female.jpg" />');
			}
		}
		return;

	},
	/**
	 * 上传logo 窗口
	 * 
	 * @return {}
	 */
	uploadDialog : function() {
		var dialog = App.createUploadDialog({
					file_cat : 'myDesktop/profile',
					scope : this,
					callback : function(data) {
						var filePath = '';
						if (!Ext.isEmpty(data)) {
							filePath = data[0].filePath;
						}
						// 设置相片
						this.setPhoto(filePath);
					},
					permitted_extensions : ['jpg', 'png', 'bmp', 'gif']
				})
		return dialog;
	},
	/**
	 * 上传相片
	 */
	uploadPhoto : function() {
		var filePath = this.formPanel.getCmpByName('appUser.photo').getValue();
		if (!Ext.isEmpty(filePath)) {
			this.deletePhoto('upload');
		} else {
			this.uploadDialog().show('queryBtn');
		}
	},
	/**
	 * 删除用户相片
	 * 
	 * @param {}
	 *            isUpload 是上传的，还是删除的
	 */
	deletePhoto : function(isUpload) {
		var msg = '';
		var userId = this.formPanel.getCmpByName('appUser.userId').getValue();
		// logo图片路径
		var filePath = this.formPanel.getCmpByName('appUser.photo').getValue();
		if (!Ext.isEmpty(filePath)) {
			if (isUpload != '' && isUpload == 'upload') {
				msg = '再次上传需要先删除原有图片,';
			} else {
				msg = '照片一旦删除将不可恢复,';
			}
			// 删除信息
			Ext.Msg.confirm('信息确认', msg + '是否删除？', function(btn) {
				if (btn == 'yes') {
					Ext.Ajax.request({
						url : __ctxPath + '/system/deleteFileAttach.do',
						method : 'post',
						params : {
							filePath : filePath
						},
						scope : this,
						success : function() {
							// 还原默认logo
							this.setPhoto('');
							// 删除照片
							if (!Ext.isEmpty(userId)) {
								Ext.Ajax.request({
									url : __ctxPath + '/system/photoAppUser.do',
									params : {
										userId : userId
									},
									method : 'post',
									scope : this,
									success : function() {
										if (isUpload != ''
												&& isUpload == 'upload') {
											this.uploadDialog()
													.show('queryBtn');
										}
									}
								});
							}

						}
					});
				}
			}, this);
		}// end if
		else {
			Ext.ux.Toast.msg('提示信息', '您还未增加照片.');
		}
	}
});