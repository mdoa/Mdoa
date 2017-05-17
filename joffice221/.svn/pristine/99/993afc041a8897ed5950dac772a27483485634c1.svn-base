/**
 * @author
 * @createtime
 * @class GoodsApplyForm
 * @extends Ext.Window
 * @description GoodsApplyForm表单
 * @company 宏天软件
 */
GoodsCheckForm = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		// 调用父类构造
		GoodsCheckForm.superclass.constructor.call(this, {
					layout : 'fit',
					id : 'GoodsCheckFormWin',
					title : '办公用品审批详细信息',
					iconCls:'menu-goods-apply',
					width : 450,
					height : 250,
					minWidth:449,
					minHeight:249,
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
						widht : 450,
						anchor : '98%,98%'
					},
					bodyStyle : 'padding-top:5px;padding-left:5px;',
					formId : 'CarApplyFormId',
					defaultType : 'displayfield',
					items : [{
								name : 'goodsApply.applyId',
								xtype : 'hidden',
								value : this.applyId == null ? '' : this.applyId
							}, {
								name : 'goodsApply.officeGoods.goodsId',
						  		xtype : 'hidden'
							}, {
								name : 'goodsApply.userId',
								xtype : 'hidden'
							}, {
								fieldLabel : '申请号',
								name : 'goodsApply.applyNo'
							}, {
								fieldLabel : '商品名称',
								name : 'goodsApply.officeGoods.goodsName'
							}, {
								fieldLabel : '申请日期',
								name : 'goodsApply.applyDate'
							}, {
								fieldLabel : '申请数量',
								name : 'goodsApply.useCounts'
							}, {
								fieldLabel : '申请人',
								name : 'goodsApply.proposer'
							}, {
								fieldLabel : '审批状态 ',
								name : 'goodsApply.approvalStatus',
								xtype:'hidden'
							}, {
								fieldLabel : '备注',
								name : 'goodsApply.notes'
							}]
				});// end of the formPanel

		// 加载表单对应的数据
		if (this.applyId != null && this.applyId != 'undefined') {
			this.formPanel.loadData({
						url : __ctxPath + '/admin/getGoodsApply.do?applyId=' + this.applyId,
						root : 'data',
						preName : 'goodsApply'
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
		// 验证
		if (formPanel.getForm().isValid()) {
			// 提交
			$postForm({
						formPanel : formPanel,
						scope : this,
						url : __ctxPath + '/admin/saveGoodsApply.do',
						callback : function(fp, action) {
							if (this.callback) {
								this.callback.call(this.scope);
							}
							this.close();
						}
					});
		}// end of save
	},
	/**
	 * 通过审批
	 */
	passCheck : function() {
		this.formPanel.getCmpByName('goodsApply.approvalStatus').setValue(2);
		this.saveRecord();
	},
	/**
	 * 不通过审批
	 */
	notpassCheck : function() {
		this.formPanel.getCmpByName('goodsApply.approvalStatus').setValue(3);
		this.saveRecord();
	}
});
