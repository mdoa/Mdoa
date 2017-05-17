/**
 * @author
 * @createtime
 * @class HireIssueCheckWin
 * @extends Ext.Window
 * @description 审核
 * @company 宏天软件
 */
HireIssueCheckWin = Ext.extend(Ext.Window, {
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 必须先初始化组件
				this.initUIComponents();
				HireIssueCheckWin.superclass.constructor.call(this, {
							title : '招聘信息',
							id : 'HireIssueChckeWin',
							iconCls : 'menu-hireIssue',
							width : 500,
							x : 300,
							y : 50,
							modal : true,
							autoHeight : true,
							buttonAlign : 'center',
							items : [this.panel, this.formPanel],
							buttons : [{
								text : '通过审核',
								iconCls : 'btn-empProfile-pass',
								hidden : Ext.isEmpty(this.check)
										? false
										: this.check,
								scope : this,
								handler : this.passClick
							}, {
								text : '不通过审核',
								iconCls : 'btn-empProfile-notpass',
								hidden : Ext.isEmpty(this.check)
										? false
										: this.check,
								scope : this,
								handler : this.unPassClick
							}, {
								text : '关闭',
								iconCls : 'btn-close',
								scope : this,
								handler : this.close
							}]
						});
			},// end of the constructor
			// 初始化组件
			initUIComponents : function() {
				this.panel = new Ext.Panel({
							border : false,
							autoLoad : {
								url : __ctxPath
										+ '/hrm/loadHireIssue.do?hireId='
										+ this.hireId
							}
						});
				this.formPanel = new Ext.FormPanel({
							hidden : Ext.isEmpty(this.check)
									? false
									: this.check,
							layout : 'form',
							frame : false,
							border : false,
							items : [{
										xtype : 'hidden',
										name : 'hireId',
										value : this.hireId
									}, {
										fieldLabel : '审核意见',
										xtype : 'textarea',
										name : 'checkOpinion',
										allowBlank : false,
										anchor : '98%'
									}, {
										name : 'status',
										xtype : 'hidden'
									}]
						});

			},// end of the initcomponents
			/**
			 * 审核通过
			 */
			passClick : function() {
				this.formPanel.getForm().findField('status').setValue(1);
				this.save();
			},
			/**
			 * 审核不通过
			 */
			unPassClick : function() {
				this.formPanel.getForm().findField('status').setValue(2);
				this.save();
			},
			save : function() {
				$postForm({
							formPanel : this.formPanel,
							scope : this,
							url : __ctxPath + '/hrm/checkHireIssue.do',
							callback : function(fp, action) {
								if (this.callback) {
									this.callback.call(this.scope);
								}
								this.close();
							}
						});
			}
		});
