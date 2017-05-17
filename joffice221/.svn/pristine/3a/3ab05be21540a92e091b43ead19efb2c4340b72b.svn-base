Ext.ns('ConferenceDetailForm');
/**
 * @class ConferenceDetailForm
 * @extends Ext.Window
 * @description 会议详细信息展示
 * @company 宏天软件
 */
ConferenceDetailForm = Ext.extend(Ext.Window, {
	sope:this,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 调用父类构造
		ConferenceDetailForm.superclass.constructor.call(this, {
					id : 'ConferenceDetailFormWin',
					layout : 'fit',
					width : 750,
					height : 580,
					autoScroll : true,
					maximizable : true,
					title : '会议详细信息',
					iconCls : 'menu-conference',
					buttonAlign : 'center',
					keys : {
						key : Ext.EventObject.ENTER,
						fn : this.cancel,
						scope : this
					},
					autoLoad : {
						url : __ctxPath + '/admin/conferenceDetail.do?confId='
								+ this.confId
					},
					buttons : [{
								text : '关闭',
								iconCls : 'btn-cancel',
								scope : this,
								handler : this.cancel
							}]
				});
	},// end of the constructor
	/**
	 * 取消
	 * 
	 * @param {}
	 * window
	 */
	cancel : function() {
		this.close();
	}
});
