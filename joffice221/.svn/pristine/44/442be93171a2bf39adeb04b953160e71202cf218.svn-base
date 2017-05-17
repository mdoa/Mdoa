Ext.ns('MySubUserDiaryView');
/**
 * 下属工作日志列表
 * @class MySubUserDiaryView
 * @extends Ext.Panel
 */
MySubUserDiaryView = Ext.extend(Ext.Panel, {	
	//构造方法
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		//初始化组件
		this.initUIComponents();
		//调用父类构造方法
		MySubUserDiaryView.superclass.constructor.call(this,{
			id : 'MySubUserDiaryView',
			title : '下属工作日志',
			iconCls : 'menu-subDiary',
			layout:'border',
			region : 'center',
			items : [this.searchPanel, this.gridPanel]
		});	
	},// end of constructor
	// 初始化组件
	initUIComponents : function(){
		// 搜索面板
		this.searchPanel = new HT.SearchPanel({
			layout : 'form',
			region : 'north',
			colNums : 3,
			keys : [{
						key : Ext.EventObject.ENTER,
						fn : this.search,
						scope : this
					}, {
						key : Ext.EventObject.ESC,
						fn : this.reset,
						scope : this
					}],
			items : [{
						fieldLabel : '下属姓名',
						xtype : 'textfield',
						name : 'Q_appUser.fullname_S_LK',
						maxLength : 125
					}, {
						xtype : 'button',
						text : '查询',
						iconCls : 'search',
						handler : this.search,
						scope : this
					}, {
						xtype : 'button',
						text : '重置',
						iconCls : 'btn-reset',
						handler : this.reset,
						scope : this
					}]
		});
		// 下属日志列表面板
		this.gridPanel = new HT.EditorGridPanel({
			region : 'center',
			title : '下属工作日志列表',
			// 使用RowActions
			rowActions : true,
			url : __ctxPath + "/system/subUserDiary.do",
			fields : ['diaryId','dayTime', 'content', 'diaryType',
			          'userId',  'appUser',{
				name : 'appUser.fullname',
				mapping : 'appUser.fullname'
			}],
			columns : [{
						 header : 'diaryId',
						 dataIndex : 'diaryId',
						 hidden : true
					  },{
						 header : '姓名',
					 	 dataIndex : 'appUser.fullname',
					  },{
						 header : '日期',
						 dataIndex : 'dayTime',
						 sortable : true
					  },{
						 header : '日志内容',
						 dataIndex : 'content',
						 sortable : false,
						 renderer : function(value){
							 return value.substring(0,20);
						 }			
					},  
					new Ext.ux.grid.RowActions({
						header : '管理',
						width : 100,
						actions : [{
							iconCls : 'btn-readdocument',
							qtip : '查看',
							style : 'margin:0 3px 0 3px'	
						}],
						listeners : {
							scope : this,
							'action' : this.onRowAction
						}
					})]
		});
		// 为Grid增加双击事件,双击行可以查看
		this.gridPanel.addListener({
					scope : this,
					'rowdblclick' : this.rowdblclick
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
	// GridPanel行点击处理事件
	rowdblclick : function(grid, rowindex, e) {
		var record = grid.getStore().getAt(rowindex);
		this.readRs.call(this, record);
	},
	//查看日记明细
	readRs :  function(record){
		new DiaryDetail({diaryId:record.data.diaryId}).show();
	},
	// 行的Action
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-readdocument' :
				this.readRs.call(this, record);
				break;
			default :
				break;
		}
	}
});
