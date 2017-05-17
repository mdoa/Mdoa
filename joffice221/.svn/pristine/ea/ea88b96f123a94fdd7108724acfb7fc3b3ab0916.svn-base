/**
 * 用户选择器
 * 
 * @class UserDialog
 * @extends Ext.Window
 * @describe
 * 
 * <pre>
 *  使用说明： 
 *  new UserDialog({ 
 *  		scope : this,//当前作用域 
 *  		single : false,//默认true为单选用户,false为多选择用户
 *  		isForFlow : false,//默认true为可以选择发起人，,false为不选择发起人 
 *  		userIds : userIds, //用户的id
 *   		userName :userName, //用户的姓名
 *          callback : function(scope,ids, names,users){//回调方法：作用域，选择的用户id、用户姓名，用户信息 
 *          
 *          } }).show();
 * </pre>
 */
UserDialog = Ext.extend(Ext.Window, {
	constructor : function(conf) {
		Ext.applyIf(this, conf);

		this.scope = this.scope ? this.scope : this;
		// 默认为多单选择用户
		this.single = this.single != null ? this.single : true;

		this.initUI();
		UserDialog.superclass.constructor.call(this, {
					title : this.title ? this.title : '用户选择器',
					height : 450,
					width : 650,
					maximizable : true,
					modal : true,
					layout : 'border',
					items : [this.westPanel, this.searchPanel, this.gridPanel],
					buttonAlign : 'center',
					buttons : [{
								text : '确定',
								iconCls : 'btn-ok',
								scope : this,
								handler : this.confirm
							}, {
								text : '取消',
								iconCls : 'btn-cancel',
								scope : this,
								handler : this.close
							}, {
								text : '发起人',
								id : 'startUser',
								iconCls : 'menu-subuser',
								scope : this,
								handler : function() {
									this.callback
											.call(this, '__start', '[发起人]');
									this.close();
								},
								hidden : true
							}]
				});
		if (this.isForFlow) {
			Ext.getCmp('startUser').hidden = false;
		}
		if (!this.single) {
			this.add(this.southPanel);
			this.doLayout();
		}
	},
	// 按组织架构查找用户
	orgTreeClick : function() {
		var orgId = this.orgTreePanel.selectedNode.id;
		var store = this.gridPanel.getStore();
		var baseParams = null;
		if (orgId != 0) { // 若orgId==0,则代表为所有用户
			baseParams = {
				'Q_orgs.orgId_L_EQ' : orgId
			};
		} else {
			baseParams = {};
		}
		baseParams.start = 0;
		baseParams.limit = store.baseParams.limit;
		store.baseParams = baseParams;
		this.gridPanel.getBottomToolbar().moveFirst();
	},
	// 按职位查找用户
	posTreeClick : function() {
		var posId = this.posTreePanel.selectedNode.id;
		var store = this.gridPanel.getStore();
		var baseParams = null;
		if (posId != 0) { // 若orgId==0,则代表为所有用户
			baseParams = {
				'Q_positions.posId_L_EQ' : posId
			};
		} else {
			baseParams = {};
		}
		baseParams.start = 0;
		baseParams.limit = store.baseParams.limit;
		store.baseParams = baseParams;
		this.gridPanel.getBottomToolbar().moveFirst();
	},
	// 按角色查找用户
	roleTreeClick : function() {
		var roleId = this.rolePanel.selectedNode.id;
		var store = this.gridPanel.getStore();
		var baseParams = null;
		if (roleId != 0) { // 若orgId==0,则代表为所有用户
			baseParams = {
				'Q_roles.roleId_L_EQ' : roleId
			};
		} else {
			baseParams = {};
		}
		baseParams.start = 0;
		baseParams.limit = store.baseParams.limit;
		store.baseParams = baseParams;
		this.gridPanel.getBottomToolbar().moveFirst();
	},
	// 查找所有在线用户
	onlineClick : function() {
		var store = this.gridPanel.getStore();
		store.proxy.conn.url = __ctxPath + '/system/onlineAppUser.do';
		store.load({
					params : {
						start : 0,
						limit : 200
					}
				});
	},
	demensionSel : function(combo, record, index) {
		var demId = combo.getValue();
		this.orgTreePanel.loader = new Ext.tree.TreeLoader({
					baseParams : {
						demId : demId
					},
					dataUrl : __ctxPath + '/system/treeOrganization.do',
					requestMethod : 'GET'
				});
		this.orgTreePanel.selectedNode = null;
		this.orgTreePanel.root.reload();
	},
	/**
	 * 初始化UI
	 */
	initUI : function() {
		this.demStore = new Ext.data.ArrayStore({
					autoLoad : true,
					url : __ctxPath + '/system/comboDemension.do',
					fields : ['id', 'name']
				});
		// 维度选择下拉
		this.demensionCombo = new Ext.form.ComboBox({
					displayField : 'name',
					valueField : 'id',
					editable : false,
					emptyText : '所有维度',
					mode : 'local',
					triggerAction : 'all',
					store : this.demStore,
					listeners : {
						scope : this,
						'select' : this.demensionSel
					}
				});
		// 按组织架构创建

		// 组织树Panel
		this.orgTreePanel = new htsoft.ux.TreePanelEditor({
					border : false,
					url : __ctxPath + '/system/treeOrganization.do',
					region : 'center',
					scope : this,
					autoScroll : true,
					onclick : this.orgTreeClick
				});
		// 岗位树Panel
		this.posTreePanel = new htsoft.ux.TreePanelEditor({
					title : '按岗位查找',
					border : false,
					iconCls : 'dep-user',
					url : __ctxPath + '/system/treePosition.do',
					scope : this,
					autoScroll : true,
					onclick : this.posTreeClick
				});

		this.orgPanel = new Ext.Panel({
					border : false,
					title : '按组织架构查找',
					iconCls : 'menu-OrgSetting',
					layout : 'fit',
					items : [{
								xtype : 'panel',
								layout : 'border',
								border : false,
								items : [{
											xtype : 'panel',
											region : 'north',
											border : false,
											autoHeight : true,
											layout : 'fit',
											items : this.demensionCombo
										}, this.orgTreePanel]
							}]
				});
		// __ctxPath + '/system/treeAppRole.do'

		this.rolePanel = new htsoft.ux.TreePanelEditor({
					border : false,
					title : '按角色查找',
					iconCls : 'role-user',
					url : __ctxPath + '/system/treeAppRole.do',
					scope : this,
					autoScroll : true,
					onclick : this.roleTreeClick
				});

		this.onlinePanel = new Ext.Panel({
					collapsible : false,
					border : false,
					autoScroll : true,
					iconCls : 'online-user',
					title : '在线人员  ',
					listeners : {
						scope : this,
						'expand' : this.onlineClick
					}
				});

		// 按逻辑代码创建

		this.westPanel = new Ext.Panel({
					split : true,
					collapsible : true,
					split : true,
					region : 'west',
					header : false,
					// title:'导航',
					width : 185,
					layout : 'accordion',
					collapsible : true,
					items : [this.orgPanel, this.posTreePanel, this.rolePanel,
							this.onlinePanel]
				});
		// 搜索用户列
		this.searchPanel = new HT.SearchPanel({
					region : 'north',
					layout : 'hbox',
					layoutConfig : {
						padding : '5',
						align : 'middle'
					},
					defaults : {
						xtype : 'label',
						border : false,
						margins : {
							top : 0,
							right : 4,
							bottom : 4,
							left : 4
						}
					},
					items : [{
								text : '用户姓名：'
							}, {
								xtype : 'textfield',
								width : 400,
								name : 'Q_fullname_S_LK'
							}, {
								xtype : 'button',
								iconCls : 'btn-search',
								text : '查询',
								scope : this,
								handler : function() {
									$search({
												searchPanel : this.searchPanel,
												gridPanel : this.gridPanel
											});
								}
							}]
				});
		// 可选择的用户列表
		this.gridPanel = new HT.GridPanel({
					singleSelect : this.single,
					title : '可选用户',
					region : 'center',
					isShowTbar : false,
					url : __ctxPath + '/system/dialogAppUser.do?curDep=true',// 默认查找当前用户所在部门的用户
					fields : [{
								name : 'userId',
								type : 'int'
							}, 'fullname', 'title', 'mobile'],
					columns : [{
						header : "用户名",
						dataIndex : 'fullname',
						renderer : function(value, meta, record) {
							var title = record.data.title;
							if (title == 1)
								return '<img src="' + __ctxPath
										+ '/images/flag/man.png"/>&nbsp;'
										+ value;
							else
								return '<img src="' + __ctxPath
										+ '/images/flag/women.png"/>&nbsp;'
										+ value;
						},
						width : 60
					}]
				});

		if (!this.single) {

			this.gridPanel.addListener('rowdblclick', this.gridPanelRowClick,
					this);

			// 已选的用户列表
			var csm = new Ext.grid.CheckboxSelectionModel();
			this.selGridPanel = new Ext.grid.EditorGridPanel({
				title : '已选用户(双击行移除)',
				split : true,
				isShowTbar : false,
				width : 160,
				height : 430,
				showPaging : false,
				autoScroll : true,
				store : new Ext.data.ArrayStore({
							fields : ['userId', 'fullname', 'title']
						}),
				trackMouseOver : true,
				sm : csm,
				columns : [csm, new Ext.grid.RowNumberer(), {
					header : "用户名",
					dataIndex : 'fullname',
					renderer : function(value, meta, record) {
						var title = record.data.title;
						if (title == 1)
							return '<img src="' + __ctxPath
									+ '/images/flag/man.png"/>&nbsp;' + value;
						else if (title == 0)
							return '<img src="' + __ctxPath
									+ '/images/flag/women.png"/>&nbsp;' + value;
						else
							return value;
					}
				}],
				listeners : {
					scope : this,
					'rowdblclick' : this.selGridPanelRowDbClick
				}
			}); // end of this selectedUserGrid

			this.southPanel = new Ext.Panel({
				width : 200,
				region : 'east',
				layout : 'column',
				border : false,
				items : [new Ext.Panel({
									frame : true,
									width : 35,
									height : 430,
									layout : {
										type : 'vbox',
										pack : 'center',
										align : 'stretch'
									},
									defaults : {
										margins : '0 0 5 0'
									},
									defaultType : 'button',
									items : [{
												iconCls : 'add-all',
												text : '',
												scope : this,
												handler : this.addAll
											}, {
												iconCls : 'rem-all',
												text : '',
												scope : this,
												handler : this.selGridPanelRowDbClick
											}]
								}), {
							autoScroll : true,
							items : [this.selGridPanel]
						}]

			});

			if (this.userIds) {
				var selStore = this.selGridPanel.getStore();
				var arrUserIds = this.userIds.split(',');
				var arrUserName = this.userName.split(',');
				if (arrUserIds[0] == "") {
					var len = this.userIds.length;
					this.userIds = this.userIds.substring(1, len - 1);
					arrUserIds = this.userIds.split(',');
				}
				for (var index = 0; index < arrUserIds.length; index++) {
					if (arrUserIds[index] != "") {
						var newData = {
							userId : arrUserIds[index],
							fullname : arrUserName[index],
							title : 3
						};
						var newRecord = new selStore.recordType(newData);
						this.selGridPanel.stopEditing();
						selStore.add(newRecord);
					}
				}
			}
		}
	},// end of initUI function
	selGridPanelRowDbClick : function() {
		var store = this.selGridPanel.getStore();
		var rows = this.selGridPanel.getSelectionModel().getSelections();
		for (var i = 0; i < rows.length; i++) {
			this.selGridPanel.stopEditing();
			store.remove(rows[i]);
		}
	},
	gridPanelRowClick : function(grid, rowIndex, e) {
		var store = grid.getStore();
		var record = store.getAt(rowIndex);
		var selStore = this.selGridPanel.getStore();
		for (var i = 0; i < selStore.getCount(); i++) {
			if (selStore.getAt(i).data.userId == record.data.userId) {
				Ext.ux.Toast.msg('操作信息', '选项已被选中！');
				return;
			}
		}
		var recordType = selStore.recordType;
		selStore.add(new recordType(record.data));
	},
	/**
	 * 用户GridPanel（中间的Grid)行点击
	 * 
	 * @param {}
	 *            grid
	 * @param {}
	 *            rowIndex
	 * @param {}
	 *            e
	 */
	addAll : function(grid, rowIndex, e) {
		var selStore = this.selGridPanel.getStore();
		var rows = this.gridPanel.getSelectionModel().getSelections();
		for (var i = 0; i < rows.length; i++) {
			var userId = rows[i].data.userId;
			var fullname = rows[i].data.fullname;
			var title = rows[i].data.title;
			var isExist = false;
			// 查找是否存在该记录
			for (var j = 0; j < selStore.getCount(); j++) {
				if (selStore.getAt(j).data.userId == userId) {
					isExist = true;
					break;
				}
			}
			if (!isExist) {
				var newData = {
					userId : userId,
					fullname : fullname,
					title : title
				};
				var newRecord = new selStore.recordType(newData);
				this.selGridPanel.stopEditing();
				selStore.add(newRecord);
			}
		}
	},
	/**
	 * 选择用户
	 */
	confirm : function() {
		var userIds = '';
		var fullnames = '';
		// 返回的用户集
		var users = [];
		if (this.single) {// 选择单个用户
			var rows = this.gridPanel.getSelectionModel().getSelections();
			for (var i = 0; i < rows.length; i++) {
				if (i > 0) {
					userIds += ',';
					fullnames += ',';
				}
				userIds += rows[i].data.userId;
				fullnames += rows[i].data.fullname;
				users.push(rows[i].data);
			}
		} else {
			var selStore = this.selGridPanel.getStore();
			for (var i = 0; i < selStore.getCount(); i++) {
				if (i > 0) {
					userIds += ',';
					fullnames += ',';
				}
				userIds += selStore.getAt(i).data.userId;
				fullnames += selStore.getAt(i).data.fullname;
				users.push(selStore.getAt(i).data);
			}
		}

		if (this.callback) {
			this.callback.call(this.scope, userIds, fullnames, users);
		}
		this.close();
	}
});