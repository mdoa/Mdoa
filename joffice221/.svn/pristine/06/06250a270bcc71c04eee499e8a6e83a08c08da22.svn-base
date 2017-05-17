Ext.ns('OfficeGoodsTypeForm');
/**
 * 办公用品类型详细信息
 * @class OfficeGoodsTypeForm
 * @extends Ext.Window
 */
OfficeGoodsTypeForm = Ext.extend(Ext.Window, {
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 必须先初始化组件
				this.initUIComponents();
				// 调用父类构造
				OfficeGoodsTypeForm.superclass.constructor.call(this, {
							id : 'OfficeGoodsTypeFormWin',
							layout : 'fit',
							items : this.formPanel,
							modal : true,
							width : 300,
							height : 120,
							maximizable : true,
							title : '办公用品类型详细信息',
							iconCls:'goods-type',
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
				// 表单面板
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
								name : 'officeGoodsType.typeId',
								xtype : 'hidden',
								value : this.typeId == null ? '' : this.typeId
							}, {
								fieldLabel : '分类名称',
								name : 'officeGoodsType.typeName',
								allowBlank:false
							}]
						});
				// 加载表单对应的数据
				if (this.typeId != null
						&& this.typeId != 'undefined') {
					this.formPanel.loadData({
								url : __ctxPath + '/admin/getOfficeGoodsType.do?typeId='+ this.typeId,
								root : 'data',
								preName : 'officeGoodsType'
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
				// 验证
				if (formPanel.getForm().isValid()) {
					// 提交
					$postForm({
								formPanel : formPanel,
								scope : this,
								url : __ctxPath + '/admin/saveOfficeGoodsType.do',
								callback : function(fp, action) {
									if (this.callback) {
										this.callback.call(this.scope);
									}
									this.close();
								}
							});
				}// end of save
			}
		});