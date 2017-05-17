/**
 * @author:
 * @class RecoveryProfileWin
 * @extends Ext.Panel
 * @description 档案管理-档案恢复
 * @company 杭州梦德软件有限公司
 * @createtime:2010-01-16
 */
RecoveryProfileWin = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		// 初始化组件
		this.initComponents();
		// 调用父类构造
		RecoveryProfileWin.superclass.constructor.call(this, {
					id : 'RecoveryProfileWin',
					iconCls : 'btn-empProfile-recovery',
					title : '档案恢复',
					border : false,
					region : 'center',
					width : 760,
					modal : true,
					height : 450,
					layout : 'border',
					maximizable : true,
					items : [this.searchPanel, this.gridPanel],
					bottons : this.buttons,
					buttonAlign : 'center'
				});
	},// end of constructor

	// 初始化组件
	initComponents : function() {
		this.buttons = [{
					iconCls : 'btn-empProfile-recovery',
					text : '恢复档案',
					xtype : 'button',
					scope : this,
					handler : this.recoveryRecord
				}, {
					text : '取消',
					iconCls : 'btn-cancel',
					scope : this,
					handler : this.cancel
				}];
		this.searchPanel = new HT.SearchPanel({
					layout : 'form',
					region : 'north',
					colNums : 6,
					keys : {
						key : Ext.EventObject.ENTER,
						fn : this.search,
						scope : this
					},
					labelWidth : 125, // 一个bug
					items : [{
								name : 'Q_delFlag_SN_EQ',
								xtype : 'hidden',
								value : 1
							}, {
								fieldLabel : '查询条件：档案编号',
								xtype : 'textfield',
								name : 'Q_profileNo_S_LK',
								width : 80,
								maxLength : 150
							}, {
								fieldLabel : '员工姓名',
								xtype : 'textfield',
								name : 'Q_fullname_S_LK',
								maxLength : 125,
								width : 70,
								labelWidth : 70
							}, {
								fieldLabel : '身份证号',
								xtype : 'textfield',
								name : 'Q_idCard_S_LK',
								maxLength : 125,
								width : 80,
								labelWidth : 70
							}, {
								fieldLabel : '审核状态',
								xtype : 'combo',
								hiddenName : 'Q_approvalStatus_SN_EQ',
								maxLength : 125,
								width : 80,
								labelWidth : 70,
								mode : 'local',
								triggerAction : 'all',
								store : [['', '　'], ['0', '未审核'],
										['1', '审核通过'], ['2', '审核未通过']]
							}, {
								xtype : 'button',
								text : '查询',
								iconCls : 'search',
								scope : this,
								handler : this.search
							}]
				});

		// 列表面板
		this.gridPanel = new HT.GridPanel({
			region : 'center',
			url : __ctxPath + "/hrm/listEmpProfile.do",
			baseParams : {
				"Q_delFlag_SN_EQ" : 1
			},// 只查询被删除的档案
			fields : [{
						name : 'profileId',
						type : 'int'
					}, 'profileNo', 'fullname', 'designation', 'creator',
					'createtime', 'approvalStatus', 'memo', 'depName'],
			columns : [{
						header : 'profileId',
						dataIndex : 'profileId',
						hidden : true
					}, {
						header : '档案编号',
						dataIndex : 'profileNo'
					}, {
						header : '员工姓名',
						dataIndex : 'fullname'
					}, {
						header : '建档人',
						dataIndex : 'creator'
					}, {
						header : '建档时间',
						dataIndex : 'createtime'
					}, {
						header : '部门或公司',
						dataIndex : 'depName'
					}, {
						header : '职称',
						dataIndex : 'designation'
					}, {
						header : '审核状态',// 0=未删除 1=删除',
						dataIndex : 'approvalStatus',
						renderer : function(value) {
							if (value == '0') {
								return '未审核';
							} else if (value == '1') {
								return '<img title="通过审核" src="'
										+ __ctxPath
										+ '/images/flag/customer/effective.png"/>';
							} else {
								return '<img title="没通过审核" src="'
										+ __ctxPath
										+ '/images/flag/customer/invalid.png"/>';
							}
						}
					}]
		});
	},// end of the initComponents()

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
	 * 恢复记录
	 * 
	 */
	recoveryRecord : function() {
		var grid = this.gridPanel;

		var selectRecords = grid.getSelectionModel().getSelections();

		if (selectRecords.length == 0) {
			Ext.Msg.alert("信息", "请选择要恢复的档案记录！");
			return;
		}
		var ids = Array();
		for (var i = 0; i < selectRecords.length; i++) {
			ids.push(selectRecords[i].data.profileId);
		}
		Ext.Msg.confirm('信息确认', '您确认要恢复选中档案记录吗？', function(btn) {
					if (btn == 'yes') {
						Ext.Ajax.request({
									url : __ctxPath
											+ '/hrm/recoveryEmpProfile.do',
									params : {
										ids : ids
									},
									scope : this,
									method : 'POST',
									success : function(response, options) {
										Ext.ux.Toast.msg('操作信息', '成功恢复档案！');
										if (this.callback) {
											this.callback.call(this.scope);
										}
										this.close();
									},
									failure : function(response, options) {
										Ext.ux.Toast
												.msg('操作信息', '操作出错，请联系管理员！');
									}
								});
					}
				}, this);// end of comfirm
	},
	/**
	 * 关闭窗口事件
	 */
	cancel : function() {
		this.close();
	}
});
