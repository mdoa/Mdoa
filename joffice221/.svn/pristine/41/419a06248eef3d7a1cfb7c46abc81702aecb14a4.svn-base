/**
 * 内部信息管理-包括已发信息、已收信息和发送信息
 * 
 * @author lyy
 * @class MessageManageView
 * @extends Ext.Panel
 */
Ext.ns('MessageManageView');

/**
 * 已收信息
 * 
 * @class MessageRecView
 * @extends Ext.Panel
 */
MessageRecView = Ext.extend(Ext.Panel, {
	// 构造方法
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		this.initUIComponents();
		MessageRecView.superclass.constructor.call(this, {
					id : 'MessageRecView',
					flex : 1,
					layout : 'border',
					items : [this.centerPanel]
				});

	},// end of constructor
	// 面板初始化
	initUIComponents : function() {
		// 已收信息查询面板
		this.searchPanel = new HT.SearchPanel({
					region : 'north',
					frame : false,
					border : false,
					layout : 'form',
					keys : [{
								key : Ext.EventObject.ENTER,
								fn : this.search,
								scope : this
							}, {
								key : Ext.EventObject.ESC,
								fn : this.reset,
								scope : this
							}],
					colNums : 6,
					labelWidth : 30,
					items : [{
						fieldLabel : '类型',
						hiddenName : 'Q_shortMessage.msgType_SN_EQ',
						xtype : 'combo',
						mode : 'local',
						width : 100,
						editable : false,
						triggerAction : 'all',
						value : '',
						forceSelection : true,
						store : [['', '全部'], ['1', '个人信息'], ['2', '日程安排'],
								['3', '计划任务'], ['4', '待办任务提醒'], ['5', '系统消息']]

					}, {
						fieldLabel : '发送人',
						width : 100,
						xtype : 'textfield',
						name : 'Q_shortMessage.sender_S_LK',
						labelWidth : 50
					}, {
						fieldLabel : '从',
						width : 100,
						xtype : 'datefield',
						format : 'Y-m-d',
						name : 'Q_shortMessage.sendTime_D_GE',
						editable : false,
						labelWidth : 20
					}, {
						fieldLabel : '到',
						width : 100,
						xtype : 'datefield',
						format : 'Y-m-d',
						name : 'Q_shortMessage.sendTime_D_LE',
						editable : false,
						labelWidth : 20
					}, {
						text : '查询',
						xtype : 'button',
						iconCls : 'search',
						scope : this,
						handler : this.search
					}, {
						xtype : 'button',
						text : '重置',
						iconCls : 'btn-reset',
						scope : this,
						handler : this.reset
					}, {
						xtype : 'hidden',
						name : 'start',
						value : 0
					}, {
						xtype : 'hidden',
						name : 'limit',
						value : 11
					}]
				});
		// 已收信息列表顶部面板
		this.topbar = new Ext.Toolbar({
					height : 30,
					bodyStyle : 'text-align:left',
					items : [{
								xtype : 'button',
								text : '标记为已读',
								iconCls : 'ux-flag-blue',
								scope : this,
								handler : this.setReadFlag
							}, '-', {
								iconCls : 'btn-del',
								text : '删除信息',
								xtype : 'button',
								scope : this,
								handler : this.removeSelRs
							}]
				});
		// 已收信息列表面板
		this.gridPanel = new HT.GridPanel({
			region : 'center',
			tbar : this.topbar,
			// 使用RowActions
			rowActions : true,
			url : __ctxPath + '/info/listInMessage.do',
			fields : [{
						name : 'receiveId',
						mapping : 'receiveId',
						type : 'int'
					}, 'messageId', {
						name : 'msgType',
						mapping : 'shortMessage.msgType'
					}, {
						name : 'senderId',
						mapping : 'shortMessage.senderId'
					}, 'shortMessage.sender', {
						name : 'content',
						mapping : 'shortMessage.content'
					}, 'shortMessage.sendTime', 'readFlag'],
			columns : [{
						header : 'receiveId',
						dataIndex : 'receiveId',
						hidden : true
					}, {
						header : '状态',
						dataIndex : 'readFlag',
						width : 40,
						sortable : false,
						renderer : function(value) {
							return value == '1'
									? "<img src='"
											+ __ctxPath
											+ "/images/btn/info/email_open.png'/>"
									: "<img src='" + __ctxPath
											+ "/images/btn/info/email.png'/>";
						}
					}, {
						header : "类别",
						dataIndex : 'msgType',
						width : 40,
						sortable : false,
						renderer : function(value) {
							switch (value) {
								case 1 :
									return "<p style='color:green;'>个人信息</p>";
								case 2 :
									return "<p style='color:green;'>日程安排</p>";
								case 3 :
									return "<p style='color:green;'>计划任务</p>";
								case 4 :
									return "<p style='color:green;'>待办任务提醒</p>";
								case 5 :
									return "<p style='color:green;'>系统消息</p>";
								case 6 :
									return "<p style='color:green;'>其他</p>";
								default :
									return '';
							}
						}
					}, {
						header : "发送人",
						dataIndex : 'shortMessage.sender',
						sortable : false,
						width : 40
					}, {
						header : "内容",
						dataIndex : 'content',
						sortable : false,
						width : 300
					}, {
						header : "发送时间",
						dataIndex : 'shortMessage.sendTime',
						sortable : false,
						width : 90
					}, new Ext.ux.grid.RowActions({
								header : '操作',
								width : 120,
								actions : [{
											iconCls : 'btn-del',
											qtip : '删除',
											style : 'margin:0 3px 0 3px'
										}, {
											iconCls : 'btn-readdocument',
											qtip : '查看',
											style : 'margin:0 3px 0 3px'
										}, {
											iconCls : 'btn-update',
											qtip : '回复',
											style : 'margin:0 3px 0 3px',
											fn : function(record) {
												if (record.data.msgType == 1)
													return true;
												return false;
											}
										}],
								listeners : {
									scope : this,
									'action' : this.onRowAction
								}
							})]
		});
		// 添加gridPanel行双击事件
		this.gridPanel.addListener({
					scope : this,
					'rowdblclick' : this.rowdblclick
				});
		// 已收信息中部面板
		this.centerPanel = new Ext.Panel({
					region : 'center',
					title : '已收信息显示',
					layout : 'border',
					border : false,
					items : [this.searchPanel, this.gridPanel]
				});
	},
	// 行的Action
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.removeRs.call(this, record.data.receiveId);
				break;
			case 'btn-update' :
				this.reply.call(this, record.data.receiveId);
				break;
			case 'btn-readdocument' :
				this.read.call(this, record.data.receiveId);
				break;
			default :
				break;
		}
	},
	// 已收信息查询
	search : function() {
		$search({
					searchPanel : this.searchPanel,
					gridPanel : this.gridPanel
				});
	},
	// 已收信息查询条件重置
	reset : function(self) {
		this.searchPanel.getForm().reset();
	},
	// 查看消息
	read : function(receiveId) {
		new MessageDetail({
					receiveId : receiveId
				}).show();
	},
	// gridPanel行双击处理事件
	rowdblclick : function(grid, rowindex, e) {
		var rec = grid.getStore().getAt(rowindex);
		this.read.call(this, rec.data.receiveId);
	},
	// 标记为已读
	setReadFlag : function() {
		var grid = this.gridPanel;
		var selectRecords = grid.getSelectionModel().getSelections();

		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择信息！");
			return;
		}
		var ids = Array();
		for (var i = 0; i < selectRecords.length; i++) {
			ids.push(selectRecords[i].data.receiveId);
		}
		Ext.Ajax.request({
					url : __ctxPath + '/info/multiReadInMessage.do',
					params : {
						ids : ids
					},
					method : 'post',
					success : function() {
						Ext.ux.Toast.msg('操作信息', '成功标记所选信息为已读！');
						grid.getStore().reload();
					}
				});
	},
	// 把选中已收消息删除
	removeSelRs : function() {
		$delGridRs({
					url : __ctxPath + '/info/multiRemoveInMessage.do',
					grid : this.gridPanel,
					idName : 'receiveId'
				});
	},
	// 按receiveId删除单个已收消息记录
	removeRs : function(receiveId) {
		$postDel({
					url : __ctxPath + '/info/multiRemoveInMessage.do',
					ids : receiveId,
					grid : this.gridPanel
				});
	},
	// 回复
	reply : function(receiveId) {
		var reView1 = Ext.getCmp('MessageManageView');
		reView1.removeAll(true);
		var message = new MessageForm();
		reView1.add(message);
		var reply = Ext.getCmp('MessageForm').formPanel;
		reView1.doLayout();
		reply.form.load({
					url : __ctxPath + '/info/replyInMessage.do',
					params : {
						receiveId : receiveId
					},
					method : 'post',
					deferredRender : true,
					layoutOnTabChange : true,
					success : function() {
						Ext.Ajax.request({
									url : __ctxPath + '/info/knowInMessage.do',
									method : 'POST',
									params : {
										receiveId : receiveId
									},
									success : function(response, options) {
									},
									failure : function(response, options) {
									},
									scope : this
								});
					},
					failure : function() {
					}
				});
	}
});

/**
 * 已发信息
 * 
 * @author lyy
 * @class MessageSendView
 * @extends Ext.Panel
 */
MessageSendView = Ext.extend(Ext.Panel, {
	// 构造方法
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		this.initUIComponents();
		// 调用父类构造方法
		MessageSendView.superclass.constructor.call(this, {
					id : 'MessageSendView',
					flex : 1,
					layout : 'border',
					style : 'padding:0px',
					items : [this.centerPanel]
				});

	},// end of constructor
	// 初始化已发信息组件
	initUIComponents : function() {
		// 已发信息查询面板
		this.searchPanel = new HT.SearchPanel({
					region : 'north',
					height : 60,
					frame : false,
					border : false,
					layout : 'form',
					style : 'margin-top:100%;',
					keys : [{
								key : Ext.EventObject.ENTER,
								fn : this.search,
								scope : this
							}, {
								key : Ext.EventObject.ESC,
								fn : this.reset,
								scope : this
							}],
					colNums : 6,
					labelWidth : 40,
					items : [{
								fieldLabel : '收信人',
								width : 100,
								xtype : 'textfield',
								name : 'Q_userFullname_S_LK'
							}, {
								fieldLabel : '从',
								width : 100,
								xtype : 'datefield',
								format : 'Y-m-d',
								name : 'Q_shortMessage.sendTime_D_GE',
								editable : false,
								labelWidth : 20
							}, {
								fieldLabel : '到',
								width : 100,
								xtype : 'datefield',
								format : 'Y-m-d',
								name : 'Q_shortMessage.sendTime_D_LE',
								editable : false,
								labelWidth : 20
							}, {
								text : '查询',
								xtype : 'button',
								iconCls : 'search',
								scope : this,
								handler : this.search
							}, {
								xtype : 'button',
								text : '重置',
								scope : this,
								iconCls : 'reset',
								handler : this.reset
							}, {
								xtype : 'hidden',
								name : 'start',
								value : 0
							}, {
								xtype : 'hidden',
								name : 'limit',
								value : 11
							}]
				});
		// 已发信息列表顶部面板
		this.topbar = new Ext.Toolbar({
					height : 30,
					bodyStyle : 'text-align:left',
					items : [{
								iconCls : 'btn-del',
								text : '删除信息',
								xtype : 'button',
								scope : this,
								handler : this.removeSelRs
							}]
				});
		// 已发信息列表面板
		this.gridPanel = new HT.GridPanel({
					region : 'center',
					tbar : this.topbar,
					// 使用RowActions
					rowActions : true,
					url : __ctxPath + '/info/listShortMessage.do',
					fields : ['receiveId', {
								name : 'messageId',
								mapping : 'shortMessage.messageId'
							}, {
								name : 'msgType',
								mapping : 'shortMessage.msgType'
							}, {
								name : 'content',
								mapping : 'shortMessage.content'
							}, {
								name : 'userId',
								type : 'int'
							}, 'shortMessage.sendTime', 'userFullname'],
					columns : [{
								header : 'receiveId',
								dataIndex : 'receiveId',
								hidden : true
							}, {
								header : "收信人",
								dataIndex : 'userFullname',
								sortable : false,
								width : 40
							}, {
								header : "内容",
								dataIndex : 'content',
								sortable : false,
								width : 300
							}, {
								header : "发送时间",
								dataIndex : 'shortMessage.sendTime',
								sortable : false,
								width : 90
							}, new Ext.ux.grid.RowActions({
										header : '操作',
										width : 120,
										actions : [{
													iconCls : 'btn-del',
													qtip : '删除',
													style : 'margin:0 3px 0 3px'
												}, {
													iconCls : 'btn-readdocument',
													qtip : '查看',
													style : 'margin:0 3px 0 3px'
												}, {
													iconCls : 'btn-update',
													qtip : '重发',
													style : 'margin:0 3px 0 3px'
												}],
										listeners : {
											scope : this,
											'action' : this.onRowAction
										}
									})]

				})
		// 添加gridPanel行双击事件
		this.gridPanel.addListener({
					scope : this,
					'rowdblclick' : this.rowdblclick
				});
		// 已发信息中部面板
		this.centerPanel = new Ext.Panel({
					region : 'center',
					title : '已发信息显示',
					layout : 'border',
					border : false,
					items : [this.searchPanel, this.gridPanel]
				});
	},
	// 已发信息行Action
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.removeRs.call(this, record.data.receiveId);
				break;
			case 'btn-update' :
				this.reSend.call(this, record);
				break;
			case 'btn-readdocument' :
				this.read.call(this, record);
				break;
			default :
				break;
		}
	},
	// 已发信息查询
	search : function() {
		$search({
					searchPanel : this.searchPanel,
					gridPanel : this.gridPanel
				});
	},
	// 已发信息查询条件重置
	reset : function() {
		this.searchPanel.getForm().reset();
	},
	// 把选中已发消息删除
	removeSelRs : function() {
		$delGridRs({
					url : __ctxPath + '/info/multiRemoveInMessage.do',
					grid : this.gridPanel,
					idName : 'receiveId'
				});
	},
	// 按receiveId删除单个已发消息记录
	removeRs : function(receiveId) {
		$postDel({
					url : __ctxPath + '/info/multiRemoveInMessage.do',
					ids : receiveId,
					grid : this.gridPanel
				});
	},
	// 查看已发消息
	read : function(record) {
		new MessageDetail({
					receiveId : record.data.receiveId,
					userId : record.data.userId,
					content : record.data.content,
					isSend : true,
					scope : this,
					callback : function(){
						this.gridPanel.getStore().reload();
					}
				}).show();
	},
	// 已发消息gridPanel行双击处理事件
	rowdblclick : function(grid, rowindex, e) {
		var rec = grid.getStore().getAt(rowindex);
		this.read.call(this, rec);
	},
	// 重发已发信息
	reSend : function(record) {
		var grid = this.gridPanel;
		Ext.Ajax.request({
					url : __ctxPath + '/info/sendShortMessage.do',
					params : {
						userId : record.data.userId + ',',
						content : record.data.content
					},
					method : 'post',
					success : function() {
						Ext.ux.Toast.msg('操作信息', '重发成功！');
						grid.getStore().reload();
					}
				});
	}

});

/**
 * 内部信息管理
 * 
 * @author lyy
 * @class MessageManageView
 * @extends Ext.Panel
 */
MessageManageView = Ext.extend(Ext.Panel, {
			// 构造方法
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 初始化组件
				this.initUIComponents();
				// 调用父类构造
				MessageManageView.superclass.constructor.call(this, {
							id : 'MessageManageView',
							region : 'center',
							iconCls : "menu-message",
							title : '我的消息',
							layout : 'vbox',
							layoutConfig : {
								align : 'stretch'
							},
							tbar : this.toolbar,
							// autoHeight : true,
							items : []
						});
				// 初始化已收面板
				this.addRecPanel(this);
			},// end of constructor

			// 初始化组件
			initUIComponents : function() {
				this.toolbar = new Ext.Toolbar({
							height : 30,
							layout : 'column',
							items : [{
								text : '发送信息',
								iconCls : 'btn-sendM',
								handler : this.addSendFormPanel
										.createCallback(this)
							}, '-', {
								xtype : 'button',
								text : '已发信息',
								iconCls : 'btn-sendMessage',
								handler : this.addSendPanel
										.createCallback(this)
							}, '-', {
								text : '已收信息',
								iconCls : 'btn-receiveMessage',
								handler : this.addRecPanel.createCallback(this)
							}]
						});
			},// end of initUIComponents
			// 已收信息
			addRecPanel : function(panel) {
				panel.removeAll(true);
				var recPanel = new MessageRecView();
				panel.add(recPanel);
				panel.doLayout();
			},
			// 已发信息
			addSendPanel : function(panel) {
				panel.removeAll(true);
				var sendPanel = new MessageSendView();
				panel.add(sendPanel);
				panel.doLayout();
			},
			// 发送信息
			addSendFormPanel : function(panel) {
				panel.removeAll(true);
				var sendForm = new MessageForm();
				panel.add(sendForm);
				panel.doLayout();
			}
		});