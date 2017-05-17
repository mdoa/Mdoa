Ext.ns('App');

App.TreeLoader = Ext.extend(Ext.ux.tree.XmlTreeLoader, {
			processAttributes : function(attr) {
				if (attr.tagName == 'Function') {
					attr.leaf = true;
				} else if (attr.tagName == 'Item') {
					attr.loaded = true;
					attr.expanded = true;
				} else if (attr.tagName == 'Items') {
					attr.loaded = true;
					attr.expanded = true;
				}
			}
		});
/**
 * 角色授权窗口
 * 
 * @class okForm
 * @extends Ext.Window
 */
RoleGrantRightView = Ext.extend(Ext.Window, {
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 必须先初始化组件
				this.initUIComponents();
				RoleGrantRightView.superclass.constructor.call(this, {
							id : 'RoleGrantRightView',
							title : '为角色[' + this.roleName + ']授权',
							layout : 'fit',
							modal : true,
							width : 600,
							height : 400,
							buttonAlign : 'center',
							tbar : this.tbar,
							items : [this.roleGrantView],
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
									}]
						});
			},// end of the constructor
			// 初始化组件
			initUIComponents : function() {
				this.tbar = new Ext.Toolbar({
							items : [{
										xtype : 'button',
										text : '展开',
										iconCls : 'btn-expand',
										scope : this,
										handler : function() {
											this.roleGrantView.expandAll();
										}
									}, {
										xtype : 'button',
										text : '收起',
										iconCls : 'btn-collapse',
										scope : this,
										handler : function() {
											this.roleGrantView.collapseAll();
										}
									}]
						});

				this.roleGrantView = new Ext.ux.tree.CheckTreePanel({
							title : '角色授权设置',
							autoScroll : true,
							rootVisible : false,
							loader : new App.TreeLoader({
										dataUrl : __ctxPath
												+ '/system/grantXmlAppRole.do'
									}),
							root : new Ext.tree.AsyncTreeNode({
										expanded : true
									}),
							tools : [{
										id : 'refresh',
										qtip : '重新加载树',
										scope : this,
										handler : this.refresh
									}],
							listeners : {
								scope : this,
								'load' : this.loadRoleGrantView
							}
						});

			},
			/**
			 * 初始化加载权限树
			 */
			loadRoleGrantView : function() {
				Ext.Ajax.request({
							url : __ctxPath + '/system/getAppRole.do',
							method : 'POST',
							params : {
								roleId : this.roleId
							},
							scope : this,
							success : function(response, options) {
								var object = Ext.util.JSON
										.decode(response.responseText);
								// alert(object.data.rights);
								if (object.data.rights != null) {
									this.roleGrantView
											.setValue(object.data.rights);
								}
								this.roleGrantView.expandAll();
							},
							failure : function(response, options) {
								Ext.ux.Toast.msg('操作信息', '加载权限出错！');
							}
						});
			},
			/**
			 * 重新加载树
			 */
			refresh : function() {
				this.roleGrantView.getRootNode().reload();
			},
			/**
			 * 取消
			 */
			cancel : function() {
				this.close();
			},
			/**
			 * 保存信息
			 */
			save : function() {
				Ext.Ajax.request({
							url : __ctxPath + '/system/grantAppRole.do',
							method : 'POST',
							params : {
								roleId : this.roleId,
								rights : this.roleGrantView.getValue()
										.toString()
							},
							scope : this,
							success : function(response, options) {
								Ext.ux.Toast.msg('操作提示',
										'你已经成功为角色[<b>{0}</b>]进行了授权',
										this.roleName);
								this.close();
							},
							failure : function(response, options) {
								Ext.ux.Toast.msg('操作信息', '授权出错，请联系管理员！');
							}
						});
			}
		});
