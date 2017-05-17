Ext.ns('Ext.ux.form');
Ext.ux.form.CommentEditor = Ext.extend(Ext.form.Field,  {
    allowBlank:true,
    blankText:Ext.form.TextArea.prototype.blankText,
    readOnly:false,
    // private
    defaultAutoCreate : {tag: "div"},

    // private
    initComponent: function(){
        Ext.ux.form.CommentEditor.superclass.initComponent.call(this);
//
//        this.addEvents({
//            'dblclick' : true,
//            'click' : true,
//            'change' : true
//        });
    },

    // private
    onRender: function(ct, position){
        Ext.ux.form.CommentEditor.superclass.onRender.call(this, ct, position);

        var fs = this.fs = new Ext.Panel({
            renderTo: this.el,
            height: this.height,
            autoScroll:this.autoScroll,
            width: this.width,
            border:false,
            style: "padding:0;",
            layout:'form'
        });
        this.store = new Ext.data.JsonStore( {
			fields : ['un','ui','c', 'v']
		});
		
		//this.store.on('load',this.storeload,this);
		
		this.view = new Ext.DataView({
	            autoScroll:false,
				store : this.store,
				// 单选
				multiSelect : false,
				//itemSelector:'div.thumb-w',
				singleSelect:false,
				border:true,
//				plugins : [ new Ext.DataView.LabelEditor({dataIndex : 'content'}) ],
				// 模板
				tpl : new Ext.XTemplate(
							'<tpl if="xcount &gt; 0">',
					            '<tpl for=".">',
									'<div class="thumb-w" style="padding:2px;"><span><font>{un}({c}):</font><span><span>{v}</span></div>',
								'</tpl>',
							'</tpl>'
				),
				scope:this
			
		});
        
        fs.add(this.view);
        this.inputField=new Ext.form.TextArea({
        	width:'98%',
        	hideLabel:true,
        	hidden:this.readOnly,
        	allowBlank:this.allowBlank
        });
        fs.add(this.inputField);
        this.inputField.on('change',this.onFieldChange,this);
        this.hiddenName = this.name || Ext.id();
        var hiddenTag = { tag: "input", type: "hidden", value: "", name: this.hiddenName };
        this.hiddenField = this.el.createChild(hiddenTag);
        this.hiddenField.dom.disabled = this.hiddenName != this.name;
        fs.doLayout();
    },
    onFieldChange:function(f,nl,ol){
    	var objs=[];
    	for(var i=0;i<this.store.getCount();i++){
    		var rec=this.store.getAt(i);
    		objs.push(rec.data);
    	}
    	if(nl){
    		objs.push({ui:curUserInfo.userId,un:curUserInfo.fullname,c:formatDate(new Date(),'yyyy-MM-dd HH:mm'),v:nl});
    	}
    	var values=Ext.encode(objs);
        this.fireEvent('change', this,values, this.hiddenField.dom.value);
        this.hiddenField.dom.value = values;
        this.validate();
    },
    
    setValue: function(values) {
        this.hiddenField.dom.value = '';
        if (!values || (values == '')) { return; }
        var recs=Ext.decode(values);
        try{
        	this.store.loadData(recs);
        }catch(e){
           alert(e);
        }
        this.hiddenField.dom.value = values;
        this.validate();
    },

    // inherit docs
    disable: function(){
        this.disabled = true;
        this.hiddenField.dom.disabled = true;
        this.fs.disable();
    },

    // inherit docs
    enable: function(){
        this.disabled = false;
        this.hiddenField.dom.disabled = false;
        this.fs.enable();
    },

    // inherit docs
    destroy: function(){
        Ext.destroy(this.fs);
        Ext.ux.form.CommentEditor.superclass.destroy.call(this);
    }
});


Ext.reg('commenteditor', Ext.ux.form.CommentEditor);