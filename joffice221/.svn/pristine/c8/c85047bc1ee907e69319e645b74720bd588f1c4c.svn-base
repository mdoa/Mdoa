Ext.ns('SearchMail');
/**
 * 搜索邮件
 * 
 * @class SearchMail
 * @extends Ext.Panel
 */
var SearchMail = function(_searchContent) {
	return new SearchMailView({_searchContent:_searchContent});
}
var SearchMailView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		SearchMailView.superclass.constructor.call(this, {
					id : 'SearchMailView',
					title : '搜索邮件',
					iconCls : 'menu-mail_box',
					region : 'center',
					layout : 'border',
					items : [this.MailCenterView]
				});
	},
	// 初始化组件
	initUIComponents : function() {
		
		// 初始化搜索条件Panel
		this.searchPanel = new HT.SearchPanel({
					region : 'north',
					layout : 'form',
					colNums : 3,
					labelWidth : 80,
					items : [{
								fieldLabel : '请输入条件',
								xtype : 'textfield',
								name : 'searchContent'
							},{
								xtype : 'button',
								text : '查询',
								iconCls : 'search',
								scope : this,
								handler : this.search
							}, {
								xtype : 'button',
								text : '清空',
								iconCls : 'reset',
								scope : this,
								handler : this.reset
							}]
				});

		var expander = new Ext.ux.grid.RowExpander({
			tpl : new Ext.Template('<div style="padding:5px 5px 5px 62px;"><b>内容摘要:</b> {content}</div>')

		});
		// 邮件列表面板
		this.gridPanel = new HT.GridPanel({
					region : 'center',
					url : __ctxPath + '/communicate/searchMail.do',
					sort : [{
								field : "sendTime",
								direction : "DESC"
							}],
					baseParams : {
						searchContent:this._searchContent
					},
					expander : expander,
					trackMouseOver : true,
					disableSelection : false,
					loadMask : true,
					stripeRows : true,
					fields : [{
								name : 'boxId',
								type : 'int'
							}, {
								name : 'mailId',
								type : 'int',
								mapping : 'mail.mailId'
							}, {
								name : 'delFlag',
								type : 'int'
							},'readFlag', 
							{
								name : 'replyFlag',
								type : 'int'
							},'mail.importantFlag',
							{
								name :'mailStatus',
								mapping : 'mail.mailStatus'
							},'sendTime','mail.fileIds',
							  'mail.subject','mail.recipientNames',
							{
								name :'content',
								mapping : 'mail.content'
							},'mail.sender'],
					columns : [expander, {
								header : 'mailId',
								dataIndex : 'mailId',
								hidden : true
							}, {
								header : '优先级',
								dataIndex : 'mail.importantFlag',
								width : 55,
								renderer : this.importantFlag
							}, {
								header : '阅读状态',
								dataIndex : 'mailStatus',
								width : 40,
								renderer : this.mailStatus
							}, {
								header : '附件',
								dataIndex : 'mail.fileIds',
								width : 40,
								renderer : this.attachment
							}, {
								header : '回复',
								dataIndex : 'replyFlag',
								width : 40,
								renderer : this.reply
							}, {
								header : '邮件主题',
								dataIndex : 'mail.subject',
								width : 150
							}, {
								header : '发件人',
								dataIndex : 'mail.sender',
								width : 80
							}, {
								header : '收件人',
								dataIndex : 'mail.recipientNames',
								width : 80,
								renderer : this.recipientNames
							}, {
								header : '发信时间',
								width : 80,
								dataIndex : 'sendTime',
								renderer : function(value) {
									return value.substring(0, 10);
								}
							}]
				});
		this.gridPanel.addListener({
					scope : this,
					'rowdblclick' : this.rowClick
				});
				
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
//		if(this._searchContent){
//			alert(this.searchPanel.getCmpByName('searchContent').getValue());
//			var _searchContent = this._searchContent 
//			this.searchPanel.getCmpByName('searchContent').setValue(this._searchContent);
//			var form = this.searchPanel.getForm();
//			var gridPanel = this.gridPanel;
//			form.submit({
//				url : __ctxPath + '/communicate/searchMail.do',
//				params : {
//					'searchContent' : _searchContent
//				},
//				success : function(formPanel, action) {
//					var result = Ext.util.JSON.decode(action.response.responseText);
//					gridPanel.getStore().loadData(result);
//				}
//			});
//		}
		
	},
	// 优先级
	importantFlag : function(value, metadata, record, rowIndex, colIndex) {
		var str = '';
		// 优先级标识
		if (value == 2) {
			str += '<img title="重要" src="' + __ctxPath
					+ '/images/flag/mail/important.png"/>'
		} else if (value == 3) {
			str += '<img title="非常重要" src="' + __ctxPath
					+ '/images/flag/mail/veryImportant.png"/>'
		} else {
			str += '<img title="一般" src="' + __ctxPath
					+ '/images/flag/mail/common.png"/>'
		}
		return str;// 考虑所有图标都在这里显示
	},
	// 阅读状态
	mailStatus : function(value, metadata, record, rowIndex, colIndex) {
		var str = '';
		// 阅读标识
		if (value != 0) {
			if (record.data.readFlag == 0) {
				str += '<img title="未读" src="' + __ctxPath
						+ '/images/flag/mail/mail.png"/>'
			} else {
				str += '<img title="已读" src="' + __ctxPath
						+ '/images/flag/mail/mail_open.png"/>'
			}
		} else {// 草稿
			str += '<img title="草稿" src="' + __ctxPath
					+ '/images/flag/mail/mail_draft.png"/>'
		}
		return str;
	},
	// 附件
	attachment : function(value, metadata, record, rowIndex, colIndex) {
		var str = '';
		// 附件标识
		if (value!=null) {
			str += '<img title="附件" src="' + __ctxPath
					+ '/images/flag/attachment.png"/>'
		}
		return str;
	},
	// 回复
	reply : function(value, metadata, record, rowIndex, colIndex) {
		var str = '';
		// 回复标识
		if (value == 1) {
			str += '<img title="已回复" src="'
					+ __ctxPath
					+ '/images/flag/mail/replyed.png" style="background: white;"/>'
		}
		return str;
	},
	// 收件人
	recipientNames : function(value) {
		if (value != '') {
			return value;
		} else {
			return '(收信人未写)';
		}
	},
	// 重置查询表单
	reset : function() {
		this.searchPanel.getForm().reset();
	},
	// 按条件搜索
	search : function() {
		this._searchContent = null;
		$search({
					searchPanel : this.searchPanel,
					gridPanel : this.gridPanel
				});
	},
	// 详情
	detail : function(rec,rowIndex) {
		var mailId = rec.data.mailId;
		var boxId = rec.data.boxId;
		var status = rec.data.mailStatus;
		if (status == 0) {
			var tab = Ext.getCmp('centerTabPanel');
			var mailForm = Ext.getCmp('MailForm');
			if (mailForm == null) {
				mailForm = new MailForm({mailId:mailId, boxId:boxId,opt:'draft'});
				tab.add(mailForm);
				tab.activate(mailForm);
			} else {
				tab.remove(mailForm);
				mailForm = new MailForm({mailId:mailId, boxId:boxId,opt:'draft'});
				tab.add(mailForm);
				tab.activate(mailForm);
			}
		} else {
			var mailCenterView = Ext.getCmp("MailCenterView");
			var centerPanel=Ext.getCmp("MailCenterPanel");
			centerPanel.hide();	
			var showDetail = new ShowMailDetail({
				boxId : boxId,
				mailId : mailId
			});
			mailCenterView.add(showDetail);
			mailCenterView.doLayout();
		}// else
	},
	// GridPanel行点击处理事件
	rowClick : function(grid, rowIndex, e) {
		var rec = grid.getStore().getAt(rowIndex);
		this.detail(rec, rowIndex);
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
						handler : this.resend
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
				alert(this.mailId);
		if (mailForm == null) {
			mailForm = new MailForm({mailId:this.mailId, boxId:this.boxId, opt:'reply'});
			tab.add(mailForm);
			tab.activate(mailForm);
		} else {
			tab.remove(mailForm);
			mailForm = new MailForm({mailId:this.mailId, boxId:this.boxId, opt:'reply'});
			tab.add(mailForm);
			tab.activate(mailForm);
		}
	},
	//转发
	resend : function() {
		var tab = Ext.getCmp('centerTabPanel');
		var mailForm = Ext.getCmp('MailForm');
		if (mailForm == null) {
			mailForm = new MailForm({mailId:this.mailId, boxId:this.boxId,opt:'forward'});
			tab.add(mailForm);
			tab.activate(mailForm);
		} else {
			tab.remove(mailForm);
			mailForm = new MailForm(this.mailId, this.boxId,
					'forward');
			tab.add(mailForm);
			tab.activate(mailForm);
		}
	}
})  
