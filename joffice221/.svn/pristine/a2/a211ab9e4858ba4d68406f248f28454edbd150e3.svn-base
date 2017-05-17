/**
 *  供应商选择器
 * @class SalaryItemDialog
 * @extends Ext.Window
 */
ProviderDialog=Ext.extend(Ext.Window,{
	constructor:function(conf){
		Ext.applyIf(this,conf);
		this.scope=this.scope?this.scope:this;		
		this.initUI();
		ProviderDialog.superclass.constructor.call(this,{
			title:this.title?this.title:'供应商选择器',
			iconCls:'menu-provider',
			width : 580,
			height : 380,
			maximizable : true,
			modal:true,
			layout:'border',			
			buttonAlign:'center',
			buttons:[
			{
				iconCls : 'btn-ok',
				text : '确定',
				scope : this,
				handler:this.confirm
			},{
				text:'重置',
				iconCls:'btn-reseted',
				scope : this,
				handler:this.reset
			},{
				text:'取消',
				iconCls:'btn-cancel',
				scope:this,
				handler:function(){
					this.close();
				}
			}],
			items:[this.searchPanel,this.gridPanel]
		});		
	},
	
	initUI:function(){
		this.searchPanel=new Ext.FormPanel({
			width : 550,
			region : 'north',
			id : 'ProviderForm',
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
				text : '查询条件:'
			},{
				text : '名称'
			}, {
				xtype : 'textfield',
				name : 'Q_providerName_S_LK',
				width: 80
			}, {
				text : '联系人'
			}, {
				xtype : 'textfield',
				name : 'Q_contactor_S_LK',
				width: 80
			}, {
				text : '等级'
			},{
				hiddenName : 'Q_rank_N_EQ',
				width: 100,
				xtype : 'combo',
				mode : 'local',
				editable : false,
				triggerAction : 'all',
				store : [['null','　'],['1', '一级供应商'], ['2', '二级供应商'],['3', '三级供应商'],['4', '四级供应商']]
			},{
				xtype : 'button',
				text : '查询',
				iconCls : 'search',
				scope:this,
				handler:this.search
			}]
		});
		
		this.gridPanel = new HT.GridPanel({
			id : 'ProviderSelectorGrid',
			singleSelect:this.isSingle!=null?this.isSingle:true,
			rowActions : true,
			width : 550,
			height : 300,
			region : 'center',
			title : '供应商列表',
			shim : true,
			url : __ctxPath + '/customer/listProvider.do',
			trackMouseOver : true,
			disableSelection : false,
			loadMask : true,
			viewConfig : {
				forceFit : true,
				enableRowBody : false,
				showPreview : false
			},
			fields : [{
				name : 'providerId',
				type : 'int'
			},'rank', 'providerName', 'contactor', 'phone','address'
			/*'fax', 'site', 'email',  'zip',
			'openBank', 'account', 'notes',*/],
			columns : [{
				header : 'providerId',
				dataIndex : 'providerId',
				hidden : true
			}, {
				header : '等级',
				dataIndex : 'rank',
				width:40,
				renderer:this.rank
			}, {
				header : '供应商名字',
				dataIndex : 'providerName',
				width:120
			},{
				header : '联系人',
				dataIndex : 'contactor',
				width:80
			},{
				header : '电话',
				dataIndex : 'phone',
				width:80
			},{
				header : '地址',
				dataIndex : 'address',
				width:150
			},new Ext.ux.grid.RowActions({
				header : '管理',
				width : 100,
				actions:[{
					 iconCls:'btn-del',qtip:'删除',style:'margin:0 3px 0 3px'
				   },{
					 iconCls:'btn-edit',qtip:'编辑',style:'margin:0 3px 0 3px'
				   }],
				listeners : {
					scope : this,
					'action' : this.onRowAction
				}
			})]
		});
		this.gridPanel.addListener('rowdblclick',this.rowClick);
	},
	
	search :function() {
		$search({
			searchPanel : this.searchPanel,
			gridPanel : this.gridPanel
		});
	},
	
	confirm : function() {
		var grid = Ext.getCmp('ProviderSelectorGrid');
		var rows = grid.getSelectionModel().getSelections();
		var providerId = '';
		var providerName = '';
		for (var i = 0; i < rows.length; i++) {

			if (i > 0) {
				providerId += ',';
				providerName += ',';
			}

			providerId += rows[i].data.providerId;
			providerName += rows[i].data.providerName;
		}

		if (this.callback != null) {
			this.callback.call(this, providerId, providerName);
		}
		this.close();
	},
	
	
    reset : function() {
		this.searchPanel.getForm().reset();
	},
	
	rank : function(value){
		if (value == '1') {
			return '<img title="一级供应商" src="' + __ctxPath
					+ '/images/flag/customer/grade_one.png"/>';
		} else if(value == '2') {
			return '<img title="二级供应商" src="' + __ctxPath
					+ '/images/flag/customer/grade_two.png"/>';
		} else if(value == '3') {
			return '<img title="三级供应商" src="' + __ctxPath
					+ '/images/flag/customer/grade_three.png"/>';
		} else {
			return '<img title="四级供应商" src="' + __ctxPath
					+ '/images/flag/customer/grade_four.png"/>';
		}
	},
	
	removeRs : function(id) {
		$postDel({
			url : __ctxPath+ '/customer/multiDelProvider.do',
			ids:id,
			grid:this.gridPanel
		});
	},
	// 编辑Rs
	editRs : function(record) {
		
		new ProviderForm({
			providerId : record.data.providerId
		}).show();
	},	
	
	
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.removeRs.call(this,record.data.providerId);
				break;
			case 'btn-edit' :
				this.editRs.call(this,record);
				break;
			default :
				break;
		}
	}
	
});