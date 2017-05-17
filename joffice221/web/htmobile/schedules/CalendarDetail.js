Ext.define('mobile.schedules.CalendarDetail', {
    extend: 'Ext.form.Panel',
    name: 'CalendarDetail',

    constructor: function (config) {
    	config = config || {};
    	Ext.apply(config,{
		    fullscreen: true,
		    scrollable:{
		    	direction: 'vertical',
		    },
		    items: [
		            {
		                xtype: 'fieldset',
		                defaults:{
		                	xtype: 'textfield',
		                	readOnly: true
		                },
		                items: [
		                    {
		                        label: '执行人',
		                        value: config.event.get("fullname")
		                    },
		                    {
		                        label: '分配人',
		                        value: config.event.get("assignerName")
		                    },
		                    {
		                    	xtype: 'textareafield',
		                        label: '内容',
		                        maxRows: config.content.length/8,
		                        value: config.content.replaceAll("<br/>","\n",false).replaceAll("<br>","\n",false).replaceAll("&nbsp;"," ",false)
		                    },
		                    {
		                        label: '紧急程度',
		                        value: config.event.get('urgent')=='0'?'一般':(config.event.get('urgent')=='1'?'重要':'紧急'),
		                        required: true,
		                        requiredCls: 'myReq'
		                    },
		                    {
		                        label: '任务类型',
		                        value: config.event.get('taskType')=='1'?'限期任务':'非限期任务'
		                    },
		                    {
		                        label: '开始时间',
		                        value: (config.event.get("startTime")).format('yyyy-MM-dd hh:mm:ss')
		                    },
		                    {
		                        label: '结束时间',
		                        value: (config.event.get("endTime")).format('yyyy-MM-dd hh:mm:ss')
		                    },
		                    {
		                        label: '状态',
		                        value: config.event.get('status')=='0'?'未完成':'完成',
		                        required: true,
		                        requiredCls: 'myReq'
		                    }
		                ]
		            },
		            {
		                xtype: 'titlebar',
		                docked: 'top',
		                title: config.event.get("summary"),
		                listeners: {
		                	initialize:function(obj,b){
		                		if(config.event.get('status')=='0') {
		                			var me = this;
		                			var button = Ext.create('Ext.Button', {
		                	    		align: 'right',
		                	        	text: '标记完成',
		                	        	handler : function() {
		                	        		Ext.Ajax.request({
		                	        			url : __ctxPath + '/htmobile/updateCalendarPlan.do?planId=' + config.event.get('planId'),
		                	        			success : function(response) {
		                	        				var result = Ext.util.JSON.decode(response.responseText);
		                	        				if(result.success) {
		                	        					var statusField = Ext.ComponentQuery.query('textfield')[7];
		                	        					statusField.setValue("完成");
		                	        					button.setHidden(true);
		                	        				}
		                	        			}
		                	        		});
		                	        	}
		                	    	});
		                			me.add(button);
		                		}
		                	}
		                }
		            }
		        ]
    	});

    	this.callParent([config]);
    	
    }
});