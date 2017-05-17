/**
 * @author:
 * @class ArchFlowConfView
 * @extends Ext.Panel
 * @description 公文流程配置
 * @company 杭州梦德软件有限公司
 * @createtime:2010-01-16
 */
ArchFlowConfView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		ArchFlowConfView.superclass.constructor.call(this, {
					id : 'ArchFlowConfView',
					title : '公文流程配置',
					iconCls : 'menu-archive-setting',
					region : 'center',
					layout : 'form',
					items : [this.formPanel]
				});
	},// end of constructor

	// 初始化组件
	initUIComponents : function() {
		// 初始化设置表单
		this.formPanel = new Ext.FormPanel({
					layout : 'form',
					bodyStyle : 'padding:10px 10px 10px 10px',
					border : false,
					url : __ctxPath + '/archive/saveArchFlowConf.do',
					defaults : {
						anchor : '98%,98%'
					},
					defaultType : 'textfield',

					items : [{
						xtype : 'fieldset',
						title : '公文流程配置',
						items : [{
							xtype : 'container',
							layout : 'column',
							items : [{
										xtype : 'label',
										text : '发文流程:',
										width : 101
									}, {
										xtype : 'textfield',
										name : 'sendProcessName',
										width : 200
									}, {
										xtype : 'hidden',
										name : 'sendProcessId'
									}, {
										xtype : 'button',
										iconCls : 'menu-flow',
										text : '选择流程',
										scope : this,
										handler : this.settingFlow
												.createDelegate(this, ['send'])
									}]

						}, {
							xtype : 'container',
							style : 'padding-top:3px;',
							layout : 'column',
							items : [{
										xtype : 'label',
										text : '收文流程:',
										width : 101
									}, {
										xtype : 'textfield',
										name : 'recProcessName',
										width : 200
									}, {
										xtype : 'hidden',
										name : 'recProcessId'
									}, {
										xtype : 'button',
										text : '选择流程',
										iconCls : 'menu-flow',
										scope : this,
										handler : this.settingFlow
												.createDelegate(this, ['rec'])
									}]

						}]
					}]
				});
				
		//初始化数据
		Ext.Ajax.request({
					url : __ctxPath + '/archive/getArchFlowConf.do',
					scope : this,
					success : function(response, options) {
						var obj = Ext.util.JSON.decode(response.responseText).data;
						this.formPanel.getCmpByName('sendProcessId')
								.setValue(obj.sendProcessId);
						this.formPanel.getCmpByName('sendProcessName')
								.setValue(obj.sendProcessName);
						this.formPanel.getCmpByName('recProcessId')
								.setValue(obj.recProcessId);
						this.formPanel.getCmpByName('recProcessName')
								.setValue(obj.recProcessName);
					}
				});
	},// end of the initComponents()

	settingFlow : function(type) {
		var formPanel = this.formPanel;
		new FlowDialog({
			scope : this,
			single : true,
			callback : function(id, name) {
				if (type == 'send') {
					var recId = formPanel.getCmpByName('recProcessId')
							.getValue();
					if (recId != null && recId == id) {
						Ext.ux.Toast.msg('操作信息', '发文和收文流程不能指定同一个！');
						formPanel.getCmpByName('sendProcessId').setValue('');
						formPanel.getCmpByName('sendProcessName').setValue('');
						return;
					}
					formPanel.getCmpByName('sendProcessId').setValue(id);
					formPanel.getCmpByName('sendProcessName').setValue(name);
				} else {
					var sendId = formPanel.getCmpByName('sendProcessId')
							.getValue();
					if (sendId != null && sendId == id) {
						Ext.ux.Toast.msg('操作提示', '发文和公文流程不能指定同一个！');
						formPanel.getCmpByName('recProcessId').setValue('');
						formPanel.getCmpByName('recProcessName').setValue('');
						return;
					}
					formPanel.getCmpByName('recProcessId').setValue(id);
					formPanel.getCmpByName('recProcessName').setValue(name);
				}

				Ext.Ajax.request({
							url : __ctxPath + '/archive/settingArchFlowConf.do',
							method : 'POST',
							params : {
								defId : id,
								settingType : type
							},
							success : function(response, options) {
								Ext.ux.Toast.msg('操作信息', '公文设置成功.');
							}
						});
			}
		}).show();
	}
});
