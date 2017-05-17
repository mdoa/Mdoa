Ext.ns('WaitOpenConferenceView');
/**
 * @class WaitOpenConferenceView
 * @extends Ext.Panel
 * @description 待开会议查询
 * @company 杭州梦德软件有限公司
 */
WaitOpenConferenceView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		WaitOpenConferenceView.superclass.constructor.call(this, {
					id : 'WaitOpenConferenceView',
					title : '待开会议查询',
					iconCls : 'menu-conference_waitOpen',
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
					labelWidth : 70,
					items : [{
						fieldLabel : '会议室名称',
						xtype : 'textfield',
						name : 'Q_roomName_S_LK',
						maxLength : 156,
						width : 225
					},{
						fieldLabel : '会议室类型',
						xtype : 'combo',
						hiddenName : 'Q_roomId_L_EQ',
						valueField : 'roomId',
						displayField : 'roomName',
						mode : 'local',
						width : 225,
						editable : false,
						triggerAction : 'all',
						forceSelection : true,
						store : new Ext.data.SimpleStore({
							url : __ctxPath + '/admin/getBoardrooConference.do',
							autoLoad : true,
							fields : ['roomId', 'roomName']
						})
					},{
						fieldLabel : '会议时间',
						xtype : 'datefield',
						name : 'Q_startTime_D_GE',
						format : 'Y-m-d'
					},{
						fieldLabel : '至',
						xtype : 'datefield',
						name : 'Q_endTime_D_LE',
						format : 'Y-m-d',
						labelWidth : 25
					},{
						xtype : 'button',
						text : '查询',
						iconCls : 'search',
						scope : this,
						handler : this.search
					},{
						fieldLabel : '会议标题',
						xtype : 'textfield',
						name : 'Q_confTopic_S_LK',
						maxLength : 256,
						width : 225
					},{
						xtype : 'combo',
						name : 'Q_confProperty_S_LK',
						fieldLabel : '会议类型',
						valueField : 'typeId',
						displayField : 'typeName',
						mode : 'local',
						editable : false,
						width : 225,
						triggerAction : 'all',
						forceSelection : true,
						store : new Ext.data.SimpleStore({
							url : __ctxPath + '/admin/getTypeAllConference.do',
							autoLoad : true,
							fields : ['typeId', 'typeName']
						})
					},{
						fieldLabel : '会议内容',
						xtype : 'textfield',
						name : 'Q_confContent_S_LK',
						maxLength : 4000,
						width : 225
					},{
						xtype : 'button',
						text : '清空',
						iconCls : 'reset',
						scope : this,
						handler : this.reset
					}]
				});// end of the searchPanel
				
		//顶端栏目条
		this.topbar = new Ext.Toolbar({
			height : 30,
			bodyStyle : 'text-align:left'
		});
		
		// 待开会议面板
		this.gridPanel = new HT.GridPanel({
					region : 'center',
					tbar : this.topbar,
					sort : [{field:"confId",direction:"DESC"}],
					// 使用RowActions
					rowActions : true,
					url : __ctxPath + "/admin/waitOpenConference.do",
					fields : [ {
						name : 'confId',
						type : 'int'
					}, 'confTopic', 'compereName', 'roomName', 'roomLocation', 'confContent',
							'attendUsersName', 'feeBudget', 'checkName', 'startTime', 'endTime', 'status' ],
					columns : [{
								header : 'confId',
								dataIndex : 'confId',
								hidden : true
							}, {
								header : '会议标题',
								dataIndex : 'confTopic'
							}, {
								header : '会议内容',
								dataIndex : 'confContent'
							}, {
								header : '主持人',
								dataIndex : 'compereName'
							}, {
								header : '会议室名',
								dataIndex : 'roomName'
							}, {
								header : '会议地址',
								dataIndex : 'roomLocation'
							}, {
								header : '开始时间',
								dataIndex : 'startTime'
							}, {
								header : '结束时间',
								dataIndex : 'endTime'
							}, {
								header : '会议内容',
								dataIndex : 'confContent'
							},new Ext.ux.grid.RowActions({
										header : '管理',
										width : 100,
										actions : [{
													iconCls : 'btn-showDetail',
													qtip : '查看',
													style : 'margin:0 3px 0 3px'
												}],
										listeners : {
											scope : this,
											'action' : this.onRowAction
										}
							})]
				});
		this.gridPanel.addListener({scope:this,'rowdblclick': this.rowClick});
	},// end of the initUIComponents
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
		this.showDetail.call(this, rec.data.confId);
		
	},
	// 查看详细信息
	showDetail : function(confId) {
		Ext.Ajax.request({
				url : __ctxPath + '/admin/allowViewConfPrivilege.do',
				params : {
					confId : confId
				},
				method : 'post',
				waitMsg : '数据正在提交，请稍候...',
				success : function(response, options) {
					var res = Ext.util.JSON.decode(response.responseText);
					if (res.success)
						new ConferenceDetailForm({
							confId : confId
						}).show();
					else
						Ext.MessageBox.show({
									title : '操作信息',
									msg : res.msg,
									buttons : Ext.MessageBox.OK,
									icon : 'ext-mb-error'
								});
				}
			});
	},
	// 行的Action
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-showDetail' :
				this.showDetail.call(this, record.data.confId);
				break;
			default :
				break;
		}
	}

});

