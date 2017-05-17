var valid;
// 初始化数据
$(function() {
			$("#btnDeploy").click(function() {
						deploy();
					});
			valid = $("#formDefForm").form();
		});

/**
 * 发布表单
 */
function deploy() {
	var rtn = valid.valid();
	if (!rtn)
		return;
	$.ligerDialog.confirm("确认要发布表单吗?", function(r) {
		if (r) {
			var params = {};
			var container = $("#formDefForm");
			$("input,textarea", container).each(function() {
						var obj = $(this);
						params[obj.attr("name")] = obj.val();
					});
			$.ligerDialog.waitting("正在发布表单，请稍候...");
			var url = __ctxPath + '/flow/saveFormFormDef.do';
			$.ajax({
						type : "POST",
						url : url,
						data : params,
						dataType : 'json',
						success : function(data) {
							$.ligerDialog.closeWaitting();
							if (data.valid) {
								$.ligerMessageBox.success('提示信息',
										data.msg, function() {
											window.close();
											window.returnValue = true;
										});
							} else {
								$.ligerMessageBox.error('出错提示',
										data.msg);
							}
						},
						error : function(data) {
							$.ligerDialog.closeWaitting();
							$.ligerMessageBox.error('出错提示', '发布失败！');
						}
					});

		}
	});
}

// 返回编辑界面
function goBack() {
	$("#formDefForm")[0].submit();
};