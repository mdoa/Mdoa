Ext.ns('ReportParamView');
/**
 * 报表列表
 * 
 * @class ReportParamView
 * @extends Ext.Panel
 */
ReportParamView = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		// 初始化组件
		this.initUI();
		// 调用父类构造
		ReportParamView.superclass.constructor.call(this, {
					id : 'ReportParamView',
					title : this.title + '--查询参数设置',
					width : 650,
					height : 250,
					modal : true,
					layout : 'fit',
					plain : true,
					buttonAlign : 'center',
					items : [this.gridPanel]
				});
	},
	// 初始化组件
	initUI : function() {
		// 列表面板
		this.gridPanel = new HT.GridPanel({
					region : 'center',
					tbar : [{
								text : '添加参数',
								iconCls : 'btn-add',
								scope : this,
								handler : this.addParam
							}, '-', {
								text : '删除参数',
								iconCls : 'btn-del',
								scope : this,
								handler : this.removeSelRs
							}],
					// 使用RowActions
					rowActions : true,
					sort : [{field:"paramId",direction:"DESC"}],
					url : __ctxPath + "/system/listReportParam.do",
					baseParams : {
						'reportId' : this.reportId
					},
					fields : [{
								name : 'paramId',
								type : 'int'
							}, 'reportId', 'paramName', 'paramKey',
							'defaultVal', 'paramType', 'sn'],
					columns : [{
								header : 'paramId',
								dataIndex : 'paramId',
								hidden : true
							}, {
								header : '参数名称',
								dataIndex : 'paramName'
							}, {
								header : '参数Key',
								dataIndex : 'paramKey'
							}, {
								header : '缺省值',
								dataIndex : 'defaultVal'
							}, {
								header : '类型',
								dataIndex : 'paramType',
								scope : this,
								renderer : this.paramTypeRenderer
							}, {
								header : '序号',
								dataIndex : 'sn'
							}, new Ext.ux.grid.RowActions({
										header : '管理',
										width : 100,
										actions : [{
													iconCls : 'btn-del',
													qtip : '删除',
													style : 'margin: 0 2px 0 2px'
												}, {
													iconCls : 'btn-edit',
													qtip : '编辑',
													style : 'margin: 0 2px 0 2px'
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
	/**
	 * 渲染参数类型
	 */
	paramTypeRenderer : function(value) {
		switch (value) {
			case 'textfield' :
				return '文件输入框';
				break;
			case 'datefield' :
				return '日期输入框';
				break;
			case 'datetimefield' :
				return '时间输入框';
				break;
			case 'numberfield' :
				return '数字输入框';
				break;
			case 'combo' :
				return '下拉框';
				break;
			case 'diccombo' :
				return '数据字典';
				break;
			default :
				return '文件输入框';
		}
	},
	// 刷新gridPanel
	reloadGridPanel : function() {
		this.gridPanel.getStore().reload();
	},
	// 新增参数
	addParam : function() {
		new ReportParamForm({
					reportId : this.reportId,
					paramId : null,
					scope : this,
					callback : this.reloadGridPanel
				}).show();
	},
	// 删除选中ID参数
	removeSelRs : function() {
		$delGridRs({
					url : __ctxPath + '/system/multiDelReportParam.do',
					grid : this.gridPanel,
					idName : 'paramId'
				});
	},
	// 行双击事件
	rowdblclick : function(grid, rowindex, e) {
		grid.getSelectionModel().each(function(record) {
					this.paramId = record.data.paramId;
					this.editRs.call(this);
				}, this);
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
		this.paramId = record.data.paramId;
		switch (action) {
			case 'btn-del' :
				this.removeRs.call(this,record.data.paramId);
				break;
			case 'btn-edit' :
				this.editRs.call(this);
				break;
			default :
				break;
		}
	},
	/**
	 * 删除参数
	 * 
	 */
	removeRs : function(id) {
		/*$postDel({
					url : __ctxPath + '/system/multiDelReportParam.do',
					ids : this.paramId,
					grid : this.gridPanel
				});*/
		$postDel({
					url : __ctxPath + '/system/multiDelReportParam.do',
					ids:id,
					grid:this.gridPanel
				});
	},
	/**
	 * 编辑参数
	 * 
	 */
	editRs : function() {
		new ReportParamForm({
					reportId : this.reportId,
					paramId : this.paramId,
					scope : this,
					callback : this.reloadGridPanel
				}).show();
	}

});