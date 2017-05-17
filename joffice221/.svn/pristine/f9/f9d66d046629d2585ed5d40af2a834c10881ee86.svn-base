Ext.ns('DocumentSharedDetail');

/**
 * 共享文档 详细信息
 * 
 * @class DocumentSharedDetail
 * @extends Ext.Window
 */
DocumentSharedDetail = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(conf) {
		Ext.apply(this, conf);
		// 必须先初始化组件
		this.initComponents();
		//调用父类构造
		DocumentSharedDetail.superclass.constructor.call(this, {
					id : 'DocumentSharedDetail',
					title : this.docName + '-详细信息',
					iconCls : 'menu-folder-shared',
					autoWidth : true,
					autoHeight : true,
					modal : true,
					autoScroll : true,
					layout : 'anchor',
					plain : true,
					items : [this.displayPanel]
				});
	},
	//初始化组件
	initComponents : function() {
		//显示文档明细面板
		this.displayPanel = new Ext.Panel({
					id : 'DocumentSharedPanel',
					modal : true,
					autoWidth : true,
					autoHeight : true,
					autoScroll : true,
					bodyStyle : 'padding-left:10%;padding-right:10%;',
					autoLoad : {
						url : __ctxPath
								+ '/document/detailDocument.do?docId='
								+ this.docId
					}
				});
	}// end of the initcomponents
});
