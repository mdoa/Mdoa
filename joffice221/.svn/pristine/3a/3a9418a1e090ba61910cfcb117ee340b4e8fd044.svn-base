
/**
 * 授权列表
 * 
 * @class KnowledgePrivilegeWin
 * @extends Ext.Window
 */
KnowledgePrivilegeWin = Ext.extend(Ext.Window, {
	// 构造的方法
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		this.initUI();
		KnowledgePrivilegeWin.superclass.constructor.call(this, {
					title : this.folderName + '-授权列表',
					iconCls : 'menu-folder-shared',
					width : 710,
					height : 500,
					modal : true,
					maximizable : true,
					layout : 'fit',
					items : [this.gridPanel],
					buttonAlign : 'center',
					buttons : [{
								xtype : 'button',
								text : '关闭',
								scope : this,
								iconCls : 'btn-cancel',
								handler : function() {
									this.close();
								}
							}]

				});
	},
	// 初始化组件
	initUI : function() {
		/**
		 * 鼠标选中
		 */
		var onMouseDown = function(e, t) {
			if (t.className
					&& t.className.indexOf('x-grid3-cc-' + this.id) != -1) {
				e.stopEvent();
				var index = this.grid.getView().findRowIndex(t);
				var cindex = this.grid.getView().findCellIndex(t);
				var record = this.grid.store.getAt(index);
				var field = this.grid.colModel.getDataIndex(cindex);

				if (field != 'rightR') {
					var e = {
						grid : this.grid,
						record : record,
						field : field,
						originalValue : record.data[this.dataIndex],
						value : !record.data[this.dataIndex],
						row : index,
						column : cindex,
						cancel : false
					};
					if (this.grid.fireEvent("validateedit", e) !== false
							&& !e.cancel) {
						delete e.cancel;
						record
								.set(this.dataIndex,
										!record.data[this.dataIndex]);
						this.grid.fireEvent("afteredit", e);
					}

				} else {
					Ext.ux.Toast.msg("信息提示", "可读为基本权限！");
				}

			}

		};

		/**
		 * 选择可读
		 */
		var checkColumnR = new Ext.grid.CheckColumn({
					id : 'read',
					header : '可读',
					dataIndex : 'rightR',
					width : 55,
					onMouseDown : onMouseDown
				});
		/**
		 * 选择可修改
		 */
		var checkColumnM = new Ext.grid.CheckColumn({
					header : '可修改',
					dataIndex : 'rightU',
					width : 55,
					onMouseDown : onMouseDown
				});
		/**
		 * 选择可删除
		 */
		var checkColumnD = new Ext.grid.CheckColumn({
					header : '可删除',
					dataIndex : 'rightD',
					width : 55,
					onMouseDown : onMouseDown
				});

		// 列表面板
		this.gridPanel = new HT.EditorGridPanel({
			region : 'center',
			tbar : [{
						iconCls : 'btn-add',
						text : '添加文件夹权限',
						scope : this,
						handler : this.addDocPrivilege
					}, '-', {
						iconCls : 'btn-del',
						text : '删除文件夹权限',
						scope : this,
						handler : this.delRecords
					}],
			// 使用RowActions
			rowActions : true,
			checkColumn : [checkColumnR, checkColumnM, checkColumnD],
			url : __ctxPath + '/document/listDocPrivilege.do',
			baseParams : {
				'Q_docFolder.folderId_L_EQ' : this.folderId
			},
			fields : [{
						name : 'privilegeId',
						type : 'int'
					}, {
						name : 'folderName',
						mapping : 'folderName'
					}
					// ,'docId'
					, 'rightR', 'rightU', 'rightD', 'udrId', 'udrName', 'flag'],
			columns : [
					{
						header : 'privilegeId',
						dataIndex : 'privilegeId',
						hidden : true
					},
					{
						header : '名称',
						dataIndex : 'udrName'
					},
					{
						header : '属性',
						dataIndex : 'flag',
						renderer : function(value, metadata, record) {
							if (value == 1) {
								return '<img title="员工" src="' + __ctxPath
										+ '/images/flag/user.jpg"/>';
							}
							if (value == 2) {
								return '<img title="部门" src="' + __ctxPath
										+ '/images/flag/department.jpg"/>';
							}
							if (value == 3) {
								return '<img title="角色" src="' + __ctxPath
										+ '/images/flag/role.jpg"/>';
							}
						}
					}, checkColumnR, checkColumnM, checkColumnD,
					new Ext.ux.grid.RowActions({
								header : '管理',
								width : 50,
								actions : [{
											iconCls : 'btn-del',
											qtip : '删除',
											style : 'margin:0 3px 0 3px',
											fn : function(record) {
												if (isGranted('_BookDel'))
													return true;
												return false;
											}
										}],
								listeners : {
									scope : this,
									'action' : this.onRowAction
								}
							})],
			listeners : {
				scope : this,
				'afteredit' : this.afteredit
			}
		});
	},
	/**
	 * 监听编辑修改事件
	 * 
	 * @param {}
	 *            e
	 */
	afteredit : function(e) {
		Ext.Ajax.request({
					url : __ctxPath + '/document/changeDocPrivilege.do',
					params : {
						field : e.field,
						fieldValue : e.value,
						privilegeId : e.record.data.privilegeId
					},
					success : function() {
						Ext.ux.Toast.msg("信息提示", "设置成功！");
					},
					failure : function() {
						Ext.Msg.show({
									title : '错误提示',
									msg : '修改数据发生错误,操作将被回滚!',
									fn : function() {
										e.record.set(e.field, e.originalValue);
									},
									buttons : Ext.Msg.OK,
									icon : Ext.Msg.ERROR
								});

					}

				});
	},
	/**
	 * 按IDS删除记录
	 * 
	 * @param {}
	 *            ids
	 */
	delByIds : function(ids) {
		$postDel({
					url : __ctxPath + '/document/multiDelDocPrivilege.do',
					ids : ids,
					grid : this.gridPanel
				});
	},
	/**
	 * 删除多条记录
	 */
	delRecords : function() {
		$delGridRs({
					url : __ctxPath + '/document/multiDelDocPrivilege.do',
					idName : 'privilegeId',
					grid : this.gridPanel
				});
	},
	/**
	 * 增加文件夹权限
	 */
	addDocPrivilege : function() {
		var forlderId = this.folderId;
		if (forlderId != null && forlderId > 0) {
			new DocFolderSharedForm({
						folderId : this.folderId,
						folderName : this.folderName,
						scope : this,
						callback : function() {
							this.gridPanel.getStore().reload();
						}
					}).show();
		} else {
			Ext.ux.Toast.msg('提示', '请选择文件夹!');
		}
	},
	/**
	 * 管理列中的事件处理
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
				this.delByIds.call(this, record.data.privilegeId);
				break;
			default :
				break;
		};
	}
});