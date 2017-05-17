/**
 * 客户选择器
 */
/**
	 * @param callback
	 *            回调函数
	 * @param single
	 *            是否单选
	 */
CustomerDialog=Ext.extend(Ext.Window,{
	constructor:function(conf){
		Ext.applyIf(this,conf);
		
		this.scope=this.scope?this.scope:this;
		//默认为多选择
		this.single=this.single!=null?this.single:false;
		
		this.initUI();
		CustomerDialog.superclass.constructor.call(this,{
			title:this.title?this.title:'客户选择器',
			iconCls : 'menu-customerView',
			width : 630,
			height : 380,
			layout : 'border',
			border : false,
			items : [this.treeCustomer,this.CustomerForm,this.CustomerSelectorGrid],
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
	
	/**
	 * 初始化UI
	 */
	initUI:function(){
		 this.treeCustomer = new Ext.tree.TreePanel({
			title : '客户地区',
			region : 'west',
			width : 180,
			height : 300,
			split : true,
			collapsible : true,
			autoScroll : true,
			bbar : new Ext.Toolbar({
						items : [{
									xtype : 'button',
									text : '展开',
									scope:this,
									iconCls : 'btn-expand',
									handler : function() {
										treeCustomer.expandAll();
									}
								}, {
									xtype : 'button',
									text : '收起',
									scope:this,
									iconCls : 'btn-collapse',
									handler : function() {
										treeCustomer.collapseAll();
									}
								}]
					}),
			loader : new Ext.tree.TreeLoader({
						url : __ctxPath + '/system/treeRegion.do'
					}),
			root : new Ext.tree.AsyncTreeNode({
						expanded : true
					}),
			rootVisible : false,
			listeners : {
				scope:this,
				'click' : this.treeClick
			}
		});
		// ---------------------------------start grid
		// panel--------------------------------
			var sm = new Ext.grid.CheckboxSelectionModel({
				singleSelect:this.single!=null?this.single:true
			});
		
	this.store = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
					url : __ctxPath + '/customer/listCustomer.do'
				}),
		reader : new Ext.data.JsonReader({
					root : 'result',
					totalProperty : 'totalCounts',
					id : 'customerId',
					fields : [{
								name : 'customerId',
								type : 'int'
							}, 'customerNo', 'customerName']
				})
			// remoteSort : true
		});
	this.CustomerSelectorGrid=new HT.GridPanel({
		width : 400,
		height : 300,
		region : 'center',
		title : '客户列表',
		store :this. store,
		shim : true,
		trackMouseOver : true,
		disableSelection : false,
		loadMask : true,
		sm : sm,
		viewConfig : {
			forceFit : true,
			enableRowBody : false,
			showPreview : false
		},
		fields : [{
			name : 'customerId',
			type : 'int'
		}, 'customerNo', 'customerName'],
		columns : [ {
			header : 'customerId',
			dataIndex : 'customerId',
			hidden : true
		}, {
			header : "客户号",
			dataIndex : 'customerNo',
			width : 60
		}, {
			header : '客户名称',
			dataIndex : 'customerName',
			width : 60
		}]
	});

	this.store.load({
			params : {
				start : 0,
				limit : 25
			}
		});
	// --------------------------------form panel start

	this.CustomerForm = new Ext.FormPanel({
		width : 400,
		region : 'north',
		id : 'CustomerForm',
		height : 40,
		frame : false,
		border : false,
		layout : 'hbox',
		layoutConfig : {
			padding : '5',
			align : 'middle'
		},
		defaults : {
			xtype : 'label',
			margins : {
				top : 0,
				right : 4,
				bottom : 4,
				left : 4
			}
		},
		items : [{
					text : '请输入查询条件:'
				}, {
					text : '客户号'
				}, {
					xtype : 'textfield',
					name : 'Q_customerNo_S_LK'
				}, {
					text : '客户名称'
				}, {
					xtype : 'textfield',
					name : 'Q_customerName_S_LK'
				}, {
					xtype : 'button',
					text : '查询',
					iconCls : 'search',
					scope:this,
					handler :this.search
				}]
		});
	// --------------------------------form panel end
	},//end of initUI function
	
	treeClick:function(node) {
		if (node != null) {
			var Customers =this.CustomerSelectorGrid;
			var store = Customers.getStore();
			store.proxy.conn.url = __ctxPath
					+ '/customer/listCustomer.do';
			if (node.leaf && node.id > 4) {
				store.baseParams = {
					'Q_city_S_EQ' : node.text
				};
			} else {
				if (node.id != 0) {
					store.baseParams = {
						'Q_state_S_EQ' : node.text
					};
				} else {
					store.baseParams = {
						'Q_state_S_EQ' : null,
						'Q_city_S_EQ' : null
					}
				}
			}
			store.load({
				params : {
					start : 0,
					limit : 12
				}
			});
		}
	},
	search:function() {
		var searchPanel = this.CustomerForm;
		var grid = this.CustomerSelectorGrid;
		if (searchPanel.getForm().isValid()) {
			searchPanel.getForm().submit({
				waitMsg : '正在提交查询',
				url : __ctxPath
						+ '/customer/listCustomer.do',
				success : function(formPanel, action) {
					var result = Ext.util.JSON
							.decode(action.response.responseText);
					grid.getStore().loadData(result);
				}
			});
		}
	},
	/**
	 * 选择确认
	 */
	confirm:function(){		 
				var grid = this.CustomerSelectorGrid;
				var rows = grid.getSelectionModel().getSelections();
				var CustomerIds = '';
				var CustomerNames = '';
				for (var i = 0; i < rows.length; i++) {
					if (i > 0) {
						CustomerIds += ',';
						CustomerNames += ',';
					}
					CustomerIds += rows[i].data.customerId;
					CustomerNames += rows[i].data.customerName;
				}
				if (this.callback) {
					this.callback.call(this, CustomerIds, CustomerNames);
				}
				this.close();
	}
});