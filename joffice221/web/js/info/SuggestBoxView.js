Ext.ns('SuggestBoxView');

/**
 * @author:
 * @class SuggestBoxView
 * @extends Ext.Panel
 * @description [SuggestBox]管理
 * @company 杭州梦德软件有限公司
 * @createtime:2010-01-16
 */
SuggestBoxView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		SuggestBoxView.superclass.constructor.call(this, {
					id : 'SuggestBoxView',
					title : '意见箱管理',
					iconCls : 'menu-suggestbox',
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
					colNums : 8,
					keys : [{
								key : Ext.EventObject.ENTER,
								fn : this.search,
								scope : this
							}, {
								key : Ext.EventObject.ESC,
								fn : this.reset,
								scope : this
							}],
					labelWidth : 30,
					items : [{
								fieldLabel : '标题',
								name : 'Q_subject_S_LK',
								xtype : 'textfield',
								width : 110
							}, {
								fieldLabel : '日期  从',
								name : 'Q_createtime_D_GE',
								xtype : 'datefield',
								format : 'Y-m-d',
								labelWidth : 60
							}, {
								fieldLabel : '至',
								name : 'Q_createtime_D_LE',
								xtype : 'datefield',
								format : 'Y-m-d',
								labelWidth : 15
							}, {
								fieldLabel : '发送人',
								name : 'Q_senderFullname_S_LK',
								xtype : 'textfield',
								labelWidth : 60,
								width : 110
							}, {
								fieldLabel : '发送人IP',
								name : 'Q_senderIp_S_LK',
								xtype : 'textfield',
								labelWidth : 60,
								width : 110
							}, {
								fieldLabel : '是否公开',
								hiddenName : 'Q_isOpen_SN_EQ',
								xtype : 'combo',
								editable : false,
								labelWidth : 60,
								width : 90,
								mode : 'local',
								triggerAction : 'all',
								store : [['', '全部'], ['0', '公开'], ['1', '未公开']]

							}, {
								xtype : 'button',
								text : '查询',
								iconCls : 'search',
								scope : this,
								handler : this.search
							}, {
								name : 'Q_senderId_SN_EQ',
								id : 'SuggestBoxViewSearchSenderId',
								xtype : 'hidden'
							}, {
								xtype : 'button',
								text : '清空',
								iconCls : 'reset',
								scope : this,
								handler : this.reset
							}]
				});// end of the searchPanel

		// 顶端栏目条
		this.topbar = new Ext.Toolbar({
					items : [{
								iconCls : 'btn-add',
								text : '添加',
								xtype : 'button',
								scope : this,
								handler : this.createRs
							}]
				});

		if (!this.isOutSide) {
			this.topbar.add({
						iconCls : 'btn-del',
						text : '删除',
						xtype : 'button',
						scope : this,
						handler : this.removeSelRs
					});
		}

		// 意见列表面板
		this.gridPanel = new HT.GridPanel({
			region : 'center',
			tbar : this.topbar,
			sort : [{field:"boxId",direction:"DESC"}],
//			baseParams : {
//				'Q_senderId_L_EQ' : curUserInfo?curUserInfo.userId:null
//			},
			// 使用RowActions
			rowActions : true,
			url : __ctxPath + "/info/listSuggestBox.do",
			fields : [{
						name : 'boxId',
						type : 'int'
					}, 'subject', 'content', 'createtime', 'recUid',
					'recFullname', 'senderId', 'senderFullname', 'senderIp',
					'phone', 'email', 'isOpen', 'replyContent', 'replyTime',
					'replyId', 'replyFullname', 'status', 'queryPwd'],
			columns : [{
						header : 'boxId',
						dataIndex : 'boxId',
						hidden : true
					}, {
						header : '是否公开',
						width : 60,
						dataIndex : 'isOpen',
						renderer : this.isOpen
					}, {
						header : '状态',
						width : 45,
						dataIndex : 'status',
						renderer : this.status
					}, {
						header : '查询密码',
						wdith : 70,
						dataIndex : 'queryPwd',
						renderer : this.queryPwd
					}, {
						header : '意见标题',
						width : 300,
						dataIndex : 'subject'
					}, {
						header : '创建日期',
						dataIndex : 'createtime',
						renderer : function(value) {
							return value.substring(0, 10);
						}
					}, {
						header : '发送人',
						dataIndex : 'senderFullname',
						renderer : this.senderFullname
					}, {
						header : '发送人IP',
						dataIndex : 'senderIp'
					}, new Ext.ux.grid.RowActions({
								header : '管理',
								width : 200,
								actions : [{
											iconCls : 'btn-del',
											qtip : '删除',
											style : 'margin:0 2px 0 2px',
											fn : function(record) {
												if (!this.isOutSide&&record.data.senderId==curUserInfo.userId)
													return true;
												return false;
											}
										}, {
											iconCls : 'btn-edit',
											qtip : '编辑',
											style : 'margin:0 2px 0 2px',
											fn : function(record) {
												if (!this.isOutSide
														&& record.data.status == 0)
													return true;
												return false;
											}
										}, {
											iconCls : 'btn-suggest-reply',
											qtip : '回复',
											style : 'margin:0 2px 0 2px',
											fn : function(record) {
												if (!this.isOutSide && record.data.status == 1&&curUserInfo.userId==1)
													return true;
												return false;
											}
										}, {
											iconCls : 'btn-suggest-scan',
											qtip : '浏览',
											style : 'margin:0 2px 0 2px'
										}],
								listeners : {
									scope : this,
									'action' : this.onRowAction
								}
							})]
		});
		this.gridPanel.addListener({
					scope : this,
					'rowdblclick' : this.rowClick
				});
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
		var status = rec.data.status;
		var isOpen = rec.data.isOpen;
		var queryPwd = rec.data.queryPwd;
		var needPwd = false;
		if (isOpen == 1 && queryPwd != null && queryPwd != '') {
			needPwd = true;
		}
		if (status == 0) {
			this.editRs.call(this, rec);
		} else {
			this.scan.call(this, rec.data.boxId,needPwd);
		}

	},
	// 创建记录
	createRs : function() {
		new SuggestBoxForm({
					scope : this,
					callback : this.reloadType
				}).show();
	},
	// 按ID删除记录
	removeRs : function(id) {
		$postDel({
					url : __ctxPath + '/info/multiDelSuggestBox.do',
					ids : id,
					grid : this.gridPanel
				});
	},
	// 把选中ID删除
	removeSelRs : function() {
		$delGridRs({
					url : __ctxPath + '/info/multiDelSuggestBox.do',
					grid : this.gridPanel,
					idName : 'boxId'
				});
	},
	// 编辑Rs
	editRs : function(record) {
		new SuggestBoxForm({
					boxId : record.data.boxId,
					scope : this,
					callback : this.reloadType
				}).show();
	},
	// 回复
	reply : function(record) {
		new SuggestBoxReplyForm({
					boxId : record.data.boxId,
					scope : this,
					callback : this.reloadType
				}).show();
	},
	// 浏览
	scan : function(id, needPwd) {
		if (needPwd) {
			var form = new Ext.FormPanel({
						layout : 'form',
						bodyStyle : 'padding:10px',
						border : false,
						url : __ctxPath + '/info/matchSuggestBox.do',
						defaults : {
							anchor : '98%,98%'
						},
						items : [{
									fieldLabel : '访问密码',
									name : 'suggestBox.queryPwd',
									id : 'queryPwd',
									xtype : 'textfield',
									inputType : 'password'
								}, {
									name : 'suggestBox.boxId',
									value : id,
									xtype : 'hidden'
								}]
					});
			var win = new Ext.Window({
						layout : 'fit',
						iconCls : 'btn-add',
						items : form,
						modal : true,
						minHeight : 149,
						minWidth : 499,
						height : 150,
						width : 500,
						maximizable : true,
						title : '访问密码',
						buttonAlign : 'center',
						buttons : [{
							text : '确定',
							iconCls : 'btn-save',
							handler : function() {
								if (form.getForm().isValid()) {
									form.getForm().submit({
										method : 'POST',
										waitMsg : '正在提交数据...',
										success : function(fp, action) {
											win.close();
											new SuggestBoxDisplay({
														boxId : id
													}).show();
										},
										failure : function() {
											Ext.ux.Toast
													.msg('操作信息', '访问密码不正确！');
										}
									});
								}
							}
						}, {
							text : '取消',
							iconCls : 'btn-cancel',
							handler : function() {
								win.close();
							}
						}]
					});
			win.show();
		} else {
			new SuggestBoxDisplay({
						boxId : id
					}).show();
		}

	},
	// 是否公开
	isOpen : function(value) {
		if (value == 0) {
			return '<font color="green">公开</font>';
		} else {
			return '<font color="red">未公开</font>';
		}
	},
	// 状态
	status : function(value) {
		if (value == 0) {
			return '<font color="blue">草稿</font>';
		} else if (value == 1) {
			return '<font color="red">提交</font>';
		} else {
			return '<font color="green">已回复</font>';
		}
	},
	// 查询密码
	queryPwd : function(value, metadata, record) {
		var isOpen = record.data.isOpen;
		if (value != null && isOpen == 1) {
			return '<font color="red">需要密码</font>';
		} else {
			return '<font color="green">无密码</font>';
		}
	},
	// 发送人
	senderFullname : function(value) {
		if (value != null && value != '') {
			return value;
		} else {
			return '匿名';
		}
	},
	// 刷新gridPanel
	reloadType : function() {
		this.gridPanel.getStore().reload();
	},
	/**
	 * 行Actions
	 * @param {} grid
	 * @param {} record
	 * @param {} action
	 * @param {} row
	 * @param {} col
	 */
	onRowAction : function(grid, record, action, row, col) {
		var isOpen = record.data.isOpen;
		var queryPwd = record.data.queryPwd;
		var boxId = record.data.boxId;
		var needPwd = false;
		if (isOpen == 1 && queryPwd != null && queryPwd != '') {
			needPwd = true;
		}
		switch (action) {
			case 'btn-del' :
				this.removeRs.call(this, boxId);
				break;
			case 'btn-edit' :
				this.editRs.call(this, record);
				break;
			case 'btn-suggest-reply' :
				this.reply.call(this, record);
				break;
			case 'btn-suggest-scan' :
				this.scan.call(this, boxId,needPwd);
				break;
			default :
				break;
		}
	}
});



