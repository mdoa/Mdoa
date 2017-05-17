/**
 * 为了兼容之前的版本暂时这样处理
 * 
 * @param {}
 *            id
 * @return {}
 */
var AppointmentDetail = function(id) {
	return new AppointmentDetailView({
				appointId : id
			});
}
/**
 * 我的约会详情
 * 
 * @class AppointmentDetailView
 * @extends Ext.Panel
 */
var AppointmentDetailView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		AppointmentDetailView.superclass.constructor.call(this, {
					id : 'AppointmentDetail',
					title : '我的约会详情',
					iconCls : 'menu-appointment',
					region : 'center',
					tbar : this.topbar,
					autoScroll : true,
					autoWidth : true,
					items : [this.detailPanel]
				});
	},// end of constructor
	// 初始化组件
	initUIComponents : function() {
		// 顶部按钮组
		this.topbar = new Ext.Toolbar({
					items : [{
								iconCls : 'btn-mail_remove',
								text : '关闭',
								xtype : 'button',
								scope : this,
								handler : this.cancel
							}, {
								iconCls : 'btn-up',
								text : '上一条',
								xtype : 'button',
								scope : this,
								handler : this.prev
							}, {
								iconCls : 'btn-last',
								text : '下一条',
								xtype : 'button',
								scope : this,
								handler : this.next
							}]
				});
		var userId = curUserInfo.userId;
		// 我的约会详情
		this.detailPanel = new Ext.Panel({
					width : 650,
					autoScroll : true,
					style : 'padding-left:10%;padding-top:10px;',
					autoHeight : true,
					border : false,
					autoLoad : {
						url : __ctxPath
								+ '/pages/task/appointmentdetail.jsp?appointId='
								+ this.appointId + '&userId=' + userId
					}
				});
	},
	/**
	 * 关闭
	 */
	cancel : function() {
		var centerTabPanel = Ext.getCmp('AppointmentDetail');
		var oldDetailPanel = centerTabPanel.getItem('AppointmentDetailView');
		centerTabPanel.remove(oldDetailPanel);
	},

	/**
	 * 上一条
	 */
	prev : function() {
		var haveNextAppointFlag = document
				.getElementById('__haveNextAppointFlag');
		if (haveNextAppointFlag != null
				&& haveNextAppointFlag.value != 'endPre') {
			var userId = curUserInfo.userId;
			var appointId = document.getElementById('__curAppointId').value;
			this.detailPanel.load({
				url : __ctxPath
						+ '/pages/task/appointmentdetail.jsp?opt=_pre&appointId='
						+ appointId + '&userId=' + userId
			});
		} else {
			Ext.ux.Toast.msg('提示信息', '这里已经是第一条了.');
		}
	},
	/**
	 * 下一条
	 */
	next : function() {
		var haveNextAppointFlag = document
				.getElementById('__haveNextAppointFlag');
		if (haveNextAppointFlag != null
				&& haveNextAppointFlag.value != 'endNext') {
			var userId = curUserInfo.userId;
			var appointId = document.getElementById('__curAppointId').value;
			this.detailPanel.load({
				url : __ctxPath
						+ '/pages/task/appointmentdetail.jsp?opt=_next&appointId='
						+ appointId + '&userId=' + userId
			});
		} else {
			Ext.ux.Toast.msg('提示信息', '这里已经是最后一条了.');
		}
	}
});
