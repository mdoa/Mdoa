Ext.ns('MyRunningTaskView');
/**
 * 我参与的正在运行中的任务
 * 以方便可以撤回
 * @class MyRunningTaskView
 * @extends Ext.Panel
 */
MyRunningTaskView = Ext.extend(Ext.Panel, {
			// 构造函数
			constructor : function(_cfg) {

				Ext.applyIf(this, _cfg);
				// 初始化组件
				this.initUIComponents();
				// 调用父类构造
				MyRunningTaskView .superclass.constructor.call(this, {
										title:'我的任务追回',
										layout:'border',
										id:'MyRunningTaskView',
										items:[
											this.gridPanel
										]
						});
			},// end of constructor
			/**
			 * 初始化组件
			 */
			initUIComponents : function() {
					// GridPanel
				this.gridPanel = new HT.GridPanel({
							region:'center',
							tbar:[{
								text:'追回',
								iconCls:'btn-back',
								scope:this,
								handler:this.rollbackTask
							}],
							rowActions : true,
							url : __ctxPath + '/flow/myRunningProcessRun.do',
							fields : [{name : 'runId',
										type : 'int'
									}, 'subject', 'createtime', 'defId',
									'piId', 'runStatus','tasks','exeUsers'],
							columns : [{
										header : 'runId',
										dataIndex : 'runId',
										hidden : true
									}, {
										header : '标题',
										dataIndex : 'subject',
										sortable:false
									}, {
										header : '创建时间',
										dataIndex : 'createtime',
										width : 60,
										sortable:false
									},{
										header:'运行任务名',
										dataIndex:'tasks',
										sortable:false
									},{
										header:'执行人',
										dataIndex:'exeUsers',
										sortable:false
									},new Ext.ux.grid.RowActions({
										header : '管理',
										width : 100,
										actions : [{
													iconCls : 'btn-flowView',
													qtip : '详细',
													style : 'margin:0 3px 0 3px'
												}, {
													iconCls : 'btn-back',
													qtip : '追回',
													style : 'margin:0 3px 0 3px'
												}],
											listeners : {
												scope : this,
												'action' : this.onRowAction
											}
										})
									]
						});
			},// end of the initComponents()
			/**
			 * 任务追回
			 */
			rollbackTask:function(){
				//取到需要进行任务追回的任务
				var selRs = this.gridPanel.getSelectionModel().getSelections();
				if(selRs.length==0){
					Ext.ux.Toast.msg('操作信息','请选择需要追回的流程!');
					return;
				}
				var runId=selRs[0].data.runId;				
				Ext.Ajax.request({
					url:__ctxPath+'/flow/rollbackProcessRun.do',
					method:'POST',
					params:{
						runId:runId
					},
					scope:this,
					success:function(resp,options){
						var result=Ext.decode(resp.responseText);
						if(!result.success){
							Ext.ux.Toast.msg('操作信息','该流程目前已经完成下一步的处理，不能追回！');
							return;
						}
						Ext.ux.Toast.msg('操作信息','已经成功追回，请查看待办事项！');
						this.gridPanel.getStore().reload();
					},
					failture:function(resp,options){
						Ext.ux.Toast.msg('操作信息','该流程目前已经完成下一步的处理，不能追回！');
					}
				});
			},
			/**
			 * 显示详细信息
			 * @param {} runId
			 * @param {} defId
			 * @param {} piId
			 * @param {} name
			 */
			showRunDetail:function(runId,defId,piId,name){
				var contentPanel=App.getContentPanel();
				var detailView=contentPanel.getItem('ProcessRunDetail'+runId);
				if(detailView==null){
					detailView=new ProcessRunDetail({
							runId:runId,
							defId:defId,
							piId:piId,
							name:name
						});
					contentPanel.add(detailView);
				}
				contentPanel.activate(detailView);
			},
			/**
			 * grid行点击触发事件
			 * @param {} grid
			 * @param {} rec
			 * @param {} action
			 * @param {} row
			 * @param {} col
			 */
			onRowAction:function(grid, rec, action, row, col) {
				switch (action) {
					case 'btn-flowView' :
						this.showRunDetail.call(this,rec.data.runId,rec.data.defId,rec.data.piId,rec.data.subject);
						break;
					case 'btn-back' :
						this.rollbackTask.call(this);
						break;
					default :
						break;
				}
			}
 });