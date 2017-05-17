/**
 * @author
 * @createtime
 * @class CarApplyForm
 * @extends Ext.Window
 * @description CarApplyForm表单
 * @company 宏天软件
 */
CarApplyForm = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		// 调用父类构造
		CarApplyForm.superclass.constructor.call(this, {
					layout : 'fit',
					id : 'CarApplyFormWin',
					title : '车辆申请详细信息',
					iconCls : 'menu-car_apply',
					width : 600,
					height : 480,
					minWidth : 599,
					minHeight : 479,
					items : this.formPanel,
					maximizable : true,
					border : false,
					modal : true,
					plain : true,
					buttonAlign : 'center',
					keys : {
						key : Ext.EventObject.ENTER,
						fn : this.submitRecord,
						scope : this
					},
					buttons : [{
								text : '保存草稿',
								iconCls : 'btn-save',
								handler : this.save,
								scope : this
							}, {
								text : '提交审批',
								iconCls : 'btn-ok',
								handler : this.submitRecord,
								scope : this
							}, {
								text : '取消',
								iconCls : 'btn-cancel',
								scope : this,
								handler : this.cancel
							}, {
								xtype : 'button',
								text : '清空',
								iconCls : 'reset',
								scope : this,
								handler : this.reset
							}]
				});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		// 表单
		this.formPanel = new Ext.FormPanel({
					layout : 'form',
					frame : false,
					border : true,
					bodyStyle : 'padding-top:5px;padding-left:5px;',
					defaults : {
						anchor : '96%,96%'
					},
					defaultType : 'textfield',
					items : [{
								name : 'carApply.applyId',
								xtype : 'hidden',
								value : this.applyId == null
										? ''
										: this.applyId
							}, {
								xtype : 'hidden',
								name : 'carApply.carId'
							}, {
								xtype : 'hidden',
								name : 'carApply.userId'
							}, {
								xtype : 'container',
								fieldLabel : '车牌号码',
								layout : 'column',
								style : 'padding-left:0px;margin-bottom:4px;',
								items : [{
											columnWidth : .999,
											xtype : 'textfield',
											name : 'car.carNo',
											allowBlank : false,
											editable : false,
											readOnly : true
										}, {
											xtype : 'button',
											iconCls : 'btn-car',
											text : '选择车辆',
											scope : this,
											handler : this.carSelect
										}, {
											xtype : 'button',
											text : '清除记录',
											iconCls : 'reset',
											scope : this,
											handler : this.resetCarSelect
										}]
							}, {
								xtype : 'container',
								fieldLabel : '用车部门',
								style : 'padding-left:0px;margin-bottom:4px;',
								layout : 'column',
								items : [{
											columnWidth : .999,
											xtype : 'textfield',
											name : 'carApply.department',
											allowBlank : false,
											editable : false,
											readOnly : true
										}, {
											xtype : 'button',
											iconCls : 'btn-dep-sel',
											text : '选择部门',
											scope : this,
											handler : this.departmentSelect
										}, {
											xtype : 'button',
											text : '清除记录',
											iconCls : 'reset',
											scope : this,
											handler : this.resetDepartmentSelect
										}]
							}, {
								xtype : 'container',
								fieldLabel : '用车人',
								style : 'padding-left:0px;margin-bottom:4px;',
								layout : 'column',
								items : [{
											columnWidth : .999,
											xtype : 'textfield',
											name : 'carApply.userFullname',
											allowBlank : false,
											editable : false,
											readOnly : true
										}, {
											xtype : 'button',
											iconCls : 'btn-user-sel',
											text : '选择人员',
											scope : this,
											handler : this.carUserSelect
										}, {
											xtype : 'button',
											text : '清除记录',
											iconCls : 'reset',
											scope : this,
											handler : this.resetCarUserSelect
										}]
							}, {
								xtype : 'container',
								fieldLabel : '申请人',
								layout : 'column',
								style : 'padding-left:0px;margin-bottom:4px;',
								items : [{
											columnWidth : .999,
											xtype : 'textfield',
											name : 'carApply.proposer',
											editable : false,
											allowBlank : false,
											readOnly : true
										}, {
											xtype : 'button',
											iconCls : 'btn-user-sel',
											text : '选择人员',
											scope : this,
											handler : this.userApplySelect
										}, {
											xtype : 'button',
											text : '清除记录',
											iconCls : 'reset',
											scope : this,
											handler : this.resetUserApplySelect
										}]
							}, {
								fieldLabel : '申请时间',
								name : 'carApply.applyDate',
								xtype : 'datefield',
								format : 'Y-m-d H:i:s',
								allowBlank : false,
								editable : false
							}, {
								fieldLabel : '原因',
								name : 'carApply.reason',
								allowBlank : false,
								xtype : 'textarea'
							}, {
								fieldLabel : '审批状态',
								name : 'carApply.approvalStatus',
								value : 0,
								xtype : 'hidden'
							}, {
								fieldLabel : '开始时间',
								name : 'carApply.startTime',
								xtype : 'datetimefield',
								format : 'Y-m-d H:i:s',
								allowBlank : false,
								editable : false
							}, {
								fieldLabel : '结束时间',
								name : 'carApply.endTime',
								xtype : 'datetimefield',
								format : 'Y-m-d H:i:s'
							}, {
								fieldLabel : '里程',
								name : 'carApply.mileage',
								xtype : 'numberfield'
							}, {
								fieldLabel : '油耗',
								name : 'carApply.oilUse',
								xtype : 'numberfield'
							}, {
								fieldLabel : '备注',
								name : 'carApply.notes',
								xtype : 'textarea'
							}]
				});// end of the formPanel

		// 加载表单对应的数据
		if (this.applyId != null && this.applyId != 'undefined') {
			this.formPanel.loadData({
						url : __ctxPath + '/admin/getCarApply.do?applyId='
								+ this.applyId,
						root : 'data',
						preName : 'carApply'
					});
		};
	},// end of the initUIComponents

	/**
	 * 保存
	 */
	save : function() {
		var formPanel = this.formPanel;
		// 验证
		if (formPanel.getForm().isValid()) {
			var st = formPanel.getCmpByName('carApply.startTime').getValue();
			var et = formPanel.getCmpByName('carApply.endTime').getValue();
			var sd = Date.parse(st);
			var ed = Date.parse(et);
			if (sd > ed) {
				Ext.ux.Toast.msg('操作信息', '申请开始时间大于结束时间,不能保存!');
				return;
			};

			// 提交
			$postForm({
						formPanel : formPanel,
						scope : this,
						url : __ctxPath + '/admin/saveCarApply.do',
						callback : function(fp, action) {
							if (this.callback) {
								this.callback.call(this.scope);
							}
							this.close();
						}
					});
		}
	},
	/**
	 * 提交
	 */
	submitRecord : function() {
		this.formPanel.getCmpByName('carApply.approvalStatus').setValue(1);
		this.save();
	},
	/**
	 * 重置
	 * 
	 * @param {}
	 *            formPanel
	 */
	reset : function() {
		this.formPanel.getForm().reset();
	},
	/**
	 * 取消
	 * 
	 * @param {}
	 *            window
	 */
	cancel : function() {
		this.close();
	},
	/**
	 * 车辆选择
	 */
	carSelect : function() {
		var formPanel = this.formPanel;
		new CarDialog({
					single : true,
					scope : this,
					callback : function(id, name) {
						formPanel.getCmpByName('car.carNo').setValue(name);
						formPanel.getCmpByName('carApply.carId').setValue(id);
					}
				}).show();
	},
	/**
	 * 取消车辆选择
	 */
	resetCarSelect : function() {
		this.formPanel.getCmpByName('car.carNo').setValue('');
		this.formPanel.getCmpByName('carApply.carId').setValue('');
	},
	/**
	 * 用车部门选择
	 */
	departmentSelect : function() {
		var formPanel = this.formPanel;
		new DepDialog({
					callback : function(id, name) {
						formPanel.getCmpByName('carApply.department')
								.setValue(name);
					}
				}).show();
	},
	/**
	 * 取消用车部门选择
	 */
	resetDepartmentSelect : function() {
		this.formPanel.getCmpByName('carApply.department').setValue('');
	},
	/**
	 * 用车人员选择
	 */
	carUserSelect : function() {
		var formPanel = this.formPanel;
		new UserDialog({
					callback : function(id, name) {
						formPanel.getCmpByName('carApply.userFullname')
								.setValue(name);
					}
				}).show();
	},
	/**
	 * 取消用车人员选择
	 */
	resetCarUserSelect : function() {
		this.formPanel.getCmpByName('carApply.userFullname').setValue('');
	},
	/**
	 * 申请人员选择
	 */
	userApplySelect : function() {
		var formPanel = this.formPanel;
		new UserDialog({
					single : true,
					scope : this,
					callback : function(id, name) {
						formPanel.getCmpByName('carApply.proposer')
								.setValue(name);
						formPanel.getCmpByName('carApply.userId').setValue(id);
					}
				}).show();
	},
	/**
	 * 取消申请人员选择
	 */
	resetUserApplySelect : function() {
		this.formPanel.getCmpByName('carApply.proposer').setValue('');
	}

});
