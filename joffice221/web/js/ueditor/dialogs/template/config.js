/**
 * Created with JetBrains PhpStorm. User: xuheng Date: 12-8-8 Time: 下午2:00 To
 * change this template use File | Settings | File Templates.
 */
// 子表模板
var subTemplate = '<p>'
		+ '	<br />'
		+ '</p>'
		+ '<div tablename="明细表" tablekey="subtable" type="subtable" name="editable-input" class="subtable" external="{tablekey:&#39;subtable&#39;,tablerows:&#39;3&#39;,tablename:&#39;明细表&#39;,inlinemodel:1,blockmodel:0,windowmodel:0}">'
		+ '	<br />'
		+ '	<table class="listTable" border="0" cellspacing="0" cellpadding="2">'
		+ '		<tbody>'
		+ '			<tr class="toolBar">'
		+ '				<td colspan="4">&nbsp; <a class="link add" 	href="javascript:;">添加</a></td>'
		+ '			</tr>' + '			<tr class="headRow">' + '				<th>列一</th>'
		+ '				<th>列二</th>' + '				<th>列三</th>' + '			</tr>'
		+ '			<tr class="listRow" formtype="edit">' + '				<td></td>'
		+ '				<td></td>' + '				<td></td>' + '			</tr>' + '		</tbody>'
		+ '	</table>' + '</div>' + '<br />';
// 多模板
var doubleTemplate = '<br />'
		+ '<table class="formTable" border="1" cellspacing="0" cellpadding="2">'
		+ '	<tbody>'
		+ '		<tr>'
		+ '			<td class="formHead" colspan="4">主表</td>'
		+ '			<tr>'
		+ '				<td style="width: 15%" class="formTitle" align="right">主表字段一:</td>'
		+ '				<td style="width: 35%" class="formInput"></td>'
		+ '				<td style="width: 15%" class="formTitle" align="right">主表字段二:</td>'
		+ '				<td style="width: 35%" class="formInput"></td>'
		+ '			</tr>'
		+ '			<tr>'
		+ '				<td style="width: 15%" class="formTitle" align="right">主表字段三:</td>'
		+ '				<td style="width: 35%" class="formInput"></td>'
		+ '				<td style="width: 15%" class="formTitle" align="right">主表字段四:</td>'
		+ '				<td style="width: 35%" class="formInput"></td>' + '			</tr>'
		+ '	</tbody>' + '</table>' + subTemplate;
// 单模板
var singleTemplate = '<br />'
		+ '<table class="formTable" border="1" cellspacing="0" cellpadding="2">'
		+ '	<tbody>'
		+ '		<tr>'
		+ '			<td class="formHead" colspan="2">主表</td>'
		+ '		</tr>'
		+ '		<tr>'
		+ '			<td style="width: 20%" class="formTitle" align="right">主表字段一:</td>'
		+ '			<td style="width: 80%" class="formInput"></td>'
		+ '		</tr>'
		+ '		<tr>'
		+ '			<td style="width: 20%" class="formTitle" align="right">主表字段二:</td>'
		+ '			<td style="width: 80%" class="formInput"></td>' + '		</tr>'
		+ '	</tbody>' + '</table>' + subTemplate;
var blankTemplate = '<p class="ue_t">欢迎使用表单设计器！</p>';
/**
 * 模板
 * 
 * @type
 */
var templates = [{
			"pre" : "pre0.png",
			'title' : lang.blank,
			'preHtml' : blankTemplate,
			"html" : blankTemplate

		}, {
			"pre" : "pre1.png",
			'title' : lang.doubleEdit,
			'preHtml' : doubleTemplate,
			'html' : doubleTemplate
		}, {
			"pre" : "pre2.png",
			'title' : lang.singleEdit,
			'preHtml' : singleTemplate,
			'html' : singleTemplate
		}];