/**
 * @author: YHZ
 * @class: RelativeUserView
 * @extends: Ext.Window
 * @description: 相对岗位人员管理
 * @company: 杭州梦德软件有限公司
 * @data: 2010-1-10AM
 */
SubordinateForm = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		SubordinateForm.superclass.constructor.call(this, {
					id : 'SubordinateForm',
					title : this.title,
					iconCls : 'menu-relativeJob',
					layout : 'fit',
					width : 550,
					height : 500,
					modal : true,
					maximizable : true,
					items : [this.gridPanel]
				});
	},// end of constructor
	// 初始化组件
	initUIComponents : function() {
		// TODO gridPanel
		// 上下级列表
		this.gridPanel = new HT.GridPanel({
			// 使用RowActions
			rowActions : true,
			url : __ctxPath + "/system/findDinateSubordinate.do?userId="
						+ this.userId+"&demId="+this.demId+"&relative="+this.relative,
			fields : [{
						name : 'subordinateId',
						type : 'int'
					}, 'appUser', 'jobUser', 'relative', 'demId'],
			columns : [{
						header : 'subordinateId',
						dataIndex : 'subordinateId',
						hidden : true
					}, {
						header : '员工账号',
						dataIndex : 'jobUser',
						renderer : function(value) {
							return value.username;
						}
					}, {
						header : '员工账号',
						dataIndex : 'jobUser',
						renderer : function(value) {
						
							return value.fullname;
						}
					},{
						header : '级数',
						dataIndex : 'relative',
						scope:this,
						renderer : function(value) {
							if (this.relative == "0")
								return "下"+value+"级";
							else if (this.relative == "1")
								return "上"+value+"级";
							else if (this.relative == "2")
								return '同级';
							else
								return '未知';
						}
					}, new Ext.ux.grid.RowActions({
								header : '管理',
								width : 250,
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
	 * 按ID删除记录
	 * 
	 * @param {}
	 *            id
	 */
	removeRs : function(id) {		
		$postDel({
			msg:'注意：该操作将会删除对方列表中的关系，是否确认删除？',
			url : __ctxPath + '/system/multiDelSubordinate.do',
			ids : id,
			grid : this.gridPanel,
		});
//		var grid=this.gridPanel;
//		Ext.Msg.confirm('信息确认',' 您确认要删除所选记录吗？', function(btn) {
//			if (btn == 'yes') {
//				Ext.Ajax.request({
//					url : __ctxPath + '/system/multiDelSubordinate.do',
//					params : {
//						ids : id
//					},
//					method : 'POST',
//					success : function(response, options) {
//						var result = Ext.util.JSON.decode(response.responseText);
//						var returnIds=result.id;
//						if (result.success) {
//							Ext.ux.Toast.msg('操作信息', '成功删除该记录！');
//							grid.getStore().reload();
//							if(returnIds)
//													} else {
//							Ext.ux.Toast.msg('操作信息', result.message);
//						}
//					},
//					failure : function(response, options) {
//						Ext.ux.Toast.msg('操作信息', '操作出错，请联系管理员！');
//					}
//				});
//			}			
//		});		
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
				this.removeRs.call(this, record.data.subordinateId);
				break;
			default :
				break;
		}
	}
});
