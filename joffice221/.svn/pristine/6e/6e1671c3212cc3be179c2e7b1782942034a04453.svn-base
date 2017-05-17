/**
 * @author
 * @createtime
 * @class TidyFileForm
 * @extends Ext.Window
 * @description RollFile表单
 * @company 宏天软件
 */

Ext.ns('TidyFileForm');

TidyFileForm = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		TidyFileForm.superclass.constructor.call(this, {
					layout : 'form',
					border : true,
					items : [this.gridPanel],
					title : '归档文件详细信息',
					frame : false,
					listeners : {
						afterlayout : function(window) {
							var wh = window.getInnerHeight();
							window.gridPanel.setHeight(wh);
						}

					}
				});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
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
					reader : this.jsonReader
				});
		

		this.topbar = new Ext.Toolbar({
			items : [{
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
				   },{
						xtype : 'tbtext',
						text : '全宗号'
					}, {
						width : 50,
						name : 'rollFile.afNo',
						xtype : 'combo',
						mode : 'local',
						allowBlank : false,
						lazyInit : false,
						forceSelection : true,
						editable : false,
						triggerAction : 'all',
						store : new Ext.data.JsonStore({
									url : __ctxPath + "/arch/listArchFond.do",
									autoLoad : true,
									autoShow : true,
									root : 'result',
									fields : ['archFondId', 'afNo'],
									listeners : {
										'load' : function(store, records,
												options) {
										}
									}
								}),
						valueField : 'afNo',
						displayField : 'afNo',
						listeners : {
							scope : this,
							'select' : this.afNoSelectClick
						}
					},{
						xtype : 'tbtext',
						text : '案卷号'
					},{
						width : 50,
						name : 'rollFile.rollNo',
						xtype : 'textfield',
						allowBlank : false,
						xtype : 'combo',
						mode : 'local',
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
							scope : this,
							'select' : this.rollNoSelectClick
						}

					}, {
						xtype : 'button',
						text : '保存',
						iconCls : 'btn-save',
						scope : this,
						handler : this.save
					}, {
						// fieldLabel : '文件分类ID',
						width : 100,
						value : this.proTypeId == null ? '' : this.proTypeId,
						name : 'rollFile.proTypeId',
						xtype : 'hidden'
					}, {
						// fieldLabel : '案卷ID',
						width : 100,
						name : 'rollFile.rollId',
						xtype : 'hidden'
					},{
						// fieldLabel : '文件分类名称',
						name : 'rollFile.typeName',
						width : 100,
						xtype : 'hidden'
					}]
		});
		this.gridPanel = new HT.GridPanel({
					frame : false,
					border : false,
					region : 'center',
					height : 400,
					tbar : this.topbar,				
					store:this.mystore,
					autoExpandColumn : 'fileName',
					rowActions:true,
					fields : [{
								name : 'rollFileId',
								type : 'int'
							}, 'afNo', 'createTime', 'creatorName',
							'creatorId', 'archStatus', 'proTypeId', 'typeName',
							'openStyle', 'rollNo', 'fileName', 'fileNo',
							'fileNo', 'catNo', 'seqNo', 'pageNo', 'pageNums',
							'secretLevel', 'timeLimit', 'keyWords', 'keyWords',
							'content', 'fileTime', 'notes', 'dutyPerson',
							'archRoll', 'globalType'],
					columns : [{
						header : 'rollFileId',
						dataIndex : 'rollFileId',
						hidden : true
					},
					{
						header : '全宗号',
						sortable : true,
						dataIndex : 'archRoll',
						renderer : function(archRoll) {
							if (archRoll)
								return archRoll.afNo;
						}
					},
					{
						header : '案卷号',
						sortable : true,
						dataIndex : 'archRoll',
						renderer : function(archRoll) {
							if (archRoll)
								return archRoll.rollNo;
						}
					}, 
					{
						header : '文件题名',
						sortable : true,
						dataIndex : 'fileName'
					}, 
					{
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
				});
		this.mystore.load({
			params : {
				Q_archStatus_SN_EQ:1
			}
		});
		
	},// end of the initcomponents
	/**
	 * 分类选择
	 */
	comboTreeSelect:function(combo, record, index){
		var proTypeId = record.id;
		var typeName = record.text;
		if (proTypeId == '0') {
			this.topbar.getCmpByName('rollFile.proTypeId')
					.setValue('');
			this.topbar.getCmpByName('rollFile.typeName')
					.setValue('');
		}else{
			this.topbar.getCmpByName('rollFile.proTypeId')
					.setValue(proTypeId);
			this.topbar.getCmpByName('rollFile.typeName')
					.setValue(typeName);
		}
	},
	/**
	 * afNo选择处理函数
	 */
	afNoSelectClick:function(combo, record, index) {
		this.topbar.getCmpByName('rollFile.rollNo')
				.getStore().load({
					params : {
						'Q_archFond.archFondId_L_EQ' : record.data.archFondId
					}
		});
		this.topbar.getCmpByName('rollFile.rollNo').reset();
	},
	/**
	 * rollNo选择处理函数
	 */
	rollNoSelectClick:function(combo, record, index) {	
		this.topbar.getCmpByName('rollFile.rollId')
				.setValue(record.get('rollId'));
		var filesGrid = this.thisWin.filesGrid;//TidyFileGrid');
		var currentdata=this.gridPanel.getStore().data.items;
		for(var i=0;i<currentdata.length;i++){
			if(currentdata[i].data.archStatus==0){
				filesGrid.store.add(currentdata[i]);
			}
		}
		this.gridPanel.store.reload({
			params : {
				'Q_archStatus_SN_EQ':1,
				'Q_archRoll.rollNo_S_EQ':record.get('rollNo')
			}
		});
	},
	/**
	 * 保存记录
	 */
	save : function() {
		var params = [];
		var store = this.gridPanel.getSelectionModel().getSelections();
		var gridPanel = this.gridPanel;
		var cnt = store.length;
		if(this.topbar.getCmpByName('rollFile.proTypeId').getValue()==''
				||this.topbar.getCmpByName('rollFile.proTypeId').getValue()==null
				||this.topbar.getCmpByName('rollFile.rollId').getValue()==''
				||this.topbar.getCmpByName('rollFile.rollId').getValue()==null){
			Ext.ux.Toast.msg("信息", "请先选择好分类！");
			return;
		}

		for (var i = 0; i < cnt; i++) {
			if( store[i].data.archStatus)
				continue;
			var record = store[i];
			// 外键
			Ext.apply(record.data, {
						typeName : this.topbar.getCmpByName('rollFile.typeName').getValue(),
						afNo : this.topbar.getCmpByName('rollFile.afNo').getValue(),
						rollNo :  this.topbar.getCmpByName('rollFile.rollNo').getValue(),
						archStatus : 1
					});
			if (record.data.globalType) {
				Ext.apply(record.data.globalType, {
							proTypeId : this.topbar.getCmpByName('rollFile.proTypeId').getValue()
						});
			} else {
				var globalType = {};
				Ext.apply(globalType, {
							proTypeId : this.topbar.getCmpByName('rollFile.proTypeId').getValue()
						});
				Ext.apply(record.data, {
							globalType : globalType
						});
			}
			if (record.data.archRoll) {
				Ext.apply(record.data.archRoll, {
							rollId : this.topbar.getCmpByName('rollFile.rollId').getValue()
						});
			} else {
				var archRoll = {};
				Ext.apply(archRoll, {
							rollId : this.topbar.getCmpByName('rollFile.rollId').getValue()
						});
				Ext.apply(record.data, {
							archRoll : archRoll
						});
			}
			params.push(record.data);
		}
		if (params.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要归档的未归档文件！");
			return;
		}				
		Ext.Ajax.request({
					url : __ctxPath + '/arch/tidyRollFile.do',
					method : 'POST',
					async : true,
					success : function(response, opts) {
						Ext.ux.Toast.msg('操作信息', '保存成功!');
						gridPanel.getStore().reload();
					},
					failure : function(response, opts) {
						Ext.MessageBox.show({
									title : '操作信息',
									msg : '信息保存出错，请联系管理员！',
									buttons : Ext.MessageBox.OK,
									icon : Ext.MessageBox.ERROR
								});
					},
					params : {
						params : Ext.encode(params)
					}
				});				
	}// end of save
	,
	viewFile : function(record) {
		Ext.Ajax.request({
					url : __ctxPath + '/arch/listRollFileList.do',
					method : 'POST',
					async : false,
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
		showFile:function(record){			
			new MyBorrowFileViewWindow({
				rollFileId:record.data.rollFileId,
				archStatus:record.data.archStatus
			}).show();
		},
	onRowAction : function(gridPanel, record, action, row, col) {
		switch (action) {
			case 'btn-readdocument':
				this.viewFile(record);
				break;
			case 'btn-showDetail':
			this.showFile.call(this, record);
			break;
			default :
				break;
		}
	}

});