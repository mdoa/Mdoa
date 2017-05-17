/**
 * 联系人信息
 * @author zqg
 * @class SharedPhoneBookWin
 * @extends Ext.Window
 */
SharedPhoneBookWin=Ext.extend(Ext.Window,{
	//构造方法
	constructor:function(_cfg){
	   Ext.applyIf(this,_cfg);
	   //必须先初始化
	   this.initUI();
	   //调用父类构造方法
	   SharedPhoneBookWin.superclass.constructor.call(this,{
		   id : 'SharedPhoneBookWin',
			title : '联系人信息',
			iconCls:"menu-phonebook-shared",
			width : 500,
			height :540,
			modal : true,
			autoScroll:true,
			layout : 'anchor',
			plain : true,
			border:false,
			bodyStyle : 'padding:5px;',
			buttonAlign : 'center',
			items : this.showPanel,
			buttons : this.buttons
	   });
     },
     //初始化面板
     initUI:function(){
     	//显示联系人详细信息面板
    	 this.showPanel=new Ext.Panel({
    	     modal : true,
    	     autoScroll:true,
    	     autoLoad:{url:__ctxPath+'/communicate/detailPhoneBook.do?phoneId='+this.phoneId}	     
    	});
    	//底部菜单面板
    	 this.buttons=[{
				text : '关闭',
				iconCls:'btn-close',
				scope : this,
				handler : this.cancel
			}];
     },
     //关闭
     cancel:function(){
    	 this.close();
     }
});