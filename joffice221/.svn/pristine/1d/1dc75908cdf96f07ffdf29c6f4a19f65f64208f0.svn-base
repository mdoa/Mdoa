Ext.ns('AppointmentView');
/**
 * 约会列表
 */
AppointmentForm = Ext.extend(Ext.Window, {
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 必须先初始化组件
				this.initUIComponents();
				AppointmentForm.superclass.constructor.call(this, {
					id : 'AppointmentFormWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					height : 500,
					width : 650,
					maximizable : true,					
					title : '约会详细信息',
					iconCls : 'menu-appointment',
					buttonAlign : 'center',
					buttons : [{
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
					]
				});
			},// end of the constructor
			// 初始化组件			
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
						name : 'appointment.appointId',
						xtype : 'hidden',
						value : this.appointId == null? '': this.appointId
					}, {
						fieldLabel : '主题',
						allowBlank:false,
						name : 'appointment.subject'
					}, {
						fieldLabel : '开始时间',
						name : 'appointment.startTime',
						allowBlank:false,
						xtype:'datetimefield',
						format: 'Y-m-d H:i:s'
					}, {
						fieldLabel : '结束时间',
						name : 'appointment.endTime',
						allowBlank:false,
						xtype:'datetimefield',
						format: 'Y-m-d H:i:s'
					}, {
						fieldLabel : '约会内容',
						name : 'appointment.content',
						xtype:'textarea',
						allowBlank:false
					}, {
						fieldLabel : '地点',
						name : 'appointment.location',
						allowBlank:false
					}, {
						fieldLabel : '备注',
						name : 'appointment.notes',
						xtype:'textarea'
					}, {
						fieldLabel : '受邀人Email',
						xtype:'textarea',
						name : 'appointment.inviteEmails'
					},{
						xtype : 'checkboxgroup',
						layout : 'column',
						fieldLabel : '提醒方式',
						items:[{
							xtype:'checkbox',
							name : 'appointment.sendMessage',
							boxLabel : '手机提醒',
							inputValue : 0
							
						},{
							xtype : 'checkbox',
							name : 'appointment.sendMail',
							boxLabel : '邮件提醒',
							inputValue : 1
							
						}]
					}]
				});
				// 加载表单对应的数据
				if (this.appointId != null && this.appointId != 'undefined') {
					this.formPanel.loadData({
						url : __ctxPath + '/task/getAppointment.do?appointId='
								+ this.appointId,
						root : 'data',
						preName : 'appointment'
					});
				};
			},// end of the initcomponents

			/**
			 * 重置
			 * 
			 * @param {}
			 * formPanel
			 */
			reset : function() {
				this.formPanel.getForm().reset();
			},
			/**
			 * 取消
			 * 
			 * @param {}
			 * window
			 */
			cancel : function() {
				this.close();
			},
			/**
			 * 保存记录
			 */			
			save : function() {
				var formPanel = this.formPanel;
				if (formPanel.getForm().isValid()) {
						var st=this.formPanel.getCmpByName('appointment.startTime').getValue();
						var et=this.formPanel.getCmpByName('appointment.endTime').getValue();
						var sd=Date.parse(st);
					    var ed=Date.parse(et);
				    	if(sd>ed){
				    		Ext.ux.Toast.msg('操作信息', '约会开始时间大于结束进间,不能保存!');
				    		return;
				    	};
					$postForm({
						formPanel : this.formPanel,
						scope : this,
						url : __ctxPath + '/task/saveAppointment.do',
						callback : function(fp, action) {															
							if (this.callback) {
								this.callback.call(this.scope);
							}
							this.close();
						}
					});
				}// end of save
			}
		});