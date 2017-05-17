/**
 * @class SectionList
 * @extends Ext.Panel
 * @description 公司主页管理-栏目列表管理
 * @company 杭州梦德软件有限公司
 * @createtime: 2011-8-19AM
 */
SectionList = Ext.extend(Ext.Panel, {
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 初始化组件
				this.initUIComponents();
				// 调用父类构造
				SectionList.superclass.constructor.call(this, {
							id : 'SectionList',
							title : '列表管理',
							iconCls : 'menu-section-list',
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
							colNums : 5,
							keys : [{
										key : Ext.EventObject.ENTER,
										fn : this.search,
										scope : this
									}, {
										key : Ext.EventObject.ESC,
										fn : this.reset,
										scope : this
									}],
							labelWidth : 60,
							items : [{
										fieldLabel : '栏目名称',
										name : 'Q_sectionName_S_LK',
										xtype : 'textfield',
										maxLength : 125,
										width:220
									}, {
										fieldLabel : '创建时间',
										name : 'Q_createtime_D_GE',
										xtype : 'datefield',
										format : 'Y-m-d'
									}, {
										fieldLabel : '至',
										name : 'Q_createtime_D_LE',
										xtype : 'datefield',
										format : 'Y-m-d',
										labelWidth : 27
									}, {
										fieldLabel : '栏目类型',
										hiddenName : 'Q_sectionType_SN_EQ',
										xtype : 'combo',
										mode : 'local',
										editable : false,
										triggerAction : 'all',
										forceSelection : true,
										store : [['', '全部'], ['1', '一般栏目'],
												['2', '桌面新闻'], ['3', '滚动公告']]
									}, {
										xtype : 'button',
										text : '查询',
										iconCls : 'search',
										scope : this,
										handler : this.search
									}, {
										fieldLabel : '创建人',
										name : 'Q_username_S_LK',
										xtype : 'textfield',
										maxLength : 125,
										width:220
									}, {
										fieldLabel : '描述',
										xtype : 'textfield',
										name : 'Q_sectionDesc_S_LK',
										maxLength : 125,
										width : 225
									}, {
										fieldLabel : '状态',
										xtype : 'combo',
										hiddenName : 'Q_status_SN_EQ',
										mode : 'local',
										editable : false,
										triggerAction : 'all',
										forceSelection : true,
										store : [['', '全部'], ['0', '禁用'],['1', '激活']]
									}, {
										xtype : 'button',
										text : '清空',
										iconCls : 'reset',
										scope : this,
										handler : this.reset
									}]
						});

			    //顶端栏目条
				this.topbar = new Ext.Toolbar({
							items : [{
										iconCls : 'btn-add',
										text : '添加',
										xtype : 'button',
										scope : this,
										handler : this.createRs
									}, '-', {
										iconCls : 'btn-del',
										text : '删除',
										xtype : 'button',
										scope : this,
										handler : this.removeSelRs
									}]
						});

				//栏目列表面板
				this.gridPanel = new HT.GridPanel({
					region : 'center',
					tbar : this.topbar,
					sort : [{field:"rowNumber",direction:"ASC"}],
					// 使用RowActions
					rowActions : true,
					url : __ctxPath + "/info/listSection.do",
					fields : [{
								name : 'sectionId',
								type : 'int'
							}, 'sectionName', 'sectionDesc', 'createtime',
							'sectionType', 'username', 'userId', 'colNumber',
							'rowNumber', 'status'],
					columns : [{
								header : 'sectionId',
								dataIndex : 'sectionId',
								hidden : true
							}, {
								header : '栏目名称',
								dataIndex : 'sectionName'
							}, {
								header : '栏目描述',
								dataIndex : 'sectionDesc'
							}, {
								header : '创建时间',
								dataIndex : 'createtime'
							}, {
								header : '栏目类型',
								dataIndex : 'sectionType',
								renderer : function(value) {
									if (value != null) {
										if (value == 1) {
											return '<font color="green">一般栏目</font>';
										} else if (value == 2) {
											return '<font color="green">桌面新闻</font>';
										} else if (value == 3) {
											return '<font color="green">滚动公告</font>';
										}
									} else {
										return '';
									}
								}
							}, {
								header : '创建人',
								dataIndex : 'username'
							}, {
								header : '所在列',
								dataIndex : 'colNumber'
							}, {
								header : '所在行',
								dataIndex : 'rowNumber'
							}, {
								header : '状态',
								dataIndex : 'status',
								renderer : function(value) {
									if (value != null && value == 1) {
										return '<font color="green">激活</font>';
									} else {
										return '<font color="red">禁用</font>';
									}
								}
							}, new Ext.ux.grid.RowActions({
										header : '管理',
										width : 100,
										actions : [{
													iconCls : 'btn-del',qtip : '删除',style : 'margin:0 3px 0 3px'
												}, {
													iconCls : 'btn-edit',	qtip : '编辑',style : 'margin:0 3px 0 3px'
												}],
										listeners : {
											scope : this,
											'action' : this.onRowAction
										}
									})]
				});

				this.gridPanel.addListener({scope:this,'rowdblclick': this.rowClick});

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
			// GridPanel行点击处理事件
			rowClick : function(grid, rowindex, e) {
				var rec = grid.getStore().getAt(rowindex);
				this.editRs.call(this, rec);
			},
			// 创建记录
			createRs : function() {
				new SectionForm({
					scope:this,
					callback : this.reloadType
				}).show();
			},
			// 刷新gridPanel
			reloadType : function() {
				this.gridPanel.getStore().reload();
			},
			// 按ID删除记录
			removeRs : function(id) {
				$postDel({
					url : __ctxPath + '/info/multiDelSection.do',
					ids : id,
					grid : this.gridPanel
				});
			},
			// 把选中ID删除
			removeSelRs : function() {
				$delGridRs({
					url : __ctxPath + '/info/multiDelSection.do',
					grid : this.gridPanel,
					idName : 'sectionId'
				});
			},
			// 编辑Rs
			editRs : function(record) {
				new SectionForm({
					sectionId : record.data.sectionId,
					scope:this,
					callback : this.reloadType
				}).show();
			},
			// 行的Action
			onRowAction : function(grid, record, action, row, col) {
				switch (action) {
					case 'btn-del' :
						this.removeRs.call(this, record.data.sectionId);
						break;
					case 'btn-edit' :
						this.editRs.call(this, record);
						break;
					default :
						break;
				}
			}
		});
