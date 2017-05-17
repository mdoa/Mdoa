/**
 * 薪酬项目选择器
 * 
 * @class SalaryItemDialog
 * @extends Ext.Window
 *          @example
 * 
 * <pre>
 * new SalaryItemDialog({
 * 		_exclude : _exclude, //
 *  	title :'薪酬项目选择' //标题  默认是'薪酬项目选择'，也可以自定义标题
 * 		single: true,   //是否单选 默认是单选薪酬项目
 * 		scope:this,   //作用域
 * 		callback :function(salaryItemId,itemName，defaultVal){//回调函数,返回所属工资组成ID、薪酬项目名称和薪酬项目金额
 * 
 * 		}	
 * 	}
 * </pre>
 */
SalaryItemDialog = Ext.extend(Ext.Window, {
	//构造函数
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		//作用域
		this.scope = this.scope ? this.scope : this;
		// 默认为单选薪酬项目
		this.isSingle = this.isSingle != null ? this.isSingle : true;
		//初始化
		this.initUI();
		SalaryItemDialog.superclass.constructor.call(this, {
					title : this.title ? this.title : '薪酬项目选择',
					width : 460,
					height : 380,
					maximizable : true,
					modal : true,
					layout : 'border',
					buttonAlign : 'center',
					buttons : [{
								text : '确定',
								iconCls : 'btn-ok',
								scope : this,
								handler : this.confirm
							}, {
								text : '取消',
								iconCls : 'btn-cancel',
								scope : this,
								handler : function() {
									this.close();
								}
							}],
					items : [this.searchPanel, this.gridPanel]
				});
	},
	//初始化组件
	initUI : function() {
		//查询面板
		this.searchPanel = new Ext.FormPanel({
					width : 400,
					region : 'north',
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
							}, {
								text : '工资项名称'
							}, {
								xtype : 'textfield',
								width : 80,
								name : 'Q_itemName_S_LK'
							}, {
								text : '缺省值'
							}, {
								xtype : 'textfield',
								width : 80,
								name : 'Q_defaultVal_S_LK'
							}, {
								xtype : 'button',
								text : '查询',
								iconCls : 'search',
								handler : this.search
							}, {
								xtype : 'button',
								scope : this,
								text : '重置',
								iconCls : 'btn-reseted',
								handler : this.reset
							}]
				});
		//薪酬项目列表面板
		this.gridPanel = new HT.GridPanel({
			width : 400,
			height : 300,
			region : 'center',
			title : '薪酬项目列表',
			shim : true,
			singleSelect : this.isSingle,
			url : __ctxPath + '/hrm/listSalaryItem.do?exclude=' + this._exclude,
			trackMouseOver : true,
			disableSelection : false,
			loadMask : true,
			fields : [{
						name : 'salaryItemId',
						type : 'int'
					}, 'itemName', 'defaultVal'],
			columns : [{
						header : 'salaryItemId',
						dataIndex : 'salaryItemId',
						hidden : true
					}, {
						header : '薪资项名称',
						dataIndex : 'itemName'
					}, {
						header : '缺省值',
						dataIndex : 'defaultVal',
						renderer : this.defaultValue
					}]
		});
	},
	// 查询
	search : function() {
		$search({
					searchPanel : this.searchPanel,
					gridPanel : this.gridPanel
				});
	},
	//重置
	reset : function() {
		this.searchPanel.getForm().reset();
	},
	//确认选择
	confirm : function() {
		var rows = this.gridPanel.getSelectionModel().getSelections();
		var salaryItemId = '';
		var itemName = '';
		var defaultVal = '';
		for (var i = 0; i < rows.length; i++) {
			if (i > 0) {
				salaryItemId += ',';
				itemName += ',';
				defaultVal += ',';
			}
			salaryItemId += rows[i].data.salaryItemId;
			itemName += rows[i].data.itemName;
			defaultVal += rows[i].data.defaultVal;
		}
		if (salaryItemId == "") {
			Ext.ux.Toast.msg('提示信息', '请选择薪酬项目');
			return;
		}
		if (this.callback != null) {
			this.callback.call(this.scope, salaryItemId, itemName, defaultVal);
		}
		this.close();
	},

	defaultValue : function(value) {
		return '<img src="' + __ctxPath + '/images/flag/customer/rmb.png"/>'
				+ value;
	}
});