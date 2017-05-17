/**
 * 我的邮箱-包括收信箱、发信箱和草稿箱，垃圾箱
 * @author zqg
 * @class PersonalMailBoxView
 * @extends Ext.Panel
 */
Ext.ns('PersonalMailBoxView');
PersonalMailBoxView=Ext.extend(Ext.Panel,{
	//构造方法
	constructor:function(conf){
		Ext.applyIf(this,conf);
		//必须先初始化
		this.initUI();
		//调用父类构造方法
		PersonalMailBoxView.superclass.constructor.call(this,{
				title : '我的邮箱',
				iconCls : 'menu-mail_box',
				layout : 'border',
				id : 'PersonalMailBoxView',
				items : [this.leftPanel,this.MailCenterView]
		});
	},
	//初始化面板
	initUI:function(){
		//目录树面板
		this.treePanel=new htsoft.ux.TreePanelEditor({
			autoScroll : true,
			border:false,
			collapsible : false,
			scope:this,
			url: __ctxPath + '/communicate/listMailFolder.do',
			onclick:this.nodeClick
		});
		this.treePanel.on('contextmenu', this.contextmenu, this);

		//左部菜单面板
		this.leftPanel=new Ext.Panel({
			layout:'fit',
			collapsible : true,
			region : 'west',
			title : '我的邮箱目录',
			width : 180,
			autoScroll : true,
			split : true,
			tbar:[{
					text : '收邮件',
					iconCls : 'btn-mail_receive',
					scope:this,
					handler : this.receiveMail
				}, {
					text : '发邮件',
					iconCls : 'btn-mail_send',
					scope:this,
					handler : this.sendMail
				}, {
					xtype : 'button',
					iconCls : 'btn-refresh',
					text : '刷新',
					scope:this,
					handler : function() {
						this.treePanel.root.reload();
					}
				}
			],
			items:[this.treePanel]
		});
		//查询面板
		this.searchPanel = new HT.SearchPanel({
			layout : 'form',
			region : 'north',
			colNums : 6,
			keys : [{
				key : Ext.EventObject.ENTER,
				fn : this.search,
				scope : this
			},{
				key : Ext.EventObject.ESC,
				fn : this.reset,
				scope : this
			}],
			labelWidth : 120,
			items : [{	
						fieldLabel : '查询条件：邮箱标题',
						xtype : 'textfield',
						name : 'Q_mail.subject_S_LK',
						width : 125,
						maxLength : 125
					},{	
						fieldLabel : '发件人',
						xtype : 'textfield',
						name : 'Q_mail.sender_S_LK',
						labelWidth :50,
						width : 125,
						maxLength : 125
					},{	
						fieldLabel : '收件人',
						xtype : 'textfield',
						name : 'Q_mail.recipientNames_S_LK',
						width : 125,
						labelWidth : 50,
						maxLength : 125
					},{
						xtype : 'button',
						text : '查询',
						style : 'padding-left:5px;',
						iconCls : 'search',
						handler : this.search,
						scope : this
					},{
						xtype : 'button',
						text : '重置',
						style : 'padding-left:5px;',
						iconCls : 'btn-reset',
						handler : this.reset,
						scope : this
					}]
		});
		//顶部菜单面板
		this.topbar = new Ext.Toolbar({
			height : 30,
			bodyStyle : 'text-align:left',
			items : [{
						iconCls : 'btn-mail_remove',
						text : '删除',
						xtype : 'button',
						scope : this,
						handler : this.multiDelMail
					},{
						iconCls : 'btn-mail_move',
						text : '移至',
						xtype : 'button',
						scope : this,
						handler : this.moveMails
					},{
					iconCls : 'btn-mail_refresh',
					text : '刷新',
					scope : this,
					handler : function() {
						this.gridPanel.getStore().reload();
					}
				}]
		});
		//邮件信息列表
		this.gridPanel = new HT.GridPanel({
			id : 'MailGrid',
			region : 'center',
			tbar : this.topbar,
			trackMouseOver : true,
			disableSelection : false,
			loadMask : true,
			stripeRows : true,
			// 使用RowActions
			rowActions : true,
			url : __ctxPath + '/communicate/listMail.do',
			fields : ['boxId', 'folderId','mailId', 'delFlag', 'readFlag',
					'replyFlag','mail.importantFlag', 
					{name:'mailStatus',mapping:'mail.mailStatus'},
					'sendTime', {name:'fileIds',mapping:'mail.fileIds'},
					'mail.subject','mail.recipientNames',
					'mail.content', 'mail.sender'],
			columns : [{
						header : 'mailId',
						dataIndex : 'mailId',
						hidden : true
					  },{
						header : 'boxId',
						dataIndex : 'boxId',
						hidden : true
					  },{
						header : 'folderId',
						dataIndex : 'folderId',
						hidden : true
					  },{
							header : '优先级',
							dataIndex : 'readFlag',
							width:45,
							renderer: function(value) {
								switch (value) {
									case 2 :
										return '<img title="重要" src="' + __ctxPath + '/images/flag/mail/important.png"/>';
									case 3 :
										return '<img title="非常重要" src="' + __ctxPath + '/images/flag/mail/veryImportant.png"/>';
									default :
										return  '<img title="一般" src="' + __ctxPath + '/images/flag/mail/common.png"/>';
								}
							}
					  },{
    					header : '阅读',
							dataIndex : 'readFlag',
							width:45,
							renderer:function(value, metadata, record, rowIndex,colIndex){
								if (value == 1) {
									if (record.data.mailStatus==1)
											return '<img title="已读" src="' + __ctxPath + '/images/flag/mail/mail_open.png"/>';
									else
											return '<img title="草稿" src="' + __ctxPath + '/images/flag/mail/mail_draft.png"/>';

								}else {
									if (record.data.mailStatus==1)
										return '<img title="未读" src="' + __ctxPath + '/images/flag/mail/mail.png"/>';
									else
										return '<img title="草稿" src="' + __ctxPath + '/images/flag/mail/mail_draft.png"/>';
								}
							}
					  },{
							header : '回复',
							dataIndex : 'replyFlag',
							width:45,
							renderer:function(value,record){
								switch (value){
									case 1 :
										return '<img title="已回复" src="' + __ctxPath + '/images/flag/mail/replyed.png" style="background: white;"/>';
									default :
										return '';
								}
							}
					  }, {
							header : '邮件主题',
							dataIndex : 'mail.subject',
							sortable : false,
							width : 150
					  }, {
							header : '发件人',
							dataIndex : 'mail.sender',
							width : 80
					  }, {
							header : '收件人',
							dataIndex : 'mail.recipientNames',
							width : 80,
							renderer : function(value) {
								if (value != '') return value;
							    else  return '(收信人未写)';
							}
					  }, {
							header : '发信时间',
							width : 80,
							dataIndex : 'sendTime',
							renderer : function(value) {
								return value.substring(0, 16);
							}
					  },new Ext.ux.grid.RowActions({
							header : '管理',
							width : 120,
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
		//gridPanel添加行双击事件
		this.gridPanel.addListener({scope : this,'rowdblclick': this.rowClick});
		//中部菜单面板
		this.centerPanel = new Ext.Panel({
			id:'MailCenterPanel',
			region : 'center',
			autoScroll : true,
			layout : 'border',
			title : '收件箱',
			items : [this.searchPanel,this.gridPanel]
		});
		this.MailCenterView = new Ext.Panel({
			id : 'MailCenterView',
			region : 'center',
			autoScroll : true,
			layout : 'fit',
			items : [this.centerPanel]
		});
	},//end of initUI
	//查询
	search : function() {
			$search({
				searchPanel :this.searchPanel,
				gridPanel : this.gridPanel
			});
	},
	// 重置查询表单
	reset : function() {
		this.searchPanel.getForm().reset();
	},
	// 行的Action
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.delMail.call(this, record.data.folderId, record.data.boxId);
				break;
			case 'btn-mail_edit' :
				this.edit.call(this, record);
				break;
			default :
				break;
		}
	},
	//行双击事件
	rowClick : function(grid, rowIndex, e) {
			var record = grid.getStore().getAt(rowIndex);
			this.edit.call(this, record);
	},
	//移动邮件
	moveMails : function() {
		var selectRecords = this.gridPanel.getSelectionModel()
				.getSelections();
		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要移动的邮件！");
			return;
		}
		var ids = Array();
		for (var i = 0; i < selectRecords.length; i++) {
			ids.push(selectRecords[i].data.boxId);
		}
		new MailMove({
			ids:ids,
			scope : this,
			callback:function(){
				var mailCenterView = Ext.getCmp("MailCenterView");
				var centerPanel=Ext.getCmp("MailCenterPanel");
				mailCenterView.remove('ShowMailDetail');
				Ext.getCmp('MailGrid').getStore().reload({
							params : {
								start : 0,
								limit : 25
							}
						});
				centerPanel.show();
				mailCenterView.doLayout();
			}
		}).show();
	},	
	
	//节点右击事件
	contextmenu : function(node, e) {
			this.treePanel.selectedNode = new Ext.tree.TreeNode({
				id : node.id,
				text : node.text
			});
			this.nodeClick(node);
			// 创建右键菜单
			var treeMenu = new Ext.menu.Menu({
				items : []
			});
			treeMenu.clearMons();
			treeMenu.add({
				text : '新增分类',
				iconCls : 'btn-add',
				handler : this.createNode,
				scope : this
			});
			if(node.id > 4){ // 总分类不能删除，和修改
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
	//新建节点
	createNode:function(){
		var treePanel = this.treePanel;
		var parentId = treePanel.selectedNode.id;
		new MailFolderForm({
			parentId : parentId,
			callback : function(){
				treePanel.root.reload();
			}
		}).show();
	},
	//编辑节点
	editNode:function(){
		var treePanel = this.treePanel;
		var folderId = this.selectNode.id;
		if (folderId > 4) {// 禁止用户对默认文件夹进行修改操作
			new MailFolderForm({
				folderId : folderId,
				callback : function(){
					treePanel.root.reload();
				}
			}).show();
		}
	},
	//删除节点
	deleteNode:function(){
		treePanel = this.treePanel;
		var folderId = this.selectNode.id;
		if(folderId<=4) return;	
		Ext.Msg.confirm('信息确认', '您确认要删除该记录吗？', function(btn) {
				if (btn == 'yes') {
			Ext.Ajax.request({
				url : __ctxPath + '/communicate/countMailFolder.do',
				params : {
					folderId : folderId
				},
				method : 'post',
				scope:this,
				success : function(result, request) {
					var res = Ext.util.JSON.decode(result.responseText);
					if (res.count == 0) {
						Ext.Ajax.request({
									url : __ctxPath + '/communicate/removeMailFolder.do',
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
					}// if count == 0
					// 文件夹中存在邮件
					else {
						Ext.Msg.confirm('警告信息', '该文件夹及其子目录下还有' + res.count + '封邮件,确定要删除吗?', function(btn) {
									if (btn == 'yes') {
										Ext.Ajax.request({
											url : __ctxPath + '/communicate/removeMailFolder.do',
											params : {
												folderId : folderId,
												count : res.count
											},
											method : 'post',
											scope:this,
											success : function(result, request) {
												Ext.ux.Toast.msg('操作信息','成功删除目录！');
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
								},this);

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
				}});
	},
	//收邮件
	receiveMail:function(){
			this.centerPanel.setTitle('[收件箱]');
			var store = this.gridPanel.getStore();
			store.url = __ctxPath + '/communicate/listMail.do';
			store.baseParams = {
				folderId : 1
			};
			store.reload({
						params : {
							start : 0,
							limit : 25
						}
			});
	},
	//发邮件
	sendMail:function(){
			var tab = Ext.getCmp('centerTabPanel');
			var mailForm = Ext.getCmp('MailForm');
			if (mailForm == null) {
				mailForm = new MailForm('','','');
				tab.add(mailForm);
				tab.activate(mailForm);
			} else {
				tab.remove(mailForm);
				mailForm = new MailForm('','','');
				tab.add(mailForm);
				tab.activate(mailForm);
			}
	},
	//树单击事件
	nodeClick:function(node){
		var me=this;
		nodeClick();
		setHTInterval(nodeClick,1000*60*5);
		function nodeClick(){
			me.selectNode = node;
			var centerPanel = me.centerPanel;
			var mailCenterView = me.MailCenterView;
			var showMailDetail = Ext.getCmp('ShowMailDetail');
			if (showMailDetail != null) {
				mailCenterView.remove('ShowMailDetail');
				centerPanel.show();
				mailCenterView.doLayout();
			}
			var node = me.treePanel.selectedNode;
			var folderId= node.id;
			var centerPanel = me.centerPanel;
			centerPanel.setTitle('[' + node.text + ']');
	 		var store=me.gridPanel.getStore();
	 		store.url = __ctxPath + '/communicate/listMail.do';
	 		if(folderId==0) {store.baseParams={'folderId':1};}
	 		else{ store.baseParams={'folderId':folderId}; }
	 	    me.gridPanel.getBottomToolbar().moveFirst();
			store.reload();	
		}
		
	},//end of node click
	//删除邮件
	delMail : function(folderId,boxId) {
		var msg = "您确认要删除所选的记录吗？";
		if(folderId==4){
			msg = "您确认要永久删除所选的记录吗？";
		}
		$postDel({
			url : __ctxPath + '/communicate/multiDelMail.do?folderId='+folderId,
			ids : boxId,
			msg :msg,
			grid : this.gridPanel,
			callback : function(){
				var mailCenterView = Ext.getCmp("MailCenterView");
				var centerPanel=Ext.getCmp("MailCenterPanel");
				mailCenterView.remove('ShowMailDetail');
				Ext.getCmp('MailGrid').getStore().reload({
							params : {
								start : 0,
								limit : 25
							}
						});
				centerPanel.show();
				mailCenterView.doLayout();
			}
		});
	},
	//批量删除邮件
	multiDelMail : function(){
		var msg = "您确认要删除所选的记录吗？";
		if(this.selectNode){
			if(this.selectNode.id==4){
				msg = "您确认要永久删除所选的记录吗？";
			}
		}
		$delGridRs({
			url : __ctxPath + '/communicate/multiDelMail.do',
			msg :msg,
			grid : this.gridPanel,
			idName : 'boxId'
		});
	},
	// 显示邮件,或载入草稿
	edit : function(record) {
		var mailId = record.data.mailId;
		var boxId = record.data.boxId;
		var folderId = record.data.folderId;
		var sendTime = record.data.sendTime;
		if (record.data.mailStatus == 0) {//草稿
			var tab = Ext.getCmp('centerTabPanel');
			var mailForm = Ext.getCmp('MailForm');
			if (mailForm != null) {
				tab.remove(mailForm);
			} 
			mailForm = new MailForm({
							mailId:mailId,
							boxId:boxId, 
							opt:'draft'
						});
			tab.add(mailForm);
			tab.activate(mailForm);
		} else {
			var mailCenterView = Ext.getCmp("MailCenterView");
			var centerPanel=Ext.getCmp("MailCenterPanel");
			centerPanel.hide();	
			var showMailDetail = new ShowMailDetail({
				boxId : boxId,
				mailId : mailId,  
				folderId : folderId,
				sendTime : sendTime
			});
			mailCenterView.add(showMailDetail);
			mailCenterView.doLayout();
		}// else
	}
});
//邮件信息
var ShowMailDetail = Ext.extend(Ext.Panel,{
	// 构造方法
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		// 必须先初始化
		this.initUI();
		// 调用父类构造方法
		ShowMailDetail.superclass.constructor.call(this, {
			id : 'ShowMailDetail',
			layout : 'border',
			title : '[邮件信息]',
			border : false,
			tbar : this.detailToolbar,
			autoLoad : {
				url : __ctxPath + '/communicate/getMail.do?',
				params : {
					mailId : this.mailId,
					boxId : this.boxId
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
	//返回
	back : function() {
		var mailCenterView = Ext.getCmp("MailCenterView");
		var centerPanel=Ext.getCmp("MailCenterPanel");
		mailCenterView.remove('ShowMailDetail');
		centerPanel.show();
		mailCenterView.doLayout();
	},
	//回复
	reply : function() {
		var tab = Ext.getCmp('centerTabPanel');
		var mailForm = Ext.getCmp('MailForm');
		if (mailForm != null) {
			tab.remove(mailForm);
		} 
		mailForm = new MailForm({mailId:this.mailId,boxId: this.boxId, opt:'reply'});
		tab.add(mailForm);
		tab.activate(mailForm);
	},
	//转发
	forward : function() {
		var tab = Ext.getCmp('centerTabPanel');
		var mailForm = Ext.getCmp('MailForm');
		if (mailForm != null) {
			tab.remove(mailForm);
		} 
		mailForm = new MailForm({mailId:this.mailId,boxId: this.boxId, opt:'forward'});
		tab.add(mailForm);
		tab.activate(mailForm);
	},
	//删除
	delMail :function() {
		PersonalMailBoxView.prototype.delMail(this.folderId,this.boxId);
		mailCenterView.remove('ShowMailDetail');
		centerPanel.show();
		mailCenterView.doLayout();
	},
	//移到
	moveTo : function() {
		new MailMove({
			ids:this.boxId,
			scope : this,
			callback:function(){
				var mailCenterView = Ext.getCmp("MailCenterView");
				var centerPanel = Ext.getCmp("MailCenterPanel");
				mailCenterView.remove('ShowMailDetail');
				Ext.getCmp('MailGrid').getStore().reload({
					params : {
						start : 0,
						limit : 25
					}
				});
				centerPanel.show();
				mailCenterView.doLayout();
			}
		}).show();
	},
	//较旧一封
	prev : function() {
		Ext.Ajax.request({
					url : __ctxPath+ '/communicate/prevMail.do?',
					method : 'POST',
					params : {
						// 点击较旧一封按钮,则取比当前邮件时间小的下一条记录
						'Q_sendTime_D_LT' : this.sendTime,
						'Q_mailFolder.folderId_L_EQ' : this.folderId
					},
					scope : this,
					success : function(response) {
						
						var res = Ext.util.JSON.decode(response.responseText);
						if (!Ext.isEmpty(res.data)) {
							this.boxId = res.data.boxId;
							this.mailId = res.data.mail.mailId;
							this.folderId =  res.data.mailFolder.folderId;
							this.sendTime = res.data.sendTime;
							this.doLayoutMails.call(this);
						} else {
							Ext.ux.Toast.msg('提示信息', '这里已经是最旧的一封了');
						}
					},
					failure : function() {
					}
		});
	},
	//较新一封
	next : function() {
		Ext.Ajax.request({
					url : __ctxPath+ '/communicate/nextMail.do?',
					method : 'POST',
					params : {
						// 点击较旧一封按钮,则取比当前邮件时间小的下一条记录
						'Q_sendTime_D_GT' : this.sendTime,
						'Q_mailFolder.folderId_L_EQ' : this.folderId
					},
					scope : this,
					success : function(response) {
						var res = Ext.util.JSON.decode(response.responseText);
						if (!Ext.isEmpty(res.data)) {
							this.boxId = res.data.boxId;
							this.mailId = res.data.mail.mailId;
							this.folderId =  res.data.mailFolder.folderId;
							this.sendTime = res.data.sendTime;
							this.doLayoutMails.call(this);
						} else {
							Ext.ux.Toast.msg('提示信息', '这里已经是最旧的一封了');
						}
					},
					failure : function() {
					}
		});
	},
	doLayoutMails : function(){
		var mailCenterView = Ext.getCmp("MailCenterView");
		var showMailDetail = new ShowMailDetail({
			mailId : this.mailId,  
			folderId : this.folderId,
			sendTime : this.sendTime,
			boxId : this.boxId
		});
		
		mailCenterView.remove("ShowMailDetail");
		mailCenterView.add(showMailDetail);
		mailCenterView.doLayout();
	}
});

