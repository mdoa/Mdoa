/**
 * @author
 * @createtime
 * @class CarApplyForm
 * @extends Ext.Window
 * @description CarApplyForm表单
 * @company 宏天软件
 */
CarCheckForm = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		// 调用父类构造
		CarCheckForm.superclass.constructor.call(this, {
					layout : 'fit',
					id : 'CarCheckFormWin',
					title : '车辆申请审批详细信息',
					iconCls : 'menu-car_apply',
					width : 600,
					height : 480,
					minWidth : 599,
					minHeight : 479,
					items : this.formPanel,
					maximizable : true,
					border : false,
					modal : true,
					plain : true,
					buttonAlign : 'center',
					buttons : this.buttons
				});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		this.formPanel = new Ext.FormPanel({
					layout : 'form',
					frame : false,
					border : true,
					defaults : {
						widht : 400,
						anchor : '98%,98%'
					},
					bodyStyle : 'padding-top:5px;padding-left:5px;',
					defaultType : 'displayfield',
					items : [{
								name : 'carApply.applyId',
								xtype : 'hidden',
								value : this.applyId == null
										? ''
										: this.applyId
							}, {
								xtype : 'hidden',
								name : 'carApply.car.carId'
							}, {
								xtype : 'hidden',
								name : 'carApply.userId'
							}, {
								fieldLabel : '车牌号码',
								name : 'carApply.car.carNo'
							}, {
								fieldLabel : '用车部门',
								name : 'carApply.department'
							}, {
								fieldLabel : '用车人',
								name : 'carApply.userFullname'
							}, {
								fieldLabel : '申请人',
								name : 'carApply.proposer'
							}, {
								fieldLabel : '申请时间',
								name : 'carApply.applyDate'
							}, {
								fieldLabel : '原因',
								name : 'carApply.reason'
							}, {
								fieldLabel : '审批状态',
								name : 'carApply.approvalStatus',
								xtype : 'hidden'
							}, {
								fieldLabel : '开始时间',
								name : 'carApply.startTime'
							}, {
								fieldLabel : '结束时间',
								name : 'carApply.endTime'
							}, {
								fieldLabel : '里程',
								name : 'carApply.mileage'
							}, {
								fieldLabel : '油耗',
								name : 'carApply.oilUse'
							}, {
								fieldLabel : '备注',
								name : 'carApply.notes'
							}]
				});// end of the formPanel

		// 加载表单对应的数据
		if (this.applyId != null && this.applyId != 'undefined') {
			this.formPanel.loadData({
						url : __ctxPath + '/admin/getCarApply.do?applyId='
								+ this.applyId,
						root : 'data',
						preName : 'carApply'
					});
		};
		//显示的按钮
		if (this.isView) {
			this.buttons = [{
						text : '关闭',
						iconCls : 'btn-cancel',
						scope : this,
						handler : function() {
							this.close();
						}
					}];
		} else {
			this.buttons = [{
						text : '通过审批',
						iconCls : 'btn-save',
						scope : this,
						handler : this.passCheck
					}, {
						text : '不通过审批',
						iconCls : 'btn-save',
						scope : this,
						handler : this.notpassCheck
					}, {
						text : '取消',
						iconCls : 'btn-cancel',
						scope : this,
						handler : function() {
							this.close();
						}
					}];// end of the buttons
		}
	},// end of the initUIComponents
	/**
	 * 保存
	 */
	saveRecord : function() {
		var formPanel = this.formPanel;
		// 提交
		$postForm({
					formPanel : formPanel,
					scope : this,
					url : __ctxPath + '/admin/saveCarApply.do',
					callback : function(fp, action) {
						if (this.callback) {
							this.callback.call(this.scope);
						}
						this.close();
					}
				});
	},
	/**
	 * 通过审批
	 */
	passCheck : function() {
		this.formPanel.getCmpByName('carApply.approvalStatus').setValue(2);
		this.saveRecord();
	},
	/**
	 * 不通过审批
	 */
	notpassCheck : function() {
		this.formPanel.getCmpByName('carApply.approvalStatus').setValue(3);
		this.saveRecord();
	}
});
