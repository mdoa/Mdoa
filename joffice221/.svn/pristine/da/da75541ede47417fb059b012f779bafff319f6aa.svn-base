Ext.ns('AppointmentView');
/**
 * 约会列表
 */
AppointmentView = Ext.extend(Ext.Panel, {
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 初始化组件
				this.initUIComponents();
				// 调用父类构造
				AppointmentView.superclass.constructor.call(this, {
							id : 'AppointmentView',
							title : '约会列表',
							region : 'center',
							iconCls:'menu-appointment',
							layout : 'border',
							items : [this.searchPanel, this.gridPanel]
						});
			},// end of constructor
			// 初始化组件
			initUIComponents : function() {
				// 初始化搜索条件Panel
				this.searchPanel = new HT.SearchPanel({
					colNums : 3,
					layout : 'form',
					region : 'north',
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
					items: [{
			            xtype:'datetimefield',
	                    fieldLabel: '开始时间',
	                    name: 'Q_startTime_D_GT',
	                    format: 'Y-m-d H:i:s',
	                    width:220
			          },{
			          	xtype : 'textfield',
						name : 'Q_subject_S_LK',
						fieldLabel:'标题',
						width:220,
						labelWidth : 40
			          },{
			          	xtype : 'button',
						text : '查询',
						iconCls : 'search',
						scope:this,
						handler:this.search,
						style:'padding-left:10px'
			          },{
			          	xtype : 'datetimefield',
                		fieldLabel: '结束时间',
						name : 'Q_endTime_D_LT',
						format: 'Y-m-d H:i:s',
						width:220
			          },{
			          	xtype : 'textfield',
						name : 'Q_location_S_LK',
						fieldLabel:'地点',
						width:220,
						labelWidth :40
			          },{
			          	xtype : 'button',
						text : '清空',
						iconCls : 'btn-reset',
						scope : this,
						handler : this.reset,
						style:'padding-left:10px'
			          }]
				});
				
				this.topbar = new Ext.Toolbar({
					items : [{
						iconCls : 'btn-add',
						text : '添加约会',
						xtype : 'button',
						scope : this,
						handler : this.createRs
					}, '-', {
						iconCls : 'btn-del',
						text : '删除约会',
						xtype : 'button',
						scope : this,
						handler : this.removeSelRs
					}]
				});

				//约会面板
				this.gridPanel = new HT.GridPanel({
					region : 'center',
					tbar : this.topbar,
					// 使用RowActions
					rowActions : true,
					sort : [{field:"appointId",direction:"DESC"}],
					url : __ctxPath + '/task/listAppointment.do',
					fields : [{
						name : 'appointId',
						type : 'int'
					    }, 'userId', 'subject', 'startTime','endTime', 'content', 'notes', 'location','inviteEmails'],
					columns : [{
						header : 'appointId',
						dataIndex : 'appointId',
						hidden : true
					}, {
						header : '主题',
						dataIndex : 'subject'
					}, {
						header : '开始时间',
						dataIndex : 'startTime'
					}, {
						header : '结束时间',
						dataIndex : 'endTime'
					}, {
						header : '地点',
						dataIndex : 'location'
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
				this.gridPanel.addListener({scope : this,'rowdblclick' : this.rowClick});
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
				new AppointmentForm({
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
					url : __ctxPath+ '/task/multiDelAppointment.do',
					ids:id,
					grid:this.gridPanel
				});
			},
			// 把选中ID删除
			removeSelRs : function() {
				$delGridRs({
					url:__ctxPath + '/task/multiDelAppointment.do',
					grid:this.gridPanel,
					idName:'appointId'
				});
			},
			// 编辑Rs
			editRs : function(record) {
				new AppointmentForm({
					appointId : record.data.appointId,
					scope : this,
					callback : this.reloadType
				}).show();
			},						
			// 行的Action
			onRowAction : function(grid, record, action, row, col) {
				switch (action) {
					case 'btn-del' :
						this.removeRs.call(this,record.data.appointId);
						break;
					case 'btn-edit' :
						this.editRs.call(this,record);
						break;
					default :
						break;
				}
			}
		});
