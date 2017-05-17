/**
 * 项目选择器
 * 
 * @class ProjectDialog
 * @extends Ext.Window
 * @example
 * 
 * <pre>
 * new ProjectDialog({
 *  	title :'选择项目' //标题  默认是'选择项目'，也可以自定义标题
 * 		single: true,   //是否单选 默认是单选图书
 * 		scope:this,   //作用域
 * 		callback :function(ids,names，projectNo){//回调函数,返回项目ids、项目名称和项目编号
 * 
 * 		}	
 * 	}
 * </pre>
 */
/**
 *  项目选择器
 * @class ProjectDialog
 * @extends Ext.Window
 */
ProjectDialog=Ext.extend(Ext.Window,{
	//构造函数
	constructor:function(conf){
		Ext.applyIf(this,conf);
		//作用域
		this.scope=this.scope?this.scope:this;
		// 默认为单选项目
		this.single = this.single != null ? this.single : true;
		//初始化
		this.initUI();
		ProjectDialog.superclass.constructor.call(this,{
			title:this.title?this.title:'选择项目',
			iconCls:'menu-project',
			width : 430,
			height : 380,
			modal:true,
			layout:'border',
			border : false,
			buttonAlign:'center',
			buttons:[
			{
				iconCls : 'btn-ok',
				text : '确定',
				scope : this,
				handler:this.confirm
			},{
				text:'取消',
				iconCls:'btn-cancel',
				scope:this,
				handler:function(){
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
			labelWidth : 120,
			items : [{
				fieldLabel : '查询条件:项目编号',
				xtype : 'textfield',
				width : 80,
				name : 'Q_projectNo_S_LK'
			}, {
				fieldLabel : '项目名称',
				xtype : 'textfield',
				width : 80,
				name : 'Q_projectName_S_LK',
				labelWidth : 65
			},{
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
		//项目列表
		this.gridPanel = new HT.GridPanel({
			singleSelect:this.single,
			width : 400,
			height : 300,
			region : 'center',
			title : '项目列表',
			shim : true,
			url : __ctxPath + '/customer/listProject.do',
			trackMouseOver : true,
			disableSelection : false,
			loadMask : true,
			viewConfig : {
				forceFit : true,
				enableRowBody : false,
				showPreview : false
			},
			fields : [{
				name : 'projectId',
				type : 'int'
			}, 'projectNo','projectName','customer','fullname','reqDesc'],
			columns : [{
				header : 'projectId',
				dataIndex : 'projectId',
				hidden : true
			}, {
				header : '项目编号',
				dataIndex : 'projectNo',
				width : 60
			}, {
				header : "项目名称",
				dataIndex : 'projectName',
				width : 60
			},{
				header : '所属客户',
				dataIndex : 'customer',
				width : 60,
				renderer:this.customer
			},{
				header : '联系人',
				dataIndex : 'fullname',
				width : 60
			},{
				header : '项目描述',
				dataIndex : 'reqDesc',
				hidden:true
			}]
		});
	},
	//查询
	search :function() {
		$search({
			searchPanel : this.searchPanel,
			gridPanel : this.gridPanel
		});
	},
	//确认选择
	confirm : function(){
		var grid = this.gridPanel;
		var rows = grid.getSelectionModel().getSelections();
		var projectId = '';
		var projectName = '';
		var projectNo = ''; 
//		var customerName = '';
//		var fullname = '';
//		var reqDesc = '';
		for (var i = 0; i < rows.length; i++) {

			if (i > 0) {
				projectId += ',';
				projectName += ',';
				projectNo +=','; 
//				customerName += ',';
//				fullname += ',';
//				reqDesc += ',';
			}

			projectId += rows[i].data.projectId;
			projectName += rows[i].data.projectName;
			projectNo += rows[i].data.projectNo;
//			customerName += rows[i].data.customer.customerName;
//			fullname += rows[i].data.fullname;
//			reqDesc += rows[i].data.reqDesc;

		}

		if (this.callback != null) {
			this.callback.call(this, projectId, projectName,projectNo
			//customerName,fullname,reqDesc
			);
		}
		this.close();
	},
	//重置查询表单
    reset : function() {
		this.searchPanel.getForm().reset();
	},
	//返回客户姓名
	customer : function(value){
		return value.customerName;
	}
	
	
	
});