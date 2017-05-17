/**
 * @author:
 * @class ComIndexPage
 * @extends Ext.Panel
 * @description 公司主页
 * @company 杭州梦德软件有限公司
 * @createtime:
 */
ComIndexPage = Ext.extend(Ext.Panel, {
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 初始化组件
				this.initUIComponents();
				// 调用父类构造
				ComIndexPage.superclass.constructor.call(this, {
							title : '公司主页',
							closable : false,
							id : 'ComIndexPage',
							iconCls : 'menu-company',
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
										iconCls : 'btn-refresh',
										text : '刷新视图',
										xtype : 'button',
										scope : this,
										handler : this.refreshRs
												.createCallback(this)
									}]
						});
				// 视图显示的重新列出
				this.refreshRs(this);
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
			refreshRs : function(self) {
				self.removeAll(true);
				self.doLayout();

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
							scope : self,
							success : function(response) {
								var res = Ext.util.JSON
										.decode(response.responseText);
								var data = res.result;

								var columnType = res.columnType;

								for (var i = 0; i < res.totalCounts; i++) {
									var section = data[i];
									column1 = self.pushColumn(1, column1,
											columnType, section);
									column2 = self.pushColumn(2, column2,
											columnType, section);
									column3 = self.pushColumn(3, column3,
											columnType, section);

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

								self.add({
											xtype : 'portal',
											region : 'center',
											border : false,
											id : 'SectionPortal',
											margins : '35 5 5 0',
											items : _items
										});
								self.doLayout();
							},
							failure : function() {

							}
						});
			}
		});