/**
 * 用于扩展一些常用的ExtJs，以简化大量重复性的代码
 */
Ext.ns('HT');
Ext.ns('HT.ux.plugins');

/**
 * 失败消息提示
 * @param {} conf
 */
HT.failureMessage = function(conf){
	if(Ext.isEmpty(conf))
		conf = {title:'操作信息',msg:'出错了，请联系管理员!'};
	
		Ext.MessageBox.show({
					title :conf.title?conf.title:'操作信息',
					msg : conf.msg?conf.msg:'出错了，请联系管理员!',
					buttons : Ext.MessageBox.OK,
					icon : 'ext-mb-error'
				});
};

/**
 * 查找grid的ids
 * 
 * @param {}
 *            grid
 * @param {}
 *            idName
 * @return {}
 */
var $getGdSelectedIds = function(grid, idName) {
	var selRs = grid.getSelectionModel().getSelections();
	var ids = Array();
	for (var i = 0; i < selRs.length; i++) {
		ids.push(eval('selRs[i].data.' + idName));
	}
	return ids;
};

/**
 * 删除所选记录 *
 * 
 * @example
 * 
 * <pre>
 *  使用以下所示：
 * 	$postDel({
 * 		msg : '您确认要删除所选的记录吗？',
 * 		url : __ctxPath + '/admin/multiDelBook.do',
 * 		grid : this.gridPanel,
 * 		ids : this.bookId
 * 	});
 * @param {} conf
 * 
 */
var $postDel = function(conf) {
	var msg = conf.msg == null ? '您确认要删除所选记录吗？' : conf.msg;
	Ext.Msg.confirm('信息确认', msg, function(btn) {
				if (btn == 'yes') {
					Ext.Ajax.request({
								url : conf.url,
								params : {
									ids : conf.ids
								},
								method : 'POST',
								success : function(response, options) {
									var result = Ext.util.JSON
											.decode(response.responseText);
									if (result.success) {
										Ext.ux.Toast.msg('操作信息', '成功删除该记录！');
										if (conf.callback) {
											conf.callback.call(conf.scope);
											return;
										}
										if (conf.grid) {
											conf.grid.getStore().reload();
										}
									} else {
										Ext.ux.Toast
												.msg('操作信息', result.message);
									}

								},
								failure : function(response, options) {
									Ext.ux.Toast.msg('操作信息', '操作出错，请联系管理员！');
								}
							});
				}
			});
};
/**
 * 删除选中的记录
 * 
 * @example
 * 
 * <pre>
 *  使用以下所示：
 * 	$delGridRs({
 * 		msg : '您确认要删除所选的记录吗？',
 * 		url : __ctxPath + '/admin/multiDelBook.do',
 * 		grid : this.gridPanel,
 * 		idName : 'bookId'
 * 	});
 * @param {} conf
 * 
 */
var $delGridRs = function(conf) {
	var ids = $getGdSelectedIds(conf.grid, conf.idName);
	if (ids.length == 0) {
		Ext.ux.Toast.msg("操作信息", "请选择要删除的记录！");
		return;
	}
	var params = {
		msg : conf.msg,
		url : conf.url,
		ids : ids,
		scope : conf.scope,
		grid : conf.grid,
		callback : conf.callback
	};
	// 删除所选记录
	$postDel(params);
};

/**
 * 提交表单
 * 
 * @example
 * 
 * <pre>
 *  使用以下所示：
 * 	$postForm({
 * 	formPanel : this.formPanel,
 * 	scope : this,
 * 	url : __ctxPath + '/admin/saveBook.do',
 * 	params : {},
 * 	callback : function(fp, action) {
 * 		if (this.callback) {
 * 			this.callback.call(this.scope);
 * 		}
 * 		this.close();
 * 	}
 *  });
 * </pre>
 * 
 * @param {}
 *            conf
 */
var $postForm = function(conf) {
	var form =conf.formPanel.getForm();
	if (!form.isValid()) {
		Ext.ux.Toast.msg('操作信息', '请正确填写表单数据！');
		return;
	}
	var scope = conf.scope ? conf.scope : this,
		waitMsg = Ext.isEmpty(conf.waitMsg) ? '正在提交数据...' : conf.waitMsg,
		successMsg = Ext.isEmpty(conf.successMsg)
			? '信息成功保存！'
			: conf.successMsg,
		failureMsg = Ext.isEmpty(conf.failureMsg)
			? '信息保存出错，请联系管理员！'
			: conf.failureMsg,result;
			
	form.submit({
				scope : scope,
				url : conf.url,
				method : 'post',
				params : conf.params,
				waitMsg : waitMsg,
				success : function(fp, action) {
					Ext.ux.Toast.msg('操作信息', successMsg);
					if (conf.callback)
						conf.callback.call(scope, fp, action);
					if (conf.success)
						conf.success.call(scope, fp, action);
				},
				failure : function(fp, action) {
					result = action.result;
					if (!Ext.isEmpty(result) && !Ext.isEmpty(result.msg))
						failureMsg = result.msg;
					HT.failureMessage({msg:failureMsg});
					if (conf.callback)
						conf.callback.call(scope, fp, action);
				}
			});
};

/**
 * 搜索，把查询的Panel提交，并且更新gridPanel的数据，
 * 
 * @example
 * 
 * <pre>
 * 使用以下所示：
 *              $search({
 * 				searchPanel:this.searchPanel,
 * 				gridPanel:this.gridPanel
 * 			});
 * </pre>
 * 
 * @param {}
 *            conf
 */
var $search = function(conf) {
	var searchPanel = conf.searchPanel;
	var gridPanel = conf.gridPanel;
	if (searchPanel.getForm().isValid()) {// 如果合法
		var store = gridPanel.getStore();
		var baseParam = Ext.Ajax.serializeForm(searchPanel.getForm().getEl());
		var deParams = Ext.urlDecode(baseParam);
		Ext.apply(deParams, {
					searchAll : gridPanel.showPaging
							|| gridPanel.showPaging === undefined
							? false
							: true
				});
		deParams.start = 0;
		deParams.limit = store.baseParams.limit;
		var baseParams = store.baseParams ? store.baseParams : {}; // 修改bug
		// baseParams设置的参数没用
		Ext.apply(baseParams, deParams);
		store.baseParams = baseParams;
		gridPanel.getBottomToolbar().moveFirst();
	}
};

/**
 * 搜索Panel
 * 
 * @class HT.SearchPanel
 * @extends Ext.form.FormPanel
 */
HT.SearchPanel = Ext.extend(Ext.form.FormPanel, {
	constructor : function(conf) {
		// 查看其是否允许多行
		var colNums = conf.colNums ? conf.colNums : 1;
		Ext.apply(this, conf);

		if (colNums > 1 && conf.items) {
			this.items = [];
			var row = null;
			var validCnt = 0;
			for (var i = 0; i < conf.items.length; i++) {
				var cmp = conf.items[i];
				if (cmp.xtype != 'hidden') {
					if (validCnt % colNums == 0) {
						row = {
							xtype : 'compositefield',
							fieldLabel : cmp.fieldLabel,
							items : [],
							defaults : {
								style : 'margin:0 0 0 0'
							}
						};
						this.items.push(row);
					} else {
						// 设置分隔符
						var sepr = ":";
						if (this.superclass.labelSeparator) {
							sepr = this.superclass.labelSeparator;
						};
						// 设置label长度
						var width = 100;
						if (this.labelWidth) {
							width = this.labelWidth;
						};
						if (cmp.labelWidth) {
							width = cmp.labelWidth;
						};
						// 设置label左右位置
						var textAlign = 'text-align:left;padding: 3px 3px 3px 0;';
						if ('right' == this.labelAlign) {
							textAlign = 'text-align:right;padding: 3px 3px 3px 0;';
						}

						if (cmp.fieldLabel) {
							row.items.push({
										xtype : 'label',
										width : width,
										style : textAlign,
										text : cmp.fieldLabel + sepr
									});
						}
					}
					row.items.push(cmp);
					validCnt++;
				} else {
					this.items.push(cmp);
				}
			}
		}
		HT.SearchPanel.superclass.constructor.call(this, {
					autoHeight : true,
					border : false,
					style : 'padding:6px;background-color: white',
					buttonAlign : 'center'
				});
	}
});

/**
 * 在分页栏中的导出插件
 * 
 * @class HT.ux.plugins.Export
 * @extends Object
 */
HT.ux.plugins.Export = Ext.extend(Object, {
	constructor : function(config) {
		Ext.apply(this, config);
		HT.ux.plugins.Export.superclass.constructor.call(this, config);
	},

	init : function(pagingToolbar) {
		var excelBtn = new Ext.SplitButton({
			text : '导出',
			iconCls : 'btn-export',
			menu : new Ext.menu.Menu({
				items : [{
					text : '导出当前页EXCEL',
					iconCls : 'btn-export-excel',
					listeners : {
						click : function() {
							var gp;
							if (pagingToolbar.getXType() != 'htgrid') {
								gp = pagingToolbar.findParentBy(function(ct,
												cmp) {
											;
											return (ct instanceof Ext.grid.GridPanel)
													? true
													: false;
										});
							} else {
								gp = pagingToolbar;
							}
							CommonExport(gp, false, 'xls',
									pagingToolbar.searchPanel);
						}
					}
				}, {
					text : '导出全部记录EXCEL',
					iconCls : 'btn-export-excel',
					listeners : {
						click : function() {
							var gp;
							if (pagingToolbar.getXType() != 'htgrid') {
								gp = pagingToolbar.findParentBy(function(ct,
												cmp) {
											;
											return (ct instanceof Ext.grid.GridPanel)
													? true
													: false;
										});
							} else {
								gp = pagingToolbar;
							}
							CommonExport(gp, true, 'xls',
									pagingToolbar.searchPanel);
						}
					}
				}, '-', {
					text : '导出当前页PDF',
					iconCls : 'btn-export-pdf',
					listeners : {
						click : function() {
							var gp;
							if (pagingToolbar.getXType() != 'htgrid') {
								gp = pagingToolbar.findParentBy(function(ct,
												cmp) {
											;
											return (ct instanceof Ext.grid.GridPanel)
													? true
													: false;
										});
							} else {
								gp = pagingToolbar;
							}
							CommonExport(gp, false, 'pdf',
									pagingToolbar.searchPanel);
						}
					}
				}, {
					text : '导出全部记录PDF',
					iconCls : 'btn-export-pdf',
					listeners : {
						click : function() {
							var gp;
							if (pagingToolbar.getXType() != 'htgrid') {
								gp = pagingToolbar.findParentBy(function(ct,
												cmp) {
											;
											return (ct instanceof Ext.grid.GridPanel)
													? true
													: false;
										});
							} else {
								gp = pagingToolbar;
							}
							CommonExport(gp, true, 'pdf',
									pagingToolbar.searchPanel);
						}
					}
				}]
			})
		});

		if (pagingToolbar.getXType() != 'htgrid') {
			pagingToolbar.add('->');
			pagingToolbar.add('-');
			pagingToolbar.add(excelBtn);
			pagingToolbar.add('-');

			pagingToolbar.on({
						beforedestroy : function() {
							excelBtn.destroy();
						}
					});
		} else {
			pagingToolbar.getTopToolbar().add(excelBtn);
		}
	}
});

/**
 * 导出公共方法，在本页下载，不打开另一页 param: gridObj 取得grid isExportAll 是否导出所有 exportType 导出的类型
 */
function CommonExport(gridObj, isExportAll, exportType, searchPanel) {
	var cols;
	if (gridObj.getXType() != 'htgrid') {
		cols = gridObj.getColumnModel().columns;
	} else {
		cols = gridObj.getColumnModel().config;
	}
	var colName = '';
	var colId = '';
	for (var index = 0; index < cols.length; index++) {
		if (!cols[index].isExp) {
			if (cols[index].dataIndex != null && cols[index].dataIndex != ''
					&& cols[index].dataIndex != 'undefined') {
				colName += cols[index].header + ',';
				if (cols[index].javaRenderer != null) {
					colId += "javaRenderer" + cols[index].javaRenderer + ",";
				} else {
					colId += cols[index].dataIndex + ',';
				}
			}
		}
	}

	if (colName.length > 0) {
		colName = colName.substring(0, colName.length - 1);
		colId = colId.substring(0, colId.length - 1);
	}

	// 排序的字段
	var sortInfo = gridObj.store.sortInfo;
	var parasArr = {
		isExport : true,
		isExportAll : isExportAll,
		exportType : exportType,
		colId : colId,
		colName : colName,
		sort : sortInfo + "" != 'undefined' ? sortInfo.field : '',
		dir : sortInfo + "" != 'undefined' ? sortInfo.direction : ''
	};

	Ext.apply(parasArr, gridObj.store.baseParams);

	// 当前页数
	var curPage = gridObj.getBottomToolbar().getPageData().activePage;
	// 当前显示条数
	var limit = gridObj.getBottomToolbar().items.items[13].getValue();
	// 当前开始行
	var start = (curPage - 1) * limit;
	var pageConfig = {
		start : start,
		limit : limit
	};
	Ext.apply(parasArr, pageConfig);

	if (searchPanel && searchPanel.getForm().isValid()) {// 如果合法
		var baseParam = Ext.Ajax.serializeForm(searchPanel.getForm().getEl());
		var deParams = Ext.urlDecode(baseParam);
		Ext.apply(parasArr, deParams);
	}

	var elemIF = document.getElementById('downloadFrame');
	if (!elemIF) {
		elemIF = document.createElement("iframe");
		elemIF.setAttribute('id', 'downloadFrame');
		elemIF.hidden = true;
		document.body.appendChild(elemIF);
	}

	var ifrdoc;
	if (elemIF.contentDocument) {
		ifrdoc = elemIF.contentDocument;
	} else if (elemIF.contentWindow) {
		ifrdoc = elemIF.contentWindow.document;
	} else {
		ifrdoc = elemIF.document;
	}

	if (ifrdoc.document) {
		ifrdoc = ifrdoc.document;
	}
	var body = ifrdoc.body;
	if (!body) {
		ifrdoc
				.write("<head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'></head>");
		body = ifrdoc.createElement('body');
		ifrdoc.appendChild(body);
	}

	var elemForm = ifrdoc.getElementById('downloadForm');

	// if exist then remove
	if (elemForm) {
		ifrdoc.body.removeChild(elemForm);
	}

	// create new form and add new parameters
	elemForm = ifrdoc.createElement("form");
	elemForm.id = 'downloadForm';

	ifrdoc.body.appendChild(elemForm);

	var url = gridObj.store.proxy.url;
	for (var v in parasArr) {
		var elmObj = ifrdoc.createElement("input");
		elmObj.type = "hidden";
		elmObj.name = v;
		elmObj.value = parasArr[v];
		if (parasArr[v] != '') {
			elemForm.appendChild(elmObj);
		}
	}

	elemForm.method = 'post';
	elemForm.action = url;
	elemForm.submit();

};

/**
 * 打印plugin
 * 
 * @class HT.ux.plugins.Print
 * @extends Object
 */
HT.ux.plugins.Print = Ext.extend(Object, {
	constructor : function(config) {
		Ext.apply(this, config);
		HT.ux.plugins.Print.superclass.constructor.call(this, config);
	},

	init : function(pagingToolbar) {

		var printBtn = new Ext.Button({
					text : '打印',
					iconCls : 'btn-print',
					listeners : {
						click : function() {
							if (pagingToolbar.getXType() != 'htgrid') {
								var gp = pagingToolbar.findParentBy(function(
												ct, cmp) {
											;
											return (ct instanceof Ext.grid.GridPanel)
													? true
													: false;
										});
								gpObj = document.getElementById(gp.id);
							} else {
								gpObj = document
										.getElementById(pagingToolbar.id);
							}
							window.open(__ctxPath + '/js/printer/Print.jsp');
						}
					}
				});

		if (pagingToolbar.getXType() != 'htgrid') {
			pagingToolbar.add('->');
			pagingToolbar.add('-');
			pagingToolbar.add(printBtn);

			pagingToolbar.on({
						beforedestroy : function() {
							printBtn.destroy();
						}
					});
		} else {
			pagingToolbar.getTopToolbar().add(printBtn);
		}

	}
});

/**
 * 分页bar
 * 
 * @class HT.PagingBar
 * @extends Ext.PagingToolbar
 */
HT.PagingBar = Ext.extend(Ext.PagingToolbar, {
			constructor : function(conf) {

				var newConf = {
					pageSize : conf.store.baseParams.limit
							? conf.store.baseParams.limit
							: 25,
					displayInfo : true,
					displayMsg : '当前显示从{0}至{1}， 共{2}条记录',
					emptyMsg : '当前没有记录',
					plugins : [new Ext.ux.plugins.PageComboResizer()]
				};

				// 导出
				if (conf.exportable) {
					var expParms = {
						store : conf.store
					};
					if (conf.searchForm) {
						Ext.apply(expParms, {
									searchForm : conf.searchForm
								});
					}
					newConf.plugins.push(new HT.ux.plugins.Export(expParms));
				}

				if (conf.printable) {
					newConf.plugins.push(new HT.ux.plugins.Print());
				}

				Ext.apply(newConf, conf);
				HT.PagingBar.superclass.constructor.call(this, newConf);
			}
		});

/**
 * 处理json数据
 * 
 * @class HT.JsonStore
 * @extends Ext.data.JsonStore
 */
HT.JsonStore = Ext.extend(Ext.data.JsonStore, {
			constructor : function(conf) {
				var baseParams = conf.baseParams ? conf.baseParams : {};
				baseParams.start = 0;
				baseParams.limit = 25;
				var def = {
					baseParams : baseParams,
					root : conf.root ? conf.root : 'result',
					totalProperty : 'totalCounts',
					remoteSort : true
				};
				Ext.applyIf(def, conf);
				HT.JsonStore.superclass.constructor.call(this, def);
			}
		});
/**
 * 根据普通的配置，生成表格所需要的配置
 * 
 * @param {}
 *            conf
 * @return {}
 */
HT.initGridConfig = function(conf) {
	// --store 数据处理 排序
	if (!conf.store) {
		conf.store = new HT.JsonStore({
					url : conf.url,
					fields : conf.fields,
					baseParams : conf.baseParams,
					root : conf.root
				});

		Ext.apply(conf.store.baseParams, {
					searchAll : conf.showPaging
							|| conf.showPaging === undefined ? false : true
				});
		/**
		 * 排序字段
		 * 
		 * <pre>
		 * 例子：sort:[{field: &quot;bookName&quot;, direction: &quot;ASC&quot;}]
		 * 说明：field : String 要排序的字段的字符串。
		 *   direction : String (optional) 排序方向，“ASC”或“DESC”（大小写敏感的，默认为“ASC”）。
		 * </pre>
		 */
		if (conf.sort) {
			conf.store.sort(conf.sort);
		} else if (conf.url) {
			conf.store.load();
		}
	} else {
		Ext.apply(conf.store.baseParams, {
					searchAll : conf.showPaging
							|| conf.showPaging === undefined ? false : true
				});
	}

	// --start grid cell 内容提示展示
	for (var i = 0; i < conf.columns.length; i++) {
		if (!conf.columns[i].renderer) {
			conf.columns[i].renderer = function(data, metadata, record,
					rowIndex, columnIndex, store) {
				if (!Ext.isEmpty(data)) {
					metadata.attr = ' ext:qtip="' + data + '"';
				}
				return data;
			};
		}
		// 动态添加时添加列头
		if (conf.headers) {
			conf.columns[i].header = conf.headers[i];
		}
	}
	// --end

	conf.sm = new Ext.grid.CheckboxSelectionModel({
				singleSelect : conf.singleSelect ? conf.singleSelect : false
			});

	var showChbCol = true;
	if (conf.showChbCol == false) {
		showChbCol = false;
	}

	if (showChbCol) {
		if (conf.columns) {
			conf.columns.unshift(conf.sm);
			conf.columns.unshift(new Ext.grid.RowNumberer());
		} else {
			conf.columns = [conf.sm, new Ext.grid.RowNumberer()];
		}
	}

	if (!conf.tbar && conf.isShowTbar != false) {
		conf.tbar = new Ext.Toolbar();
	}

	if (conf.addTool) {
		conf.tbar.add(new Ext.Button({
					text : '添加记录',
					iconCls : 'btn-add',
					scope : this,
					handler : function() {
						var recordType = conf.store.recordType;
						conf.store.add(new recordType());
					}
				}));
	}

	conf.cm = new Ext.grid.ColumnModel({
				columns : conf.columns,
				defaults : {
					sortable : conf.sortable ? conf.sortable : true,
					menuDisabled : false,
					width : 100
				}
			});
	conf.plugins = [];
	// 加上rowActions
	if (conf.rowActions) {
		var rowActionCol = conf.columns[conf.columns.length - 1];
		conf.plugins.push(rowActionCol);
	}
	// 加上插件 expander
	if (conf.expander) {
		conf.plugins.push(conf.expander);
	}
	// 加上插件 checkColumn
	if (conf.checkColumn) {
		if (Ext.isArray(conf.checkColumn)) {
			for (var i = 0; i < conf.checkColumn.length; i++) {
				conf.plugins.push(conf.checkColumn[i]);
			}
		} else {
			conf.plugins.push(conf.checkColumn);
		}
	}
	// 导出
	if (conf.exportable && !conf.setPagingBar) {
		var expArr = {
			store : conf.store
		};
		if (conf.searchPanel) {
			Ext.apply(expArr, {
						searchPanel : conf.searchPanel
					});
		}
		conf.plugins.push(new HT.ux.plugins.Export(expArr));
	}

	// 把导出功能加在pagingBar
	var pbArr = {
		store : conf.store
	};
	if (conf.showPaging != false) {

		if (conf.exportable && conf.setPagingBar) {
			if (conf.setPagingBar) {
				Ext.apply(pbArr, {
							exportable : true
						});
			}
			if (conf.searchPanel) {
				Ext.apply(pbArr, {
							searchPanel : conf.searchPanel
						});
			}
		}
	} else {
		Ext.apply(pbArr, {
					hidden : true
				});
	}
	var paingBar = new HT.PagingBar(pbArr);

	// 打印
	if (conf.printable) {
		conf.plugins.push(new HT.ux.plugins.Print());
	}

	var def = {
		shim : true,
		trackMouseOver : true,
		disableSelection : false,
		loadMask : true,
		stripeRows : true,
		viewConfig : {
			forceFit : true,
			enableRowBody : false,
			showPreview : false
		},
		bbar : paingBar
	};
	Ext.apply(def, conf);

	return def;
};

/**
 * 
 * 
 * 表格管理控件，包括高级查询，数据导出，分页，排序等
 * 
 * @class HT.GridPanel
 * @extends Ext.grid.GridPanel
 */
HT.GridPanel = Ext.extend(Ext.grid.GridPanel, {
			constructor : function(conf) {
				var def = HT.initGridConfig(conf);
				HT.GridPanel.superclass.constructor.call(this, def);
			}
		});

/**
 * 
 * 
 * 表格编辑管理控件，包括高级查询，数据导出，分页，排序等
 * 
 * @class HT.GridPanel
 * @extends Ext.grid.GridPanel
 */
HT.EditorGridPanel = Ext.extend(Ext.grid.EditorGridPanel, {
			constructor : function(conf) {
				var def = HT.initGridConfig(conf);
				HT.GridPanel.superclass.constructor.call(this, def);
			}
		});

Ext.reg("htgrid", HT.GridPanel);
Ext.reg("hteditorgrid", HT.EditorGridPanel);

/**
 * 初始化数据模板配置
 * 
 * @param {}
 *            conf
 * @return {}
 */
HT.initDataViewConfig = function(conf) {
	if (!conf.store) {

		conf.store = new HT.JsonStore({
					url : conf.url,
					fields : conf.fields,
					baseParams : conf.baseParams,
					root : conf.root
				});
		Ext.apply(conf.store.baseParams, {
					searchAll : conf.showPaging
							|| conf.showPaging === undefined ? false : true
				});
		// 排序字段
		if (conf.sort) {
			conf.store.sort(conf.sort);
		}

		if (conf.url) {
			conf.store.load();
		}
	} else {
		Ext.apply(conf.store.baseParams, {
					searchAll : conf.showPaging
							|| conf.showPaging === undefined ? false : true
				});
	}

	var def = {
		multiSelect : true,
		autoScroll : true
	};
	Ext.apply(def, conf);

	return def;
};
/**
 * 封装数据模板
 */
HT.DataView = Ext.extend(Ext.DataView, {
			// 构造方法
			constructor : function(conf) {
				var def = HT.initDataViewConfig(conf);
				HT.DataView.superclass.constructor.call(this, def);
			}

		});

Ext.reg("htdataview", HT.DataView);
/**
 * 设置一些默认的表单设置
 * 
 * @class HT.FormPanel
 * @extends Ext.form.FormPanel
 */
HT.FormPanel = Ext.extend(Ext.form.FormPanel, {
			constructor : function(conf) {
				var def = {
					layout : 'form',
					bodyStyle : 'padding:5px',
					defaults : {
						anchor : '96%,96%'
					},
					defaultType : 'textfield',
					border : false
				};
				Ext.apply(def, conf);
				HT.FormPanel.superclass.constructor.call(this, def);
			}
		});
/**
 * 通过name和相关配置获取value
 * 
 * @param {}
 *            name
 * @param {}
 *            data
 * @param {}
 *            conf
 * @return {String}
 */
var getValueByName = function(name, data, conf) {
	if (name) {
		if (conf.preName) {
			if (name.indexOf(conf.preName) != -1) {
				name = name.substring(conf.preName.length + 1);
			}
		}
		var val = eval(conf.root + '.' + name);
		if (!Ext.isEmpty(val)) {
			return val;
		}
	}
	return '';

};
/**
 * 遍历该表单下所有的子项控件，并且为它赋值
 * 
 * @param {}
 *            container
 * @param {}
 *            data
 * @param {}
 *            conf
 */
var setByName = function(container, data, conf) {
	var items = container.items;
	if (items != null && items != undefined && items.getCount) {
		for (var i = 0; i < items.getCount(); i++) {
			var comp = items.get(i);
			if (comp.items) {
				setByName(comp, data, conf);
				continue;
			}
			// 判断组件的类型，并且根据组件的名称进行json数据的自动匹配
			var xtype = comp.getXType();
			try {
				if (xtype == 'textfield' || xtype == 'textarea'
						|| xtype == 'radio' || xtype == 'checkbox'
						|| xtype == 'datefield' || xtype == 'hidden'
						|| xtype == 'numberfield' || xtype == 'datetimefield'
						|| xtype == 'timefield' || xtype == 'htmleditor'
						|| xtype == 'ckeditor' || xtype == 'displayfield'
						|| xtype == 'diccombo') {
					var val = getValueByName(comp.getName(), data, conf);
					if(val==''){
						val = comp.getValue();
					}
					comp.setValue(val);
				} else if (xtype == 'combo' || xtype == 'iconcomb') {// 修复下拉赋值bug
					// 赋值显示的值
					comp.valueNotFoundText = getValueByName(comp.name, data,
							conf);
					// 赋值隐藏的值
					comp.setValue(getValueByName(comp.getName(), data, conf));
				} else if (xtype == 'combotree') { // 修复下拉树赋值bug
					// 赋值显示的值
					comp.setValue(getValueByName(comp.name, data, conf));
					// 赋值隐藏的值
					comp.hiddenField.value = getValueByName(comp.hiddenName,
							data, conf);
				}
			} catch (e) {
				// alert(e);
			}
		}
	}
};
/**
 * 重写loadData， 为Form表单设置加载数据,
 * 
 * <pre>
 * 使用方式如下： 
 * 	this.formPanel.loadData({ 
 * 		url:__ctxPath +'/system/getAppRole.do?roleId=' + this.roleId,
 * 		preName:'AppRole',
 * 		root:'data'
 * });
 * </pre>
 */
Ext.override(Ext.Panel, {
			loadData : function(conf) {
				if (!conf.root) {
					conf.root = 'data';
				}
				var me = this;
				if (!this.loadMask) {
					this.loadMask = new Ext.LoadMask(Ext.getBody());
					this.loadMask.show();
				}
				var scope = conf.scope ? conf.scope : this;
				var params = conf.params ? conf.params : {};
				Ext.Ajax.request({
							method : 'POST',
							url : conf.url,
							scope : scope,
							params : params,
							success : function(response, options) {
								var json = Ext.util.JSON
										.decode(response.responseText);
								var data = null;
								if (conf.root) {
									data = eval('json.' + conf.root);
								} else {
									data = json;
								}
								setByName(me, data, conf);
								if (me.loadMask) {
									me.loadMask.hide();
									me.loadMask = null;
								}
								if (conf.success) {
									conf.success.call(scope, response, options);
								}
							},// end of success
							failure : function(response, options) {
								if (me.loadMask) {
									me.loadMask.hide();
									me.loadMask = null;
								}
								if (conf.failure) {
									conf.failure.call(scope, response, options);
								}
							}
						});
			}
		});
/**
 * 修正表单的字段的长度限制的问题
 */
Ext.form.TextField.prototype.size = 20;
Ext.form.TextField.prototype.initValue = function() {
	if (this.value !== undefined) {
		this.setValue(this.value);
	} else if (this.el.dom.value.length > 0) {
		this.setValue(this.el.dom.value);
	}
	this.el.dom.size = this.size;
	if (!isNaN(this.maxLength) && (this.maxLength * 1) > 0
			&& (this.maxLength != Number.MAX_VALUE)) {
		this.el.dom.maxLength = this.maxLength * 1;
	}
};

Ext.override(Ext.Container, {
	getCmpByName : function(name) {
		var getByName = function(container, name) {
			var items = container.items;
			if (items && items.getCount != undefined) {
				for (var i = 0; i < items.getCount(); i++) {
					var comp = items.get(i);
					if (name == comp.name
							|| (comp.getName && name == comp.getName())) {
						return comp;
						break;
					}
					var cp = getByName(comp, name);
					if (cp != null)
						return cp;
				}
			}
			return null;
		};
		return getByName(this, name);
	},
	onResize : function(adjWidth, adjHeight, rawWidth, rawHeight) {
		Ext.Container.superclass.onResize.apply(this, arguments);
		if ((this.rendered && this.layout && this.layout.monitorResize)
				&& !this.suspendLayoutResize) {
			this.layout.onResize();
		}
	},

	canLayout : function() {
		var el = this.getVisibilityEl();
		return el && !el.isStyle("display", "none");
	},

	/**
	 * Force this container's layout to be recalculated. A call to this function
	 * is required after adding a new component to an already rendered
	 * container, or possibly after changing sizing/position properties of child
	 * components.
	 * 
	 * @param {Boolean}
	 *            shallow (optional) True to only calc the layout of this
	 *            component, and let child components auto calc layouts as
	 *            required (defaults to false, which calls doLayout recursively
	 *            for each subcontainer)
	 * @param {Boolean}
	 *            force (optional) True to force a layout to occur, even if the
	 *            item is hidden.
	 * @return {Ext.Container} this
	 */
	doLayout : function(shallow, force) {
		var rendered = this.rendered, forceLayout = force || this.forceLayout, cs, i, len, c;

		if (!this.canLayout() || this.collapsed) {
			this.deferLayout = this.deferLayout || !shallow;
			if (!forceLayout) {
				return;
			}
			shallow = shallow && !this.deferLayout;
		} else {
			delete this.deferLayout;
		}

		cs = (shallow !== true && this.items) ? this.items.items : [];

		// Inhibit child Containers from relaying on resize. We plan to
		// explicitly call doLayout on them all!
		for (i = 0, len = cs.length; i < len; i++) {
			if ((c = cs[i]).layout) {
				c.suspendLayoutResize = true;
			}
		}

		// Tell the layout manager to ensure all child items are rendered, and
		// sized according to their rules.
		// Will not cause the child items to relayout.
		if (rendered && this.layout) {
			this.layout.layout();
		}

		// Lay out all child items
		for (i = 0; i < len; i++) {
			if ((c = cs[i]).doLayout) {
				c.doLayout(false, forceLayout);
			}
		}
		if (rendered) {
			this.onLayout(shallow, forceLayout);
		}
		// Initial layout completed
		this.hasLayout = true;
		delete this.forceLayout;

		// Re-enable child layouts relaying on resize.
		for (i = 0; i < len; i++) {
			if ((c = cs[i]).layout) {
				delete c.suspendLayoutResize;
			}
		}
	}
});

Ext.override(Ext.layout.ContainerLayout, {
			setContainer : function(ct) { // Don't use events!
				this.container = ct;
			}
		});

Ext.override(Ext.BoxComponent, {
			setSize : function(w, h) {
				// support for standard size objects
				if (typeof w == 'object') {
					h = w.height, w = w.width;
				}
				if (Ext.isDefined(w) && Ext.isDefined(this.minWidth)
						&& (w < this.minWidth)) {
					w = this.minWidth;
				}
				if (Ext.isDefined(h) && Ext.isDefined(this.minHeight)
						&& (h < this.minHeight)) {
					h = this.minHeight;
				}
				if (Ext.isDefined(w) && Ext.isDefined(this.maxWidth)
						&& (w > this.maxWidth)) {
					w = this.maxWidth;
				}
				if (Ext.isDefined(h) && Ext.isDefined(this.maxHeight)
						&& (h > this.maxHeight)) {
					h = this.maxHeight;
				}
				// not rendered
				if (!this.boxReady) {
					this.width = w, this.height = h;
					return this;
				}

				// prevent recalcs when not needed
				if (this.cacheSizes !== false && this.lastSize
						&& this.lastSize.width == w
						&& this.lastSize.height == h) {
					return this;
				}
				this.lastSize = {
					width : w,
					height : h
				};
				var adj = this.adjustSize(w, h), aw = adj.width, ah = adj.height, rz;
				if (aw !== undefined || ah !== undefined) { // this code is
					// nasty but
					// performs better
					// with floaters
					rz = this.getResizeEl();
					if (rz != null) {
						if (!this.deferHeight && aw !== undefined
								&& ah !== undefined) {
							rz.setSize(aw, ah);
						} else if (!this.deferHeight && ah !== undefined) {
							rz.setHeight(ah);
						} else if (aw !== undefined) {
							rz.setWidth(aw);
						}
					}
					this.onResize(aw, ah, w, h);
				}
				return this;
			},

			onResize : function(adjWidth, adjHeight, rawWidth, rawHeight) {
				this.fireEvent('resize', this, adjWidth, adjHeight, rawWidth,
						rawHeight);
			}
		});

Ext.override(Ext.Panel, {
			onResize : Ext.Panel.prototype.onResize
					.createSequence(Ext.Container.prototype.onResize)
		});

Ext.override(Ext.Viewport, {
			fireResize : function(w, h) {
				this.onResize(w, h, w, h);
			}
		});
		

HT.ComboBox = Ext.extend(Ext.form.ComboBox, {
			constructor : function(conf) {
				Ext.apply(this, conf);
				HT.ComboBox.superclass.constructor.call(this);
			}
		});
Ext.reg('htcombo', HT.ComboBox);
/**
 * 设置表单值
 * 
 * @param {}
 *            form
 * @param {}
 *            values
 */
setFormValues = function(form, values) {
	var fElements = form.elements
			|| (document.forms[form] || Ext.getDom(form)).elements, encoder = encodeURIComponent, element, options, name, val, data = '', type;

	Ext.each(fElements, function(element) {
				name = element.name;
				type = element.type;

				if (!element.disabled && name) {
					if (/select-(one|multiple)/i.test(type)) {
						Ext.each(element.options, function(opt) {
									if (opt.value == values[name]) {
										opt.selected = true;
									}
								});
					} else if (!/file|undefined|reset|button/i.test(type)) {
						if (!(/radio|checkbox/i.test(type) && !element.checked)
								&& !(type == 'submit')) {
							element.value = values[name];
						}
					}
				}
			});
};

Ext.override(Ext.util.JSON, {
			encode : function() {

			},
			encodeDate : function(o) {
				return '"' + o.getFullYear() + "-" + pad(o.getMonth() + 1)
						+ "-" + pad(o.getDate()) + " " + pad(o.getHours())
						+ ":" + pad(o.getMinutes()) + ":" + pad(o.getSeconds())
						+ '"';
			}
		});

HT.JSON = new (function() {
	var useHasOwn = !!{}.hasOwnProperty, isNative = function() {
		var useNative = null;
		return function() {
			if (useNative === null) {
				useNative = Ext.USE_NATIVE_JSON && window.JSON
						&& JSON.toString() == '[object JSON]';
			}

			return useNative;
		};
	}(), pad = function(n) {
		return n < 10 ? "0" + n : n;
	}, doEncode = function(o) {
		if (!Ext.isDefined(o) || o === null) {
			return "null";
		} else if (Ext.isArray(o)) {
			return encodeArray(o);
		} else if (Ext.isDate(o)) {
			return HT.JSON.encodeDate(o);
		} else if (Ext.isString(o)) {
			return encodeString(o);
		} else if (typeof o == "number") {
			// don't use isNumber here, since finite checks happen inside
			// isNumber
			return isFinite(o) ? String(o) : "null";
		} else if (Ext.isBoolean(o)) {
			return String(o);
		} else {
			var a = ["{"], b, i, v;
			for (var i in o) {
				// don't encode DOM objects
				if (!o.getElementsByTagName) {
					if (!useHasOwn || o.hasOwnProperty(i)) {
						v = o[i];
						switch (typeof v) {
							case "undefined" :
							case "function" :
							case "unknown" :
								break;
							default :
								if (b) {
									a.push(',');
								}
								a.push(doEncode(i), ":", v === null
												? "null"
												: doEncode(v));
								b = true;
						}
					}
				}
			}
			a.push("}");
			return a.join("");
		}
	}, m = {
		"\b" : '\\b',
		"\t" : '\\t',
		"\n" : '\\n',
		"\f" : '\\f',
		"\r" : '\\r',
		'"' : '\\"',
		"\\" : '\\\\'
	}, encodeString = function(s) {
		if (/["\\\x00-\x1f]/.test(s)) {
			return '"' + s.replace(/([\x00-\x1f\\"])/g, function(a, b) {
						var c = m[b];
						if (c) {
							return c;
						}
						c = b.charCodeAt();
						return "\\u00" + Math.floor(c / 16).toString(16)
								+ (c % 16).toString(16);
					}) + '"';
		}
		return '"' + s + '"';
	}, encodeArray = function(o) {
		var a = ["["], b, i, l = o.length, v;
		for (i = 0; i < l; i += 1) {
			v = o[i];
			switch (typeof v) {
				case "undefined" :
				case "function" :
				case "unknown" :
					break;
				default :
					if (b) {
						a.push(',');
					}
					a.push(v === null ? "null" : HT.JSON.encode(v));
					b = true;
			}
		}
		a.push("]");
		return a.join("");
	};

	this.encodeDate = function(o) {
		return '"' + o.getFullYear() + "-" + pad(o.getMonth() + 1) + "-"
				+ pad(o.getDate()) + " " + pad(o.getHours()) + ":"
				+ pad(o.getMinutes()) + ":" + pad(o.getSeconds()) + '"';
	};

	this.encode = function() {
		var ec;
		return function(o) {
			if (!ec) {
				// setup encoding function on first access
				ec = isNative() ? JSON.stringify : doEncode;
			}
			return ec(o);
		};
	}();
})();

HT.encode = HT.JSON.encode;
Ext.useShims = true;

HT.HBoxPanel = Ext.extend(Ext.Panel, {
			constructor : function(conf) {
				var newConf = {
					border : false,
					layoutConfig : {
						padding : '5',
						pack : conf.pack != null ? conf.pack : 'center',
						align : conf.align != null ? conf.align : 'middle'
					},
					defaults : {
						margins : '0 5 0 0'
					},
					layout : 'hbox'
				};

				Ext.apply(newConf, conf);
				HT.HBoxPanel.superclass.constructor.call(this, newConf);
			}
		});

Ext.reg('hboxpanel', HT.HBoxPanel);
Ext.useShims = true;
// Ext.form.CompositeField.prototype.defaults={style:'margin:0 0 0 0'};

/**
 * CompositeField组件在IE下显示完全
 * 
 * @type
 */
Ext.form.CompositeField.prototype.defaults = {
	style : 'margin:0 0 0 0'
};

/**
 * 以下改写grid的cell样式,使得可以选中Cell里的内容,进行复制的操作
 */
if (!Ext.grid.GridView.prototype.templates) {
	Ext.grid.GridView.prototype.templates = {};
}
Ext.grid.GridView.prototype.templates.cell = new Ext.Template(
		'<td class="x-grid3-col x-grid3-cell x-grid3-td-{id} x-selectable {css}"  style="{style}" tabIndex="0" {cellAttr}>',
		'<div class="x-grid3-cell-inner x-grid3-col-{id}" {attr}>{value}</div>',
		'</td>');

/**
 * 把窗口约束到视图范围内
 * 
 * @type Boolean
 */
Ext.Window.prototype.constrain = true;

/**
 * 添加特殊的规则
 * 
 * <pre>
 *  daterange--&gt;
 * 	{	
 * 		fieldLabel : '日期',
 * 		xtype:'datefield',
 * 		id:'startDate',
 * 		vtype : 'daterange',
 * 		endDateField: 'endDate',
 * 		format : 'Y-m-d',
 * },{
 * 		fieldLabel : '至',
 * 		xtype:'datefield',
 * 		id:'endDate',
 * 		vtype : 'daterange'//vtype 验证规则类型
 * 		startDateField: 'startDate',//开始时间的ID
 * 		format : 'Y-m-d',
 * </pre>
 */
Ext.apply(Ext.form.VTypes, {
	/**
	 * *
	 * 
	 * <pre>
	 * daterange--（ showstwo date fields acting as a date range. 
	 * Selecting an initial date sets the minimum value for the end field.
	 *  Selecting an ending date sets a maximum value for the start field.）
	 *  显示两个日期字段作为一个日期范围。选择一个初始的日期设定的结束字段的最小值。选择的结束日期设置的开始字段中的最大值
	 * 	{	
	 * 		fieldLabel : '日期',
	 * 		xtype:'datefield',
	 * 		id:'startDate',
	 * 		vtype : 'daterange',//vtype 验证规则类型
	 * 		endDateField: 'endDate', //ID为结束时间
	 * 		format : 'Y-m-d',
	 * },{
	 * 		fieldLabel : '至',
	 * 		xtype:'datefield',
	 * 		id:'endDate',
	 * 		vtype : 'daterange'//vtype 验证规则类型
	 * 		startDateField: 'startDate',//ID为开始时间
	 * 		format : 'Y-m-d',
	 * </pre>
	 */
	daterange : function(val, field) {
		var date = field.parseDate(val);

		if (!date)
			return false;

		if (field.startDateField) {
			var start = Ext.getCmp(field.startDateField);
			if (!start.maxValue || (date.getTime() != start.maxValue.getTime())) {
				start.setMaxValue(date);
				start.validate();
			}
		} else if (field.endDateField) {
			var end = Ext.getCmp(field.endDateField);
			if (!end.minValue || (date.getTime() != end.minValue.getTime())) {
				end.setMinValue(date);
				end.validate();
			}
		}
		/*
		 * Always return true since we're only using this vtype to set the
		 * min/max allowed values (these are tested for after the vtype test)
		 */
		return true;
	},
	/**
	 * *
	 * 
	 * <pre>
	 * timerange--（ showstwo date fields acting as a date range. 
	 * Selecting an initial date sets the minimum value for the end field.
	 *  Selecting an ending date sets a maximum value for the start field.）
	 *  显示两个日期字段作为一个日期范围。选择一个初始的日期设定的结束字段的最小值。选择的结束日期设置的开始字段中的最大值
	 * 	{	
	 * 		fieldLabel : '时间1',
	 * 		xtype:'datefield',
	 * 		id:'time1',
	 * 		vtype : 'timerange',//vtype 验证规则类型
	 * 		minDateField: 'time2', //ID为结束时间
	 * 		format : 'Y-m-d',
	 * },{
	 * 		fieldLabel : '时间2',
	 * 		xtype:'datefield',
	 * 		id:'time2',
	 * 		vtype : 'timerange'//vtype 验证规则类型
	 * 		startDateField :'time2'
	 * 		endDateField: 'time3',//ID为开始时间
	 * 		format : 'Y-m-d'
	 * },{
	 * 		fieldLabel : '时间3',
	 * 		xtype:'datefield',
	 * 		id:'time3',
	 * 		vtype : 'timerange'//vtype 验证规则类型
	 * 		maxDateField: 'time1',//ID为开始时间
	 * 		format : 'Y-m-d'
	 * }
	 * </pre>
	 */
	timerange : function(val, field) {
		var date = field.parseDate(val);

		if (!date)
			return false;

		if (field.maxDateField) {// 最大时间
			var maxDateField = field.maxDateField;
			if (Ext.isArray(maxDateField)) {
				for (var i = 0; i < maxDateField.length; i++) {// 取最大值
					var maxDate = Ext.getCmp(maxDateField[i]);
					if (!maxDate.maxValue
							|| (date.getTime() != maxDate.maxValue.getTime())) {
						maxDate.setMaxValue(date);
						maxDate.validate();
					}
				}
			}

		} else if (field.minDateField) {// 最小时间
			var minDateField = field.minDateField;
			if (Ext.isArray(minDateField)) {
				for (var i = 0; i < minDateField.length; i++) {// 取最大值
					var minDate = Ext.getCmp(minDateField[i]);
					if (!minDate.minValue
							|| (date.getTime() != minDate.minValue.getTime())) {
						minDate.setMinValue(date);
						minDate.validate();
					}

				}
			}

		}
		return true;
	},

	/**
	 * { fieldLabel: '密码', name: 'password', id: 'password' },{ fieldLabel:
	 * '确认密码', name: 'pass-cfrm', vtype: 'password', initialPassField:
	 * 'password' // id of the initial password field }
	 */
	password : function(val, field) {
		if (field.initialPassField) {
			var pwd = Ext.getCmp(field.initialPassField);
			return (val == pwd.getValue());
		}
		return true;
	},

	passwordText : '密码不匹配，请重新输入！'// Passwords do not match
});

/**
 * 生成流水号
 * 
 * @param {}
 *            conf
 * 
 * <pre>
 *       	$genNumber({
 *            scope :this,//作用域
 *            alias : 'OfficeGoods'//别名，通过这个查找到流水号 、必填
 *            callback:function(scope,number){//回调函数
 *            }
 *            });
 * </pre>
 */
var $genNumber = function(conf) {
	var scope = conf.scope ? conf.scope : this;
	Ext.Ajax.request({
		url : __ctxPath + '/system/genNumberSerialNumber.do',
		params : {
			alias : conf.alias
		},
		scope : scope,
		success : function(response, options) {
			var result = Ext.util.JSON.decode(response.responseText);
			if (Ext.isEmpty(result.number)) {
				Ext.ux.Toast.msg('操作信息', '请到流水号管理设置别名为"' + conf.alias + '"的配置');
				return;
			}
			if (conf.callback) {
				conf.callback.call(scope, result.number);
			}
		},
		failure : function(response, options) {
			if (conf.callback) {
				conf.callback.call(scope, '');
			}
		}
	});
};