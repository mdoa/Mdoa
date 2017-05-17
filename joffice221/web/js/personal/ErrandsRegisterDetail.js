/**
 * 显示请假的详细信息
 * 
 * @param {}
 *            dateId
 */
ErrandsRegisterDetail = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		// 调用父类构造
		ErrandsRegisterDetail.superclass.constructor.call(this, {
					title : '请假详细信息',
					id : 'ErrandsRegisterDetail',
					iconCls : 'menu-holiday',
					maximizable : true,
					autoScroll : true,
					layout : 'form',
					modal : true,
					height : 480,
					width : 770,
					border : false,
					buttonAlign : 'center',
					buttons : [{
								text : '关闭',
								iconCls : 'btn-del',
								scope : this,
								handler : this.closePanel
							}],
					items : [this.detailPanel, this.flowdetailPanel,
							this.flowImagePanel]
				});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		//详情面板
		this.detailPanel = new Ext.Panel({
					border : false,
					autoScroll : true,
					autoLoad : {
						url : __ctxPath
								+ '/pages/personal/errandsRegisterDetail.jsp?dateId='
								+ this.dateId
					}
				});

		// 显示流程审批的表单
		this.flowdetailPanel = new Ext.Panel({
					border : false,
					autoHeight : true,
					autoLoad : {
						url : __ctxPath + '/flow/processRunDetail.do?runId='
								+ this.runId,
						nocache : true
					}
				});

		//流程示意图面板		
		this.flowImagePanel = new Ext.Panel({
					layout : 'column',
					border : false,
					bodyStyle : 'padding:5px;',
					items : [new Ext.Panel({
								title : '流程示意图',
								width : '97%',
								html : '<img src="' + __ctxPath
										+ '/jbpmImage?runId=' + this.runId
										+ '"/>'
							})]
				});

	},// end of the initcomponents

	closePanel : function() {
		this.close();
	}

});