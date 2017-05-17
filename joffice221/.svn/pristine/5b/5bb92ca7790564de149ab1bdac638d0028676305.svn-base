Ext.ns('SerialNumberForm');

/**
 * @author zxh
 * @createtime
 * @class SerialNumberForm
 * @extends Ext.Window
 * @description 流水号表单
 * @company 宏天软件
 */
SerialNumberForm = Ext.extend(Ext.Window, {
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		SerialNumberForm.superclass.constructor.call(this, {
					id : 'SerialNumberFormWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					height : 300,
					width : 500,
					maximizable : true,
					title : '流水号详细信息',
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
								text : '预览流水号',
								iconCls : 'btn-detail',
								scope : this,
								handler : this.preview
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
		// 修改提示宽度
		Ext.apply(Ext.QuickTips.getQuickTip(), {
					maxWidth : 500
				});

		Date.patterns = {
			ShortDate : "Ymd"
		};
		var dt = new Date();
		var time = dt.format(Date.patterns.ShortDate) + '-00001';
		var tips = '<pre>格式形如：<font style="color:red;">{yyyy}{MM}{DD}-{NO}</font>==>'
				+ time
				+ '</br>'
				+ '格式符	输出                    说明</br>'
				+ '*******************************************</br>'
				+ 'yyyy	2012		4位数字表示的当前年数</br>'
				+ 'yy	12		2位数字表示的当前年数</br>'
				+ 'MM	01		当前的月份，两位数字，不足位补“0”</br>'
				+ 'mm	1 		当前的月份，不补“0”</br>'
				+ 'DD	10 		月份中的天数，两位数字，不足位补“0” </br>'
				+ 'dd	1 		月份中的天数，不补“0”</br>'
				+ 'HH	15		24小时制表示的当前小时数，不足位补“0”</br>'
				+ 'hh	15		24小时制表示的当前小时数，不补“0”</br>'
				+ 'GG	03		12小时制表示的当前小时数，不足位补“0”</br>'
				+ 'gg	3 		12小时制表示的当前小时数，不补“0”</br>'
				+ 'MI	5 		不足位补“0”的分钟数</br>'
				+ 'mi	05 		不补“0”的分钟数</br>'
				+ 'SS	08 		不足位补“0”的秒数</br>'
				+ 'ss	8 		不补“0”的秒数</br>'
				+ 'SSS	023		不足位补“0”的毫秒数</br>'
				+ 'sss	23 		不补“0”的毫秒数</br>'
				+ 'FF	January		当前月份的完整拼写</br>'
				+ 'ff	Jan		当前月份的缩写，前三个字母</br>'
				+ 'WW	Wednesday	当前星期的完整拼写 </br>'
				+ 'ww	Wed		当前星期的缩写，前三个字母</br>'
				+ 'YW	52		一年之中的周数，两位数字（00～53）</br>'
				+ 'WD	00 		 一周之中的天数（0～6）（周日为0, 周六为6）</br>'
				+ 'AA	PM		大写的“AM”和“PM”</br>'
				+ 'aa	pm		小写的“am”和“pm”</br>'
				+ '<font style="color:red;">NO</font>	00001		 流水号</br>'
				+ '<font style="color:red;">RN</font>	08625		 随机号 </pre>';

		// 流水号表单
		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			bodyStyle : 'padding:10px',
			border : false,
			autoScroll : true,
			defaults : {
				anchor : '96%,96%',
				allowBlank : false
			},
			defaultType : 'textfield',
			items : [{
						name : 'serialNumber.numberId',
						xtype : 'hidden',
						value : this.numberId == null ? '' : this.numberId
					}, {
						fieldLabel : '当前时间',
						name : 'serialNumber.curDate',
						xtype : 'hidden'
					}, {
						fieldLabel : '名称',
						name : 'serialNumber.name',
						maxLength : 50,
						listeners : {
							scope : this,
							'change' : this.changeName
						}
					}, {
						fieldLabel : '别名',
						name : 'serialNumber.alias',
						maxLength : 20
					}, {
						xtype : 'container',
						layout : 'column',
						fieldLabel : '规则',
						items : [{
									columnWidth : .99,
									name : 'serialNumber.regulation',
									value : '{yyyy}{MM}{DD}{NO}',
									xtype : 'textfield',
									allowBlank : false
								}, {
									xtype : 'button',
									iconCls : 'help',
									iconAlign : 'left',
									width : 10,
									trackMouse : true,
									anchor : 'left',
									tooltip : tips,
									scope : this,
									handler : function() {
										Ext.MessageBox.show({
											title : '提示信息',
											msg : tips,
											buttons : Ext.MessageBox.OK
												// icon : Ext.MessageBox.INFO
											});
									}
								}]

					}, {
						xtype : 'container',
						layout : 'column',
						fieldLabel : '生成方式',
						items : [{
							columnWidth : .99,
							xtype : 'combo',
							hiddenName : 'serialNumber.genType',
							mode : 'local',
							editable : false,
							triggerAction : 'all',
							store : [['1', '每日生成'], ['2', '每月生成'],
									['3', '每年生成'], ['4', '递增']],
							value : '1',
							allowBlank : false
						}, {
							xtype : 'button',
							// text : '说明',
							iconCls : 'help',
							iconAlign : 'left',
							width : 10,
							trackMouse : true,
							anchor : 'left',
							tooltip : '<pre>流水号生成规则： </br><font style="color:red;">每日生成</font>：每日从初始值开始计数。</br><font style="color:red;">递增</font>：流水号一直增加。</pre>'
						}]
					}, {
						xtype : 'container',
						layout : 'column',
						fieldLabel : '流水号长度',
						items : [{
									columnWidth : .99,
									name : 'serialNumber.noLength',
									allowBlank : false,
									value : 5,
									xtype : 'numberfield'
								}, {
									xtype : 'button',
									iconCls : 'help',
									iconAlign : 'left',
									width : 10,
									trackMouse : true,
									anchor : 'left',
									tooltip : '这个长度表示当前流水号的长度数，只包括流水号部分{NO}。</br>比如：长度为5当前流水号为5，则在流水号前补4个0，表示为00005。 '
								}]

					}, {
						xtype : 'container',
						layout : 'column',
						fieldLabel : '步长',
						items : [{
									columnWidth : .99,
									name : 'serialNumber.step',
									xtype : 'numberfield',
									value : 1
								}, {
									xtype : 'button',
									iconCls : 'help',
									iconAlign : 'left',
									width : 10,
									trackMouse : true,
									anchor : 'left',
									tooltip : '流水号每次递增的数字，默认步长为1。</br>比如步长为2，则每次获取流水号则在原来的基础上加2。'
								}]
					}, {
						fieldLabel : '初始值',
						name : 'serialNumber.initValue',
						xtype : 'numberfield',
						value : 1
					}, {
						fieldLabel : '当前值',
						name : 'serialNumber.curValue',
						value : 1,
						xtype : this.numberId == null ? 'hidden' : 'textfield'
					}]
		});
		// 加载表单对应的数据
		if (!Ext.isEmpty(this.numberId)) {
			this.formPanel.loadData({
						url : __ctxPath
								+ '/system/getSerialNumber.do?numberId='
								+ this.numberId,
						root : 'data',
						preName : 'serialNumber'
					});
		}

	},// end of the initcomponents
	/**
	 * 改变名称 动态获取名字拼音
	 * 
	 * @param field
	 * @param newValue
	 * @param oldValue
	 */
	changeName : function(field, newValue, oldValue) {
		var formPanel = this.formPanel;
		var alias = formPanel.getCmpByName('serialNumber.alias')
		if (Ext.isEmpty(alias.getValue())) {
			Ext.Ajax.request({
						url : __ctxPath + '/system/getPinyinGlobalType.do',
						params : {
							typeName : newValue
						},
						scope : this,
						success : function(resp, options) {
							var result = Ext.decode(resp.responseText);
							alias.setValue(result.nodeKey);
						},
						failure : function(resp, options) {
						}
					});
		}
	},
	/**
	 * 预览流水号
	 */
	preview : function() {
		if (this.formPanel.getForm().isValid()) {
			this.formPanel.getForm().submit({
						scope : this,
						url : __ctxPath + '/system/previewSerialNumber.do',
						method : 'post',
						waitMsg : '等待生成预览数据...',
						success : function(fp, action) {
							var result = action.result;
							if (result.success) {
								Ext.MessageBox.show({
											title : '预览流水号',
											msg : result.msg,
											buttons : Ext.MessageBox.OK
										});
							}
						},
						failure : function(fp, action) {
							Ext.MessageBox.show({
										title : '操作信息',
										msg : '信息保存出错，请联系管理员！',
										buttons : Ext.MessageBox.OK,
										icon : 'ext-mb-error'
									});
						}
					})
		}

	},
	/**
	 * 重置
	 * 
	 */
	reset : function() {
		this.formPanel.getForm().reset();
	},
	/**
	 * 取消
	 * 
	 */
	cancel : function() {
		this.close();
	},
	/**
	 * 保存记录
	 */
	save : function() {
		var alias = this.formPanel.getCmpByName('serialNumber.alias');
		if (this.formPanel.getForm().isValid()) {
			Ext.Ajax.request({
						url : __ctxPath + '/system/checkAliasSerialNumber.do',
						params : {
							'numberId' : this.formPanel
									.getCmpByName('serialNumber.numberId')
									.getValue(),
							'alias' : alias.getValue()
						},
						scope : this,
						method : 'POST',
						success : function(response, options) {
							var result = Ext.util.JSON
									.decode(response.responseText);
							if (result.success) {
								$postForm({
											formPanel : this.formPanel,
											scope : this,
											url : __ctxPath
													+ '/system/saveSerialNumber.do',
											callback : function(fp, action) {
												if (this.callback) {
													this.callback
															.call(this.scope);
												}
												this.close();
											}
										});
							} else {
								Ext.ux.Toast.msg('操作信息', result.message);
								alias.focus(true);
							}
						},
						failure : function(response, options) {
							Ext.ux.Toast.msg('操作信息', '操作出错，请联系管理员！');
						}
					});
		}
	}// end of save

});