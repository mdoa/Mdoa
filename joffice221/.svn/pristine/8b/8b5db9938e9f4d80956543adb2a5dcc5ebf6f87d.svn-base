Ext.ns('ArchTemplateForm');
/**
 * 公文模板详细信息
 * 
 * @author csx
 * @createtime 2010-10-11
 * @class ArchTemplateForm
 * @extends Ext.Window
 * @description 公文模板表单
 * @company 宏天软件
 */
ArchTemplateForm = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		// 必须先初始化组件
		this.initComponents();
		ArchTemplateForm.superclass.constructor.call(this, {
					id : 'ArchTemplateFormWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					height : 180,
					width : 560,
					title : '公文模板详细信息',
					iconCls : 'menu-archive-template',
					buttonAlign : 'center',
					buttons : this.buttons
				});
	},// end of the constructor
	// 初始化组件
	initComponents : function() {
		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			bodyStyle : 'padding:10px 10px 10px 10px',
			border : false,
			id : 'ArchTemplateForm',
			defaults : {
				anchor : '98%,98%'
			},
			defaultType : 'textfield',
			items : [{
						name : 'archTemplate.templateId',
						xtype : 'hidden',
						value : this.templateId == null ? '' : this.templateId
					}, {
						xtype : 'compositefield',
						fieldLabel : '所属类型',
						items : [{
									name : 'archTemplate.archivesType.typeName',
									xtype : 'textfield',
									width : 250,
									value : this.typeName
											? this.typeName
											: null,
									readOnly : true,
									allowBlank : false
								}, {
									xtype : 'button',
									text : '选择类型',
									iconCls : 'btn-select',
									scope : this,
									handler : this.selectType
								}]
					}, {
						fieldLabel : '模板名称',
						name : 'archTemplate.tempName',
						allowBlank : false
					}, {
						xtype : 'container',
						layout : 'column',
						style : 'padding-left:0px;margin-left:0px;',
						defaults : {
							border : false
						},
						items : [{
									width : 280,
									height : 36,
									style : 'padding-left:0px;',
									layout : 'form',
									items : {
										xtype : 'textfield',
										fieldLabel : '模板文件',
										name : 'archTemplate.tempPath',
										readOnly : true,
										anchor : '98%,98%'
									}
								}, {
									xtype : 'button',
									text : '上传模板',
									iconCls : 'btn-upload',
									scope : this,
									handler : this.uploadFile
								}, {
									xtype : 'button',
									text : '在线编辑',
									iconCls : 'btn-edit-online',
									scope : this,
									handler : this.editOnline
								}]
					}, {
						xtype : 'hidden',
						name : 'archTemplate.fileId'
					}, {
						xtype : 'hidden',
						name : 'archTemplate.archivesType.proTypeId',
						value : this.typeId ? this.typeId : null
					}]
		});
		// 加载表单对应的数据
		if (!Ext.isEmpty(this.templateId)) {
			this.formPanel.loadData({
						url : __ctxPath
								+ '/archive/getArchTemplate.do?templateId='
								+ this.templateId,
						root : 'data',
						preName : 'archTemplate'
					});
		}
		// 初始化功能按钮
		this.buttons = [{
					text : '保存',
					iconCls : 'btn-save',
					scope : this,
					handler : this.save
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
				}];
	},// end of the initcomponents
	/**
	 * 选择类型
	 */
	selectType : function() {
		var formPanel = this.formPanel;
		new GlobalTypeDialog({
					catKey : 'ARC_TEM_TYPE',
					isSingle : true,
					scope : this,
					callback : function(typeId, typeName) {
						formPanel
								.getCmpByName('archTemplate.archivesType.proTypeId')
								.setValue(typeId);
						formPanel
								.getCmpByName('archTemplate.archivesType.typeName')
								.setValue(typeName);
					}
				}).show();
	},
	/**
	 * 上传文件
	 */
	uploadFile : function() {
		var dialog = App.createUploadDialog({
					file_cat : 'archive',
					scope : this,
					callback : function(data) {
						for (var i = 0; i < data.length; i++) {
							this.formPanel.getCmpByName('archTemplate.fileId')
									.setValue(data[i].fileId);
							this.formPanel
									.getCmpByName('archTemplate.tempPath')
									.setValue(data[i].filePath);
						}
					}
				});
		dialog.show();
	},
	/**
	 * 在线编辑
	 */
	editOnline : function() {
		var tempPath = this.formPanel.getCmpByName('archTemplate.tempPath');
		var tempFileId = this.formPanel.getCmpByName('archTemplate.fileId');
		new OfficeTemplateView({
					fileId : tempFileId.getValue(),
					docType : 'doc',
					docPath : tempPath.getValue(),
					readOnly : false,
					scope : this,
					callback : function(fileId, filePath) {
						tempFileId.setValue(fileId);
						tempPath.setValue(filePath);
					}
				}).show();
	},
	/**
	 * 重置
	 * 
	 */
	reset : function() {
		this.formPanel.getForm().reset();
	},
	/**
	 * 取消
	 * 
	 */
	cancel : function() {
		this.close();
	},
	/**
	 * 保存记录
	 */
	save : function() {
		$postForm({
					formPanel : this.formPanel,
					url : __ctxPath + '/archive/saveArchTemplate.do',
					scope : this,
					callback : function(fp, action) {
						if (this.callback) {
							this.callback.call(this.scope);
						}
						this.close();
					}
				});
	}// end of save

});