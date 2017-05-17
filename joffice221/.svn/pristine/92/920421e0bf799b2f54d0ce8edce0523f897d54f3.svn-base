/**
 * 岗位选择器
 */

PosDialog = Ext.extend(Ext.Panel, {
	constructor : function(conf) {
		Ext.apply(this, conf);
		
		this.initUI();
		
		PosDialog.superclass.constructor.call(this, {
			border:false,
			items:[this.cmp]
		});
	},
	initUI:function(){
		var txtf=new Ext.form.TextField({
			name:this.name,
			margins:Ext.isChrome?'0 10 0 0':'0 3 0 0',
			readOnly:true,
			allowBlank:this.allowBlank,
			width:this.width?(this.width-90>0?this.width-90:this.width):this.width
		});
		
		if(this.isSingle==0){
		   txtf=new Ext.form.TextArea({
		       name:this.name,
		       margins:Ext.isChrome?'0 10 3 0':'0 3 0 0',
		       readOnly:true,
		       allowBlank:this.allowBlank,
		       width:this.width?(this.width-90>0?this.width-90:this.width):this.width
		   });
		}
		
		this.cmp=new Ext.form.CompositeField({
			width:this.width,
			items:[txtf,
			       {
						xtype:'button',
						width:78,
						border:false,
						text:'选择岗位',
						iconCls:'btn-sel',
				        handler:function(){
		        			new PositionDialog({
		        				scope:this,
		        				single:this.isSingle==1?true:false,
		        				callback:function(ids,names){
		        	   				txtf.setValue(names);
//				             	    hiddenF.setValue(ids);
		        				}
		        			}).show();
				        }
					}
			]
		});
	}
});

Ext.reg('posdialog', PosDialog);