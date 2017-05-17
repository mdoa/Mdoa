/**
 * 请假核审信息
 * @class LeaveManageWin
 * @extends Ext.Window
 */
LeaveManageWin = Ext.extend(Ext.Window, {
	showPanel : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		LeaveManageWin.superclass.constructor.call(this, {
					title : '请假审核信息',
					iconCls : 'menu-holiday',
					width : 560,
					height : 255,
					modal : true,
					maximizable : true,
					layout : 'fit',
					items : [this.showPanel],
					buttonAlign : 'center',
					buttons : this.buttons
				});
	},
	// 初始化组件
	initUIComponents : function() {
		//请假核审信息面板
		this.showPanel = new Ext.Panel({
					id : 'LeaveManageWin',
					modal : true,
					autoScroll : true,
					autoLoad : {
						url : __ctxPath
								+ '/pages/personal/errandsRegisterDetail.jsp?dateId='
								+ this.dateId
					}
				});

		this.buttons = [{
					xtype : 'button',
					text : '关闭',
					iconCls : 'btn-close',
					handler : function() {
						this.close();
					},
					scope : this
				}];
	}
});
