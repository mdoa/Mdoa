/**
 * @description 新增和编辑窗口
 * @class BoardTypeForm
 * @extends Ext.Window
 */
BoardTypeForm = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		// 调用父类构造
		BoardTypeForm.superclass.constructor.call(this, {
					layout : 'fit',
					id : 'BoardTypeFormWin',
					title : '编辑/新增会议类型',
					iconCls : 'menu-confernece_boardType',
					width : 350,
					minWidth : 350,
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
								name : 'boardType.typeId',
								value : this.typeId == null? '': this.typeId
							},{
								name : 'boardType.typeName',
								fieldLabel : '会议类型名称',
								allowBlank : false,
								blankText : '会议类型名称不能为空，请输入！',
								maxLength : 128,
								maxLengthText : '会议类型名称不能超过128个字符长度！'
							}, {
								name : 'boardType.typeDesc',
								xtype : 'textarea',
								fieldLabel : '会议类型描述',
								allowBlank : false,
								blankText : '会议类型描述不能为空，请输入！',
								maxLength : 4000,
								maxLengthText : '会议类型描述不能超过4000个字符串长度！'
							}
					]
				});// end of the formPanel

		// 加载表单对应的数据
		if (this.typeId != null && this.typeId != 'undefined') {
			this.formPanel.loadData({
						url : __ctxPath + '/admin/getBoardType.do?typeId='+ this.typeId,
						root : 'data',
						preName : 'boardType'
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
			url : __ctxPath + '/admin/saveBoardType.do',
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


