/**
 * 流程节点设置
 * @class ProcessNodeSet
 * @extends Ext.Panel
 */
ProcessNodeSet=Ext.extend(Ext.Window,{
	constructor:function(){
		this.initUI();
		
		ProcessNodeSet.superclass.constructor.call(this,{
			title:'流程' + this.name + '设置',
			tar:this.toolBar,
			layout:'form',
			modal:true,
			maximizable:true,
			items:[
				this.detailPanel,
				this.nodeImagePanel
			]
		});
	},
	initUI:function(){
		this.toolBar=new Ext.Toolbar({
			items:[
			{
				iconCls:'btn-add',
				text:'JPDL XML',
				scope:this,
				handler:this.showJpdlXmlWin
			},{
				iconCls:'btn-add',
				text:'Design XML',
				scope:this,
				handler:this.showDesignXmlWin
			}
			]
		});
		
		this.detailPanel=new Ext.Panel({
			border:0,
			autoLoad:{
				url:__ctxPath+'/flow/detailProDefinition.do' + this.defId,
				nocache:true
			}
		});
		this.nodeImagePanel=new Ext.Panel({
			
		});
		
	}
});