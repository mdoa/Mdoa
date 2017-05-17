CKEDITOR.config.toolbar_DesignDefault =
[
		{ name: 'document',		items : [ 'Source','-','Print','Maximize', 'ShowBlocks'] },
		{ name: 'clipboard',	items : [ 'Cut','Copy','Paste','PasteText','PasteFromWord','-','Undo','Redo' ] },
		{ name: 'editing',		items : [ 'Find','Replace','-','SelectAll','-','SpellChecker', 'Scayt' ] },
		{ name: 'dforms',        items:  ['DHiddenField','DSelect','DTextField','DTextArea','DCheckbox','DRadio','Diccombo','UserSelector','DepSelector','PosSelector','DateField','Ckeditor','Officeeditor','Commoneditor','Fileattach','Grid']},
		{ name: 'forms',		items : ['ImageButton', '-','Image','Flash','DTable','HorizontalRule','Smiley','SpecialChar','PageBreak','Iframe','-','Link','Unlink','Anchor' ] }, //'Form', 'Checkbox', 'Radio', 'Textarea', 'Select', 'Button','HiddenField',
		{ name: 'basicstyles',	items : [ 'Bold','Italic','Underline','Strike','Subscript','Superscript','-','RemoveFormat' ] },
		{ name: 'paragraph',	items : [ 'NumberedList','BulletedList','-','Outdent','Indent','-','Blockquote','CreateDiv','-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','BidiLtr','BidiRtl' ] },
		{ name: 'styles',		items : [ 'Styles','Format','Font','FontSize' ] },
		{ name: 'colors',		items : [ 'TextColor','BGColor' ] }
];

CKEDITOR.editorConfig = function( config )
{
	config.language = 'zh-cn';
	config.uiColor = '#AADC6E';
	config.skin='office2003';
	config.font_names = '宋体;楷体_GB2312;新宋体;黑体;隶书;幼圆;微软雅黑;Arial;Comic Sans MS;Courier New;Tahoma;Times New Roman;Verdana';
	config.toolbarStartupExpanded=true;
	
	config.plugins+=',dforms,dtable,dtabletools,ajax';
	
	config.mvenu_groups='clipboard,'+
	'tablecell,tablecellproperties,tablerow,tablecolumn,dtable,grid,'+
	'anchor,link,image,flash,' +
	'dcheckbox,dradio,imagebutton,button,dtextarea,div,dtextfield,userselector,depselector,posselector,datefield,dhiddenfield,dselect,ckeditor,officeeditor,fileattach,commoneditor,diccombo';
};
