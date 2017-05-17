/**
 * @author:
 * @class RollFileListView
 * @extends Ext.Panel
 * @description 附件管理
 * @company 杭州梦德软件有限公司
 * @createtime:
 */
RollFileListView = Ext.extend(Ext.grid.EditorGridPanel, {

	viewConfig : null,
	startIndex : 0,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		//this.viewConfig=new Array ();
		this.initUIComponents();
		// 调用父类构造
		RollFileListView.superclass.constructor.call(this, {
					id : 'RollFileListView',
					tbar : this.topbar,
					store : this.store,
					height : 200,
					plugins : this.rowActions,
					trackMouseOver : true,
					autoScroll : true,
					disableSelection : false,
					loadMask : true,
					clicksToEdit : 1,
					frame : false,
					cm : this.cm,
					sm : this.sm,
					autoExpandColumn : 'shortDesc',
					viewConfig : {
						forceFit : true,
						enableRowBody : false,
						showPreview : false
					},
					listeners : {
						'rowdblclick' : this.rowClick
					}

				});
	},// end of constructor
	// 初始化组件
	initUIComponents : function() {

		this.topbar = new Ext.Toolbar({
					items : [{
								xtype : 'tbtext',
								text : '附件'
							}, {
								xtype : 'tbseparator'
							}, {
								iconCls : 'btn-add',
								text : '添加',
								xtype : 'button',
								scope : this,
								handler : this.createRs
							}, {
								iconCls : 'btn-del',
								text : '删除',
								xtype : 'button',
								scope : this,
								handler : this.removeSelRs
							}
					]
				});
		this.store = new Ext.data.Store({
					proxy : new Ext.data.HttpProxy({
								url : __ctxPath + '/arch/listRollFileList.do'
							}),
					autoLoad : false,
					reader : new Ext.data.JsonReader({

								root : 'result',
								totalProperty : 'totalCounts',
								id : 'listId',
								fields : [{
											name : 'listId',
											type : 'int'
										}, 'sn','shortDesc','downLoads',
										'rollFileId', 'fileAttach'
										]
							}),
					remoteSort : true
				});

		this.store.setDefaultSort('sn', 'asc');
		this.store.on('load', function(store, records) {
			this.viewConfig=[];
			Ext.each(records, function(record) {
					this.viewConfig.push({
								fileName : record.data.fileAttach.fileName,
								filePath : record.data.fileAttach.filePath
							});
				
			}, this);
					
				

		}, this);

		this.rowActions = new Ext.ux.grid.RowActions({
					header : '管理',
					width : 15,
					actions : [
					{
								iconCls : 'btn-readdocument',
								qtip : '预览',
								style : 'margin:0 3px 0 3px'
							},
							{
								iconCls : 'btn-downLoad',
								qtip : '下载',
								style : 'margin:0 3px 0 3px'
							}, {
								iconCls : 'btn-up',
								qtip : '向上',
								style : 'margin:0 3px 0 3px'
							}, {
								iconCls : 'btn-last',
								qtip : '向下',
								style : 'margin:0 3px 0 3px'
							}]
				});
		this.rowActions.on('action', this.onRowAction, this);
		this.sm = new Ext.grid.CheckboxSelectionModel();
		this.cm = new Ext.grid.ColumnModel({
					columns : [
							this.sm
							, {
								header : 'listId',
								dataIndex : 'listId',
								hidden : true
							}, {
								header : '文件名称',
								width : 20,
								dataIndex : 'fileAttach',
								renderer : function(fileAttach) {
									return fileAttach.fileName;
								}
							}, {
								header : '概要',
								dataIndex : 'shortDesc',
								editor : {
									allowBlank : false,
									xtype : 'textarea'
								}
							}, {
								header : '下载次数',
								width : 10,
								dataIndex : 'downLoads'
							}, this.rowActions],
					defaults : {
						sortable : true,
						menuDisabled : false,
						width : 40
					}
				});

	},// end of the initComponents()

	// GridPanel行点击处理事件
	rowClick : function(grid, rowindex, e) {
//		grid.getSelectionModel().each(function(rec) {
//					new RollFileListForm({
//								listId : rec.data.listId
//							}).show();
//				});
	},
	// 创建记录
	createRs : function() {

		App.createUploadDialog({
			file_cat : 'arch/upload',
			callback : function(data) {
				var grid = Ext.getCmp('RollFileListView');
				var store = grid.getStore();
				grid.stopEditing();
				for (i = 0; i < data.length; i++) {
					var fileId = data[i].fileId;
					var fileName = data[i].fileName;
					var filePath = data[i].filePath;

					var recrod = new store.recordType();
					recrod.data = {};

					recrod.data['downLoads'] = 0;

					var fileAttach = {};
					Ext.applyIf(fileAttach, {
						fileId : fileId,
						fileName : fileName,
						filePath : filePath
					});

					recrod.data['fileAttach'] = fileAttach;

					store.insert(store.getCount(), recrod);
					recrod.markDirty();

				}
				store.commitChanges();
				grid.getView().refresh();
				grid.startEditing(0, 0);

			}
		}).show();

	},
	// 按ID删除记录
	removeByIds : function(listIds, fileIds) {
		Ext.Ajax.request({
					url : __ctxPath + '/arch/multiDelRollFileList.do',
					params : {
						listIds : listIds,
						fileIds : fileIds
					},
					method : 'POST',
					success : function(response, options) {
						Ext.ux.Toast.msg('操作信息', '成功删除该明细！');
						Ext.getCmp('RollFileListView').getStore().reload();
					},
					failure : function(response, options) {
						Ext.ux.Toast.msg('操作信息', '操作出错，请联系管理员！');
					}
				});

	},
	// 把选中ID删除
	removeSelRs : function() {
		Ext.Msg.confirm('信息确认', '注意：删除该附件将会影响其他所有引用该附件的页面预览不了，您确认要删除所选附件吗？', function(btn) {
					if (btn == 'yes') {
						var gridPanel = Ext.getCmp('RollFileListView');
						var store = gridPanel.getStore();
						var selectRecords = gridPanel.getSelectionModel()
								.getSelections();
						if (selectRecords.length == 0) {
							Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
							return;
						}
						var listIds = Array();
						var fileIds = Array();
						for (var i = 0; i < selectRecords.length; i++) {
							if (selectRecords[i].data.listId != ''&& selectRecords[i].data.listId != null) {
								listIds.push(selectRecords[i].data.listId);
								store.remove(selectRecords[i]);
							} else {
								fileIds.push(selectRecords[i].data.fileAttach.fileId);
								store.remove(selectRecords[i]);
							}
						}
						if(listIds>0||fileIds>0){
							this.removeByIds(listIds, fileIds);
						}
					}
				}, this);// end of comfirm

	},
	// 编辑Rs
	editRs : function(record) {
		var gridPanel = Ext.getCmp('RollFileListView');
		var record = gridPanel.getSelectionModel().getSelections();

		new RollFileListForm({
					listId : record[0].data.listId
				}).show();
	},
	saveSelRs : function() {

	},
	downLoad : function(record) {
		// FileAttachDetail.show(record.data.fileAttach.fileId);
		// <a
		// href="<%=request.getContextPath()%>/attachFiles/${fileAttach.filePath}"
		// target="_blank">下载</a>
		window.open(__ctxPath + '/attachFiles/'
				+ record.data.fileAttach.filePath);
		record.data.downLoads = record.data.downLoads + 1;
		//record.markDirty();
		Ext.getCmp('RollFileFormWin').save();
	},

	sn : function(grid, thisRow, nextRow) {
		var store = grid.getStore();

		var tihsR = store.getAt(thisRow);
		var nextR = store.getAt(nextRow);

		if (tihsR && nextR) {
			var thisSn = tihsR.get('sn');
			var nextSn = nextR.get('sn');

			tihsR.data.sn = nextSn;
			nextR.data.sn = thisSn;

			Ext.getCmp('RollFileFormWin').save('sn');
			grid.getView().refresh();
		}
	},
	viewFile:function(record){
		 FileAttachDetail.show(record.data.fileAttach.fileId);
	},
	// 行的Action
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-readdocument':
				this.viewFile.call(this,record);
				break;
			case 'btn-downLoad' :
				this.downLoad.call(this, record);
				break;
			case 'btn-up' :
				this.sn.call(this, grid, row, row - 1);
				break;
			case 'btn-last' :
				this.sn.call(this, grid, row, row + 1);
				break;
			default :
				break;

		}
	}
});
