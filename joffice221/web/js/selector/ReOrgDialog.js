/**
 * 上下级编辑器
 * 
 * @class ReJobDialog
 * @extends Ext.Window
 *          @example
 * 
 * <pre>
 * new ReOrgDialog({
 * 		reJobId : uids, //已选岗位或人员Ids
 * 		reJobName : unames, //已选岗位或人员Names
 * 		posUserFlag: posUserFlag, //0 按岗位选择，1 按人员选择
 *  	title :'相对岗位选择器', //标题  默认是'相对岗位选择器'，也可以自定义标题
 * 		single: false,   //是否单选 默认是单选上下级
 * 		scope:this,   //作用域
 * 		callback :function(ids,names,posUserFlag){//回调函数,返回岗位或人员ids、岗位或人员名称和按岗位或人员选择标志（0 按岗位选择，1 按人员选择）
 * 
 * 		}	
 * 	}
 * </pre>
 */
ReOrgDialog=Ext.extend(Ext.Window,{
	constructor:function(conf){
		Ext.applyIf(this,conf);
		this.scope=this.scope?this.scope:this;
		//默认为多单选择岗位
		this.single=this.single!=null?this.single:true;
		this.initUI();
		ReOrgDialog.superclass.constructor.call(this,{
			id : "ReOrgDialog",
			title:'上下级部门选择器',
			height:250,
			width:430,
			layout : 'fit',
			maximizable : true,
			items:this.items,
			buttonAlign:'center',
			buttons:[
			{
				text:'确定',
				iconCls:'btn-ok',
				scope:this,
				handler:this.confirm
			}, {
				text : '清空',
				iconCls : 'reset',
				scope : this,
				handler : this.reset
			}, {
				text:'取消',
				iconCls:'btn-cancel',
				scope:this,
				handler:this.close
			}
			]
		});
	},
	/**
	 * 初始化UI
	 */
	initUI:function(){
		this.demfirFlag=this.levfirFlag=true; //第一次进来标示
		//维度选择下拉
		this.demensionCombo=new Ext.form.ComboBox({
			displayField:'name',
			valueField:'id',
			editable : false,
			mode : 'local',
			fieldLabel:"维度选择", 
			width:'150',
			emptyText:'请选择',
			triggerAction : 'all',
			store:new Ext.data.ArrayStore({
				autoLoad:true,
				url:__ctxPath+'/system/comboDemension.do',
				fields:['id','name'],
				listeners:{
			         scope: this,
			         'load':function(){
			        	 if(!this.demfirFlag) return;
			        	 this.demfirFlag=false;
			        	 if(this.demId!="0"||this.demId!=""){
			        		 this.demensionCombo.setValue(this.demId);		     	 		
			     	 	}
			         }
				}
			}),
			listeners:{
			         scope: this,
			         'select':this.demensionComboSelect
			}
		});
		this.level=new Ext.form.ComboBox({
			displayField:'level',
			valueField:'level',
			editable : false,
			mode : 'local',
			fieldLabel:"级数选择", 
			width:'150',
			emptyText:'请选择',
			triggerAction : 'all',
			store:new Ext.data.ArrayStore({
				autoLoad:true,
				url:__ctxPath+'/system/getLevelOrganization.do',
				fields:['level'],
				baseParams :{'demId':(this.demId&&this.demId!='')?this.demId:1},
				listeners:{
			         scope: this,
			         'load':function(){
			        	 if(!this.levfirFlag) return;
			        	 this.levfirFlag=false;
			        	 if(this.reOrgId!=""){
			        		 if(this.reOrgId==0){
			        			 this.contentPanel.getCmpByName('rdoLv').setValue('0');
			        			 this.level.setValue(0);
			        		 }
			        		 else if(this.reOrgId>0)	{
			        			 this.contentPanel.getCmpByName('rdoLv').setValue('1');
			        			 this.level.setValue(this.reJobId);
			        		 }else if(this.reOrgId<0){
			        			 this.contentPanel.getCmpByName('rdoLv').setValue('1');
			        			 this.level.setValue(this.reJobId*(-1));
			        		 }
			     	 	}
			         }
				}
			}),
			listeners:{
			         scope: this,
			         'select':function(){}
			}
		});
		this.strategySelect=new Ext.form.ComboBox({
			displayField:'strategyName',
			valueField:'strategyId',
			editable : false,
			mode : 'local',
			fieldLabel:"选择策略", 
			width:'150',
			emptyText:'请选择',
			triggerAction : 'all',
			store :[			        
			        ['0','只查找本级的部门负责人'],
			        ['1','如果本级负责人为空，继续往上级查找'],
			        ['2','只查找本级的部门所有人'],
			        ['3','如果本级所有人为空，继续往上级查找']
			   ],	
			   value : 0,
			listeners:{
			         scope: this,
			         'load':function(){
			         	
			         }
			}
		});
	 	this.contentPanel=new Ext.Panel({
			layout : 'form',
			bodyStyle : 'padding:10px',
			border : false,
			defaults : {
				anchor : '96%,96%'
			},
			items:[this.demensionCombo,
				{
					name : 'rdoLv',
	                fieldLabel:"选择上下级",  
	                xtype:'radiogroup',
	                items:[ new Ext.form.Radio({      
	                    name : "controlLv",     
	                    inputValue : "0",      
	                    boxLabel : "同级",
	        			listeners:{    
	        				scope : this,
	        				check : function(checkbox, checked) {
	                        if (checked) {
	                        	//this.level.getStore().removeAll();
	                        	this.level.setValue(0);
	                        	this.level.disabled=true;
	                        }
	                        else{
	                        	this.level.setValue('');
	                        	this.level.disabled=false;
	                        }
	        			}}
	                }), new Ext.form.Radio({      
	                    name : "controlLv",   
	                    inputValue : "1",      
	                    boxLabel : "上级",
	        			listeners:{  
	        				scope : this,
	        				check : function(checkbox, checked) {
	                        if (checked) {
	                        	if(this.level.getStore().data.length>0)
                        			this.level.setValue(1);                   
	                        }
	        			}}
	                }),  new Ext.form.Radio({      
	                    name : "controlLv",    
	                    inputValue : "2",      
	                    boxLabel : "下级",
	        			listeners:{ 
	        				scope : this,
	        				check : function(checkbox, checked) {
	                        if (checked) {	      
	                        	if(this.level.getStore().data.length>0)
	                        			this.level.setValue(1);
	                        }
	        			}}
	                })],
	                getValue:function(){
	                    var v;   
	                    if (this.rendered) {   
	                        this.items.each(function(item){   
	                            if (!item.getValue())    
	                                return true;   
	                            v = item.getRawValue();   
	                            return false;   
	                        });   
	                    }   
	                    else {   
	                        for (var k in this.items) {   
	                            if (this.items[k].checked) {   
	                                v = this.items[k].inputValue;   
	                                break;   
	                            }   
	                        }   
	                    }   
	                    return v;
	                }
				},this.level,this.strategySelect]
	 	});
	 	
		this.mainPanel=new Ext.Panel({
			layout : 'form',
			bodyStyle : 'padding:10px',
			border : false,
			defaults : {
				anchor : '96%,96%'
			},
			items:[this.contentPanel]
		});
	 	
	
//	 	if(this.demId!=0||this.demId!=""){
//	 		this.demensionCombo.selectByValue(this.demId);
//	 		
//	 	}
	 	this.items = [];
		this.items.push(this.mainPanel);
		if(this.reOrgstrategy){
			   this.strategySelect.setValue(this.reOrgstrategy);
		}

	},//end of initUI function
	
	demensionComboSelect:function(combo,record,index){
		this.demId=combo.getValue();
		this.level.getStore().load(
	    {
	    	params :{demId:this.demId}
	    });
	
	},
	/**
	 * 确认选择
	 */
	confirm:function(){
			var rdoLvValue = this.contentPanel.getCmpByName('rdoLv').getValue();
			var lvValue=this.level.getValue();
			var demId=this.demensionCombo.getValue();
			var strategy=this.strategySelect.getValue();
			
			if(rdoLvValue==''|| demId=='' ){
				Ext.MessageBox.alert("提示","请选择完整");
				return;
			}
			var lvText = "同级";
			if(rdoLvValue==0){
				if(lvValue!=0){
					Ext.MessageBox.alert("提示","级数输入错误");
					return;
				}
			}else if(rdoLvValue==1){
				if(lvValue<=0){
					Ext.MessageBox.alert("提示","级数输入错误");
					return;
				}
				lvText = "上 "+lvValue+" 级";
				
			}else if(rdoLvValue==2){
				if(lvValue<=0){
					Ext.MessageBox.alert("提示","级数输入错误");
					return;
				}
				lvText = "下 "+lvValue+" 级";
				lvValue = lvValue*(-1);
			}else{
				Ext.MessageBox.alert("提示","请选择上下级");
				return;
			}
			switch(parseInt(strategy)){
				case 0:
				   lvText+="(策略:只查找本级的部门负责人)";
				 break;
				case 1:
				 lvText+="(策略:如果本级负责人为空，继续往上级查找)";
				 break;
				case 2:
				 lvText+="(策略:只查找本级的部门所有人)";
				 break;
				case 3:
				 lvText+="(策略:如果本级所有人为空，继续往上级查找)";
				 break;
			}
			
			if (this.callback){
				this.callback.call(this.scope,lvValue, lvText, demId,strategy);
			}

		this.close();
	},
	//重置
	reset : function() {
		 this.contentPanel.getCmpByName('rdoLv').setValue('');
		this.level.setValue('');
		this.demensionCombo.setValue('');
	}
});