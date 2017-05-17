/**
 * @author
 * @createtime
 * @class EmpProfileForm
 * @extends Ext.Window
 * @description EmpProfile表单
 * @company 宏天软件
 */
EmpProfileForm = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initComponents();
		EmpProfileForm.superclass.constructor.call(this, {
					id : 'EmpProfileForm',
					title : '档案详细信息',
					iconCls : 'menu-profile-create',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					tbar : this.topbar,
					maximizable : true,
					buttonAlign : 'center'
				});
	},// end of the constructor
	// 初始化组件
	initComponents : function() {
		// formPanel
		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			autoScroll : true,
			tbar : this.topbar,
			bodyStyle : 'padding:10px 20px 10px 10px',
			border : false,
			defaults : {
				anchor : '98%,98%'
			},
			defaultType : 'textfield',
			items : [{ // ----------------------------------hidden start
				name : 'empProfile.profileId',
				xtype : 'hidden',
				value : this.profileId == null ? '' : this.profileId
			}, {
				fieldLabel : '建档人',
				name : 'empProfile.creator',
				xtype : 'hidden'
			}, {
				fieldLabel : '建档时间',
				name : 'empProfile.createtime',
				xtype : 'hidden'
			}, {
				fieldLabel : '审核人',
				name : 'empProfile.checkName',
				xtype : 'hidden'
			}, {
				fieldLabel : '审核时间',
				name : 'empProfile.checktime',
				xtype : 'hidden'
			}, {
				fieldLabel : '核审状态',// 0=未审批 1=通过审核 2=未通过审核',
				name : 'empProfile.approvalStatus',
				xtype : 'hidden'
			}, {
				fieldLabel : '删除状态',// 0=未删除 1=删除',
				name : 'empProfile.delFlag',
				xtype : 'hidden'
			}, {
				fieldLabel : '所属职位',
				name : 'empProfile.jobId',
				xtype : 'hidden'
			}, {
				fieldLabel : '照片',
				name : 'empProfile.photo',
				xtype : 'hidden'
			}, {
				fieldLabel : '薪酬标准单编号',
				name : 'empProfile.standardId',
				xtype : 'hidden'
			}, {
				fieldLabel : '所属员工ID',
				name : 'empProfile.userId',
				xtype : 'hidden'
			}, {	// ----------------------------------hidden end
						xtype : 'container',
						layout : 'column',
						height : 26,
						anchor : '100%',
						items : [{
									xtype : 'label',
									style : 'padding:3px 5px 0px 17px;',
									text : '档案编号:'
								}, {
									name : 'empProfile.profileNo',
									width : 200,
									xtype : 'textfield',
									readOnly : true,
									allowBlank : false,
									blankText : '档案编号不能为空!'
								}, {
									xtype : 'button',
									iconCls : 'btn-system-setting',
									text : '系统生成',
									hidden : Ext.isEmpty(this.profileId)
											? false
											: true,
									scope : this,
									handler : this.genNumber
								}]
					}, {
						xtype : 'container',
						height : 26,
						layout : 'column',
						anchor : '100%',
						items : [{
									xtype : 'label',
									style : 'padding:3px 5px 0px 17px;',
									text : '员工姓名:'
								}, {
									width : 200,
									xtype : 'textfield',
									name : 'empProfile.fullname',
									allowBlank : false,
									blankText : '姓名不能为空！',
									readOnly : true
								}, {
									xtype : 'button',
									text : '选择员工',
									name : 'EmpProfileSelectEmp',
									iconCls : 'btn-mail_recipient',
									scope : this,
									handler : this.selectUser
								}]
					}, {
						xtype : 'fieldset',
						title : '基本资料',
						layout : 'column',
						items : [{
									xtype : 'container',
									columnWidth : .37,
									defaultType : 'textfield',
									layout : 'form',
									defaults : {
										anchor : '96%,96%'
									},
									items : [{
												fieldLabel : '身份证号',
												name : 'empProfile.idCard'
											}, {
												fieldLabel : '出生日期',
												name : 'empProfile.birthday',
												xtype : 'datefield',
												format : 'Y-m-d'
											}, {
												fieldLabel : '性别',
												name : 'empProfile.sex',
												xtype : 'combo',
												editable : false,
												mode : 'local',
												triggerAction : 'all',
												store : ['男', '女']
											}, {
												fieldLabel : '婚姻状况',// 已婚 未婚',
												name : 'empProfile.marriage',
												xtype : 'combo',
												editable : false,
												mode : 'local',
												triggerAction : 'all',
												store : ['已婚', '未婚']
											}]
								}, {
									xtype : 'container',
									columnWidth : .37,
									defaultType : 'textfield',
									layout : 'form',
									defaults : {
										anchor : '96%,96%'
									},
									items : [{
												fieldLabel : '宗教信仰',
												name : 'empProfile.religion',
												xtype : 'diccombo',
												nodeKey : 'religion'
											}, {
												fieldLabel : '政治面貌',
												name : 'empProfile.party',
												xtype : 'diccombo',
												nodeKey : 'political_status'
											}, {
												fieldLabel : '国籍',
												name : 'empProfile.nationality',
												xtype : 'diccombo',
												nodeKey : 'nationality'
											}, {
												fieldLabel : '民族',
												name : 'empProfile.race',
												xtype : 'diccombo',
												nodeKey : 'nation'
											}, {
												fieldLabel : '出生地',
												name : 'empProfile.birthPlace'
											}]
								}, {
									xtype : 'container',
									columnWidth : .26,
									layout : 'column',
									items : [{
												xtype : 'label',
												text : '个人相片:',
												style : 'padding-left:0px;'
											}, {
												xtype : 'container',
												layout : 'form',
												width : 100,
												items : [{
													id : 'ProfilePhotoPanel',
													height : 120,
													width : 88,
													xtype : 'panel',
													border : true,
													html : '<img src="'
															+ __ctxPath
															+ '/images/default_person.gif" width="88" height="120"/>'
												}, {
													xtype : 'button',
													style : 'padding-top:3px;',
													iconCls : 'btn-upload',
													text : '上传照片',
													scope : this,
													handler : this.uploadClick
												}]
											}]
								}]
					}, {
						xtype : 'fieldset',
						title : '职务薪酬信息',
						defaultType : 'textfield',
						layout : 'column',
						items : [{
							xtype : 'container',
							columnWidth : .5,
							defaultType : 'textfield',
							layout : 'form',
							defaults : {
								anchor : '96%,96%'
							},
							items : [{
								fieldLabel : '所属部门或公司',
								hiddenName : 'empProfile.depId',
								name : 'empProfile.depName',
								xtype : 'combotree',
								url : __ctxPath
										+ '/system/listDepartment.do?opt=appUser'
							}, {
								fieldLabel : '职称',
								name : 'empProfile.designation'
							}, {
								fieldLabel : '开户银行',
								name : 'empProfile.openBank'
							}, {
								fieldLabel : '银行账号',
								name : 'empProfile.bankNo'
							}]
						}, {
							xtype : 'container',
							columnWidth : .5,
							defaultType : 'textfield',
							layout : 'form',
							defaults : {
								anchor : '96%,96%'
							},
							items : [{
										xtype : 'container',
										layout : 'column',
										border : false,
										fieldLabel : '职位',
										items : [{
													columnWidth : .99,
													xtype : 'textfield',
													name : 'empProfile.position',
													allowBlank : false,
													blankText : '职位不能为空！',
													readOnly : true
												}, {
													width : 80,
													text : '请选择',
													xtype : 'button',
													iconCls : 'btn-position-sel',
													scope : this,
													handler : this.positionSelect
												}]
									}, {
										fieldLabel : '薪酬标准单名称',
										name : 'empProfile.standardName',
										xtype : 'combo',
										mode : 'local',
										allowBlank : false,
										editable : false,
										valueField : 'standardName',
										displayField : 'standardName',
										triggerAction : 'all',
										store : new Ext.data.JsonStore({
											url : __ctxPath
													+ '/hrm/comboStandSalary.do',
											fields : [{
														name : 'standardId',
														type : 'int'
													}, 'standardNo',
													'standardName',
													'totalMoney',
													'setdownTime', 'status']
										}),
										listeners : {
											scope : this,
											focus : function() {
												this.formPanel
														.getForm()
														.findField('empProfile.standardName')
														.getStore().reload();
											},
											select : function(combo, record,
													index) {
												this.formPanel
														.getForm()
														.findField('empProfile.standardId')
														.setValue(record.data.standardId);
												this.formPanel
														.getForm()
														.findField('empProfile.standardMiNo')
														.setValue(record.data.standardNo);
												this.formPanel
														.getForm()
														.findField('empProfile.standardMoney')
														.setValue(record.data.totalMoney);
											}
										}
									}, {
										fieldLabel : '薪酬标准编号',
										name : 'empProfile.standardMiNo',
										readOnly : true
									}, {
										fieldLabel : '薪酬标准金额',
										name : 'empProfile.standardMoney',
										readOnly : true
									}]
						}, {
							xtype : 'container',
							columnWidth : 1,
							defaultType : 'textfield',
							layout : 'form',
							defaults : {
								anchor : '96%,96%'
							},
							items : [{
										fieldLabel : '培训情况',
										name : 'empProfile.trainingCase',
										xtype : 'textarea'
									}]

						}]
					}, {
						xtype : 'fieldset',
						title : '联系方式',
						defaultType : 'textfield',
						layout : 'column',
						items : [{
									xtype : 'container',
									columnWidth : .5,
									defaultType : 'textfield',
									layout : 'form',
									defaults : {
										anchor : '96%,96%'
									},
									items : [{
												fieldLabel : '家庭地址',
												name : 'empProfile.address'
											}, {
												fieldLabel : '家庭邮编',
												name : 'empProfile.homeZip'
											}, {
												fieldLabel : '手机号码',
												name : 'empProfile.mobile'
											}]
								}, {
									xtype : 'container',
									columnWidth : .5,
									defaultType : 'textfield',
									layout : 'form',
									defaults : {
										anchor : '96%,96%'
									},
									items : [{
												fieldLabel : '电话号码',
												name : 'empProfile.phone'
											}, {
												fieldLabel : 'QQ号码',
												name : 'empProfile.qq'
											}, {
												fieldLabel : '电子邮箱',
												name : 'empProfile.email',
												vtype : 'email',
												vtypeText : '邮箱格式不正确!'
											}]

								}]
					}, {
						xtype : 'fieldset',
						title : '教育情况',
						defaultType : 'textfield',
						layout : 'column',
						items : [{
									xtype : 'container',
									columnWidth : .5,
									defaultType : 'textfield',
									layout : 'form',
									defaults : {
										anchor : '96%,96%'
									},
									items : [{
												fieldLabel : '学历',
												name : 'empProfile.eduDegree',
												xtype : 'diccombo',
												nodeKey : 'education'
											}, {
												fieldLabel : '专业',
												name : 'empProfile.eduMajor',
												xtype : 'diccombo',
												nodeKey : 'major'
											}]
								}, {
									xtype : 'container',
									columnWidth : .5,
									defaultType : 'textfield',
									layout : 'form',
									defaults : {
										anchor : '96%,96%'
									},
									items : [{
												fieldLabel : '毕业院校',
												name : 'empProfile.eduCollege'
											}, {
												fieldLabel : '参加工作时间',
												name : 'empProfile.startWorkDate',
												xtype : 'datefield',
												format : 'Y-m-d'
											}]

								}, {
									xtype : 'container',
									columnWidth : 1,
									defaultType : 'textfield',
									layout : 'form',
									defaults : {
										anchor : '96%,96%'
									},
									items : [{
												fieldLabel : '教育背景',
												name : 'empProfile.eduCase',
												xtype : 'textarea'
											}]
								}]
					}, {
						xtype : 'fieldset',
						title : '奖惩情况',
						layout : 'anchor',
						items : [{
									fieldLabel : '奖惩情况',
									name : 'empProfile.awardPunishCase',
									xtype : 'textarea',
									anchor : '100%'
								}]
					}, {
						xtype : 'fieldset',
						title : '工作经历',
						layout : 'anchor',
						items : [{
									fieldLabel : '工作经历',
									name : 'empProfile.workCase',
									xtype : 'textarea',
									anchor : '100%'
								}]
					}, {
						xtype : 'fieldset',
						title : '个人爱好',
						layout : 'anchor',
						items : [{
									fieldLabel : '爱好',
									name : 'empProfile.hobby',
									anchor : '100%',
									xtype : 'textarea'
								}]
					}, {
						xtype : 'fieldset',
						title : '备注',
						layout : 'anchor',
						items : [{
									fieldLabel : '备注',
									name : 'empProfile.memo',
									anchor : '100%',
									xtype : 'textarea'
								}]
					}]
		});
		this.topbar = new Ext.Toolbar({
					height : 30,
					bodyStyle : 'text-align:left',
					defaultType : 'button',
					items : [{
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
		// 加载表单对应的数据
		if (this.profileId != null && this.profileId != 'undefined') {
			this.formPanel.loadData({
						url : __ctxPath + '/hrm/getEmpProfile.do?profileId='
								+ this.profileId,
						preName : 'empProfile',
						root : 'data',
						waitMsg : '正在载入数据...',
						scope : this,
						success : function(response, options) {
							var res = Ext.util.JSON
									.decode(response.responseText).data;
							this.formPanel.getForm()
									.findField('empProfile.profileNo').getEl().dom.readOnly = true;
							this.formPanel.getCmpByName('EmpProfileSelectEmp')
									.hide();

							// 载入照片
							var photo = res.photo;

							if (photo != null && photo != '') {
								Ext.getCmp('ProfilePhotoPanel').body
										.update('<img src="' + __ctxPath
												+ '/attachFiles/' + photo
												+ '" width="88" height="120"/>');
							}

						},
						failure : function(response, options) {
						}
					});
		}
	},// end of the initcomponents
	/**
	 * 职位选择
	 */
	positionSelect : function() {
		var formPanel = this.formPanel;
		new PositionDialog({
					single : true,
					callback : function(posIds, posNames) {
						formPanel.getForm().findField('empProfile.position')
								.setValue(posNames);
						formPanel.getForm().findField('empProfile.jobId')
								.setValue(posIds);
					}
				}).show();
	},
	/**
	 * 上传图片处理函数
	 */
	uploadClick : function() {
		var formPanel = this.formPanel;
		var photo = formPanel.getForm().findField('empProfile.photo');
		var dialog = App.createUploadDialog({
					file_cat : 'hrm/hrmManage/empProfile',
					callback : function uploadResumePhoto(data) {
						var display = Ext.getCmp('ProfilePhotoPanel');
						photo.setValue(data[0].filePath);
						display.body.update('<img src="' + __ctxPath
								+ '/attachFiles/' + data[0].filePath
								+ '"  width="88" height="120"/>');
					},
					permitted_extensions : ['jpg', 'png', 'gif', 'bmp']
				});
		if (photo.getValue() != '' && photo.getValue() != null
				&& photo.getValue() != 'undefined') {
			var msg = '再次上传需要先删除原有图片,';
			Ext.Msg.confirm('信息确认', msg + '是否删除？', function(btn) {
				if (btn == 'yes') {
					// 删除图片
					var profileId = formPanel.getForm()
							.findField('empProfile.profileId').getValue();
					Ext.Ajax.request({
						url : __ctxPath + '/hrm/delphotoEmpProfile.do',
						method : 'post',
						params : {
							profileId : profileId
						},
						scope : this,
						success : function() {
							var path = photo.value;
							photo.setValue('');
							Ext.getCmp('ProfilePhotoPanel').body
									.update('<img src="'
											+ __ctxPath
											+ '/images/default_person.gif" width="88" height="120"/>');
							Ext.Ajax.request({
								url : __ctxPath + '/system/deleteFileAttach.do',
								method : 'post',
								params : {
									filePath : path
								},
								success : function() {
									dialog.show('queryBtn');
								}
							});
						}
					});
				}
			});
		} else {
			dialog.show('queryBtn');
		}
	},
	/**
	 * 系统生成
	 */
	autoGenerate : function() {
		Ext.Ajax.request({
					url : __ctxPath + '/hrm/numberEmpProfile.do',
					scope : this,
					success : function(response) {
						var result = Ext.util.JSON
								.decode(response.responseText);
						this.formPanel.getForm()
								.findField('empProfile.profileNo')
								.setValue(result.profileNo);
					}
				});
	},
	/**
	 * 生成编码
	 */
	genNumber : function() {
		$genNumber({
			scope : this,
			alias : 'EmpProfile',
			callback : function(number) {
				this.formPanel.getCmpByName('empProfile.profileNo').setValue(number);
			}
		});
	},
	/**
	 * 选择员工
	 */
	selectUser : function() {
		var formPanel = this.formPanel;
		new UserDialog({
			single : true,
			scope : this,
			callback : function(userId, fullname) {
				formPanel.getForm().findField('empProfile.fullname')
						.setValue(fullname);
				formPanel.getForm().findField('empProfile.userId')
						.setValue(userId);
				Ext.Ajax.request({
					url : __ctxPath + '/system/getAppUser.do',
					params : {
						userId : userId
					},
					method : 'post',
					scope : this,
					success : function(response) {
						var result = Ext.util.JSON
								.decode(response.responseText).data[0];
						if (!result)
							return;
						Ext.getCmp('empProfile.depId').value = result.department.depId;
					}
				});
			}
		}).show();
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
		var tabs = Ext.getCmp('centerTabPanel');
		if (this.formPanel != null) {
			tabs.remove('EmpProfileForm');
		}
	},
	/**
	 * 保存记录
	 */
	save : function() {
		$postForm({
					formPanel : this.formPanel,
					waitMsg : '正在提交数据...',
					scope : this,
					url : __ctxPath + '/hrm/saveEmpProfile.do',
					callback : function(fp, action) {
						var EmpProfileView = Ext.getCmp('EmpProfileView');
						if (EmpProfileView != null) {
							EmpProfileView.gridPanel.getStore().reload();
							AppUtil.removeTab('EmpProfileForm');
						}
					}
				});
	}// end of save

});