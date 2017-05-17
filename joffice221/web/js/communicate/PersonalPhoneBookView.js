/**
 * 个人通讯薄目录视图
 * @author YungLocke
 * @class PersonalPhoneBookView
 * @extends Ext.Panel
 */
Ext.ns("PersonalPhoneBookView");
PersonalPhoneBookView=Ext.extend(Ext.Panel,{
	//构造方法
	constructor:function(_cfg){
	   Ext.applyIf(this,_cfg);
	   //必须先初始化
	   this.initUI();
	   //调用父类的构造方法
	   PersonalPhoneBookView.superclass.constructor.call(this,{
	        title : '我的通讯薄',
			iconCls : "menu-personal-phoneBook",
			layout : 'border',
			id : 'PersonalPhoneBookView',
			height : 800,
			items : [this.leftPanel, this.phoneBookView]
	   });
	},
	//初始化面板
	initUI:function(){
		//通讯簿分组树
	    this.treePanel=new htsoft.ux.TreePanelEditor({
			autoScroll : true,
			border:false,
			collapsible : false,
			scope:this,
			enableDD : true, 
			url : __ctxPath + '/communicate/listPhoneGroup.do',
			onclick:this.clickNode
		});
		//为树添加右击事件
		this.treePanel.on('contextmenu', this.contextmenu, this);
		this.leftPanel=new Ext.Panel({
			layout:'fit',
			collapsible : true,
			region : 'west',
			title : '我的通讯分组',
			width : 180,
			autoScroll : true,
			split : true,
			items:[this.treePanel]
		});
		//查询面板
		this.searchPanel = new HT.SearchPanel({
			layout : 'form',
			region : 'north',
			colNums : 4,
			keys : [{
				key : Ext.EventObject.ENTER,
				fn : this.search,
				scope : this
			},{
				key : Ext.EventObject.ESC,
				fn : this.reset,
				scope : this
			}],
			labelWidth : 120,
			items : [{	
						fieldLabel : '查询条件：姓名',
						xtype : 'textfield',
						name : 'Q_fullname_S_LK',
						width : 125,
						maxLength : 125
					},{	
						fieldLabel : '称谓',
						name : 'Q_title_S_LK',
						xtype : 'combo',
						anchor : '95%',
						labelWidth :50,
						width : 125,
						maxLength : 125,
						editable : false,
						mode : 'local',
						triggerAction : 'all',
						store : ['先生', '女士']
					},{
						xtype : 'button',
						text : '查询',
						style : 'padding-left:5px;',
						iconCls : 'search',
						handler : this.search,
						scope : this
					},{
						xtype : 'button',
						text : '重置',
						style : 'padding-left:5px;',
						iconCls : 'btn-reset',
						handler : this.reset,
						scope : this
					},{
					    xtype:'hidden',
						name:'Q_appUser.userId_L_EQ',
						value:curUserInfo.userId
					},{
					    xtype:'hidden',
					    name:'Q_phoneGroup.isPublic_SN_EQ',
					    value:0
					}]
		});
		this.tpl = 	new Ext.XTemplate(
					'<tpl for=".">',
					'<div class="thumb-wrap" id="{phoneId}" isShared="{isShared}">',
					'<span><font>姓名&nbsp;：</font>{fullname} {[values.isShared == 1 ? "（共享）" : ""]}</span>',
					'<span><font>手机&nbsp;：</font>{mobile}</span>',
					'<span><font>电话&nbsp;：</font>{phone}</span>',
					'<span><font>Email：</font>{email}</span>',
					'<span><font>QQ&nbsp;&nbsp;&nbsp;：</font>{qqNumber}</span>',
					'<span><font>MSN&nbsp;&nbsp;：</font>{msn}</span>',
					'</div>', '</tpl>'),	
		//显示联系人明细
		this.dataView = new HT.DataView({
			id : 'phones',
			region : 'center',
			border : false,
			url : __ctxPath+ '/communicate/listPhoneBook.do',
			baseParams:{
				       'Q_appUser.userId_L_EQ':curUserInfo.userId,
				       'Q_phoneGroup.isPublic_SN_EQ':0
				},
			fields : [{
							name : 'phoneId',
							type : 'int'
						}

						, 'fullname', 'title', 'birthday',
						'nickName', 'duty', 'spouse', 'childs',
						'companyName', 'companyAddress',
						'companyPhone', 'companyFax',
						'homeAddress', 'homeZip', 'mobile',
						'phone', 'email', 'qqNumber', 'msn',
						'note', 'userId', 'groupId', 'isShared'],
			sort : [{
								field : 'phoneId',
								direction : 'DESC'
							}],		
			tpl : this.tpl,
//			height:326,
			autoScroll:true,
			multiSelect : true,
			overClass : 'x-view-over',
			itemSelector : 'div.thumb-wrap',
			emptyText : '目前尚无记录',
			plugins:[
			   new this.DragSelector()
			],
			listeners : {
				'render' : {
						fn : this.bodyRender,
						scope : this
					},
				'dblclick' : {
					fn : this.editRecord,
					scope : this
				},
				'contextmenu' : {
					fn : this.showMenu,
					scope : this
				}
			}
		});
		//联系人列表顶部菜单面板
		this.toolbar = new Ext.Toolbar({
			id : 'PhoneBookFootBar',
			height : 28,
			bodyStyle : 'text-align:left',
			items : [{
				iconCls : 'btn-add',
				xtype : 'button',
				text : '添加',
				scope : this,
				handler : this.createRecord
			}, '-', {
			     iconCls:'btn-del',
			     xtype:'button',
			     text:'删除',
			     scope : this,
			     handler:this.deleteRecord
			}]
		});
		//联系人列表显示面板
		this.gridPanel = new Ext.Panel({
					region : 'center',
					tbar : this.toolbar,
					layout : 'fit',
//					autoHeight : true,
//					autoScroll:true,
					defaults:{
					   anchor : '96%,96%'
					},
					items : this.dataView,
					bbar : new Ext.PagingToolbar({
							pageSize : 15,
							store : this.dataView.getStore(),
							displayInfo : true,
							displayMsg : '当前显示从{0}至{1}， 共{2}条记录',
							emptyMsg : "当前没有记录"
						})
				});

		//个人通讯簿详细信息列表
		this.phoneBookView = new Ext.Panel({
			title : '联系人列表',
			region : 'center',
			autoScroll : true,
			layout : 'border',
			items : [this.searchPanel, this.gridPanel]
		});
	},
	//右键菜单
	bodyRender : function(p) {
		p.getEl().on('contextmenu', this.bodyContextClick, this);
	},
	bodyContextClick : function(e, t, o) {
		var target = t;
		if (target.id != 'phones') {
			return;
					
		}
		var menuItems = new Array();

		menuItems.push({
					text : '添加',
					scope : this,
					disabled : this.isSearching ? true : false,
					iconCls : 'btn-add',
					handler : this.createRecord
				});
		menuItems.push({
					text : '刷新',
					scope : this,
					iconCls : 'btn-refresh',
					disabled : this.isSearching ? true : false,
					handler : this.refresh
				});
		var menus = new Ext.menu.Menu({
					items : menuItems
				});
		menus.showAt(e.getXY());
	},
	//刷新
	refresh : function(){
		this.dataView.getStore().reload({params : {start : 0,limit : 15}});
		this.treePanel.root.reload();
	},
	
	//树左键单击事件
	clickNode:function(node) {
		this.selectNode = node;
		if (node != null) {
			var phoneBookView = this.phoneBookView;
			if (node.id == 0) {
				phoneBookView.setTitle('所有联系人');
			} else {
				phoneBookView.setTitle(node.text + '组列表');
			}
			var store = this.dataView.getStore();
			store.url = __ctxPath + '/communicate/listPhoneBook.do';
			store.baseParams = {
				'Q_phoneGroup.groupId_L_EQ' : node.id>0?node.id:'',
				'Q_appUser.userId_L_EQ':curUserInfo.userId,
				'Q_phoneGroup.isPublic_SN_EQ':0
			};
			store.reload({params : {start : 0,limit : 15}});
		}
	},
	//树右击事件
	contextmenu:function(node, e) {
		this.clickNode(node);
		selected = new Ext.tree.TreeNode({
				id : node.id,
				text : node.text
			});
			// 创建右键菜单
			var treeMenu = new Ext.menu.Menu({
				items : []
			});
			treeMenu.clearMons();
			treeMenu.add({
						text : '新建组',
						scope : this,
						iconCls : 'btn-add',
						handler : this.createNode
					});
			if(node.id > 0){ // 总分类不能删除，和修改
				treeMenu.add({
						text : '修改组',
						scope : this,
						iconCls : 'btn-edit',
						handler : this.editNode
					}, {
						text : '删除组',
						scope : this,
						iconCls : 'btn-delete',
						handler : this.deleteNode
//					}, {
//						text : '上移',
//						scope : this,
//						iconCls : 'btn-up',
//						handler : this.upMove
//					}, {
//						text : '下移',
//						scope : this,
//						iconCls : 'btn-last',
//						handler : this.downMove
//					}, {
//						text : '置顶',
//						scope : this,
//						iconCls : 'btn-superior',
//						handler : this.topMove
//					}, {
//						text : '置底',
//						scope : this,
//						iconCls : 'btn-subordinate',
//						handler : this.underMove
					});
			}  
		treeMenu.showAt(e.getXY());
	},
	//创建节点
	createNode:function(){
		var treePanel = this.treePanel;
	    new PhoneGroupForm({
	    	isPublic:0,
    		scope :this,
			callback:function(){
				treePanel.root.reload();
			}
	    }).show();
	},
	//编辑节点
	editNode:function(){
		var treePanel = this.treePanel;
	   var groupId = this.selectNode.id;
		new PhoneGroupForm({
			groupId:groupId,
			isPublic:0,
			scope :this,
			callback:function(){
				treePanel.root.reload();
			}
		}).show();
	},
	//删除节点
	deleteNode:function(){
	    var groupId = this.selectNode.id;
	    var treePanel=this.treePanel;
	    var phoneBookView=this.dataView;
		Ext.Ajax.request({
			url : __ctxPath + '/communicate/countPhoneGroup.do',
			params : {
				'Q_phoneGroup.groupId_L_EQ' : groupId
			},
			method : 'post',
			success : function(result, request) {
				var count = Ext.util.JSON.decode(result.responseText).count;
				Ext.Msg.confirm('删除操作', '组里还有' + count + '个联系人，你确定删除目录组吗?',
						function(btn) {
							if (btn == 'yes') {
								Ext.Ajax.request({
									url : __ctxPath + '/communicate/multiDelPhoneGroup.do',
									params : {
										ids : groupId
									},
									method : 'post',
									success : function(result, request) {
										Ext.ux.Toast.msg('操作信息', '成功删除目录！');
										treePanel.root.reload();
										dataView.getStore().reload({params : {start : 0,limit : 15}});
									},
									failure : function(result, request) {
										Ext.MessageBox.show({title : '操作信息',msg : '信息保存出错，请联系管理员！',buttons : Ext.MessageBox.OK,icon : 'ext-mb-error'});
									}
								});

							}
						});
				
			},
			failure : function(result, request) {
			}
		});
	},
//	//上移
//	upMove:function(){
//		var groupId = this.selectNode.id;
//		this.moveTO(this.treePanel,groupId,1);
//
//	},
//	//下移
//	downMove:function(){
//	   var groupId = this.selectNode.id;
//	   this.moveTO(this.treePanel,groupId,2);
//		
//	},
//	//置顶
//	topMove:function(){
//	    var groupId = this.selectNode.id;
//	    this.moveTO(this.treePanel,groupId,3);
//	},
//	//置底
//	underMove:function(){
//	    var groupId = this.selectNode.id;
//	    this.moveTO(this.treePanel,groupId,4);
//	},
//	//移动节点处理函数
//	moveTO:function(treePanel,groupId,optId){
//	   Ext.Ajax.request({
//					url : __ctxPath + '/communicate/movePhoneGroup.do',
//					params : {
//						groupId : groupId,
//						isPublic : 0,
//						optId : optId
//					},
//					method : 'post',
//					success : function(result, request) {
//						Ext.ux.Toast.msg('操作信息', '成功移动目录！');
//						treePanel.root.reload();
//					},
//					failure : function(result, request) {
//					}
//				});
//	},
	// 重置查询表单
	reset : function() {
		this.searchPanel.getForm().reset();
	},
	// 查询
	search : function() {
		var searchPanel = this.searchPanel;
		var gridPanel = this.gridPanel;
		var dataView = this.dataView;
		if (searchPanel.getForm().isValid()) {
			var store = dataView.getStore();
			var baseParam = Ext.Ajax.serializeForm(searchPanel.getForm().getEl());
			var deParams = Ext.urlDecode(baseParam);
			deParams.start = 0;
			deParams.limit = 15;
			store.baseParams = deParams;
			gridPanel.getBottomToolbar().moveFirst();
		}
	},
	// 联系人记录右键显示菜单
	showMenu : function(view, index, node, e) {
		var nodes = view.getSelectedNodes();
		if(nodes.length<2){
			view.all.each(function(el) {view.deselect(el);});
			view.select(index, true);
		}
		if (nodes != '' && nodes != null && nodes != 'undefined') {
			var isShared = nodes[0].getAttribute("isShared");
			var menuItems=new Array();
			if(nodes.length==1){
				menuItems.push({
					text : '修改',
					scope : this,
					iconCls : 'btn-edit',
					handler : this.editRecord
				});
			}
			if(isShared==0){
				menuItems.push({
					text : '共享',
					scope : this,
					iconCls : 'btn-shared',
					handler : this.Shared
				});	
			}
			if(isShared==1){
				menuItems.push({
					text : '取消共享',
					scope : this,
					iconCls : 'btn-shared',
					handler : this.Shared
				});	
			}
			menuItems.push({
				text : '删除',
				scope : this,
				iconCls : 'btn-delete',
				handler : this.deleteRecord
			});
			menuItems.push({
				text : '查看',
				scope : this,
				iconCls : 'btn-detail',
				handler : this.detail
			});
			var menus = new Ext.menu.Menu({
					items : menuItems
				});
			menus.showAt(e.getXY());
		}

	},
	//新建联系人
	createRecord : function() {
		var parentId = 0;
		var node = this.selectNode;
		if (node) {
			parentId = this.selectNode.id;
		}
		new PhoneBookForm({
			parentId : parentId,
			scope : this,
			callback : function(){
				this.dataView.getStore().reload();
			}
		}).show();
	},
	// 编辑记录
	editRecord : function() {	
		var nodes = this.dataView.getSelectedNodes();
		if (nodes != '' && nodes != null && nodes != 'undefined') {
			var phoneId = nodes[0].id;
			new PhoneBookForm({
				phoneId:phoneId,
				scope : this,
				callback : function(){
					this.dataView.getStore().reload();
				}
			}).show();
		}
	},
	//设置或取消共享
	Shared : function(){
		var dataView = this.dataView
		var nodes = this.dataView.getSelectedNodes()
		var isShared = nodes[0].getAttribute("isShared");
		if(isShared == 1){
			isShared = 0;
		}else{ isShared = 1; }
		if (nodes != '' && nodes != null && nodes != 'undefined') {
			var phoneId = nodes[0].id;
			Ext.Ajax.request({
				url : __ctxPath+ '/communicate/isSharedPhoneBook.do',
				params : {
							phoneId : phoneId,
							isShared : isShared
						},
				success : function(result, request) {
					Ext.ux.Toast.msg('操作信息', '成功设置共享状态！');
					dataView.getStore().reload();
				},
				failure : function(result, request) {
					Ext.MessageBox.show({title : '操作信息',msg : '设置共享状态出错，请联系管理员！',buttons : Ext.MessageBox.OK,icon : 'ext-mb-error'});
				}	
			});
		}
	},
	
	//删除记录
	deleteRecord : function() {
		var nodes = this.dataView.getSelectedNodes();
		var store = this.dataView.getStore();
		if (nodes != '' && nodes != null && nodes != 'undefined') {
			var ids=new Array();
			for(var i=0;i<nodes.length;i++){
			   ids.push(nodes[i].id);
			}
			$postDel({
					url : __ctxPath + '/communicate/multiDelPhoneBook.do',
					ids : ids,
					grid : this.dataView
				});
		}else{
		    Ext.ux.Toast.msg("信息提示","请选择删除记录！");
		}
	},
	//查看明细
	detail : function(){
		var nodes = this.dataView.getSelectedNodes();
		if (nodes != '' && nodes != null && nodes != 'undefined') {
			var phoneId = nodes[0].id;
			new SharedPhoneBookWin({phoneId:phoneId}).show();
		}
	},
	DragSelector : function(cfg){
	    cfg = cfg || {};
	    var view, proxy, tracker;
	    var rs, bodyRegion, dragRegion = new Ext.lib.Region(0,0,0,0);
	    var dragSafe = cfg.dragSafe === true;
	
	    this.init = function(dataView){
	        view = dataView;
	        view.on('render', onRender);
	    };
	
	    function fillRegions(){
	        rs = [];
	        view.all.each(function(el){
	            rs[rs.length] = el.getRegion();
	        });
	        bodyRegion = view.el.getRegion();
	    }
	
	    function cancelClick(){
	        return false;
	    }
	
	    function onBeforeStart(e){
	        return !dragSafe || e.target == view.el.dom;
	    }
	
	    function onStart(e){
	        view.on('containerclick', cancelClick, view, {single:true});
	        if(!proxy){
	            proxy = view.el.createChild({cls:'x-view-selector'});
	        }else{
	            if(proxy.dom.parentNode !== view.el.dom){
	                view.el.dom.appendChild(proxy.dom);
	            }
	            proxy.setDisplayed('block');
	        }
	        fillRegions();
	        view.clearSelections();
	    }
	
	    function onDrag(e){
	        var startXY = tracker.startXY;
	        var xy = tracker.getXY();
	
	        var x = Math.min(startXY[0], xy[0]);
	        var y = Math.min(startXY[1], xy[1]);
	        var w = Math.abs(startXY[0] - xy[0]);
	        var h = Math.abs(startXY[1] - xy[1]);
	        dragRegion.left = x;
	        dragRegion.top = y;
	        dragRegion.right = x+w;
	        dragRegion.bottom = y+h;
	        dragRegion.constrainTo(bodyRegion);
	        proxy.setRegion(dragRegion);
	        
	        for(var i = 0, len = rs.length; i < len; i++){
	            var r = rs[i];
	            var sel = dragRegion.intersect(r);
	            if(sel && !r.selected){  
	                r.selected = true;
	                view.select(i, true);
	            }else if(!sel && r.selected){
	                r.selected = false;
	                view.deselect(i);
	            }
	        }
	    }
	
	    function onEnd(e){
	        if (!Ext.isIE) {
	            view.un('containerclick', cancelClick, view);    
	        }        
	        if(proxy){
	            proxy.setDisplayed(false);
	        }
	    }
	
	    function onRender(view){
	        tracker = new Ext.dd.DragTracker({
	            onBeforeStart: onBeforeStart,
	            onStart: onStart,
	            onDrag: onDrag,
	            onEnd: onEnd
	        });
	        tracker.initEl(view.el);
	    }
	}
});



