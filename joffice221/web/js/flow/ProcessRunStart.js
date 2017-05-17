Ext.ns('ProcessRunStart');
/**
 *  提交内容时，如果有文档话，需要下面参数
 *  //实例化的控件对象
 *  var myNewOffice = null;
 *  //页面对象
 *  var myFormPanel = null;
 *  //提交类型
 *  var mySubmitType = null;
 *  //文档上传后的内容容器
 *  var myhiddenF = null;
 *  //参数
 *  var myBaseParams = null;
 *  //文档上传后的内容容器
 *  var myTabFormId = null;
 * 
 *  流程起始 
 */
ProcessRunStart = Ext.extend(Ext.Panel, {
			// 构造函数
			constructor : function(_cfg) {

				Ext.applyIf(this, _cfg);

				// 初始化组件
				this.initUIComponents();
				// 调用父类构造
				ProcessRunStart.superclass.constructor.call(this,{
						title:'流程启动-' + this.flowName,
						iconCls:'btn-flow-start',
						autoScroll:true,
						layout:'form',
						id:'ProcessRunStart'+this.defId,
						tbar:new Ext.Toolbar({height:26,items:this.buttonsArr}),
						layoutConfig: {
			                padding:'5',
			                pack:'center',
			                align:'middle'
			            },
			            defaults:{
			            	margins:'0 5 10 0'
			            },
			            items:[
			                this.jumpPanel,
			            	this.formPanel	
			            ]
					});
			},// end of constructor
			/**
			 * 初始化组件
			 */
			initUIComponents : function() {
					//使用HTML表单模板
					this.useTemplate=false;
					//下一授予任务名
					this.assignTasks=new Array();
					//下一任务用户名
					this.assignUserIds=new Array();
					//下一任务用户名
					this.assignUserName=new Array();
					this.buttonsArr=[
						{
							text:'提交并启动流程',
							iconCls : 'btn-ok',
							scope:this,
							handler:this.saveAndStart
						},'-',{
							xtype:'checkbox',
							boxLabel:'发送邮件',
							scope:this,
							handler:function(ck,checked){
								if(checked){
									this.sendMail=true;
								}else{
									this.sendMail=false;
								}
							}
						},{
							xtype:'checkbox',
							boxLabel:'发送短信',
							scope:this,
							handler:function(ck,checked){
								if(checked){
									this.sendMsg=true;
								}else{
									this.sendMsg=false;
								}
							}
						},'-',{
							text:'流程示意图',
							iconCls:'btn-flow-chart',
							scope:this,
							handler:this.showFlowImage
						}
				];
				//用户选择的Panel，为下一节点进行人员选择
				this.userJumpPanel=new Ext.form.FieldSet({
							title:'选择执行人',
							autoHeight:true,
							collapsed: false,
							collapsible : true
				});
				//取得开始节点之后跳转路径
				this.jumpPanel=new Ext.Panel({
					bodyStyle:'padding:16px 4px 4px 20px',
					autoHeight:true,
					layout:'form',
					border:false
				});
				//加载开始节点后的分支
				Ext.Ajax.request({
					url:__ctxPath+'/flow/startTransProcessActivity.do',
					params:{
						defId:this.defId
					},
					scope:this,
					success:function(resp,options){
						var object=Ext.decode(resp.responseText);
						var radioItems=[];
						for(var i=0;i<object.data.length;i++){
							radioItems.push({
								boxLabel:object.data[i].destination,
								name:'jumpPath_'+this.defId,
								inputValue:object.data[i].name,
								destType:object.data[i].destType,
								destName:object.data[i].destination,
								checked:i==0?true:false//缺省第一个选中
							});
						}
						this.jumpRadioGroup = new Ext.form.RadioGroup({
							listeners:{
								scope:this,
								'change':this.jumpRadioCheck
							},
							fieldLabel : '执行路径',
							items : radioItems
						});
						
		                //以加载相应的人员
						this.jumpPanel.insert(0,this.jumpRadioGroup);
						this.jumpPanel.doLayout();
						this.jumpRadioCheck.call(this);
					}
				});
				
				//加载其对应的HTML或EXT表单
				this.formPanel=new HT.FormPanel({
					region:'center',
					border:false,
					bodyStyle:'padding:20px',
					autoScroll:true,
					autoLoad:{
						url:__ctxPath+ "/flow/getProcessActivity.do?defId="+this.defId,
						nocache:true,
						scope:this,
						scripts :true,
						callback:this.convertHtml
					}
				});
				myFormPanel = this.formPanel;
			},// end of the initComponents()
			
			/**
			 * 单选项按钮点击
			 * @param {} rd
			 * @param {} ckradio
			 */
			jumpRadioCheck:function(){
				
				var radio=this.jumpRadioGroup.getValue();
				
				this.getTaskUsers.call(this,radio.destName,radio.destType);
				
			},
			/**
			 * 取得下一节点对应的处理人员
			 * @param {} destName 目标节点的名称
			 * @param {} destType 目标节点的类型
			 */
			getTaskUsers:function(destName,destType){
				
				//下一节点为分支及fork节点
				if('join'==destType || 'decision'==destType || 'fork'==destType){
					this.userJumpPanel.removeAll();
					this.userJumpPanel.show();
					var url=__ctxPath+'/flow/outerTransProcessActivity.do';
					var params={defId:this.defId,nodeName:destName};
					this.genForkDecUserAssign.call(this,destName,url,params);
				}else if(destType.indexOf('end')!=-1 ){//下一节点为结束节点，需要隐藏下一步的执行人	
					this.userJumpPanel.removeAll();
					this.userJumpPanel.hide();
				}else{//下一节点为普通任务节点
					this.userJumpPanel.removeAll();
					this.userJumpPanel.show();
					var params={defId:this.defId,activityName:destName};
					var url=__ctxPath+'/flow/usersProcessActivity.do';
					this.genForkDecUserAssign.call(this,destName,url,params);
				}
				this.jumpPanel.doLayout();
			
			},
			/**
			 * 选中和取消复选框时触发的事件
			 */
			userCheckBoxSelect:function(curChb,isChecked){
				var selIds=[];
				var selNames=[];
				var index=0;
				if(this.assignUserIds[curChb.taskIdx]!=''&&!isChecked){
					selIds = this.assignUserIds[curChb.taskIdx].toString().split(',');
					selNames = this.assignUserName[curChb.taskIdx].toString().split(',');
					for(index=0;index<selIds.length;index++){
						if(selIds[index]==curChb.inputValue){
							break;
						}
					}
				}
				if(isChecked){
					var auName=this.assignUserName[curChb.taskIdx].toString();
					var auIds=this.assignUserIds[curChb.taskIdx].toString();
					if(auIds.length==0){
						auIds+=curChb.inputValue;
						auName+=curChb.boxLabel;
					}else{
						auIds+=','+curChb.inputValue;
						auName+=','+curChb.boxLabel;
					}
					this.assignUserIds[curChb.taskIdx]=[auIds];
					this.assignUserName[curChb.taskIdx]=[auName];
					curChb.isChecked = true;
				}else{
					if(selIds.length==0){
						this.assignUserIds[curChb.taskIdx]=[];
						this.assignUserName[curChb.taskIdx]=[];
					}else{
						selIds = selIds.slice(0,index).concat(selIds.slice(index+1,selIds.length));
						selNames = selNames.slice(0,index).concat(selNames.slice(index+1,selNames.length));
						this.assignUserIds[curChb.taskIdx]=[selIds];
						this.assignUserName[curChb.taskIdx]=[selNames];
					}
					curChb.isChecked = false;
				}
			},
			
			//为汇集或分支节点产生自由跳转的人员选择
			genForkDecUserAssign:function(destName,url,params){
				Ext.Ajax.request({
					url:url,
					params:params,
					scope:this,
					success:function(resp,options){
						//outers数据格式为[{singalName,activityName,destType},...]
						//如：[["to 总经理审阅","总经理审阅","task","1,2","张三,李四"],["to 财务审核","财务审核","task","1,2","张三,李五"]]
						var outers=Ext.decode(resp.responseText);
						var fans = [];
						var relArr = [];
						var suid = [];
						var userCheckboxItems=[];
						for(var i=0;i<outers.length;i++){
							this.userJumpPanel.add(this.genUserFieldSel.call(this,outers[i],i,fans,relArr,suid,userCheckboxItems));
						}

						this.jumpPanel.add(this.userJumpPanel);
						this.jumpPanel.doLayout();
						
					}
				});
			},
				//产生用户选择
			genUserFieldSel:function(outers,idx,fans,relArr,suid,userCheckboxItems){
				
				//目标节点名称
				var destName=outers[1];
				this.assignTasks[idx]=destName;
				this.assignUserIds[idx]=outers[3];
				this.assignUserName[idx]=outers[4];
				
				relArr[idx] = outers[4].split(',');
				suid[idx] = outers[3].split(',');
				userCheckboxItems[idx] = [];
				
				// 创建复选框数组
				for(var index=0;index<relArr[idx].length;index++){
					userCheckboxItems[idx].push({
						boxLabel: relArr[idx][index],
						name: 'userName',
						inputValue: suid[idx][index],
						checked: true,
						taskIdx:idx,
						listeners:{
							check:this.userCheckBoxSelect,
							scope:this
		            	}
					});
				}
				// 把复选框填入CheckboxGroup
				var userCheckboxgroup = new Ext.form.CheckboxGroup({
				    columns:5,
				    items:userCheckboxItems[idx]
				});
				
				fans[idx]=new Ext.Panel({
					width:450,
					height:50,
				    bodyPadding: 2,
				    border: false,
					autoScroll : true,
				    items:[userCheckboxgroup]
				});
				
				var cmpField=new Ext.form.CompositeField({
					border:false,
					fieldLabel:destName,
					index:idx,
					items:[
						fans[idx],
						{
							xtype:'button',
							text:'...',
							iconCls : 'btn-users',
							scope:this,
							handler:function(){
								new UserDialog({
									scope:this,
									single:false,
									callback:function(uIds, uNames) {
										
										//查找该数组中是否已经存在这个目标节点，若存在，则找到其坐标
										var index=this.assignTasks.length;
										for(var i=index-1;i>=0;i--){
											if(this.assignTasks[i]==destName){
												index=i;
												break;
											}
										}
										this.assignTasks[index]=destName;
										
										// 判断返回的项是否在存在的项当中
										var flag = false;
										var relArr0 = uNames.split(',');
										var suid0 = uIds.split(',');
										if(uIds.length>0){
											var aUids=this.assignUserIds[cmpField.index].toString();
											var aUnames=this.assignUserName[cmpField.index].toString();
											suid = this.assignUserIds[cmpField.index].toString().split(',');
		
											for(var index0=0;index0<suid0.length;index0++){
												for(var index=0;index<suid.length;index++){
													if(suid0[index0]==suid[index]){
														flag=true;
														break;
													}
												}
												if(!flag){
													var isExist=false;
													for(var i=0;i<userCheckboxItems[cmpField.index].length;i++){
														if(userCheckboxItems[cmpField.index][i].inputValue==suid0[index0]){
															isExist = true;
															break;
														}
													}
													aUids+=aUids.length>0?','+suid0[index0]:suid0[index0];
													aUnames+=aUnames.length>0?','+relArr0[index0]:relArr0[index0];
													if(!isExist){
														userCheckboxItems[cmpField.index].push({
															boxLabel: relArr0[index0],
															name: 'userName',
															inputValue: suid0[index0],
															checked: true,
															taskIdx:cmpField.index,
															listeners:{
																check:this.userCheckBoxSelect,
																scope:this
								                        	}
														});
													}
												}
												flag=false;
											}
											
											this.assignUserIds[cmpField.index]=[aUids];
											this.assignUserName[cmpField.index]=[aUnames];
										}
		
										// 判断存在的项是否选中
										var isExist=true;
										var arrUid = [this.assignUserIds[cmpField.index].toString()];
										if(arrUid.toString().indexOf('\,')!=-1){
											arrUid = arrUid.toString().split(',');
										}
										for(var i=0;i<userCheckboxItems[cmpField.index].length;i++){
											for(var j=0;j<arrUid.length;j++){
												if(userCheckboxItems[cmpField.index][i].inputValue==arrUid[j]){
													break;
												}
												if(j+1==arrUid.length){
													isExist=false;
												}
											}
											if(!isExist){
												userCheckboxItems[cmpField.index][i].checked = false;
												isExist=true;
											}
										}
										
										userCheckboxgroup = new Ext.form.CheckboxGroup({
										    columns:5,
										    items:userCheckboxItems[cmpField.index]
										});
		
										fans[cmpField.index].removeAll();
										fans[cmpField.index].add(userCheckboxgroup);
										fans[cmpField.index].doLayout();
									}
								}).show();
							}
						},{
							xtype:'button',
							scope:this,
							text:'未选中删除',
							iconCls : 'btn-del',
							handler:function(){
								if(this.assignUserIds[cmpField.index].toString().length>0){
									var selItem = this.assignUserIds[cmpField.index].toString().split(',');
									var backupItem = userCheckboxItems[cmpField.index];
									var backupItem0 = userCheckboxItems[cmpField.index];
									var isExist=true;
		
									// 把没有选中的项删除
									for(var i=backupItem.length-1;i>=0;i--){
										for(var j=0;j<selItem.length;j++){
											if(backupItem[i].inputValue==selItem[j]){
												break;
											}
											if(j+1==selItem.length){
												isExist = false;
											}
										}
										if(!isExist){
											isExist = true;
											userCheckboxItems[cmpField.index] 
												= backupItem0.slice(0,i).concat(backupItem0.slice(i+1,backupItem0.length));
											backupItem0 = userCheckboxItems[cmpField.index];
										}
									}
									
									// 重新对已选人员赋值
									if(selItem.length>0){
										var auIds = '';
										var auName = '';
										
										for(var i=0;i<userCheckboxItems[cmpField.index].length;i++){
											if(auIds.length>0){
												auIds += ","+userCheckboxItems[cmpField.index][i].inputValue;
												auName += ","+userCheckboxItems[cmpField.index][i].boxLabel;
											}else{
												auIds = userCheckboxItems[cmpField.index][i].inputValue;
												auName = userCheckboxItems[cmpField.index][i].boxLabel;
											}
										}
										
										this.assignUserIds[cmpField.index]=[auIds];
										this.assignUserName[cmpField.index]=[auName];
									}
		
									userCheckboxgroup = new Ext.form.CheckboxGroup({
									    columns:5,
									    items:userCheckboxItems[cmpField.index]
									});
									
									fans[cmpField.index].removeAll();
									fans[cmpField.index].add(userCheckboxgroup);
									fans[cmpField.index].doLayout();
								}
							}
						}
					]
				});
				
				return cmpField;
										
			},
			/**
			 * 取得下一任务及其对应的人员
			 */
			getFlowAssignId:function(){
					//返回其格式如下：领导审批:财务审核:...|1,2:3,4:...),也只可为1,2,3(当下一步仅有一任务时）
					var flowAssignId='';
					var destTasks='';
					var destUserIds='';
					for(var i=0;i<this.assignTasks.length;i++){
						if(i>0){
							destTasks+=':';
							destUserIds+=':';
						}
						destTasks+=this.assignTasks[i];
						destUserIds+=this.assignUserIds[i];
					}
					if(destTasks!=''){
						flowAssignId=destTasks+'|'+destUserIds;
					}
					return flowAssignId;
			},
			//转化Html
			convertHtml:function(){
				var formExt=document.getElementById('formExt'+this.defId);
				if(formExt!=null){
					//加上标识，表示是使用EXT模板进行
					this.useTemplate=true;
					var valExt=formExt.value;
					valExt=valExt.replace('Ext.form.FormPanel','Ext.Panel');
					this.formExtPanel=eval('new ('+valExt+')('+this.vmParams+');');
					this.formPanel.add(this.formExtPanel);
					this.formPanel.doLayout();
					
					return;
				}
				this.formPanel.doLayout();
		        try{
			        var json=document.getElementById('rightsdef_'+this.defId);
			        var subRjsonStr=document.getElementById('subButtonRightsdef_'+this.defId);
			        if(json!=null){
			        	//加载JS回调函数
			        	var callback=function(){
			        	    var rightsJson=Ext.decode(json.value);
			        	    var subRjson = "";
			        	    if(subRjsonStr!=null){
			        	    	subRjson=Ext.decode(subRjsonStr.value);
			        	    }
					        $converDetail.call(this,null,rightsJson,null,subRjson);
					        if (Ext.DocumentForm){
					        	Ext.DocumentForm.call(this);
					        	Ext.DocumentForm="";
					        }
			        	};
			        	//后加载文档的JS
			        	$ImportSimpleJs([
			        	   '/js/core/ntkoffice/NtkOfficePanel.js',
		        	     '/js/selector/SealDialog.js',
			             '/js/selector/PaintTemplateDialog.js'
			        	 
			        	 ],callback,this);
			            
			        }
		        }catch(e){
					//alert(e);
				}
				
			},
			
			/**
			 * 保存并启动流程
			 */
			saveAndStart:function(){
				
				// 判断是否选择了任务执行人
				if(this.assignUserName!=null&&this.assignUserName.length>0){
					var assignUserFlag = false;
					for(var idx=0;idx<this.assignUserName.length;idx++){
						var arrAu = this.assignUserName[idx].toString();
						if(arrAu.length==0){
							assignUserFlag = true;
						}
					}
					if(assignUserFlag){
						Ext.ux.Toast.msg('操作信息','请选择执行人');
						return;
					}
				}
				//表单是否合法，默认为合法
				var isValid=true;
				//加上对于表单的前置验证，允许在模板中加上自己的验证
				if(this.formExtPanel!=null&& this.formExtPanel.validate){
					isValid=this.formExtPanel.validate.call(this.formExtPanel,this);
				}else{
					isValid=$validForm.call(this);
				}
				if(!isValid){
					Ext.ux.Toast.msg('操作信息','请填写有效表单数据！');
					return;
				}
				
				var destName=this.jumpRadioGroup.getValue().destName;
				//设置flowAssignId,用于指定下一任务的执行人
				var flowAssignId=this.getFlowAssignId.call(this);
				
				var formPanel=this.formPanel;
				
				//加上流程参数配置
				var flowVars="";
				var defParams=document.getElementById('defParams'+this.defId);
				if(defParams){
					flowVars=defParams.value;
				}
				
				var baseParams={
							useTemplate:this.useTemplate,
							defId:this.defId,
							startFlow:true,
							destName:destName,
							sendMsg:this.sendMsg,
							sendMail:this.sendMail,
							flowAssignId:flowAssignId,
							flowVars:"{"+flowVars+"}"
						};
				if(this.detailGrids){//适用于多个GRID的
					var grids=this.detailGrids.keys;
					for(var j=0;j<grids.length;j++){
						var details=[];
						var detailPanel=this.detailGrids.get(grids[j]);
					    var store=detailPanel.getStore();
						for(var i=0;i<store.getCount();i++){
							var record=store.getAt(i);
							var d=HT.encode(record.data);
							details.push(d);
						}
						baseParams[grids[j]+'details']=Ext.encode(details);
					}
				}

				var dom=formPanel.getForm().getEl().dom;
		        //取得表单里面的子表单
		        var forms=dom.getElementsByTagName('form');
		        var dv=[];
		        var detailsMap=new Ext.util.MixedCollection();
		        for(var i=0;i<forms.length;i++){
		        	 var belongName=forms[i].getAttribute('belongName');
		        	 var pkName=forms[i].getAttribute('pkName');
		        	 var pkValue=forms[i].getAttribute('pkValue');
		             var baseParam2 = Ext.Ajax.serializeForm(forms[i]);
		             var deParams = Ext.urlDecode(baseParam2);//取得了从表里面的数据
		             //进行数据组装
		             if(pkName&&pkValue){
		                deParams[pkName]=pkValue;
		             }
		             var dd=HT.encode(deParams);
		             
		             var tt=detailsMap.get(belongName);
		             if(!tt){
		             	 var details=[];
		             	 details.push(dd);
		                 detailsMap.add(belongName,details);
		             }else{
		                 tt.push(dd);
		             }
		        }
		        
		        for(var i=0;i<detailsMap.keys.length;i++){
		        	var keyName=detailsMap.keys[i];
		            baseParams[keyName+'details']=Ext.encode(detailsMap.get(keyName));
		        }
				
				/**
				 * @author lyy
				 * @description
				 *  取得表单里面的OFFICE控件面板，保存文档，再把文档ID返回给表单字段
				 */
				var officePanel=this.officePanel;
				if(officePanel){
					mySubmitType = "ProcessRunStart";
					myNewOffice = this.officePanel;
					myFormPanel = this.formPanel;
					myhiddenF = this.hiddenF;
					myBaseParams = baseParams;
					myTabFormId = this.getId();
					var obj=null;
				    if(this.fileId!=''&&this.fileId!=undefined){
		     	 	     obj=officePanel.saveDoc({docName:'ProcessDocument',fileId:this.fileId,doctype:'doc'});
		     	    }else{
		     	         obj=officePanel.saveDoc({docName:'ProcessDocument',doctype:'doc'});
		     	    }
		     	    if(obj&&obj.success){
						var fileId=obj.fileId;
						if(fileId>0){
							this.hiddenF.setValue(fileId);
							//启动工作流
							if(formPanel.getForm().isValid()){
								formPanel.getForm().submit({
									url : __ctxPath + '/flow/saveProcessActivity.do',
									waitMsg : '正在提交流程表单信息...',
									scope:this,
									params:baseParams,
									success : function(fp, action) {
										Ext.ux.Toast.msg('操作信息','成功保存信息！');
										if(officePanel){
										   officePanel.closeDoc();
										}
										AppUtil.removeTab(this.getId());
									},
									failure: function(fp, action) {
										var msg = action.result.message;
										Ext.ux.Toast.msg('操作信息',msg);
										if(officePanel){
										   officePanel.closeDoc();
										}
									}
								});
							}
						}else{
							//火狐谷歌实现异步提交的内容：在NtkOfficePanel.js 中的saveMethodOnComplete方法实现;
						}	
				    } 
				}else{
					//启动工作流
					if(formPanel.getForm().isValid()){
						formPanel.getForm().submit({
							url : __ctxPath + '/flow/saveProcessActivity.do',
							waitMsg : '正在提交流程表单信息...',
							scope:this,
							params:baseParams,
							success : function(fp, action) {
								Ext.ux.Toast.msg('操作信息','成功保存信息！');
								if(officePanel){
								   officePanel.closeDoc();
								}
								AppUtil.removeTab(this.getId());
							},
							failure: function(fp, action) {
								var msg = action.result.message;
								Ext.ux.Toast.msg('操作信息',msg);
								if(officePanel){
								   officePanel.closeDoc();
								}
							}
						});
					}
				}
			},
			reset:function(){
				this.formPanel.getForm().reset();
			},
			/**
			 * 显示流程图
			 */
			showFlowImage:function(){
				var win=new Ext.Window({
					autoScroll:true,
					iconCls:'btn-flow-chart',
					bodyStyle:'background-color:white',
					maximizable : true,
					title:'流程示意图',
					width:600,
					height:500,
					modal:true,
					html:'<img src="'+__ctxPath+ '/jbpmImage?defId='+this.defId+ '&rand=' + Math.random()+ '"/>'
				});
				win.show();
			}
 });
 
 function removeFile(obj, fileId) {
	var fileIds = Ext.getCmp("archivesRecfileIds");
	var value = fileIds.getValue();
	if (value.indexOf(',') < 0) {// 仅有一个附件
		fileIds.setValue('');
	} else {
		value = value.replace(',' + fileId, '').replace(fileId + ',', '');
		fileIds.setValue(value);
	}

	var el = Ext.get(obj.parentNode);
	el.remove();
};