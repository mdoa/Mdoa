Ext.ns('DiaryView');
/**
 * 我的日志
 * 
 * @class DiaryView
 * @extends Ext.Panel
 */
DiaryView = Ext.extend(Ext.Panel, {
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		//初始化组件
		this.initUIComponents();
		DiaryView.superclass.constructor.call(this, {
			id : 'DiaryView',
			title : '我的日志列表',
			region : 'center',
			iconCls : 'menu-diary',
			layout : 'border',
			items : [this.searchPanel, this.gridPanel]
		});	
	},// end of constructor
	// 初始化组件
	initUIComponents : function() {
		// 查询面板
		this.searchPanel = new HT.SearchPanel({
			layout : 'form',
			region : 'north',
			colNums : 6,
			labelWidth : 30,
			items : [{	
						fieldLabel : '日期',
						xtype:'datefield',
						id:'startDate',
						vtype : 'daterange',
						endDateField: 'endDate',
						name : 'Q_dayTime_D_GE',
						format : 'Y-m-d',
						style : "padding-left:5px",
						width : 125,
						maxLength : 125
					},{
						fieldLabel : '至',
						xtype:'datefield',
						id:'endDate',
						vtype : 'daterange',
						startDateField: 'startDate',
						name : 'Q_dayTime_D_LE',
						format : 'Y-m-d',
						maxLength : 125,
						width : 125,
						labelWidth : 20
					},{
						fieldLabel : '关键字',
						xtype : 'textfield',
						name : 'Q_content_S_LK',
						labelWidth : 50,
						maxLength : 125
					},{
						fieldLabel : '日志类型',
						xtype : 'combo',
						hiddenName : 'Q_diaryType_SN_EQ',
						mode : 'local',
						triggerAction : 'all',
						editable : false,
						forceSelection : true,
						store : [ [ '0', '个人日志' ],[ '1', '工作日志' ] ],
						labelWidth : 60,
						maxLength : 125,
						width : 125
					},{
						xtype : 'button',
						text : '查询',
						style : 'padding-left:5px;',
						iconCls : 'search',
						handler : this.search,
						scope : this
					},{
						xtype : 'button',
						text : '重置',
						style : 'padding-left:5px;',
						iconCls : 'btn-reset',
						handler : this.reset,
						scope : this
					}]
		});//end of searchPanel
		
		this.topbar = new Ext.Toolbar({
			height : 30,
			bodyStyle : 'text-align:left',
			items : [{
						iconCls : 'btn-add',
						text : '添加日志',
						xtype : 'button',
						scope : this,
						handler : this.createRs
					},{
						iconCls : 'btn-del',
						text : '删除日志',
						xtype : 'button',
						scope : this,
						handler : this.removeSelRs
					}]
		});
		//日志列表面板
		this.gridPanel = new HT.GridPanel({
			region : 'center',
			tbar : this.topbar,
			sort : [{
						field : "diaryId",
						direction : "DESC"
					}],
			// 使用RowActions
			rowActions : true,
			url : __ctxPath + "/system/listDiary.do",
			fields : ['diaryId','userId', 'dayTime', 'content', 'diaryType','appUser'],
			columns : [{
							header : 'diaryId',
							dataIndex : 'diaryId',
							hidden : true
						},{
							header : '日期',
							dataIndex : 'dayTime'
						},{
							header : '日志类型',
							dataIndex : 'diaryType',
							renderer:function(value){
								return value=='0'?"个人日志":"工作日志";
							}
						},{
							header : '日志内容',
							dataIndex : 'content',
							sortable : false,
							renderer : function(value){
								 return value.substring(0,20);
							}			
						}, new Ext.ux.grid.RowActions({
							header : '管理',
							width : 100,
							actions : [{
								iconCls : 'btn-del',
								qtip : '删除',
								style : 'margin:0 3px 0 3px'
							},{
								iconCls : 'btn-readdocument',
								qtip : '查看',
								style : 'margin:0 3px 0 3px'	
							}, {
								iconCls : 'btn-edit',
								qtip : '编辑',
								style : 'margin:0 3px 0 3px'	
							}],
							listeners : {
								scope : this,
								'action' : this.onRowAction
							}
						})]// end of columns
		});
		this.gridPanel.addListener({scope : this,'rowdblclick': this.rowClick});
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
		var record = grid.getStore().getAt(rowindex);
		this.editRs.call(this, record);
	},
	// 创建记录
	createRs : function() {
		new DiaryForm({
				scope:this,
				callback:this.reloadType
			}).show();
	},
	// 按ID删除记录
	removeRs : function(id) {
		$postDel({
					url : __ctxPath + '/system/multiDelDiary.do',
					ids : id,
					grid : this.gridPanel
				});
	},
	// 把选中ID删除
	removeSelRs : function() {
		$delGridRs({
					url : __ctxPath + '/system/multiDelDiary.do',
					grid : this.gridPanel,
					idName : 'diaryId'
				});
	},
	// 编辑Rs
	editRs : function(record) {
		new DiaryForm({
			diaryId : record.data.diaryId,
			scope:this,
			callback:this.reloadType
		}).show();
	},
	// 刷新gridPanel
	reloadType : function() {
		this.gridPanel.getStore().reload();
	},
	//查看日记明细
	readRs :  function(record){
		new DiaryDetail({diaryId:record.data.diaryId}).show();
	},
	// 行的Action
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.removeRs.call(this, record.data.diaryId);
				break;
			case 'btn-readdocument' :
				this.readRs.call(this, record);
				break;
			case 'btn-edit' :
				this.editRs.call(this, record);
				break;
			default :
				break;
		}
	}
});