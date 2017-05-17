/**
 * 表单明细表
 */

FormDetailGrid = Ext.extend(Ext.Panel, {
	constructor : function(conf) {
		Ext.apply(this, conf);
		
		this.initUI();
		
		FormDetailGrid.superclass.constructor.call(this, {
			border:false,
			items:[this.detailPanel]
		});
	},
	initUI:function(){

		this.detailGrids=new Ext.util.MixedCollection();
		
		this.oldrow = -1;
		
		this.detailPanel = new HT.EditorGridPanel({
//			hiddenSm:this.readOnly,
			id:'fdg-'+this.gridName,
			tbar:new Ext.Toolbar({
//				disabled:this.readOnly==true?true:false,
				frame: true,
				items:[{
						text:'添加记录',
						iconCls:'btn-add',
						scope:this,
						gridName:this.gridName,
						handler:function(cmp){
							var detailPanel=this.detailGrids.get(cmp.gridName);
							var recordType=detailPanel.getStore().recordType;
							var record=new recordType();
							var fields=record.fields.items;
							for(var v=0;v<fields.length;v++){
							   var field=fields[v];
							   if(field.defaultValue){
							      record.set(field.name,field.defaultValue);
							   }
							}
							detailPanel.getStore().add(record);
						}
					},{
						text:'删除记录',
						iconCls:'btn-del',
						scope:this,
						gridName:this.gridName,
						handler:this.detailDel
					}
				]
			}),
			clicksToEdit:1,
			width:600,
			showPaging:false,
			autoHeight:true,
			fields:this.fields,
			columns:this.columns,
			headers:this.headers
		});
		
		this.detailGrids.add(this.gridName, this.detailPanel);
		
	},
	
	detailDel:function(cmp){
		var detailPanel=this.detailGrids.get(cmp.gridName);
		var gridName=cmp.gridName;
		var taskId=this.taskId;
		Ext.Msg.confirm('信息确认', '您确认要删除所选记录吗？', function(btn) {
			if (btn == 'yes') {
				var tableId=null;
				if(taskId){
					tableId=document.getElementById(gridName+'_'+taskId).value;
				}
				var store=detailPanel.getStore();
				var selRs =detailPanel.getSelectionModel().getSelections();
				var ids=[];
				var delRecords=[];
				var pkKey=document.getElementById('WF_'+gridName+'_'+taskId);
				var pkKeyVar;
				if(pkKey){
				    pkKeyVar=pkKey.value;
				}
				for(var i=0;i<selRs.length;i++){
					if(selRs[i].data!=null){
						if(pkKeyVar){
							var detailId=selRs[i].data[pkKeyVar];
							if(detailId){
							   ids.push(detailId);
							}
						}
						delRecords.push(selRs[i]);
					}
				}
				if(ids.length){
					Ext.Ajax.request({
						url :__ctxPath+'/flow/delItemsProcessActivity.do',
						params : {tableId:tableId,ids : ids},method : 'POST',
						success : function(response,options) {
							Ext.ux.Toast.msg('操作信息','成功删除该记录！');
								store.remove(delRecords);
							
						},
						failure : function(response,options) {
							Ext.ux.Toast.msg('操作信息','操作出错，请联系管理员！');
						}
					});
				}else{
					store.remove(delRecords);
				}
			}
		});
	}
	
});

Ext.reg('formDetailGrid', FormDetailGrid);