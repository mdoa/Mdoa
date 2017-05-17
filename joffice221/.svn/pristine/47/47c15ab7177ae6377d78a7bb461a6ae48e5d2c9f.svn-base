Ext.ns('BookTypeForm');
/**
 * @author
 * @createtime
 * @class BookTypeForm
 * @extends Ext.Window
 * @description 图书类别详细信息表单
 * @company 宏天软件
 */
BookTypeForm = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		BookTypeForm.superclass.constructor.call(this, {
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					id : 'BookTypeFormWin',
					title : '图书类别详细信息',
					iconCls : 'menu-book-type',
					height:100,
					width: 300,
					buttonAlign : 'center',
					buttons : this.buttons
				});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		//表单
		this.formPanel = new Ext.FormPanel({
					layout : 'form',
					border:false,
					bodyStyle : 'padding:5px;',
					defaults : {
						anchor : '96%,96%'
					},
					defaultType : 'textfield',
					items : [ {
							name : 'bookType.typeId',
							xtype : 'hidden',
							value : this.typeId == null ? '' : this.typeId
						}, {
							fieldLabel : '图书类别名称',
							name : 'bookType.typeName',
							allowBlank : false,
							blankText : '图书类别不能为空'
						}]
				});

		if (this.typeId != null && this.typeId != 'undefined') {
			this.formPanel.loadData({
				url : __ctxPath + '/admin/getBookType.do?typeId=' + this.typeId,
				preName:'bookType',
				root:'data'
			});
		}
		//按钮组
		this.buttons = [{
			text : '保存',
			iconCls : 'btn-save',
			scope:this,
			handler : this.save
		}, {
			text : '取消',
			iconCls : 'btn-cancel',
			scope:this,
			handler : function() {
					this.close();
			}
		}]
	},
	//保存
	save:function(){
		$postForm({
			formPanel : this.formPanel,
			waitMsg : '正在提交数据...',
			scope : this,
			url : __ctxPath + '/admin/saveBookType.do',
			callback : function(fp, action) {
				if (this.callback) {
					this.callback.call(this.scope);
				}
				this.close();
			}
		});
	}//end of save
});