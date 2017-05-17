/**
 * 表单初始化
 * 
 */
var editor, valid, formContainer, status= $('#status').val(),canEditColumnNameAndType= $('#canEditColumnNameAndType').val();
// 初始化
$(function() {
			var h = $(document.body).height();
			editor = new baidu.editor.ui.Editor({
						minFrameHeight : h - 210
					});
			if(canEditColumnNameAndType == "false"){
				editor.canEditColumnNameAndType = 1;
			}
			editor.render("defHtml");

			valid = $("#formDefForm").form();

			$("#btnPreView").click(function() {
						preView();
					});

			$("#btnNext").click(function() {
						nextStep();
					});

			$("#btnSaveForm").click(function() {
						saveForm();
					});
		});
/**
 * 是否在源代码模式下
 * 
 * @return {Boolean}
 */
function isSourceMode() {
	if (editor.ifSourceMode() == 1) {
		$.ligerMessageBox.warn('提示信息', '不能在源代码模式下该操作！');
		return true;
	}
	return false;
}


/**
 * 保存表单
 */
function saveForm(){
	// 是否在源代码模式下
	if (isSourceMode())
		return;
	// 验证表单
	var rtn = valid.valid();
	if (!rtn)
		return;	
		
	// 回调
	var callBack=function(){
			var params={};
			params.formDefId=$("#formDefId").val();		
			params.formTitle=$("#formTitle").val();
			params.formDesp=$("#formDesp").val();
			// 是否发布
			if(status){
				params.tableKey=$("#tableKey").val();
				params.tableName=$("#tableName").val();
			}
			
			var html = editor.getContent();
			var defHtml = htmlDecode(html);// external中的内容需要替换回单引号和双引号
			
			params.defHtml = defHtml;
			params.html = html;
			
			var url =  __ctxPath + '/flow/saveFormFormDef.do';
			
			$.ajax({
			   type: "POST",
			   url: url,
			   data: params,
			   dataType:'json',
			   success:function(data){
						if (data.valid) {
							$.ligerMessageBox.success('提示信息',data.msg,function(){
								window.close();
								window.returnValue = true;
							});
						} else {
							$.ligerMessageBox.error('出错提示', data.msg);
						}
			   },
			   error:function(data){
					$.ligerMessageBox.error('出错提示', '保存失败！');
				}
		});
	};
		
	validForm(callBack);
}
/**
 * 下一步
 */
function nextStep() {
	// 是否在源代码模式下
	if (isSourceMode())
		return;
	// 验证表单
	var rtn = valid.valid();
	if (!rtn)
		return;

	var frm=document.getElementById("formDefForm");
	frm.action = __ctxPath + '/flow/designTableFormDef.do';
	// 回调
	var callBack = function(params) {
		$("#content").val(params.defHtml);
		
		frm.submit();
	};
	// 验证表单是否合法。
	validForm(callBack);
};
// 表单保存到本地
function exportform(){
	var rtn = valid.valid();
	if (!rtn)
		return;
		
	var formTitle = $('#formTitle').val();
	var defHtml = editor.getContent();
	
	var url = __ctxPath + '/flow/exportFormFormDef.do';
		
	var frm=new com.hotent.form.Form();
	frm.creatForm("exportForm",url);		
	frm.addFormEl("formTitle",formTitle);
	frm.addFormEl("defHtml",htmlDecode(defHtml));
	frm.setTarget("_blank");
	frm.setMethod("post");
	frm.submit();
	frm.clearFormEl();
};


// 预览
function preView() {
	var rtn = valid.valid();
	if (!rtn)
		return;

	var url = __ctxPath + '/flow/previewFormDef.do';
	var tableName = 'main';
	var formTitle = $('#formTitle').val();
	var defHtml = editor.getContent();

	var frm = new com.hotent.form.Form();
	frm.creatForm("FormPreview", url);
// frm.addFormEl("tableKey", tableKey);
	frm.addFormEl("formTitle", formTitle);
	frm.addFormEl("defHtml", defHtml);
	frm.setTarget("_blank");
	frm.setMethod("post");
	frm.submit();
	frm.clearFormEl();
};
/**
 * 验证表单是否合法。
 * 
 * @param {}
 *            callBack 回调方法
 */
function validForm(callBack) {
	var html = editor.getContent();
	var defHtml = htmlDecode(html);// external中的内容需要替换回单引号和双引号

	var params = {};
	params.formDefId = $("#formDefId").val();
	params.formTitle = $('#formTitle').val();
	params.defHtml = defHtml;
	params.html = html;
	params.tableKey = isEmpty($("#tableKey").val())?"main":$("#tableKey").val();
	params.tableName= isEmpty($("#tableName").val())?"主表":$("#tableName").val();

	var url = __ctxPath + '/flow/validDesignFormDef.do';

	$.ajax({
	   type: "POST",
	   url: url,
	   data: params,
	   dataType:'json',
	   success:function(data){
				if (data.valid) {
					// 使用回调函数
					callBack(params);
				} else {
					$.ligerMessageBox.error('出错提示', data.errorMsg);
				}
	   },
	   error:function(data){
			$.ligerMessageBox.error('出错提示', "验证表单出错了");
		}
	   });
};
// 解码单引号和双引号
function htmlDecode(str) {
	return str.replace(/\&quot\;/g, '\"').replace(/\&\#39\;/g, '\'');
};
