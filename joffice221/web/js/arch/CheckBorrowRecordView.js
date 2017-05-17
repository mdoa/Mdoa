/**
 * @author:
 * @class CheckBorrowRecordView
 * @extends Ext.Panel
 * @description [RollFile]管理
 * @company 杭州梦德软件有限公司
 * @createtime:
 */
CheckBorrowRecordView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		CheckBorrowRecordView.superclass.constructor.call(this, {
			id : 'CheckBorrowRecordView',
			iconCls : 'menu-checkBorrowRecord',
			title : '借阅审批',
			region : 'center',
			layout : 'border',
			defaults : {
				anchor : '96%,96%'
			},
			listeners : {
				'afterlayout' : function(CheckBorrowRecordView) {
					CheckBorrowRecordView.search();
				}
			},
			items : [ this.searchPanel, this.gridPanel ],
			keys : {
				key : Ext.EventObject.ENTER,
				fn : this.search, 
				scope : this
			}
		});
	},// end of constructor
	// 初始化组件
	initUIComponents : function() {
		this.searchPanel = new HT.SearchPanel({
			layout : 'form',
			region : 'north',
			colNums : 8,
			keys : {
				key : Ext.EventObject.ENTER,
				fn : this.search,
				scope : this
			},
			labelWidth : 70, // 一个bug
			items : [{
						fieldLabel : '借阅目的',
						width : 80,
						name : 'Q_borrowReason_S_LK',
						editable : false,
						lazyInit : false,
						forceSelection : false,
						xtype : 'diccombo',
						nodeKey : 'borrow_purpose'
					}, {
						fieldLabel : '借阅状态',
						labelWidth : 65, 
						width : 80,
						hiddenName : 'Q_returnStatus_SN_EQ',
						xtype : 'combo',
						mode : 'local',
						value : '0',
						editable : false,
						triggerAction : 'all',
						store : [ [ '', '全部' ], [ '0', '申请' ], [ '1', '通过' ],
								[ '-1', '驳回' ], [ '2', '归还' ] ]
					},{
						fieldLabel : '借阅时间:从',
						width : 80,
						labelWidth : 75, 
						name : 'Q_borrowDate_D_GE',
						xtype : 'datefield',
						format : 'Y-m-d'
					},{
						fieldLabel : '至',
						width : 80,
						labelWidth : 20, 
						name : 'Q_borrowDate_D_LE',
						xtype : 'datefield',
						format : 'Y-m-d'
					},{
						fieldLabel : '应还日期:从',
						width : 80,
						labelWidth : 75, 
						name : 'Q_returnDate_D_GE',
						xtype : 'datefield',
						format : 'Y-m-d'
					},{
						fieldLabel : '至',
						labelWidth : 20, 
						width : 80,
						name : 'Q_returnDate_D_LE',
						xtype : 'datefield',
						format : 'Y-m-d'
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

		this.gridPanel = new HT.GridPanel( {
			region : 'center',
			tbar : [ {
				iconCls : 'btn-ok',
				text : '审批',
				xtype : 'button',
				scope : this,
				hidden:!isGranted('_CheckBorrowRecordViewCheck'),
				handler : this.check
			} ],
			rowActions : true,
			fields:[ {
				name : 'recordId',
				type : 'int'
			}, 'borrowDate', 'borrowType', 'borrowReason', 'checkUserId',
					'checkUserName', 'checkDate', 'returnDate', 'returnStatus',
					'borrowNum', 'checkUserName' ],
			url : __ctxPath + "/arch/listBorrowRecord.do",
			columns : [ {
				header : 'recordId',
				dataIndex : 'recordId',
				hidden : true
			}, {
				header : '借阅编号',
				dataIndex : 'borrowNum'
			}, {
				header : '借阅人',
				dataIndex : 'checkUserName'
			}, {
				header : '借阅日期',
				width : 60,
				dataIndex : 'borrowDate'
			},{
				header : '应还日期',
				width : 60,
				dataIndex : 'returnDate'
			}, {
				header : '借阅方式',
				width : 60,
				dataIndex : 'borrowType'
			}, {
				header : '借阅目的',
				width : 60,
				dataIndex : 'borrowReason'
			}, {
				header : '归还状态',
				width : 60,
				dataIndex : 'returnStatus',
				renderer : function(returnStatus) {
					switch (returnStatus) {
					case 0:
						return '申请';
						break;
					case 1:
						return '通过';
						break;
					case -1:
						return '驳回';
						break;
					case 2:
						return '归还';
						break;
					}
				}
			}, new Ext.ux.grid.RowActions( {
				header : '管理',
				width : 100,
				actions : [ {
					iconCls : 'btn-ok',
					qtip : '审批',
					style : 'margin:0 3px 0 3px',
					fn:!isGranted('_CheckBorrowRecordViewCheck')
				}, {}],
				listeners : {
					scope : this,
					'action' : this.onRowAction
				}
			}) ]

		});

		this.gridPanel.addListener('rowdblclick', this.rowClick);

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
		grid.getSelectionModel().each(function(rec) {
			new CheckBorrowRecordForm({
				recordId : rec.data.recordId,
				returnStatus : rec.data.returnStatus,
				borrowNum:rec.data.borrowNum
			}).show();
		});
	},

	// 按ID删除记录

	// 把选中ID审核
	check : function() {
		var gridPanel=this.gridPanel;
		var r = gridPanel.getSelectionModel().getSelections()[0];
		if(!r){
			Ext.ux.Toast.msg("操作信息", "审核清单不能为空!");
			return;
		}

		new CheckBorrowRecordForm({
			recordId : r.data.recordId,
			returnStatus : r.data.returnStatus,
			borrowNum:r.data.borrowNum,
		   callback:function(){
			   gridPanel.getStore().reload();
		   }
		}).show();
	},

	// 编辑Rs
	editRs : function(rec,gridPanel) {
		new CheckBorrowRecordForm({
			recordId : rec.data.recordId,
			returnStatus : rec.data.returnStatus,
			borrowNum:rec.data.borrowNum,
			callback:function(){
				gridPanel.getStore().reload();
			}
		}).show();
	},
	
	// 行的Action
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
		case 'btn-ok':
			this.editRs.call(this, record,grid);
			break;
		default:
			break;
		}
	}
});
