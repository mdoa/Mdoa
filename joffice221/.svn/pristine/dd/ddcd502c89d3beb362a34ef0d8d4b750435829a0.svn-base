/**
 * @author lyy
 * @createtime
 * @class JobChangeForm
 * @extends Ext.Window
 * @description JobChange表单
 * @company 宏天软件
 */
CheckJobChangeWin = Ext.extend(Ext.Window, {
			// 构造函数
			constructor : function(_cfg) {
				Ext.apply(this, _cfg);
				// 必须先初始化组件
				this.initComponents();
				CheckJobChangeWin.superclass.constructor.call(this, {
							id : 'CheckJobChangeWinPanel',
							layout : 'form',
							iconCls : 'menu-job-check',
							modal : true,
							autoHeight : true,
							x : 280,
							y : 50,
							shadow : false,
							width : 550,
							maximizable : true,
							title : '职位调动详细信息',
							buttonAlign : 'center',
							items : [this.showPanel, this.formPanel],
							buttons : this.buttons
						});
			},// end of the constructor
			// 初始化组件
			initComponents : function() {
				this.showPanel = new Ext.Panel({
							id : 'CheckJobChangePanel',
							autoScroll : true,
							height : 280,
							border : false,
							autoLoad : {
								url : __ctxPath
										+ '/hrm/loadJobChange.do?changeId='
										+ this.changeId
							}
						});
				this.formPanel = new Ext.FormPanel({
							hidden : Ext.isEmpty(this.check)
									? false
									: this.check,
							layout : 'form',
							bodyStyle : 'padding:8px 8px 2px 8px',
							border : false,
							autoScroll : true,
							defaults : {
								anchor : '96%,96%'
							},
							items : [{
								xtype : 'hidden',
								name : 'jobChange.changeId',
								value : this.changeId == null
										? ''
										: this.changeId
							}, {
								xtype : 'hidden',
								name : 'jobChange.status'
							}, {
								xtype : 'textarea',
								fieldLabel : '审核意见',
								name : 'jobChange.checkOpinion',
								allowBlank : false
							}]
						});
				// 初始化功能按钮
				this.buttons = [{
							text : '审核通过',
							hidden : Ext.isEmpty(this.check)
									? false
									: this.check,
							iconCls : 'btn-empProfile-pass',
							scope : this,
							handler : this.pass
						}, {
							text : '审核不通过',
							hidden : Ext.isEmpty(this.check)
									? false
									: this.check,
							iconCls : 'btn-empProfile-notpass',
							scope : this,
							handler : this.notpass
						}, {
							text : '取消',
							iconCls : 'btn-cancel',
							scope : this,
							handler : this.cancel
						}]
			},// end of the initcomponents

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
			 * 通过审核
			 */
			pass : function() {
				this.formPanel.getCmpByName('jobChange.status').setValue(1);
				this.save();
			},// end of pass
			/**
			 * 不通过审核
			 */
			notpass : function() {
				this.formPanel.getCmpByName('jobChange.status').setValue(2);
				this.save();
			},// end of notpass
			save : function() {
				$postForm({
							formPanel : this.formPanel,
							scope : this,
							url : __ctxPath + '/hrm/checkJobChange.do',
							callback : function(fp, action) {
								if (this.callback) {
									this.callback.call(this.scope);
								}
								this.close();
							}
						});
			}
		});
