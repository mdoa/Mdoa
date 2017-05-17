/**
 * 用户管理
 * 
 * @class AppUserView
 * @extends Ext.Panel
 */
AppUserView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(config) {
		Ext.applyIf(this, config);
		this.initUIComponents();
		AppUserView.superclass.constructor.call(this, {
					id : 'AppUserView',
					title : '账号信息',
					iconCls : 'menu-appuser',
					layout : 'border',
					items : [this.searchPanel, this.gridPanel],
					autoScroll : true
				});
	},
	// 初始化组件
	initUIComponents : function() {
		// 查询面板
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
						fieldLabel : '用户账号',
						xtype : 'textfield',
						name : 'Q_username_S_LK',
						maxLength : 150
							// labelWidth:55
						}, {
						fieldLabel : '用户姓名',
						xtype : 'textfield',
						name : 'Q_fullname_S_LK',
						maxLength : 150
					}, {
						fieldLabel : '入职时间:从',
						xtype : 'datefield',
						format : 'Y-m-d',
						name : 'Q_accessionTime_D_GT',
						maxLength : 150,
						labelWidth : 75
					}, {
						fieldLabel : '至',
						xtype : 'datefield',
						format : 'Y-m-d',
						name : 'Q_accessionTime_D_LT',
						maxLength : 150,
						labelWidth : 20
					}, {
						xtype : 'button',
						text : '查询',
						iconCls : 'search',
						scope : this,
						handler : this.search
					}]
				});// end of search panel
		// 人员列表面板
		this.gridPanel = new HT.GridPanel({
			region : 'center',
			tbar : [{
						text : '添加员工',
						iconCls : 'add-user',
						scope : this,
						handler : this.addUser,
						hidden : !isGranted('_AppUserAdd')
					}, '-', {
						text : '删除员工',
						iconCls : 'btn-del',
						scope : this,
						handler : this.removeSelRs,
						hidden : !isGranted('_AppUserDel')
					}],
			// 使用RowActions
			rowActions : true,
			url : __ctxPath + '/system/listAppUser.do',
			fields : [{
						name : 'userId',
						type : 'int'
					}, 'username', 'password', 'fullname', 'address', 'email',
					'depNames', // 'title',// 性别
					'posNames', 'roleNames', 'dynamicPwd', 'dyPwdStatus', {
						name : 'accessionTime'
					}, {
						name : 'status',
						type : 'int'
					}],
			columns : [{
						header : 'userId',
						dataIndex : 'userId',
						hidden : true
					}, {
						header : '状态',
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
						header : '账号',
						dataIndex : 'username',
						width : 60,
						isExp : true
					}, {
						header : '地址',
						dataIndex : 'address',
						hidden : true,
						exprint : true
					}, {
						header : '用户名',
						dataIndex : 'fullname',
						width : 60
					}, {
						header : '邮箱',
						dataIndex : 'email',
						width : 120
					}, {// 先不显示
						header : '所属部门',
						dataIndex : 'depNames',
						sortable : false,
						width : 60
					}, {
						header : '所在职位',
						dataIndex : 'posNames',
						sortable : false,
						width : 60
					}, {
						header : '拥有角色',
						dataIndex : 'roleNames',
						sortable : false,
						width : 60
					}, {
						header : '入职时间',
						dataIndex : 'accessionTime',
						width : 100
					}, {
						header : '令牌序列',
						dataIndex : 'dynamicPwd',
						sortable : false,
						width : 100
					}, {
						header : '令牌绑定',
						dataIndex : 'dyPwdStatus',
						sortable : false,
						width : 60,
						renderer : function(value) {
							if (value != null && value == 0) {
								return '<font color="red">未绑定</font>';
							} else if (value != null && value == 1) {
								return '<font color="green">已绑定</font>';
							}
						}
					}, new Ext.ux.grid.RowActions({
								header : '管理',
								width : 200,
								actions : [{
											iconCls : 'btn-del',
											qtip : '删除',
											style : 'margin:0 3px 0 3px',
											fn : function(record) {
												if (isGranted('_AppUserDel')&&record.data.userId!=1)
													return true;
												return false;
											}
										}, {
											iconCls : 'btn-edit',
											qtip : '编辑',
											style : 'margin:0 3px 0 3px',
											fn : function(record) {
												if (isGranted('_AppUserEdit'))
													return true;
												return false;
											}
										}, {
											iconCls : 'btn-password',
											qtip : '重置密码',
											style : 'margin:0 3px 0 3px',
											fn : function(record) {
												if (isGranted('_AppUserReset'))
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
		});// end gridPanel
	},
	// 查询
	search : function() {
		$search({
					searchPanel : this.searchPanel,
					gridPanel : this.gridPanel
				});
	},
	// 新增用户
	addUser : function() {
		App.clickTopTab('UserFormPanel');
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
		this.userId = record.data.userId;
		this.username = record.data.username;
		switch (action) {
			case 'btn-del' :
				this.removeRs.call(this);
				break;
			case 'btn-edit' :
				this.editRs.call(this);
				break;
			case 'btn-password' :
				this.resetRs.call(this);
				break;
			default :
				break;
		}
	},
	
	// 删除用户
	removeSelRs : function() {
		var selectRecords = this.gridPanel.getSelectionModel().getSelections();
		if(selectRecords[0].data.userId==1){
			Ext.ux.Toast.msg("信息", "超级管理员不删除,请选择其他用户!");
			return;
		}
		$delGridRs({
					url : __ctxPath + '/system/multiDelAppUser.do',
					grid : this.gridPanel,
					idName : 'userId'
				});
	},
	// 删除用户
	removeRs : function() {
		Ext.Msg.confirm('删除操作', '你确定要删除该用户吗?', function(btn) {
					if (btn == 'yes') {
						Ext.Ajax.request({
									url : __ctxPath
											+ '/system/multiDelAppUser.do',
									method : 'post',
									params : {
										ids : this.userId
									},
									scope : this,
									success : function(response) {
										var result = Ext.util.JSON.decode(response.responseText);
										if (result.msg == '') {
											Ext.ux.Toast.msg("操作信息", "用户删除成功");
										} else {
											Ext.ux.Toast.msg("操作信息", result.msg);
										}
										this.gridPanel.getStore().reload();
									},
									failure : function() {
										Ext.ux.Toast.msg("操作信息", "用户删除失败");
									}
								});
					}
				},this);
	},
	// 编辑用户
	editRs : function() {
		App.clickTopTab('UserFormPanel_' + this.userId, {
					userId : this.userId,
					username : this.username
				});
	},
	// 重置密码
	resetRs : function() {
		new SetPasswordForm({
					userId : this.userId
				}).show();
	},
	// 为Grid增加双击事件,双击行可编辑
	rowdblclick : function(gridPanel, rowindex, e) {
		gridPanel.getSelectionModel().each(function(rec) {
					this.userId = rec.data.userId;
					this.username = rec.data.username;
					if (isGranted('_AppUserEdit') && this.userId != 1) {// 不等于超级管理员
						this.editRs();
					}
				}, this);
	}

});