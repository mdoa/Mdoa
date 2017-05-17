<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.htsoft.core.util.AppUtil"%>
<%@page import="com.htsoft.core.util.CookieUtil"%>
<%@page import="org.apache.commons.lang.StringUtils"%><html>
<head>
<title>欢迎登录<%=AppUtil.getCompanyName()%>协同办公系统
</title>
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/ext3/resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/ext3/resources/css/ext-patch.css" />
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/css/login.css" />
<%
	response.addHeader("__timeout", "true");
	String codeEnabled = (String) AppUtil.getSysConfig().get("codeConfig");
	String dyPwdEnabled = (String) AppUtil.getSysConfig().get("dynamicPwd");
	if (StringUtils.isEmpty(codeEnabled)) {//若当前数据库没有配置验证码参数
		codeEnabled = "close";//代表需要输入
	}
	if (StringUtils.isEmpty(dyPwdEnabled)) {//若当前数据库没有配置动态密码参数
		dyPwdEnabled = "close";//代表需要输入
	}
	//设置cookie
	boolean falg = false;
	falg = CookieUtil.isExistByName("username", request);
	String username = "";
	if (falg) {
		username = CookieUtil.getValueByName("username", request);
	}
	//System.out.println("fffff:"+falg);
	//MyCookie.delCookie("username", pageContext);
%>
<script type="text/javascript">
		var __ctxPath="<%=request.getContextPath()%>";
		var __loginImage=__ctxPath+"<%=AppUtil.getCompanyLogo()%>";
</script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/ext3/adapter/ext/ext-base.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/ext3/ext-all.gzjs"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/ext3/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/App.LoginWin.js"></script>
<script type="text/javascript">
	 Ext.onReady(function(){
 		Ext.QuickTips.init(); 
 	    Ext.form.Field.prototype.msgTarget = 'side';
 		new App.LoginWin({isCodeEnabled:'<%=codeEnabled%>',
 			isDyPwdEnabled:'<%=dyPwdEnabled%>',
 			username : '<%=username%>',
 			falg: <%=falg%>
		}).show();
	});
</script>
	<style type="text/css">
		#ext-gen30>img{width:245px;height:50px;}
		#loginWin{border-radius:50%;}
		.x-window-tc,.x-window-bc{background-image:none;background-color:rgba(255,255,255,.01);}
		.x-window-tr,.x-window-mr,.x-window-br{background-image:none;background-color:rgba(255,255,255,.1);}
		.x-window-tl,.x-window-ml,.x-window-bl{background-image:none;background-color:rgba(255,255,255,.5);}
		/* .x-window-mc{/* background:url(images/loginbar_bg.png) no-repeat 0 0; */background:#ff0000;} */
	</style>
</head>
<body>
	<img style="width:300px;height:150px;" src="images/ht-logo.png">
	<div style="text-align: center;">
		<div id="loginArea"></div>
	</div>
</body>
</html>