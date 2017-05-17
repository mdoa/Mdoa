Ext.ns('DepreTypeForm');
/**
 * @author
 * @createtime
 * @class DepreTypeForm
 * @extends Ext.Window
 * @description 折旧类型详细信息 表单
 * @company 宏天软件
 */
DepreTypeForm = Ext.extend(Ext.Window, {
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 必须先初始化组件
				this.initUIComponents();
				// 调用父类构造
				DepreTypeForm.superclass.constructor.call(this, {
							id : 'DepreTypeFormWin',
							layout : 'fit',
							items : this.formPanel,
							modal : true,
							width : 400,
							height : 240,
							minWidth : 399,
							minHeight : 239,
							maximizable : true,
							border : false,
							modal : true,
							plain : true,
							title : '折旧类型详细信息',
							iconCls : 'menu-depre-type',
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
				// 表单面板
				this.formPanel = new Ext.FormPanel({
							layout : 'form',
							frame : false,
							border : false,
							bodyStyle : 'padding:5px;',
							defaults : {
								anchor : '98%,98%'
							},
							defaultType : 'textfield',
							items : [{
								name : 'depreType.depreTypeId',
								xtype : 'hidden',
								value : this.depreTypeId == null
										? ''
										: this.depreTypeId
							}, {
								fieldLabel : '分类名称',
								name : 'depreType.typeName',
								allowBlank : false
							}, {
								xtype : 'container',
								height : 28,
								fieldLabel : '折旧周期(月)',
								layout : 'column',
								border : false,
								layoutConfigs : {
									align : 'middle'
								},
								defaults : {
									margins : '0 2 0 2'
								},
								items : [{
											columnWidth : .999,
											xtype : 'numberfield',
											name : 'depreType.deprePeriod',
											allowBlank : false
										}]
							}, {
								fieldLabel : '折旧计算方法',
								hiddenName : 'depreType.calMethod',
								xtype : 'combo',
								mode : 'local',
								editable : false,
								allowBlank : false,
								triggerAction : 'all',
								store : [['1', '平均年限法'], ['2', '工作量法'],
										['3', '双倍余额递减法'], ['4', '年数总和法']]
							}, {
								fieldLabel : '类型说明',
								name : 'depreType.typeDesc',
								xtype : 'textarea'
							}]
						});
				// 加载表单对应的数据
				if (this.depreTypeId != null && this.depreTypeId != 'undefined') {
					this.formPanel.loadData({
								url : __ctxPath
										+ '/admin/getDepreType.do?depreTypeId='
										+ this.depreTypeId,
								root : 'data',
								preName : 'depreType'
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
				$postForm({
							formPanel : this.formPanel,
							scope : this,
							url : __ctxPath + '/admin/saveDepreType.do',
							callback : function(fp, action) {
								if (this.callback) {
									this.callback.call(this.scope);
								}
								this.close();
							}
						});
			}

		});