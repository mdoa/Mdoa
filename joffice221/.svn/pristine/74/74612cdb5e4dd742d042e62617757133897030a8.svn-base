DiaryDetail= Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		// 必须先初始化组件
		this.initComponents();
		DiaryDetail.superclass.constructor.call(this, {
			id : 'DiaryDetailWin',
			title : '工作日志详情',
			layout : 'fit',
			modal : true,
			iconCls:'menu-diary',
			items : this.displayPanel,
			autoHeight :true,
			autoScroll:true,
			maximizable : true,
			width : 500,
			buttonAlign : 'center',
			buttons: this.buttons	
		});
	},
	//初始化组件
	initComponents: function(){
		//显示窗口
		this.displayPanel = new Ext.Panel({
			flex : 1,
//			height : 430,
			autoScroll : true,
			border : false,
			autoLoad : {
				url : __ctxPath + '/system/checkDiary.do?diaryId='+this.diaryId
			}
		});
		//底部菜单面板
		this.buttons = [{
			text : '取消',
			iconCls : 'btn-cancel',
			scope : this,
			handler : this.cancel
		} ];
	},// end of the initcomponents
	cancel : function() {
		this.close();
	}
});
