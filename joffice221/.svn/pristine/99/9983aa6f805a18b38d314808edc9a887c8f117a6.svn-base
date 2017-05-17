/**
 * 
 * @class ConferenceDialog
 * @extends Ext.Window
 *          @example
 * 
 * <pre>
 * new CarDialog({
 *  	title :'会议选择' //标题  默认是'会议选择'，也可以自定义标题
 * 		single: true,   //是否单选 默认是多选会议
 * 		scope:this,   //作用域
 * 		callback :function(ids,names){//回调函数,返回会议ids和会议标题
 * 
 * 		}	
 * 	}
 * </pre>
 * 
 */
ConferenceDialog = Ext.extend(Ext.Window, {
			constructor : function(conf) {
				Ext.applyIf(this, conf);
				// 作用域
				this.scope = this.scope ? this.scope : this;
				// 默认为多选择
				this.single = this.single != null ? this.single : false;

				this.initUI();
				ConferenceDialog.superclass.constructor.call(this, {
							title : this.title ? this.title : '会议选择',
							iconCls : 'menu-confSummary',
							width : 630,
							height : 380,
							layout : 'border',
							border : false,
							modal : true,
							buttonAlign : 'center',
							items : [this.searchPanel, this.gridPanel],
							buttons : [{
										iconCls : 'btn-ok',
										text : '确定',
										scope : this,
										handler : this.confirm
									}, {
										text : '取消',
										iconCls : 'btn-cancel',
										scope : this,
										handler : function() {
											this.close();
										}
									}]
						});
			},

			/**
			 * 初始化UI
			 */
			initUI : function() {

				this.searchPanel = new HT.SearchPanel({
							width : 400,
							layout : 'form',
							region : 'north',
							colNums : 3,
							keys : {
								key : Ext.EventObject.ENTER,
								fn : this.search,
								scope : this
							},
							labelWidth : 155,
							items : [{
										fieldLabel : '请输入查询条件: 会议标题',
										xtype : 'textfield',
										name : 'Q_confTopic_S_LK',
										maxLength : 150
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
										handler : this.reset
									}]
						});
				// -------start grid panel--------------------------------
				this.gridPanel = new HT.GridPanel({
							title : '会议标题列表',
							width : 400,
							height : 300,
							region : 'center',
							singleSelect : this.single,
							url : __ctxPath
									+ "/admin/getConfTopicConference.do",
							baseParams : {
								'Q_status_SN_EQ' : this.status == null
										? 1
										: this.status
							},
							fields : [{
										name : 'confId',
										type : 'int'
									}, 'confTopic'],
							columns : [{
										header : 'confId',
										dataIndex : 'confId',
										hidden : true
									}, {
										header : "会议标题",
										dataIndex : 'confTopic',
										width : 60
									}]

						});

				// ----------end grid panel------------------
			},// end of initUI function
			/**
			 * 查询
			 */
			search : function() {
				$search({
							searchPanel : this.searchPanel,
							gridPanel : this.gridPanel
						});
			},
			/**
			 * 重置(清空)查询表单
			 */
			reset : function() {
				this.searchPanel.getForm().reset();
			},
			/**
			 * 选择确认
			 */
			confirm : function() {
				var rows = this.gridPanel.getSelectionModel().getSelections();
				var confIds = '';
				var confTopics = '';
				for (var i = 0; i < rows.length; i++) {
					if (i > 0) {
						confIds += ',';
						confTopics += ',';
					}
					confIds += rows[i].data.confId;
					confTopics += rows[i].data.confTopic;
				}

				if (this.callback) {
					this.callback.call(this.scope, confIds, confTopics);
				}
				this.close();
			}
		});