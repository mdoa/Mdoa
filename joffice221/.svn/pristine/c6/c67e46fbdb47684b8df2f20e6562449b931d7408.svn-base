/**
 * @author:
 * @class SectionView
 * @extends Ext.Panel
 * @description 栏目管理
 * @company 杭州梦德软件有限公司
 * @createtime:
 */
SectionView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		SectionView.superclass.constructor.call(this, {
					title : '视图管理',
					closable : false,
					id : 'SectionView',
					iconCls : 'menu-section-view',
					layout : 'fit',
					defaults : {
						padding : '0 5 0 0'
					},
					tbar : this.toolbar,
					items : []
				});
	},
	// 初始化组件
	initUIComponents : function() {
		// 顶部按钮组
		this.toolbar = new Ext.Toolbar({
					height : 30,
					items : [{
								iconCls : 'btn-add',
								text : '添加栏目',
								xtype : 'button',
								scope : this,
								handler : this.createRs
							}, '-', {
								iconCls : 'btn-refresh',
								text : '刷新视图',
								xtype : 'button',
								scope : this,
								handler : this.refreshRs
							}, '-', {
								xtype : 'button',
								text : '保存视图',
								scope : this,
								iconCls : 'btn-save',
								handler : this.saveViewRs.createCallback(this)
							}, '-', {
								xtype : 'button',
								text : '添加模块',
								scope : this,
								iconCls : 'btn-add',
								handler : this.addModuleRs
							}, '-', {
								xtype : 'button',
								text : '视图设置',
								iconCls : 'btn-system-setting',
								scope : this,
								handler : this.viewSettingRs
										.createCallback(this)
							}]
				});

		// 视图显示的重新列出
		this.refreshRs.call(this);
	},
	// 创建栏目
	createRs : function() {
		new SectionForm({
					scope : this,
					callback : function(fp, action) {
						var section = action.result.data;
						if (section.sectionType != 1)
							return; // 禁用
						var firstColumn = this.getCmpByName('FirstColumn');
						if (!Ext.isEmpty(section)) {
							firstColumn.add(new Portlet({
										title : section.sectionName,
										sectionId : section.sectionId,
										sectionType : section.sectionType
									}));
							this.doLayout();
						}
						this.refreshRs.call(this);
					}
				}).show();
	},
	/**
	 * 存放列表
	 * 
	 * @param {}
	 *            colNumber 列数
	 * @param {}
	 *            column 存储的列数组
	 * @param {}
	 *            columnType 布局类型
	 * @param {}
	 *            section
	 * @return {}
	 */
	pushColumn : function(colNumber, column, columnType, section) {
		var portlet = new Portlet({
					title : section.sectionName,
					sectionId : section.sectionId,
					sectionType : section.sectionType
				});
		if (columnType == 1) {// 一列显示
			column.push(portlet);
		} else if (columnType == 2) {// 两列显示
			if (section.colNumber == colNumber) {
				column.push(portlet);

			} else if (section.colNumber == 3) {
				if (colNumber == 1) {
					column.push(portlet);
				}
			}
		} else {
			if (section.colNumber == colNumber) {
				column.push(portlet);
			}
		}
		return column;

	},
	/**
	 * 视图显示的列出
	 * 
	 * @param {}
	 *            self
	 */
	refreshRs : function() {
		var self = this;
		this.removeAll();
		this.doLayout();
		var column1 = [];
		var column2 = [];
		var column3 = [];
		Ext.Ajax.request({
					url : __ctxPath + '/info/listSection.do',
					method : 'POST',
					params : {
						'Q_status_SN_EQ' : 1,
						// 状态为激活的栏目
						'sort' : 'rowNumber',
						'dir' : 'ASC'
					},
					scope : this,
					success : function(response) {
						var res = Ext.util.JSON.decode(response.responseText);
						var data = res.result;

						var columnType = res.columnType;

						for (var i = 0; i < res.totalCounts; i++) {
							var section = data[i];
							column1 = this.pushColumn(1, column1, columnType,
									section);
							column2 = this.pushColumn(2, column2, columnType,
									section);
							column3 = this.pushColumn(3, column3, columnType,
									section);

						}

						var _items = null;
						if (columnType == 1) {
							_items = [{
										columnWidth : .98,
										style : 'padding:10px 0 10px 10px',
										name : 'FirstColumn',
										items : column1
									}];
						} else if (columnType == 2) {
							_items = [{
										columnWidth : .64,
										style : 'padding:10px 0 10px 10px',
										name : 'FirstColumn',
										items : column1
									}, {
										columnWidth : .35,
										style : 'padding:10px 10px 10px 10px',
										items : column2
									}];
						} else {
							_items = [{
										columnWidth : .33,
										style : 'padding:10px 0 10px 10px',
										name : 'FirstColumn',
										items : column1
									}, {
										columnWidth : .33,
										style : 'padding:10px 10px 10px 10px',
										items : column2
									}, {
										columnWidth : .33,
										style : 'padding:10px 0px 10px 0px',
										items : column3
									}];
						}
						var item = null
						item = [{
									xtype : 'portal',
									region : 'center',
									border : false,
									id : 'SectionPortal',
									margins : '35 5 5 0',
									items : [_items]
								}];
						this.gridPanel = new Ext.Panel({
									bodyStyle : 'padding:16px 4px 4px 20px',
									autoHeight : true,
									layout : 'form',
									border : false,
									items : [item]
								});
						this.removeAll();
						this.add(this.gridPanel);
						this.doLayout();
					},
					failure : function() {

					}
				})

	},
	/**
	 * 保存视图
	 */
	saveViewRs : function() {
		var Portal = Ext.getCmp('SectionPortal');
		var items = Portal.items;
		var sections = new Array();
		for (var i = 0; i < items.length; i++) {
			var v = items.itemAt(i);
			for (var j = 0; j < v.items.getCount(); j++) {
				var m = v.items.itemAt(j);
				var sectionItem = new SectionItem(m.sectionId, i + 1, j + 1);
				sections.push(sectionItem);
			}
		}
		Ext.MessageBox.show({
				msg : '保存中，请稍候...',
				width : 300,
				wait : true,
				progress : true,
				closable : true,
				waitConfig : {
					interval : 200
				},
				icon : Ext.Msg.INFO
			});
		Ext.Ajax.request({
					method : 'post',
					url : __ctxPath + '/info/positionSection.do',
					params : {
						sections : Ext.encode(sections)
					},
					success : function(request) {
						Ext.MessageBox.hide();
						Ext.ux.Toast.msg('操作信息', '保存成功');
					},
					failure : function(request) {
						Ext.MessageBox.show({
									title : '操作信息',
									msg : '信息保存出错，请联系管理员！',
									buttons : Ext.MessageBox.OK,
									icon : 'ext-mb-error'
								});
					}
				});
		//
	},
	/**
	 * 添加模块
	 */
	addModuleRs : function() {
		var firstColumn = this.getCmpByName('FirstColumn');

		new SectionDialog({
					isSingle : false,
					status : 0,// 取出被禁用的栏目
					scope : this,
					callback : function(sectionId, sectionName, sectionType) {
						var secIds = sectionId.split(',');
						var secNames = sectionName.split(',');
						var secTypes = sectionType.split(',');
						for (var i = 0; i < secIds.length; i++) {
							if (!Ext.isEmpty(secIds[i])) {
								firstColumn.add(new Portlet({
											title : secNames[i],
											sectionId : secIds[i],
											sectionType : secTypes[i]
										}));
							}
						}

						firstColumn.ownerCt.doLayout();
						// 选中的栏目标为激活
						this.enableSection(sectionId);

					}
				}).show();
	},
	/**
	 * 栏目标为激活
	 * 
	 * @param {}
	 *            sectionId
	 */
	enableSection : function(sectionId) {
		Ext.Ajax.request({
					url : __ctxPath + '/info/enableSection.do',
					method : 'POST',
					params : {
						secIds : sectionId
					},
					success : function() {

					},
					failure : function() {

					}
				});
	},
	/**
	 * 视图设置
	 */
	viewSettingRs : function(self) {
		var form = new Ext.FormPanel({
			layout : 'form',
			bodyStyle : 'padding:10px',
			border : false,
			defaults : {
				anchor : '98%,98%'
			},
			items : [{
				xtype : 'radiogroup',
				autoHeight : true,
				columns : 1,
				items : [{
							boxLabel : '一列布局',
							name : 'columnType',
							inputValue : '1'
						}, {
							boxLabel : '两列布局',
							name : 'columnType',
							inputValue : '2'
						}, {
							boxLabel : '三列布局',
							name : 'columnType',
							inputValue : '3'
						}],
				getValue : function() {
					var v;
					if (this.rendered) {
						this.items.each(function(item) {
									if (!item.getValue())
										return true;
									v = item.getRawValue();
									return false;
								});
					} else {
						for (var k in this.items) {
							if (this.items[k].checked) {
								v = this.items[k].inputValue;
								break;
							}
						}
					}
					return v;
				},
				setValue : function(v) {
					if (this.rendered)
						this.items.each(function(item) {
									item.setValue(item.getRawValue() == v);
								});
					else {
						for (var k in this.items) {
							this.items[k].checked = this.items[k].inputValue == v;
						}
					}
				}
			}]
		})
		// 初始化数据
		Ext.Ajax.request({
					url : __ctxPath + '/info/getSysConfigSection.do',
					method : 'POST',
					success : function(response, options) {
						var result = Ext.util.JSON
								.decode(response.responseText);
						form.getCmpByName('columnType')
								.setValue(result.dataValue);
					},
					failure : function() {

					}
				})

		// 视图设置
		var win = new Ext.Window({
					layout : 'fit',
					iconCls : 'btn-system-setting',
					items : form,
					modal : true,
					minHeight : 149,
					minWidth : 499,
					height : 150,
					width : 500,
					maximizable : true,
					title : '视图设置',
					buttonAlign : 'center',
					buttons : [{
						text : '确定',
						iconCls : 'btn-save',
						handler : function() {
							var columnType = form.getCmpByName('columnType')
									.getGroupValue();
							Ext.Ajax.request({
										url : __ctxPath
												+ '/info/columnSection.do',
										method : 'POST',
										params : {
											columnType : columnType
										},
										success : function() {
											Ext.ux.Toast.msg('操作信息', '视图设置成功.');
										},
										failure : function() {

										}
									})
							self.refreshRs.call(self);
							win.close();
						}
					}, {
						text : '取消',
						iconCls : 'btn-cancel',
						handler : function() {
							win.close();
						}
					}]
				});
		win.show();
	}

})