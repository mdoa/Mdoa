var excelplugin = {};
/**
 * 将tr框中的内容进行交换
 * 仅在该插件内部使用
 */
excelplugin.changeAttr = function(dom1,dom2){
	var propertyName1 = dom1.find('input[name=propertyName]').val();
	var propertyName2 = dom2.find('input[name=propertyName]').val();
	dom1.find('input[name=propertyName]').val(propertyName2);
	dom2.find('input[name=propertyName]').val(propertyName1);
	
	var tableHeader1 = dom1.find('input[name=tableHeader]').val();
	var tableHeader2 = dom2.find('input[name=tableHeader]').val();
	dom1.find('input[name=tableHeader]').val(tableHeader2);
	dom2.find('input[name=tableHeader]').val(tableHeader1);
	
	var dateFormat1 = dom1.find('select[name=dateFormat]').val();
	var dateFormat2 = dom2.find('select[name=dateFormat]').val();
	dom1.find('select[name=dateFormat]').val(dateFormat2);
	dom2.find('select[name=dateFormat]').val(dateFormat1);
	
	var dateFormatAttr1 = dom1.find('select[name=dateFormat]').attr('disabled');
	var dateFormatAttr2 = dom2.find('select[name=dateFormat]').attr('disabled');
	
	if(typeof dateFormatAttr1 == 'undefined')
		dom2.find('select[name=dateFormat]').removeAttr('disabled');
	else
		dom2.find('select[name=dateFormat]').attr('disabled','disabled');
	
	if(typeof dateFormatAttr2 == 'undefined')
		dom1.find('select[name=dateFormat]').removeAttr('disabled');
	else
		dom1.find('select[name=dateFormat]').attr('disabled','disabled');
}

excelplugin.selectAll = function(dom){
	dom.find('input[name=propertyName]').prop('checked',true);
//	dom.find('input[name=selectAll]').attr('checked','checked');
}

excelplugin.cleanAll = function(dom){
	dom.find('input[name=propertyName]').prop('checked',false);
//	dom.find('input[name=selectAll]').removeAttr('checked');
}

/**
 * 生成一个 excel表的设置器
 设置器生成参数：
 columns : 所生成的列的信息
 	propertyName : 后台用于处理的字段名
 	tableHeader : excel的字段名对应的表头
 	date : boolean 型变量，如果为 true 则为 时间类型，允许使用时间选择
 dom : 所生成的位置的节点
 */
excelplugin.excel = function(plugin){
	var html =  ''
	html += '<table><tbody>';
	html += '<tr>'
		+'<th style="width:19px;"><input name="selectAll" type="checkbox"></th>'
		+'<th style="width: 1px;text-align:center;">字段名称</th>'
		+'<th style="width: 1px;text-align:center;">时间格式</th>'
		+'<th style="width:33px;text-align:center;">顺序</th></tr>';
	for(var i = 0 ; i < plugin.columns.length ; i++){
		html += '<tr>'
			+'<td><input type="checkbox" name="propertyName" value="'+plugin.columns[i].propertyName+'"></td>'
			+'<td><input type="text" name="tableHeader" value="'+plugin.columns[i].tableHeader+'" style="text-align:center;" disabled="disabled"></td>'
			+'<td>';
		if(plugin.columns[i].date){
			html += '<select name="dateFormat">';
		}else{
			html += '<select disabled="disabled" name="dateFormat">';
		}
		html += '<option></option>'
			+'<option value="yyyy年MM月dd日">2000年01月01日</option>'
			+'<option value="yyyy年MM月dd日&nbsp;HH时mm分ss秒">2000年01月01日 23时00分00秒</option>'
			+'<option value="yyyy-MM-dd">2000-01-01</option>'
			+'<option value="yyyy-MM-dd&nbsp;HH-mm-ss">2000-01-01 23:00:00</option>'
			+'</select></td>'
			+'<td>'
		if(i == 0){
			html+='<span class="">&nbsp;&nbsp;</span>';
			html+='<span class="downset" title="下移"></span>';
		}else if(i == plugin.columns.length - 1){
			html+='<span class="upset" title="上移"></span>';
			html+='<span class=""></span>';
		}else{
			html+='<span class="upset" title="上移"></span>';
			html+='<span class="downset" title="下移"></span>';
		}
		html += '</td></tr>';
	}
	html += '</tbody></table>';
	plugin.dom.html(html);
	//上移点击事件
	plugin.dom.find('.upset').click(function(){
		var dom1 = $(this).parents('tr');
		var dom2 = $(this).parents('tr').prev();
		excelplugin.changeAttr(dom1, dom2);
	});
	//下移点击事件
	plugin.dom.find('.downset').click(function(){
		var dom1 = $(this).parents('tr');
		var dom2 = $(this).parents('tr').next();
		excelplugin.changeAttr(dom1, dom2);
	});
	//全选或全部取消选择点击事件
	plugin.dom.find('input[name=selectAll]').click(function(){
		if($(this).is(':checked'))
			excelplugin.selectAll(plugin.dom);
		else
			excelplugin.cleanAll(plugin.dom);
	})
}
/**
 * 获取关于excel表格式的json串
 */
excelplugin.getJson = function(target){
	var checkboxs = target.find('input[name=propertyName]:checked');
	var array = [];
	for(var i = 0 ; i < checkboxs.length ; i++){
		var dom = checkboxs.eq(i).parents('tr');
		var node = {
			propertyName: dom.find('input[name=propertyName]').val(),
	        tableHeader: dom.find('input[name=tableHeader]').val(),
	        dateFormat: dom.find('input[name=dateFormat]').val(),
	        propertyType: "String",
		};
		array.push(node);
	}
	return JSON.stringify(array);
}

/**
 * 向服务器进行form表单形式的提交
 action : 参数地址
 array : 数据列
 	name : 向后台传输的参数名
 	value : 向后台传输的参数值
 type : 传输类型   post/get  默认为post
 */
excelplugin.submit = function(submitForm){
	if(!submitForm.type)
		submitForm.type = 'post';
	var form=$("<form>");//定义一个form表单
	form.attr("style","display:none");
	form.attr("target","");
	form.attr("method",submitForm.type);
	form.attr("action",submitForm.action);
	for(var i = 0 ; i < submitForm.array.length ; i ++){
		var input = $("<input>");
		input.attr("type","hidden");
		input.attr("name", submitForm.array[i].name);
		input.attr("value", submitForm.array[i].value);
		form.append(input);
	}
	$("body").append(form);//将表单放置在web中
	form.submit();//表单提交 
	form.remove();
}