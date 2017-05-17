/**
 * @author:
 * @class FormRuleView
 * @extends Ext.Panel
 * @description 表单规则管理
 * @company 杭州梦德软件有限公司
 * @createtime:
 */
FormRuleView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		FormRuleView.superclass.constructor.call(this, {
					id : 'FormRuleView',
					title : '表单规则管理',
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
					colNums : 3,
					items : [{
								fieldLabel : '规则名称',
								name : 'Q_name_S_EQ',
								xtype : 'textfield'
							}, {
								text : '查询',
								xtype : 'button',
								iconCls : 'btn-search',
								scope : this,
								handler : this.search
							}, {
								text : '重置',
								xtype : 'button',
								iconCls : 'btn-reset',
								scope : this,
								handler : this.reset
							}]
				});// end of searchPanel

		this.topbar = new Ext.Toolbar({
					items : [{
								iconCls : 'btn-add',
								text : '添加规则',
								xtype : 'button',
								scope : this,
								handler : this.createRs
							}, '-',{
								iconCls : 'btn-del',
								text : '删除规则',
								xtype : 'button',
								scope : this,
								handler : this.removeSelRs
							}]
				});

		this.gridPanel = new HT.GridPanel({
					region : 'center',
					tbar : this.topbar,
					// 使用RowActions
					rowActions : true,
					url : __ctxPath + "/flow/listFormRule.do",
					fields : [{
								name : 'ruleId',
								type : 'int'
							}, 'name', 'rule', 'tipInfo', 'memo'],
					columns : [{
								header : 'ruleId',
								dataIndex : 'ruleId',
								hidden : true
							}, {
								header : '名称',
								dataIndex : 'name'
							}, {
								header : '验证表达式',
								dataIndex : 'rule'
							}, {
								header : '提示信息',
								dataIndex : 'tipInfo'
							}, {
								header : '备注',
								dataIndex : 'memo'
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
									})],// end of columns
					// 双击行
					listeners : {
						scope : this,
						'rowdblclick' : this.rowClick
					}
				});

	},// end of the initComponents()
	/**
	 * 重置(清空)查询表单
	 */
	reset : function() {
		this.searchPanel.getForm().reset();
	},
	/**
	 * 按条件搜索
	 */
	search : function() {
		$search({
					searchPanel : this.searchPanel,
					gridPanel : this.gridPanel
				});
	},
	/**
	 * 刷新gridPanel
	 */
	reloadGridPanel : function() {
		this.gridPanel.getStore().reload();
	},
	/**
	 * 行双击事件
	 * 
	 * @param {}
	 *            grid
	 * @param {}
	 *            rowindex
	 * @param {}
	 *            e
	 */
	rowClick : function(grid, rowindex, e) {
		grid.getSelectionModel().each(function(record) {
					this.editRs(record);
				}, this);
	},
	/**
	 * 创建记录
	 */
	createRs : function() {
		new FormRuleForm({
					scope : this,
					callback : this.reloadGridPanel
				}).show();
	},
	/**
	 * 按ID删除记录
	 */
	removeRs : function(id) {
		$postDel({
					url : __ctxPath + '/flow/multiDelFormRule.do',
					ids : id,
					grid : this.gridPanel
				});
	},
	/**
	 * 把选中ID删除
	 */
	removeSelRs : function() {
		$delGridRs({
					url : __ctxPath + '/flow/multiDelFormRule.do',
					grid : this.gridPanel,
					idName : 'ruleId'
				});
	},
	/**
	 * 编辑Rs
	 */
	editRs : function(record) {
		new FormRuleForm({
					ruleId : record.data.ruleId
				}).show();
	},
	/**
	 * 行的Action
	 */
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.removeRs.call(this, record.data.ruleId);
				break;
			case 'btn-edit' :
				this.editRs.call(this, record);
				break;
			default :
				break;
		}
	}
});
