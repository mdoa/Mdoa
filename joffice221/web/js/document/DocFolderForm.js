Ext.ns('DocFolderForm');
/**
 * 目录详细信息
 * 
 * @class DocFolderForm
 * @extends Ext.Window
 */
DocFolderForm = Ext.extend(Ext.Window, {
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		this.scope = this.scope == null ? this : this.scope;
		this.initUI();
		DocFolderForm.superclass.constructor.call(this, {
					id : 'DocFolderFormWin',
					title : '目录详细信息',
					iconCls : 'menu-mail_folder',
					width : 400,
					height : 220,
					modal : true,
					layout : 'fit',
					border : false,
					buttonAlign : 'center',
					items : [this.formPanel],
					buttons : this.buttons
				});
	},
	//初始化面板
	initUI : function() {
		// 表单面板
		this.formPanel = new Ext.FormPanel({
					layout : 'form',
					defaults : {
						widht : 400,
						anchor : '96%,96%'
					},
					bodyStyle : 'padding:5px;',
					formId : 'DocFolderFormId',
					defaultType : 'textfield',
					items : [{
						name : 'docFolder.folderId',
						xtype : 'hidden',
						value : this.folderId == null
								? ''
								: this.folderId
					}, {
						fieldLabel : '目录名称',
						name : 'docFolder.folderName',
						allowBlank : false
					}, {
						fieldLabel : '描述',
						name : 'docFolder.descp',
						id : 'descp',
						xtype : 'textarea'
					}, {
						xtype : 'hidden',
						name : 'docFolder.parentId',
						value : this.parentId
					}, {
						xtype : 'hidden',
						name : 'docFolder.isShared',
						value : this.isShared == null
								? 0
								: this.isShared
					},{
						xtype : 'hidden',
						name : 'docFolder.appUser.userId',
						value : this.userId
					}
					]
				});

		this.buttons = [{
					xtype : 'button',
					text : '保存',
					iconCls : 'btn-save',
					scope : this,
					handler : this.saveRecord
				}, {
					xtype : 'button',
					text : '关闭',
					iconCls : 'btn-close',
					scope : this,
					handler : function() {
						this.close();
					}
				}];

		if (!Ext.isEmpty(this.folderId)) {
			this.formPanel.loadData({
						url : __ctxPath
								+ '/document/getDocFolder.do?folderId='
								+ this.folderId,
						preName : 'docFolder',
						root : 'data',
						scope : this,
						success : function(response, options) {
						},
						failure : function(form, action) {
							Ext.ux.Toast.msg('编辑', '载入失败');
						}
					});
		}
	},
	//保存
	saveRecord : function() {

		$postForm({
					formPanel : this.formPanel,
					waitMsg : '正在提交数据...',
					scope : this,
					url : __ctxPath + '/document/saveDocFolder.do',
					callback : function(fp, action) {
						if (this.callback) {
							this.callback.call(this.scope);
						}
						this.close();
					}
				});
	}
});
