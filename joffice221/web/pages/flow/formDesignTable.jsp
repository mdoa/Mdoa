
<%--
	time:2012-09-26 16:34:16
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@ page
	import="com.htsoft.oa.model.flow.FormDef,com.htsoft.core.util.AppUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<head>
	<title>发布表单</title>
	 <link rel="stylesheet" type="text/css" href="<%=basePath%>/js/util/lg/skins/Aqua/css/ligerui-all.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/ueditor/themes/default/ueditor.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/form.css" />
	
	<script type="text/javascript" src="<%=basePath%>/js/dynamic.jsp"></script>
	<script type="text/javascript" charset="utf-8"	src="<%=basePath%>/js/util/jquery.js"></script>
	<script type="text/javascript"  charset="utf-8"	src="<%=basePath%>/js/util/util.js"></script>
	<script type="text/javascript"  charset="utf-8"	src="<%=basePath%>/js/util/CustomValid.js"></script>
	<script type="text/javascript"  charset="utf-8"	src="<%=basePath%>/js/util/form.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath%>/js/util/lg/ligerui.min.js"></script>
</head>
<body>
<div class="panel">
	<div class="panel-top">	
		<div class="tbar-title">
			<span class="tbar-label">表单字段列表</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group">
					<a class="link save" id="btnDeploy" href="#">发布表单</a>
				</div>
				<div class="l-bar-separator"></div>
				<div class="group">
					<a class="link back" href="javascript:;" onclick="goBack()">上一步</a>
				</div>
			</div>
		</div>
		<div class="panel-search">
				<form id="formDefForm" namespace="/flow"  action="formDesignFormDef.do" method="post">
				<table cellpadding="0" cellspacing="0" border="0" style=" margin-bottom:4px;"   class="table-detail">
				<tr style="height:25px;">
					<th>表单标题:</th>
					<td>&nbsp;<input id="formTitle" type="text" name="formTitle"
					value="${formDef.formTitle}" validate="{required:true}" class="inputText" style="width: 120px" /></td>
					<th>表单描述:</th>
					<td>&nbsp;<input id="formDesp" type="text" name="formDesp"
						value="${formDef.formDesp}" class="inputText" style="width: 120px" /></td>
						
					<th>表名:</th>
						<td style="padding: 2px;">
							<input id="tablekey" type="text" name="tableKey" class="<c:if test="${canEditTableName==true }"> inputText</c:if><c:if test="${canEditTableName==false }">readonlyText</c:if>"  <c:if test="${canEditTableName==false }">readonly='readonly'</c:if> validate="{required:true,variable:true}" value="${tableKey }"  />	
						</td>
						<th>表描述:</th>
						<td style="padding: 2px;">
							<input id="tableName" type="text" name="tableName"  class="inputText" value="${tableName }" />
						</td>
							<textarea name="defHtml" style="display:none;">${fn:escapeXml(formDef.defHtml) }</textarea>
							<input id="formDefId" type="hidden" name="formDefId" value="${formDefId}" />
							<input id="status" type="hidden" name="status" value="1" />
							<input id="isBack" type="hidden" name="isBack" value="1" />
						</tr>
					</table>
				</form>
		</div>
	</div>
	<div class="panel-body">
			<table cellpadding="1" cellspacing="1" class="table-grid" >
				<tr>
					<th>列名</th>
					<th >注释</th>
					<th width="30%">类型</th>
					<th width="10%">必填</th>
					<th width="10%">作为查询条件</th>
					<th width="10%">是否流程标题</th>
					<th width="10%">显示到列表</th>
				</tr>
				
				<tbody  type="mainTable">
					<c:forEach items="${result.formTable.formFields}" var="field">
					<tr fieldName="${field.fieldName}">
						<td>${field.fieldName }</td>
						<td>${field.fieldLabel }</td>
						<td>${field.fieldTypeDisplay}</td>
						<td align="center">
							<c:choose>
								<c:when test="${field.isRequired==1}">
									是
								</c:when>
								<c:otherwise>
									否
								</c:otherwise>
							</c:choose>
						</td>
						<td align="center">
							<c:choose>
								<c:when test="${field.isQuery==1}">
									是
								</c:when>
								<c:otherwise>
									否
								</c:otherwise>
							</c:choose>
						</td>
						<td align="center">
							<c:choose>
								<c:when test="${field.isFlowTitle==1}">
									是
								</c:when>
								<c:otherwise>
									否
								</c:otherwise>
							</c:choose>
						</td>
						<td align="center">
							<c:choose>
								<c:when test="${field.isList==1}">
									是
								</c:when>
								<c:otherwise>
									否
								</c:otherwise>
							</c:choose>	
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
				<c:if test="${not empty result.formTable.subTableList}">
				<br/>
					<c:forEach items="${result.formTable.subTableList}" var="table">
						<table cellpadding="1" cellspacing="1" class="table-grid" style="margin-top: 5px;" >
							<tr>
								<td colspan="7">&nbsp;&nbsp;&nbsp;&nbsp;类型:子表 &nbsp;&nbsp;|&nbsp;&nbsp; 表名:${table.tableKey} &nbsp;&nbsp;|&nbsp;&nbsp; 表描述:${table.tableName}</td>
							</tr>
							<tr>
								<th >列名</th>
								<th >注释</th>
								<th >类型</th>
								<th >必填</th>
							</tr>
						
							<tbody type="subTable" tablename="${table.tableName}">
								<c:forEach items="${table.formFields}" var="field">
								<tr fieldName="${field.fieldLabel}">
									<td>${field.fieldName}</td>
									<td>${field.fieldLabel}</td>
									<td>${field.fieldTypeDisplay}</td>
									<td>
										<c:choose>
											<c:when test="${field.isRequired==1}">
												是
											</c:when>
											<c:otherwise>
												否
											</c:otherwise>
										</c:choose>	
									</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:forEach>
			</c:if>
					
	</div>
</div>
	<script type="text/javascript" charset="utf-8"	src="<%=basePath%>/js/formDesign/formDesignTable.js"></script>
</body>
</html>


