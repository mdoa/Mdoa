/**
 * 流程表单选择器
 * 
 * @class FormDefDialog
 * @extends Ext.Window
 * @example
 * 
 * <pre>
 * new FormDefDialog({
 *  	title :'流程表单选择' //标题  默认是'流程表单选择'，也可以自定义标题
 * 		scope:this,   //作用域
 * 		callback :function(formDefId,formTitle){//回调函数,返回表单id和表单标题
 * 
 * 		}	
 * 	}
 * </pre>
 */

FormDefDialog=Ext.extend(Ext.Window,{
	//构造函数
	constructor:function(conf){
		Ext.applyIf(this,conf);
		//作用域
		this.scope=this.scope?this.scope:this;
		//初始化
		this.initUI();
		FormDefDialog.superclass.constructor.call(this,{
			title:'流程表单选择',
			iconCls : 'menu-form',
			maximizable : true,
			modal : true,
			width : 650,
			height : 400,
			layout : 'border',
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
	// 初始化UI
	initUI:function(){
		//查询面板
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
			labelWidth : 65,
			items : [{
				fieldLabel : '表单名称',
				xtype : 'textfield',
				name : 'Q_formTitle_S_LK',
				maxLength : 128
			},{
				xtype : 'button',
				text : '搜索',
				iconCls : 'search',
				handler : this.search,
				scope : this
			}]
		});
		//表单列表
		this.gridPanel =new HT.GridPanel({
			region : 'center',
			width : 400,
			height : 300,
			url : __ctxPath + "/flow/listFormDef.do?Q_status_SN_EQ=1",
			// 使用RowActions
			rowActions : true,
			fields : [{
				name : 'formDefId',
				type : 'int'
			}, 'formTitle', 'formDesp'],
			columns : [{
				header : 'formDefId',
				dataIndex : 'formDefId',
				hidden : true
			}, {
				header : '表单标题',
				dataIndex : 'formTitle'
			}, {
				header : '描述',
				dataIndex : 'formDesp',
				sortable:false
			}, new Ext.ux.grid.RowActions({
				header : '管理',
				width : 80,
				actions : [{
					iconCls : 'btn-showDetail',
					qtip : '查看',
					style : 'margin:0 3px 0 3px'
				}],
				listeners : {
								scope : this,
								'action' : this.onRowAction
							}
			})]
		});
	},//end of initUI function
	//查询
	search:function() {
		$search({
			searchPanel : this.searchPanel,
			gridPanel : this.gridPanel
		});
	},
	//管理
	onRowAction : function(gridPanel, record, action, row, col) {
		switch (action) {
			case 'btn-showDetail' :
				this.showManagement.call(this,record.data.formDefId);
				break;
		}
	},
	
	//详细信息
	showManagement : function(formDefId){
		FormDefDetailForm.show(formDefId);
	},
	//选择确认
	confirm:function(){
		var record =this.gridPanel.getSelectionModel().getSelections();
		if(record != null && record.length > 0){
			if (this.callback) 
				this.callback.call(this.scope, record[0].data.formDefId, record[0].data.formTitle);
		}else
			this.callback.call(this.scope,null,null);
		this.close();
	}
});