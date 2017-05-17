/**
 * 流程详细页
 */
ProDefinitionDetail = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		this.initUI();
		ProDefinitionDetail.superclass.constructor.call(this, {
	 		id:'ProDefinitionDetail_'+this.defId,
	 		title:'流程详细信息－'+this.name,
			layout : 'border',
			items : [ this.leftPanel, this.rightPanel ]
		});
	},
	// 初始化组件
	initUI : function() {
	 	this.leftPanel=new Ext.Panel({
	 		title:'流程示意图',
	 		width:500,
	 		height:800,
	 		autoScroll:true,
	 		split:true,
	 		region:'west',
	 		margin:'5 5 5 5',
	 		html:'<img src="'+__ctxPath+ '/jbpmImage?defId='+this.defId+ '&rand='+Math.random()+'"/>'
	 	});
	 	this.rightPanel=new Ext.Panel({
	 		title:'流程描述',
	 		width:400,
	 		height:800,
	 		margin:'5 5 5 5',
	 		region:'center',
	 		autoScroll:true,
	 		autoLoad:{
				url:__ctxPath+'/flow/processDetail.do?defId='+this.defId+'&rand='+Math.random()
			}
	 	});
	}
});