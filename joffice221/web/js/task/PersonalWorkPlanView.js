Ext.ns('PersonalWorkPlanView');
/**
 * @description PersonalWorkPlanView我的计划
 * @extend Panel
 * @author ZQG
 * @createtime 2012-8-13AM 我的计划列表
 */
PersonalWorkPlanView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUI();
		// 调用父类构造函数
		PersonalWorkPlanView.superclass.constructor.call(this, {
					id : 'PersonalWorkPlanView',
					title : '我的计划列表',
					iconCls : 'menu-myplan',
					region : 'center',
					layout : 'border',
					items : [this.leftPanel, this.centerPanel]
				});
	},
	// 初始化组件
	initUI : function() {
		// 分类树
		this.treePanel = new htsoft.ux.TreePanelEditor({
					url : __ctxPath + '/system/treeGlobalType.do?catKey=PWP',
					scope : this,
					autoScroll : true,
					split : true,
					onclick : this.nodeClick,
					enableDD : true
				});
		// 给树添加右击事件
		this.treePanel.on('contextmenu', this.contextmenu, this);
		// 左部面板
		this.leftPanel = new Ext.Panel({
					region : 'west',
					title : '分类管理',
					layout : 'fit',
					collapsible : true,
					split : true,
					border : false,
					width : 200,
					items : [this.treePanel]
				});
		// 查询面板
		this.searchFormPanel = new HT.SearchPanel({
			layout : 'form',
			region : 'north',
			colNums : 4,
			keys : [{
						key : Ext.EventObject.ENTER,
						fn : this.search,
						scope : this
					}, {
						key : Ext.EventObject.ESC,
						fn : this.reset,
						scope : this
					}],
			labelWidth : 60,
			items : [{
						xtype : 'textfield',
						fieldLabel : '计划名称 ',
						name : 'Q_planName_S_LK',
						maxLength : 125
					}, {
						hiddenName : 'Q_globalType.proTypeId_L_EQ',
						fieldLabel : '计划类型',
						hiddenId : 'workPlayTypeId',
						xtype : 'combotree',
						url : __ctxPath
								+ '/system/treeGlobalType.do?catKey=PWP&method=1'
					}, {
						xtype : 'button',
						text : '查询',
						iconCls : 'search',
						scope : this,
						handler : this.search
					}, {
						iconCls : 'btn-reset',
						xtype : 'button',
						text : '重置',
						scope : this,
						handler : function() {
							this.searchFormPanel.getForm().reset();
							this.searchFormPanel.getCmpByName('Q_globalType.proTypeId_L_EQ').clearValue();
						}
					}]
		});

		// 顶部菜单面板
		this.topbar = new Ext.Toolbar({
					height : 30,
					bodyStyle : 'text-align:left',
					items : [{
								xtype : 'button',
								text : '添加个人计划',
								iconCls : 'btn-add',
								scope : this,
								handler : this.createRecord
							}, {
								xtype : 'button',
								text : '删除',
								iconCls : 'btn-del',
								scope : this,
								handler : this.delRecord
							}]
				});
		// 个人计划列表面板
		this.gridPanel = new HT.GridPanel({
			region : 'center',
			tbar : this.topbar,
			sort : [{
						field : "planId",
						direction : "DESC"
					}],
			// 使用RowActions
			rowActions : true,
			url : __ctxPath + '/task/personalWorkPlan.do',
			fields : [{
						name : 'planId',
						type : 'int'
					}, 'planName', 'planContent', 'startTime', 'endTime',
					'globalType', {
						name : 'appUser.fullname',
						mapping : 'appUser.fullname'
					}, 'principal', 'note', 'status', 'isPersonal', 'icon'],
			columns : [{
						header : 'planId',
						dataIndex : 'planId',
						hidden : true

					}, {
						header : '标识',
						dataIndex : 'icon',
						renderer : function(value) {
							var tip = (value =='ux-flag-blue'?'日常计划':
										(value =='ux-flag-orange'?'重要计划':
										(value =='ux-flag-green'?'特殊计划':
										 (value =='ux-flag-pink'?'个人计划':
										(value =='ux-flag-red'?'紧急计划':
										(value =='ux-flag-purple'?'部门计划':
										(value =='ux-flag-yellow'?'待定计划':'')))))));
							
							return '<div ext:qtip="	'+tip+'" class="' + value
									+ '"></div>';
						}
					}, {
						header : '计划名称',
						dataIndex : 'planName'
					}, {
						header : '开始日期',
						dataIndex : 'startTime'
					}, {
						header : '结束日期',
						dataIndex : 'endTime'
					}, {
						header : '计划类型',
						dataIndex : 'globalType',
						renderer : function(obj) {
							if (obj != null) {
								return obj.typeName;
							}
						}
					}, {
						header : '创建人',
						dataIndex : 'appUser.fullname'
					}, {
						header : '负责人',
						dataIndex : 'principal'
					}, {
						header : '是否生效',
						dataIndex : 'startTime',
						renderer : this.effective
					}, new Ext.ux.grid.RowActions({
								header : '管理',
								width : 100,
								actions : [{
											iconCls : 'btn-del',
											qtip : '删除',
											style : 'margin:0 3px 0 3px'
										}, {
											iconCls : 'btn-edit',
											qtip : '编辑',
											style : 'margin:0 3px 0 3px'
										}],
								listeners : {
									scope : this,
									'action' : this.onRowAction
								}
							})]
				// end of columns
			});
		// 添加gridPanel行双击事件
		this.gridPanel.addListener({
					scope : this,
					'rowdblclick' : this.rowdblclickaction
				});
		// 中部面板
		this.centerPanel = new Ext.Panel({
					title : '所有分类列表',
					layout : 'border',
					region : 'center',
					items : [this.searchFormPanel, this.gridPanel]
				});
	},
	// 判断是否生效
	effective : function(value, metadata, record, rowIndex, colIndex) {
		var startTime = new Date(getDateFromFormat(value, "yyyy-MM-dd H:mm:ss"));
		var endTime = new Date(getDateFromFormat(record.data.endTime,
				"yyyy-MM-dd H:mm:ss"));
		var today = new Date();
		if (startTime > today) {
			return '<a style="color:blue;">未生效</a>';
		} else if (startTime <= today && endTime >= today) {
			return '<a style="color:green;">已生效</a>';
		} else if (endTime < today) {
			return '<a style="color:red;">已失效</a>';
		}
	},
	// 查询
	search : function() {
		$search({
					searchPanel : this.searchFormPanel,
					gridPanel : this.gridPanel
				});
	},
	// 行双击事件
	rowdblclickaction : function(grid, rowindex, e) {
		var rec = grid.getStore().getAt(rowindex);
		this.editRecord.call(this, rec);
	},
	// 添加计划
	createRecord : function() {
		new PersonalWorkPlanForm({
					scope : this,
					callback : this.gridPanelReload
				}).show();
	},
	// 编辑计划
	editRecord : function(record) {
		new PersonalWorkPlanForm({
					planId : record.data.planId,
					scope : this,
					callback : this.gridPanelReload
				}).show();
	},
	// 按ID删除计划
	removeRs : function(id) {
		$postDel({
					url : __ctxPath + '/task/multiDelWorkPlan.do',
					ids : id,
					grid : this.gridPanel
				});
	},
	// 删除计划
	delRecord : function() {
		$delGridRs({
					url : __ctxPath + '/task/multiDelWorkPlan.do',
					grid : this.gridPanel,
					idName : 'planId'
				});
	},
	// 节点点击事件
	nodeClick : function(node) {
		this.selectNode = node;
		var typeId = this.selectNode.id;
		this.centerPanel.setTitle('[' + node.text + ']' + '类型计划列表');
		var store = this.gridPanel.getStore();
		store.url = __ctxPath + '/task/personalWorkPlan.do';
		if (typeId != 0) {
			store.baseParams = {
				'Q_globalType.proTypeId_L_EQ' : typeId
			};
		} else {
			store.baseParams = {
				'Q_globalType.proTypeId_L_EQ' : null
			};
		}
		this.gridPanel.getBottomToolbar().moveFirst();
		store.reload();
	},
	// 添加节点
	addNode : function() {
		var parentId = this.selectNode.id;
		new GlobalTypeForm({
					parentId : parentId,
					catKey : 'PWP',
					scope : this,
					callback : this.reload
				}).show();
	},
	// 编辑节点
	editNode : function() {
		var proTypeId = this.selectNode.id;
		new GlobalTypeForm({
					proTypeId : proTypeId,
					scope : this,
					callback : this.reload
				}).show();
	},
	// 删除节点
	delNode : function() {
		var proTypeId = this.selectNode.id;
		$postDel({
					msg : '注意：删除该分类将会删除其下所有的子分类，您确认要删除所选分类吗？',
					url : __ctxPath + '/system/multiDelGlobalType.do',
					ids : proTypeId,
					scope : this,
					callback : function() {
						var grid = this.gridPanel;
						this.centerPanel.setTitle('[总分类]类型计划列表');
						grid.getStore().baseParams = {
							'Q_globalType.proTypeId_L_EQ' : null
						};
						grid.getBottomToolbar().moveFirst();
						this.treePanel.root.reload();
					}
				});
	},
	// 重新加载treePanel和gridPanel
	reload : function() {
		this.treePanel.root.reload();
		this.gridPanel.getStore().reload();
	},
	// 重新加载gridPanel
	gridPanelReload : function() {
		this.gridPanel.getStore().reload();
	},
	// 节点右击事件
	contextmenu : function(node, e) {
		// 只有私有的才可以，修改，删除操作
		if (node.attributes.isPublic == 'false' || node.id == '0') {
			this.nodeClick(node);
			selected = new Ext.tree.TreeNode({
						id : node.id,
						text : node.text
					});
			// 创建右键菜单
			var treeMenu = new Ext.menu.Menu({
						items : []
					});
			treeMenu.clearMons();
			treeMenu.add({
						text : '新增分类',
						iconCls : 'btn-add',
						handler : this.addNode,
						scope : this
					});
			if (node.id > 0) { // 总分类不能删除，和修改
				treeMenu.add({
							text : '修改分类',
							iconCls : 'btn-edit',
							handler : this.editNode,
							scope : this
						}, {
							text : '删除分类',
							iconCls : 'btn-del',
							handler : this.delNode,
							scope : this
						});
			}
			treeMenu.showAt(e.getXY());
		}
	},
	// 行的Action
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.removeRs.call(this, record.data.planId);
				break;
			case 'btn-edit' :
				this.editRecord.call(this, record);
				break;
			default :
				break;
		}
	}
});
