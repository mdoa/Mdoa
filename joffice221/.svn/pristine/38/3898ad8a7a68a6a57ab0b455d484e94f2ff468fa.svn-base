Ext.ns('GoodsCheckView');
/**
 * @author:
 * @class GoodsCheckView
 * @extends Ext.Panel
 * @description 办公用品申请列表
 * @company 杭州梦德软件有限公司
 * @createtime:2010-04-12
 */
GoodsCheckView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		GoodsCheckView.superclass.constructor.call(this, {
					id : 'GoodsCheckView',
					title : '办公用品审批列表',
					iconCls : 'menu-goods-apply',
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
					},{
						fieldLabel : '申请号',
						xtype : 'textfield',
						name : 'Q_applyNo_S_LK',
						labelWidth : 60
					},{
						fieldLabel : '申请人',
						xtype : 'textfield',
						name : 'Q_proposer_S_LK',
						labelWidth : 60
					},{
						xtype : 'button',
						text : '查询',
						iconCls : 'search',
						scope : this,
						handler : this.search
					},{
						xtype : 'button',
						text : '清空',
						iconCls : 'reset',
						scope : this,
						handler : this.reset
					}]
				});// end of the searchPanel
		
		// 车辆列表面板
		this.gridPanel = new HT.GridPanel({
					region : 'center',
					sort : [{field:"applyId",direction:"DESC"}],
					// 使用RowActions
					rowActions : true,
					url : __ctxPath + '/admin/listGoodsApply.do',
					fields : [{
								name : 'applyId',
								type : 'int'
							}, {
								name : 'goodsName',
								mapping : 'officeGoods.goodsName'
							}, 'applyDate', 'applyNo', 'useCounts','proposer', 'notes', 'approvalStatus'],
					columns : [{
								header : 'applyId',
								dataIndex : 'applyId',
								hidden : true
							}, {
								header : '申请号',
								dataIndex : 'applyNo'
							}, {
								header : '商品名称',
								dataIndex : 'goodsName'
							}, {
								header : '申请日期',
								dataIndex : 'applyDate',
								renderer:function(value){
								  return value.substring(0,10);
								}
							}, {
								header : '申请数量',
								dataIndex : 'useCounts'
							}, {
								header : '申请人',
								dataIndex : 'proposer'
							}, {
								header : '备注',
								dataIndex : 'notes'
							}, {
								header : '审批状态',
								dataIndex : 'approvalStatus',
								renderer : function(value) {
									if (value == 1) {
										return '提交审批';
									}else if (value == 2) {
										return '通过审批';
									}else if(value==3){
									   return '不通过审批';
									}
								}
							},  new Ext.ux.grid.RowActions({
										header : '管理',
										width : 100,
										actions : [{
													iconCls : 'btn-check',
													qtip : '审批',
													style : 'margin:0 3px 0 3px',
													fn : function(record) {
														if (record.data.approvalStatus == 1)
															return true;
														return false;
													}
												}, {
													iconCls : 'btn-readdocument',
													qtip : '查看',
													style : 'margin:0 3px 0 3px',
													fn : function(record) {
														if (record.data.approvalStatus != 1)
															return true;
														return false;
													}
												}],
										listeners : {
											scope : this,
											'action' : this.onRowAction
										}
							})]
				});
		this.gridPanel.addListener({scope:this,'rowdblclick': this.rowClick});
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
		this.detail.call(this, rec.data.applyId);
		
	},
	//审批
	check : function(id) {
		new GoodsCheckForm({
			applyId : id,
			scope : this,
			callback : this.reloadType
		}).show();
	},
	//查看
	detail : function(id){
	   new GoodsCheckForm({
	      applyId : id,
	      isView:true
	   }).show();
	},
	// 刷新gridPanel
	reloadType : function() {
		this.gridPanel.getStore().reload();
	},
	// 行的Action
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-check' :
				this.check.call(this, record.data.applyId);
				break;
			case 'btn-readdocument' :
				this.detail.call(this, record.data.applyId);
				break;
			default :
				break;
		}
	}

});

