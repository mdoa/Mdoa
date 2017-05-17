/**
 * 编辑和新增窗口
 * @class BoardRooForm
 * @extends Ext.Window
 */
BoardRooForm = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		// 调用父类构造
		BoardRooForm.superclass.constructor.call(this, {
					layout : 'fit',
					id : 'BoardRooFormWin',
					title : '编辑/新增会议室信息',
					iconCls : 'menu-conference_boardRoom',
					width : 400,
					minWidth : 400,
					height : 200,
					minHeight : 200,
					items : this.formPanel,
					maximizable : true,
					border : false,
					modal : true,
					buttonAlign : 'center',
					keys : {
						key : Ext.EventObject.ENTER,
						fn : this.save,
						scope : this
					},
					buttons : [{
								text : '保存',
								iconCls : 'btn-save',
								scope : this,
								handler : this.save
							}, {
								text : '取消',
								iconCls : 'btn-cancel',
								scope : this,
								handler : this.cancel
							}, {
								text : '重置',
								iconCls : 'btn-reset',
								scope : this,
								handler : this.reset
							}]
				});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		//表单
		this.formPanel = new Ext.FormPanel({
					layout : 'form',
					frame : false,
					bodyStyle : 'padding-top:5px;padding-left:5px;',
					defaults : {
						width : 400,
						anchor : '96%,96%'
					},
					defaultType : 'textfield',
					items : [{
						        xtype : 'hidden',
								name : 'boardRoo.roomId',
								value : this.roomId == null? '': this.roomId
							},{
								name : 'boardRoo.roomName',
								xtype : 'textfield',
								fieldLabel : '会议室名称',
								allowBlank : false,
								blankText : '会议室名称不能为空！',
								maxLength : 128,
								maxLengthText : '会议室名称不能超过128个字符长度！'
							}, {
								name : 'boardRoo.roomDesc',
								xtype : 'textarea',
								fieldLabel : '会议室描述',
								allowBlank : false,
								blankText : '会议室描述不能为空！',
								maxLength : '4000',
								maxLengthText : '会议室描述不能超过4000个字符长度！'
							}, {
								xtype : 'numberfield',
								name : 'boardRoo.containNum',
								fieldLabel : '会议室总容量',
								allowBlank : false,
								blankText : '请输入总容量！',
								maxlength : 5,
								maxLengthText : '会议室总容量不能超过十万人！'
							}
					]
				});// end of the formPanel

		// 加载表单对应的数据
		if (this.roomId != null && this.roomId != 'undefined') {
			this.formPanel.loadData({
						url : __ctxPath + '/admin/getBoardRoo.do?roomId=' + this.roomId,
						root : 'data',
						preName : 'boardRoo'
					});
		};
	},// end of the initUIComponents

	/**
	 * 保存
	 */
	save : function() {
		var formPanel = this.formPanel;
		// 提交
		$postForm({
			formPanel : formPanel,
			scope : this,
			url : __ctxPath + '/admin/saveBoardRoo.do',
			callback : function(fp, action) {
				if (this.callback) {
					this.callback.call(this.scope);
				}
				this.close();
			}
		});
	},
	/**
	 * 重置
	 * 
	 * @param {}
	 * formPanel
	 */
	reset : function() {
		this.formPanel.getForm().reset();
	},
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


