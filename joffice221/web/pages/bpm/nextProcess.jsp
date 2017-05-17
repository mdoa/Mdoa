<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="flow" tagdir="/WEB-INF/tags/flow"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>流程下一步</title>
</head>
<body>
	<%
		Long curUserId=new Long(request.getParameter("curUserId"));
		Long taskId=new Long(request.getParameter("taskId"));
	%>
	<flow:next curUserId="<%=curUserId%>" taskId="<%=taskId %>">
		<table>
			<tr>
				<td>我的业务表单</td>
			</tr>
			<tr>
				<td>
					taskName:<input type="text" name="xxx"/>
				</td>
			</tr>
		</table>
	</flow:next>
</body>
</html>