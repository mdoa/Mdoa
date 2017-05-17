/**
 * 部门选择器
 */
DepDialog = Ext.extend(Ext.Window, {
	constructor : function(conf) {
		Ext.applyIf(this, conf);

		this.scope = this.scope ? this.scope : this;
		// 默认为多单选择部门
		this.single = this.single != null ? this.single : false;
		this.initUI();
		DepDialog.superclass.constructor.call(this, {
					title : this.title ? this.title : '部门选择器',
					iconCls : 'menu-department',
					width : 630,
					height : 380,
					maximizable : true,
					modal : true,
					layout : 'border',
					modal : true,
					border : false,
					buttonAlign : 'center',
					items : [this.panel],
					buttons : [{
								iconCls : 'btn-ok',
								text : '确定',
								scope : this,
								handler : this.confirm
							}, {
								text : '取消',
								iconCls : 'btn-cancel',
								handler : this.close,
								scope : this
							}]
				});
	},// end constructor

	/**
	 * 初始化UI
	 */
	initUI : function() {

		this.treePanel = new htsoft.ux.TreePanelEditor({
					title : '部门信息显示',
					region : 'west',
					width : 180,
					height : 300,
					split : true,
					collapsible : true,
					autoScroll : true,
					url : __ctxPath + '/system/listDepartment.do',
					scope : this,
					onclick : this.treeClick
				});
		// ---------------------------------start grid
		// panel--------------------------------
		this.gridPanel = new HT.GridPanel({
					width : 400,
					height : 300,
					region : 'center',
					title : '部门列表(双击选中)',
					shim : true,
					trackMouseOver : true,
					disableSelection : false,
					singleSelect : true,
					loadMask : true,
					viewConfig : {
						forceFit : true,
						enableRowBody : false,
						showPreview : false
					},
					url : __ctxPath + '/system/selectDepartment.do',
					fields : [{
								name : 'depId',
								type : 'int'
							}, 'depName', {
								name : 'depLevel',
								type : 'int'
							}, 'chargeIds', 'chargeNames'],
					columns : [{
								header : 'depId',
								dataIndex : 'depId',
								hidden : true
							}, {
								header : "部门名称",
								dataIndex : 'depName',
								scope : this,
								renderer : this.depNameRenderer,
								width : 60
							}, {
								dataIndex : 'chargeIds',
								hidden : true
							}, {
								dataIndex : 'chargeNames',
								hidden : true
							}],
					listeners : {
						scope : this,
						'rowdblclick' : this.rowdblclick
					}
				});

		this.selectedDepGrid = new HT.GridPanel({
					title : '已选部门(双击删除)',
					layout : 'form',
					region : 'center',
					width : '100%',
					height : '100%',
					autoWidth : true,
					autoHeight : true,
					autoScroll : true,
					border : false,
					trackMouseOver : true,
					showPaging : false,
					fields : ['depId', 'depName', 'chargeIds', 'chargeNames'],
					columns : [{
								header : "部门名称",
								dataIndex : 'depName'
							}, {
								dataIndex : 'chargeIds',
								hidden : true
							}, {
								dataIndex : 'chargeNames',
								hidden : true
							}],
					listeners : {
						scope : this,
						'rowdblclick' : this.selectRowdblclick
					}
				}); // end of this selectedUserGrid

		if (this.depIds) {
			var selStore = this.selectedDepGrid.getStore();
			var arrDepIds = this.depIds.split(',');
			var arrDepNames = this.depNames.split(',');
			if(arrDepIds[0]==""){
				var len = this.depIds.length;
				this.depIds = this.depIds.substring(1, len - 1);
				arrDepIds = this.depIds.split(',');
			}
			for (var i = 0; i < arrDepIds.length; i++) {
				if(arrDepIds[i]!=""){
					var newData = {
						depId : arrDepIds[i],
						depName : arrDepNames[i]
					};
					var newRecord = new selStore.recordType(newData);
					this.selectedDepGrid.stopEditing();
					selStore.add(newRecord);
				}
			}
		};

		// 多选添加的面板
		this.selectedPanel = new Ext.Panel({
					layout : 'border',
					region : 'east',
					width : '200',
					height : '100%',
					border : false,
					autoScroll : true,
					items : [new Ext.Panel({
										region : 'west',
										frame : true,
										width : 40,
										layout : {
											type : 'vbox',
											pack : 'center',
											align : 'stretch'
										},
										defaults : {
											height : 20,
											xtype : 'button'
										},
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
									}), {
								region : 'center',
								autoScroll : true,
								items : [this.selectedDepGrid]
							}]
				}); // selectedPanel

		this.panel = new Ext.Panel({
					id : 'contactPanel',
					layout : 'border',
					region : 'center',
					border : false,
					anchor : '96%,96%',
					items : [this.treePanel, this.gridPanel]
				}); // end of this contactPanel
		//添加：多选面板
		if(this.single != null && this.single == true){
			this.panel.add(this.selectedPanel);
			this.panel.doLayout();
		}
		
	},// initUI
	/**
	 * 部门树选择
	 * 
	 * @param {}
	 *            node
	 */
	treeClick : function(node) {
		if (!Ext.isEmpty(node)) {
			var store = this.gridPanel.getStore();
			store.baseParams = {
				depId : node.id
			};
			this.gridPanel.getBottomToolbar().moveFirst();
		}
	},
	/**
	 * 部门名字渲染
	 * 
	 * @param {}
	 *            value
	 * @param {}
	 *            metadata
	 * @param {}
	 *            record
	 * @return {}
	 */
	depNameRenderer : function(value, metadata, record) {
		var str = '';
		var level = record.data.depLevel;
		if (level != null && !isNaN(level)) {
			for (var i = 2; i <= level; i++) {
				str += '<img src="' + __ctxPath + '/images/system/down.gif"/>';
			}
		}
		str += value;
		return str;
	},
	/**
	 * 部门选择双击事件
	 * 
	 * @param grid
	 * @param rowIndex
	 * @param e
	 */
	rowdblclick : function(grid, rowIndex, e) {
		var store = grid.getStore();
		var record = store.getAt(rowIndex);
		var selStore = this.selectedDepGrid.getStore();
		for (var i = 0; i < selStore.getCount(); i++) {
			if (selStore.getAt(i).data.depId == record.data.depId){
				Ext.ux.Toast.msg('操作信息', '选项已被选中！');
				return;
			}
		}
		var recordType = selStore.recordType;
		selStore.add(new recordType(record.data));
	},
	/**
	 * 已选择部门双击事件
	 * 
	 * @param grid
	 * @param e
	 */
	selectRowdblclick : function(grid, e) {
		var store = this.selectedDepGrid.getStore();
		var rows = this.selectedDepGrid.getSelectionModel().getSelections();
		for (var i = 0; i < rows.length; i++) {
			this.selectedDepGrid.stopEditing();
			store.remove(rows[i]);
		}
	},
	/**
	 * 添加所有
	 */
	addAll : function() {
		var contactGrid = this.gridPanel;
		var selGrid = this.selectedDepGrid;
		var selStore = selGrid.getStore();
		var rows = contactGrid.getSelectionModel().getSelections();
		for (var i = 0; i < rows.length; i++) {
			var depId = rows[i].data.depId;
			var depName = rows[i].data.depName;
			var chargeIds = rows[i].data.chargeIds;
			var chargeNames = rows[i].data.chargeNames;
			var isExist = false;
			// 查找是否存在该记录
			for (var j = 0; j < selStore.getCount(); j++) {
				if (selStore.getAt(j).data.depId == depId) {
					isExist = true;
					break;
				}
			}
			if (!isExist) {
				var newData = {
					depId : depId,
					depName : depName,
					chargeIds : chargeIds,
					chargeNames : chargeNames
				};
				var newRecord = new selStore.recordType(newData);
				selGrid.stopEditing();
				selStore.add(newRecord);
			}
		}
	},

	/**
	 * 移除所有
	 */
	removeAll : function() {
		var selGrid = this.selectedDepGrid;
		var rows = selGrid.getSelectionModel().getSelections();
		var selStore = selGrid.getStore();
		for (var i = 0; i < rows.length; i++) {
			selGrid.stopEditing();
			selStore.remove(rows[i]);
		}
	},
	/**
	 * 确认
	 */
	confirm : function() {
		var depIds = '';
		var depNames = '';
		var chargeIds = '';
		var chargeNames = '';
		if (this.single) {// 选择多个
			var selStore = this.selectedDepGrid.getStore();
			for (var i = 0; i < selStore.getCount(); i++) {
				if (i > 0) {
					depIds += ',';
					depNames += ',';
					chargeIds += ',';
					chargeNames += ',';
				}
				depIds += selStore.getAt(i).data.depId;
				depNames += selStore.getAt(i).data.depName;
				chargeIds += selStore.getAt(i).data.chargeIds;
				chargeNames += selStore.getAt(i).data.chargeNames;
			}
		} else {// 选择单个
			var grid = this.gridPanel;
			var rows = grid.getSelectionModel().getSelections();
			for (var i = 0; i < rows.length; i++) {
				depIds = rows[i].data.depId;
				depNames = rows[i].data.depName;
				chargeIds = rows[i].data.chargeIds;
				chargeNames = rows[i].data.chargeNames;
			}
		}

		if (this.callback) {
			this.callback.call(this.scope, depIds, depNames);
		}
		this.close();
	}// end confirm
});