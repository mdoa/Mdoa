/**
 * 车辆选择器
 * 
 * @class CarDialog
 * @extends Ext.Window
 *          @example
 * 
 * <pre>
 * new CarDialog({
 *  	title :'车辆选择' //标题  默认是'车辆选择'，也可以自定义标题
 * 		single: true,   //是否单选 默认是多选车辆
 * 		status 1,
 *            状态：1,可用；2,维修中，3,报废
 * 		scope:this,   //作用域
 * 		callback :function(ids,names){//回调函数,返回车辆ids和车牌号
 * 
 * 		}	
 * 	}
 * </pre>
 * 
 */
var CarDialog = Ext.extend(Ext.Window, {
			constructor : function(conf) {
				Ext.applyIf(this, conf);
				// 当前作用域
				this.scope = this.scope ? this.scope : this;
				// 默认为多单选择车辆
				this.single = this.single != null ? this.single : false;
				// 初始化组件
				this.initUI();
				CarDialog.superclass.constructor.call(this, {
							title : this.title ? this.title : '车辆选择',
							iconCls : 'menu-car',
							width : 630,
							height : 380,
							layout : 'border',
							border : false,
							modal : true,
							buttonAlign : 'center',
							items : [this.gridPanel, this.searchPanel],
							buttons : [{
										iconCls : 'btn-ok',
										text : '确定',
										scope : this,
										handler : this.confirm
									}, {
										text : '取消',
										iconCls : 'btn-cancel',
										scope : this,
										handler : function() {
											this.close();
										}
									}]
						});
			},

			/**
			 * 初始化UI
			 */
			initUI : function() {
				// -------start grid panel--------------------------------
				this.gridPanel = new HT.GridPanel({
							title : '车辆列表',
							width : 400,
							height : 300,
							region : 'center',
							singleSelect : this.single,
							url : __ctxPath + "/admin/listCar.do",
							baseParams : {
								'Q_status_SN_EQ' : this.status == null
										? 1
										: this.status
							},
							fields : [{
										name : 'carId',
										type : 'int'
									}, 'carNo', 'status'],
							columns : [{
										header : 'carId',
										dataIndex : 'carId',
										hidden : true
									}, {
										header : "车辆车牌号",
										dataIndex : 'carNo',
										width : 60
									}]

						});

				// ----------end grid panel------------------
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
							labelWidth : 160,
							items : [{
										fieldLabel : '请输入查询条件: 车辆车牌号',
										xtype : 'textfield',
										name : 'Q_carNo_S_LK',
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
										handler : this.reset
									}]
						});
			},// end of initUI function
			/**
			 * 查询
			 */
			search : function() {
				$search({
							searchPanel : this.searchPanel,
							gridPanel : this.gridPanel
						});
			},
			/**
			 * 重置(清空)查询表单
			 */
			reset : function() {
				this.searchPanel.getForm().reset();
			},
			/**
			 * 选择确认
			 */
			confirm : function() {
				var rows = this.gridPanel.getSelectionModel().getSelections();
				var carIds = '';
				var carNos = '';
				for (var i = 0; i < rows.length; i++) {

					if (i > 0) {
						carIds += ',';
						carNos += ',';
					}
					carIds += rows[i].data.carId;
					carNos += rows[i].data.carNo;
				}
				if (this.callback) {
					this.callback.call(this.scope, carIds, carNos);
				}
				this.close();
			}
		});