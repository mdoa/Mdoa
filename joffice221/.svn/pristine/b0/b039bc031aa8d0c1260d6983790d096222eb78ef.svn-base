
/**
 * 手机表单
 * by cjj
 */

Ext.define('mobile.myTask.TableForm', {
	extend: 'Ext.TabPanel',
    
    name: 'tableForm',

    constructor: function (config) {
		
		config = config || {};
		
		this.taskId = config.taskId;
		this.defId = config.defId;
		this.activityName = config.activityName;
		
    	var mainHidden = true;
    	
    	var mainTalbeItems = [];
    		
    	// 主表字段
    	var mainform = config.mainform;

    	if(mainform.length!=0){
    		mainHidden = false;
    		var oldkey = '';
	    	for(var idx=0;idx<mainform.length;idx++){
	    		var field = mainform[idx];
	    		if(oldkey!=field.key){
	    			if(field.key=='preHandler'){
	    				this.preHandler = field.value;
	    			}else if(field.key=='afterHandler'){
	    				this.afterHandler = field.value;
	    			}else{
			    		if(field.xtype=="fckeditor"){
			    			mainTalbeItems.push({
				                xtype: 'fieldset',
				                name: field.key,
				                title: field.label,
				                html: field.value
				    		});
			    		}else{
			    			mainTalbeItems.push({
				    			xtype:'textfield',
				    			name:field.key,
				    			label:field.label,
				    			value:field.value,
				    			readOnly:true
				    		});
			    		}
	    			}
	    			oldkey = field.key;
	    		}
	    	}
    	}

    	// 处理子表字段
    	var subform = config.subform;
    	if(typeof subform!='undefined'){
	    	for(var idx=0;idx<subform.length;idx++)
	    	{
	    		var html ='<br><table class="touchgridpanel">'+
	    			'<caption>'+subform[idx].tableName+'</caption>'+
					'<tr class="x-grid-hd-row">';
	    		var data = subform[idx].data;
	    		if(data.length>0){
	    			for(var idx1=0;idx1<data.length;idx1++){
			    		var row = data[idx1].row;
			    		var content = '';
						for(var idx0=0;idx0<row.length;idx0++){
							var field = row[idx0];
							if(idx1==0){
								html+='<th width="40%" class="x-grid-cell-hd">'+field.label+'</th>';
							}
							if(idx0==0){
								content+='<tr>';
							}
							content+='<td width="40%" class="x-grid-cell">'+field.value+'</td>';
							if(idx0==1){
								if(idx==0){
									html+='<th class="x-grid-cell-hd"></th>';
								}
								var rowStr = Ext.util.JSON.encode(row).replace(new RegExp("\"","gm"),"\'");
								content+='<td class="x-grid-cell"><a style="float:none;" href="javascript:getFormDetail('+rowStr+')">'+
									'<font color="#008800"><u>明细</u></font></a></td>';
								content+='</tr>';
								break;
							}
						}
						html+='</tr>';
						html+=content;
	    			}
					html+='</table><br>';
					
					mainTalbeItems.push({
		    			xtype:'panel',
		    			html:html
		    		});
	    		}
	    	}
    	}
	
    	var toolbar = Ext.create('Ext.Toolbar',{
			docked: 'top'
		});
    	
		toolbar.add({
            xtype: 'button',
            iconCls: 'mytaskNext',
        	iconMask: true,
            text: '执行下一步',
            active:1,
            handler: this.formSubmit
        });
    	
    	// 普通节点
    	this.preTaskName = config.preTaskName;
    	if(this.preTaskName){
    		toolbar.add({
                xtype: 'button',
	            iconCls: 'mytaskBack',
	            iconMask: true,
	            text: '驳回',
	            active:2,
                handler: this.formSubmit
            });
    	}
    	
    	// 审批设置
    	var approveSetting = Ext.create('Ext.form.Panel');
		// 下一任务路径
		var trans = config.trans;
		Ext.define('transModel', {
		    extend: 'Ext.data.Model',
		    config: {
		        fields: [
		            {name: 'destType', type: 'string'},
		            {name: 'destination', type: 'string'},
		            {name: 'name', type: 'string'},
		            {name: 'source', type: 'string'}
		        ]
		    }
		});
		
		approveSetting.add({
			xtype:'label',
			html:"<div style='text-align:center;'><font style='color:blue;'>"+config.taskName+"</font></div><br>"
		});
		
		var transStore = Ext.create("Ext.data.Store", {
		    model: "transModel",
		    data: trans
		});
		approveSetting.add({
			xtype: 'selectfield',
			name: 'dest',
			required: true,
			label: '执行路径',
			store: transStore,
			displayField: 'destination',
			valueField: 'destination'
		});
    	// 会签节点
    	this.isSignTask = config.isSignTask;
    	if(this.isSignTask){
    		var signPanel = Ext.create('Ext.form.FieldSet',{
    			itemId:'signField',
    			title:'会签设置',
    			items:[{
                    xtype: 'radiofield',
                    name : 'signVoteType',
                    value: 1,
                    label: '赞成'
                },{
                    xtype: 'radiofield',
                    name : 'signVoteType',
                    value: 2,
                    label: '拒绝'
                },{
                    xtype: 'radiofield',
                    name : 'signVoteType',
                    value: 0,
                    label: '弃权'
                }]
    		});
    		approveSetting.add(signPanel);
    	}
    	
    	approveSetting.add({
    		xtype:'fieldset',
    		items:[{
    			// 是否短讯提醒
                xtype: 'checkboxfield',
                name : 'sms',
                label: '短讯提醒'
            },{
            	// 是否邮件提醒
                xtype: 'checkboxfield',
                name : 'mail',
                label: '邮件提醒'
            }]
    	});
    	
    	// 审批意见
    	approveSetting.add({
            xtype: 'textareafield',
            label: '审批意见',
            maxRows: 4,
            name: 'comments'
    	});
    	
        Ext.apply(config,{
        	title:'表单明细',
            layoutOnTabChange: true,
            items: [
                toolbar,
	            {
	            	title: '流程表单',
	                items:[{
	    	            xtype: 'fieldset',
	    	            items:mainTalbeItems
	    	        }],
	                scrollable: {
	                	direction: 'vertical',
		                directionLock: true
		            },
	                hidden:mainHidden
	            },
	            {
		            title: '流程图',
	                items: Ext.create('mobile.myTask.JbpmImage',{
		        		taskId:this.taskId
		        	})
	            },
	            {
	            	title: '审批设置',
	            	layout: 'fit',
	                items: approveSetting,
	                scrollable: {
	                	direction: 'vertical',
		                directionLock: true
		            }
	            }
            ]
        });
    
        this.callParent([config]);
	}

});

