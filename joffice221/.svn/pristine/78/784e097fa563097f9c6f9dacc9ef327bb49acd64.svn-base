/**
 * @author csx
 * @createtime
 * @class ArchivesDocForm
 * @extends Ext.Window
 * @description ArchivesDoc表单
 * @company 宏天软件
 * 
 *  //实例化的控件对象
 *  var myNewOffice = null;
 *  //页面对象
 *  var myFormPanel = null;
 *  //提交类型
 *  var mySubmitType = null;
 * 
 */
ArchivesDocForm = Ext.extend(Ext.Window, {
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 必须先初始化组件
				this.initUIComponents();
				// 调用父类构造
				ArchivesDocForm.superclass.constructor.call(this, {
							id : 'ArchivesDocFormWin',
							layout : 'fit',
							border : false,
							items : this.formPanel,
							modal : true,
							height : 600,
							width : 800,
							iconCls : 'btn-archive-attachment',
							maximizable : true,
							title : '发文附件',
							buttonAlign : 'center',
							buttons : this.buttons
						});
			},// end of the constructor
			// 初始化组件
			initUIComponents : function() {
				Ext.useShims = true;// 默认下，Ext会自动决定浮动元素是否应该被填充。如果你在用Flash那么该值很可能要设置为True
				mySubmitType = "ArchivesDocForm";
				this.docPanel = new NtkOfficePanel({
							showToolbar : true,
							fileId : this.fileId,
							height : 480
						});
				
				myNewOffice = this.docPanel;
				
				if (!this.docPanel.flag) {
					var msg = this.docPanel.msg;
					setTimeout(function() {
								Ext.MessageBox.show({
											title : '操作信息',
											msg : msg,
											buttons : Ext.MessageBox.OK,
											icon : 'ext-mb-warning',
											scope : this,
											fn : function() {
												Ext
														.getCmp('ArchivesDocFormWin')
														.close();
											}
										});
							}, 500);
					return;
				}

				this.formPanel = new Ext.FormPanel({
							layout : 'form',
							frame : true,
							url : __ctxPath + '/archive/saveArchivesDoc.do',
							id : 'ArchivesDocForm',
							defaults : {
								anchor : '98%,98%'
							},
							items : [{
										name : 'archivesDoc.docId',
										id : 'docId',
										xtype : 'hidden',
										value : this.docId == null
												? ''
												: this.docId
									}, {
										xtype : 'hidden',
										name : 'archivesDoc.fileId',
										id : 'fileId'
									}, {
										fieldLabel : '文档名称',
										name : 'archivesDoc.docName',
										xtype : 'textfield',
										allowBlank : false,
										id : 'docName'
									}, {
										xtype : 'hidden',
										fieldLabel : '文档路径',
										name : 'archivesDoc.docPath',
										id : 'docPath'
									}, this.docPanel.panel]
						});
				
				myFormPanel  = this.formPanel;
				
				// 加载表单对应的数据
				if (!Ext.isEmpty(this.docId)) {
					this.formPanel.loadData({
								url : __ctxPath
										+ '/archive/getArchivesDoc.do?docId='
										+ this.docId,
								root : 'data',
								preName : 'archivesDoc',
								scope : this,
								success : function(response, options) {
									if (this.fileId) {
										if(Ext.isChrome){
											//谷歌浏览器加载插件有延迟问题，所以我们迟一点时间去打开文档
											var setTimeNum = mySetTimeNum;
				    					       if(setTimeNum==null||setTimeNum=='undefined'||setTimeNum<1){
				    						    	setTimeNum = 500;
				    						   }
											setTimeout(function (){
												this.docPanel.openDoc(this.fileId);
											},setTimeNum);	
										}else{
											this.docPanel.openDoc(this.fileId);
										}		
									}
								}

							});

				}
				// 初始化功能按钮
				this.buttons = [{
							text : '保存',
							iconCls : 'btn-save',
							scope : this,
							handler : this.onSave
						}, {
							text : '重置',
							iconCls : 'btn-reset',
							scope : this,
							handler : this.onReset
						}, {
							text : '取消',
							iconCls : 'btn-cancel',
							scope : this,
							handler : this.onCancel
						}];
			},// end of the initcomponents

			// overwrite the show method
			show : function() {

				ArchivesDocForm.superclass.show.call(this);
				// if (this.fileId) {
				// this.docPanel.openDoc(this.fileId);
				// }
			},

			/**
			 * 重置
			 * 
			 * @param {}
			 *            formPanel
			 */
			onReset : function() {
				this.formPanel.getForm().reset();
			},
			/**
			 * 取消
			 * 
			 * @param {}
			 *            window
			 */
			onCancel : function() {
				this.close();
			},
			/**
			 * 保存记录
			 */
			onSave : function() {
				this.scope = this.scope ? this.scope : this;
				if (this.formPanel.getForm().isValid()) {
					var docPath = null;
					var fileId = null;
					var docName = this.formPanel.getCmpByName('archivesDoc.docName').getValue();
					var obj = this.docPanel.saveDoc({
								fileId : fileId,
								docName : docName,
								doctype : 'doc'
							});
					if (obj && obj.success) {
						fileId = obj.fileId;
						if(fileId>0){
							docPath = obj.filePath;
							Ext.getCmp('docPath').setValue(docPath);
						}else{
							//火狐谷歌上的提交处理 后  火狐谷歌在NtkOfficePanel.js中的  saveMethodOnComplete方法处理内容
							//这里只是消息提示，在saveMethodOnComplete方法会隐藏它的   
						}						
					} else {
						Ext.ux.Toast.msg('操作信息', '保存文档出错！');
						return;
					}

					if (this.docId == 0) {
						var archivesDoc = {
							docId : 0,// 用于标记尚未持久化的记录
							fileId : fileId,
							docPath : docPath,
							docName : docName,
							curVersion : 1
						};
						this.callback.call(this.scope, archivesDoc);
						this.close();
						return;
					}
					this.formPanel.getForm().submit({
						method : 'POST',
						params : {
							docPath : docPath,
							fileId : fileId
						},
						scope : this,
						success : function(fp, action) {
							Ext.ux.Toast.msg('操作信息', '成功保存附加文档！');
							var result = Ext.util.JSON
									.decode(action.response.responseText);
							// 把添加的文档记录实体返回
							this.callback.call(this.scope, result.data);
							this.close();
						},
						failure : function(fp, action) {
							Ext.MessageBox.show({
										title : '操作信息',
										msg : '信息保存出错，请联系管理员！',
										buttons : Ext.MessageBox.OK,
										icon : Ext.MessageBox.ERROR
									});
						}
					});
				}
			},// end of save
			/**
			 * 插入附加文件记录
			 * 
			 * @param {}
			 *            store
			 * @param {}
			 *            archivesDoc
			 */
			insertNewDoc : function(store, archivesDoc) {
				var orec;
				if (store.recordType) {
					orec = new store.recordType();
					orec.data = {};
					orec.data['docId'] = archivesDoc.docId;
					orec.data['fileId'] = archivesDoc.fileId;
					orec.data['docPath'] = archivesDoc.docPath;
					orec.data['docName'] = archivesDoc.docName;
					orec.data['curVersion'] = archivesDoc.curVersion
							? archivesDoc.curVersion
							: 1;
					orec.data.newRecord = true;
					orec.commit();
					store.add(orec);
				}
			}
		});
