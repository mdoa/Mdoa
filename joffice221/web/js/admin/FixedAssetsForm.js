Ext.ns('FixedAssetsForm');
/**
 * @author
 * @createtime
 * @class FixedAssetsForm
 * @extends Ext.Window
 * @description FixedAssetsForm表单
 * @company 宏天软件
 */
FixedAssetsForm = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		// 调用父类构造
		FixedAssetsForm.superclass.constructor.call(this, {
					id : 'FixedAssetsFormWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					width : 520,
					height : 450,
					minWidth : 519,
					minHeight : 449,
					maximizable : true,
					border : false,
					modal : true,
					plain : true,
					title : '固定资产详细信息(*为必填)',
					iconCls : 'menu-assets',
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
		var x = '<font style="color: red;">*</font>';
		// 表单面板
		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			frame : false,
			bodyStyle : 'padding:5px;',
			defaultType : 'textfield',
			items : [{
						name : 'fixedAssets.assetsId',
						xtype : 'hidden',
						value : this.assetsId == null ? '' : this.assetsId
					}, {
						name : 'fixedAssets.depreRate',
						xtype : 'hidden'
					}, {
						name : 'fixedAssets.depreType.calMethod',
						xtype : 'hidden'

					}, {
						fieldLabel : '资产名称' + x,
						name : 'fixedAssets.assetsName',
						anchor : '99%',
						allowBlank : false
					}, {
						xtype : 'container',
						layout : 'column',
						fieldLabel : '资产编号' + x,
						items : [{
									columnWidth : .99,
									xtype : 'textfield',
									name : 'fixedAssets.assetsNo',
									allowBlank : false,
									readOnly : true
								}, {
									xtype : 'button',
									iconCls : 'btn-system-setting',
									text : '系统生成',
									hidden : Ext.isEmpty(this.assetsId)
											? false
											: true,
									scope : this,
									handler : this.genNumber
								}]
					}, {
						fieldLabel : '资产类别' + x,
						hiddenName : 'fixedAssets.assetsType.assetsTypeId',
						name : 'fixedAssets.assetsType.typeName',
						xtype : 'combo',
						mode : 'local',
						anchor : '99%',
						allowBlank : false,
						editable : false,
						valueField : 'id',
						displayField : 'name',
						triggerAction : 'all',
						store : new Ext.data.SimpleStore({
									autoLoad : true,
									url : __ctxPath
											+ '/admin/comboxAssetsType.do',
									fields : ['id', 'name']
								})
					}, {
						fieldLabel : '置办日期' + x,
						name : 'fixedAssets.buyDate',
						format : 'Y-m-d',
						xtype : 'datefield',
						allowBlank : false,
						editable : false,
						anchor : '99%'
					}, {
						xtype : 'container',
						layout : 'column',
						fieldLabel : '所属部门' + x,
						layoutConfigs : {
							align : 'middle'
						},
						defaults : {
							margins : '0 2 0 0'
						},
						items : [{
									columnWidth : .99,
									xtype : 'textfield',
									name : 'fixedAssets.beDep',
									allowBlank : false,
									readOnly : true
								}, {
									xtype : 'button',
									iconCls : 'btn-dep-sel',
									text : '选择部门',
									scope : this,
									handler : this.depSelect
								}, {
									xtype : 'button',
									text : '清除记录',
									iconCls : 'reset',
									scope : this,
									handler : this.resetDepSelect
								}]
					}, {
						xtype : 'container',
						layout : 'column',
						fieldLabel : '保管人',
						height : 28,
						layoutConfigs : {
							align : 'middle'
						},
						defaults : {
							margins : '0 2 0 0'
						},
						items : [{
									columnWidth : .99,
									xtype : 'textfield',
									name : 'fixedAssets.custodian',
									readOnly : true
								}, {
									xtype : 'button',
									iconCls : 'btn-user-sel',
									text : '选择人员',
									scope : this,
									handler : this.userSelect
								}, {
									xtype : 'button',
									text : '清除记录',
									iconCls : 'reset',
									scope : this,
									handler : this.resetUserSelect
								}]
					}, {
						xtype : 'tabpanel',
						height : 220,
						plain : true,
						activeTab : 0,
						deferredRender : false,
						items : [{
							layout : 'form',
							title : '折旧信息',
							frame : false,
							bodyStyle : 'padding:4px 4px 4px 4px',
							height : 190,
							defaults : {
								anchor : '98%,98%'
							},
							defaultType : 'textfield',
							items : [{
								fieldLabel : '折旧类型' + x,
								hiddenName : 'fixedAssets.depreType.depreTypeId',
								name : 'fixedAssets.depreType.typeName',
								xtype : 'combo',
								mode : 'local',
								allowBlank : false,
								editable : false,
								valueField : 'id',
								displayField : 'name',
								triggerAction : 'all',
								store : new Ext.data.SimpleStore({
											autoLoad : true,
											url : __ctxPath
													+ '/admin/comboxDepreType.do',
											fields : ['id', 'name', 'method']
										}),
								listeners : {
									scope : this,
									select : this.depreTypeSelect
								}
							}, {
								fieldLabel : '开始折旧日期',
								name : 'fixedAssets.startDepre',
								format : 'Y-m-d',
								xtype : 'datefield',
								editable : false
							}, {
								xtype : 'container',
								layout : 'column',
								fieldLabel : '预计使用年限' + x,
								height : 28,
								layoutConfigs : {
									align : 'middle'
								},
								defaults : {
									margins : '0 2 0 0'
								},
								name : 'intendTermContainer',
								items : [{
											columnWidth : .99,
											xtype : 'numberfield',
											name : 'fixedAssets.intendTerm',
											allowNegative : false,
											allowDecimals : false
										}, {
											xtype : 'label',
											text : '年',
											width : 10
										}]
							}, {
								layout : 'form',
								xtype : 'container',
								name : 'WorkGrossPanel',
								hidden : true,
								items : [{
									xtype : 'container',
									layout : 'hbox',
									height : 28,
									layoutConfigs : {
										align : 'middle'
									},
									defaults : {
										margins : '0 2 0 0'
									},
									items : [{
												xtype : 'label',
												text : '预使用总工作量*:',
												width : 103
											}, {
												xtype : 'numberfield',
												name : 'fixedAssets.intendWorkGross',
												allowNegative : false
											}, {
												xtype : 'label',
												text : '单位*:'
											}, {
												xtype : 'textfield',
												name : 'fixedAssets.workGrossUnit',
												width : 30
											}]

								}, {
									fieldLabel : '默认周期工作量',
									xtype : 'numberfield',
									allowNegative : false,
									name : 'fixedAssets.defPerWorkGross'
								}]
							}, {
								fieldLabel : '残值率(%)' + x,
								xtype : 'numberfield',
								name : 'fixedAssets.remainValRate',
								allowNegative : false,
								decimalPrecision : 2,
								allowBlank : false
							}, {
								fieldLabel : '资产值' + x,
								name : 'fixedAssets.assetValue',
								allowBlank : false,
								allowNegative : false,
								decimalPrecision : 2,
								xtype : 'numberfield'
							}, {
								fieldLabel : '资产当前值' + x,
								name : 'fixedAssets.assetCurValue',
								allowBlank : false,
								allowNegative : false,
								decimalPrecision : 2,
								xtype : 'numberfield'
							}]

						}, {
							layout : 'form',
							title : '扩展信息',
							width : 300,
							height : 190,
							bodyStyle : 'padding:4px 4px 4px 4px',
							defaults : {
								anchor : '98%,98%'
							},
							defaultType : 'textfield',
							items : [{
										fieldLabel : '规格型号',
										name : 'fixedAssets.model'
									}, {
										fieldLabel : '制造厂商',
										name : 'fixedAssets.manufacturer'
									}, {
										fieldLabel : '出厂日期',
										name : 'fixedAssets.manuDate',
										format : 'Y-m-d',
										xtype : 'datefield'
									}, {
										fieldLabel : '备注',
										name : 'fixedAssets.notes',
										height : 100,
										xtype : 'textarea'
									}]
						}]

					}

			]
		});
		// 加载表单对应的数据
		if (this.assetsId != null && this.assetsId != 'undefined') {
			this.formPanel.loadData({
						url : __ctxPath + '/admin/getFixedAssets.do?assetsId='
								+ this.assetsId,
						root : 'data',
						preName : 'fixedAssets',
						scope : this,
						success : function(response, action) {
							var result = Ext.util.JSON
									.decode(response.responseText);
							var method = result.data.depreType.calMethod;
							this.setDepreTypeSelect(this.formPanel, method);
						},
						failure : function(form, action) {
							Ext.ux.Toast.msg('编辑', '载入失败');
						}
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
		var method = formPanel.getCmpByName('fixedAssets.depreType.calMethod')
				.getValue();
		var buyDate = formPanel.getCmpByName('fixedAssets.buyDate').getValue();
		var startDepre = formPanel.getCmpByName('fixedAssets.startDepre')
				.getValue();
		var bd = Date.parse(buyDate);
		var sd = Date.parse(startDepre);
		if (bd <= sd) {
			Ext.ux.Toast.msg('操作提示', '开始折旧日期大于或等于置办日期，请重新输入！');
			return;
		}
		var flag = false;
		if (!Ext.isEmpty(method)) {
			if (method == 2) {
				var intendWorkGross = formPanel
						.getCmpByName('fixedAssets.intendWorkGross').getValue();
				var workGrossUnit = formPanel
						.getCmpByName('fixedAssets.workGrossUnit').getValue();
				if (!Ext.isEmpty(intendWorkGross)
						&& !Ext.isEmpty(workGrossUnit)) {
					flag = true;
				} else {
					Ext.ux.Toast.msg('操作提示', '预使用总工作量、单位不能为空，请输入！');
				}
			} else {
				var intendTerm = formPanel
						.getCmpByName('fixedAssets.intendTerm').getValue();
				if (!Ext.isEmpty(intendTerm)) {
					flag = true;
				} else {
					Ext.ux.Toast.msg('操作提示', '预计使用年限不能为空，请输入！');
				}
			}

			if (formPanel.getForm().isValid() && flag) {
				// 提交
				$postForm({
							formPanel : formPanel,
							scope : this,
							url : __ctxPath + '/admin/saveFixedAssets.do',
							callback : function(fp, action) {
								if (this.callback) {
									this.callback.call(this.scope);
								}
								this.close();
							}
						});
			}
		}
	},
	/**
	 * 部门选择
	 */
	depSelect : function() {
		new DepDialog({
			scope : this,
			single : true,
			callback : function(id, name) {
				this.formPanel.getCmpByName('fixedAssets.beDep').setValue(name);
			}
		}).show();
	},
	/**
	 * 取消部门选择
	 */
	resetDepSelect : function() {
		this.formPanel.getCmpByName('fixedAssets.beDep').setValue('');
	},
	/**
	 * 人员选择
	 */
	userSelect : function() {
		new UserDialog({
					single : true,
					scope : this,
					callback : function(id, name) {
						this.formPanel.getCmpByName('fixedAssets.custodian')
								.setValue(name);
					}
				}).show();
	},
	/**
	 * 取消人员选择
	 */
	resetUserSelect : function() {
		this.formPanel.getCmpByName('fixedAssets.custodian').setValue('');
	},
	/**
	 * 折旧类型选择
	 * 
	 * @param {}
	 *            combo
	 * @param {}
	 *            record
	 * @param {}
	 *            index
	 */
	depreTypeSelect : function(combo, record, index) {
		this.setDepreTypeSelect(this.formPanel, record.data.method);

	},
	/**
	 * 设置折旧类型选择
	 * 
	 * @param {}
	 *            method
	 */
	setDepreTypeSelect : function(formPanel, method) {
		if (method == '2') {// 2为按工作量折算
			formPanel.getCmpByName('fixedAssets.depreType.calMethod')
					.setValue(method);
			formPanel.getCmpByName('WorkGrossPanel').show();
			formPanel.getCmpByName('intendTermContainer').hide();
			formPanel.getCmpByName('fixedAssets.intendTerm').setValue('');
		} else {
			formPanel.getCmpByName('fixedAssets.depreType.calMethod')
					.setValue(method);
			formPanel.getCmpByName('intendTermContainer').show();
			formPanel.getCmpByName('WorkGrossPanel').hide();
			formPanel.getCmpByName('fixedAssets.intendWorkGross').setValue('');
			formPanel.getCmpByName('fixedAssets.workGrossUnit').setValue('');
			formPanel.getCmpByName('fixedAssets.defPerWorkGross').setValue('');
		}
	},
	/**
	 * 生成编码
	 */
	genNumber : function() {
		$genNumber({
					scope : this,
					alias : 'FixedAssets',
					callback : function(number) {
						this.formPanel.getCmpByName('fixedAssets.assetsNo')
								.setValue(number);
					}
				});
	}
});