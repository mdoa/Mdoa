Ext.ns('CarView');
/**
 * @author:
 * @class CarView
 * @extends Ext.Panel
 * @description 车辆列表
 * @company 杭州梦德软件有限公司
 * @createtime:2010-04-12
 */
CarView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		CarView.superclass.constructor.call(this, {
					id : 'CarView',
					title : '车辆列表',
					iconCls : 'menu-car',
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
						fieldLabel : '请输入搜索条件：车牌号码',
						xtype : 'textfield',
						name : 'Q_carNo_S_LK'
					},{
						fieldLabel : '车牌类型',
						xtype : 'textfield',
						name : 'Q_carType_S_LK',
						labelWidth : 60
					},{
						fieldLabel : '当前状态',
						hiddenName : 'Q_status_SN_EQ',
						xtype : 'combo',
						mode : 'local',
						editable : true,
						labelWidth : 60,
						triggerAction : 'all',
						store : [['1', '可用'], ['2', '维修中'],
								['0', '已报废']]
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
						iconCls : 'btn-car_add',
						text : '添加车辆',
						xtype : 'button',
						scope : this,
						handler : this.createRs
					}, '-', {
						iconCls : 'btn-car_del',
						text : '删除车辆',
						xtype : 'button',
						scope : this,
						handler : this.removeSelRs
					}]
		});
		
		// 车辆列表面板
		this.gridPanel = new HT.GridPanel({
					region : 'center',
					tbar : this.topbar,
					sort : [{field:"carId",direction:"DESC"}],
					// 使用RowActions
					rowActions : true,
					url : __ctxPath + '/admin/listCar.do',
					fields : [{
								name : 'carId',
								type : 'int'
							}, 'carNo', 'carType', 'engineNo',
							'buyInsureTime', 'auditTime', 'notes',
							'factoryModel', 'driver', 'buyDate',
							'status', 'cartImage'],
					columns : [{
								header : 'carId',
								dataIndex : 'carId',
								hidden : true
							}, {
								header : '车牌号码',
								dataIndex : 'carNo'
							}, {
								header : '车辆类型',
								dataIndex : 'carType'
							}, {
								header : '发动机型号',
								dataIndex : 'engineNo'
							}, {
								header : '购买保险时间',
								dataIndex : 'buyInsureTime'
							}, {
								header : '年审时间',
								dataIndex : 'auditTime'
							}, {
								header : '厂牌型号',
								dataIndex : 'factoryModel'
							}, {
								header : '驾驶员',
								dataIndex : 'driver'
							}, {
								header : '购置日期',
								dataIndex : 'buyDate'
							}, {
								header : '当前状态', // 1=可用2=维修中0=报废
								dataIndex : 'status',
								renderer : function(value) {
									if (value == '1') {
										return '可用';
									}
									if (value == '2') {
										return '维修中';
									}
									if (value == '0') {
										return '已报废';
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
		new CarForm({
	        scope:this,
			callback : this.reloadType
		}).show();
	},
	// 按ID删除记录
	removeRs : function(id) {
		$postDel({
			msg : '删除车辆会把该车辆申请记录和维修记录一起删除，您确认要删除该记录吗？',
			url : __ctxPath + '/admin/multiDelCar.do',
			ids : id,
			grid : this.gridPanel
		});
	},
	// 把选中ID删除
	removeSelRs : function() {
		$delGridRs({
			msg : '删除车辆会把该车辆申请记录和维修记录一起删除，您确认要删除该记录吗？',
			url : __ctxPath + '/admin/multiDelCar.do',
			grid : this.gridPanel,
			idName : 'carId'
		});
	},
	// 编辑Rs
	editRs : function(record) {
		new CarForm({
			carId : record.data.carId,
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
				this.removeRs.call(this, record.data.carId);
				break;
			case 'btn-edit' :
				this.editRs.call(this, record);
				break;
			default :
				break;
		}
	}

});

