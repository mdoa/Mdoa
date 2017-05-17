/**
 * 文件夹授权
 * 
 * @class DocFolderSharedForm
 * @extends Ext.Window
 */
DocFolderSharedForm = Ext.extend(Ext.Window, {
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		this.initUI();
		DocFolderSharedForm.superclass.constructor.call(this, {
					title : '文件夹授权',
					iconCls : 'menu-public-fol',
					width : 550,
					height : 340,
					modal : true,
					layout : 'fit',
					buttonAlign : 'center',
					items : [this.formPanel],
					buttons : this.buttons

				});
	},
	initUI : function() {
		// 表单面板
		this.formPanel = new Ext.FormPanel({
					layout : 'form',
					bodyStyle : 'padding:10px',
					border : false,
					autoScroll : true,
					items : [{
								xtype : 'hidden',
								name : 'privilegeId'
							}, {
								xtype : 'hidden',
								name : 'folderId',
								value : this.folderId
							}, {
								xtype : 'container',
								layout : 'column',
								fieldLabel : '共享人员',
								items : [{
											xtype : 'hidden',
											name : 'userIds'
										}, {
											xtype : 'textarea',
											name : 'userNames',
											width : 300
										}, {
											width : 75,
											layout : 'form',
											defaultType : 'button',
											border : false,
											items : [{
														iconCls : 'btn-select',
														text : '选择',
														scope : this,
														handler : this.userSelector
													}, {
														text : '清空',
														iconCls : 'reset',
														scope : this,
														handler : this.userClear
													}]
										}]
							}, {
								xtype : 'container',
								layout : 'column',
								fieldLabel : '共享部门',
								items : [{
											name : 'depIds',
											xtype : 'hidden'
										}, {
											name : 'depNames',
											xtype : 'textarea',
											width : 300
										}, {
											width : 75,
											layout : 'form',
											defaultType : 'button',
											border : false,
											items : [{
														iconCls : 'btn-select',
														text : '选择',
														scope : this,
														handler : this.depDialog
													}, {
														text : '清空',
														iconCls : 'reset',
														scope : this,
														handler : this.depClear
													}]
										}]
							}, {
								xtype : 'container',
								layout : 'column',
								fieldLabel : '共享角色',
								items : [{
											xtype : 'hidden',
											name : 'roleIds'
										}, {
											name : 'roleNames',
											xtype : 'textarea',
											width : 300
										},{
											width : 75,
											layout : 'form',
											defaultType : 'button',
											border : false,
											items : [{
														iconCls : 'btn-select',
														text : '选择',
														scope : this,
														handler : this.roleSelector
													}, {
														text : '清空',
														iconCls : 'reset',
														scope : this,
														handler : this.clearRole
													}]
										}]
							}, {
								xtype : 'container',
								layout : 'column',
								fieldLabel : '权限选择',
								items : [{
											xtype : 'checkbox',
											name : 'rightR',
											checked : true
										}, {
											xtype : 'label',
											text : '可读',
											width : 60
										}, {
											xtype : 'checkbox',
											name : 'rightU',
											listeners : {
												scope : this,
												"check" : this.checkRightU
														.createDelegate(this)
											}
										}, {
											xtype : 'label',
											text : '可修改',
											width : 60
										}, {
											xtype : 'checkbox',
											name : 'rightD',
											listeners : {
												scope : this,
												"check" : this.checkRightD
														.createDelegate(this)
											}

										}, {
											xtype : 'label',
											text : '可删除',
											width : 60
										}]

							}]
				});

		this.buttons = [{
					xtype : 'button',
					iconCls : 'btn-ok',
					text : '共享',
					scope : this,
					handler : this.share
				}, {
					xtype : 'button',
					iconCls : 'btn-cancel',
					text : '关闭',
					scope : this,
					handler : function() {
						this.close();
					}
				}];
	},
	/**
	 * 设置值
	 * 
	 * @param {}
	 *            name 设置的name
	 * @param {}
	 *            namesValue 设置的值
	 */
	setNameValue : function(name, namesValue) {
		var names = this.formPanel.getCmpByName(name);
		if (names.getValue() == '') {// 若原没有值，则直接设置
			names.setValue(namesValue);
			return;
		}
		// 去掉重复的人员
		var vnames = names.getValue().split(',');
		names.setValue(uniqueArray(vnames.concat(namesValue.split(','))));
	},
	clearValue : function(name) {
		this.formPanel.getCmpByName(name).setValue('');
	},
	/**
	 * 用户选择
	 */
	userSelector : function() {
		// 显示选择器，并且设置用户
		new UserDialog({
					single : false,
					isForFlow : false,
					scope : this,
					callback : function(ids, names) {
						this.setNameValue('userIds', ids);
						this.setNameValue('userNames', names);
					}
				}).show();
	},
	/**
	 * 用户清空
	 */
	userClear : function() {
		this.clearValue('userIds');
		this.clearValue('userNames');
	},
	/**
	 * 部门选择
	 */
	depDialog : function() {
		new DepDialog({
					// depIds:depIds,
					// depNames:depNames,
					scope : this,
					callback : function(ids, names) {
						this.setNameValue('depIds', ids);
						this.setNameValue('depNames', names);
					}
				}).show();
	},
	/**
	 * 部门清空
	 */
	depClear : function() {
		this.clearValue('depIds');
		this.clearValue('depNames');
	},
	/**
	 * 角色选择
	 */
	roleSelector : function() {
		var uids = this.formPanel.getCmpByName("roleIds").getValue();
		var unames = this.formPanel.getCmpByName("roleNames").getValue();
		new RoleDialog({
					scope : this,
					single : false,
					roleIds : uids,
					roleName : unames,
					callback : function(ids, names) {
						this.setNameValue('roleIds', ids);
						this.setNameValue('roleNames', names);
					}
				}).show();
	},
	/**
	 * 清空角色
	 */
	clearRole : function() {
		this.clearValue('roleIds');
		this.clearValue('roleNames');
	},
	/**
	 * 选择可修改
	 */
	checkRightU : function() {
		var rightU = this.formPanel.getCmpByName('rightU');
		var rightD = this.formPanel.getCmpByName('rightD');
		var rightR = this.formPanel.getCmpByName('rightR');
		if (rightU.getValue()) {
			rightR.setValue(true);
			rightR.disable();
		} else if (!rightD.getValue()) {
			rightR.enable();
		}
	},
	/**
	 * 选择可删除
	 */
	checkRightD : function() {
		var rightU = this.formPanel.getCmpByName('rightU');
		var rightD = this.formPanel.getCmpByName('rightD');
		var rightR = this.formPanel.getCmpByName('rightR');
		if (rightD.getValue()) {
			rightR.setValue(true);
			rightR.disable();
		} else if (!rightU.getValue()) {
			rightR.enable();
		}
	},
	/**
	 * 共享
	 */
	share : function() {
		var userIds = this.formPanel.getCmpByName('userIds').getValue();
		var depIds = this.formPanel.getCmpByName('depIds').getValue();
		var roleIds = this.formPanel.getCmpByName('roleIds').getValue();
		var rightU = this.formPanel.getCmpByName('rightU').getValue();
		var rightD = this.formPanel.getCmpByName('rightD').getValue();
		var rightR = this.formPanel.getCmpByName('rightR').getValue();
		if (userIds != '' || depIds != '' || roleIds != '') {
			if (rightR == true) {
				this.formPanel.getCmpByName('rightR').enable();
				$postForm({
							formPanel : this.formPanel,
							waitMsg : '正在提交数据...',
							scope : this,
							url : __ctxPath + '/document/addDocPrivilege.do',
							callback : function(fp, action) {
								if (this.callback) {
									this.callback.call(this.scope);
								}
								this.close();
							}
						});

			} else {
				Ext.ux.Toast.msg('提示', '读权限为基本权限！');
			}
		} else {
			Ext.ux.Toast.msg('提示', '请选择共享人员或部门或角色！');
		}
	}

});