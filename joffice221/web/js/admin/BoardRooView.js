Ext.ns('BoardRooView');

/**
 * @author:
 * @class BoardRooView
 * @extends Ext.Panel
 * @description 会议室管理
 * @company 杭州梦德软件有限公司
 */
BoardRooView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		BoardRooView.superclass.constructor.call(this, {
					id : 'BoardRooView',
					title : '会议室管理',
					iconCls : 'menu-conference_boardRoom',
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
					colNums : 7,
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
						fieldLabel : '会议室名称',
						name : 'Q_roomName_S_LK',
						xtype : 'textfield',
						maxLength : 128
					},{
						fieldLabel : '描述',
						name : 'Q_roomDesc_S_LK',
						xtype : 'textfield',
						maxLength : 4000,
						labelWidth : 40
					},{
						fieldLabel : '容纳人数',
						name : 'Q_containNum_L_GE',
						xtype : 'numberfield',
						labelWidth : 60,
						maxLength : 6
					},{
						fieldLabel : '至',
						name : 'Q_containNum_L_LE',
						xtype : 'numberfield',
						labelWidth : 15,
						maxLength : 6
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
						handler : this.boardRoomEdit
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
					url : __ctxPath + '/admin/listBoardRoo.do',
					sort : [{field:"roomId",direction:"DESC"}],
					fields : [ {
						name : 'roomId',
						type : 'int'
					}, 'roomName', 'roomDesc', 'containNum' ],
					columns : [{
								header : 'roomId',
								dataIndex : 'roomId',
								hidden : true
							}, {
								header : '会议室名称',
								dataIndex : 'roomName'
							}, {
								header : '容纳人数(单位：个)',
								dataIndex : 'containNum'
							}, {
								header : '描述',
								dataIndex : 'roomDesc'
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
		this.editRs.call(this, rec.data.roomId);
		
	},
	// 创建记录
	createRs : function() {
		new BoardRooForm({
	        scope:this,
			callback : this.reloadType
		}).show();
	},
	// 按ID删除记录
	removeRs : function(id) {
		$postDel({
			url : __ctxPath + '/admin/multiDelBoardRoo.do',
			ids : id,
			grid : this.gridPanel
		});
	},
	// 把选中ID删除
	removeSelRs : function() {
		$delGridRs({
			url : __ctxPath + '/admin/multiDelBoardRoo.do',
			grid : this.gridPanel,
			idName : 'roomId'
		});
	},
	// 按ID编辑Rs
	editRs : function(roomId) {
		new BoardRooForm({
			roomId : roomId,
			scope:this,
			callback : this.reloadType
		}).show();
	},
	//编辑选择的ID
	boardRoomEdit : function() {
		var grid = this.gridPanel;
		var selectRecords = grid.getSelectionModel().getSelections();
		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg('编辑提示', '请选择要修改的记录！');
			return;
		}
		this.editRs.call(this, selectRecords[0].data.roomId);
	},
	// 刷新gridPanel
	reloadType : function() {
		this.gridPanel.getStore().reload();
	},
	// 行的Action
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.removeRs.call(this, record.data.roomId);
				break;
			case 'btn-edit' :
				this.editRs.call(this, record.data.roomId);
				break;
			default :
				break;
		}
	}

});

