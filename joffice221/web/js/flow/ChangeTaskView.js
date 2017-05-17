Ext.ns('ChangeTaskView');
/**
 *新建流程 
 */

ChangeTaskView = Ext.extend(Ext.Window, {
			// 构造函数
			constructor : function(_cfg) {

				Ext.applyIf(this, _cfg);
				// 初始化组件
				this.initUIComponents();
				// 调用父类构造
				ChangeTaskView .superclass.constructor.call(this, {
									title:'任务代办--' + this.taskname,
									height:180,
									id:'ChangeTaskView',
									iconCls:'btn-changeTask',
									buttonAlign:'center',
									width:500,
									modal:true,
									layout:'fit',
								    items:[this.formPanel],
								    buttons:[
										{
											text:'转交代办人',
											iconCls:'btn-save',
											scope:this,
											handler :this.commissionClick
										},
										{
											text:'关闭',
											iconCls:'btn-close',
											scope:this,
											handler:function(){
												this.close();
											}
										}
									]
						});
			},// end of constructor
			/**
			 * 初始化组件
			 */
			initUIComponents : function() {
				this.formPanel=new HT.FormPanel({
						layout:'form',
						bodyStyle:'padding:4px 4px 4px 4px',
						url:__ctxPath+'/flow/changeTask.do',
						border:false,
						items:[
							{
								xtype:'hidden',
								name:'taskId',
								value:this.taskId
							},{
								xtype : 'panel',
								height:32,
								border:false,
								layout : 'column',
								defaults:{
									margins:'0 6 0 0'
								},
								items : [{
											xtype : 'label',
											text : '代办人',
											width : 105
										}, {
											name : 'fullname',
											xtype:'textfield',
											allowBlank:false,
											width:240
										}, {
											xtype:'hidden',
											name:'userId'
										},{
											xtype : 'button',
											text : '选择',
											iconCls : 'btn-select',
											width : 80,
											scope:this,
											handler : this.selectUser
										}]
							},{
								xtype:'textarea',
								name:'msg',
								anchor:'98%,98%',
								fieldLabel:'备注'
							}
						]
					});
			},// end of the initComponents()
			/**
			 * 转交代办人 函数
			 */
			 commissionClick:function() {
					if (this.formPanel.getForm().isValid()) {
						this.formPanel.getForm().submit({
							scope:this,
							success : function(form, action) {
								Ext.ux.Toast.msg('操作信息提示','任务已经成功转交代办人来处理！');
								var MyTaskView=Ext.getCmp("MyTaskView");;
								var appHomeTaskGrid=Ext.getCmp('TaskPanelView');
								if(appHomeTaskGrid!=null){
									appHomeTaskGrid.getUpdater().update(__ctxPath+ '/flow/displayTask.do');
								}
								if(MyTaskView!=null){
									MyTaskView.gridPanel.getStore().reload();
								}
								this.close();
								
							}
						});
					}
				},
			selectUser:function() {
					var outDocument=this;
					new  UserDialog({
						callback:function(ids, names) {
							  var fullname =outDocument.getCmpByName('fullname');//Ext.getCmp('fullname');
							  var userId = outDocument.getCmpByName('userId');
							  fullname.setValue(names);
							  userId.setValue(ids);
							},
						single:true     //单选的意思
					}).show();
				}
 });