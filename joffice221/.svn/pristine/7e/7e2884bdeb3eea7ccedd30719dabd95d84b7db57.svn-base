Ext.ns('HolidayRecordForm');
/**
 * 假期设置列表
 */
HolidayRecordForm = Ext.extend(Ext.Window, {
	sope:this,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		// 调用父类构造
		HolidayRecordForm.superclass.constructor.call(this, {
					id : 'HolidayRecordFormWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					width : 390,
					minWidth : 390,
					height : 270,
					minHeight : 270,
					maximizable : true,
					title : '假期设置详细信息',
					iconCls : 'menu-holidayRecord',
					buttonAlign : 'center',
					buttons : [{
								text : '保存',
								iconCls : 'btn-save',
								scope : this,
								handler : this.save
							}, {
								text : '重置',
								iconCls : 'btn-reset',
								scope : this,
								handler : this.reset
							}, {
								text : '取消',
								iconCls : 'btn-cancel',
								scope : this,
								handler : this.cancel
							}]
				});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		//表单面板
		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			bodyStyle : 'padding:5px;',
			border : false,
			autoScroll : true,
			defaults : {
				width : 400,
				anchor : '98%,98%'
			},
			defaultType : 'textfield',
			items : [{
						name : 'holidayRecord.recordId',
						xtype : 'hidden',
						value : this.recordId == null ? '' : this.recordId
					}, {
						fieldLabel : '开始日期',
						name : 'holidayRecord.startTime',
						xtype : 'datetimefield',
						format : 'Y-m-d',
						allowBlank : false
					}, {
						fieldLabel : '结束日期',
						xtype : 'datetimefield',
						name : 'holidayRecord.endTime',
						format : 'Y-m-d',
						allowBlank : false
					}, {
						fieldLabel : '描述',
						xtype : 'textarea',
						name : 'holidayRecord.descp',
						allowBlank : false
					}, {
						width : '100%',
						xtype : 'container',
						layout : 'column',
						defaultType : 'textfield',
						height : 26,
						items : [{
									xtype : 'label',
									text : '所属用户:',
									width : 105
								}, {
									columnWidth : .999,
									name : 'holidayRecord.fullname',
									readOnly : true
								}, {
									xtype : 'hidden',
									name : 'holidayRecord.userId'
								}, {
									width : 70,
									xtype : 'button',
									text : '选择',
									name : 'userSelect',
									iconCls : 'btn-user-sel',
									scope : this,
									handler : this.userSelector
								}]
					}, {
						fieldLabel : '全公司假期',
						name : 'holidayRecord.isAll',
						xtype : 'checkbox',
						inputValue : 1,
						listeners : {
							scope : this,
							check : this.check
						}
					}]
		});
		// 加载表单对应的数据
		if (this.recordId != null && this.recordId != 'undefined') {
			this.formPanel.loadData({
						url : __ctxPath + '/personal/getHolidayRecord.do?recordId='+ this.recordId,
						root : 'data',
						preName : 'holidayRecord'
					});
		};
		
		
	},// end of the initcomponents

	/**
	 * 重置
	 * 
	 * @param {}
	 *            formPanel
	 */
	reset : function() {
		this.formPanel.getForm().reset();
	},
	/**
	 * 取消
	 * 
	 * @param {}
	 *            window
	 */
	cancel : function() {
		this.close();
	},
	//人员选择
	userSelector : function() {
		var formPanel = this.formPanel;
		new UserDialog({callback:function(ids, names) {
				  formPanel.getCmpByName('holidayRecord.fullname').setValue(names);
				   formPanel.getCmpByName('holidayRecord.userId').setValue(ids);
				}},true).show();//true表示单选
	},
	//是否是全公司放假
	check : function(ck,isChecked){
		var formPanel = this.formPanel;
		var fullname = formPanel.getCmpByName('holidayRecord.fullname');
		var userId = formPanel.getCmpByName('holidayRecord.userId');
		var userSelect = formPanel.getCmpByName('userSelect');	
		var isAll = formPanel.getCmpByName('holidayRecord.isAll');
		if(isChecked){
			//全公司假期时
			fullname.setValue('');
			fullname.setDisabled(true);
			userSelect.setDisabled(true);
			userId.setValue('');
			
		}else{
			//不是全公司假期时
			fullname.setDisabled(false);
			userSelect.setDisabled(false);
			isAll.setDisabled(false);
		}
	},
	/**
	 * 保存记录
	 */
	save : function() {
		var formPanel = this.formPanel;
		//验证
		if (formPanel.getForm().isValid()) {
			var st = formPanel.getCmpByName('holidayRecord.startTime').getValue();
			var et = formPanel.getCmpByName('holidayRecord.endTime').getValue();
			var sd = Date.parse(st);
			var ed = Date.parse(et);
			if (sd > ed) {
				Ext.ux.Toast.msg('操作信息', '假期开始时间大于结束时间,不能保存!');
				return;
			};
			
			//提交
			$postForm({
				        formPanel : formPanel,
						scope : this,
						url : __ctxPath + '/personal/saveHolidayRecord.do',
						callback : function(fp, action) {
							if (this.callback) {
								this.callback.call(this.scope);
							}
							this.close();
						}
					});
		}// end of save
	}
});