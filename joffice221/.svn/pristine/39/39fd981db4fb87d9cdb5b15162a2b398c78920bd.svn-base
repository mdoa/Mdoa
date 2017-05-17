Ext.ns('ResumeForm');
/**
 * 简历详细信息
 * 
 * @class ResumeForm
 * @extends Ext.Panel
 */
ResumeForm = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		// 必须先初始化组件
		this.initComponents();
		ResumeForm.superclass.constructor.call(this, {
					id : 'ResumeForm',
					layout : 'fit',
					iconCls : 'menu-resume',
					items : this.formPanel,
					height : 200,
					width : 400,
					tbar : this.toolbar,
					maximizable : true,
					title : '简历详细信息',
					buttonAlign : 'center'
				});
	},// end of the constructor
	initComponents : function() {
		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			bodyStyle : 'padding:10px 20px 10px 10px',
			border : false,
			autoScroll : true,
			defaults : {
				anchor : '98%,98%'
			},
			defaultType : 'textfield',
			items : [{
						name : 'resume.resumeId',
						xtype : 'hidden',
						value : this.resumeId == null ? '' : this.resumeId
					}, {
						xtype : 'hidden',
						name : 'resume.registor'
					}, {
						xtype : 'hidden',
						name : 'resume.regTime'
					}, {
						xtype : 'container',
						layout : 'column',
						height : 40,
						items : [{
							xtype : 'label',
							style : 'padding-left:48%;',
							html : '<span style="font-size:28px;color:blue;">简历</span>'
						}, {
							xtype : 'container',
							width : 150,
							style : 'float:right;',
							layout : 'column',
							items : [{
										xtype : 'label',
										width : 30,
										text : '状态:',
										style : 'padding-top:3px;'
									}, {
										width : 100,
										xtype : 'combo',
										name : 'resume.status',
										editable : false,
										mode : 'local',
										triggerAction : 'all',
										store : ['通过', '未通过', '准备安排面试', '通过面试'],
										value : '未通过'
									}]
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
												fieldLabel : '姓名',
												name : 'resume.fullname',
												allowBlank : false,
												blankText : '姓名不可为空!'
											}, {
												fieldLabel : '年龄',
												name : 'resume.age',
												xtype : 'numberfield'
											}, {
												fieldLabel : '生日',
												name : 'resume.birthday',
												xtype : 'datefield',
												format : 'Y-m-d'
											}, {
												fieldLabel : '籍贯',
												name : 'resume.birthPlace'
											}, {
												fieldLabel : '参加工作时间',
												name : 'resume.startWorkDate',
												xtype : 'datefield',
												format : 'Y-m-d'
											}, {
												fieldLabel : '身份证',
												name : 'resume.idNo'
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
												name : 'resume.religion',
												xtype : 'diccombo',
												nodeKey : 'religion'
											}, {
												fieldLabel : '政治面貌',
												name : 'resume.party',
												xtype : 'diccombo',
												nodeKey : 'political_status'
											}, {
												fieldLabel : '国籍',
												name : 'resume.nationality',
												xtype : 'diccombo',
												nodeKey : 'nationality'
											}, {
												fieldLabel : '民族',
												name : 'resume.race',
												xtype : 'diccombo',
												nodeKey : 'nation'
											}, {
												fieldLabel : '性别',
												name : 'resume.sex',
												xtype : 'combo',
												editable : false,
												mode : 'local',
												triggerAction : 'all',
												store : ['男', '女']
											}, {
												fieldLabel : '职位名称',
												name : 'resume.position'
											}]
								}, {
									name : 'resume.photo',
									xtype : 'hidden'
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
													id : 'ResumePhotoPanel',
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
													handler : this.uploadPhoto
												}]
											}]
								}]
					}, {
						xtype : 'fieldset',
						title : '联系方式',
						defaultType : 'textfield',
						layout : 'form',
						items : [{
									fieldLabel : '地址',
									name : 'resume.address',
									anchor : '100%'
								}, {
									layout : 'column',
									defaults : {
										anchor : '96%,96%'
									},
									style : 'padding-left:0px;padding-right:0px;',
									xtype : 'container',
									items : [{
												layout : 'form',
												xtype : 'container',
												style : 'padding-left:0px;',
												columnWidth : .5,
												defaults : {
													anchor : '96%,96%'
												},
												defaultType : 'textfield',
												items : [{
															fieldLabel : '邮编',
															name : 'resume.zip'
														}, {
															fieldLabel : '电子邮箱',
															name : 'resume.email',
															vtype : 'email'
														}]
											}, {
												xtype : 'container',
												columnWidth : .5,
												style : 'padding-right:0px;',
												defaultType : 'textfield',
												layout : 'form',
												items : [{
															fieldLabel : '电话号码',
															name : 'resume.phone',
															anchor : '100%'
														}, {
															fieldLabel : '手机号码',
															name : 'resume.mobile',
															anchor : '100%'
														}]
											}]
								}]
					}, {
						xtype : 'fieldset',
						title : '教育背景',
						defaultType : 'textfield',
						layout : 'form',
						items : [{
									fieldLabel : '毕业院校',
									name : 'resume.eduCollege',
									anchor : '70%'
								}, {
									fieldLabel : '学历',
									name : 'resume.eduDegree',
									xtype : 'diccombo',
									nodeKey : 'education',
									anchor : '70%'
								}, {
									fieldLabel : '专业',
									name : 'resume.eduMajor',
									xtype : 'diccombo',
									nodeKey : 'major',
									anchor : '70%'
								}]
					}, {
						xtype : 'fieldset',
						title : '爱好',
						layout : 'anchor',
						items : [{
									name : 'resume.hobby',
									xtype : 'textarea',
									anchor : '100%'
								}]
					}, {
						xtype : 'fieldset',
						title : '工作经历',
						layout : 'anchor',
						items : [{
									name : 'resume.workCase',
									xtype : 'textarea',
									anchor : '100%'
								}]
					}, {
						xtype : 'fieldset',
						title : '培训经历',
						layout : 'anchor',
						items : [{
									name : 'resume.trainCase',
									xtype : 'textarea',
									anchor : '100%'
								}]
					}, {
						xtype : 'fieldset',
						title : '项目经验',
						layout : 'anchor',
						items : [{
									name : 'resume.projectCase',
									xtype : 'textarea',
									anchor : '100%'
								}]
					}, {
						xtype : 'fieldset',
						title : '备注',
						layout : 'anchor',
						items : [{
									name : 'resume.memo',
									xtype : 'textarea',
									anchor : '100%'
								}]
					}, {
						xtype : 'fieldset',
						title : '附件',
						layout : 'column',
						items : [{
									columnWidth : .8,
									layout : 'form',
									items : [{
												id : 'resumeFilePanel',
												xtype : 'panel',
												height : 50,
												border : false,
												autoScroll : true,
												html : ''
											}]
								}, {
									columnWidth : .2,
									border : false,
									items : [{
												xtype : 'button',
												text : '添加附件',
												iconCls : 'menu-attachment',
												scope : this,
												handler : this.attachmentAddClick
											}, {
												xtype : 'button',
												text : '清除附件',
												iconCls : 'reset',
												scope : this,
												handler : this.attachmentClsClick
											}, {
												xtype : 'hidden',
												name : 'fileIds'
											}]
								}]
					}]
		});

		this.toolbar = new Ext.Toolbar({
					items : [{
								text : '保存',
								iconCls : 'btn-save',
								scope : this,
								handler : this.save
							}, '-', {
								text : '取消',
								iconCls : 'btn-cancel',
								scope : this,
								handler : this.cancel
							}]
				});

		if (!Ext.isEmpty(this.resumeId)) {
			this.formPanel.loadData({
				url : __ctxPath + '/hrm/getResume.do?resumeId=' + this.resumeId,
				waitMsg : '正在载入数据...',
				root : 'data',
				preName : 'resume',
				scope : this,
				success : function(response, options) {
					var res = Ext.util.JSON.decode(response.responseText).data;
					var photo = res.photo;
					var af = res.resumeFiles;
					var filePanel = Ext.getCmp('resumeFilePanel');
					var fileIds = this.formPanel.getForm().findField('fileIds');
					for (var i = 0; i < af.length; i++) {
						if (fileIds.getValue() != '') {
							fileIds.setValue(fileIds.getValue() + ',');
						}
						fileIds.setValue(fileIds.getValue() + af[i].fileId);
						Ext.DomHelper
								.append(
										filePanel.body,
										'<span><a href="#" onclick="FileAttachDetail.show('
												+ af[i].fileId
												+ ')">'
												+ af[i].fileName
												+ '</a><img class="img-delete" src="'
												+ __ctxPath
												+ '/images/system/delete.gif" onclick="ResumeForm.removeResumeFile(this,'
												+ af[i].fileId
												+ ')"/>&nbsp;|&nbsp;</span>');
					}
					if (photo != null && photo != '') {
						Ext.getCmp('ResumePhotoPanel').body.update('<img src="'
								+ __ctxPath + '/attachFiles/' + photo
								+ '" width="88" height="120"/>');
					}
					// Ext.Msg.alert('编辑', '载入成功！');
				},
				failure : function(response, options) {
					// Ext.Msg.alert('编辑', '载入失败');
				}
			});
		}
	},// end init
	/**
	 * 上传图片处理函数
	 */
	uploadPhoto : function() {
		var photo = this.formPanel.getForm().findField('resume.photo'); // Ext.getCmp('ResumeForm.photo');
		var dialog = App.createUploadDialog({
					file_cat : 'hrm/Resume',
					callback : function uploadResumePhoto(data) {
						var display = Ext.getCmp('ResumePhotoPanel');
						photo.setValue(data[0].filePath);
						display.body.update('<img src="' + __ctxPath
								+ '/attachFiles/' + data[0].filePath
								+ '"  width="88" height="120"/>');
					},
					permitted_extensions : ['jpg', 'png']
				});
		if (photo.getValue() != '' && photo.getValue() != null
				&& photo.getValue() != 'undefined') {
			var msg = '再次上传需要先删除原有图片,';
			Ext.Msg.confirm('信息确认', msg + '是否删除？', function(btn) {
				if (btn == 'yes') {
					// 删除图片
					var resumeId = this.formPanel.getForm()
							.findField('resume.resumeId').getValue();
					Ext.Ajax.request({
						url : __ctxPath + '/hrm/delphotoResume.do',
						method : 'post',
						params : {
							resumeId : resumeId
						},
						scope : this,
						success : function() {
							var path = photo.value;
							this.formPanel.getForm().findField('resume.photo')
									.setValue('');
							Ext.getCmp('ResumePhotoPanel').body
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
			}, this);
		} else {
			dialog.show('queryBtn');
		}
	},

	/**
	 * 添加附件
	 */
	attachmentAddClick : function() {
		var filePanel = Ext.getCmp('resumeFilePanel');
		var fileIds = this.formPanel.getForm().findField('fileIds');

		var dialog = App.createUploadDialog({
			file_cat : 'hrm/resume',
			callback : function(data) {
				for (var i = 0; i < data.length; i++) {
					if (fileIds.getValue() != '') {
						fileIds.setValue(fileIds.getValue() + ',');
					}
					fileIds.setValue(fileIds.getValue() + data[i].fileId);
					Ext.DomHelper
							.append(
									filePanel.body,
									'<span><a href="#" onclick="FileAttachDetail.show('
											+ data[i].fileId
											+ ')">'
											+ data[i].fileName
											+ '</a> <img class="img-delete" src="'
											+ __ctxPath
											+ '/images/system/delete.gif" onclick="ResumeForm.removeResumeFile(this,'
											+ data[i].fileId
											+ ')"/>&nbsp;|&nbsp;</span>');
				}
			}
		});
		dialog.show(this);
	},
	/**
	 * 删除附件
	 * 
	 * @param {}
	 *            obj
	 * @param {}
	 *            fileId
	 */
	removeResumeFile : function(obj, fileId) {
		// var fileIds = Ext.getCmp("resumefileIds");
		var fileIds = Ext.getCmp("ResumeForm").formPanel.getForm()
				.findField('fileIds');
		var value = fileIds.getValue();
		if (value.indexOf(',') < 0) {// 仅有一个附件
			fileIds.setValue('');
		} else {
			value = value.replace(',' + fileId, '').replace(fileId + ',', '');
			fileIds.setValue(value);
		}
		var el = Ext.get(obj.parentNode);
		el.remove();
	},
	/**
	 * 清除附件
	 */
	attachmentClsClick : function() {
		var filePanel = Ext.getCmp('resumeFilePanel');
		var fileAttaches = this.formPanel.getForm().findField('fileIds');
		filePanel.body.update('');
		fileAttaches.setValue('');
	},
	/**
	 * 保存记录
	 */
	save : function() {
		$postForm({
					formPanel : this.formPanel,
					scope : this,
					url : __ctxPath + '/hrm/saveResume.do',
					callback : function(fp, action) {
						if (this.callback) {
							this.callback.call(this.scope);
						}
						Ext.getCmp('ResumeView').gridPanel.getStore().reload();
						AppUtil.removeTab('ResumeForm');
					}
				});
	},
	/**
	 * 关闭
	 */
	cancel : function() {
		AppUtil.removeTab('ResumeForm');
	}
});
