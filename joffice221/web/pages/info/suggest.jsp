<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@page import="com.htsoft.core.util.AppUtil"%>
<%@page import="com.htsoft.core.util.ContextUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath=request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="msthemecompatible" content="no">
		<title>意见箱</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/ext3/resources/css/ext-all-notheme.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/ext3/resources/css/ext-patch.css" />
		
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/admin.css"/>
		
		<script type="text/javascript" src="<%=basePath%>/ext3/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="<%=basePath%>/ext3/ext-all.js"></script>
		<script type="text/javascript" src="<%=basePath%>/ext3/ext-lang-zh_CN.js"></script>

		<script type="text/javascript" src="<%=basePath%>/js/system/FileAttachDetail.js"></script>
		
		<script type="text/javascript" src="<%=basePath%>/js/core/AppUtil.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/dynamic.jsp"></script>

		<script type="text/javascript" src="<%=basePath%>/ext3/ux/Toast.js"></script>

		<script type="text/javascript" src="<%=basePath%>/js/info/SuggestBoxLinkView.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/info/SuggestBoxDisplay.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/info/SuggestBoxForm.js"></script>
				<!-- GirdPanel中引用 -->
		<script type="text/javascript" src="<%=basePath%>/ext3/ux/Ext.ux.grid.RowActions.js"></script>
       	<!-- 分页栏JS HTExt.js中引用 -->
		<script type="text/javascript" src="<%=basePath%>/ext3/ux/PageComboResizer.js"></script>	
		<!-- core 工具JS -->
		<script type="text/javascript" src="<%=basePath%>/js/core/ux/HTExt.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/core/ScriptMgr.js"></script>
		<!-- FCK控件JS -->
		<script type="text/javascript" src="<%=basePath%>/js/ckeditor/ckfinder/ckfinder.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/ckeditor/ckeditor_source.js"></script>
		<script type="text/javascript" src="<%=basePath%>/ext3/ux/CKEditor.js"></script>

		<script type="text/javascript">
			var curUserInfo=null;
		   Ext.onReady(function(){
			   Ext.QuickTips.init(); 
			   var suggestBox = new SuggestBoxLinkView({
						isOutSide : true
				   });

				var mySuggest = new Ext.Viewport({
					border : true,
					layout : 'border',
					items : [suggestBox]
					
					});
				suggestBox.doLayout();
				mySuggest.render(document.body);
				
				
			   var storeTheme=getCookie('theme');
			   	  if(storeTheme==null || storeTheme==''){
				   	  storeTheme='ext-all';
			   	  }
			      Ext.util.CSS.swapStyleSheet("theme", __ctxPath+"/ext3/resources/css/"+storeTheme+".css");  
			   
		    });
	    </script>
	</head>
	<body >

	</body>
</html>