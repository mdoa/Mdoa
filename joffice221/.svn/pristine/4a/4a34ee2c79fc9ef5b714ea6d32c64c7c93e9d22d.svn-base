/**
 * 流程选择器
 * 
 * @class FlowDialog
 * @extends Ext.Window
 * @example
 * 
 * <pre>
 * new BookDialog({
 *  	title :'流程选择' //标题  默认是'流程选择'，也可以自定义标题
 * 		single: true,   //是否单选 默认是多选流程
 * 		scope:this,   //作用域
 * 		callback :function(flowIds, flowNos ,processNames){//回调函数,返回流程flowIds、流程名称和processNames
 * 
 * 		}	
 * 	}
 * </pre>
 */
FlowDialog=Ext.extend(Ext.Window,{
	//构造函数
	constructor:function(conf){
		Ext.applyIf(this,conf);
		//作用域
		this.scope=this.scope?this.scope:this;
		//默认为多单选择流程
		this.single=this.single!=null?this.single:false;
		//初始化
		this.initUI();
		FlowDialog.superclass.constructor.call(this,{
			title:this.title?this.title:'流程选择',
			iconCls:'menu-flow',
			width : 630,
			height : 380,
			layout : 'border',
			border : false,
			items : [this.searchPanel,this.gridPanel],
			modal:true,	
			buttonAlign : 'center',
			buttons : [{
						iconCls : 'btn-ok',
						text : '确定',
						scope : this,
						handler : this.confirm
					}, {
						text : '取消',
						iconCls : 'btn-cancel',
						scope:this,
						handler : function() {
							this.close();
						}
					}]
		});
	},
	
	//初始化UI
	initUI:function(){
		//查询表单
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
				labelWidth : 155,
				items : [{
							fieldLabel : '请输入查询条件:流程的名称',
							xtype : 'textfield',
							name : 'Q_name_S_LK'
						}, {
							xtype : 'button',
							text : '查询',
							iconCls : 'search',
							scope:this,
							handler : this.search
						},{
							xtype : 'button',
							text : '清空',
							iconCls : 'reset',
							scope : this,
							handler : this.reset
						}]
		});
		//流程列表
		this.gridPanel = new HT.GridPanel({
			width : 400,
			height : 300,
			region : 'center',
			title : '流程列表',
			url : __ctxPath + '/flow/listProDefinition.do',
			singleSelect : this.single,
			fields : [{
				name : 'defId',
				type : 'int'
			}, 'name', 'description'],
			columns : [{
				header : 'defId',
				dataIndex : 'defId',
				hidden : true
			},{
				header : '流程的名称',
				dataIndex : 'name'
			}, {
				header : '描述',
				dataIndex : 'description'
			}]
		});
	},//end of initUI function
	//查询
	search:function() {
		$search({
			searchPanel : this.searchPanel,
			gridPanel : this.gridPanel
		});
	},
	//清空
	reset : function() {
		this.searchPanel.getForm().reset();
	},
	//选择确认
	confirm:function(){
		var grid = this.gridPanel;
		var rows = grid.getSelectionModel().getSelections();
		var flowIds = '';
		var flowNos = '';
		var processNames = '';
		for (var i = 0; i < rows.length; i++) {

			if (i > 0) {
				flowIds += ',';
				flowNos += ',';
				processNames += ','
			}

			flowIds += rows[i].data.defId;
			flowNos += rows[i].data.name;
			processNames += rows[i].data.processName;

		}

		if (this.callback) {
			this.callback.call(this, flowIds, flowNos ,processNames);
		}
		this.close();
	}
});