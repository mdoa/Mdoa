Ext.ns('NewProcess');
/**
 *新建流程 
 */

NewProcess = Ext.extend(Ext.Panel, {
			// 构造函数
			constructor : function(_cfg) {

				Ext.applyIf(this, _cfg);
				// 初始化组件
				this.initUIComponents();
				// 调用父类构造
				NewProcess .superclass.constructor.call(this, {
									title : '新建流程',
									id:'NewProcess',
									layout : 'border',
									iconCls:'menu-flowNew',
									height : 800,
									scope:this,
									items : [this.treePanel,this.proDefView]
						});
			},// end of constructor
			/**
			 * 初始化组件
			 */
			initUIComponents : function() {
					this.proDefView=new ProDefinitionView({isManager:true});//不能进行数据的管理
					this.treePanel = new Ext.Panel({
				 			layout : 'fit',
				 			region : 'west',
					 		collapsible : true,
							split : true, 
					 		width : 200,
					 		title:'流程分类树', 
					 		items : [new htsoft.ux.TreePanelEditor({
							             border:false,
							             autoScroll:true,
							             url:__ctxPath+'/system/flowTreeGlobalType.do?catKey=FLOW',
							             scope:this,
							             onclick:this.clickHandle
				 			})]
				 	});
			},// end of the initComponents()
			/**
			 * 树节点单击事件
			 * */
			 clickHandle:function(node){
		             	this.proDefView.setTypeId(node.id);					
						var defGridView=this.proDefView.grid;
						defGridView.getStore().proxy.conn.url=__ctxPath + '/flow/listProDefinition.do?typeId='+node.id;
						defGridView.getStore().load({
											params : {start : 0,limit : 25}
										});
		          }
 });