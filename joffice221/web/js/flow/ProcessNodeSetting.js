/**
 * 流程节点事件处理
 * @class ProcessNodeSetting
 * @extends Ext.Window
 */
ProcessNodeSetting=Ext.extend(Ext.Window,{
	
	constructor:function(conf){
		
		this.codeArr = new Array();
		
		Ext.applyIf(this,conf);
		this.initUI();
		
		ProcessNodeSetting.superclass.constructor.call(this,{
			title:'流程节点干预设置-' + this.activityName,
			width:670,
			height:480,
			maximizable : true,
			modal : true,
			layout:'border',
			items:[
				this.tabPanel
			],
			buttonAlign:'center',
			buttons:[
			{
				text:'保存',
				iconCls:'btn-save',
				scope:this,
				handler:this.save
			},{
				text:'取消',
				iconCls:'btn-cancel',
				scope:this,
				handler:this.close
			}
			]
			
		});
	},
	initUI:function(){
		this.startPanel=new Ext.Panel({
			title:'节点开始事件代码',
			bodyStyle:'padding:8px',
			layout:'form',
			items:[{
					xtype:'textarea',
					fieldLabel:'事件代码:(BSH脚本，采用JAVA语法）',
					name:'startExeCode',
					anchor : '96%,96%',
					height:350
					
				}
			]
		});
		
		this.endPanel=new Ext.Panel({
			title:'节点结束事件代码',
			layout:'form',
			bodyStyle:'padding:8px',
			items:[{
					xtype:'textarea',
					fieldLabel:'事件代码:(BSH脚本，采用JAVA语法）',
					name:'endExeCode',
					anchor : '96%,96%',
					height:350
				}
			]
		});

		this.tabPanel=new Ext.TabPanel({
			region:'center',
			activeTab : 0,
			items:[
				this.startPanel,
				this.endPanel
			]
		});
		var includeDecision=false;
		//加上分支条件的处理
		/*if(this.nodeType=='decision'){
			this.decisionPanel=new Ext.Panel({
				title:'分支条件设置代码',
				layout:'form',
				bodyStyle:'padding:8px',
				items:[
					{
						xtype:'textarea',
						fieldLabel:'分支条件代码',
						name:'decisionExeCode',
						anchor : '96%,96%',
						height:350
					},{
						xtype:'label',
						text:'通过设置tranTo值来决定分支跳转，若投票分支决定，可以使用decisionType变量（其有两值：refuse,pass）'
					}
				]
			});
			this.tabPanel.add(this.decisionPanel);
			this.tabPanel.doLayout();
			includeDecision=true;
		}*/
		
		// 分支代码生成器
		if(this.nodeType=='decision'){
			this.branchTreePanel=new htsoft.ux.TreePanelEditor({
				width:160,
				region:'west',
				title:'分支树',
				scope : this,
				split:true,
				collapsible: true,
				autoScroll:true,
				onclick:this.branchTreeClick
				
			});
			
			var stringType = new Array(['等于','0'],['不等于','1']);
			var decimalType= new Array(['等于','0'],['不等于','1'],['大于','2'],['小于','3'],['大于等于','4'],['小于等于','5']);
			
			var tmpDefId = this.tempCheck?'0':this.defId;
			var branchUrl = __ctxPath+'/flow/getFieldFormDef.do?defId='+tmpDefId;
			this.branchCodePanel=new Ext.Panel({
				layout:'border',
				region:'center',
				bodyStyle:'padding:5px',
				border:false,
				fieldIndex:0,
				items:[{
					xtype : 'panel',
					layout:'hbox',
					border:false,
					region:'north',
					frame:true,
					height:50,
					style:{
						padding:'5px'
					},
					items : [{
						xtype:'label',
						text:'字 段：',
						style:{marginTop:'8px'}
					},{
						xtype:'combo',
						hiddenName:'fields',
						displayField:'fieldName',
						valueField:'fieldLabel',
						editable : true,
						mode : 'local',
						width:80,
						triggerAction : 'all',
						store:new Ext.data.ArrayStore({
							autoLoad:true,
							url:branchUrl,
							fields:['fieldLabel','fieldName','fieldType'],
							listeners:{
								scope:this,
								'load':function(store){
									var cmp=this.getCmpByName('fields');
									if(cmp.store.getCount()>0){
										cmp.setValue('');
									}
								}
							}
						}),
						listeners:{
							scope:this,
							'select':function(item,cbo,index){
								this.branchCodePanel.fieldIndex = index;
								var symbol = this.getCmpByName('symbol');
								var field = this.getCmpByName('fields');
								var id = field.store.getAt(index).data.fieldName;
								var fieldType = field.store.getAt(index).data.fieldType;
								if(id=='ID'||fieldType=='text'||fieldType=='varchar'){
									symbol.store.removeAll();
									for(var i=0;i<stringType.length;i++){
										var record = new symbol.store.recordType({
											key:stringType[i][0],
											value:stringType[i][1]
										});
										symbol.store.add(record);
									}
								}else{							
									symbol.store.removeAll();
									for(var i=0;i<decimalType.length;i++){
										var record = new symbol.store.recordType({
											key:decimalType[i][0],
											value:decimalType[i][1]
										});
										symbol.store.add(record);
									}
								}
								symbol.setValue(0);
							}
						}
					},{
						xtype:'label',
						text:'比较：',
						style:{marginTop:'8px',marginLeft:'3px'}
					},{
						xtype:'combo',
						hiddenName:'symbol',
						editable : false,
						displayField:'key',
						valueField:'value',
						mode : 'local',
						width:80,
						triggerAction : 'all',
						store:new Ext.data.SimpleStore({
		                    fields : ['key', 'value'],
		                    data : stringType
		                })
					},{
						xtype:'label',
						text:'比较的值：',
						style:{marginTop:'8px',marginLeft:'3px'}
					},{
						xtype:'textfield',
						name:'compareValue',
						width:100
					},{
						xtype:'button',
						name:'btnGen',
						style:{marginLeft:'5px'},
						width:50,
						text:'代码生成',
						scope:this,
						handler:this.generate
					}]
				},{
					xtype: 'panel',
					region:'center',
					layout:'fit',
					border: false,
					style:{
						padding:'8px'
					},
					items:[{
						xtype:'textarea',
						name:'decisionExeCode'
					}]
				},{
					region:'south',
					xtype:'button',
					name:'btnReset',
					text:'重置',
					scope:this,
					handler:this.reset,
					style:{
						padding:'0px,0px,5px,120px'
					}
				}]
			});
			
			Ext.Ajax.request({
				url:__ctxPath+'/flow/getBranchTaskProHandleComp.do',
				params:{
					defId:this.defId,
					activityName:this.activityName
				},
				scope:this,
				success:function(resp,options){
					
					var result=Ext.decode(resp.responseText);
					if(result!=null){
		                var root = new Ext.tree.AsyncTreeNode({
		                     children: result.root
		                });  
						
						this.branchTreePanel.setRootNode(root);
						
						this.initCode = '';
						var nodes = result.root;
						this.nodesLength = result.root.length;
						for(var i=0;i<nodes.length;i++){
							if(i==0){
								this.initCode+='String tranTo=\"'+nodes[0].text+'\";\r\n';
							}
							this.initCode+='// node'+(i+1)+'\r\n';
							if(i>0){
								this.initCode+='else ';
							}
							this.initCode+='if('+nodes[i].id+'=='+nodes[i].id
								+'){\r\n   tranTo=\"'+nodes[i].text+'\";\r\n}\r\n';
						}
//						this.branchCodePanel.getCmpByName('decisionExeCode').setValue(initCode);
					}
					
				}
			});
			
			var symbolObj = this.branchCodePanel.getCmpByName('symbol');
			symbolObj.setValue('0');
			
			this.genCodePanel= new Ext.Panel({
				title:'节点代码生成器',
				border:false,
				layout:'border',
				items:[this.branchTreePanel,this.branchCodePanel]
			});
			
			this.tabPanel.add(this.genCodePanel);
			this.tabPanel.doLayout();
			includeDecision=true;
		}
		
		Ext.Ajax.request({
			url:__ctxPath+'/flow/getCodeProHandleComp.do',
			params:{
				defId:this.defId,
				activityName:this.activityName,
				includeDecision:includeDecision
			},
			sync:false,
			scope:this,
			success:function(resp,options){
				var result=Ext.decode(resp.responseText);
				if(result!=null){
					if(result.startExeCode){
						this.startPanel.getCmpByName('startExeCode').setValue(result.startExeCode);
					}
					if(result.endExeCode){
						this.endPanel.getCmpByName('endExeCode').setValue(result.endExeCode);
					}
//					if(this.decisionPanel && result.decisionExeCode){
//						this.decisionPanel.getCmpByName('decisionExeCode').setValue(result.decisionExeCode);
//					}
					
					if(this.branchCodePanel){
						if(result.branchDecExeCode){
							var code = result.branchDecExeCode;
							this.branchCodePanel.getCmpByName('decisionExeCode').setValue(code);
							this.initCode = code;
						}else{
							this.branchCodePanel.getCmpByName('decisionExeCode').setValue(this.initCode);
						}
					}
				}
			},
			failture:function(resp,options){}
		});
	},
	save:function(){
		var startExeCode=this.startPanel.getCmpByName('startExeCode').getValue();
		var endExeCode=this.endPanel.getCmpByName('endExeCode').getValue();
		var decisionExeCode='';
		var branchDecExeCode='';
		if(this.branchCodePanel){
			//decisionExeCode=this.decisionPanel.getCmpByName('decisionExeCode').getValue();
			branchDecExeCode=this.branchCodePanel.getCmpByName('decisionExeCode').getValue();
			/*for(var idx=0;idx<this.nodesLength;idx++){
				branchDecExeCode = branchDecExeCode.replace(idx+"=="+idx+"&&", "");
			}*/
		}
		
		Ext.Ajax.request({
			url:__ctxPath+'/flow/saveCodeProHandleComp.do',
			params:{
				defId:this.defId,
				activityName:this.activityName,
				nodeType:this.nodeType,
				startExeCode:startExeCode,
				endExeCode:endExeCode,
				//decisionExeCode:decisionExeCode
				branchDecExeCode:branchDecExeCode
			},
			scope:this,
			success:function(resp,options){
				Ext.ux.Toast.msg('操作信息','成功保存设置！');
				this.close();
			}
		});
	},
	// 分支树点击
	branchTreeClick:function(){

	},
	// 生成方法
	generate:function(){

		var cmp = this.branchCodePanel;
		
		var node = this.branchTreePanel.selectedNode;
		if(!node||node == "undefined"){
			Ext.ux.Toast.msg('操作信息','请选择一个任务');
			return;
		}

		var field = cmp.getCmpByName('fields');
		if(field.getRawValue().trim().length==0){
			Ext.ux.Toast.msg('操作信息','请填写或选取一个字段');
			return;
		}
		
		var compareValue = cmp.getCmpByName('compareValue');
		if(compareValue.getValue().replace(/(^\s*)|(\s*$)/g, "")==''){
			Ext.ux.Toast.msg('操作信息','需要比较的值不能为空');
			return;
		}
		
		var symbol = cmp.getCmpByName('symbol');
		var decisionExeCode = cmp.getCmpByName('decisionExeCode');
		var eldCode = decisionExeCode.getValue();
		var genCode = node.id+'=='+node.id+'&& ';

		if(cmp.fieldIndex!=-1){
			var fieldName = field.store.getAt(cmp.fieldIndex).data.fieldName;
			var fieldType = field.store.getAt(cmp.fieldIndex).data.fieldType;
			var fieldLabel = field.store.getAt(cmp.fieldIndex).data.fieldLabel;
			
			if(fieldName=='ID'){
				genCode+=fieldLabel;
				if(symbol.getValue()=='0'){
					genCode += '==';
				}else{
					genCode += '!=';
				}
				genCode += compareValue.getValue();
			}else if(fieldType=='text'||fieldType=='varchar'){
				if(symbol.getValue()=='0'){
					genCode+=fieldLabel;
					genCode += '.equals(\"'+compareValue.getValue()+'\")';
				}else{
					genCode+='!'+fieldLabel;
					genCode += '.equals(\"'+compareValue.getValue()+'\")';
				}
			}else{
				genCode+=fieldLabel;
				
				var symbolIdx = symbol.getValue();
				if(symbolIdx=='0'){
					genCode += '==';
				}else if(symbolIdx=='1'){
					genCode += '!=';
				}else if(symbolIdx=='2'){
					genCode += '>';
				}else if(symbolIdx=='3'){
					genCode += '<';
				}else if(symbolIdx=='4'){
					genCode += '>=';
				}else if(symbolIdx=='5'){
					genCode += '<=';
				}
					
				genCode += compareValue.getValue();
			}
		}else{
			var fieldLabel = field.getRawValue();
			if(symbol.getValue()=='0'){
				genCode+=fieldLabel;
				genCode += '.equals(\"'+compareValue.getValue()+'\")';
			}else{
				genCode+='!'+fieldLabel;
				genCode += '.equals(\"'+compareValue.getValue()+'\")';
			}
		}
			
		var newCode = eldCode.replace(node.id+'=='+node.id,genCode);
		decisionExeCode.setValue(newCode);
			
		this.codeArr[node.id] = newCode;

	},
	// 重置分支代码
	reset:function(){
		this.branchCodePanel.getCmpByName('decisionExeCode').setValue(this.initCode);
	}
	
});