/**
 * 单一档案选择器
 * 
 * @class EmpProfileDialog
 * @extends Ext.Window
 *          @example
 * 
 * <pre>
 * new EmpProfileDialog({
 *  	title :'档案选择' //标题  默认是'选择图书'，也可以自定义标题
 * 		scope:this,   //作用域
 * 		callback :function(array){//回调函数,返回由档案信息组成的一个数组，0:profileId,1:profileNo,
 * 					2:fullname,3:jobId,4:position,5:depId,6:depName,7:standardMiNo
 * 					8:standardName,9:standardMoney,10:standardId,11:idCard,12:userId
 * 		}	
 * 	}
 * </pre>
 */
EmpProfileDialog = Ext.extend(Ext.Window, {
			constructor : function(conf) {
				Ext.applyIf(this, conf);
				// 作用域
				this.scope = this.scope ? this.scope : this;
				// 初始化
				this.initUI();
				EmpProfileDialog.superclass.constructor.call(this, {
							title : this.title ? this.title : '档案选择',
							iconCls : 'menu-profile',
							width : 630,
							height : 380,
							layout : 'border',
							border : false,
							items : [this.searchPanel, this.gridPanel],
							modal : true,
							buttonAlign : 'center',
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
				// 档案列表
				this.gridPanel = new HT.GridPanel({
							title : '档案列表',
							width : 400,
							height : 300,
							region : 'center',
							url : __ctxPath + "/hrm/listEmpProfile.do",
							baseParams : {
								'Q_approvalStatus_SN_EQ' : this.status == null
										? 1
										: this.status,
								"Q_delFlag_SN_EQ" : this.delFlag == null
										? 0
										: this.delFlag
							},
							sort : [{
										field : "profileId",
										direction : "DESC"
									}],
							fields : [{
										name : 'profileId',
										type : 'int'
									}, 'profileNo', 'fullname', 'jobId',
									'position', 'standardMiNo',
									'standardMoney', 'standardName',
									'standardId', 'approvalStatus', 'depName',
									'depId', 'delFlag', 'userId', 'idCard'],
							columns : [{
										header : 'profileId',
										dataIndex : 'profileId',
										hidden : true
									}, {
										header : '档案编号',
										dataIndex : 'profileNo'
									}, {
										header : '员工姓名',
										dataIndex : 'fullname'
									}]
						});

				// 查询面板
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
							labelWidth : 145,
							items : [{
										fieldLabel : '请输入查询条件:姓名',
										xtype : 'textfield',
										name : 'Q_fullname_S_LK',
										maxLength : 150
									}, {
										xtype : 'button',
										text : '查询',
										iconCls : 'search',
										scope : this,
										handler : this.search
									}, {
										xtype : 'button',
										text : '重置',
										iconCls : 'btn-reset',
										scope : this,
										handler : this.reset
									}, {
										xtype : 'hidden',
										name : 'Q_approvalStatus_SN_EQ',
										value : 1
									}]
						});

			},// end of initUI function
			// 查询
			search : function() {
				$search({
							searchPanel : this.searchPanel,
							gridPanel : this.gridPanel
						});
			},
			// 选择确认
			confirm : function() {
				var grid = this.gridPanel;
				var rows = grid.getSelectionModel().getSelections();
				var array = Array();
				array.push(rows[0].data.profileId);// 0
				array.push(rows[0].data.profileNo);// 1
				array.push(rows[0].data.fullname);// 2
				array.push(rows[0].data.jobId);// 3
				array.push(rows[0].data.position);// 4
				array.push(rows[0].data.depId);// 5
				array.push(rows[0].data.depName);// 6
				array.push(rows[0].data.standardMiNo);// 7
				array.push(rows[0].data.standardName);// 8
				array.push(rows[0].data.standardMoney);// 9
				array.push(rows[0].data.standardId);// 10
				array.push(rows[0].data.idCard);// 11
				array.push(rows[0].data.userId);// 12
				if (this.callback) {
					this.callback.call(this.scope, array);
				}
				this.close();
			}
		});