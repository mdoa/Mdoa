Ext.ns('ProcessRunFinishView');
/**
 * 已办结的流程
 * 
 * @class ProcessRunFinishView
 * @extends Ext.Panel
 */
ProcessRunFinishView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		ProcessRunFinishView.superclass.constructor.call(this, {
					id : 'ProcessRunFinishView',
					title : '已办结的流程',
					iconCls : 'menu-flowEnd',
					layout : 'border',
					region : 'center',
					autoScroll : true,
					items : [this.searchPanel, this.gridPanel]
				});
	},// end of constructor
	/**
	 * 初始化组件
	 */
	initUIComponents : function() {
		this.searchPanel = new HT.SearchPanel({
					region : 'north',
					height : 40,
					layout : 'form',
					border : false,
					colNums : 5,
					keys : {
						key : Ext.EventObject.ENTER,
						fn : this.search,
						scope : this
					},
					labelWidth : 135,
					items : [{
								fieldLabel : '请输入查询条件:标题',
								xtype : 'textfield',
								name : 'Q_subject_S_LK',
								maxLength : 150
							}, {
								fieldLabel : '时间 从',
								name : 'Q_createtime_D_GT',
								xtype : 'datefield',
								format : 'Y-m-d',
								maxLength : 125,
								labelWidth : 55
							}, {
								fieldLabel : '至',
								name : 'Q_createtime_D_LT',
								xtype : 'datefield',
								format : 'Y-m-d',
								maxLength : 125,
								labelWidth : 7
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
		// 建立DataGrid
		this.gridPanel = new HT.GridPanel({
					trackMouseOver : true,
					disableSelection : false,
					loadMask : true,
					region : 'center',
					rowActions : true,
					sort : [{
								field : 'runId',
								direction : 'DESC'
							}],
					url : __ctxPath
							+ '/flow/listProcessRun.do?Q_runStatus_SN_EQ=2',
					tbar : new Ext.Toolbar(),
					fields : [{
								name : 'runId',
								type : 'int'
							}, 'subject', 'createtime', 'defId', 'piId',
							'runStatus'],
					columns : [{
								header : 'runId',
								dataIndex : 'runId',
								hidden : true
							}, {
								header : '标题',
								dataIndex : 'subject'
							}, {
								header : '时间',
								sortable : false,
								dataIndex : 'createtime',
								width : 60
							}, {
								header : '流程状态',
								dataIndex : 'runStatus',
								renderer : this.runStatusControl
							}, new Ext.ux.grid.RowActions({
										header : '管理',
										width : 50,
										actions : [{
													iconCls : 'btn-flowView',
													qtip : '审批明细',
													style : 'margin:0 3px 0 3px'
												}, {
													iconCls : 'btn-del',
													qtip : '删除',
													style : 'margin:0 3px 0 3px',
													fn : function(record) {
														// if
														// (record.data.runStatus
														// == 0)
														// return true;
														return false;
													}
												}],
										listeners : {
											scope : this,
											'action' : this.onRowAction
										}
									})],
					listeners : {
						scope : this,
						'rowdblclick' : this.rowClick
					}
				});
	},// end of the initComponents()
	/**
	 * 列表双击处理
	 */
	rowClick : function(gridPanel, rowindex, e) {
		var out = this;
		gridPanel.getSelectionModel().each(function(rec) {
			this.detail(rec.data.runId, rec.data.defId, rec.data.piId,
					rec.data.subject);
		}, this);
	},
	/**
	 * runStatus字段列控制
	 * 
	 * @param {}
	 *            val
	 * @return {String}
	 */
	runStatusControl : function(val) {
		if (val == 0) {
			return '<font color="red">草稿</font>';
		} else if (val == 1) {
			return '<font color="green">正在运行</font>';
		} else if (val == 2) {
			return '<font color="gray">结束</font>';
		}
	},
	/**
	 * 搜索
	 */
	search : function() {
		$search({
					searchPanel : this.searchPanel,
					gridPanel : this.gridPanel
				});
	},
	/**
	 * 重置(清空)查询表单
	 */
	reset : function() {
		this.searchPanel.getForm().reset();
	},
	/**
	 * 显示明细
	 * 
	 * @param {}
	 *            runId
	 * @param {}
	 *            name
	 */
	detail : function(runId, defId, piId, name) {
		var contentPanel = App.getContentPanel();
		var detailView = contentPanel.getItem('ProcessRunDetail' + runId);

		if (detailView == null) {
			detailView = new ProcessRunDetail({
						runId : runId,
						defId : defId,
						piId : piId,
						name : name
					});
			contentPanel.add(detailView);
		}
		contentPanel.activate(detailView);
	},
	/**
	 * 行的Action
	 */
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-flowView' :
				this.detail.call(this, record.data.runId, record.data.defId,
						record.data.piId, record.data.subject);
				break;
			default :
				break;
		}
	}
});