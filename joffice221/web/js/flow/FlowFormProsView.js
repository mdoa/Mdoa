/**
 * @author:
 * @class FlowFormProsView
 * @extends Ext.Panel
 * @description 流程表单查询
 * @company 杭州梦德软件有限公司
 * @createTime:
 */
FlowFormProsView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		FlowFormProsView.superclass.constructor.call(this, {
			id : 'FlowFormProsView',
			title : '流程表单查询',
			iconCls:'menu-biaodan',
			region : 'center',
			layout : 'border',
			items : [ this.leftPanel, this.proPanel ]
		});
	},// end of constructor
	// 初始化组件
	typeId:0,
	initUIComponents : function() {
		
		this.treePanel=new htsoft.ux.TreePanelEditor({
			border : false,
			autoScroll : true,
			url : __ctxPath + '/system/flowTreeGlobalType.do?catKey=FLOW',
			scope:this,
			onclick : function(node) {
				this.QueryForms.items.items[0].getCmpByName('typeId').setValue(node.id);
				var gridPanel = this.QueryForms.items.items[1];
				Ext.apply(gridPanel.getStore().baseParams,{'typeId':node.id});
				gridPanel.getBottomToolbar().moveFirst();
			}
		});
		
		this.leftPanel = new Ext.Panel( {
			layout : 'fit',
			region : 'west',
			collapsible : true,
			split : true,
			width : 200,
			title : '流程分类树',
			items : [this.treePanel]
		});		

		this.QueryForms = new FlowFormQueryForms({treePanel:this.treePanel}).show();
		this.QueryForms.gridPanel.addListener('rowdblclick',
				this.QueryForms_rowClick, this);
		this.QueryForms.rowActions.on('action',
				this.QueryForms_onRowAction, this);

		this.proPanel = new Ext.Panel( {
			title : '流程表单',
			layout : 'border',
			region : 'center',
			autoScroll : true,
			items : [this.QueryForms]
		});

	},



	showForms : function(record) {
		var centerPanel = Ext.getCmp('centerTabPanel');
		centerPanel.remove(this.QueryView,true);
		
		this.QueryView = centerPanel.add(new FlowFormQueryView( {
			defId : record.data.defId,
			name : record.data.name,
			description : record.data.description,
			deployId : record.data.deployId
		}));
		
		centerPanel.activate(this.QueryView);
		
		
	},
	
	activeEntity:function(record){
		var center = Ext.getCmp('centerTabPanel');
		center.remove(this.QueryEntity,true);
		this.QueryEntity = center.add(new FlowFormQueryEntity({
			tableKey:record.data.tableKey,
			tableId:record.data.tableId,
			title : record.data.tableName
		}));
		center.activate(this.QueryEntity);
	},



	QueryForms_rowClick : function(grid, rowindex, e) {
		var record = grid.getStore().getAt(rowindex);
		this.activeEntity.call(this, record);
	},
	QueryForms_onRowAction : function(grid, record, action, row, col) {

		switch (action) {
			case 'btn-showDetail' :
				this.activeEntity.call(this, record);
				break;
			default :
				break;
		}

	}

});
