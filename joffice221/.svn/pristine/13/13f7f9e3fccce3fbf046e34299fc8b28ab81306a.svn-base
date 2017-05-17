Ext.ns('InStockForm');
/**
 * @author
 * @createtime
 * @class InStockForm
 * @extends Ext.Window
 * @description InStockForm表单
 * @company 宏天软件
 */
InStockForm = Ext.extend(Ext.Window, {
	sope : this,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		// 调用父类构造
		InStockForm.superclass.constructor.call(this, {
					id : 'InStockFormWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					width : 450,
					height : 300,
					minWidth : 449,
					minHeight : 200,
					maximizable : true,
					title : '入库单详细信息',
					iconCls : 'menu-instock',
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
					bodyStyle : 'padding:5px;',
					border : false,
					autoScroll : true,
					defaultType : 'textfield',
					items : [{
								name : 'inStock.buyId',
								xtype : 'hidden',
								value : this.buyId == null ? '' : this.buyId
							}, {
								name : 'inStock.goodsId',
								xtype : 'hidden'
							}, {
								xtype : 'container',
								layout : 'column',
								fieldLabel : '入库单号',
								style : 'padding-left:2px;margin-bottom:4px;',
								items : [{
											columnWidth : .99,
											xtype : 'textfield',
											name : 'inStock.stockNo',
											allowBlank : false,
											readOnly : true
										}, {
											xtype : 'button',
											iconCls : 'btn-system-setting',
											text : '系统生成',
											hidden : Ext.isEmpty(this.buyId)
													? false
													: true,
											scope : this,
											handler : this.genNumber
										}]

							}, {
								xtype : 'container',
								layout : 'column',
								border : false,
								style : 'padding-left:2px;margin-bottom:4px;',
								items : [{
											xtype : 'label',
											text : '商品名称:',
											style : 'margin-top:2px;',
											width : 106
										}, {
											columnWidth : .999,
											xtype : 'textfield',
											name : 'inStock.officeGoods.goodsName',
											allowBlank : false,
											readOnly : true
										}, {
											xtype : 'button',
											text : '选择商品',
											iconCls : 'btn-select',
											scope : this,
											handler : this.goodsSelect
										}, {
											xtype : 'button',
											text : ' 清除记录',
											iconCls : 'reset',
											scope : this,
											handler : this.resetGoodsSelect
										}]
							}, {
								width : '100%',
								xtype : 'container',
								border : false,
								defaultType : 'textfield',
								style : 'padding-left:3px;',
								layout : 'form',
								defaults : {
									anchor : '96%,96%'
								},
								items : [{
											fieldLabel : ' 供应商',
											name : 'inStock.providerName'
										}, {
											fieldLabel : '价格',
											name : 'inStock.price',
											allowBlank : false,
											xtype : 'numberfield'
										}, {
											fieldLabel : ' 总数',
											name : 'inStock.inCounts',
											allowBlank : false,
											xtype : 'numberfield'
										}, {
											fieldLabel : '总计',
											name : 'inStock.amount',
											readOnly : true
										}, {
											fieldLabel : ' 进货日期',
											name : 'inStock.inDate',
											allowBlank : false,
											editable : false,
											xtype : 'datefield',
											format : 'Y-m-d H:i:s',
											width : 126
										}]
							}, {
								width : '100%',
								xtype : 'container',
								layout : 'column',
								style : 'padding-left:2px;margin-bottom:4px;',
								border : false,
								items : [{
											xtype : 'label',
											text : '购买人:',
											style : 'margin-top:2px;',
											width : 106
										}, {
											columnWidth : .999,
											xtype : 'textfield',
											name : 'inStock.buyer',
											allowBlank : false,
											readOnly : true
										}, {
											xtype : 'button',
											text : '选择人员',
											iconCls : 'btn-user-sel',
											scope : this,
											handler : this.userSelect
										}, {
											xtype : 'button',
											text : ' 清除记录',
											iconCls : 'reset',
											scope : this,
											handler : this.resetUserSelect
										}]
							}]
				});
		// 加载表单对应的数据
		if (this.buyId != null && this.buyId != 'undefined') {
			this.formPanel.loadData({
						url : __ctxPath + '/admin/getInStock.do?buyId='
								+ this.buyId,
						root : 'data',
						preName : 'inStock'
					});
		};

	},// end of the initcomponents
	/**
	 * 生成编码
	 */
	genNumber : function() {
		$genNumber({
			scope : this,
			alias : 'InStock',
			callback : function(number) {
				this.formPanel.getCmpByName('inStock.stockNo').setValue(number);
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
	 * 保存记录
	 */
	save : function() {
		var formPanel = this.formPanel;
		// 验证
		if (formPanel.getForm().isValid()) {
			// 提交
			$postForm({
						formPanel : formPanel,
						scope : this,
						url : __ctxPath + '/admin/saveInStock.do',
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
	 * 商品选择
	 */
	goodsSelect : function() {
		var formPanel = this.formPanel;
		new GoodsDialog({
					scope : this,
					single : true,
					callback : function(id, name) {
						formPanel.getCmpByName('inStock.goodsId').setValue(id);
						formPanel.getCmpByName('inStock.officeGoods.goodsName')
								.setValue(name);
					}
				}).show();
	},
	/**
	 * 取消商品选择
	 */
	resetGoodsSelect : function() {
		var formPanel = this.formPanel;
		formPanel.getCmpByName('inStock.goodsId').setValue('');
		formPanel.getCmpByName('inStock.officeGoods.goodsName').setValue('');
	},
	/**
	 * 人员选择
	 */
	userSelect : function() {
		var formPanel = this.formPanel;
		new UserDialog({
					callback : function(id, name) {
						formPanel.getCmpByName('inStock.buyer').setValue(name);
					}
				}, false).show();
	},
	/**
	 * 取消人员选择
	 */
	resetUserSelect : function() {
		this.formPanel.getCmpByName('inStock.buyer').setValue('');
	}
});