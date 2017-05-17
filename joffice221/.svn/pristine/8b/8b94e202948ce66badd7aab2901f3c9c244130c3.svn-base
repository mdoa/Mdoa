/**
 * @author:
 * @class ArchivesSignView
 * @extends Ext.Panel
 * @description 规章制度管理
 * @company 杭州梦德软件有限公司
 * @createtime:
 */
ArchivesSignView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		ArchivesSignView.superclass.constructor.call(this, {
					id : 'ArchivesSignView',
					title : '公文签收待办',
					region : 'center',
					layout : 'border',
					iconCls : 'menu-archive-sign',
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
					labelWidth : 60,
					items : [{
								fieldLabel : '类型名称',
								name : 'Q_archives.typeName_S_LK',
								width : 100,
								xtype : 'textfield'
							},{
								fieldLabel : '发文字号',
								width : 100,
								name : 'Q_archives.archivesNo_S_LK',
								xtype : 'textfield'
							},{
								fieldLabel : '文件标题',
								width : 100,
								name : 'Q_subject_S_LK',
								xtype : 'textfield'
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
							},{
								xtype : 'hidden',
								name : 'Q_archives.archStatus_SN_EQ',
								value : 1
								// 查找状态为已经部门审核的公文
						}	, {
								xtype : 'hidden',
								name : 'Q_archives.archType_SN_EQ',
								value : 0
								// 查找发文的公文
						}]
				});// end of the searchPanel

		this.gridPanel = new HT.EditorGridPanel({
					region : 'center',
					clicksToEdit : 1,
					height : 500,
					// 使用RowActions
					rowActions : true,
					url : __ctxPath + "/archive/listArchivesDep.do",
					baseParams : {
						'Q_archives.archType_SN_EQ' : 0, // 查询是发文的公文
						'Q_archives.archStatus_SN_EQ' : 1, // 查询已归档的公文
						'Q_status_SN_EQ' : 0
						// 查找未签收的公文
					},
					fields : [{
								name : 'archDepId',
								type : 'int'
							}, 'archives'],
					columns : [{
								header : 'archDepId',
								dataIndex : 'archDepId',
								hidden : true
							}, {
								header : '公文类型名称',
								dataIndex : 'archives',
								renderer : function(value) {
									return value.typeName;
								}
							}, {
								header : '发文字号',
								dataIndex : 'archives',
								renderer : function(value) {
									return value.archivesNo;
								}
							}, {
								header : '发文机关或部门',
								dataIndex : 'archives',
								renderer : function(value) {
									return value.issueDep;
								}
							}, {
								header : '文件标题',
								dataIndex : 'archives',
								renderer : function(value) {
									return value.subject;
								}
							}, {
								header : '公文状态',
								dataIndex : 'archives',
								renderer : function(value) {
									if (value != null) {
										return value.status;
									} else {
										return '办结';
									}
								}
							}, {
								header : '秘密等级',
								dataIndex : 'archives',
								renderer : function(value) {
									return value.privacyLevel;
								}
							}, {
								header : '紧急程度',
								dataIndex : 'archives',
								renderer : function(value) {
									return value.urgentLevel;
								}
							}, {
								header : '发文时间',
								dataIndex : 'archives',
								renderer : function(value) {
									return value.createtime.substring(0, 10);
								}
							}, new Ext.ux.grid.RowActions({
										header : '管理',
										width : 100,
										actions : [{
													iconCls : 'btn-archives-detail',
													qtip : '查阅详情',
													style : 'margin:0 3px 0 3px'
												}, {
													iconCls : 'btn-archive-sign',
													qtip : '公文签收',
													style : 'margin:0 3px 0 3px'
												}],
										listeners : {
											scope : this,
											'action' : this.onRowAction
										}
									})],
					// end of columns
					listeners : {
						scope : this,
						'rowdblclick' : this.rowClick
					}
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
	// GridPanel行点击处理事件
	rowClick : function(grid, rowindex, e) {
		var record = grid.getStore().getAt(rowindex);
		this.scanRs.call(this, record);
	},
	// 预览Rs
	scanRs : function(record) {
		new ArchivesDetailWin({
					archivesId : record.data.archives.archivesId,
					runId : record.data.archives.runId
				}).show();
	},
	// 公文签收
	signRs : function(record) {
		var contentPanel = App.getContentPanel();
		var startForm = contentPanel.getItem('ProcessRunStart' + this.defId);

		if (startForm == null) {
			startForm = new ProcessRunStart({
						id : 'ProcessRunStart' + this.defId,
						defId : this.defId,
						flowName : this.flowName,
						vmParams : '{archivesId:'
								+ record.data.archives.archivesId
								+ ',archDepId:' + record.data.archDepId + '}'
					});
			contentPanel.add(startForm);
		}
		contentPanel.activate(startForm);
	},
	// 行的Action
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-archives-detail' :
				this.scanRs.call(this, record);
				break;
			case 'btn-archive-sign' :
				this.signRs.call(this, record);
				break;
			default :
				break;
		}
	}
});
