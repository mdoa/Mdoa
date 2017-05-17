Ext.ns('ArchivesDraftWin');
/**
 * @createtime:2010-01-20
 * @author csx
 * @description 公文拟稿发文界面 - 公文修改界面
 * @class ArchivesDraftWin
 * @extends Ext.Panel
 */
ArchivesDraftWin = Ext.extend(Ext.Window, {
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		this.initUI();
		ArchivesDraftWin.superclass.constructor.call(this, {
					title : '发文修改',
					id : 'ArchivesDraftWin',
					iconCls : 'menu-archive-draft',
					layout : 'fit',
					bodyStyle : 'padding:2px 20px 2px 2px;',
					border : false,
					modal : true,
					height : 530,
					width : 800,
					maximizable : true,
					autoScroll : true,
					buttonAlign : 'center',
					buttons : [{
								text : '保存',
								iconCls : 'btn-save',
								handler : this.save,
								scope : this
							}, {
								text : '关闭',
								iconCls : 'btn-del',
								handler : this.closePanel,
								scope : this
							}],
					items : [this.formPanel]
				});
	},
	/**
	 * 保存公文的方法,传入公文状态作为参数
	 * @param {} _status
	 */
	onSave : function(win) {
		if (this.formPanel.getForm().isValid()) {
			// 发文的文档附件
			var docParams = [];
			for (var i = 0, cnt = this.docGridPanel.getStore().getCount(); i < cnt; i++) {
				docParams.push(this.docGridPanel.getStore().getAt(i).data);
			}
			var detailPanel = this.detailPanel;
			this.formPanel.getForm().submit({
						method : 'POST',
						waitMsg : '正在提交数据...',
						params : {
							docs : Ext.encode(docParams)
						},
						success : function(fp, action) {
							Ext.ux.Toast.msg('操作信息', '成功保存信息！');
							if(detailPanel !=null && detailPanel !='undefined'){
								detailPanel.getUpdater().refresh();
							}
							win.close();
						},
						failure : function(fp, action) {
							Ext.MessageBox.show({
										title : '操作信息',
										msg : '信息保存出错，请联系管理员！',
										buttons : Ext.MessageBox.OK,
										icon : Ext.MessageBox.ERROR
									});
						}
					});
		}
	},
	/**
	 * 初始化组件
	 */
	initUI : function() {
		// 初始化附件文档
		this.docGridPanel = new HT.GridPanel({
			id : 'archiveDocGrid',
			title : '公文正文',
			iconCls : 'menu-attachment',
			tbar : [{
						text : '按模板在线添加',
						iconCls : 'menu-archive-template',
						handler : this.addArchiveDoc,
						scope : this
					}, '-', {
						text : '在线添加',
						iconCls : 'btn-edit-online',
						handler : this.addNewArchiveDoc,
						scope : this
					}, '-', {
						text : '上传文档',
						iconCls : 'btn-upload',
						handler : this.uploadArchiveDoc,
						scope : this
					}, '-', {
						text : '删除附件文档',
						iconCls : 'btn-del',
						scope : this,
						handler : this.deleteArchiveDoc
					}],
			singleSelect : true,
			showPaging : false,
			autoHeight : true,
			// 使用RowActions
			rowActions : true,
			url : __ctxPath + '/archive/listArchivesDoc.do?archivesId='
					+ this.archivesId,
			fields : [{
						name : 'docId',
						type : 'int'
					}, 'fileAttach', 'creator', 'creatorId', 'menderId',
					'mender', 'docName', 'docStatus', 'curVersion', 'docPath',
					'updatetime', 'createtime'],
			columns : [{
						dataIndex : 'docId',
						hidden : true
					}, {
						dataIndex : 'fileAttach',
						hidden : true,
						renderer : function(value) {
							// return value.fileId;
						}
					}, {
						dataIndex : 'docStatus',
						hidden : true
					}, {
						dataIndex : 'menderId',
						hidden : true
					}, {
						dataIndex : 'creatorId',
						hidden : true
					}, {
						dataIndex : 'docName',
						width : 150,
						header : '文档名称'
					}, {
						dataIndex : 'docPath',
						header : '文档路径',
						width : 300
					}, {
						dataIndex : 'curVersion',
						header : '当前版本',
						renderer : function(value) {
							return '第' + value + '版';
						}
					}, new Ext.ux.grid.RowActions({
								header : '管理',
								width : 200,
								actions : [{
											iconCls : 'btn-archive-history',
											qtip : '历史版本',
											style : 'margin:0 3px 0 3px'
										}, {
											iconCls : 'menu-archive-issue-manage',
											qtip : '查看修改文档',
											style : 'margin:0 3px 0 3px'
										}],
								listeners : {
									scope : this,
									'action' : this.onRowAction
								}
							})]
		});

		// ======== 初始化表单===============
		this.formPanel = new Ext.FormPanel({
			bodyStyle : 'padding: 4px 8px 4px 8px',
			layout : 'form',
			autoHeight : true,
			url : __ctxPath + '/archive/saveIssueArchives.do',
			items : [{
						name : 'archives.archivesId',
						id : 'archivesWin.archivesId',
						xtype : 'hidden',
						value : this.archivesId == null ? '' : this.archivesId
					}, {
						xtype : 'compositefield',
						fieldLabel : '所属类型',
						items : [{
									name : 'archives.typeName',
									xtype : 'textfield',
									width : 250,
									readOnly : true,
									allowBlank : false
								}, {
									xtype : 'button',
									text : '选择类型',
									iconCls : 'btn-select',
									scope : this,
									handler : function() {
										var fPanel = this;
										new GlobalTypeDialog({
											catKey : 'ARCHIVES_TYPE',
											isSingle : true,
											callback : function(typeId,
													typeName) {
												fPanel
														.getCmpByName('archives.typeId')
														.setValue(typeId);
												fPanel
														.getCmpByName('archives.typeName')
														.setValue(typeName);
											}
										}).show();
									}

								}]
					}, {
						xtype : 'fieldset',
						title : '发文设置',
						border : true,
						defaults : {
							anchor : '98%,98%'
						},
						items : [{
									layout : 'form',
									// columnWidth : .4,
									border : false,
									items : {
										fieldLabel : '发文字号',
										name : 'archives.archivesNo',
										id : 'archivesWin.archivesNo',
										xtype : 'textfield',
										allowBlank : false,
										anchor : '100%'
									}
								}, {
									layout : 'form',
									border : false,
									style : 'padding:0px 0px 7px 0px;',
									defaults : {
										anchor : '96%,96%'
									},
									items : [{
										layout : 'column',
										border : false,
										items : [{
											layout : 'form',
											anchor : '99%',
											style : 'padding:0px 0px 0px 0px;',
											border : false,
											items : {
												fieldLabel : '密级',
												width : 200,
												name : 'archives.privacyLevel',
												id : 'archivesWin.privacyLevel',
												triggerAction : 'all',
												lazyRender : true,
												allowBlank : false,
												emptyText : '选择密级',
												xtype : 'combo',
												store : ['普通', '秘密', '机密', '绝密']
											}
										}, {
											layout : 'form',
											border : false,
											items : {
												fieldLabel : '紧急程度',
												width : 200,
												name : 'archives.urgentLevel',
												id : 'archivesWin.urgentLevel',
												triggerAction : 'all',
												lazyRender : true,
												allowBlank : false,
												emptyText : '选择紧急程度',
												xtype : 'combo',
												store : ['普通', '紧急', '特急', '特提']
											}
										}]
									}]
								}, {
									fieldLabel : '文件标题',
									name : 'archives.subject',
									id : 'archivesWin.subject',
									xtype : 'textfield',
									allowBlank : false
								}, {
									xtype : 'container',
									layout : 'column',
									style : 'padding-left:0px;margin-left:0px;',
									height : 30,
									defaults : {
										border : false
									},
									items : [{
												xtype : 'label',
												text : '发文机关或部门',
												style : 'padding:0px 0px 0px 0px;',
												width : 105
											}, {
												name : 'archives.issueDep',
												id : 'archivesWin.issueDep',
												xtype : 'textfield',
												width : '70%',
												allowBlank : false,
												readOnly : true
											}, {
												name : 'archives.depId',
												id : 'archivesWin.depId',
												xtype : 'hidden'
											}, {
												xtype : 'button',
												iconCls : 'menu-department',
												text : '选择部门',
												handler : function() {
													DepSelector.getView(
															function(depId,
																	depName) {
																Ext
																		.getCmp('archivesWin.issueDep')
																		.setValue(depName);
																Ext
																		.getCmp('archivesWin.depId')
																		.setValue(depId);
															}, true).show();
												}
											}]
								}, {
									xtype : 'container',
									layout : 'column',
									style : 'padding:0px 0px 8px 0px;margin-left:0px;',
									defaults : {
										border : false
									},
									items : [{
												xtype : 'label',
												style : 'padding:0px 0px 0px 0px;',
												text : '接收单位或部门',
												width : 105
											}, {
												xtype : 'textarea',
												name : 'archives.recDepNames',
												width : '70%',
												readOnly : true,
												id : 'archivesWin.recDepNames'
											}, {
												xtype : 'hidden',
												name : 'archives.recDepIds',
												id : 'archivesWin.recDepIds'
											}, {
												xtype : 'button',
												iconCls : 'menu-department',
												text : '选择部门',
												handler : function() {
													DepSelector.getView(
															function(depIds,
																	depNames) {
																Ext
																		.getCmp('archivesWin.recDepIds')
																		.setValue(depIds);
																Ext
																		.getCmp('archivesWin.recDepNames')
																		.setValue(depNames);
															}, false).show();
												}
											}]
								}, {
									fieldLabel : '主题词',
									name : 'archives.keywords',
									id : 'archivesWin.keywords',
									xtype : 'textfield'
								}, {
									fieldLabel : '内容简介',
									name : 'archives.shortContent',
									id : 'archivesWin.shortContent',
									xtype : 'textarea'
								}, {
									fieldLabel : '公文来源',
									name : 'archives.sources',
									id : 'archivesWin.sources',
									xtype : 'textfield'
								}, {
									name : 'archives.typeId',
									xtype : 'hidden'
								}]
						// end of the field set items
					},// end of fieldset
					this.docGridPanel]
		});
		// 加载表单对应的数据
		if (!Ext.isEmpty(this.archivesId)) {
			var fPanel = this.formPanel;
			this.formPanel.loadData({
						root : 'data',
						preName : 'archives',
						url : __ctxPath
								+ '/archive/getIssueArchives.do?archivesId='
								+ this.archivesId,
						success : function(form, action) {
						},
						failure : function(form, action) {
						}
					});
		}
	},
	/**
	 * 添加附件文档
	 */
	addArchiveDoc : function() {
		new ArchTemplateSelector({
					scope : this,
					callback : function(fileId) {
						// 选择打开文档
						new ArchivesDocForm({
									scope : this,
									fileId : fileId,
									callback : function(archivesDoc) {
										// 返回文档附加记录
										this.insertNewDoc(archivesDoc);
									}
								}).show();
					}
				}).show();
	},
	/**
	 * 在线添加
	 * </p>
	 * 添加新的公文文档，以一个空白的文档开始
	 */
	addNewArchiveDoc : function() {
		new ArchivesDocForm({
					scope : this,
					callback : function(archivesDoc) {
						// 返回文档附加记录
						this.insertNewDoc(archivesDoc);
					}
				}).show();
	},

	/**
	 * 插入附加文件记录
	 * 
	 * @param {}
	 *            archivesDoc
	 */
	insertNewDoc : function(archivesDoc) {
		var store = this.docGridPanel.store;
		var orec;
		if (store.recordType) {
			orec = new store.recordType();
			orec.data = {};
			orec.data['docId'] = archivesDoc.docId;
			orec.data['fileId'] = archivesDoc.fileId;
			orec.data['docPath'] = archivesDoc.docPath;
			orec.data['docName'] = archivesDoc.docName;
			orec.data['curVersion'] = archivesDoc.curVersion
					? archivesDoc.curVersion
					: 1;
			orec.data.newRecord = true;
			orec.commit();
			store.add(orec);
		}
	},
	/**
	 * 上传附件
	 */
	uploadArchiveDoc : function() {
		var curView = this;
		var callback = function(data) {
			for (var i = 0; i < data.length; i++) {
				var archivesDoc = {
					docId : 0,// 用于标记尚未持久化的记录
					fileId : data[i].fileId,
					docPath : data[i].filePath,
					docName : data[i].fileName,
					curVersion : 1
				};
				curView.insertNewDoc(archivesDoc);
			}
		};
		var dialog = App.createUploadDialog({
					file_cat : 'archive',
					scope : this,
					callback : callback
				});
		dialog.show();
	},
	/**
	 * 查看公文附件
	 */
	detailArchivesDoc : function(record) {
		var path = record.data.docPath;
		var docId = record.data.docId;
		var fileId = null;
		if (record.data.fileAttach) {
			fileId = record.data.fileAttach.fileId;
		} else {
			fileId = record.data.fileId;
		}
		new ArchivesDocForm({
					fileId : fileId,
					docId : docId,
					docPath : path,
					scope : this,
					// 返回文档附加记录
					callback : function(archivesDoc) {
						this.docGridPanel.store.remove(record);
						this.insertNewDoc(archivesDoc);
					}
				}).show();
	},
	/**
	 * 删除附件文档
	 */
	deleteArchiveDoc : function() {
		$delGridRs({
					url :  __ctxPath + '/archive/multiDelArchivesDoc.do',
					grid : this.docGridPanel,
					idName : 'docId'
				});
	},
	attach : function(record) {
		new ArchivesDocHistoryWin({
					docId : record.data.docId,
					scope : this,
					// 返回文档附加记录
					callback : function(archivesDoc) {
						this.docGridPanel.store.remove(record);
						this.insertNewDoc(archivesDoc);
					}
				}).show();
	},

	/**
	 * 行的Action
	 * 
	 * @param grid
	 * @param record
	 * @param action
	 * @param row
	 * @param col
	 */
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-archive-history' :
				this.attach.call(this, record);
				break;
			case 'menu-archive-issue-manage' :
				this.detailArchivesDoc.call(this, record);
				break;
			default :
				break;
		}
	},
	/**
	 * 关闭Panel
	 */
	closePanel : function() {
		this.close();
	},
	/**
	 * 保存公文的方法,传入公文状态作为参数 </br>拟搞完成,发送给指定的人员核稿
	 */
	save : function() {
		this.onSave(this);
	}
});