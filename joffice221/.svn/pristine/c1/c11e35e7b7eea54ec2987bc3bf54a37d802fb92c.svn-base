/**
 * 操作记录窗口
 * 
 * @class OperationRecord
 * @extends Ext.Window
 */
OperationRecord = Ext.extend(Ext.Window, {
			// 构造函数
			constructor : function(_cfg) {
				Ext.apply(this, _cfg);
				// 必须先初始化组件
				this.initComponents();
				OperationRecord.superclass.constructor.call(this, {
							id :  Ext.isEmpty(this.id)
									? Ext.id()
									: this.id,
							title : Ext.isEmpty(this.title)
									? '操作记录'
									: this.title,
							iconCls : 'btn-operation',
							width : 500,
							x : 300,
							y : 50,
							autoHeight : true,
							border : false,
							modal : true,
							layout : 'anchor',
							plain : true,
							bodyStyle : 'padding:5px;',
							buttonAlign : 'center',
							items : [this.showPanel],
							buttons : [{
										text : '关闭',
										iconCls : 'btn-cancel',
										scope : this,
										handler : function() {
											this.close();
										}
									}]
						});
			},// end of the constructor
			// 初始化组件
			initComponents : function() {
				this.showPanel = new Ext.Panel({
							autoScroll : true,
							autoHeight : true,
							border : false,
							autoLoad : {
								url : this.url
							}
						});

			}// end of the initcomponents
		});