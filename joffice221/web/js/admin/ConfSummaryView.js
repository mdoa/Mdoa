Ext.ns('ConfSummaryView');
/**
 * @description 会议纪要管理
 * @company 杭州梦德软件有限公司
 * @class ConfSummaryView
 * @extends Ext.Panel
 */
ConfSummaryView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		ConfSummaryView.superclass.constructor.call(this, {
					id : 'ConfSummaryView',
					title : '会议纪要查询',
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
						xtype : 'combo',
						fieldLabel : '状态',
						hiddenName : 'Q_status_SN_EQ',
						editable : false,
						triggerAction : 'all',
						mode : 'local',
						width : 220,
						store : [ [ '0', '待发送' ], [ '1', '发送' ] ]
					},{
						fieldLabel : '纪要时间',
						name : 'Q_createtime_D_GE',
						xtype : 'datefield',
						width : 150,
						format : 'Y-m-d'
					},{
						fieldLabel : '至',
						name : 'Q_createtime_D_LE',
						xtype : 'datefield',
						format : 'Y-m-d',
						width : 150,
						labelWidth : 15
					},{
						xtype : 'button',
						text : '查询',
						iconCls : 'search',
						scope : this,
						handler : this.search
					},{
						fieldLabel : '会议标题',
						name : 'Q_confId.confTopic_S_LK',
						xtype : 'textfield',
						width : 220,
						maxLength : 156
					},{
						fieldLabel : '纪要内容',
						xtype : 'textfield',
						name : 'Q_sumContent_S_LK',
						width : 325,
						maxlength : 4000
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
						iconCls : 'btn-edit',
						text : '编辑',
						xtype : 'button',
						scope : this,
						handler : this.confSummaryEdit
					},{
						iconCls : 'btn-del',
						text : '删除',
						xtype : 'button',
						scope : this,
						handler : this.removeSelRs
					}]
		});
		
		// 会议纪要面板
		this.gridPanel = new HT.GridPanel({
			        id : 'ConfSummaryGrid',
					region : 'center',
					tbar : this.topbar,
					// 使用RowActions
					rowActions : true,
					url : __ctxPath + "/admin/listConfSummary.do",
					sort : [{field:"sumId",direction:"DESC"}],
					fields : [ {
						name : 'sumId',
						type : 'int'
					}, 'confId', 'createtime', 'creator', 'sumContent','status' ],
					columns : [{
								header : 'sumId',
								dataIndex : 'sumId',
								hidden : true
							}, {
								header : '会议标题',
								dataIndex : 'confId',
								renderer : function(value) {
									return value == null ? '为空' : value.confTopic;
								}
							}, {
								header : '创建人',
								dataIndex : 'creator'
							}, {
								header : '创建日期',
								dataIndex : 'createtime'
							}, {
								header : '纪要内容',
								dataIndex : 'sumContent',
								renderer : function(value){
									 return value.substring(0,20);
								}
							},new Ext.ux.grid.RowActions({
										header : '管理',
										width : 100,
										actions : [{
													iconCls : 'btn-del',
													qtip : '删除',
													style : 'margin:0 3px 0 3px',
													fn : function(rs){
														if(isGranted('_ConfSummaryDel')){
															return true;
														}else{
															return false;
														}
													}
												}, {
													iconCls : 'btn-edit',
													qtip : '编辑',
													style : 'margin:0 3px 0 3px'
												},{
													iconCls : 'btn-showDetail',
													qtip : '查看',
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
	// 按ID删除记录
	removeRs : function(id) {
		$postDel({
			url : __ctxPath + '/admin/multiDelConfSummary.do',
			ids : id,
			grid : this.gridPanel
		});
	},
	// 把选中ID删除
	removeSelRs : function() {
		$delGridRs({
			url : __ctxPath + '/admin/multiDelConfSummary.do',
			grid : this.gridPanel,
			idName : 'sumId'
		});
	},
	// 按ID编辑Rs
	editRs : function(rec) {
		if(rec.data.status==1){
			Ext.MessageBox.show({
				title : '操作提示',
				msg : '对不起，该数据已经发送，不可以编辑，请谅解！',
				buttons : Ext.MessageBox.OK,
				icon : 'ext-mb-error'
			});
			return ;
		}
		new ConfSummaryForm({
			sumId : rec.data.sumId,
			scope:this,
			callback : this.reloadType
		}).show();
	},
	//编辑选择的ID
	confSummaryEdit : function() {
		var grid = this.gridPanel;
		var selectRecords = grid.getSelectionModel().getSelections();
		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg('编辑提示', '请选择要修改的记录！');
			return;
		}
		if(selectRecords[0].data.status==1){
			Ext.MessageBox.show( {
				title : '操作信息',
				msg : '对不起，该数据已经发送，不可以编辑，请原谅！',
				buttons : Ext.MessageBox.OK,
				icon : 'ext-mb-error'
			});
			return ;
		}
		new ConfSummaryForm({
			sumId : selectRecords[0].data.sumId,
			scope:this,
			callback : this.reloadType
		}).show();
		
		
	},
	 //展示详细信息
	showDetail : function(sumId) {
		ConfSummaryDetailForm.show(sumId);
	},
	// 刷新gridPanel
	reloadType : function() {
		this.gridPanel.getStore().reload();
	},
	// 行的Action
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.removeRs.call(this, record.data.sumId);
				break;
			case 'btn-edit' :
				this.editRs.call(this, record);
				break;
			case 'btn-showDetail' :
				this.showDetail.call(this, record.data.sumId);
				break;
			default :
				break;
		}
	}

});

