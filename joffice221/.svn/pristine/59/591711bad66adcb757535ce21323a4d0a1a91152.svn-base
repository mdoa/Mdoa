/**
 * 文档共享表单
 * 
 */
 
DocumentSharedForm=Ext.extend(Ext.Window,{
    formPanel:null,
	constructor:function(_cfg){
	    Ext.applyIf(this,_cfg);
	    this.initUI();
	    DocumentSharedForm.superclass.constructor.call(this,{
	            title : '文档授权信息',
				width : 545,
				iconCls : 'menu-mail_folder',
				height : 380,
				modal : true,
				layout : 'fit',
				plain : true,
				scope : this,
				minWidth:544,
				buttonAlign : 'center',
				items : this.formPanel,
				buttons:this.buttons
	    
	    });
	},
	initUI:function(){
	    this.formPanel=new Ext.FormPanel({
			bodyStyle : 'padding:4px 10px 4px 10px',
			items : [{
						xtype : 'hidden',
						name : 'document.docId',
						value : this.docId
					}, {
						xtype : 'fieldset',
						border : false,
						layout : 'column',
						items : [{
									xtype : 'label',
									text : '共享人员',
									width : 100
								}, {
									xtype : 'hidden',
									name : 'document.sharedUserIds'
								}, {
									xtype : 'textarea',
									name : 'document.sharedUserNames',
									width : 300
								}, {
									xtype : 'button',
									text : '选择',
									iconCls : 'btn-select',
									width : 80,
									scope : this,
									handler : this.userSelector
								}, {
									xtype : 'button',
									iconCls : 'btn-clear',
									text : '清空',
									scope : this,
									handler : this.userClear,
									width : 80
								}]
					}, {
						xtype : 'fieldset',
						border : false,
						layout : 'column',
						items : [{
									xtype : 'label',
									text : '共享部门',
									width : 100
								}, {
									name : 'document.sharedDepIds',
									xtype : 'hidden'
								}, {
									name : 'document.sharedDepNames',
									xtype : 'textarea',
									width : 300
								}, {
									xtype : 'button',
									text : '选择',
									iconCls : 'btn-select',
									width : 80,
									scope : this,
									handler : this.DepSelector
								}, {
									xtype : 'button',
									text : '清空',
									iconCls : 'btn-clear',
									scope : this,
									handler : this.DepClear,
									width : 80
								}]
					}, {
						xtype : 'fieldset',
						border : false,
						layout : 'column',
						items : [{
									xtype : 'label',
									text : '共享角色',
									width : 100
								}, {
									xtype : 'hidden',
									name : 'document.sharedRoleIds'
								}, {
									name : 'document.sharedRoleNames',
									xtype : 'textarea',
									width : 300
								}, {
									xtype : 'button',
									text : '选择',
									iconCls : 'btn-select',
									width : 80,
									scope : this,
									handler : this.RoleSelect
								}, {
									xtype : 'button',
									text : '清空',
									iconCls : 'btn-clear',
									scope : this,
									handler : this.roleClear,
									width : 80
								}]
					}]
		});
	
		if (this.docId != null && this.docId != '' && this.docId != 'undefined') {
			this.formPanel.loadData({
						deferredRender : false,
						url : __ctxPath + '/document/getDocument.do?docId=' + this.docId,
						root : 'data',
						preName : 'document',
						waitMsg : '正在载入数据...',
						success : function(form, action) {
	
						},
						failure : function(form, action) {
							Ext.MessageBox.show({
										title : '操作信息',
										msg : '载入信息失败，请联系管理员！',
										buttons : Ext.MessageBox.OK,
										icon : 'ext-mb-error'
									});
						}
					});
		}
		
		
		this.buttons=[{
					xtype : 'button',
					text : '共享',
					scope:this,
					iconCls : 'btn-ok',
					scope : this,
					handler : this.share
				},{
				    xtype : 'button',
					text : '取消共享',
					scope:this,
					iconCls : 'btn-cancel',
					scope : this,
					handler : this.cancelShare
				},{
					xtype : 'button',
					iconCls : 'btn-cancel',
					text : '关闭',
					scope:this,
					handler : function() {
						this.close();
					}
				}];
	
	},
	// 显示选择器，并且设置用户
 	userSelector : function() {
 		var sharedUserIds = this.formPanel.getCmpByName('document.sharedUserIds');
		var sharedUserNames = this.formPanel.getCmpByName('document.sharedUserNames');
		new UserDialog({
			single : false,
			scope : this,
			userIds : sharedUserIds.getValue(),
			userName : sharedUserNames.getValue(),
			callback :function(uIds, fnames) {
						sharedUserIds.setValue(','+ uIds + ',');
						sharedUserNames.setValue(fnames);
				}
		}).show();
	},
	//清空用户
	userClear : function() {
		var sharedUserIds = this.formPanel.getCmpByName('document.sharedUserIds');
		var sharedUserNames = this.formPanel.getCmpByName('document.sharedUserNames');
		sharedUserIds.setValue('');
		sharedUserNames.setValue('');
	},
	//显示选择器，并且设置部门
	DepSelector : function() {
		var sharedDepIds = this.formPanel.getCmpByName('document.sharedDepIds');
		var sharedDepNames =  this.formPanel.getCmpByName('document.sharedDepNames');
		new DepDialog({
			sigle : false,
			scope : this,
			depIds : sharedDepIds.getValue(),
			depNames : sharedDepNames.getValue(),
			callback : function(ids, names) {
					sharedDepIds.setValue(',' + ids+ ',');
					sharedDepNames.setValue(names);
			}
		}).show();
	},
	//清除部门
	DepClear : function() {
		var sharedDepIds =  this.formPanel.getCmpByName('document.sharedDepIds');
		var sharedDepNames =  this.formPanel.getCmpByName('document.sharedDepNames');
		sharedDepIds.setValue('');
		sharedDepNames.setValue('');
	},
	//显示选择器，并且设置角色
	RoleSelect : function() {
		var sharedRoleIds = this.formPanel.getCmpByName('document.sharedRoleIds');
		var sharedRoleNames = this.formPanel.getCmpByName('document.sharedRoleNames');
		new RoleDialog({
			single : false,
			roleIds : sharedRoleIds.getValue(),
			roleName : sharedRoleNames.getValue(),
			scope : this,
			callback : function(ids, names) {
				sharedRoleIds.setValue("");
				sharedRoleNames.setValue("");
				sharedRoleIds.setValue(',' + ids+ ',');
				sharedRoleNames.setValue(names);
			}
		}).show();
	},
	//清除角色
	roleClear : function() {
		var sharedRoleIds = this.formPanel.getCmpByName('document.sharedRoleIds');
		var sharedRoleNames = this.formPanel.getCmpByName('document.sharedRoleNames');
		sharedRoleIds.setValue('');
		sharedRoleNames.setValue('');
	},
	//共享
	share : function() {
		this.formPanel.getForm().submit({
			url : __ctxPath
					+ '/document/shareDocument.do',
			method : 'post',
			scope : this,
			waitMsg : '正在提交...',
			success : function(fp, action) {
				if(this.callback){
				   this.callback.call(this.scope);
				}
				this.close();
			}
		});
	},
	//取消共享
	cancelShare : function() {
		this.formPanel.getForm().submit({
					url : __ctxPath+ '/document/unshareDocument.do',
					method : 'post',
					waitMsg : '正在提交...',
					scope : this,
					success : function(fp, action) {
						if(this.callback){
						   this.callback.call(this.scope);
						}
						this.close();
					}
				});
	}
});
