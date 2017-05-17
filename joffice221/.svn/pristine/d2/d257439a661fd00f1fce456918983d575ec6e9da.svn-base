/****************************************************
* CKdesigner Extension
* {
	  xtype: 'ckdesigner',
	  fieldLabel: 'Editor',
	  name: 'htmlcode',
	  customConfig : '/ckeditor/config.js',
      toolbar: 'Other',
      height : 200,
      width: 250
   }
* 
*****************************************************/
Ext.form.CKDesigner = function(config){
  this.config = config;
  Ext.form.CKDesigner.superclass.constructor.call(this, config);
};

Ext.form.CKDesigner.CKEDITOR_CONFIG = __ctxPath+"/js/ckeditor/cus_config.js";
Ext.form.CKDesigner.CKEDITOR_TOOLBAR = "DesignDefault";

Ext.extend(Ext.form.CKDesigner, Ext.form.TextArea,  {
  onRender : function(ct, position){
    if(!this.el){
      this.defaultAutoCreate = {
        tag: "textarea",
        autocomplete: "off"
      };
    }
    Ext.form.TextArea.superclass.onRender.call(this, ct, position);
    
    var config =
    {
      customConfig: Ext.form.CKDesigner.CKEDITOR_CONFIG,
      toolbar: Ext.form.CKDesigner.CKEDITOR_TOOLBAR
    };
    
    Ext.apply(config, this.config);
    if(!config.height) config.height=150;
    var editor = CKEDITOR.replace(this.id, config);
    CKFinder.setupCKEditor( editor, __ctxPath+'/js/ckeditor/ckfinder/' ) ;
  },
 
  onDestroy: function(){
    if (CKEDITOR.instances[this.id]) {
      delete CKEDITOR.instances[this.id];
    }
  },
 
  setValue : function(value){
    Ext.form.TextArea.superclass.setValue.apply(this,[value]);
    CKEDITOR.instances[this.id].setData( value );
  },
 
  getValue : function(){
    CKEDITOR.instances[this.id].updateElement();
    var value=CKEDITOR.instances[this.id].getData();
    Ext.form.TextArea.superclass.setValue.apply(this,[value]);
    return Ext.form.TextArea.superclass.getValue.call(this);    
  },

  getRawValue : function(){
    CKEDITOR.instances[this.id].updateElement();
    return Ext.form.TextArea.superclass.getRawValue.call(this);
  }
});
Ext.reg('ckdesigner', Ext.form.CKDesigner);