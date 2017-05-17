/**
 * @author:hcy
 * @class OutMailSetView
 * @extends Ext.Panel
 * @description [OutMailSetView]管理
 * @company 杭州梦德软件有限公司
 * @createtime:2010-12-26
 */
OutMailSetView = Ext.extend(Ext.Panel, {
			// 构造函数
			constructor : function(conf) {
				Ext.applyIf(this, conf);
				// 初始化组件
				this.initUI();
				// 调用父类构造
				OutMailSetView.superclass.constructor.call(this, {
							id : 'OutMailSetView',
							title : '外部邮箱配置',
							autoScroll : true,
							iconCls : 'menu-mail_send',
							region : 'center',
							layout : 'border',
							items : [this.searchPanel, this.gridPanel]
						});
			},// end of constructor
			// 初始化组件
			initUI : function() {
				this.colKeys = new Array('id', 'userId','isDefault', 'userName','accountName',
						'mailAddress', 'mailPass', 'smtpHost', 'smtpPort',
						'popHost', 'popPort');
				this.colNames = new Array('id', 'userId', '是否默认','用户名称', '帐号名称','外部邮件地址',
						'密码', 'smtp主机', 'smtp端口', 'pop主机', 'pop端口');
				// 初始化搜索条件Panel
				this.searchPanel = new HT.SearchPanel({
							layout : 'form',
							region : 'north',
							colNums : 4,
							keys : {
								key : Ext.EventObject.ENTER,
								fn : this.search,
								scope : this
							},
							items : [{
										fieldLabel : '用户名称',
										xtype : 'textfield',
										name : 'userName',
										maxLength : 125,
										width : 160
									}, {
										xtype : 'button',
										text : '查询',
										iconCls : 'search',
										scope : this,
										handler : this.search
									}, {
										xtype : 'button',
										text : '清空',
										iconCls : 'reset',
										scope : this,
										handler : this.clear
									}]
						});// end of the searchPanel
				this.gridPanel = new HT.EditorGridPanel({
							region : 'center',
							tbar : [{
										text : '保存外部邮箱配置',
										iconCls : 'btn-save',
										scope : this,
										handler : this.saveRecord
									}],
							clicksToEdit : 1,
							url : __ctxPath + "/system/listOutMailSet.do",
							fields : [{
										name : 'setId',
										type : 'int'
									}, {
										name : 'reuserId',
										mapping : 'userId',
										type : 'int'
									}, 'accountName','userName', 'mailAddress', 'mailPass',
									'smtpHost', 'smtpPort', 'popHost',
									'popPort','isDefault'],
							columns : [{
										header : 'setId',
										dataIndex : 'setId',
										hidden : true
									}, {
										header : 'userId',
										dataIndex : 'reuserId',
										hidden : true
									},{
										header : 'isDefault',
										dataIndex : 'isDefault',
										hidden : true
									}, {
										header : '用户名称',
										dataIndex : 'userName'
									}, {
										header : '帐号名称',
										dataIndex : 'accountName',
										editor : new Ext.form.TextField()
									}, {
										header : '外部邮件地址',
										dataIndex : 'mailAddress',
										vtype : 'email',
										editor : new Ext.form.TextField({
													allowBlank : false,
													vtype : 'email',
													blankText : '请输入外部邮件地址'
												})
									}, {
										header : '密码',
										dataIndex : 'mailPass',
										renderer : function(value) {
											var length = value.length;
											var str = '';
											for (var i = 0; i < length; i++) {
												str += '*';
											}
											return str;
										},
										editor : new Ext.form.TextField({
													allowBlank : false,
													inputType : 'password'
												})
									}, {
										header : 'smtp主机',
										dataIndex : 'smtpHost',
										editor : new Ext.form.TextField({
													allowBlank : false
												})
									}, {
										header : 'smtp端口',
										dataIndex : 'smtpPort',
										vtype : 'alphanum',
										editor : new Ext.form.NumberField({
													allowBlank : false,
													vtype : 'alphanum',
													blankText : '端口必须是填写数字'
												})
									}, {
										header : 'pop主机',
										dataIndex : 'popHost',
										editor : new Ext.form.TextField({
													allowBlank : false
												})
									}, {
										header : 'pop端口',
										dataIndex : 'popPort',
										vtype : 'alphanum',
										blankText : '端口必须是填写数字',
										editor : new Ext.form.NumberField({
													allowBlank : false,
													vtype : 'alphanum',
													blankText : '端口必须是填写数字'
												})
									}],
								defaults : {
									sortable : true,
									menuDisabled : false,
									width : 100
								}
						});

			},// end of the initComponents()

			/**
			 * 按条件搜索
			 */
			search : function() {
				$search({
							searchPanel : this.searchPanel,
							gridPanel : this.gridPanel
						});
			},

			/**
			 * 清空
			 */
			clear : function() {
				this.searchPanel.getForm().reset();
			},
			/**
			 * 保存记录
			 */
			saveRecord : function() {
				var params = [];
				var grid = this.gridPanel;
				var store = grid.getStore();
				var selectRecords = grid.getSelectionModel().getSelections();
				if (selectRecords.length == 0) {
					Ext.ux.Toast.msg("信息", "请选择要保存的记录！");
					return;
				}
				
				for (var i = 0; i < selectRecords.length; i++) {
					var data = selectRecords[i].data;
					for (var j = 0; j < store.getCount(); j++) {
						var record = store.getAt(j);
						if (record.data.reuserId == data.reuserId&&record.data.id==data.id) {
							for (var idx = 3; idx < this.colKeys.length; idx++) {
								var val = record.get(this.colKeys[idx]);
								if (Ext.isEmpty(val)) {
									Ext.ux.Toast.msg('信息', '【'
													+ record.data.userName
													+ '】用户的['
													+ this.colNames[idx]
													+ '] 不能为空');
									return;
								}
							}
							if (Ext.isEmpty(record.data.id)
									|| record.data.id == 0) { // 设置未保存的id标记，方便服务端进行gson转化
								record.set('id', -1);
							}
							if (record.dirty) {
								params.push(record.data);
							}
//							break;
						}
					}
				}
				if (params.length == 0) {
					Ext.ux.Toast.msg('信息', '没有对数据进行任何更改');
					return;
				}

				Ext.Ajax.request({
							method : 'post',
							url : __ctxPath + '/system/saveOutMailSet.do',
							params : {
								data : Ext.encode(params)
							},
							success : function(request) {
								Ext.ux.Toast.msg('操作信息', '成功设置外部邮箱！');
								store.reload();
								grid.getView().refresh();
							},
							failure : function(request) {
								Ext.MessageBox.show({
											title : '操作信息',
											msg : '信息保存出错，请联系管理员！',
											buttons : Ext.MessageBox.OK,
											icon : 'ext-mb-error'
										});
							}
						});
			}
		});
