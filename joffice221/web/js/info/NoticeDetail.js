/**
 * 为了兼容之前的版本暂时这样处理
 * @param {} _id
 * @return {}
 */
var NoticeDetail = function(_id) {
	return new NoticeDetail({
				noticeId : _id
			});
}
/**
 * 新闻详情
 * 
 * @class NewsDetailView
 * @extends Ext.Panel
 */
NoticeDetailView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		NoticeDetailView.superclass.constructor.call(this, {
					id : 'NoticeDetailView',
					title : '公告详情',
					region : 'center',
					iconCls : 'menu-notice',
					tbar : this.topbar,
					autoScroll : true,
					autoWidth : true,
					defaults : {
						width : '80%'
					},
					items : [this.homeNewsPanel]
				});
	},// end of constructor
	// 初始化组件
	initUIComponents : function() {
		// 顶部按钮组
		this.topbar = new Ext.Toolbar({
					items : [{
								iconCls : 'btn-mail_remove',
								text : '关闭',
								xtype : 'button',
								scope : this,
								handler : this.cancel
							}, {
								iconCls : 'btn-up',
								text : '上一条',
								xtype : 'button',
								scope : this,
								handler : this.pre
							}, {
								iconCls : 'btn-last',
								text : '下一条',
								xtype : 'button',
								scope : this,
								handler : this.next
							}]
				});
		// 新闻内容
		this.homeNewsPanel = new Ext.Panel({
					width : 650,
					autoScroll : true,
					style : 'padding-left:10%;padding-top:10px;',
					autoHeight : true,
					border:false,
					autoLoad : {
						url : __ctxPath
								+ '/pages/info/noticedetail.jsp?noticeId='
								+ this.noticeId
					}
				});
	},
	/**
	 * 关闭
	 */
	cancel : function() {
		var centerTabPanel = Ext.getCmp('centerTabPanel');
		var oldDetailPanel = centerTabPanel.getItem('NoticeDetail');
		centerTabPanel.remove(oldDetailPanel);
	},
	/**
	 * 上一条
	 */
	pre : function(){
		var haveNextNewsFlag = document.getElementById('__haveNextNoticeFlag');
		var homeNewsPanel = this.homeNewsPanel;
		if(haveNextNewsFlag !=null && haveNextNewsFlag.value !='endPre'){
			var noticeId = document.getElementById('__curNoticeId').value;
			homeNewsPanel.load({
				url : __ctxPath+ '/pages/info/noticedetail.jsp?opt=_pre&noticeId='+ noticeId
			});
		}else{
			Ext.ux.Toast.msg('提示信息','这里已经是第一条了.');
		}
	},
	/**
	 * 下一条
	 */
	next : function(){
		var haveNextNewsFlag = document.getElementById('__haveNextNoticeFlag');
		var homeNewsPanel = this.homeNewsPanel;
		if(haveNextNewsFlag !=null && haveNextNewsFlag.value !='endNext'){
			var noticeId = document.getElementById('__curNoticeId').value;
			homeNewsPanel.load({
				url : __ctxPath+ '/pages/info/noticedetail.jsp?opt=_next&noticeId='+ noticeId
			});
		}else{
			Ext.ux.Toast.msg('提示信息','这里已经是最后一条了.');
		}
	}

});
