/**
 * @author:
 * @class DemensionView
 * @extends Ext.Panel
 * @description 维度管理
 * @company 杭州梦德软件有限公司
 * @createtime:
 */
DemensionView = Ext.extend(Ext.Panel, {
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 初始化组件
				this.initUIComponents();
				// 调用父类构造
				DemensionView.superclass.constructor.call(this, {
							id : 'DemensionView',
							iconCls : 'btn-weidu',
							title : '维度管理',
							region : 'center',
							layout : 'border',
							items : [this.searchPanel, this.gridPanel]
						});
			},// end of constructor
			// 初始化组件
			initUIComponents : function() {
				// 初始化搜索条件Panel
				this.searchPanel = new HT.SearchPanel({
							region : 'north',
							layout : 'form',
							colNums : 3,
							keys : {
								key : Ext.EventObject.ENTER,
								fn : this.search,
								scope : this
							},
							labelWidth : 55,
							items : [{
										fieldLabel : '维度名称',
										xtype : 'textfield',
										name : 'Q_demName_S_LK',
										maxLength : 150
									}, {
										text : '查询',
										scope : this,
										xtype : 'button',
										iconCls : 'btn-search',
										handler : this.search
									}, {
										text : '重置',
										scope : this,
										xtype : 'button',
										iconCls : 'btn-reset',
										handler : this.reset
									}]
						});// end of searchPanel
				// 顶部按钮组
				this.topbar = new Ext.Toolbar({
							items : [{
										iconCls : 'btn-add',
										text : '添加维度',
										xtype : 'button',
										scope : this,
										handler : this.addRs
									}, {
										iconCls : 'btn-del',
										text : '删除维度',
										xtype : 'button',
										scope : this,
										handler : this.removeSelRs
									}]
						});
				// 初始化人员维度列表
				this.gridPanel = new HT.GridPanel({
					region : 'center',
					tbar : this.topbar,
					// 使用RowActions
					rowActions : true,
					url : __ctxPath + "/system/listDemension.do",
					fields : [{
								name : 'demId',
								type : 'int'
							}, 'demName', 'demDesc', 'demType'],
					columns : [{
								header : 'demId',
								dataIndex : 'demId',
								hidden : true
							}, {
								header : '维度名称',
								dataIndex : 'demName'
							}, {
								header : '维度描述',
								dataIndex : 'demDesc'
							}, {
								header : 'demType',
								dataIndex : 'demType',
								hidden : true
							}, new Ext.ux.grid.RowActions({
										header : '管理',
										width : 200,
										actions : [{
													iconCls : 'btn-del',
													qtip : '删除',
													style : 'margin:0 3px 0 3px',
													fn : function(record) {
														if (record.data.demType != 1)
															return true;
														return false;
													}
												}, {
													iconCls : 'btn-edit',
													qtip : '编辑',
													style : 'margin:0 3px 0 3px'
												}],
										listeners : {
											scope : this,
											'action' : this.onRowAction
										}
									})],
						// end of columns
					// 为Grid增加双击事件,双击行可编辑
					listeners : {
						scope : this,
						'rowdblclick' : this.rowdblclick
					}
				});

			},// end of the initComponents()
			// 重置查询表单
			reset : function() {
				this.searchPanel.getForm().reset();
			},
			// 按条件搜索
			search : function() {
				$search({
							searchPanel : this.searchPanel,
							gridPanel : this.gridPanel
						});
			},
			// 重新加载列表
			reloadGrid : function() {
				this.gridPanel.getStore().reload();
			},
			// GridPanel行点击处理事件
			rowdblclick : function(grid, rowindex, e) {
				grid.getSelectionModel().each(function(rec) {
							this.editRs(rec);
						}, this);
			},
			// 创建记录
			addRs : function() {
				new DemensionForm({
							scope : this,
							callback : this.reloadGrid
						}).show();
			},
			// 把选中ID删除
			removeSelRs : function() {
				var selectRecords = this.gridPanel.getSelectionModel()
						.getSelections();
				if (selectRecords.length == 0) {
					Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
					return;
				}
				var ids = Array();
				var demNames = '';
				for (var i = 0; i < selectRecords.length; i++) {
					if (selectRecords[i].data.demType != 1) {
						ids.push(selectRecords[i].data.demId);
					} else {
						demNames += selectRecords[i].data.demName + ',';
					}
				}
				if (demNames == '') {
					this.removeRs(ids);
				} else {
					Ext.ux.Toast.msg("信息", demNames + "不能被删除！");
				}

			},
			// 按ID删除记录
			removeRs : function(id) {
				$postDel({
							url : __ctxPath + '/system/multiDelDemension.do',
							ids : id,
							grid : this.gridPanel
						});
			},

			// 编辑Rs
			editRs : function(record) {
				new DemensionForm({
							demId : record.data.demId,
							demType : record.data.demType,
							scope : this,
							callback : this.reloadGrid
						}).show();
			},
			// 行的Action
			onRowAction : function(grid, record, action, row, col) {
				switch (action) {
					case 'btn-del' :
						this.removeRs.call(this, record.data.demId);
						break;
					case 'btn-edit' :
						this.editRs.call(this, record);
						break;
					default :
						break;
				}
			}
		});