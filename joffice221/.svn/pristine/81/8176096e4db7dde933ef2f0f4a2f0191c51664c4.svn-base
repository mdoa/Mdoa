/**
 * 岗位人员管理
 * 
 * @class PositionUserView
 * @extends Ext.Panel
 */
PositionUserView = Ext.extend(Ext.Panel, {
	constructor : function(conf) {
		Ext.apply(this, conf);
		this.initUI();
		PositionUserView.superclass.constructor.call(this, {
					id : 'PositionUserView',
					iconCls : 'menu-position',
					title : '岗位人员管理',
					layout : 'border',
					items : [this.leftPanel, this.outPanel]
				});
	},
	initUI : function() {
		this.treePanel = new htsoft.ux.TreePanelEditor({
					url : __ctxPath + '/system/treePosition.do',
					scope : this,
					autoScroll : true,
					contextMenuItems : [{
								text : '新建岗位',
								iconCls : 'btn-add',
								scope : this,
								isShowSameLevel : false,
								handler : this.addPosition
							}, {
								text : '编辑岗位',
								iconCls : 'btn-edit',
								scope : this,
								isShowSameLevel : true,
								handler : this.editPosition
							}, {
								text : '删除岗位',
								iconCls : 'btn-del',
								scope : this,
								handler : this.delPosition
							}, '-', {
								text : '加入员工',
								iconCls : 'btn-add',
								scope : this,
								handler : this.addPosUser
							}],
					onclick : this.posTreeClick,
					enableDD : true,
					movenode : this.movenode,
					nodedragover : this.nodedragover
				});

		this.leftPanel = new Ext.Panel({
					region : 'west',
					title : '岗位树',
					layout : 'fit',
					collapsible : true,
					split : true,
					border : false,
					width : 200,
					items : [this.treePanel]
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
					tbar : [{
								xtype : 'button',
								iconCls : 'btn-add',
								text : '添加账号',
								scope : this,
								handler : this.addUser
							}, '-', {
								xtype : 'button',
								iconCls : 'btn-del',
								text : '删除岗位的员工',
								scope : this,
								handler : this.removePosUser
							}],
					url : __ctxPath + '/system/posUsersAppUser.do',
					fields : ['userId', 'fullname', 'username', 'posNames'],
					columns : [{
								header : '账号',
								dataIndex : 'username'
							}, {
								header : '姓名',
								dataIndex : 'fullname'
							}, {
								header : '岗位',
								dataIndex : 'posNames'
							}, new Ext.ux.grid.RowActions({
										header : '管理',
										width : 120,
										actions : [
												{
													iconCls : 'btn-edit',
													qtip : '编辑',
													style : 'margin:0 3px 0 3px'
												},{
													iconCls : 'btn-del',
													qtip : '删除',
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
					title : '岗位人员列表',
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
	// 添加职位
	addPosition : function() {
		var posId = this.treePanel.selectedNode.id;
		new PositionForm({
					posSupId : posId,
					scope : this,
					callback : this.reloadPosition
				}).show();
	},
	// 重新加载树菜單
	reloadPosition : function() {
		this.treePanel.root.reload();
	},
	// 编辑职位
	editPosition : function() {
		var posId = this.treePanel.selectedNode.id;
		if (posId == 0)
			return;
		new PositionForm({
					posId : posId,
					scope : this,
					callback : this.reloadPosition
				}).show();
	},
	// 删除岗位
	delPosition : function() {
		var posId = this.treePanel.selectedNode.id;
		if (posId == 0)
			return;
		Ext.Msg.confirm('信息确认', '注意：删除该岗位将会删除其下所有的子岗位，您确认要删除所选岗位吗？', function(btn) {
			if (btn == 'yes') {
				Ext.Ajax.request({
					url:__ctxPath+'/system/multiDelPosition.do',
					method:'POST',
					scope:this,
					params:{
						ids:posId
					},
					success:function(response,options){
						var result = Ext.util.JSON.decode(response.responseText);
						if(result.success){
							Ext.ux.Toast.msg('操作信息','成功删除岗位!');	
							this.reloadPosition.call(this);
						}			
						else
							Ext.ux.Toast.msg('操作信息','删除岗位前请先删除该岗位及其子岗位下的用户!');	
					
					},
					failure : function(response,options) {
						Ext.ux.Toast.msg('操作信息','操作出错，请联系管理员！');
					}
				});
			}
		},this);
	},
	// 职位树点击
	posTreeClick : function() {
		var posId = this.treePanel.selectedNode.id;
		var store = this.gridPanel.getStore();
		var fullname = this.searchPanel.getCmpByName('Q_fullname_S_LK')
				.getValue();
		var username = this.searchPanel.getCmpByName('Q_username_S_LK')
				.getValue();
		store.baseParams = {
			posId : posId,
			'Q_username_S_LK' : username,
			'Q_fullname_S_LK' : fullname
		};
		this.gridPanel.getBottomToolbar().moveFirst();
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
	// 删除岗位的员工
	removeRs : function(id) {
		var grid = this.gridPanel;
		if (this.treePanel.selectedNode == null) {
			Ext.ux.Toast.msg("信息", "请选择岗位！");
			return;
		}
		Ext.Msg.confirm('删除操作', '注意：要删除该员工与岗位的关系吗？', function(btn) {
			if (btn == 'yes') {
				Ext.Ajax.request({
							url : __ctxPath + '/system/multiDelUserPosition.do',
							method : 'post',
							params : {
								ids : id.toString(),
								posId : this.treePanel.selectedNode.id
							},
							success : function(response) {
								var result = Ext.util.JSON
										.decode(response.responseText);
								if (result.msg == null) {
									Ext.ux.Toast.msg("操作信息", "员工删除成功");
								}
								grid.getStore().reload();
							},
							failure : function() {
								Ext.ux.Toast.msg("操作信息", "员工删除失败");
							}
						});
			}
		}, this);
	},
	// 删除岗位的员工
	removePosUser : function() {
		var grid = this.gridPanel;
		var selectRecords = grid.getSelectionModel().getSelections();
		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
			return;
		}
		if (this.treePanel.selectedNode == null) {
			Ext.ux.Toast.msg("信息", "请选择岗位！");
			return;
		}
		var ids = Array();
		for (var i = 0; i < selectRecords.length; i++) {
			ids.push(selectRecords[i].data.userId);
		}
		Ext.Msg.confirm('删除操作', '注意：要删除该员工与岗位的关系吗？', function(btn) {
			if (btn == 'yes') {
				Ext.Ajax.request({
							url : __ctxPath + '/system/multiDelUserPosition.do',
							method : 'post',
							params : {
								ids : ids.toString(),
								posId : this.treePanel.selectedNode.id
							},
							success : function(response) {
								var result = Ext.util.JSON
										.decode(response.responseText);
								if (result.msg == null) {
									Ext.ux.Toast.msg("操作信息", "员工删除成功");
								}
								grid.getStore().reload();
							},
							failure : function() {
								Ext.ux.Toast.msg("操作信息", "员工删除失败");
							}
						});
			}
		}, this);
	},
	// 为岗位加入员工
	addPosUser : function() {
		var posId = 0;
		var selNode = this.treePanel.selectedNode;
		if (selNode) {
			posId = selNode.id;
		}
		if (posId == 0) {
			Ext.ux.Toast.msg('操作信息', '请选择对应的岗位!');
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
											+ '/system/saveUserPosition.do',
									params : {
										userIds : ids,
										posId : posId
									},
									success : function(resp, options) {
										var store = this.gridPanel.getStore();
										store.baseParams = {
											posId : posId
										};
										this.gridPanel.getBottomToolbar()
												.moveFirst();
									}
								});
					}
				}).show();
	},

	movenode : function(tree, node, oldParent, newParent, index) {
		if (newParent.id.indexOf('xnode') != 0) {

			Ext.Ajax.request({
						url : __ctxPath + '/system/saveTreeSeqPosition.do',
						success : function() {
							this.reloadPosition.call(this);
						},
						failure : function() {
							this.reloadPosition.call(this);
						},
						params : {
							curNode : node.id,
							oldParent : oldParent.id,
							newParent : newParent.id,
							setIdx : index
						},
						scope : this
					});
		} else {
			Ext.ux.Toast.msg('操作信息', '非法拖动!');
			tree.root.reload();
		}
	},

	nodedragover : function(e) {
		var node = e.target;

		if (node.id != 0) {

			node.leaf = false;
		} else {

			e.tree.root.reload();
		}
		return true;
	}

});