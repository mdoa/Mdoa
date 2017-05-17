/**
 * 移动外部邮件
 * @author zqg
 * @class OutMailMove
 * @extends Ext.Window
 */
Ext.ns('OutMailMove');
OutMailMove = Ext.extend(Ext.Window, {
	//构造方法
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		//必须先初始化组件
		this.initUIComponents();
		//调用父类的构造方法
		OutMailMove.superclass.constructor.call(this, {
			x : 350,
			y : 100,
			width : 340,
			height : 300,
			title : '移动外部邮件',
			iconCls : 'btn-mail_move',
			modal : true,
			buttonAlign : 'center',
			plain : true,
			layout : 'fit',
			border : false,
			bodyStyle : 'padding:5px;',
			items : [this.formPanel],
			buttons : [{
						text : '确定移动',
						iconCls : 'btn-save',
						scope : this,
						handler : this.move
					}, {
						text : '取消',
						iconCls : 'btn-cancel',
						scope : this,
						handler : this.close
					}]
			});
	},//end of the constructor
	initUIComponents : function(){
		//树
		this.treePanel=new htsoft.ux.TreePanelEditor({
					// id:'',
			autoScroll : true,
			title : '请选择文件夹',
			border:false,
			id : 'leftMailBoxTree',
			collapsible : false,
			scope:this,
			url: __ctxPath + '/communicate/moveTreeOutMailFolder_.do?setId='+this.setId,
			onclick:this.nodeClick		
		});
		//移动邮件面板
		
		this.formPanel = new Ext.FormPanel({
			url : __ctxPath + '/communicate/moveOutMail_.do',
			layout : 'table',
			id : 'moveFolderForm',
			frame : true,
			defaultType : 'textfield',
			layoutConfig : {
				columns : 1
			},
			defaults : {
				width : 296
			},
			items : [{
						xtype : 'label',
						text : '移至:'
					}, {
						id : 'dispalyFolderName',
						name : 'dispalyFolderName',
						readOnly : true
					}, {
						xtype : 'hidden',
						name : 'folderId'
					}, {
						id : 'outMailIds',
						name : 'outMailIds',
						xtype : 'hidden',
						value : this.ids
					}, {
						xtype : 'panel',
						items : [this.treePanel]
					}]
		});
	},
	//移动邮件
	move  : function() {
		var formPanel = this.formPanel;
		var folderId = formPanel.getCmpByName('folderId').value;
		
		var win = this;
		if (folderId == '' && folderId == null
				&& folderId == 'undefined') {
					
			Ext.ux.Toast.msg('操作信息', '请先选择文件夹');
		} else {
			
			formPanel.getForm().submit({
				waitMsg : '正在提交用户信息',
				success : function(formPanel, o) {
					// 成功之后关闭窗口,显示邮件列表Panel,reload()
					Ext.ux.Toast.msg('操作信息', '移动成功！');
					if(win.callback){
						win.callback.call(this.scope);
					}
					win.close();
				},
				failure : function(moveFolderForm, o) {
					// 移动失败后提示失败原因
					Ext.ux.Toast.msg('提示信息', o.result.msg);
				}
			});
		}
	},
	//树单击
	nodeClick : function(){
		var node = this.treePanel.selectedNode
		this.formPanel.getCmpByName('dispalyFolderName').setValue(node.text);
		this.formPanel.getCmpByName('folderId').setValue(node.id);
	}
	
});
		