/**
 * 文档详细信息
 * @class DocumentDetailWin
 * @extends Ext.Window
 */
DocumentDetailWin=Ext.extend(Ext.Window,{
    showPanel:null,
	constructor:function(conf){
	   Ext.applyIf(this,conf);
	   this.initUI();
	   DocumentDetailWin.superclass.constructor.call(this,{
	       	title :'详细信息',
			iconCls:'menu-folder-shared',
			width : 710,
			modal : true,
			maximizable:true,
			layout : 'fit',
	        items:[this.showPanel],
	        buttonAlign:'center',
	        buttons:this.buttons
	   });
	},
	initUI:function(){
	    this.showPanel=new Ext.Panel({
	     id:'DocumentSharedPanel',
	     modal : true,
	     autoScroll:true,
//	     bodyStyle : 'padding-left:10%;padding-right:10%;',
	     height:450,
	     autoLoad:{url:__ctxPath+'/document/detailDocument.do?docId='+this.docId}	     
		});
		
		this.buttons=[{
		   xtype:'button',
		   text:'关闭',
		   iconCls:'btn-close',
		   scope:this,
		   handler:function(){
		      this.close();
		   }	
		}];
	}
});

