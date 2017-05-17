
/**
 *  通讯薄目录视图
 * @author YungLocke
 * @class PublicPhoneBookView
 * @extends Ext.Panel
 */
Ext.ns("PublicPhoneBookView");
PublicPhoneBookView=Ext.extend(Ext.Panel,{
	//构造方法
	constructor:function(_cfg){
	   Ext.applyIf(this,_cfg);
	   //必须先初始化
	   this.initUI();
	   //调用父类构造方法
	   PublicPhoneBookView.superclass.constructor.call(this,{
	        title : '公共通讯薄',
			iconCls : "menu-sharebook",
			layout : 'border',
			id : 'PublicPhoneBookView',
			height : 800,
			items : [this.leftPanel, this.phoneBookView]
	   });
	},
	//初始化面板
	initUI:function(){
		//通讯簿分类树
	    this.treePanel= new htsoft.ux.TreePanelEditor({
	    	url : __ctxPath + '/communicate/listPhoneGroup.do?isPublic=true',
			scope : this,
			autoScroll:true,
			split : true,
			enableDD : true, 
			onclick:this.clickNode
		});
		//给树添加右击事件
		this.treePanel.on('contextmenu', this.contextmenu, this);	
		//左部面板
		this.leftPanel = new Ext.Panel({
		 	region:'west',
		 	title:'通讯簿分类',
		 	layout:'fit',
		 	collapsible : true,
			split : true,
			border:false,
			width : 200,
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
			labelWidth : 125,
			items : [{
						xtype : 'textfield',
						fieldLabel : '请输入查询条件: 姓名',
						name : 'Q_fullname_S_LK',
						maxLength : 125
					}, {
						xtype : 'combo',
						fieldLabel : '称谓',
						name : 'Q_title_S_LK',
						labelWidth : 40,
						anchor : '95%',
						mode : 'local',
						width : 125,
						maxLength : 125,
						editable : false,
						triggerAction : 'all',
						store : ['先生', '女士']
					}, {
						xtype : 'button',
						text : '查询',
						iconCls : 'search',
						scope : this,
						handler : this.search
					},{
						xtype : 'button',
						text : '重置',
						style : 'padding-left:5px;',
						iconCls : 'btn-reset',
						handler : this.reset,
						scope : this
					},{
					    xtype:'hidden',
					    name:'Q_phoneGroup.isPublic_SN_EQ',
					    value:1
					}]
		});
		this.tpl = new Ext.XTemplate(
					'<tpl for=".">',
					'<div class="thumb-wrap" id="{phoneId}">',
					'<span><font>姓名&nbsp;：</font>{fullname}</span>',
					'<span><font>手机&nbsp;：</font>{mobile}</span>',
					'<span><font>电话&nbsp;：</font>{phone}</span>',
					'<span><font>Email：</font>{email}</span>',
					'<span><font>QQ&nbsp;&nbsp;&nbsp;：</font>{qqNumber}</span>',
					'<span><font>MSN&nbsp;&nbsp;：</font>{msn}</span>',
					'</div>', '</tpl>');
		//联系人明细
		this.dataView = new HT.DataView({
			id : 'publicPhones',
			region : 'center',
			border : false,
			url : __ctxPath+ '/communicate/listPhoneBook.do',
			baseParams:{
			        'Q_phoneGroup.isPublic_SN_EQ':1
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
		//顶部菜单面板
		this.toolbar = new Ext.Toolbar({
			height : 28,
			bodyStyle : 'text-align:left',
			items : [{
						iconCls : 'btn-add',
						xtype : 'button',
						text : '添加公共联系人',
						scope : this,
						handler : this.createRecord
					},{
					     iconCls:'btn-del',
					     xtype:'button',
					     text:'删除公共联系人',
					     scope : this,
					     handler:this.deleteRecord
					}]
		});
		//联系人列表显示面板
		this.phoneBookPanel = new Ext.Panel({
					region : 'center',
					tbar : this.toolbar,
					layout : 'fit',
					items : this.dataView,
					bbar : new Ext.PagingToolbar({
							pageSize : 15,
							store : this.dataView.store,
							displayInfo : true,
							displayMsg : '当前显示从{0}至{1}， 共{2}条记录',
							emptyMsg : "当前没有记录"
						})
				});
		//公共通讯簿详细信息列表
		this.phoneBookView=new Ext.Panel({
					title : '联系人列表',
					region : 'center',
					layout : 'border',
					items : [this.searchPanel, this.phoneBookPanel]
		});
	},
	//右键菜单
	bodyRender : function(p) {
		p.getEl().on('contextmenu', this.bodyContextClick, this);
	},
	bodyContextClick : function(e, t, o) {
		var target = t;
		if (target.id != 'publicPhones') {
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
	//节点单击
	clickNode:function(node) {
		this.selectNode = node;
		if (node != null) {
			var bookView = this.phoneBookView;
			if (node.id == 0) {
				bookView.setTitle('所有联系人');
			} else {
				bookView.setTitle(node.text + '组列表');
			}
			var store = this.dataView.getStore();
			store.url = __ctxPath + '/communicate/listPhoneBook.do';
			store.baseParams = {
				'Q_phoneGroup.groupId_L_EQ' : node.id>0?node.id:'',
				'Q_phoneGroup.isPublic_SN_EQ':1
			};
			store.reload({params : {start : 0,limit : 15}});
		}
	},
	//节点右击
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
	//添加节点
	createNode:function(){
		var treePanel = this.treePanel;
	    new PublicPhoneGroupForm({
	    	scope :this,
			callback:function(){
				treePanel.root.reload();
			}
	    }).show();
	},
	//编辑节点
	editNode:function(){
		var treePanel = this.treePanel;
	    var groupId =this.selectNode.id;
		new PublicPhoneGroupForm({
			groupId:groupId,
			scope :this,
			callback:function(){
				treePanel.root.reload();
			}
		}).show();
	},
	//删除节点
	deleteNode:function(){
	    var groupId = this.selectNode.id;
	    var store = this.dataView.getStore();
	    var treePanel=this.treePanel;
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
										store.reload({params : {start : 0,limit : 15}});
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
//						isPublic : 1,
//						optId : optId
//					},
//					method : 'post',
//					success : function(result, request) {
//						treePanel.root.reload();
//					},
//					failure : function(result, request) {
//					}
//				});
//	},
	// 查询
	search : function() {
		var searchPanel = this.searchPanel;
		var dataView = this.dataView
		var phoneBookPanel = this.phoneBookPanel
		if (searchPanel.getForm().isValid()) {
			var store = dataView.getStore();
			var baseParam = Ext.Ajax.serializeForm(searchPanel.getForm().getEl());
			var deParams = Ext.urlDecode(baseParam);
			deParams.start = 0;
			deParams.limit = 15;
			store.baseParams = deParams;
			phoneBookPanel.getBottomToolbar().moveFirst();
		}
	},
	// 重置查询表单
	reset : function() {
		this.searchPanel.getForm().reset();
	},
	// 显示菜单
	showMenu : function(view, index, node, e) {
		var nodes = view.getSelectedNodes();
		if(nodes.length<2){
			view.all.each(function(el) {
						view.deselect(el);
					});
			view.select(index, true);
		}
		nodes = view.getSelectedNodes();
		if (nodes != '' && nodes != null && nodes != 'undefined') {
			var menuItems=new Array();
			if(nodes.length==1){
			menuItems.push({
							text : '修改',
							scope : this,
							iconCls : 'btn-edit',
							handler : this.editRecord
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
	// 创建新记录
	createRecord : function() {
		new PhoneBookForm({
			isPublic : true,
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
			new PhoneBookForm({phoneId:phoneId,isPublic:true}).show();
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

