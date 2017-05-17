/**
 * @author 
 * @createtime 
 * @class OrganizationForm
 * @extends Ext.Window
 * @description Organization表单
 * @company 宏天软件
 */
OrganizationForm = Ext.extend(Ext.Window, {
			//构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				
				if(this.demId=='所有维度') this.demId=null;
				//必须先初始化组件
				this.initUIComponents();
				OrganizationForm.superclass.constructor.call(this, {
							id : 'OrganizationFormWin',
							layout : 'fit',
							items : this.formPanel,
							modal : true,
							height : 400,
							width : 500,
							maximizable : true,
							title : '组织详细信息',
							buttonAlign : 'center',
							buttons : [{
										text : '保存',
										iconCls : 'btn-save',
										scope : this,
										handler : this.save
									}, {
										text : '取消',
										iconCls : 'btn-cancel',
										scope : this,
										handler : this.close
									}]
						});
			},//end of the constructor
			//初始化组件
			initUIComponents : function() {
				this.formPanel = new Ext.FormPanel({
							layout : 'form',
							bodyStyle : 'padding:10px',
							border : false,
							autoScroll : true,
							defaults : {
								anchor : '96%,96%'
							},
							defaultType : 'textfield',
							items : [{
										name : 'organization.orgId',
										xtype : 'hidden',
										value : this.orgId == null ? '' : this.orgId
									}, {
										fieldLabel:'维度',
										xtype:'combo',
										hiddenName:'organization.demId',
										displayField:'name',
										valueField:'id',
										editable : false,
										mode : 'local',
										width:180,
										triggerAction : 'all',
										store:new Ext.data.ArrayStore({
											autoLoad:true,
											url:__ctxPath+'/system/comboDemension.do?idUpdate=true',
											fields:['id','name'],
											listeners:{
												scope:this,
												'load':function(store){
													var cmp=this.getCmpByName('organization.demId');
													if(this.demId!=null){
														if(this.demId!=0){
															cmp.setValue(this.demId);
															cmp.readOnly = true;
														}else{
															cmp.setValue(1);
														}
													}else{
														if(this.orgDem!=null){
															if(this.orgDem!=0){
																cmp.setValue(this.orgDem);
																cmp.readOnly = true;
															}else{
																cmp.setValue(1);
															}
														}
													}
												}
											}
										}),
										editable : false,
 										triggerAction: 'all'
									}, {
										fieldLabel : '组织名称',
										name : 'organization.orgName',
										allowBlank : false,
										maxLength : 128
									}, {
										fieldLabel : '组织描述',
										xtype:'textarea',
										name : 'organization.orgDesc',
										maxLength : 500
									},{
										fieldLabel : '上级组织',
										name : 'organization.orgSupName',
										readOnly:true
									},{
										fieldLabel : '上级组织',
										name : 'organization.orgSupId',
										value:this.orgSupId?this.orgSupId:0,
										xtype : 'numberfield',
										hidden:true
									},{
										fieldLabel : '类型',
										hiddenName : 'organization.orgType',
										xtype : 'combo',
										editable : false,
 										triggerAction: 'all',
										store :[['0','集团'],['1','公司'],['2','部门'],['3','其他组织']]
									  }, {
											xtype : 'compositefield',
											fieldLabel : '主要负责人',
											items : [{
													xtype : 'textfield',
													readOnly : true,
													width : 270,
													name : 'chargeNames'
												},{
													xtype : 'button',
													text : '选择',
													scope : this,
													iconCls : 'btn-select',
													handler : function(){
														var fPanel = this.formPanel;
														new UserDialog({
															scope:this,
															single:false,
															callback:function(ids,fullnames){
																fPanel.getCmpByName('chargeNames').setValue(fullnames);
																fPanel.getCmpByName('chargeIds').setValue(ids);
															}
														}).show();
													}
												}]
										},{
											name : 'chargeIds',
											xtype : 'hidden'
										},{
											name : 'oldChargeIds',
											xtype : 'hidden'
										},{
											fieldLabel:'序号',
											xtype:'numberfield',
											name:'organization.sn'
										}
									]
						});
				//加载表单对应的数据	
				if (this.orgId != null && this.orgId != 'undefined') {
					this.formPanel.loadData({
								url : __ctxPath + '/system/getOrganization.do?orgId=' + this.orgId,
								root : 'data',
								preName : 'organization'
							});
				}
				
				// 取上级组织名称
				if(this.orgSupId && this.orgSupId!=0){
					Ext.Ajax.request({
					   url: __ctxPath + '/system/getSupOrganization.do',
					   scope : this,
					   success: function(resp){
						   var respText = Ext.util.JSON.decode(resp.responseText);                                                 
                           var orgName=respText.data.orgName;
                           this.formPanel.getCmpByName('organization.orgSupName').setValue(orgName);
					   },
					   failure: function(){},
					   params: { orgSupId: this.orgSupId }
					});
				}
				
				var fpStore = this.formPanel.getCmpByName('organization.orgType').store;
				if(this.isAdd==true){
					if(this.orgType==1||this.orgType==0){
						fpStore.remove(fpStore.getAt(0));
						this.formPanel.getCmpByName('organization.orgType').setValue(1);
					}
					else if(this.orgType==2){
						fpStore.remove(fpStore.getAt(0));
						fpStore.remove(fpStore.getAt(0));
						this.formPanel.getCmpByName('organization.orgType').setValue(2);
					}else{
						this.formPanel.getCmpByName('organization.orgType').setValue(0);
					}
				}
//				var comboDem = this.formPanel.getCmpByName('organization.demId');
//				comboDem.setValue(1);
			},//end of the initcomponents

			/**
			 * 重置
			 * @param {} formPanel
			 */
			reset : function() {
				this.formPanel.getForm().reset();
			},
			/**
			 * 保存记录
			 */
			save : function() {
				$postForm({
							formPanel : this.formPanel,
							scope : this,
							url : __ctxPath + '/system/saveOrganization.do',
							callback : function(fp, action) {
								if(this.callback){
									this.callback.call(this.scope);
									this.close();
									return;
								}
								var gridPanel = Ext.getCmp('OrganizationGrid');
								if (gridPanel != null) {
									gridPanel.getStore().reload();
								}
								this.close();
							}
						});
			}//end of save

		});