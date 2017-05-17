/**
 * @author:
 * @class LeaveManageView
 * @extends Ext.Panel
 * @description 请假管理
 * @company 杭州梦德软件有限公司
 * @createtime:
 */
LeaveManageView = Ext.extend(Ext.Panel, {
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 初始化组件
				this.initUIComponents();
				// 调用父类构造
				LeaveManageView.superclass.constructor.call(this, {
							id : 'LeaveManageView',
							title : '请假管理',
							region : 'center',
							layout : 'border',
							iconCls : 'menu-holiday',
							items : [this.searchPanel, this.gridPanel]
						});
			},// end of constructor
			// 初始化组件
			initUIComponents : function() {
				// 初始化搜索条件Panel
				this.searchPanel = new HT.SearchPanel({
							region : 'north',
							layout : 'form',
							colNums : 5,
							keys : [{
										key : Ext.EventObject.ENTER,
										fn : this.search,
										scope : this
									}, {
										key : Ext.EventObject.ESC,
										fn : this.reset,
										scope : this
									}],
							labelWidth : 135,
							items : [{
										fieldLabel : '查询条件: 开始时间: 从',
										xtype : 'datetimefield',
										format : 'Y-m-d H:i:s',
										name : 'Q_startTime_D_GE',
										editable : false,
										width : 200
									}, {
										fieldLabel : '到',
										xtype : 'datetimefield',
										format : 'Y-m-d H:i:s',
										name : 'Q_endTime_D_LE',
										editable : false,
										labelWidth : 15,
										width : 200
									}, {
										fieldLabel : '审批状态',
										xtype : 'combo',
										hiddenName : 'Q_status_SN_EQ',
										mode : 'local',
										width : 100,
										labelWidth : 60,
										editable : false,
										triggerAction : 'all',
										store : [['0', '未审批'], ['1', '通过审批'],
												['2', '未通过审批']]
									}, {
										text : '查询',
										xtype : 'button',
										scope : this,
										iconCls : 'btn-search',
										handler : this.search
									}, {
										text : '重置',
										xtype : 'button',
										scope : this,
										iconCls : 'btn-reset',
										handler : this.reset
									}]
						});// end of searchPanel

				this.gridPanel = new HT.GridPanel({
					region : 'center',
					height : 500,
					sort : [{
						field : "dateId",
						direction : "DESC"
					}],
					// 使用RowActions
					rowActions : true,
					url : __ctxPath
							+ '/personal/listErrandsRegister.do',
					baseParams : {
						'Q_approvalId_L_EQ' : curUserInfo.userId,
						'Q_flag_SN_EQ' : 1
					},
					fields : [{
								name : 'dateId',
								type : 'int'
							},{
								name : 'userName',
								mapping : 'appUser.fullname'
							}, 'descp', 'startTime', 'endTime', 'approvalId',
							'status', 'approvalOption', 'approvalName', 'flag'],
					columns : [{
								header : 'dateId',
								dataIndex : 'dateId',
								hidden : true
							}, {
								header : '描述',
								dataIndex : 'descp'
							}, {
								header : '开始日期',
								dataIndex : 'startTime'
							}, {
								header : '结束日期',
								dataIndex : 'endTime'
							}, {
								header : '审批状态',
								dataIndex : 'status',
								renderer : function(value) {
									if (value == '0') {
										return '未审批';
									}
									if (value == '1') {
										return '<font color="green">通过审批</font>';
									}
									if (value == '2') {
										return '<font color="red">未通过审批</font>';
									}
								}
							}, {
								header : '审批意见',
								dataIndex : 'approvalOption'
							}, {
								header : '审批人',
								dataIndex : 'approvalName'
							}, new Ext.ux.grid.RowActions({
										header : '管理',
										width : 100,
										actions : [{
													iconCls : 'btn-suggest-scan',qtip : '预览',stype : 'margin:0 3px 0 3px'
												}],
										listeners : {
											scope : this,
											'action' : this.onRowAction
										}
									})]
						// end of columns
				});

				this.gridPanel.addListener({scope:this,'rowdblclick': this.rowClick});

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
			// GridPanel行点击处理事件
			rowClick : function(grid, rowindex, e) {
				var rec = grid.getStore().getAt(rowindex);
				this.detailRs.call(this, rec);
			},
			// 预览Rs
			detailRs : function(record) {
				new LeaveManageWin({
							dateId : record.data.dateId
						}).show();
			},
			// 行的Action
			onRowAction : function(grid, record, action, row, col) {
				switch (action) {
					case 'btn-suggest-scan' :
						this.detailRs.call(this, record);
						break;
					default :
						break;
				}
			}
		});
