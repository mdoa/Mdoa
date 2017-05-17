Ext.ns('DutyRegisterView');
/**
 * 考勤管理
 */
DutyRegisterView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		DutyRegisterView.superclass.constructor.call(this, {
					id : 'DutyRegisterView',
					title : '考勤管理',
					region : 'center',
					iconCls : 'menu-dutyRegister',
					layout : 'border',
					items : [this.searchPanel, this.gridPanel]
				});
	},// end of constructor
	// 初始化组件
	initUIComponents : function() {
		// 初始化搜索条件Panel
		this.searchPanel = new HT.SearchPanel({
					layout : 'form',
					region : 'north',
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
					labelWidth : 115,
					items : [{
								xtype : 'hidden',
								name : 'Q_appUser.userId_L_EQ'
							}, {
								fieldLabel : '查询条件:   上下班',
								hiddenName : 'Q_inOffFlag_SN_EQ',
								xtype : 'combo',
								width : 150,
								mode : 'local',
								editable : true,
								triggerAction : 'all',
								store : [['1', '上班'], ['2', '下班']]
							}, {
								fieldLabel : '所属用户',
								xtype : 'textfield',
								readOnly : true,
								name : 'DR_fullname',
								width : 150,
								labelWidth : 60
							}, {
								xtype : 'button',
								text : '选择',
								iconCls : 'btn-user-sel',
								width : 50,
								scope : this,
								// 人员选择器
								handler : this.userSelector
							}, {
								fieldLabel : '考勤选项',
								hiddenName : 'Q_regFlag_SN_EQ',
								xtype : 'combo',
								width : 150,
								labelWidth : 60,
								mode : 'local',
								editable : true,
								triggerAction : 'all',
								store : [['1', '√'], ['2', '迟到'], ['3', '早退']]
							}, {
								xtype : 'button',
								text : '查询',
								iconCls : 'search',
								scope : this,
								handler : this.search
							}, {
								xtype : 'button',
								text : '清空',
								iconCls : 'reset',
								scope : this,
								handler : this.reset
							}]
				});

		this.topbar = new Ext.Toolbar({
					items : [{
								iconCls : 'btn-add',
								text : '补签',
								xtype : 'button',
								scope : this,
								handler : this.signed
							}, '-', {
								iconCls : 'btn-del',
								text : '删除考勤',
								xtype : 'button',
								scope : this,
								handler : this.removeSelRs
							}]
				});

		// 外出登记面板
		var weekdays = ['周日', '周一', '周二', '周三', '周四', '周五', '周六'];
		this.gridPanel = new HT.GridPanel({
			region : 'center',
			tbar : this.topbar,
			sort : [{
						field : "registerDate",
						direction : "DESC"
					}],
			// 使用RowActions
			rowActions : true,
			url : __ctxPath + '/personal/listDutyRegister.do',
			fields : [{
						name : 'registerId',
						type : 'int'
					}, 'registerDate', 'fullname', 'regFlag', 'regMins',
					'reason', 'dayOfWeek', 'inOffFlag'],
			columns : [{
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
						renderer : function(val) {
							if (val == 1) {
								return '<font color="green">√</font>';
							} else if (val == 2) {
								return '<font color="red">迟到</font>';
							} else if (val == 3) {
								return '<font color="red">早退</font>';
							}
						}
					}, {
						header : '周几',
						dataIndex : 'dayOfWeek',
						renderer : function(val) {
							return weekdays[val - 1];
						}
					}, {
						header : '上下班标识',
						dataIndex : 'inOffFlag',
						renderer : function(val) {
							if (val == 1) {
								return "上班";
							} else {
								return "下班";
							}
						}
					}, {
						header : '迟到或早退分钟',
						dataIndex : 'regMins'
					}, {
						header : '迟到或早退原因',
						dataIndex : 'reason'
					}, new Ext.ux.grid.RowActions({
								header : '管理',
								width : 100,
								actions : [{
											iconCls : 'btn-del',
											qtip : '删除',
											style : 'margin:0 3px 0 3px',
											fn : function(record) {
												if (isGranted('_DutyRegisterDel'))
													return true;
												return false;
											}
										}],
								listeners : {
									scope : this,
									'action' : this.onRowAction
								}
							})]
		});

	},// end of the initComponents()
	// 重置查询表单
	reset : function() {
		this.searchPanel.getForm().reset();
	},
	// 按条件搜索
	search : function() {
		$search({
					searchPanel : this.searchPanel,
					gridPanel : this.gridPanel
				});
	},
	// 按ID删除记录
	removeRs : function(id) {
		$postDel({
					url : __ctxPath + '/personal/multiDelDutyRegister.do',
					ids : id,
					grid : this.gridPanel
				});
	},
	// 把选中ID删除
	removeSelRs : function() {
		$delGridRs({
					url : __ctxPath + '/personal/multiDelDutyRegister.do',
					grid : this.gridPanel,
					idName : 'registerId'
				});
	},
	// 行的Action
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.removeRs.call(this, record.data.registerId);
				break;
			default :
				break;
		}
	},

	/**
	 * 人员选择器
	 */
	userSelector : function() {
		var searchPanel = this.searchPanel;
		new UserDialog({
					scope : this,
					single : true,
					callback : function(ids, names) {
						var userId = searchPanel
								.getCmpByName('Q_appUser.userId_L_EQ')
								.setValue(ids);
						var fullname = searchPanel.getCmpByName('DR_fullname')
								.setValue(names);
					}
				}).show();// true表示单选
	},

	/**
	 * 补签
	 */
	signed : function() {
		new DutyRegisterForm({
					scope : this,
					callback : this.reloadType
				}).show();
	},
	// 刷新gridPanel
	reloadType : function() {
		this.gridPanel.getStore().reload();
	}

});
