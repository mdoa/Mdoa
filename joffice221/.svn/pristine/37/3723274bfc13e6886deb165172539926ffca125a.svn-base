/**
 * @author 
 * @createtime 
 * @class 
 * @extends Ext.Window
 * @description
 * @company 宏天软件
 */
DiaryForm = Ext.extend(Ext.Window, {
	//构造方法
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		//必须先初始化组件
		this.initUIComponents();
		//调用父类的构造方法
		DiaryForm.superclass.constructor.call(this, {
			id : 'DiaryFormWin',
			title : '日志详细信息',
			layout : 'fit',
			items : this.formPanel,
			modal : true,
			width : 650,
			height : 450,
			maximizable : true,
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
	},//end of the constructor
	//初始化组件
	initUIComponents : function() {
		var x = '<font style="color: red;">*</font>';
		//日志信息表单
		this.formPanel = new Ext.FormPanel( {
			layout : 'form',
			frame : false,
			border:false,
			bodyStyle : 'padding:5px;',
			defaults : {
				widht : 400,
				anchor : '96%,96%'
			},
			defaultType : 'textfield',
			items : [ {
				name : 'diary.diaryId',
				xtype : 'hidden',
				value : this.diaryId == null ? '' : this.diaryId
			}, {
				xtype:'hidden',
				name : 'diary.appUser.userId'
			}, {
				fieldLabel : '日期'+x,
				xtype : 'datefield',
				name : 'diary.dayTime',
				editable:false,
				allowBlank : false,
				format : 'Y-m-d'				
			}, {
				fieldLabel : '日志类型',
				xtype : 'combo',
				hiddenName : 'diary.diaryType',
				mode : 'local',
				editable : false,
				value:'1',
				triggerAction : 'all',
				store :[['0','个人日志'],['1','工作日志']]
			}, {
				fieldLabel : '内容',
				xtype : 'htmleditor',
				name : 'diary.content'
			} ]
		});
		
		//加载表单对应的数据	
		if (this.diaryId != null && this.diaryId != 'undefined') {
			this.formPanel.loadData({
				url : __ctxPath + '/system/getDiary.do?diaryId=' + this.diaryId,
				root : 'data',
				preName : 'diary'
			});
		}
		
	},//end of the initcomponents
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
	/**
	 * 保存记录
	 */
	save : function() {
		$postForm({
			formPanel : this.formPanel,
			scope : this,
			url : __ctxPath + '/system/saveDiary.do',
			callback : function(fp, action) {
				if(this.callback){
					this.callback.call(this.scope);
				}
				this.close();
			}
		});
	}//end of save

});
