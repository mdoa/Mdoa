Ext.ns('GoodsApplyForm');
/**
 * @author
 * @createtime
 * @class GoodsApplyForm
 * @extends Ext.Window
 * @description GoodsApplyForm表单
 * @company 宏天软件
 */
GoodsApplyForm = Ext.extend(Ext.Window, {
			sope : this,
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 必须先初始化组件
				this.initUIComponents();
				// 调用父类构造
				GoodsApplyForm.superclass.constructor.call(this, {
							id : 'GoodsApplyFormWin',
							layout : 'fit',
							items : this.formPanel,
							width : 450,
							height : 300,
							minWidth : 449,
							minHeight : 299,
							maximizable : true,
							plain : true,
							modal : true,
							border : false,
							title : '申请表详细信息',
							iconCls : 'menu-goods-apply',
							buttonAlign : 'center',
							keys : {
								key : Ext.EventObject.ENTER,
								fn : this.submit,
								scope : this
							},
							buttons : [{
										text : '保存草稿',
										iconCls : 'btn-save',
										scope : this,
										handler : this.draff
									}, {
										text : '提交审核',
										iconCls : 'btn-ok',
										scope : this,
										handler : this.submit
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
							defaults : {
								widht : 450,
								anchor : '96%,96%'
							},
							items : [{
								name : 'goodsApply.applyId',
								xtype : 'hidden',
								value : this.applyId == null
										? ''
										: this.applyId
							}, {
								name : 'goodsApply.goodsId',
								xtype : 'hidden'
							}, {
								name : 'goodsApply.userId',
								xtype : 'hidden'
							}, {
								xtype : 'container',
								layout : 'column',
								fieldLabel : '申请号',
								items : [{
											columnWidth : .99,
											xtype : 'textfield',
											name : 'goodsApply.applyNo',
											allowBlank : false,
											readOnly : true
										}, {
											xtype : 'button',
											iconCls : 'btn-system-setting',
											text : '系统生成',
											hidden : Ext
													.isEmpty(this.applyId)
													? false
													: true,
											scope : this,
											handler : this.genNumber
										}]
							}, {
								width : '100%',
								xtype : 'container',
								layout : 'column',
								fieldLabel : '商品名称',
								style : 'padding-left:0px;margin-bottom:4px;',
								items : [{
											columnWidth : .999,
											xtype : 'textfield',
											name : 'officeGoods.goodsName',
											allowBlank : false,
											readOnly : true
										}, {
											xtype : 'button',
											text : '选择商品',
											iconCls : 'btn-select',
											scope : this,
											name : 'goodsSelect',
											handler : this.goodsSelect
										}, {
											xtype : 'button',
											text : ' 清除记录',
											iconCls : 'reset',
											name : 'resetGoodsSelect',
											scope : this,
											handler : this.resetGoodsSelect
										}]
							}, {
								fieldLabel : '申请日期',
								name : 'goodsApply.applyDate',
								xtype : 'datefield',
								format : 'Y-m-d H:i:s',
								allowBlank : false,
								editable : false
							}, {
								fieldLabel : '申请数量',
								name : 'goodsApply.useCounts',
								allowBlank : false,
								xtype : 'numberfield'
							}, {
								xtype : 'container',
								layout : 'column',
								fieldLabel : '申请人',
								style : 'padding-left:0px;margin-bottom:4px;',
								border : true,
								items : [{
											columnWidth : .999,
											xtype : 'textfield',
											name : 'goodsApply.proposer',
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
							}, {
								fieldLabel : '审批状态 ',
								name : 'goodsApply.approvalStatus',
								xtype : 'hidden'
							}, {
								fieldLabel : '备注',
								name : 'goodsApply.notes',
								xtype : 'textarea'
							}]
						});
				// 加载表单对应的数据
				if (this.applyId != null && this.applyId != 'undefined') {
					var formPanel = this.formPanel;
					formPanel.loadData({
								url : __ctxPath
										+ '/admin/getGoodsApply.do?applyId='
										+ this.applyId,
								root : 'data',
								preName : 'goodsApply',
								success : function(form, action) {
									var goodsSelect = formPanel
											.getCmpByName('goodsSelect');
									var resetGoodsSelect = formPanel
											.getCmpByName('resetGoodsSelect');
									goodsSelect.setDisabled(true);
									resetGoodsSelect.setDisabled(true);
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
			 * 草稿
			 */
			draff : function() {
				this.save(0);
			},
			/**
			 * 提交
			 */
			submit : function() {
				this.save(1);
			},
			/**
			 * 保存记录
			 */
			save : function(_approvalStatus) {
				var formPanel = this.formPanel;
				formPanel.getCmpByName('goodsApply.approvalStatus')
						.setValue(_approvalStatus);
				// 提交
				$postForm({
							formPanel : formPanel,
							scope : this,
							url : __ctxPath + '/admin/saveGoodsApply.do',
							callback : function(formPanel, action) {
								if (this.callback) {
									this.callback.call(this.scope);
								}
								this.close();
							}
						});
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
								formPanel.getCmpByName('goodsApply.goodsId')
										.setValue(id);
								formPanel.getCmpByName('officeGoods.goodsName')
										.setValue(name);
							}
						}).show();
			},
			/**
			 * 取消商品选择
			 */
			resetGoodsSelect : function() {
				var formPanel = this.formPanel;
				formPanel.getCmpByName('goodsApply.goodsId').setValue('');
				formPanel.getCmpByName('officeGoods.goodsName').setValue('');
			},
			/**
			 * 人员选择
			 */
			userSelect : function() {
				var formPanel = this.formPanel;
				new UserDialog({
							callback : function(id, name) {
								formPanel.getCmpByName('goodsApply.proposer')
										.setValue(name);
								formPanel.getCmpByName('goodsApply.userId')
										.setValue(id);
							}
						}, true).show();
			},
			/**
			 * 取消人员选择
			 */
			resetUserSelect : function() {
				this.formPanel.getCmpByName('goodsApply.proposer').setValue('');
			},
			/**
			 * 生成编码
			 */
			genNumber : function() {
				$genNumber({
							scope : this,
							alias : 'GoodsApply',
							callback : function(number) {
								this.formPanel
										.getCmpByName('goodsApply.applyNo')
										.setValue(number);
							}
						});
			}
		});