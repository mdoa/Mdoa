/**
 * @author
 * @createtime
 * @class StatisticsArchFileReportPanel
 * @extends Ext.Window
 * @description RollFile表单
 * @company 宏天软件
 */
StatisticsArchFileReportPanel = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		StatisticsArchFileReportPanel.superclass.constructor.call(this, {
			id : 'StatisticsArchFileReportPanel',
			iconCls : "menu-archFileReport",
			layoutConfig : {
				padding : '5',
				pack : 'center',
				align : 'middle'
			},
			layout : 'form',
	
			items : [this.formPanel, this.gridPanel ],
			title : '文件统计',
			listeners : {
				'afterlayout' : function(window) {
				}
			}
		});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
	this.searchTypeComBox = new Ext.form.ComboBox({
		hiddenName : 'searchPath',
		store : new Ext.data.ArrayStore({
					fields : ['name', 'path'],
					data : [
							['案卷', '/arch/fileReportByRollArchReport.do'],
							['保管期限', '/arch/fileReportByTimeLimitArchReport.do']]
				}),
		value:'/arch/fileReportByRollArchReport.do',		
		displayField : 'name',
		valueField : 'path',
		typeAhead : true,
		mode : 'local',
		triggerAction : 'all',
		forceSelection : true,
		listeners:{
				scope: this,
			 	'select': function( combo,  record, index ){
				 	this.gridPanel.getStore().proxy.conn.url =__ctxPath + combo.getValue();
				 	this.gridPanel.getStore().load({
				 	params : {
						itemName : '文件保管期限'
					}
				 	});
				 	var cm =this.gridPanel.getColumnModel();
				 	cm.setColumnHeader( 3,  record.get('name') );
				 }
			}
	});
	//查询面板
	this.formPanel = new Ext.FormPanel({
		layout : 'table',
		width : 800,
		layoutConfig : {
			columns : 3
		},
		style : 'padding:30px 100px 0px',
		border : true,
		bodyBorder : false,
		hideBorders : false,
		region : 'north',
		autoScroll : false,
		defaults : {
			border : true,
			width : 300,
			height : 25,
			layout : 'form',
			anchor : '96%,96%',
			xtype : 'panel'

		},
		items : [{
					colspan : 2,
					width : 300,
					items : {
						text : '统计方式:',
						xtype : 'label'

					}
				}, {
					items : this.searchTypeComBox
				}]
	});
	//显示统计列表面板
	this.gridPanel = new HT.GridPanel( {
		width : 800,
		style : 'padding:0px 100px',
		stripeRows : true,
		frame : false,
		border : true,
		autoHeight : true,
		viewConfig : {
			forceFit : true,
			autoFill : true
		},
		url : __ctxPath + '/arch/fileReportByRollArchReport.do',
		fields : [ 'name', {
					name : 'nums',
					type : 'int'
				}, {
					name : 'isTotal',
					type : 'boolean'
				} ],
		columns : [  {
					header : '案卷号',
					renderer : function(value, metadata, record) {
						if (record.get('isTotal'))
							metadata.attr = 'style="font-weight: bold"';
						return value;
					},
					
					sortable : false,
					groupable : false,
					dataIndex : 'name'
				}, {
					header : '总计',
					renderer : function(value, metadata, record) {
						if (record.get('isTotal'))
							metadata.attr = 'style="font-weight: bold"';
						return value;
					},
					sortable : false,
					groupable : false,
					dataIndex : 'nums'
		
				} ]
	});
	this.gridPanel.getStore().on('load', function(store, records, options) {
		var total = 0;
		Ext.each(records, function(r) {
			total += r.get('nums');
		}, this);
		var recrod = new store.recordType();
		recrod.data = {};
		recrod.data.name = '合计';
		recrod.data.nums = total;
		recrod.data.isTotal = true;
		store.add(recrod);
		store.commitChanges();
	}, this);
	this.gridPanel.getStore().load();
}
});