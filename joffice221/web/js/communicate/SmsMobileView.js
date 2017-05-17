/**
 * @author:
 * @class SmsMobileView
 * @extends Ext.Panel
 * @description [SmsMobile]管理
 * @company 杭州梦德软件有限公司
 */
SmsMobileView = Ext.extend(Ext.Panel, {
	// 条件搜索Panel
	searchPanel : null,
	// 数据展示Panel
	gridPanel : null,
	// GridPanel的数据Store
	store : null,
	// 头部工具栏
	topbar : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		SmsMobileView.superclass.constructor.call(this, {
					id : 'SmsMobileView',
					title : '手机短信管理',
					iconCls : 'menu-mobile',
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
					colNums : 7,
					keys : [{
						key : Ext.EventObject.ENTER,
						fn : this.search,
						scope : this
					}, {
						key : Ext.EventObject.ESC,
						fn : this.reset,
						scope : this
					}],
					labelWidth : 70,
					items : [{
								fieldLabel : '发送时间从',
								name : 'Q_sendTime_DL_GT',
								xtype : 'datefield',
								format : 'Y-m-d',
								style : "padding-left:5px",
								width : 125
							},{
								fieldLabel : '到',
								name : 'Q_sendTime_DG_LT',
								xtype : 'datefield',
								format : 'Y-m-d',
								style : "padding-left:5px",
								labelWidth : 20,
								width : 125
							},{
								fieldLabel : '收信人',
								name : 'Q_recipients_S_LK',
								xtype : 'textfield',
								labelWidth : 50,
								width : 125
							}, {
								fieldLabel : '收信号码',
								name : 'Q_phoneNumber_S_LK',
								xtype : 'textfield',
								labelWidth : 60,
								width : 125
							}, {
								fieldLabel : '状态',
								hiddenName : 'Q_status_SN_EQ',
								xtype : 'combo',
								editable : false,
								mode : 'local',
								triggerAction : 'all',
								store : [['1', '已发送'], ['0', '待发送']],
								value : 1,
								labelWidth : 40
							}, {
									xtype : 'button',
									text : '查询',
									scope : this,
									iconCls : 'search',
									handler : this.search 
							},{
								xtype : 'button',
								text : '重置',
								style : 'padding-left:5px;',
								iconCls : 'btn-reset',
								handler : this.reset,
								scope : this
							}]
		});// end of the searchPanel
		// 初始化工具栏
		this.topbar = new Ext.Toolbar({
					height : 30,
					bodyStyle : 'text-align:left',
					items : [
						{
								iconCls : 'btn-add',
								text : '系统内短信',
								xtype : 'button',
								scope : this,
								handler : this.innerRecord
							},{
								iconCls : 'btn-add',
								text : '系统外短信',
								xtype : 'button',
								scope : this,
								handler : this.outsideRecord
							},
							{
								iconCls : 'btn-del',
								text : '删除短信',
								xtype : 'button',
								handler : this.delRecords,
								scope : this
							}]
		});
		//短信列表面板
		this.gridPanel = new HT.GridPanel({
					region : 'center',
					tbar : this.topbar,
					// 使用RowActions
					rowActions : true,
					url : __ctxPath + "/communicate/listSmsMobile.do",
					fields : [{name : 'smsId',type : 'int'}, 'sendTime',
							 'recipients', 'phoneNumber','userId',
							 'userName', 'smsContent', 'status'],
				    columns : [{
					    			header : 'smsId',
									dataIndex : 'smsId',
									hidden : true
							   }, {
									header : '发送时间',
									dataIndex : 'sendTime',
									renderer : function(value){
										return value.substring(0,10);
									}
							   }, {
									header : '收信人',
									dataIndex : 'recipients'
							   }, {
									header : '收信号码',
									dataIndex : 'phoneNumber'
							   }, {
									header : '发信人',
									dataIndex : 'userName'
							   }, {
									header : '短信内容',
									dataIndex : 'smsContent'
							   }, {
									header : '状态',
									dataIndex : 'status',
									renderer : function(value){
										if(value == 1){
											return '<font color="green">已发送</font>';	
										}else{
											return '<font color="red">待发送</font>';
										}
									}
				    		   },new Ext.ux.grid.RowActions({
									header : '管理',
									width : 80,
									actions : [{
												iconCls : 'btn-del',
												qtip : '删除',
												style : 'margin:0 3px 0 3px'
											}],
									listeners : {
										scope : this,
										'action' : this.onRowAction
									}
								})]// end of columns
		    		   });

		this.gridPanel.addListener('rowdblclick',this.dbclick);
	},// end of the initComponents()

	//查询
	search : function() {
		$search({
			searchPanel :this.searchPanel,
			gridPanel : this.gridPanel
		});
	},
	// 重置查询表单
	reset : function() {
		this.searchPanel.getForm().reset();
	},
	// 添加系统内短信
	innerRecord : function() {
		new SmsMobileForm({
			isInner : true,
			scope : this,
			callback : function(){
				this.gridPanel.getStore().reload();
			}
		}).show();
	},
	// 添加系统外短信
	outsideRecord : function() {
		new SmsMobileForm({
			scope : this,
			callback : function(){
				this.gridPanel.getStore().reload();
			}
		}).show();
	},
	// 按IDS删除记录
	delByIds : function(ids) {
		$postDel({
			url : __ctxPath + '/communicate/multiDelSmsMobile.do',
			ids : ids,
			grid : this.gridPanel
		});
	},
	// 删除多条记录
	delRecords : function() {
		$delGridRs({
			url : __ctxPath + '/communicate/multiDelSmsMobile.do',
			grid : this.gridPanel,
			idName : 'smsId'
		});
	},
	// 编辑记录
	editRecord : function(record) {
		new SmsMobileForm({
					smsId : record.data.smsId
				}).show();
	},
	//行双击事件
	dbclick : function(grid, rowindex, e) {
		grid.getSelectionModel().each(function(rec) {
					new SmsMobileForm({smsId : rec.data.smsId}).show();
				});
	},
	//管理列中onRowAction事件处理
	onRowAction : function(gridPanel, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.delByIds.call(this,record.data.smsId);
				break;
			case 'btn-edit' :
				this.editRecord.call(this,record);
				break;
			default :
				break;
		}
	}
});
