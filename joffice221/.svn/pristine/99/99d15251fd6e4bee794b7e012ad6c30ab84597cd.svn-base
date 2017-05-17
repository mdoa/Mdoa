/**
 * @author lyy
 * @createtime
 * @class SalaryPayoffForm
 * @extends Ext.Window
 * @description SalaryPayoff表单
 * @company 宏天软件
 */
SalaryPayoffForm = Ext.extend(Ext.Panel, {
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
		SalaryPayoffForm.superclass.constructor.call(this, {
					id : 'SalaryPayoffForm',
					layout : 'fit',
					iconCls:'menu-add-salay',
					items : this.formPanel,
					modal : true,
					tbar : this.toolbars,
					height : 200,
					width : 400,
					maximizable : true,
					title : '工资发放详细信息'
				});
	},// end of the constructor
	// 初始化组件
	initComponents : function() {
		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			autoScroll : true,
			bodyStyle : 'padding:10px 10px 10px 10px',
			border : false,
			url : __ctxPath + '/hrm/saveSalaryPayoff.do',
			defaults : {
				anchor : '98%,98%'
			},
			defaultType : 'textfield',
			items : [{
						name : 'salaryPayoff.recordId',
						xtype : 'hidden',
						value : this.recordId == null ? '' : this.recordId
					},{
						xtype : 'hidden',
						name : 'salaryPayoff.userId'
					},{
						xtype : 'hidden',
						name : 'salaryPayoff.regTime'
					},{
						xtype : 'hidden',
						name : 'salaryPayoff.register'
					},{
						xtype : 'hidden',
						name : 'salaryPayoff.checkName'
					},{
						xtype : 'hidden',
						name : 'salaryPayoff.checkTime'
					},{
						xtype : 'hidden',
						name : 'salaryPayoff.checkStatus'
					},{
						xtype : 'hidden',
						name : 'salaryPayoff.acutalAmount'
					},{
						xtype : 'hidden',
						name : 'salaryPayoff.standardId'
					},{
						xtype : 'fieldset',
						title : '发放时间段',
						layout : 'column',
						items : [{
									layout : 'form',
									border : false,
									columnWidth : .5,
									items : [{
												fieldLabel : '开始时间',
												xtype : 'datefield',
												format : 'Y-m-d',
												allowBlank : false,
												name : 'salaryPayoff.startTime'
											}]
								},{
									layout : 'form',
									border : false,
									columnWidth : .5,
									items : [{
												xtype : 'datefield',
												format : 'Y-m-d',
												fieldLabel : '结束时间',
												allowBlank : false,
												name : 'salaryPayoff.endTime'
											}]
								}]
					},{
						xtype : 'fieldset',
						title : '基本情况',
						// defaultType : 'textfield',
						layout : 'form',
						defaults : {
							anchor : '96%,96%'
						},
						items : [{
							xtype : 'container',
							layout : 'column',
							style : 'padding-bottom:3px;',
							anchor : '100%',
							items : [{
										columnWidth : .5,
										xtype : 'container',
										defaults : {
											anchor : '96%,96%'
										},
										style : 'padding-left:0px;',
										layout : 'form',
										items : [{
													fieldLabel : '档案编号',
													name : 'salaryPayoff.profileNo',
													xtype : 'textfield',
													readOnly : true,
													allowBlank : false
												}]
									},{
										columnWidth : .5,
										xtype : 'button',
										autoWidth : true,
										text : '选择档案',
										iconCls : 'menu-profile',
										scope:this,
										handler : this.selectEmpProfile
									}]
						},{
							layout : 'column',
							xtype : 'container',
							items : [{
										xtype : 'container',
										layout : 'form',
										columnWidth : .5,
										defaults : {
											anchor : '96%,96%'
										},
										style : 'padding-left:0px;',
										defaultType : 'textfield',
										items : [{
													fieldLabel : '员工姓名',
													name : 'salaryPayoff.fullname',
													readOnly : true,
													allowBlank:false
												}]
									},{
										xtype : 'container',
										layout : 'form',
										columnWidth : .5,
										defaults : {
											anchor : '96%,96%'
										},
										defaultType : 'textfield',
										items : [{
													fieldLabel : '身份证号',
													name : 'salaryPayoff.idNo',
													readOnly : true
												}]
									}]
						}]
					},{
						xtype : 'fieldset',
						layout : 'column',
						title : '工资发放基本情况',
						items : [{
									layout : 'form',
									columnWidth : .5,
									xtype : 'container',
									items : [{
												fieldLabel : '薪标金额',
												xtype : 'numberfield',
												readOnly : true,
												allowBlank : false,
												name : 'salaryPayoff.standAmount',
												blankText : '薪标金额不能为空!'
											},{
												fieldLabel : '扣除金额',
												xtype : 'numberfield',
												allowBlank : false,
												name : 'salaryPayoff.deductAmount',
												blankText : '扣除金额不能为空!'
											}]
								}, {
									layout : 'form',
									columnWidth : .5,
									xtype : 'container',
									items : [{
												fieldLabel : '奖励金额',
												xtype : 'numberfield',
												allowBlank : false,
												name : 'salaryPayoff.encourageAmount',
												blankText : '奖励金额不能为空!'
											}, {
												fieldLabel : '绩效工资',
												allowBlank : false,
												xtype : 'numberfield',
												name : 'salaryPayoff.achieveAmount',
												blankText : '绩效工资不能为空!'
											}]
								}
						]

					}, {
						xtype : 'fieldset',
						title : '发放说明',
						layout : 'form',
						defaultType : 'textarea',
						items : [{
									fieldLabel : '奖励描述',
									name : 'salaryPayoff.encourageDesc',
									anchor : '100%'
								}, {
									fieldLabel : '扣除描述',
									anchor : '100%',
									name : 'salaryPayoff.deductDesc'
								}, {
									fieldLabel : '备注',
									anchor : '100%',
									name : 'salaryPayoff.memo'
								}]
					}]
		});
		// 加载表单对应的数据
		if (this.recordId != null && this.recordId != 'undefined') {
			this.formPanel.loadData({
				deferredRender : false,
				url : __ctxPath + '/hrm/getSalaryPayoff.do?recordId='
						+ this.recordId,
				waitMsg : '正在载入数据...',
				preName : 'salaryPayoff',
				success : function(response, options) {
				},
				failure : function(response, options) {
				}
			});
		}
		// 初始化功能按钮
		this.toolbars = [{
					text : '保存',
					iconCls : 'btn-save',
					scope:this,
					handler : this.save.createCallback(this.formPanel)
				}, {
					text : '重置',
					iconCls : 'btn-reset',
					scope:this,
					handler : this.reset.createCallback(this.formPanel)
				}, {
					text : '取消',
					iconCls : 'btn-cancel',
					scope:this,
					handler : this.cancel.createCallback(this)
				}];
	},// end of the initcomponents
	/**
	 * 选择档案
	 */
	selectEmpProfile:function() {
		var formPanel=this.formPanel;
		new EmpProfileDialog({
			  callback:function(array) {
				  	formPanel.getForm().findField('salaryPayoff.profileNo')
				  		.setValue(array[1]);
					formPanel.getForm().findField('salaryPayoff.fullname')
							.setValue(array[2]);
					formPanel.getForm().findField('salaryPayoff.standAmount')
							.setValue(array[9]);
					formPanel.getForm().findField('salaryPayoff.standardId')
							.setValue(array[10]);
					formPanel.getForm().findField('salaryPayoff.idNo')
							.setValue(array[11]);
					formPanel.getForm().findField('salaryPayoff.userId')
							.setValue(array[12]);	
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
	 *            window
	 */
	cancel : function(panel) {
		var tabs = Ext.getCmp('centerTabPanel');
		if (panel != null) {
			tabs.remove('SalaryPayoffForm');
		}
	},
	/**
	 * 保存记录
	 */
	save : function(formPanel) {
		if (formPanel.getForm().isValid()) {
			formPanel.getForm().submit({
						method : 'POST',
						waitMsg : '正在提交数据...',
						scope:this,
						success : function(fp, action) {
							Ext.ux.Toast.msg('操作信息', '成功保存信息！');
							var SalaryPayoffView = Ext.getCmp('SalaryPayoffView');
							if (SalaryPayoffView != null) {
								SalaryPayoffView.gridPanel.getStore().reload();
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
	}// end of save

});