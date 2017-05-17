/**
 * @author lyy
 * @createtime
 * @class JobChangeForm
 * @extends Ext.Panel
 * @description JobChange表单
 * @company 宏天软件
 */
JobChangeForm = Ext.extend(Ext.Panel, {
	// 内嵌FormPanel
	formPanel : null,
	// 构造函数
	constructor : function(_cfg) {
		if (_cfg == null) {
			_cfg = {};
		}
		Ext.apply(this, _cfg);
		// 必须先初始化组件
		this.initComponents();
		JobChangeForm.superclass.constructor.call(this, {
					id : 'JobChangeForm',
					layout : 'fit',
					iconCls : 'menu-job-reg',
					items : this.formPanel,
					modal : true,
					height : 200,
					width : 400,
					tbar : this.toolbar,
					maximizable : true,
					title : '登记职位调动信息',
					buttonAlign : 'center'
				});
	},// end of the constructor
	// 初始化组件
	initComponents : function() {
		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			bodyStyle : 'padding:10px 20px 10px 10px',
			border : false,
			url : __ctxPath + '/hrm/saveJobChange.do',
			id : 'JobChangeFormPanel',
			autoScroll : true,
			defaults : {
				anchor : '98%,98%'
			},
			defaultType : 'textfield',
			items : [{
						name : 'jobChange.changeId',
						xtype : 'hidden',
						value : this.changeId == null ? '' : this.changeId
					}, {
						xtype : 'hidden',
						name : 'jobChange.profileId'
					}, {
						xtype : 'hidden',
						name : 'jobChange.orgJobId'
					}, {
						xtype : 'hidden',
						name : 'jobChange.newJobId'
					}, {
						xtype : 'hidden',
						name : 'jobChange.orgDepId'
					}, {
						xtype : 'hidden',
						name : 'jobChange.orgStandardId'
					}, {
						xtype : 'hidden',
						name : 'jobChange.newStandardId'
					}, {
						xtype : 'hidden',
						name : 'jobChange.regName'
					}, {
						xtype : 'hidden',
						name : 'jobChange.regTime'
					}, {
						xtype : 'hidden',
						name : 'jobChange.checkName'
					}, {
						xtype : 'hidden',
						name : 'jobChange.checkTime'
					}, {
						xtype : 'hidden',
						name : 'jobChange.checkOpinion'
					}, {
						xtype : 'hidden',
						name : 'jobChange.status'
					}, {
						xtype : 'container',
						layout : 'column',
						anchor : '100%',
						items : [{
									xtype : 'label',
									style : 'padding:3px 5px 0px 17px;',
									text : '档案编号:'
								}, {
									name : 'jobChange.profileNo',
									width : '50%',
									xtype : 'textfield',
									allowBlank : false,
									readOnly : true
								}, {
									xtype : 'button',
									autoWidth : true,
									text : '选择档案',
									iconCls : 'menu-profile',
									scope : this,
									handler : this.selectEmpProfile
								}]
					}, {
						xtype : 'fieldset',
						title : '员工基本信息',
						layout : 'column',
						items : [{
									xtype : 'container',
									columnWidth : .5,
									layout : 'form',
									defaults : {
										anchor : '98%,98%'
									},
									defaultType : 'textfield',
									items : [{
												fieldLabel : '姓名',
												readOnly : true,
												allowBlank : false,
												name : 'jobChange.userName'
											}, {
												fieldLabel : '原部门名称',
												readOnly : true,
												name : 'jobChange.orgDepName'
											}, {
												fieldLabel : '原职位名称',
												readOnly : true,
												name : 'jobChange.orgJobName'
											}]
								}, {
									xtype : 'container',
									columnWidth : .5,
									defaults : {
										anchor : '98%,98%'
									},
									layout : 'form',
									defaultType : 'textfield',
									items : [{
												fieldLabel : '原薪酬标准',
												readOnly : true,
												name : 'jobChange.orgStandardName'
											}, {
												fieldLabel : '原薪酬编号',
												readOnly : true,
												name : 'jobChange.orgStandardNo'
											}, {
												fieldLabel : '原工资总额',
												readOnly : true,
												name : 'jobChange.orgTotalMoney'
											}]
								}]
					}, {
						xtype : 'fieldset',
						title : '员工调动信息',
						layout : 'column',
						items : [{
							xtype : 'container',
							layout : 'form',
							columnWidth : .5,
							defaults : {
								anchor : '98%,98%'
							},
							defaultType : 'textfield',
							items : [{
								fieldLabel : '新部门名称',
								hiddenId : 'jobChange.newDepId',// 隐藏域的id
								hiddenName : 'jobChange.newDepId',
								name : 'jobChange.newDepName',
								xtype : 'combotree',
								url : __ctxPath
										+ '/system/listDepartment.do?opt=appUser'// 不把根目录显示出来
							}, {
								fieldLabel : '新薪酬标准',
								name : 'jobChange.newStandardName',
								xtype : 'combo',
								mode : 'local',
								allowBlank : false,
								editable : false,
								valueField : 'standardName',
								displayField : 'standardName',
								triggerAction : 'all',
								store : new Ext.data.JsonStore({
											url : __ctxPath
													+ '/hrm/comboStandSalary.do',
											fields : [{
														name : 'standardId',
														type : 'int'
													}, 'standardNo',
													'standardName',
													'totalMoney',
													'setdownTime', 'status']
										}),
								listeners : {
									scope : this,
									focus : function() {
										this.formPanel
												.getForm()
												.findField('jobChange.newStandardName')
												.getStore().reload();
									},
									select : function(combo, record, index) {
										this.formPanel
												.getForm()
												.findField('jobChange.newStandardId')
												.setValue(record.data.standardId);
										this.formPanel
												.getForm()
												.findField('jobChange.newStandardNo')
												.setValue(record.data.standardNo);
										this.formPanel
												.getForm()
												.findField('jobChange.newTotalMoney')
												.setValue(record.data.totalMoney);
									}
								}
							}, {
								fieldLabel : '新薪酬编号',
								allowBlank : false,
								readOnly : true,
								name : 'jobChange.newStandardNo'
							}]
						}, {
							xtype : 'container',
							layout : 'form',
							defaults : {
								anchor : '98%,98%'
							},
							columnWidth : .5,
							defaultType : 'textfield',
							items : [{
										xtype : 'container',
										layout : 'column',
										border : false,
										fieldLabel : '新职位',
										items : [{
													columnWidth : .99,
													xtype : 'textfield',
													name : 'jobChange.newJobName',
													readOnly : true
												}, {
													width : 80,
													text : '请选择',
													xtype : 'button',
													iconCls : 'btn-position-sel',
													scope : this,
													handler : this.selectPosition
												}]
									}, {
										fieldLabel : '新工资总额',
										allowBlank : false,
										readOnly : true,
										name : 'jobChange.newTotalMoney'
									}]
						}]
					}, {
						xtype : 'fieldset',
						title : '调动情况说明',
						layout : 'form',
						defaultType : 'textfield',
						items : [{
									fieldLabel : '更改原由',
									name : 'jobChange.changeReason',
									xtype : 'textarea',
									anchor : '100%'
								}, {
									fieldLabel : '备注',
									name : 'jobChange.memo',
									xtype : 'textarea',
									anchor : '100%'
								}]
					}]
		});
		this.toolbar = new Ext.Toolbar({
					items : [{
								text : '保存为草稿',
								xtype : 'button',
								iconCls : 'btn-save',
								handler : this.save
										.createCallback(this.formPanel)
							}, {
								text : '提交审核',
								xtype : 'button',
								iconCls : 'btn-ok',
								handler : this.submit
										.createCallback(this.formPanel)
							}, {
								text : '重置',
								xtype : 'button',
								iconCls : 'btn-reset',
								handler : this.reset
										.createCallback(this.formPanel)
							}, {
								text : '取消',
								xtype : 'button',
								iconCls : 'btn-cancel',
								handler : this.cancel.createCallback(this)
							}]
				});// end of toolbar
		// 加载表单对应的数据
		if (this.changeId != null && this.changeId != 'undefined') {
			this.formPanel.loadData({
						deferredRender : false,
						url : __ctxPath + '/hrm/getJobChange.do?changeId='
								+ this.changeId,
						preName : 'jobChange',
						waitMsg : '正在载入数据...',
						root : 'data',
						success : function(response, options) {
						},
						failure : function(response, options) {
						}
					});
		}
	},// end of the initcomponents
	/**
	 * 选择职位
	 */
	selectPosition : function() {
		var formPanel = this.formPanel;
		new PositionDialog({
					scope : this,
					single : true,
					callback : function(posIds, posNames) {
						formPanel.getCmpByName('jobChange.newJobName')
								.setValue(posNames);
						formPanel.getCmpByName('jobChange.newJobId')
								.setValue(posIds);
					}
				}).show();
	},
	/**
	 * 选择档案
	 */
	selectEmpProfile : function() {
		var formPanel = this.formPanel;
		new EmpProfileDialog({
					scope : this,
					callback : function(array) {
						formPanel.getForm().findField('jobChange.profileId')
								.setValue(array[0]);
						formPanel.getForm().findField('jobChange.profileNo')
								.setValue(array[1]);
						formPanel.getForm().findField('jobChange.userName')
								.setValue(array[2]);
						formPanel.getForm().findField('jobChange.orgJobId')
								.setValue(array[3]);
						formPanel.getForm().findField('jobChange.orgJobName')
								.setValue(array[4]);
						formPanel.getForm().findField('jobChange.orgDepId')
								.setValue(array[5]);
						formPanel.getForm().findField('jobChange.orgDepName')
								.setValue(array[6]);
						formPanel.getForm()
								.findField('jobChange.orgStandardNo')
								.setValue(array[7]);
						formPanel.getForm()
								.findField('jobChange.orgStandardName')
								.setValue(array[8]);
						formPanel.getForm()
								.findField('jobChange.orgTotalMoney')
								.setValue(array[9]);
						formPanel.getForm()
								.findField('jobChange.orgStandardId')
								.setValue(array[10]);
					}
				}).show();
	},
	/**
	 * 重置
	 * 
	 * @param {}
	 *            formPanel
	 */
	reset : function(formPanel) {
		formPanel.getForm().reset();

	},
	/**
	 * 取消
	 * 
	 * @param {}
	 *            tappanel
	 */
	cancel : function(formpanel) {
		var tabs = Ext.getCmp('centerTabPanel');
		if (formpanel != null) {
			tabs.remove('JobChangeForm');
		}
	},
	/**
	 * 保存草稿
	 */
	save : function(formPanel) {
		// Ext.getCmp('JobChangeForm.status').setValue('-1');
		formPanel.getForm().findField('jobChange.status').setValue('-1');
		if (formPanel.getForm().isValid()) {
			formPanel.getForm().submit({
						method : 'POST',
						waitMsg : '正在提交数据...',
						success : function(fp, action) {
							Ext.ux.Toast.msg('操作信息', '成功保存信息！');
							var JobChangeView = Ext.getCmp('JobChangeView');
							if (JobChangeView != null) {
								JobChangeView.gridPanel.getStore().reload();
							}
							formPanel.getForm().reset();
						},
						failure : function(fp, action) {
							Ext.MessageBox.show({
										title : '操作信息',
										msg : '信息保存出错，请联系管理员！',
										buttons : Ext.MessageBox.OK,
										icon : Ext.MessageBox.ERROR
									});
						}
					});
		}
	},// end of save
	/**
	 * 提交审核
	 */
	submit : function(formPanel) {
		// Ext.getCmp('JobChangeForm.status').setValue('0');
		formPanel.getForm().findField('jobChange.status').setValue('0');
		if (formPanel.getForm().isValid()) {
			formPanel.getForm().submit({
						method : 'POST',
						waitMsg : '正在提交数据...',
						success : function(fp, action) {
							Ext.ux.Toast.msg('操作信息', '成功保存信息！');
							var JobChangeView = Ext.getCmp('JobChangeView');
							if (JobChangeView != null) {
								JobChangeView.gridPanel.getStore().reload();
							}
							formPanel.getForm().reset();
						},
						failure : function(fp, action) {
							Ext.MessageBox.show({
										title : '操作信息',
										msg : '信息保存出错，请联系管理员！',
										buttons : Ext.MessageBox.OK,
										icon : Ext.MessageBox.ERROR
									});
						}
					});
		}
	}// end of submit
});