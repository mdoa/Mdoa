Ext.ns('ReportTemplateForm');
/**
 * @author
 * @createtime
 * @class BookForm
 * @extends Ext.Window
 * @description 图书类别详细信息表单
 * @company 宏天软件
 */
ReportTemplateForm = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		// 必须先初始化组件
		this.initUI();
		ReportTemplateForm.superclass.constructor.call(this, {
					layout : 'fit',
					modal : true,
					id : 'ReportTemplateFormWin',
					title : '报表详细信息',
					iconCls : 'menu-report',
					width : 500,
					height : 400,
					buttonAlign : 'center',
					items : this.formPanel,
					buttons : this.buttons
				});
	},// end of the constructor
	// 初始化组件
	initUI : function() {
		// 按钮组
		this.buttons = [{
					text : '保存',
					iconCls : 'btn-save',
					scope : this,
					handler : this.save
				}, {
					text : '取消',
					iconCls : 'btn-cancel',
					scope : this,
					handler : this.cancel
				}];
		// 报表详细信表单
		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			border : false,
			bodyStyle : 'padding:5px;',
			defaults : {
				anchor : '96%,96%'
			},
			defaultType : 'textfield',
			items : [{
						name : 'reportTemplate.reportId',
						xtype : 'hidden',
						value : this.reportId == null ? '' : this.reportId
					}, {
						name : 'reportTemplate.createtime',
						xtype : 'hidden'
					}, {
						fieldLabel : '报表标题',
						readOnly : this.isDefaultIn == 1 ? true : false,
						name : 'reportTemplate.title',
						allowBlank : false,
						blankText : '报表标题不能为空'
					}, {
						fieldLabel : '报表描述',
						readOnly : this.isDefaultIn == 1 ? true : false,
						name : 'reportTemplate.descp',
						xtype : 'htmleditor',
						height : 200,
						allowBlank : false,
						blankText : '报表描述不能为空'

					}, {
						xtype : 'container',
						height : 26,
						layout : 'column',
						defaultType : 'textfield',
						items : [{
							xtype : 'label',
							style : 'padding-left:0px;margin-left:0px;margin-bottom:2px;',
							text : '报表模块路径:',
							width : 103
						}, {
							columnWidth : .8,
							name : 'reportTemplate.reportLocation',
							readOnly : this.isDefaultIn == 1 ? true : false,
							allowBlank : false,
							blankText : '报表模块jasper文件路径不能为空'
						}, {
							xtype : 'button',
							text : '上传附件',
							columnWidth : .2,
							disabled : this.isDefaultIn == 1 ? true : false,
							scope : this,
							handler : this.uploadFile
						}]
					}, {
						fieldLabel : '报表Key',
						readOnly : this.isDefaultIn == 1 ? true : false,
						name : 'reportTemplate.reportKey',
						allowBlank : false
					}, {
						fieldLabel : '是否缺省',
						readOnly : this.isDefaultIn == 1 ? true : false,
						hiddenName : 'reportTemplate.isDefaultIn',
						allowBlank : false,
						blankText : '是否缺省不能为空',
						xtype : 'combo',
						value : 0,
						store : new Ext.data.ArrayStore({
									fields : ['i', 'n'],
									data : [[0, '否'], [1, '是']]
								}),
						displayField : 'n',
						valueField : 'i',
						typeAhead : true,
						mode : 'local',
						triggerAction : 'all',
						forceSelection : true
					}]
		});
		// 加载数据
		if (!Ext.isEmpty(this.reportId)) {
			this.formPanel.loadData({
						url : __ctxPath
								+ '/system/getReportTemplate.do?reportId='
								+ this.reportId,
						preName : 'reportTemplate',
						root : 'data',
						scope : this,
						success : function(response, options) {
						},
						failure : function(response, options) {
							Ext.ux.Toast.msg('编辑', '载入失败');
						}
					});
		}

	},
	/**
	 * 上传附件
	 */
	uploadFile : function() {
		// 点击上传附件按钮后，调用上传组件
		var dialog = App.createUploadDialog({
					permitted_extensions : ['zip', 'jasper'],
					url : __ctxPath + '/jasper-upload',
					file_cat : 'report',
					scope : this,
					callback : function(data) {
						// 定义一个变量用来接收上传文件成功后返回的路径值
						var path = '';
						for (var i = 0; i < data.length; i++) {
							path = data[i].filePath;
							// 得到报表模块路径的字段，然后把上传成功后返回的路径值设到字段里面
							this.formPanel
									.getCmpByName('reportTemplate.reportLocation')
									.setValue(path);
						}
					}
				});
		dialog.show('queryBtn');
	},
	/**
	 * 保存
	 */
	save : function() {
		if (!this.formPanel.getForm().isValid())
			return;
		var reportKey = this.formPanel.getCmpByName('reportTemplate.reportKey')
				.getValue();
		var params = {};
		if (!Ext.isEmpty(this.reportId)) {
			params = {
				'Q_reportKey_S_EQ' : reportKey,
				'Q_reportId_L_NEQ' : this.reportId
			};
		} else {
			params = {
				'Q_reportKey_S_EQ' : reportKey
			};
		}
		Ext.Ajax.request({
					url : __ctxPath + '/system/listReportTemplate.do',
					method : 'POST',
					params : params,
					scope : this,
					success : function(response, opts) {
						var obj = Ext.decode(response.responseText);
						var checkCount = obj.totalCounts * 1;
						if (checkCount < 1) {
							// 保存数据
							$postForm({
										formPanel : this.formPanel,
										waitMsg : '正在提交数据...',
										scope : this,
										url : __ctxPath
												+ '/system/saveReportTemplate.do',
										callback : function(fp, action) {
											if (this.callback) {
												this.callback.call(this.scope);
											}
											this.close();
										}
									});
						} else {
							Ext.ux.Toast.msg("操作信息", "该报表Key已存在,不能重复保存!");
							this.formPanel
									.getCmpByName('reportTemplate.reportKey')
									.focus();
							return;
						}
					},
					failure : function(response, opts) {
					}
				});

	},// end of save
	/**
	 * 取消
	 */
	cancel : function() {
		this.close();
	}
});