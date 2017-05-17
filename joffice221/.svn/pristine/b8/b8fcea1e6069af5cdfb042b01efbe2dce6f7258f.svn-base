/**
 * @author: YHZ
 * @class: RelativeUserView
 * @extends: Ext.Window
 * @description: 相对岗位人员管理
 * @company: 杭州梦德软件有限公司
 * @data: 2010-1-10AM
 */
RelativeUserView = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		RelativeUserView.superclass.constructor.call(this, {
					id : 'RelativeUserView',
					title : '该用户[' + this.username + ']添加上下级管理',
					iconCls : 'menu-relativeJob',
					region : 'center',
					layout : 'border',
					width : 850,
					height : 600,
					modal : true,
					maximizable : true,
					items : [this.treePanel, this.gridPanel]
				});
	},// end of constructor
	// 初始化组件
	initUIComponents : function() {
		// TODO gridPanel
		// 实例化treePanel, 加载相对岗位人员管理
		this.treePanel = new htsoft.ux.TreePanelEditor({
					region : 'west',
					collapsible : true,
					autoScroll : true,
					split : true,
					width : 150,
					scope : this,
					title : '相对岗位列表',
					url : __ctxPath + '/system/treeLoadRelativeJob.do',
					contextMenuItems : [{
								iconCls : 'btn-add',
								text : '新增岗位',
								scope : this,
								handler : this.addRelativeJob
							}, {
								text : '修改岗位信息',
								iconCls : 'btn-edit',
								scope : this,
								handler : this.editRelativeJob
							}, {
								text : '删除岗位信息',
								iconCls : 'btn-del',
								scope : this,
								handler : this.delRelativeJob
							}],
					onclick:this.clickTree
				}); // end of this treePanel
		// 顶部按钮
		this.topbar = new Ext.Toolbar({
					defaultType : 'button',
					items : [{
						iconCls : 'btn-superior',
						text : '添加上级',
						scope : this,
						handler : this.addRelativeUser
								.createDelegate(this, [1])
					}, '-', {
						iconCls : 'btn-sibling',
						text : '添加同级',
						scope : this,
						handler : this.addRelativeUser
								.createDelegate(this, [2])
					}, '-', {
						iconCls : 'btn-subordinate',
						text : '添加下级',
						scope : this,
						handler : this.addRelativeUser
								.createDelegate(this, [0])
					}, '-', {
						iconCls : 'btn-del',
						text : '删除',
						scope : this,
						handler : this.removeSelRs
					}]
				});
		// 上下级列表
		this.gridPanel = new HT.GridPanel({
			region : 'center',
			tbar : this.topbar,
			// 使用RowActions
			rowActions : true,
			url : __ctxPath + "/system/listRelativeUser.do?userId="
					+ this.userId,
			fields : [{
						name : 'relativeUserId',
						type : 'int'
					}, 'appUser', 'jobUser', 'isSuper', 'relativeJob'],
			columns : [{
						header : 'relativeUserId',
						dataIndex : 'relativeUserId',
						hidden : true
					}, {
						header : '岗位员工',
						dataIndex : 'jobUser',
						renderer : function(value) {
							return value.username;
						}
					}, {
						header : '上下级标识 ',
						dataIndex : 'isSuper',
						renderer : function(value) {
							if (value == 0)
								return '<button class="btn-subordinate" title="下级"/>';
							else if (value == 1)
								return '<button class="btn-superior" title="上级" />';
							else if (value == 2)
								return '<button class="btn-sibling" title="同级" />';
							else
								return '';
						}
					}, new Ext.ux.grid.RowActions({
								header : '管理',
								width : 100,
								actions : [{
											iconCls : 'btn-del',
											qtip : '删除',
											style : 'margin:0 3px 0 3px'
										}],
								listeners : {
									scope : this,
									'action' : this.onRowAction
								}
							})]
				// end of columns
		});
	},// end of the initComponents()
	/**
	 * 点击树
	 * 
	 * @param {}
	 *            node
	 */
	clickTree : function(node) {
		if (node != null) {
			var store = this.gridPanel.getStore();
			store.baseParams = {
				reJobId : node.id
			};
			store.reload({
						params : {
							start : 0,
							limit : 25
						}
					});
		}
	},
	/**
	 * 新增岗位信息
	 */
	addRelativeJob : function() {
		var nodeId = this.treePanel.selectedNode.id;
		if (Ext.isEmpty(nodeId) || nodeId <= 0) {
			nodeId = 0;
		}
		new RelativeJobForm({
					nodeId : nodeId,
					scope : this,
					callback : this.reloadTreePanel
				}).show();
	},
	/**
	 * 编辑岗位信息
	 */
	editRelativeJob : function() {
		var nodeId = this.treePanel.selectedNode.id;
		if (nodeId > 0) {
			new RelativeJobForm({
						reJobId : nodeId,
						scope : this,
						callback : this.reloadTreePanel
					}).show();

		} else {
			Ext.ux.Toast.msg('操作提示', '对不起，公司名称不能修改！');
		}
	},
	/**
	 * 刷新树
	 */
	reloadTreePanel : function() {
		this.treePanel.root.reload();
	},
	/**
	 * 删除岗位信息
	 */
	delRelativeJob : function() {
		var nodeId = this.treePanel.selectedNode.id;
		if (nodeId > 0) {
			$postDel({
						msg : '注意：您真的要删除该岗位信息吗',
						url : __ctxPath + '/system/multiDelRelativeJob.do',
						ids : nodeId,
						scope : this,
						callback : this.reloadTreePanel
					});
		} else {
			Ext.ux.Toast.msg('操作提示', '对不起，公司名称不能删除，请原谅！');
		}
	},
	/**
	 * 按ID删除记录
	 * 
	 * @param {}
	 *            id
	 */
	removeRs : function(id) {
		var grid=this.gridPanel;
		Ext.Msg.confirm('信息确认',' 您确认要删除所选记录吗？', function(btn) {
			if (btn == 'yes') {
				Ext.Ajax.request({
					url : __ctxPath + '/system/multiDelRelativeUser.do',
					params : {
						ids : id
					},
					method : 'POST',
					success : function(response, options) {
						var result = Ext.util.JSON.decode(response.responseText);
						var returnIds=result.id;
						if (result.success) {
							Ext.ux.Toast.msg('操作信息', '成功删除该记录！');
							grid.getStore().reload();
							if(returnIds)
								$postDel({
									msg:'是否从对方的关系列表中删除相关记录？',
									url : __ctxPath + '/system/multiDelRelativeUser.do',
									ids : returnIds
								});
						} else {
							Ext.ux.Toast.msg('操作信息', result.message);
						}
					},
					failure : function(response, options) {
						Ext.ux.Toast.msg('操作信息', '操作出错，请联系管理员！');
					}
				});
			}			
		});		
	},
	/**
	 * 把选中ID删除
	 */
	removeSelRs : function() {
		$delGridRs({
					url : __ctxPath + '/system/multiDelRelativeUser.do',
					grid : this.gridPanel,
					idName : 'relativeUserId'
				});
	},

	/**
	 * 行的Action
	 * 
	 * @param {}
	 *            grid
	 * @param {}
	 *            record
	 * @param {}
	 *            action
	 * @param {}
	 *            row
	 * @param {}
	 *            col
	 */
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.removeRs.call(this, record.data.relativeUserId);
				break;
			default :
				break;
		}
	},

	/**
	 * @description 添加用户级别[上级,下级,同级]
	 * @param type
	 *            1.上级,0.下级,2.同级
	 */
	addRelativeUser : function(type) {
		var userId = this.userId;
		var typeMsg = '';
		if (type == 1)
			typeMsg = '上级';
		else if (type == 2)
			typeMsg = '同级';
		else
			typeMsg = '下级';
		// 判断是否选中相对岗位
		var node = this.treePanel.getSelectionModel().getSelectedNode();
		if (Ext.isEmpty(node) || node.id <= 0) {
			Ext.ux.Toast.msg('操作提示', '请选择岗位名称！');
			return;
		}
		// 获取已选用户userId
		var store = this.gridPanel.getStore();
		var selArr = new Array();
		for (var i = 0; i < store.getCount(); i++) {
			var ap = store.getAt(i).data.jobUser;
			selArr.push({
						userId : ap.userId,
						fullname : ap.fullname
					});
		}
		// 弹出选择器
		UserSelector.getView(function(jobUserIds, fullNames) {
			if (jobUserIds != '') {
				// 向RelativeUser表中添加数据操作
				Ext.Ajax.request({
							url : __ctxPath + '/system/mutliAddRelativeUser.do',
							method : 'post',
							params : {
								'userId' : userId, // 所属员工
								'reJobId' : node.id, // 对应的岗位
								'jobUserIds' : jobUserIds, // 岗位人员列表
								'relativeUser.isSuper' : type
							},
							success : function(response, op) {
								var res = Ext.util.JSON
										.decode(response.responseText);
								Ext.ux.Toast.msg('操作提示', res.msg);
								store.reload();
							},
							failure : function() {
								Ext.ux.Toast.msg('操作提示', '对不起，添加' + typeMsg
												+ '用户信息失败！');
							}
						});
			}
		}, false, false, selArr).show();
	}
});
