/*
 * 文件附件详细
 */
Ext.ns('FileAttachDetail');
/**
 * 为了兼容以前版本
 * @param {} fileId
 */
FileAttachDetail.show = function(fileId) {;
	return new FileAttachDetailDialog({fileId:fileId}).show();
};

FileAttachDetailDialog= Ext.extend(Ext.Window, {
	// 内嵌FormPanel
	formPanel : null,
	// 显示附件信息
	displayPanel : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		// 必须先初始化组件
		this.initComponents();
		FileAttachDetailDialog.superclass.constructor.call(this, {
			id : 'FileAttachDetailWin',
			title : '附件详细信息',
			layout : 'fit',
			modal : true,
			iconCls:'menu-attachment',
			items : this.displayPanel,
			autoHeight :true,
			autoScroll:true,
			maximizable : true,
			width : 600,
			buttonAlign : 'center',
			buttons: this.buttons	
		});
	},
	initComponents: function(){
		this.displayPanel = new Ext.Panel({
			flex : 1,
			id : 'FileAttachDetailPanel',
//			height : 430,
			autoScroll : true,
			border : false,
			autoLoad : {
				url : __ctxPath + '/fileDetail.do?fileId=' + this.fileId
			}
		});

		this.buttons = [{
			text : '取消',
			iconCls : 'btn-cancel',
			scope:this,
			handler : this.cancel
		} ];
	},// end of the initcomponents
	cancel : function() {
		this.close();
	}
});
