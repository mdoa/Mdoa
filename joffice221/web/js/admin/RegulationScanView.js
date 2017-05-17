/**
 * @author:
 * @class RegulationScanView
 * @extends Ext.Panel
 * @description 我的桌面-规章制度管理
 * @company 杭州梦德软件有限公司
 * @createtime:
 */
RegulationScanView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		RegulationScanView.superclass.constructor.call(this, {
					id : 'RegulationScanView',
					title : '规章制度',
					iconCls : 'menu-regulation',
					region : 'center',
					layout : 'border',
					items : [this.searchPanel, this.gridPanel]
				});
	},// end of constructor
	// 初始化组件
	initUIComponents : function() {
		// 初始化搜索条件Panel
		this.searchPanel = new HT.SearchPanel({
					region : 'north',
					layout : 'form',
					colNums : 7,
					keys : [{
						key : Ext.EventObject.ENTER,
						fn : this.search,
						scope : this
					}, {
						key : Ext.EventObject.ESC,
						fn : this.reset,
						scope : this
					}],
                    labelWidth : 35,
					items : [{
								fieldLabel : '标题',
								name : 'Q_subject_S_LK',
								xtype : 'textfield'
							},{
								fieldLabel : '日期  从',
								name : 'Q_issueDate_D_GE',
								xtype : 'datefield',
								format : 'Y-m-d',
								labelWidth : 50
							},{
								fieldLabel : '至',
								name : 'Q_issueDate_D_LE',
								xtype : 'datefield',
								format : 'Y-m-d',
								labelWidth : 15
							},{
								fieldLabel : '发布部门',
								name : 'Q_issueDep_S_EQ',
								xtype : 'textfield',
								labelWidth : 60
							},{
								fieldLabel : '关键字',
								name : 'Q_keywords_S_LK',
								xtype : 'textfield',
								labelWidth : 60
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
							}
					]
				});// end of searchPanel
		//列表面板
		this.gridPanel = new HT.GridPanel({
			region : 'center',
			height : 500,
			// 使用RowActions
			rowActions : true,
			url : __ctxPath + "/admin/scanRegulation.do",
			fields : [{
						name : 'regId',
						type : 'int'
					}, 'subject', 'issueDate', 'issueUserId',
					'issueFullname', 'issueDepId', 'issueDep',
					'recDeps', 'recDepIds', 'recUsers', 'recUserIds',
					'keywords', 'status', 'globalType'],
			columns : [{
						header : 'regId',
						dataIndex : 'regId',
						hidden : true
					}, {
						header : '类型',
						dataIndex : 'globalType',
						renderer : function(value) {
							if (value != null) {
								return value.typeName;
							} else {
								return '';
							}
						}
					}, {
						header : '标题',
						dataIndex : 'subject'
					}, {
						header : '发布日期',
						dataIndex : 'issueDate',
						renderer : function(value) {
							if (value != null) {
								return value.substring(0, 10);
							} else {
								return '';
							}
						}
					}, {
						header : '发布人',
						dataIndex : 'issueFullname'
					}, {
						header : '发布部门',
						dataIndex : 'issueDep'
					}, {
						header : '接收部门范围',
						dataIndex : 'recDeps'
					}, {
						header : '接收人范围',
						dataIndex : 'recUsers'
					}, {
						header : '关键字',
						dataIndex : 'keywords'
					}, {
						header : '状态',
						dataIndex : 'status',
						renderer : function(value) {
							if (value != null && value == 1) {
								return '<font color="green">生效</font>';
							} else {
								return '<font color="red">草稿</font>';
							}
						}
					}, new Ext.ux.grid.RowActions({
								header : '管理',
								width : 100,
								actions : [{
											iconCls : 'btn-suggest-scan',
											qtip : '查看',
											stype : 'margin:0 3px 0 3px'
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
		var record = grid.getStore().getAt(rowindex);
		this.scanRs.call(this, record);
	},
	// 查看Rs
	scanRs : function(record) {
		new RegulationScanWin({
			regId : record.data.regId
		}).show();
	},
	// 行的Action
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-suggest-scan' :
				this.scanRs.call(this, record);
				break;
			default :
				break;
		}
	}
});
