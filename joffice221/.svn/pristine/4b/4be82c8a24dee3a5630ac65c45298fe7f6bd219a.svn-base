/**
 * 
 * @class TaskHandlerWindow
 * @extends Ext.Window
 */
TaskHandlerWindow = Ext.extend(Ext.Window, {
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		this.initUIs();
		TaskHandlerWindow.superclass.constructor.call(this, {
					title : '为任务节点更改执行人',
					height : 120,
					width : 350,
					layout : 'fit',
					modal : true,
					items : this.formPanel,
					buttonAlign : 'center',
					buttons : [{
								text : '保存',
								iconCls : 'btn-save',
								scope : this,
								handler : this.save
							}, {
								text : '关闭',
								iconCls : 'btn-cancel',
								scope : this,
								handler : this.close
							}]
				});
	},
	// 初始化UI
	initUIs : function() {
		this.formPanel = new Ext.form.FormPanel({
					layout : 'form',
					modal : true,
					border : false,
					bodyStyle : 'padding:4px',
					items : [{
								xtype : 'hidden',
								name : 'userId'
							}, {
								xtype : 'compositefield',
								fieldLabel : '指定人员',
								items : [{
											xtype : 'textfield',
											name : 'userName',
											allowBlank : false,
											anchor : '98%,98%'
										}, {
											xtype : 'button',
											text : '选择待办人员',
											scope : this,
											handler : this.selectUser
										}]
							}]
				});
	},
	//选择
	selectUser : function() {
		var userId = this.getCmpByName('userId');
		var userName = this.getCmpByName('userName');

		new UserDialog({
		 		scope : this,
		 		isForFlow : false, 
		 		userIds : userId.getValue(), 
		 		userName : userName.getValue(), 
		 		callback : function(ids, names) {
		 			userId.setValue(ids);
					userName.setValue(names);
		 		}
		 	}).show();
	},
	//保存
	save : function() {
		var taskGrid = this.taskGrid;
		var rs = taskGrid.getSelectionModel().getSelections();
		var taskIds = '';
		for (var i = 0; i < rs.length; i++) {
			if (i > 0)
				taskIds += ',';
			taskIds += rs[i].data.taskId;
		}

		if (this.formPanel.getForm().isValid()) {
			this.formPanel.getForm().submit({
						scope : this,
						params : {
							taskIds : taskIds
						},
						url : __ctxPath + '/flow/handlerTask.do',
						method : 'POST',
						success : function(fp, action) {
							Ext.ux.Toast.msg('操作信息', '成功更改待办人员!');
							taskGrid.getStore().reload();
							this.close();
						},
						failure : function(fp, action) {
							Ext.ux.Toast.msg('操作信息', '操作失败，请联系管理员!');
						}
					});
		}
	}
});
