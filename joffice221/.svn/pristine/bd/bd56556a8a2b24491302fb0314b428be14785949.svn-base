/**
 * @author
 * @createtime
 * @class OrganizationForm
 * @extends Ext.Window
 * @description Organization表单
 * @company 宏天软件
 */
HireIssueForm = Ext.extend(Ext.Window, {
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 必须先初始化组件
				this.initUIComponents();
				HireIssueForm.superclass.constructor.call(this, {
							id : 'HireIssueFormWin',
							title : '招聘信息详细信息',
							width : 550,
							height : 480,
							layout : 'fit',
							items : this.formPanel,
							modal : true,
							maximizable : true,
							buttonAlign : 'center',
							buttons : [{
										text : '保存',
										iconCls : 'btn-save',
										scope : this,
										handler : this.save
									}, {
										text : '取消',
										iconCls : 'btn-cancel',
										scope : this,
										handler : this.close
									}]
						});
			},// end of the constructor
			// 初始化组件
			initUIComponents : function() {
				this.formPanel = new Ext.FormPanel({
							layout : 'form',
							frame : false,
							border : false,
							bodyStyle : 'padding:5px;',
							defaults : {
								width : 600,
								anchor : '98%,98%'
							},
							formId : 'HireIssueFormId',
							defaultType : 'textfield',
							items : [{
										name : 'hireIssue.hireId',
										xtype : 'hidden',
										value : this.hireId == null
												? ''
												: this.hireId
									}, {
										xtype : 'hidden',
										name : 'hireIssue.regFullname'
									}, {
										xtype : 'hidden',
										name : 'hireIssue.regDate'
									}, {
										xtype : 'hidden',
										name : 'hireIssue.modifyFullname'
									}, {
										xtype : 'hidden',
										name : 'hireIssue.modifyDate'
									}, {
										xtype : 'hidden',
										name : 'hireIssue.checkFullname'
									}, {
										xtype : 'hidden',
										name : 'hireIssue.checkOpinion'
									}, {
										xtype : 'hidden',
										name : 'hireIssue.checkDate'
									}, {
										xtype : 'hidden',
										name : 'hireIssue.status'
									}, {
										fieldLabel : '标题',
										name : 'hireIssue.title',
										allowBlank : false
									}, {
										fieldLabel : '开始时间',
										name : 'hireIssue.startDate',
										xtype : 'datefield',
										format : 'Y-m-d',
										allowBlank : false
									}, {
										fieldLabel : '结束时间',
										name : 'hireIssue.endDate',
										xtype : 'datefield',
										format : 'Y-m-d',
										allowBlank : false
									}, {
										fieldLabel : '招聘人数',
										name : 'hireIssue.hireCount',
										xtype : 'numberfield',
										allowBlank : false
									}, {
										fieldLabel : '招聘职位',
										name : 'hireIssue.jobName',
										allowBlank : false
									}, {
										fieldLabel : '招聘条件',
										name : 'hireIssue.jobCondition',
										xtype : 'htmleditor',
										allowBlank : false
									}, {
										fieldLabel : '备注',
										name : 'hireIssue.memo',
										xtype : 'textarea'
									}

							]
						});

				// 加载表单对应的数据
				if (this.hireId != null && this.hireId != 'undefined') {
					this.formPanel.loadData({
								url : __ctxPath
										+ '/hrm/getHireIssue.do?hireId='
										+ this.hireId,
								root : 'data',
								preName : 'hireIssue'
							});
				}

			},// end of the initcomponents
			/**
			 * 保存记录
			 */
			save : function() {
				$postForm({
							formPanel : this.formPanel,
							scope : this,
							url : __ctxPath + '/hrm/saveHireIssue.do',
							callback : function(fp, action) {
								if (this.callback) {
									this.callback.call(this.scope);
								}
								this.close();
							}
						});
			}// end of save

		});
