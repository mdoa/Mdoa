/**
 * @author lyy 查看消息窗口
 * @class BorrowRecordWin 档案借阅到期提醒
 * @extends Ext.Window
 */
BorrowRecordWin = Ext.extend(Ext.Window, {
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		BorrowRecordWin.superclass.constructor.call(this, {
			id : 'BorrowRecordWin',
			title : '档案借阅到期提醒',
			iconCls : 'btn-replyM',
			region : 'west',
			width : 180,
			height : 150,
			x : 2,
			y : 450,
			layout : 'fit',
			html : '<p>温馨提示：</p><p style="color:blue;">你有借阅的档案已超期,请查看详细信息并及早归还</p>',
			plain : true,
			border : false,
			bodyStyle : 'padding:5px;',
			buttonAlign : 'center',
			buttons : [{
						xtype : 'button',
						text : ' 查  看',
						iconCls : 'btn-search',
						scope : this,
						handler : function() {
							App.clickTopTab('MyBorrowRecordView', '');
							this.close();
						}
					}]
		});
	}
});
