/**
 * 办公用品选择器
 * 
 * @class GoodsDialog
 * @extends Ext.Window
 * @example
 * 
 * <pre>
 * new GoodsDialog({
 *  	title :'办公用品选择' //标题  默认是'办公用品选择'，也可以自定义标题
 * 		single: true,   //是否单选 默认是多选办公用品
 * 		scope:this,   //作用域
 * 		callback :function(ids,names){//回调函数,返回办公用品ids和办公用品名称
 * 
 * 		}	
 * 	}
 * </pre>
 */
GoodsDialog=Ext.extend(Ext.Window,{ 
	//构造函数
	constructor:function(conf){
		Ext.applyIf(this,conf);
		// 作用域
		this.scope = this.scope ? this.scope : this;
		//默认为多选办公用品
		this.single=this.single!=null?this.single:false;
		//初始化
		this.initUI();
		GoodsDialog.superclass.constructor.call(this,{
			title : '办公用品选择',
			iconCls:'menu-goods',
			width : 630,
			height : 380,
			layout : 'border',
			border : false,
			modal : true,
			buttonAlign : 'center',
			buttons:[
			{
				iconCls : 'btn-ok',
				text : '确定',
				scope : this,
				handler:this.confirm
			},{
				text : '取消',
				iconCls : 'btn-cancel',
				scope : this,
				handler : function(){
					this.close();
				}
			}],
			items : [this.leftPanel,this.searchPanel, this.gridPanel]
		});
	},	
	//初始化面板
	initUI:function(){
		//树
		this.treePanel = new htsoft.ux.TreePanelEditor({
			url : __ctxPath+ '/admin/treeOfficeGoodsType.do',
			scope : this,
			autoScroll:true,
			split : true,
			onclick:this.click
		});
		this.leftPanel = new Ext.Panel({
		 	region:'west',
		 	title:'商品显示',
		 	layout:'fit',
		 	collapsible : true,
			split : true,
			border:false,
			width : 200,
		 	items:[this.treePanel]			
		});
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
			items:[{
				fieldLabel : '请输入查询条件: 商品名称',
				xtype : 'textfield',
				name : 'Q_goodsName_S_LK'
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
		//办公用品列表面板
		 this.gridPanel = new HT.GridPanel({
			singleSelect:this.isSingle!=null?this.isSingle:true,
			width : 400,
			height : 300,
			region : 'center',
			title : '办公用品列表',
			url : __ctxPath + '/admin/listOfficeGoods.do',			
			fields : [{
				name : 'goodsId',
				type : 'int'
			}, 'goodsName', {
				name : 'stockCounts',
				type : 'int'
			}, {
				name : 'warnCounts',
				type : 'int'
			},'isWarning'],
			columns:[{
				header : 'typeId',
				dataIndex : 'typeId',
				hidden : true
			},{
				header : "商品名称",
				dataIndex : 'goodsName',
				width : 60
			},{
				header : '库存数',
				dataIndex : 'stockCounts',
				width : 60,
				renderer:this.stockCounts
			}]
		 });
	},
	//确认选择
	confirm : function() {
		var grid = this.gridPanel;
		var rows = grid.getSelectionModel().getSelections();
		var goodsIds = '';
		var goodsNames = '';
		for (var i = 0; i < rows.length; i++) {
			if (i > 0) {
				goodsIds += ',';
				goodsNames += ',';
			}
			goodsIds += rows[i].data.goodsId;
			goodsNames += rows[i].data.goodsName;
		}
		if (this.callback != null) {
			this.callback.call(this, goodsIds, goodsNames);
		}
		this.close();
	},
	
	//树单击
	click :function(node) {
		if (node != null) {
			var goodss = this.gridPanel;
			var store = goodss.getStore();
			store.baseParams = {
				'Q_officeGoodsType.typeId_L_EQ' : node.id == 0? null: node.id
			};
			store.load();
		}
	},
	//重置查询表单
	reset : function() {
		this.searchPanel.getForm().reset();
	},
	//查询
	search :function() {
		$search({
			searchPanel : this.searchPanel,
			gridPanel : this.gridPanel
		});
	},
	//报警
	stockCounts :function(value, metadata, record, rowIndex,colIndex) {
		var warnCounts = record.data.warnCounts;
		var isWarning = record.data.isWarning;
		if (value <= warnCounts && isWarning == '1') {
			return '<a style="color:red;" title="已少于警报库存！">'
					+ value + '</a>';
		} else {
			return value;
		}
	}
	
	
});