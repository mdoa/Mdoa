Ext.ns('CompanyView');
/**
 * @author:
 * @class CompanyView
 * @extends Ext.Panel
 * @description 公司信息
 * @compgany 杭州梦德软件有限公司
 * @createtime:2010-08-21
 */
CompanyView = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		CompanyView.superclass.constructor.call(this, {
					id : 'CompanyView',
					title : '公司信息',
					iconCls : 'menu-company',
					layout : 'fit',
					items : this.formPanel
				});
	},// end of constructor

	// 初始化组件
	initUIComponents : function() {
		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			border : false,
			autoScroll : true,
			bodyStyle : 'padding:5px;',
			defaults : {
				anchor : '98%'
			},
			tbar : [{
						text : '保存',
						iconCls : 'btn-save',
						scope : this,
						handler : this.save,
						hidden : !isGranted('_CompanyEdit')
					},'-', {
						text : '关闭',
						iconCls : 'btn-close',
						scope : this,
						handler : this.close
					}],
			defaultType : 'textfield',
			items : [{
						xtype : 'hidden',
						name : 'company.companyId'
					}, {
						xtype : 'container',
						labelAlign : 'top',
						border : false,
						style : 'padding-left:10%;padding-right:10%',
						layout : 'form',
						items : [{
									xtype : 'textfield',
									fieldLabel : '公司编号',
									name : 'company.companyNo',
									anchor : '78%'
								}, {
									xtype : 'textfield',
									fieldLabel : '公司名称',
									name : 'company.companyName',
									anchor : '78%',
									allowBlank : false
								}, {
									layout : 'column',
									border : false,
									anchor : '78%',
									items : [{
										layout : 'form',
										columnWidth : .5,
										border : false,
										style : 'padding-left:0px;',
										items : [{
													xtype : 'textfield',
													fieldLabel : '法人',
													name : 'company.legalPerson',
													anchor : '98%'
												}, {
													xtype : 'textfield',
													fieldLabel : '电话',
													name : 'company.phone',
													anchor : '98%'
												}]
									}, {
										layout : 'form',
										border : false,
										columnWidth : .5,
										items : [{
													fieldLabel : '成立时间',
													xtype : 'datefield',
													format : 'Y-m-d',
													name : 'company.setup',
													anchor : '98%'
												}, {
													xtype : 'textfield',
													fieldLabel : '传真',
													name : 'company.fax',
													anchor : '98%'
												}]
									}]
								}, {
									xtype : 'hidden',
									fieldLabel : ' 公司Logo',
									name : 'company.logo'
								}, {
									xtype : 'container',
									fieldLabel : '公司主页',
									style : 'padding-left:0px;padding-bottom:3px;',
									layout : 'column',
									items : [{
												xtype : 'textfield',
												name : 'company.site',
												width : 300,
												listeners : {
													scope : this,
													'focus' : this.onFocusSite
												}
											}, {
												xtype : 'button',
												text : '访问公司主页',
												scope : this,
												handler : this.visitSite
											}, {
												xtype : 'label',
												width : 80,
												text : '以http://开头'
											}]
								}, {
									xtype : 'container',
									fieldLabel : 'Logo',
									style : 'padding-left:0px;padding-bottom:3px;',
									layout : 'column',
									items : [{
										xtype : 'container',
										border : true,
										style : 'padding-left:0px;',
										layout : 'form',
										height : 58,
										items : [{
											xtype : 'panel',
											height : 55,
											width : 247,
											id : 'LogoPanel',
											autoScroll : true,
											html : '<img src="'
													+ __ctxPath
													+ '/images/default_image_car.jpg" width="100%" height="100%"/>'
										}]
									}, {
										border : false,
										xtype : 'container',
										layout : 'form',
										width : 93,
										style : 'padding-left:3px;',
										items : [{
													xtype : 'button',
													iconCls : 'btn-add',
													text : '上传LOGO',
													scope : this,
													handler : this.uploadLogo
												}, {
													xtype : 'button',
													text : '删除LOGO',
													iconCls : 'btn-delete',
													scope : this,
													handler : this.deleteLogo
												}]

									}, {
										border : false,
										xtype : 'label',
										width : 150,
										html : '<a style="color:red;">请上传比例为247*55的图片,透明底的图片更佳</a>'
									}]
								}, {
									xtype : 'htmleditor',
									fieldLabel : '公司描述',
									name : 'company.companyDesc',
									height : 200,
									anchor : '78%'
								}]
					}]

		});	
		//初始化加载表单数据
		Ext.Ajax.request({
			url : __ctxPath + '/system/checkCompany.do',
			scope : this,
			success : function(result, request) {
				var res = Ext.util.JSON.decode(result.responseText);
				if (res.success) {
					this.formPanel.loadData({
						url:__ctxPath + '/system/listCompany.do',
						preName:'company',
						root:'data',
						scope : this,
						success : function(response, option) {
								var result = Ext.util.JSON
										.decode(response.responseText);
								if (result) {
									var filePath = result.data.logo;
									this.setLogo(filePath);
									}
								},
						failure : function(form, action) {
							Ext.ux.Toast.msg('编辑', '载入失败');
						}
					});
				} else {
					Ext.ux.Toast.msg('提示', '还没填写公司信息');
				}
			},
			failure : function(result, request) {
	
			}
		});
	},//end 初始化组件
	// 保存
	save : function() {
		var formPanel = this.formPanel;
		$postForm({
			formPanel : formPanel,
			waitMsg : '正在修改公司信息...',
			scope : this,
			url : __ctxPath + '/system/addCompany.do',
			callback : function(fp, action) {
				var companyName = formPanel.getCmpByName('company.companyName').getValue();
				Ext.getCmp('toolbarCompanyName')
						.setText(companyName + '--办公协同管理系统');
			}
		});
	},
	// 关闭
	close : function() {
		var tabPanel = Ext.getCmp('centerTabPanel');
		tabPanel.remove('CompanyView');
	},
	//增加http://前缀
	onFocusSite:function(obj){
		if(obj.getValue() == ''){
			obj.setValue('http://');
		}
	},
	// 访问公司主页
	visitSite : function() {
		var formPanel = this.formPanel;
		var s = formPanel.getCmpByName('company.site');
		var site = s.getValue().trim();
		if (site.indexOf('http://') == 0) {
			window.open(site, '_bank');
		} else {
			Ext.ux.Toast.msg('提示信息', '没写完整网址.');
		}
	},
	//设置logo
	setLogo : function(filePath) {
		var logo = this.formPanel.getCmpByName('company.logo');	
		var logoPanel = Ext.getCmp('LogoPanel');
		if (filePath != '' && filePath != null && filePath != 'undefined') {
			var src = __ctxPath + '/attachFiles/' + filePath;
			logo.setValue(filePath);
			logoPanel.body.update('<img src="' + src
					+ '"  width="100%" height="100%"/>');
			logo.src = src;
		} else {//默认Logo
			logo.setValue('');
			logoPanel.body
					.update('<img src="'
							+ __ctxPath
							+ '/images/default_image_car.jpg" width="100%" height="100%" />');
			logo.src = '/images/ht-logo.png';
		}

	},
	//上传logo 窗口
	uploadDialog :   function(){
		var	dialog =  App.createUploadDialog({
				file_cat : 'system/company',
				scope : this,
				callback : function(data) {
					var filePath = '';
					if (data != '' && data != null && data != 'undefined') {
						filePath = data[0].filePath;
					}
					//设置Logo
					this.setLogo(filePath);
				},
				permitted_extensions : ['jpg', 'png']
		})
		return dialog;
	},
		
	// 上传Logo
	uploadLogo : function() {
		var filePath = this.formPanel.getCmpByName('company.logo').getValue();	
		if (filePath != '' && filePath != null && filePath != 'undefined') {
			this.deleteLogo('upload');
		} else {
			this.uploadDialog().show('queryBtn');
		}
	},
	// 删除Logo
	deleteLogo : function(isUpload) {
		var msg = '';
		// logo图片路径
		var filePath = this.formPanel.getCmpByName('company.logo').getValue();		
		if (filePath != null && filePath != ''
				&& filePath != 'undefined') {
			if(isUpload != '' && isUpload == 'upload'){
				msg = '再次上传需要先删除原有图片,';
			}else{
				msg = 'LOGO一旦删除将不可恢复,';
			}
			//删除信息
			Ext.Msg.confirm('信息确认', msg+'是否删除？', function(btn) {
				if (btn == 'yes') {
					Ext.Ajax.request({
						url : __ctxPath + '/system/deleteFileAttach.do',
						method : 'post',
						params : {
							filePath : filePath
						},
						scope : this,
						success : function() {
							//还原默认logo
							this.setLogo('');
							//删除照片
							Ext.Ajax.request({
										url : __ctxPath
												+ '/system/delphotoCompany.do',
										method : 'post',
										scope : this,
										success : function() {
											if(isUpload != '' && isUpload == 'upload'){
												this.uploadDialog().show('queryBtn');
											}
										}
							});
						}
					});
				}
			},this);
		}// end if
		else {
			Ext.ux.Toast.msg('提示信息', '您还未增加照片.');
		}
	}
});

