/**
 * 部门计划详细信息
 * 
 * @class DepWorkPlanForm
 * @extends Ext.Window
 */
DepWorkPlanForm = Ext.extend(Ext.Window, {
	// 构造方法
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUI();
		// 调用父类构造方法
		DepWorkPlanForm.superclass.constructor.call(this, {
					id : 'DepWorkPlanForm',
					iconCls : 'menu-depplan',
					layout : 'fit',
					region : 'center',
					title : '部门计划详细信息',
					items : this.formPanel,
					modal : true,
					width : 770,
					minWidth : 750,
					height : 600,
					minHeight : 600,
					maximizable : true,
					keys : {
						key : Ext.EventObject.ENTER,
						fn : this.saveRecord,
						scope : this
					},
					buttonAlign : 'center',
					buttons : this.buttons
				});
	},
	// 初始化组件
	initUI : function() {
		// 部门计划详细信息面板
		this.formPanel = new Ext.FormPanel({
			frame : false,
			border : false,
			layout : 'form',
			region : 'center',
			defaults : {
				padding : '5'
			},
			items : [{
						name : 'workPlan.planId',
						xtype : 'hidden',
						value : this.planId == null ? '' : this.planId
					}, {
						name : 'issueScopeIds',
						xtype : 'hidden'
					}, {
						name : 'participantsIds',
						xtype : 'hidden'
					}, {
						name : 'principalIds',
						xtype : 'hidden'
					}, {
						name : 'workPlan.isPersonal',
						value : 0,
						xtype : 'hidden'
					}, {
						xtype : 'panel',
						layout : 'form',
						border : false,
						width : 680,
						defaultType : 'textfield',
						bodyStyle : 'padding-top:10px',
						defaults : {
							border : false
						},
						items : [{
							width : 520,
							hiddenName : 'workPlan.globalType.proTypeId',
							name : 'workPlan.globalType.typeName',
							fieldLabel : '计划类型',
							xtype : 'combotree',
							allowBlank : false,
							url : __ctxPath
									+ '/system/treeGlobalType.do?catKey=DEPWORKPLAN&method=1'
						}, {
							fieldLabel : '计划名称',
							name : 'workPlan.planName',
							width : 520,
							allowBlank : false
						}, {
							xtype : 'container',
							border : false,
							layout : 'hbox',
							layoutConfig : {
								padding : '0',
								align : 'middle'
							},
							defaults : {
								xtype : 'label',
								margins : {
									top : 0,
									right : 4,
									bottom : 4,
									left : 0
								}
							},
							items : [{
										text : '时间范围:',
										width : 101,
										style : 'padding-left:0px;padding-top:3px;'
									}, {
										xtype : 'datetimefield',
										width : 250,
										format : 'Y-m-d H:i:s',
										allowBlank : false,
										editable : false,
										name : 'workPlan.startTime'
									}, {
										text : ' 至 ',
										style : 'padding-left:0px;padding-top:3px;',
										width : 8
									}, {
										xtype : 'datetimefield',
										width : 253,
										format : 'Y-m-d H:i:s',
										allowBlank : false,
										editable : false,
										name : 'workPlan.endTime'
									}]
						}, {
							fieldLabel : '计划内容',
							name : 'workPlan.planContent',
							xtype : 'htmleditor',
							height : 180,
							width : 520,
							allowBlank : false
						}, {
							layout : 'column',
							style : 'padding-left:0px;',
							width : 670,
							border : false,
							xtype : 'container',
							items : [{
										columnWidth : .83,
										border : false,
										style : 'padding-left:0px;',
										layout : 'form',
										items : [{
													fieldLabel : '附件',
													xtype : 'panel',
													frame : false,
													name : 'planFilePanel',
													height : 45,
													autoScroll : true,
													html : ''
												}]
									}, {
										columnWidth : .17,
										border : false,
										items : [{
													xtype : 'button',
													iconCls : 'menu-attachment',
													text : '添加附件',
													scope : this,
													handler : this.addAttachment
												}, {
													xtype : 'button',
													iconCls : 'reset',
													text : '清除附件',
													scope : this,
													handler : this.resetAttachment
												}, {
													xtype : 'hidden',
													id : 'planFileIds',
													name : 'planFileIds'
												}]
									}]
						}, {
							xtype : 'container',
							style : 'padding-left:0px;padding-bottom:3px;',
							layout : 'column',
							items : [{
										xtype : 'label',
										style : 'padding-left:0px;',
										text : '发布范围:',
										width : 105
									}, {
										xtype : 'textfield',
										name : 'workPlan.issueScope',
										readOnly : true,
										width : 402
									}, {
										xtype : 'button',
										text : '选择部门',
										iconCls : 'btn-select',
										scope : this,
										handler : this.depSelect
									}, {
										xtype : 'button',
										text : '清除记录',
										scope : this,
										handler : this.resetDepSelect
									}]
						}, {
							xtype : 'container',
							layout : 'column',
							style : 'padding-left:0px;padding-bottom:3px;',
							items : [{
										xtype : 'label',
										text : '参与人:',
										style : 'padding-left:0px;',
										width : 105
									}, {
										xtype : 'textfield',
										name : 'workPlan.participants',
										readOnly : true,
										width : 402
									}, {
										xtype : 'button',
										text : '选择人员',
										iconCls : 'btn-select',
										scope : this,
										handler : this.participantSelect
									}, {
										xtype : 'button',
										text : '清除记录',
										scope : this,
										handler : this.resetParticipantSelect
									}]
						}, {
							xtype : 'container',
							layout : 'column',
							style : 'padding-left:0px;padding-bottom:3px;',
							items : [{
										xtype : 'label',
										text : '负责人:',
										style : 'padding-left:0px;',
										width : 105
									}, {
										xtype : 'textfield',
										name : 'workPlan.principal',
										allowBlank : false,
										readOnly : true,
										width : 402
									}, {
										xtype : 'button',
										text : '选择人员',
										scope : this,
										iconCls : 'btn-select',
										handler : this.principalSelect
									}, {
										xtype : 'button',
										text : '清除记录',
										scope : this,
										handler : this.resetPrincipalSelect
									}]
						}, {
							xtype : 'radiogroup',
							fieldLabel : '是否启用',
							autoHeight : true,
							columns : 2,
							width : 520,
							items : [{
										boxLabel : '是',
										name : 'workPlan.status',
										inputValue : 1,
										checked : true
									}, {
										boxLabel : '否',
										name : 'workPlan.status',
										inputValue : 0
									}]
						}, {
							fieldLabel : '标识',
							hiddenName : 'workPlan.icon',
							xtype : 'iconcomb',
							mode : 'local',
							allowBlank : false,
							width : 520,
							editable : false,
							store : new Ext.data.SimpleStore({
										fields : ['flagStyle', 'flagName'],
										data : [['ux-flag-blue', '日常计划'],
												['ux-flag-orange', '重要计划'],
												['ux-flag-green', '特殊计划'],
												['ux-flag-pink', '个人计划'],
												['ux-flag-red', '紧急计划'],
												['ux-flag-purple', '部门计划'],
												['ux-flag-yellow', '待定计划']]
									}),
							valueField : 'flagStyle',
							displayField : 'flagName',
							// iconClsField : 'flagClass',
							triggerAction : 'all',
							value : 'ux-flag-blue'
						}, {
							fieldLabel : '备注',
							name : 'workPlan.note',
							xtype : 'textarea',
							width : 520,
							height : 50
						}]
					}]
		});

		this.buttons = [{
					text : '保存',
					iconCls : 'btn-save',
					scope : this,
					handler : this.saveRecord
				}, {
					text : '重置',
					iconCls : 'btn-reset',
					scope : this,
					handler : this.reset
				}, {
					text : '关闭',
					iconCls : 'btn-cancel',
					scope : this,
					handler : this.closeWin
				}];
		// 加载表单
		if (this.planId != '' && this.planId != null
				&& this.planId != undefined) {
			var formPanel = this.formPanel;
			formPanel.loadData({
				url : __ctxPath + '/task/getWorkPlan.do?planId=' + this.planId,
				waitMsg : '正在载入数据...',
				preName : 'workPlan',
				root : 'data',
				success : function(response, options) {
					var json = Ext.util.JSON.decode(response.responseText);
					var workPlan = json.data;
					var af = workPlan.planFiles;
					var filePanel = formPanel.getCmpByName('planFilePanel');
					var fileIds = formPanel.getCmpByName("planFileIds");
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
												+ '/images/system/delete.gif" onclick="DepWorkPlanForm.prototype.removeResumeFile(this,'
												+ af[i].fileId
												+ ')"/>&nbsp;|&nbsp;</span>');
					}

				},
				failure : function(form, action) {
					Ext.ux.Toast.msg('编辑', '载入失败');
				}
			});
		}
	},
	// 保存
	saveRecord : function() {
		var formPanel = this.formPanel;
		var issueScope = formPanel.getCmpByName('workPlan.issueScope')
				.getValue();// 发布范围
		var participants = formPanel.getCmpByName('workPlan.participants')
				.getValue();// 参与人
		var st = formPanel.getCmpByName('workPlan.startTime').getValue();
		var et = formPanel.getCmpByName('workPlan.endTime').getValue();
		if ((issueScope == '') && participants == '') {
			Ext.ux.Toast.msg('操作提示', '发布范围，参与人至少填写一项!');
			return;
		}
		if (Date.parse(st) > Date.parse(et)) {
			Ext.ux.Toast.msg('操作信息', '开始时间大于结束进间,不能保存!');
			return;
		};
		$postForm({
					formPanel : formPanel,
					scope : this,
					url : __ctxPath + '/task/saveWorkPlan.do',
					callback : function(fp, action) {
						if (this.callback) {
							this.callback.call(this.scope);
						}
						this.close();
					}
				});

	},// save method over
	/**
	 * 关闭
	 */
	closeWin : function() {
		this.close();

	},
	// 重置
	reset : function() {
		this.formPanel.getForm().reset();
	},
	// 添加附件
	addAttachment : function() {
		var formPanel =this.formPanel;
		var dialog = App.createUploadDialog({
			file_cat : 'task/plan/depWorkPlan',
			callback : function(data) {
				var fileIds = formPanel.getCmpByName("planFileIds");
				var filePanel = formPanel.getCmpByName('planFilePanel');
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
											+ '/images/system/delete.gif" onclick="DepWorkPlanForm.prototype.removeResumeFile(this,'
											+ data[i].fileId
											+ ')"/>&nbsp;|&nbsp;</span>');
				}
			}
		});
		dialog.show(this);
	},
	// 清除附件
	resetAttachment : function() {
		var fileAttaches = this.formPanel.getCmpByName("planFileIds");
		var filePanel = this.formPanel.getCmpByName('planFilePanel');
		filePanel.body.update('');
		fileAttaches.setValue('');
	},
	// 上传文件删除
	removeResumeFile : function(obj, fileId) {
		var fileIds = Ext.getCmp("planFileIds");
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
	// 部门选择
	depSelect : function() {
		var formPanel = this.formPanel;
		new DepDialog({
					scope : this,
					callback : function(id, name) {
						formPanel.getCmpByName('workPlan.issueScope')
								.setValue(name);
						formPanel.getCmpByName('issueScopeIds').setValue(id);
					}
				}).show();
	},
	// 清除部门选择记录
	resetDepSelect : function() {
		this.formPanel.getCmpByName('workPlan.issueScope').setValue('');
		this.formPanel.getCmpByName('issueScopeIds').setValue('');
	},
	// 参与人人员选择
	participantSelect : function() {
		var formPanel = this.formPanel;
		new UserDialog({
					scope : this,
					single : false,
					callback : function(id, name) {
						formPanel.getCmpByName('workPlan.participants')
								.setValue(name);
						formPanel.getCmpByName('participantsIds').setValue(id);
					}
				}).show();
	},
	// 取消参与人人员选择
	resetParticipantSelect : function() {
		this.formPanel.getCmpByName('workPlan.participants').setValue('');
		this.formPanel.getCmpByName('participantsIds').setValue('');
	},
	// 负责人人员选择
	principalSelect : function() {
		var formPanel = this.formPanel;
		new UserDialog({
					scope : this,
					single : false,
					callback : function(id, name) {
						formPanel.getCmpByName('workPlan.principal')
								.setValue(name);
						formPanel.getCmpByName('principalIds').setValue(id);
					}
				}).show();
	},
	// 取消负责人人员选择
	resetPrincipalSelect : function() {
		this.formPanel.getCmpByName('workPlan.principal').setValue('');
		this.formPanel.getCmpByName('principalIds').setValue('');
	}
});
