/**
 * @author:
 * @class ProcessHistoryView
 * @extends Ext.Panel
 * @description 流程历史管理
 * @company 杭州梦德软件有限公司
 * @createtime:
 */
ProcessHistoryView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		ProcessHistoryView.superclass.constructor.call(this, {
					id : 'ProcessHistoryView',
					title : '流程历史管理',
					iconCls : 'menu-history',
					region : 'center',
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
					width : '100%',
					height : 38,
					keys : [{
								key : Ext.EventObject.ENTER,
								fn : this.search,
								scope : this
							}, {
								key : Ext.EventObject.ESC,
								fn : this.reset,
								scope : this
							}],
					items : [{
						xtype : 'container',
						layout : 'column',
						border : false,
						style : 'padding-left:5px; padding-right:5px;padding-top:5px;',
						layoutConfig : {
							align : 'middle',
							padding : '5px'
						},
						items : [{
									columnWidth : .3,
									layout : 'form',
									xtype : 'container',
									items : [{
												anchor : '99%',
												fieldLabel : '事项名称',
												name : 'Q_subject_S_EQ',
												flex : 1,
												xtype : 'textfield'
											}, {
												anchor : '99%',
												fieldLabel : '创建人',
												name : 'Q_creator_S_EQ',
												flex : 1,
												xtype : 'textfield'
											}]
								}, {
									columnWidth : .3,
									layout : 'form',
									xtype : 'container',
									items : [{
										anchor : '99%',
										fieldLabel : '状态',
										xtype : 'combo',
										hiddenName : 'Q_runStatus_SN_EQ',
										mode : 'local',
										editable : false,
										value : '1',
										triggerAction : 'all',
										store : [['0', '未启动'], ['1', '正在运行'],
												['2', '运行结束']]
									}, {
										anchor : '99%',
										xtype : 'container',
										layout : 'column',
										fieldLabel : '创建时间从',
										items : [{
													columnWidth : .49,
													name : 'Q_createtime_D_GE',
													flex : 1,
													xtype : 'datefield',
													format : 'Y-m-d'
												}, {
													xtype : 'label',
													style : 'margin-top:3px;',
													text : ' 至 '
												}, {
													columnWidth : .49,
													anchor : '99%',
													name : 'Q_createtime_D_LE',
													flex : 1,
													xtype : 'datefield',
													format : 'Y-m-d'
												}]
									}]
								}, {
									layout : 'form',
									xtype : 'container',
									defaults : {
										xtype : 'button'
									},
									items : [{
												text : '查询',
												scope : this,
												iconCls : 'btn-search',
												handler : this.search
											}, {
												text : '重置',
												style : 'margin-top:5px;',
												scope : this,
												iconCls : 'btn-reset',
												handler : this.reset
											}]
								}]
					}]
				});// end of searchPanel

		this.topbar = new Ext.Toolbar({
					items : [{
								iconCls : 'btn-del',
								text : '删除流程历史',
								xtype : 'button',
								scope : this,
								handler : this.removeSelRs
							}]
				});

		var storeIdx = 0;
		this.gridPanel = new HT.GridPanel({
			region : 'center',
			tbar : this.topbar,
			// 使用RowActions
			rowActions : true,
			id : 'ProcessRunGrid',
			url : __ctxPath + "/flow/historyProcessRun.do",
			fields : [{
						name : 'runId',
						type : 'int'
					}, 'subject', 'creator', 'userId', 'defId', 'piId',
					'createtime', 'runStatus'],
			columns : [{
						header : '事项名',
						dataIndex : 'subject'
					}, {
						header : '发起人',
						dataIndex : 'creator'
					}, {
						header : '启动时间',
						dataIndex : 'createtime'
					}, {
						header : '运行状态',
						dataIndex : 'runStatus',
						renderer : function(val) {
							if (val == 0) {
								return '<font color="red">草稿</font>';
							} else if (val == 1) {
								return '<font color="green">正在运行</font>';
							} else if (val == 2) {
								return '<font color="gray">结束</font>';
							}
						}
					}, {
						header : '业务描述',
						dataIndex : 'busdesc'
					}, new Ext.ux.grid.RowActions({
								header : '管理',
								width : 100,
								actions : [{
											iconCls : 'btn-detail',
											qtip : '明细',
											style : 'margin:0 3px 0 3px;'
										}, {
											iconCls : 'btn-del',
											qtip : '结束实例',
											style : 'margin:0 3px 0 3px;',
											fn : function(record) {
												if (record.data.runStatus == '1')
													return true;
												return false;
											}
										}],
								listeners : {
									scope : this,
									'action' : this.onRowAction
								}
							})]
				// end of columns
		});

		this.gridPanel.addListener('rowdblclick', this.rowClick, this);

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
					this.detail.call(this, rec);
				}, this);
	},

	// 结束流程实例
	removeRs : function(id) {

		var gridPanel = this.gridPanel;
		var ids = $getGdSelectedIds(gridPanel, 'runId');
		if (ids.length == 0) {
			Ext.ux.Toast.msg('操作信息', '请选择记录！');
			return;
		}
		var strIds = '';
		for (var i = 0; i < ids.length; i++) {
			if (strIds != '') {
				strIds += ',';
			}
			strIds += ids[i];
		}
		Ext.Msg.confirm('信息确认', '您确认要结束所选实例吗？', function(btn) {
					if (btn == 'yes') {
						Ext.Ajax.request({
									url : __ctxPath + '/flow/endProcessRun.do',
									params : {
										runIds : strIds
									},
									method : 'post',
									success : function(resp, op) {
										var res = Ext.util.JSON
												.decode(resp.responseText);
										if (res.success) {
											Ext.ux.Toast.msg('信息提示', '成功结束实例！');
											gridPanel.getStore().reload();
										} else {
											Ext.ux.Toast.msg('信息提示',
													'出错，请联系管理员！');
											gridPanel.getStore().reload();
										}
									},
									failure : function() {
										Ext.ux.Toast.msg('信息提示', '出错，请联系管理员！');
										gridPanel.getStore().reload();
									}

								});
					}
				});

	},
	// 把选中ID删除
	removeSelRs : function() {
		$delGridRs({
					url : __ctxPath + '/flow/multiDelProcessRun.do',
					grid : this.gridPanel,
					idName : 'runId'
				});
	},

	detail : function(record) {
		var contentPanel = App.getContentPanel();
		var detailView = contentPanel.getItem('ProcessRunDetail'
				+ record.data.runId);

		if (detailView == null) {
			detailView = new ProcessRunDetail({
						runId : record.data.runId,
						defId : record.data.defId,
						piId : record.data.piId,
						name : record.data.subject
					});
			contentPanel.add(detailView);
		}
		contentPanel.activate(detailView);
	},
	// 行的Action
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-detail' :
				this.detail.call(this, record);
				break;
			case 'btn-del' :
				this.removeRs.call(this, record.data.runId);
				break;
			default :
				break;
		}
	}
});