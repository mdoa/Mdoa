/**
 * 分类选择器
 * 
 * @class GlobalTypeDialog
 * @extends Ext.Window
 * @example
 * 
 * <pre>
 * new GlobalTypeDialog({
 *  	title :'分类选择' //标题  默认是'分类选择'，也可以自定义标题
 * 		single: true,   //是否单选 默认是多选分类
 * 		scope:this,   //作用域
 * 		callback :function(ids,names){//回调函数,返回分类ids和分类名称
 * 
 * 		}	
 * 	}
 * </pre>
 */
GlobalTypeDialog = Ext.extend(Ext.Window, {
	constructor : function(config) {
		Ext.applyIf(this, config);
		// 作用域
		this.scope = this.scope ? this.scope : this;
		// 默认为多选择类型
		this.isSingle = this.isSingle == null ? false : this.isSingle;
		this.initUIComponents();
		GlobalTypeDialog.superclass.constructor.call(this, {
					title : this.title ? this.title : '分类选择',
					layout : 'border',
					height : 480,
					width : 520,
					buttonAlign : 'center',
					modal : true,
					items : [this.leftPanel, this.rightPanel],
					buttons : [{
								text : '选择',
								iconCls : 'btn-save',
								scope : this,
								handler : this.selectButton
							}, {
								text : '取消',
								iconCls : 'btn-cancel',
								scope : this,
								handler : function() {
									this.close();
								}
							}]
				});
	},
	// 初始化组件
	initUIComponents : function() {
		// 左边的树
		this.leftPanel = new htsoft.ux.TreePanelEditor({
			layout : 'fit',
			region : 'west',
			collapsible : true,
			split : true,
			width : 200,
			title : '分类树',
			url : __ctxPath + '/system/treeGlobalType.do?catKey=' + this.catKey,
			scope : this,
			autoScroll : true,
			// 点击分类树节点
			onclick : this.onNodeClick
		});
		// 右边的分类列表
		this.rightPanel = new HT.GridPanel({
					region : 'center',
					title : '分类列表',
					rowActions : false,
					showPaging : false,
					singleSelect : this.isSingle,
					url : __ctxPath + '/system/subGlobalType.do?catKey='
							+ this.catKey,
					baseParams : {
						parentId : 0
					},
					fields : [{
								name : 'proTypeId',
								type : 'int'
							}, 'typeName', 'nodeKey', 'sn'],
					columns : [{
								header : 'proTypeId',
								dataIndex : 'proTypeId',
								hidden : true
							}, {
								header : '名称',
								dataIndex : 'typeName',
								editor : new Ext.form.TextField({
											allowBlank : false
										})
							}]
				});
	},
	onNodeClick : function(node) {
		var store = this.rightPanel.getStore();
		store.baseParams = {
			parentId : node.id
		};
		this.rightPanel.getBottomToolbar().moveFirst();
	},
	/**
	 * 选择
	 */
	selectButton : function() {
		var rows = this.rightPanel.getSelectionModel().getSelections();
		var typeIds = '';
		var typeNames = '';
		for (var i = 0; i < rows.length; i++) {
			if (i > 0) {
				typeIds += ',';
				typeNames += ',';
			}
			typeIds += rows[i].data.proTypeId;
			typeNames += rows[i].data.typeName;
		}
		if (typeIds == "") {
			Ext.ux.Toast.msg('提示信息', '请选择分类');
			return;
		}
		if (this.callback) {
			this.callback.call(this.scope, typeIds, typeNames);
		}
		this.close();
	}
});