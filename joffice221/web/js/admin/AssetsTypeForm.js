Ext.ns('AssetsTypeForm');
/**
 * 固定资产管理
 * 
 * @class AssetsTypeForm
 * @extends Ext.Window
 */
AssetsTypeForm = Ext.extend(Ext.Window, {
			sope : this,
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 必须先初始化组件
				this.initUIComponents();
				// 调用父类构造
				AssetsTypeForm.superclass.constructor.call(this, {
							id : 'AssetsTypeFormWin',
							layout : 'fit',
							items : this.formPanel,
							modal : true,
							width : 300,
							height : 130,
							maximizable : true,
							title : '资产分类详细信息',
							iconCls : 'assets-type',
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
								name : 'assetsType.assetsTypeId',
								xtype : 'hidden',
								value : this.assetsTypeId == null
										? ''
										: this.assetsTypeId
							}, {
								fieldLabel : '分类名称',
								name : 'assetsType.typeName',
								allowBlank : false
							}]
						});
				// 加载表单对应的数据
				if (this.assetsTypeId != null
						&& this.assetsTypeId != 'undefined') {
					this.formPanel.loadData({
								url : __ctxPath
										+ '/admin/getAssetsType.do?assetsTypeId='
										+ this.assetsTypeId,
								root : 'data',
								preName : 'assetsType'
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
								url : __ctxPath + '/admin/saveAssetsType.do',
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