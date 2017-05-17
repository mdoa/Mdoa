Ext.ns('GoodsApplyView');
/**
 * @author:
 * @class GoodsApplyView
 * @extends Ext.Panel
 * @description 办公用品申请列表
 * @company 杭州梦德软件有限公司
 * @createtime:2010-04-12
 */
GoodsApplyView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		GoodsApplyView.superclass.constructor.call(this, {
					id : 'GoodsApplyView',
					title : '办公用品申请',
					iconCls : 'menu-conf-summary',
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
				
		//顶端栏目条
		this.topbar = new Ext.Toolbar({
			items : [{
						iconCls : 'btn-add',
						text : '添加申请单',
						xtype : 'button',
						scope : this,
						handler : this.createRs
					}, '-', {
						iconCls : 'btn-del',
						text : '删除申请单',
						xtype : 'button',
						scope : this,
						handler : this.removeSelRs
					}]
		});
		
		// 办公用品申请列表面板
		this.gridPanel = new HT.GridPanel({
					region : 'center',
					tbar : this.topbar,
					// 使用RowActions
					rowActions : true,
					sort:[{field: "applyId", direction: "DESC"}],
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
									if (value == '0') {
										return '草稿';
									}else if (value == '1') {
										return '提交审批';
									}else if(value==2){
										return '通过审批';
									}else if(value==3){
									   return '未通过审批';
									}
								}
							}, new Ext.ux.grid.RowActions({
										header : '管理',
										width : 100,
										actions : [{
													iconCls : 'btn-del',
													qtip : '删除',
													style : 'margin:0 3px 0 3px',
													fn : function(record) {
														if (record.data.approvalStatus == 0 || record.data.approvalStatus == 3)
															return true;
														return false;
													}
												}, {
													iconCls : 'btn-edit',
													qtip : '编辑',
													style : 'margin:0 3px 0 3px',
													fn : function(record) {
														if (record.data.approvalStatus == 0 || record.data.approvalStatus == 3)
															return true;
														return false;
													}
												},{
													iconCls : 'btn-readdocument',
													qtip : '查看',
													style : 'margin:0 3px 0 3px',
													fn : function(record) {
														if (record.data.approvalStatus != 0 && record.data.approvalStatus != 3)
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
		this.editRs.call(this, rec);
		
	},
	// 创建记录
	createRs : function() {
		new GoodsApplyForm({
	        scope:this,
			callback : this.reloadType
		}).show();
	},
	// 按ID删除记录
	removeRs : function(id) {
		$postDel({
			url : __ctxPath + '/admin/multiDelGoodsApply.do',
			ids : id,
			grid : this.gridPanel
		});
	},
	// 把选中ID删除
	removeSelRs : function() {
		$delGridRs({
			url : __ctxPath + '/admin/multiDelGoodsApply.do',
			grid : this.gridPanel,
			idName : 'applyId'
		});
	},
	// 编辑Rs
	editRs : function(record) {
		var approvalStatus = record.data.approvalStatus;
		if(approvalStatus != 0 && approvalStatus != 3){
			Ext.ux.Toast.msg('编辑', '此状态不能编辑');
			return;
		} 
		new GoodsApplyForm({
			applyId : record.data.applyId,
			scope:this,
			callback : this.reloadType
		}).show();
	},
	// 查看
	detail : function(record) {
		new GoodsCheckForm({
			applyId : record.data.applyId,
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
			case 'btn-del' :
				this.removeRs.call(this, record.data.applyId);
				break;
			case 'btn-edit' :
				this.editRs.call(this, record);
				break;
			case 'btn-readdocument' :
				this.detail.call(this, record);
				break;
			default :
				break;
		}
	}

});

