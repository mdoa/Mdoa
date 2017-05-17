Ext.ns('FileUploadImageDetail');
/**
 * @description 图片详细信息展示
 * @class FileUploadImageDetail
 * @date 2010-11-23 AM
 * @company www.htsoft-jee.cn
 */
FileUploadImageDetail = Ext.extend(Ext.Window, {
			// 构造函数
			constructor : function(conf) {
				Ext.apply(this, conf);
				// 必须先初始化组件
				this.initComponents();
				FileUploadImageDetail.superclass.constructor.call(this, {
							name : 'FileUploadImageDetail',
							width : 600,
							heigth : 500,
							modal : true,
							autoScroll : true,
							maximizable : true,
							title : '图片详细信息',
							iconCls : 'menu-file',
							layout : 'form',
							region : 'center',
							buttonAlign : 'center',
							buttons : this.buttons,
							items : this.displayPanel
						});
			},
			initComponents : function() {
				this.displayPanel = new Ext.Panel({
							flex : 1,
							id : 'FileAttachDetailPanel',
							// height : 430,
							autoScroll : true,
							border : false,
							autoLoad : {
								url : __ctxPath
										+ '/system/fileAttachDetail.do?fileId='
										+ this.fileId
							}
						});

				this.buttons = [{
							text : '取消',
							iconCls : 'btn-cancel',
							scope : this,
							handler : this.cancel
						}];
			},// end of the initcomponents
			// 取消
			cancel : function() {
				this.close();
			}
		});