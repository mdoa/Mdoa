Ext.ns('AppRoleView');
/**
 * @author:
 * @class AppRoleView
 * @extends Ext.Panel
 * @description 用户角色列表
 * @company 杭州梦德软件有限公司
 * @createtime:2010-04-12
 */
AppRoleView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		AppRoleView.superclass.constructor.call(this, {
					id : 'AppRoleView',
					title : '角色列表',
					iconCls : 'menu-role',
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
					keys : {
						key : Ext.EventObject.ENTER,
						fn : this.search,
						scope : this
					},
					labelWidth : 55,
					items : [{
						fieldLabel : '角色名称',
						xtype : 'textfield',
						name : 'Q_roleName_S_LK',
						maxLength : 150
							// labelWidth:55
						}, {
						fieldLabel : '角色描述',
						xtype : 'textfield',
						name : 'Q_roleDesc_S_LK',
						maxLength : 150
					}, {
						xtype : 'button',
						text : '查询',
						iconCls : 'search',
						scope : this,
						handler : this.search
					}]
				});// end of the searchPanel
		// 角色列表面板
		this.gridPanel = new HT.EditorGridPanel({
			region : 'center',
			tbar : [{
						iconCls : 'btn-add',
						text : '添加角色',
						scope : this,
						handler : this.addRole,
						hidden : !isGranted('_AppRoleAdd')
					}, '-', {
						text : '删除角色',
						iconCls : 'btn-del',
						scope : this,
						handler : this.removeSelRs,
						hidden : !isGranted('_AppRoleDel')
					}],
			// 使用RowActions
			rowActions : true,
			url : __ctxPath + '/system/listAppRole.do',
			sort : [{
						field : "roleId",
						direction : "ASC"
					}],
			fields : [{
						name : 'roleId',
						type : 'int'
					}, 'roleName', 'roleDesc', {
						name : 'status',
						type : 'int'
					}, 'isDefaultIn'],
			columns : [{
						header : 'roleId',
						dataIndex : 'roleId',
						hidden : true
					}, {
						header : "状态",
						dataIndex : 'status',
						width : 30,
						renderer : function(value) {
							var str = '';
							if (value == '1') {// 激活用户
								str += '<img title="激活" src="'
										+ __ctxPath
										+ '/images/flag/customer/effective.png"/>';
							} else {// 禁用用户
								str += '<img title="禁用" src="'
										+ __ctxPath
										+ '/images/flag/customer/invalid.png"/>';
							}
							return str;
						}
					}, {
						header : "角色名称",
						dataIndex : 'roleName',
						width : 200
					}, {
						header : "角色描述",
						dataIndex : 'roleDesc',
						width : 400
					}, new Ext.ux.grid.RowActions({
								header : '管理',
								width : 200,
								actions : [{
									iconCls : 'btn-del',
									qtip : '删除',
									style : 'margin:0 3px 0 3px',
									fn : function(record) {
										var roleId = record.data.roleId;
										var isDefaultIn = record.data.isDefaultIn;
										if (roleId != -1 && isDefaultIn == '0'
												&& isGranted('_AppRoleDel'))
											return true;
										return false;
									}
								}, {
									iconCls : 'btn-edit',
									qtip : '编辑',
									style : 'margin:0 3px 0 3px',
									fn : function(record) {
										var roleId = record.data.roleId;
										var isDefaultIn = record.data.isDefaultIn;
										if (roleId != -1 && isDefaultIn == '0'
												&& isGranted('_AppRoleEdit'))
											return true;
										return false;
									}
								}, {
									iconCls : 'btn-grant',
									qtip : '授权',
									style : 'margin:0 3px 0 3px',
									fn : function(record) {
										var roleId = record.data.roleId;
										var isDefaultIn = record.data.isDefaultIn;
										if (roleId != -1 && isDefaultIn == '0'
												&& isGranted('_AppRoleGrant'))
											return true;
										return false;
									}
								}, {
									iconCls : 'btn-copyrole',
									qtip : '复制',
									style : 'margin:0 3px 0 3px',
									fn : function(record) {
										var roleId = record.data.roleId;
										var isDefaultIn = record.data.isDefaultIn;
										if (roleId != -1 && isDefaultIn != '0'
												&& isGranted('_AppRoleGrant'))
											return true;
										return false;
									}
								}],
								listeners : {
									scope : this,
									'action' : this.onRowAction
								}
							})],
			// 为Grid增加双击事件,双击行可编辑
			listeners : {
				scope : this,
				'rowdblclick' : this.rowdblclick
			}
		});

	},// end of the initUIComponents
	// 查询
	search : function() {
		$search({
					searchPanel : this.searchPanel,
					gridPanel : this.gridPanel
				});
	},
	// 重新加载列表
	reloadGrid : function() {
		// 刷新gridPanel
		this.gridPanel.getStore().reload();
	},
	// 新增角色
	addRole : function() {
		new AppRoleForm({
					scope : this,
					callback : this.reloadGrid
				}).show();
	},
	// 删除角色
	removeSelRs : function() {
		$delGridRs({
					url : __ctxPath + '/system/multiDelAppRole.do',
					grid : this.gridPanel,
					idName : 'roleId'
				});
	},
	/**
	 * 行的Action
	 * 
	 * @param grid
	 * @param record
	 * @param action
	 * @param row
	 * @param col
	 */
	onRowAction : function(grid, record, action, row, col) {
		this.roleId = record.data.roleId;
		this.roleName = record.data.roleName;
		switch (action) {
			case 'btn-del' :
				this.removeRs.call(this);
				break;
			case 'btn-edit' :
				this.editRs.call(this);
				break;
			case 'btn-grant' :
				this.grantRs.call(this);
				break;
			case 'btn-copyrole' :
				this.copyRs.call(this);
				break;
			default :
				break;
		}
	},

	// 删除角色
	removeRs : function() {
		$postDel({
					url : __ctxPath + '/system/multiDelAppRole.do',
					ids : this.roleId,
					grid : this.gridPanel
				});
	},
	// 编辑角色
	editRs : function() {
		new AppRoleForm({
					roleId : this.roleId,
					isCopy : 0,
					// 0代表不是复制
					scope : this,
					callback : this.reloadGrid
				}).show();
	},
	// 授权角色
	grantRs : function() {
		new RoleGrantRightView({
					roleId : this.roleId,
					roleName : this.roleName
				}).show();
	},
	// 复制角色
	copyRs : function() {
		new AppRoleForm({
					roleId : this.roleId,
					isCopy : 1,
					// 1代表是可以复制
					scope : this,
					callback : this.reloadGrid
				}).show();
	},
	// 为Grid增加双击事件,双击行可编辑
	rowdblclick : function(gridPanel, rowindex, e) {
		gridPanel.getSelectionModel().each(function(rec) {
			this.roleId = rec.data.roleId;
			if (rec.data.isDefaultIn == '0' && this.roleId != -1
					&& isGranted('_AppRoleEdit')) {// 不等于超级管理员
				this.editRs();
			}
		}, this);
	}
});
