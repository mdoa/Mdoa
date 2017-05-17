Ext.ns('PersonalSalaryView');
/**
 * @author:
 * @class PersonalSalaryView
 * @extends Ext.Panel
 * @description 薪酬发放管理
 * @company 杭州梦德软件有限公司
 * @createtime:2010-02-01
 */
PersonalSalaryView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(conf) {
		Ext.apply(this, conf);
		// 初始化组件
		this.initComponents();
		// 调用父类构造
		PersonalSalaryView.superclass.constructor.call(this, {
					id : 'PersonalSalaryView',
					title : '个人薪酬记录',
					region : 'center',
					iconCls : 'menu-personal-salary',
					layout : 'border',
					items : [this.searchPanel, this.gridPanel]
				});
	},// end of constructor

	initComponents : function() {
		// 初始化搜索条件Panel
		this.searchPanel = new HT.SearchPanel({
					region : 'north',
					layout : 'form',
					colNums : 4,
					keys : [{
								key : Ext.EventObject.ENTER,
								fn : this.search,
								scope : this
							}, {
								key : Ext.EventObject.ESC,
								fn : this.reset,
								scope : this
							}],
					labelWidth : 115,
					items : [{
								fieldLabel : '查询条件：日期从',
								xtype : 'datefield',
								format : 'Y-m-d',
								name : 'Q_startTime_D_GE',
								editable : false,
								width : 100
							}, {
								fieldLabel : '到',
								xtype : 'datefield',
								format : 'Y-m-d',
								name : 'Q_endTime_D_LE',
								editable : false,
								labelWidth : 15,
								width : 100
							}, {
								text : '查询',
								xtype : 'button',
								scope : this,
								iconCls : 'search',
								handler : this.search
							}, {
								text : '重置',
								xtype : 'button',
								scope : this,
								iconCls : 'btn-reset',
								handler : this.reset
							}]
				});// end of searchPanel

		// 初始化ColumnModel
		var expander = new Ext.ux.grid.RowExpander({
			tpl : new Ext.Template('<div style="padding:5px 5px 5px 62px;">{content}</div>')
		});
		this.gridPanel = new HT.GridPanel({
					region : 'center',
					height : 500,
					expander : expander,
					showChbCol : false,
					url : __ctxPath + "/hrm/personalSalaryPayoff.do",
					baseParams : {
						'Q_userId_L_EQ' : curUserInfo.userId,
						'Q_checkStatus_SN_EQ' : 1
						// 只查已通过审核的
					},
					sort : [{
								field : "startTime",
								direction : "DESC"
							}],
					fields : [{
								name : 'recordId',
								type : 'int'
							}, 'fullname', 'userId', 'profileNo', 'idNo',
							'standAmount', 'acutalAmount', 'checkStatus',
							'startTime', 'endTime', 'content'],
					columns : [new Ext.grid.RowNumberer(), expander, {
								header : 'recordId',
								dataIndex : 'recordId',
								hidden : true
							}, {
								header : '员工姓名',
								width : 60,
								dataIndex : 'fullname'
							}, {
								header : '档案编号',
								width : 150,
								dataIndex : 'profileNo'
							}, {
								header : '身份证号',
								width : 120,
								dataIndex : 'idNo'
							}, {
								header : '薪标金额',
								width : 60,
								dataIndex : 'standAmount',
								renderer : function(value) {
									return '<img src="'
											+ __ctxPath
											+ '/images/flag/customer/rmb.png"/>'
											+ value;
								}
							}, {
								header : '实发金额',
								width : 60,
								dataIndex : 'acutalAmount',
								renderer : function(value) {
									return '<img src="'
											+ __ctxPath
											+ '/images/flag/customer/rmb.png"/>'
											+ value;
								}
							}, {
								header : '薪酬日期',
								width : 130,
								dataIndex : 'startTime',
								renderer : function(value, metadata, record,
										rowIndex, colIndex) {
									return value.substring(0, 10)
											+ '至'
											+ record.data.endTime.substring(0,
													10);
								}
							}]
				});

	},// end of the initComponents()

	/**
	 * 
	 * @param {}
	 *            self 当前窗体对象
	 */
	search : function() {
		$search({
					searchPanel : this.searchPanel,
					gridPanel : this.gridPanel
				});
	},
	/**
	 * 重置查询表单
	 */
	reset : function() {
		this.searchPanel.getForm().reset();
	}
});
