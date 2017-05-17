/**
 * @author
 * @createtime
 * @class ProNodeSetForm
 * @extends Ext.Window
 * @description 查看/编辑任务节点设置
 * @company 宏天软件
 */
ProNodeSetForm = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		// 必须先初始化组件
		this.initUIComponents();
		ProNodeSetForm.superclass.constructor.call(this, {
					id : 'ProNodeSetFormWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					height : 250,
					width : 500,
					maximizable : true,
					title : '查看/编辑任务[' + this.nodeName + ']节点设置',
					buttonAlign : 'center',
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
		// 会签节点设置
		this.assignNodeSetPanel = new Ext.Panel({
			layout : 'form',
			bodyStyle : 'padding:10px',
			border : false,
			autoScroll : true,
			// id : 'TaskSignForm',
			defaults : {
				anchor : '96%,96%'
			},
			defaultType : 'textfield',
			items : [{
						name : 'taskSign.signId',
						xtype : 'hidden'
					}, {
						name : 'taskSign.proNodeSet.setId',
						xtype : 'hidden',
						value : this.setId == null ? '' : this.setId
					}, {
						xtype : 'radiogroup',
						fieldLabel : '票数类型',
						items : [{
									boxLabel : '绝对票数',
									name : 'taskSign.signType',
									inputValue : 1,
									checked : true
								}, {
									boxLabel : '百分比票数',
									name : 'taskSign.signType',
									inputValue : 2
								}],
						listeners : {
							scope : this,
							'change' : function(obj, newV, oldV) {
								var fm = this.assignNodeSetPanel;
								var isTrue = obj.getValue();
								var count = fm
										.getCmpByName('taskSign.voteCounts');
								var percents = fm
										.getCmpByName('taskSign.votePercents');
								if (isTrue == 1) {
									count.show();
									percents.hide();
									percents.setValue(0);
								} else {
									count.hide();
									count.setValue(0);
									percents.show();
								}
							}
						},
						getValue : function() {
							var v;
							if (this.rendered) {
								this.items.each(function(item) {
											if (!item.getValue())
												return true;
											v = item.getRawValue();
											return false;
										});
							} else {
								for (var k in this.items) {
									if (this.items[k].checked) {
										v = this.items[k].inputValue;
										break;
									}
								}
							}
							return v;
						},
						setValue : function(v) {
							if (this.rendered)
								this.items.each(function(item) {
											item.setValue(item.getRawValue() == v);
										});
							else {
								for (var k in this.items) {
									this.items[k].checked = this.items[k].inputValue == v;
								}
							}
						}
					}, {
						fieldLabel : '绝对票数',
						name : 'taskSign.voteCounts',
						xtype : 'numberfield',
						maxLength : 11,
						minValue : 0,
						regex : /^\d*$/,
						regexText : '绝对票数只能输入数字！'
					}, {
						fieldLabel : '百分比票数',
						name : 'taskSign.votePercents',
						xtype : 'numberfield',
						minValue : 0,
						maxValue : 100,
						maxLength : 11,
						// disabled : true,
						hidden : true
					}, {
						fieldLabel : '决策方式',
						hiddenName : 'taskSign.decideType',
						allowBlank : false,
						valueField : 'id',
						displayField : 'name',
						xtype : 'combo',
						store : [['2', '拒绝'], ['1', '通过']],
						emptyText : '--请选择决策方式--',
						triggerAction : 'all',
						editable : false,
						width : ' 96%'
					}]
		});

		// 会签节点设置
		this.assignNodePanel = new Ext.form.FieldSet({
					title : '会签节点设置',
					autoHeight : true,
					collapsible : false,
					height : 300,
					hidden : true,
					items : [this.assignNodeSetPanel]
				});
		// 节点设置
		this.nodeSetPanel = new Ext.Panel({
			layout : 'form',
			bodyStyle : 'padding:10px',
			border : false,
			autoScroll : true,
			defaults : {
				anchor : '96%,96%'
			},
			defaultType : 'textfield',
			items : [{
						name : 'proNodeSet.setId',
						xtype : 'hidden',
						value : this.setId == null ? '' : this.setId
					}, {
						fieldLabel : '节点名称',
						name : 'proNodeSet.nodeName',
						readOnly : true,
						allowBlank : false,
						maxLength : 256
					}, {
						xtype : 'radiogroup',
						fieldLabel : '节点类型',
						items : [{
									boxLabel : '普通节点',
									name : 'proNodeSet.nodeType',
									inputValue : 1,
									checked : true
								}, {
									boxLabel : '会签节点',
									name : 'proNodeSet.nodeType',
									inputValue : 2
								}],
						listeners : {
							scope : this,
							'change' : function(obj, newV, oldV) {
								var nodeType = newV.getRawValue();
								if (nodeType == 2) {
									this.assignNodePanel.show();
								} else {
									this.assignNodePanel.hide();
								}
							}
						}
					}]
		});

		// 表单面板
		this.formPanel = new Ext.FormPanel({
					autoHeight : true,
					layout : 'form',
					border : false,
					items : [this.nodeSetPanel, this.assignNodePanel]
				});
		// 加载表单对应的数据
		if (!Ext.isEmpty(this.setId)) {
			this.nodeSetPanel.loadData({
				url : __ctxPath + '/flow/getProNodeSet.do?setId=' + this.setId,
				root : 'data',
				preName : 'proNodeSet',
				scope : this,
				success : function(response, option) {
					var results = Ext.util.JSON.decode(response.responseText).data;
					if (results.nodeType == 2) 
						this.assignNodePanel.show();
				}
			});

			this.assignNodeSetPanel.loadData({
						url : __ctxPath + '/flow/findTaskSign.do?setId='
								+ this.setId,
						root : 'data',
						preName : 'taskSign'
					});
		}

	},// end of the initcomponents
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
		var formPanel = this.formPanel;
		var nodeType = this.nodeSetPanel.getCmpByName('proNodeSet.nodeType')
				.getValue();
		if (nodeType == 2) {
			if (!formPanel.getForm().isValid()){
				Ext.ux.Toast.msg('操作信息', '请写检查表单数据！');
				return;
			}
		}
		formPanel.getForm().submit({
					url : __ctxPath + '/flow/infoSaveProNodeSet.do',
					method : 'post',
					waitMsg : '正在提交数据...',
					scope : this,
					success : function(fp, action) {
						Ext.ux.Toast.msg('操作信息', '信息成功保存！');
						this.close();
					},
					failure : function(fp, action) {
						if(action.result!=null){
							HT.failureMessage();
						}
						Ext.ux.Toast.msg('操作信息', '信息成功保存！');
						this.close();
					}
				});
	}// end of save

});