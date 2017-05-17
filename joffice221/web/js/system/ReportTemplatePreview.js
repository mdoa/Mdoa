var ReportTemplatePreview = Ext.extend(Ext.Panel, {
	// 构造函数
	constructor : function(conf) {
		Ext.applyIf(this, conf);
		this.initUI();
		ReportTemplatePreview.superclass.constructor.call(this, {
					id : 'ReportPreview' + this.reportId,
					title : '查看-' + this.title,
					iconCls : 'menu-report',
					autoScroll : false,
					autoHeight : true,
					border : false,
					frame : false,
					items : [this.formPanel, this.toolbar, this.reportTempPanel],
					listeners : {
						scope : this,
						'afterrender' : this.afterrender
					}
				});
	},
	// 初始化组件
	initUI : function() {
		this.reportTempPanel = new Ext.Panel({
			// tbar : toolbar,
			autoHeight : false,
			autoWidth : false,
			autoScroll : false,
			border : false,
			height : 415,
			autoLoad : false
				// // 根据页面传来reportId，动态加载报表模版路径，显示报表
				// html : '<iframe src="'
				// + __ctxPath
				// + '/report/report.jsp?reportId='
				// + this.reportId
				// + '" height="98%" width="98%" scrolling="auto"></iframe>'
			});

		this.searchButton = new Ext.Button({
					text : '查询',
					iconCls : 'search',
					scope : this,
					handler : this.search,
					width : 20
				});
		// 参数Form
		this.formPanel = new Ext.FormPanel({
					frame : false,
					border : false,
					layout : 'hbox',
					// style : 'padding:50px 50px 50px 50px',
					layoutConfig : {
						padding : '5',
						align : 'middle'
					},
					defaults : {
						anchor : '10%,10%',
						xtype : 'label',
						margins : {
							top : 0,
							right : 4,
							bottom : 4,
							left : 4
						}
					},
					items : [],
					listeners : {
						scope : this,
						'afterrender' : function() {
							this.searchButton.on({
								scope:this,
								'click': this.search
								});
						}
					}
				}).show();

		this.toolbar = new Ext.Toolbar({
					// id : 'createToolbar',
					autoWidth : true,
					layout : 'hbox',
					defaults : {

						anchor : '10%,10%'
					},
					items : [{
								text : 'pdf',
								iconCls : 'btn-pdf',
								scope : this,
								handler : this.submit.createDelegate(this,
										['pdf'])
							}, '-', {
								text : 'excel',
								iconCls : 'btn-xls',
								scope : this,
								handler : this.submit.createDelegate(this,
										['xls'])
							}, '-', {
								text : 'html',
								iconCls : 'btn-ie',
								scope : this,
								handler : this.submit.createDelegate(this,
										['html'])
							}]
				});
		// 参数容器
		var container = new Ext.Container({
					layout : 'table',
					layoutConfig : {
						columns : 8
					},
					defaults : {
						bodyStyle : 'padding:20px'
					}

				});

		// 取得查询字段
		Ext.Ajax.request({
					url : __ctxPath + "/system/loadReportTemplate.do?reportId="
							+ this.reportId,
					method : 'POST',
					scope : this,
					success : function(response, options) {
						var dateSort = 0;// 日期序列
						var nowDate = new Date();
						var nowMonth = new Date(nowDate.getFullYear(), nowDate
										.getMonth(), 1);
						var nextMon = new Date(nowDate.getFullYear(),
								(nowDate.getMonth()) + 1, 1);

						this.formPanel.removeAll(true);
						var object = Ext.util.JSON.decode(response.responseText);
						for (var i = 0; i < object.data.length; i++) {
							var text = object.data[i].paramName;
							var xtype = object.data[i].paramType;
							var name = object.data[i].paramKey;
							var value = object.data[i].defaultVal;
							var paramTypeStr = object.data[i].paramTypeStr;
							var paramTypeExt = Ext.decode(paramTypeStr);

							var label = new Ext.form.Label({
										text : text + ': '
									});

							switch (xtype) {
								case 'combo' :
									Ext.apply(paramTypeExt, {
												allowBlank : true,
												fieldLabel : '',
												hiddenName : name,
												id : name + new Date(),
												width : 100
											});
									container.add(label);
									var combo = new Ext.form.ComboBox(paramTypeExt);
									combo.getStore().on("load", function(){
										combo.setValue(value);
									});
									container.add(combo);
									break;
								case 'diccombo' :
									Ext.apply(paramTypeExt, {
												allowBlank : true,
												value : value,
												fieldLabel : '',
												name : name,
												id : name + new Date(),
												width : 100
											});
									container.add(label);
									var diccombo = new DicCombo(paramTypeExt);
									container.add(diccombo);
									break;
								case 'datetimefield' :

									var s = dateSort % 2;
									switch (s) {
										case 0 :
											Ext.apply(paramTypeExt, {
														allowBlank : false,
														value : nowMonth,
														format : 'Y-m-d H:i:s',
														fieldLabel : '',
														name : name,
														id : name + new Date(),
														width : 200
													});
											container.add(label);
											container.add(paramTypeExt);
											dateSort++;
											break;
										case 1 :
											Ext.apply(paramTypeExt, {
														allowBlank : false,
														value : nextMon,
														format : 'Y-m-d H:i:s',
														fieldLabel : '',
														name : name,
														id : name + new Date(),
														width : 200
													});
											container.add(label);
											container.add(paramTypeExt);
											dateSort++;
											break;
									}

									break;
								case 'datefield' :
									var s = dateSort % 2;
									switch (s) {
										case 0 :
											Ext.apply(paramTypeExt, {
														allowBlank : false,
														value : nowMonth,
														format : 'Y-m-d',
														fieldLabel : '',
														name : name,
														id : name + new Date(),
														width : 100
													});
											container.add(label);
											container.add(paramTypeExt);
											dateSort++;
											break;
										case 1 :
											Ext.apply(paramTypeExt, {
														allowBlank : false,
														value : nextMon,
														format : 'Y-m-d',
														fieldLabel : '',
														name : name,
														id : name + new Date(),
														width : 100
													});
											container.add(label);
											container.add(paramTypeExt);
											dateSort++;
											break;
									}

									break;
								default :
									Ext.apply(paramTypeExt, {
												allowBlank : true,
												value : value,
												fieldLabel : '',
												name : name,
												id : name + new Date(),
												width : 100
											});
									container.add(label);
									container.add(paramTypeExt);
							}
						}

						container.add(this.searchButton);

						this.formPanel.add(container);
						container.doLayout(true);
						this.formPanel.doLayout(true);
					}
				});
	},
	/**
	 * 重新布局，并自动加载
	 * 
	 * @param {}
	 *            panel
	 */
	afterrender : function(panel) {
		// 高度布局
		var f_h = this.formPanel.getHeight();
		var t_h = this.toolbar.getHeight();
		var a_h = Ext.getCmp('centerTabPanel').getInnerHeight();
		this.reportTempPanel.setHeight(a_h - (f_h + t_h));
		this.reportTempPanel.doLayout();
		// 自动加载
		this.searchButton.fireEvent('click');

	},
	/**
	 * 查询
	 */
	search : function() {
		if (this.formPanel.getForm().isValid()) {
			this.formPanel.getForm().submit({
				waitMsg : '正在提交查询',
				url : __ctxPath + '/system/submitReportTemplate.do',
				method : 'post',
				scope : this,
				success : function(form, action) {
					var object = Ext.util.JSON
							.decode(action.response.responseText);
					var temp = this.reportTempPanel;
					temp.body
							.update('<iframe src="'
									+ __ctxPath
									+ '/report/report.jsp?reportId='
									+ this.reportId
									+ encodeURI(encodeURI(object.data))
									+ '" height="98%" width="98%" scrolling="auto"></iframe>');

				},
				failure : function(form, action) {

				}
			});
		}
	},
	/**
	 * 提交
	 * @param {}
	 *            reportType
	 */
	submit : function(reportType) {
		if (this.formPanel.getForm().isValid()) {
			this.formPanel.getForm().submit({
				waitMsg : '正在提交查询',
				url : __ctxPath + '/system/submitReportTemplate.do',
				method : 'post',
				scope : this,
				success : function(form, action) {
					var object = Ext.util.JSON
							.decode(action.response.responseText);
					window.open(__ctxPath + '/report/report.jsp?reportId='
							+ this.reportId + encodeURI(encodeURI(object.data))
							+ '&reportType=' + reportType);

				},
				failure : function(form, action) {

				}
			});
		}
	}
});