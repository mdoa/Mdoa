Ext.ns('InStockView');
/**
 * @author:
 * @class InStockView
 * @extends Ext.Panel
 * @description 入库用品列表
 * @company 杭州梦德软件有限公司
 * @createtime:2010-04-12
 */
InStockView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		InStockView.superclass.constructor.call(this, {
					id : 'InStockView',
					title : '入库用品列表',
					iconCls : 'menu-instock',
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
					labelWidth : 155,
					items : [{
								fieldLabel : '请输入搜索条件：商品名称',
								xtype : 'textfield',
								name : 'Q_officeGoods.goodsName_S_LK'
							}, {
								fieldLabel : '供应商',
								xtype : 'textfield',
								name : 'Q_providerName_S_LK',
								labelWidth : 60
							}, {
								fieldLabel : '购买人',
								xtype : 'textfield',
								name : 'Q_buyer_S_LK',
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
								handler : this.reset
							}]
				});// end of the searchPanel

		// 顶端栏目条
		this.topbar = new Ext.Toolbar({
					items : [{
								iconCls : 'btn-add',
								text : '添加入库单',
								xtype : 'button',
								scope : this,
								handler : this.createRs
							}, '-', {
								iconCls : 'btn-del',
								text : '删除入库单',
								xtype : 'button',
								scope : this,
								handler : this.removeSelRs
							}]
				});

		// 办公用品列表面板
		this.gridPanel = new HT.GridPanel({
					region : 'center',
					tbar : this.topbar,
					sort : [{
								field : "buyId",
								direction : "DESC"
							}],
					// 使用RowActions
					rowActions : true,
					url : __ctxPath + '/admin/listInStock.do',
					fields : [{
								name : 'buyId',
								type : 'int'
							}, {
								name : 'goodsName',
								mapping : 'officeGoods.goodsName'
							}, 'providerName', 'stockNo', 'price', 'inCounts',
							'amount', 'inDate', 'buyer'],
					columns : [{
								header : 'buyId',
								dataIndex : 'buyId',
								hidden : true
							}, {
								header : '库存号',
								dataIndex : 'stockNo'
							}, {
								header : '商品名称',
								dataIndex : 'goodsName'
							}, {
								header : '供应商',
								dataIndex : 'providerName'
							}, {
								header : '价格',
								dataIndex : 'price'
							}, {
								header : '入货数量',
								dataIndex : 'inCounts'
							}, {
								header : '总额',
								dataIndex : 'amount'
							}, {
								header : '入库日期',
								dataIndex : 'inDate',
								renderer : function(value) {
									return value.substring(0, 10);
								}
							}, {
								header : '购买人',
								dataIndex : 'buyer'
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
		this.gridPanel.addListener({
					scope : this,
					'rowdblclick' : this.rowClick
				});
	},// end of the initUIComponents
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
		this.editRs.call(this, rec);

	},
	// 创建记录
	createRs : function() {
		new InStockForm({
					scope : this,
					callback : this.reloadType
				}).show();
	},
	// 按ID删除记录
	removeRs : function(id) {
		$postDel({
					url : __ctxPath + '/admin/multiDelInStock.do',
					ids : id,
					grid : this.gridPanel
				});
	},
	// 把选中ID删除
	removeSelRs : function() {
		$delGridRs({
					url : __ctxPath + '/admin/multiDelInStock.do',
					grid : this.gridPanel,
					idName : 'buyId'
				});
	},
	// 编辑Rs
	editRs : function(record) {
		new InStockForm({
					buyId : record.data.buyId,
					scope : this,
					callback : this.reloadType
				}).show();
	},
	// 刷新gridPanel
	reloadType : function() {
		this.gridPanel.getStore().reload();
	},
	// 行的Action
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.removeRs.call(this, record.data.buyId);
				break;
			case 'btn-edit' :
				this.editRs.call(this, record);
				break;
			default :
				break;
		}
	}

});
