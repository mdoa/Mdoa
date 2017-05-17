/**
 * @author: YHZ
 * @class: RelativeUserView
 * @extends: Ext.Window
 * @description: 相对岗位人员管理
 * @company: 杭州梦德软件有限公司
 * @data: 2010-1-10AM
 */
SubordinateView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		SubordinateView.superclass.constructor.call(this, {
					id : 'SubordinateView',
					title : '用户上下级管理',
					iconCls : 'menu-relativeJob',
					region : 'center',
					layout : 'border',
					width : 850,
					height : 600,
					modal : true,
					maximizable : true,
					items : [this.leftPanel, this.gridPanel]
				});
	},// end of constructor
	// 初始化组件
	initUIComponents : function() {

		this.demStore=new Ext.data.ArrayStore({
			autoLoad:true,
			url:__ctxPath+'/system/comboDemension.do',
			fields:['id','name'],
			listeners:{
 				    	scope:this,
 				       'load':function(store){
 				       }
			}
		});
		//维度选择下拉
		this.demensionCombo=new Ext.form.ComboBox({
			displayField:'name',
			valueField:'id',
			editable : false,
			mode : 'local',
			emptyText:'请选择',
			width:150,
			triggerAction : 'all',
			store:this.demStore,
			listeners:{
			         scope: this,
			         'select':this.demensionSel
			}
		});
		// TODO gridPanel
		// 实例化treePanel, 加载相对岗位人员管理
		this.treePanel = new htsoft.ux.TreePanelEditor({
					region : 'center',
					autoScroll : true,
					split : true,
					width : 150,
					scope : this,
					url : __ctxPath + '/system/treeOrganization.do?demId=1',
					onclick:this.clickTree
				}); // end of this treePanel
		// 顶部按钮
		this.leftPanel=new Ext.Panel({
			collapsible : true,
			split : true,
			region:'west',
			width:150,
			title:'组织架构',
			layout:'fit',
			tbar:[{
				text:'选择维度'
			}],
			items:[{
					xtype:'panel',
					layout:'border',
					border:false,
					items:[
						{
							xtype:'panel',
							region:'north',
							border:false,
							autoHeight:true,
							layout:'fit',
							items:this.demensionCombo
						},
						this.treePanel
					]
				}
			]
		});
		this.topbar = new Ext.Toolbar({
					defaultType : 'button',
					items : [{
						iconCls : 'btn-superior',
						text : '添加上级',
						scope : this,
						handler : this.addRelativeUser
								.createDelegate(this, [1])
					}, '-', {
						iconCls : 'btn-sibling',
						text : '添加同级',
						scope : this,
						handler : this.addRelativeUser
								.createDelegate(this, [2])
					}, '-', {
						iconCls : 'btn-subordinate',
						text : '添加下级',
						scope : this,
						handler : this.addRelativeUser
								.createDelegate(this, [0])
					}]
				});
		// 上下级列表
		this.gridPanel = new HT.GridPanel({
			region : 'center',
			tbar : this.topbar,
			rowActions:true,
			// 使用RowActions
			url : __ctxPath + '/system/depUsersAppUser.do',
			fields : ['userId', 'fullname', 'username',  {
							name : 'department.depId',
							mapping : 'primaryDep'
						},
					'department'],
			columns : [{
				header : '账号',
						dataIndex : 'username'
					}, {
						header : '姓名',
						dataIndex : 'fullname'
					}, {
						header : '主部门',
						dataIndex : 'department.depId'
					},new Ext.ux.grid.RowActions({
						header : '管理',
						width : 100,
						actions : [{
												 iconCls : 'btn-superior',
												 qtip : '查看上级',
												 style : 'margin:0 3px 0 3px',
										 },{
											iconCls : 'btn-subordinate',
											qtip : '查看下级',
											style : 'margin:0 3px 0 3px'
										}, {
											iconCls : 'btn-sibling',
											qtip : '查看同级',
											style : 'margin:0 3px 0 3px'
										}],
						listeners : {
							scope : this,
							'action' : this.onRowAction
						}
					})]
				// end of columns
		});
	},// end of the initComponents()
	demensionSel:function(combo,record,index){
		this.demId=combo.getValue();
		this.treePanel.loader=new Ext.tree.TreeLoader(
	    {
	        baseParams:{demId:this.demId},
	        dataUrl: __ctxPath+'/system/treeOrganization.do',
	        requestMethod:'GET'
	    });
	    this.treePanel.selectedNode=null;
	    this.treePanel.root.reload();
	},
	/**
	 * 点击树
	 * 
	 * @param {}
	 *            node
	 */
	clickTree : function(node) {
		var depId = this.treePanel.selectedNode.id;
		var store = this.gridPanel.getStore();
		store.baseParams = {
			depId : depId
		};
		this.gridPanel.getBottomToolbar().moveFirst();
	},
	
	/**
	 * 刷新树
	 */
	reloadTreePanel : function() {
		this.treePanel.root.reload();
	},
	
	viewRecord:function(record,type){
		var demId = this.demensionCombo.getValue();
		if(demId==null || demId=='请选择' || demId==0)
			demId=1;
		if (type == 1)
			typeMsg = '上级';
		else if (type == 2)
			typeMsg = '同级';
		else
			typeMsg = '下级';
		new SubordinateForm({
				title:record.fullname+typeMsg,
				userId:record.userId,
				demId:demId,
				relative:type
			}).show();
	},
	/**
	 * 行的Action
	 * 
	 * @param {}
	 *            grid
	 * @param {}
	 *            record
	 * @param {}
	 *            action
	 * @param {}
	 *            row
	 * @param {}
	 *            col
	 */
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-superior' :
				this.viewRecord.call(this, record.data ,1);
				break;	
			case 'btn-subordinate' :
				this.viewRecord.call(this, record.data ,0);
				break;	
			case 'btn-sibling' :
				this.viewRecord.call(this, record.data ,2);
				break;	
			default :
				break;
		}
	},

	/**
	 * @description 添加用户级别[上级,下级,同级]
	 * @param type
	 *            1.上级,0.下级,2.同级
	 */
	addRelativeUser : function(type) {
		var selectRecords =this.gridPanel.getSelectionModel().getSelections();
		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择上下级管理人员！");
			return;
		}
		var userId = selectRecords[0].data.userId;
		var typeMsg = '';
		if (type == 1)
			typeMsg = '上级';
		else if (type == 2)
			typeMsg = '同级';
		else
			typeMsg = '下级';
		// 判断是否选中相对岗位
		var selNode = this.demensionCombo.getValue();
		var node;
		if(selNode==null || selNode=='请选择' || selNode==0)
			node=1;
		else
			node=selNode;
		
		Ext.Ajax.request({
			url : __ctxPath + '/system/findDinateSubordinate.do',
			method : 'post',
			params : {
				'userId' : userId, // 所属员工
				'demId' : node, // 对应的岗位
				'relative' : type
			},
			success : function(response, op) {
				var res = Ext.util.JSON.decode(response.responseText);
				if(res.success){
					var rel=res.result;
					var uid="";
					var uNames="";
					for(var i=0;i<rel.length;i++){
						uid+=rel[i].jobUser.id+",";
						uNames+=rel[i].jobUser.fullname+",";
					}
					new UserDialog({
						callback:function(jobUserIds, fullNames){
								var jobUserIdArray=jobUserIds.split(',');
								for(var i=0;i<jobUserIdArray.length;i++){
									if(userId==jobUserIdArray[i]){
										Ext.ux.Toast.msg('操作提示', '对不起，不能添加自己为' + typeMsg+'!');
										return;
									}
								}
								var delUid="";
								for(var i=0;i<rel.length;i++){
									if(jobUserIds.indexOf(rel[i].jobUser.id)==-1)
									delUid+=rel[i].jobUser.id+",";
								}
								if(jobUserIds.length<1 && delUid.length<1)
									return;
								Ext.Ajax.request({
										url : __ctxPath + '/system/mutliAddSubordinate.do',
										method : 'post',
										params : {
											'userId' : userId, // 所属员工
											'demId' : node, // 对应的岗位
											'relative' : type,  //上下级关系
											'jobUserIds':jobUserIds,
											'delIds':delUid
										},
										success : function(response, op){
											var res = Ext.util.JSON.decode(response.responseText);
											Ext.ux.Toast.msg('操作提示', res.msg,10000);
										//	alert(res.msg);
										},
										failure : function() {
											Ext.ux.Toast.msg('操作提示', '对不起，添加' + typeMsg
															+ '用户信息失败！');
										}										
									});	
						},
						single:false,
						userIds:uid,
						userName:uNames
					}).show();
				}else{
					Ext.ux.Toast.msg('操作提示', '对不起，添加' + typeMsg
							+ '用户信息失败！');
				}
			},
			failure : function() {
				Ext.ux.Toast.msg('操作提示', '对不起，添加' + typeMsg
								+ '用户信息失败！');
			}
		});
	}
});
