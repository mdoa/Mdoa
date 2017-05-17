
DutyRegisterForm = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		// 调用父类构造
		DutyRegisterForm.superclass.constructor.call(this, {
					id : 'DutyRegisterFormWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					width : 500,
					height : 260,
					maximizable : true,
					title : '补签到、签退',
					iconCls : 'menu-signInOff',
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
		//表单
		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			bodyStyle : 'padding:5px;',
			border : false,
			autoScroll : true,
			defaults : {
				width : 400,
				anchor : '98%,98%'
			},
			defaultType : 'textfield',
			items : [{
						name : 'dutyRegister.registerId',
						xtype : 'hidden',
						value : this.registerId == null ? '' : this.registerId
					}, {
						fieldLabel : '登记时间',
						name : 'registerDate',
						xtype : 'datetimefield',
						format : 'Y-m-d H:i:s',
						allowBlank : false,
						maxValue : new Date()
					}, {
						fieldLabel : '班次',
						name : 'sectionName',
						hiddenName : 'sectionId',
						xtype : 'combo',
						allowBlank : false,
						editable : false,
						lazyInit : false,
						allowBlank : false,
						triggerAction : 'all',
						store : new Ext.data.SimpleStore({
									autoLoad : true,
									url : __ctxPath
											+ '/personal/comboDutySection.do',
									fields : ['sectionId', 'sectionName']
								}),
						displayField : 'sectionName',
						valueField : 'sectionId'
					}, {
						xtype : 'radiogroup',
						fieldLabel : '签到类型',
						autoHeight : true,
						columns : 2,
						items : [{
									boxLabel : '上班',
									name : 'inOffFlag',
									inputValue : 1,
									id : 'inOffFlag1',
									checked : true
								}, {
									boxLabel : '下班',
									name : 'inOffFlag',
									inputValue : 2,
									id : 'inOffFlag2'
								}]
					}, {
						xtype : 'container',
						layout : 'column',
						fieldLabel : '登记人',
						border : false,
						items : [{
									columnWidth : .999,
									name : 'dutyRegister.fullname',
									xtype : 'textarea',
									allowBlank : false,
									height : 45
								}, {
									xtype : 'hidden',
									name : 'userIds'
								}, {
									layout : 'form',
									defaultType : 'button',
									border : false,
									items : [{
										width : 70,
										text : '选择',
										iconCls : 'btn-user-sel',
										scope:this,
										handler : this.userSelector
									}, {
										width : 70,
										iconCls : 'reset',
										text : '清空',
										scope:this,
										handler : this.resetUserSelect
									}]
								}]
					}]
		});
		// 加载表单对应的数据
		if (this.registerId != null && this.registerId != 'undefined') {
			this.formPanel.loadData({
						url : __ctxPath + '/personal/getDutyRegister.do?registerId='+ this.registerId,
						root : 'data',
						preName : 'dutyRegister'
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
		if (formPanel.getForm().isValid()) {
			$postForm({
						formPanel : this.formPanel,
						scope : this,
						url : __ctxPath + '/personal/saveDutyRegister.do',
						callback : function(fp, action) {
							if (this.callback) {
								this.callback.call(this.scope);
							}
							this.close();
						}
					});
		}// end of save
	},
	/**
	 * 登记人员选择
	 */
	userSelector : function() {
		var formPanel=this.formPanel;
		new UserDialog({callback:function(ids, names) {
		  formPanel.getCmpByName('dutyRegister.fullname').setValue(names);
	      formPanel.getCmpByName('userIds').setValue(ids);
		}},false).show();//true表示单选
	},
	/**
	 * 取消登记人员选择
	 */
	resetUserSelect : function() {
		var formPanel = this.formPanel;
		formPanel.getCmpByName('dutyRegister.fullname').setValue('');
		formPanel.getCmpByName('userIds').setValue('');
	}
});