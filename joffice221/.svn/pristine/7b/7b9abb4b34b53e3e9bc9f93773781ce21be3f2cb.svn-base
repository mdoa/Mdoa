Ext.ns('DutyRegisterPersonView');

var weekdays = ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
/**
 * 个人考勤
 * @class
 * @extends Ext.Panel
 */
DutyRegisterPersonView = Ext.extend(Ext.Panel, {
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		this.initUIComponents();
		DutyRegisterPersonView.superclass.constructor.call(this, {
					id : 'DutyRegisterPersonView',
					title : '个人考勤查询列表',
					region : 'center',
					iconCls : 'menu-person',
					layout : 'border',
					items : [this.searchPanel, this.gridPanel]
				});
	},
	//初始化组件
	initUIComponents : function() {
		this.searchPanel = new HT.SearchPanel({
			region : 'north',
			layout : 'form',
			colNums : 6,
			keys : [{
						key : Ext.EventObject.ENTER,
						fn : this.search,
						scope : this
					}, {
						key : Ext.EventObject.ESC,
						fn : this.reset,
						scope : this
					}],
			labelWidth : 155,
			items : [{
						fieldLabel : '请输入查询条件:登记时间从',
						xtype : 'datefield',
						format : 'Y-m-d',
						name : 'Q_registerDate_DL_GE'
					}, {
						fieldLabel : '至',
						xtype : 'datefield',
						format : 'Y-m-d',
						name : 'Q_registerDate_DG_LE',
						labelWidth : 20
					}, {
						fieldLabel : '上下班',
						hiddenName : 'Q_inOffFlag_SN_EQ',
						xtype : 'combo',
						width : 100,
						mode : 'local',
						editable : true,
						triggerAction : 'all',
						store : [['1', '上班'], ['2', '下班']],
						labelWidth : 55
					}, {
						fieldLabel : '至',
						xtype : 'datefield',
						format : 'Y-m-d',
						name : 'Q_accessionTime_D_LT',
						maxLength : 150,
						labelWidth : 20
					}, {
						xtype : 'button',
						text : '查询',
						iconCls : 'search',
						scope : this,
						handler : this.search
					}, {
						xtype : 'button',
						scope : this,
						text : '重置',
						iconCls : 'btn-reseted',
						handler : this.reset
					}]
		});// end of search panel

		this.gridPanel = new HT.GridPanel({
			region : 'center',
			url : __ctxPath + '/personal/personDutyRegister.do',
			fields : [{
						name : 'registerId',
						type : 'int'
					}, 'registerDate', 'fullname', 'regFlag',
					'regMins', 'reason', 'dayOfWeek',
					'inOffFlag'],
			columns : [new Ext.grid.RowNumberer(), {
						header : 'registerId',
						dataIndex : 'registerId',
						hidden : true
					}, {
						header : '登记时间',
						dataIndex : 'registerDate'
					}, {
						header : '登记人',
						dataIndex : 'fullname'
					}, {
						header : '登记标识',
						dataIndex : 'regFlag',
						renderer : this.regFlag
					}, {
						header : '周次',
						dataIndex : 'dayOfWeek',
						renderer : this.dayOfWeek
					}, {
						header : '上下班标识',
						dataIndex : 'inOffFlag',
						renderer : this.inOffFlag
					}]
				});
	},
	/**
	 * 重置
	 */
	reset : function() {
		this.searchPanel.getForm().reset();
	},
	/**
	 * 查询
	 */
	search : function() {
		$search({
					searchPanel : this.searchPanel,
					gridPanel : this.gridPanel
				});
	},
	/**
	 *登记标识
	 * @param {} val
	 * @return {String}
	 */
	regFlag : function(val) {
		if (val == 1) {
			return '<font color="green">√</font>';
		} else if (val == 2) {
			return '<font color="red">迟到</font>';
		} else if (val == 3) {
			return '<font color="red">早退</font>';
		}
	},
	/**
	 * 上下班
	 * @param {} val
	 * @return {String}
	 */
	inOffFlag : function(val) {
		if (val == 1) {
			return "上班";
		} else {
			return "下班";
		}
	},
	/**
	 * 周次
	 * @param {} val
	 * @return {}
	 */
	dayOfWeek : function(val) {
		return weekdays[val - 1];
	}

});