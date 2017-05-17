/**
 * 我的日程
 * 
 */

Ext.Loader.setConfig({
    enabled: true,
	paths: {  
		'Ext.ux': 'htmobile/schedules/Ext.ux'  
	} 
});

    Ext.define('mobile.schedules.MySchedule', {
	   extend: 'Ext.Panel',
	   name : 'mySchedule',
	   constructor: function (config) {
			config = config || {};
		   Ext.define("Event", {
				extend: "Ext.data.Model",
				config: {
					fields: [
					   { name: 'content', type: 'string' }, 
					   { name: 'summary', type: 'string' },
					   { name: 'startTime', type: 'date', dateFormat: 'c' },
					   { name: 'endTime', type: 'date', dateFormat: 'c' },
					   { name: 'css', type: 'string' },
					   { name: 'taskType', type: 'string' },
					   { name: 'status', type: 'string' },
					   { name: 'assignerName', type: 'string' },
					   { name: 'urgent', type: 'string' },
					   { name: 'fullname', type: 'string' },
					   { name: 'planId', type: 'string'}
			        ]
				}
			});
			var eventStore = Ext.create("Ext.data.Store", {
			    model: 'Event',
			    proxy:{
			    	type:'ajax',
			    	url:__ctxPath+'/htmobile/listCalendarPlan.do',
			    	reader:{
			    		type: "json",
			            rootProperty: "result"
			    	}
			    },
			    autoLoad:true
			});
			
	    	var calendar = Ext.create('Ext.ux.TouchCalendar', {
                viewMode: 'month',

                value: new Date(),
                enableEventBars: {
                    eventHeight: 'auto',
                    eventBarTpl: '<div>{summary}</div>'
                },
                viewConfig: {
                    weekStart: 0,
                    eventStore: eventStore
                }
            });
	    	
	    	calendar.on('eventtap', function(event){
	    		content = event.get("content").substring(1,5)==="span"?event.get("content").
                		substring(93, event.get("content").length-7):event.get("content");
	    		mobileNavi.push(
	    				Ext.create('mobile.schedules.CalendarDetail', {
	    					event: event,
	    					content: content
	    				})
	    		);
            });
            
	    	Ext.apply(config,{
            	fullscreen: true,
                layout: 'fit',
                items: [calendar, {
                    xtype: 'toolbar',
                    docked: 'top',
                    items: [{
                        xtype: 'button',
                        text: '月程',
                        handler: function(){
                            calendar.setViewMode('month');
                        }
                    }, {
                        xtype: 'button',
                        text: '周程',
                        handler: function(){
                            calendar.setViewMode('week');
                        }
                    }, {
                        xtype: 'button',
                        text: '日程',
                        handler: function(){
                            calendar.setViewMode('day');
                        }
                    }]
                }]
	    	});
	    	
	    	this.callParent([config]);   	
	    }
    });
    
