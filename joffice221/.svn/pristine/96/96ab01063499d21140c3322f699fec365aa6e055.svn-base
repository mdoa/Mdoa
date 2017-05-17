OnlineDocumentDetail=Ext.extend(Ext.Window,{

	constructor:function(_cfg){
	
		Ext.applyIf(this,_cfg);
    	this.docPanel=new NtkOfficePanel({showToolbar:false,height:500,doctype:this.docType,unshowMenuBar:true});
		this.initUI(this.docPanel);
		OnlineDocumentDetail.superclass.constructor.call(this,{
			   id:'olDocView',
		       width:700,
               title : '在线文档',
               width : 680,
			   height : 640,
               shim:false,
               modal : true,
               iconCls:'menu-onlinedoc',
               layout:'fit',
               maximizable:true,
               buttonAlign : 'center',
			   buttons:this.buttons,
               items:[
                  this.formPanel
               ]
		});
		
		myNewOffice = this.docPanel;
		
        if(!this.docPanel.flag){
        	var msg = this.docPanel.msg;
	    	setTimeout( 
	   	    	function(){
	   	    		Ext.MessageBox.show({
	   					title : '操作信息',
	   					msg : msg,
	   					buttons : Ext.MessageBox.OK,
	   					icon : 'ext-mb-warning',
	   					fn:function(){
	   						Ext.getCmp('olDocView').close();
	   					}
	   				});
	       	}, 500);
        }
		
	},
	initUI:function(dPanel){
	     Ext.useShims=true;
         this.formPanel=new HT.FormPanel({
         	  url : __ctxPath + '/document/saveOnlineDocument.do',
              defaults : {
					anchor : '98%,98%'
			  },
			  defaultType:'displayfield',
              items:[{
                 xtype:'hidden',
                 name:'document.docId'
              },{
				name : 'folderId',
				xtype : 'hidden',
				value:this.folderId
			},{
				fieldLabel:'目录名称',
				name : 'OnlineDocFolderName'
			},{
              	 fieldLabel:'文档名称',
                 name:'document.docName'
              },
              dPanel.panel,
              {
                 xtype:'hidden',
                 name:'documentFileId'
              },{
                 xtype:'hidden',
                 name:'document.docType'
              }
              ]
         
         });
         
         myFormPanel = this.formPanel;
         
         if(dPanel.flag)
         {
             var formPanel=this.formPanel;
             if (this.docId != null && this.docId != 'undefined') {
    			this.formPanel.loadData({
    				url : __ctxPath + '/document/getDocument.do?docId=' + this.docId,		
    				waitMsg : '正在载入数据...',
    				preName:'document',
    				root:'data',
    				success : function(response,options) {
    					var doc=Ext.util.JSON.decode(response.responseText).data;
    					var af = doc.attachFiles;
    					var folderId = doc.docFolder.folderId;
    					var folderName = doc.docFolder.folderName;
    					formPanel.getCmpByName('folderId').setValue(folderId);
    					formPanel.getCmpByName('OnlineDocFolderName').setValue(folderName);
    					if(af!=null){
    					   var fileId=af[0].fileId;
    					   formPanel.getCmpByName('documentFileId').setValue(fileId);
    					   if(Ext.isChrome){
								//谷歌浏览器加载插件有延迟问题，所以我们迟一点时间去打开文档
    					    	var setTimeNum = mySetTimeNum;
    					    	if(setTimeNum==null||setTimeNum=='undefined'||setTimeNum<1){
    						    	setTimeNum = 500;
    						    }
    						    setTimeout(function (){
    						    	//谷歌是异步的，需要加载完后触发事件来实现只读 （NtkOfficePanel.js中的documentOpenedOnComplete）
    						    	dPanel.officeObj.setAttribute("setFileReadOnly","yes");
									dPanel.openDoc(fileId);
			    				//	dPanel.setReadOnly();
								},setTimeNum);	
							}else{
								//IE的是同步的，只需要加载完直接设置只读
								if(Ext.isIE){
									dPanel.openDoc(fileId);
			    					dPanel.setReadOnly();
								}else{
									//火狐是异步的，需要加载完后触发事件来实现只读 （NtkOfficePanel.js中的documentOpenedOnComplete）
									dPanel.officeObj.setAttribute("setFileReadOnly","yes");
									dPanel.openDoc(fileId);
								} 	
							}	
    					}
    				},
    				failure : function(response,options) {
    					Ext.MessageBox.show({
    								title : '操作信息',
    								msg : '载入信息失败，请联系管理员！',
    								buttons : Ext.MessageBox.OK,
    								icon : 'ext-mb-error'
    							});
    				}
    			});
    		}
         }
		
		this.buttons=[{
		    xtype:'button',
		    text:'关闭',
		    iconCls:'btn-cancel',
		    scope:this,
		    handler:function(){
		    	if(this.docPanel.flag){
		    		this.docPanel.closeDoc();
		    	}
		        this.close();
		    }
		}];
	}
});