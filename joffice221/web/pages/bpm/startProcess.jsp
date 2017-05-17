<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="flow" tagdir="/WEB-INF/tags/flow"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>启动我的流程</title>
</head>
<body>
	<%
		//Long defId=new Long(request.getParameter("defId"));
		//Long curUserId=new Long(request.getParameter("curUserId"));
		
		//System.out.println("=======defId:"+defId + " curUserInfo:" + curUserId);
		//request.setAttribute("curUserId",curUserId);

	%>
	<flow:start curUserId="10461" defId="41" startNext="true">
		<table cellpadding="0" cellspacing="1" border="1" >
			<tr>
				<td>我的业务表单</td>
			</tr>
			<tr>
				<td>
					xx:<input type="text" name="xxx"/>
					<!-- 
					<input type="hidden" name="afterHandler" value="axxxService.aaMethod"/>
					<input type="hidden" name="preHandler" value="axxxService.aaMethod"/>
					 -->
				</td>
			</tr>
		</table>
	</flow:start>
</body>
</html>