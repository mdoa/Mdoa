Ext.ns('ReportParamForm');
/**
 * @author
 * @createtime
 * @class ReportParamForm
 * @extends Ext.Window
 * @description 报表参数设置详细信息
 * @company 宏天软件
 */
ReportParamForm = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		// 必须先初始化组件
		this.initUIComponents();
		ReportParamForm.superclass.constructor.call(this, {
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					id : 'ReportParamFormWin',
					title : this.name == null ? '详细信息' : this.name + '详细信息',
					width : 500,
					height : 230,
					buttonAlign : 'center'
				});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		// 详细信息表单
		this.formPanel = new Ext.FormPanel({
					layout : 'form',
					border : false,
					bodyStyle : 'padding:5px;',
					defaults : {
						anchor : '96%,96%'
					},
					defaultType : 'textfield',
					items : [{
								name : 'reportParam.paramId',
								xtype : 'hidden',
								value : this.paramId == null
										? ''
										: this.paramId
							}, {
								xtype : 'hidden',
								name : 'reportParam.reportId',
								value : this.reportId
							}, {
								xtype : 'hidden',
								name : 'reportParam.paramTypeStr'
							}, {
								fieldLabel : '参数名称',
								name : 'reportParam.paramName',
								allowBlank : false
							}, {
								fieldLabel : '参数Key',
								name : 'reportParam.paramKey',
								allowBlank : false
							}, {
								fieldLabel : '类型',
								hiddenName : 'reportParam.paramType',
								xtype : 'combo',
								mode : 'local',
								allowBlank : false,
								editable : false,
								triggerAction : 'all',
								store : [
										['textfield', '文本输入框(textfield)'],
										['datefield', '日期输入框(datefield)'],
										['datetimefield',
												'时间输入框(datetimefield)'],
										['numberfield', '数字输入框(numberfield)'],
										['combo', '下拉框(combo)'],
										['diccombo', '数据字典(diccombo)']],
								listeners : {
									scope : this,
									'select' : this.paramTypeSelect
								}
							}, {
								fieldLabel : '序号',
								name : 'reportParam.sn',
								xtype : 'numberfield',
								allowBlank : false
							}, {
								fieldLabel : '缺省值',
								id : 'defaultVal',
								name : 'reportParam.defaultVal',
								allowBlank : true
							}],
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
							}]
				});
		// 加载数据
		if (!Ext.isEmpty(this.paramId)) {
			this.formPanel.loadData({
				url : __ctxPath + '/system/getReportParam.do?paramId='
						+ this.paramId,
				preName : 'reportParam',
				root : 'data',
				scope : this,
				success : function(response, options) {
					var data = Ext.util.JSON.decode(response.responseText).data;
					var xtype = data.paramType;
					var defaultVal = data.defaultVal;
					var paramTypeStr = data.paramTypeStr;
					var formPanel = this.formPanel;
					if (!Ext.isEmpty(paramTypeStr)) {
						formPanel.remove('defaultVal');
						switch (xtype) {
							case 'combo' :
								var combo = new Ext.form.ComboBox(Ext.decode(paramTypeStr));
								combo.setValue(defaultVal);
								formPanel.add(combo);
								break;
							case 'diccombo' :
								var diccombo = new DicCombo(Ext
										.decode(paramTypeStr));
								diccombo.setValue(defaultVal);
								formPanel.add(diccombo);
								break;
							default :
								var df = Ext.decode(paramTypeStr);
								Ext.apply(df, {
											value : defaultVal
										});

								formPanel.add(df);
						}
						formPanel.doLayout(true);
					}
				},
				failure : function(form, action) {
					// Ext.Msg.alert('编辑', '载入失败');
				}
			});
		}

	},
	/**
	 * 参数类型选择
	 */
	paramTypeSelect : function(combo, record, index) {
		var xtype = combo.value;
		var formPanel = this.formPanel;
		formPanel.remove('defaultVal');
		var paramTypeStr = {};
		switch (xtype) {
			case 'datefield' :// 日期
				Ext.apply(paramTypeStr, {
							fieldLabel : '缺省值',
							name : 'reportParam.defaultVal',
							id : 'defaultVal',
							xtype : 'datefield',
							format : 'Y-m-d',
							allowBlank : true
						});
				formPanel.getCmpByName('reportParam.paramTypeStr').setValue(Ext.encode(paramTypeStr));
				formPanel.add(paramTypeStr);
				break;
			case 'datetimefield' :// 时间
				Ext.apply(paramTypeStr, {
							fieldLabel : '缺省值',
							name : 'reportParam.defaultVal',
							id : 'defaultVal',
							xtype : 'datetimefield',
							format : 'Y-m-d H:i:s',
							allowBlank : true

						});
				formPanel.getCmpByName('reportParam.paramTypeStr').setValue(Ext
						.encode(paramTypeStr));
				formPanel.add(paramTypeStr);
				break;
			case 'numberfield' :// 数字
				Ext.apply(paramTypeStr, {
							fieldLabel : '缺省值',
							name : 'reportParam.defaultVal',
							id : 'defaultVal',
							xtype : 'numberfield',
							allowBlank : true
						});
				formPanel.getCmpByName('reportParam.paramTypeStr').setValue(Ext
						.encode(paramTypeStr));
				formPanel.add(paramTypeStr);
				break;
			case 'combo' :// 下拉
				this.comboType.call(this);
				break;
			case 'diccombo' :// 数据字典
				this.diccomboType.call(this);
				break;
			default :// 文本
				Ext.apply(paramTypeStr, {
							fieldLabel : '缺省值',
							name : 'reportParam.defaultVal',
							id : 'defaultVal',
							xtype : 'textfield',
							allowBlank : true
						});
				formPanel.getCmpByName('reportParam.paramTypeStr').setValue(Ext.encode(paramTypeStr));
				formPanel.add(paramTypeStr);
		}

		formPanel.doLayout(true);
	},
	/**
	 * 下拉类型
	 */
	comboType : function() {
		this.comboContainer = new Ext.Panel({
					layout : 'form',
					buttonAlign : 'center',
					buttons : [{
								text : '确定',
								iconCls : 'btn-save',
								scope : this,
								handler : this.comboConfirm
							}, {
								text : '取消',
								iconCls : 'btn-cancel',
								scope : this,
								handler : this.comboCancel
							}]
				});

		/**
		 * 选择数据源
		 */
		this.comboSelectForm = new Ext.FormPanel({
					layout : 'form',
					frame : true,
					defaults : {
						anchor : '98%,98%'
					},
					modal : true,
					defaultType : 'textfield',
					items : [{
						fieldLabel : '数据来源',
						xtype : 'combo',
						store : new Ext.data.ArrayStore({
									fields : ['value', 'text'],
									data : [['ArrayStore', '静态数据(ArrayStore)'],
											['JsonStore', '动态数据(JsonStore)']]
								}),
						valueField : 'value',
						displayField : 'text',
						typeAhead : true,
						mode : 'local',
						triggerAction : 'all',
						forceSelection : true,
						emptyText : '--数据来源--',
						listeners : {
							scope : this,
							select : this.selectStore
						}
					}]

				});

		this.comboContainer.add(this.comboSelectForm);
		this.comboContainer.doLayout();
		this.add(this.comboContainer);
		this.formPanel.hide();
		this.doLayout();
	},
	/**
	 * 选择数据来源
	 * 
	 * @param {}
	 *            combo
	 * @param {}
	 *            record
	 * @param {}
	 *            index
	 */
	selectStore : function(combo, record, index) {
		if (this.storePanel) {
			this.comboContainer.remove(this.storePanel);
		}
		var value = combo.value;
		if (value == 'ArrayStore') {// 静态数据源
			var comboData = [['', ''], ['', '']];
			var comboStore = new Ext.data.ArrayStore({
						fields : [{
									name : 'value'
								}, {
									name : 'text'
								}]
					});

			comboStore.loadData(comboData);
			var sm = new Ext.grid.CheckboxSelectionModel();
			// 填入静态数据
			this.storePanel = new Ext.grid.EditorGridPanel({
				id : 'comboStore',
				autoScroll : true,
				stripeRows : true,
				height : 120,
				tbar : new Ext.Toolbar({
					height : 25,
					bodyStyle : 'text-align:left',
					items : [{
								iconCls : 'btn-add',
								text : '添加',
								handler : function() {
									var _gridPanel = Ext.getCmp('comboStore');
									var _store = _gridPanel.getStore(); // //
									var _cm = _gridPanel.getColumnModel();
									var recrod = new _store.recordType();
									recrod.data = {};

									var keys = _store.fields.keys;
									for (var j = 0; j < keys.length; j++) {
										recrod.data[keys[j]] = '';
									}
									recrod.markDirty();
									_gridPanel.stopEditing();
									_store.insert(0, recrod);

								}

							}, {
								iconCls : 'btn-del',
								text : '删除',
								handler : function() {
									var gridPanel = Ext.getCmp('comboStore');
									gridPanel.stopEditing();
									var selectRecords = gridPanel
											.getSelectionModel()
											.getSelections();
									if (selectRecords.length == 0) {
										Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
										return;
									}

									for (var i = 0; i < selectRecords.length; i++) {
										gridPanel.getStore()
												.remove(selectRecords[i]);
									}
								}
							}]
				}),
				store : comboStore,
				trackMouseOver : true,
				disableSelection : false,
				loadMask : true,
				clicksToEdit : 1,
				cm : new Ext.grid.ColumnModel({
							columns : [sm, {
										header : '值域',
										dataIndex : 'value',
										editor : new Ext.form.TextField({
													allowBlank : false
												})
									}, {
										header : '显示域',
										dataIndex : 'text',
										editor : new Ext.form.TextField({
													allowBlank : false
												})
									}],
							defaults : {
								sortable : true,
								menuDisabled : false
							}
						}),
				sm : sm,
				viewConfig : {
					forceFit : true,
					autoFill : true
				}
			});

		} else if (value == 'JsonStore') {// 动态数据源
			this.storePanel = new Ext.FormPanel({
						id : 'comboStore',
						layout : 'form',
						frame : true,
						defaults : {
							anchor : '98%,98%'
						},
						modal : true,
						defaultType : 'textfield',
						items : [{
									fieldLabel : '请求URL',
									id : 'url',
									xtype : 'textfield',
									allowBlank : false
								}, 
								{
									fieldLabel : 'root',
									id : 'root',
									xtype : 'textfield',
									hidden:true
								}, 
								{
									fieldLabel : '值域字段',
									id : 'value',
									xtype : 'textfield',
									allowBlank : false
								}, {
									fieldLabel : '显示域字段',
									id : 'text',
									xtype : 'textfield',
									allowBlank : false
								}]
					});
		}
		this.storePanel.show();
		this.comboContainer.add(this.storePanel);
		this.comboContainer.doLayout();
	},
	/**
	 * 下拉类型-确定
	 */
	comboConfirm : function() {
		var formPanel = this.formPanel;
		var paramTypeStr = {};
		var storePanel = this.storePanel;
		if (!storePanel) {
			this.comboCancel();
			return;
		}
		var combo_type = storePanel.getXType();
		if (combo_type == 'editorgrid') {// 静态数据源
			var _comboData = [];
			var _store = storePanel.getStore();
			_store.each(function(r) {
						_comboData.push([r.data['value'], r.data['text']]);
					});
			Ext.apply(paramTypeStr, {
						xtype : 'combo',
						fieldLabel : '缺省值',
						store : {
							xtype : 'arraystore',
							fields : ['value', 'text'],
							data : _comboData
						},
						valueField : 'value',
						displayField : 'text',
						typeAhead : true,
						mode : 'local',
						triggerAction : 'all',
						forceSelection : true,
						hiddenName : 'reportParam.defaultVal',
						id : 'defaultVal'
					});

		} else if (combo_type == 'form') {// 动态数据源
			var url = storePanel.findById('url').getValue();
			var root = storePanel.findById('root').getValue();

			var value = storePanel.findById('value').getValue();
			var text = storePanel.findById('text').getValue();

			var _comboFiels = [value, text];
			Ext.apply(paramTypeStr, {
						xtype : 'combo',
						fieldLabel : '缺省值',
						store : {
							xtype : 'jsonstore',
							autoLoad : true,
							root : root,
							url : __ctxPath + url,
							fields : _comboFiels
						},
						valueField : value,
						displayField : text,
						typeAhead : true,
						mode : 'local',
						triggerAction : 'all',
						forceSelection : true,
						hiddenName : 'reportParam.defaultVal',
						id : 'defaultVal'
					});
		}
		// 组件序列化
		formPanel.getCmpByName('reportParam.paramTypeStr').setValue(Ext
				.encode(paramTypeStr));
		// 加入组件
		formPanel.add(new Ext.form.ComboBox(paramTypeStr));
		this.comboCancel();
	},
	/**
	 * 下拉类型-取消
	 */
	comboCancel : function() {
		this.remove(this.comboContainer);
		this.formPanel.show();
	},
	/**
	 * 数字字典类型
	 */
	diccomboType : function() {
		this.diccomboForm = new Ext.FormPanel({
					layout : 'form',
					frame : true,
					defaults : {
						width : 400,
						anchor : '98%,98%'
					},
					modal : true,
					defaultType : 'textfield',
					buttonAlign : 'center',
					items : [{
						fieldLabel : '数据字典',
						id : 'proTypeId',
						width : 200,
						name : 'proTypeId',
						hiddenName : 'proTypeId',
						xtype : 'combotree',
						url : __ctxPath
								+ '/system/treeGlobalType.do?catKey=DIC&method=1',
						allowBlank : false
					}],
					buttons : [{
								text : '确定',
								iconCls : 'btn-save',
								scope : this,
								handler : this.diccomboConfirm
							}, {
								text : '取消',
								iconCls : 'btn-cancel',
								scope : this,
								handler : this.diccomboCancel
							}]

				}).show();
		this.add(this.diccomboForm);
		this.formPanel.hide();
		this.doLayout();
	},
	/**
	 * 数字字典-确定
	 */
	diccomboConfirm : function() {
		var formPanel = this.formPanel;
		var paramTypeStr = {};
		var proTypeId = Ext.getCmp('proTypeId').getValue();

		Ext.apply(paramTypeStr, {
					xtype : 'diccombo',
					fieldLabel : '缺省值',
					name : 'reportParam.defaultVal',
					id : 'defaultVal',
					//mode : 'local',
					proTypeId : proTypeId
					//displayField : 'itemValue',
					//valueField : 'itemValue'

				});
		formPanel.getCmpByName('reportParam.paramTypeStr').setValue(Ext
				.encode(paramTypeStr));
		formPanel.add(new DicCombo(paramTypeStr));
		this.diccomboCancel();
	},
	/**
	 * 数字字典-取消
	 */
	diccomboCancel : function() {
		this.remove(this.diccomboForm);
		this.formPanel.show();
	},
	/**
	 * 保存
	 */
	save : function() {
		$postForm({
					formPanel : this.formPanel,
					waitMsg : '正在提交数据...',
					scope : this,
					url : __ctxPath + '/system/saveReportParam.do',
					callback : function(fp, action) {
						if (this.callback) {
							this.callback.call(this.scope);
						}
						this.close();
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
