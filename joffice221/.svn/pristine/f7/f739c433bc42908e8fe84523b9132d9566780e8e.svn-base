/**
 * @author
 * @createtime
 * @class CheckSalaryPayoffForm
 * @extends Ext.Window
 * @description Job表单
 * @company 宏天软件
 */
CheckSalaryPayoffForm = Ext.extend(Ext.Window, {
			// 构造函数
			constructor : function(_cfg) {
				Ext.apply(this, _cfg);
				// 必须先初始化组件
				this.initComponents();
				CheckSalaryPayoffForm.superclass.constructor.call(this, {
							id : 'CheckSalaryPayoffFormWin',
							iconCls : 'btn-empProfile-pass',
							layout : 'form',
							items : [this.displayPanel, this.formPanel],
							modal : true,
							height : 415,
							shadow : false,
							autoScroll : true,
							width : 520,
							maximizable : true,
							title : '薪酬发放审核',
							buttonAlign : 'center',
							buttons : this.buttons
						});
			},// end of the constructor
			// 初始化组件
			initComponents : function() {
				this.displayPanel = new Ext.Panel({
							autoHeight : true,
							border : false,
							autoLoad : {
								url : __ctxPath
										+ '/pages/hrm/checkSalaryPayoff.jsp?recordId='
										+ this.recordId
							}
						}),
				this.formPanel = new Ext.FormPanel({
							layout : 'form',
							border : false,
							bodyStyle : 'padding:0 0 0 10px;',
							defaultType : 'recordId',
							items : [{
										fieldLabel : '审核意见',
										xtype : 'textarea',
										anchor : '98%',
										allowBlank : false,
										blankText : '审核意见为必填!',
										name : 'salaryPayoff.checkOpinion'
									}, {
										xtype : 'hidden',
										name : 'salaryPayoff.checkStatus'
									}]
						});
				// 初始化功能按钮
				this.buttons = [{
							text : '审核通过',
							iconCls : 'btn-salaryPayoff-pass',
							id : 'salaryPayoffbtnY',
							scope : this,
							handler : this.check
						}, {
							text : '审核未通过',
							id : 'salaryPayoffbtnN',
							iconCls : 'btn-salaryPayoff-notpass',
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
			check : function() {
				this.formPanel.getCmpByName('salaryPayoff.checkStatus')
						.setValue('1');// 表审核通过
				this.save();

			},// end of check
			/**
			 * 审核未通过
			 * 
			 */
			refuse : function() {
				this.formPanel.getCmpByName('salaryPayoff.checkStatus')
						.setValue('2');// 表审核未通过
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
									+ '/hrm/checkSalaryPayoff.do?recordId='
									+ this.recordId,
							callback : function(fp, action) {
								if (this.callback) {
									this.callback.call(this.scope);
								}
								this.close();
							}
						});
			}

		});