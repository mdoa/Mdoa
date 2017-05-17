/**
 * 手机登录初始化
 * by cjj
 */

Ext.Loader.setConfig({
    enabled: true,
	paths: {  
		'mobile': 'htmobile'  
	} 
});

Ext.application({
	
    name: 'mobileLogin',

    launch: function() {
		
    	clientWidth = document.body.clientWidth;
    	mobileView = Ext.Viewport;
        mobileLogin = Ext.create('mobile.Login', {fullscreen: true});

    }

});