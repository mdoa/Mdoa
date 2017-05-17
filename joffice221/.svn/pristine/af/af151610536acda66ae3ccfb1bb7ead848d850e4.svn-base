Ext.ns("OutMailBoxView");
/**
 * 外部邮箱-包括收信箱、发信箱和草稿箱，垃圾箱
 * 
 * @author zqg
 * @class PersonalMailBoxView
 * @extends Ext.Panel
 */
OutMailBoxView = Ext.extend(Ext.Panel, {
	// 构造方法
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		// 必须先初始化
		this.initUI();
		// 调用父类构造方法
		OutMailBoxView.superclass.constructor.call(this, {
					title : '我的外部邮箱',
					iconCls : 'menu-mail_box',
					layout : 'border',
					id : 'OutMailBoxView',
					items : [this.leftPanel, this.outMailCenterView]
				});
	},
	// 初始化面板
	initUI : function() {
		// 目录树面板
		this.treePanel = new htsoft.ux.TreePanelEditor({
					autoScroll : true,
					border : false,
					collapsible : false,
					scope : this,
					url : __ctxPath + '/communicate/treeOutMailFolder_.do',
					onclick : this.nodeClick
				});
		this.treePanel.on('contextmenu', this.contextmenu, this);
		// 左部菜单面板
		this.leftPanel = new Ext.Panel({
					layout : 'fit',
					collapsible : true,
					region : 'west',
					title : '我的邮箱目录',
					width : 180,
					autoScroll : true,
					split : true,
					tbar : [{
								text : '收邮件',
								iconCls : 'btn-mail_receive',
								scope : this,
								handler : this.receiveAllMail
							}, {
								text : '发邮件',
								iconCls : 'btn-mail_send',
								scope : this,
								handler : this.defaultSendMail
							}, {
								xtype : 'button',
								iconCls : 'btn-refresh',
								text : '刷新',
								scope : this,
								handler : this.refresh
							}],
					items : [this.treePanel]
				});
		// 查询面板
		this.searchPanel = new HT.SearchPanel({
					layout : 'form',
					region : 'north',
					colNums : 6,
					labelWidth : 120,
					keys : [{
								key : Ext.EventObject.ENTER,
								fn : this.search,
								scope : this
							}, {
								key : Ext.EventObject.ESC,
								fn : this.reset,
								scope : this
							}],
					items : [{
								fieldLabel : '查询条件：邮件主题',
								xtype : 'textfield',
								name : 'Q_title_S_LK',
								width : 125,
								maxLength : 125
							}, {
								fieldLabel : '发件人',
								xtype : 'textfield',
								name : 'Q_senderName_S_LK',
								labelWidth : 50,
								width : 125,
								maxLength : 125
							}, {
								fieldLabel : '收件人',
								xtype : 'textfield',
								name : 'Q_receiverNames_S_LK',
								format : 'Y-m-d',
								width : 125,
								labelWidth : 50,
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
				});
		// 顶部菜单面板
		this.topbar = new Ext.Toolbar({
					height : 30,
					bodyStyle : 'text-align:left',
					items : [{
								iconCls : 'btn-mail_remove',
								text : '删除',
								xtype : 'button',
								scope : this,
								handler : this.multiDelMail
							}, {
								iconCls : 'btn-mail_move',
								text : '移至',
								xtype : 'button',
								scope : this,
								handler : this.moveMails
							}, {
								iconCls : 'btn-mail_refresh',
								text : '刷新',
								scope : this,
								handler : this.refresh
							}]
				});
		var expander = new Ext.ux.grid.RowExpander({
			tpl : new Ext.Template('<div style="padding:5px 5px 5px 62px;"><b>内容摘要:</b> {content}</div>')

		});
		// 邮件信息列表
		this.gridPanel = new HT.GridPanel({
			id : 'outMailGrid',
			region : 'center',
			tbar : this.topbar,
			expander : expander,
			trackMouseOver : true,
			disableSelection : false,
			loadMask : true,
			stripeRows : true,
			// 使用RowActions
			rowActions : true,
			url : __ctxPath + '/communicate/listOutMail_.do',
			sort : [{
						field : "mailDate",
						direction : "DESC"
					}],
			fields : [{
						name : 'folderId',
						mapping : 'outMailFolder.folderId'
					}, {
						name : 'folderType',
						mapping : 'outMailFolder.folderType'
					}, {
						name : 'setId',
						mapping : 'outMailUserSeting.setId'
					}, 'mailId', 'readFlag', 'replyFlag', 'mailDate',
					'fileIds', 'title', 'receiverNames', 'content',
					'senderName'],
			columns : [expander, {
						header : 'mailId',
						dataIndex : 'mailId',
						hidden : true
					}, {
						header : 'folderId',
						dataIndex : 'folderId',
						hidden : true
					}, {
						header : 'folderType',
						dataIndex : 'folderType',
						hidden : true
					}, {
						header : 'setId',
						dataIndex : 'setId',
						hidden : true
					}, {
						header : '阅读',
						dataIndex : 'readFlag',
						width : 45,
						renderer : function(value, metadata, record, rowIndex,
								colIndex) {
							if (record.data.folderType == 3) {// 草稿
								return '<img title="草稿" src="' + __ctxPath
										+ '/images/flag/mail/mail_draft.png"/>';
							} else if (value == 0) {
								return '<img title="未读" src="' + __ctxPath
										+ '/images/flag/mail/mail.png"/>';
							} else {
								return '<img title="已读" src="' + __ctxPath
										+ '/images/flag/mail/mail_open.png"/>';
							}
						}
					}, {
						header : '回复',
						dataIndex : 'replyFlag',
						width : 45,
						renderer : function(value, record) {
							switch (value) {
								case 1 :
									return '<img title="已回复" src="'
											+ __ctxPath
											+ '/images/flag/mail/replyed.png" style="background: white;"/>';
								default :
									return '';
							}
						}
					}, {
						header : '邮件主题',
						dataIndex : 'title',
						sortable : false,
						width : 150
					}, {
						header : '发件人',
						dataIndex : 'senderName',
						width : 80
					}, {
						header : '收件人',
						dataIndex : 'receiverNames',
						sortable : false,
						width : 80,
						renderer : function(value) {
							if (value != '')
								return value;
							else
								return '(收信人未写)';
						}
					}, {
						header : '发信时间',
						width : 80,
						dataIndex : 'mailDate',
						renderer : function(value) {
							return value.substring(0, 16);
						}
					}, new Ext.ux.grid.RowActions({
								header : '管理',
								width : 100,
								actions : [{
											iconCls : 'btn-del',
											qtip : '删除',
											scope : this,
											style : 'margin:0 3px 0 3px'
										}, {
											iconCls : 'btn-mail_edit',
											qtip : '查看',
											scope : this,
											style : 'margin:0 3px 0 3px'
										}],
								listeners : {
									scope : this,
									'action' : this.onRowAction
								}
							})]
		});
		// gridPanel添加行双击事件
		this.gridPanel.addListener('rowdblclick', function(grid, rowIndex, e) {
					grid.getSelectionModel().each(function(rec) {
								OutMailBoxView.prototype.edit(rec, rowIndex);
							});
				});
		// 中部菜单面板
		this.centerPanel = new Ext.Panel({
					id : 'outMailCenterPanel',
					region : 'center',
					autoScroll : true,
					layout : 'border',
					title : '收件箱',
					items : [this.searchPanel, this.gridPanel]
				});
		this.outMailCenterView = new Ext.Panel({
					id : 'outMailCenterView',
					region : 'center',
					autoScroll : true,
					layout : 'fit',
					items : [this.centerPanel]
				});

		if (this.setId == null) {
			Ext.Ajax.request({
				url : __ctxPath + '/communicate/getDefaultOutMailUserSeting.do',
				scope : this,
				success : function(resp, options) {
					var outMailUserSeting = Ext.util.JSON
							.decode(resp.responseText).data;
					if (outMailUserSeting != null) {
						this.setId = outMailUserSeting.setId;
						this.centerPanel.setTitle(outMailUserSeting.accountName
								+ '[收信箱]');
					} else {
						Ext.ux.Toast.msg("操作信息", "请先在邮箱设置菜单中设置邮箱配置！");
					}
				}
			});
		}
	},// end of initUI
	// 查询
	search : function() {
		$search({
					searchPanel : this.searchPanel,
					gridPanel : this.gridPanel
				});
	},
	// 重置查询表单
	reset : function() {
		this.searchPanel.getForm().reset();
	},
	// 刷新
	refresh : function() {
		this.treePanel.root.reload();
		this.gridPanel.getStore().reload();
	},
	// 行的Action
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.delMail.call(this, record);
				break;
			case 'btn-mail_edit' :
				this.edit.call(this, record, row);
				break;
			default :
				break;
		}
	},
	// 移动邮件
	moveMails : function() {
		var selectRecords = this.gridPanel.getSelectionModel().getSelections();
		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要移动的邮件！");
			return;
		}
		var ids = Array();
		for (var i = 0; i < selectRecords.length; i++) {
			ids.push(selectRecords[i].data.mailId);
		}
		var setId = selectRecords[0].data.setId;
		new OutMailMove({
					ids : ids,
					setId : setId,
					scope : this,
					callback : function() {
						var outMailCenterView = Ext.getCmp("outMailCenterView");
						var centerPanel = Ext.getCmp("outMailCenterPanel");
						outMailCenterView.remove('ShowoutMailDetail');
						Ext.getCmp('outMailGrid').getStore().reload({
									params : {
										start : 0,
										limit : 25
									}
								});
						centerPanel.show();
						outMailCenterView.doLayout();
					}
				}).show();
	},

	// 节点右击事件
	contextmenu : function(node, e) {
		this.nodeClick(node);
		selected = new Ext.tree.TreeNode({
					id : node.id,
					text : node.text
				});
		// 创建右键菜单
		var treeMenu = new Ext.menu.Menu({
					items : []
				});
		treeMenu.clearMons();
		if (this.folderType > -1) {
			treeMenu.add({
						text : '新增分类',
						iconCls : 'btn-add',
						handler : this.createNode,
						scope : this
					});
		}
		if (this.folderType == 0) {
			treeMenu.add({
						text : '收邮件',
						iconCls : 'btn-mail_receive',
						handler : this.receiveMail,
						scope : this
					});
			treeMenu.add({
						text : '发邮件',
						iconCls : 'btn-mail_send',
						handler : this.sendMail,
						scope : this
					});
		}
		if (this.folderType > 4) { // 总分类不能删除，和修改
			treeMenu.add({
						text : '修改分类',
						iconCls : 'btn-edit',
						handler : this.editNode,
						scope : this
					}, {
						text : '删除分类',
						iconCls : 'btn-del',
						handler : this.deleteNode,
						scope : this
					});
		}
		treeMenu.showAt(e.getXY());
	},
	// 新建节点
	createNode : function() {
		var treePanel = this.treePanel;
		var parentId = this.folderId;
		var setId = this.setId;
		new OutMailFolderForm({
					parentId : parentId,
					setId : this.setId,
					scope : this,
					callback : function() {
						treePanel.root.reload();
					}
				}).show();
	},
	// 编辑节点
	editNode : function() {
		var treePanel = this.treePanel;
		var folderId = this.folderId;
		new OutMailFolderForm({
					folderId : folderId,
					callback : function() {
						treePanel.root.reload();
					}
				}).show();
	},
	// 删除节点
	deleteNode : function() {
		treePanel = this.treePanel;
		var folderId = this.folderId;
		if (folderId <= 4)
			return;
		Ext.Ajax.request({
			url : __ctxPath + '/communicate/countOutMailFolder_.do',
			params : {
				folderId : folderId
			},
			method : 'post',
			scope : this,
			success : function(result, request) {
				var res = Ext.util.JSON.decode(result.responseText);
				if (res.count == 0) {
					Ext.Msg.confirm('警告信息',
							'删除目录，子目录和邮件也一并删除,<br/>且无法恢复，确定要删除吗?',
							function(btn) {
								if (btn == 'yes') {
									Ext.Ajax.request({
										url : __ctxPath
												+ '/communicate/removeOutMailFolder_.do',
										params : {
											folderId : folderId
										},
										method : 'post',
										success : function(result, request) {
											Ext.ux.Toast.msg('操作信息', '成功删除目录！');
											treePanel.root.reload();
										},
										failure : function(result, request) {
											Ext.MessageBox.show({
														title : '操作信息',
														msg : '信息保存出错，请联系管理员！',
														buttons : Ext.MessageBox.OK,
														icon : 'ext-mb-error'
													});
										}
									});
								}
							});

				}// if count == 0
				// 文件夹中存在邮件
				else {
					Ext.Msg.confirm('警告信息', '删除目录，子目录也一并删除,该文件夹及其子目录下还有'
									+ res.count + '封邮件,确定要删除吗?', function(btn) {
								if (btn == 'yes') {
									Ext.Ajax.request({
										url : __ctxPath
												+ '/communicate/removeOutMailFolder_.do',
										params : {
											folderId : folderId,
											count : res.count
										},
										method : 'post',
										scope : this,
										success : function(result, request) {
											Ext.ux.Toast.msg('操作信息', '成功删除目录！');
											this.treePanel.root.reload();
										},
										failure : function(result, request) {
											Ext.MessageBox.show({
														title : '操作信息',
														msg : '信息保存出错，请联系管理员！',
														buttons : Ext.MessageBox.OK,
														icon : 'ext-mb-error'
													});
										}

									});
								}
							}, this);

				}
			},
			failure : function(result, request) {
				Ext.MessageBox.show({
							title : '操作信息',
							msg : '信息保存出错，请联系管理员！',
							buttons : Ext.MessageBox.OK,
							icon : 'ext-mb-error'
						});
			}

		});
	},
	// 收所有邮件
	receiveAllMail : function() {
		this.receiveMail.call(this, false);
	},
	// 收邮件
	receiveMail : function(flage) {// flage为true时收所选邮箱的邮件，为false时收取所有邮箱邮件
		var centerPanel = this.centerPanel;
		var gridPanel = this.gridPanel;
		var setId = this.setId;
		var folderId = this.folderId;
		if (Ext.isEmpty(this.selectNode)) {
			Ext.ux.Toast.msg('操作信息', '请选择收件邮箱！');
			return;
		}
		var accountName = this.selectNode.attributes.accountName;
		var text = this.selectNode.text;
		Ext.MessageBox.show({
					msg : '邮件收取中，请稍候...',
					width : 300,
					wait : true,
					progress : true,
					closable : true,
					waitConfig : {
						interval : 200
					},
					icon : Ext.Msg.INFO
				});
		var url = __ctxPath + '/communicate/fetchOutMail_.do';
		if (flage) {
			url = __ctxPath + '/communicate/fetchOutMail_.do?setId='
					+ this.setId;
		}
		Ext.Ajax.request({
					url : url,
					timeout : 12000000,
					success : function(response, opts) {
						Ext.MessageBox.hide();
						var result = Ext.util.JSON
											.decode(response.responseText);
						if (!result.success) 
							Ext.ux.Toast
												.msg('操作信息', result.message);
						var boxName = "收件箱";
						if (folderId > 0) {
							boxName = text;
						}
						centerPanel.setTitle(accountName + '[' + boxName + ']');
						var store = gridPanel.getStore();
						store.url = __ctxPath + '/communicate/listOutMail_.do';
						store.baseParams = {
							folderId : folderId,
							setId : setId,
							isFetch : 'Y'
						};
						store.reload({
									params : {
										start : 0,
										limit : 25
									}
								});
					},
					failure : function(response, opts) {
						Ext.MessageBox.updateText('邮件收取出错!!!...');
						Ext.MessageBox.hide();
					}
				});
	},
	defaultSendMail : function() {
		this.sendMail(this, false);
	},
	// 发邮件
	sendMail : function(flage) {
		var tab = Ext.getCmp('centerTabPanel');
		var outMailForm = Ext.getCmp('OutMailForm');
		if (outMailForm != null) {
			tab.remove(outMailForm);
		}
		if (flage) {
			outMailForm = new OutMailForm({
						setId : this.setId
					});
		} else
			outMailForm = new OutMailForm();
		tab.add(outMailForm);
		tab.activate(outMailForm);
	},
	// 树单击事件
	nodeClick : function(node) {
		var me=this;
		nodeClick();
		setHTInterval(nodeClick,1000*60*5);
		function nodeClick(){
			me.selectNode = node;
			var centerPanel = me.centerPanel;
			var outMailCenterView = me.outMailCenterView;
			var showoutMailDetail = Ext.getCmp('ShowoutMailDetail');
			if (showoutMailDetail != null) {
				outMailCenterView.remove('ShowoutMailDetail');
				centerPanel.show();
				outMailCenterView.doLayout();
			}
			me.folderType = node.attributes.folderType;
			me.folderId = node.id.split(".")[1];
			me.setId = node.id.split(".")[0];
			var centerPanel = me.centerPanel;
			var accountName = node.attributes.accountName;
			if (me.folderId == 0) {
				centerPanel.setTitle(accountName + '[收信箱]');
			} else {
				centerPanel.setTitle(accountName + '[' + node.text + ']');
			}
			var store = me.gridPanel.getStore();
			store.url = __ctxPath + '/communicate/listOutMail.do';
			store.baseParams = {
				'folderId' : me.folderId,
				'setId' : me.setId
			};
			me.gridPanel.getBottomToolbar().moveFirst();
			store.reload();
		}
	},// end of node click
	// 删除邮件
	delMail : function(record) {
		var folderId = record.data.folderId;
		var msg = "您确认要删除所选的记录吗？";
		if (this.folderType == 4) {
			msg = "您确认要永久删除所选的记录吗？";
		}
		$postDel({
					msg : msg,
					url : __ctxPath
							+ '/communicate/multiDelOutMail_.do?folderId='
							+ folderId + '&setId=' + record.data.setId,
					ids : record.data.mailId,
					grid : this.gridPanel,
					callback : function() {
						var outMailCenterView = Ext.getCmp("outMailCenterView");
						var centerPanel = Ext.getCmp("outMailCenterPanel");
						outMailCenterView.remove('ShowoutMailDetail');
						Ext.getCmp('outMailGrid').getStore().reload({
									params : {
										start : 0,
										limit : 25
									}
								});
						centerPanel.show();
						outMailCenterView.doLayout();
					}
				});
	},
	// 批量删除邮件
	multiDelMail : function() {
		var msg = "您确认要删除所选的记录吗？";
		if (this.folderType == 4) {
			msg = "您确认要永久删除所选的记录吗？";
		}
		$delGridRs({
					url : __ctxPath + '/communicate/multiDelOutMail_.do?setId='
							+ this.setId,
					msg : msg,
					grid : this.gridPanel,
					idName : 'mailId'
				});
	},
	// 显示邮件,或载入草稿
	edit : function(record) {
		var mailId = record.data.mailId;
		var mailDate = record.data.mailDate;
		var folderId = record.data.folderId;
		var setId = record.data.setId;
		// var detailToolbar = new this.centerViewToolbar(mailId, folderId,
		// rowIndex);
		if (this.folderType == 3) {
			var tab = Ext.getCmp('centerTabPanel');
			var outMailForm = Ext.getCmp('OutMailForm');
			if (outMailForm != null) {
				tab.remove(outMailForm);
			}
			outMailForm = new OutMailForm({
						mailId : mailId,
						opt : 'draft'
					});
			tab.add(outMailForm);
			tab.activate(outMailForm);

		} else {
			var outMailCenterView = Ext.getCmp("outMailCenterView");
			var centerPanel = Ext.getCmp("outMailCenterPanel");
			centerPanel.hide();
			var showoutMailDetail = new ShowoutMailDetail({
						mailId : mailId,
						folderId : folderId,
						mailDate : mailDate,
						setId : setId
					});
			outMailCenterView.add(showoutMailDetail);
			outMailCenterView.doLayout();
		}// else
	}
});
// 邮件信息
var ShowoutMailDetail = Ext.extend(Ext.Panel, {
	// 构造方法
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		// 必须先初始化
		this.initUI();
		// 调用父类构造方法
		ShowoutMailDetail.superclass.constructor.call(this, {
					id : 'ShowoutMailDetail',
					layout : 'border',
					title : '[邮件信息]',
					border : false,
					tbar : this.detailToolbar,
					autoLoad : {
						url : __ctxPath + '/communicate/getOutMail_.do?',
						params : {
							mailId : this.mailId
						},
						method : 'Post'
					}
				});
	},
	// 初始化面板
	initUI : function() {
		this.detailToolbar = new Ext.Toolbar({
					height : 30,
					defaultType : 'button',
					bodyStyle : 'text-align:left',
					items : [{
								iconCls : 'btn-mail_back',
								text : '返回',
								scope : this,
								handler : this.back
							}, {
								iconCls : 'btn-mail_reply',
								text : '回复',
								scope : this,
								handler : this.reply
							}, {
								iconCls : 'btn-mail_resend',
								text : '转发',
								scope : this,
								handler : this.forward
							}, {
								iconCls : 'btn-mail_remove',
								text : '删除',
								scope : this,
								handler : this.delMail
							}, {
								iconCls : 'btn-mail_move',
								text : '移至',
								scope : this,
								handler : this.moveTo
							}, '->', {
								iconCls : 'btn-last',
								text : '较旧一封',
								scope : this,
								handler : this.prev
							}, {
								iconCls : 'btn-up',
								text : '较新一封',
								scope : this,
								handler : this.next
							}]

				});

	},
	// 返回
	back : function() {
		var outMailCenterView = Ext.getCmp("outMailCenterView");
		var centerPanel = Ext.getCmp("outMailCenterPanel");
		outMailCenterView.remove('ShowoutMailDetail');
		Ext.getCmp("outMailGrid").getStore().reload();
		centerPanel.show();
		outMailCenterView.doLayout();
	},
	// 回复
	reply : function() {
		var tab = Ext.getCmp('centerTabPanel');
		var outMailForm = Ext.getCmp('OutMailForm');
		if (outMailForm != null) {
			tab.remove(outMailForm);
		}
		outMailForm = new OutMailForm({
					mailId : this.mailId,
					opt : 'reply'
				});
		tab.add(outMailForm);
		tab.activate(outMailForm);
	},
	// 转发
	forward : function() {
		var tab = Ext.getCmp('centerTabPanel');
		var outMailForm = Ext.getCmp('OutMailForm');
		if (outMailForm != null) {
			tab.remove(outMailForm);
		}
		outMailForm = new OutMailForm({
					mailId : this.mailId,
					opt : 'forward',
					title : "转发邮件"
				});
		tab.add(outMailForm);
		tab.activate(outMailForm);

	},
	// 删除
	delMail : function() {
		OutMailBoxView.prototype.delMail(this.mailId);
		outMailCenterView.remove('ShowoutMailDetail');
		centerPanel.show();
		outMailCenterView.doLayout();
	},
	// 移到
	moveTo : function() {
		new OutMailMove({
					ids : this.mailId,
					setId : this.setId,
					scope : this,
					callback : function() {
						var outMailCenterView = Ext.getCmp("outMailCenterView");
						var centerPanel = Ext.getCmp("outMailCenterPanel");
						outMailCenterView.remove('ShowoutMailDetail');
						Ext.getCmp('outMailGrid').getStore().reload({
									params : {
										start : 0,
										limit : 25
									}
								});
						centerPanel.show();
						outMailCenterView.doLayout();
					}
				}).show();
	},
	// 较旧一封
	prev : function() {
		Ext.Ajax.request({
					url : __ctxPath + '/communicate/prevOutMail_.do?',
					method : 'POST',
					params : {
						// 点击较旧一封按钮,则取比当前邮件时间小的下一条记录
						'Q_mailDate_D_LT' : this.mailDate.toString(),
						'Q_outMailFolder.folderId_L_EQ' : this.folderId
					},
					scope : this,
					success : function(response) {

						var res = Ext.util.JSON.decode(response.responseText);
						if (!Ext.isEmpty(res.data)) {
							this.mailId = res.data.mailId;
							this.setId = res.data.outMailUserSeting.setId;
							this.folderId = res.data.outMailFolder.folderId;
							this.mailDate = res.data.mailDate;
							this.doLayoutMails.call(this);
						} else {
							Ext.ux.Toast.msg('提示信息', '这里已经是最旧的一封了');
						}
					},
					failure : function() {
					}
				});
	},
	// 较新一封
	next : function() {
		Ext.Ajax.request({
					url : __ctxPath + '/communicate/nextOutMail_.do?',
					method : 'POST',
					params : {
						// 点击较旧一封按钮,则取比当前邮件时间小的下一条记录
						'Q_mailDate_D_GT' : this.mailDate.toString(),
						'Q_outMailFolder.folderId_L_EQ' : this.folderId,
						'Q_outMailUserSeting.setId_L_EQ' : this.setId
					},
					scope : this,
					success : function(response) {
						var res = Ext.util.JSON.decode(response.responseText);
						if (!Ext.isEmpty(res.data)) {
							this.mailId = res.data.mailId;
							this.folderId = res.data.outMailFolder.folderId;
							this.setId = res.data.outMailUserSeting.setId;
							this.mailDate = res.data.mailDate;
							this.doLayoutMails.call(this);
						} else {
							Ext.ux.Toast.msg('提示信息', '这里已经是最新的一封了');
						}
					},
					failure : function() {
					}
				});
	},
	doLayoutMails : function() {
		var outMailCenterView = Ext.getCmp("outMailCenterView");
		var showoutMailDetail = new ShowoutMailDetail({
					mailId : this.mailId,
					folderId : this.folderId,
					mailDate : this.mailDate,
					setId : this.setId
				});

		outMailCenterView.remove("ShowoutMailDetail");
		outMailCenterView.add(showoutMailDetail);
		outMailCenterView.doLayout();
	}
});
