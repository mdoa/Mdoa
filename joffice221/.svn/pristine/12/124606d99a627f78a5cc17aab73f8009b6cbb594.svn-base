/**
 * @author
 * @createtime
 * @class SuggestBoxReplyForm
 * @extends Ext.Window
 * @description SuggestBox表单
 * @company 宏天软件
 */
SuggestBoxReplyForm = Ext.extend(Ext.Window, {
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 必须先初始化组件
				this.initUIComponents();
				// 调用父类构造
				SuggestBoxReplyForm.superclass.constructor.call(this, {
							id : 'SuggestBoxReplyFormWin',
							layout : {
								type : 'vbox',
								align : 'stretch'
							},
							items : [this.displayPanel, this.formPanel],
							modal : true,
							height : 550,
							width : 735,
							maximizable : true,
							title : '意见回复',
							iconCls : 'menu-suggestbox',
							buttonAlign : 'center',
							buttons : this.buttons
						});
			},// end of the constructor
			// 初始化组件
			initUIComponents : function() {
				// 显示意见信息面板
				this.displayPanel = new Ext.Panel({
							flex : 1,
							autoScroll : true,
							border : false,
							autoLoad : {
								url : __ctxPath
										+ '/pages/info/displaySuggest.jsp?boxId='
										+ this.boxId
							}
						});
				// 表单
				this.formPanel = new Ext.FormPanel({
							layout : 'form',
							flex : 1,
							bodyStyle : 'padding:10px 10px 10px 10px',
							border : false,
							defaults : {
								anchor : '95%,95%'
							},
							autoScroll : true,
							defaultType : 'textfield',
							items : [{
										name : 'suggestBox.boxId',
										xtype : 'hidden',
										value : this.boxId == null
												? ''
												: this.boxId
									}, {
										xtype : 'radiogroup',
										fieldLabel : '是否公开',
										autoHeight : true,
										columns : 2,
										width : 520,
										items : [{
													boxLabel : '公开',
													name : 'suggestBox.isOpen',
													inputValue : 0,
													checked : true
												}, {
													boxLabel : '不公开',
													name : 'suggestBox.isOpen',
													inputValue : 1
												}]
									}, {
										fieldLabel : '回复内容',
										name : 'suggestBox.replyContent',
										width : 200,
										xtype : 'htmleditor'
									}]
						});
				// 初始化功能按钮
				this.buttons = [{
							text : '保存',
							iconCls : 'btn-save',
							scope : this,
							handler : this.reply
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
						}];
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
			reply : function() {
					$postForm({
								formPanel : this.formPanel,
								scope : this,
								url : __ctxPath + '/info/replySuggestBox.do',
								callback : function(fp, action) {
									if (this.callback) {
										this.callback.call(this.scope);
									}
									this.close();
								}
							});
			}// end of save
		});