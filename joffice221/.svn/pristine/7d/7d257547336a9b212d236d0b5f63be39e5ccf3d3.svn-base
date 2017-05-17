Ext.ns("OutMailFolderForm");
/**
 * 邮件文件夹详细信息
 * 
 * @author zqg
 * @class OutMailFolderForm
 * @extends Ext.Window
 */
OutMailFolderForm=Ext.extend(Ext.Window,{
		//构造方法
       constructor : function(_cfg){
           Ext.applyIf(this,_cfg);
           //必须先初始化组件
           this.initUI();
           //调用父类构造方法
           OutMailFolderForm.superclass.constructor.call(this,{
                id : 'OutMailFolderForm',
                layout : 'fit',
                iconCls : 'menu-mail_folder',
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
							name : 'outMailFolder.folderId',
							xtype : 'hidden',
							value : this.folderId == null ? '' : this.folderId
						},{
							name : 'outMailFolder.outMailUserSeting.setId',
							xtype : 'hidden',
							value : this.setId == null ? '' : this.setId
						},{
							fieldLabel : '父目录',
							name : 'outMailFolder.parentId',
							xtype:'hidden',
							value:this.parentId == null ? '' : this.parentId
						},{
							fieldLabel : '文件夹名称',
							name : 'outMailFolder.folderName',
							id : 'folderName'
						}]
			});
			//加载表单数据
			if (this.folderId != null && this.folderId != 'undefined') {
				this.formPanel.loadData({
					deferredRender : false,
					url : __ctxPath + '/communicate/getOutMailFolder_.do?folderId='
					+ this.folderId,
					method : 'post',
					preName : 'outMailFolder',
					root : 'data',
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
					url : __ctxPath + '/communicate/saveOutMailFolder_.do',
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
