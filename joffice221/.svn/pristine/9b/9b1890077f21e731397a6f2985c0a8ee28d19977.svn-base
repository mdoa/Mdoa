Ext.ns('BoardTypeView');

/**
 * @description 会议类型信息展示
 * @class BoardTypeView
 * @extends Ext.Panel
 */
BoardTypeView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		BoardTypeView.superclass.constructor.call(this, {
					id : 'BoardTypeView',
					title : '会议类型管理',
					iconCls : 'menu-confernece_boardType',
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
					labelWidth : 80,
					items : [{
						fieldLabel : '会议类型名称',
						xtype : 'textfield',
						name : 'Q_typeName_S_EQ',
						maxLength : 128
					},{
						fieldLabel : '会议类型描述',
						xtype : 'textfield',
						name : 'Q_typeDesc_S_LK',
						maxLength : 4000
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
						text : '新增',
						xtype : 'button',
						scope : this,
						handler : this.createRs
					},{
						iconCls : 'btn-edit',
						text : '编辑',
						xtype : 'button',
						scope : this,
						handler : this.boardTypeEdit
					},{
						iconCls : 'btn-del',
						text : '删除',
						xtype : 'button',
						scope : this,
						handler : this.removeSelRs
					}]
		});
		
		// 会议室列表面板
		this.gridPanel = new HT.GridPanel({
					region : 'center',
					tbar : this.topbar,
					// 使用RowActions
					rowActions : true,
					url : __ctxPath + '/admin/listBoardType.do',
					sort : [{field:"typeId",direction:"DESC"}],
					fields : [ {
							name : 'typeId',
							type : 'int'
						}, 'typeName', 'typeDesc' ],
					columns : [{
								header : 'typeId',
								dataIndex : 'typeId',
								hidden : true
							}, {
								header : '会议类型名称',
								dataIndex : 'typeName'
							}, {
								header : '会议类型描述',
								dataIndex : 'typeDesc'
							},  new Ext.ux.grid.RowActions({
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
		this.editRs.call(this, rec.data.typeId);
		
	},
	// 创建记录
	createRs : function() {
		new BoardTypeForm({
	        scope:this,
			callback : this.reloadType
		}).show();
	},
	// 按ID删除记录
	removeRs : function(id) {
		$postDel({
			url : __ctxPath + '/admin/multiDelBoardType.do',
			ids : id,
			grid : this.gridPanel
		});
	},
	// 把选中ID删除
	removeSelRs : function() {
		$delGridRs({
			url : __ctxPath + '/admin/multiDelBoardType.do',
			grid : this.gridPanel,
			idName : 'typeId'
		});
	},
	// 按ID编辑Rs
	editRs : function(typeId) {
		new BoardTypeForm({
			typeId : typeId,
			scope:this,
			callback : this.reloadType
		}).show();
	},
	//编辑选择的ID
	boardTypeEdit : function() {
		var grid = this.gridPanel;
		var selectRecords = grid.getSelectionModel().getSelections();
		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg('编辑提示', '请选择要修改的记录！');
			return;
		}
		this.editRs.call(this, selectRecords[0].data.typeId);
	},
	// 刷新gridPanel
	reloadType : function() {
		this.gridPanel.getStore().reload();
	},
	// 行的Action
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.removeRs.call(this, record.data.typeId);
				break;
			case 'btn-edit' :
				this.editRs.call(this, record.data.typeId);
				break;
			default :
				break;
		}
	}

});

