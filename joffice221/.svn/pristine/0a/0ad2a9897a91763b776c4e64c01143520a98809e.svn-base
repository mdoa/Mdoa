Ext.ns("MailFolderForm");
/**
 * 邮件文件夹详细信息
 * 
 * @author zqg
 * @class MailFolderForm
 * @extends Ext.Window
 */
MailFolderForm=Ext.extend(Ext.Window,{
		//构造方法
       constructor : function(_cfg){
           Ext.applyIf(this,_cfg);
           //必须先初始化组件
           this.initUI();
           //调用父类构造方法
           MailFolderForm.superclass.constructor.call(this,{
                id : 'MailFolderForm',
                layout : 'fit',
                iconCls : 'menu-myplan',
                title : '邮件文件夹详细信息',
				items : this.formPanel,
				modal : true,
				width : 300,
				height : 160,
				minWidth : 200,
				minHeight : 100,
				buttonAlign : 'center',
				bodyStyle : 'padding:5px;',
				buttons : this.buttons,
				keys : {
        	   		key : Ext.EventObject.ENTER,
        	   		scope : this,
        	   		fn : this.saveRecord
           		}
           });
       },//end of constructor
       //初始化组件
       initUI:function(){
			//计划信息面板
			this.formPanel = new Ext.FormPanel({
				layout : 'form',
				frame : true,
				defaults : {
					widht : 300,
					anchor : '96%,96%'
				},
				defaultType : 'textfield',
				items : [{
							name : 'mailFolder.folderId',
							xtype : 'hidden',
							value : this.folderId == null ? '' : this.folderId
						},
							{
							fieldLabel : '父目录',
							name : 'mailFolder.parentId',
							xtype:'hidden',
							value:this.parentId == null ? '' : this.parentId
						},{
							fieldLabel : '文件夹名称',
							name : 'mailFolder.folderName',
							id : 'folderName'
						},{
							fieldLabel :'是否共享', //'1=表示共享，则所有的员工均可以使用该文件夹 0=私人文件夹',
							name : 'mailFolder.isPublic',
							xtype:'hidden',
							//mode : 'local',
							//editable : false,
							//triggerAction : 'all',
							//store:[['1','是'], ['0','否']],
							value:0
						}]
			});
			//加载表单数据
			if (this.folderId != null && this.folderId != 'undefined') {
				this.formPanel.load({
					deferredRender : false,
					url : __ctxPath + '/communicate/getMailFolder.do?folderId='
							+ this.folderId,
					waitMsg : '正在载入数据...',
					success : function(form, action) {
					},
					failure : function(form, action) {
						Ext.ux.Toast.msg('编辑', '载入失败');
					}
				});
			}
			//底部菜单面板
			this.buttons = [{
	            text : '保存',
	            iconCls : 'btn-save',
	            scope : this,
	            handler : this.saveRecord
	        },{
	            text : '关闭',
	            iconCls : 'btn-cancel',
	            scope : this,
	            handler : this.closeWin
	        }];		
       },//end of initUI
      //保存
      saveRecord : function() {
      	var formPanel = this.formPanel
		$postForm({
					formPanel : formPanel,
					scope : this,
					url : __ctxPath + '/communicate/saveMailFolder.do',
					callback : function(fp, action) {
						if (this.callback) {
							this.callback.call(this.scope);
						}
						this.close();
					}
				});
	 },
	 //关闭窗口
	 closeWin : function() {
		this.close();
	 }
});
