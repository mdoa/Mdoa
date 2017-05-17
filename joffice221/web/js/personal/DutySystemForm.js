/**
 * 班制定义列表
 */
DutySystemForm = Ext.extend(Ext.Window, {
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 必须先初始化组件
				this.initUIComponents();
				DutySystemForm.superclass.constructor.call(this, {
							id : 'DutySystemFormWin',
							layout : 'fit',
							items : this.formPanel,
							modal : true,
							height : 230,
							width : 830,
							maximizable : true,
							title : '班制定义详细信息',
							iconCls : 'btn-clock',
							buttonAlign : 'center',
							buttons : [{
										text : '保存',
										iconCls : 'btn-save',
										scope : this,
										handler : this.save
									}, {
										text : '重置',
										iconCls : 'btn-reset',
										scope : this,
										handler : this.reset
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
				this.row = 0;
				this.col = 0;
				var me = this;
				// 班次设置编辑
				this.dutySecEditor = new Ext.form.TriggerField({
							scope : this,
							onTriggerClick : function(e) {
								new DutySectionDialog({
											single : true,
											scope : this,
											callback : function(ids, names) {
												var grid = me.gridPanel;
												var store = grid.getStore();
												var record = store.getAt(0);
												// 计算对应的index值,注意到列头的顺序不能变换
												var columns = me.col / 2;
												record.set('dayId' + columns,
														ids);
												record.set('day' + columns,
														names);
											}
										}).show();
							}
						}, this);
				// 班次设置面板
				this.gridPanel = new HT.EditorGridPanel({
							showChbCol : false,
							region : 'center',
							title : '班次设置',
							height : 100,
							showPaging : false,
							sortable : false,
							url : __ctxPath
									+ '/personal/settingDutySystem.do?systemId='
									+ this.systemId,
							fields : ['day0', 'day1', 'day2', 'day3', 'day4',
									'day5', 'day6', 'dayId0', 'dayId1',
									'dayId2', 'dayId3', 'dayId4', 'dayId5',
									'dayId6'],
							columns : [{
										header : "周日",
										dataIndex : 'day0',
										scope : this,
										editor : this.dutySecEditor
									}, {
										dataIndex : 'dayId0',
										hidden : true
									}, {
										header : "周一",
										dataIndex : 'day1',
										scope : this,
										editor : this.dutySecEditor
									}, {
										dataIndex : 'dayId1',
										hidden : true
									}, {
										header : "周二",
										dataIndex : 'day2',
										scope : this,
										editor : this.dutySecEditor
									}, {
										dataIndex : 'dayId2',
										hidden : true
									}, {
										header : "周三",
										dataIndex : 'day3',
										scope : this,
										editor : this.dutySecEditor
									}, {
										dataIndex : 'dayId3',
										hidden : true
									}, {
										header : "周四",
										dataIndex : 'day4',
										scope : this,
										editor : this.dutySecEditor
									}, {
										dataIndex : 'dayId4',
										hidden : true
									}, {
										header : "周五",
										dataIndex : 'day5',
										scope : this,
										editor : this.dutySecEditor
									}, {
										dataIndex : 'dayId5',
										hidden : true
									}, {
										header : '周六',
										dataIndex : 'day6',
										scope : this,
										editor : this.dutySecEditor
									}, {
										dataIndex : 'dayId6',
										hidden : true
									}],
							// 监听
							listeners : {
								scope : this,
								'cellclick' : this.cellclick
							}
						});

				// 表单
				this.formPanel = new Ext.FormPanel({
							layout : 'form',
							bodyStyle : 'padding:5px;',
							border : false,
							autoScroll : true,
							scope : this,
							defaults : {
								anchor : '98%,98%'
							},
							defaultType : 'textfield',
							items : [{
								name : 'dutySystem.systemId',
								xtype : 'hidden',
								value : this.systemId == null
										? ''
										: this.systemId
							}, {
								fieldLabel : '班制名称',
								name : 'dutySystem.systemName',
								allowBlank : false
							}, {
								xtype : 'radiogroup',
								fieldLabel : '是否缺省',
								autoHeight : true,
								columns : 2,
								items : [{
											boxLabel : '是',
											name : 'dutySystem.isDefault',
											inputValue : 1,
											id : 'isDefault1',
											checked : true
										}, {
											boxLabel : '否',
											name : 'dutySystem.isDefault',
											inputValue : 0,
											id : 'isDefault0'
										}]
							}, this.gridPanel]
						});
				// 加载表单对应的数据
				if (this.systemId != null && this.systemId != 'undefined') {
					this.formPanel.loadData({
								url : __ctxPath
										+ '/personal/getDutySystem.do?systemId='
										+ this.systemId,
								root : 'data',
								preName : 'dutySystem',
								success : function(response, option) {
									var ds = Ext.util.JSON
											.decode(response.responseText).data;
									if (ds.isDefault == 1) {
										Ext.getCmp("isDefault1").setValue(true);
									} else {
										Ext.getCmp("isDefault0").setValue(true);
									}
								},
								failure : function(form, action) {
									Ext.ux.Toast.msg('编辑', '载入失败');
								}
							});
				};

			},// end of the initcomponents

			/**
			 * 重置
			 * 
			 * @param {}
			 *            formPanel
			 */
			reset : function() {
				this.formPanel.getForm().reset();
			},
			/**
			 * 取消
			 * 
			 * @param {}
			 *            window
			 */
			cancel : function() {
				this.close();
			},
			/**
			 * 保存记录
			 */
			save : function() {
				var formPanel = this.formPanel;
				var gridPanel = this.gridPanel;
				var params = [];
				var store = gridPanel.getStore();
				var isValid = true;
				for (i = 0, cnt = store.getCount(); i < cnt; i += 1) {
					var record = store.getAt(i);
					for (var ct = 0; ct < 7; ct++) {
						var ids = record.get('day' + ct);
						if (ids == null || ids == '') {
							isValid = false;
							break;
						}
					}
					params.push(record.data);
				}
				if (!isValid) {
					Ext.Msg.alert('警告', '没有完全设置星期的班次!');
					return;
				}

				$postForm({
							formPanel : formPanel,
							scope : this,
							url : __ctxPath + '/personal/saveDutySystem.do',
							params : {
								data : Ext.encode(params)
							},
							callback : function(fp, action) {
								if (this.callback) {
									this.callback.call(this.scope);
								}
								this.close();
							}
						});
			},
			cellclick : function(grid, rowIndex, columnIndex, e) {
				this.row = rowIndex;
				this.col = columnIndex;
			}
		});