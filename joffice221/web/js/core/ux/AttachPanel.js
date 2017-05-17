/**
 * 附件管理 -包含附件添加，清除，清除所选
 * 
 * @class HT.AttachPanel
 * @extends Ext.Panel
 */
HT.AttachPanel = Ext.extend(Ext.Panel, {
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		this.scope = this.scope ? this.scope : this;
		this.initUI();
		HT.AttachPanel.superclass.constructor.call(this, {
					layout : 'hbox',
					border : false,
					width : this.leftWidth ? this.leftWidth + 250 : 650,
					layoutConfig : {
						padding : '5 5 5 0',
						align : 'top'
					},
					autoHeight : true,
					defaults : {
						margins : '0 5 0 0'
					},
					items : [
							// {
							// xtype : 'label',
							// text : conf.fieldLabel
							// ? conf.fieldLabel
							// : '附件:',
							// width : 100
							// },
							this.attachPanel, {
								xtype : 'button',
								iconCls : 'menu-attachment',
								scope : this,
								handler : this.addFile,
								text : '添加'
							}, {
								xtype : 'button',
								iconCls : 'reset',
								scope : this,
								text : '清除所选',
								handler : this.clearSelectedFiles
							}, {
								xtype : 'button',
								iconCls : 'btn-cancel',
								scope : this,
								handler : this.clearFile,
								text : '清除所有'
							}]
				});
	},
	initUI : function() {
		this.attachPanel = new HT.GridPanel({
					url : __ctxPath + '/system/loadByIdsFileAttach.do',
					root : this.root ? this.root : 'fileAttachs',
					fields : [{
								type : 'int',
								name : 'fileId'
							}, 'fileName'],
					bodyStyle : 'padding:4px 4px 4px 0px',
					name : 'fileAttachPanel',
					autoHeight : true,
					showPaging : false,
					isShowTbar : false,
					rowActions : true,
					width : this.leftWidth ? this.leftWidth : 400,
					hideHeaders : true,
					columns : [{
								header : 'fileId',
								// width: 10,
								hidden : true,
								dataIndex : 'fileId'
							}, {
								header : 'fileName',
								dataIndex : 'fileName',
								// width: 200,
								renderer : function(value, metadata, record) {
									return '<a href="' + __ctxPath
											+ '/file-download?fileId='
											+ record.data.fileId
											+ '", target="_blank">' + value
											+ '</a>';
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
				});
		this.attachPanel.addListener('rowdblclick', this.rowClick);
	},
	/**
	 * 添加附件
	 */
	addFile : function() {
		var panel = this.attachPanel;
		var outerpanel = this;
		App.createUploadDialog({
					file_cat : this.fileCat ? this.fileCat : '',
					scope : this.scope,
					callback : function(data) {
						var store = panel.getStore();
						var Plant = panel.getStore().recordType;
						for (var i = 0; i < data.length; i++) {
							var p = new Plant();
							p.set('fileId', data[i].fileId);
							p.set('fileName', data[i].fileName);
							p.commit();
							store.insert(store.getCount(), p);
						}
						panel.getView().refresh();
						outerpanel.doLayout();
					}
				}).show();
	},
	/**
	 * 清除附件
	 */
	clearFile : function() {
		this.attachPanel.getStore().removeAll();
		this.attachPanel.getView().refresh();
		this.fileIds = [];
		this.fileNames = [];
		this.doLayout();
	},
	/**
	 * 清除所选附件
	 */
	clearSelectedFiles : function() {
		var store = this.attachPanel.getStore();
		var selRs = this.attachPanel.getSelectionModel().getSelections();
		for (var i = 0; i < selRs.length; i++) {
			store.remove(selRs[i]);
		}
		this.attachPanel.getView().refresh();
		this.doLayout();
	},
	// GridPanel行点击处理事件
	rowClick : function(grid, rowindex, e) {
		grid.getSelectionModel().each(function(rec) {
					FileAttachDetail.show(rec.data.fileId);
				},this);
	},
	// 删除事件
	removeRs : function(record) {
		var store = this.attachPanel.getStore();
		store.remove(record);
		this.attachPanel.getView().refresh();
		this.doLayout();
	},
	// 行的Action
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.removeRs.call(this, record);
				break;
			default :
				break;
		}
	},
	getFileIds : function() {
		var store = this.attachPanel.getStore();
		var fileIds = '';
		for (var i = 0; i < store.getCount(); i++) {
			var record = store.getAt(i);
			fileIds += record.get('fileId') + ',';
		}
		return fileIds;
	},
	getFileNames : function() {
		var store = this.attachPanel.getStore();
		var fileNames = '';
		for (var i = 0; i < store.getCount(); i++) {
			var record = store.getAt(i);
			fileNames += record.get('fileName') + ',';
		}
		return fileNames;
	},
	getAttachStore : function() {
		return this.attachPanel.getStore();
	},
	loadByResults : function(results) {
		this.attachPanel.getStore().loadData(results);
		this.attachPanel.getView().refresh();
		this.doLayout();
	},
	loadByIds : function(ids) {
		this.attachPanel.getStore().load({
					params : {
						ids : ids
					},
					callback : function() {
						this.doLayout();
					},
					scope : this
				});
	}
});

Ext.reg('attachpanel', HT.AttachPanel);