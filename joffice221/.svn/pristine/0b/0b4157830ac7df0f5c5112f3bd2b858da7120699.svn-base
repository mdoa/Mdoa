Ext.ns('DocumentForm');
/**
 * 文档编辑表单
 * 
 * @class DocumentForm
 * @extends Ext.Window
 */
DocumentForm = Ext.extend(Ext.Window, {
	// 构造方法
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		this.initUI();
		DocumentForm.superclass.constructor.call(this, {
					id : 'DocumentFormWin',
					title : '文档详细信息',
					iconCls : 'menu-personal-doc',
					width : 700,
					height : 500,
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
								handler :  function() {
									this.close();
								}
							}]
				});
	},
	initUI : function() {
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
						fieldLabel : '目录*',
						hiddenId : 'documentFolderId',
						hiddenName : 'document.docFolder.folderId',
						name : 'document.docFolder.folderName',
						xtype : 'combotree',
						allowBlank : false,
						url : __ctxPath + '/document/listDocFolder.do?method=1'// 不把根目录显示出来
					}, {
						xtype : 'textfield',
						fieldLabel : '文档名称',
						name : 'document.docName',
						anchor : '98%',
						allowBlank : false
					}, {
						height : 280,
						anchor : '98%',
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
												id : 'personFilePanel',
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
		if (!Ext.isEmpty(this.folderId) && !Ext.isEmpty(this.folderName)) {
			this.formPanel.getCmpByName('document.docFolder.folderName')
					.setValue(this.folderName);
		} else if (!Ext.isEmpty(this.docId)) {
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
	/**
	 * 加载附件
	 */
	loadAttachment : function(attachFiles) {
		var filePanel = Ext.getCmp('personFilePanel');
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
									+ '/images/system/delete.gif" onclick="DocumentForm.prototype.removeFile(this,'
									+ fileId + ')"/>&nbsp;|&nbsp;</span>');
		}
	},
	/**
	 * 添加附件
	 */
	addAttachment : function() {
		App.createUploadDialog({
					file_cat : 'document/privateDocument',
					scope : this,
					callback : function(attachFiles) {
						this.loadAttachment(attachFiles);
					}
				}).show();
	},
	/**
	 * 清除附件
	 */
	cleanAttachment : function() {
		var fileAttaches = Ext.getCmp("document.fileIds");
		var filePanel = Ext.getCmp('personFilePanel');
		filePanel.body.update('');
		fileAttaches.setValue('');
	},
	/**
	 * 删除附件
	 */
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
	/**
	 * 保存
	 */
	saveRecord : function() {
		// 处理保存bug
		if (!Ext.isEmpty(this.folderId) && !Ext.isEmpty(this.folderName)) {
			var documentFolderName = Ext.getDom('documentFolderId').value;
			if (documentFolderName == this.folderName) {// 没修改
				Ext.getDom('documentFolderId').value = this.folderId;
			}
		}
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