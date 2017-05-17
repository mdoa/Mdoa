Ext.ns('FieldRightsForm');
/**
 * @author
 * @createtime
 * @class FieldRightsForm
 * @extends Ext.Window
 * @description 表单字段权限详细信息
 * @company 宏天软件
 */
FieldRightsForm = Ext.extend(Ext.Window, {
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 必须先初始化组件
				this.initUIComponents();
				FieldRightsForm.superclass.constructor.call(this, {
							id : 'FieldRightsFormWin',
							layout : 'fit',
							items : this.tabPanel,
							modal : true,
							height : 500,
							width : 800,
							maximizable : true,
							title : '表单字段权限详细信息',
							buttonAlign : 'center',
							buttons : [{
										text : '保存',
										iconCls : 'btn-save',
										scope : this,
										handler : this.save
									}, {
										text : '取消',
										iconCls : 'btn-cancel',
										scope : this,
										handler : this.cancel
									}]
						});
			},// end of the constructor
			// 初始化组件
			initUIComponents : function() {
	
				//分组数据
				this.store = new Ext.data.GroupingStore({
							proxy : new Ext.data.HttpProxy({
										url : __ctxPath
												+ '/flow/nodesFieldRights.do?defId='
												+ this.defId
									}),
							reader : new Ext.data.JsonReader({
										root : 'result',
										id : 'id',
										fields : [{
													name : 'rightId',
													type : 'int'
												}, {
													name : 'mappingId',
													type : 'int'
												}, 'taskName',{
													name : 'readWrite',
													type : 'int'
												}, {
													name : 'refieldId',
													type : 'int'
												}, 'fieldName', 'fieldLabel']
									}),
							groupField : 'taskName'
						});
				this.store.load();
				//列表面板
				this.gridPanel = new HT.EditorGridPanel({
							store : this.store,
							tbar : new Ext.Toolbar({
										items : [{
											text : '标为可写',
											iconCls : '',
											scope : this,
											handler : this.flagFunction.createDelegate(this,['2'])
										}, '-', {
											text : '标为可读',
											iconCls : '',
											scope : this,
											handler : this.flagFunction.createDelegate(this,['1'])
										}, '-', {
											text : '标为隐藏',
											iconCls : '',
											scope : this,
											handler : this.flagFunction.createDelegate(this,['0'])
										}]
									}),
							columns : [{
										header : "rightId",
										dataIndex : 'rightId',
										hidden : true
									}, {
										header : '节点名称',
										dataIndex : 'taskName',
										hidden : true
									}, {
										header : "字段名称",
										dataIndex : 'fieldName',
										sortable : true
									}, {
										header : "字段标签",
										dataIndex : 'fieldLabel',
										sortable : true
									},{
										dataIndex : 'readWrite',
										header : '权限',
										fixed : true,
										resizable : false,
										sortable : false,
										width : 50,
										menuDisabled : true,
										renderer : function(value) {
											if (value == 0) {
												return '<font color="gray">隐藏</font>';
											} else if (value == 1) {
												return '<font color="red">读</font>';
											} else if (value == 2) {
												return '<font color="green">写</font>';
											}
											else if (value == 3) {
												return '<font color="red">必填</font>';
											}
										},
										editor : new Ext.form.ComboBox({
													typeAhead : true,
													editable : false,
													triggerAction : 'all',
													lazyRender : true,
													mode : 'local',
													store : [['0', '隐藏'],
															['1', '读'],
															['2', '写'],['3', '必填']]
												})
									}],
							view : new Ext.grid.GroupingView({
										forceFit : true,
										startCollapsed : true,
										enableNoGroups : false,
										enableGroupingMenu : false,
										showGroupName : false,
										autoFill:true,
										groupTextTpl : '{text} 节点'
									})

						});
			    this.otherTab=new FormButtonRightUserSetting({defId:this.defId});
				
					this.tabPanel = new Ext.TabPanel({
								deferredRender : true,
								enableTabScroll : true,
								activeTab : 0, // first tab initially active,
								defaults : {
									autoScroll : true
								},
								
								scope : this,
								items : [{
											title : '表单字段权限',
											scope : this,
											layout : 'fit',
											items:this.gridPanel
										}, {
											title : '子表按钮权限',
											lazyrendering : true,
											layout : 'fit',
											scope : this,
											items :this.otherTab
										}] 
							});

			},// end of the initcomponents			
			/**
			 * 选择标记字段
			 * @return {}
			 */
			selectData : function() {
				var selectData = this.gridPanel.getSelectionModel().getSelections();
				if (selectData.length < 1) {
					Ext.ux.Toast.msg('操作信息', '请选择字段！');
					return null;
				}
				return selectData;
			},
			/**
			 * 标记为可写，可读，或者隐藏
			 * @param {} flag
			 */
			flagFunction : function(flag) {
				var array = this.selectData();
				if (array) {
					for (var i = 0; i < array.length; i++) {
						var rec = array[i];
						rec.set('readWrite', flag);
					}
				}
			},
			/**
			 * 保存记录
			 */
			save : function() {
				var params = [],secParams = [];
				var showTip1=true,showTip2=true;
				var errorMessage='';
				var _this=this;
				var mappingId='';
				var secStore = this.otherTab.buttonGridPanel.getStore();
				for (var i = 0; i < this.store.getCount(); i++) {
					var rec = this.store.getAt(i);					
					if (rec.dirty) {
						if (rec.data.rightId == '' || rec.data.rightId == null
								|| rec.data.rightId == undefined) {
							rec.set('rightId', -1);
					    }
						params.push(rec.data);
					}
				}				
				for (var i = 0; i < secStore.getCount(); i += 1) {
					var record = secStore.getAt(i);
					if (record.dirty) {
						if (record.data.buttonId == '' || record.data.buttonId == null
								|| record.data.rightId == undefined) {
							record.set('buttonId', -1);
						}
						mappingId=record.data.mappingId;
						secParams.push(record.data);
					}
				}
				if (params.length == 0 && secParams.length==0) {
					Ext.ux.Toast.msg('操作信息', '没有修改过数据！');
					return;
				}
				var showMessage=function(err){
					if(showTip1 && showTip2){
						if(errorMessage.length==0 && err.length==0){
						   Ext.ux.Toast.msg('操作信息', '成功保存信息！');
						   _this.close();
						}else{
							Ext.ux.Toast.msg('操作信息', '保存信息出错！'+errorMessage);
						}
					}else{
						if(err.length>0){
							errorMessage+=err;
						}
					}
				};
				if(params.length > 0){
					showTip1=false;
					Ext.Ajax.request({
							url : __ctxPath + '/flow/multSaveFieldRights.do',
							method : 'post',

							params : {
								data : Ext.encode(params)
								
							},
							scope : this,
							success : function() {
								showTip1=true;
								showMessage('');
								
							},
							failure : function() {
								showTip1=true;
								showMessage('字段权限保存出错');
							}
						});
				}
				if(secParams.length > 0){
					showTip2=false;
					Ext.Ajax.request({
							url : __ctxPath + '/flow/multSaveFormButtonRight.do',
							method : 'post',
							params : {
								data : Ext.encode(secParams),
								mappingId:mappingId
							},
							scope : this,
							success : function() {
								showTip2=true;
								showMessage('');
							},
							failure : function() {
								showTip2=true;
								showMessage('按钮权限保存出错');
								
							}
						});
				}
		       
			},// end of save
			/**
			 * 取消
			 * 
			 */
			cancel : function() {
				this.close();
			}
		});
		
var FormButtonRightUserSetting = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		this.initUIs();
		FormButtonRightUserSetting.superclass.constructor.call(this, {
					layout : 'fit',
					border :false,
					items : this.buttonGridPanel
				});
	},
	/**
	 * 初始化组件
	 */
	initUIs:function(){
		    var me = this;
		    var row = 0;
			// 用户来自编辑器
			this.unamesEditor = new Ext.form.TriggerField({
						triggerClass : 'x-form-browse-trigger',
						editable : false,
						scope : this,
						allowBlank : false,
						scope : this,
						onTriggerClick : function(e) {
							var store = me.buttonGridPanel.getStore();
							var record = store.getAt(row);
							var userType = record.get('userType');
							var uids = record.get('uids');
							var unames = record.get('unames');
							// alert(me.setUserInfo);
							switch (userType) {
							//	case 1 : // 发起人
							//		me.setUserInfo(record, '__start', '[发起人]');
							//	break;
								case 2 :// 用户
									me.userSelector(record, uids, unames);
									break;
								case 3 :// 角色
									me.roleSelector(record, uids, unames);
									break;
								case 4 :// 岗位
									me.jobSelector(record, uids, unames);
									break;
							   	case 5 : // 部门岗位
									me.depPosSelector(record, uids, unames);
									break;
								case 6 :// 部门负责人
									me.depSelector(record, uids, unames);
									break;
								//case 7 :// 发起人上下级
								//	me.reJobSelector(record, uids, unames);
								//	break;
								default :
									break;
							}
							me.buttonGridPanel.stopEditing();
						}
					});
			/**
			 * 对应的地方设置值
			 */
			this.setUserInfo = function(record, ids, names) {
				record.set('uids', ids);
				record.set('unames', names);
			},
			/**
			 * 2.用户选择器
			 */
			this.userSelector = function(record, uids, unames) {
				new UserDialog({
							scope : this,
							single : false,
							isForFlow : false,
							userIds : uids,
							userName : unames,
							callback : function(ids, names) {
								me.setUserInfo(record, ids, names);
							}
						}).show();
			};
			/**
			 * 3.角色选择器
			 */
			this.roleSelector = function(record, uids, unames) {
				new RoleDialog({
							scope : this,
							single : false,
							roleIds : uids,
							roleName : unames,
							callback : function(ids, names) {
								this.setUserInfo(record, ids, names);
							}
						}).show();
			};
	
			/**
			 * 4.岗位选择器
			 */
			this.jobSelector = function(record, uids, unames) {
				new PositionDialog({
							scope : this,
							single : false,
							sameLevelIds : uids,
							sameLevelNames : unames,
							callback : function(ids, names) {
								this.setUserInfo(record, ids, names);
							}
						}).show();
			};
			/**
			 * 5.部门职位选择器
			 */
			this.depPosSelector = function(record, uids, unames) {
				new PositionDialog({
							scope : this,
							single : false,
							sameLevelIds : uids,
							sameLevelNames : unames,
							callback : function(ids, names) {
								this.setUserInfo(record, ids, names);
							}
						}).show();
			};
			/**
			 * 6.部门负责人
			 */
			this.depSelector = function(record, uids, unames) {
				new DepDialog({
							single : false,
							isCharge : true,
							depIds : uids,
							depNames : unames,
							socpe : this,
							callback : function(ids, names) {
								me.setUserInfo(record, ids, names);
							}
						}).show();
			};
	
			/**
			 * 7.相对岗位选择器
			 */
			this.reJobSelector = function(record, uids, unames) {
				new ReJobDialog({
							scope : this,
							single : false,
							reJobId : uids,
							reJobName : unames,
							// posUserFlag:
							// record.get('posUserFlag')==null||record.get('posUserFlag')==''?0:record.get('posUserFlag'),
							callback : function(ids, names, posUserFlag) {
								this.setUserInfo(record, ids, names);
							}
						}).show();
			};
				
		    //分组数据
			this.buttonStore = new Ext.data.GroupingStore({
						proxy : new Ext.data.HttpProxy({
									url : __ctxPath
											+ '/flow/nodesFormButtonRight.do?defId='
											+ this.defId
								}),
						reader : new Ext.data.JsonReader({
									root : 'result',
									fields : [{
												name : 'buttonId',
												type : 'int'
											},{
												name : 'tableId',
												type : 'int'
											},{
												name : 'buttonType',
												type : 'int'
											},'tableShowName','tableName', {
												name : 'mappingId',
												type : 'int'
											}, 'taskName',{
												name : 'buttonRight',
												type : 'int'
											},{
												name : 'userType',
												type : 'int'
											}, 'uids', 'unames']
								}),
						groupField : 'taskName'
					});
			this.buttonStore.load();
				//列表面板
			this.buttonGridPanel = new HT.EditorGridPanel({
							store : this.buttonStore,
							columns : [{
										header : "buttonId",
										dataIndex : 'buttonId',
										hidden : true
									},{
										header : "tableId",
										dataIndex : 'tableId',
										hidden : true
									}, {
										header : '节点名称',
										dataIndex : 'taskName',
										hidden : true
									}, {
										header : '已选用户',
										dataIndex : 'uids',
										hidden : true
									}, {
										header : '子表key',
										dataIndex : 'tableName',
										hidden : true
									}, {
										header : "子表名称",
										dataIndex : 'tableShowName'
									}, {
										header : "按钮类型",
										dataIndex : 'buttonType',
										sortable : false,
										menuDisabled : true,
										renderer : function(value) {
											switch (value) {
												case 1 :
													return '添加按钮';
												case 2 :
													return '删除按钮';							
												default :
													return '无';
											}
										}
									},{
										header : "用户类型",
										dataIndex : 'userType',
										sortable : false,
										menuDisabled : true,
										renderer : function(value) {
											switch (value) {
											//	case 1 :
											//		return '发起人';
												case 2 :
													return '用户';
												case 3 :
													return '角色';
												case 4 :
													return '岗位';
												case 5 :
													return '部门岗位';
												case 6 :
													return '部门负责人';
											//	case 7 :
											//		return '发起人上下级';
												default :
													return '任何人';
											}
										},
										editor : new Ext.form.ComboBox({
														allowBlank : false,
														editable : false,
														mode : 'local',
														triggerAction : 'all',
														store : [[0, '任何人'], [2, '用户'], [3, '角色'], [4, '岗位'],
																[5, '部门岗位'], [6, '部门负责人']],
														listeners : {
															scope : this,
															'change' : function(field, newValue, oldValue) {
																var store = this.buttonGridPanel.getStore();
																var record = store.getAt(row);
																if (newValue != oldValue) {
																	this.setUserInfo(record, '', '');
																}
																this.buttonGridPanel.stopEditing();
															}
														}
													})
									}, {
										header : '用户来自',
										dataIndex : 'unames',
										width : 300,
										scope : this,
										editor : this.unamesEditor
									},{
										dataIndex : 'buttonRight',
										header : '权限',
										fixed : true,
										resizable : false,
										sortable : false,
										width : 50,
										menuDisabled : true,
										renderer : function(value) {
											if (value == 0) {
												return '<font color="gray">隐藏</font>';
											} else if (value == 1) {
												return '<font color="red">显示</font>';
											} 
										},
										editor : new Ext.form.ComboBox({
													typeAhead : true,
													editable : false,
													triggerAction : 'all',
													lazyRender : true,
													mode : 'local',
													store : [['0', '隐藏'],
															['1', '显示']]
												})
									}],
										// 行选择监听
							listeners : {
								scope : this,
								'cellclick' : function(grid, rowIndex, columnIndex, e) {
									row = rowIndex;
								}
							},
							view : new Ext.grid.GroupingView({
										forceFit : true,
										startCollapsed : true,
										enableNoGroups : false,
										enableGroupingMenu : false,
										showGroupName : false,
										autoFill:true,
										groupTextTpl : '{text} 节点'
									})
						});
	}
});