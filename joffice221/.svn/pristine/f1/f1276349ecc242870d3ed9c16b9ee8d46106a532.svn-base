Ext.ns("ProDefinitionSetting");
/**
 * 流程定义设置
 * 
 * @class ProDefinitionSetting
 * @extends Ext.Panel
 */
ProDefinitionSetting = Ext.extend(Ext.Panel, {
	// 构造方法
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		this.initUIs();
		ProDefinitionSetting.superclass.constructor.call(this, {
					id : 'ProDefinitionSetting_' + this.defId,
					title : this.title?this.title:'流程设置－' + this.name,
					layout : 'border',
					autoScroll : true,
					border : false,
					iconCls : 'menu-system-setting',
					items : [this.tabPanel]
				});
	},
	// 初始化组件
	initUIs : function() {
		// 选项卡布局
		this.tabPanel = new Ext.TabPanel({
					region : 'center',
					deferredRender : true,
					enableTabScroll : true,
					activeTab : 0, // first tab initially active,
					defaults : {
						autoScroll : true
					},
					scope : this,
					items : [{
						title : '流程定义明细',
						scope : this,
						autoLoad : {
							waitMsg : '载入中...',
							url : __ctxPath + '/flow/processDetail.do?defId='
									+ this.defId + '&rand=' + Math.random()
						}
					}, {
						title : '人员设置',
						lazyrendering : true,
						scope : this,
						items : [new ProDefinitionUserSetting({
									defId : this.defId
								})]
					}, {
						title : '流程干预设置',
						lazyrendering : true,
						autoLoad : {
							waitMsg : '载入中...',
							nocache : true,
							scope : this,
							url : __ctxPath + '/flow/processImage.do?defId='
									+ this.defId
						}
					}, {
						title : '表单设置',
						lazyrendering : true,
						scope : this,
						items : this.loadFromSet()
					}, {
						title : '历史版本',
						lazyrendering : true,
						scope : this,
						items : [new ProDefinitionHistory({
									defId : this.defId
								})]
					}, {
						title : '其它参数',
						lazyrendering : true,
						scope : this,
						items : this.otherParam()
					}]
				});
		this.tabPanel.doLayout();
	},// end of initUIs
	// ================begin 表单设置tab======================
	/**
	 * 通过配置 加载表单使用的是什么模板
	 * 
	 * @return {}
	 */
	loadFromSet : function() {
		// 选择使用的表单模板
		this.useTemplateCheckbox = new Ext.form.Checkbox({
					boxLabel : '使用表单模板',
					scope : this,
					handler : this.setExtTemplate
				});
		// 表单设置
		this.formSetPanel = new Ext.Panel({
					title : '表单设置',
					border : false,
					autoScroll : true,
					layout : 'form',
					tbar : [this.useTemplateCheckbox, '-', {
								text : '设置流程表单',
								iconCls : 'btn-setting',
								scope : this,
								handler : this.formSetting
							}, {
								text : '设置表单字段权限',
								scope : this,
								iconCls : 'btn-setting',
								handler : this.setFormRights
							}],
					items : [
					// this.formPanel
					]
				});
		// ajax加载表单设置
		Ext.Ajax.request({
					url : __ctxPath + '/flow/formTempProDefinition.do',
					params : {
						defId : this.defId
					},
					scope : this,
					success : function(resp, options) {
						var result = Ext.decode(resp.responseText);
						this.mappingId = result.mappingId;
						this.formSetPanel.removeAll();
						if (result != null && result.isUseTemplate == 1) {// 使用Ext模板
							this.isTempCheck = true;
							this.useTemplateCheckbox.setValue(true);
						} else {
							this.isTempCheck = false;
							this.formSetPanel.add(this.getFormPanel.call(this));
						}
						this.formSetPanel.doLayout();
					},
					failure : function(resp, options) {
					}
				});
		return this.formSetPanel;
	},
	/**
	 * 获取表单设置panel
	 * 
	 * @return {}
	 */
	getFormPanel : function() {
		// 表单设置panel
		this.formPanel = new Ext.FormPanel({
					autoLoad : {
						url : __ctxPath + '/flow/getProcessActivity.do?defId='
								+ this.defId,
						scope : this,
						callback : this.getFormHtmlCallback
					}
				});
		return this.formPanel;
	},
	/**
	 * 构造按模板设置的表单面板，方便为流程的每个任务设置表单
	 */
	getFormTemplateGrid : function() {
		// 表单模板Panel
		this.formTempGridPanel = new HT.EditorGridPanel({
			clicksToEdit : 1,
			autoHeight : true,
			showPaging : false,
			rowActions : true,
			isShowTbar : false,
			url : __ctxPath + "/flow/mappingsFormTemplate.do?mappingId="
					+ this.mappingId,
			fields : ['templateId', 'mappingId', 'nodeName', 'formUrl',
					'tempType'],
			columns : [{
						header : 'templateId',
						dataIndex : 'templateId',
						hidden : true
					}, {
						header : '表单名称',
						dataIndex : 'nodeName',
						width : 300
					}, {
						header : '模板类型',
						dataIndex : 'tempType',
						width : 100,
						renderer : function(val) {
							if (val == 2) {
								return 'URL模板';
							} else {
								return 'EXT模板';
							}
						},
						editor : new Ext.form.ComboBox({
									valueField : 'id',
									displayField : 'name',
									store : [['1', 'EXT模板'], ['2', 'URL模板']],
									triggerAction : 'all',
									editable : false
								})
					}, {
						header : 'URL',
						dataIndex : 'formUrl',
						width : 250,
						editor : new Ext.form.TextField()
					}, new Ext.ux.grid.RowActions({
								header : '管理',
								width : 100,
								actions : [{
											iconCls : 'btn-form-design',
											qtip : '设计表单',
											style : 'margin:0 3px 0 3px',
											fn : function(rs) {
												if (rs.data.templateId != null) {
													return true;
												}
												return false;
											}
										}, {
											iconCls : 'btn-form-tag',
											qtip : '设置表单源码',
											style : 'margin:0 3px 0 3px',
											fn : function(rs) {
												if (rs.data.templateId != null) {
													return true;
												}
												return false;
											}
										}],
								listeners : {
									scope : this,
									'action' : this.onRowAction
								}
							})]
		});
		return this.formTempGridPanel;
	},
	/**
	 * 
	 * @param {}
	 *            grid
	 * @param {}
	 *            record
	 * @param {}
	 *            action
	 * @param {}
	 *            row
	 * @param {}
	 *            col
	 */
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-form-design' :
				this.taskFormDesigner.call(this, record);
				break;
			case 'btn-form-tag' :
				this.vmEditor.call(this, record);
				break;
			default :
				break;
		}
	},
	/**
	 * 流程表单设计器
	 * 
	 * @param {}
	 *            record
	 */
	taskFormDesigner : function(record) {
		var designWin = new FormDesignWindow({
					defId : this.defId,
					templateId : record.data.templateId,
					activityName : record.data.nodeName
				});
		designWin.show();
	},
	/**
	 * VM编辑
	 * 
	 * @param {}
	 *            record
	 */
	vmEditor : function(record) {
		var vmEditorWin = new FormEditorWindow({
					defId : this.defId,
					activityName : record.data.nodeName
				});
		vmEditorWin.show();
	},
	/**
	 * 使用Ext模板以支持
	 */
	setExtTemplate : function(ck, checked) {
		var items = this.formSetPanel.getTopToolbar().items;
		for (var i = 0; i < items.getCount(); i++) {
			if (i > 0) {
				var it = items.get(i);
				if (checked && it.hide) {
					it.hide();
				} else if (it.show) {
					it.show();
				}
			}
		}

		// 通过defId取得deployId及version，从而映射
		Ext.Ajax.request({
					url : __ctxPath + '/flow/saveFmProDefinition.do?defId='
							+ this.defId,
					params : {
						useTemplate : checked
					},
					scope : this,
					success : function(resp, options) {
						var result = Ext.decode(resp.responseText);
						this.mappingId = result.mappingId;
						this.formSetPanel.removeAll();
						if (checked) {
							this.saveTemplateBtn = new Ext.Button({
										iconCls : 'btn-save',
										text : '保存模板设置',
										scope : this,
										handler : this.saveFormTemplate
									});
							this.formSetPanel.getTopToolbar().insert(1,
									this.saveTemplateBtn);
							this.formSetPanel.add(this.getFormTemplateGrid
									.call(this));
						} else {
							if (this.saveTemplateBtn) {
								this.formSetPanel.getTopToolbar()
										.remove(this.saveTemplateBtn);
							}
							this.formSetPanel.add(this.getFormPanel.call(this));
						}
						this.formSetPanel.doLayout();
					}
				});
	},
	/**
	 * 保存模表单模板
	 */
	saveFormTemplate : function() {

		var store = this.formTempGridPanel.getStore();
		var formTemps = [];
		for (var i = 0; i < store.getCount(); i++) {
			formTemps.push(store.getAt(i).data);
		}

		// 保存表单模板
		Ext.Ajax.request({
					url : __ctxPath + '/flow/saveListFormTemplate.do',
					method : 'post',
					params : {
						formTemps : Ext.encode(formTemps)
					},
					scope : this,
					success : function(resp, options) {
						store.commitChanges();
						Ext.ux.Toast.msg('操作信息', '成功保存表单设置！');
					}
				});
	},
	/**
	 * 设置表单字段权限
	 */
	setFormRights : function() {
		var defId = this.defId;
		Ext.Ajax.request({
					url : __ctxPath + '/flow/checkFieldRights.do',
					method : 'post',
					params : {
						defId : defId
					},
					success : function(response, op) {
						var res = Ext.util.JSON.decode(response.responseText);
						if (res.success) {
							new FieldRightsForm({
										defId : defId
									}).show();
						} else {
							Ext.ux.Toast.msg('操作提示', res.msg == null
											? '未绑定表单！'
											: res.msg);
						}
					},
					failure : function() {
					}

				});

	},
	/**
	 * 表单设置
	 */
	formSetting : function() {
		new FormDefDialog({
					scope : this,
					callback : function(formDefId, formTitle) {
						if (formDefId != null) {
							this.save(this, this.defId, formDefId);
						}
					}
				}).show();

	},
	// 添加操作【设置表单数据】
	save : function(obj, defId, formDefId) {
		Ext.Ajax.request({
					url : __ctxPath + '/flow/addFormDef.do?defId=' + defId
							+ '&formDefId=' + formDefId,
					method : 'post',
					waitMsg : '数据正在提交，请稍候...',
					success : function(response, options) {
						var res = Ext.util.JSON.decode(response.responseText);
						if (res.success) {
							Ext.ux.Toast.msg('操作提示', '设置流程表单操作成功！');
							var panel = obj.formPanel;
							panel.getUpdater().update({
								url : __ctxPath
										+ '/flow/getProcessActivity.do?defId='
										+ defId,
								scope : obj,
								callback : obj.getFormHtmlCallback
							});
						} else
							Ext.ux.Toast.msg('操作提示', res.msg);
					}
				});
	},
	getFormHtmlCallback : function() {
		var form = this.formPanel.getForm().getEl().dom;
		var formPanel = this.formPanel;
		var fElements = form.elements
				|| (document.forms[form] || Ext.getDom(form)).elements;
		try {
			// AppUtil
			$converDetail.call(this, null);
		} catch (e) {
			// alert(e);
		}
	},
	// ================end 表单设置tab======================
	/**
	 * 其它参数设置
	 * 
	 * @return {}
	 */
	otherParam : function() {
		this.otherParamForm = new Ext.FormPanel({
					bodyStyle : 'padding : 5px;',
					defaults : {
						anchor : '95%,95%',
						allowBlank : false,
						selectOnFocus : true,
						msgTarget : 'side'
					},
					defaultType : 'textfield',
					tbar : [{
								text : '保存',
								iconCls : 'btn-save',
								scope : this,
								handler : this.otherParamSave
							}],
					items : [{
								name : 'proDefinition.defId',
								xtype : 'hidden',
								value : this.defId == null ? '' : this.defId
							}, {
								fieldLabel : '跳过第一个任务',
								name : 'proDefinition.skipFirstNode',
								xtype : 'checkbox',
								inputValue : 1
							}]
				});
		this.otherParamForm.loadData({
					scope : this,
					url : __ctxPath + '/flow/getProDefinition.do?defId='
							+ this.defId,
					root : 'data',
					preName : 'proDefinition'
				});

		return this.otherParamForm;
	},
	/**
	 * 保存其它参数
	 */
	otherParamSave : function() {
		$postForm({
					formPanel : this.otherParamForm,
					scope : this,
					url : __ctxPath + '/flow/saveParamProDefinition.do'
				});
	}
});// end of class extend
// TODO
/**
 * 人员设置
 * 
 * @class
 * @extends Ext.Panel
 */
var ProDefinitionUserSetting = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		this.initUIs();
		ProDefinitionUserSetting.superclass.constructor.call(this, {
					// id : 'ProDefinitionUserSetting',
					region : 'center',
					//title : '人员设置',
					autoHeight : true,
					autoWidth : true,
					border :false,
					items : [
						//this.userSetingPanel
					],
					listeners : {
						scope : this,
						'render' : this.renderItems
					}
				});
		// debugger;
	},
	/**
	 * 初始化组件
	 */
	initUIs:function(){
		this.userSetingPanel = new Ext.Panel({
				title : '人员设置',
				autoHeight : true,
				autoWidth : true,
				border :false
		});
	},
	/**
	 * 渲染组件
	 */
	renderItems : function() {
		Ext.Ajax.request({
					url : __ctxPath + '/flow/nodeSetListProNodeSet.do',
					params : {
						defId : this.defId
					},
					scope : this,
					success : function(resp, options) {
						result = Ext.decode(resp.responseText);
						this.userSetingPanel.removeAll();
						if (!Ext.isEmpty(result)) {
							for (var key in result) {
								var name = result[key].nodeName;
								if (!Ext.isEmpty(name)) {
									var userSetFieldSet = this
											.getUserSetFieldSet(result[key]);
									this.userSetingPanel.add(userSetFieldSet);
								}
							}
						}
						this.removeAll();
						this.add(this.userSetingPanel);
						this.doLayout();
					}
				});
	},

	/*
	 * 人员设置FieldSet
	 * 
	 * @param {} proNodeSet 当前节点 @return {}
	 */
	getUserSetFieldSet : function(proNodeSet) {
		var fieldSet = new Ext.form.FieldSet({
					title : proNodeSet.nodeName,
					autoHeight : true,
					collapsible : true,
					items : [this.getUserSetGridPanel(proNodeSet)]
				});
		return fieldSet;
	},
	/**
	 * 人员设置GriddPanel
	 * 
	 * @param {}
	 *            proNodeSet 当前节点人员设置
	 * @return {}
	 */
	getUserSetGridPanel : function(proNodeSet) {
		var row = 0;
		var me = this;
		// 人员设置增加
		this.userSetAdd = function() {
			var proUserSetForm = new ProUserSetForm({
						defId : proNodeSet.defId,
						deployId : proNodeSet.deployId,
						nodeName : proNodeSet.nodeName,
						jbpmDefId : proNodeSet.jbpmDefId,
						scope : this,
						callback : function() {
							userSetGridPanel.getStore().reload();
						}
					}).show();
		};
		// 人员设置删除
		this.userSetDel = function() {
			$delGridRs({
						url : __ctxPath + '/flow/multiDelProUserSet.do',
						grid : userSetGridPanel,
						idName : 'id'
					});
		};
		// 人员设置保存
		this.userSetSave = function() {
			var store = userSetGridPanel.getStore();
			var params = [];

			for (var i = 0; i < store.getCount(); i += 1) {
				var record = store.getAt(i);
				// if (record.dirty) // 得到所有修改过的数据
				params.push(record.data);
			}
			if (params.length == 0) {
				Ext.ux.Toast.msg('信息', '没有对数据进行任何更改');
				return;
			}

			Ext.Ajax.request({
						method : 'post',
						url : __ctxPath + '/flow/mulSaveProUserSet.do',
						params : {
							data : Ext.encode(params)
						},
						success : function(request) {
							Ext.ux.Toast.msg('操作信息', '成功保存');
							store.reload();
							userSetGridPanel.getView().refresh();
						},
						failure : function(request) {
							Ext.ux.Toast.msg('操作信息', '保存出错，请联系管理员!');
						}
					});
		};
		// 用户类型渲染器
		this.userTypeRenderer = function(value) {
			switch (value) {
				case 1 :
					return '发起人';
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
				case 7 :
					return '发起人上下级';
				case 8:
				    return '发起人部门上下级';
				case 9:
					return '部门';
				default :
					return '';
			}
		};
		// 用户类型编辑器
		this.userTypeEditor = new Ext.form.ComboBox({
					allowBlank : false,
					editable : false,
					mode : 'local',
					triggerAction : 'all',
					store : [[1, '发起人'], [2, '用户'], [3, '角色'], [4, '岗位'],
							[5, '部门岗位'], [6, '部门负责人'], [7, '发起人上下级'],[8,'发起人部门上下级'],[9,'部门']],
					listeners : {
						scope : this,
						'change' : function(field, newValue, oldValue) {
							var store = userSetGridPanel.getStore();
							var record = store.getAt(row);
							if (newValue != oldValue) {
								this.setUserInfo(record, '', '');
							}
							userSetGridPanel.stopEditing();
						}
					}
				});
		// 用户来自编辑器
		this.unamesEditor = new Ext.form.TriggerField({
					triggerClass : 'x-form-browse-trigger',
					editable : false,
					scope : this,
					allowBlank : false,
					scope : this,
					onTriggerClick : function(e) {
						var store = userSetGridPanel.getStore();
						var record = store.getAt(row);
						var userType = record.get('userType');
						var uids = record.get('uids');
						var unames = record.get('unames');
						// alert(me.setUserInfo);
						switch (userType) {
							case 1 : // 发起人
								me.setUserInfo(record, '__start', '[发起人]');
								break;
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
							case 7 :// 发起人上下级
								me.reJobSelector(record, uids, unames);
							case 8://发起人部门上下级
				    			me.reOrgSelector(record,uids,unames);
								break;
							case 9://部门
								me.depSelector(record, uids, unames);
							default :
								break;
						}
						userSetGridPanel.stopEditing();
					}
				});
		/**
		 * 对应的地方设置值
		 */
		this.setUserInfo = function(record, ids, names,strategy) {
			record.set('uids', ids);
			record.set('unames', names);
			if(strategy)
				record.set('strategy',strategy);
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
		 * 6.部门负责人||部门
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
		
		this.reOrgSelector=function(record,uids, unames){
			var strategy=record.get('strategy');
			//var demId=record.get('demId');
				new ReOrgDialog({				
							scope : this,
							single : false,
							reOrgId : uids,
							//demId:demId,
							reOrgstrategy:strategy,
							// posUserFlag:
							// record.get('posUserFlag')==null||record.get('posUserFlag')==''?0:record.get('posUserFlag'),
							callback : function(ids, names,retuenDemId,returnStrategy) {
								this.setUserInfo(record,ids, names,returnStrategy);
							}
						}).show();
			};

		/**
		 * 用户设置GridPanel
		 */
		var userSetGridPanel = new HT.EditorGridPanel({
					autoHeight : true,
					bbar : null,
					rowActions : true,// 使用RowActions
					tbar : [{
								text : '添加',
								iconCls : 'btn-add',
								scope : this,
								handler : this.userSetAdd
							}, '-', {
								text : '删除',
								iconCls : 'btn-del',
								scope : this,
								handler : this.userSetDel
							}, '-', {
								text : '节点设置',
								iconCls : 'btn-setting',
								scope : this,
								handler : this.nodeSetting
										.createCallback(proNodeSet)
							}, '-', {
								text : '保存',
								iconCls : 'btn-save',
								scope : this,
								handler : this.userSetSave
							}],
					url : __ctxPath + "/flow/userSetListProUserSet.do?setId="
							+ proNodeSet.setId,
					fields : ['id', 'defId', 'deployId', 'nodeName',
							'userType', 'uids', 'unames', 'compType','strategy'],
					columns : [{
								header : '用户类型',
								dataIndex : 'userType',
								renderer : this.userTypeRenderer,
								editor : this.userTypeEditor
							}, {
								header : '用户Id',
								dataIndex : 'uids',
								hidden : true
							}, {
								header : '用户来自',
								dataIndex : 'unames',
								width : 300,
								scope : this,
								editor : this.unamesEditor
							}, {
								header : '运算类型',
								dataIndex : 'compType',
								width : 100,
								renderer : function(value) {
									switch (value) {
										case 1 :
											return '或运算';
										case 2 :
											return '与运算';
										case 3 :
											return '排除';
										default :
											return '';
									}
								},
								editor : new Ext.form.ComboBox({
											allowBlank : false,
											editable : false,
											mode : 'local',
											triggerAction : 'all',
											store : [[1, '或运算'], [2, '与运算'],
													[3, '排除']]
										})
							}, new Ext.ux.grid.RowActions({
										header : '管理',
										width : 80,
										actions : [{
													iconCls : 'btn-last',
													qtip : '向下',
													style : 'margin:0 3px 0 3px'
												}, {
													iconCls : 'btn-up',
													qtip : '向上',
													style : 'margin:0 3px 0 3px'
												}],
										listeners : {
											scope : this,
											'action' : this.onRowAction
										}
									})],
					// 行选择监听
					listeners : {
						scope : this,
						'cellclick' : function(grid, rowIndex, columnIndex, e) {
							row = rowIndex;
						}
					}
				});
		return userSetGridPanel;
	},
	/**
	 * 节点设置
	 * 
	 * @param {}
	 *            setId 当前节点
	 */
	nodeSetting : function(proNodeSet) {
		new ProNodeSetForm({
					setId : proNodeSet.setId,
					nodeName : proNodeSet.nodeName
				}).show();
	},
	/**
	 * 人员设置 管理-》排序
	 */
	onRowAction : function(grid, record, action, row, col) {
		var store = grid.getStore();
		switch (action) {
			case 'btn-up' :
				if (row == 0) {
					Ext.ux.Toast.msg('操作信息', '已经为第一条!');
					return;
				}

				var rd1 = store.getAt(row - 1);
				var rd2 = store.getAt(row);
				store.removeAt(row);
				store.removeAt(row - 1);
				store.insert(row - 1, rd2);
				store.insert(row, rd1);
				break;
			case 'btn-last' :
				if (row == store.getCount() - 1) {
					Ext.ux.Toast.msg('操作信息', '已经为最后一条!');
					return;
				}
				var rd1 = store.getAt(row);
				var rd2 = store.getAt(row + 1);

				store.removeAt(row + 1);
				store.removeAt(row);

				store.insert(row, rd2);
				store.insert(row + 1, rd1);

				break;
			default :
				break;
		}
	}
});

/**
 * //TODO 流程定义历史记录
 * 
 * @class
 * @extends Ext.Panel
 */
var ProDefinitionHistory = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		this.initUI();
		ProDefinitionHistory.superclass.constructor.call(this, {
					id : 'ProDefinitionHistory',
					// title : '流程定义历史版本',
					region : 'center',
					autoHeight : true,
					autoWidth : true,
					items : [this.historyGridPanel]
				});
	},
	// 初始化组件
	initUI : function() {
		this.historyGridPanel = new HT.GridPanel({
					title : '历史版本',
					region : 'center',
					autoHeight : true,
					autoWidth : true,
					// 使用RowActions
					rowActions : true,
					url : __ctxPath + "/flow/historyProDefinition.do?parentId="
							+ this.defId,
					fields : ['defId', 'proType', 'name', 'createtime',
							'description', 'deployId', 'newVersion', 'status',
							'drawDefXml','pdId'],
					sort : [{
								field : "newVersion",
								direction : "ASC"
							}],
					columns : [{
								header : '分类名称',
								dataIndex : 'proType',
								sortable : true,
								renderer : function(value) {
									if (value != null)
										return value.typeName;
									else
										return '<font color=\'red\'>未定义</font>';
								}
							}, {
								header : '流程的名称',
								dataIndex : 'name',
								sortable : true
							}, {
								header : '描述',
								dataIndex : 'description'
							}, {
								header : '创建时间',
								dataIndex : 'createtime',
								sortable : true
							}, {
								header : '工作流id',
								dataIndex : 'deployId',
								hidden : 'true'
							}, {
								header : '版本号',
								dataIndex : 'newVersion'
								// sortable : true
						}	, {
								header : '状态',
								dataIndex : 'status',
								sortable : true,
								renderer : function(value) {
									if (value != null && value == 1) {
										return '<font color="green">激活</font>';
									} else {
										return '<font color="red">禁用</font>';
									}
								}
							}, new Ext.ux.grid.RowActions({
										header : '管理',
										width : 200,
										actions : [{
											iconCls : 'btn-del',
											qtip : '删除',
											style : 'margin:0 2px 0 2px',
											fn : function(record) {
												if (isGranted('_FlowDel')
														&& record.data.isDefault != 1)
													return true;
												return false;
											}
										}, {
											iconCls : 'btn-detail',
											qtip : '查看',
											style : 'margin: 0 2px 0 2px',
											fn : function(record) {
												if (record.data.deployId != null
														&& isGranted('_FlowQuery'))
													return true;
												return false;
											}
										}],
										listeners : {
											scope : this,
											'action' : this.onRowAction
										}
									})]
				});
	},
	/**
	 * 行的Action
	 * 
	 * @param grid
	 * @param record
	 * @param action
	 * @param row
	 * @param col
	 */
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.removeRs.call(this, record.data.defId);
				break;
			case 'btn-detail' :
				this.detailRs.call(this, record.data.defId, record.data.name,record.data.pdId);
				break;
			default :
				break;
		}
	},
	/**
	 * 刷新gridPanel
	 */
	reloadGridPanel : function() {
		this.historyGridPanel.getStore().reload();
	},
	/**
	 * 删除历史流程定义
	 * 
	 * @param {}
	 *            defId
	 */
	removeRs : function(defId) {
		$postDel({
					msg : '注意：删除该流程定义，该流程下的所有相关数据也一并删除，</br>并不能恢复，您确认要删除该记录吗？',
					url : __ctxPath + '/flow/multiDelProDefinition.do',
					ids : defId,
					scope : this,
					callback : this.reloadGridPanel
				});
	},
	/**
	 * 查看历史明细
	 */
	detailRs : function(defId, name,pdId) {
//		var tab = Ext.getCmp('centerTabPanel');
//		tab.remove('ProDefinitionSetting_' + this.defId);
		App.clickTopTab('ProDefinitionSetting_' + defId, {
					defId : defId,
					name : name,
					title : '流程历史- '+pdId
				});
	}
});

/**
 * 点击流程图上的任务节点
 * 
 * @param {}
 *            defId
 * @param {}
 *            activityName
 */
ProDefinitionSetting.clickNode = function(defId, activityName, nodeType) {
	var tempCheck = false;
	Ext.Ajax.request({
				url : __ctxPath + '/flow/formTempProDefinition.do',
				params : {
					defId : defId
				},
				scope : this,
				success : function(resp, options) {
					var result = Ext.decode(resp.responseText);
					if (result != null && result.isUseTemplate == 1) {// 使用Ext模板
						tempCheck = true;
					}
					$ImportSimpleJs(['/js/flow/ProcessNodeSetting.js'],
							function() {
								new ProcessNodeSetting({
											defId : defId,
											activityName : activityName,
											nodeType : nodeType,
											tempCheck : tempCheck
										}).show();
							}, this);
				},
				failure : function(resp, options) {
				}
			});

};
