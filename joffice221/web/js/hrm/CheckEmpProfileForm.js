/**
 * @author
 * @createtime
 * @class CheckEmpProfileForm
 * @extends Ext.Window
 * @description Job表单
 * @company 宏天软件
 */
CheckEmpProfileForm = Ext.extend(Ext.Window, {
			// 构造函数
			constructor : function(_cfg) {
				Ext.apply(this, _cfg);
				// 必须先初始化组件
				this.initComponents();
				CheckEmpProfileForm.superclass.constructor.call(this, {
							id : 'CheckEmpProfileFormWin',
							iconCls : 'btn-empProfile-pass',
							layout : 'form',
							items : [this.formPanel, this.displayPanel],
							modal : true,
							autoHeight : true,
							shadow : false,
							y : 10,
							width : 820,
							maximizable : true,
							title : Ext.isEmpty(this.title)?'档案审核':this.title,
							buttonAlign : 'center',
							buttons : this.buttons
						});
			},// end of the constructor
			// 初始化组件
			initComponents : function() {
				this.displayPanel = new Ext.Panel({
							height : 430,
							autoScroll : true,
							border : false,
							autoLoad : {
								url : __ctxPath
										+ '/pages/hrm/CheckEmpProfile.jsp?profileId='
										+ this.profileId
							}
						})
				this.formPanel = new Ext.FormPanel({
							hidden : Ext.isEmpty(this.check)
									? false
									: this.check,
							layout : 'form',
							border : false,
							bodyStyle : 'padding:10px 10px 0 10px;',
							defaultType : 'textfield',
							items : [{
										fieldLabel : '审核意见',
										xtype : 'textarea',
										anchor : '98%',
										allowBlank : false,
										blankText : '审核意见为必填!',
										name : 'empProfile.opprovalOpinion',
										id : 'CheckEmpProfileForm.opprovalOpinion'
									}, {
										xtype : 'hidden',
										name : 'empProfile.approvalStatus'
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
							text : '审核未通过',
							hidden : Ext.isEmpty(this.check)
									? false
									: this.check,
							iconCls : 'btn-empProfile-notpass',
							scope : this,
							handler : this.refuse
						}, {
							text : '取消',
							iconCls : 'btn-cancel',
							scope : this,
							handler : this.cancel
						}];
			},// end of the initcomponents
			/**
			 * 审核通过
			 */
			pass : function() {
				this.formPanel.getCmpByName('empProfile.approvalStatus')
						.setValue('1');// 表审核通过
				this.save();

			},// end of check
			/**
			 * 审核未通过
			 * 
			 */
			refuse : function() {
				this.formPanel.getCmpByName('empProfile.approvalStatus')
						.setValue('2');// 表审核通过
				this.save();

			},// end of refuse

			/**
			 * 取消
			 * 
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
							waitMsg : '正在提交数据...',
							scope : this,
							url : __ctxPath
									+ '/hrm/checkEmpProfile.do?profileId='
									+ this.profileId,
							callback : function(fp, action) {
								if (this.callback) {
									this.callback.call(this.scope);
								}
								this.close();
							}
						});
			}

		});