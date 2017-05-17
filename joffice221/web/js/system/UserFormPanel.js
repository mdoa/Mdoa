Ext.ns('UserFormPanel');
/**
 * 用户信息编辑
 * 
 * @class UserFormPanel
 * @extends Ext.FormPanel
 * @description 用于窗口的信息编辑管理
 */
UserFormPanel = Ext.extend(Ext.FormPanel, {
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		this.userId = this.userId ? this.userId : '';
		this.initUI();
		UserFormPanel.superclass.constructor.call(this, {
					id : 'UserFormPanel_' + this.userId,
					iconCls : 'add-user',
					title : (this.username ? this.username : '') + '员工信息',
					autoScroll : true,
					width : 1000,
					tbar : [{
								text : '保存',
								iconCls : 'btn-save',
								scope : this,
								handler : this.save
							}, {
								text : '重设密码',
								iconCls : 'btn-password',
								hidden : Ext.isEmpty(this.userId)
										? true
										: false,
								scope : this,
								handler : this.setPassword
							}],
					layout : 'form',
					items : [this.tabs]
				});
	},

	// 初始化UI
	initUI : function() {
		var x = '<font style="color: red;">*</font>';
		// 用户相片
		this.photoPanel = new Ext.Panel({
					tbar : [{
								text : '上传',
								iconCls : 'btn-upload',
								scope : this,
								handler : this.uploadPhoto
							}],
					title : '个人照片',
					width : 230,
					height : 500,
					html : '<img src="' + __ctxPath
							+ '/images/default_image_male.jpg"/>'
				});
		// 基本信息
		this.requiredPanel = new Ext.Panel({
					title : '基本信息',
					width : ' 100%',
					height : 500,
					layout : 'form',
					defaultType : 'textfield',
					bodyStyle : 'padding:6px',
					defaults : {
						anchor : '50%,98%'
					},
					items : [{
								xtype : 'hidden',
								name : 'appUser.userId'
							}, {
								fieldLabel : '登录账号'+ x,
								name : 'appUser.username',
								allowBlank : false
							}, {
								fieldLabel : '员工姓名'+ x,
								name : 'appUser.fullname',
								allowBlank : false
							}, {
								fieldLabel : '性别',
								xtype : 'combo',
								hiddenName : 'appUser.title',
								mode : 'local',
								editable : false,
								triggerAction : 'all',
								store : [['1', '先生'], ['0', '女士']],
								value : '1',
								listeners : {
									scope : this,
									'select' : this.selectedTitle
								}
							}, {
								fieldLabel : 'E-mail'+ x,
								name : 'appUser.email',
								vtype : 'email',
								allowBlank : false,
								blankText : '邮箱不能为空!',
								vtypeText : '邮箱格式不正确!'
							}, {
								fieldLabel : '入职时间'+ x,
								xtype : 'datefield',
								format : 'Y-m-d',
								name : 'appUser.accessionTime',
								allowBlank : false,
								length : 50
							}, {
								fieldLabel : '是否可用',
								hiddenName : 'appUser.status',
								xtype : 'combo',
								mode : 'local',
								editable : false,
								triggerAction : 'all',
								store : [['1', '激活'], ['0', '禁用']],
								value : 1
							}, {
								fieldLabel : '家庭电话',
								name : 'appUser.phone'
							}, {
								fieldLabel : '移动电话',
								xtype : 'numberfield',
								name : 'appUser.mobile'
							}, {
								fieldLabel : '传真',
								name : 'appUser.fax'
							}, {
								fieldLabel : '家庭住址',
								name : 'appUser.address'
							}, {
								fieldLabel : '邮编',
								xtype : 'numberfield',
								name : 'appUser.zip'
							}, {
								xtype : 'hidden',
								name : 'appUser.photo'
							}]
				});

		/**
		 * 可选择的部门树
		 */
		this.depTreePanel = new htsoft.ux.TreePanelEditor({
					title : '部门(双击选择)',
					url : __ctxPath + '/system/treeOrganization.do?demId=1',// 1代表行政维度
					scope : this,
					autoScroll : true,
					showContextMenu : false,
					height : 500,
					columnWidth:0.2,
					dblclick : this.depTreePanelDblClick
				});
		/**
		 * 已选部门
		 */
		this.depGridPanel = new HT.EditorGridPanel({
			clicksToEdit : 1,
			isShowTbar : false,
			showPaging : false,
			title : '已选部门',
			columnWidth:0.79,
			height : 500,
			url : __ctxPath + '/system/findUserOrg.do?userId=' + this.userId,
			fields : ['userOrgId', 'orgId', 'orgName', 'isPrimary', 'isCharge'],
			columns : [{
						header : '部门名',
						dataIndex : 'orgName'
					}, {
						header : '是否主部门',
						dataIndex : 'isPrimary',
						renderer : function(val) {
							return val == 1 ? '是' : '否';
						},
						editor : {
							id : 'orgPrimary',
							xtype : 'combo',
							mode : 'local',
							editable : false,
							triggerAction : 'all',
							store : [['1', '是'], ['0', '否']],
							value : 0,
							listeners : {
								scope : this,
								'select' : this.setDepPrimary
							}
						}
					}, {
						header : '是否主要负责人',
						dataIndex : 'isCharge',
						hidden : true
					}],
			listeners : {
				scope : this,
				'rowdblclick' : this.depGridPanelDblClick,
				'cellclick' : function(grid, rowIndex, columnIndex, e) {
					this.depGridPanelIdx = rowIndex;
				}
			}
		});

		this.roleForSelPanel = new HT.GridPanel({
					width : 230,
					height : 500,
					isShowTbar : false,
					showPaging : false,
					autoScroll : true,
					title : '可选角色(双击选择)',
					url : __ctxPath + '/system/getRolesAppUser.do?userId='
							+ this.userId,
					fields : ['roleId', 'roleName'],
					columns : [{
								header : '角色名',
								dataIndex : 'roleName'
							}],
					listeners : {
						scope : this,
						'rowdblclick' : this.roleForSelPanelDblClick
					}
				});

		this.roleSelectedPanel = new HT.GridPanel({
					width : '100%',
					height : 500,
					isShowTbar : false,
					showPaging : false,
					autoScroll : true,
					title : '已选角色(双击删除)',
					url : __ctxPath + '/system/getSelRolesAppUser.do?userId='
							+ this.userId,
					fields : ['roleId', 'roleName'],
					columns : [{
								header : '角色名',
								dataIndex : 'roleName'
							}],
					listeners : {
						scope : this,
						'rowdblclick' : this.roleSelectedPanelDblClick
					}
				});

		/**
		 * 可选岗位数
		 */
		this.posTreePanel = new htsoft.ux.TreePanelEditor({
					height : 500,
					columnWidth:0.2,
					title : '岗位(双击添加)',
					url : __ctxPath + '/system/treePosition.do',
					scope : this,
					autoScroll : true,
					dblclick : this.posTreePanelDblClick
				});
		/**
		 * 已选岗位
		 */
		this.posGridPanel = new HT.EditorGridPanel({
					id : 'posGridPanel',
					clicksToEdit : 1,
					title : '已选岗位(双击删除)',
					height : 500,
					columnWidth:0.79,
					isShowTbar : false,
					showPaging : false,
					url : __ctxPath + '/system/findUserPosition.do?userId='
							+ this.userId,
					fields : ['userPosId', 'posId', 'posName', 'isPrimary'],
					columns : [{
								header : '岗位名称',
								dataIndex : 'posName'
							}, {
								header : '是否主岗位',
								dataIndex : 'isPrimary',
								renderer : function(val) {
									return val == 1 ? '是' : '否';
								},
								editor : {
									xtype : 'combo',
									mode : 'local',
									editable : false,
									triggerAction : 'all',
									store : [['1', '是'], ['0', '否']],
									value : 0,
									listeners : {
										select : this.setPosPrimary,
										scope : this
									}
								}

							}],
					listeners : {
						scope : this,
						'rowdblclick' : this.posGridPanelDblClick
					}
				});
		/**
		 * 部门选择Panel
		 */
		this.depPanel = new HT.HBoxPanel({
					title : '部门选择',
					align : 'stretch ',
					pack : 'start',
					layout: 'column', 
					items : [this.depTreePanel,{columnWidth:0.005},this.depGridPanel]
				});
		/**
		 * 角色选择Panel
		 */
		this.rolePanel = new HT.HBoxPanel({
					title : '角色选择',
					pack : 'start',
					items : [this.roleForSelPanel, this.roleSelectedPanel]
				});

		/**
		 * 职位Panel
		 */
		this.posPanel = new HT.HBoxPanel({
					title : '岗位选择',
					pack : 'start',
					layout: 'column', 
					items : [this.posTreePanel,{columnWidth:0.005},this.posGridPanel]
				});

		/**
		 * 创建基本信息的panel
		 */
		this.basePanel = new HT.HBoxPanel({
					title : '基本信息',
					pack : 'start',
					items : [this.photoPanel, this.requiredPanel]
				});

		// 初始化加载数据
		if (!Ext.isEmpty(this.userId)) {
			this.requiredPanel.loadData({
						url : __ctxPath + '/system/loadAppUser.do?userId='
								+ this.userId,
						root : 'data',
						preName : 'appUser',
						scope : this,
						success : function(resp, options) {
							var photo = this.getCmpByName('appUser.photo')
									.getValue();
							if (photo != '' && photo != null) {
								this.photoPanel.body.update('<img src="'
										+ __ctxPath + '/attachFiles/' + photo
										+ '"  width="100%" height="100%"/>');
							}
						}
					});
		} else { // 可以设置密码
			this.requiredPanel.insert(2, new Ext.form.TextField({
								fieldLabel : '登录密码'+ x,
								id : "appUserPassword",
								name : 'appUser.password',
								inputType : 'password',
								allowBlank : false
							}));
			this.requiredPanel.insert(3, new Ext.form.TextField({
								fieldLabel : '确认登录密码'+ x,
								name : 'pass-cfrm',
								inputType : 'password',
								vtype : 'password',
								initialPassField : 'appUserPassword',
								allowBlank : false
							}));
			this.requiredPanel.doLayout();
		};
		this.tabs = new Ext.TabPanel({
					activeTab : 0,
					height : '100%',
					border : false,
					items : [this.basePanel, this.posPanel, this.depPanel,
							this.rolePanel]
				});
	},
	// ==================functions start===============
	/**
	 * 上传图片
	 */
	uploadPhoto : function() {
		App.createUploadDialog({
			file_cat : 'system/appUser',
			scope : this,
			callback : function(data) {
				var photo = this.requiredPanel.getCmpByName('appUser.photo');
				photo.setValue(data[0].filePath);
				this.photoPanel.body.update('<img src="' + __ctxPath
						+ '/attachFiles/' + data[0].filePath
						+ '"  width="100%" height="100%"/>');
			},
			permitted_extensions : ['jpg', 'png', 'bmp', 'gif']
		}).show();
	},

	/**
	 * 选择Title时，进行图片切换
	 * 
	 * @param {}
	 *            combo
	 * @param {}
	 *            record
	 * @param {}
	 *            index
	 */
	selectedTitle : function(combo, record, index) {
		if (combo.value == '0') {
			this.photoPanel.body.update('<img src="' + __ctxPath
					+ '/images/default_image_female.jpg"/>');
		} else {
			this.photoPanel.body.update('<img src="' + __ctxPath
					+ '/images/default_image_male.jpg"/>');
		}
	},
	/**
	 * 双击已选择的部门
	 * 
	 * @param {}
	 *            grid
	 * @param {}
	 *            rowIndex
	 * @param {}
	 *            e
	 */
	posGridPanelDblClick : function(grid, rowIndex, e) {
		var userPosId = grid.getStore().getAt(rowIndex).data.userPosId;
		if (userPosId) {
			Ext.Ajax.request({
						url : __ctxPath
								+ '/system/delUserPosition.do?userPosId='
								+ userPosId,
						method : 'POST',
						scope : this,
						success : function(response, options) {
							Ext.ux.Toast.msg('操作', '成功删除所选择岗位！');
							this.posGridPanel.getStore().removeAt(rowIndex);
						}
					});
		} else {
			this.posGridPanel.getStore().removeAt(rowIndex);
		}
	},

	/**
	 * 双击职位树
	 * 
	 * @param {}
	 *            node
	 */
	posTreePanelDblClick : function(node) {
		if (node.id == 0)
			return;
		var store = this.posGridPanel.getStore();
		for (var i = 0; i < store.getCount(); i++) {
			if (store.getAt(i).data.posId == node.id) {
				return;
			}
		}
		var recordType = store.recordType;
		store.add(new recordType({
					posId : node.id,
					posName : node.text,
					isPrimary : store.getCount() > 0 ? 0 : 1
				}));
	},

	/**
	 * 删除已选择的角色
	 * 
	 * @param {}
	 *            grid
	 * @param {}
	 *            rowIndex
	 * @param {}
	 *            e
	 */
	roleSelectedPanelDblClick : function(grid, rowIndex, e) {
		if (this.userId != '') {
			var roleId = grid.getStore().getAt(rowIndex).data.roleId;
			Ext.Ajax.request({
						url : __ctxPath + '/system/delRoleAppUser.do?userId='
								+ this.userId,
						params : {
							roleId : roleId
						},
						method : 'POST',
						scope : this,
						success : function(response, options) {
							Ext.ux.Toast.msg('操作', '成功删除角色！');
							this.roleSelectedPanel.getStore()
									.removeAt(rowIndex);
						}
					});
		} else {
			this.roleSelectedPanel.getStore().removeAt(rowIndex);
		}
	},

	/**
	 * 删除已选部门数据
	 * 
	 * @param {}
	 *            grid
	 * @param {}
	 *            rowIndex
	 * @param {}
	 *            e
	 */
	depGridPanelDblClick : function(grid, rowIndex, e) {
		var data = grid.getStore().getAt(rowIndex).data;
		if (data.userOrgId && data.isPrimary != 1) {
			Ext.Ajax.request({
						url : __ctxPath + '/system/delUserOrg.do?userOrgId='
								+ data.userOrgId,
						method : 'Post',
						scope : this,
						success : function(response, options) {
							Ext.ux.Toast.msg('操作信息', '成功删除所属部门');
							this.depGridPanel.getStore().removeAt(rowIndex);
						}
					});
		} else {
			if (data.isPrimary != 1) {
				this.depGridPanel.getStore().removeAt(rowIndex);
			} else {
				Ext.ux.Toast.msg('操作信息', '主部门不能删除');
			}
		}
	},
	/**
	 * 角色选择器单击
	 * 
	 * @param {}
	 *            grid
	 * @param {}
	 *            rowIndex
	 * @param {}
	 *            e
	 */
	roleForSelPanelDblClick : function(grid, rowIndex, e) {
		var store = grid.getStore();
		var record = store.getAt(rowIndex);
		var selStore = this.roleSelectedPanel.getStore();
		for (var i = 0; i < selStore.getCount(); i++) {
			if (selStore.getAt(i).data.roleId == record.data.roleId)
				return;
		}
		var recordType = selStore.recordType;
		selStore.add(new recordType(record.data));
	},
	/**
	 * 设置为主岗位
	 * 
	 * @param {}
	 *            curCbo
	 */
	setPosPrimary : function(combo, record, index) {
		var primaryFlag = false;
		var selRe = this.posGridPanel.getSelectionModel().getSelections();
		var store = this.posGridPanel.getStore();
		for (var i = 0; i < store.getCount(); i++) {
			var data = store.getAt(i).data;
			if (data.isPrimary == 1 && data.posId != selRe[0].data.posId) {
				primaryFlag = true;
				break;
			}
		}
		if (primaryFlag && combo.value == 1) {
			combo.setValue(0);
			Ext.ux.Toast.msg('操作信息', '只能有一个主岗位');
		}
		primaryFlag = false;
	},
	/**
	 * 设置为主部门
	 * 
	 * @param {}
	 *            combo
	 * @param {}
	 *            record
	 * @param {}
	 *            index
	 */
	setDepPrimary : function(combo, record, index) {

		var primaryFlag = false;
		var selRe = this.depGridPanel.getSelectionModel().getSelections();
		var store = this.depGridPanel.getStore();
		for (var i = 0; i < store.getCount(); i++) {
			var data = store.getAt(i).data;
			if (data.isPrimary == 1 && data.orgId != selRe[0].data.orgId) {
				primaryFlag = true;
				break;
			}
		}
		if (primaryFlag && combo.value == 1) {
			combo.setValue(0);
			Ext.ux.Toast.msg('操作信息', '只能有一个主部门');
			primaryFlag = false;
			return;
		}

		// var rec = store.getAt(this.depGridPanelIdx);
		// if(combo.value==0){
		// rec.set('isCharge','0');
		// }else{
		// rec.set('isCharge','1');
		// }
		// rec.commit();

		// var isPrimary=record.data.field1;
		// if(isPrimary==1){
		// for(var i=0;i<store.getCount();i++){
		// var record=store.getAt(i);
		// record.data['field0']=0;
		// record.commit();
		// }
		// this.depGridPanel.stopEditing();
		// }
	},
	/**
	 * 部门树的双击
	 * 
	 * @param {}
	 *            node
	 */
	depTreePanelDblClick : function(node) {
		if (node.id == 0)
			return;
		// 检查该nodeid是否已经存在于depGrid用户
		var store = this.depGridPanel.getStore();
		var nodeId = node.id;
		for (var i = 0; i < store.getCount(); i++) {
			if (store.getAt(i).data.orgId == nodeId) {
				return;
			}
		}
		var recordType = store.recordType;
		store.add(new recordType({
					orgId : node.id,
					orgName : node.text,
					isPrimary : store.getCount() > 0 ? 0 : 1,
					isCharge : 0
				}));
	},
	/**
	 * 设置密码
	 */
	setPassword : function() {
		new SetPasswordForm({
					userId : this.userId
				}).show();
	},
	/**
	 * 保存
	 */
	save : function() {
		// 取得选择的角色
		var roleIds = '';

		// 取得选择的部门
		var orgIds = [];
		// 取得选择的职位
		var posIds = [];

		for (var i = 0; i < this.roleSelectedPanel.getStore().getCount(); i++) {
			if (i > 0)
				roleIds += ',';
			roleIds += this.roleSelectedPanel.getStore().getAt(i).data.roleId;
		}

		var primaryOrgFlag = false;
		for (var i = 0; i < this.depGridPanel.getStore().getCount(); i++) {
			var data = this.depGridPanel.getStore().getAt(i).data;
			orgIds.push(data);
			if (data.isPrimary == 1) {
				primaryOrgFlag = true;
			}
		}
		if (orgIds.length == 0 || !primaryOrgFlag) {
			Ext.ux.Toast.msg('操作信息', '必须选择一个主部门');
			return;
		}

		for (var i = 0; i < this.posGridPanel.getStore().getCount(); i++) {
			posIds.push(this.posGridPanel.getStore().getAt(i).data);
		}

		if (primaryOrgFlag) {
			$postForm({
						url : __ctxPath + '/system/saveOrUpdateAppUser.do',
						formPanel : this,
						params : {
							roleIds : roleIds,
							orgIds : Ext.encode(orgIds),
							posIds : Ext.encode(posIds)
						},
						scope : this,
						
						callback : function(fp, action) {
							if (action.result.success) {
								App.getContentPanel().remove(this, true);
								if (Ext.getCmp('AppUserView')) {
									Ext.getCmp('AppUserView').gridPanel.getStore().reload();
								}
								
								if (Ext.getCmp('PositionUserView')) {
									Ext.getCmp('PositionUserView').gridPanel.getStore().reload();
								}
							} else {
								Ext.ux.Toast.msg('操作信息', action.result.msg);
							}
							
							var grid = Ext.getCmp('orgUserGrid');
							if(grid!=null&&grid!= undefined&&grid!=''){
								App.getContentPanel().remove(this, true);
								grid.getStore().reload();
							}
						}
					});
		}
	}
		// ==================function end==================
});