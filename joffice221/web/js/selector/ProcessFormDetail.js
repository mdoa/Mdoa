Ext.ns('ProcessRunDetail');
/**
 *新建流程 
 */
ProcessFormDetail = Ext.extend(Ext.Window, {
			// 构造函数
			constructor : function(_cfg) {

				Ext.applyIf(this, _cfg);
				// 初始化组件
				this.initUIComponents();
				// 调用父类构造
				ProcessFormDetail .superclass.constructor.call(this, {
									id:'ProcessFormDetail'+this.runId,
									title:'数据表单－'+this.name,
							 		iconCls:'menu-flowEnd',
							 		layout:'border',
							 		width : 830,
									height : 480,
									modal : true,
									buttonAlign : 'center',
							 		autoScroll:true,
							 		items:[
							 			this.rightPanel
							 		],
							 		buttons : [ {
										text : '取消',
										iconCls : 'btn-cancel',
										scope : this,
										handler : function() {
											this.close();
										}
									}]
				});
					
			},// end of constructor
			/**
			 * 初始化组件
			 */
			initUIComponents : function() {
					var piId=this.piId;
					var defId=this.defId;
					var runId=this.runId;
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
					this.rightPanel=new Ext.Panel({
						    region:'center',
						    width:400,
						    autoScroll:true,
						    border:false,
						    layout:'form',
						    defaults:{
						        anchor:'100% 100%'
						    },
						    items:[this.formPanel]
					});
			},// end of the initComponents()
 			 /**
 			  * 流程表单信息获取回调函数
 			  */
			getFormHtmlCallback:function(){
				//使用自定义Ext模板表单
				var json=document.getElementById('entity_'+this.runId);
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
							 if (Ext.DocumentForm){
						        	Ext.DocumentForm.call(this);
						        	Ext.DocumentForm="";
						        }
			        	};
			        //后加载文档的JS
		        	$ImportSimpleJs([
		        	     '/js/core/ntkoffice/NtkOfficePanel.js',
		        	     '/js/selector/SealDialog.js',
			             '/js/selector/PaintTemplateDialog.js'
		        	 ],callback,this);
					
				}catch(e){
					alert("数据加载有问题,请联系管理员!");
				}
			}
 });