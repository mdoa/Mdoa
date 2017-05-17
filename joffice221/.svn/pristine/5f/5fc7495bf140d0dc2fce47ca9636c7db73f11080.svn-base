Ext.ns('AddConfSummaryView');
/**
 * @description 新建会议纪要
 * @class AddConfSummaryView
 * @extends Ext.Panel
 */
AddConfSummaryView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		// 调用父类构造
		AddConfSummaryView.superclass.constructor.call(this, {
					id : 'AddConfSummaryViewPanel',
					layout : 'form',
					region : 'center',
					title : '创建会议纪要',
					iconCls : 'menu-confSummary_add',
					header : true,
					region : 'center',
					layout : 'border',
					bodyStyle : 'padding:5px 5px 5px 5px',
					items : this.formPanel,
					keys : {
						key : Ext.EventObject.ENTER,
						fn : this.sendConf.createCallback(this),
						scope : this
					}
				});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		// 顶端初始化工具栏
		this.topBar = new Ext.Toolbar({
					heigth : 30,
					defaultType : 'button',
					items : [{
								iconCls : 'btn-mail_send',
								text : '发送',
								scope : this,
								handler : this.sendConf
							}, '-', {
								iconCls : 'btn-save',
								text : '保存',
								scope : this,
								handler : this.saveConf
							},'-',{
								xtype : 'button',
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

		this.addConfSummary = new Ext.form.FieldSet({
					title : '新增会议纪要信息',
					width : '100%',
					anchor : '100%',
					layout : 'form',
					buttonAlign : 'center',
					defaults : {
						margins : {
							top : 5,
							left : 5,
							bottom : 5,
							right : 5
						}
					},
					items : [{
								xtype : 'hidden',
								name : 'confSummary.confId.confId'
							}, {
								anchor : '99%',
								fieldLabel : '会议标题',
								xtype : 'container',
								layout : 'column',
								style : 'margin-bottom:5px;',
								items : [{
											columnWidth : 1,
											anchor : '99%',
											xtype : 'textfield',
											name : 'confTopic',
											readOnly : true,
											allowBlank : false,
											maxLength : 128
										}, {
											xtype : 'button',
											text : '请选择',
											iconCls : 'btn-user-sel',
											scope : this,
											handler : this.conferenceTitleSelect
										}]
							}, {
								anchor : '99%',
								xtype : 'htmleditor',
								fieldLabel : '纪要内容',
								name : 'confSummary.sumContent',
								height : 200,
								allowBlank : false,
								maxLength : 4000
							}, {
								xtype : 'hidden',
								name : 'fileIds'
							}, {
								anchor : '99%',
								fieldLabel : '附件',
								xtype : 'container',
								layout : 'column',
								defaults : {
									border : false
								},
								border : false,
								items : [{
											columnWidth : 1,
											anchor : '100%',
											layout : 'form',
											border : false,
											items : [{
														anchor : '100%',
														xtype : 'panel',
														name : 'filePathPanel',
														frame : false,
														border : true,
														height : 50,
														autoScroll : true,
														html : ''
													}]
										}, {
											width : 75,
											defaultType : 'button',
											items : [{
														text : '添加附件',
														iconCls : 'menu-attachment',
														scope : this,
														handler : this.upLoadFile
													}, {
														text : '清除附件',
														iconCls : 'reset',
														scope : this,
														handler : this.delLoadFile
													}]
										}]
							}],
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
								xtype : 'button',
								text : '清空',
								iconCls : 'reset',
								scope : this,
								handler : this.reset
							}, {
								text : '取消',
								iconCls : 'btn-cancel',
								handler : this.close,
								scope : this
							}]

				});
		// 表单面板
		this.formPanel = new Ext.FormPanel({
					region : 'center',
					layout : 'form',
					id : 'AddConfSummaryViewForm',
					tbar : this.topBar,
					bodyStyle : 'padding:10px 10px 0 10px;',
					frame : false,
					border : true,
					defaultType : 'texfield',
					items : [this.addConfSummary]
				});// end of this formPanel
				
	}, // end of this initUIComponent
	/**
	 * 清空
	 */
	reset : function() {
		this.formPanel.getForm().reset();
		this.formPanel.getCmpByName('fileIds').setValue('');
		this.addConfSummary.getCmpByName('filePathPanel').update();
	},
	/**
	 * 发送
	 */
	sendConf : function(){
		this.save(1);
	},
	/**
	 * 保存
	 */
	saveConf : function(){
		this.save(0);
	},
	/**
	 * 保存
	 */
	save : function(type) {
		var formPanel = this.formPanel;
		var addConfSummary =this.addConfSummary;
		var url = type == 0 ? __ctxPath + '/admin/saveConfSummary.do'
				: __ctxPath + '/admin/sendConfSummary.do';
		if (formPanel.getForm().isValid()) {
			formPanel.getForm().submit({
						url : url,
						method : 'post',
						success : function() {
							Ext.ux.Toast.msg('操作信息', type == 0 ? '恭喜您，保存会议纪要成功！' : '恭喜您，会议纪要发送成功！');
							formPanel.getForm().reset();
							addConfSummary.getCmpByName('filePathPanel').update();
							var tabs = Ext.getCmp('centerTabPanel');
							tabs.remove('AddConfSummaryViewPanel');
							App.clickTopTab('ConfSummaryView');
						},
						failure : function(fm, action) {
							Ext.ux.Toast.msg('操作提示', action.result.msg);
							addConfSummary.getCmpByName('confSummary.sumContent').focus(true);
						}
					});
		}
	},
	/**
	 * 文件上传
	 */
	upLoadFile : function() {
		var addConfSummary = this.addConfSummary;
		var formPanel = this.formPanel;
		var dialog = App.createUploadDialog({
			url : __ctxPath + '/file-upload',
			file_cat : 'admin/confSummary',
			callback : function(arr) {
				var str = '';
				var filePath = '';
				for (var i = 0; i < arr.length; i++) {
					str += arr[i].fileId + ",";
					filePath += '<span><a href="#" onclick="FileAttachDetail.show('
							+ arr[i].fileId
							+ ')">'
							+ arr[i].fileName
							+ '</a> <img class="img-delete" src="'
							+ __ctxPath
							+ '/images/system/delete.gif" onclick="AddConfSummaryView.prototype.removeContractAttach(this,'
							+ arr[i].fileId + ')"/>&nbsp;|&nbsp;</span>';
				}
				str = str.substring(0, str.length - 1);
				formPanel.getCmpByName('fileIds').setValue(str);
				var filePathPanel = addConfSummary.getCmpByName('filePathPanel');
				Ext.DomHelper.append(filePathPanel.body, filePath);
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
		var addConfSummary = this.addConfSummary;
		if (fileIds != null && fileIds != '' && fileIds != 'undefined') {
			Ext.Msg.confirm('确认信息', '您真的要删除上传文件吗？', function(btn) {
						if (btn == 'yes') {
							formPanel.getCmpByName('fileIds').setValue('');
							addConfSummary.getCmpByName('filePathPanel').update();	
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
		Ext.getCmp('centerTabPanel').remove('AddConfSummaryViewPanel');
	},
	/**
	 * 标题选择
	 */
	conferenceTitleSelect : function() {
		var formPanel = this.formPanel;
		new ConferenceDialog({
					single : true,
					scope : this,
					callback : function(confId, confTopic) {
						formPanel.getCmpByName('confSummary.confId.confId')
								.setValue(confId);
						formPanel.getCmpByName('confTopic').setValue(confTopic);
					}
				}).show();
	},
	/**
	 * 删除附件
	 * @param {} obj
	 * @param {} fileId
	 */
	removeContractAttach : function (obj, fileId) {
		var fileIds = Ext.getCmp("AddConfSummaryViewForm").getCmpByName('fileIds');
		var value = fileIds.getValue();
		if (value.indexOf(',') < 0) {// 仅有一个附件
			fileIds.setValue('');
		} else {
			value = value.replace(',' + fileId, '').replace(fileId + ',', '');
			fileIds.setValue(value);
		}
		var el = Ext.get(obj.parentNode);
		el.remove();
	}
	
});


