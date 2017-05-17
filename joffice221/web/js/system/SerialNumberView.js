Ext.ns('SerialNumberView');
/**
 * @author:
 * @class SerialNumberView
 * @extends Ext.Panel
 * @description 流水號管理
 * @company 杭州梦德软件有限公司
 * @createtime:
 */
SerialNumberView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		SerialNumberView.superclass.constructor.call(this, {
					id : 'SerialNumberView',
					title : '流水号管理',
					iconCls : 'menu-serialNumber',
					region : 'center',
					layout : 'border',
					items : [this.searchPanel, this.gridPanel]
				});
	},// end of constructor
	// 初始化组件
	initUIComponents : function() {
		// 初始化搜索条件Panel
		this.searchPanel = new HT.SearchPanel({
					layout : 'form',
					region : 'north',
					keys : {
						key : Ext.EventObject.ENTER,
						fn : this.search,
						scope : this
					},
					colNums : 4,
					labelWidth : 45,
					items : [{
								fieldLabel : '名称',
								name : 'Q_name_S_EQ',
								xtype : 'textfield'
							}, {
								fieldLabel : '别名',
								name : 'Q_alias_S_EQ',
								xtype : 'textfield'
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
				});// end of searchPanel

		this.topbar = new Ext.Toolbar({
					items : [{
								iconCls : 'btn-add',
								text : '添加流水号',
								xtype : 'button',
								scope : this,
								handler : this.createRs
							}, '-', {
								iconCls : 'btn-del',
								text : '删除流水号',
								xtype : 'button',
								scope : this,
								handler : this.removeSelRs
							}]
				});

		this.gridPanel = new HT.GridPanel({
					region : 'center',
					tbar : this.topbar,
					// 使用RowActions
					rowActions : true,
					url : __ctxPath + "/system/listSerialNumber.do",
					fields : [{
								name : 'numberId',
								type : 'int'
							}, 'name', 'alias', 'regulation', 'genType',
							'noLength', 'curDate', 'initValue', 'curValue',
							'step'],
					columns : [{
								header : 'numberId',
								dataIndex : 'numberId',
								hidden : true
							}, {
								header : '名称',
								dataIndex : 'name'
							}, {
								header : '别名',
								dataIndex : 'alias'
							}, {
								header : '规则',
								dataIndex : 'regulation'
							}, {
								header : '生成方式',
								dataIndex : 'genType',
								renderer : function(value) {
									switch (value) {
										case 1 :
											return '<font color="green">每日生成</font>';
										case 2 :
											return '<font color="green">每月生成</font>';
										case 3 :
											return '<font color="green">每年生成</font>';
										default :
											return '<font color="red">递增</font>';
									}

								}
							}, {
								header : '流水号长度',
								dataIndex : 'noLength'
							}, {
								header : '初始值',
								dataIndex : 'initValue'
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
												}
										// , {
										// iconCls : 'btn-detail',
										// qtip : '明细',
										// style : 'margin:0 3px 0 3px'
										// }
										],
										listeners : {
											scope : this,
											'action' : this.onRowAction
										}
									})],// end of columns
					// 双击行
					listeners : {
						scope : this,
						'rowdblclick' : this.rowClick
					}
				});

	},// end of the initComponents()
	/**
	 * 重置(清空)查询表单
	 */
	reset : function() {
		this.searchPanel.getForm().reset();
	},
	/**
	 * 按条件搜索
	 */
	search : function() {
		$search({
					searchPanel : this.searchPanel,
					gridPanel : this.gridPanel
				});
	},
	/**
	 * 刷新gridPanel
	 */
	reloadGridPanel : function() {
		this.gridPanel.getStore().reload();
	},
	/**
	 * 行双击事件
	 * 
	 * @param {}
	 *            grid
	 * @param {}
	 *            rowindex
	 * @param {}
	 *            e
	 */
	rowClick : function(grid, rowindex, e) {
		grid.getSelectionModel().each(function(record) {
					this.editRs(record);
				}, this);
	},
	/**
	 * 创建记录
	 */
	createRs : function() {
		new SerialNumberForm({
					scope : this,
					callback : this.reloadGridPanel
				}).show();
	},
	/**
	 * 按ID删除记录
	 */
	removeRs : function(id) {
		$postDel({
					url : __ctxPath + '/system/multiDelSerialNumber.do',
					ids : id,
					grid : this.gridPanel
				});
	},
	/**
	 * 把选中ID删除
	 */
	removeSelRs : function() {
		$delGridRs({
					url : __ctxPath + '/system/multiDelSerialNumber.do',
					grid : this.gridPanel,
					idName : 'numberId'
				});
	},
	/**
	 * 编辑Rs
	 */
	editRs : function(record) {
		new SerialNumberForm({
					numberId : record.data.numberId,
					scope : this,
					callback : this.reloadGridPanel
				}).show();
	},
	/**
	 * 行的Action
	 */
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.removeRs.call(this, record.data.numberId);
				break;
			case 'btn-edit' :
				this.editRs.call(this, record);
				break;
			default :
				break;
		}
	}
});
