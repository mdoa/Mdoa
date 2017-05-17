/**
 * 手机邮箱列表 by wdr
 */

Ext.define('mobile.myEmail.MyEmailBox', {
	extend : 'Ext.List',
	id : 'MyEmailBox',

	constructor : function(config) {
		config = config || {};
		var username = config.username;
		var mailBoxType = config.mailBoxType;
		this.userId = config.userId;
		this.forword = '';
		// debugger
		this.isout = false;
		if (mailBoxType == 'inEmail') {
			url = __ctxPath + '/htmobile/myEmail.do'
			this.forword = 'mobile.myEmail.MyEmail'
			this.isout = false;
		} else {
			url = __ctxPath + '/htmobile/countMyOutMail.do?setId='
					+ mailBoxType
			this.forword = 'mobile.myEmail.MyEmail'
			this.isout = true;
		}
		var toolbar = Ext.create('Ext.Toolbar', {
					docked : 'bottom',
					items : [{
								xtype : 'spacer'
							}, {
								xtype : 'button',
								text : '写邮件',
								iconMask : true,
								scope : this,
								flex : .5,
								handler : this.newEmail
							}, {
								xtype : 'spacer'
							}]
				});

		var emailBoxStore = Ext.create('Ext.data.Store', {
					model : 'emailbox',
					data : [{
								mailType : 1,
								title : '收件箱'
							}, {
								mailType : 2,
								title : '发件箱'
							}, {
								mailType : 3,
								title : '草稿箱'
							}, {
								mailType : 4,
								title : '垃圾箱'
							}]
				});
		Ext.apply(config, {
			title : config.title,
			store : emailBoxStore,
			itemTpl : "{title}<span style='float:right;font-size:18px;color:#009ad6;'>></span>",
			listeners : {
				itemsingletap : this.itemsingletap
			},
			items : toolbar
		});
		this.callParent([config]);
	},
	itemsingletap : function(obj, index, target, record) {
		var store = this.getStore();
		var isout = this.isout;
		var mailBoxType = this.config.mailBoxType;
		mobileNavi.push(Ext.create(obj.forword, {
					isDel : false,
					title : record.get('title'),
					mailType : record.get('mailType'),
					mailBoxType : mailBoxType,
					isout : isout,
					userId : this.userId
				}));

	},
	newEmail : function() {
		var store = this.getStore();
		var isout = this.isout;
		mobileNavi.push(Ext.create('mobile.myEmail.MyNewEmail', {
					data : '',
					userId : this.userId,
					isout : isout,
					opt : 1,
					mailBoxStore : store
				}))

	}
})
Ext.define('emailbox', {
			extend : 'Ext.data.Model',
			config : {
				fields : [{
							name : 'mailType',
							type : 'string'
						}, {
							name : 'title',
							type : 'string'
						}]
			}
		})