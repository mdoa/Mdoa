
/**
 * 查看流程图
 * by cjj
 */
Ext.define('mobile.myTask.JbpmImage', {
    extend: 'Ext.Panel',
    xtype:'jbpmImage',
    name:'jbpmImage',

    constructor:function(config){

    	config = config || {};
    	
    	var taskId=config.taskId;
//    		,runId='';
//		switch(config.type){
//			case 1:
//				taskId=config.id;
//				break;
//			case 2:
//				runId=config.id;
//				break;
//		}

		Ext.apply(config,{
			title: '查看流程图',
			hidden: config.hidden,
//			html:'<img src="'+__ctxPath+ '/jbpmImage?taskId='+taskId+ '&rand=' + Math.random()+ '"/>'
			html:'<iframe '+
			'frameborder="0" width="100%" height="100%"'+
			'src="'+__ctxPath+ '/jbpmImage?taskId='+taskId+ '&rand=' + Math.random()+ 
			'"></iframe>'
		});
		
		this.callParent([config]);
    }

});
    