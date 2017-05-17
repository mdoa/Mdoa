Ext.ns("App");

/**
 * 登陆界面
 * @author zxh
 * @class App.LoginWin
 * @extends Ext.Window
 */
App.LoginWin = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		// 必须先初始化组件
		this.initUI();
		App.LoginWin.superclass.constructor.call(this, {
					id : 'LoginWin',
					title : '        '+'用户登录',
					bodyStyle :'background-color:white;',
					border : false,
					closable : false,
					resizable : true,
					buttonAlign : 'center',
					height : 275,
					width : 460,
					layout : {
						type : 'vbox',
						align : 'stretch'
					},
					keys : [{
								key : Ext.EventObject.ENTER,
								scope : this,
								fn : this.loginHandler
							}, {
								key : Ext.EventObject.ESC,
								scope : this,
								fn : this.loginReset
							}],
					items : [{
								xtype : 'panel',
								border : false,
								/*bodyStyle : 'padding-left:60px;background:url(../images/md-top-bar.png) no-repeat',*/
								html : '<img style="width:100%;" src="images/md-top-bar.png" />',
								height : 50
							}, {
								xtype : 'panel',
								border : false,
								layout : 'column',
								items : [this.formPanel, {
									xtype : 'panel',
									border : false,
									columnWidth : .25
									/*html:'<img src="images/loginbar_bg.png"/>',
									bodyStyle:'background-color:red'*/
									/*html : '<img src="' + __ctxPath
											+ '/images/login-user.jpg"/>'*/
								}]
							}],
					buttons : [{
								text : '登录',
								iconCls : 'btn-login',
								scope : this,
								handler : this.loginHandler
							}, {
								text : '重置',
								iconCls : 'btn-login-reset',
								scope : this,
								handler : this.loginReset
							}]
				});
	},// end of the constructor
	// 初始化组件
	initUI : function() {
		// 登陆表单
		this.formPanel = new Ext.form.FormPanel({
			id : 'LoginFormPanel',
			bodyStyle : 'padding-top:6px;padding-left:70px',
			defaultType : 'textfield',
			columnWidth : .75,
			labelAlign : 'right',
			labelWidth : 55,
			labelPad : 0,
			border : false,
			layout : 'form',
			defaults : {
				style : 'margin:0 0 0 0',
				anchor : '90%,120%',
				selectOnFocus : true
			},
			items : [{
						id : 'username',
						name : 'username',
						fieldLabel : '账      号',
						cls : 'text-user',
						allowBlank : false,
						blankText : '账号不能为空',
						value : this.username == null ? '' : this.username
					}, {
						id : 'password',
						name : 'password',
						fieldLabel : '密      码',
						allowBlank : false,
						blankText : '密码不能为空',
						cls : 'text-lock',
						inputType : 'password'
					}, {
						name : 'checkCode',
						id : 'checkCode',
						fieldLabel : '验证码',
						allowBlank : false,
						hidden : true,
						cls : 'text-code',
						blankText : '验证码不能为空'
					}, {
						name : 'curDynamicPwd',
						hidden : true,
						id : 'curDynamicPwd',
						fieldLabel : '令     牌',
						cls : 'text-dynamic',
						blankText : '令牌不能为空'
					}, {
						xtype : 'container',
						id : 'codeContainer',
						layout : 'table',
						defaultType : 'textfield',
						hideLabel : false,
						layoutConfig : {
							columns : 3
						}
					}, {
						xtype : 'container',
						style : 'padding-left:57px',
						layout : 'column',
						items : [{
									xtype : 'checkbox',
									name : '_spring_security_remember_me',
									boxLabel : '记住密码',
									
									checked : this.falg == null
											? false
											: this.falg
								}, {
									xtype : 'panel',
									border : false,
									bodyStyle : 'font-size:12px;padding-left:80px;',
									html :'' /*'<a href="javascript:toSuggestBox()">意见箱</a>'*/
								}]
					}, {
						xtype : 'panel',
						border : false,
						style : 'padding-left:57px;padding-top:5px',
						html : '<a href="'
								+ __ctxPath
								+ '/help/NtkoControlSetup.zip">安装在线office插件(32位IE、火狐和谷歌)</a>'
					},{
						xtype : 'panel',
						border : false,
						style : 'padding-left:57px;padding-top:5px',
						html : '<a href="'
								+ __ctxPath
								+ '/help/joffice_mobile.apk">移动办公(android)</a>'
					}]
		});
		// 验证码
		if (!Ext.isEmpty(this.isCodeEnabled) && this.isCodeEnabled != '1'
				|| this.isCodeEnabled == 'close') {// 不需要验证码
			this.formPanel.remove(Ext.getCmp('checkCode'));
		} else {
			Ext.getCmp('checkCode').show();
			var items = [{
						width : 55,
						xtype : 'label',
						text : '　　　　'// 这里的排序以后再改
					}, {
						width : 150,
						bodyStyle : 'padding-left:40px',
						id : 'loginCode',
						xtype : 'panel',
						border : false,
						html : '<img border="0" height="30" width="150" src="'
								+ __ctxPath + '/CaptchaImg"/>'
					}, {
						width : 55,
						xtype : 'panel',
						border : false,
						bodyStyle : 'font-size:12px;padding-left:12px',
						html : '<a href="javascript:refeshCode()">看不清</a>'
					}];
			var codeContainer = Ext.getCmp('codeContainer');
			codeContainer.add(items);
			codeContainer.doLayout();
		}
		// 动态密码
		if (!Ext.isEmpty(this.isDyPwdEnabled) && this.isDyPwdEnabled != '1'
				|| this.isDyPwdEnabled == 'close') {// 不需要动态密码
			this.formPanel.remove(Ext.getCmp('curDynamicPwd'));
		} else {
			Ext.getCmp('curDynamicPwd').show();
		}
		// 等待渲染完成再聚焦
		this.formPanel.findById("username").focus(true, 1000);
	},
	/**
	 * 登录
	 */
	loginHandler : function() {
		if (this.formPanel.form.isValid()) {
			this.formPanel.form.submit({
						waitTitle : "请稍候",
						waitMsg : '正在登录......',
						url : __ctxPath + '/login.do',
						scope : this,
						success : function(form, action) {
							this.handleLoginResult(action.result);
						},
						failure : function(form, action) {
							this.handleLoginResult(action.result);
							form.findField("password").setRawValue("");
							form.findField("username").focus(true);
						}
					});
		}
	},
	/**
	 * 登陆结果
	 * 
	 * @param {}
	 *            result
	 */
	handleLoginResult : function(result) {
		if (result.success) {
			Ext.getCmp('LoginWin').hide();
			var statusBar = new Ext.ProgressBar({
						text : '正在登录...'
					});
			statusBar.show();
			window.location.href = __ctxPath + '/index.jsp';
		} else {
			Ext.MessageBox.show({
						title : '操作信息',
						msg : result.msg,
						buttons : Ext.MessageBox.OK,
						icon : Ext.MessageBox.ERROR
					});
		}
	},
	/**
	 * 登陆表单重置
	 */
	loginReset : function() {
		this.formPanel.getForm().reset();
	}
});

function refeshCode() {
	var loginCode = Ext.getCmp('loginCode');
	loginCode.body.update('<img border="0" height="30" width="150" src="'
			+ __ctxPath + '/CaptchaImg?rand=' + Math.random() + '"/>');
}
function toSuggestBox() {
	window.open(__ctxPath + '/info/suggest.do', '_blank');
}
