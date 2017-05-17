Ext.ns('CartRepairView');
/**
 * @author:
 * @class CarView
 * @extends Ext.Panel
 * @description 车辆列表
 * @company 杭州梦德软件有限公司
 * @createtime:2010-04-12
 */
CartRepairView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		CartRepairView.superclass.constructor.call(this, {
					id : 'CartRepairView',
					title : '车辆维修列表',
					iconCls : 'menu-car_repair',
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
					colNums : 4,
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
						fieldLabel : '请输入搜索条件：车牌号码',
						xtype : 'textfield',
						name : 'Q_car.carNo_S_LK'
					},{
						fieldLabel : '维修类型',
						xtype : 'textfield',
						name : 'Q_repairType_S_LK',
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
						text : '添加维修单',
						xtype : 'button',
						scope : this,
						handler : this.createRs
					}, '-', {
						iconCls : 'btn-del',
						text : '删除维修单',
						xtype : 'button',
						scope : this,
						handler : this.removeSelRs
					}]
		});
		
		// 车辆维修列表面板
		this.gridPanel = new HT.GridPanel({
					region : 'center',
					tbar : this.topbar,
					sort : [{field:"repairId",direction:"DESC"}],
					// 使用RowActions
					rowActions : true,
					url : __ctxPath + '/admin/listCartRepair.do',
					fields : [{
								name : 'repairId',
								type : 'int'
							},{
								name:'car.carNo',
								mapping:'car.carNo'
							}, 'repairDate', 'reason','executant', 'notes', 'repairType', 'fee'],
					columns : [{
								header : 'repairId',
								dataIndex : 'repairId',
								hidden : true
							}, {
								header : '车辆车牌号',
								dataIndex : 'car.carNo'
							}, {
								header : '维护日期',
								dataIndex : 'repairDate',
								renderer:function(value){
								  return value.substring(0,10);
								}
							}, {
								header : '维护原因',
								dataIndex : 'reason'
							}, {
								header : '经办人',
								dataIndex : 'executant'
							}, {
								header : '备注',
								dataIndex : 'notes'
							}, {
								header : '维修类型',
								dataIndex : 'repairType'
							}, {
								header : '费用',
								dataIndex : 'fee'
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
		new CartRepairForm({
	        scope:this,
			callback : this.reloadType
		}).show();
	},
	// 按ID删除记录
	removeRs : function(id) {
		$postDel({
			url : __ctxPath+ '/admin/multiDelCartRepair.do',
			ids : id,
			grid : this.gridPanel
		});
	},
	// 把选中ID删除
	removeSelRs : function() {
		$delGridRs({
			url : __ctxPath+ '/admin/multiDelCartRepair.do',
			grid : this.gridPanel,
			idName : 'repairId'
		});
	},
	// 编辑Rs
	editRs : function(record) {
		new CartRepairForm({
			repairId : record.data.repairId,
			scope:this,
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
				this.removeRs.call(this, record.data.repairId);
				break;
			case 'btn-edit' :
				this.editRs.call(this, record);
				break;
			default :
				break;
		}
	}

});

