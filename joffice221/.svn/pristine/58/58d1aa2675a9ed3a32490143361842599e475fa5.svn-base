/**
 * folderType=1\2\3\4
 * 显示个人收件箱、发件箱、草稿箱、垃圾箱 
 */
function MailBoxFolder(conf){
	var folderType=conf.folderType;
	var tabs = Ext.getCmp('centerTabPanel');
	var mailView = tabs.getItem("PersonalMailBoxView");
	if(mailView==null){
		mailView=new PersonalMailBoxView();
	}else{
		tabs.activate(mailView);
	}
	var title="";
	switch(folderType){
		case 1:
			title="收件箱";
			break;
		case 2:
			title="发件箱";
			break;
		case 3:
			title="草稿箱";
			break;
		case 4:
			title="垃圾箱";
			break;
		default:
			break;
	}
	
	mailView.nodeClickHanlder.call(mailView,folderType,title);
	return mailView;
	
}