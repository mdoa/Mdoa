/**
 * @author
 * @createtime
 * @class ArchHastenForm
 * @extends Ext.Window
 * @description 催办信息 表单
 * @company 宏天软件
 */
ArchHastenForm = Ext.extend(Ext.Window, {
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 必须先初始化组件
				this.initUIComponents();
				// 调用父类构造
				ArchHastenForm.superclass.constructor.call(this, {
							id : 'ArchHastenFormWin',
							layout : 'fit',
							items : this.formPanel,
							modal : true,
							height : 200,
							width : 400,
							maximizable : true,
							title : '催办信息',
							buttonAlign : 'center',
							buttons : this.buttons
						});
			},// end of the constructor
			// 初始化组件
			initUIComponents : function() {
				// 表单面板
				this.formPanel = new Ext.FormPanel({
							layout : 'form',
							bodyStyle : 'padding:10px 10px 10px 10px',
							border : false,
							defaults : {
								anchor : '98%,98%'
							},
							items : [{
										xtype : 'hidden',
										name : 'archivesId',
										value : this.archivesId
									}, {
										fieldLabel : '催办内容',
										name : 'content',
										xtype : 'textarea',
										value : '你有[' + this.archivesNo + '--'
												+ this.activityName
												+ ']任务,请速去办理！'
									}, {
										xtype : 'hidden',
										name : 'activityName',
										value : this.activityName
									}]
						});
				// 初始化功能按钮
				this.buttons = [{
							text : '发送',
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
						}];
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
				$postForm({
							formPanel : formPanel,
							scope : this,
							url : __ctxPath + '/archive/hastenArchives.do',
							successMsg : '成功发送催办信息',
							callback : function(fp, action) {
								if (this.callback) {
									this.callback.call(this.scope);
								}
								this.close();
							}
						});
			}// end of save

		});