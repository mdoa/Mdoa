
/**
 * 在线文档编辑
 * 
 *  //实例化的控件对象
 *  var myNewOffice = null;
 *  //页面对象
 *  var myFormPanel = null;
 *  //提交类型
 *  var mySubmitType = null;
 * 
 * @class OnlineDocumentForm
 * @extends Ext.Window
 */
OnlineDocumentForm = Ext.extend(Ext.Window, {
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		// 初始化组件
		this.initUI();
		OnlineDocumentForm.superclass.constructor.call(this, {
					id : 'olDocForm',
					title : '在线文档',
					width : 800,
					height : 640,
					shim : false,
					modal : true,
					layout : 'fit',
					iconCls : 'menu-onlinedoc',
					maximizable : true,
					buttonAlign : 'center',
					buttons : this.buttons,
					items : [this.formPanel]
				});

	},
	// 初始化组件
	initUI : function() {
		mySubmitType = "OnlineDocumentForm";
		this.docPanel = new NtkOfficePanel({
					showToolbar : true,
					height : 500,
					doctype : this.docType,
					unshowMenuBar : true
				});
		myNewOffice = this.docPanel;
		
		if (!this.docPanel.flag) {
			var msg = this.docPanel.msg;
			setTimeout(function() {
						Ext.MessageBox.show({
									title : '操作信息',
									msg : msg,
									buttons : Ext.MessageBox.OK,
									icon : 'ext-mb-warning',
									fn : function() {
										Ext.getCmp('olDocForm').close();
									}
								});
					}, 500);
			return;
		}

		Ext.useShims = true;// 默认下，Ext会自动决定浮动元素是否应该被填充。如果你在用Flash那么该值很可能要设置为True

		this.formPanel = new HT.FormPanel({
					defaults : {
						anchor : '98%,98%'
					},
					items : [{
								xtype : 'hidden',
								name : 'document.docId'
							}, {
								name : 'document.docFolder.folderId',// 文档目录
								xtype : 'hidden',
								value : this.folderId == null
										? ''
										: this.folderId
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
								fieldLabel : '文档名称',
								xtype : 'textfield',
								allowBlank : false,
								name : 'document.docName'
							}, this.docPanel.panel, {
								xtype : 'hidden',
								name : 'documentFileId'
							}, {
								xtype : 'hidden',
								name : 'document.docType',
								value : this.docType
							}]

				});
		
		myFormPanel = this.formPanel;

		if (this.docId != null && this.docId != 'undefined') {
			this.formPanel.loadData({
						url : __ctxPath + '/document/getDocument.do?docId='
								+ this.docId,
						waitMsg : '正在载入数据...',
						preName : 'document',
						root : 'data',
						scope : this,
						success : function(response, options) {
							var doc = Ext.util.JSON.decode(response.responseText).data;
							var af = doc.attachFiles;
							if (af != null) {
								var fileId = af[0].fileId;
								this.formPanel.getCmpByName('documentFileId').setValue(fileId);
								var filePath = af[0].filePath;
								if(Ext.isChrome){
									//谷歌浏览器加载插件有延迟问题，所以我们迟一点时间去打开文档
									var setTimeNum = mySetTimeNum;
	    					        if(setTimeNum==null||setTimeNum=='undefined'||setTimeNum<1){
	    						    	setTimeNum = 500;
	    						    }
									setTimeout(function (){
										this.docPanel.openDoc(fileId);
									},setTimeNum);	
								}else{
									this.docPanel.openDoc(fileId);
								}			
							}
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

		this.buttons = [{
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
						if (this.docPanel.flag) {
							this.docPanel.closeDoc();
						}
						this.close();
					}
				}];

	},
	/**
	 * 选择目录
	 */
	selectFolder : function() {
		new DocFolderSelector({
					isOnline : true,
					scope : this,
					callback : function(id, name) {
						this.formPanel
								.getCmpByName('document.docFolder.folderId')
								.setValue(id);
						this.formPanel
								.getCmpByName('document.docFolder.folderName')
								.setValue(name);
					}
				}).show();

	},
	/**
	 * 清除目录
	 */
	cleanFolder : function() {
		this.formPanel.getCmpByName('document.docFolder.folderId').setValue('');
		this.formPanel.getCmpByName('document.docFolder.folderName')
				.setValue('');
	},
	/**
	 * 保存
	 */
	saveRecord : function() {
		var dPanel = this.docPanel;
		if (dPanel.flag) {
			var win = this;
			var formPanel = this.formPanel;
			var docName = formPanel.getCmpByName('document.docName').getValue();
			var fileIdField = formPanel.getCmpByName('documentFileId');
			var fileId = '';
			fileId = fileIdField.getValue();
			var obj = null;
			if (fileId != '' && fileId != undefined) {
				obj = dPanel.saveDoc({
							docName : docName,
							fileId : fileId,
							doctype : this.docType
						});
			} else {
				obj = dPanel.saveDoc({
							docName : docName,
							doctype : this.docType
						});
			}
			if (obj && obj.success) {
				var fileId = obj.fileId;
				if(fileId>0){
					fileIdField.setValue(fileId);
					// 提交表单
					$postForm({
								formPanel : this.formPanel,
								waitMsg : '正在提交数据...',
								scope : this,
								url : __ctxPath + '/document/saveOnlineDocument.do',
								callback : function(fp, action) {
									if (this.callback) {
										this.callback.call(this.scope);
									}
									if (this.docPanel.flag) {
										this.docPanel.closeDoc();
									}
									this.close();
								}
							});	
				}else{
					//火狐谷歌实现异步提交的内容：在NtkOfficePanel.js 中的saveMethodOnComplete方法实现;
				}
			}
		} else {
			Ext.MessageBox.show({
						title : '操作信息',
						msg : '保存信息失败，请联系管理员！',
						buttons : Ext.MessageBox.OK,
						icon : 'ext-mb-error'
					});
		}
	}
});