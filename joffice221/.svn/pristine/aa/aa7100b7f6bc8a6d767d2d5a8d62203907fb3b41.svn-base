/**
 * 公司部门人员管理
 * 
 * @class DepUserView
 * @extends Ext.Panel
 */
DepUserView = Ext.extend(Ext.Panel, {
	constructor : function(conf) {
		Ext.apply(this, conf);
		this.initUI();
		DepUserView.superclass.constructor.call(this, {
					id : 'DepUserView',
					title : '部门人员管理',
					layout : 'border',
					iconCls : 'menu-department',
					items : [this.leftPanel, this.outPanel]
				});
	},
	initUI : function() {
		this.depTreePanel = new htsoft.ux.TreePanelEditor({
					treeType : 'org',
					url : __ctxPath + '/system/treeOrganization.do?demId=1',
					scope : this,
					autoScroll : true,
					contextMenuItems : [{
								text : '新建公司或组织',
								iconCls : 'btn-add',
								scope : this,
								handler : this.addDep
							}, {
								text : '编辑公司或组织',
								iconCls : 'btn-edit',
								scope : this,
								handler : this.editDep
							}, '-', {
								text : '加入员工',
								iconCls : 'btn-add',
								scope : this,
								handler : this.addDepUser
							}, '-' ,{
								 text:'删除新建公司或组织',
								 iconCls:'btn-del',
								 scope:this,
								 handler:this.delDep
					 }
					],
					onclick : this.depTreeClick
				});

		this.leftPanel = new Ext.Panel({
					region : 'west',
					title : '行政维度部门管理',
					layout : 'fit',
					collapsible : true,
					split : true,
					border : false,
					width : 200,
					items : [this.depTreePanel]
				});
		// 搜索面板
		this.searchPanel = new HT.SearchPanel({
					layout : 'form',
					region : 'north',
					colNums : 4,
					keys : {
						key : Ext.EventObject.ENTER,
						fn : this.search,
						scope : this
					},
					labelWidth : 35,
					items : [{
								fieldLabel : '姓名',
								xtype : 'textfield',
								name : 'Q_username_S_LK',
								maxLength : 150
							}, {
								fieldLabel : '账号',
								xtype : 'textfield',
								name : 'Q_fullname_S_LK',
								maxLength : 125,
								labelWidth : 35
							}, {
								xtype : 'button',
								text : '查询',
								iconCls : 'search',
								scope : this,
								handler : this.search
							}, {
								xtype : 'button',
								text : '清空',
								iconCls : 'reset',
								scope : this,
								handler : this.reset
							}]
				});

		this.gridPanel = new HT.GridPanel({
					rowActions : true,
					region : 'center',
					sort : [{
						field : "userId",
						direction : "DESC"
					}],
					tbar : [{
								xtype : 'button',
								iconCls : 'btn-add',
								text : '添加账号',
								scope : this,
								handler : this.addUser
							}, '-', {
								xtype : 'button',
								iconCls : 'btn-del',
								text : '删除部门的员工',
								scope : this,
								handler : this.removeUserOrg
							}],
					url : __ctxPath + '/system/depUsersAppUser.do',
					fields : ['userId', 'fullname', 'username',  {
									name : 'department.depId',
									mapping : 'primaryDep'
								},
							'department'],
					columns : [{
								header : '账号',
								dataIndex : 'username'
							}, {
								header : '姓名',
								dataIndex : 'fullname'
							}, {
								header : '主部门',
								dataIndex : 'department.depId'
							}, new Ext.ux.grid.RowActions({
										header : '管理',
										width : 100,
										actions : [
										 {
												 iconCls : 'btn-del',
												 qtip : '删除',
												 style : 'margin:0 3px 0 3px',
												 fn:function(rs){
												 if(rs.data.userId==-1){
													 return false;
													 }
													 return true;
												 }
										 },{
											iconCls : 'btn-edit',
											qtip : '编辑',
											style : 'margin:0 3px 0 3px'
										}],
										listeners : {
											scope : this,
											'action' : this.onRowAction
										}
									})]
				});

		this.outPanel = new Ext.Panel({
					region : 'center',
					title : '组织人员列表',
					layout : 'border',
					items : [this.searchPanel, this.gridPanel]
				});
	},
	/**
	 * 查询
	 */
	search : function() {
		$search({
					searchPanel : this.searchPanel,
					gridPanel : this.gridPanel
				});
	},
	/**
	 * 重置(清空)查询表单
	 */
	reset : function() {
		this.searchPanel.getForm().reset();
	},
	// 添加部门
	addDep : function() {
		var depId = this.depTreePanel.selectedNode.id;
		var depName = this.depTreePanel.selectedNode.text;
		var orgType = this.depTreePanel.selectedNode.attributes.orgType;
		if (!orgType) {// 集团
			orgType = 0;
		} else if (orgType <= 1) {// 公司
			orgType = 1;
		} else {
			orgType = 2;// 则为部门
		}
		new DepForm({
					parentId : depId,
					parentText : depName,
					scope : this,
					orgType : orgType,
					callback : this.reloadDep
				}).show();
	},
	// 重新加载树
	reloadDep : function() {
		this.depTreePanel.root.reload();
	},
	// 编辑职位
	editDep : function() {
		var depId = this.depTreePanel.selectedNode.id;
		if (depId == 0)
			return;
		new DepForm({
					depId : depId,
					scope : this,
					callback : this.reloadDep
				}).show();
	},
	// 删除职位
	delDep : function() {
		var orgId = this.depTreePanel.selectedNode.id;
		if (orgId == 0) return;
		Ext.Msg.confirm('信息确认', '注意：删除该部门将会删除其下所有的部门，您确认要删除所选部门吗？', function(btn) {
			if (btn == 'yes') {
				Ext.Ajax.request({
					url : __ctxPath + '/system/multiDelOrganization.do',
					method:'POST',
					scope:this,
					params:{
						ids:orgId
					},
					success:function(response,options){
						var result = Ext.util.JSON.decode(response.responseText);
						if(result.success){
							Ext.ux.Toast.msg('操作信息','成功删除部门!');	
							this.reloadDep.call(this);
						}			
						else
							Ext.ux.Toast.msg('操作信息','删除部门前请先删除该部门及其子部门下的用户!');	
					
					},
					failure : function(response,options) {
						Ext.ux.Toast.msg('操作信息','操作出错，请联系管理员！');
					}
				});
			}
		},this);
	},
	// 部门树点击
	depTreeClick : function() {
		var depId = this.depTreePanel.selectedNode.id;
		var store = this.gridPanel.getStore();
		var fullname = this.searchPanel.getCmpByName('Q_fullname_S_LK')
				.getValue();
		var username = this.searchPanel.getCmpByName('Q_username_S_LK')
				.getValue();
		store.baseParams = {
			depId : depId,
			'Q_username_S_LK' : username,
			'Q_fullname_S_LK' : fullname
		};
		this.gridPanel.getBottomToolbar().moveFirst();
	},
	//行删除处理函数
	removeRs:function(id){
			var  orgId=0;
			var gridPanel=this.gridPanel;
			var node=this.depTreePanel.selectedNode;
			if(node)
				orgId=node.id;
			if(!node || orgId==0){
				Ext.ux.Toast.msg("信息", "请选择组织！");
				return;
			}			
			Ext.Msg.confirm('信息确认', '您确认要删除所选记录吗？', function(btn) {
					if (btn == 'yes') {
						Ext.Ajax.request({
							url:__ctxPath+'/system/multiDelUserOrg.do',
							params : {
											ids : id,
											orgId:orgId
											},
							method : 'POST',
							scope:this,
							success : function(response,options) {
								var result = Ext.util.JSON
								.decode(response.responseText);
								if(result.msg)
									Ext.ux.Toast.msg('操作信息',result.msg);								
								gridPanel.getStore().reload();
							},
							failure : function(response,options) {
								Ext.ux.Toast.msg('操作信息','操作出错，请联系管理员！');
							}
						});
					}
			});			
	},
	// 行编辑
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.removeRs.call(this, record.data.userId);
				break;
			case 'btn-edit' :
				this.editRs.call(this, record);
				break;
			default :
				break;
		}
	},
	// 添加上下级
	addRelativeUser : function(record) {
		new RelativeUserView({
			userId : record.data.userId, // 用户编号
			username : record.data.username,
			depId : record.data.department.depId
				// 部门编号
			}).show();
	},
	// 编辑用户信息
	editRs : function(record) {
		App.clickTopTab('UserFormPanel_' + record.data.userId, {
					userId : record.data.userId,
					username : record.data.username
				});
	},
	addUser : function() {
		App.clickTopTab('UserFormPanel');
	},
	// 删除部门员工
	removeUserOrg : function() {
		var grid = this.gridPanel;
		var selectRecords = grid.getSelectionModel().getSelections();
		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
			return;
		}
		if (this.depTreePanel.selectedNode == null
				|| this.depTreePanel.selectedNode.id == 0) {
			Ext.ux.Toast.msg("信息", "请选择组织！");
			return;
		}
		var ids = Array();
		for (var i = 0; i < selectRecords.length; i++) {
			ids.push(selectRecords[i].data.userId);
		}
		Ext.Msg.confirm('删除操作', '注意：要删除该员工与部门的关系吗？', function(btn) {
					if (btn == 'yes') {
						Ext.Ajax.request({
									url : __ctxPath
											+ '/system/multiDelUserOrg.do',
									method : 'post',
									params : {
										ids : ids.toString(),
										orgId : this.depTreePanel.selectedNode.id
									},
									success : function(response) {
										var result = Ext.util.JSON
												.decode(response.responseText);
										if(result.msg)
											Ext.ux.Toast.msg('操作信息',result.msg);		
										grid.getStore().reload();
									},
									failure : function() {
										Ext.ux.Toast.msg("操作信息", "员工删除失败");
									}
								});
					}
				}, this);
	},
	// 添加组织的员工
	addDepUser : function() {
		var orgId = 0;
		var selNode = this.depTreePanel.selectedNode;
		if (selNode) {
			orgId = selNode.id;
		}
		if (orgId == 0) {
			Ext.ux.Toast.msg('操作信息', '请选择对应的部门!');
			return;
		}
		// 弹出用户选择器，根据当前选择的节点，把用户加入该组织或部门
		new UserDialog({
					title : '加入新用户',
					scope : this,
					single : false,
					callback : function(ids, names) {
						Ext.Ajax.request({
									method : 'POST',
									scope : this,
									url : __ctxPath
											+ '/system/addOrgsUserOrg.do',
									params : {
										userIds : ids,
										orgId : orgId
									},
									success : function(resp, options) {
										var store = this.gridPanel.getStore();
										store.baseParams = {
											depId : orgId
										};
										this.gridPanel.getBottomToolbar()
												.moveFirst();
									}
								});
					}
				}).show();
	}
});