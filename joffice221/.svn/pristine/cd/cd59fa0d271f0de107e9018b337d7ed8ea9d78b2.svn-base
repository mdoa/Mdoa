Ext.ns('DepreRecordView');
/**
 * @author:
 * @class DepreRecordView
 * @extends Ext.Panel
 * @description 固定资产折旧记录列表
 * @company 杭州梦德软件有限公司
 * @createtime:2010-04-12
 */
DepreRecordView = Ext.extend(Ext.Panel, {
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 初始化组件
				this.initUIComponents();
				// 调用父类构造
				DepreRecordView.superclass.constructor.call(this, {
							id : 'DepreRecordView',
							title : '固定资产折旧记录列表',
							iconCls : 'menu-depRecord',
							region : 'center',
							layout : 'border',
							items : [this.searchPanel, this.gridPanel]
						});
			},// end of constructor
			// 初始化组件
			initUIComponents : function() {
				// 初始化搜索条件Panel
				this.searchPanel = new HT.SearchPanel({
							region : 'north',
							layout : 'form',
							colNums : 5,
							keys : [{
										key : Ext.EventObject.ENTER,
										fn : this.search,
										scope : this
									}, {
										key : Ext.EventObject.ESC,
										fn : this.reset,
										scope : this
									}],
							labelWidth : 155,
							items : [{
										fieldLabel : '请输入查询条件:资产名称',
										xtype : 'textfield',
										name : 'Q_fixedAssets.assetsName_S_LK'
									}, {
										fieldLabel : '折旧时间: 从',
										xtype : 'datefield',
										name : 'Q_calTime_D_GE',
										format : 'Y-m-d',
										editable : false,
										labelWidth : 80
									}, {
										fieldLabel : '到',
										xtype : 'datefield',
										name : 'Q_calTime_D_LE',
										format : 'Y-m-d',
										editable : false,
										labelWidth : 20
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
										handler : this.reset
									}]
						});// end of the searchPanel
				// 折旧记录列表面板
				this.gridPanel = new HT.GridPanel({
							region : 'center',
							sort:[{field: "recordId", direction: "DESC"}],
							url : __ctxPath + '/admin/listDepreRecord.do',
							fields : [{
										name : 'recordId',
										type : 'int'
									}, {
										name : 'assets',
										mapping : 'fixedAssets.assetsName'
									}, {
										name : 'workGrossUnit',
										mapping : 'fixedAssets.workGrossUnit'
									}, {
										name : 'depType',
										mapping : 'fixedAssets.depreType.typeName'
									}, 'workCapacity', 'depreAmount', 'calTime'],
							columns : [{
										header : 'recordId',
										dataIndex : 'recordId',
										hidden : true
									}, {
										header : '资产名称',
										dataIndex : 'assets'
									}, {
										header : '折算类型',
										dataIndex : 'depType'
									}, {
										header : '工作量',
										dataIndex : 'workCapacity',
										renderer : function(value, metadata,
												record, rowIndex, colIndex) {
											if (value != null) {
												var unit = record.data.workGrossUnit;
												return value + ' ' + unit;
											} else {
												return '';
											}
										}
									}, {
										header : '折旧值',
										dataIndex : 'depreAmount'
									}, {
										header : '计算时间',
										dataIndex : 'calTime'
									}]
						});
			},// end of the initUIComponents
			// 重置查询表单
			reset : function() {
				this.searchPanel.getForm().reset();
			},
			// 按条件搜索
			search : function() {
				$search({
							searchPanel : this.searchPanel,
							gridPanel : this.gridPanel
						});
			}
		});
