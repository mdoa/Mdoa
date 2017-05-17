
/**
 * 手机登录
 * by cjj
 */

Ext.define('mobile.Login', {
    extend: 'Ext.form.Panel',
    
    name: 'login',
    
    constructor: function (config) {
    	
    	config = config || {};
    	var user = localStorage.getItem("userName")!=null?localStorage.getItem("userName"):'';
    	Ext.apply(config,{
    		items : [
    			{
	        	    docked: 'top',
	        	    title: 'joffice移动管理系统',
	        	    xtype: 'titlebar',
	        	    items:[{
	        	    	align : 'right',
			            xtype: 'button',
			            name: 'submit',
			            text:'设置',
			            cls : 'buttonCls',
			            handler:this.setConfig
	        	    }]
	        	},
    			{
	        		docked: 'top',
	    			xtype: 'fieldset',
	    			defaults:{
	    				 labelWidth:document.body.clientWidth/3,
	    				 clearIcon : false
	    			},
	    			items:[
				        {
				            xtype: 'textfield',
				            name: 'username',
				            label: '账号',
				            value:user
				        },
				        {
				            xtype: 'passwordfield',
				            name: 'password',
				            label: '密码'
				        },
				        {
				            xtype: 'togglefield',
				            name: 'remenberUser',
				            label: '记住账号',
				            value:user!=null&&user!=''?1:0
				        }
			        ]
		        },
		        {
		        	docked: 'top',
		            xtype: 'button',
		            align : 'center',
		            name: 'submit',
		            text:'登录',
		            cls : 'buttonCls',
		            handler:this.formSubmit
		        }
	        ]
    	});
    	
    	this.callParent([config]);
    },
    
    formSubmit:function(){
		var loginForm = this.up('formpanel');
		if (loginForm.validate(loginForm.items)) {
			if (loginForm.getCmpByName('remenberUser').getValue() == 1) {
				localStorage.setItem("userName",loginForm.getCmpByName('username').getValue());
//				Ext.util.Cookies.set("password", loginForm.getCmpByName('password').getValue());
			}

			loginForm.submit({
			    url: __ctxPath+'/mobile.do',
		        params: {
		            username: loginForm.getCmpByName('username').getValue(),
		            password: loginForm.getCmpByName('password').getValue()
		        },
		        method: 'POST',
		        success: function(form,action,response) {
		        	var obj = Ext.util.JSON.decode(response);
		        	if(obj.success){
		        		mobileView.removeAt(0);
		        		mobileLogin.destroy();
		        		mobileNavi = Ext.create('mobile.View',{username:obj.username,userId:obj.userId});
		        		mobileView.add(mobileNavi);
		        	}else{}
		        },
		        failure: function(form,action,response){
					var obj = Ext.util.JSON.decode(response);
					Ext.Msg.alert('', obj.msg);
		        }
			});
		}
	},
    
    setConfig:function(){
    	var setDomain = Ext.create('mobile.SetDomain',{domVal:__ctxPath});
		mobileView.removeAt(0);
		this.destroy();
    	mobileView.add(setDomain);
    }

});

