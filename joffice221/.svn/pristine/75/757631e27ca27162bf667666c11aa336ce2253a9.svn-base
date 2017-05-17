Ext.ns('SalaryItemView');
/**
 * @author:
 * @class SalaryItemView
 * @extends Ext.Panel
 * @description 工资项列表
 * @company 杭州梦德软件有限公司
 * @createtime:2010-04-12
 */
SalaryItemView = Ext.extend(Ext.Panel, {
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		SalaryItemView.superclass.constructor.call(this, {
					id : 'SalaryItemView',
					title : '工资项列表',
					iconCls : 'menu-salary',
					region : 'center',
					layout : 'border',
					items : [this.searchPanel, this.gridPanel]
				});
	},// end of constructor

	// 初始化组件
	initUIComponents : function() {

		// 搜索面板
		this.searchPanel = new HT.SearchPanel({
					layout : 'form',
					region : 'north',
					colNums : 2,
					keys : {
						key : Ext.EventObject.ENTER,
						fn : this.search,
						scope : this
					},
					labelWidth : 175, // 一个bug
					items : [{
								fieldLabel : '请输入查询条件：工资项名称',
								xtype : 'textfield',
								name : 'Q_itemName_S_LK',
								maxLength : 150
							}, {
								xtype : 'button',
								text : '查询',
								iconCls : 'search',
								scope : this,
								handler : this.search
							}]
				});

		this.gridPanel = new HT.GridPanel({
			region : 'center',
			tbar : [{
						iconCls : 'btn-add',
						text : '添加薪酬项目',
						scope : this,
						handler : this.addSalaryItem,
						hidden : !isGranted('_SalaryItemAdd')
					}, {
						iconCls : 'btn-del',
						text : '删除薪酬项目',
						hidden : !isGranted('_SalaryItemDel'),
						scope : this,
						handler : this.delClick
					}],
			url : __ctxPath + '/hrm/listSalaryItem.do',
			rowActions : true,
			fields : [{
						name : 'salaryItemId',
						type : 'int'
					}, 'itemName', 'defaultVal'],
			columns : [{
						header : 'salaryItemId',
						dataIndex : 'salaryItemId',
						hidden : true
					}, {
						header : '薪资项名称',
						dataIndex : 'itemName'
					}, {
						header : '缺省值',
						dataIndex : 'defaultVal',
						renderer : function(value) {
							return '<img src="' + __ctxPath
									+ '/images/flag/customer/rmb.png"/>'
									+ value;
						}
					}, new Ext.ux.grid.RowActions({
						header : '管理',
						width : 100,
						actions : [{
									iconCls : 'btn-del',
									qtip : '删除',
									style : 'margin:0 3px 0 3px',
									fn : function(record) {
										if (isGranted('_SalaryItemDel'))
											return true;
										return false;
									}
								}, {
									iconCls : 'btn-edit',
									qtip : '编辑',
									style : 'margin:0 3px 0 3px',
									fn : function(record) {
										if (isGranted('_SalaryItemEdit')
												&& record.data.approvalStatus != 1)
											return true;
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
				'rowdblclick' : this.gridRowdblClick
			}
		});
	},// end of the initUIComponents
	/**
	 * grid行双击处理
	 */
	gridRowdblClick : function(grid, rowindex, e) {
		grid.getSelectionModel().each(function(rec) {
					this.edit(rec.data.salaryItemId);
				}, this);
	},
	/**
	 * 新增薪酬项目
	 */
	addSalaryItem :function() {
			new SalaryItemForm({
					salaryItemId : null,
					scope : this,
					callback : this.reloadGridPanel
			}).show();
	},
	/**
	 * 删除薪酬项目
	 */
	delClick : function() {
		$delGridRs({
					url : __ctxPath + '/hrm/multiDelSalaryItem.do',
					grid : this.gridPanel,
					idName : 'salaryItemId'
				});
	},
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
	 * 刷新gridPanel
	 */
	reloadGridPanel : function() {
		this.gridPanel.getStore().reload();
	},
	/**
	 * 删除单个记录
	 */
	remove : function(id) {
		$postDel({
					url : __ctxPath + '/hrm/multiDelSalaryItem.do',
					ids : id,
					grid : this.gridPanel
				});
	},
	/**
	 * 编辑
	 */
	edit : function(id) {
		new SalaryItemForm({
					salaryItemId : id,
					scope : this,
					callback : this.reloadGridPanel
				}).show();
	},
	/**
	 * 行的Action
	 */
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.remove(record.data.salaryItemId);
				break;
			case 'btn-edit' :
				this.edit(record.data.salaryItemId);
				break;
			default :
				break;
		}
	}
});
