/**
 * @author:
 * @class PaintTemplateView
 * @extends Ext.Panel
 * @description [PaintTemplate]管理
 * @company 杭州梦德软件有限公司
 * @createtime:
 */
PaintTemplateView = Ext.extend(Ext.Panel, {
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 初始化组件
				this.initUIComponents();
				// 调用父类构造
				PaintTemplateView.superclass.constructor.call(this, {
							id : 'PaintTemplateView',
							title : '套红模板管理',
							region : 'center',
							iconCls:'menu-template',
							layout : 'border',
							items : [this.searchPanel, this.gridPanel]
						});
			},// end of constructor
			// 初始化组件
			initUIComponents : function() {
				// 初始化搜索条件Panel
				this.searchPanel=new HT.SearchPanel({
					    layout : 'form',
						region : 'north',
						colNums : 5,
						keys : [{
									key : Ext.EventObject.ENTER,
									fn : this.search,
									scope : this
								}, {
									key : Ext.EventObject.ESC,
									fn : this.reset,
									scope : this
								}],
						height : 35,
						labelWidth : 60,
					   items:[{
							   fieldLabel : '模板名称',
							   xtype:'textfield',
							   name:'Q_templateName_S_LK'
						   },{
							   xtype:'button',
							   text:'查询',
							   scope:this,
							   iconCls:'btn-search',
							   handler:this.search
						   },{
							   xtype:'button',
							   text:'重置',
							   scope:this,
							   iconCls:'btn-reset',
							   handler:this.reset
						   }]
			   }); 

			   //顶部按钮
				this.topbar = new Ext.Toolbar({
							items : [{
										iconCls : 'btn-add',
										text : '添加模板',
										xtype : 'button',
										scope : this,
										handler : this.createRs
									}, {
										iconCls : 'btn-del',
										text : '删除模板',
										xtype : 'button',
										scope : this,
										handler : this.removeSelRs
									}]
						});

				//在线文档列表GridPanel
				this.gridPanel = new HT.GridPanel({
					id:'PaintTemplateGrid',
					region : 'center',
					tbar : this.topbar,
					// 使用RowActions
					rowActions : true,
					url : __ctxPath + "/document/listPaintTemplate.do",
					sort : [{
								field : 'ptemplateId',
								direction : 'DESC'
							}],
					fields : [{
								name : 'ptemplateId',
								type : 'int'
							}, 'fileId', 'templateName', 'templateKey', 'path', 'isActivate','fileAttach','fileAttach.note'],
					columns : [{
								header : 'ptemplateId',
								dataIndex : 'ptemplateId',
								hidden : true
							}, {
								header : 'fileId',
								hidden:true,
								dataIndex : 'fileId'
							}, {
								header : '模板名称',
								dataIndex : 'templateName'
							},{
								header: '模板Key',
								dataIndex:'templateKey'
							},{
							    header:'文件大小',
							    dataIndex:'fileAttach.note'
							}
							, new Ext.ux.grid.RowActions({
										header : '管理',
										actions : [{
													iconCls : 'btn-del',
													qtip : '删除',
													style : 'margin:0 3px 0 3px'
												},{
													iconCls : 'btn-edit',
													qtip : '编辑',
													style : 'margin:0 3px 0 3px'
												},{
												     iconCls:'btn-edit-online',
												     qtip:'在线修改',
												     style:'margin:0 3px 0 3px'
												}],
										listeners : {
											scope : this,
											'action' : this.onRowAction
										}
									})]
						// end of columns
				});
				this.gridPanel.addListener({scope : this,'rowdblclick': this.rowClick});

			},// end of the initComponents()
			// 重置查询表单
			reset : function() {
				this.searchPanel.getForm().reset();
			},
			// 按条件搜索
			search : function() {
				$search({
							searchPanel : this.searchPanel,
							gridPanel : this.gridPanel
						});
			},
			// GridPanel行点击处理事件
			rowClick : function(grid, rowindex, e) {
				var record = grid.getStore().getAt(rowindex);
				this.editRs.call(this,record);						
			},
			// 创建记录
			createRs : function() {
				new DocumentTemplateForm().show();
			},
			// 按ID删除记录
			removeRs : function(id) {
				$postDel({
							url : __ctxPath
									+ '/document/multiDelPaintTemplate.do',
							ids : id,
							grid : this.gridPanel
						});
			},
			// 把选中ID删除
			removeSelRs : function() {
				$delGridRs({
							url : __ctxPath
									+ '/document/multiDelPaintTemplate.do',
							grid : this.gridPanel,
							idName : 'ptemplateId'
						});
			},
			// 编辑Rs
			editRs : function(record) {
				new PaintTemplateForm({
							ptemplateId : record.data.ptemplateId
						}).show();
			},
			editOnline:function(record){
			     new DocumentTemplateForm({
			         ptemplateId : record.data.ptemplateId
			     }).show();
			},
			// 行的Action
			onRowAction : function(grid, record, action, row, col) {
				switch (action) {
					case 'btn-del' :
						this.removeRs.call(this, record.data.ptemplateId);
						break;
					case 'btn-edit' :
						this.editRs.call(this, record);
						break;
					case 'btn-edit-online' :
						this.editOnline.call(this, record);
						break;
					default :
						break;
				}
			}
		});
