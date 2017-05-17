<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.htsoft.oa.service.system.AppUserService"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@page import="com.htsoft.core.util.AppUtil"%>
<%@page import="com.htsoft.core.util.ContextUtil"%>
<%
	String basePath=request.getContextPath();
	//登录成功后，需要把该用户显示至在线用户
	AppUtil.addOnlineUser(request.getSession().getId(), ContextUtil.getCurrentUser());
	AppUserService appUserService=(AppUserService)AppUtil.getBean("appUserService");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />  
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="msthemecompatible" content="no">
		<title><%=AppUtil.getCompanyName()%>－－办公协同管理系统</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/ext3/resources/css/ext-all-notheme.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/ext3/resources/css/ext-patch.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/ext3/ux/css/Portal.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/ext3/ux/css/Ext.ux.UploadDialog.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/admin.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/ext3/ux/css/ux-all.css"/>
		<!-- load the extjs libary -->
		<script type="text/javascript" src="<%=basePath%>/js/dynamic.jsp"></script>
		
		<!-- Ext 核心JS -->
		<script type="text/javascript" src="<%=basePath%>/ext3/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="<%=basePath%>/ext3/ext-all.gzjs"></script>
		<script type="text/javascript" src="<%=basePath%>/ext3/ext-basex.js"></script>
		<script type="text/javascript" src="<%=basePath%>/ext3/jit.js"></script>
	

		<!--使用iframe加载的依赖JS  -->
		<script type="text/javascript" src="<%=basePath%>/ext3/miframe-debug.js"></script>
		
		<!-- CK控件JS -->
		<script type="text/javascript" src="<%=basePath%>/js/ckeditor/ckfinder/ckfinder.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/ckeditor/ckeditor_source.js"></script>
		<script type="text/javascript" src="<%=basePath%>/ext3/ux/CKEditor.js"></script>
		<!-- 意见编辑器 -->
		<script type="text/javascript" src="<%=basePath%>/js/core/ux/CommentEditor.js"></script>
		
		<!-- 附件上传对话框 -->
		<script type="text/javascript" src="<%=basePath%>/ext3/ux/UploadDialog.js"></script>
		<!-- 附件明细JS,多处用到 -->
		<script type="text/javascript" src="<%=basePath%>/js/system/FileAttachDetail.js"></script>
		<!-- AppUtil.js中引用附件上传的JS -->
        <script type="text/javascript" src="<%=basePath%>/js/system/FileUploadManager.js"></script>
        <script type="text/javascript" src="<%=basePath%>/js/system/FileUploadImageDetail.js"></script>
        <script type="text/javascript" src="<%=basePath%>/js/fileupload/swfobject.js"></script>
        <script type="text/javascript" src="<%=basePath%>/js/fileupload/FlexUploadDialog.js"></script>
        <script type="text/javascript" src="<%=basePath%>/js/system/GlobalTypeForm.js"></script>
        
		<!-- 分页栏JS HTExt.js中引用 -->
		<script type="text/javascript" src="<%=basePath%>/ext3/ux/PageComboResizer.js"></script>
		
		<!-- 提示信息JS -->
		<script type="text/javascript" src="<%=basePath%>/ext3/ux/Toast.js"></script>
		
		<!-- GirdPanel中引用 -->
		<script type="text/javascript" src="<%=basePath%>/ext3/ux/Ext.ux.grid.RowActions.js"></script>
		
		<!-- 需要的 JS-->
		<script type="text/javascript" src="<%=basePath%>/js/App.import.js"></script>
		<script type="text/javascript" src="<%=basePath%>/ext3/ux/XmlTreeLoader.js"></script>
		<script type="text/javascript" src="<%=basePath%>/ext3/ux/TabCloseMenu.js"></script>		
		<script type="text/javascript" src="<%=basePath%>/ext3/ux/DateTimeField.js"></script>
		
		<!-- core 工具JS -->
		<script type="text/javascript" src="<%=basePath%>/js/core/ux/HTExt.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/core/ScriptMgr.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/core/AppUtil.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/core/ux/TreePanelEditor.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/core/ux/TreeXmlLoader.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/core/ux/WebOffice.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/core/ux/ComboTree.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/core/ux/AttachPanel.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/core/ux/DicCombo.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/selector/ProcessFormDetail.js"></script>
		
		<!-- 用户选择器,多处用到  替换UserDialog -->
		<script type="text/javascript" src="<%=basePath%>/js/selector/UserDialog.js"></script>
		<!-- 部门选择器,多处用到 替换DepDialog -->
		<script type="text/javascript" src="<%=basePath%>/js/selector/DepSelector.js"></script>
		<!-- 部门选择器,多处用到 替换DepDialog -->
		<script type="text/javascript" src="<%=basePath%>/js/selector/DepDialog.js"></script>
		<!-- 在线用户选择器,在主页面显示在线用户时用到 -->
		<script type="text/javascript" src="<%=basePath%>/js/selector/OnlineUserDialog.js"></script>
		
		<!-- 日期转换JS,目前AppUtil.js已提供$parseDate()方法 ,后续可将此JS换掉-->
		<script type="text/javascript" src="<%=basePath%>/js/core/date.js"></script>
		
		<!-- 接收站内短消息时所用JS -->
		<script type="text/javascript" src="<%=basePath%>/js/info/MessageWin.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/info/ReMessageWin.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/info/MessageDetail.js"></script>
		
		<script type="text/javascript" src="<%=basePath%>/js/info/BorrowRecordWin.js"></script>
		<!-- 启动流程JS,多处用到-->
		<script type="text/javascript" src="<%=basePath%>/js/flow/ProcessRunStart.js"></script>
		
		<!-- 公文流程任务结点JS,多处用到-->
		<script type="text/javascript" src="<%=basePath%>/js/archive/ArchivesNode.js"></script>
		
        <!-- 首页站内搜索JS -->
        <script type="text/javascript" src="<%=basePath%>/js/search/SearchForm.js"></script>
	
    	<!-- 首页样式加载 -->
    	<link href="<%=basePath%>/css/desktop.css" rel="stylesheet" type="text/css" />	
	    <script type="text/javascript">
	       var __companyName="<%=AppUtil.getCompanyName()%>";
	       var userInfo="<%=appUserService.getCurUserInfo()%>";
		   Ext.onReady(function(){
			   	  var storeTheme=getCookie('theme');
			   	  if(storeTheme==null || storeTheme==''){
				   	  storeTheme='ext-all';
			   	  }
			      Ext.util.CSS.swapStyleSheet("theme", __ctxPath+"/ext3/resources/css/"+storeTheme+".css");    
			      window.onbeforeunload = function() {			    				    	
			    	    var isout=Ext.get("logout").value;
					    window.unloadTimer = setInterval("Ext.get('logout').value='';clearInterval(window.unloadTimer);",100);				    
					    if(isout=="isout"){
							  window.onunload = function() {App.runOnUnload();clearInterval(window.unloadTimer);};					 
						}else{							
							  window.onunload = function() {clearInterval(window.unloadTimer);};					  
						}		
					    return '你确定吗？';
				   }; 
		   });	 
		  
	    </script>
	    
	    <!-- 登录信息,工具JS -->
		<script type="text/javascript" src="<%=basePath%>/js/App.js"></script>	
		
	     <!-- 个人首页JS -->
	    <script type="text/javascript" src="<%=basePath%>/js/IndexPage.js"></script>
	    
	    
		<script type="text/javascript" src="<%=basePath%>/ext3/ext-lang-zh_CN.js"></script>
	</head>
	<body oncontextmenu="return false" >
		<div id="loading">
             <div class="loading-indicator">
                  <img src="<%=basePath%>/images/loading.gif" alt="" width="153" height="16" style="margin-right:8px;" align="absmiddle"/>
                  <div class="clear"></div>
         		    正在加载，请稍候......
             </div>
        </div>
        <div id="loading-mask"></div>
		<div id="app-header">
			<div id="header-left">
				<img id ="CompanyLogo" src="<%=basePath%>/images/Mender.png" height="50" style="max-width:230px;float:left;"/>
				<h1 style="margin-left:40px;color:blue;line-height:50px;font-size:30px;">德OA办公平台</h1>
			</div>
			<div id="header-main">
				<div id="header-info">
					<a href="#" onclick="App.MyDesktopClick()" style="text-indent:25px;padding-left: 28px;" class="menu-index-company" >公司主页</a>
					<a href="#" onclick="App.clickTopTab('AppHome')" style="text-indent:25px; padding-left: 28px" class="menu-desktop">个人桌面</a>
					<a href="#" onclick="App.clickTopTab('PersonalMailBoxView')" style="text-indent:25px; padding-left: 28px" class="menu-mail_box">邮件</a>
					&nbsp;
					欢迎您，<security:authentication property="principal.fullname"/>，[<a href="#" onclick = "App.Logout()">注销</a>]
				</div>
				<div id="header-nav"></div>
			</div>
			<div id="header-right">
				<div id="setting">
					<%if(ContextUtil.getCurrentUser().isSuperAdmin()){ %>
						|&nbsp;<a href="#" onclick="App.clickTopTab('SysConfigView')">设置</a>
					<%} %>
					|&nbsp;<a href="<%=basePath%>/help/NtkoControlSetup.zip" target="blank">OFFICE插件</a>
				</div>
				<div id="searchFormDisplay" style="width:260px;height:30px;float:right;padding-top:10px;">&nbsp;</div>
			</div>
		</div>
		<input type="hidden" id="logout"/>
	</body>
</html>