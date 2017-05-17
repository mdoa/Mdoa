Ext.ns('CartRepairForm');
/**
 * @author
 * @createtime
 * @class CartRepairForm
 * @extends Ext.Window
 * @description 车辆维修详细信息表单
 * @company 宏天软件
 */
CartRepairForm = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		// 调用父类构造
		CartRepairForm.superclass.constructor.call(this, {
					layout : 'fit',
					id : 'CartRepairFormWin',
					title : '车辆维修详细信息',
					iconCls : 'menu-car_repair',
					width : 600,
					height : 345,
					minWidth : 599,
					minHeight : 344,
					items : this.formPanel,
					maximizable : true,
					border : false,
					modal : true,
					plain : true,
					buttonAlign : 'center',
					keys : {
						key : Ext.EventObject.ENTER,
						fn : this.save,
						scope : this
					},
					buttons : [{
								text : '保存',
								iconCls : 'btn-save',
								scope : this,
								handler : this.save
							}, {
								text : '取消',
								iconCls : 'btn-cancel',
								scope : this,
								handler : this.cancel
							}, {
								text : '重置',
								iconCls : 'btn-reset',
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
					bodyStyle : 'padding:5px;',
					defaults : {
						anchor : '96%,96%'
					},
					defaultType : 'textfield',
					items : [{
								name : 'cartRepair.repairId',
								xtype : 'hidden',
								value : this.repairId == null
										? ''
										: this.repairId
							}, {
								xtype : 'hidden',
								name : 'cartRepair.carId'
							}, {
								xtype : 'container',
								layout : 'column',
								layoutConfigs : {
									align : 'middle'
								},
								defaults : {
									margins : '0 2 0 0'
								},
								height : 26,
								items : [{
											xtype : 'label',
											text : '车牌号码:',
											width : 103
										}, {
											columnWidth : .999,
											xtype : 'textfield',
											name : 'car.carNo',
											editable : false,
											readOnly : true,
											allowBlank : false
										}, {
											xtype : 'button',
											iconCls : 'btn-car',
											text : '选择车辆',
											scope : this,
											handler : this.carSelect
										}, {
											xtype : 'button',
											text : '消除记录',
											iconCls : 'reset',
											scope : this,
											handler : this.resetCarSelect
										}]
							}, {
								fieldLabel : '维护日期',
								name : 'cartRepair.repairDate',
								xtype : 'datefield',
								format : 'Y-m-d',
								allowBlank : false,
								editable : false
							}, {
								xtype : 'container',
								layout : 'column',
								layoutConfigs : {
									align : 'middle'
								},
								defaults : {
									margins : '0 2 0 0'
								},
								height : 26,
								items : [{
											xtype : 'label',
											text : '经办人:',
											width : 103
										}, {
											columnWidth : .999,
											xtype : 'textfield',
											name : 'cartRepair.executant',
											editable : false,
											allowBlank : false,
											readOnly : true
										}, {
											xtype : 'button',
											iconCls : 'btn-user-sel',
											text : '选择人员',
											scope : this,
											handler : this.userSelect
										}, {
											xtype : 'button',
											text : '清除纪录',
											iconCls : 'reset',
											scope : this,
											handler : this.resetUserSelect
										}]
							}, {
								fieldLabel : '维修类型',
								name : 'cartRepair.repairType',
								xtype : 'combo',
								mode : 'local',
								allowBlank : false,
								editable : false,
								triggerAction : 'all',
								store : [['1', '保养'], ['2', '维修']]
							}, {
								fieldLabel : '费用',
								name : 'cartRepair.fee',
								xtype : 'numberfield'
							}, {
								fieldLabel : '维护原因',
								name : 'cartRepair.reason',
								allowBlank : false,
								xtype : 'textarea'
							}, {
								fieldLabel : '备注',
								name : 'cartRepair.notes',
								xtype : 'textarea'
							}

					]
				});// end of the formPanel

		// 加载表单对应的数据
		if (this.repairId != null && this.repairId != 'undefined') {
			this.formPanel.loadData({
						url : __ctxPath + '/admin/getCartRepair.do?repairId='
								+ this.repairId,
						root : 'data',
						preName : 'cartRepair'
					});
		};
	},// end of the initUIComponents

	/**
	 * 保存
	 */
	save : function() {
		// 提交
		$postForm({
					formPanel : this.formPanel,
					scope : this,
					url : __ctxPath + '/admin/saveCartRepair.do',
					callback : function(fp, action) {
						if (this.callback) {
							this.callback.call(this.scope);
						}
						this.close();
					}
				});
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
				formPanel.getCmpByName('cartRepair.carId').setValue(id);
				formPanel.getCmpByName('car.carNo').setValue(name);
			}
		}).show();
	},
	/**
	 * 取消车辆选择
	 */
	resetCarSelect : function() {
		this.formPanel.getCmpByName('cartRepair.carId').setValue('');
		this.formPanel.getCmpByName('car.carNo').setValue('');
	},
	/**
	 * 人员选择
	 */
	userSelect : function() {
		var formPanel = this.formPanel;
		new UserDialog({
			scope:this,
			single:true,
			callback : function(id, name) {
				formPanel.getCmpByName('cartRepair.executant')
						.setValue(name);
			}
		}).show();
	},
	/**
	 * 取消人员选择
	 */
	resetUserSelect : function() {
		this.formPanel.getCmpByName('cartRepair.executant').setValue();
	}

});
