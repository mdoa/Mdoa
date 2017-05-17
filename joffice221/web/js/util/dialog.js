/**
 * 
 * @param url
 * @returns
 */
function openFullWindow(url){
	var h=screen.availHeight-35;
	var w=screen.availWidth-5;
	var vars="top=0,left=0,height="+h+",width="+w+",status=no,toolbar=no,menubar=no,location=no,resizable=1,scrollbars=1";
    var UUID = (new Date()).getTime();
	if (url.indexOf("?") >=0 ) {
    	url += "&UUID="+UUID;
    } else {
    	url += "?UUID="+UUID;
    } 
	var win=window.open(url,"",vars,true);
	return win;
};


/**
 * 弹出窗口
 * @param {} url  访问的地址
 * @param {} width 宽度
 * @param {} height 高度
 * @return {}
 */
function showWindowDialog(url, width, height) {
    var style = "status:no;scroll:no;help:no;center:yes";
    style += ";dialogWidth:"+width+"px;dialogHeight:"+height+"px";
    var UUID = (new Date()).getTime();
    if (url.indexOf("?") >=0 ) {
    	url += "&UUID="+UUID;
    } else {
    	url += "?UUID="+UUID;
    }    
    return showModalDialog(url, window, style);
};

function showDialog(url){
	// 定义窗口宽度、窗口高度、屏幕宽度、屏幕高度      
    var width = screen.availWidth;    
    var height = screen.availHeight; 
    var dialogTop = 10;
   var dialogLeft = 10;
    var style = "status:no;scroll:no;help:no;center:yes";
    style += ";dialogTop:"+dialogTop+"px;dialogLeft:"+dialogLeft+"px;dialogWidth:"+width+"px;dialogHeight:"+width+"px";
    var UUID = (new Date()).getTime();
    if (url.indexOf("?") >=0 ) {
    	url += "&UUID="+UUID;
    } else {
    	url += "?UUID="+UUID;
    }    
    return showModalDialog(url, window, style);
	
};


/**
 * 模态窗口高度调整. 
* 根据操作系统及ie不同版本,重新设置窗口高度,避免滚动条出现.
 * @param {} height
 * @return {}
 */
function resetDialogHeight(height){ 
	var ua = navigator.userAgent; 
	if(ua.lastIndexOf("MSIE 6.0") != -1){ 
		if(ua.lastIndexOf("Windows NT 5.1") != -1){ 
			//alert("xp.ie6.0"); 
			return (height+102); 
		} 
		else if(ua.lastIndexOf("Windows NT 5.0") != -1){ 
			//alert("w2k.ie6.0"); 
			return (height+49); 
		} 
	} 
};
/**
 * 关闭窗口
 * @return 返回false
 */
function closeDialog(){
  	if(confirm('是否确定关闭该窗口？')){
  	  	window.close();
  	  	window.returnValue = false;
  	}	
};