
<%--
	表单预览
	time:2012-09-26 16:34:16
--%>
<%@page import="com.opensymphony.xwork2.inject.Context"%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.htsoft.oa.model.flow.FormDef,com.htsoft.core.util.AppUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.htsoft.core.util.AppUtil"%>
<%@page import="com.htsoft.oa.service.system.AppUserService"%>
<%
	String basePath = request.getContextPath();
	AppUserService appUserService=(AppUserService)AppUtil.getBean("appUserService");
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="msthemecompatible" content="no">
	<title><%=AppUtil.getCompanyName()%>－－办公协同管理系统</title>
	<link rel="stylesheet" type="text/css"	href="<%=basePath%>/ext3/resources/css/ext-all-notheme.css" />
	<link rel="stylesheet" type="text/css"	href="<%=basePath%>/ext3/resources/css/ext-patch.css" />
	<link rel="stylesheet" type="text/css"	href="<%=basePath%>/ext3/ux/css/Portal.css" />
	<link rel="stylesheet" type="text/css"	href="<%=basePath%>/ext3/ux/css/Ext.ux.UploadDialog.css" />
	<link rel="stylesheet" type="text/css"	href="<%=basePath%>/css/admin.css" />
	<link rel="stylesheet" type="text/css"	href="<%=basePath%>/ext3/ux/css/ux-all.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/form.css">
	<!-- load the extjs libary -->
	<script type="text/javascript" src="<%=basePath%>/js/dynamic.jsp"></script>
	<!-- Ext 核心JS -->
	<script type="text/javascript" src="<%=basePath%>/ext3/adapter/ext/ext-base.js"></script>
	<script type="text/javascript" src="<%=basePath%>/ext3/ext-all.gzjs"></script>
	<script type="text/javascript" src="<%=basePath%>/ext3/ext-basex.js"></script>
	<script type="text/javascript" src="<%=basePath%>/ext3/ext-lang-zh_CN.js"></script>

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
		
		<script type="text/javascript" src="<%=basePath%>/ext3/ux/MultiSelect.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/core/ntkoffice/NtkOfficePanel.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/selector/SealDialog.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/selector/PaintTemplateDialog.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/selector/PositionDialog.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/fckdesign/Ckdesigner.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/selector/RoleDialog.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/core/ux/RegUserDialog.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/core/ux/RegDepDialog.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/core/ux/RegPosDialog.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/core/ux/RegRoleDialog.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/system/FileAttachDetail.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/core/ux/AttachPanel.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/core/ux/FormDetailGrid.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/core/ux/FormDetailPanel.js"></script>
		<script type="text/javascript" src="<%=basePath%>/ext3/ux/DateTimeField.js"></script>
		
		<script type="text/javascript" src="<%=basePath%>/js/formDesign/formUtil.js"></script>

<script type="text/javascript">
	
	var UserInfo=function(user){
		this.userId=user.userId;
		this.username=user.username;
		this.fullname=user.fullname;
		this.depId=user.depId;
		this.depName=user.depName;
		this.rights=user.rights;
		this.portalConfig=user.items;
		this.topModules=user.topModules;
	};
	
	var userInfo="<%=appUserService.getCurUserInfo()%>";
	var object=Ext.util.JSON.decode(userInfo);
	var curUserInfo=new UserInfo(object.user);
	
	var FormDesign = Ext.extend(Ext.Viewport, {
		constructor : function() {
			this.defHtml = "${defHtml}";
			this.initUI();
			FormDesign.superclass.constructor.call(this, {
	              title:'表单预览',
		          iconCls:'menu-find-doc',
		          layout : 'border', 
		      	 deferredRender : true,
		          items:[this.formPanel]
			});
		},
		initUI : function() {
			
	    	eval(this.defHtml);
	    	this.formTable = eval('new FormTable();');
	      	this.formPanel=new Ext.FormPanel({
		      	region : 'center',
	       		layout:'form',
	       	    border:false,
				autoScroll:true,
	       	    items:this.formTable
       		});
	        
		}
	});
	var init = function() {
		var storeTheme = getCookie('theme');
		if (storeTheme == null || storeTheme == '') {
			storeTheme = 'ext-all';
		}
		Ext.util.CSS.swapStyleSheet("theme", __ctxPath + "/ext3/resources/css/"
				+ storeTheme + ".css");

		Ext.QuickTips.init();//这句为表单验证必需的代码
		Ext.BLANK_IMAGE_URL = __ctxPath
				+ '/ext3/resources/images/default/s.gif';
		//setTimeout(function() {
		//	Ext.get('loading').remove();
		//	Ext.get('loading-mask').fadeOut({
		//		remove : true
		//	});
		//	document.getElementById('app-header').style.display = 'block';
		//}, 1000);
		new FormDesign();
	};
	Ext.onReady(function() {

		init();
	});
</script>
</head>
<body style="overflow: hidden">
	<div class="panel-top">
	</div>
</body>
</html>


