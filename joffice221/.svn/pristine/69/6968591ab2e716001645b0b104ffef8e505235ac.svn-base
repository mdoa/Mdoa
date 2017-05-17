Ext.ns('StandSalaryForm')
/**
 * @author
 * @createtime
 * @class StandSalaryForm
 * @extends Ext.Window
 * @description 薪酬标准详细信息
 * @company 宏天软件
 */
StandSalaryForm = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(conf) {
		Ext.apply(this, conf);
		this.initComponents();
		// 必须先初始化组件
		StandSalaryForm.superclass.constructor.call(this, {
					id : 'StandSalaryForm',
					iconCls : 'menu-development',
					border : false,
					title : '薪酬标准详细信息',
					items : [this.formPanel],
					tbar : [{
								text : '保存',
								iconCls : 'btn-save',
								scope : this,
								handler : this.saveStandSalary
							},'-', {
								text : '取消',
								iconCls : 'btn-cancel',
								scope : this,
								handler : function() {
									var tabs = Ext.getCmp('centerTabPanel');
									tabs.remove('StandSalaryForm');
								}
							}]
				});
	},// end of the constructor
	// 初始化组件
	initComponents : function() {
		// 薪酬项目金额设置
		this.salaryItemGridPanel = new HT.EditorGridPanel({
					iconCls : 'menu-salary',
					title : '薪酬项目金额设置',
					rowActions : true,
					tbar : [{
								iconCls : 'btn-add',
								text : '添加薪酬项目',
								xtype : 'button',
								scope : this,
								handler : this.addSalaryItem
							}],
					height : 220,
					fields : [{
								name : 'itemId',
								type : 'int'
							}, 'standardId', 'itemName', 'amount',
							'salaryItemId'],
					url : __ctxPath + '/hrm/listStandSalaryItem.do?standardId='
							+ this.standardId,
					columns : [{
								header : 'itemId',
								dataIndex : 'itemId',
								hidden : true
							}, {
								header : '所属标准',
								dataIndex : 'standardId',
								hidden : true
							}, {
								header : '项目名称',
								dataIndex : 'itemName'
							}, {
								header : '金额',
								dataIndex : 'amount',
								editor : new Ext.form.NumberField({
											allowBlank : false,
											listeners : {
												scope : this,
												'change' : this.changeValue
											}
										}),
								renderer : function(value) {
									return '<img src="'
											+ __ctxPath
											+ '/images/flag/customer/rmb.png"/>'
											+ value;
								}
							}, {
								header : '所属工资组成ID',
								dataIndex : 'salaryItemId',
								hidden : true
							}, new Ext.ux.grid.RowActions({
										header : '管理',
										width : 100,
										actions : [{
													iconCls : 'btn-del',
													qtip : '删除',
													style : 'margin:0 3px 0 3px'
												}],
										listeners : {
											scope : this,
											'action' : this.onRowAction
										}
									})]
				});

		// 表单数据
		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			bodyStyle : 'padding:5px 10px 10px 10px;',
			formId : 'StandSalaryFormId',
			defaultType : 'textfield',
			border : false,
			items : [{
						name : 'standSalary.standardId',
						xtype : 'hidden',
						value : this.standardId == null ? '' : this.standardId
					}, {
						name : 'standSalary.setdownTime',
						xtype : 'hidden'
					}, {
						name : 'standSalary.framer',
						xtype : 'hidden'
					}, {
						name : 'standSalary.checkName',
						xtype : 'hidden'
					}, {
						name : 'standSalary.checkTime',
						xtype : 'hidden'
					}, {
						name : 'standSalary.checkOpinion',
						xtype : 'hidden'
					}, {
						name : 'deleteItemIds',
						xtype : 'hidden'
					}, {
						xtype : 'fieldset',
						iconCls : 'menu-development',
						title : '薪酬信息',
						anchor : '100%',
						layout : 'form',
						items : [{
							xtype : 'container',
							layout : 'column',
							style : 'padding-left:0px;',
							items : [{
										xtype : 'container',
										defaultType : 'textfield',
										style : 'padding-left:0px;',
										columnWidth : .5,
										defaults : {
											anchor : '96%,96%'
										},
										layout : 'form',
										items : [{
													fieldLabel : '标准编号',
													allowBlank : false,
													blankText : '标准编号不能为空!',
													name : 'standSalary.standardNo'
												}, {
													fieldLabel : '标准名称',
													xtype : 'textfield',
													name : 'standSalary.standardName',
													allowBlank : false,
													blankText : '标准名称不能为空!'
												}]
									}, {
										xtype : 'container',
										columnWidth : .5,
										style : 'padding-left:0px;',
										defaults : {
											anchor : '96%,96%'
										},
										layout : 'form',
										items : [{
											xtype : 'button',
											autoWidth : true,
											iconCls : 'btn-system-setting',
											text : '系统生成',
											hidden : Ext.isEmpty(this.standardId)
													? false
													: true,
											scope : this,
											handler : this.genNumber
										}, {
													fieldLabel : '薪资总额',
													name : 'standSalary.totalMoney',
													xtype : 'textfield',
													readOnly : true,
													anchor : '100%'
												}]
									}]
						}, {
							fieldLabel : '备注',
							name : 'standSalary.memo',
							xtype : 'textarea',
							anchor : '99.99%'
						}]
						// 项目薪金设置
					}, this.salaryItemGridPanel]
		});
		if (!Ext.isEmpty(this.standardId)) {
			this.formPanel.loadData({
				preName : 'standSalary',
				root : 'data',
				url : __ctxPath + '/hrm/getStandSalary.do?standardId='
						+ this.standardId,
				waitMsg : '正在载入数据...',
				scope : this,
				success : function(response, options) {
					this.formPanel.getForm()
							.findField('standSalary.standardNo').getEl().dom.readOnly = true;
				},
				failure : function(response, options) {

				}
			});
		}
	},// end of the initcomponents
	/**
	 * 生成编码
	 */
	genNumber : function() {
		$genNumber({
			scope : this,
			alias : 'StandSalary',
			callback : function(number) {
				this.formPanel.getCmpByName('standSalary.standardNo').setValue(number);
			}
		});
	},
	/**
	 * 添加薪酬项目金额
	 */
	addSalaryItem : function() {
		var grid = this.salaryItemGridPanel;
		var _store = grid.getStore();
		var _exclude = '';
		// 拼出已选的薪酬项目ID
		for (var i = 0; i < _store.getCount(); i++) {
			_exclude += _store.getAt(i).get('salaryItemId') + ',';
		}
		new SalaryItemDialog({
					_exclude : _exclude,
					isSingle : false,
					scope : this,
					callback : function(salaryItemId, itemName, defaultVal) {
						var ids = salaryItemId.split(',');
						var names = itemName.split(',');
						var values = defaultVal.split(',');
						var store = grid.getStore();
						var plant = grid.getStore().recordType;
						grid.stopEditing();

						for (var i = 0; i < ids.length; i++) {
							var p = new plant();
							p.set('salaryItemId', ids[i]);
							p.set('itemName', names[i]);
							p.set('amount', values[i]);
							p.commit();
							store.insert(store.getCount(), p);
						}

						grid.getView().refresh();
						grid.startEditing(0, 0);
						// 计算金额
						this.onCalcTotalMoney();
					}

				}).show();
	},
	/**
	 * 删除薪酬项目金额
	 */
	remove : function(id, salaryId) {
		var grid = this.salaryItemGridPanel;
		grid.stopEditing();
		var deleteItemIds = this.formPanel.getForm().findField('deleteItemIds');

		var s = grid.getSelectionModel().getSelections();
		for (var i = 0, r; r = s[i]; i++) {
			grid.getStore().remove(r);
		}
		if (!deleteItemIds)
			return;

		if (id != null && id != 'undefined' && id != '')
			deleteItemIds.setValue(deleteItemIds.getValue() + ',' + id);
		grid.getView().refresh();
		this.onCalcTotalMoney();
		grid.startEditing(0, 0);

	},
	/**
	 * 计算金额
	 */
	onCalcTotalMoney : function() {
		// 获取表格的数据存储器
		var _store = this.salaryItemGridPanel.getStore();
		// 提交修改的数值
		// _store.commit();
		// 总金额
		var _totalMoney = 0.00;
		// 计算总金额
		for (var i = 0; i < _store.getCount(); i++) {
			_totalMoney += Number(_store.getAt(i).get('amount'));
		}
		// 将总金额设置到form中
		this.formPanel.getCmpByName('standSalary.totalMoney')
				.setValue(_totalMoney);
	},
	/**
	 * 行的Action
	 */
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.remove.call(this, record.data.itemId,
						record.data.salaryItemId);
				break;
			default :
				break;
		}
	},
	/**
	 * 改变值
	 * 
	 * @param {}
	 *            field
	 * @param {}
	 *            newValue
	 * @param {}
	 *            oldValue
	 */
	changeValue : function(field, newValue, oldValue) {

		if (newValue != oldValue) {
			this.salaryItemGridPanel.startEditing(0, 0);
			this.onCalcTotalMoney();
		}
	},
	/**
	 * 保存
	 */
	saveStandSalary : function() {
		// 计算金额
		this.onCalcTotalMoney();
		var fp = this.formPanel;
		if (this.salaryItemGridPanel) {
			var store = this.salaryItemGridPanel.getStore();
			var params = [];
			for (var i = 0, cnt = store.getCount(); i < cnt; i += 1) {
				var record = store.getAt(i);
				if (Ext.isEmpty(record.data.itemId)) {// 设置未保存的assignId标记，方便服务端进行gson转化
					record.set('itemId', -1);
				}
				if (record.dirty) // 得到所有修改过的数据
					params.push(record.data);
			}
		}
		$postForm({
					formPanel : this.formPanel,
					waitMsg : '正在提交数据...',
					scope : this,
					url : __ctxPath + '/hrm/saveStandSalary.do',
					params : {
						data : Ext.encode(params)
					},
					callback : function(fp, action) {
						if (this.callback) {
							this.callback.call(this.scope);
						}
						this.formPanel.getForm().reset();
						var view = Ext.getCmp('StandSalaryView');
						if (view != null) {
							view.gridPanel.getStore().reload();
						}
						var tabs = Ext.getCmp('centerTabPanel');
						tabs.remove('StandSalaryForm');
						App.clickTopTab('StandSalaryView');
					}
				});
	}
});