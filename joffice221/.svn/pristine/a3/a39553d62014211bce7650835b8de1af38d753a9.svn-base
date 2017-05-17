/**
 * @author csx
 * @description 公文模板在线显示及编辑窗口
 * @company 杭州梦德软件有限公司
 * @param {}
 *            readOnly
 */
var OfficeTemplateView = Ext.extend(Ext.Window, {
			// 构造函数
			constructor : function(conf) {
				Ext.apply(this, conf);
				// 必须先初始化组件
				this.initUI();
				OfficeTemplateView.superclass.constructor.call(this, {
							id : 'OfficeTemplateView',
							title : '公文模板详细信息',
							iconCls : 'menu-archive-template',
							height : 500,
							width : 700,
							maximizable : true,
							modal : true,
							items : this.docPanel.panel,
							buttonAlign : 'center',
							buttons : [{
								iconCls : 'btn-save',
								text : '保存',
								hidden : this.readOnly == null
										? true
										: this.readOnly,
								scope : this,
								handler : this.save
							}, {
								iconCls : 'btn-cancel',
								text : '关闭',
								scope : this,
								handler : function() {
									this.close();
								}
							}]
						});
			},// end of the constructor
			initUI : function() {
				this.docType = this.docType ? this.docType : 'doc';
				Ext.useShims = true;// 默认下，Ext会自动决定浮动元素是否应该被填充。如果你在用Flash那么该值很可能要设置为True
				this.docPanel = new NtkOfficePanel({
					showToolbar : false,
					height : 420,
					doctype : this.docType,
					fileId : this.fileId
						// unshowMenuBar : true
					});

				if (!this.docPanel.flag) {
					var msg = this.docPanel.msg;
					setTimeout(function() {
								Ext.MessageBox.show({
											title : '操作信息',
											msg : msg,
											buttons : Ext.MessageBox.OK,
											icon : 'ext-mb-warning',
											scope : this,
											fn : function() {
												Ext
														.getCmp('OfficeTemplateView')
														.close();
											}
										});
							}, 500);
					return;
				}

			},
			/**
			 * 保存
			 */
			save : function() {
				var obj = this.docPanel.saveDoc({
							fileId : this.fileId,
							doctype : this.docType
						});
				if (obj && obj.success) {
					if (this.callback) {
						this.callback
								.call(this.scope, obj.fileId, obj.filePath);
					}
					this.close();
				} else {
					Ext.MessageBox.show({
								title : '操作信息',
								msg : '保存信息失败，请联系管理员！',
								buttons : Ext.MessageBox.OK,
								icon : 'ext-mb-error'
							});
				}
			}
		});