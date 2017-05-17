/**
 * @class ConfSummaryForm
 * @extends Ext.Window
 * @description ConfSummary表单
 * @company 宏天软件
 */
ConfSummaryForm = Ext.extend(Ext.Window, {
	sope:this,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		// 调用父类构造
		ConfSummaryForm.superclass.constructor.call(this, {
					id : 'ConfSummaryFormWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					height : 350,
					minHeight : 350,
					width : 420,
					minWidth : 420,
					maximizable : true,
					title : '编辑会议纪要',
					iconCls : 'menu-confSummary',
					buttonAlign : 'center',
					buttons : [{
								text : '发送',
								iconCls : 'btn-mail_send',
								scope : this,
								handler : this.sendConf
							}, {
								text : '保存',
								iconCls : 'btn-save',
								scope : this,
								handler : this.saveConf
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
		//表单面板
		this.formPanel = new Ext.FormPanel({
			id : 'ConfSummaryForm',
			layout : 'form',
			bodyStyle : 'padding:10px 10px 10px 10px',
			border : false,
			defaults : {
				anchor : '98%,98%'
			},
			items : [{
				name : 'confSummary.sumId',
				xtype : 'hidden',
				value : this.sumId == null ? '' : this.sumId
			}, {
				anchor : '99%',
				fieldLabel : '会议议题',
				name : 'confSummary.confId.confTopic',
				xtype : 'textfield',
				readOnly : true
			}, {
				xtype : 'hidden',
				name : 'confSummary.confId.confId'
			}, {
				anchor : '99%',
				fieldLabel : '纪要创建时间',
				name : 'confSummary.createtime',
				xtype : 'textfield',
				readOnly : true
			}, {
				anchor : '99%',
				xtype : 'container',
				layout : 'column',
				fieldLabel : '纪要人',
				items : [{
					columnWidth : 1,
					anchor : '100%',
					xtype : 'textfield',
					name : 'confSummary.creator',
					readOnly : true,
					allowBlank : false,
					maxLength : 256
				}, {
					xtype : 'button',
					text : '请选择',
					iconCls : 'btn-user-sel',
					scope : this,
					handler : this.creatorSelect
				} ]
			}, {
				anchor : '99%',
				xtype : 'textarea',
				fieldLabel : '纪要内容',
				name : 'confSummary.sumContent',
				allowBlank : false,
				maxLength : 4000
			}, {
				anchor : '99%',
				fieldLabel : '状态',
				hiddenName : 'confSummary.status',
				xtype : 'combo',
				mode : 'local',
				readOnly : true,
				editable : false,
				triggerAction : 'all',
				store : [ [ '0', '未发送' ], [ '1', '发送' ] ]
			}, {
				anchor : '99%',
				fieldLabel : '附件',
				xtype : 'container',
				layout : 'column',
				border : false,
				defaults : { 
					border : false 
				},
				items : [{
					columnWidth : 1,
					layout : 'form',
					border : false,
					items : [{
						anchor : '100%',
						xtype : 'panel',
						name : 'confSummaryFormFilePanel',
						frame : false,
						border : true,
						bodyStyle : 'padding:4px 4px 4px 4px',
						height : 50,
						autoScroll : true,
						html : ''
					}, {
						xtype : 'hidden',
						name : 'fileIds'
					}]
				}, {
					items : [{
						xtype : 'button',
						text : '添加附件',
						iconCls:'menu-attachment',
						scope : this,
						handler : this.upLoadFile
					}, {
						xtype : 'button',
						text : '清除附件',
						iconCls : 'reset',
						scope : this,
						handler : this.delLoadFile
					}]
				}]
			} ]
		});
		// 加载表单对应的数据
		if (this.sumId != null && this.sumId != 'undefined') {
			var me = this;
			this.formPanel.loadData({
						url : __ctxPath + '/admin/getConfSummary.do?sumId=' + this.sumId,
						root : 'data',
						preName : 'confSummary',
						success : function(response, obj) {
							var cf = Ext.util.JSON.decode(response.responseText);
							me.setFilePanel(cf.data.attachFiles);
						},
						failure : function(form, action) {
							Ext.ux.Toast.msg('操作提示','对不起，数据加载有误！');
						}
					});
		};
		
		
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
	sendConf : function(){
		this.save(1);
	},
	saveConf : function(){
		this.save(0);
	},
	/**
	 * 保存记录
	 */
	save : function(type) {
		var formPanel = this.formPanel;
		var me = this;
		var url = type == 0 ? __ctxPath + '/admin/editConfSummary.do'
				: __ctxPath + '/admin/sendConfSummary.do';
		if (formPanel.getForm().isValid()) {
			formPanel.getForm().submit( {
				url : url,
				method : 'post',
				waitMsg : '正在提交数据，请稍候...',
				success : function(fp, action) {
					Ext.ux.Toast.msg('操作信息', type == 0 ? '会议纪要保存成功！' : '会议纪要发送成功！');
					var gridPanel = Ext.getCmp('ConfSummaryGrid');
					if (gridPanel != null) {
						gridPanel.getStore().reload();
					}
					me.close();
				},
				failure : function(fp, action) {
					Ext.MessageBox.show( {
						title : '操作信息',
						msg : '信息发送出错，请联系管理员！',
						buttons : Ext.MessageBox.OK,
						icon : Ext.MessageBox.ERROR
					});
					me.close();
				}
			});
		}
	},
	/**
	 * 文件上传
	 */
	upLoadFile : function() {
		var formPanel = this.formPanel;
		var dialog = App.createUploadDialog( {
			url : __ctxPath+'/file-upload',
			file_cat : 'admin/confSummary',
			callback : function(arr) {
				var str = '';
				var filePath = '';
				for ( var i = 0; i < arr.length; i++) {
					str += arr[i].fileId + ",";
					filePath += '<span><a href="#" onclick="FileAttachDetail.show('
						+ arr[i].fileId + ')">'
						+ arr[i].fileName + '</a> <img class="img-delete" src="' + __ctxPath
						+ '/images/system/delete.gif" onclick="removeContractAttach(this,'
						+ arr[i].fileId + ')"/>&nbsp;|&nbsp;</span>';
				}
				str = str.substring(0, str.length - 1);
				formPanel.getCmpByName('fileIds').setValue(str);
				var filePathPanel = formPanel.getCmpByName('confSummaryFormFilePanel');
				Ext.DomHelper.append(filePathPanel.body,filePath);
			}
		});
		dialog.show('querybtn');
	},
	
	/**
	 * 删除上传文件
	 */
	delLoadFile : function() {
		var formPanel = this.formPanel;
		var fileIds = formPanel.getCmpByName('fileIds').value;
		if (fileIds != null && fileIds != '' && fileIds != 'undefined') {
			Ext.Msg.confirm('确认信息', '您真的要删除上传文件吗？', function(btn) {
				if (btn == 'yes') {
					Ext.Ajax.request( {
						url : __ctxPath + '/system/multiDelFileAttach.do',
						method : 'post',
						params : {
							ids : fileIds
						},
						success : function() {
							Ext.ux.Toast.msg('操作提示', '上传文件删除成功！');
							formPanel.getCmpByName('fileIds').setValue('');
							formPanel.getCmpByName('confSummaryFormFilePanel').update();
						},
						failure : function() {
							Ext.ux.Toast.msg('操作提示', '对不起，您上传文件删除失败！');
						}
					});
				}
			});
		} else {
			Ext.ux.Toast.msg('操作提示', '对不起，你还没有上传文件！');
			}
		},
	
	/**
	 * 显示附件列表
	 */
	setFilePanel : function(records) {
		var fileIds = '';
		var formPanel = this.formPanel;
		var filePanel = formPanel.getCmpByName('confSummaryFormFilePanel');
		for ( var i = 0; i < records.length; i++) {
			fileIds += records[i].fileId + ',';
			var del = '<img class="img-delete" src="'+ __ctxPath
					+ '/images/system/delete.gif" onclick="ConfSummaryForm.prototype.removeResumeFile(this,'
					+ records[i].fileId + ')"/>';
			Ext.DomHelper.append(filePanel.body,
					'<span><a href="#" onclick="FileAttachDetail.show('
							+ records[i].fileId + ')">' + records[i].fileName + '</a>'
							+ del + '&nbsp;|&nbsp;</span>');
		}
		formPanel.getCmpByName('fileIds').setValue(fileIds.substring(0, fileIds.length - 1));
	},
	
	/**
	 * 上传文件删除
	 */
	removeResumeFile : function (obj, fileId) {
		var fileIds = Ext.getCmp('ConfSummaryForm').getCmpByName('fileIds');
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
	 * 纪要人选择
	 */
	creatorSelect : function() {
		var formPanel = this.formPanel;
		new UserDialog({callback:function(userId,fullName) {
				formPanel.getCmpByName('confSummary.creator').setValue(fullName);
		}}).show();
	}
	
});

