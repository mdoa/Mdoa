/**
 * 我的计划详细信息
 * @author zqg
 * @class PersonalWorkPlanForm
 * @extends Ext.Window
 */
PersonalWorkPlanForm=Ext.extend(Ext.Window,{
		//构造方法
       constructor : function(_cfg){
           Ext.applyIf(this,_cfg);
           //必须先初始化组件
           this.initUI();
           //调用父类构造方法
           PersonalWorkPlanForm.superclass.constructor.call(this,{
                id : 'PersonalWorkPlanForm',
                layout : 'fit',
                iconCls : 'menu-myplan',
                title : '我的计划详细信息',
				items : this.formPanel,
				modal : true,
				width : 700,
				minWidth : 700,
				maxWidth : 700,
				height : 520,
				buttonAlign : 'center',
				buttons : this.buttons,
				keys : {
        	   		key : Ext.EventObject.ENTER,
        	   		scope : this,
        	   		fn : this.saveRecord
           		}
           });
       },
       //初始化组件
       initUI:function(){
		//计划信息面板
		this.formPanel = new Ext.FormPanel({
			layout : 'column',
			bodyStyle : 'padding:10px;',
			border : false,
			autoScroll : true,
			defaults : {
				border : false,
				anchor : '98%,98%'
			},
			items : [{
				name : 'workPlan.planId',
				xtype : 'hidden',
				value : this.planId == null ? '' : this.planId
			},{
			    name:'workPlan.isPersonal',
			    value:1,
			    xtype:'hidden'
			},{	
			    columnWidth : 1,		
				layout : 'form',
				labelWidth : 70,	
				 defaults: {
			        width: 520
			    },
				items:[{
						fieldLabel : '计划分类',
						hiddenId : 'playTypeId',
						hiddenName : 'workPlan.globalType.proTypeId',
						name : 'workPlan.globalType.typeName',
						xtype : 'combotree',
						url : __ctxPath + '/system/treeGlobalType.do?catKey=PWP&method=1'
					}]
			},
			{
				columnWidth :1,
				layout : 'form',
				labelWidth : 70,
				items : {
					fieldLabel : '计划名称',
					width : 520,
					name : 'workPlan.planName',
					allowBlank : false,
					xtype : 'textfield',
					maxLength :600
				}
			}, {
					columnWidth : 0.51,
					layout : 'form',
					labelWidth : 70,
					items : {
						fieldLabel : '时间范围',
						width : 250,
						name : 'workPlan.startTime',
						xtype : 'datetimefield',
						format : 'Y-m-d H:i:s',
						allowBlank:false,
						editable : false
					}
				},{
					columnWidth : 0.49,
					layout : 'form',
					labelWidth : 20,
					items : {
						fieldLabel : '到',
						width : 240,
						name : 'workPlan.endTime',
						xtype : 'datetimefield',
						format : 'Y-m-d H:i:s',
						allowBlank:false,
						editable : false
					}
				},{
					columnWidth :1,
					layout : 'form',
					labelWidth : 70,
					items : {
						fieldLabel : '计划内容',
						width : 520,
						name : 'workPlan.planContent',
						xtype : 'htmleditor',
						allowBlank : false,
						maxLength :600
					}
				},{
					columnWidth : 0.81,
					layout : 'form',
					labelWidth : 70,
					items : {
						fieldLabel : '附件',
						xtype : 'panel',
						frame:false,
						name : 'planFilePanel',
						height : 50,
						autoScroll : true,
						width : 440,
						html : ''
					}
				},{
					columnWidth : 0.19,
					layout : 'form',
					labelWidth : 70,
					items : [{
								iconCls : 'menu-attachment',
								xtype : 'button',
								text : '添加附件',
								scope : this,
								handler : this.addAttachment	
								}, {
								iconCls : 'reset',
								xtype : 'button',
								text : '清除附件',
								scope : this,
								handler : this.resetAttachment
							}, {
								xtype : 'hidden',
								id : 'planFileIds',
								name : 'planFileIds'
							}]
				},{
					columnWidth : 0.8,
					layout : 'form',
					labelWidth : 70,
					items : {
						xtype : 'radiogroup',
						fieldLabel : '是否启用',
						autoHeight : true,
						columns : 2,
						items : [{
									boxLabel : '是',
									name : 'workPlan.status',
									inputValue : 1,
									checked : true
								}, {
									boxLabel : '否',
									name : 'workPlan.status',
									inputValue : 0
								}]
					}
				},{
					columnWidth : 1,
					layout : 'form',
					labelWidth : 70,
					items :  {
							fieldLabel : '标识',
							hiddenName : 'workPlan.icon',
							xtype : 'iconcomb',
							mode : 'local',
							allowBlank : false,
							width : 520,
							editable : false,
							store : new Ext.data.SimpleStore({
										fields : ['flagStyle', 'flagName'],
										data : [['ux-flag-blue', '日常计划'],
												['ux-flag-orange', '重要计划'],
												['ux-flag-green', '特殊计划'],
												['ux-flag-pink','个人计划'],
												['ux-flag-red','紧急计划'],
												['ux-flag-purple','部门计划'],
												['ux-flag-yellow','待定计划']]
									}),
							valueField : 'flagStyle',
							displayField : 'flagName',
							triggerAction : 'all',
							value:'ux-flag-blue'
						}
				},{
					columnWidth : 1,
					layout : 'form',
					labelWidth : 70,
					items : {
					fieldLabel : '备注',
					name : 'workPlan.note',
					xtype : 'textarea',
					width : 520,
					height : 50
				}
				}]
		})

		//加载表单数据
		if(this.planId!=''&&this.planId!=null&&this.planId!=undefined){
		 	    var formPanel = this.formPanel
	            formPanel.loadData({
		                url : __ctxPath + '/task/getWorkPlan.do?planId=' + this.planId,
		                preName:'workPlan',
		                root:'data',
		                scope : this,
						waitMsg : '正在载入数据...',
						success:function(response,options){
							var json=Ext.util.JSON.decode(response.responseText);
							var workPlan=json.data;
							var af = workPlan.planFiles;
							var filePanel = formPanel.getCmpByName('planFilePanel');
							var fileIds = formPanel.getCmpByName("planFileIds");
							for (var i = 0; i < af.length; i++) {
								if (fileIds.getValue() != '') {
									fileIds.setValue(fileIds.getValue() + ',');
								}
								fileIds.setValue(fileIds.getValue() + af[i].fileId);
								Ext.DomHelper.append(
												filePanel.body,
												'<span><a href="#" onclick="FileAttachDetail.show('
														+ af[i].fileId
														+ ')">'
														+ af[i].fileName
														+ '</a><img class="img-delete" src="'
														+ __ctxPath
														+ '/images/system/delete.gif" onclick="PersonalWorkPlanForm.prototype.removeResumeFile(this,'
														+ af[i].fileId
														+ ')"/>&nbsp;|&nbsp;</span>');
							
							}
						},
						failure : function(response,options){
							Ext.ux.Toast.msg('编辑', '载入失败');
						}
	            });
            }
		
		//底部菜单面板
   		this.buttons = [{
				            text : '保存',
				            iconCls : 'btn-save',
				            scope : this,
				            handler : this.saveRecord
				        },{
				        	text : '重置',
							iconCls : 'btn-reset',
							scope : this,
							handler : this.reset
				        },{
				            text : '关闭',
				            iconCls : 'btn-cancel',
				            scope : this,
				            handler : this.cancel
				        }];
        
      },
      //保存
      saveRecord : function() {
      	var formPanel = this.formPanel;
      	if (formPanel.getForm().isValid()) {
				var st=this.formPanel.getCmpByName('workPlan.startTime').getValue();
				var et=this.formPanel.getCmpByName('workPlan.endTime').getValue();
				var sd=Date.parse(st);
			    var ed=Date.parse(et);
		    	if(sd>ed){
		    		Ext.ux.Toast.msg('操作信息', '开始时间大于结束进间,不能保存!');
		    		return;
		    	};
				$postForm({
							formPanel : formPanel,
							scope : this,
							url : __ctxPath + '/task/saveWorkPlan.do',
							callback : function(fp, action) {
								if (this.callback) {
									this.callback.call(this.scope);
								}
								this.close();
							}
				});
      	}
	},
	//重置
	reset : function() {
		this.formPanel.getForm().reset();
	},
	//关闭窗口
	cancel : function() {
		this.close();
	},
	//添加附件
	 addAttachment : function() {
	 	var formPanel =this.formPanel
		var dialog = App.createUploadDialog({
			file_cat : 'task/plan/personalWorkPlan',
			callback : function(data) {
				var fileIds = formPanel.getCmpByName("planFileIds");
				var filePanel =formPanel.getCmpByName('planFilePanel');

				for (var i = 0; i < data.length; i++) {
					if (fileIds.getValue() != '') {
						fileIds.setValue(fileIds.getValue() + ',');
					}
					fileIds.setValue(fileIds.getValue() + data[i].fileId);
					Ext.DomHelper
							.append(
									filePanel.body,
									'<span><a href="#" onclick="FileAttachDetail.show('
											+ data[i].fileId
											+ ')">'
											+ data[i].fileName
											+ '</a> <img class="img-delete" src="'
											+ __ctxPath
											+ '/images/system/delete.gif" onclick="PersonalWorkPlanForm.prototype.removeResumeFile(this,'
											+ data[i].fileId
											+ ')"/>&nbsp;|&nbsp;</span>');
				}
			}
		});
		dialog.show(this);
	},
	//清除附件
	resetAttachment : function() {
		var fileAttaches = this.formPanel.getCmpByName("planFileIds");
		var filePanel = this.formPanel.getCmpByName('planFilePanel');
		filePanel.body.update('');
		fileAttaches.setValue('');
	},
	// 上传文件删除
	removeResumeFile : function (obj, fileId) {
		var fileIds = Ext.getCmp("planFileIds");
		var value = fileIds.getValue();
		if (value.indexOf(',') < 0) {// 仅有一个附件
			fileIds.setValue('');
		} else {
			value = value.replace(',' + fileId, '').replace(fileId + ',', '');
			fileIds.setValue(value);
		}
		var el = Ext.get(obj.parentNode);
		el.remove();
	} 
});