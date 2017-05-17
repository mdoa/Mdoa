<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>文件预览</title>
<script type="text/javascript" src="<%=basePath%>/pages/swf/flexpaper_flash.js"></script>
<script type="text/javascript" src="<%=basePath%>/pages/swf/flexpaper_flash_debug.js"></script>
<script type="text/javascript">
   function init(){
	  var mark = '${mark}';
	  var obj =  document.getElementById("viewerPlaceHolder");
	  if(mark=="no"){
		  obj.style.display= "none"; 
		  alert("预览文件出错！");
	  }	  
  }

</script>    
<body onload ="init()">         
       <div style="position:absolute;left:10px;top:10px;">  
                 <a id="viewerPlaceHolder" style="width:900px;height:640px;display:block"></a>                     
                 <script type="text/javascript">
                          var fp = new FlexPaperViewer(
                        		  '<%=basePath%>/pages/swf/FlexPaperViewer',     //FlexPaperViewer.swf 的相对路径
                        		  'viewerPlaceHolder',       //展示SWF的对象ID
                                  { config : {
                                	  SwfFile : escape('<%=basePath%>/${swfPath}'),      
                                	  Scale : 0.6,
                                	  ZoomTransition : 'easeOut',
                                	  ZoomTime : 0.5,
                                	  ZoomInterval : 0.2,
                                	  FitPageOnLoad : true,
                                	  FitWidthOnLoad : false,
                                	  PrintEnabled : true,
                                	  FullScreenAsMaxWindow : false,
                                	  ProgressiveLoading : false,
                                	  MinZoomSize : 0.2,
                                	  MaxZoomSize : 5,
                                	  SearchMatchAll : false,
                                	  InitViewMode : 'Portrait',
                                	  ViewModeToolsVisible : true,
                                	  ZoomToolsVisible : true,
                                	  NavToolsVisible : true,
                                	  CursorToolsVisible : true,
                                	  SearchToolsVisible : true,
                                	  localeChain: 'zh_CN'          //'zh_CN'     'en_US'                  
                                	  }});            
                          </script>        
            </div>
</body>
</html>
