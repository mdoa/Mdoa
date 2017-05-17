Ext.ns('ConfApplyForm');
/**
 * @description 会议审核信息，添加会议审核描述
 * @company www.jee-soft.cn
 */
ConfApplyForm = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		// 调用父类构造
		ConfApplyForm.superclass.constructor.call(this, {
					layout : 'fit',
					id : 'ConfApplyFormWin',
					title : '审核信息',
					iconCls : 'menu-waitCheckConf',
					width : 350,
					height : 200,
					minWidth : 350,
					minHeight : 200,
					items : this.formPanel,
					plain : true,
					border : false,
					modal : true,
					buttonAlign : 'center',
					keys : {
						key : Ext.EventObject.ENTER,
						fn : this.agree,
						scope : this
					},
					buttons : [ {
						text : '同意',
						iconCls : 'btn-confApply-yes',
						scope : this,
						handler : this.agree
					}, {
						text : '不同意',
						iconCls : 'btn-confApply-no',
						scope : this,
						handler : this.unAgree
					}, {
						text : '重置',
						iconCls : 'btn-reset',
						scope : this,
						handler : this.reset
					}, {
						text : '取消',
						iconCls : 'btn-cancel',
						handler : this.close,
						scope : this
					} ]
				});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		//表单
		this.formPanel = new Ext.FormPanel({
			        region : 'center',
					layout : 'form',
					frame : false,
					style : 'padding:5px 5px 5px 5px;',
					defaultType : 'textfield',
					items : [{
						        xtype : 'hidden',
								name : 'confId',
								value : this.confId == null? '': this.confId
							},{
								style : 'margin-top : 5px;',
								name : 'confTopic',
								fieldLabel : '会议标题',
								width : 200,
								readOnly : true,
								value : this.confTopic
							}, {
								name : 'checkReason',
								fieldLabel : '审核描述',
								width : 200,
								height : 80,
								xtype : 'textarea',
								maxLength : 512,
								maxLengthText : '会议审核描述不能超过512个字符！'
							}
					]
				});// end of the formPanel
	},// end of the initUIComponents
	/**
	 * 重置
	 * 
	 * @param {}
	 * formPanel
	 */
	reset : function() {
		this.formPanel.getCmpByName('checkReason').setValue('');
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
	 * 审核通过[首先判断会议室是否处于空闲状态，方可通过审核]
	 */
	agree : function() {
		var formPanel = this.formPanel;
		var me = this;
		if (formPanel.getForm().isValid()) {
			formPanel.getForm().submit( {
				url : __ctxPath + '/admin/applyConference.do?status=1',
				method : 'post',
				waitMsg : '数据正在提交，请稍候...',
				success : function() {
					Ext.ux.Toast.msg('操作提示', '恭喜您,会议审核成功！');
					me.close();
					Ext.getCmp('waitCheckConfGrid').getStore().reload();
				},
				failure : function(fm, action) {
					var res = Ext.util.JSON.decode(action.response.responseText);
					if(res.msg == null)
						res ='会议审核异常！';
					Ext.MessageBox.show( {
						title : '操作信息',
						msg : res.msg,
						buttons : Ext.MessageBox.OK,
						icon : 'ext-mb-error'
					});
					me.close();
					Ext.getCmp('waitCheckConfGrid').getStore().reload();
				}
			});
		}
	},

	/**
	 * 未通过
	 */
	/*unAgree : function() {
		var formPanel = this.formPanel;
		var me = this;
		if (formPanel.getForm().isValid()) {
			formPanel.getForm().submit( {
				url : __ctxPath + '/admin/applyConference.do?status=2',
				method : 'post',
				waitMsg : '数据正在提交，请稍候...',
				success : function() {
					Ext.ux.Toast.msg('操作提示', '恭喜您,会议审核成功！');
					Ext.getCmp('waitCheckConfGrid').getStore().reload({
						params : {
							limit : 25,
							start : 0
						}
					});
					me.close();
				},
				failure : function(fm, action) {
					Ext.ux.Toast.msg('操作提示', '对不起，会议审核未通过失败！');
					formPanel.getCmpByName('checkReason').setValue(
					'');
				}
			});
		}
	}*/
	unAgree : function() {
		var formPanel = this.formPanel;
		var me = this;
		if (formPanel.getForm().isValid()) {
			formPanel.getForm().submit( {
				url : __ctxPath + '/admin/applyConference.do?status=2',
				method : 'post',
				waitMsg : '数据正在提交，请稍候...',
				success : function() {
					Ext.ux.Toast.msg('操作提示', '恭喜您,会议审核成功！');
					Ext.getCmp('waitCheckConfGrid').getStore().reload();
					me.close();
				},
				failure : function(fm, action) {
					Ext.ux.Toast.msg('操作提示', '对不起，会议审核未通过失败！');
					formPanel.getCmpByName('checkReason').setValue('');
				}
			});
		}
	}

});


