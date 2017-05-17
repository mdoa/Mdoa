Ext.ns('CheckStandSalaryForm');
/**
 * 标准审核
 * 
 * @class CheckStandSalaryForm
 * @extends Ext.Window
 */
CheckStandSalaryForm = Ext.extend(Ext.Window, {
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		CheckStandSalaryForm.superclass.constructor.call(this, {
					id : 'CheckStandSalaryFormWin',
					title : '标准审核',
					iconCls : 'menu-goods-apply',
					width : 550,
					height : 510,
					modal : true,
					layout : 'fit',
					plain : true,
					bodyStyle : 'padding:5px;',
					buttonAlign : 'center',
					buttons : [{
								text : '审核通过',
								iconCls : 'btn-salaryPayoff-pass',
								scope : this,
								handler : this.checkPassClick
							}, {
								text : '审核未通过',
								iconCls : 'reset',
								scope : this,
								handler : this.checkUnpassClick
							}, {
								text : '取消',
								iconCls : 'btn-cancel',
								scope : this,
								handler : function() {
									this.close();
								}
							}],
					items : [this.formPanel]
				});
	},// end of constructor
	// 初始化组件
	initUIComponents : function() {
		this.grid = new HT.GridPanel({
					height : 200,
					trackMouseOver : true,
					autoScroll : true,
					disableSelection : false,
					loadMask : true,
					fields : [{
								name : 'itemId',
								type : 'int'
							}, 'standardId', 'itemName', 'amount',
							'salaryItemId'],
					url : __ctxPath + '/hrm/listStandSalaryItem.do?standardId='
							+ this.standardId,
					columns : [{
								header : 'itemId',
								dataIndex : 'itemId',
								hidden : true
							}, {
								header : '所属标准',
								dataIndex : 'standardId',
								hidden : true
							}, {
								header : '项目名称',
								dataIndex : 'itemName'
							}, {
								header : '金额',
								dataIndex : 'amount',
								renderer : function(value) {
									return '<img src="'
											+ __ctxPath
											+ '/images/flag/customer/rmb.png"/>'
											+ value;
								}
							}, {
								header : '所属工资组成ID',
								dataIndex : 'salaryItemId',
								hidden : true
							}]
				});
		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			bodyStyle : 'padding:0px 5px 5px 5px;',
			defaultType : 'textfield',
			border : false,
			items : [{
						name : 'standSalary.standardId',
						xtype : 'hidden',
						value : this.standardId == null ? '' : this.standardId
					}, {
						xtype : 'fieldset',
						title : '薪酬信息',
						layout : 'form',
						anchor : '460',
						width : 490,
						height : 225,
						labelWidth : 62,
						defaultType : 'textfield',
						items : [{
							xtype : 'container',
							style : 'padding-left:0px;',
							defaultType : 'textfield',
							layout : 'column',
							autoWidth : true,
							height : 26,
							defaults : {
								width : 150
							},
							items : [{
										xtype : 'label',
										text : '标准编号:',
										style : 'padding-left:0px;padding-top:3px;',
										width : 71
									}, {
										fieldLabel : '标准编号',
										readOnly : true,
										name : 'standSalary.standardNo'
									}, {
										xtype : 'label',
										text : '标准名称',
										style : 'padding-left:0px;padding-top:3px;',
										width : 71
									}, {
										fieldLabel : '标准名称',
										readOnly : true,
										name : 'standSalary.standardName'
									}]
						}, {
							xtype : 'container',
							style : 'padding-left:0px;',
							layout : 'column',
							defaultType : 'textfield',
							autoWidth : true,
							height : 26,
							defaults : {
								width : 150
							},
							items : [{
										xtype : 'label',
										text : '薪资总额',
										style : 'padding-left:0px;padding-top:3px;',
										width : 71
									}, {
										name : 'standSalary.totalMoney',
										readOnly : true
									}, {
										xtype : 'label',
										text : '标准制定人',
										style : 'padding-left:0px;padding-top:3px;',
										width : 71
									}, {
										name : 'standSalary.framer',
										readOnly : true,
										value : curUserInfo.fullname
									}]
						}, {
							fieldLabel : '备注',
							name : 'standSalary.memo',
							readOnly : true,
							width : 370,
							xtype : 'textarea'
						}, {
							fieldLabel : '审核意见',
							name : 'standSalary.checkOpinion',
							allowBlank : false,
							blankText : '审核意见不可为空!',
							width : 370,
							xtype : 'textarea'
						}]
					}, this.grid]
		});
		if (this.standardId != null && this.standardId != 'undefined') {
			this.formPanel.loadData({
						preName : 'standSalary',
						root : 'data',
						url : __ctxPath + '/hrm/getStandSalary.do?standardId='
								+ this.standardId,
						waitMsg : '正在载入数据...'
					});
		};
	},// end initUIComponents
	/**
	 * 审核通过
	 */
	checkPassClick : function() {
		this.save('1');// 状态参数1,表审核通过
	},
	/**
	 * 审核未通过
	 */
	checkUnpassClick : function() {
		this.save('2');// 状态参数1,表审核通过
	},
	save : function(status) {
		$postForm({
					formPanel : this.formPanel,
					waitMsg : '正在提交数据...',
					scope : this,
					url : __ctxPath + '/hrm/checkStandSalary.do',
					params : {
						status : status
					},
					callback : function(fp, action) {
						if (this.callback) {
							this.callback.call(this.scope);
						}
						this.close();
					}
				});
	}
});