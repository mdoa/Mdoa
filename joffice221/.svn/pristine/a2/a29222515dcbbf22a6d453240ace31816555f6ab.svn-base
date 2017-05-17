/**
 * @author
 * @createtime
 * @class SmsMobileForm
 * @extends Ext.Window
 * @description SmsMobile表单
 * @company 宏天软件
 */
SmsMobileForm = Ext.extend(Ext.Window, {
	// 内嵌FormPanel
	formPanel : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		SmsMobileForm.superclass.constructor.call(this, {
					id : 'SmsMobileFormWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					height : 400,
					width : 718,
					minHeight : 400,
					autoScroll:true,
					minWidth : 717,
					iconCls : 'menu-mobile',
					maximizable : true,
					title : '手机详细信息',
					buttonAlign : 'center',
					buttons : this.buttons
				});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		this.formPanel = new Ext.FormPanel({
					layout : 'form',
					bodyStyle : 'padding:10px 10px 10px 10px',
					border : false,
					
					id : 'SmsMobileForm',
					defaults : {
						anchor : '98%,98%'
					},
					defaultType : 'textfield',
					items : [{
								name : 'smsMobile.smsId',
								id : 'smsId',
								xtype : 'hidden',
								value : this.smsId == null
										? ''
										: this.smsId
							}, {// 2 row
								xtype : 'container',
								hidden : this.isInner? false: true,
								//anchor : '99%,99%',
								layout : 'hbox',
								layoutConfig : {
									padding : '5',
									align : 'middle'
								},
								items : [{
											style : 'padding-left:0px;',
											xtype : 'displayfield',
											value : '收信人:',
											width : 100
										},{
											xtype : 'textfield',
											//name : 'smsMobile.recipients',
											id : 'recipients',
											width : 350,
											style : 'padding-right:8px;'
										}, {
											xtype : 'button',
											text : '选择',
											iconCls : 'btn-add',
											scope: this,
											handler : this.userSelector
										}]
							}, {// 2 row
								xtype : 'container',
								layout : 'form',
								anchor : '99%,99%',
								hidden : this.isInner? true: false,
								items : [{
									fieldLabel : '收信号码',
									xtype : 'textarea',
									anchor : '99%,99%',
									name : 'smsMobile.phoneNumber',
									id : 'phoneNumber'
								}]
							}, {
								fieldLabel : '发信人',
								name : 'smsMobile.userName',
								id : 'userName',
								value : curUserInfo.fullname
							}, {
								fieldLabel : '短信内容',
								name : 'smsMobile.smsContent',
								id : 'smsContent',
								height:220,
								xtype : 'htmleditor'
							}, {
								name : 'recipientIds',
								xtype : 'hidden',
								id : 'recipientIds'
							}

					]
				});
		// 加载表单对应的数据
		if (this.smsId != null && this.smsId != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath
						+ '/communicate/getSmsMobile.do?smsId='
						+ this.smsId,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
				},
				failure : function(form, action) {
				}
			});
		}
		// 初始化功能按钮
		this.buttons = [{
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
				}];
	},// end of the initcomponents
	/**
	 * 选择用户
	 */
	userSelector:function(){	
			new UserDialog({
		 		scope : this,
		 		isForFlow : false, 
		 		userIds : Ext.getCmp('recipientIds').getValue(), 
		 		userName : Ext.getCmp('recipients').getValue(), 
		 		callback : function(ids, names) {
		  			Ext.getCmp('recipients').setValue(names);
					Ext.getCmp('recipientIds').setValue(ids);
		 		}
		 	}).show();
		},
	// 重置
	reset : function(formPanel) {
		this.formPanel.getForm().reset();
	},
	//取消
	cancel : function() {
		this.close();
	},
	// 保存记录
	save : function() {
		$postForm({
			formPanel : this.formPanel,
			scope : this,
			waitMsg : '正在提交数据...',
			successMsg :  '短信正在发送,请等待接收！',
			url : __ctxPath + '/communicate/saveSmsMobile.do',
			callback : function(fp, action) {
				if(this.callback){
					this.callback.call(this.scope);
				}
				this.close();
			}
		});
	}// end of save
});