/**
 * @author
 * @createtime
 * @class RegulationForm
 * @extends Ext.Window
 * @description 行政管理-规章制度管理-表单
 * @company 宏天软件
 */
RegulationForm = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		// 调用父类构造
		RegulationForm.superclass.constructor.call(this, {
					id : 'RegulationFormWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					iconCls : 'menu-regulation',
					height : 577,
					width : 1000,
					maximizable : true,
					title : '规章制度详细信息',
					buttonAlign : 'center',
					buttons : [{
								text : '草稿',
								iconCls : 'btn-save',
								scope : this,
								handler : this.draft
							}, {
								text : '生效',
								iconCls : 'btn-save',
								scope : this,
								handler : this.effect
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
		// 表单
		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			bodyStyle : 'padding:10px',
			border : false,
			autoScroll : true,
			defaults : {
				anchor : '98%,96%'
			},
			defaultType : 'textfield',
			items : [{
						name : 'regulation.regId',
						xtype : 'hidden',
						value : this.regId == null ? '' : this.regId
					}, {
						fieldLabel : '状态',
						name : 'regulation.status',
						xtype : 'hidden'
					}, {
						name : 'regulation.globalType.proTypeId',
						xtype : 'hidden'
					}, {
						name : 'regulation.issueUserId',
						xtype : 'hidden',
						value : curUserInfo.userId
					}, {
						name : 'regulation.issueDepId',
						xtype : 'hidden',
						value : curUserInfo.depId
					}, {
						name : 'regulation.recDepIds',
						xtype : 'hidden',
						maxLength : 1024
					}, {
						name : 'regulation.recUserIds',
						xtype : 'hidden',
						maxLength : 1024
					}, {
						name : 'regAttachsFileIds',
						id : 'regAttachsFileIds',
						xtype : 'hidden'
					}, {
						xtype : 'compositefield',
						fieldLabel : '制度类型',
						items : [{
									name : 'regulation.globalType.typeName',
									xtype : 'textfield',
									width : 250,
									readOnly : true,
									allowBlank : false
								}, {
									xtype : 'button',
									text : '选择类型',
									iconCls : 'btn-select',
									scope : this,
									handler : this.selectType

								}, {
									text : '关键字:',
									xtype : 'label',
									width : 60
								}, {
									xtype : 'textfield',
									name : 'regulation.keywords',
									maxLength : 256,
									width : 250
								}, {
									text : '发布日期:',
									xtype : 'label',
									width : 60
								}, {
									// fieldLabel : '发布日期',
									name : 'regulation.issueDate',
									xtype : 'datefield',
									format : 'Y-m-d',
									value : new Date()
								}]
					}, {
						xtype : 'compositefield',
						fieldLabel : '发布人',
						items : [{
									value : curUserInfo.fullname,
									width : 250,
									xtype : 'textfield',
									readOnly : true,
									name : 'regulation.issueFullname',
									maxLength : 64
								}, {
									xtype : 'button',
									iconCls : 'btn-select',
									text : '选择人员',
									scope : this,
									handler : this.userSelector
								}, {
									text : '发布部门:',
									xtype : 'label',
									width : 60
								}, {
									xtype : 'textfield',
									name : 'regulation.issueDep',
									maxLength : 64,
									width : 250,
									readOnly : true,
									value : curUserInfo.depName
								}, {
									xtype : 'button',
									iconCls : 'btn-select',
									text : '选择部门',
									scope : this,
									handler : this.depDialog
								}]
					}, {
						xtype : 'compositefield',
						fieldLabel : '接收部门范围',
						items : [{
									name : 'regulation.recDeps',
									xtype : 'textarea',
									readOnly : true,
									width : 650,
									maxLength : 1024
								}, {
									xtype : 'button',
									text : '选择部门',
									iconCls : 'btn-select',
									scope : this,
									handler : this.depScopeSelector
								}]
					}, {
						xtype : 'compositefield',
						fieldLabel : '接收人范围',
						items : [{
									width : 650,
									name : 'regulation.recUsers',
									readOnly : true,
									xtype : 'textarea',
									maxLength : 1024
								}, {
									xtype : 'button',
									iconCls : 'btn-select',
									text : '选择人员',
									scope : this,
									handler : this.userScopeSelector
								}]
					}, {
						fieldLabel : '标题',
						name : 'regulation.subject',
						allowBlank : false,
						maxLength : 256
					}, {
						fieldLabel : '内容',
						name : 'regulation.content',
						xtype : 'ckeditor',
						maxLength : 65535
					}, {
						xtype : 'container',
						layout : 'column',
						border : false,
						defaults : {
							border : false
						},
						items : [{
									columnWidth : .7,
									layout : 'form',
									border : false,
									items : [{
												fieldLabel : '附件',
												xtype : 'panel',
												name : 'regAttachs',
												frame : false,
												border : true,
												bodyStyle : 'padding:4px 4px 4px 4px',
												height : 70,
												autoScroll : true,
												html : ''
											}]
								}, {
									columnWidth : .3,
									items : [{
												border : false,
												xtype : 'button',
												text : '添加附件',
												scope : this,
												iconCls : 'menu-attachment',
												handler : this.addAttachment
											}, {
												xtype : 'button',
												text : '清除附件',
												scope : this,
												iconCls : 'reset',
												handler : this.resetAttachment
											}]
								}]
					}]
		});

		// 加载表单对应的数据
		if (!Ext.isEmpty(this.regId)) {
			var formPanel = this.formPanel;
			formPanel.loadData({
				url : __ctxPath + '/admin/getRegulation.do?regId=' + this.regId,
				root : 'data',
				preName : 'regulation',
				// 成功时
				success : function(response, options) {
					var result = Ext.util.JSON.decode(response.responseText);
					if (!result)
						return;
					var af = result.data.regAttachs;
					var filePanel = formPanel.getCmpByName('regAttachs');
					var fileIds = formPanel.getCmpByName("regAttachsFileIds");
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
												+ '/images/system/delete.gif" onclick="RegulationForm.prototype.removeFile(this,'
												+ af[i].fileId
												+ ')"/>&nbsp;|&nbsp;</span>');
					}
				},
				failure : function(response, options) {
					Ext.ux.Toast.msg('编辑', '载入失败');
				}
			});
		}

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
	 * 草稿
	 */
	draft : function() {
		this.save(0);
	},
	/**
	 * 生效
	 */
	effect : function() {
		this.save(1);
	},
	/**
	 * 保存记录
	 */
	save : function(status) {
		var formPanel = this.formPanel;
		formPanel.getCmpByName('regulation.status').setValue(status);
		$postForm({
					formPanel : formPanel,
					scope : this,
					url : __ctxPath + '/admin/saveRegulation.do',
					callback : function(fp, action) {
						if (this.callback) {
							this.callback.call(this.scope);
						}
						this.close();
					}
				});
	},// end of save
	/**
	 * 制度选择类型
	 */
	selectType : function() {
		var formPanel = this.formPanel;
		new GlobalTypeDialog({
					catKey : 'REGULATION',
					isSingle : true,
					callback : function(typeId, typeName) {
						formPanel
								.getCmpByName('regulation.globalType.proTypeId')
								.setValue(typeId);
						formPanel
								.getCmpByName('regulation.globalType.typeName')
								.setValue(typeName);
					}
				}).show();
	},
	/**
	 * 人员选择
	 */
	userSelector : function() {
		var formPanel = this.formPanel;
		new UserDialog({
					scope : this,
					single : true,
					callback : function(userIds, fullnames) {
						formPanel.getCmpByName('regulation.issueFullname')
								.setValue(fullnames);
						formPanel.getCmpByName('regulation.issueUserId')
								.setValue(userIds);
					}
				}).show();
	},
	/**
	 * 接收人范围选择
	 */
	userScopeSelector : function() {
		var formPanel = this.formPanel;
		new UserDialog({
					scope : this,
					single : false,
					callback : function(userIds, fullnames) {
						formPanel.getCmpByName('regulation.recUsers')
								.setValue(fullnames);
						formPanel.getCmpByName('regulation.recUserIds')
								.setValue(userIds);
					}
				}).show();
	},
	/**
	 * 部门选择
	 */
	depDialog : function() {
		var formPanel = this.formPanel;
		new DepDialog({
					single : true,
					scope : this,
					callback : function(ids, names) {
						formPanel.getCmpByName('regulation.issueDep')
								.setValue(names);
						formPanel.getCmpByName('regulation.issueDepId')
								.setValue(ids);
					}
				}).show();
	},
	/**
	 * 接收部门范围选择
	 */
	depScopeSelector : function() {
		var formPanel = this.formPanel;
		new DepDialog({
					scope : this,
					single : true,
					callback : function(ids, names) {
						formPanel.getCmpByName('regulation.recDeps')
								.setValue(names);
						formPanel.getCmpByName('regulation.recDepIds')
								.setValue(ids);
					}
				}).show();
	},
	/**
	 * 添加附件
	 */
	addAttachment : function() {
		var formPanel = this.formPanel;
		var dialog = App.createUploadDialog({
			file_cat : 'admin/regulation',
			callback : function(data) {
				var fileIds = formPanel.getCmpByName("regAttachsFileIds");
				var filePanel = formPanel.getCmpByName('regAttachs');

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
											+ '/images/system/delete.gif" onclick="RegulationForm.prototype.removeFile(this,'
											+ data[i].fileId
											+ ')"/>&nbsp;|&nbsp;</span>');
				}
			}
		}).show(this);
	},
	/**
	 * 清除附件
	 */
	resetAttachment : function() {
		var formPanel = this.formPanel;
		var fileIds = formPanel.getCmpByName("regAttachsFileIds");
		var filePanel = formPanel.getCmpByName('regAttachs');

		filePanel.body.update('');
		fileIds.setValue('');
	},
	/**
	 * 删除附件
	 * 
	 * @param {}
	 *            obj
	 * @param {}
	 *            fileId
	 */
	removeFile : function(obj, fileId) {
		var fileIds = Ext.getCmp("regAttachsFileIds");
		var value = fileIds.getValue();
		if (value.indexOf(',') < 0) {// 仅有一个附件
			fileIds.setValue('');
		} else {
			value = value.replace(',' + fileId, '').replace(fileId + ',', '');
			fileIds.setValue(value);
		}
		// 移出页面附件的节点
		Ext.get(obj.parentNode).remove();
	}

});
