<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String basePath=request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>打印</title>

<link rel="stylesheet" type="text/css" href="<%=basePath%>/ext3/resources/css/ext-all-notheme.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/ext3/resources/css/ext-patch.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/ext3/resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/ext3/ux/css/Portal.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/ext3/ux/css/Ext.ux.UploadDialog.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/admin.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/ext3/ux/css/ux-all.css"/>

<script language="javascript" >
var gpObj = window.opener.gpObj;
document.write("<head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'><style type='text/css'>html,body{height:100%;}</style></head>");
document.write("<body></body>");

/* 设置最外层div样式  */
var tmpDiv = document.createElement('div');
tmpDiv.setAttribute('class','x-form-clear-left');
document.body.appendChild(tmpDiv);
document.write(gpObj.innerHTML);

var divs = document.getElementsByTagName("div");
if(divs.length>0){
	var divCls = divs[3].getAttribute('class');
	if(divCls!=null&&divCls=='x-form-clear-left'){
		document.body.removeChild(divs[1]);
	}
}
/* 设置最外层div样式  */

/* 为所有控件赋值  */
var gpElems = new Array();
gpElems = mergeElems(gpElems,gpObj.getElementsByTagName("input"));
gpElems = mergeElems(gpElems,gpObj.getElementsByTagName("textarea"));

var curElems = new Array();
curElems = mergeElems(curElems,document.getElementsByTagName("input"));
curElems = mergeElems(curElems,document.getElementsByTagName("textarea"));

for(var index=0;index<curElems.length;index++){
	curElems[index].value = gpElems[index].value;
	curElems[index].readOnly=true;
}

function mergeElems(oldElems,newElems){
	for(var index=0;index<newElems.length;index++){
		oldElems.push(newElems[index]);
	}
	return oldElems;
}
/* 为所有控件赋值 */

/* ckedit 赋值 */
var gpFrame = gpObj.getElementsByTagName("iframe")[0];
var gpFckdoc;
if(gpFrame!=null){
	if(gpFrame.contentDocument){
		gpFckdoc = gpFrame.contentDocument;
	}else if(gpFrame.contentWindow){
		gpFckdoc = gpFrame.contentWindow.document;
	}else{
		gpFckdoc = gpFrame.document;
	}
	
	var curSpans = document.getElementsByTagName("span");
	for(var i=0;i<curSpans.length;i++){
		if(curSpans[i].id.indexOf('cke_ext-comp')!=-1){
			curSpans[i].innerHTML = gpFckdoc.body.innerHTML;
		}
	}	
}
/* fck 赋值 */

function selectReplace(nameStr){
	var strArray=nameStr.split(',');
	for(var i=0;i<strArray.length;i++){
		 if(navigator.userAgent.indexOf("MSIE")>0){
			 replaceByTextInIe(strArray[i]);
		 }else{
				replaceByText(strArray[i]);
		 }
	}
}
/*ie浏览器处理函数*/
function replaceByTextInIe(tagName){
	var objs= document.getElementsByTagName(tagName);
	var parentElements=new Array();
	var objValues=new Array;

	for(var key in objs){		
		var obj=objs[key];
		if(!obj ) continue;		
		var parentElement=obj.parentElement;
		var objValue=null;
		if(obj.tagName=="SELECT"){
			for(var i=0;i<obj.options.length;i++){
				if(obj.options[i].selected){
					objValue = obj.options[i].text ;
					break;
				}
			}		
		}else{				
			objValue = obj.value ;	
			//处理特殊radio和checked类型
			if(obj.length>0 && obj[0].tagName=="INPUT"){		
				objValue="";
				for(var i=0;i<obj.length;i++){
					var subItem=obj[i];
					var subParentElement=subItem.parentElement;				
					if(subItem.defaultChecked){
						objValue+="  "+subItem.value+"  ";
						parentElement=subParentElement;
					}
				}				
			}
		}			
		if(parentElement &&(parentElement.tagName=="TD" || parentElement.tagName=="DIV")){
			if(obj.type && (obj.type=='hidden' || obj.style.display=='none') ) continue;
			parentElements.push(parentElement);
			objValues.push(objValue);
		}		
	}
	for(var j=0;j<parentElements.length;j++ ){
		parentElements[j].innerHTML =objValues[j];
	}
}
/*其他浏览器处理函数*/
function replaceByText(tagName){	
	var objs= document.getElementsByTagName(tagName);
	var parentElements=new Array();
	var objValues=new Array;
	for(var key in objs){
		var obj=objs[key];
		if(!obj || objs.length==0 ) continue;		
		var parentElement=obj.parentElement;
		var objValue=null;
		if(obj.tagName=="SELECT"){
			objValue = obj.options[obj.options.selectedIndex].text ;
		}else{
			objValue = obj.value ;
		}			
		if(parentElement &&(parentElement.tagName=="TD" || parentElement.tagName=="DIV")){
			if(obj.attributes.type && (obj.attributes.type.nodeValue=="hidden" || obj.style.display=="none") ) continue;
			if(obj.tagName=="INPUT" && (obj.attributes.type.nodeValue=="radio" || obj.attributes.type.nodeValue=="checked" )  ) {
				if(obj.checked){
					parentElements.push(parentElement);
					objValues.push(objValue);
				}
				continue;
			}
    		parentElement.innerHTML =objValue;
		}			
	}
	for(var j=0;j<parentElements.length;j++ ){
		if(!parentElements[j].style.handle){
			parentElements[j].style.handle="true";
			parentElements[j].innerHTML ="";
		}
		parentElements[j].innerHTML +="  "+objValues[j]+"  ";
	}
}


function resizeBody(){
	if(window.opener.printFlag){
		// 把所有按钮删除
		var btns = document.getElementsByTagName("table");
		var area = document.getElementsByTagName("textarea");
		for(var i=0;i<btns.length;i++){
			var cls = btns[i].getAttribute("class");
			if(cls!=null&&cls.indexOf("x-btn-text-icon")!=-1){
				btns[i].style.display="none";
			}
		}
		for(var i=0;i<area.length;i++){			
			var als = area[i].getAttribute("class");			
			if(als!=null&&als.indexOf("x-form-textarea")!=-1){
				area[i].style.display="none";
			}
		}
		
		selectReplace('select,input');
		
		// 删除 office 控件
		var ofEdit = document.getElementsByTagName("object");
		for(var i=0;i<ofEdit.length;i++){
			var codeBase = ofEdit[i].getAttribute("codeBase");
			if(codeBase.indexOf("OfficeControl")!=-1){
				ofEdit[i].parentNode.parentNode.parentNode.removeChild(ofEdit[i].parentNode.parentNode);
			}
		}
		
		var divs = document.getElementsByTagName("div");
		for(var i=0;i<divs.length;i++){
			if(divs[i].style.height!=""){
				divs[i].style.height = divs[i].scrollHeight+"px";
			}
		}
		
		// ckedit 内容高度调整
		var exerObj;
		for(var i=0;i<divs.length;i++){
			if(divs[i].style.height!=""){
				if(divs[i].innerHTML.indexOf('cke_editor')!=-1){
					divs[i].style.height = "auto";
					exerObj = divs[i];
					break;
				}
			}
		}
		// ckedit 内容高度调整
		
		for(var i=0;i<divs.length;i++){
			if(divs[i].style.height!=""){
				divs[i].style.height = ((divs[i]===undefined?0:divs[i].offsetHeight)
						+(exerObj===undefined?0:exerObj.offsetHeight))+"px";
				break;
			}
		}
	
		//window.opener.printFlag = false;
	}
	else{
		var divs = document.getElementsByTagName("div");
		for(var i=0;i<divs.length;i++){
			if(divs[i].style.height!=""){
				divs[i].style.height = "auto";
			}
		}
	}

	window.print();
}
/* 页面body自适应 */
resizeBody();
</script>
