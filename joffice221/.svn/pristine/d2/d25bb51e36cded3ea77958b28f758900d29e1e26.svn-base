/**
 * 相对岗位选择器
 * 
 * @class RelativeJobDialog
 * @extends Ext.Window
 *          @example
 * 
 * <pre>
 * new RelativeJobDialog({
 * 		reUserIds : reUserIds, //相对岗位Ids
 * 		reUserName : reUserName, //相对岗位Names
 *  	title :'相对岗位选择器', //标题  默认是'相对岗位选择器'，也可以自定义标题
 * 		single: true,   //是否单选 默认是多选相对岗位
 * 		scope:this,   //作用域
 * 		callback :function(ids,names){//回调函数,返回相对岗位ids和相对岗位名称
 * 
 * 		}	
 * 	}
 * </pre>
 */
RelativeJobDialog =Ext.extend(Ext.Window,{
	//构造函数
	constructor:function(_cfg){
	   Ext.applyIf(this,_cfg);
	   // 作用域
	   this.scope = this.scope ? this.scope : this;
	   // 默认为多选择相对岗位
		this.single = this.single != null ? this.single : false;
	   //初始化
	   this.initUI();
	   RelativeJobDialog.superclass.constructor.call(this,{
	   		id : 'RelativeJobDialog',
			title : this.title ? this.title : '相对岗位选择器',
			iconCls : 'menu-relativeJob',
			width : 630,
			minWidth : 400,
			height : 430,
			minHeight : 430,
			maximizable : true,
			layout : 'fit',
			border : false,
			items : [this.panel],
			modal : true,
			buttonAlign : 'center',
			buttons : [{
					iconCls:'btn-ok',
					text : '确定',
					scope : this,
					handler : this.confirm
				}, {
					text : '取消',
					iconCls:'btn-cancel',
					scope : this,
					handler : function() {
						this.close();
					}
				}
			]	
	   });
	   
	},
	initUI : function(){
		//岗位树
	   	this.treePanel = new htsoft.ux.TreePanelEditor({
	   		layout : 'form',
			region : this.single != null && this.single == true ? 'center' : 'west',
			collapsible : true,
			split : true,
			width : 200,
			title : '相对岗位列表',
			url : __ctxPath + '/system/treeLoadRelativeJob.do',
			scope : this,
			autoScroll : true,
			dblclick : this.nodeDBLClick
	   	});
//	   	this.treePanel.addListener({scope : this,'dblclick': this.dblclick});
	   	this.csm = new Ext.grid.CheckboxSelectionModel();
	   	//显示已选岗位面板
		this.selectedUserGrid = new Ext.grid.EditorGridPanel({
			// TODO selectedUserGrid已选相对岗位列表
			id : 'selectedRelativeJobGridPanel',
			title : '已选相对岗位',
			layout : 'form',
			region : 'center',
			width : '100%',
			autoWidth : '100%',
			height : '100%',
			autoHeight : '100%',
			border : false,
			autoScroll : true,
			viewConfig : {
				forceFit : true,
				enableRowBody : false,
				showPreview : false
			},
			store : new Ext.data.ArrayStore({
    			fields: ['jobId', 'jobName']
			}),
			trackMouseOver : true,
			sm : this.csm,
			columns : [ this.csm, new Ext.grid.RowNumberer(), {
					header : 'jobId',
					dataIndex : 'jobId',
					hidden : true
				},{
					header : "岗位名称",
					dataIndex : 'jobName'
				}
			]
		});
	   	// 初始化已选岗位
	 	if(this.reUserIds&&this.reUserIds.length>0){
			var selStore = this.selectedUserGrid.getStore();
			var arrReUserIds = this.reUserIds.split(',');
			var arrReUserName = this.reUserName.split(',');
			for(var index=0;index<arrReUserIds.length;index++){
				var newData = {jobId: arrReUserIds[index],jobName: arrReUserName[index]};
				var newRecord = new selStore.recordType(newData);
				this.selectedUserGrid.stopEditing();
				selStore.add(newRecord);
			}
	 	}
	 	//添加双击事件，移除已选数据
		this.selectedUserGrid.addListener({scope : this,'dblclick': this.dblclick});
		//多选面板
		this.multiPanel = new Ext.Panel({
			layout : 'border',
			region : 'center',
			width : '100%',
			height : '100%',
			border : false,
			autoScroll : true,
			items : [new Ext.Panel({
				region : 'west',
				frame : true,
				width : 40,
				layout : {
                    type : 'vbox',
                    pack : 'center',
                    align : 'stretch'
                },
                defaultType : 'button',
                items : [{
                	iconCls : 'add-all',
                	text : '',
                	scope : this,
                	handler : this.addAll
                },{
                	iconCls : 'rem-all',
                	text : '',
                	scope : this,
                	handler : this.removeAll
                }]
			}),{
				region : 'center',
				autoScroll : true,
				items : [this.selectedUserGrid]
			}]
		});
		
		this.panel = new Ext.Panel({
			// TODO panel总面板
			layout : 'border',
			region : 'center',
			autoScroll : true,
			border : false,
			anchor : '98%,98%',
			items : [this.treePanel]
		});
		//添加：[中间] 多选面板
		if(this.single != null && this.single == false){
			this.panel.add(this.multiPanel);
			this.panel.doLayout();
		}
	},
	//已选岗位双击删除
	dblclick : function(grid){
		var grid = this.selectedUserGrid;
		var store = grid.getStore();
		var rows = grid.getSelectionModel().getSelections();
		for(var i=0;i<rows.length;i++){
			grid.stopEditing();
			store.remove(rows[i]);
		}
	},
	//树节点双击
	nodeDBLClick : function(node){
		if(node != null && node.id>0){
			var grid = this.selectedUserGrid;
			var store = grid.getStore();
			var isExist = true;//默认不存在
			for(var i=0; i<store.getCount(); i++){
				if(store.getAt(i).data.jobId == node.id){
					isExist = false;
					break;
				}
			}
			//4.添加数据
			if(isExist){
				var newData={jobId:node.id,jobName:node.text};
				var newRecord=new store.recordType(newData);
				grid.stopEditing();
				store.add(newRecord);
			}
		}
	},
	//添加所有选中岗位
	addAll : function(){
		var tree = this.treePanel;
		var grid = this.selectedUserGrid;
		//1.获取选中的节点
		var node = tree.getSelectionModel().getSelectedNode();
		if(node != null && node.id>0){
			//2.获取store
			var store = grid.getStore();
			//3.判断是否已经存在
			var isExist = true;//默认不存在
			for(var i=0; i<store.getCount(); i++){
				if(store.getAt(i).data.jobId == node.id){
					isExist = false;
					break;
				}
			}
			//4.添加数据
			if(isExist){
				var newData={jobId:node.id,jobName:node.text};
				var newRecord=new store.recordType(newData);
				grid.stopEditing();
				store.add(newRecord);
			}
		}
	},
	//移除所有已选岗位
	removeAll : function(){
		var grid = this.selectedUserGrid;
		var rows = grid.getSelectionModel().getSelections();
		var store=grid.getStore();
		
		for(var i=0;i<rows.length;i++){
			grid.stopEditing();
			store.remove(rows[i]);
		}
	},
	confirm : function() {
		var ids = '';
		var names = '';
		if(this.single == null || this.single == true){ //单选
			var node = this.treePanel.getSelectionModel().getSelectedNode();
			if(node != null && node.id>0){
				ids = node.id;
				names = node.text;
			}
		} else { //多选
			var store = this.selectedUserGrid.getStore();
			for(var i=0;i<store.getCount();i++){
				ids += store.getAt(i).data.jobId+',';
				names += store.getAt(i).data.jobName+',';
			}
			ids = ids.substring(0, ids.length-1);
			names = names.substring(0, names.length-1);
		}
		if(this.callback != null)
			this.callback.call(this.scope, ids, names);
		this.close();
	}
});
