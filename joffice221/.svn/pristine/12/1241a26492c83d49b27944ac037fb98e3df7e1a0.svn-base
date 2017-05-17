/**
 * @author
 * @createtime
 * @class MyBorrowFileTypePanel
 * @extends Ext.Window
 * @description RollFile表单
 * @company 宏天软件
 */

Ext.ns('MyBorrowFileTypePanel');

MyBorrowFileTypePanel = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		MyBorrowFileTypePanel.superclass.constructor.call(this, {
					layout : 'fit',
					border : true,
					items : [this.gridPanel],
					frame : false

				});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {



		this.rowActions = new Ext.ux.grid.RowActions({
			header : '管理',
			width : 80,
			actions : [ {
						iconCls : 'btn-showDetail',
						qtip : '查看详细',
						style : 'margin:0 3px 0 3px'
					},{
						iconCls : 'btn-readdocument',
						qtip : '预览附件',
						style : 'margin:0 3px 0 3px',
						hideIndex : 'preview'	
					}]
		});
		this.gridPanel = new HT.GridPanel({
					frame : false,
					border : false,
					store : this.store,
					rowActions:true,
					baseParams : {
							'Q_borrowRecord.recordId_L_EQ' : this.recordId,
							start : 0,
							limit : 25
						},
					url : __ctxPath + "/arch/listCheckBorrowFileList.do",
					fields : [{
						name : 'listId',
						type : 'int'
					}, 'recordId', 'listType', 'archFond', 'afNo', 'afName',
					'archRoll', 'rollNo', 'rolllName', 'rollFile', 'fileNo',
					'fileName',{name:'preview',type:'boolean'}],
					columns : [
					           new Ext.grid.RowNumberer(), {
								header : 'listId',
								dataIndex : 'listId',
								hidden : true
							}, {
								header : '借阅单位',
								dataIndex : 'listType'
							}, {
								header : '全宗ID',
								dataIndex : 'archFond',
								hidden : true,
								renderer : function(archFond) {
									if (archFond)
										return archFond.archFondId;
								}
							}, {
								header : '全宗号',
								dataIndex : 'archFond',
								renderer : function(archFond) {
									if (archFond)
										return archFond.afNo;
								}
							}, {
								header : '全宗名',
								dataIndex : 'archFond',
								renderer : function(archFond) {
									if (archFond)
										return archFond.afName;
								}
							}, {
								header : '案卷ID',
								hidden : true,
								dataIndex : 'archRoll',
								renderer : function(archRoll) {
									if (archRoll)
										return archRoll.rollId;
								}
							}, {
								header : '案卷号',
								dataIndex : 'archRoll',
								renderer : function(archRoll) {
									if (archRoll)
										return archRoll.rollNo;
								}
							}, {
								header : '案卷名',
								dataIndex : 'archRoll',
								renderer : function(archRoll) {
									if (archRoll)
										return archRoll.rolllName;
								}
							}, {
								header : '文件ID',
								hidden : true,
								dataIndex : 'rollFile',
								renderer : function(rollFile) {
									if (rollFile)
										return rollFile.rollFileId;
								}
							}, {
								header : '文件号',
								dataIndex : 'rollFile',
								renderer : function(rollFile) {
									if (rollFile)
										return rollFile.fileNo;
								}
							}, {
								header : '文件题名',
								dataIndex : 'rollFile',
								renderer : function(rollFile) {
									if (rollFile)
										return rollFile.fileName;
								}
							},this.rowActions]

				});
		this.gridPanel.store.on('load',function(s,rs){
			Ext.each(rs,function(r){
				if(r.get('listType')=='文件'){
					r.set('preview',false);
					
				}else{
					r.set('preview',true);
				}
				r.commit();
			});
			s.commitChanges();
		});

	}
	

});