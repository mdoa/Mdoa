Ext.ns('ProDefinitionView');
/**
 * 流程列表，可管理
 * 
 * @param isManager
 *            是否可管理
 */
ProDefinitionView = Ext.extend(Ext.Panel, {
			// 构造函数
				constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 初始化组件
				this.initUIComponents();
				// 调用父类构造
				ProDefinitionView .superclass.constructor.call(this, {
								title : '流程列表',
								layout:'border',
								region : 'center',
								autoScroll : true,
								items : [this.searchPanel, this.grid]
						});
			},// end of constructor
			/**
			 * 初始化组件
			 */
			initUIComponents : function() {
					    var isManager = this.isManager;					
						// 搜索面板
						this.searchPanel = new HT.SearchPanel({
							layout : 'form',
							region : 'north',
							colNums : 4,
							keys : {
								key : Ext.EventObject.ENTER,
								fn : this.search,
								scope : this
							},
							labelWidth : 70, // 一个bug
							items : [
									{
										fieldLabel : '流程名称',
										xtype : 'textfield',
										name : 'Q_name_S_LK',
										maxLength : 150
									},
									{
										fieldLabel : '流程描述',
										xtype : 'textfield',
										name : 'Q_description_S_LK',
										maxLength : 125,
										labelWidth : 70
									},
									{
										xtype : 'button',
										text : '查询',
										iconCls : 'search',
										scope : this,
										handler : this.search
									}, {
										xtype : 'button',
										text : '清空',
										iconCls : 'reset',
										scope : this,
										handler : this.clean
									}]
						});				
			//start gridpanel
					var _params = null;
					if(this.isManager){
						_params = {
							typeId : this.typeId == null ? 0 : this.typeId
						};
					}else{
						_params = {
							typeId : this.typeId == null ? 0 : this.typeId,
							'Q_deployId_S_NEQ' : 'null', //只取已发布的
							'Q_status_SN_EQ' : 1 //只取激活
						};
					}		
			   
				 this.grid = new HT.EditorGridPanel({
								clicksToEdit:1,
								region : 'center',
								tbar : [{
									iconCls : 'btn-flow-design',
									text : '在线流程设计',
									hidden:(!isGranted('_FlowDesign') && this.isManager ),
									handler : function() {
										window.open(__ctxPath + '/bpm/bpmDesign.do', '_blank');
									}
								},{
									iconCls : 'btn-add',
									text : '发布JBPM4流程',
									scope:this,
									hidden:(!isGranted('_FlowAdd') && this.isManager ),
									handler : function() {
										new ProDefinitionForm({
											typeId: this.typeId
										}).show();
									}
								},{
								iconCls : 'btn-del',
								text : '删除流程',
								scope:this,
								hidden:(!isGranted('_FlowDel') && this.isManager ),
								handler : this.delRecords
							}],
							rowActions:true,
							sort:[{field:'defId',direction:'DESC'}],
							url:__ctxPath + '/flow/listProDefinition.do',
							baseParams : _params,
							trackMouseOver : true,
							disableSelection : false,
							loadMask : true,
							fields : [{
										name : 'defId',
										type : 'int'
									}, 'proType', 'name', 'description',
									'createtime', 'deployId',{
										name : 'newVersion',
										type : 'int'
									},'status','drawDefXml'],
								  columns : [{
										header : 'defId',
										dataIndex : 'defId',
										hidden : true
									}, {
										header : '分类名称',
										dataIndex : 'proType',
										renderer : this.proTypeControl
									}, {
										header : '流程的名称',
										dataIndex : 'name'
									}, {
										header : '描述',
										dataIndex : 'description'
									}, {
										header : '创建时间',
										dataIndex : 'createtime'
									}, {
										header : '工作流id',
										dataIndex : 'deployId',
										hidden : 'true'
									},{
										header : '版本号',
										dataIndex : 'newVersion'
									},{
										header : '状态',
										dataIndex : 'status',
										renderer :this.statusControl ,
										editor : isManager ? new Ext.form.ComboBox({
											allowBlank : false,
											editable : false,
											mode : 'local',
											triggerAction : 'all',
											store : [['0', '禁用'], ['1', '激活']],
											listeners : {
															scope:this,
															'change' : this.statusEdit
														}
										}):null
									},new Ext.ux.grid.RowActions({
											header : '管理',
											width : 200,
											actions:[{
												 iconCls:'btn-del',qtip:'删除',style:'margin:0 3px 0 3px',
												 fn:function(record){
												 	 	if (isManager && isGranted('_FlowDel')) 
												 	 			return true;
												 	 	return false;
												 }
											   },{
												 iconCls:'btn-flow-design',qtip:'编辑在线',style:'margin:0 3px 0 3px',
												  fn:function(record){
												 	 	if (isManager && isGranted('_FlowEdit') && record.data.drawDefXml!=null)  
												 	 			return true;
												 	 	return false;
												 }
											   },{
												 iconCls:'btn-edit',qtip:'编辑',style:'margin:0 3px 0 3px',
												  fn:function(record){
												 	 	if (isManager && isGranted('_FlowEdit')) 
												 	 			return true;
												 	 	return false;
												 }
											   },{
												 iconCls:'btn-preview',qtip:'查看',style:'margin:0 3px 0 3px',
												  fn:function(record){
												 	 	if ( record.data.deployId!=null) 
												 	 			return true;
												 	 	return false;
												 }
											   },{
												 iconCls:'btn-setting',qtip:'流程设置',style:'margin:0 3px 0 3px',
												   fn:function(record){
												 	 	if ( record.data.deployId!=null && isGranted('_FlowSetting') && isManager) 
												 	 			return true;
												 	 	return false;
												 }
											   },{
												 iconCls:'btn-newFlow',qtip:'新建流程',style:'margin:0 3px 0 3px',
												   fn:function(record){
												 	 	if ( record.data.deployId!=null ) 
												 	 			return true;
												 	 	return false;
												 }
											   },{
												 iconCls:'btn-shared',qtip:'设置权限',style:'margin:0 3px 0 3px',
												   fn:function(record){
												 	 	if (isGranted('_FlowShared')) 
												 	 			return true;
												 	 	return false;
												 }
											   }],
											listeners : {
												 scope : this,
												'action' : this.onRowAction
											}
										})
							],
					listeners : {
									 scope : this,
									'rowdblclick' : this.rowClick
								}							
				});		
			},// end of the initComponents()
			/**
			 * 删除记录
			 * 
			 * @param {}
			 *            record
			 */
			delRecords : function(record) {
				$delGridRs({
					url : __ctxPath+ '/flow/multiDelProDefinition.do',
					grid : this.grid,
					idName : 'defId'
				});
			},
			/**
			 * proType字段显示控制
			 */
			proTypeControl:	function(value) {
								if (value != null)
										return value.typeName;
								else
									  return '<font color=\'red\'>未定义</font>';
			},
			/**
			 * status字段显示控制
			 */
			statusControl:function(value){
							if(value !=null && value ==1){
								return '<font color="green">激活</font>';
							}else{
								return '<font color="red">禁用</font>';
							}
			},
			/**
			 * status字段编辑控制
			 */
			statusEdit:function(field, newValue,oldValue) {
								var gridPanel =this.grid;// Ext.getCmp('ProDefinitionGrid' + (isManager ? '' : '0'));  //还得重新写
								var record = gridPanel.getStore().getAt(row);
								Ext.Ajax.request({
									url : __ctxPath
											+ '/flow/updateProDefinition.do',
									params : {
										'proDefinition.defId' : record.get('defId'),
										'proDefinition.status' : newValue
									},
									method : 'POST',
									success : function(response, options) {
										Ext.ux.Toast.msg('操作信息', '修改成功！');
									},
									failure : function(response, options) {
										Ext.ux.Toast.msg('操作信息','操作出错，请联系管理员！');
									}
								});
			},
			/**
			 * 列表双击处理
			 */
			rowClick:function(grid, rowindex, e) {
				row = rowindex;
				grid.getSelectionModel().each(function(rec) {
							if (this.isManager) {
								if (isGranted('_FlowEdit')) {
									this.reDesign(rec.data.defId);
								}
							}
						});
			},
			/**
			 * 搜索
			 */
			search : function(){
					$search({
						searchPanel : this.searchPanel,
						gridPanel : this.grid
					});
			},
			/**
			 * 清空
			 */
			clean: function(){
				this.searchPanel.getForm().reset();
			},
			/**
			 * 删除单个记录
			 */
			remove: function(id) {
				$postDel({
					msg: '注意：删除该流程定义，该流程下的所有相关数据也一并删除，\n并不能恢复，您确认要删除该记录吗？',
					url : __ctxPath+ '/flow/multiDelProDefinition.do',
					ids : id,
					grid : this.grid
				});
			},
			
			/**
			  * 编辑数据
			  * @param {} defId 流程主键id
			  */
			reDesign : function(defId){
					 window.open(__ctxPath + '/bpm/bpmDesign.do?defId=' + defId , 'flowDesign'+defId);			
			 },
			 /**
			  * 编辑
			  */
			edit : function(defId) {
			 	new ProDefinitionForm({defId:defId});
			 },
			 /**
			 * 流程信息查看
			 * 
			 * @param {}
			 *            id
			 * @param {}
			 *            name
			 */
			view : function(defId, name) {
				App.clickTopTab('ProDefinitionDetail_'+defId,{defId:defId,name:name});
			},
			/**
			 * 流程人员设置
			 */
			setting : function(defId, name) {
				var contentPanel = App.getContentPanel();
				var settingView = contentPanel.getItem('ProDefinitionSetting' + defId);
				if (!settingView) {
					settingView = new ProDefinitionSetting({defId:defId,name:name});
					contentPanel.add(settingView);
				}
				contentPanel.activate(settingView);
			},
			/**
			 * 新建流程
			 * 
			 * @param {}
			 *            defId
			 * @param {}
			 *            name
			 */
			newFlow : function(defId, name) {
				var contentPanel = App.getContentPanel();
				var startForm = contentPanel.getItem('ProcessRunStart' + defId);
			
				if (!startForm) {
					startForm = new ProcessRunStart({
								id : 'ProcessRunStart' + defId,
								defId : defId,
								flowName : name
							});
					contentPanel.add(startForm);
				}
				contentPanel.activate(startForm);
			},
			/**
			 * Pro设置流程权限
			 */
			 rights : function(defId){
			 	new ProDefRightsForm({
			 		defId : defId
			 	}).show();
			 },
			 /**
				 * 设置typeId函数
				 */
			 setTypeId : function(typeId) {
					this.typeId = typeId;
					ProDefinitionView.typeId = typeId;
				},
			/**
			 * 行的Action
			 */
			onRowAction : function(grid, record, action, row, col) {
				switch (action) {
					case 'btn-del' :
						this.remove.call(this,record.data.defId);
						break;
					case 'btn-flow-design' :
						this.reDesign.call(this,record.data.defId);
						break;
					case 'btn-edit' :
						this.edit.call(this,record.data.defId);
						break;
					case 'btn-preview' :
						this.view.call(this,record.data.defId,record.data.name);
						break;
					case 'btn-setting' :
						this.setting.call(this,record.data.defId,record.data.name);
						break;
					case 'btn-newFlow' :
						this.newFlow.call(this,record.data.defId,record.data.name);
						break;
					case 'btn-shared' :
						this.rights.call(this,record.data.defId);
						break;
					default :
						break;
				}
		}
 });