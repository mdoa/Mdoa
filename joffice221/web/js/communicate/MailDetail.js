MailDetail=Ext.extend(Ext.Panel,{
	//构造方法
	constructor:function(_cfg){
	    Ext.applyIf(this,_cfg);
	    //必须先初始化组件
	    this.initUI();
	    //调用父类构造方法
	    MailDetail.superclass.constructor.call(this,{
	    	id : 'MailDetail',
	    	title:'我的邮件',
	    	iconCls:'menu-mail',
			border : false,
			tbar : this.toolbar,
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
    //初始化面板
    initUI:function(){
    	var mailId=this.mailId;
    	var boxId=this.boxId;
    	this.toolbar = new Ext.Toolbar({
    		height : 30,
    		defaultType : 'button',
    		bodyStyle : 'text-align:left',
    		items : [{
    					iconCls : 'btn-mail_reply',
    					text : '回复',
    					handler : function() {
    						var tab = Ext.getCmp('centerTabPanel');
    						var mailForm = Ext.getCmp('MailForm');
    						if (mailForm != null) {
    							tab.remove(mailForm);
    						} 
    						mailForm = new MailForm({mailId:mailId,boxId:boxId,opt:'reply'});
    						tab.add(mailForm);
    						tab.activate(mailForm);
    					}
    				}, {
    					iconCls : 'btn-mail_resend',
    					text : '转发',
    					handler : function() {
    						var tab = Ext.getCmp('centerTabPanel');
    						var mailForm = Ext.getCmp('MailForm');
    						if (mailForm != null) {
    							tab.remove(mailForm);
    						} 
    						mailForm = new MailForm({mailId:mailId,boxId:boxId,opt:'forward'});
    						tab.add(mailForm);
    						tab.activate(mailForm);
    					}
    				}]
    	});
    	
    }
});