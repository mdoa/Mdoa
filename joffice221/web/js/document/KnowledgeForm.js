Ext.ns('KnowledgeForm')
/**
 * 知识详细信息
 * 
 * @class KnowledgeForm
 * @extends Ext.Window
 */
KnowledgeForm = Ext.extend(Ext.Window, {
	// 构造方法
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		this.initUI();
		KnowledgeForm.superclass.constructor.call(this, {
					id : 'KnowledgeFormWin',
					title : '知识详细信息',
					iconCls : 'menu-personal-doc',
					width : 700,
					height : 560,
					modal : true,
					maximizable : true,
					layout : 'fit',
					items : this.formPanel,
					buttonAlign : 'center',
					buttons : [{
								xtype : 'button',
								text : '保存',
								iconCls : 'btn-save',
								handler : this.saveRecord,
								scope : this
							}, {
								xtype : 'button',
								text : '关闭',
								iconCls : 'btn-cancel',
								scope : this,
								handler : function() {
									this.close();
								}
							}]
				});
	},
	//初始化组件
	initUI : function() {
		//知识详细信息面板
		this.formPanel = new HT.FormPanel({
			defaults : {
				anchor : '98%,98%',
				margins : {
					top : 4,
					right : 4,
					bottom : 4,
					left : 4
				}
			},
			items : [{
						name : 'document.docId',
						xtype : 'hidden',
						value : this.docId == null ? '' : this.docId
					}, {
						name : 'document.docFolder.folderId',// 文档目录
						xtype : 'hidden',
						value : this.folderId == null ? '' : this.folderId
					}, {
						xtype : 'container',
						layout : 'column',
						height : 32,
						items : [{
									text : '选择目录:',
									xtype : 'label',
									width : 104
								}, {
									name : 'document.docFolder.folderName',
									xtype : 'textfield',
									width : 250,
									readOnly : true,
									allowBlank : false,
									value : this.folderName == null
											? ''
											: this.folderName
								}, {
									xtype : 'button',
									text : '请选择目录',
									iconCls : 'menu-mail_folder',
									scope : this,
									handler : this.selectFolder
								}, {
									xtype : 'button',
									text : '清除目录',
									iconCls : 'reset',
									scope : this,
									handler : this.cleanFolder
								}]
					}, {
						xtype : 'textfield',
						fieldLabel : '文档名称',
						name : 'document.docName',
						anchor : '98%',
						allowBlank : false
					}, {
						xtype : 'textfield',
						fieldLabel : '作者',
						name : 'document.author',
						anchor : '98%'
					}, {
						xtype : 'textfield',
						fieldLabel : '关键字',
						name : 'document.keywords',
						anchor : '98%'
					}, {
						height : 280,
						anchor : '96%',
						xtype : 'ckeditor',
						fieldLabel : '内容',
						name : 'document.content',
						allowBlank : false
					}, {
						xtype : 'hidden',
						name : 'document.docType',
						value : '文档'
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
												id : 'knowledgeFilePanel',
												frame : false,
												border : true,
												bodyStyle : 'padding:4px 4px 4px 4px',
												height : 80,
												autoScroll : true,
												html : ''
											}]
								}, {
									columnWidth : .3,
									items : [{
												border : false,
												xtype : 'button',
												text : '添加附件',
												iconCls : 'menu-attachment',
												scope : this,
												handler : this.addAttachment
											}, {
												xtype : 'button',
												text : '清除附件',
												iconCls : 'reset',
												scope : this,
												handler : this.cleanAttachment
											}, {
												xtype : 'hidden',
												id : 'document.fileIds',
												name : 'fileIds'
											}]
								}]
					}]
		});
		//加载数据
		if (!Ext.isEmpty(this.docId)) {
			this.formPanel.loadData({
						url : __ctxPath + '/document/getDocument.do?docId='
								+ this.docId,
						waitMsg : '正在载入数据...',
						preName : 'document',
						root : 'data',
						scope : this,
						success : function(response, options) {
							var doc = Ext.util.JSON
									.decode(response.responseText).data;
							// 附件展示
							this.loadAttachment(doc.attachFiles);
						},
						failure : function(response, options) {
							Ext.MessageBox.show({
										title : '操作信息',
										msg : '载入信息失败，请联系管理员！',
										buttons : Ext.MessageBox.OK,
										icon : 'ext-mb-error'
									});
						}
					});
		}

	},
	// 加载附件
	loadAttachment : function(attachFiles) {
		var filePanel = Ext.getCmp('knowledgeFilePanel');
		var fileIds = Ext.getCmp("document.fileIds");
		for (var i = 0; i < attachFiles.length; i++) {
			if (fileIds.getValue() != '') {
				fileIds.setValue(fileIds.getValue() + ',');
			}
			var fileId = attachFiles[i].fileId;
			var fileName = attachFiles[i].fileName;
			fileIds.setValue(fileIds.getValue() + fileId);
			Ext.DomHelper
					.append(
							filePanel.body,
							'<span><a href="#" onclick="FileAttachDetail.show('
									+ fileId
									+ ')">'
									+ fileName
									+ '</a><img class="img-delete" src="'
									+ __ctxPath
									+ '/images/system/delete.gif" onclick="KnowledgeForm.prototype.removeFile(this,'
									+ fileId + ')"/>&nbsp;|&nbsp;</span>');
		}
	},
	//添加附件
	addAttachment : function() {
		App.createUploadDialog({
					file_cat : 'document/privateDocument',
					scope : this,
					callback : function(attachFiles) {
						this.loadAttachment(attachFiles);
					}
				}).show();
	},
	// 清除附件
	cleanAttachment : function() {
		var fileAttaches = Ext.getCmp("document.fileIds");
		var filePanel = Ext.getCmp('knowledgeFilePanel');
		filePanel.body.update('');
		fileAttaches.setValue('');
	},
	//删除附件
	removeFile : function(obj, fileId) {
		var fileIds = Ext.getCmp("document.fileIds");
		var value = fileIds.getValue();
		if (value.indexOf(',') < 0) {// 仅有一个附件
			fileIds.setValue('');
		} else {
			value = value.replace(',' + fileId, '').replace(fileId + ',', '');
			fileIds.setValue(value);
		}
		Ext.get(obj.parentNode).remove();
	},
	//选择目录
	selectFolder : function() {
		new DocFolderSelector({
			isOnline: false,
			scope:this,
			callback : function(id,name){
				this.formPanel.getCmpByName('document.docFolder.folderId').setValue(id);
				this.formPanel.getCmpByName('document.docFolder.folderName').setValue(name);
			}
		}).show();

	},
	//清除目录
	cleanFolder : function() {
		this.formPanel.getCmpByName('document.docFolder.folderId').setValue('');
		this.formPanel.getCmpByName('document.docFolder.folderName').setValue('');
	},
	//保存
	saveRecord : function() {
		// 提交表单
		$postForm({
					formPanel : this.formPanel,
					scope : this,
					url : __ctxPath + '/document/saveDocument.do',
					callback : function(fp, action) {
						if (this.callback)
							this.callback.call(this.scope);
						this.close();
					}
				});
	}
});