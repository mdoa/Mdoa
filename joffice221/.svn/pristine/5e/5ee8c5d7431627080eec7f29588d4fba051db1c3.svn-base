/**
 * 外出单详细信息
 * @class ErrandsRegisterOutForm
 * @extends Ext.Window
 */
ErrandsRegisterOutForm = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		// 调用父类构造
		ErrandsRegisterOutForm.superclass.constructor.call(this, {
					id : 'ErrandsRegisterOutFormWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					width : 400,
					height : 250,
					maximizable : true,
					title : '外出单详细信息',
					iconCls : 'menu-errands',
					buttonAlign : 'center',
					buttons : [{
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
							}]
				});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		//表单面板
		this.formPanel = new Ext.FormPanel({
					layout : 'form',
					bodyStyle : 'padding:10px',
					border : false,
					autoScroll : true,
					id : 'ErrandsRegisterOutForm',
					defaults : {
						anchor : '96%,96%'
					},
					defaultType : 'textfield',
					items : [{
								name : 'errandsRegister.dateId',
								xtype : 'hidden',
								value : this.dateId == null ? '' : this.dateId
							}, {
								xtype : 'hidden',
								name : 'errandsRegister.userId'
							}, {
								xtype : 'hidden',
								name : 'errandsRegister.status',
								id : 'status'
							}, {
								xtype : 'hidden',
								name : 'errandsRegister.approvalOption'
							}, {
								xtype:'hidden',
								name : 'errandsRegister.approvalName'
							}, {
								name : 'errandsRegister.flag',
								xtype : 'hidden',
								value : 2
							}, {
								fieldLabel : '描述',
								xtype : 'textarea',
								name : 'errandsRegister.descp',
								allowBlank : false
							}, {
								fieldLabel : '开始时间',
								name : 'errandsRegister.startTime',
								xtype : 'datetimefield',
								format : 'Y-m-d H:i:s',
								allowBlank : false
							}, {
								fieldLabel : '结束时间',
								name : 'errandsRegister.endTime',
								xtype : 'datetimefield',
								format : 'Y-m-d H:i:s',
								allowBlank : false
							}, {
								fieldLabel : '审批人',
								hiddenName : 'errandsRegister.approvalId', 
								name : 'errandsRegister.approvalName',
								emptyText : '请选择审批人',
								xtype : 'combo',
								mode : 'local',
								anchor : '98%',
								allowBlank : false,
								editable : false,
								valueField : 'id',
								displayField : 'name',
								triggerAction : 'all',
								store : new Ext.data.SimpleStore({
											autoLoad : true,
											url : __ctxPath
													+ '/system/upUserAppUser.do',
											fields : ['id', 'name']
										}),
								listeners : {
									scope : this,
									select : function(combo, record, index) {//选择审批人赋值
										this.formPanel.getCmpByName('errandsRegister.approvalName').setValue(record.data.name);
									}
								}
							}]
				});
		// 加载表单对应的数据
		if (this.dateId != null && this.dateId != 'undefined') {
			this.formPanel.loadData({
						url : __ctxPath
								+ '/personal/getErrandsRegister.do?dateId='
								+ this.dateId,
						root : 'data',
						preName : 'errandsRegister'
					});
		};
	},// end of the initcomponents

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
	 * 保存记录
	 */
	save : function() {
		var formPanel = this.formPanel;
		//验证外出开始时间大于结束进间
		var st = formPanel.getCmpByName('errandsRegister.startTime').getValue();
		var et = formPanel.getCmpByName('errandsRegister.endTime').getValue();
		var sd = Date.parse(st);
		var ed = Date.parse(et);
		if (sd > ed) {
			Ext.ux.Toast.msg('操作信息', '外出开始时间大于结束时间,不能保存!');
			return;
		};
		//提交
		$postForm({
					formPanel : formPanel,
					scope : this,
					url : __ctxPath + '/personal/saveErrandsRegister.do',
					callback : function(fp, action) {
						if (this.callback) {
							this.callback.call(this.scope);
						}
						this.close();
					}
				});
	}
});