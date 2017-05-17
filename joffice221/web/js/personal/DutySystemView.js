Ext.ns('DutySystemView');
/**
 * 班制定义列表
 */
DutySystemView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		DutySystemView.superclass.constructor.call(this, {
					id : 'DutySystemView',
					title : '班制定义列表',
					region : 'center',
					iconCls : 'menu-dutySystem',
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
					labelWidth : 150,
					items : [{
								fieldLabel : '请输入查询条件: 班制名称',
								xtype : 'textfield',
								name : 'Q_systemName_S_LK'
							},{
								fieldLabel : '班次描述',
								xtype : 'textfield',
								name : 'Q_systemDesc_S_LK',
								labelWidth : 60
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
								handler : this.reset,
								style : 'padding-left:10px'
							}]
				});

				//顶端栏目条
		this.topbar = new Ext.Toolbar({
					items : [{
								iconCls : 'btn-add',
								text : '添加班制定义',
								xtype : 'button',
								scope : this,
								handler : this.createRs
							}, '-', {
								iconCls : 'btn-del',
								text : '删除班制定义',
								xtype : 'button',
								scope : this,
								handler : this.removeSelRs
							}]
				});

		// 班制面板
		this.gridPanel = new HT.GridPanel({
			region : 'center',
			tbar : this.topbar,
			sort : [{field:"systemId",direction:"DESC"}],
			// 使用RowActions
			rowActions : true,
			url : __ctxPath + '/personal/listDutySystem.do',
			fields : [{
						name : 'systemId',
						type : 'int'
					}, 'systemName', 'systemSetting', 'systemDesc', 'isDefault'],
			columns : [{
						header : 'systemId',
						dataIndex : 'systemId',
						hidden : true
					}, {
						header : '班制名称',
						width : 100,
						dataIndex : 'systemName'
					}, {
						header : '班次描述',
						dataIndex : 'systemDesc',
						width : 500
					}, {
						header : '缺省',
						dataIndex : 'isDefault',
						renderer : function(val) {
							if (val == 1) {
								return '是';
							} else {
								return '否';
							}
						}
					}, new Ext.ux.grid.RowActions({
								header : '管理',
								width : 100,
								actions : [{
											iconCls : 'btn-del',
											qtip : '删除',
											style : 'margin:0 3px 0 3px'
										}, {
											iconCls : 'btn-edit',
											qtip : '编辑',
											style : 'margin:0 3px 0 3px'
										}],
								listeners : {
									scope : this,
									'action' : this.onRowAction
								}
							})]
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
		this.editRs.call(this,rec);
	},
	// 创建记录
	createRs : function() {
		new DutySystemForm({
					scope : this,
					callback : this.reloadType
				}).show();
	},
	// 刷新gridPanel
	reloadType : function() {
		this.gridPanel.getStore().reload();
	},
	// 按ID删除记录
	removeRs : function(id) {
		$postDel({
					url : __ctxPath + '/personal/multiDelDutySystem.do',
					ids : id,
					grid : this.gridPanel
				});
	},
	// 把选中ID删除
	removeSelRs : function() {
		$delGridRs({
					url : __ctxPath + '/personal/multiDelDutySystem.do',
					grid : this.gridPanel,
					idName : 'systemId'
				});
	},
	// 编辑Rs
	editRs : function(record) {
		new DutySystemForm({
					systemId : record.data.systemId,
					scope : this,
					callback : this.reloadType
				}).show();
	},
	// 行的Action
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.removeRs.call(this, record.data.systemId);
				break;
			case 'btn-edit' :
				this.editRs.call(this, record);
				break;
			default :
				break;
		}
	}
});
