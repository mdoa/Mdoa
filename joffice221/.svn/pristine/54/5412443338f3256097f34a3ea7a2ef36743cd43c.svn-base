/**
 * 在线用户选择器
 * 
 * @class OnlineUserDialog
 * @extends Ext.Window
 * @example
 * 
 * <pre>
 * new OnlineUserDialog({
 *  	title :'选择在线用户' //标题  默认是'选择在线用户'，也可以自定义标题
 * 		single: true,   //是否单选 默认是单选在线用户
 * 		scope:this,   //作用域
 * 		callback :function(ids,names){//回调函数,返回在线用户ids和在线用户名称
 * 
 * 		}	
 * 	}
 * </pre>
 */

OnlineUserDialog = Ext.extend(Ext.Window, {
	//构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 作用域
		this.scope = this.scope ? this.scope : this;
		// 默认为单选在线用户
		this.single = this.single != null ? this.single : true;
		//初始化
		this.initUI();
		OnlineUserDialog.superclass.constructor.call(this, {
					title : '选择在线用户',
					iconCls : 'menu-appuser',
					width : 440,
					height : 420,
					border : false,
					layout : 'fit',
					modal : true,
					buttonAlign : 'center',
					buttons : [{
								iconCls : 'btn-ok',
								text : '确定',
								scope : this,
								handler : this.confirm
							}, {
								text : '取消',
								iconCls : 'btn-cancel',
								scope : this,
								handler : function() {
									this.close();
								}
							}],
					items : [this.contactPanel]
				});
	},
	//初始化组件
	initUI : function() {
		/**
		 * 按部门分类树
		 */
		this.depTreePanel = new Ext.tree.TreePanel({
					title : '按部门分类 ',
					iconCls : 'dep-user',
					autoScroll : true,
					loader : new Ext.tree.TreeLoader({
								url : __ctxPath + '/system/listDepartment.do'
							}),
					root : new Ext.tree.AsyncTreeNode({
								expanded : true
							}),
					rootVisible : false,
					listeners : {
						scope : this,
						'click' : this.clickDepNode
					}
				});

		/**
		 * 按角色分类树
		 */
		this.roleTreePanel = new Ext.tree.TreePanel({
					iconCls : 'role-user',
					title : '按角色分类 ',
					autoScroll : true,
					loader : new Ext.tree.TreeLoader({
								url : __ctxPath + '/system/treeAppRole.do'
							}),
					root : new Ext.tree.AsyncTreeNode({
								expanded : true
							}),
					rootVisible : false,
					listeners : {
						scope : this,
						'click' : this.clickRoleNode
					}
				});

		/**
		 * 所有在线人员
		 */
		this.onlineTreePanel = new Ext.Panel({
					autoScroll : true,
					iconCls : 'online-user',
					title : '所有在线人员  ',
					listeners : {
						scope : this,
						'expand' : this.clickOnlinePanel
					}
				});
		/**
		 * 
		 */
		this.gridPanel = new HT.GridPanel({
					autoScroll : true,
					height : 345,
					singleSelect : this.single,
					url : __ctxPath + '/system/onlineAppUser.do',
					fields : [{
								name : 'userId',
								type : 'int'
							}, 'fullname', 'title'],
					columns : [{
						header : "用户名",
						dataIndex : 'fullname',
						renderer : function(value, meta, record) {
							var title = record.data.title;
							if (title == '1') {
								return '<img src="' + __ctxPath
										+ '/images/flag/man.png"/>&nbsp;'
										+ value;
							} else {
								return '<img src="' + __ctxPath
										+ '/images/flag/women.png"/>&nbsp;'
										+ value;
							}
						},
						width : 60
					}]
				});
		/**
		 * 展示的内容
		 */
		this.contactPanel = new Ext.Panel({
					id : 'contactPanel',
					width : 460,
					height : 400,
					layout : 'border',
					border : false,
					items : [{
						region : 'west',
						split : true,
						header : false,
						collapsible : true,
						width : 160,
						layout : 'accordion',
						items : [this.depTreePanel, this.roleTreePanel,
								this.onlineTreePanel]
					}, {
						region : 'center',
						layout : 'fit',
						width : 250,
						items : [this.gridPanel]
					}]
				});

	},
	/**
	 * 确定
	 */
	confirm : function() {
		var grid = this.gridPanel;
		var rows = grid.getSelectionModel().getSelections();
		var userIds = '';
		var fullnames = '';
		for (var i = 0; i < rows.length; i++) {
			if (i > 0) {
				userIds += ',';
				fullnames += ',';
			}
			userIds += rows[i].data.userId;
			fullnames += rows[i].data.fullname;
		}

		if (this.callback != null) {
			this.callback.call(this, userIds, fullnames);
		}
		this.close();
	},
	/**
	 * 点击部门树节点
	 * @param {} node
	 */
	clickDepNode : function(node) {
		if (node != null) {
			var store = this.gridPanel.getStore();
			store.baseParams = {
				depId : node.id
			};
			store.load();
		}
	},
	/**
	 * 点击角色树的节点
	 * @param {} node
	 */
	clickRoleNode : function(node) {
		if (node != null) {
			var store = this.gridPanel.getStore();
			store.baseParams = {
				roleId : node.id
			};
			store.load();
		}
	},
	/**
	 * 点击在线用户树的节点
	 */
	clickOnlinePanel : function() {
		var store = this.gridPanel.getStore();
		store.baseParams = {
			depId : null,
			roleId : null
		};
		store.load();
	}
});