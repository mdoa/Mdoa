/**
 * 岗位选择器
 * 
 * @class BookDialog
 * @extends Ext.Window
 * @example
 * 
 * <pre>
 * new PositionDialog({
 *  	title :'选择岗位' //标题  默认是'选择岗位'，也可以自定义标题
 * 		single: true,   //是否单选 默认是单选择岗位
 * 		scope:this,   //作用域
 * 		sameLevelIds ：uids， //用户Id,多选时才有效
 * 		sameLevelNames ： unames， //用户名,多选时才有效
 * 		posSupId : posSupId,  //上级岗位Id
 *		posId : posId,    //岗位Id
 *		sameLevel : true,   //是否只选择同一上级的岗位，默认是false
 * 		callback :function(ids,names，positions){//回调函数,返回岗位ids、岗位名称和岗位
 * 
 * 		}	
 * 	}
 * </pre>
 */
PositionDialog=Ext.extend(Ext.Window,{
	//构造函数
	constructor:function(conf){
		Ext.applyIf(this,conf);
		//作用域
		this.scope=this.scope?this.scope:this;
		//默认为多单选择岗位
		this.single=this.single!=null?this.single:true;
		this.initUI();
		PositionDialog.superclass.constructor.call(this,{
			title : this.title ? this.title : '选择岗位',
			height:450,
			width:630,
			maximizable : true,
			layout:'border',
			iconCls : 'btn-position-sel',
			items:this.items,
			modal:true,
			buttonAlign:'center',
			buttons:[
			{
				text:'确定',
				iconCls:'btn-ok',
				scope:this,
				handler:this.confirm
			},{
				text:'取消',
				iconCls:'btn-cancel',
				scope:this,
				handler:this.close
			}
			]
		});
		if(!this.single){
			this.add(this.selGridPanel);
			this.doLayout();
		}
	},
	
	/**
	 * 初始化UI
	 */
	initUI:function(conf){
		//
	 	this.westPanel=new htsoft.ux.TreePanelEditor({
	 		split:true,
			split : true,
			region:'west',
	 		width:185,
	 		title:'按岗位查找',
			url : __ctxPath+'/system/treePosition.do',
			scope : this,
			autoScroll:true,
			onclick:this.posTreeClick
	 	});
	 	
		//搜索岗位列
	 	this.searchPanel=new HT.SearchPanel({
	 		region:'north',
	 		layout : 'form',
			colNums : 2,
			items : [{
				fieldLabel:'岗位名称',
				xtype:'textfield',
				flex:1,
				name:'Q_posName_S_LK'
			},{
				xtype:'button',
				iconCls:'btn-search',
				text:'查询',
				scope:this,
				handler:this.search
			},{
				name : 'posSupId',
				value : this.posSupId?this.posSupId:0,
				xtype : 'hidden'
			}
			,{
				name : 'sameLevel',
				value : this.sameLevel?'true':null,
				xtype : 'hidden'
			},{
				id:'posTreeId',
				name : 'posId',
				value : this.posId,
				xtype : 'hidden'
			}
			]
	 	});
	 	
	 	var _params = null;
	 	if(this.sameLevel&&this.sameLevel==true){
	 		_params = {
	 			posId : this.posId,
	 			posSupId : this.posSupId,
		 		sameLevel : 'true'
	 		};
	 	}

	 	//可选择的岗位列表
	 	this.gridPanel=new HT.GridPanel({
	 		singleSelect:this.single,
	 		title:'可选岗位',
	 		region:'center',
	 		baseParams : _params,
	 		isShowTbar:false,
	 		url:__ctxPath+'/system/listPosition.do',//默认查找当前岗位所在部门的岗位
	 		fields :[{name : 'posId',type : 'int'}, 'posName','depth'],
	 		columns:[
	 			{
					header : "岗位名",
					dataIndex : 'posName',
					renderer : function(value,metadata,record){
						var str='';
						var depth=record.data.depth;
						if(depth!=null&& !isNaN(depth)){
							for(var i=2;i<=depth;i++){
								str+='<img src="' + __ctxPath+ '/images/system/down.gif"/>';
							}
						}
						str+=value;
						return str;
					},
					width : 60
				}
	 		]
	 	});
	 	
	 	if(!this.single){
	 		//添加行点击事件
	 		this.gridPanel.addListener('rowclick',this.gridPanelRowClick,this);
	 		//已选的岗位列表
		 	this.selGridPanel = new HT.GridPanel({
		 		title:'已选岗位(双击行移除)',
		 		split:true,
				isShowTbar:false,
				region : 'east',
				width : 160,
				showPaging:false,
				autoScroll : true,
				store : new Ext.data.ArrayStore({
	    			fields : ['posId', 'posName']
				}),
				trackMouseOver : true,
				columns : [{
					header : "岗位名",
					dataIndex : 'posName'
				}],
				listeners:{
		 			scope:this,
		 			'rowdblclick':this.selGridPanelRowDbClick
		 		}
			}); // end of this selectedUserGrid
		 	
		 	if(this.sameLevelIds&&this.sameLevelIds.length>0){
				var selStore = this.selGridPanel.getStore();
				var arrSLids = this.sameLevelIds.split(',');
				var arrSLnames = this.sameLevelNames.split(',');
				for(var index=0;index<arrSLids.length;index++){
					var newData = {posId: arrSLids[index],posName: arrSLnames[index]};
					var newRecord = new selStore.recordType(newData);
					this.selGridPanel.stopEditing();
					selStore.add(newRecord);
				}
		 	}
	 	}
	 	
	 	this.items = [];
	 	if(!this.sameLevel){
	 		this.items.push(this.westPanel);
	 	}
	 	this.items.push(this.searchPanel);
		this.items.push(this.gridPanel);
		
	},//end of initUI function
	
	//查询
	search : function(){
		$search({
				searchPanel : this.searchPanel,
				gridPanel : this.gridPanel
		});
	},
	//已选岗位双击事件
	selGridPanelRowDbClick:function(){
		var selstore = this.selGridPanel.getStore();
		var selrows = this.selGridPanel.getSelectionModel().getSelections();
		var store = this.gridPanel.getStore();
		for(var i =0; i<selrows.length; i++){
			var posId = selrows[i].data.posId;
			var posName = selrows[i].data.posName;
			var isExist = false;
			//查找是否存在该记录
			for(var j=0;j<store.getCount();j++){
				if(store.getAt(j).data.posId == posId){
					isExist = true;
					break;
				}
			}
			selstore.remove(selrows[i]);
			if(!isExist){
				var newData = {posId : posId,posName : posName};
				var newRecord = new store.recordType(newData);
				this.selGridPanel.stopEditing();
				store.add(newRecord);
			}
		}
	},
	/**
	 * 岗位GridPanel（中间的Grid)行点击
	 * @param {} grid
	 * @param {} rowIndex
	 * @param {} e
	 */
	gridPanelRowClick:function(grid,rowIndex,e){
		var selStore = this.selGridPanel.getStore();
		var rows = this.gridPanel.getSelectionModel().getSelections();
		for(var i= 0 ; i<rows.length ; i++){
			var posId = rows[i].data.posId;
			var posName = rows[i].data.posName;
			var isExist = false;
			//查找是否存在该记录
			for(var j=0;j<selStore.getCount();j++){
				if(selStore.getAt(j).data.posId == posId){
					isExist = true;
					break;
				}
			}
			if(!isExist){
				var newData = {posId : posId,posName : posName};
				var newRecord = new selStore.recordType(newData);
				this.selGridPanel.stopEditing();
				selStore.add(newRecord);
			}
		}
	},
	//按职位查找岗位
	posTreeClick:function(){
	 	var posId=this.westPanel.selectedNode.id;
	 	if(!this.sameLevel||this.sameLevel==false){
	 		Ext.getCmp('posTreeId').setValue(posId);
	 	}
		var store = this.gridPanel.getStore();
		var baseParams=null;
		if(posId!=0){	//若orgId==0,则代表为所有岗位
			baseParams = {'posId':posId};
		}else{
			baseParams={};
		}
		baseParams.start = 0;
		baseParams.limit = store.baseParams.limit;
		store.baseParams=baseParams;
		this.gridPanel.getBottomToolbar().moveFirst();
	},
	/**
	 * 选择岗位
	 */
	confirm:function(){
		var posIds = '';
		var posNames = '';
		//返回的岗位集
		var positions=[];
		if(this.single){//选择单个岗位
			var rows = this.gridPanel.getSelectionModel().getSelections();
			for (var i = 0; i < rows.length; i++) {
				if (i > 0) {
					posIds += ',';
					posNames += ',';
				}
				posIds += rows[i].data.posId;
				posNames += rows[i].data.posName;
				positions.push(rows[i].data);
			}
		} else {
			var selStore = this.selGridPanel.getStore();
			for(var i = 0 ; i<selStore.getCount(); i++){
				if (i > 0) {
					posIds += ',';
					posNames += ',';
				}
				posIds += selStore.getAt(i).data.posId;
				posNames += selStore.getAt(i).data.posName;
				positions.push(selStore.getAt(i).data);
			}
		}

		if (this.callback){
			this.callback.call(this.scope, posIds, posNames,positions);
		}
		this.close();
	}
});