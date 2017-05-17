Ext.ns('ReportTemplateView');
/**
 * 报表列表
 * 
 * @class ReportTemplateView
 * @extends Ext.Panel
 */
ReportTemplateView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		// 初始化组件
		this.initUI();
		// 调用父类构造
		ReportTemplateView.superclass.constructor.call(this, {
					id : 'ReportTemplateView',
					title : '报表列表',
					iconCls : 'menu-report',
					layout : 'border',
					items : [this.searchPanel, this.gridPanel]
				});
	},
	// 初始化组件
	initUI : function() {
		// 搜索面板
		this.searchPanel = new HT.SearchPanel({
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
					labelWidth : 135,
					items : [{
								fieldLabel : '请输入查询条件: 标题名',
								xtype : 'textfield',
								name : 'Q_title_S_LK',
								maxLength : 150
							}, {
								fieldLabel : '描述',
								xtype : 'textfield',
								name : 'Q_descp_S_LK',
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

		// 报表列表面板
		this.gridPanel = new HT.GridPanel({
			region : 'center',
			tbar : [{
						text : '添加报表',
						iconCls : 'btn-add',
						scope : this,
						handler : this.addReport,
						hidden : !isGranted('_ReportTemplateAdd')
					}, '-', {
						text : '删除报表',
						iconCls : 'btn-del',
						scope : this,
						handler : this.removeSelRs,
						hidden : !isGranted('_ReportTemplateDel')
					}],
			// 使用RowActions
			rowActions : true,
			sort : [{field:"reportId",direction:"DESC"}],
			url : __ctxPath + "/system/listReportTemplate.do",
			fields : [{
						name : 'reportId',
						type : 'int'
					}, 'title', 'descp', 'reportLocation', 'createtime',
					'updatetime', {
						name : 'isDefaultIn',
						type : 'int'
					}],
			columns : [{
						header : 'reportId',
						dataIndex : 'reportId',
						hidden : true
					}, {
						header : '标题',
						dataIndex : 'title'
					}, {
						header : '模版路径',
						dataIndex : 'reportLocation'
					}, {
						header : '创建时间',
						dataIndex : 'createtime'
					}, {
						header : '修改时间',
						dataIndex : 'updatetime'
					}, {
						header : '是否缺省',
						dataIndex : 'isDefaultIn',
						renderer : function(v) {
							if (v == 0) {
								return '否';
							} else if (v == 1) {
								return '是';
							} else {
								return v;
							}
						}
					}, new Ext.ux.grid.RowActions({
						header : '管理',
						width : 100,
						actions : [{
									iconCls : 'btn-preview',
									qtip : '查看',
									style : 'margin:0 3px 0 3px',
									fn : function(record) {
										if (isGranted('_ReportTemplateQuery'))
											return true;
										return false;
									}
								}, {
									iconCls : 'btn-setting',
									qtip : '设置查询参数',
									style : 'margin:0 3px 0 3px',
									fn : function(record) {
										if (isGranted('_ReportParamSetting'))
											return true;
										return false;
									}
								}, {
									iconCls : 'btn-del',
									qtip : '删除',
									style : 'margin: 0 2px 0 2px',
									fn : function(record) {
										if (record.data.isDefaultIn != 1
												&& isGranted('_ReportTemplateDel'))
											return true;
										return false;
									}
								}, {
									iconCls : 'btn-edit',
									qtip : '编辑',
									style : 'margin: 0 2px 0 2px',
									fn : function(record) {
										if (record.data.isDefaultIn != 1
												&& isGranted('_ReportTemplateEdit'))
											return true;
										return false;
									}
								}],
						listeners : {
							scope : this,
							'action' : this.onRowAction
						}
					})],
			// 监听行点击事件
			listeners : {
				scope : this,
				'rowdblclick' : this.rowdblclick
			}
		});
	},

	// 按条件搜索
	search : function() {
		$search({
					searchPanel : this.searchPanel,
					gridPanel : this.gridPanel
				});
	},
	// 重置(清空)查询表单
	reset : function() {
		this.searchPanel.getForm().reset();
	},
	// 刷新gridPanel
	reloadGridPanel : function() {
		this.gridPanel.getStore().reload();
	},
	// 新增报表
	addReport : function() {
		new ReportTemplateForm({
					reportId : null,
					scope : this,
					callback : this.reloadGridPanel
				}).show();
	},
	// 删除选中ID报表
	removeSelRs : function() {
		var selectRecords = this.gridPanel.getSelectionModel().getSelections();

		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
			return;
		}
		for (var i = 0; i < selectRecords.length; i++) {
			if (selectRecords[i].data.isDefaultIn == 1) {
				Ext.ux.Toast.msg("操作信息", selectRecords[i].data.title
								+ "为缺省报表,不能删除!");
				return;
			}
		}

		$delGridRs({
					url : __ctxPath + '/system/multiDelReportTemplate.do',
					grid : this.gridPanel,
					idName : 'reportId'
				});
	},
	// 行双击事件
	rowdblclick : function(grid, rowindex, e) {
		var record = grid.getStore().getAt(rowindex);
		this.reportId = record.data.reportId;
		if(record.data.isDefaultIn != 1){
			this.editRs.call(this);
		}
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
		this.reportId = record.data.reportId;
		this.title = record.data.title;
		switch (action) {
			case 'btn-preview' :
				this.preview.call(this);
				break;
			case 'btn-setting' :
				this.paramSetting.call(this);
				break;
			case 'btn-del' :
				this.removeRs.call(this);
				break;
			case 'btn-edit' :
				this.editRs.call(this);
				break;
			default :
				break;
		}
	},
	/**
	 * 查看
	 */
	preview : function() {
		// 只允许有一个查看窗口
		var tabs = Ext.getCmp('centerTabPanel');
		var edit = Ext.getCmp('ReportPreview' + this.reportId);
		if (!Ext.isEmpty(edit)) {
			tabs.remove('ReportPreview' + this.reportId);
		}
		edit = new ReportTemplatePreview({
					reportId : this.reportId,
					title : this.title
				});
		tabs.add(edit);
		tabs.activate(edit);
	},
	/**
	 * 设置参数
	 */
	paramSetting : function() {
		new ReportParamView({
					reportId : this.reportId,
					title : this.title
				}).show();
	},
	/**
	 * 删除报表
	 * 
	 */
	removeRs : function() {
		$postDel({
					url : __ctxPath + '/system/multiDelReportTemplate.do',
					ids : this.reportId,
					grid : this.gridPanel
				});
	},
	/**
	 * 编辑报表
	 * 
	 */
	editRs : function(reportId) {
		new ReportTemplateForm({
					reportId : this.reportId,
					scope : this,
					callback : this.reloadGridPanel
				}).show();
	}

});