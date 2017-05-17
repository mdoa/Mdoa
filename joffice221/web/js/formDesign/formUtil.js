/**
 * form 转换Ext控件
 */
var $formConver = function() {
//	// 当前form
	var form = this.formPanel.getForm().getEl().dom;
//	// 当前table
	var tables = form.getElementsByTagName('table');

	// 转化控件及赋值
//	var fElements = form.elements
//			|| (document.forms[form] || Ext.getDom(form)).elements;
	//查找该form下的自定义的节点
	var fElements =  Ext.query('*[@name=editable-input]',form);
	$formConverCmp.call(this, fElements);
};
/**
 * 转化控件及赋值
 * @param {} fElements
 */
var $formConverCmp = function(fElements) {
}
