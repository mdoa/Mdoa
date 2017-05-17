/**
 * @author
 * @createtime
 * @class BorrowRecordForm
 * @extends Ext.Window
 * @description RollFile表单
 * @company 宏天软件
 */
BorrowRecordForm = Ext.extend(Ext.Window, {
	returnStatusName : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		BorrowRecordForm.superclass.constructor.call(this, {
			id : 'BorrowRecordFormWin',
			// layout : 'form',
			items : [this.formPanel, this.fileListGrid],
			modal : true,
			height : 600,
			width : 800,
			maximizable : true,
			title : '借阅文件详细信息',
			buttonAlign : 'center',
			listeners : {
				scope:this,
				'afterrender' : this.afterrenderClick
			},
			buttons : [this.saveButton, this.reSaveButton, this.returnButton,
					this.resetButton, {
						text : '关闭',
						iconCls : 'btn-cancel',
						scope : this,
						handler : this.cancel
					}]
		});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		this.saveButton = new Ext.Button({
					text : '申请',
					iconCls : 'btn-save',
					scope : this,
					handler : function() {
						this.save(0);
					}
				});
		this.reSaveButton = new Ext.Button({
					text : '重新申请',
					iconCls : 'btn-save',
					scope : this,
					handler : function() {
						this.save(0);
					}
				});
		this.returnButton = new Ext.Button({
					text : '归还',
					iconCls : 'btn-collapse',
					scope : this,
					handler : function() {
						this.save(2);
					}
				});
		this.resetButton = new Ext.Button({
					text : '重置',
					iconCls : 'btn-reset',
					scope : this,
					handler : this.reset
				});

		this.formPanel = new Ext.FormPanel({
			layout : 'column',
			bodyStyle : 'padding:30px',
			border : false,
			autoScroll : true,
			defaults : {
				border : false,
				anchor : '98%,98%'
			},
			items : [{
						name : 'borrowRecord.recordId',
						xtype : 'hidden',
						value : this.recordId == null ? '' : this.recordId
					},
					{
						columnWidth : 1,
						layout : 'form',
						labelWidth : 100,
						items : {
							fieldLabel : '借阅编号',
							allowBlank : false,
							anchor : '97%',
							xtype : 'textfield',
							readOnly : true,
							value : new Date().getFullYear()+''+(new Date().getMonth()+1)+''+new Date().getDate()+'-'+new Date().getHours()+new Date().getMinutes(),
							name : 'borrowRecord.borrowNum'
						}
					}, {
						columnWidth : 0.33,
						layout : 'form',
						labelWidth : 100,
						items : {
							fieldLabel : '借阅人',anchor : '95%',
							readOnly : true,
							value : curUserInfo.fullname,
							width : 100,
							xtype : 'textfield',
							name : 'borrowRecord.checkUserName'
						}
					}, {
						columnWidth : 0.33,
						layout : 'form',
						labelWidth : 100,
						items : {
							fieldLabel : '借阅日期',anchor : '95%',
							readOnly : (this.returnStatus == 1)
									? true
									: false,
							allowBlank : false,
							width : 100,
							xtype : 'datefield',
							format : 'Y-m-d',
							value : new Date(),
							name : 'borrowRecord.borrowDate'
						}
					}, {
						columnWidth : 0.33,
						layout : 'form',
						labelWidth : 100,
						items : {
							fieldLabel : '应还日期',anchor : '95%',
							allowBlank : false,
							readOnly : (this.returnStatus == 1)
									? true
									: false,
							width : 100,
							name : 'borrowRecord.returnDate',
							xtype : 'datefield',
							format : 'Y-m-d'
						}
					}, {
						columnWidth : 0.33,
						layout : 'form',
						labelWidth : 100,
						items : {
							fieldLabel : '借阅方式',anchor : '95%',
							readOnly : (this.returnStatus == 1)
									? true
									: false,
							width : 100,
							name : 'borrowRecord.borrowType',
							editable : true,
							lazyInit : false,
							forceSelection : false,
							xtype : 'diccombo',
							nodeKey : 'borrow_form'
						}
					}, {
						columnWidth : 0.33,
						layout : 'form',
						labelWidth : 100,
						items : {
							fieldLabel : '借阅目的',anchor : '95%',
							allowBlank : false,
							readOnly : (this.returnStatus == 1)
									? true
									: false,
							width : 100,
							name : 'borrowRecord.borrowReason',
							editable : true,
							lazyInit : false,
							forceSelection : false,
							xtype : 'diccombo',
							nodeKey : 'borrow_purpose'
						}
					}, {
						columnWidth : 0.33,
						layout : 'form',
						labelWidth : 100,
						items : {
							fieldLabel : '借阅状态',anchor : '95%',
							allowBlank : false,
							width : 100,
							name : 'BorrowRecordForm.borrowRecord.returnStatusName',
							// name : 'borrowRecord.returnStatus',
							readOnly : true,
							xtype : 'textfield'
						}
					}, {
						columnWidth : 1,
						layout : 'form',
						labelWidth : 100,
						items : {
							fieldLabel : '备注',
							readOnly : (this.returnStatus == 1)
									? true
									: false,
							allowBlank : true,
							anchor : '97%',
							xtype : 'textarea',
							name : 'borrowRecord.borrowRemark'
						}
					}, {
						columnWidth : 1,
						layout : 'form',
						labelWidth : 100,
						items : {
							fieldLabel : '登记人ID',
							value : curUserInfo.userId,
							width : 100,
							xtype : 'hidden',
							name : 'borrowRecord.checkUserId'
						}
					}, {
						columnWidth : 1,
						layout : 'form',
						labelWidth : 100,
						hidden : true,
						items : {
							fieldLabel : '登记日期',
							width : 100,
							xtype : 'datefield',
							format : 'Y-m-d',
							value : new Date(),
							name : 'borrowRecord.checkDate'
						}
					}, {
						columnWidth : 1,
						layout : 'form',
						labelWidth : 100,
						hidden : true,
						items : {
							fieldLabel : '借阅状态',
							allowBlank : false,
							width : 100,
							name : 'borrowRecord.returnStatus',
							xtype : 'numberfield',
							value : this.returnStatus
						}
					}
			]
		});

		this.fileListGrid = new BorrowFileListView({
			returnStatus:this.returnStatus			
		});
		this.fileListGrid.store.on('beforeload', function(store) {

					if (this.recordId) {
						store.baseParams = {
							'Q_borrowRecord.recordId_L_EQ' : this.recordId,
							start : 0,
							limit : 100000
						};
					} else {
						return false;
					}

				}, this);

		this.load();

	},// end of the initcomponents
	/**
	 * afterrender处理函数
	 */
	afterrenderClick : function(window) {
		var wh = window.getInnerHeight();
		var fh = window.formPanel.getHeight();
		window.fileListGrid.setHeight(wh - fh);

		var returnStatus = window.returnStatus * 1;

		window.returnStatusName = '';
		switch (returnStatus) {
			case 0 :
				window.returnStatusName = '申请';
				window.reSaveButton.setVisible(false);
				window.returnButton.setVisible(false);
				window.formPanel
							.getForm()
							.findField('BorrowRecordForm.borrowRecord.returnStatusName')
							.hide();
				break;
			case 1 :
				window.returnStatusName = '通过';
				window.saveButton.setVisible(false);
				window.reSaveButton.setVisible(false);
				window.resetButton.setVisible(false);
				break;
			case -1 :
				window.returnStatusName = '驳回';
				window.saveButton.setVisible(false);
				window.returnButton.setVisible(false);
				break;
			case 2 :
				window.returnStatusName = '归还';
				window.saveButton.setVisible(false);
				window.returnButton.setVisible(false);
				window.resetButton.setVisible(false);
				break;

		}
		window.formPanel
				.getForm()
				.findField('BorrowRecordForm.borrowRecord.returnStatusName')
				.setValue(window.returnStatusName);
	},
	load : function() {
		// 加载表单对应的数据

		if (this.recordId != null && this.recordId != 'undefined') {
			this.formPanel.loadData({
						url : __ctxPath + '/arch/getBorrowRecord.do?recordId='
								+ this.recordId,
						root : 'data',
						preName : 'borrowRecord'
					});
			this.fileListGrid.store.load();

		}
	},

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
	save : function(returnStatus) {
		if (this.formPanel.getForm().isValid()) {

			this.formPanel.getForm()
					.findField('borrowRecord.returnStatus')
					.setValue(returnStatus);
			var params = [];
			var store = this.fileListGrid.pageingStore;
			var cnt = store.getTotalCount();
			for (var i = 0; i < cnt; i++) {
				var record = store.allData.items[i];
				params.push(record.data);
			}
			if(params.length>0){
				$postForm({
					formPanel : this.formPanel,
					waitMsg : '正在提交数据...',
					params : {
						params : Ext.encode(params)
					},
					scope : this,
					url : __ctxPath + '/arch/saveBorrowRecord.do',
					callback : function(fp, action) {
						if (this.callback) {
							this.callback.call(this.scope);
						}
						this.close();
					}
				});
			}else{
				Ext.ux.Toast.msg("操作信息", "借阅清单不能为空!");
			}		
		}

	}// end of save

});