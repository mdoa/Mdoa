/**
 * @author:
 * @class TidyFileView
 * @extends Ext.Panel
 * @description [RollFile]管理
 * @company 杭州梦德软件有限公司
 * @createtime:
 */
TidyFileView = Ext.extend(Ext.Panel, {
	// viewConfig:[],
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		TidyFileView.superclass.constructor.call(this, {
					title : '归档文件管理',
					iconCls : "menu-tidyFile",
					region : 'center',
					layout : 'border',
					id:'TidyFileView',
					buttonAlign : 'center',
					items : [this.searchPanel, this.centerPanel, this.eastPanel],
					listeners : {
						afterrender : function(View) {
							var vW=View.getInnerWidth();
							View.eastPanel.setWidth(vW/2);						
						}
					}
				});
	},// end of constructor
	// 初始化组件
	initUIComponents : function() {
		this.searchPanel = new HT.SearchPanel({
			hidden : true,// 隐藏
			forceLayout : true,// 渲染
			layout : 'form',
			region : 'north',
			colNums : 4,
			items:[{
				fieldLabel : '全宗号',
				name:'TidyFileView.Q_afNo_S_EQ',
				hiddenName : 'Q_archRoll.archFondId_L_EQ',
				flex : 1,
				xtype : 'combo',
				mode : 'local',
				editable : true,
				lazyInit : false,
				forceSelection : false,
				triggerAction : 'all',
				store : new Ext.data.JsonStore({
							url : __ctxPath + "/arch/listArchFond.do",
							autoLoad : true,
							autoShow : true,
							root : 'result',
							fields : ['archFondId', 'afNo']
						}),
				valueField : 'archFondId',
				displayField : 'afNo',
				listeners : {
					scope : this,
					'select' :this.afNoSelect
				}
			},{
				fieldLabel : '案卷号',
				name : 'Q_archRoll.rollNo_S_EQ',
				flex : 1,
				xtype : 'combo',
				mode : 'local',
				editable : true,
				lazyInit : false,
				forceSelection : false,
				triggerAction : 'all',
				store : new Ext.data.JsonStore({
							url : __ctxPath + "/arch/listArchRoll.do",
							autoLoad : true,
							autoShow : true,
							root : 'result',
							idProperty : 'rollId',
							fields : ['rollId', 'rollNo', 'afNo']
						}),
					valueField : 'rollId',
					displayField : 'rollNo'
			},{
				fieldLabel : '文件题名',
				name : 'Q_fileName_S_EQ',
				flex : 1,
				xtype : 'textfield'
			},{
				fieldLabel : '目录号',
				name : 'Q_catNo_S_EQ',
				flex : 1,
				xtype : 'textfield'		
			},{
				fieldLabel : '密级',
				name : 'Q_secretLevel_S_LK',
				flex : 1,
				editable : true,
				lazyInit : false,
				forceSelection : false,
				xtype : 'diccombo',
				nodeKey : 'file_security'
			},{
				fieldLabel : '保管期限',
				name : 'Q_timeLimit_S_LK',
				flex : 1,
				editable : true,
				lazyInit : false,
				forceSelection : false,
				xtype : 'diccombo',
				nodeKey : 'file_time_limit'
			},{
				fieldLabel : '开放形式',
				name : 'Q_openStyle_S_LK',
				flex : 1,
				editable : true,
				lazyInit : false,
				forceSelection : false,
				xtype : 'diccombo',
				nodeKey : 'file_open_form'
			},{
				fieldLabel : '归档状态',
				hiddenName : 'Q_archStatus_SN_EQ',
				flex : 1,
				xtype : 'combo',
				value : '0',
				mode : 'local',
				editable : false,
				triggerAction : 'all',
				store : [['', '全部'], ['0', '未归档'], ['1', '已归档']]
			},{
				fieldLabel : '分类Id',
				name : 'Q_globalType.proTypeId_L_EQ',
				flex : 1,
				xtype : 'hidden'
			}],
			buttons : [{
				text : '查询',
				scope : this,
				iconCls : 'btn-search',
				handler : this.search
				},{
				text : '重置',
				scope : this,
				iconCls : 'btn-reset',
				handler : this.reset
			}]
		});		

		 this.afNo = new Ext.form.ComboBox({
					mode : 'local',
					lazyInit : false,
					forceSelection : true,
					editable : false,
					triggerAction : 'all',
					width : 50,
					store : new Ext.data.JsonStore({
								url : __ctxPath + "/arch/listArchFond.do",
								autoLoad : true,
								autoShow : true,
								root : 'result',
								fields : ['archFondId', 'afNo']
							}),
					valueField : 'archFondId',
					displayField : 'afNo',
					listeners : {
						scope:this,
						'select' : this.comboClick
					}
				});
		 
		this.rollNo = new Ext.form.ComboBox({
					width : 50,
					mode : 'local',
					lazyInit : false,
					forceSelection : true,
					editable : false,
					triggerAction : 'all',
					store : new Ext.data.JsonStore({
								url : __ctxPath + "/arch/listArchRoll.do",
								autoLoad : true,
								autoShow : true,
								root : 'result',
								idProperty : 'rollId',
								fields : ['rollId', 'rollNo', 'afNo']
							}),
					valueField : 'rollId',
					displayField : 'rollNo',
					listeners : {
						'select' : function(combo, record, index) {
								this.searchPanel.getForm()
									.findField('Q_archRoll.rollNo_S_EQ')
									.setValue(record.get('rollId'));
						}
					}
				});
		this.fileRecord = Ext.data.Record.create([{
					name : 'rollFileId',
					type : 'int'
				}, 'afNo', 'createTime', 'creatorName', 'creatorId',
				'archStatus', 'proTypeId', 'typeName', 'openStyle', 'rollNo',
				'fileName', 'fileNo', 'fileNo', 'catNo', 'seqNo', 'pageNo',
				'pageNums', 'secretLevel', 'timeLimit', 'keyWords', 'keyWords',
				'content', 'fileTime', 'notes', 'dutyPerson', 'archRoll',
				'globalType']);
		this.memoryProxy = new Ext.data.HttpProxy({
					url : __ctxPath + "/arch/listRollFile.do"
				});
		this.jsonReader = new Ext.data.JsonReader({
					root : 'result',
					totalProperty : 'totalCounts',
					idProperty : "rollFileId"
				}, this.fileRecord);
		this.mystore = new Ext.data.Store({
					proxy : this.memoryProxy,
					reader : this.jsonReader,
					baseParams : {
						'Q_archStatus_SN_EQ':0
					},
					autoLoad:true
				});	
		
		this.filesGrid = new HT.GridPanel({
					frame : false,
					border : false,
					region : 'center',
					tbar :  [{
						xtype : 'tbtext',
						text : '分类'
					},{
						width:100,
				 		name : 'name',
				 		hiddenName: 'hiddenName',
				 		xtype:'combotree',	
				 		url : __ctxPath+ '/system/treeGlobalType.do?catKey=AR_RLF',
				 		listeners : {
							scope:this,
							'select' : this.comboTreeSelect
						}
				  }, {
						xtype : 'tbtext',
						text : '全宗号'
					}, this.afNo, {
						xtype : 'tbtext',
						text : '案卷号'
					}, this.rollNo, {
						text : '查询',
						scope : this,
						iconCls : 'btn-search',
						handler : this.search
					}, {
						text : '高级查询>>',
						scope : this,
						handler : this.advancedSeach
					}],
					rowActions : true,
					store : this.mystore,
					viewConfig : {
						forceFit : true,
						autoFill : true
					},
					columns : [{
						header : 'rollFileId',
						dataIndex : 'rollFileId',
						sortable : true,
						hidden : true
					}, {
						header : '全宗号',
						sortable : true,
						width : 55,
						dataIndex : 'archRoll',
						renderer : function(archRoll) {
							if (archRoll) {
								return archRoll.archFond.afNo;
							}
						}
					}, {
						header : '案卷号',
						sortable : true,
						width : 55,
						dataIndex : 'archRoll',
						renderer : function(archRoll) {
							if (archRoll)
								return archRoll.rollNo;
						}
					}, {
						header : '文件题名',
						sortable : true,
						dataIndex : 'fileName'
					}, {
						header : '归档状态',
						sortable : true,
						dataIndex : 'archStatus',
						width : 65,
						renderer : function(v) {
							switch (v) {
								case 0 :
									return '未归档';
									break;
								case 1 :
									return '已归档';
									break;
							}
						}
					}, new Ext.ux.grid.RowActions({
						header : '管理',
						width : 55,
						actions : [{
									iconCls : 'btn-readdocument',
									qtip : '预览附件',
									style : 'margin:0 3px 0 3px'
								}, {
									iconCls : 'btn-showDetail',
									qtip : '查看',
									style : 'margin:0 3px 0 3px'
							}],
						listeners : {
							scope : this,
							'action' : this.onRowAction
						}
					})]
				// end of columns
			});
		this.centerPanel = new Ext.Panel({
					title : '卷内文件详细信息',
					region : 'center',
					layout : 'fit',
					frame : false,
					border : true,
					items : [this.filesGrid],
					listeners : {
						scope:this,
						afterlayout : function(centerPanel) {
							var ch = centerPanel.getInnerHeight();
							this.filesGrid.setHeight(ch);
						}
					}
				});
		this.selectPanel = new Ext.Panel({
					frame : true,
					border : false,
					hideBorders : true,
					width : 35,
					region : 'west',
					layout : {
						type : 'vbox',
						pack : 'center',
						align : 'stretch'
					},
					defaults : {
						margins : '0 0 5 0'
					},
					items : [{
								xtype : 'button',
								iconCls : 'btn-down',
								scope : this,
								handler : this.up
							}, {
								xtype : 'button',
								iconCls : 'btn-top',
								scope : this,
								handler : this.down
							}]
				});
		this.tidyFileForm = new TidyFileForm({
					frame : true,
					region : 'center',
					thisWin:this
				}).show();

		this.eastPanel = new Ext.Panel({
					frame : false,
					border : false,
					region : 'east',
					layout : 'border',
					items : [this.selectPanel, this.tidyFileForm]
				});

	},// end of the initComponents()
	/**
	 * 分类选择
	 */
	comboTreeSelect:function(combo, record, index){
		var proTypeId = record.id;
		var typeName = record.text;
		if (proTypeId == '0') {
			this.searchPanel.getForm()
			.findField('Q_globalType.proTypeId_L_EQ').setValue('');
		}else{
			this.searchPanel.getForm()
			.findField('Q_globalType.proTypeId_L_EQ').setValue(proTypeId);
		}
		this.search();
	},
	/**
	 * 高级查询
	 */
	advancedSeach: function() {
		if(this.searchPanel.isVisible()){
			this.searchPanel.setVisible(false);
			this.doLayout(true,true);
		}
		else {
			this.searchPanel.setVisible(true);
			this.doLayout(true,true);							
		}
	},
	/**
	 * 高级查询全宗号选择
	 */
	afNoSelect:function(combo, record, index) {
		this.getCmpByName('Q_archRoll.rollNo_S_EQ').getStore()
				.load({
					params : {
						'Q_archFond.archFondId_L_EQ' : record.data.archFondId
					}
				});
		this.getCmpByName('Q_archRoll.rollNo_S_EQ').reset();
	},
	/**
	 * afNo选择处理函数
	 */
	comboClick:function(combo, record, index) {
		this.searchPanel.getForm()
				.findField('TidyFileView.Q_afNo_S_EQ').setValue(record
						.get('archFondId'));
		this.rollNo.getStore().load({
				params : {
					'Q_archFond.archFondId_L_EQ' : record.data.archFondId
				}
			});
		this.rollNo.reset();
		this.searchPanel.getForm()
			.findField('Q_archRoll.rollNo_S_EQ').reset();
	},
	
	up : function() {
		var filesGrid =this.filesGrid;
		var selectRecords = filesGrid.getSelectionModel().getSelections();
		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要移动的记录！");
			return;
		}
		for (var i = 0; i < selectRecords.length; i++) {
			if (selectRecords[i].data.archStatus == '1') {
				selectRecords.splice(i, 1);
				i--;
			}
		}
		if (selectRecords.length == 0) {

			Ext.ux.Toast.msg("信息", "所选择的文件件已归档！");
			return;

		}
		for (var i = 0; i < selectRecords.length; i++) {
			
			this.tidyFileForm.mystore.add(selectRecords[i]);

			filesGrid.getStore().remove(selectRecords[i]);
		}
		//this.tidyFileForm.gridPanel.getBottomToolbar().moveFirst();

	},
	down : function() {
		var filesGrid = 	this.filesGrid;
		var gridPanel = this.tidyFileForm.gridPanel;
		var store = this.tidyFileForm.mystore;
		var selectRecords = gridPanel.getSelectionModel().getSelections();
		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要移动的记录！");
			return;
		}
		var showalert=false;
		for (var i = 0; i < selectRecords.length; i++) {
			if(selectRecords[i].data.archStatus==1){
				showalert=true;				
				continue;
			}
			store.remove(selectRecords[i]);
			filesGrid.getStore().add(selectRecords[i]);
		}
		if(showalert)
			Ext.ux.Toast.msg("信息", "已归档记录不能移回！");

	},
	// 重置查询表单
	reset : function() {
		this.searchPanel.getForm().reset();
		this.searchPanel.getCmpByName('hiddenName').clearValue();
	},
	// 按条件搜索
	search : function() {
		$search({
					searchPanel : this.searchPanel,
					gridPanel : this.filesGrid
				});
	},
	viewFile : function(record) {

		Ext.Ajax.request({
					url : __ctxPath + '/arch/listRollFileList.do',
					method : 'POST',
					success : function(response, opts) {

						var obj = Ext.decode(response.responseText);
						var viewConfig = [];
						for (var i = 0; i < obj.result.length; i++) {
							viewConfig.push({
										fileName : obj.result[i].fileAttach.fileName,
										filePath : obj.result[i].fileAttach.filePath
									});
						}

						new ViewFileWindow({
									viewConfig : viewConfig,
									startIndex : 0
								}).show();
					},

					failure : function(response, opts) {

					},
					params : {
						'Q_rollFile.rollFileId_L_EQ' : record.data.rollFileId,
						dir : 'ASC',
						sort : 'sn'
					}
				});

	},
	showFile : function(record) {
		new MyBorrowFileViewWindow({
					rollFileId : record.data.rollFileId,
					archStatus : record.data.archStatus
				}).show();
	},
	// 行的Action
	onRowAction : function(grid, record, action, row, col) {

		switch (action) {
			case 'btn-readdocument' :
				this.viewFile.call(this, record);
				break;
			case 'btn-showDetail' :
				this.showFile.call(this, record);
				break;
			default :
				break;
		}
	}

});
