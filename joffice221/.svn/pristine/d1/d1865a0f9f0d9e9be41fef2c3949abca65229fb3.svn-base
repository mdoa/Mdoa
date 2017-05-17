/**
 * 用户选择器
 */

UserDialog = Ext.extend(Ext.Panel, {
	constructor : function(conf) {
		Ext.apply(this, conf);
		
		this.initUI();
		
		UserDialog.superclass.constructor.call(this, {
			border:false,
			items:[this.cmp]
		});
	},
	initUI:function(){
		
		var span=document.createElement('div');
		var hiddenF=new Ext.form.Hidden({
			value:this.iscurrent==1?curUserInfo.userId:'',
		    name:this.name+'UId'
		});
		hiddenF.render(span);
		
		var txtf=new Ext.form.TextField({
			name:this.name,
			margins:Ext.isChrome?'0 10 0 0':'0 3 0 0',
			readOnly:true,
			value:curUserInfo.fullname,
			allowBlank:this.allowBlank,
			width:this.width?(this.width-90>0?this.width-90:this.width):this.width
		});
		
		if(this.isSingle==0){
		   txtf=new Ext.form.TextArea({
		       name:this.name,
		       margins:Ext.isChrome?'0 10 3 0':'0 3 0 0',
		       readOnly:true,
		       allowBlank:this.allowBlank,
		       value:curUserInfo.fullname,
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
						text:'选择人员',
						iconCls:'btn-sel',
						scope:this,
				        handler:function(){
				        	new UserDialog({
								single : this.isSingle==1?true:false,
								isForFlow : false,
								scope : this,
								callback : function(ids, names) {
									txtf.setValue(names);
				        			hiddenF.setValue(ids)
								}
							}).show();
				        }
					}
			]
		});
		
		if(this.value){
			txtf.setValue(this.value);
		}
		
	}
});

Ext.reg('userdialog', UserDialog);