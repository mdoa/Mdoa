/**
 * 角色选择器
 * 
 * @class RoleDialog
 * @extends Ext.Window
 * @example
 * 
 * <pre>
 * new RoleDialog({
 *  	title :'选择角色' //标题  默认是'选择角色'，也可以自定义标题
 * 		single: flase,   //是否单选 默认是单选角色
 * 		roleIds ： roleIds， //已选角色Ids， 仅限single为flase时使用，多个Id用','号分开
 * 		roleName ：roleNames， //已选角色名称，仅限single为flase时使用，多个名称用','号分开，顺序与id对应
 * 		scope:this,   //作用域
 * 		callback :function(ids,names){//回调函数,返回角色ids和角色名称
 * 
 * 		}	
 * 	}
 * </pre>
 */
RoleDialog = Ext.extend(Ext.Window, {
	//构造函数
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		//作用域
		this.scope = this.scope ? this.scope : this;
		//默认为多单选择用户
		this.single=this.single!=null?this.single:true;
		//初始化
		this.initUI();
		RoleDialog.superclass.constructor.call(this, {
					modal : true,
					width : 800,
					height : 420,
					title : this.title ? this.title : '选择角色',
					layout : 'border',
					maximizable : true,
					items : [this.panel],
					buttonAlign : 'center',
					buttons : [{
								iconCls : 'btn-ok',
								text : '确定',
								scope : this,
								handler : this.confirm
							}, {
								text : '取消',
								iconCls : 'btn-cancel',
								scope : this,
								handler : this.close
							}]
				});
	},
	/**
	 * 初始化组件
	 */
	initUI : function() {
		// 查询面板
		this.searchPanel = new HT.SearchPanel({
					layout : 'form',
					region : 'north',
					colNums : 3,
					keys : {
						key : Ext.EventObject.ENTER,
						fn : this.search,
						scope : this
					},
					align : 'center',
					items : [{
								fieldLabel : '角色名称',
								name : 'Q_roleName_S_LK',
								maxLength : 150,
								width : 150,
								xtype : 'textfield'
							}, {
								xtype : 'button',
								text : '查询',
								iconCls : 'btn-search',
								scope : this,
								handler : this.search
							}, {
								xtype : 'button',
								text : '重置',
								iconCls : 'btn-reset',
								scope : this,
								handler : this.reset
							}]
				});
		// 左边选择角色面板
		this.gridPanel = new HT.GridPanel({
					singleSelect:this.single,
					width : '100%',
					height : 400,
					isShowTbar : true,
					tbar : [{
								xtype : 'label',
								text : '选择角色(双击选中)'
							}],
					region : 'center',
					url : __ctxPath + '/system/listAppRole.do',
					fields : [{
								name : 'roleId',
								type : 'int'
							}, 'roleName', 'roleDesc'],
					columns : [{
								header : 'roleId',
								dataIndex : 'roleId',
								hidden : true
							}, {
								header : "角色名称",
								dataIndex : 'roleName',
								width : 60
							}, {
								header : "角色描述",
								dataIndex : 'roleDesc',
								width : 60
							}]
//					listeners : {
//						scope : this,
//						'rowdblclick' : this.roleForSelPanelDblClick
//					}
				});
		if(!this.single){
			this.gridPanel.addListener('rowdblclick',this.roleForSelPanelDblClick,this);
			// 中间面板
			this.buttonPanel = new Ext.Panel({
					region : 'west',
					width : 40,
					height : 400,
					layout : {
						type : 'vbox',
						pack : 'center',
						align : 'center'
					},
					defaultType : 'button',
					border : false,
					items : [{
								iconCls : 'add-all',
								text : '',
								scope : this,
								handler : this.addAll
							}, {
								iconCls : 'rem-all',
								text : '',
								scope : this,
								handler : this.removeAll
							}]
				});
				// 右边的面板
				this.resultPanel = new HT.GridPanel({
					region : 'center',
					width : 370,
					height : 400,
					isShowTbar : true,
					showPaging : false,
					tbar : [{
								xtype : 'label',
								text : '已选角色(双击删除)'
							}],
					fields : [{
								name : 'roleId',
								type : 'int'
							}, 'roleName', 'roleDesc'],
					columns : [{
								header : 'roleId',
								dataIndex : 'roleId',
								hidden : true
							}, {
								header : "角色名称",
								dataIndex : 'roleName',
								width : 60
							}, {
								header : "角色描述",
								dataIndex : 'roleDesc',
								width : 60
							}],
					listeners : {
						scope : this,
						'rowdblclick' : this.roleForResPanelDblClick
					}
				});
				this.eastPanel = new Ext.Panel({
					layout : 'border',
					region : 'east',
					border : false,
					width : 400,
					height : 400,
					items : [ this.buttonPanel,this.resultPanel]
				})
				this.initData();
				this.panel = new Ext.Panel({
					layout : 'border',
					region : 'center',
					border : false,
					width : '96%',
					height : '96%',
					items : [this.searchPanel, this.gridPanel, this.eastPanel]
				});
		}else{
				this.panel = new Ext.Panel({
					layout : 'fit',
					region : 'center',
					width : '96%',
					height : '96%',
					items : [this.searchPanel, this.gridPanel]
				}); 
		}
	},
	/**
	 * 初始化数据
	 */
	initData : function() {
//		var roleids = document.getElementsByName("roleid");
//		var rolenames = document.getElementsByName("rolename");
//		var roledescs = document.getElementsByName("roledesc");
//		var resultPanel = this.resultPanel;
//		var resultStore = resultPanel.getStore();
//		if (roleids) {
//			for (var index = 0; index < roleids.length; index++) {
//				var roleId = roleids[index].value;
//				var roleName = rolenames[index].value;
//				var roleDesc = roledescs[index].value;
//				var newData = {
//					roleId : roleId,
//					roleName : roleName,
//					roleDesc : roleDesc
//				};
//				var newRecord = new resultStore.recordType(newData);
//				resultPanel.stopEditing();
//				resultStore.add(newRecord);
//			}
//		}

		if (this.roleIds) {
			var resultStore = this.resultPanel.getStore();
			var arrRoleIds = this.roleIds.split(',');
			var arrRoleName = this.roleName.split(',');
			if(arrRoleIds[0]==""){
				var len = this.roleIds.length;
				this.roleIds = this.roleIds.substring(1, len - 1);
				arrRoleIds = this.roleIds.split(',');
			}
			for (var index = 0; index < arrRoleIds.length; index++) {				
				var roleId = arrRoleIds[index];
				if(roleId!=""){
					var roleName = arrRoleName[index];
					var newData = {
						roleId : roleId,
						roleName : roleName,
						roleDesc : ''
					};
					var newRecord = new resultStore.recordType(newData);
					this.resultPanel.stopEditing();
					resultStore.add(newRecord);
				}
			}
		}

	},
	/**
	 * 搜索
	 */
	search : function() {
		$search({
					searchPanel : this.searchPanel,
					gridPanel : this.gridPanel
				});
	},
	/**
	 * 重置
	 */
	reset : function() {
		this.searchPanel.getForm().reset();
	},
	/**
	 * 确定
	 */
	confirm : function() {
		var roleIds = '';
		var roleNames = '';
		if(this.single){//选择单个用户
			var rows = this.gridPanel.getSelectionModel().getSelections();
			for (var i = 0; i < rows.length; i++) {
				if (i > 0) {
					roleIds += ',';
					roleNames += ',';
				}
				roleIds += rows[i].data.roleId;
				roleNames += rows[i].data.roleName;
			}
		}else{
			var rows = this.resultPanel.getStore();
			for (var i = 0; i < rows.getCount(); i++) {
				if (i > 0) {
					roleIds += ',';
					roleNames += ',';
				}
				roleIds += rows.getAt(i).data.roleId;
				roleNames += rows.getAt(i).data.roleName;
			}
		}
		if (this.callback) {
			this.callback.call(this.scope, roleIds, roleNames);
		}
		this.close();
	},
	/**
	 * 添加所有
	 */
	addAll : function() {
		var gridPanel = this.gridPanel;
		var resultPanel = this.resultPanel;
		var resultStore = resultPanel.getStore();
		var gridRows = gridPanel.getSelectionModel().getSelections();
		for (var i = 0; i < gridRows.length; i++) {
			var roleId = gridRows[i].data.roleId;
			var roleName = gridRows[i].data.roleName;
			var roleDesc = gridRows[i].data.roleDesc;
			var isExist = false;
			// 查找是否存在该记录
			for (var j = 0; j < resultStore.getCount(); j++) {
				if (resultStore.getAt(j).data.roleId == roleId) {
					isExist = true;
					break;
				}
			}
			if (!isExist) {
				var newData = {
					roleId : roleId,
					roleName : roleName,
					roleDesc : roleDesc
				};
				var newRecord = new resultStore.recordType(newData);
				resultPanel.stopEditing();
				resultStore.add(newRecord);
			}
		}
	},

	/**
	 * 移除所有
	 */
	removeAll : function() {
		var resultPanel = this.resultPanel;
		var rows = resultPanel.getSelectionModel().getSelections();
		var store = resultPanel.getStore();
		for (var i = 0; i < rows.length; i++) {
			resultPanel.stopEditing();
			store.remove(rows[i]);
		}
	},

	/**
	 * 双击选择角色
	 * 
	 * @param grid
	 * @param rowIndex
	 * @param e
	 */
	roleForSelPanelDblClick : function(grid, rowIndex, e) {
		var store = grid.getStore();
		var record = store.getAt(rowIndex);
		var selStore = this.resultPanel.getStore();
		for (var i = 0; i < selStore.getCount(); i++) {
			if (selStore.getAt(i).data.roleId == record.data.roleId){
				Ext.ux.Toast.msg('操作信息', '选项已被选中！');
				return;
			}
		}
		var recordType = selStore.recordType;
		selStore.add(new recordType(record.data));
	},

	/**
	 * 双击删除已选角色
	 * 
	 * @param grid
	 * @param rowIndex
	 * @param e
	 */
	roleForResPanelDblClick : function(grid, rowIndex, e) {
		var selStore = this.resultPanel.getStore();
		selStore.removeAt(rowIndex);
	}

});