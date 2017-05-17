//实例化的控件对象
var myNewOffice = null;
//页面对象
var myFormPanel = null;
//提交类型
var mySubmitType = null;
//文档上传后的内容容器
var myhiddenF = null;
//参数
var myBaseParams = null;
//文档上传后的内容容器
var myTabFormId = null;
//文档上传后的内容容器1.0秒
var mySetTimeNum =1000;



/**
 * 集成软航Office在线编辑控件
 * @param {} conf
 * @return {}
 */
NtkOfficePanel=function(conf){
	var me = this;
	var isFileOpen=false;
	conf.doctype=conf.doctype?conf.doctype:'doc';
	var fileId=conf.fileId?conf.fileId:'';
	var tempPath=conf.tempPath;
	var usetemplate=conf.usetemplate?conf.usetemplate:0;
	var deftemplatekey=conf.deftemplatekey?conf.deftemplatekey:'';

	{
		//浏览器判断
		var Sys = {};
		var ua = navigator.userAgent.toLowerCase();
		var s;
		(s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] : (s = ua
			    .match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] : (s = ua
				.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] : (s = ua
				.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] : (s = ua
				.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;
				
		this.width = "100%";
		this.height = "100%";
		//插件实例化对象的ID
		this.myOfficeObject=conf.myOfficeObject?conf.myOfficeObject:'myOfficeObject';
		//插件的CLASSID
		this.classid = 'A39F1330-3322-4a1d-9BF0-0BA2BB90E970';
		//插件CAB包路径和版本
		this.cabPath = __ctxPath+'/js/core/ntkoffice/OfficeControl.cab#version=5,0,2,5';
		//授权KEY
		this.params={
				Caption:"欢迎使用",
				MakerCaption:"杭州梦德软件有限公司",
				MakerKey:"CF4960BFDB79D36ADDC5493B116D39D6A4E335D9",
				ProductCaption:"office在线编辑",
				ProductKey:"59B7BC9B82FF12E76A1530A04ABC9BB4896BC153",
				WebUserName:"office在线编辑",
				TitlebarColor:"14402205",
				IsCheckEkey:"0",
				IsUseUTF8URL:"-1",
				IsUseUTF8Data:"-1",
				BorderStyle:"1",
				BorderColor:"14402205",
				TitlebarTextColor:"0",
				MenubarColor:"14402205",
				MenuButtonColor:"16180947",
				MenuBarStyle:"3",
				MenuButtonStyle:"7"
		};
	};

	var officeObj = document.createElement('object');
	if (Sys.ie) {		

		officeObj.id = this.myOfficeObject;
		officeObj.width = this.width;
		officeObj.height = this.height;
		officeObj.codebase = this.cabPath;
		officeObj.classid= "clsid:"+this.classid; 
		officeObj.Caption=this.params["Caption"];
		officeObj.MakerCaption=this.params["MakerCaption"];
		officeObj.MakerKey=this.params["MakerKey"];
		officeObj.ProductCaption=this.params["ProductCaption"];
		officeObj.ProductKey=this.params["ProductKey"];
		officeObj.WebUserName=this.params["WebUserName"];
		
		var p = null;
		for(var key in this.params){
			if(key!='Caption'&&key!='MakerCaption'&&key!='MakerKey'&&key!='ProductCaption'&&key!='ProductKey'&&key!='WebUserName'){
				p=document.createElement('param');
				p.setAttribute('name',key);
				p.setAttribute('value',this.params[key]);
				officeObj.appendChild(p);
			}
		}		
		
	} else if (Sys.firefox||Sys.chrome) {
		officeObj.setAttribute("id",this.myOfficeObject);
		officeObj.setAttribute("width",this.width);
		officeObj.setAttribute("height",this.height);
		officeObj.setAttribute("codebase",this.cabPath);
		officeObj.setAttribute("clsid","{"+this.classid+"}");
		officeObj.setAttribute("type","application/ntko-plug");
		//只读判断
		if(conf.right==1||conf.readOnly){
			officeObj.setAttribute("setFileReadOnly","yes");
		}else{
			officeObj.setAttribute("setFileReadOnly","no");  //yes or no 
		}
		
		officeObj.setAttribute("ForOnSaveToURL","saveMethodOnComplete");
		officeObj.setAttribute("ForOnAddTemplateFromURL","addTemplateOnComplete");	
		officeObj.setAttribute("ForOndocumentopened","documentOpenedOnComplete");	
		for(var key in this.params){
			officeObj.setAttribute("_"+key,this.params[key]);
		}
		
	}else{
		//alert("对不起,控件暂时不支持该浏览器!");
	}
	
	var panelConf={
		border:false,
		layout:'fit'
	};
	
	/**
	 * 保存文档
	 */
	var saveFn=function(config){
		Ext.MessageBox.show({    
		     		title:'上传文档数据',    
			        msg:'正在上传文档数据...',        
			        Width:200,    
			        progress:false,    
			        closable:false    
		}); 
		fileId=config.fileId?config.fileId:'';
		var docName=config.docName?config.docName:'未命名';
		officeObj.IsUseUTF8URL=true;
     	officeObj.IsUseUTF8Data=true;
		var result= officeObj.SaveToURL(__fullPath + '/file-upload',"document/saveOnlineManage","fileId="+fileId+'&&file_cat=document/saveOnlineManage',docName+'.'+conf.doctype,0);
		var obj;
		if(Sys.firefox||Sys.chrome){
			fileId=-11;
			obj={success:true};
		}else{
			obj = Ext.util.JSON.decode(result);
			if(obj && obj.success){
				fileId=obj.fileId;
			}else{
				obj={success:false};
			}
			Ext.MessageBox.hide();
		}
		
		return obj;
	};
	/**
	 * 是否显示菜单
	 */
	if(conf.unshowMenuBar==undefined ||conf.unshowMenuBar==null){
		conf.unshowMenuBar=true;
	}
	if(conf.unshowMenuBar){
		if (Sys.firefox||Sys.chrome) {
		    officeObj.setAttribute("_Menubar",false); // 控制菜单栏
		    officeObj.setAttribute("_IsShowEditMenu",false);
		    officeObj.setAttribute("_FileNew",false);
		    officeObj.setAttribute("_FileOpen",false);
		    officeObj.setAttribute("_FileSave",false);
		    officeObj.setAttribute("_FileSaveAs",false);
		    officeObj.setAttribute("_Titlebar",false); // 控制标题栏
		}else{
			officeObj.Menubar=false; // 控制菜单栏
		    officeObj.IsShowEditMenu=false;
		    officeObj.FileNew=false;
		    officeObj.FileOpen=false;
		    officeObj.FileSave=false;
		    officeObj.FileSaveAs=false;
		    officeObj.Titlebar=false; // 控制标题栏
		}	    
	}
	if(conf.showToolbar){
		var buttons=[];
		
		var fileMenu = new Ext.menu.Menu({
	        items:[
	            {
	            	text:'新建',
	            	handler : function() {
	            		if(conf.doctype=='doc'){
	            			officeObj.CreateNew("Word.Document");
	            		}
						if(conf.doctype=='xls'){
							officeObj.CreateNew("Excel.Sheet");
						}
					}
				},
	           	{
	            	text:'打开',
	            	handler : function() {
						officeObj.showDialog(1);
					}
				},
	            {
	            	text:'关闭',
	            	handler : function() {
						officeObj.Close();
					}
				},'-',
				{
					text:'保存',
	            	handler : function() {
						officeObj.showdialog(2);
					}
				},'-',
				{
					text:'打印设置',
	            	handler : function() {
						officeObj.showdialog(5);
					}
				},{
					text:'打印',
	            	handler : function() {
						officeObj.printout(true);
					}
				},{
					text:'打印预览',
	            	handler : function() {
						officeObj.PrintPreview();
					}
				}
	        ]
	    });
		
	    buttons.push({
        	text : '文件',
			iconCls : '',
			menu:fileMenu
        });
        
        buttons.push('-');
		
		if(conf.doctype=='doc'){
            buttons.push({
		               text : '保留修改痕迹',
							iconCls : 'btn-archive-save-trace',
							handler : function() {
								if(isFileOpen){
									officeObj.ActiveDocument.Application.UserName=curUserInfo.fullname;
									officeObj.ActiveDocument.TrackRevisions=true;
							    }
						}
            });
            buttons.push('-');
		}
		if(conf.doctype=='doc'){
		   buttons.push({
						text : '取消保留痕迹',
						iconCls : 'btn-archive-cancel-trace',
						handler : function() {
							if(isFileOpen){
								officeObj.ActiveDocument.TrackRevisions=false;
							}
						}
					});
			buttons.push('-');
		}
		if(conf.doctype=='doc'){
		   buttons.push({
			   	    text : '清除痕迹',
					iconCls : 'btn-archive-eraser',
					handler : function() {
						if(isFileOpen){
							officeObj.ActiveDocument.AcceptAllRevisions();
						}
					}
			   });
			buttons.push('-');
		}
		
		if(conf.doctype=='doc'){
			buttons.push({
		            text:'模板套红',
					iconCls:'',
					scope:this,
					handler:function(){
						if(isFileOpen){
							new PaintTemplateDialog({callback:function(name,path){
							    if(path!=''){
							    	var headFileURL=__ctxPath+'/attachFiles/'+path;
								    if(officeObj.doctype!=1){return;}//OFFICE_CONTROL_OBJ.doctype=1为word文档
								    try{
										//选择对象当前文档的所有内容
										var curSel = officeObj.ActiveDocument.Application.Selection;
										curSel.WholeStory();
										curSel.Cut();
										
										if(!(Sys.firefox) && !(Sys.chrome)){      //IE是同步的，它 会等待模版加载成功后执行书签的插入 （火狐和谷歌就不可以）
											//插入模板
											officeObj.AddTemplateFromURL(headFileURL);
											var BookMarkName = "content";
											if(!officeObj.ActiveDocument.BookMarks.Exists(BookMarkName)){
												alert("Word 模板中不存在名称为：\""+BookMarkName+"\"的书签！");
												return;
											}
											var bkmkObj = officeObj.ActiveDocument.BookMarks(BookMarkName);	
											var saverange = bkmkObj.Range
											saverange.Paste();
											officeObj.ActiveDocument.Bookmarks.Add(BookMarkName,saverange);		
										}else{
											//插入模板(火狐谷歌 异步)
											officeObj.AddTemplateFromURL(headFileURL);     //AddTemplateFromURL
										}						
									}catch(err){
										alert("错误：" + err.number + ":" + err.description);
									}
							    }
							}}).show();
						}
					}
			});
			buttons.push('-');
		}
		
		if(fileId==null || fileId==''){
			buttons.push({
				text:'选择Office模板',
				scope:this,
				handler:function(){
					new PaintTemplateDialog({callback:function(name,path){
					    this.close();
					    if(path!=''){
					    	var headFileURL=__ctxPath+'/attachFiles/'+path;
						    if(officeObj.doctype!=1){return;}//OFFICE_CONTROL_OBJ.doctype=1为word文档
							try
							{
							   officeObj.ActiveDocument.Application.Selection.HomeKey(6,0);//光标移动到文档开头
							   officeObj.OpenFromURL(headFileURL);//在光标位置插入红头文档
							}catch(err){
								alert('err:'+err);
							}
					    }
					}}).show();
				}
			});
		}
		
		if(conf.doctype=='doc'||conf.doctype=='xls'){
			buttons.push('-');
		   buttons.push({
		     		text:'手写签名',
					iconCls:'',
					scope:this,
					handler:function(){
						if(isFileOpen){
							try
							{
							   officeObj.DoHandSign2(
										"ntko",//手写签名用户名称
										"ntko",//signkey,DoCheckSign(检查印章函数)需要的验证密钥。
										0,//left
										0,//top
										1,//relative,设定签名位置的参照对象.0：表示按照屏幕位置插入，此时，Left,Top属性不起作用。1：光标位置；2：页边距；3：页面距离 4：默认设置栏，段落（为兼容以前版本默认方式）
										100);
							}catch(err){
							}
						}
					}
		   });
			buttons.push('-');
		}
		
		if(conf.doctype=='doc'||conf.doctype=='xls'){
			//import SealDialog.js
			//
			
			buttons.push({
			        text:'盖章',
					iconCls:'',
					scope:this,
					handler:function(){
							new SealDialog({
								scope : this,
								callback:function(name,path,belongName){
									this.close();
									if(path!=''){
										var signUrl=__ctxPath+'/attachFiles/'+path;
										if(officeObj.doctype==1||officeObj.doctype==2)
										{
											try
											{
												officeObj.AddSecSignFromURL(curUserInfo.fullname,//印章的用户名
												signUrl,//印章所在服务器相对url
												0,//left
												0,//top
												1,//relative
												2,  //print mode 2
												false,//是是否使用证书，true或者false，
												1);
											}catch(error){
											
											}
										}
									}
								}
							}).show();
					}
				});
				buttons.push('-');
		}
		
		buttons.push({
	        text:'全屏',
			iconCls:'',
			scope:this,
			handler:function(){
				officeObj.FullScreenMode=true;
			}
		});
		
		panelConf.tbar=new Ext.Toolbar({
			items:buttons
		});
	}
	Ext.applyIf(panelConf,conf);
	var panel=new Ext.Panel(panelConf);
	panel.on('afterrender',function(){	   
		    panel.body.appendChild(officeObj);	
		    if(Sys.chrome){
				//谷歌浏览器加载插件有延迟问题，所以我们迟一点时间去打开文档
		    	var setTimeNum = mySetTimeNum;
		    	if(setTimeNum==null||setTimeNum=='undefined'||setTimeNum<1){
			    	setTimeNum = 800;
			    }
		    	if(mySubmitType == "ProcessRunStart"||mySubmitType == "ProcessNextForm"){
			    	setTimeNum = 1800;      //1.8秒  流程启动和下一步时
			    }else{
			    	setTimeNum = mySetTimeNum-300;
			    }
			    
		    	setTimeout(function (){
					//若使用了模板，则缺省打开模板
					if(fileId){
						officeObj.OpenFromURL(__ctxPath+'/file-download?fileId='+fileId);
						isFileOpen=true;
					}else if(usetemplate==1 && deftemplatekey!=''){
						Ext.Ajax.request({
							url:__ctxPath+'/document/getByKeyPaintTemplate.do',
							params:{
								method:'post',
								templateKey:deftemplatekey
							},
							success:function(response,options){
								var result=Ext.decode(response.responseText);
								if(result.data){
									var templateFileURL=__ctxPath+'/attachFiles/'+result.data.path;
									officeObj.OpenFromURL(templateFileURL);
									isFileOpen=true;
								}else{
									officeObj.CreateNew(fileType);
									isFileOpen=true;
								}
							}
						});
						
					}else if(tempPath){//直接按路径打开
						//var templateFileURL=__ctxPath+'/attachFiles/'+result.data.path;
						officeObj.OpenFromURL(tempPath);
					}else{
						var fileType='';
						switch (conf.doctype)
						{
							case 'doc':
								fileType = "Word.Document";
								fileTypeSimple = "wrod";
								break;
							case 'xls':
								fileType = "Excel.Sheet";
								fileTypeSimple="excel";
								break;
							case 'ppt':
								fileType = "PowerPoint.Show";
								fileTypeSimple = "ppt";
								break;
							case 4:
								fileType = "Visio.Drawing";
								break;
							case 5:
								fileType = "MSProject.Project";
								break;
							case 6:
								fileType = "WPS Doc";
								break;
							case 7:
								fileType = "Kingsoft Sheet";
								break;
							default :
								fileType = "Word.Document";
						}
						try{
							officeObj.CreateNew(fileType);
							isFileOpen=true;
						}catch(err){}
						
					}
					panel.doLayout();
				},setTimeNum);	
			}else{
				//若使用了模板，则缺省打开模板
				if(fileId){
					officeObj.OpenFromURL(__ctxPath+'/file-download?fileId='+fileId);
					isFileOpen=true;
				}else if(usetemplate==1 && deftemplatekey!=''){
					Ext.Ajax.request({
						url:__ctxPath+'/document/getByKeyPaintTemplate.do',
						params:{
							method:'post',
							templateKey:deftemplatekey
						},
						success:function(response,options){
							var result=Ext.decode(response.responseText);
							if(result.data){
								var templateFileURL=__ctxPath+'/attachFiles/'+result.data.path;
								officeObj.OpenFromURL(templateFileURL);
								isFileOpen=true;
							}else{
								officeObj.CreateNew(fileType);
								isFileOpen=true;
							}
						}
					});
					
				}else if(tempPath){//直接按路径打开
					//var templateFileURL=__ctxPath+'/attachFiles/'+result.data.path;
					officeObj.OpenFromURL(tempPath);
				}else{
					var fileType='';
					switch (conf.doctype)
					{
						case 'doc':
							fileType = "Word.Document";
							fileTypeSimple = "wrod";
							break;
						case 'xls':
							fileType = "Excel.Sheet";
							fileTypeSimple="excel";
							break;
						case 'ppt':
							fileType = "PowerPoint.Show";
							fileTypeSimple = "ppt";
							break;
						case 4:
							fileType = "Visio.Drawing";
							break;
						case 5:
							fileType = "MSProject.Project";
							break;
						case 6:
							fileType = "WPS Doc";
							break;
						case 7:
							fileType = "Kingsoft Sheet";
							break;
						default :
							fileType = "Word.Document";
					}
					try{
						officeObj.CreateNew(fileType);
						isFileOpen=true;
					}catch(err){}
					
				}
				panel.doLayout();
				//只读功能设置
				if(conf.right==1||conf.readOnly){
					if (Sys.ie) {	
						officeObj.SetReadOnly(true,'');
					}
				}
				
			}	
	});
	
	var  activeDoc = officeObj.ActiveDocument+'';
	
	//对外公共方法
	if(activeDoc=='null'||Sys.firefox||Sys.chrome||activeDoc=='undefined'){
		return {
			panel:panel,
			officeObj:officeObj,
			flag:true,
			openDoc:function(inFileId){
				panel.setDisabled(true);
				fileId=inFileId;
				officeObj.OpenFromURL(__ctxPath+'/file-download?fileId='+fileId);
				panel.setDisabled(false);
			},
			setReadOnly:function(){
			   officeObj.SetReadOnly(true,'');
			   if(Sys.firefox||Sys.chrome){
				   officeObj.setAttribute("setFileReadOnly","yes");
			   }			   
			},
			openDoc2:function(fileId,fileUrl){
			    fileId=fileId;
			    try{
			    	panel.setDisabled(true);
				    officeObj.OpenFromURL(__ctxPath+'/attachFiles/'+fileUrl);
					panel.setDisabled(false);
				    isFileOpen=true;
				}catch(err){
					isFileOpen=false;
				}
			},
			/**
			 * return json result is format as below:
			 * {sucess:false} or 
			 * {success:true,fileId:73,fileName:'myDoc.doc',filePath:'others/2010/aaa0393304.doc',message:'upload file success(10229 bytes)'}
			 */
			saveDoc:function(config){
				return saveFn(config);
			},
			closeDoc:function(){
				isFileOpen=false;
				officeObj.Close();
			}
		};
	}
	
	if(activeDoc=='undefined'&&!(Sys.firefox)&&!(Sys.chrome))
	{
		if(Sys.ie) 
		{
			msg = '请安装office插件';
		}else
		{
		    msg = 'office插件必需使用32位的ie、firefox和chrome浏览器进行浏览';
		}
		
		return {
			flag:false,
			msg:msg,
			panel:panel
		};
	}

};


/**
 * 火狐谷歌浏览器控件文档保存事件（异步的，IE是同步的）回调接管函数  是控件属性的ForOnSaveToURL对应的方法 （SaveToURL保存后的回调函数）
 * html 为后台返回的内容
 */
function saveMethodOnComplete(type, code, html) {
	Ext.MessageBox.hide();  //隐藏提示框
	if(html!=null&&html!='undefined'&&html!=""){
		var obj = Ext.util.JSON.decode(html);
		if (obj && obj.success) {
			var fileId=obj.fileId;
			if(mySubmitType == "OnlineDocumentForm"){
				var fileIdField = myFormPanel.getCmpByName('documentFileId');
				fileIdField.setValue(fileId);
				// 提交表单
				$postForm({
							formPanel : myFormPanel,
							waitMsg : '正在提交数据...',
							scope : this,
							url : __ctxPath + '/document/saveOnlineDocument.do',
							callback : function(myFormPanel, action) {
								if (this.callback) {
									this.callback.call(this.scope);
								}
								if (myNewOffice.flag) {
									myNewOffice.closeDoc();
								}
								Ext.getCmp('olDocForm').close();
							}
						});
			}else if(mySubmitType == "DocumentTemplateForm"){
				var path = obj.filePath;
				var fileField = myFormPanel.getCmpByName('paintTemplate.fileId');
				var PathField = myFormPanel.getCmpByName('paintTemplate.path');
				fileField.setValue(fileId);
				PathField.setValue(path);
				myFormPanel.getForm().submit({
						method : 'post',
						success : function(myFormPanel, action) {
							Ext.ux.Toast.msg('操作信息', '成功信息保存！');
							var grid = Ext.getCmp('PaintTemplateGrid');
							if (grid != null && grid != undefined) {
								grid.getStore().reload();
							}
							myNewOffice.closeDoc();
							Ext.getCmp('DocumentTemplateForm').close();
						},
						failure : function(myFormPanel, action) {
							
							var result=Ext.decode(action.response.responseText);
							
							if(result.success==false && (result.msg!=null && result.msg !=undefined)){
								Ext.ux.Toast.msg('操作信息','该Key已经存在!');
								return;
							}else{
								Ext.MessageBox.show({
											title : '操作信息',
											msg : '信息保存出错，请联系管理员！',
											buttons : Ext.MessageBox.OK,
											icon : 'ext-mb-error'
										});
								myNewOffice.closeDoc();
								Ext.getCmp('DocumentTemplateForm').close();
							}
						}
				});
			}else if(mySubmitType == "ArchivesDocForm"){
				docPath = obj.filePath;
				Ext.getCmp('docPath').setValue(docPath);
			}else if(mySubmitType == "ProcessRunStart"){
				myhiddenF.setValue(fileId);
				//启动工作流
				if(myFormPanel.getForm().isValid()){
					myFormPanel.getForm().submit({
						url : __ctxPath + '/flow/saveProcessActivity.do',
						waitMsg : '正在提交流程表单信息...',
						scope:this,
						params:myBaseParams,
						success : function(myFormPanel, action) {
							Ext.ux.Toast.msg('操作信息','成功保存信息！');
							if(myNewOffice){
								myNewOffice.closeDoc();
							}
							AppUtil.removeTab(myTabFormId);
						},
						failure: function(myFormPanel, action) {
							var msg = action.result.message;
							Ext.ux.Toast.msg('操作信息',msg);
							if(myNewOffice){
								myNewOffice.closeDoc();
							}
						}
					});
				}
			}else if(mySubmitType == "ProcessNextForm"){
				myhiddenF.setValue(fileId);
				if(myFormPanel.getForm().isValid()){//是合法有效
					myFormPanel.getForm().submit({
						url:__ctxPath+ "/flow/nextProcessActivity.do",
						method:'post',
						waitMsg:'正在提交处理，请稍等',
						scope:this,
						params:myBaseParams,
						success : function(myFormPanel, action) {
							Ext.ux.Toast.msg('操作信息','成功保存！');
							AppUtil.removeTab(myTabFormId);
							var MyTaskView=Ext.getCmp("MyTaskView");
							var appHomeTaskGrid=Ext.getCmp('TaskPanelView');
							if(appHomeTaskGrid!=null){
								appHomeTaskGrid.getUpdater().update(__ctxPath+ '/flow/displayTask.do');
							}
							if(MyTaskView!=null){
								MyTaskView.gridPanel.getStore().reload();
							}
							if(myNewOffice){
								myNewOffice.closeDoc();
							}
						},
						failure : function(myFormPanel, action) {
							Ext.ux.Toast.msg('操作信息','操作出错，请联系管理员！');
						}
					});
			   }			
			}
			
			
		}
	}
	
}



/**
 * 火狐谷歌浏览器控件文档在套红模版事件（异步的，IE是同步的）回调接管函数  一定是单独方法  是控件属性的ForOnAddTemplateFromURL对应的方法 （AddTemplateFromURL保存后的回调函数）
 * 
 */
function addTemplateOnComplete() {	
	var  officeObj = myNewOffice.officeObj;  //Office 实例对象
	var BookMarkName = "content";
	if(!officeObj.ActiveDocument.BookMarks.Exists(BookMarkName)){
		alert("Word 模板中不存在名称为：\""+BookMarkName+"\"的书签！");
		return false;
	}
	var bkmkObj = officeObj.ActiveDocument.BookMarks.Item(BookMarkName);	  //火狐和谷歌特有的
	var saverange = bkmkObj.Range;
	saverange.Paste();
	officeObj.ActiveDocument.Bookmarks.Add(BookMarkName,saverange);	
}


/**
 * 火狐谷歌浏览器控件文档在打开文档后的事件（异步的，IE是同步的）
 * 
 */
function documentOpenedOnComplete() {	
	if(myNewOffice!=null&& myNewOffice != undefined){
		var  officeObj = myNewOffice.officeObj;  //Office 实例对象
		//文档要求为只读时，通过Office 实例对象设置文档为只读
		if(officeObj!=null&& officeObj != undefined){
			var setFileReadOnly = officeObj.getAttribute("setFileReadOnly");
			if(setFileReadOnly=='yes'){
				officeObj.SetReadOnly(true,'');
			}
		}
	}
}


