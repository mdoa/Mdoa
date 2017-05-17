/**
 * 栏目选择器
 * 
 * @class SectionDialog
 * @extends Ext.Window
 * @describe 使用说明：
 * 
 * <pre><code>
 * new SectionDialog({
 * 			scope : this,//当前作用域
 * 			single : false, //默认true为多选择栏目,false为单选择栏目
 * 			status : 1, //1表示激活，否则为禁用，默认为全部
 * 			callback : function(scope, sectionId, sectionName, sectionType) {//回调方法：作用域，选择的栏目id、栏目名称，栏目信息
 * 
 * 			}
 * 		}).show();
 * </code></pre>
 */
SectionDialog = Ext.extend(Ext.Window, {
	// 构造方法
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		this.initUI();
		SectionDialog.superclass.constructor.call(this, {
					title : '栏目选择器',
					width : 630,
					height : 380,
					iconCls : 'menu-section-list',
					maximizable : true,
					border : false,
					modal : true,
					layout : 'border',
					buttonAlign : 'center',
					buttons : [{
								iconCls : 'btn-ok',
								text : '确定',
								scope : this,
								handler : this.confirm
							}, {
								text : '取消',
								iconCls : 'btn-cancel',
								scope : this,
								handler : function() {
									this.close();
								}
							}],
					items : [this.searchPanel, this.gridPanel]
				});
	},
	// 初始化UI
	initUI : function() {
		// 栏目查询面板
		this.searchPanel = new HT.SearchPanel({
					width : 400,
					layout : 'form',
					region : 'north',
					colNums : 3,
					keys : {
						key : Ext.EventObject.ENTER,
						fn : this.search,
						scope : this
					},
					labelWidth : 145,
					items : [{
								fieldLabel : '请输入查询条件:栏目名称',
								xtype : 'textfield',
								name : 'Q_sectionName_S_LK'
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
				});

		// 栏目列表面板
		this.gridPanel = new HT.GridPanel({
					region : 'center',
					singleSelect : this.isSingle != null ? this.isSingle : true,
					baseParams : {
						'Q_status_SN_EQ' : this.status == null
								? ''
								: this.status
					},
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
								header : '栏目类型',
								dataIndex : 'sectionType',
								renderer : this.sectionTypeRenderer
							}, {
								header : '状态',
								dataIndex : 'status',
								renderer : this.statusRenderer
							}]
				});
	},
	// 栏目类型
	sectionTypeRenderer : function(value) {
		switch (value) {
			case 1 : // 一般栏目
				return '<font color="green">一般栏目</font>';
			case 2 :// 桌面新闻
				return '<font color="green">桌面新闻</font>';
			case 3 :// 滚动公告
				return '<font color="green">滚动公告</font>';
			default :
				return '';
		}
	},
	// 状态
	statusRenderer : function(value) {
		if (value != null && value == 1) {
			return '<font color="green">激活</font>';
		} else {
			return '<font color="red">禁用</font>';
		}
	},
	// 重置
	reset : function() {
		this.searchPanel.getForm().reset();
	},
	// 查询
	search : function() {
		$search({
					searchPanel : this.searchPanel,
					gridPanel : this.gridPanel
				});
	},
	// 确定
	confirm : function() {
		var grid = this.gridPanel;
		var rows = grid.getSelectionModel().getSelections();
		var sectionId = '';
		var sectionName = '';
		var sectionType = '';
		for (var i = 0; i < rows.length; i++) {
			if (i > 0) {
				sectionId += ',';
				sectionName += ',';
				sectionType += ',';
			}
			sectionId += rows[i].data.sectionId;
			sectionName += rows[i].data.sectionName;
			sectionType += rows[i].data.sectionType;
		}
		if (this.callback != null) {
			this.callback.call(this.scope, sectionId, sectionName, sectionType);
		}
		this.close();
	}
});