 /**
 * 邮件人员选择 by wdr
 */
Ext.define('mobile.myEmail.MyaddPeople', {
	extend : 'mobile.List',
	id : 'myaddPeople',
	constructor : function(config) {
		config = config || {};
		isOut=config.isOut;
		this.recipientNames = config.recipientNames;
		this.recipientIDs =config.recipientIDs;
		this.txtRecipient =config.txtRecipient;
		url=__ctxPath +'/htmobile/addPeople.do?isOut=';
		if(isOut){
			url=url+1;
		}else{
			url=url+0;
		}
		Ext.apply(config, {
			title : '选择收件人',
			buttonText: '确定',
			fields : [{
						name : 'fullname',
						type : 'string'
					}, {
						name : !isOut?'userId':'email',
						type : 'string'
					}],
			url : url,
			root : 'result',
			loadingText : '正在加载',
			grouped : true,
			groupedFiled : 'fullname',
			itemTpl : '{fullname}',
			useSelect : true,
			selectWhenIN : true,
			returnFileds:['fullname',!isOut?'userId':'email'],
			doneSuccess : function(values) {
				this.recipientNames.setValue((values.fullname+"").replaceAll(",","\r\n"));
				this.txtRecipient.setHtml((values.fullname+"").replaceAll(",",";<br>"));
				if (isOut) {
							this.recipientIDs.setValue(values.email);
						} else {
							this.recipientIDs.setValue(values.userId);
						}
				
				mobileNavi.pop();

			}
			
		});
		this.callParent([config]);
	}
});
