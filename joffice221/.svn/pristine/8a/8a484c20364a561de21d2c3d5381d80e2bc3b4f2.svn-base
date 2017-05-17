/**
 * 选择联系人
 * 
 * @class EMailDialog
 * @extends Ext.Window
 */
EMailDialog = Ext.extend(Ext.Window, {
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		// 作用域
		this.scope = this.scope ? this.scope : this;
		// 默认为多选择
		this.single = this.single != null ? this.single : false;
		// 初始化组件
		this.initUI();
		EMailDialog.superclass.constructor.call(this, {
					title : this.title ? this.title : '选择联系人',
					iconCls : 'menu-appuser',
					width : 460,
					height : 440,
					layout : 'fit',
					border : false,
					resizable : false,
					modal : true,
					buttonAlign : 'center',
					items : [this.emailContactPanel],
					buttons : [{
								iconCls : 'btn-ok',
								text : '确定',
								scope : this,
								handler : this.confirm
							}, {
								text : '关闭',
								iconCls : 'btn-cancel',
								scope : this,
								handler : function() {
									this.close();
								}
							}]
				});
	},

	/**
	 * 初始化UI
	 */
	initUI : function() {
		// 个人通信录
		this.personlPanel = new Ext.tree.TreePanel({
					title : '个人通信录',
					iconCls : 'menu-personal-phoneBook',
					loader : new Ext.tree.TreeLoader({
								url : __ctxPath
										+ '/communicate/listPhoneGroup.do'
							}),
					root : new Ext.tree.AsyncTreeNode({
								expanded : true
							}),
					rootVisible : false,
					listeners : {
						scope : this,
						'click' : this.clickPersonlNode
					}
				});
		// 共享通信录
		this.sharedPanel = new Ext.tree.TreePanel({
					title : '共享通信录 ',
					iconCls : 'menu-phonebook-shared',
					loader : new Ext.tree.TreeLoader({
								url : __ctxPath
										+ '/communicate/sharePhoneBook.do'
							}),
					root : new Ext.tree.AsyncTreeNode({
								text : '共享通信录',
								expanded : true
							}),
					listeners : {
						scope : this,
						'click' : this.clickSharedNode
					}
				});
		//联系人列表
		this.gridPanel = new HT.GridPanel({
					region : 'center',
					height : 360,
					autoWidth : true,
					url : __ctxPath + '/communicate/listPhoneBook.do',
					baseParams : {
						'Q_appUser.userId_L_EQ' : curUserInfo.userId,
						'Q_phoneGroup.isPublic_SN_EQ' : 0
					},
					sort : [{
								field : 'phoneId',
								direction : 'DESC'
							}],
					fields : [{
								name : 'phoneId',
								type : 'int'
							}, 'fullname', 'email', 'title'],
					columns : [{
						header : "名称",
						dataIndex : 'fullname',
						renderer : function(value, meta, record) {
							var title = record.data.title;
							if (title == '先生') {
								return '<img src="' + __ctxPath
										+ '/images/flag/man.png"/>&nbsp;'
										+ value;
							} else {
								return '<img src="' + __ctxPath
										+ '/images/flag/women.png"/>&nbsp;'
										+ value;
							}
						}
					}]
				});
		//左边的面板
		this.leftPanel = new Ext.Panel({
					title : '通信录 ',
					region : 'west',
					split : true,
					collapsible : true,
					width : 180,
					margins : '5 0 5 5',
					layout : 'accordion',
					items : [this.personlPanel, this.sharedPanel]
				});
		//外面面板
		this.emailContactPanel = new Ext.Panel({
					width : 420,
					height : 410,
					layout : 'border',
					border : false,
					items : [this.leftPanel, this.gridPanel]
				});

	},// end of initUI function
	/**
	 * 点击个人通讯录
	 * @param {} node
	 */
	clickPersonlNode : function(node) {
		if (node != null) {
			var store =  this.gridPanel.getStore();
			store.proxy.conn.url = __ctxPath + '/communicate/listPhoneBook.do';
			store.baseParams = {
				'Q_appUser.userId_L_EQ' : curUserInfo.userId,
				'Q_phoneGroup.isPublic_SN_EQ' : 0
			};
			if (node.id != 0 && node.id != '0') {
				store.load({
							params : {
								start : 0,
								limit : 12,
								'Q_appUser.userId_L_EQ' : curUserInfo.userId,
								'Q_phoneGroup.groupId_L_EQ' : node.id,
								'Q_phoneGroup.isPublic_SN_EQ' : 0
							}
						});
			} else {
				store.load({
							params : {
								start : 0,
								limit : 12,
								'Q_appUser.userId_L_EQ' : curUserInfo.userId,
								'Q_phoneGroup.isPublic_SN_EQ' : 0

							}
						});

			}

		}
	},
	/**
	 * 点击共享通讯录
	 * @param {} node
	 */
	clickSharedNode : function(node) {
		if (node != null) {
			var store = this.gridPanel.getStore();
			store.baseParams = {
				Q_isShared_SN_EQ : 1
			};
			store.proxy.conn.url = __ctxPath + '/communicate/sharePhoneBook.do';
			store.load({
						params : {
							start : 0,
							limit : 12,
							Q_isShared_SN_EQ : 1
						}
					});
		}
	},
	/**
	 * 选择确认
	 */
	confirm : function() {
		var rows = this.gridPanel.getSelectionModel().getSelections();
		var emails = '';
		for (var i = 0; i < rows.length; i++) {
			emails += '' + rows[i].data.fullname + '' + '<'
					+ rows[i].data.email + '>' + ';';
		}

		if (this.callback) {
			this.callback.call(this, emails);
		}
		this.close();
	}
});