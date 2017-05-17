/**
 * @createtime:2010-01-20
 * @author csx
 * @description 公文拟稿发文界面
 * @class ArchivesDetailWin
 * @extends Ext.Panel
 */
ArchivesDetailWin = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.init();
		// 调用父类构造
		ArchivesDetailWin.superclass.constructor.call(this, {
					title : '公文详情',
					id : 'ArchivesDetailWin',
					iconCls : 'btn-archives-detail',
					maximizable : true,
					autoScroll:true,
					layout : 'fit',
					modal : true,
					height : 480,
					width : 770,
					border : false,
					buttonAlign : 'center',
					buttons :  [ {
									text : '关闭',
									iconCls : 'btn-del',
									handler : this.closePanel,
									scope : this
								}],
					items : this.panel
				});
	},
	/**
	 * 关闭Panel
	 */
	closePanel : function() {
		this.close();
	},
	
	// 初始化组件
	init : function() {

		//公文详细面板
		this.detailPanel = new Ext.Panel({
			border : false,
			autoScroll:true,
			autoHeight:true,
			autoLoad:{
				url:__ctxPath+ '/pages/archive/archiveInfo.jsp?archivesId='+ this.archivesId+ '&rand='+Math.random()
			}
		});
		// 流程审批面板
		this.flowdetailPanel=new Ext.Panel({
			border :false,
			autoHeight:true,
			autoLoad:{
				url:__ctxPath+'/flow/processRunDetail.do?runId='+this.runId+ '&rand='+Math.random(),
				nocache: true
			}
		});
		//流程示意图
		this.flowImagePanel=new Ext.Panel({
	 		layout : 'column',
	 		border : false,
	 		bodyStyle : 'padding:5px;',
	 		autoHeight:true,
	 		items : [
	 			new Ext.Panel({
	 				title:'流程示意图',
	 				html:'<img src="'+__ctxPath+ '/jbpmImage?runId='+this.runId+ '&rand='+Math.random()+'"/>'
	 			})
	 		]});
	 		
	 	this.panel =   new Ext.Panel({
			layout : 'form',
			autoScroll:true,
			border:false,
			bodyStyle : 'padding:5px;',
			items:[ this.detailPanel,
					         this.flowdetailPanel,
					         this.flowImagePanel]
		
		});
		
	}// end of init
});