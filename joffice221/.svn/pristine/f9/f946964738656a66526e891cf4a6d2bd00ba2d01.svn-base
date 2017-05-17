Ext.ns('DocumentSharedView');
/**
 * 共享文档
 * 
 * @class DocumentSharedView
 * @extends Ext.Panel
 */
DocumentSharedView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		this.initUI();
		// 调用父类构造
		DocumentSharedView.superclass.constructor.call(this, {
					id : 'DocumentSharedView',
					title : '共享文档列表',
					layout : 'border',
					iconCls : 'menu-folder-shared',
					autoScroll : true,
					items : [this.searchPanel, this.gridPanel]
				});
	},
	// 初始化组件
	initUI : function() {
		// 搜索面板
		this.searchPanel = new HT.SearchPanel({
					layout : 'form',
					region : 'north',
					height : 40,
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
					labelWidth : 55,
					items : [{
								fieldLabel : '文档名称',
								xtype : 'textfield',
								name : 'document.docName',
								maxLength : 150
							}, {
								fieldLabel : '共享人',
								xtype : 'textfield',
								name : 'document.fullname',
								maxLength : 125,
								labelWidth : 45
							}, {
								fieldLabel : '创建时间 从',
								xtype : 'datefield',
								format : 'Y-m-d',
								name : 'from',
								labelWidth : 75
							}, {
								fieldLabel : '至',
								xtype : 'datefield',
								format : 'Y-m-d',
								name : 'to',
								labelWidth : 25
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

		// 列表面板
		this.gridPanel = new HT.GridPanel({
					region : 'center',
					// 使用RowActions
					rowActions : true,
					url : __ctxPath + '/document/shareListDocument.do',
					fields : [{
								name : 'docId',
								type : 'int'
							}, 'docName', 'fullname', 'content', 'createtime',
							'haveAttach', 'attachFiles'],
					columns : [{
								header : 'docId',
								dataIndex : 'docId',
								hidden : true
							}, {
								header : '文档名称',
								dataIndex : 'docName',
								sortable : false,
								width : 120,
								renderer : function(value, metadata, record) {
									return value + '--'
											+ '由 <font color="green">'
											+ record.data.fullname
											+ '</font> 共享';
								}
							}, {
								header : '创建时间',
								sortable : false,
								dataIndex : 'createtime'
							}, {
								header : '共享人',
								sortable : false,
								dataIndex : 'fullname'
							}, {
								header : '附件',
								sortable : false,
								dataIndex : 'haveAttach',
								scope : this,
								renderer : this.rendererAttach
							}, new Ext.ux.grid.RowActions({
										header : '管理',
										width : 50,
										actions : [{
													iconCls : 'btn-readdocument',
													qtip : '查看',
													style : 'margin:0 3px 0 3px'
												}, ''],
										listeners : {
											scope : this,
											'action' : this.onRowAction
										}
									})],
					// 监听行点击事件
					listeners : {
						scope : this,
						'rowdblclick' : this.rowdblclick
					}
				});
	},

	// 按条件搜索
	search : function() {
		$search({
					searchPanel : this.searchPanel,
					gridPanel : this.gridPanel
				});
	},
	// 重置(清空)查询表单
	reset : function() {
		this.searchPanel.getForm().reset();
	},
	// 刷新gridPanel
	reloadGridPanel : function() {
		this.gridPanel.getStore().reload();
	},
	// 行双击事件
	rowdblclick : function(grid, rowindex, e) {
		var record = grid.getStore().getAt(rowindex);
		this.readRs.call(this, record.data.docId, record.data.docName);
	},
	rendererAttach : function(value, metadata, record) {
		if (value == '' || value == '0') {
			return '无附件';
		} else {
			var attachFiles = record.data.attachFiles;
			var str = '';
			for (var i = 0; i < attachFiles.length; i++) {
				str += '<a href="#" onclick="FileAttachDetail.show('
						+ attachFiles[i].fileId + ');" class="attachment">'
						+ attachFiles[i].fileName + '</a>';
				str += '&nbsp;';
			}

			return str;
		}
	},
	/**
	 * 行的Action
	 * 
	 * @param grid
	 * @param record
	 * @param action
	 * @param row
	 * @param col
	 */
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-readdocument' :
				this.readRs(record.data.docId, record.data.docName);
				break;
			default :
				break;
		}
	},
	// 查看文档
	readRs : function(id, name) {
		var tabs = Ext.getCmp('centerTabPanel');
		var panel = Ext.getCmp('DocumentSharedDetail');
		if (!Ext.isEmpty(panel)) {
			tabs.remove('DocumentSharedDetail');
		}
		panel = new DocumentSharedDetail({
					docId : id,
					docName : name
				});
		tabs.add(panel);
		tabs.activate(panel);
	},
	// 暂时没用
	flexRead : function(docId) {
		window.open(__ctxPath + '/iText/flexPaper.do?docId=' + docId);
	}
});