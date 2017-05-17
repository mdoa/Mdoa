Ext.ns('ProcessRunDetail');
/**
 *新建流程 
 */
ProcessRunDetail = Ext.extend(Ext.Panel, {
			// 构造函数
			constructor : function(_cfg) {

				Ext.applyIf(this, _cfg);
				// 初始化组件
				this.initUIComponents();
				// 调用父类构造
				ProcessRunDetail .superclass.constructor.call(this, {
									id:'ProcessRunDetail'+this.runId,
									title:'流程详细－'+this.name,
							 		iconCls:'menu-flowEnd',
							 		layout:'border',
							 		tbar:this.toolbar,
							 		autoScroll:true,
							 		items:[
							 			this.leftPanel,this.rightPanel
							 		]
						});
			},// end of constructor
			/**
			 * 初始化组件
			 */
			initUIComponents : function() {
					var piId=this.piId;
					var defId=this.defId;
					var runId=this.runId;
					 this.leftPanel=new Ext.Panel({
				 		title:'流程示意图',
				 		width:500,
				 		autoScroll:true,
				 		height:800,
				 		split:true,
				 		collapsible: true,
				 		/*collapsed:true,*/
				 		region:'west',
				 		margin:'5 5 5 5',
				 		html:'<img src="'+__ctxPath+ '/jbpmImage?piId='+piId+'&defId='+defId+'&runId='+runId+'&rand='+ Math.random()+'"/>'
				 	});
				 	
				 	this.toolbar=new Ext.Toolbar({
				 		height:28,
				 		items:[
				 			{
				 				text:'刷新',
				 				iconCls:'btn-refresh',
				 				scope:this,
				 				handler:this.refreshClick
				 			},'-',
				 			{
				 			   text:'打印表单',
				 			   name:'formTablePrintButton',
				 			   iconCls:'btn-print',
				 			   scope:this,
				 			   handler:this.printClick				 				
				 			}
				 		]
				 	});
				 	
				 	this.formPanel=new HT.FormPanel({
						    title:'流程表单信息',
						    width:400,
						    autoScroll:true,
						    height:300,
						    autoLoad:{
									url:__ctxPath+ "/flow/getFormProcessActivity.do",
									nocache: true,
									params:{piId:piId,runId:runId,defId:defId},
									scope:this,
									callback:this.getFormHtmlCallback
								}
						});
					this.panel=new Ext.Panel({
						title:'流程审批信息',
						region:'center',
						width:400,
						autoScroll:true,
						autoLoad:{
							url:__ctxPath+'/flow/processRunDetail.do?piId='+piId + "&runId="+ runId,
							nocache:true
						}
					});
					
					this.rightPanel=new Ext.Panel({
						    region:'center',
						    width:400,
						    autoScroll:true,
						    border:false,
						    layout:'form',
						    defaults:{
						        anchor:'100% 100%'
						    },
						    items:[this.formPanel,this.panel]
					});
			},// end of the initComponents()
			/**
			 * 刷新处理函数
			 */
			refreshClick:function(){
 					this.leftPanel.body.update('<img src="'+__ctxPath+ '/jbpmImage?piId='+this.piId+'&defId='+this.defId+'&rand='+ Math.random()+'"/>');
 					this.rightPanel.doLayout(true);
 				},
 			/**
 			 * 打印表单处理函数
 			 */
 			printClick:function(){
 				   gpObj=this.formPanel.body.dom;
  				   printFlag = true;
 			       fckeditorIframe = null;
	  			   var iframes = gpObj.getElementsByTagName("iframe");
	  			   for(var i=0;i<iframes.length;i++){
		  			   if(iframes[i].id.indexOf("fckeditor")!=-1){
		  					fckeditorIframe = iframes[i];
		  				}
	  			   }
  				
		  		   window.open(__ctxPath+'/js/printer/Print.jsp');
 			   },
 			 /**
 			  * 流程表单信息获取回调函数
 			  */
			getFormHtmlCallback:function(){
				//使用自定义Ext模板表单
				var json=document.getElementById('entity_'+this.runId);
				var button=this.toolbar.getCmpByName('formTablePrintButton');
				if(!json||(json!=null&&!json.value)){
				   this.formPanel.hide();
				   button.hide();
				   return;
				}else{
				   this.formPanel.show();
				   button.show();
				}
				this.formPanel.doLayout();
				var formPanel=this.formPanel;
				//回填数据
				
				var form=this.formPanel.getForm().getEl().dom;
				var fElements = form.elements || (document.forms[form] || Ext.getDom(form)).elements;
				try{
					//var json=document.getElementById('entity_'+this.runId);
					var name,type,value,xtype;
				    //加载JS回调函数
					var callback=function(){
						    var entityJson=null;
							if(json!=null&&json.value){
								entityJson=Ext.decode(json.value);
							}
							$converDetail.call(this,entityJson,null,true);
			        	};
			        //后加载文档的JS
		        	$ImportSimpleJs([
		        	     '/js/core/ntkoffice/NtkOfficePanel.js',
		        	     '/js/selector/SealDialog.js',
			             '/js/selector/PaintTemplateDialog.js'
		        	 ],callback,this);
					
				}catch(e){
					//alert(e);
				}
			}
 });