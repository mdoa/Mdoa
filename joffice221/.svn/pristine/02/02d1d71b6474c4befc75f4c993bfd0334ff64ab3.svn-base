/**
 * 套红模板选择器
 * 
 * @class PaintTemplateDialog
 * @extends Ext.Window
 * @example
 * 
 * <pre>
 * new PaintTemplateDialog({
 *  	title :'套红模板选择器' //标题  默认是'套红模板选择器'，也可以自定义标题
 * 		scope:this,   //作用域
 * 		callback :function(tName,tpath){//回调函数,返回套红模板名称和模板路径
 * 
 * 		}	
 * 	}
 * </pre>
 */
PaintTemplateDialog=Ext.extend(Ext.Window,{
	//构造函数
	constructor:function(_cfg){
	   Ext.applyIf(this,_cfg);
	   // 作用域
	   this.scope = this.scope ? this.scope : this;
	   //初始化
	   this.initUI();
	   PaintTemplateDialog.superclass.constructor.call(this,{
	        layout:'border',
	        width : 630,
	        iconCls:'menu-template',
	        title : this.title ? this.title : '套红模板选择器',
			height : 380,
			modal : true,
	        border : false,
	        buttonAlign : 'center',
			buttons : [{
						iconCls : 'btn-ok',
						text : '确定',
						scope:this,
						handler : this.confirm
					}, {
						text : '取消',
						iconCls : 'btn-cancel',
						scope:this,
						handler : function(){
							this.close();
						}
					}],
	        items:[this.searchPanel,this.gridPanel]
	   });
	},
	//初始化面板
	initUI:function(){
		//查询面板
		this.searchPanel=new HT.SearchPanel({
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
						fieldLabel : '请输入查询条件:模版名称',
						xtype : 'textfield',
						name : 'Q_templateName_S_LK'
					}, {
						xtype : 'button',
						text : '查询',
						iconCls : 'search',
						scope:this,
						handler:this.search
					},{
						xtype : 'button',
						text:'重置',
						iconCls:'btn-reseted',
						scope : this,
						handler:this.reset
					}]
		});
		//套红模板列表
	    this.gridPanel = new HT.GridPanel({
					region : 'center',
					singleSelect:true,
					// 使用RowActions
					rowActions : false,
					url : __ctxPath + "/document/listPaintTemplate.do",
					fields : [{
								name : 'ptemplateId',
								type : 'int'
							}, 'fileId', 'templateName','path'],
					columns : [{
								header : 'ptemplateId',
								dataIndex : 'ptemplateId',
								hidden : true
							}, {
								header : '模板名称',
								dataIndex : 'templateName'
							}]
						// end of columns
				});
	},
	//确认选择
	confirm : function() {
		var grid = this.gridPanel;
		var rows = grid.getSelectionModel().getSelections();
//		var sealIds = '';
		var tNames = '';
		var tpath='';
		if(rows.length>0){
		   tName = rows[0].data.templateName;
		   tpath=rows[0].data.path;
		}
		if (this.callback != null) {
			this.callback.call(this.scope,tName,tpath);
		}
		this.close();
	},
	//查询
	search :function() {
		$search({
			searchPanel : this.searchPanel,
			gridPanel : this.gridPanel
		});
	},
	//重置
    reset : function() {
		this.searchPanel.getForm().reset();
	}	
});