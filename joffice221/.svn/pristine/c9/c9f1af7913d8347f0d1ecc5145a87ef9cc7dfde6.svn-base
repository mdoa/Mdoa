
/**
 * 手机功能菜单
 * by cjj
 */

Ext.define('mobile.Main', {
    extend: 'Ext.Panel',
    
    name:'main',
    id:'main',
    
    constructor:function(config){
		
		var username = config.username;
		var userId=config.userId;
		config = config || {};
		
		var selfItem = [];

		var rowPanel = null;
		
		var menuSize = menus.length;
		if(menus.length!=0 && menus.length%4!=0){
			while(menuSize%4!=0){
				menuSize++;
			}
		}

		for(var idx=0;idx<menuSize;idx++){
			
			if(idx==0||idx%4==0){
				rowPanel = Ext.create('Ext.Panel', {
					layout: {
						type: 'hbox',
						align: 'middle'
					}
				});
			}
			
			var itemPanel = Ext.create('Ext.Panel', {
				layout: {
					type: 'vbox',
					align: 'middle'
				},
				style: {
					'padding-top':'15px',
					'padding-bottom':'15px'
				},
				flex:1,
		    	height:100
			});

			if(idx<menus.length)
			{
				var item = menus[idx];
	
				itemPanel.add({
					xtype:'button',
					id:item.id,
					name:item.view,
					title:item.title,
					width:50,
					height:50,
					cls:item.cls,
					notice:item.notice,
					mask:item.mask,
					listeners:{
						tap:function(){
							mobileNavi.push(
								Ext.create(this.config.name,{
									username:username,
									userId:userId,
									title:this.config.title
								})
							);
						},
						initialize:function(obj,b){
							var me = this;
							var notice = me.config.notice;
							if(typeof notice != 'undefined'){
								var formp = Ext.create('Ext.form.Panel');
								submitFormp();
								window.setInterval(submitFormp,1000*60*5);
								function submitFormp(){
									formp.submit({
									    url: __ctxPath+notice+"?autoload=true",
									    method: 'POST',
									    autoload:true,
									    success: function(form,action,response){
									        var result = Ext.util.JSON.decode(response);
									        me.setBadgeText(result.totalCounts);
									    }
									});
								}
								
							}
						}
					}
				});
				
				itemPanel.add({
					xtype:'label',
					style: {
					    'text-align': 'center',
					    'font-size' : '9pt'
					},
					html:item.title
				});
			}

			rowPanel.add(itemPanel);
			
			if(idx==0||idx%4==0){
				selfItem.push(rowPanel);
			}
		}
		
		selfItem.push(Ext.create('mobile.UserInfo',{username:username}));

		Ext.apply(config,{
			title:'功能菜单',
			name:'menu',
			items:selfItem,
			scrollable:false
		});
		
		this.callParent([config]);
		
    }

});