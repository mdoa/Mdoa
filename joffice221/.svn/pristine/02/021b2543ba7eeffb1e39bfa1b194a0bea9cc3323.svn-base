/**
 * @author lyy
 * @createtime
 * @class JobChangeForm
 * @extends Ext.Panel
 * @description JobChange表单
 * @company 宏天软件
 */
ResumeView = Ext.extend(Ext.Panel, {
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		// 必须先初始化组件
		this.initComponents();
		ResumeView.superclass.constructor.call(this, {
			id : 'ResumeView',
			title : '简历列表',
			iconCls : 'menu-resume',
			layout:'border',
			autoScroll : true,
			items:[this.searchPanel,this.gridPanel]
		});
	},// end of the constructor
	// 初始化组件
	initComponents : function() {	
		// 搜索面板
		this.searchPanel = new HT.SearchPanel({
			layout : 'form',
			region : 'north',
			colNums : 5,
			keys : {
				key : Ext.EventObject.ENTER,
				fn : this.search,
				scope : this
			},
			labelWidth : 135, // 一个bug
			items : [{
							fieldLabel : '请输入查询条件：姓名',
							xtype : 'textfield',
							name : 'Q_fullname_S_LK',
							maxLength : 150
						},
						{
							fieldLabel : '应聘职位',
							xtype : 'textfield',
							name : 'Q_position_S_LK',
							maxLength : 125,
							labelWidth : 70
						},
						{
							fieldLabel : '状态',
							xtype : 'combo',
							hiddenName : 'Q_status_S_EQ',
							maxLength : 125,
							labelWidth : 70,
							mode : 'local',
							triggerAction : 'all',
							store : ['通过', '未通过', '准备安排面试', '通过面试']
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
		this.gridPanel = new HT.GridPanel({
				tbar : [{
					iconCls : 'btn-add',
					text : '添加简历',
					hidden:!isGranted('_ResumeAdd'),
					scope:this,
					handler :this.ResumeAdd
				},{
					iconCls : 'btn-del',
					text : '删除简历',
					hidden:!isGranted('_ResumeDel'),
					scope:this,
					handler :this.ResumeDel
				}],
				trackMouseOver : true,
				disableSelection : false,
				loadMask : true,
				sort:[{field:'resumeId',direction:'DESC'}],
				rowActions:true,
				region : 'center',
				url : __ctxPath + '/hrm/listResume.do',
				fields : [{
								name : 'resumeId',
								type : 'int'
							}
							, 'fullname', 'age', 'birthday', 'address',
							'zip', 'sex', 'position', 'phone',
							'mobile', 'email', 'hobby', 'religion',
							'party', 'nationality', 'race',
							'birthPlace', 'eduCollege', 'eduDegree',
							'eduMajor', 'startWorkDate', 'idNo',
							'photo', 'status', 'memo', 'registor',
							'regTime', 'workCase', 'trainCase',
							'projectCase'],
			   columns : [{
								header : 'resumeId',
								dataIndex : 'resumeId',
								hidden : true
							}, {
								header : '姓名',
								dataIndex : 'fullname'
							}, {
								header : '年龄',
								dataIndex : 'age'
							}, {
								header : '性别',
								dataIndex : 'sex'
							}, {
								header : '应聘职位',
								dataIndex : 'position'
							}, {
								header : '电话',
								dataIndex : 'phone'
							}, {
								header : '手机',
								dataIndex : 'mobile'
							}, {
								header : '邮箱',
								dataIndex : 'email'
							}, {
								header : '专业',
								dataIndex : 'eduMajor'
							}, {
								header : '状态',
								dataIndex : 'status'
							}, {
								header : '登记人',
								dataIndex : 'registor'
							}, {
								header : '登记时间',
								dataIndex : 'regTime'
							}, new Ext.ux.grid.RowActions({
									header : '管理',
									width : 100,
									actions : [{
												iconCls : 'btn-del',
												qtip : '删除',
												style : 'margin:0 3px 0 3px',
												fn:function(record){
													if(isGranted('_ResumeDel'))
														return true;
													return false;
												}
											}, {
												iconCls : 'btn-edit',
												qtip : '编辑',
												style : 'margin:0 3px 0 3px',
												fn:function(record){
													if(isGranted('_ResumeEdit'))
														return true;
													return false;
												}
											}],
									listeners : {
										scope : this,
										'action' : this.onRowAction
									}
								})],
					listeners : {
									scope : this,
									'rowdblclick' : this.rowdblclick
							}
		});
	},// end of the initcomponents
	/**
	 * 查询
	 */
	search: function() {
			$search({
				searchPanel :this.searchPanel,
				gridPanel : this.gridPanel
			});
	},
	/**
	 * 重置
	 */
	resetedClick:function() {
		this.searchPanel.getForm().reset();
	},
	/**
	 * rowdblclick处理函数
	 * @param grid
	 * @param rowindex
	 * @param e
	 */
	rowdblclick:function(grid, rowindex, e) {
		grid.getSelectionModel().each(function(rec) {
					if(isGranted('_ResumeEdit')){
					  this.edit(rec.data.resumeId);
					}
				},this);
	},
	/**
	 * 添加简历
	 */
	ResumeAdd:function() {
		var tabs = Ext.getCmp('centerTabPanel');
		var ResumeFormPanel = Ext.getCmp('ResumeForm');
		if (ResumeFormPanel != null) {
			tabs.remove('ResumeForm');
		}
		ResumeFormPanel = new ResumeForm();
		tabs.add(ResumeFormPanel);
		tabs.activate(ResumeFormPanel);
	},
	/**
	 * 删除简历
	 */
	ResumeDel: function() {
		$delGridRs({
			url : __ctxPath + '/hrm/multiDelResume.do',
			grid : this.gridPanel,
			idName : 'resumeId'
		});
	},
	/**
	 * 删除单个记录
	 */
	remove: function(id) {
		$postDel({
			url : __ctxPath + '/hrm/multiDelResume.do',
			ids : id,
			grid : this.gridPanel
		});
	},

	/**
	 * 
	 */
	edit: function(id) {
		var tabs = Ext.getCmp('centerTabPanel');
		var ResumeFormPanel = Ext.getCmp('ResumeFormPanel');
		if (ResumeFormPanel != null) {
			tabs.remove('ResumeFormPanel');
		}
		ResumeFormPanel = new ResumeForm({resumeId:id});
		tabs.add(ResumeFormPanel);
		tabs.activate(ResumeFormPanel);
	},
	/**
	 * 行的Action
	 */
	onRowAction : function(grid, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.remove(record.data.resumeId);
				break;
			case 'btn-edit' :
				this.edit(record.data.resumeId);
				break;
			default :
				break;
		}
	}
	
});