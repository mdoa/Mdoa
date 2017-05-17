Ext.ns('CarForm');
/**
 * @author
 * @createtime
 * @class CarForm
 * @extends Ext.Window
 * @description CarForm表单
 * @company 宏天软件
 */
CarForm = Ext.extend(Ext.Window, {
	sope : this,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		// 调用父类构造
		CarForm.superclass.constructor.call(this, {
					id : 'CarFormWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					width : 830,
					height : 450,
					minWidth : 829,
					minHeight : 449,
					maximizable : true,
					title : '车辆详细信息',
					iconCls : 'menu-car',
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
								text : '取消',
								iconCls : 'btn-cancel',
								scope : this,
								handler : this.cancel
							}]
				});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		// 右边面板的栏目条
		this.topbar = new Ext.Toolbar({
					width : '100%',
					height : 30,
					items : [{
								text : '上传',
								iconCls : 'btn-upload',
								handler : this.uploadImage,
								scope : this
							}, {
								text : '删除',
								iconCls : 'btn-delete',
								handler : this.deleteImage,
								scope : this
							}]
				});
		// 表单面板
		this.formPanel = new Ext.FormPanel({
			layout : 'hbox',
			layoutConfig : {
				padding : '5',
				align : 'middle'
			},
			defaults : {
				margins : '0 5 0 0'
			},
			anchor : '100%',
			frame : false,
			items : [{
						xtype : 'panel',
						title : '基本信息',
						layout : 'form',
						frame : false,
						width : 300,
						height : 360,
						bodyStyle : 'padding:5px;',
						labelWidth : 100,
						defaults : {
							anchor : '96%,96%'
						},
						defaultType : 'textfield',
						items : [{
									name : 'car.carId',
									xtype : 'hidden',
									value : this.carId == null
											? ''
											: this.carId
								}, {
									name : 'car.cartImage',
									xtype : 'hidden'

								}, {
									fieldLabel : '车牌号码',
									name : 'car.carNo',
									allowBlank : false,
									xtype : 'textfield'
								}, {
									fieldLabel : '车辆类型',
									name : 'car.carType',
									xtype : 'combo',
									mode : 'local',
									editable : true,
									allowBlank : false,
									triggerAction : 'all',
									store : [['1', '轿车'], ['2', '货车'],
											['3', '商务车']]
								}, {
									fieldLabel : '发动机型号',
									name : 'car.engineNo'
								}, {
									fieldLabel : '购买保险时间',
									name : 'car.buyInsureTime',
									editable : false,
									xtype : 'datefield',
									format : 'Y-m-d'
								}, {
									fieldLabel : '年审时间',
									name : 'car.auditTime',
									editable : false,
									xtype : 'datefield',
									format : 'Y-m-d'
								}, {
									fieldLabel : '厂牌型号',
									name : 'car.factoryModel',
									allowBlank : false
								}, {
									fieldLabel : '驾驶员',
									name : 'car.driver',
									allowBlank : false
								}, {
									fieldLabel : '购置日期',
									name : 'car.buyDate',
									allowBlank : false,
									editable : false,
									xtype : 'datefield',
									format : 'Y-m-d'
								}, {
									fieldLabel : '当前状态',// 1=可用2=维修中0=报废
									hiddenName : 'car.status',
									xtype : 'combo',
									mode : 'local',
									allowBlank : false,
									editable : false,
									triggerAction : 'all',
									store : [['1', '可用'], ['2', '维修中'],
											['0', '已报废']]
								}, {
									fieldLabel : '备注',
									name : 'car.notes',
									xtype : 'textarea',
									anchor : '96%,96%'
								}]
					}, {
						xtype : 'panel',
						id : 'carImageDisplay',
						frame : false,
						border : true,
						height : 360,
						html : '<img src="'
								+ __ctxPath
								+ '/images/default_image_car.jpg" width="400" height="350"/>',
						tbar : this.topbar
					}]
		});
		// 加载表单对应的数据
		if (this.carId != null && this.carId != 'undefined') {
			this.formPanel.loadData({
				url : __ctxPath + '/admin/getCar.do?carId=' + this.carId,
				root : 'data',
				preName : 'car',
				scope : this,
				success : function(response, options) {
					var data = Ext.util.JSON.decode(response.responseText).data;
					if (!data)
						return;
					// 当前用户id
					this.userId = data.userId;
					// 设置图片
					this.setImage(data.cartImage);
				},
				failure : function(response, options) {
					Ext.ux.Toast.msg('编辑', '载入失败');
				}
			});
		};

	},// end of the initcomponents

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
	save : function() {
		var formPanel = this.formPanel;
		// 提交
		$postForm({
					formPanel : formPanel,
					scope : this,
					url : __ctxPath + '/admin/saveCar.do',
					callback : function(fp, action) {
						if (this.callback) {
							this.callback.call(this.scope);
						}
						this.close();
					}
				});
	},
	/**
	 * 设置图片
	 * 
	 * @param {}
	 *            filePath 当前图片路径
	 */
	setImage : function(filePath) {
		var image = this.formPanel.getCmpByName('car.cartImage');
		var display = Ext.getCmp('carImageDisplay');
		if (!Ext.isEmpty(filePath)) {
			image.setValue(filePath);
			var src = __ctxPath + '/attachFiles/' + filePath;
			display.body.update('<img src="' + src
					+ '"  width="100%" height="100%"/>');
		} else {// 默认图片
			image.setValue('');
			display.body.update('<img src="' + __ctxPath
						+ '/images/default_image_car.jpg" />');
		}
		return;

	},
	/**
	 * 上传图片 窗口
	 * 
	 * @return {}
	 */
	uploadDialog : function() {
		var dialog = App.createUploadDialog({
					file_cat : 'admin/carManager/car',
					scope : this,
					callback : function(data) {
						var filePath = '';
						if (!Ext.isEmpty(data)) {
							filePath = data[0].filePath;
						}
						// 设置图片
						this.setImage(filePath);
					},
					permitted_extensions : ['jpg']
				});
		return dialog;
	},
	/**
	 * 上传图片
	 */
	uploadImage : function() {
		var filePath = this.formPanel.getCmpByName('car.cartImage').getValue();
		if (!Ext.isEmpty(filePath)) {
			this.deleteImage('upload');
		} else {
			this.uploadDialog().show('queryBtn');
		}
	},
	/**
	 * 删除图片
	 * 
	 * @param {}
	 *            isUpload 是上传的，还是删除的
	 */
	deleteImage : function(isUpload) {
		var msg = '';
		var carId = this.formPanel.getCmpByName('car.carId').getValue();
		// logo图片路径
		var filePath = this.formPanel.getCmpByName('car.cartImage').getValue();
		if (!Ext.isEmpty(filePath)) {
			if (isUpload != '' && isUpload == 'upload') {
				msg = '再次上传需要先删除原有图片,';
			} else {
				msg = '照片一旦删除将不可恢复,';
			}
			// 删除信息
			Ext.Msg.confirm('信息确认', msg + '是否删除？', function(btn) {
				if (btn == 'yes') {
					Ext.Ajax.request({
						url : __ctxPath + '/system/deleteFileAttach.do',
						method : 'post',
						params : {
							filePath : filePath
						},
						scope : this,
						success : function() {
							// 还原默认图片
							this.setImage('');
							// 删除图片
							if (!Ext.isEmpty(carId)) {
								Ext.Ajax.request({
									url : __ctxPath + '/admin/delphotoCar.do',
									params : {
										carId : carId
									},
									method : 'post',
									scope : this,
									success : function() {
										if (isUpload != ''
												&& isUpload == 'upload') {
											this.uploadDialog()
													.show('queryBtn');
										}
									}
								});
							}

						}
					});
				}
			}, this);
		}// end if
		else {
			Ext.ux.Toast.msg('提示信息', '您还未增加图片.');
		}
	}
});