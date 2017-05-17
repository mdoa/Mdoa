/**
 * @author csx
 * @createtime 2010年1月10日
 * @class ArchiveTypeTempView
 * @extends Ext.Panel
 * @description 公文分类及模板管理-公文模板选择
 * @company 宏天软件
 */
ArchTemplateSelector = Ext.extend(Ext.Window, {
			/**
			 * 构造函数
			 */
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 作用域
				this.scope = this.scope ? this.scope : this;
				// 默认为多选择类型
				this.isSingle = this.isSingle == null ? false : this.isSingle;
				this.initUIComponents();
				// 拷贝父类
				ArchTemplateSelector.superclass.constructor.call(this, {
							title : '公文模板选择',
							iconCls : 'menu-archive-template',
							layout : 'border',
							maximizable : true,
							width : 800,
							height : 500,
							modal : true,
							closeAction : 'hide',
							scope : this,
							items : [this.treePanel, this.outPanel],
							buttonAlign : 'center',
							buttons : [{
										text : '选择模板',
										iconCls : 'menu-archive-template',
										scope : this,
										handler : this.selectTemplate
									}, {
										text : '取消',
										iconCls : 'btn-cancel',
										scope : this,
										handler : function() {
											this.close();
										}
									}]
						});// end of the
			},// end of the constructor

			initUIComponents : function() {
				this.catKey = 'ARC_TEM_TYPE';// 公文分类
				// 公文分类树
				this.treePanel = new htsoft.ux.TreePanelEditor({
							layout : 'fit',
							region : 'west',
							collapsible : true,
							split : true,
							width : 200,
							title : '公文模板分类',
							url : __ctxPath
									+ '/system/flowTreeGlobalType.do?catKey='
									+ this.catKey,
							scope : this,
							autoScroll : true,
							// 点击分类树节点
							onclick : this.typeNodeClick
						});

				// 搜索面板
				this.searchPanel = new HT.SearchPanel({
							layout : 'form',
							region : 'north',
							colNums : 3,
							keys : {
								key : Ext.EventObject.ENTER,
								fn : this.search,
								scope : this
							},
							labelWidth : 60,
							items : [{
										fieldLabel : '模板名称',
										xtype : 'textfield',
										name : 'Q_tempName_S_LK',
										maxLength : 150
									}, {
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
										handler : this.resetSearchForm
									}]
						});

				// 列表面板
				this.gridPanel = new HT.GridPanel({
							region : 'center',
							singleSelect : this.isSingle,
							url : __ctxPath + "/archive/listArchTemplate.do",
							fields : [{
										name : 'templateId',
										type : 'int'
									}, 'archivesType', 'archivesType.typeName',
									'tempName', 'tempPath', {
										name : 'fileId',
										mapping : 'fileAttach.fileId'
									}],
							columns : [{
										header : 'templateId',
										dataIndex : 'templateId',
										hidden : true
									}, {
										header : '所属类型',
										dataIndex : 'archivesType.typeName'
									}, {
										header : '模板名称',
										dataIndex : 'tempName'
									}, {
										header : '文件路径',
										hidden : true,
										dataIndex : 'tempPath'
									}]
						});
				this.outPanel = new Ext.Panel({
							region : 'center',
							title : '公文模板',
							layout : 'border',
							items : [this.searchPanel, this.gridPanel]
						});
			},
			/**
			 * 选择模板
			 */
			selectTemplate : function() {
				if (this.callback != null) {
					// 返回模板的名称，路径
					var selectRecords = this.gridPanel.getSelectionModel()
							.getSelections();
					if (selectRecords.length == 0) {
						Ext.ux.Toast.msg("信息", "请选择模板！");
						return;
					}
					if (this.callback) {
						this.callback.call(this.scope,
								selectRecords[0].data.fileId);
					}
					this.close();
				}
			}

		});
