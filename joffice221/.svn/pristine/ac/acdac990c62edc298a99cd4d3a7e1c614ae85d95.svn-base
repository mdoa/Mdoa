DutyForm = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		// 调用父类构造
		DutyForm.superclass.constructor.call(this, {
					id : 'DutyFormWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					width : 550,
					height : 260,
					maximizable : true,
					title : '排班详细信息',
					iconCls : 'menu-duty',
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
		// 表单面板
		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			bodyStyle : 'padding:10px',
			border : false,
			autoScroll : true,
			defaults : {
				anchor : '98%,98%'
			},
			defaultType : 'textfield',
			items : [{
						name : 'duty.dutyId',
						xtype : 'hidden',
						value : this.dutyId == null ? '' : this.dutyId
					},{
						fieldLabel : '班制',
						xtype : 'combo',
						hiddenName : 'duty.dutySystem.systemId',
						name : 'duty.dutySystem.systemName',
						editable : false,
						triggerAction : 'all',
						emptyText : '请选择班制...',
						store : new Ext.data.ArrayStore({
									autoLoad : true,
									url : __ctxPath
											+ '/personal/comboDutySystem.do',
									fields : ['systemId', 'systemName','systemDesc']
								}),
						tpl : '<tpl for="."><div ext:qtip=" {systemDesc}" class="x-combo-list-item">{systemName}</div></tpl>',
						displayField : 'systemName',
						valueField : 'systemId'			

					}, {
						fieldLabel : '开始时间',
						name : 'duty.startTime',
						format : 'Y-m-d',
						id : 'dutyStartTime',
						vtype : 'daterange',
						endDateField : 'dutyEndTime',
						allowBlank : false,
						xtype : 'datefield'
					}, {
						fieldLabel : '结束时间',
						name : 'duty.endTime',
						format : 'Y-m-d',
						id : 'dutyEndTime',
						vtype : 'daterange',
						startDateField : 'dutyStartTime',
						xtype : 'datefield',
						allowBlank : false
					}, {
						xtype : 'container',
						layout : 'column',
						fieldLabel : '员工',
						items : [{
									name : 'duty.fullname',
									xtype : 'textarea',
									allowBlank : false,
									readOnly : true,
									columnWidth : .999,
									height : 45
								}, {
									layout : 'form',
									defaultType : 'button',
									border : false,
									items : [{
												text : '选择',
												name : 'userSelect',
												iconCls : 'btn-user-sel',
												width : 70,
												scope : this,
												// 人员选择器
												handler : this.userSelector
											}, {
												width : 70,
												text : '清空',
												iconCls : 'reset',
												scope : this,
												handler : this.resetUserSelect
											}]
								}, {
									name : 'duty.userId',
									xtype : 'hidden'
								}]
					}]
		});
		// 加载表单对应的数据
		if (!Ext.isEmpty(this.dutyId)) {
			this.formPanel.loadData({
						url : __ctxPath + '/personal/getDuty.do?dutyId='
								+ this.dutyId,
						root : 'data',
						preName : 'duty'
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
		// 提交
		$postForm({
					formPanel : this.formPanel,
					scope : this,
					url : __ctxPath + '/personal/saveDuty.do',
					callback : function(fp, action) {
						if (this.callback) {
							this.callback.call(this.scope);
						}
						this.close();
					}

				});
	},// end of save
	/**
	 * 人员选择
	 */
	userSelector : function() {
		// 当为更新时，用户选择器为单选，添加时，可为选择多个用户
		var formPanel = this.formPanel;
		var userId = formPanel.getCmpByName('duty.userId')
		var useName = formPanel.getCmpByName('duty.fullname')
		new UserDialog({
					userIds : userId.getValue(),
					userName : useName.getValue(),
					scope : this,
					callback : function(ids, names) {
						userId.setValue(ids);
						useName.setValue(names);
					}
				}).show();
	},
	/**
	 * 取消人员选择
	 */
	resetUserSelect : function() {
		var formPanel = this.formPanel;
		formPanel.getCmpByName('duty.fullname').setValue('');
		formPanel.getCmpByName('duty.userId').setValue('');
	}
});