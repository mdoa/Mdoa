Ext.ns('DepreTypeView');
/**
 * @author:
 * @class DepreTypeView
 * @extends Ext.Panel
 * @description 折算类型列表
 * @company 杭州梦德软件有限公司
 * @createtime:2012-08-29
 */
DepreTypeView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		DepreTypeView.superclass.constructor.call(this, {
					id : 'DepreTypeView',
					title : '折算类型列表',
					iconCls:'menu-depre-type',
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
						fieldLabel : '请输入搜索条件：类型名称',
						xtype : 'textfield',
						name : 'Q_typeName_S_LK'
					},{
						fieldLabel : '折算类型',
						xtype : 'textfield',
						hiddenName : 'Q_calMethod_SN_EQ',
						xtype : 'combo',
						mode : 'local',
						labelWidth : 60,
						editable : false,
						triggerAction : 'all',
						store : [['1', '平均年限法'], ['2', '工作量法'],['3','双倍余额递减法'],['4','年数总和法']]
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
						text : '添加折旧类型',
						xtype : 'button',
						scope : this,
						handler : this.createRs
					}, '-', {
						iconCls : 'btn-del',
						text : '删除折旧类型',
						xtype : 'button',
						scope : this,
						handler : this.removeSelRs
					}]
		});
		
		// 折旧类型列表面板
		this.gridPanel = new HT.GridPanel({
					region : 'center',
					tbar : this.topbar,
					// 使用RowActions
					rowActions : true,
					url : __ctxPath + '/admin/listDepreType.do',
					fields : [{
								name : 'depreTypeId',
								type : 'int'
							}, 'typeName',  'deprePeriod',
							'typeDesc', 'calMethod'],
					columns : [{
								header : 'depreTypeId',
								dataIndex : 'depreTypeId',
								hidden : true
							}, {
								header : '分类名称',
								dataIndex : 'typeName'
							}, {
								header : '折旧周期(月)',
								dataIndex : 'deprePeriod'
							}, {
								header : '折旧方法',
								dataIndex : 'calMethod',
								renderer : function(value){
								    if(value == '1')
								        return '平均年限法';
								    if(value == '2')
								    	return '工作量法';
								    if(value == '3')
								        return '双倍余额递减法';
								    if(value == '4')
								    	return '年数总和法';
								}
							}, {
								header : '方法描述',
								dataIndex : 'typeDesc'
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
		new DepreTypeForm({
	        scope:this,
			callback : this.reloadType
		}).show();
	},
	// 按ID删除记录
	removeRs : function(id) {
		$postDel({
			url : __ctxPath + '/admin/multiDelDepreType.do',
			ids : id,
			grid : this.gridPanel
		});
	},
	// 把选中ID删除
	removeSelRs : function() {
		$delGridRs({
			url : __ctxPath + '/admin/multiDelDepreType.do',
			grid : this.gridPanel,
			idName : 'depreTypeId'
		});
	},
	// 编辑Rs
	editRs : function(record) {
		new DepreTypeForm({
			depreTypeId : record.data.depreTypeId,
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
				this.removeRs.call(this, record.data.depreTypeId);
				break;
			case 'btn-edit' :
				this.editRs.call(this, record);
				break;
			default :
				break;
		}
	}

});

