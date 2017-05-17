/**
 * 手机待办
 * by cjj
 * 
 * var data = {
 *    "tasks" : [
 *    	{
            "taskId": "1",
            "taskName": "task1"
        },
        {
            "taskId": "2",
            "taskName": "task2"
        },
        {
            "taskId": "3",
            "taskName": "task3"
        }
 *    ] 
 * }
 * 
 */

Ext.define('mobile.myTask.MyTask', {
    extend: 'mobile.List',
    
    name: 'myTask',

    constructor: function (config) {
		
    	config = config || {};
    	
    	Ext.apply(config,{
    		title:config.title,
    		fields:[
    			{name: 'taskId',type: 'string'},
				{name: 'taskName',  type: 'string'},
				{name: 'activityName',  type: 'string'},
				{name: 'defId',  type: 'string'}
    		],
    		url:__ctxPath+'/htmobile/myTask.do',
    		root:'result',
		    searchCol:'taskName',
		    itemTpl: "{taskName}",
		    grouped: false,
		    totalProperty: 'totalCounts',
		    pullRefresh: true,
		    listPaging: true,
		    listeners: {
    			itemsingletap:this.itemsingletap
    		}
    	});

    	this.callParent([config]);

    },

	itemsingletap:function(obj, index, target, record){
		var taskId = record.get('taskId');
		var activityName = record.get('activityName');
		var defId = record.get('defId');
		var formpanel = Ext.create('Ext.form.Panel');
		var taskName = record.get('taskName');
		formpanel.submit({
		    url: __ctxPath+'/htmobile/getTask.do',
		    params: {
				taskId:taskId,
				activityName:activityName,
				defId:defId
		    },
		    method: 'POST',
		    success: function(form,action,response){
		        var result = Ext.util.JSON.decode(response);
		    	mobileNavi.push(
		        	Ext.create('mobile.myTask.MyTaskForm',{
		        		mainform:result.mainform,
		        		subform:result.subform,
		        		taskId:taskId,
		        		defId:defId,
		        		preTaskName:result.preTaskName,
		        		isSignTask:result.isSignTask,
		        		trans:result.trans,
		        		taskName:taskName,
		        		activityName:activityName,
		        		callback:function(){
		        			obj.store.load();
		        		}
		        	})
		    	);
		    },
		    failure: function(form,action,response){
				var obj = Ext.util.JSON.decode(response);
				Ext.Msg.alert('', obj.msg);
	        }
		});
    }

});
