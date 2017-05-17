/**
 * 表单明细表
 */

FormDetailPanel = Ext.extend(Ext.Panel, {
	constructor : function(conf) {
		Ext.apply(this, conf);
		
		this.initUI();
		
		FormDetailPanel.superclass.constructor.call(this, {
			border:false,
			items:[this.detailPanels],
			buttonAlign:'left',
			buttons:[{
				iconCls:'btn-add',
				text:'添加',
				xtype:'button',
				scope:this,
				handler:this.additem
			}]
		});
	},
	initUI:function(){
		
		this.itemidx = 0;
		
		this.detailPanels = new Ext.Panel({border:false});
	},
	
	additem:function(){
		
		var detailPanel = new Ext.Panel({
			layout:'table',
			border:false,
			id:'formdp'+this.itemidx,
			layoutConfig: {
				columns:this.tablerows,
				tableAttrs:{
					bordercolor:'#99BBFF',
					border:1
				}
			},
			bodyStyle:'margin-bottom:5px;'
		});
		
		var btnDelPanel = new Ext.Panel({
			border:false,
			colspan:this.tablerows,
			items:[{
				iconCls: 'btn-del',
				text: '删除',
				xtype: 'button',
				scope: this,
				dpflag:'formdp'+this.itemidx,
				handler: this.delitem
			}]
		});
		
		detailPanel.add(btnDelPanel);
		detailPanel.add(this.item);
		
		this.detailPanels.add(detailPanel);
		this.detailPanels.doLayout();
		
		this.itemidx++;
	},
	
	delitem:function(obj, e){
		var dpflag = obj.dpflag;
		var detailPanels = this.detailPanels;
		Ext.Msg.confirm('信息确认', '您确认要删除所选记录吗？', function(btn) {
			if (btn == 'yes') {
				detailPanels.remove(Ext.getCmp(dpflag));
				detailPanels.doLayout();
			}
		});
	}
	
});

Ext.reg('formDetailPanel', FormDetailPanel);