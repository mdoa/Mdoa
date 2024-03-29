/**
 * @author YHZ
 * @createtime 2010-12-13PM
 * @class RelativeUserForm
 * @extends Ext.Window
 * @description RelativeUser表单
 * @company 宏天软件
 */
RelativeUserForm = Ext.extend(Ext.Window, {
			//构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				//必须先初始化组件
				this.initUIComponents();
				RelativeUserForm.superclass.constructor.call(this, {
					id : 'RelativeUserFormWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					height : 400,
					width : 500,
					maximizable : true,
					title : '相对岗位人员详细信息',
					buttonAlign : 'center',
					buttons : [
						{
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
						}
			         ]
				});
			},//end of the constructor
			//初始化组件
			initUIComponents : function() {
				this.formPanel = new Ext.FormPanel({
					layout : 'form',
					bodyStyle : 'padding:10px',
					border : false,
					autoScroll:true,
					defaults : {
						anchor : '96%,96%'
					},
					defaultType : 'textfield',
					items : [{
						name : 'relativeUser.relativeUserId',
						xtype : 'hidden',
						value : this.relativeUserId == null ? '' : this.relativeUserId
					} , { 
						fieldLabel : '所属相对岗位',	
						name : 'relativeUser.reJobId',
						xtype:'numberfield'
					} , { 
						fieldLabel : '所属员工',	
						name : 'relativeUser.userId'
						,xtype:'numberfield'
					} , {
						fieldLabel : '岗位员工',	
						name : 'relativeUser.jobUserId',
						xtype:'numberfield'
					} , { 
						fieldLabel : '上下级标识 ',
						name : 'relativeUser.isSuper',
						xtype:'numberfield'
					}
																																	]
				});
				//加载表单对应的数据	
				if (this.relativeUserId != null && this.relativeUserId != 'undefined') {
					this.formPanel.loadData({
						url : __ctxPath + '/system/getRelativeJob.do?relativeUserId='+ this.relativeUserId,
						root : 'data',
						preName : 'relativeUser'
					});
				}
				
			},//end of the initcomponents

			/**
			 * 重置
			 * @param {} formPanel
			 */
			reset : function() {
				this.formPanel.getForm().reset();
			},
			/**
			 * 取消
			 * @param {} window
			 */
			cancel : function() {
				this.close();
			},
			/**
			 * 保存记录
			 */
			save : function() {
				$postForm({
						formPanel:this.formPanel,
						scope:this,
						url:__ctxPath + '/system/saveRelativeJob.do',
						callback:function(fp,action){
							this.close();
						}
					}
				);
			}//end of save

		});