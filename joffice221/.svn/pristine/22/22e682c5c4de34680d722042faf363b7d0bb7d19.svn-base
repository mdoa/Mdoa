Ext.ns('DutySectionView');
/**
 * 班次定义列表
 */
DutySectionView = Ext.extend(Ext.Panel, {
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 初始化组件
				this.initUIComponents();
				// 调用父类构造
				DutySectionView.superclass.constructor.call(this, {
							id : 'DutySectionView',
							title : '班次定义列表',
							region : 'center',
							iconCls:'menu-dutySection',
							layout : 'border',
							items : [this.gridPanel]
						});
			},// end of constructor
			// 初始化组件
			initUIComponents : function() {
				this.topbar = new Ext.Toolbar({
					items : [{
						iconCls : 'btn-add',
						text : '添加班次定义',
						xtype : 'button',
						scope : this,
						handler : this.createRs
					}, '-', {
						iconCls : 'btn-del',
						text : '删除班次定义',
						xtype : 'button',
						scope : this,
						handler : this.removeSelRs
					}]
				});

				//班次面板
				this.gridPanel = new HT.GridPanel({
					region : 'center',
					tbar : this.topbar,
					sort : [{field:"sectionId",direction:"DESC"}],
					// 使用RowActions
					rowActions : true,
					url : __ctxPath + '/personal/listDutySection.do',
					fields : [{
						name : 'sectionId',
						type : 'int'
					    },'sectionName', 'startSignin', 'dutyStartTime','endSignin', 'earlyOffTime','dutyEndTime', 'signOutTime'],
					columns : [{
						header : 'sectionId',
						dataIndex : 'sectionId',
						hidden : true
					}, {
						header:'班次名称',
						dataIndex:'sectionName'
					}, {
						header : '开始签到',
						dataIndex : 'startSignin'
					}, {
						header : '上班时间',
						dataIndex : 'dutyStartTime'
					}, {
						header : '签到结束时间',
						dataIndex : 'endSignin'
					}, {
						header : '早退计时',
						dataIndex : 'earlyOffTime'
					}, {
						header : '下班时间',
						dataIndex : 'dutyEndTime'
					}, {
						header : '签退结束',
						dataIndex : 'signOutTime'
					},new Ext.ux.grid.RowActions({
						header : '管理',
						width : 100,
						actions:[{
							 iconCls:'btn-del',qtip:'删除',style:'margin:0 3px 0 3px'
						   },{
							 iconCls:'btn-edit',qtip:'编辑',style:'margin:0 3px 0 3px'
						   }],
						listeners : {
							scope : this,
							'action' : this.onRowAction
						}
					})]
				});
				this.gridPanel.addListener({scope:this,'rowdblclick':this.rowClick});
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
				this.editRs.call(this,rec);
				
			},
			// 创建记录
			createRs : function() {
				new DutySectionForm({
					scope : this,
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
					url : __ctxPath+ '/personal/multiDelDutySection.do',
					ids:id,
					grid:this.gridPanel
				});
			},
			// 把选中ID删除
			removeSelRs : function() {
				$delGridRs({
					url : __ctxPath+ '/personal/multiDelDutySection.do',
					grid:this.gridPanel,
					idName:'sectionId'
				});
			},
			// 编辑Rs
			editRs : function(record) {
				new DutySectionForm({
					sectionId : record.data.sectionId,
					scope : this,
					callback : this.reloadType
				}).show();
			},						
			// 行的Action
			onRowAction : function(grid, record, action, row, col) {
				switch (action) {
					case 'btn-del' :
						this.removeRs.call(this,record.data.sectionId);
						break;
					case 'btn-edit' :
						this.editRs.call(this,record);
						break;
					default :
						break;
				}
			}
		});
