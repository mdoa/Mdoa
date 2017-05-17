/**
 * 组织选择器
 */

DepDialog = Ext.extend(Ext.Panel, {
	constructor : function(conf) {
		Ext.apply(this, conf);
		
		this.initUI();
		
		DepDialog.superclass.constructor.call(this, {
			border:false,
			items:[this.cmp]
		});
	},
	initUI:function(){
		var txtf=new Ext.form.TextField({
			name:this.name,
			margins:Ext.isChrome?'0 10 0 0':'0 3 0 0',
			readOnly:true,
			value:curUserInfo.depName,
			allowBlank:this.allowBlank,
			width:this.width?(this.width-90>0?this.width-90:this.width):this.width
		});
		
		if(this.isSingle==0){
		   txtf=new Ext.form.TextArea({
		       name:this.name,
		       margins:Ext.isChrome?'0 10 3 0':'0 3 0 0',
		       readOnly:true,
		       allowBlank:this.allowBlank,
		       value:curUserInfo.depName,
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
						text:'选择组织',
						iconCls:'btn-sel',
				        handler:function(){
		        	   		DepSelector.getView(function(id, name){
	        	   				txtf.setValue(name);
//			             	    hiddenF.setValue(id);
		        	   		},this.isSingle==1?true:false).show();
				        }
					}
			]
		});
	}
});

Ext.reg('depdialog', DepDialog);