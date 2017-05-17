Ext.ns('OfficeGoodsForm');
/**
 * @author 办公用品详细信息
 * @createtime
 * @class OfficeGoodsForm
 * @extends Ext.Window
 * @description OfficeGoodsForm表单
 * @company 宏天软件
 */
OfficeGoodsForm = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		// 调用父类构造
		OfficeGoodsTypeForm.superclass.constructor.call(this, {
					id : 'OfficeGoodsFormWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					width : 700,
					height : 260,
					// minWidth : 590,
					// minHeight : 260,
					maximizable : true,
					title : '办公用品详细信息',
					iconCls : 'menu-goods',
					buttonAlign : 'center',
					keys : {
						key : Ext.EventObject.ENTER,
						scope : this,
						fn : this.save
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
			bodyStyle : 'padding:5px;',
			border : false,
			autoScroll : true,
			defaults : {
				widht : 500
			},
			defaultType : 'textfield',
			items : [{
						name : 'officeGoods.goodsId',
						xtype : 'hidden',
						value : this.goodsId == null ? '' : this.goodsId
					}, {
						layout : 'column',
						xtype : 'fieldset',
						style : 'padding:0px',
						border : false,
						items : [{
							layout : 'form',
							columnWidth : .5,
							defaultType : 'textfield',
							border : false,
							items : [{
								xtype : 'container',
								layout : 'column',
								fieldLabel : '编号' + x,
								items : [{
											columnWidth : .99,
											xtype : 'textfield',
											name : 'officeGoods.goodsNo',
											allowBlank : false,
											readOnly : true
										}, {
											xtype : 'button',
											iconCls : 'btn-system-setting',
											text : '系统生成',
											hidden : Ext
													.isEmpty(this.goodsId)
													? false
													: true,
											scope : this,
											handler : this.genNumber
										}]
							}, {
								fieldLabel : '物品名称',
								name : 'officeGoods.goodsName',
								allowBlank : false,
								anchor : '95%'
							}, {
								xtype : 'container',
								layout : 'form',
								anchor : '95%',
								items : [{
									fieldLabel : '所属分类',
									hiddenName : 'officeGoods.officeGoodsType.typeId',
									name : 'officeGoods.officeGoodsType.typeName',
									xtype : 'combotree',
									url : __ctxPath
											+ '/admin/treeOfficeGoodsType.do?method=1'
								}]
							}, {
								fieldLabel : '规格',
								name : 'officeGoods.specifications',
								allowBlank : false,
								anchor : '95%'
							}]
						}, {
							layout : 'form',
							columnWidth : .5,
							border : false,
							defaultType : 'textfield',
							items : [{
										fieldLabel : '计量单位',
										name : 'officeGoods.unit',
										allowBlank : false,
										anchor : '95%'
									}, {
										fieldLabel : '库存总数',
										name : 'officeGoods.stockCounts',
										xtype : 'numberfield',
										allowBlank : false,
										anchor : '95%'
									}, {
										name : 'counts',
										xtype : 'hidden'
									}, {
										fieldLabel : '是否启用库存警示',
										xtype : 'combo',
										anchor : '95%',
										allowBlank : false,
										hiddenName : 'officeGoods.isWarning',
										mode : 'local',
										editable : false,
										triggerAction : 'all',
										store : [['0', '否'], ['1', '是']],
										value : 0
									}, {
										fieldLabel : '警报库存数',
										name : 'officeGoods.warnCounts',
										allowBlank : false,
										xtype : 'numberfield',
										anchor : '95%'
									}]
						}]
					}, {
						xtype : 'container',
						layout : 'form',
						items : [{
									fieldLabel : '备注',
									name : 'officeGoods.notes',
									xtype : 'textarea',
									anchor : '98%'
								}]
					}]
		});
		// 加载表单对应的数据
		if (!Ext.isEmpty(this.goodsId)) {
			var formPanel = this.formPanel;
			formPanel.loadData({
				url : __ctxPath + '/admin/getOfficeGoods.do?goodsId='
						+ this.goodsId,
				root : 'data',
				preName : 'officeGoods',
				success : function(response, options) {
					var data = Ext.util.JSON.decode(response.responseText).data;
					formPanel.getCmpByName('counts').setValue(data.stockCounts);
				},
				failure : function(response, options) {
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
	 * 生成编码
	 */
	genNumber : function() {
		$genNumber({
					scope : this,
					alias : 'OfficeGoods',
					callback : function(number) {
						this.formPanel.getCmpByName('officeGoods.goodsNo')
								.setValue(number);
					}
				});
	},
	/**
	 * 保存记录
	 */
	save : function() {
		var formPanel = this.formPanel;
		var stockCounts = formPanel.getCmpByName('officeGoods.stockCounts');
		if (stockCounts != null) {
			var newStockCounts = stockCounts.getValue();
			var counts = formPanel.getCmpByName('counts').getValue();
		}
		if (newStockCounts != counts && !Ext.isEmpty(this.goodsId)) {
			Ext.Msg.confirm('操作信息', '你已经修改了库存，是否继续保存?', function(btn) {
						if (btn == 'yes') {
							// 提交
							this.formSave();
						}
					}, this);
		} else {
			// 提交
			this.formSave();
		}
	},
	/**
	 * 提交表单
	 */
	formSave : function() {
		// 提交
		$postForm({
					formPanel : this.formPanel,
					scope : this,
					url : __ctxPath + '/admin/saveOfficeGoods.do',
					callback : function(formPanel, action) {
						if (this.callback) {
							this.callback.call(this.scope);
						}
						this.close();
					}
				});
	}
});