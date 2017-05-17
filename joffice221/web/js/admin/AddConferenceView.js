/**
 * @description 会议申请
 * @company www.htjee-soft.cn
 * @dateTime 2012-9-3
 */
AddConferenceView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		// 调用父类构造
		AddConferenceView.superclass.constructor.call(this, {
					id : 'AddConferenceViewWin',
					layout : 'form',
					region : 'center',
					title : '会议申请',
					iconCls : 'menu-conference_add',
					tbar : this.topBar,
					modal : true,
					maximizable : true,
					bodyStyle : 'padding : 5px 5px 5px 5px',
					autoScroll : false,
					items : this.formPanel,
					keys : {
						key : Ext.EventObject.ENTER,
						fn : this.temp,
						scope : this
					}
				});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		//顶端初始化工具栏
		this.topBar = new Ext.Toolbar({
					heigth : 30,
					defaultType : 'button',
					items : [{
								text : '发送会议通知',
								iconCls : 'btn-mail_send',
								scope : this,
								handler : this.send
							}, '-', {
								text : '暂存会议',
								iconCls : 'temp',
								scope : this,
								handler : this.temp
							}, '-', {
								text : '清空',
								iconCls : 'reset',
								scope : this,
								handler : this.reset
							}, '-', {
								text : '取消',
								iconCls : 'btn-cancel',
								scope : this,
								handler : this.close
							}]
				}),
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
						width : '80%'
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
								emptyText : '--请选择会议类型--',
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
								defaults : {
									xtype : 'checkbox',
									margins : '0 20 0 10'
								},
								items : [{
											columnWidth : .5,
											anchor : '99%',
											boxLabel : '普通留言',
											name : 'conference.isEmail',
											inputValue : 1,
											checked : true
										}, {
											columnWidth : .5,
											anchor : '99%',
											boxLabel : '手机留言',
											name : 'conference.isMobile',
											inputValue : 1,
											width : 100
										}]
							}]
				}, {
					anchor : '99%',
					layout : 'form',
					columnWidth : .5,
					items : [{
						anchor : '99%',
						xtype : 'combo',
						hiddenName : 'conference.roomId',
						fieldLabel : '会议室名称',
						valueField : 'roomId',
						displayField : 'roomName',
						mode : 'local',
						editable : false,
						emptyText : '--请选择会议室--',
						triggerAction : 'all',
						forceSelection : true,
						allowBlank : false,
						store : new Ext.data.SimpleStore({
							url : __ctxPath + '/admin/getBoardrooConference.do',
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
						fieldLabel : '会议室',
						name : 'conference.roomName'
					}, {
						anchor : '99%',
						xtype : 'textfield',
						fieldLabel : '地址',
						name : 'conference.roomLocation',
						allowBlank : false,
						maxLength : '128'
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

		// 会议参加人员joinerPanel
		this.joinerPanel = new Ext.form.FieldSet({
			title : '参加人员',
			layout : 'form',
			autoScroll : true,
			border : true,
			items : [{
				anchor : '99%',
				fieldLabel : '主持人',
				xtype : 'container',
				layout : 'column',
				border : false,
				defaults : {
					border : false
				},
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
							width : 75,
							xtype : 'button',
							text : '请选择',
							iconCls : 'btn-user-sel',
							scope : this,
							handler : this.compereSelect
						}]
			}, {
				anchor : '99%',
				fieldLabel : '记录人',
				xtype : 'container',
				layout : 'column',
				border : false,
				defaults : {
					border : false
				},
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
							width : 75,
							xtype : 'button',
							text : '请选择',
							iconCls : 'btn-user-sel',
							scope : this,
							handler : this.recorderSelect
						}]
			}, {
				anchor : '99%',
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
							width : 75,
							xtype : 'button',
							text : '请选择',
							iconCls : 'btn-user-sel',
							scope : this,
							handler : this.attendUserSelect
						}]
			}]
		}); // end of this joinerPanel

		// 权限设置grantPanel
		this.grantPanel = new Ext.form.FieldSet({
			title : '权限设置',
			region : 'center',
			layout : 'form',
			border : true,
			items : [{
				anchor : '99%',
				xtype : 'container',
				fieldLabel : '修改人',
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
							width : 75,
							xtype : 'button',
							text : '请选择',
							iconCls : 'btn-user-sel',
							scope : this,
							handler : this.updaterSelect
						}]
			}, {
				anchor : '99%',
				fieldLabel : '审核人',
				xtype : 'container',
				layout : 'column',
				defaults : {
					border : false
				},
				border : false,
				items : [{
							xtype : 'hidden',
							name : 'conference.checkUserId'
						}, {
							columnWidth : 1,
							width : '90%',
							xtype : 'textfield',
							name : 'conference.checkName',
							readOnly : true,
							allowBlank : false,
							maxLength : 64
						}, {
							width : 75,
							xtype : 'button',
							text : '请选择',
							iconCls : 'btn-user-sel',
							scope : this,
							handler : this.checkUserSelect
						}]
			}, {
				layout : 'column',
				height : 26,
				border : false
			}]
		}); // end of this grantPanel

		// 时间和内容设置面板contextPanel
		this.contextPanel = new Ext.form.FieldSet({
					title : '时间和内容设置',
					layout : 'column',
					columnWidth : 1,
					border : true,
					items : [{
								columnWidth : .5,
								anchor : '99%',
								layout : 'form',
								border : false,
								items : [{
											anchor : '99%',
											xtype : 'datetimefield',
											format : 'Y-m-d H:i:s',
											editable : false,
											name : 'conference.startTime',
											minValue : new Date(),
											fieldLabel : '开始时间',
											allowBlank : false
										}]
							}, {
								columnWidth : .5,
								anchor : '99%',
								layout : 'form',
								border : false,
								items : [{
											anchor : '99%',
											name : 'conference.endTime',
											xtype : 'datetimefield',
											format : 'Y-m-d H:i:s',
											minValue : new Date(),
											editable : false,
											fieldLabel : '结束时间',
											allowBlank : false
										}]
							}, {
								columnWidth : 1,
								anchor : '100%',
								layout : 'form',
								height : 160,
								border : false,
								items : [{
											anchor : '98%',
											name : 'conference.confContent',
											xtype : 'htmleditor',
											height : 150,
											fieldLabel : '会议内容',
											autoScroll : true,
											allowBlank : false,
											maxLength : 4000
										}]
							}]
				}); // end of this contextPanel

		// 附件信息面板
		this.filePanel = new Ext.form.FieldSet({
					layout : 'form',
					id : 'addConferenceView.filePath',
					region : 'center',
					title : '附件信息',
					bodyStyle : 'padding : 5px 5px 5px 5px',
					items : [{
								xtype : 'hidden',
								name : 'filePath'
							}, {
								fieldLabel : '附件信息',
								xtype : 'container',
								columnWidth : 1,
								layout : 'column',
								border : false,
								items : [{
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
														scope :this,
														handler : this.delLoadFile
													}]
										}]
							}]
				}); // end of this filePanel

		//参加人员和权限设置面板		
		this.joinAddGrantPanel = new Ext.Panel({
					layout : 'column',
					border : false,
					columnWidth : 1,
					defaults : {
						border : false
					},
					items : [{
								columnWidth : .5,
								layout : 'form',
								items : [this.joinerPanel]
							}, {
								columnWidth : .5,
								layout : 'form',
								items : [this.grantPanel]
							}]
				});
		
		//整体面板
		this.mainPanel = new Ext.form.FieldSet({
			title : '会议申请',
			bodyStyle : 'padding:5px 5px 5px 5px',
			layout : 'form',
			buttonAlign : 'center',
			items : [this.basePanel, this.contextPanel,
					this.joinAddGrantPanel, this.filePanel],
			buttons : [{
						text : '发送会议通知',
						iconCls : 'btn-mail_send',
						scope : this,
						handler : this.send
					}, {
						text : '暂存会议',
						iconCls : 'temp',
						scope : this,
						handler : this.temp
					}, {
						text : '清空',
						iconCls : 'reset',
						scope : this,
						handler : this.reset
					}]
		});

		//表单面板		
		this.formPanel = new Ext.FormPanel({
					autoScroll : true,
					layout : 'form',
					region : 'center',
					bodyStyle : 'padding : 10px 10px 10px 10px;',
					border : false,
					defaults : {
						readOnly : true
					},
					items : [this.mainPanel]
				});// end of this formPanel
	}, // end of this initUIComponent
	/**
	 * 重置
	 */
	reset : function() {
		this.formPanel.getForm().reset();
		this.filePanel.getCmpByName('resumeFilePanel').body.update('');
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
		var filePanel = this.filePanel;
		var url = status ==0 ? __ctxPath + '/admin/tempConference.do':__ctxPath + '/admin/sendConference.do';
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
			if (!AddConferenceView.prototype.validateCompereAndRecorder(this))
				return;
			formPanel.getForm().submit({
						url : url,
						method : 'post',
						waitMsg : '数据正在保存，请稍候...',
						success : function(fp, action) {
							status ==0 ?Ext.ux.Toast.msg('操作信息', '成功暂存信息！') : Ext.ux.Toast.msg('操作信息', '成功发送会议申请信息,等待审批！');
							formPanel.getForm().reset();
							filePanel.getCmpByName('resumeFilePanel').body.update('');
							var addConferenceViewWin = Ext.getCmp("AddConferenceViewWin");
							addConferenceViewWin.hide();
							status ==0 ?App.clickTopTab('TemporaryConferenceView') : App.clickTopTab('WaitCheckConfView');
						},
						failure : function(fp, action) {
							Ext.MessageBox.show({
										title : '操作信息',
										msg : '对不起，会议室暂时被征用，请再重新选择会议室！',
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
			scope : this,
			file_cat : 'admin/conference',
			callback : function(arr) {
				var fileIds = '';
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
											+ '/images/system/delete.gif" onclick="AddConferenceView.prototype.removeResumeFile(this,'
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
	 * 关闭
	 */
	close : function() {
		Ext.getCmp('centerTabPanel').remove('AddConferenceViewWin');
	},
	/**
	 * 会议类型选择
	 * @param {} cbo
	 * @param {} record
	 * @param {} index
	 */
	confPropertySelect : function(cbo, record, index) {
		var basePanel = this.basePanel;
		basePanel.getCmpByName('conference.typeId')
				.setValue(cbo.value);
	},
	/**
	 * 会议室选择
	 * @param {} cbo
	 * @param {} record
	 * @param {} index
	 */
	roomSelect : function(cbo, record, index) {
		var basePanel = this.basePanel;
		basePanel.getCmpByName('conference.roomName')
				.setValue(cbo.getRawValue());
		basePanel.getCmpByName('conference.roomLocation')
				.setValue(cbo.getRawValue());
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
			callback:function(userId, fullName) {
				joinerPanel.getCmpByName('conference.compere').setValue(userId);
				joinerPanel.getCmpByName('conference.compereName').setValue(fullName);
		}}, false).show();
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
			callback:function(userId, fullName) {
				joinerPanel.getCmpByName('conference.recorder').setValue(userId);
				joinerPanel.getCmpByName('conference.recorderName').setValue(fullName);
			}}, false).show();
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
			callback:function(userId, fullName) {
				joinerPanel.getCmpByName('conference.attendUsers').setValue(userId);
				joinerPanel.getCmpByName('conference.attendUsersName').setValue(fullName);
			}}, false).show();
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
			callback:function(userId, fullName) {
				grantPanel.getCmpByName('updater').setValue(userId);
				grantPanel.getCmpByName('updaters').setValue(fullName);
			}}, false).show();
	},
	/**
	 * 审核人选择
	 */
	checkUserSelect : function() {
		var grantPanel = this.grantPanel;
		new UserDialog({
			callback:function(userId, fullName) {
				grantPanel.getCmpByName('conference.checkUserId').setValue(userId);
				grantPanel.getCmpByName('conference.checkName').setValue(fullName);
			}}, true).show();
	},
	/**
	 * 上传文件删除
	 */
	removeResumeFile : function (obj, fileId) {
		var fileIds = Ext.getCmp("addConferenceView.filePath").getCmpByName('filePath');
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
	 * 判断会议主持人和记录人是否存在重复,重复false 主持人---记录人,一对一，一对多,多对一时才会重复
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
			Ext.ux.Toast.msg('操作提示', '会议主持人和记录人不能出现重复，请重新选择！');
		}
		return bo;
	}
});


