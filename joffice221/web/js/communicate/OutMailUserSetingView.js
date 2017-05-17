/**
 * @author:
 * @class OutMailUserSetingForm
 * @extends Ext.Panel
 * @description [OutMailUserSeting]管理
 * @company 杭州梦德软件有限公司
 * @createtime:2010-01-16
 */
OutMailUserSetingView = Ext.extend(Ext.Panel, {
	// 内嵌FormPanel
	formPanel : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		OutMailUserSetingView.superclass.constructor.call(this, {
					id : 'OutMailUserSetingView',
					title : '邮箱配置管理',
					region : 'center',
					iconCls : 'menu-diary',
					layout : 'border',
					items : [this.searchPanel, this.gridPanel]
				});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		// 查询面板
		this.searchPanel = new HT.SearchPanel({
					layout : 'form',
					region : 'north',
					colNums : 6,
					labelWidth : 60,
					items : [{
								fieldLabel : '帐号名称',
								xtype : 'textfield',
								name : 'Q_accountName_S_LK',
								maxLength : 125
							}, {
								fieldLabel : '邮箱地址',
								xtype : 'textfield',
								name : 'Q_mailAddress_S_LK',
								maxLength : 125
							}, {
								xtype : 'button',
								text : '查询',
								style : 'padding-left:5px;',
								iconCls : 'search',
								handler : this.search,
								scope : this
							}, {
								xtype : 'button',
								text : '重置',
								style : 'padding-left:5px;',
								iconCls : 'btn-reset',
								handler : this.reset,
								scope : this
							}]
				});// end of searchPanel
		this.topbar = new Ext.Toolbar({
					height : 30,
					bodyStyle : 'text-align:left',
					items : [{
								iconCls : 'btn-add',
								text : '添加帐号',
								xtype : 'button',
								scope : this,
								handler : this.createRs
							}, '-', {
								iconCls : 'btn-del',
								text : '删除帐号',
								xtype : 'button',
								scope : this,
								handler : this.removeSelRs
							}]
				});
		// 日志列表面板
		this.gridPanel = new HT.GridPanel({
					region : 'center',
					tbar : this.topbar,
					sort : [{
								field : "setId",
								direction : "DESC"
							}],
					// 使用RowActions
					rowActions : true,
					url : __ctxPath + "/communicate/listOutMailUserSeting.do",
					fields : ['setId', 'userId', 'accountName', 'mailAddress',
							'isDefault'],
					columns : [{
								header : 'setId',
								dataIndex : 'setId',
								hidden : true
							}, {
								header : '帐号名称',
								dataIndex : 'accountName'
							}, {
								header : '邮箱地址',
								dataIndex : 'mailAddress'
							}, {
								header : '是否默认',
								dataIndex : 'isDefault',
								sortable : false,
								renderer : function(value) {
									return value == '1'
											? "<font color='red'>是</font>"
											: "否";
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
												}, {
													iconCls : 'btn-setting',
													qtip : '设置默认',
													style : 'margin:0 3px 0 3px',
													fn : function(record) {
														if (record.data.isDefault != 1)
															return true;
														return false;
													}
												}],
										listeners : {
											scope : this,
											'action' : this.onRowAction
										}
									})],// end of columns
					// 监听行点击事件
					listeners : {
						scope : this,
						'rowdblclick' : this.rowdblclick
					}
				});
	},// end of the initcomponents
	// baocun
	// 查询
	search : function() {
		$search({
					searchPanel : this.searchPanel,
					gridPanel : this.gridPanel
				});
	},// end of search
	// 创建记录
	createRs : function() {
		new OutMailUserSetingForm({
					scope : this,
					callback : this.reloadType
				}).show();
	},
	// 按ID删除记录
	removeRs : function(setId) {
		$postDel({
					url : __ctxPath
							+ '/communicate/multiDelOutMailUserSeting.do',
					ids : setId,
					grid : this.gridPanel
				});
	},
	// 把选中ID删除
	removeSelRs : function() {
		$delGridRs({
					url : __ctxPath
							+ '/communicate/multiDelOutMailUserSeting.do',
					grid : this.gridPanel,
					idName : 'setId'
				});
	},
	// 行双击事件
	rowdblclick : function(grid, rowindex, e) {
		grid.getSelectionModel().each(function(record) {
					this.editRs.call(this, record);
				}, this);
	},
	// 编辑Rs
	editRs : function(record) {
		new OutMailUserSetingForm({
					setId : record.data.setId,
					scope : this,
					callback : this.reloadType
				}).show();
	},
	reset : function() {
		this.searchPanel.getForm().reset();
	},
	// 刷新gridPanel
	reloadType : function() {
		this.gridPanel.getStore().reload();
	},
	// 查看日记明细
	setDefault : function(record) {
		gridPanel = this.gridPanel;
		Ext.Ajax.request({
					url : __ctxPath
							+ '/communicate/setDefaultOutMailUserSeting.do',
					params : {
						setId : record.data.setId
					},
					method : 'post',
					scope : this,
					success : function(result, request) {
						Ext.ux.Toast.msg('操作信息', '成功设置默认状态！');
						gridPanel.getStore().reload();
					}
				})

	},
	// 行的Action
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.removeRs.call(this, record.data.setId);
				break;
			case 'btn-setting' :
				this.setDefault.call(this, record);
				break;
			case 'btn-edit' :
				this.editRs.call(this, record);
				break;
			default :
				break;
		}
	}
});
