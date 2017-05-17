
<%--
	time:2012-09-26 16:34:16
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<head>
	<title>表单设计</title>
	 <link rel="stylesheet" type="text/css" href="<%=basePath%>/js/util/lg/skins/Aqua/css/ligerui-all.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/ueditor/themes/default/ueditor.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/form.css" />
	
	<script type="text/javascript" src="<%=basePath%>/js/dynamic.jsp"></script>
	<script type="text/javascript" charset="utf-8"	src="<%=basePath%>/js/util/jquery.js"></script>
	<script type="text/javascript"  charset="utf-8"	src="<%=basePath%>/js/util/util.js"></script>
	<script type="text/javascript"  charset="utf-8"	src="<%=basePath%>/js/util/CustomValid.js"></script>
	<script type="text/javascript"  charset="utf-8"	src="<%=basePath%>/js/util/form.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath%>/js/util/lg/ligerui.min.js"></script>
	<!-- ueditor -->
	<script type="text/javascript" charset="utf-8"	src="<%=basePath%>/js/ueditor/editor_config.js"></script>
	<script type="text/javascript" charset="utf-8"	src="<%=basePath%>/js/ueditor/editor_api.js"></script>
	<script type="text/javascript" defer="defer"  src="<%=basePath%>/js/ueditor/lang/zh-cn/zh-cn.js" ></script>

</head>
<body style="overflow: hidden">
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">表单设计</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group">
					<a class="link save" id="btnSaveForm" href="#">保存</a>
				</div>
				<div class="l-bar-separator"></div>
				<div class="group">
					<a class="link run" id="btnNext" href="#">下一步</a>
				</div>
				<div class="l-bar-separator"></div>
				<div class="group">
					<a class="link preview" id="btnPreView" href="#">预览</a>
				</div>
				<div class="l-bar-separator"></div>

				<div class="group">
					<a class="link colse" href="javascript:window.close();">关闭</a>
				</div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="formDefForm" method="post" name="formDefForm">
			<input id="formDefId" type="hidden" name="formDefId" value="${formDef.formDefId}" /> 
			<input id="status" type="hidden" name="status" value="${status}" />
			<input id="canEditColumnNameAndType" type="hidden" name="canEditColumnNameAndType" value="${canEditColumnNameAndType}" />
			
			<table cellpadding="0" cellspacing="0" border="0"
				style="margin-bottom: 4px;" class="table-detail">
				<tr style="height: 25px;">
					<th>表单标题:</th>
					<td>&nbsp;<input id="formTitle" type="text" name="formTitle"
					value="${formDef.formTitle}" validate="{required:true}" class="inputText" style="width: 120px" /></td>
					<th>表单描述:</th>
					<td>&nbsp;<input id="formDesp" type="text" name="formDesp"
						value="${formDef.formDesp}" validate="{required:true}"  class="inputText" style="width: 120px" /></td>
						
					<c:if test="${status}">
						<th>表名:</th>
						<td style="padding: 2px;">
							<c:choose>
								<c:when test="${canEditColumnNameAndType }">
									<input id="tableKey" type="text" name="tableKey" class="inputText" validate="{required:true,variable:true}" value="${formTable.tableKey }"  />	
								</c:when>
								<c:otherwise>
									<input id="tableKey" type="text" name="tableKey"  readonly="readonly" class="readonlyText" validate="{required:true,variable:true}" value="${formTable.tableKey }"  />
								</c:otherwise>
							</c:choose>
							
						</td>
						<th>表描述:</th>
						<td style="padding: 2px;">
							<input id="tableName" type="text" name="tableName"  class="inputText" value="${formTable.tableName }" />
						</td>
					</c:if>
				</tr>
			</table>

			<div title="表单设计">
				<div id="editor" position="center"
					style="overflow: auto; height: 100%;">
					<textarea id="defHtml" name="defHtml">${fn:escapeXml(formDef.defHtml) }</textarea>
					<textarea id="content" name="content" style="display: none;"></textarea>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript" charset="utf-8"	src="<%=basePath%>/js/formDesign/formDesign.js"></script>
</body>
</html>


