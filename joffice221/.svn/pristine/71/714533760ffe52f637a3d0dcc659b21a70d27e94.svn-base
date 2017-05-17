/**
 * @class ConferenceForm
 * @extends Ext.Window
 * @description 会议信息管理
 * @company 宏天软件
 */
ConferenceForm = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		// 调用父类构造
		ConferenceForm.superclass.constructor.call(this, {
					id : 'ConferenceFormWin',
					layout : 'fit',
					iconCls : 'menu-conference',
					items : this.formPanel,
					modal : true,
					maximizable : true,
					minWidth : 800,
					width : 730,
					height : 600,
					autoScroll : false,
					title : '编辑会议内容',
					buttonAlign : 'center',
					keys : {
						key : Ext.EventObject.ENTER,
						fn : this.send,
						scope : this
					}
				});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {

		// 会议信息设置
		this.basePanel = new Ext.form.FieldSet({
			title : '会议信息',
			layout : 'form',
			border : true,
			items : [{
				layout : 'column',
				columnWidth : 1,
				border : false,
				defaults : {
					border : false
				},
				items : [{
					layout : 'form',
					columnWidth : .5,
					defaults : {
						width : '100%'
					},
					items : [{
								xtype : 'hidden',
								name : 'conference.confId',
								value : this.confId != null ? this.confId : ''
							}, {
								anchor : '99%',
								xtype : 'textfield',
								name : 'conference.confTopic',
								fieldLabel : '会议标题',
								allowBlank : false,
								maxLength : 128
							}, {
								xtype : 'hidden',
								name : 'conference.typeId'
							}, {
								anchor : '99%',
								xtype : 'combo',
								name : 'conference.confProperty',
								fieldLabel : '会议类型',
								valueField : 'typeId',
								displayField : 'typeName',
								mode : 'local',
								editable : false,
								triggerAction : 'all',
								forceSelection : true,
								allowBlank : false,
								store : new Ext.data.SimpleStore({
											url : __ctxPath
													+ '/admin/getTypeAllConference.do',
											autoLoad : true,
											fields : ['typeId', 'typeName']
										}),
								listeners : {
									scope : this,
									select : this.confPropertySelect
								}
							}, {
								anchor : '99%',
								name : 'conference.feeBudget',
								fieldLabel : '经费(元)',
								text : '0',
								xtype : 'numberfield',
								maxLength : 21
							}, {
								anchor : '99%',
								fieldLabel : '留言方式',
								xtype : 'container',
								layout : 'column',
								border : false,
								defaults : {
									border : false,
									xtype : 'checkbox'
								},
								items : [{
											columnWidth : .5,
											boxLabel : '普通留言',
											name : 'conference.isEmail',
											inputValue : 1,
											checked : true
										}, {
											columnWidth : .5,
											boxLabel : '手机留言',
											name : 'conference.isMobile',
											inputValue : 1
										}]
							}]
				}, {
					layout : 'form',
					columnWidth : .5,
					defaults : {
						width : '100%'
					},
					items : [{
								xtype : 'hidden',
								name : 'conference.roomId'
							}, {
								anchor : '99%',
								xtype : 'combo',
								name : 'conference.roomName',
								fieldLabel : '会议室名称',
								valueField : 'roomId',
								displayField : 'roomName',
								mode : 'local',
								editable : false,
								triggerAction : 'all',
								forceSelection : true,
								allowBlank : false,
								store : new Ext.data.SimpleStore({
									url : __ctxPath
											+ '/admin/getBoardrooConference.do',
									autoLoad : true,
									fields : ['roomId', 'roomName']
								}),
								listeners : {
									scope : this,
									select : this.roomSelect
								}
							}, {
								anchor : '99%',
								xtype : 'textfield',
								readOnly : true,
								fieldLabel : '会议室',
								name : 'roomName'
							}, {
								anchor : '99%',
								xtype : 'textfield',
								fieldLabel : '地址',
								name : 'conference.roomLocation',
								allowBlank : false,
								maxLength : 128
							}, {
								anchor : '99%',
								xtype : 'radiogroup',
								fieldLabel : '重要级别',
								border : false,
								items : [{
											boxLabel : '普通',
											name : 'conference.importLevel',
											inputValue : 0,
											checked : true
										}, {
											boxLabel : '重要',
											name : 'conference.importLevel',
											inputValue : 1
										}]
							}]
				}]
			}]
		}); // end of this basePanel

		// 会议参加人员jonerPanel
		this.joinerPanel = new Ext.form.FieldSet({
					title : '参加人员',
					layout : 'form',
					border : true,
					items : [{
								anchor : '100%',
								fieldLabel : '主持人',
								xtype : 'container',
								layout : 'column',
								border : false,
								items : [{
											xtype : 'hidden',
											name : 'conference.compere'
										}, {
											columnWidth : 1,
											anchor : '90%',
											xtype : 'textfield',
											name : 'conference.compereName',
											readOnly : true,
											allowBlank : false,
											maxLength : 256
										}, {
											xtype : 'button',
											text : '请选择',
											iconCls : 'btn-user-sel',
											scope : this,
											handler : this.compereSelect
										}]
							}, {
								anchor : '100%',
								fieldLabel : '记录人',
								xtype : 'container',
								layout : 'column',
								border : false,
								items : [{
											xtype : 'hidden',
											name : 'conference.recorder'
										}, {
											columnWidth : 1,
											anchor : '90%',
											xtype : 'textfield',
											name : 'conference.recorderName',
											readOnly : true,
											allowBlank : false,
											maxLength : 256
										}, {
											xtype : 'button',
											text : '请选择',
											iconCls : 'btn-user-sel',
											scope : this,
											handler : this.recorderSelect
										}]
							}, {
								anchor : '100%',
								fieldLabel : '参加人',
								xtype : 'container',
								layout : 'column',
								border : false,
								items : [{
											xtype : 'hidden',
											name : 'conference.attendUsers'
										}, {
											columnWidth : 1,
											anchor : '90%',
											xtype : 'textfield',
											name : 'conference.attendUsersName',
											readOnly : true,
											allowBlank : false,
											maxLength : 4000
										}, {
											xtype : 'button',
											text : '请选择',
											iconCls : 'btn-user-sel',
											scope : this,
											handler : this.attendUserSelect
										}]
							}]
				}); // end of this joinerPanel

		// 权限grantPanel
		this.grantPanel = new Ext.form.FieldSet({
					title : '权限设置',
					region : 'center',
					layout : 'form',
					border : true,
					items : [{
								anchor : '100%',
								fieldLabel : '修改人',
								xtype : 'container',
								layout : 'column',
								border : false,
								items : [{
											xtype : 'hidden',
											name : 'updater'
										}, {
											columnWidth : 1,
											anchor : '90%',
											xtype : 'textfield',
											name : 'updaters',
											readOnly : true,
											allowBlank : false,
											maxLength : 256
										}, {
											xtype : 'button',
											text : '请选择',
											iconCls : 'btn-user-sel',
											scope : this,
											handler : this.updaterSelect
										}]
							}, {
								anchor : '100%',
								fieldLabel : '审核人',
								xtype : 'container',
								layout : 'column',
								border : false,
								items : [{
											xtype : 'hidden',
											name : 'conference.checkUserId'
										}, {
											columnWidth : 1,
											anchor : '90%',
											xtype : 'textfield',
											name : 'conference.checkName',
											readOnly : true,
											allowBlank : false,
											maxLength : 64
										}, {
											xtype : 'button',
											text : '请选择',
											iconCls : 'btn-user-sel',
											scope : this,
											handler : this.checkUserSelect
										}]
							}, {
								layout : 'column',
								border : false,
								height : 26
							}]
				}); // end of this grantPanel

		// 会议内容contextPanel
		this.contextPanel = new Ext.form.FieldSet({
					title : '时间和内容设置',
					layout : 'column',
					columnWidth : 1,
					border : true,
					items : [{
								columnWidth : .5,
								anchor : '100%',
								layout : 'form',
								border : false,
								items : [{
											anchor : '99%',
											xtype : 'datetimefield',
											format : 'Y-m-d H:i:s',
											editable : false,
											name : 'conference.startTime',
											fieldLabel : '开始时间',
											allowBlank : false
										}]
							}, {
								columnWidth : .5,
								anchor : '100%',
								layout : 'form',
								border : false,
								items : [{
											anchor : '99%',
											xtype : 'datetimefield',
											name : 'conference.endTime',
											format : 'Y-m-d H:i:s',
											editable : false,
											fieldLabel : '结束时间',
											allowBlank : false
										}]
							}, {
								columnWidth : 1,
								layout : 'form',
								height : 100,
								anchor : '100%',
								border : false,
								items : [{
											anchor : '100%',
											name : 'conference.confContent',
											xtype : 'htmleditor',
											height : 100,
											fieldLabel : '会议内容',
											allowBlank : false,
											maxLength : 4000
										}]
							}]
				}); // end of this contextPanel

		// 附件
		this.filePanel = new Ext.form.FieldSet({
			        id : 'ConferenceForm.filePath',
					layout : 'form',
					region : 'center',
					title : '附件信息',
					items : [{
								fieldLabel : '附件信息',
								xtype : 'container',
								columnWidth : 1,
								layout : 'column',
								border : false,
								items : [{
											xtype : 'hidden',
											name : 'filePath'
										}, {
											columnWidth : 1,
											anchor : '99%',
											xtype : 'panel',
											name : 'resumeFilePanel',
											height : 50,
											border : true,
											autoScroll : true,
											html : ''
										}, {
											width : 75,
											layout : 'form',
											defaultType : 'button',
											border : false,
											items : [{
														iconCls : 'menu-attachment',
														text : '上传附件',
														scope : this,
														handler : this.upLoadFile
													}, {
														text : '清除附件',
														iconCls : 'reset',
														scope : this,
														handler : this.delLoadFile
													}]
										}]
							}]
				}); // end of this filePath

		// 参加人员和权限设置面板
		this.joinAddGrantPanel = new Ext.form.FieldSet({
					layout : 'column',
					border : false,
					columnWidth : 1,
					defaults : {
						border : false
					},
					items : [{
								columnWidth : .5,
								anchor : '100%',
								layout : 'form',
								items : [this.joinerPanel]
							}, {
								anchor : '100%',
								columnWidth : .5,
								layout : 'form',
								items : [this.grantPanel]
							}]
				})

		this.formPanel = new Ext.FormPanel({
					autoScroll : false,
					layout : 'form',
					region : 'center',
					border : false,
					autoScroll:true,
					bodyStyle : 'padding:10px 10px 10px 10px;',
					buttonAlign : "center", 
					defaults : {
						readOnly : true
					},
					items : [this.basePanel, this.contextPanel,
							this.joinAddGrantPanel, this.filePanel],
					buttons : [{
								text : '暂存会议信息',
								iconCls : 'temp',
								scope : this,
								handler : this.temp
							}, {
								text : '发送会议通知',
								iconCls : 'btn-mail_send',
								scope : this,
								handler : this.send
							}, {
								text : '取消',
								iconCls : 'btn-cancel',
								scope : this,
								handler : this.cancel
							}]
				});// end of this formPanel

		// 数据加载
		if (this.confId != null && this.confId != ''
				&& this.confId != 'undenfied') {
			var me = this;
			this.formPanel.loadData({
						url : __ctxPath + '/admin/getConference.do?confId='
								+ this.confId,
						root : 'data',
						preName : 'conference',
						success : function(response, options) {
							var action = Ext.util.JSON
									.decode(response.responseText);
							me.setGrantPanel(action);
							me.setFilePanel(action.data.attachFiles);
						},
						failure : function() {
							Ext.ux.Toast.msg('操作提示', '数据加载失败！');
						}
					});
		}// end of this 数据加载
	}, // end of this initUIComponent
	/**
	 * 取消
	 */
	cancel : function() {
		this.close();
	},
	/**
	 * 发送
	 */
	send : function(){
		this.save(1);
	},
	/**
	 * 暂存
	 */
	temp : function(){
		this.save(0);
	},

	/**
	 * 保存
	 */
	save : function(status) {
		var formPanel = this.formPanel;
		var url = status ==0 ? __ctxPath + '/admin/tempConference.do':__ctxPath + '/admin/sendConference.do';
		var me = this;
		if (formPanel.getForm().isValid()) {
			// 开会时间验证
			var dateTimeNow = new Date().format('Y-m-d H:i:s');
			var startTime = formPanel.getCmpByName('conference.startTime');
			var endTime = formPanel.getCmpByName('conference.endTime');
			if (dateTimeNow > startTime.value) {
				Ext.ux.Toast.msg('操作提示', '对不起，开会时间必须大于当前时间，请重新输入！');
				startTime.focus(true);
				return;
			}
			if (startTime.value >= endTime.value) {
				Ext.ux.Toast.msg('操作提示', '开始时间大于结束时间，请重新输入！');
				startTime.focus(true);
				return;
			}
			// 会议主持人和记录人重复人员验证
			if (!ConferenceForm.prototype.validateCompereAndRecorder(this))
				return;
			formPanel.getForm().submit({
						url : url,
						method : 'post',
						waitMsg : '数据正在保存，请稍候...',
						success : function(fp, action) {
							status ==0 ?Ext.ux.Toast.msg('操作信息', '成功暂存信息！') : Ext.ux.Toast.msg('操作信息', '成功发送会议申请信息,等待审批！');
							Ext.getCmp('TemporaryConferenceGrid').getStore().reload();
							me.close();
						},
						failure : function(fp, action) {
							Ext.MessageBox.show({
										title : '操作信息',
										msg : '对不起，会议申请失败！',
										buttons : Ext.MessageBox.OK,
										icon : 'ext-mb-error'
									});
						}
					});
		}
	},
	/**
	 * 文件上传
	 */
	upLoadFile : function() {
		var filePanel = this.filePanel;
		var dialog = App.createUploadDialog({
			file_cat : 'admin/conference',
			callback : function(arr) {
				var fileIds = '';
				fileIds = filePanel.getCmpByName('filePath').getValue() != '' ? filePanel
						.getCmpByName('filePath').getValue()
						+ ',' : '';
				var resumeFilePanel = filePanel.getCmpByName('resumeFilePanel');
				for (var i = 0; i < arr.length; i++) {
					fileIds += arr[i].fileId + ',';
					Ext.DomHelper.append(
									resumeFilePanel.body,
									'<span><a href="#" onclick="FileAttachDetail.show('
											+ arr[i].fileId
											+ ')">'
											+ arr[i].fileName
											+ '</a><img class="img-delete" src="'
											+ __ctxPath
											+ '/images/system/delete.gif" onclick="ConferenceForm.prototype.removeResumeFile(this,'
											+ arr[i].fileId
											+ ')"/>&nbsp;|&nbsp;</span>');
				}
				filePanel.getCmpByName('filePath').setValue(fileIds.substring(0,fileIds.length - 1));
			}
		});
		dialog.show('querybtn');
	},

	/**
	 * 删除上传文件
	 */
	delLoadFile : function() {
		var filePanel = this.filePanel;
		var fileIds = filePanel.getCmpByName('filePath').value;
		if (fileIds != null && fileIds != '' && fileIds != 'undefined') {
			Ext.Msg.confirm('确认信息', '您真的要删除上传文件吗？', function(btn) {
						if (btn == 'yes') {
							filePanel.getCmpByName('filePath').setValue('');
							filePanel.getCmpByName('resumeFilePanel').update();
						}
					});
		} else {
			Ext.ux.Toast.msg('操作提示', '对不起，你还没有上传文件！');
		}
	},
	/**
	 * 显示上传文件列表
	 */
	setFilePanel : function(records) {
		var fileIds = '';
		var url = '';
		var resumeFilePanel = this.filePanel.getCmpByName('resumeFilePanel');
		for (var i = 0; i < records.length; i++) {
			fileIds += records[i].fileId + ',';
			var del = '</a><img class="img-delete" src="'
					+ __ctxPath
					+ '/images/system/delete.gif" onclick="ConferenceForm.prototype.removeResumeFile(this,'
					+ records[i].fileId + ')"/>';
			url += '<span><a href="#" onclick="FileAttachDetail.show('
					+ records[i].fileId + ')">' + records[i].fileName + del
					+ '&nbsp;|&nbsp;</span>';
		}
		Ext.DomHelper.append(resumeFilePanel.body, url);
		this.filePanel.getCmpByName('filePath').setValue(fileIds.substring(0,
				fileIds.length - 1));
	},
	/**
	 * 权限设置面板绑定数据
	 */
	setGrantPanel : function(action) {
		var grantPanel = this.grantPanel;
		var cp = action.data.confPrivilege;
		var updater = '';
		var updaters = '';
		for (var i = 0; i < cp.length; i++) {
			if (cp[i].rights == 2) {// 修改
				updater += cp[i].userId + ',';
				updaters += cp[i].fullname + ',';
			}
		}
		grantPanel.getCmpByName('updater').setValue(updater.substring(0, updater.length- 1));
		grantPanel.getCmpByName('updaters').setValue(updaters.substring(0,updaters.length - 1));
	},
	/**
	 * 判断会议主持人和记录人是否存在重复,重复false
	 */
	validateCompereAndRecorder : function(obj) {
		var joinerPanel = obj.joinerPanel;
		var compere = joinerPanel.getCmpByName('conference.compere').value.split(',');
		var recorder = joinerPanel.getCmpByName('conference.recorder').value.split(',');
		var bo = true;
		if (compere.length == 1 && recorder.length == 1) { // 一对一
			if (compere[0].search(recorder) >= 0)
				bo = false;
		} else if (compere.length == 1 && recorder.length != 1) {// 一对多
			for (var i = 0; i < recorder.length; i++) {
				if (recorder[i].search(compere) >= 0)
					bo = false;
			}
		} else if (compere.length != 1 && recorder.length == 1) { // 多对一
			for (var i = 0; i < compere.length; i++) {
				if (compere[i].search(recorder) >= 0)
					bo = false;
			}
		}
		if (bo == false) {
			joinerPanel.getCmpByName('conference.compereName').focus(true);
			Ext.ux.Toast.msg('操作提示', '对不起，会议主持人和记录人不能重复出现，请重新选择！');
		}
		return bo;
	},
	/**
	 * 上传文件删除
	 */
	removeResumeFile : function(obj, fileId) {
		//var fileIds = Ext.getCmp("conferenceFormFilePath");
		var fileIds = Ext.getCmp("ConferenceForm.filePath").getCmpByName('filePath');
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
	 * 会议类型选择
	 * @param {} cbo
	 * @param {} record
	 * @param {} index
	 */
	confPropertySelect : function(cbo, record, index) {
		this.basePanel.getCmpByName('conference.typeId').setValue(cbo.getValue());
	},
	/**
	 * 会议室选择
	 * @param {} cbo
	 * @param {} record
	 * @param {} index
	 */
	roomSelect : function(cbo, record, index) {
		var basePanel = this.basePanel;
		basePanel.getCmpByName('conference.roomId').setValue(cbo.getValue());
		basePanel.getCmpByName('roomName').setValue(cbo.getRawValue());
		basePanel.getCmpByName('conference.roomLocation').setValue(cbo.getRawValue());
	},
	/**
	 * 主持人选择
	 */
	compereSelect : function() {
		var joinerPanel = this.joinerPanel;
		var editUserId = joinerPanel.getCmpByName('conference.compere').getValue();
		var editFullName = joinerPanel.getCmpByName('conference.compereName').getValue();
		new UserDialog({
			scope:this,
			single:false,
			userIds : editUserId,
			userName : editFullName,
			callback : function(userId, fullName) {
				joinerPanel.getCmpByName('conference.compere').setValue(userId);
				joinerPanel.getCmpByName('conference.compereName').setValue(fullName);
			}
		}).show();
	},
	/**
	 * 记录人选择
	 */
	recorderSelect : function() {
		var joinerPanel = this.joinerPanel;
		var editUserId = joinerPanel.getCmpByName('conference.recorder').getValue();
		var editFullName = joinerPanel.getCmpByName('conference.recorderName').getValue();
		new UserDialog({
			scope:this,
			single:false,	
			userIds : editUserId,
			userName : editFullName,
			callback : function(userId, fullName) {
				joinerPanel.getCmpByName('conference.recorder').setValue(userId);
				joinerPanel.getCmpByName('conference.recorderName').setValue(fullName);
			}
		}).show();
	},
	/**
	 * 参加人选择
	 */
	attendUserSelect : function() {
		var joinerPanel = this.joinerPanel;
		var editUserId = joinerPanel.getCmpByName('conference.attendUsers').getValue();
		var editFullName = joinerPanel.getCmpByName('conference.attendUsersName').getValue();
		new UserDialog({
			scope:this,
			single:false,	
			userIds : editUserId,
			userName : editFullName,
			callback : function(userId, fullName) {
				joinerPanel.getCmpByName('conference.attendUsers').setValue(userId);
				joinerPanel.getCmpByName('conference.attendUsersName').setValue(fullName);
			}
		}).show();
	},
	/**
	 * 修改人选择
	 */
	updaterSelect : function() {
		var grantPanel = this.grantPanel;
		var editUserId = grantPanel.getCmpByName('updater').getValue();
		var editFullName = grantPanel.getCmpByName('updaters').getValue();
		new UserDialog({
			scope:this,
			single:false,	
			userIds : editUserId,
			userName : editFullName,
			callback : function(userId, fullName) {
				grantPanel.getCmpByName('updater').setValue(userId);
				grantPanel.getCmpByName('updaters').setValue(fullName);
			}
		}).show();
	},
	/**
	 * 审核人选择
	 */
	checkUserSelect : function() {
		var grantPanel = this.grantPanel;
		new UserDialog(function(userId, fullName) {
					grantPanel.getCmpByName('conference.checkUserId').setValue(userId);
					grantPanel.getCmpByName('conference.checkName').setValue(fullName);
				}, true).show();
	}

});
