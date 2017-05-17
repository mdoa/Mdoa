// dialog 处理js
var oldElement, valid, cmdName = editor.curInputType, language = UE.I18N[editor.options.lang]['inputDialog'];
/**
 * 
 * 如果传入的值是null、undefined或空字符串，则返回true。（可选的）
 * 
 * @param {Mixed}
 *            value 要验证的值。
 * @param {Boolean}
 *            allowBlank （可选的） 如果该值为true，则空字符串不会当作空而返回true。
 * @return {Boolean}
 */
var isEmpty = function(v, allowBlank) {
	return v === null || v === undefined || (!allowBlank ? v === '' : false);
}
/**
 * 加载完inputPanel的内容以后触发该事件 对页面初始化及赋值
 */
function initComplete() {
	$(".button-td").bind("mouseenter mouseleave",function(){
		$(this).toggleClass("button-td-hover");
	});
		
	if(cmdName == 'subtable'){//子表
		editInitData();
	}else{
		$("[eid='type']").bind("change", typeChange);
		$("[eid='value']").bind("change", valueChange);
		$("#isQuery").bind("click", conditionChange);
		$("[eid='searchFrom']").bind("change", searchFromChange);
		typeChange.call($("[eid='type']")[0]);
		if (checkName(cmdName)) {// 需要验证规则
			$(".validRule_td").html("<span style='color:red;'>"+language.loadRule+"</span>");
			getAllRules();
		} else {
			editInitData();
		}
	}
};

/**
 * 需要验证规则
 * 
 * @param {}
 *            name
 * @return {Boolean}
 */
function checkName(name) {
	if (!isEmpty(name)) {
		if (name == 'textfield' || name == 'textarea' 
		|| name == 'checkbox'|| name == 'radioinput' || name == 'selectinput') {
			return true;
		}
	}
	return false;
}

/**
 * 获取所有验证规则
 */
function getAllRules() {
	var url = __ctxPath + "/flow/getAllFormRule.do";
	$.ajax({
	   type: "POST",
	   url: url,
	   dataType:'json',
	   success:function(data){
			if (!data)
				return;
			data = eval(data);
			var html = ['<select eid="content" prenode="valueFrom"><option value="">'+language.not+'</option>'];
			for (var i = 0, c; c = data[i++];) {
				html.push('<option value="' + c.ruleId + '">' + c.name
						+ '</option>');
			}
			html.push('</select></td>');
			$(".validRule_td").html(html.join(''));
			// 修改初始化数据
			editInitData();
	   },
	   error:function(data){
			$(".validRule_td").html("<span style='color:red;'>"+language.loadError+"</span>");
		}
	});
};
/**
 * 查询条件值显示
 */
function conditionChange() {
	$(".condition_tr").toggleClass("hidden");
};
/**
 * 查询条件值来源变化
 */
function searchFromChange() {
	var val = $(this).val();
	if (val == "fromForm")
		$(".searchValue-td").addClass('hidden');
	else
		$(".searchValue-td").removeClass('hidden');
};

/**
 * 修改状态下的初始化 下的绑定数据
 */
function editInitData() {
	oldElement = null;
	if (!editor.curInputElement)
		return;
	var externalStr = $(editor.curInputElement).attr("external");
	externalStr = utils.htmlDecode(externalStr);
	var external = eval("(" + externalStr + ")");
	oldElement = editor.curInputElement;
	editor.curInputElement = null;
	// 绑定数据
	bind(external);
}
/**
 * 递归绑定值
 * 
 * @param {}
 *            d
 */
function bind(d) {
	for (var k in d) {
		var val = d[k];
		if (typeof val == 'object' && val != null) {
			bind(val);
		} else if (val === 1) { // 单选
			$("[eid='" + k + "']").attr("checked", "checked");
			if (k == 'isQuery') {// 是否查询
				conditionChange();
			}
			if (editor.canEditColumnNameAndType) {
				if (k == 'displayDate')
					$("[eid='" + k + "']").attr("disabled", "disabled");
			}
		} else {
			val = val.toString().replace(/#newline#/g, '\n');
			$("[eid='" + k + "']:visible").val(val);
			if (k == 'type') {
				typeChange.call($("[eid='" + k + "']")[0]);
			}
			if (k == 'value') {
				valueChange.call($("[eid='" + k + "']")[0]);
			}
			if (k == 'searchFrom') {
				searchFromChange.call($("[eid='" + k + "']")[0]);
			}
			if (editor.canEditColumnNameAndType) {
				if (k == 'name' || k == 'type' || k == 'length'
						|| k == 'intLen' || k == 'decimalLen' || k == 'format') {// 编辑时不能修改字段的这些属性
					$("[eid='" + k + "']").attr("disabled", "disabled");
				}
			}
		}
	}
}
// 值来源变化
function valueChange() {
	var val = $(this).val();
	$("tr[class^='valuefrom']").each(function() {
				if ($(this).attr('class').indexOf(val) > -1) {
					$(this).removeClass('hidden');
				} else {
					$(this).addClass('hidden');
				}
			});
};
/**
 * 值类型变化
 */
function typeChange() {
	$(".dbformat_td").html(getFormatHtml($(this).val()));
	if (cmdName == "attachement") {// 附件设置长度为2000
		$("[eid='length']").val('2000');
	}
};

/**
 * 切换数据格式
 */
function getFormatHtml(type) {
	var html = [''];
	switch (type) {
		case 'varchar' :
			html
					.push(language.length+':<input eid="length" prenode="dbType" style="width:60px;" type="text" value="50" validate="{number:true}"/>');
			break;
		case 'number' :
			html
					.push(language.integer+':<input eid="intLen" prenode="dbType" style="width:30px;" type="text" value="14" validate="{number:true}"/><br/>'
					+language.decimal+':<input eid="decimalLen" prenode="dbType" style="width:30px;" type="text" value="0" validate="{number:true}"/>');
			break;
		case 'date' :
			html
					.push('<select eid="format" prenode="dbType">' +
							'<option value="yyyy-MM-dd">yyyy-MM-dd</option>' +
							'<option value="yyyy-MM-dd HH:mm:ss">yyyy-MM-dd HH:mm:ss</option>' +
							'<option value="yyyy-MM-dd HH:mm:00">yyyy-MM-dd HH:mm:00</option>' +
							//'<option value="HH:mm:ss">HH:mm:ss</option>' +
							//'<option value="HH:mm">HH:mm</option>' +
						'</select><br /><label for="displayDate">' +
						'<input id="displayDate" eid="displayDate" prenode="dbType" type="checkbox" />'+language.currentTime+'</label>');
			break;
	}
	return html.join('');
};
/**
 * 确定 按鈕
 */
dialog.onok = function() {
	var rtn = valid.valid();
	if (!rtn)
		return false;
	var data = [];
	var dictType= $("#dictType").val();
	$("#inputPanel").find("*:visible").each(function() {
		var id = $(this).attr("eid"), val = $(this).val(), prenode = $(this)
				.attr("prenode");
		if ($(this).attr("type")) {
			if ($(this).attr("type") == "checkbox"
					|| $(this).attr("type") == "radio") {
				val = (!!$(this).attr("checked")) ? 1 : 0;
			}
		}
		if ((id && val) || (id && val == 0)) {
			var item = {
				id : id,
				val : val
			};
			if (prenode)
				item.prenode = prenode;
			data.push(item);
		}
	});
	// 数据字典取值
	if (!isEmpty(dictType)) {
		data.push({
					id : 'dictType',
					val : dictType
			});
	}
	editor.execCommand(cmdName, data, oldElement);
};

/**
 * 加载初始化
 */
$(function() {
			$focus($G("fieldName"));
			initComplete();
			valid = $("#inputPanel").form();
		});