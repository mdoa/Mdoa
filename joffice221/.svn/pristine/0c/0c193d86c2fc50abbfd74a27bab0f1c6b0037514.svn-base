Ext.ns('AppRoleForm');
/**
 * @author
 * @createtime
 * @class AppRoleForm
 * @extends Ext.Window
 * @description AppRoleForm表单
 * @company 宏天软件
 */
AppRoleForm = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		AppRoleForm.superclass.constructor.call(this, {
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					id : 'AppRoleFormWin',
					title : '角色详细信息',
					iconCls : 'menu-role',
					width : 370,
					height : 220,
					buttonAlign : 'center',
					buttons : this.buttons
				});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		var x = '<font style="color: red;">*</font>';
		//表单
		this.formPanel = new Ext.FormPanel({
					layout : 'form',
					border:false,
					bodyStyle : 'padding:5px;',
					defaults : {
						anchor : '96%,96%'
					},
					defaultType : 'textfield',
					items : [{
								name : 'appRole.roleId',
								xtype : 'hidden',
								value : this.roleId == null ? '' : this.roleId
							}, {
								fieldLabel : '角色名称'+ x,
								name : 'appRole.roleName',
								allowBlank : false
							}, {
								fieldLabel : '角色描述',
								xtype : 'textarea',
								name : 'appRole.roleDesc'
							}, {
								fieldLabel : '状态',
								hiddenName : 'appRole.status',
								xtype : 'combo',
								mode : 'local',
								editable : true,
								triggerAction : 'all',
								store : [['0', '禁用'], ['1', '可用']],
								value : 1
							}]
				});

		if (this.roleId != null && this.roleId != 'undefined') {
			this.formPanel.loadData({
				url:__ctxPath + '/system/getAppRole.do?roleId=' + this.roleId,
				preName:'appRole',
				root:'data'
			});
		}
		//按钮组
		this.buttons = [{
			text : '保存',
			iconCls : 'btn-save',
			scope:this,
			handler : this.save
		}, {
			text : '取消',
			iconCls : 'btn-cancel',
			scope:this,
			handler : function() {
					this.close();
			}
		}];
	},
	//保存
	save:function(){
		var formPanel = this.formPanel;
		if (this.isCopy == 1) { //可以复制	
			var roleName = formPanel.getCmpByName('appRole.roleName').getValue();
			Ext.Ajax.request({
				url : __ctxPath + '/system/checkAppRole.do',
				params : {
					roleName : roleName
				},
				method : 'post',
				scope : this,
				success : function(response) {
					var result = Ext.util.JSON.decode(response.responseText);
					if (result.success) {
						//保存表单信息
						this.saveForm().createCallback(this);		
					} else {
						Ext.ux.Toast.msg('提示信息', '该角色名字已经存在，请更改！');
					}
				},
				failure : function() {
				}
			});
		} else {
			//保存表单信息
			this.saveForm();
		}

	},//end of save
	//保存表单信息
	saveForm : function(){
		$postForm({
			formPanel : this.formPanel,
			waitMsg : '正在提交数据...',
			scope : this,
			url : __ctxPath + '/system/saveAppRole.do',
			params : {
				isCopy : this.isCopy
			},
			callback : function(fp, action) {
				if (this.callback) {
					this.callback.call(this.scope);
				}
				this.close();
			}
		});
	}
});