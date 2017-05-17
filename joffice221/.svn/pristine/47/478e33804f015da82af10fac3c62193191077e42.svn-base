/**
 * 印章选择器
 * 
 * @class SealDialog
 * @extends Ext.Window
 *          @example
 * 
 * <pre>
 * new SealDialog({
 *  	title :'选择印章' //标题  默认是'选择印章'，也可以自定义标题
 * 		scope:this,   //作用域
 * 		callback :function(sealName,path,belongName){//回调函数,返回印章名称、路径地址和所属人名称
 * 
 * 		}	
 * 	}
 * </pre>
 */
SealDialog = Ext.extend(Ext.Window, {
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 作用域
				this.scope = this.scope ? this.scope : this;
				// 初始化
				this.initUI();
				SealDialog.superclass.constructor.call(this, {
							layout : 'border',
							width : 630,
							height : 380,
							iconCls : 'menu-seal',
							title : this.title ? this.title : '选择印章',
							border : false,
							modal : true,
							buttonAlign : 'center',
							buttons : [{
										text : '确定',
										iconCls : 'btn-ok',
										scope : this,
										handler : this.confirm
									}, {
										text : '取消',
										iconCls : 'btn-cancel',
										scope : this,
										handler : function() {
											this.close();
										}
									}],
							items : [this.searchPanel, this.gridPanel]
						});
			},
			// 初始化面板
			initUI : function() {
				// 查询面板
				this.searchPanel = new HT.SearchPanel({
							width : 400,
							layout : 'form',
							region : 'north',
							colNums : 3,
							keys : {
								key : Ext.EventObject.ENTER,
								fn : this.search,
								scope : this
							},
							labelWidth : 145,
							items : [{
										fieldLabel : '请输入查询条件:印章名称',
										xtype : 'textfield',
										name : 'Q_sealName_S_LK'
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
						});
				// 印章列表面板
				this.gridPanel = new HT.GridPanel({
					region : 'center',
					tbar : this.topbar,
					singleSelect : true,
					// 使用RowActions
					rowActions : false,
					url : __ctxPath + "/document/listSeal.do",
					fields : [{
								name : 'sealId',
								type : 'int'
							}, 'sealName', 'sealPath', 'belongName'],
					columns : [{
								header : 'sealId',
								dataIndex : 'sealId',
								hidden : true
							}, {
								header : '印章名称',
								dataIndex : 'sealName'
							}, {
								header : '所属人员',
								dataIndex : 'belongName'
							}]
						// end of columns
					});
			},
			// 重置
			reset : function() {
				this.searchPanel.getForm().reset();
			},
			// 查询
			search : function() {
				$search({
							searchPanel : this.searchPanel,
							gridPanel : this.gridPanel
						});
			},
			// 确定
			confirm : function() {
				var grid = this.gridPanel;
				var rows = grid.getSelectionModel().getSelections();
				// var sealIds = '';
				var sealNames = '';
				var path = '';
				var belongName = '';
				if (rows.length > 0) {
					sealName = rows[0].data.sealName;
					belongName = rows[0].data.belongName;
					path = rows[0].data.sealPath;
				}
				if (this.callback != null) {
					this.callback.call(this, sealName, path, belongName);
				}
				this.close();
			}
		});
