Ext.ns('ProDefRightsForm');
/**
 * @author
 * @createtime
 * @class ProDefRightsForm
 * @extends Ext.Window
 * @description 流程权限详细信息
 * @company 宏天软件
 */
ProDefRightsForm = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		// 必须先初始化组件
		this.initUIComponents();
		ProDefRightsForm.superclass.constructor.call(this, {
					id : 'ProDefRightsFormWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					height : 300,
					width : 500,
					maximizable : true,
					title : '流程权限详细信息',
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
								handler : this.cancel
							}]
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
								name : 'proDefRights.rightsId',
								xtype : 'hidden',
								value : this.rightsId == null
										? ''
										: this.rightsId
							}, {
								name : 'proDefRights.globalType.proTypeId',
								xtype : 'hidden',
								value : this.proTypeId ? this.proTypeId : null
							}, {
								name : 'proDefRights.proDefinition.defId',
								xtype : 'hidden',
								value : this.defId ? this.defId : null
							}, {
								name : 'proDefRights.userIds',
								xtype : 'hidden'
							}, {
								name : 'proDefRights.roleIds',
								xtype : 'hidden'
							}, {
								name : 'proDefRights.depIds',
								xtype : 'hidden'
							}, {
								xtype : 'compositefield',
								fieldLabel : '用户权限',
								items : [{
											width : 255,
											name : 'proDefRights.userNames',
											xtype : 'textarea',
											readOnly : true,
											maxLength : 2000
										}, {
											layout : 'form',
											border : false,
											items : [{
														xtype : 'button',
														iconCls : 'btn-select',
														text : '选择人员',
														scope : this,
														handler : this.selectUser
													}, {
														xtype : 'button',
														iconCls : 'btn-cancel',
														text : '清除人员',
														scope : this,
														handler : this.cancelUser
													}]
										}]
							}, {
								xtype : 'compositefield',
								fieldLabel : '角色权限',
								items : [{
											width : 255,
											name : 'proDefRights.roleNames',
											xtype : 'textarea',
											readOnly : true,
											maxLength : 2000
										}, {
											layout : 'form',
											border : false,
											items : [{
														xtype : 'button',
														iconCls : 'btn-select',
														text : '选择角色',
														scope : this,
														handler : this.selectRole
													}, {
														xtype : 'button',
														iconCls : 'btn-cancel',
														text : '清除角色',
														scope : this,
														handler : this.cancelRole
													}]
										}]
							}, {
								xtype : 'compositefield',
								fieldLabel : '部门权限',
								items : [{
											width : 255,
											name : 'proDefRights.depNames',
											xtype : 'textarea',
											readOnly : true,
											maxLength : 2000
										}, {
											layout : 'form',
											border : false,
											items : [{
														xtype : 'button',
														iconCls : 'btn-select',
														text : '选择部门',
														scope : this,
														handler : this.selectDep
													}, {
														xtype : 'button',
														iconCls : 'btn-cancel',
														text : '清除部门',
														scope : this,
														handler : this.cancelDep
													}]
										}]
							}]
				});
		// 加载表单对应的数据
		if (this.proTypeId != null && this.proTypeId != 'undefined') {
			this.formPanel.loadData({
						url : __ctxPath + '/flow/getProDefRights.do?proTypeId='
								+ this.proTypeId,
						root : 'data',
						preName : 'proDefRights'
					});
		}

		// 加载表单对应的数据
		if (this.defId != null && this.defId != 'undefined') {
			this.formPanel.loadData({
						url : __ctxPath + '/flow/getProDefRights.do?defId='
								+ this.defId,
						root : 'data',
						preName : 'proDefRights'
					});
		}
	},// end of the initcomponents

	// /**
	// * 重置
	// *
	// * @param {}
	// * formPanel
	// */
	// reset : function() {
	// this.formPanel.getForm().reset();
	// },
	/**
	 * 取消
	 * 
	 * @param {}
	 *            window
	 */
	cancel : function() {
		this.close();
	},
	/**
	 * 保存记录
	 */
	save : function() {
		$postForm({
					formPanel : this.formPanel,
					scope : this,
					url : __ctxPath + '/flow/saveProDefRights.do',
					callback : function(fp, action) {
						this.close();
					}
				});
	},// end of save
	// 选择人员
	selectUser : function() {
		var formPanel = this.formPanel;
		var userIds = formPanel.getCmpByName('proDefRights.userIds');
		var userNames = formPanel.getCmpByName('proDefRights.userNames');
		new UserDialog({
					isForFlow : false,
					single : false,
					userIds : userIds.getValue(),
					userName : userNames.getValue(),
					scope : this,// 当前作用域
					callback : function(ids, names) {
						userIds.setValue(ids);
						userNames.setValue(names);
					}
				}).show();
	},
	// 清除人员
	cancelUser : function() {
		var formPanel = this.formPanel;
		formPanel.getCmpByName('proDefRights.userNames').setValue('');
		formPanel.getCmpByName('proDefRights.userIds').setValue('');
	},
	// 选择角色
	selectRole : function() {
		var formPanel = this.formPanel;
		var roleId = formPanel.getCmpByName('proDefRights.roleIds');
		var roleName = formPanel.getCmpByName('proDefRights.roleNames');
		new RoleDialog({
					single : false,
					scope : this,
					roleIds : roleId.getValue(),
					roleName : roleName.getValue(),
					callback : function(roleIds, roleNames) {
						roleId.setValue(roleIds);
						roleName.setValue(roleNames);
					}
				}).show();
	},
	// 清除角色
	cancelRole : function() {
		fPanel = this.formPanel;
		fPanel.getCmpByName('proDefRights.roleNames').setValue('');
		fPanel.getCmpByName('proDefRights.roleIds').setValue('');
	},
	// 选择部门
	selectDep : function() {
		fPanel = this.formPanel;
		var depName = fPanel.getCmpByName('proDefRights.depNames');
		var depId = fPanel.getCmpByName('proDefRights.depIds');
		new DepDialog({
					single : true,
					scope : this,
					depIds : depId.getValue(),
					depNames : depName.getValue(),
					callback : function(depIds, fullnames) {
						depName.setValue(fullnames);
						depId.setValue(depIds);
					}
				}).show();
	},
	// 清除部门
	cancelDep : function() {
		fPanel = this.formPanel;
		fPanel.getCmpByName('proDefRights.depNames').setValue('');
		fPanel.getCmpByName('proDefRights.depIds').setValue('');
	}

});