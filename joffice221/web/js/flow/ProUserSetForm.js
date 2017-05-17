/**
 * @author 
 * @createtime 
 * @class ProUserSetForm
 * @extends Ext.Window
 * @description ProUserSet表单
 * @company 宏天软件
 */
ProUserSetForm = Ext.extend(Ext.Window, {
			//构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				//必须先初始化组件
				this.initUIComponents();
				ProUserSetForm.superclass.constructor.call(this, {
							id : 'ProUserSetFormWin',
							layout : 'fit',
							items : this.formPanel,
							modal : true,
							height : 200,
							width : 500,
							maximizable : true,
							title : '人员设置详细信息',
							buttonAlign : 'center',
							buttons : [
										{
											text : '保存',
											iconCls : 'btn-save',
											scope : this,
											handler : this.save
										}, {
											text : '重置',
											iconCls : 'btn-reset',
											scope : this,
											handler : this.reset
										}, {
											text : '取消',
											iconCls : 'btn-cancel',
											scope : this,
											handler : this.cancel
										}
							         ],
				         keys : {
								key : Ext.EventObject.ENTER,
								fn : this.save,
								scope : this
							}
				});
			},//end of the constructor
			//初始化组件
			initUIComponents : function() {
				this.formPanel = new Ext.FormPanel({
							layout : 'form',
							bodyStyle : 'padding:10px',
							border : false,
							autoScroll:true,
							//id : 'ProUserSetForm',
							defaults : {
								anchor : '96%,96%'
							},
							defaultType : 'textfield',
							items : [{
								name : 'proUserSet.id',
								xtype : 'hidden',
								value : this.id == null ? '' : this.id
							},{
								fieldLabel : 'defId',	
								name : 'proUserSet.defId',
 								xtype : 'hidden',
 								value : this.defId == null ? '' : this.defId
 							},{
								fieldLabel : 'demId',	
								name : 'proUserSet.demension.demId',
 								xtype : 'hidden',
 								value : this.demId == null ? '' : this.demId
 							},{
								fieldLabel : 'strategy',	
								name : 'proUserSet.strategy',
 								xtype : 'hidden',
 								value : this.strategy == null ? '' : this.strategy
 							},{
								fieldLabel : 'deployId',	
								name : 'proUserSet.deployId',
 								xtype : 'hidden',
 								value : this.deployId == null ? '' : this.deployId
 							},{
								fieldLabel : 'nodeName',	
								name : 'proUserSet.nodeName',
 								xtype : 'hidden',
 								value : this.nodeName == null ? '' : this.nodeName
 							},{
								fieldLabel : 'posUserFlag',	
 								name : 'proUserSet.posUserFlag',
 								xtype : 'hidden',
 								value : '0'
 							},{
								fieldLabel : 'jbpmDefId',	
								name : 'proUserSet.jbpmDefId',
 								xtype : 'hidden',
 								value : this.jbpmDefId == null ? '' : this.jbpmDefId
 							},{
								fieldLabel : '用户类型',
								hiddenName : 'proUserSet.userType',
 								xtype : 'combo',
								allowBlank : false,
								editable : false,
								mode : 'local',
								triggerAction : 'all',
								store : [[1, '发起人'], [2, '用户'],
											[3, '角色'], [4, '岗位'],
											[5, '部门岗位'], [6, '部门负责人'],
											[7, '发起人上下级'],[8,'发起人部门上下级'],[9,'部门']],
								value : 1,
								listeners : {
									scope : this,
									'select' : function(comboBox) {
											if(comboBox.getValue() == 1){
												this.setUserInfo('__start','[发起人]');
												Ext.getCmp("btn_select").setDisabled(true);
											}else{
												this.setUserInfo('','');
												Ext.getCmp("btn_select").setDisabled(false);

											}
									}
								}
								
 							},{
								fieldLabel : '用户Ids',	
 								name : 'proUserSet.uids',
 								xtype : 'hidden',
 								value : '__start'
 							},{
								fieldLabel : '用户来自',	
 								xtype:'compositefield',
 								items : [{
 									name : 'proUserSet.unames',
									xtype : 'textarea',
									allowBlank : false,
									width : 250,
									readOnly : true,
									value : '发起人'
									}, {
									text : '选择来自',
									xtype : 'button',
									id:'btn_select',
									iconCls : 'btn-select',
									scope : this,
									disabled:true,
									handler : this.selectFrom
								}]
 							},{
								fieldLabel : '运算类型',	
								hiddenName : 'proUserSet.compType',
 								xtype : 'combo',
								allowBlank : false,
								editable : false,
								mode : 'local',
								triggerAction : 'all',
								store : [[1, '或运算'], [2, '与运算'],[3, '排除']],
								value : 1
 							}
							]
						});
				
			},//end of the initcomponents
			selectFrom :function(){
				var me = this;
				var fPanel = this.formPanel;
				var userType = fPanel.getCmpByName('proUserSet.userType').getValue();
				var uids = fPanel.getCmpByName('proUserSet.uids').getValue();
				var unames = fPanel.getCmpByName('proUserSet.unames').getValue();
				//var posUserFlag = fPanel.getCmpByName('proUserSet.posUserFlag').getValue();
				var demId = fPanel.getCmpByName('proUserSet.demension.demId').getValue();
				var strategy = fPanel.getCmpByName('proUserSet.strategy').getValue();
				switch (userType) {
					case 1 : //发起人
						me.setUserInfo('__start','[发起人]');
						break;
	          		case 2 ://用户
	          			me.userSelector(uids,unames);
	          			break;
	          		case 3 ://角色
	          			me.roleSelector(uids,unames);
	          			break;
	          		case 4 ://岗位
	          			me.jobSelector(uids,unames);
	          			break;
	          		case 5 : //部门岗位
	          			me.depPosSelector(uids,unames);
	          			break;
	          		case 6 ://部门负责人	
	          			me.depSelector(uids,unames);
	          			break;
	          		case 7 ://发起人上下级	
	          			me.reJobSelector(uids,unames,demId);
	          			break;
          			case 8://发起人部门上下级
			    		me.reOrgSelector(uids,unames,demId,strategy);
          			case 9://部门
          				me.depSelector(uids,unames);
	          		default :
	    				break;
				}

			},
			//设置值
			setUserInfo : function(ids,names,demId,strategy){
				var fPanel = this.formPanel;	
				fPanel.getCmpByName('proUserSet.uids').setValue(ids);
				fPanel.getCmpByName('proUserSet.unames').setValue(names);
				//fPanel.getCmpByName('proUserSet.posUserFlag').setValue(posUserFlag);
				fPanel.getCmpByName('proUserSet.demension.demId').setValue(demId);
				fPanel.getCmpByName('proUserSet.strategy').setValue(strategy);
			},
			//2.用户选择器
			userSelector :function(uids,unames){
				var me = this;
				 new UserDialog({
						scope:this.scope ? this.scope : this,
						single:false,
						isForFlow:false,
						userIds:uids,
						userName:unames,
						callback:function(ids,names){
							me.setUserInfo(ids,names);
						}
					}).show();
			},
			//3.角色选择器
			roleSelector :function(uids,unames){
				new RoleDialog({
					scope:this,
					single:false,
					roleIds:uids,
					roleName:unames,
					callback:function(ids,names){
						this.setUserInfo(ids,names);
					}
				}).show();
			},
			//4.岗位选择器
			jobSelector :function(uids,unames){
				new PositionDialog({
					scope:this,
					single:false,
					sameLevelIds:uids,
					sameLevelNames:unames,
					callback:function(ids,names){
						this.setUserInfo(ids,names);
					}
				}).show();
			},

			//5.部门岗位选择器
			depPosSelector :function(uids,unames){
				new PositionDialog({
					scope:this,
					single:false,
					sameLevelIds:uids,
					sameLevelNames:unames,
					callback:function(ids,names){
						this.setUserInfo(ids,names);
					}
				}).show();
			},	
			
			//6 部门负责人选择
			 depSelector : function(uids,unames){
				var me = this;
				new DepDialog({
					single : false,
					scope : this,
					depIds : uids,
					depNames : unames,
					callback : function(ids, names) {
						me.setUserInfo(ids,names);
					}
				}).show();
			},
			//7.相对岗位选择器
			reJobSelector :function(uids,unames,demId){
				new ReJobDialog({
					scope:this,
					single:false,
					reJobId:uids,
					reJobName:unames,
				//	posUserFlag: posUserFlag==''?0:posUserFlag,
					demId:	demId==''?0:demId,	
					callback:function(ids,names,demId){
						this.setUserInfo(ids,names,demId);
					}
				}).show();
			},
			reOrgSelector:function(uids, unames,demId,strategy){
				new ReOrgDialog({				
							scope : this,
							single : false,
							reOrgId : uids,
							demId:demId,
							reOrgstrategy:strategy,
							// posUserFlag:
							// record.get('posUserFlag')==null||record.get('posUserFlag')==''?0:record.get('posUserFlag'),
							callback : function(ids, names,retuenDemId,returnStrategy) {
								this.setUserInfo(ids, names,retuenDemId,returnStrategy);
							}
						}).show();
			},
	
			/**
			 * 重置
			 * @param {} formPanel
			 */
			reset : function() {
				this.formPanel.getForm().reset();
			},
			/**
			 * 取消
			 * @param {} window
			 */
			cancel : function() {
				this.close();
			},
			/**
			 * 保存记录
			 */
			save : function() {
				$postForm({
						formPanel:this.formPanel,
						scope:this,
						url:__ctxPath + '/flow/saveProUserSet.do',
						callback : function(fp, action) {
							if (this.callback) {
								this.callback.call(this.scope);
							}
							this.close();
						}
					}
				);
			}//end of save

		});