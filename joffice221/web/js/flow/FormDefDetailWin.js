/**
 * 定义表单明细
 * 
 * @class FormDefDetailWin
 * @extends Ext.Window
 */
FormDefDetailWin = Ext.extend(Ext.Window, {
	// 构造的方法
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		this.initUI();
		FormDefDetailWin.superclass.constructor.call(this, {
					layout : 'border',
					items : this.detailForm,
					modal : true,
					height : 400,
					iconCls : 'menu-form',
					width : 500,
					maximizable : true,
					title : '表单定义的详细信息',
					buttonAlign : 'center',
					buttons : [{
								text : '关闭',
								iconCls : 'btn-cancel',
								scope : this,
								handler : this.cancel
							}]
				});
	},
	// 初始化组件
	initUI : function() {

		this.store = new Ext.data.JsonStore({
					fields : [{
								name : 'tableId',
								type : 'int'
							}, 'isMain', 'formDefId', 'tableName', 'tableKey',
							'formFields']
				});
		this.store.setDefaultSort('isMain', 'DESC');
		// 表列表
		this.tableGrid = new HT.GridPanel({
					region : 'center',

					showPaging : false,
					// 使用RowActions
					rowActions : true,
					store : this.store,
					columns : [{
								header : 'tableId',
								dataIndex : 'tableId',
								hidden : true
							}, {
								header : '表描述',
								dataIndex : 'tableName'
							}, {
								header : '表名',
								dataIndex : 'tableKey'
							}, {
								header : '是否主表',
								dataIndex : 'isMain',
								renderer : function(value) {
									return value == 1 ? '是' : '否';
								}
							}, new Ext.ux.grid.RowActions({
										header : '管理',
										width : 40,
										actions : [{
													iconCls : 'btn-detail',
													qtip : '查看表的字段',
													style : 'margin:0 3px 0 3px'
												}],
										listeners : {
											scope : this,
											'action' : this.onRowAction
										}
									})]
				});

		// 表单信息
		this.detailForm = new Ext.FormPanel({
					layout : 'form',
					region : 'center',
					border : false,
					defaults : {
						padding : '5',
						anchor : '98%,98%'
					},
					items : [{
								xtype : 'fieldset',
								title : '表单信息',
								layout : 'form',
								defaultType : 'displayfield',
								defaults : {
									anchor : '98%,98%'
								},
								items : [{
											name : 'formDef.formTitle',
											fieldLabel : '表单名称'
										}, {
											name : 'formDef.formDesp',
											fieldLabel : '表单描述'
										}]
							}, {
								xtype : 'fieldset',
								anchor : '98% -100',
								title : '数据库表',
								layout : 'fit',
								items : [this.tableGrid]
							}]
				});

		// 加载数据
		if (!Ext.isEmpty(this.formDefId)) {
			this.detailForm.loadData({
						url : __ctxPath + '/flow/getFormDef.do?formDefId='
								+ this.formDefId,
						root : 'data',
						preName : 'formDef',
						scope : this,
						success : function(response, options) {
							var obj = Ext.util.JSON
									.decode(response.responseText).data;
							var tables = obj.formTables;
							this.store.loadData(tables);
						}
					});
		}

	},
	/**
	 * 关闭
	 */
	cancel : function() {
		this.close();
	},
	/**
	 * 表明细
	 * 
	 * @param {}
	 *            record
	 */
	detail : function(record) {
		new TableDetailWin({
					tableName : record.data.tableName,
					tableKey : record.data.tableKey,
					formFields : record.data.formFields
				}).show();

	},
	// 行的Action
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-detail' :
				this.detail.call(this, record);
				break;
			default :
				break;
		}
	}
});
/**
 * 数据表
 * 
 * @class TableDetailWin
 * @extends Ext.Window
 */
TableDetailWin = Ext.extend(Ext.Window, {

	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		this.initUI();
		TableDetailWin.superclass.constructor.call(this, {
					layout : 'fit',
					items : this.detailForm,
					modal : true,
					height : 400,
					iconCls : 'menu-form',
					width : 800,
					maximizable : true,
					title : '数据库表信息',
					buttonAlign : 'center',
					buttons : [{
								text : '关闭',
								iconCls : 'btn-cancel',
								scope : this,
								handler : this.cancel
							}]

				});
	},
	initUI : function() {
		this.store = new Ext.data.JsonStore({
					fields : [{
								name : 'fieldId',
								type : 'int'
							}, 'tableId', 'fieldName', 'fieldLabel',
							'fieldType', 'isRequired', 'fieldSize',
							'fieldDscp', 'isPrimary', 'foreignKey',
							'foreignTable', 'isList', 'isQuery', 'isFlowTitle',
							'showFormat', 'status'],
					remoteSort : false
				});

		this.store.setDefaultSort('isPrimary', 'desc');

		var cm = new Ext.grid.ColumnModel({
			columns : [new Ext.grid.RowNumberer(), {
						header : 'fieldId',
						dataIndex : 'fieldId',
						hidden : true
					}, {
						header : '字段名称',
						dataIndex : 'fieldName',
						xtype : 'templatecolumn',
						tpl : new Ext.XTemplate('<font <tpl if="status ==1 ">style="text-decoration:line-through;color:red;"</tpl>>{fieldName} </font>')
					}, {
						header : '字段描述',
						dataIndex : 'fieldLabel',
						xtype : 'templatecolumn',
						tpl : new Ext.XTemplate('<font <tpl if="status ==1 ">style="text-decoration:line-through;color:red;"</tpl>>{fieldLabel} </font>')
					}, {
						header : '字段长度',
						dataIndex : 'fieldSize'
					}, {
						header : '字段类型',
						dataIndex : 'fieldType'
					}, {
						header : '显示格式',
						dataIndex : 'showFormat'
					}, {
						header : '是否为节点标题',
						dataIndex : 'isFlowTitle',
						xtype : 'templatecolumn',
						tpl : new Ext.XTemplate('<p><input type="checkbox" <tpl if="isFlowTitle ==1">checked="checked"</tpl> onclick="return false;" class="x-form-checkbox x-form-field"/></p>')

					}, {
						header : '是否主键',
						dataIndex : 'isPrimary',
						xtype : 'templatecolumn',
						tpl : new Ext.XTemplate('<p><input type="checkbox" <tpl if="isPrimary ==1">checked="checked"</tpl> onclick="return false;" class="x-form-checkbox x-form-field"/></p>')

					}, {
						header : '是否必填',
						dataIndex : 'isRequired',
						xtype : 'templatecolumn',
						tpl : new Ext.XTemplate('<p><input type="checkbox" <tpl if="isRequired ==1">checked="checked"</tpl> onclick="return false;" class="x-form-checkbox x-form-field"/></p>')

					}, {
						header : '是否显示',
						dataIndex : 'isList',
						xtype : 'templatecolumn',
						tpl : new Ext.XTemplate('<p><input type="checkbox" <tpl if="isList ==1">checked="checked"</tpl> onclick="return false;" class="x-form-checkbox x-form-field"/></p>')

					}, {
						header : '是否查询',
						dataIndex : 'isQuery',
						xtype : 'templatecolumn',
						tpl : new Ext.XTemplate('<p><input type="checkbox" <tpl if="isQuery ==1">checked="checked"</tpl> onclick="return false;" class="x-form-checkbox x-form-field"/></p>')
					}, {
						header : '状态',
						dataIndex : 'status',
						renderer : function(value) {
							if (!Ext.isEmpty(value) && value == 1) {
								return '<font color="red">已删除</font>';
							}
							return '';
						}
					}
			// ,{
			// header : '外键字段',
			// dataIndex : 'foreignKey'
			// }, {
			// header : '关联表',
			// dataIndex : 'foreignTable'
			// }
			],
			defaults : {
				sortable : false,
				menuDisabled : false,
				width : 100
			}
		});
		this.fieldGrid = new Ext.grid.GridPanel({
					flex : 1,
					shim : true,
					trackMouseOver : true,
					disableSelection : false,
					loadMask : true,
					stripeRows : true,
					region : 'center',
					cm : cm,
					store : this.store,
					viewConfig : {
						forceFit : true,
						enableRowBody : false,
						showPreview : false
					}
				});

		this.detailForm = new Ext.FormPanel({
					layout : 'form',
					region : 'north',
					border : false,
					defaults : {
						padding : '5',
						anchor : '98%,98%'
					},
					items : [{
								xtype : 'fieldset',
								title : '数据库表信息',
								layout : 'form',
								defaultType : 'displayfield',
								defaults : {
									anchor : '98%,98%'
								},
								items : [{
											name : 'formTable.tableName',
											fieldLabel : '表名',
											value : this.tableName
										}, {
											name : 'formTable.tableKey',
											fieldLabel : '表KEY',
											value : this.tableKey
										}]
							}, {
								xtype : 'fieldset',
								anchor : '98% -100',
								title : '表字段列表',
								layout : 'hbox',
								layoutConfig : {
									align : 'stretch'
								},
								items : [this.fieldGrid]
							}]
				});

		if (this.formFields) {
			this.store.loadData(this.formFields);
		}

	},
	cancel : function() {
		this.close();
	}
});
