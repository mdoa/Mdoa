/**
 * 共享联系人列表
 * @author zqg
 * @class SharedPhoneBookView
 * @extends Panel
 */
Ext.ns('SharedPhoneBookView');
SharedPhoneBookView=Ext.extend(Ext.Panel,{
	//构造函数
	constructor:function(_cfg){
	    Ext.applyIf(this,_cfg);
	    //必须先初始化
	    this.initUI();
	    //调用父类构造函数
	    SharedPhoneBookView.superclass.constructor.call(this,{
	    	id : 'SharedPhoneBookView',
			iconCls : "menu-phonebook-shared",
			title : '共享联系人列表',
			layout:'border',
			region:'center',
			autoScroll : true,
			items:[this.searchPanel,this.gridPanel]
	    });
    },
    //初始化面板
    initUI:function(){
    	//查询面板
    	this.searchPanel=new HT.SearchPanel({
			layout : 'form',
			region : 'north',
			colNums : 6,
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
						fieldLabel : '请输入查询条件: 姓名',
						xtype : 'textfield',
						name : 'Q_fullname_S_LK',
						maxLength : 125
					},{
						fieldLabel : '共享人',
						xtype : 'textfield',
						name : 'Q_appUser.fullname_S_LK',
						labelWidth : 50,
						maxLength : 125
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
					}]
		});
    	//共享联系人列表模板
    	this.gridPanel=new HT.GridPanel({
    		region : 'center',
			tbar:new Ext.Toolbar({height:27}),
			// 使用RowActions
			rowActions : true,
			url : __ctxPath + '/communicate/sharePhoneBook.do',
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
							'note', 'appUser.fullname',
							'groupId', 'isShared'],
    		columns : [{
							header : 'phoneId',
							dataIndex : 'phoneId',
							hidden : true
					  },{
							header : '名字',
							dataIndex : 'fullname',
							sortable : true
					  }, {
							header : '职位',
							dataIndex : 'duty',
							sortable : true
					  }, {
							header : '电话',
							dataIndex : 'mobile',
							sortable : true
					  }, {
							header : '共享人',
							dataIndex : 'appUser.fullname',
							sortable : true
					  },new Ext.ux.grid.RowActions({
							header : '管理',
							width : 100,
							actions : [{
								iconCls : 'btn-detail',
								qtip : '查看',
								style : 'margin:0 3px 0 3px'
							}],
							listeners : {
								scope : this,
								'action' : this.onRowAction
							}
					 })]

		});
		//gridPanel添加行双击事件
    	this.gridPanel.addListener({scope : this,'rowdblclick': this.rowClick});
    },
    //行双击
    rowClick : function(grid, rowindex, e) {
    	var record = grid.getStore().getAt(rowindex);
		this.detail.call(this,record)
	},
    //查询
    search:function(){
		$search({
			searchPanel : this.searchPanel,
			gridPanel : this.gridPanel
		});
	},
	// 重置查询表单
	reset : function() {
		this.searchPanel.getForm().reset();
	},
	//查看明细
	detail : function(record){
		new SharedPhoneBookWin({phoneId:record.data.phoneId}).show();
	},
	// 行的Action
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-detail' :
				this.detail.call(this, record);
				break;
			default :
				break;
		}
	}
});
