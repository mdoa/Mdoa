Ext.ns("App");
Ext.ns("AppUtil");
//用于标记已经加载过的js库
var jsCache=new Array();

/*
 * XML字符串转化为Dom对象
 */
function strToDom(xmlData) {
	if (window.ActiveXObject) {
		//for IE
		var xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
		xmlDoc.async="false";
		xmlDoc.loadXML(xmlData);
		return xmlDoc;
	} else if (document.implementation && document.implementation.createDocument) {
		//for Mozila
		var parser=new DOMParser();
		var xmlDoc=parser.parseFromString(xmlData,"text/xml");
		return xmlDoc;
	}
}

function newView(viewName,params){
	var str='new ' + viewName ;
	if(params!=null){
		str+='(params);';
	}else{
		str+='();';
	}
	return eval(str);
}

/**
 * Import Js
 * @return {}
 */
function $ImportJs(viewName,callback,params) {
	var b = jsCache[viewName];
	
	if (b != null) {
		var view =newView(viewName,params);
		callback.call(this, view);
	} else {
		var jsArr = eval('App.importJs.' + viewName);
		if(jsArr==undefined || jsArr.length==0){
			try{
				var view = newView(viewName,params);
				callback.call(this, view);
			}catch(e){
			}
			return ;
		}
		ScriptMgr.load({
					scripts : jsArr,
					callback : function() {
						jsCache[viewName]=0;
						var view = newView(viewName,params);
						callback.call(this, view);
					}
		});
	}
}
/**
 * 加载的js,并调用回调函数
 * @param {} jsArr
 * @param {} callback
 */
function $ImportSimpleJs(jsArr,callback,scope){
	ScriptMgr.load({
					scripts : jsArr,
					scope:scope,
					callback : function() {
						if(callback){
							callback.call(this);
						}
					}
	});
}


 /*将String类型解析为Date类型.   
   parseDate('2006-1-1') return new Date(2006,0,1)   
   parseDate(' 2006-1-1 ') return new Date(2006,0,1)   
   parseDate('2006-1-1 15:14:16') return new Date(2006,0,1,15,14,16)   
   parseDate(' 2006-1-1 15:14:16 ') return new Date(2006,0,1,15,14,16);   
   parseDate('2006-1-1 15:14:16.254') return new Date(2006,0,1,15,14,16,254)   
   parseDate(' 2006-1-1 15:14:16.254 ') return new Date(2006,0,1,15,14,16,254)   
   parseDate('不正确的格式') retrun null   
 */    
 function $parseDate(str){     
   if(typeof str == 'string'){     
     var results = str.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) *$/);     
     if(results && results.length>3)     
       return new Date(parseInt(results[1]),parseInt(results[2]) -1,parseInt(results[3]));      
     results = str.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) +(\d{1,2}):(\d{1,2}):(\d{1,2}) *$/);     
     if(results && results.length>6)   
    	 /*08/09时bug修复*/
    	var temph=results[4].match(/^ *(0)(\d{1})*$/);
    	if( temph && temph.length>2){
    		results[4]=temph[2];
    	}
       return new Date(parseInt(results[1]),parseInt(results[2]) -1,parseInt(results[3]),parseInt(results[4]),parseInt(results[5]),parseInt(results[6]));      
     results = str.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) +(\d{1,2}):(\d{1,2}):(\d{1,2})\.(\d{1,9}) *$/);     
     if(results && results.length>7)     
       return new Date(parseInt(results[1]),parseInt(results[2]) -1,parseInt(results[3]),parseInt(results[4]),parseInt(results[5]),parseInt(results[6]),parseInt(results[7]));      
   }     
   return null;     
 }     
     
 /*   
   将Date/String类型,解析为String类型.   
   传入String类型,则先解析为Date类型   
   不正确的Date,返回 ''   
   如果时间部分为0,则忽略,只返回日期部分.   
 */    
 function $formatDate(v){     
   if(typeof v == 'string') v = parseDate(v);     
   if(v instanceof Date){     
     var y = v.getFullYear();     
     var m = v.getMonth() + 1;     
     var d = v.getDate();     
     var h = v.getHours();     
     var i = v.getMinutes();     
     var s = v.getSeconds();     
     var ms = v.getMilliseconds();  
     
     if(m<10) m='0'+m;
     if(d<10) d='0'+d;
     if(h<10) h='0'+h;
     if(i<10) i='0'+i;
     if(s<10) s='0'+s;
           
     if(ms>0) return y + '-' + m + '-' + d + ' ' + h + ':' + i + ':' + s + '.' + ms;     
     if(h>0 || i>0 || s>0) return y + '-' + m + '-' + d + ' ' + h + ':' + i + ':' + s;     
     return y + '-' + m + '-' + d;     
   }     
   return '';     
 }
/**
 *  日期格式的时间比较
 * @param {} val1 前一个时间
 * @param {} val2 第二个时间
 * @param {} format 格式化日期 按Ext的格式化日期
 * 			默认 H:i格式
 * @return {Number} 1 前一个大于后一个
 * 					0 前一个等于后一个
 * 					-1 前一个小于后一个
 * 					
 */
function compareTime(val1,val2,format){
	if(Ext.isEmpty(val1))  return null;
	if(Ext.isEmpty(val2))  return null;
	if(!format) format = 'H:i';
	var time1 = Date.parseDate(val1,format);
	var time2 = Date.parseDate(val2,format);
	if(time1 > time2) return 1;
	if(time1 = time2) return 0;
	if(time1 < time2) return -1;
}
/**
 * 取table里所有的输入框txtname
 * @param {} table
 * @return {}
 */
  function $getTableInputCmpName(table){
		var maps=[];
		for(var i=0;i<table.rows.length;i++){
			var row=table.rows[i];
		    for(var j=0;j<row.cells.length;j++){
		       var cell=row.cells[j];
		       var control;
		       for(var k=0;k<cell.childNodes.length;k++){
				if(cell.childNodes[k].getAttribute&&cell.childNodes[k].getAttribute('name')){
					control=cell.childNodes[k];
					if(control){
					   var name=control.getAttribute('name');
					   maps.push(name);
				    }
				}
			   }
			   
		    }
		}
		return maps;
 }
 
/**
 * 取得中间的内容面板
 * @return {}
 */
App.getContentPanel=function(){
	var tabs = Ext.getCmp('centerTabPanel');
	return tabs;
};
/**
 * 删除Content中的Tab
 */
AppUtil.removeTab=function(tabId){
	var contentPanel=App.getContentPanel();
	var tabItem = contentPanel.getItem(tabId);
	if(tabItem!=null){
		contentPanel.remove(tabItem,true);
	}
};
/**
 * 激活Content中的Tab
 * @param {} panel
 */
AppUtil.activateTab=function(panel){
	var contentPanel=App.getContentPanel();
	contentPanel.activate(panel);
};

/**
 * 新增并激活Content中的Tab
 * @param {} panel
 */
AppUtil.addTab=function(panel){
	var contentPanel=App.getContentPanel();
	contentPanel.add(panel);
	contentPanel.activate(panel);
};

///**
// * 创建上传的对话框
// * @param {} config
// * @return {}
// */
//App.createUploadDialog=function(config){
//	var defaultConfig={
//		file_cat:'others',
//		url:__ctxPath+'/file-upload',
//		reset_on_hide: false,
//		upload_autostart:false,
//		modal : true
//	};
//	Ext.apply(defaultConfig,config);			
//	var	dialog = new Ext.ux.UploadDialog.Dialog(defaultConfig);
//	return dialog;
//};

/**
 * Flex方式 创建上传的对话框
 * @param {} config
 * @return {}
 */
App.createUploadDialog=function(config){
	var defaultConfig={		
		file_cat: config.file_cat?config.file_cat:'others',
		url:__ctxPath+'/file-upload',
		reset_on_hide: false,
		upload_autostart:false,
		scope:config.scope?config.scope:this,
		modal : true
	};
	Ext.apply(defaultConfig,config);
//	var	dialog = new Ext.ux.UploadDialog.Dialog(defaultConfig);
	var dialog = new FileUploadManager(defaultConfig);
	return dialog;
};

/**
 * Ext方式 上传对话框
 * @param {} config
 * @return {}
 */
App.createUploadDialog2 = function(config){
	var defaultConfig = {
		file_cat : 'others',
		url : __ctxPath + '/file-upload',
		reset_on_hide : false,
		upload_autostart : false,
		modal : true
	};
	Ext.apply(defaultConfig,config);	
	var	dialog = new Ext.ux.UploadDialog.Dialog(defaultConfig);
	return dialog;
};

/**
 * 把数组中的重复元素去掉
 * @param {} data 传入一个数组
 * @return {}　返回一个元素唯一的数组
 */
function uniqueArray(data) {
	data = data || [];
	var a = {};
	for (var i = 0; i < data.length; i++) {
		var v = data[i];
		if (typeof(a[v]) == 'undefined') {
			a[v] = 1;
		}
	};
	data.length = 0;
	for (var i in a) {
		data[data.length] = i;
	}
	return data;
};

/**
 * 这个方法是创建cookies<br>
 *  This function is used to set cookies 
 * @param {} name
 * @param {} value
 * @param {} expires
 * @param {} path
 * @param {} domain
 * @param {} secure
 */
function setCookie(name,value,expires,path,domain,secure) {
  document.cookie = name + "=" + escape (value) +
    ((expires) ? "; expires=" + expires.toGMTString() : "") +
    ((path) ? "; path=" + path : "") +
    ((domain) ? "; domain=" + domain : "") + ((secure) ? "; secure" : "");
}

/**
 * 这个方法是获取cookies<br>
 * This function is used to get cookies
 * @param {} name
 * @return {}
 */
function getCookie(name) {
	var prefix = name + "=" ;
	var start = document.cookie.indexOf(prefix); 
	
	if (start==-1) {
		return null;
	}
	
	var end = document.cookie.indexOf(";", start+prefix.length);
	if (end==-1) {
		end=document.cookie.length;
	}

	var value=document.cookie.substring(start+prefix.length, end) ;
	return unescape(value);
}

/**
 * 这个方法是删除cookies<br>
 * This function is used to delete cookies
 * @param {} name
 * @param {} path
 * @param {} domain
 */
function deleteCookie(name,path,domain) {
  if (getCookie(name)) {
    document.cookie = name + "=" +
      ((path) ? "; path=" + path : "") +
      ((domain) ? "; domain=" + domain : "") +
      "; expires=Thu, 01-Jan-70 00:00:01 GMT";
  }
}

/**
 * 重写去空格方法
 * @return {}
 */
String.prototype.trim = function() {
	return(this.replace(/^[\s\xA0]+/, "").replace(/[\s\xA0]+$/, ""));
};

/**
 * 封装请求
 * @param {} config
 */
function $request(config){
		Ext.Ajax.request({
			url:config.url,
			params:config.params,
			method:config.method==null?'POST':config.method,
			success:function(response,options){
				if(config.success!=null){
					config.success.call(this,response,options);
				}
			},
			failure:function(response,options){
				Ext.ux.Toast.msg('操作信息','操作出错，请联系管理员！');
				if(config.success!=null){
					config.failure.call(this,response,options);
				}
			}
		});
}

/**
 * 异步的GET请求
 */
function asynReq(){
	var conn = Ext.Ajax.getConnectionObject().conn;
	conn.open("GET", url,false);
	conn.send(null); 
}

/**
 * 为GridPanel添加打印及导出功能
 * @param {} grid GridPanel
 */
AppUtil.addPrintExport=function(grid){
	/*
	var exportButton = new Ext.ux.Exporter.Button({
          component: grid,
          iconCls: 'btn-excel',
          text     : '导出'
        });
        
        grid.getTopToolbar().add('->');
       	grid.getTopToolbar().add(exportButton);
        
		grid.getTopToolbar().add(
				new Ext.Button({
					text:'打印',
					iconCls:'btn-print',
					handler:function(){
						Ext.ux.Printer.print(grid);
					}
				})
		);*/
		
};


function $getConfFromTable(table){
	   var map=new Ext.util.MixedCollection(true);
       if(table.rows.length!=2){
 			return map;
 	   }
 	   var row1=table.rows[0];
	   var row2=table.rows[1];
	   for(var i=0;i<row1.cells.length;i++){
			var cell=row2.cells[i];
			var control;
			for(var j=0;j<cell.childNodes.length;j++){
				if(cell.childNodes[j].getAttribute&&cell.childNodes[j].getAttribute('name')){
					control=cell.childNodes[j];
					break;
				}
			}
			if(!control)continue;
			var name=control.getAttribute('name');
			var xtype=control.getAttribute('xtype');
			var header=row1.cells[i].innerHTML;
			var format=control.getAttribute('dataformat');
			var itemsName=control.getAttribute('txtitemname');
			var isnotnull=control.getAttribute('txtisnotnull');
			var issingle=control.getAttribute('issingle');
			var iscurrent=control.getAttribute('iscurrent');
			var txtvaluetype=control.getAttribute('txtvaluetype');
			var type = control.type;
			
			var vals=[];
			if(type&&type.indexOf('select')!=-1){
			   xtype='comboselect';
			   var opts=control.options;
			   for(var v=0;v<opts.length;v++){
			      var opt=opts[v];
			      var o=[];
			      o.push(opt.value);
			      o.push(opt.text);
			      vals.push(o);
			   }
			}
			var conf;
			switch(xtype){
			   case 'numberfield':
			      conf={
				      xtype:'numbercolumn',
				      txtvaluetype:txtvaluetype,
				      sortable : false,
					  format:format?format:'',
					  dataIndex:name,
					  isNotNull:isnotnull==1?true:false,
					  dxtype:xtype,
					  isSingle:issingle==1?true:false,
					  header:header
				  };
				  break;
			   case 'comboselect':
			      conf={
				      xtype:'gridcolumn',
					  dataIndex:name,
					  sortable : false,
					  isNotNull:isnotnull==1?true:false,
					  dxtype:xtype,
					  header:header,
					  datas:vals
				  };
			      break;
			   case 'datefield':
			      conf={
				      xtype:'datecolumn',
				      sortable : false,
					  dataIndex:name,
					  isNotNull:isnotnull==1?true:false,
					  dxtype:xtype,
					  defaultValue:iscurrent?new Date():null,
					  format :format=='yyyy-MM-dd'?'Y-m-d':'Y-m-d H:i:s',
					  iscurrent:iscurrent,
					  header:header
				  };
			      break;
			   case 'userselector':
			      conf={
				      xtype:'gridcolumn',
				      sortable : false,
					  dataIndex:name,
					  isNotNull:isnotnull==1?true:false,
					  dxtype:xtype,
					  iscurrent:iscurrent,
					  defaultValue:iscurrent?App.auth.fullName:null,
					  isSingle:issingle==1?true:false,
					  format :format?format:'',
					  header:header
				  };
			      break;
			   case 'depselector':
			      conf={
				      xtype:'gridcolumn',
				      sortable : false,
					  dataIndex:name,
					  isNotNull:isnotnull==1?true:false,
					  dxtype:xtype,
					  iscurrent:iscurrent,
					  defaultValue:iscurrent?App.auth.orgName:null,
					  isSingle:issingle==1?true:false,
					  format :format?format:'',
					  header:header
				  };
			      break;   
			   case 'posselector':
			      conf={
					      xtype:'gridcolumn',
						  dataIndex:name,
						  isNotNull:isnotnull==1?true:false,
						  dxtype:xtype,
						  sortable : false,
						  iscurrent:iscurrent,
						  defaultValue:iscurrent?App.auth.orgName:null,
						  isSingle:issingle==1?true:false,
						  format :format?format:'',
						  header:header
					  };
				      break;
			   case 'diccombo':
			      conf={
				      xtype:'gridcolumn',
				      sortable : false,
					  dataIndex:name,
					  isNotNull:isnotnull==1?true:false,
					  dxtype:xtype,
					  proTypeId: itemsName,
					   //itemsname: itemsName,
					  width: control.getAttribute('width')*0.9,
					  header:header
				  };
			      break;
			   default:
			      conf={
				      xtype:'gridcolumn',
				      sortable : false,
					  dataIndex:name,
					  isNotNull:isnotnull==1?true:false,
					  dxtype:xtype,
					  isSingle:issingle==1?true:false,
					  format :format?format:'',
					  header:header
				  };
			      break;
			}
			var callfunction=control.getAttribute('callbackfunction');//对外的funciton名，可以对列表的数据进行操作
			
			if(callfunction){
				try{
					var callf=eval(callfunction);
					if(callf){
						conf['callbackfunction']=callf;//callbackfunction.call(this,name,value,record,store);
					}
				}catch(e){}
			}
			
			var cfunction=control.getAttribute('changefunction');
			if(cfunction && cfunction!==undefined){
				try{
					var callf=eval(cfunction);
					if(callf){
						conf['changefunction']=callf;
					}
				}catch(e){}
			}
			
			map.add(name,conf);
			
	   }
	   
	   return map;
 	   
}
 
/**
 * 取table里所有的输入框txtname
 * @param {} table
 * @return {}
 */
  function $getTableInputCmpName(table){
		var maps=[];
		for(var i=0;i<table.rows.length;i++){
			var row=table.rows[i];
		    for(var j=0;j<row.cells.length;j++){
		       var cell=row.cells[j];
		       var control;
		       for(var k=0;k<cell.childNodes.length;k++){
				if(cell.childNodes[k].getAttribute&&cell.childNodes[k].getAttribute('name')){
					control=cell.childNodes[k];
					if(control){
					   var name=control.getAttribute('name');
					   maps.push(name);
				    }
				}
			   }
			   
		    }
		}
		return maps;
 }
 
/**
 * 替换组件
 * @param {} fElements 
 * @param {} jsonData
 * @param {} rightJson
 * @param    addFlag 是否为后来加上的
 * @return {}
 */
function $converCmp(fElements,jsonData,rightJson,addFlag,readOnly){
	    var flag='on';
	    var formPanel=this.formPanel;
        var map =new Ext.util.MixedCollection();
		var arrays=new Array();
		var removeArray=[];
		
		//计算表达式
		//${totalSum}=${priceform}*${quaritiesv}
		
		//${totalSum}=(${priceform}+${quaritiesv})*${priceform}
		//${totalSum}=SUM(${priceform});
		
		//${totalAvge}=AVG(${priceform});
		
		var sumTotal={ //计算规则
			valueName:'',//值el的name
			operNames:['',''],//统计的el的name
		    valueEl:null,//运算值的El
		    operEls:[],//统计的els
			operaType:''//运算符
		};
		
		
		
		Ext.each(fElements, function(element,index) {
			var name,type,value,xtype,height;
			var right=null;
	        if(!element){
	            return;
	        }
	        name = element.name;
	        type = element.type;
	        if(rightJson && rightJson[name]){
				right=rightJson[name];
			}
	        if(type=='button'||type=='hidden'){
	            return;
	        }
	        if(element.style.display=='none'){
	            return;
	        }
			xtype=element.getAttribute('xtype');
			if(name){
				name=name.replace(/(^\s*)|(\s*$)/g, "");//去掉两边的空格
			}
			var parent=element.parentNode;
			
			if(type=='radio'||type=='checkbox'){
				var value;
				if(jsonData && jsonData[name]){
				   value=jsonData[name];
				}
                if(element.value&&value){
                	var str1=element.value+'|',str2=value+'|';
                	var isContain=str2.indexOf(str1)>-1;
	                if((element.value==value)||isContain){
	                	element.checked=true;
	                	element.setAttribute('isSelect', 'true');
//	                	element.setAttribute('checked', 'true');
	                }else{
	                	element.checked=false;
//	                	element.setAttribute('checked', 'false');
	                }
                }
                if(readOnly||(right&&right==1)){
	            	element.onclick=function(){return false;};
	            }else if(right&&right==0){
	            	 element.style.display='none';
	            }
                return;
			}else if(jsonData && jsonData[name]){
				element.value=jsonData[name];
			}
			
			
			
			if(type&&type.indexOf('select')!=-1){
				if(readOnly||(right&&right==1)){
					element.disabled = true;
					element.onfocus=function(){
						this.blur();
					};
				}else if(readOnly||(right&&right==0)){
					element.style.display='none';
				}
				return;
			}
			
			
			var width=element.getAttribute('width');
			var isNotNull=element.getAttribute('txtisnotnull');
			//如果权限修改为必填，不管之前设置，修改为必填  修改时间：2013-10-28 修改人：wzh
			if(right&&right==3){
				isNotNull=1;
				element.setAttribute("txtisnotnull","1");
			}
			if(!width){
			  width=parent.offsetWidth;
			}
			if(width<300&&parent.offsetWidth>300){
			   width=300;
			}
			
			//查找统计的el
			if(element.tagName=='INPUT'){
				if(element.name==sumTotal.valueName){
				   sumTotal.valueEl=element;
				}
				if(sumTotal.operNames.indexOf(element.name)!=-1){
				   sumTotal.operEls.push(element);
				}
			}
			
			
			
			var readable=readOnly||(right&&right==1);
			if(readable){
			   readable=true;
			}else{
			   readable=false;
			}
			
			if(readable&&xtype=='fckeditor'){
				var children=parent.children;
				var height;
				if(children.length==1){
				   height=parent.scrollHeight;
				}else{
				   height=element.scrollHeight;
				}
	   	        var html=element.value;
	   	        
			    var p=document.createElement("div");
			    p.setAttribute('style','color:black;font-size:14px;text-align:left;');
			    p.style.width='100%';
			    
			    p.style.height=height+'px';
			    p.innerHTML=html;
			    parent.insertBefore(p,element);
			    element.style.display='none';
				return ;
			}
			//附件
			if(readable&&xtype=='fileattach'){
			    var html='';
			    if(element.value!=""){
				    var files=element.value.split(',');
		   	        for(var i=0;i<files.length;i++){
			   	      var vo=files[i].split("|");
			   	      var id=vo[0];
			   	      var name=vo[1];
			   	      if(!isNaN(vo[0])){
			   	        html+='<a href="'+__ctxPath+'/file-download?fileId='+vo[0]+'">'+vo[1]+'</a>';
			   	      }
		   	       }
			    }
	   	       if(html==''){
	   	         html=element.value;
	   	       }
			   var p=document.createElement("span");
			   p.innerHTML=html;
			   parent.insertBefore(p,element);
			   element.style.display='none';
			   return;
			}
			
			if(!right||right!=0){
				if(xtype=='datefield'){
						var format=element.getAttribute('dataformat');//dataformat
						var iscurrent=element.getAttribute('iscurrent');
						var span=document.createElement('div');
						var obj={parentNode:parent,oldEl:element,newEl:span};
						removeArray.push(obj);
						var div=document.createElement('div');
						div.setAttribute('style','width:'+width+'px');
						span.appendChild(div);
					try{
						var cmp;
						if(format=='yyyy-MM-dd HH:mm:ss'){
						    cmp=new Cls.form.DateTimeField({
									name:name,
									width : 200,
									//editable:false,
									readOnly:readable,
									autoWidth:false,
									sortable : false,
									boxMaxWidth:200,
									format : 'Y-m-d H:i:s',
									value:iscurrent==1?new Date():'',
									allowBlank:isNotNull==1&&!readable?false:true
								});
						    
						}else{
							cmp=new Ext.form.DateField({
									name:name,
									height:21,
									//editable:false,
									readOnly:readable,
									sortable : false,
									width : 100,
									boxMaxWidth:100,
									autoWidth:false,
									format:'Y-m-d',
									value:iscurrent==1?new Date():'',
									allowBlank:isNotNull==1&&!readable?false:true
								});
						}
						cmp.remove;
						arrays.push('datefield'+index);
						cmp.on('resize',function(c,width1,height,owidth){
					        cmp.setWidth(width);
					    },this);
					    map.add('datefield'+index,span);
						map.add('datefield'+index+'-cmp',cmp);
						// 保存控件类型
						map.add('datefield'+index+'-type','datefield');
						if(element.value){
							cmp.setValue($parseDate(element.value));
						}
					}catch(e){}
				}else if(xtype=='diccombo'){
					try{
					var itemname=element.getAttribute('txtitemname');
					var span=document.createElement('span');
					var obj={parentNode:parent,oldEl:element,newEl:span};
					removeArray.push(obj);
					var div=document.createElement('div');
					span.appendChild(div);
					var cmp=new DicCombo({
						        name:name,
						        readOnly:readable,
						        sortable : false,
								proTypeId:itemname,
								displayField : 'itemName',
				                valueField : 'itemName',
								//renderTo:div,
								width : width*0.9,
								allowBlank:isNotNull==1&&!readable?false:true
							});
					arrays.push('diccombo'+index);
					cmp.on('resize',function(c,width1,height,owidth){
				        cmp.setWidth(width);
				    },this);
				    map.add('diccombo'+index,span);
					map.add('diccombo'+index+'-cmp',cmp);
					// 保存控件类型
					map.add('diccombo'+index+'-type','diccombo');
					if(element.value){
						cmp.setValue(element.value);
					}
					}catch(e){}
				}else if(xtype=='commoneditor'){
					height=parent.offsetHeight;
					var h=element.getAttribute('txtheight');
					var scl=false;
					if(h&&h>0){
					    height=parent.offsetHeight;
					    scl=true;
					}else{
					    height='';
					}
					var span=document.createElement('div');
					var obj={parentNode:parent,oldEl:element,newEl:span};
					removeArray.push(obj);
					var div=document.createElement('div');
					span.appendChild(div);
					var cmp=new Ext.ux.form.CommentEditor({
							name:name,
							readOnly:readable,
							sortable : false,
						    width:width,
						    autoScroll:scl,
							height:height,
							value:element.value?element.value:''
					});
					arrays.push('commoneditor'+index);
					cmp.on('resize',function(c,width1,height,owidth){
				        cmp.setWidth(width);
				    },this);
				    map.add('commoneditor'+index,span);
					map.add('commoneditor'+index+'-cmp',cmp);
					// 保存控件类型
					map.add('commoneditor'+index+'-type','commoneditor');
				}else if(xtype=='fckeditor'){
					height=parent.offsetHeight;
					var span=document.createElement('div');
					var obj={parentNode:parent,oldEl:element,newEl:span};
					removeArray.push(obj);
					var div=document.createElement('div');
					span.appendChild(div);
					var cmp=new Ext.form.CKEditor({
							name:name,
							readOnly:readable,
							sortable : false,
							height:height,
							value:element.value?element.value:''
					});
					arrays.push('fckeditor'+index);
				    map.add('fckeditor'+index,span);
					map.add('fckeditor'+index+'-cmp',cmp);
					// 保存控件类型
					map.add('fckeditor'+index+'-type','fckeditor');
	//				if(element.value){
	//					cmp.setValue(element.value);
	//				}
				}else if(xtype=='officeeditor'){
				    try{
					    var span=document.createElement('div');
					    height=parent.offsetHeight;
						this.hiddenF=new Ext.form.Hidden({
						    name:name
						});
						this.hiddenF.render(span);
						var obj={parentNode:parent,oldEl:element,newEl:span};
						removeArray.push(obj);
						Ext.useShims=true;
						var cmp={
							  isOfficePanel:true,
							  right:right,
							  sortable : false,
						      showToolbar:right==1?false:true,
		     	              width:width,
		     	              height:height,
		     	              fileId:element.value,
		     	              doctype:'doc',
		     	              readOnly:readable,
		     	              unshowMenuBar:true};
		     	        arrays.push('officeeditor'+index);
					    map.add('officeeditor'+index,span);
						map.add('officeeditor'+index+'-cmp',cmp);
						// 保存控件类型
						map.add('officeeditor'+index+'-type','officeeditor');
						if(element.value){
							this.hiddenF.setValue(element.value);
							 this.fileId=element.value;
						}
				    }catch(e){}
				}else if(xtype=='userselector'){
					try{
					var span=document.createElement('div');
					var isSingle=element.getAttribute('issingle');
					var iscurrent=element.getAttribute('iscurrent');
					var hiddenF=new Ext.form.Hidden({
						value:iscurrent==1?curUserInfo.userId:'',
					    name:name+'UId'
					});
					if(jsonData){
						var id=jsonData[name+'UId'];
						if(id){
							hiddenF.setValue(id);
						}
					}
					hiddenF.render(span);
					var obj={parentNode:parent,oldEl:element,newEl:span};
					removeArray.push(obj);
					
					var txtf=new Ext.form.TextField({
					       name:name,
					       margins:Ext.isChrome?'0 10 0 0':'0 3 0 0',
					       readOnly:true,
					       sortable : false,
					       value:curUserInfo.fullname,
					       allowBlank:isNotNull==1&&!readable?false:true,
					       width:width?(width-90>0?width-90:width):width
					});
					
					if(isSingle==0){
					   txtf=new Ext.form.TextArea({
					       name:name,
					       margins:Ext.isChrome?'0 10 3 0':'0 3 0 0',
					       readOnly:true,
					       sortable : false,
					       allowBlank:isNotNull==1&&!readable?false:true,
					       value:curUserInfo.fullname,
					       width:width?(width-90>0?width-90:width):width
					   });
					}
					
				    var cmp=new Ext.form.CompositeField({
				    	   width:width,
				           items:[txtf,{
				               xtype:'button',
				               width:78,
				               border:false,
				               disabled:readable,
				               text:'选择人员',
				               iconCls:'btn-sel',
				               handler:function(){  
				        	  	 new UserDialog({
				        	    	   scope:this,
				        	    	   single:isSingle==1?true:false,
					                   callback:function(id,name){
					                   	  txtf.setValue(name);
					                   	  hiddenF.setValue(id);
					                   }
					               }).show();
				               }
				           
				           }]
				    });
				    arrays.push('userselector'+index);
				    cmp.on('resize',function(c,width1,height,owidth){
				        cmp.setWidth(width);
				    },this);
				    map.add('userselector'+index,span);
					map.add('userselector'+index+'-cmp',cmp);
					// 保存控件类型
					map.add('userselector'+index+'-type','userselector');
					if(element.value){
						txtf.setValue(element.value);
					}
	//				formPanel.add(cmp);
					}catch(e){}
				}else if(xtype=='depselector'){
					var isSingle=element.getAttribute('issingle');
					var iscurrent=element.getAttribute('iscurrent');
					try{
					var span=document.createElement('div');
					var hiddenF=new Ext.form.Hidden({
						value:iscurrent==1?App.auth.orgId:'',
					    name:name+'Did'
					});
					if(jsonData){
						var id=jsonData[name+'Did'];
						if(id){
							hiddenF.setValue(id);
						}
					}
					hiddenF.render(span);
					var obj={parentNode:parent,oldEl:element,newEl:span};
					removeArray.push(obj);
					var txtf=new Ext.form.TextField({
					       name:name,
					       margins:Ext.isChrome?'0 10 0 0':'0 3 0 0',
					       readOnly:true,
					       sortable : false,
					       value:curUserInfo.depName,
					       allowBlank:isNotNull==1&&!readable?false:true,
					       width:width?(width-90>0?width-90:width):width
					});
					if(isSingle==0){
					   txtf=new Ext.form.TextArea({
					       name:name,
					       sortable : false,
					       margins:Ext.isChrome?'0 10 3 0':'0 3 0 0',
					       readOnly:true,
					       value:curUserInfo.depName,
					       allowBlank:isNotNull==1&&!readable?false:true,
					       width:width?(width-90>0?width-90:width):width
					   });
					}
				    var cmp=new Ext.form.CompositeField({
				    	   width:txtf.width+80,
				           items:[txtf,{
				               xtype:'button',
				               border:false,
				               width:78,
				               disabled:readable,
				               text:'选择组织',
				               iconCls:'btn-users',
				               handler:function(){
				        	   		DepSelector.getView(function(id, name){
				        	   				txtf.setValue(name);
						             	    hiddenF.setValue(id);
				        	   		},isSingle==1?true:false).show();
				               }
				           
				           }]
				    });
				    arrays.push('depselector'+index);
				    cmp.on('resize',function(c,width1,height,owidth){
				        cmp.setWidth(width);
				    },this);
				    map.add('depselector'+index,span);
					map.add('depselector'+index+'-cmp',cmp);
					// 保存控件类型
					map.add('depselector'+index+'-type','depselector');
					if(element.value){
						txtf.setValue(element.value);
					}
					}catch(e){}
				}else if(xtype=='posselector'){
					var isSingle=element.getAttribute('issingle');
					var iscurrent=element.getAttribute('iscurrent');
					try{
					var span=document.createElement('div');
					var hiddenF=new Ext.form.Hidden({
						value:iscurrent==1?App.auth.orgId:'',
					    name:name+'Did'
					});
					if(jsonData){
						var id=jsonData[name+'Did'];
						if(id){
							hiddenF.setValue(id);
						}
					}
					hiddenF.render(span);
					var obj={parentNode:parent,oldEl:element,newEl:span};
					removeArray.push(obj);
					var txtf=new Ext.form.TextField({
					       name:name,
					       sortable : false,
					       margins:Ext.isChrome?'0 10 0 0':'0 3 0 0',
					       readOnly:true,
					       value:curUserInfo.posName,
					       allowBlank:isNotNull==1&&!readable?false:true,
					       width:width?(width-90>0?width-90:width):width
					});
					if(isSingle==0){
					   txtf=new Ext.form.TextArea({
					       name:name,
					       sortable : false,
					       margins:Ext.isChrome?'0 10 3 0':'0 3 0 0',
					       readOnly:true,
					       value:curUserInfo.posName,
					       allowBlank:isNotNull==1&&!readable?false:true,
					       width:width?(width-90>0?width-90:width):width
					   });
					}
				    var cmp=new Ext.form.CompositeField({
				    	   width:txtf.width+80,
				           items:[txtf,{
				               xtype:'button',
				               border:false,
				               width:78,
				               disabled:readable,
				               text:'选择岗位',
				               iconCls:'btn-users',
				               handler:function(){
				            	   
				        			new PositionDialog({
				        				scope:this,
				        				single:isSingle==1?true:false,
				        				callback:function(ids,names){
				        	   				txtf.setValue(names);
						             	    hiddenF.setValue(ids);
				        				}
				        			}).show();
				        	   		
				               }
				           
				           }]
				    });
				    arrays.push('posselector'+index);
				    cmp.on('resize',function(c,width1,height,owidth){
				        cmp.setWidth(width);
				    },this);
				    map.add('posselector'+index,span);
					map.add('posselector'+index+'-cmp',cmp);
					// 保存控件类型
					map.add('posselector'+index+'-type','posselector');
					if(element.value){
						txtf.setValue(element.value);
					}
					}catch(e){}
				}else if(xtype=='fileattach'){
					try{
					var span=document.createElement("div");
					span.setAttribute("style","height:63px;");
					var hiddenF=new Ext.form.Hidden({
					    name:name
					});
					hiddenF.render(span);
					var obj={parentNode:parent,oldEl:element,newEl:span};
					removeArray.push(obj);
					var r=width?(width-90>0?width-90:width):width;
					var txtf=new Ext.Panel({
						bodyStyle:'width:'+r+'px;',
						height:60,
						margins:Ext.isChrome?'0 3 3 0':'0 3 3 0',
						border:true,
						autoScroll:true,
						html:''
					});
					
						
				    var cmp=new Ext.form.CompositeField({
				    	   width:r+84,
				           items:[txtf,{
				               xtype:'button',
				               width:78,
				               disabled:readable,
				               text:'选择附件',
				               iconCls:'menu-attachment',
				               handler:function(){
	               	               var dialog = App.createUploadDialog({
										file_cat : 'flow',
										callback : function(data) {
											for (var i = 0; i < data.length; i++) {
												if (hiddenF.getValue() != '') {
													hiddenF
															.setValue(hiddenF
																	.getValue()
																	+ ',');
												}
												hiddenF.setValue(hiddenF
														.getValue()
														+ data[i].fileId+'|'+data[i].fileName);
												Ext.DomHelper
														.append(
																txtf.body,
																'<span><a href="#" onclick="FileAttachDetail.show('
																		+ data[i].fileId
																		+ ')">'
																		+ data[i].fileName
																		+ '</a> <img class="img-delete" src="'
																		+ __ctxPath
																		+ '/images/system/delete.gif" onclick="AppUtil.removeFile(this,'
																		+ data[i].fileId
																		+ ')"/>&nbsp;|&nbsp;</span>');
											}
										}
									});
									dialog.show(this);
				               }
				           
				           }]
				    });
				    cmp.on('resize',function(c,width1,height,owidth){
				        cmp.setWidth(width);
				    },this);
				    arrays.push('fileattach'+index);
				    map.add('fileattach'+index,span);
					map.add('fileattach'+index+'-cmp',cmp);
					// 保存控件类型
					map.add('fileattach'+index+'-type','fileattach');
					AppUtil.removeFile=function(obj, fileId,fileName) {
						var fileIds = hiddenF;
						var value = fileIds.getValue();
						if (value.indexOf(',') < 0) {// 仅有一个附件
							fileIds.setValue('');
						} else {
							value = value.replace(',' + fileId+'|'+fileName, '').replace(fileId+'|'+fileName + ',', '');
							fileIds.setValue(value);
						}
						var el = Ext.get(obj.parentNode);
						el.remove();
					};
					cmp.on('render',function(){
	
					   if(element.value){
							hiddenF.setValue(element.value);
							var filea=element.value.split(',');
							for(var i=0;i<filea.length;i++){
							    var ss=filea[i];
							    var as=ss.split('|');
							    var fileId=as[0];
							    var fileName=as[1];
							    var delImg='';
							    if(!readable){
							       delImg='<img class="img-delete" src="'
										+ __ctxPath
										+ '/images/system/delete.gif" onclick="AppUtil.removeFile(this,'
										+ fileId+',\''+fileName
										+ '\')"/>';
							    }
							    Ext.DomHelper
								.append(
										txtf.body,
										'<span><a href="#" onclick="FileAttachDetail.show('
												+ fileId
												+ ')">'
												+ fileName
												+ '</a> ' +delImg+
												'&nbsp;|&nbsp;</span>');
							}
						}
						
						
					},this);
				   }catch(e){}
					
				}else{
				    //未转换的EL
				    if(readable){
						element.setAttribute('readOnly','true');
						element.setAttribute('txtisnotnull','0');
						element.readOnly=true;
						element.onclick=function(){return false;};
						return;
					}
				}
			}
			
			// mod by cjj 20120924
			//无权限的字段  
			if(right&&right==0){
			    element.style.display='none';
			    var p=document.createElement("div");
			    p.setAttribute('style','width:'+element.style.width+';height:'+element.style.height>23?element.style.height:23+';background-color:#E9E9E9');
				p.innerHTML='<div style="height:23px;"><font color="red" align="center">无权限</font></div>';
				parent.insertBefore(p,element);
			}
			// mod by cjj 20120924
			
		   element.onblur=function(){
		   	  $validField.call(this,element);
		   };
			
		 },this);
		 
		 var valueEl=sumTotal.valueEl;
		 var opersEl=sumTotal.operEls;
		 var operType=sumTotal.operaType;
		 for(var b=0;b<opersEl.length;b++){
		     var gel=opersEl[b];
		     gel.onblur=function(){
		     	 if(isNaN(opersEl[0].value)){
		     	 	opersEl[0].value=0;
		     	    return false;
		     	 }
		     	 var q=opersEl[1];
		     	 var invalidClass=' x-form-invalid';
			   	 var oldClass=q.getAttribute('class');
		     	 if(isNaN(opersEl[1].value)||opersEl[1].value>12){
		     	 	 opersEl[1].value=0;
		   	      	 
			         if(oldClass){
			             if(oldClass.indexOf(invalidClass)==-1){
				   	  	    oldClass=oldClass+invalidClass;
				   	  	 }
			         }else{
			             oldClass=invalidClass;
			         }
			   	     q.setAttribute('class',oldClass);
			         q.qclass = 'x-form-invalid-tip';
			         q.qtip = '该值不能超过12';
			         Ext.ux.Toast.msg('表单验证信息','该值不能超过12');
		     	     return false;
		     	 }else{
		     	     if(oldClass){
		                 q.setAttribute('class',oldClass.replace(invalidClass,''));
				     }
	                 q.qtip='';
			         q.qclass = '';
		     	 }
		     	 var valuew=0;
		     	 
		     	 var val1=parseFloat(opersEl[0].value);
		     	 var val2=parseFloat(opersEl[1].value);
		     	 if(isNaN(val1)){
		     	    val1=0;
		     	 }
		     	 if(isNaN(val2)){
		     	    val2=0;
		     	 }
		         var str=String.format('{0}'+operType+'{1}',val1,val2);
		         valuew=eval(str);
		         valueEl.value=parseFloat((valuew==NaN)?0:valuew).toFixed(3);
		         var sum=document.getElementsByName('sum');
		         var elss=document.getElementsByName(valueEl.name);
		         var dval=0;
		         for(var q=0;q<elss.length;q++){
		             var qel=elss[q];
		             var qval=qel.value;
		             if(isNaN(qval)){
			     	    qval=0;
			     	 }
			     	 dval+=parseFloat(qval);
		         }
		         sum[0].value=dval.toFixed(3);
		     };
		 }
		 
		for(var g=0;g<removeArray.length;g++){
		    var obj=removeArray[g];
		    try
		    {
		       obj.parentNode.replaceChild(obj.newEl,obj.oldEl);
		    }
		    catch(e){alert(e);}
		}
		
	    if(arrays.length>0&&map.length>0){

	    	Ext.each(arrays,function(its,index){
	    		// 控件
	    	    var cmp=map.get(its+'-cmp');
	    	    // 控件返回值，隐藏域
				var span=map.get(its);
				// 控件类型
				var type = map.get(its+'-type');
				if(cmp.isOfficePanel){
				   mySubmitType = "ProcessRunStart";
				   var o=new NtkOfficePanel(cmp);
				   if(!o.flag){
				     Ext.ux.Toast.msg('提示信息',o.msg);
				     return;
				   }
				   o.panel.on('resize',function(c,width1,height,owidth){
				        o.panel.setWidth(cmp.width);
				    },this);
				   o.panel.render(span);
				   /*  在NtkOfficePanel.js中加载时已经处理只读功能
				   if(cmp.right==1||cmp.readOnly){
					   	//IE的是同步的，只需要加载完直接设置只读
						if(Ext.isIE){
							o.setReadOnly();
						} 	
				   }*/
				   this.officePanel=o;
				   myNewOffice = o;				   
				}else{
		    	    try{
			            var divs=document.createElement('div');
			            var div22=document.createElement('div');
			            divs.appendChild(div22);
			            span.appendChild(divs);
			            cmp.render(div22);
//			            if(addFlag!=true){
//	                        formPanel.add(cmp);
//			            }
			            if(type=='fckeditor'){
			            	formPanel.add(cmp);
			            }
		    	    }catch(e){
		    	      alert(e);
		    	    }
				}
	    	    
	    	},this);
		}
		return flag;

};

var validForm={isValid:true,messge:'',el:null};
FileAttachDetail.setGrids=function(grids){
   this.grids=grids;
};

FileAttachDetail.delFile=function(name,dataindex,fileId,fileName){
    var grid=this.grids.get(name);
	var res=grid.getSelectionModel().getSelections();
	var record=res[0];
    var oldv=record.get(dataindex);
    
    if (oldv.indexOf(',') < 0) {// 仅有一个附件
		record.set(dataindex,'');
	} else {
		oldv = oldv.replace(',' + fileId+'|'+fileName, '').replace(fileId+'|'+fileName + ',', '');
		record.set(dataindex,oldv);
	}
};

FileAttachDetail.upload=function(name,dataindex){
	var grid=this.grids.get(name);
	var res=grid.getSelectionModel().getSelections();
	var record=res[0];
	
	var dialog = App.createUploadDialog({
		file_cat : 'flow',
		callback : function(data) {
			  var oldv=record.get(dataindex);
			  if(oldv){
			     oldv+=',';
			  }else{
			     oldv='';   
			  }
			  var str='';
			  for (var i = 0; i < data.length; i++) {
			  	if(i>0){
			  	   str+=',';
			  	}
			  	str+=data[i].fileId+'|'+data[i].fileName;
			  }
			  record.set(dataindex,oldv+str);
		}
	});
	dialog.show(this);
};


function $converDetail(jsonData,rightJson,readOnly,subRightjson){
		//回填数据
		var form=this.formPanel.getForm().getEl().dom;
		var tables=form.getElementsByTagName('table');
		this.detailGrids=new Ext.util.MixedCollection();
		
		var formobjs=[];
		var removeTables=[];
		
		this.formValidCmp=new Array();
		
		for(var i=0;i<tables.length;i++){
			var isdetail=tables[i].getAttribute('isdetail');
			var isgrid=tables[i].getAttribute('isgrid');
			var gridName=tables[i].getAttribute('txtname');
			var parent=tables[i].parentNode;
			if(isdetail!=null&&'true'==isgrid){
				var hide=rightJson&&rightJson[gridName];
				if(!hide||hide==0){
					var detailPanel= $converDetailGrid.call(this,tables[i],gridName,rightJson,readOnly,subRightjson);
					this.detailGrids.add(gridName,detailPanel);
					//加载其列表
				    if(jsonData){
						var data=jsonData['WF_'+gridName+'s'];
						if(data){
							detailPanel.getStore().loadData({result:data});
						}
					}
				}else{
				    var p=document.createElement("div");
				    p.setAttribute('style','width:'+tables[i].style.width+';height:'+tables[i].style.height+';background-color:#E9E9E9');
					p.innerHTML='<font color="red">无权限</font>';
					parent.insertBefore(p,tables[i]);
				}
				removeTables.push(tables[i]);
			}else if(isdetail!=null&&'false'==isgrid){//表单形式的明细展示
			  
				/**
				 * 将子表单放到一个div里，点击添加明细表单时，则在这个div里面添加。
				 */
			  try{
			  	var hide=rightJson&&rightJson[gridName];
			    if(!hide||hide==0){
					var names=$getTableInputCmpName(tables[i]);
					//取得该子表单的数据
					var datas;
					var pkName;
					if(this.taskId){
						var pkKey=document.getElementById('WF_'+gridName+'_'+this.taskId);
						var pkKeyVar=pkKey.value;
						if(jsonData){
						   datas=jsonData['WF_'+gridName+'s'];
						   pkName=pkKeyVar;
						   if(false&&datas){
						      var obj=data[0];
						      var flag=false;
						      var pkKeyValue=obj[pkKeyVar];
						      for(var w=0;w<names.length;w++){
						      	 if(names[w]==pkKeyVar){
						      	    flag=true;
						      	 }
						         jsonData[names[w]]=obj[names[w]];
						      }
						   }
						}
					}else if(this.runId){
						var pkKey=document.getElementById('WF_'+gridName+'_'+this.runId);
						var pkKeyVar=pkKey.value;
						if(jsonData){
						   datas=jsonData['WF_'+gridName+'s'];
						   pkName=pkKeyVar;
						}
					}
					var attributes=tables[i].attributes;
					
					var tableHtml='<table ';
					for(var v=0;v<attributes.length;v++){
					  var d=attributes[v];
					  if(typeof d=='object'){
						  tableHtml+=' '+d.name+'=\''+d.value+'\' ';
					  }
					}
					tableHtml+='>';
					tableHtml+=tables[i].innerHTML;
				    tableHtml+='</table>';
					var html=tableHtml;//parent.innerHTML;
					
					var obj={
					   innerhtml:html,//对应的HTML
					   parentNode:parent,//父节点
					   gridName:gridName,//表名
					   elsName:names,//包括的控件名称
					   jsonDatas:datas,
					   pkName:pkName,
					   sortable : false,
					   rightJson:rightJson,
					   readOnly:readOnly
					};
					
					var cfunction=tables[i].getAttribute('delformfunction');
					if(cfunction){
						try{
							var callf=eval(cfunction);
							if(callf){
								obj['delformfunction']=callf;
							}
						}catch(e){}
					}
					
					formobjs.push(obj);
			    }else{
				    var p=document.createElement("div");
				    p.setAttribute('style','width:'+tables[i].style.width+';height:'+tables[i].style.height+';background-color:#E9E9E9');
					p.innerHTML='<font color="red">无权限</font>';
					parent.insertBefore(p,tables[i]);
				}
				removeTables.push(tables[i]);
			 }catch(e){alert(e);}
			 //**/
			}
		}
		
		FileAttachDetail.setGrids(this.detailGrids);
		/**
		 * 移动被替换的TABLE
		 * @type Number
		 */
		for(var i=0;i<removeTables.length;i++){
		    var table=removeTables[i];
		    var parent=table.parentNode;
		    parent.removeChild(table);
		}
		
		//转化控件及赋值	
		
		var fElements = form.elements || (document.forms[form] || Ext.getDom(form)).elements;
		
		$converCmp.call(this,fElements,jsonData,rightJson,false,readOnly);
		$converFormDetail.call(this,formobjs,readOnly);
		
};

function $checkSubButtonRight(subRightId){
  if(!subRightId) return true;
   var result=true;
    Ext.Ajax.request({
					url :__ctxPath+'/flow/checkRightFormButtonRight.do',
					params : {rightId:subRightId},
					method : 'POST',
					async: false, 
					success : function(response,options) {
						 var json=Ext.decode(response.responseText);						 
						 	result=json.success;
					},
					failure : function(response,options) {
						result=false;
					}
	});
  if(result) return result;
  return false;
}
function $converDetailGrid(table,gridName,rightJson,readOnly,subRightjson){
        var parent=table.parentNode;
        var tablekey=table.getAttribute('txtname');
		//var map=$convertTableToMap.call(this,table);
       
		var fields=[];
		var columns=[];
		//加入从表主键
		if(this.taskId){
			var subTableId=document.getElementById('WF_'+gridName+'_'+this.taskId);
			if(subTableId!=null&&subTableId!=""&&subTableId!=undefined){
				var subTableIdVal=subTableId.value;
				fields.push(subTableIdVal);
				columns.push({dataIndex:subTableIdVal,header:subTableIdVal,hidden:true,sortable : false});
			}
		}
		
		var map=$getConfFromTable.call(this,table);//Map variable
		var names=map.keys;
		var flag=0;
		var addRight=true,delRight=true;
		for(var f=0;f<names.length;f++){
			var conf=map.get(names[f]);//get conf;
			Ext.apply(conf,{//copy configuration
			   gridName:gridName
			});
			
			var right=null;//rights
			if(rightJson && rightJson[names[f]]){
				right=rightJson[names[f]];
			}
		    if(right==1){
		       flag--;
		       conf['uneditable']=true;
		    }else if(right==3){
		    	conf.isNotNull =true;
		       flag++;
		    }else{
		       flag++;
		    }
		    if(readOnly){
		       conf['uneditable']=true;
		    }
		    
		    //store.fields
			if(conf.xtype=='datecolumn'){
				fields.push({
					name : names[f],
					sortable : false,
					type:'date',
					isCurrent:conf.iscurrent,
					defaultValue:conf.defaultValue,
					dateFormat:conf.format,
					allowBlank : conf.isNotNull == true ? false : true,
					header : conf.header,
					callbackfunction : conf.callbackfunction,
					changefunction:conf.changefunction
				});
			}else{
			    fields.push({
					name : names[f],
					sortable : false,
					allowBlank : conf.isNotNull == true ? false : true,
					header : conf.header,
					defaultValue:conf.defaultValue,
					callbackfunction : conf.callbackfunction,
					changefunction:conf.changefunction
				});
			}
			
			if(right!=0){
				flag--;
				if(conf.dxtype=='fileattach'){
					conf['renderer']=$renderAttach;
				}
				if(conf.dxtype=='diccombo'){
					conf['itemname']=conf.itemsname;
					conf.returnName=true;
				}
                columns.push(conf);//grid.columns
			}
	}
	var div=document.createElement('div');
	parent.appendChild(div);
		var toobar=new Ext.Toolbar({
			disabled:readOnly==true?true:false,
			frame: true,
			items:[]
	});
	var addButton=new Ext.Button({
					text:'添加记录',
					iconCls:'btn-add',
					scope:this,
					gridName:gridName,
					handler:function(cmp){
						var detailPanel=this.detailGrids.get(cmp.gridName);
						var recordType=detailPanel.getStore().recordType;
						var record=new recordType();
						var fields=record.fields.items;
						for(var v=0;v<fields.length;v++){
						   var field=fields[v];
						   if(field.defaultValue){
						      record.set(field.name,field.defaultValue);
						   }
						}
						detailPanel.getStore().add(record);
					}
				});
	var delButton=new Ext.Button({
					text:'删除记录',
					iconCls:'btn-del',
					scope:this,
					gridName:gridName,
					handler:$detailDel
				});
	
	if(subRightjson){
		addRight=$checkSubButtonRight(subRightjson[tablekey+'-1']);
		delRight=$checkSubButtonRight(subRightjson[tablekey+'-2']);
		
	}
	if(addRight) toobar.addItem(addButton);
	if(delRight) toobar.addItem(delButton);
/*	[{
					text:'添加记录',
					iconCls:'btn-add',
					scope:this,
					gridName:gridName,
					handler:function(cmp){
						var detailPanel=this.detailGrids.get(cmp.gridName);
						var recordType=detailPanel.getStore().recordType;
						var record=new recordType();
						var fields=record.fields.items;
						for(var v=0;v<fields.length;v++){
						   var field=fields[v];
						   if(field.defaultValue){
						      record.set(field.name,field.defaultValue);
						   }
						}
						detailPanel.getStore().add(record);
					}
				},{
					text:'删除记录',
					iconCls:'btn-del',
					scope:this,
					gridName:gridName,
					handler:$detailDel
				}
			]
		})*/
	//加上明细的表格
	var detailPanel= new HT.EditorGridPanel({
		renderTo:div,
		hiddenSm:readOnly,
		tbar:new Ext.Toolbar({
			disabled:readOnly==true?true:false,
			frame: true,
			items:[toobar]
		}),
		clicksToEdit:1,
		width:table.offsetWidth,
		showPaging:false,
		autoHeight:true,
		fields : fields,
		columns : columns,
		listeners:{
			scope:this,
		    'rowclick':function(grid,index,e){
		        this.clickRow=index;
		    },
		    'cellclick':function(grid,row,col,e){
		    	if(!readOnly){
			        var column=grid.getColumnModel().getColumnById(col);
			        if(column&&column.dxtype=='fileattach'){
			            return ;
			        }
			        var cmp=$converCmpInColumn.call(this,column,row);
			        if(column.dxtype=='diccombo'){
			        	column.renderer = function(v,m,record){
							if(column.returnName){
			        			return v;
			        		}
			        		var s = cmp.getStore();
			        		var idx = s.find('itemId',v);
			        		if(idx!=-1){
			        			return s.getAt(idx).data.itemName;
			        		}	
			        		return record.get('itemName');
			        	};
			        }
			        if(column&&cmp)column.setEditor(cmp);
		    	}
		    }
		}
	});
	if(!readOnly){
		var store=detailPanel.getStore();
		store.on('update',$changeRecord,this);
		store.on('remove',$changeRecord,this);
	}
	return detailPanel;
};

/**
 * 子表单中 render 附件控件
 * @param value
 * @param metaData
 * @param record
 * @param rowIndex
 * @param colIndex
 * @param store
 * @returns {String}
 */
function $renderAttach(value, metaData, record, rowIndex, colIndex, store){
	var unedit=this.uneditable;
	var str='<span>';
	if(value){
		var files=value.split(',');
		if(files){
			for(var f=0;f<files.length;f++){
				var dd=files[f].split('|');
				var fileId=dd[0];
				var fileName=dd[1];
				str+= '<a href="#" onclick="FileAttachDetail.show('
								+ fileId
								+ ')">'
								+ fileName
								+ '</a>';
				if(!unedit){
					str+='<img class="img-delete" src="'
										+ __ctxPath
										+ '/images/system/delete.gif" title="删除附件" qtip="删除附件" onclick="FileAttachDetail.delFile(\''+this.gridName+'\',\''+this.dataIndex+'\','+fileId+',\''+fileName+'\')"/>'+
									'&nbsp;&nbsp;';		
				}
				str+='<br/>';
			}
		}
	}
   	if(!unedit){
   		str+='<a href="#" onclick="FileAttachDetail.upload(\''+this.gridName+'\',\''+this.dataIndex+'\')">添加</a></span>';
   	}
   	return str;
};

/**
 * 删除子表单数据
 * @param cmp
 */
function $detailDel(cmp){
	var detailPanel=this.detailGrids.get(cmp.gridName);
	var gridName=cmp.gridName;
	var taskId=this.taskId;
	Ext.Msg.confirm('信息确认', '您确认要删除所选记录吗？', function(btn) {
		if (btn == 'yes') {
			var tableId=null;
			if(taskId){
				tableId=document.getElementById(gridName+'_'+taskId).value;
			}
			var store=detailPanel.getStore();
			var selRs =detailPanel.getSelectionModel().getSelections();
			var ids=[];
			var delRecords=[];
			var pkKey=document.getElementById('WF_'+gridName+'_'+taskId);
			var pkKeyVar;
			if(pkKey){
			    pkKeyVar=pkKey.value;
			}
			for(var i=0;i<selRs.length;i++){
				if(selRs[i].data!=null){
					if(pkKeyVar){
						var detailId=selRs[i].data[pkKeyVar];
						if(detailId){
						   ids.push(detailId);
						}
					}
					delRecords.push(selRs[i]);
				}
			}
			if(ids.length){
				Ext.Ajax.request({
					url :__ctxPath+'/flow/delItemsProcessActivity.do',
					params : {tableId:tableId,ids : ids},method : 'POST',
					success : function(response,options) {
						Ext.ux.Toast.msg('操作信息','成功删除该记录！');
							store.remove(delRecords);
						
					},
					failure : function(response,options) {
						Ext.ux.Toast.msg('操作信息','操作出错，请联系管理员！');
					}
				});
			}else{
			   store.remove(delRecords);
			}
		}
	});
}

function $changeRecord(store,record){
	return record.fields.find(function(f){
		if(f.changefunction!==undefined){
			var funName=f.changefunction;
			var fun = eval(funName);
		    if(typeof fun=='function'){
		    	fun.call(this,store,record,record.data[f.name],f.name,f.header,this.activityName);
		    	return true;
		    }
		}
	},this);
}

/**
 *	子表单列控件管理 
 * @param column
 * @param row
 * @returns
 */
function $converCmpInColumn(column,row){
	var cmp=null;
	if(!column||column.uneditable==true){
	   return cmp;
	}
	var sel=this;
	var xtype=column.dxtype;
	switch(xtype){
	   case 'numberfield':
	         var format=column.format;
	         var de=2;
	         var minValue=0.01;
	         var maxValue=9999999999999.999999;
	         if(format){
	            de=format.lastIndexOf('.');
	            if(de!=-1&&de<format.length){
	               de=format.length-de-1;
	            }else if(de==-1){
	               de=0;
	               minValue=0;
	               format.length;
	            }
	         }
	         cmp=new Ext.form.NumberField({
	        	 sortable : false,
	         	 allowBlank:column.isNotNull==true?false:true,
                 decimalPrecision:de,
                 minValue:minValue,
                 maxValue:maxValue
	         });
	         break;
	   case 'datefield':
		      if(column.format=='Y-m-d'){
		          cmp=new Ext.form.DateField({
		        	sortable : false,
					format :'Y-m-d',
					value:column.iscurrent?new Date():null,
					allowBlank:column.isNotNull==true?false:true
				  });
		      }else{
		      	  cmp=new Cls.form.DateTimeField({
		      		sortable : false,
					format :'Y-m-d H:i:s',
					value:column.iscurrent?new Date():null,
					allowBlank:column.isNotNull==true?false:true
				  });
		      }
		     break;
	   case 'userselector':
	       cmp = new Ext.form.TriggerField({
				triggerClass : 'x-form-browse-trigger',
				gridName:column.gridName,
				isSingle:column.isSingle,
				dataIndexName:column.dataIndex,
				editable:false,
				sortable : false,
				value:column.iscurrent?App.auth.fullName:'',
				allowBlank:column.isNotNull==true?false:true,
				onTriggerClick : function(e) {
					var grid=sel.detailGrids.get(this.gridName);
					var modifyName=this.dataIndexName;
						new UserDialog({
	        	    	   scope:this,
	        	    	   single:this.isSingle,
		                   callback:function(id,name){
							var store = grid.getStore();
							var record = store.getAt(row);
							record.set(modifyName, name);
		                   }
	                }).show();
					grid.stopEditing();
				}
		   });
	       break;
	   case 'depselector':
	       cmp= new Ext.form.TriggerField({
				triggerClass : 'x-form-browse-trigger',
				gridName:column.gridName,
				isSingle:column.isSingle,
				dataIndexName:column.dataIndex,
				editable:false,
				sortable : false,
				allowBlank:column.isNotNull==true?false:true,
				onTriggerClick : function(e) {
					var grid=sel.detailGrids.get(this.gridName);
					var modifyName=this.dataIndexName;
					DepSelector.getView(function(id, name){
						   var store = grid.getStore();
						   var record = store.getAt(row);
						   record.set(modifyName, name);
	    	   		},this.isSingle).show();
					grid.stopEditing();
				}
		   });
	       break;
	   case 'posselector':
	       cmp= new Ext.form.TriggerField({
				triggerClass : 'x-form-browse-trigger',
				gridName:column.gridName,
				isSingle:column.isSingle,
				dataIndexName:column.dataIndex,
				editable:false,
				sortable : false,
				allowBlank:column.isNotNull==true?false:true,
				onTriggerClick : function(e) {
					var grid=sel.detailGrids.get(this.gridName);
					var modifyName=this.dataIndexName;
					
        			new PositionDialog({
        				scope:this,
        				single:this.isSingle,
        				callback:function(ids,names){
 						   var store = grid.getStore();
						   var record = store.getAt(row);
						   record.set(modifyName, names);
        				}
        			}).show();
        			
					grid.stopEditing();
				}
		   });
	       break;
	   case 'comboselect':
		   cmp = new Ext.form.ComboBox({
				mode : 'local',
				triggerAction : 'all',
				gridName:column.gridName,
				dataIndexName:column.dataIndex,
				editable:false,
				sortable : false,
				allowBlank:column.isNotNull==true?false:true,
				store:new Ext.data.SimpleStore({ //通过字典表获得用户使用状态数据源
			          fields: ['code', 'value'],
			          data:column.datas //这里对应我在字典表里定义的类型名称
			     }),
				displayField : 'value',
				valueField: 'value'
			});
	       break;
	   case 'diccombo':
			cmp=new DicCombo({
				dataIndexName:column.dataIndex,
		        gridName:column.gridName,
		        readOnly:column.readable,
				proTypeId:column.proTypeId,
				width : column.width*0.9,
				sortable : false,
				allowBlank:column.isNotNull==1&&!column.readable?false:true,
				isDisplayItemName:true,
				returnName:column.returnName
			});
			break;
	   case 'fileattach':
	        break;
	   default:
	      	cmp=new Ext.form.TextField({allowBlank:column.isNotNull==true?false:true,sortable : false});
	        break;
	}
     return cmp;
}

function $converForm(conf,readOnly){
	   var html=conf.innerhtml;
       var parent=conf.parentNode;
       var gridName=conf.gridName;
       var rightJson=conf.rightJson;
       
       var div_1=document.createElement('div');
       div_1.setAttribute('style','border:1px solid #C1DAD7;');
       parent.appendChild(div_1);
       var datas=conf.jsonDatas;
       var pkName=conf.pkName;
       var flag=true;
       ///**
       if(datas&&pkName){
	       for(var i=0;i<datas.length;i++){
		       //method start
		       var div_1_1=document.createElement('div');
		       div_1_1.setAttribute('style','border:1px solid #99BBE8;margin:3px;');
		       div_1.appendChild(div_1_1);
			   div_1_1.setAttribute('class','tipDiv');
			   var form2=document.createElement('form');
			   form2.setAttribute('belongName',gridName);
			   form2.setAttribute('pkName',pkName);
			   form2.setAttribute('pkValue',datas[i][pkName]);
			   if(!readOnly){
			   	  div_1_1.appendChild($addDelButton(div_1,div_1_1,gridName,form2,this.taskId,datas[i][pkName],conf.delformfunction));
			   }
			   
			   form2.innerHTML=html;
			   try{
				   var r=$converCmp.call(this,form2.elements,datas[i],rightJson,true,readOnly);
				   if(r=='un'){
				      flag=false;
				   }
			   }catch(e){alert(e);}

			   div_1_1.appendChild(form2);
			   
//			   div_1_1.onmouseover=function(){
//			      div_1_1.setAttribute('style','border:1px solid #B5B8C8;margin:3px;');
//			      div_1_1.setAttribute('class','x-grid3-row-over');
//			      div_1_1.firstChild.setAttribute('style','display:block;position:absolute;right:-5px;height:20px;');
//			   };
//			   div_1_1.onmouseout=function(){
//			      div_1_1.setAttribute('style','border:1px solid #99BBE8;margin:3px;');
//			      div_1_1.setAttribute('class','');
//			      div_1_1.firstChild.setAttribute('style','display:none;position:absolute;right:-5px;height:20px;');
//			   };

			   //method end
	       }
       }else{
           var div_1_1=document.createElement('div');
           div_1_1.setAttribute('style','border:1px solid #99BBE8;margin:3px;');
		   var form2=document.createElement('form');
		   form2.setAttribute('belongName',gridName);
		   
		   if(!readOnly){
		     div_1_1.appendChild($addDelButton(div_1,div_1_1,gridName,form2,this.taskId,null,conf.delformfunction));
		   }

		   form2.innerHTML=html;

		   try{
			   var r=$converCmp.call(this,form2.elements,null,rightJson,true,readOnly);
			   if(r=='un'){//权限处理的
			      flag=false;
			   }
		   }catch(e){}
		   
           div_1_1.appendChild(form2);
	       div_1.appendChild(div_1_1);
		   
//		   div_1_1.onmouseover=function(){
//		      div_1_1.setAttribute('style','border:1px solid #B5B8C8;margin:3px;');
//		      div_1_1.setAttribute('class','x-grid3-row-over');
//		      div_1_1.firstChild.setAttribute('style','display:block;position:absolute;right:-5px;height:20px;');
//		   };
//		   div_1_1.onmouseout=function(){
//		      div_1_1.setAttribute('style','border:1px solid #99BBE8;margin:3px;');
//		      div_1_1.setAttribute('class','');
//		      div_1_1.firstChild.setAttribute('style','display:none;position:absolute;right:-5px;height:20px;');
//		   };
       }
       //**/
       if(flag&&!readOnly){//对从表有所写权限
		   var addButtonDiv=document.createElement('div');
		   div_1.appendChild(addButtonDiv);
		   var button=new Ext.Button({
		       renderTo:addButtonDiv,
		       text:'添加',
		       tableHtml:html,
		       gridName:gridName,
		       addButtonDiv:addButtonDiv,
		       parentNode:div_1,
		       rightJson:rightJson,
	           iconCls:'btn-add',
	           delformfunction:conf.delformfunction,
	           scope:this,
	           handler:function(bt){
	           	   var div_2=document.createElement('div');
	           	   div_2.setAttribute('style','border:1px solid #99BBE8;margin:3px;');
	           	   bt.parentNode.insertBefore(div_2,bt.addButtonDiv);
	           	   
	               var form_2=document.createElement('form');
	               form_2.setAttribute('belongName',gridName);
	               form_2.innerHTML=html;
			       
				   $converCmp.call(this,form_2.elements,null,bt.rightJson,true);
				   
	               div_2.appendChild($addDelButton(bt.parentNode,div_2,gridName,form_2,null,null,bt.delformfunction));
			       div_2.appendChild(form_2);
				   
//	               div_2.onmouseover=function(){
//				      div_2.setAttribute('style','border:1px solid #B5B8C8;margin:3px;');
//				      div_2.setAttribute('class','x-grid3-row-over');
//				      div_2.firstChild.setAttribute('style','display:block;position:absolute;right:-5px;height:20px;');
//				   };
//				   div_2.onmouseout=function(){
//				      div_2.setAttribute('style','border:1px solid #99BBE8;margin:3px;');
//				      div_2.setAttribute('class','');
//				      div_2.firstChild.setAttribute('style','display:none;position:absolute;right:-5px;height:20px;');
//				   };
	           }
		   });
       }
};

function $converFormDetail(formobjs,readOnly){
	   for(var i=0;i<formobjs.length;i++){
           $converForm.call(this,formobjs[i],readOnly);
	   }
};

function $addDelButton(container,tartget,gridName,form,taskId,pkValue,delfunction){
       var span=document.createElement('b');
//       span.setAttribute('style','display:none;position:absolute;right:-5px;height:20px;');
	   //del button start
	   var vv=document.createElement('div');
	   vv.setAttribute('class','x-btn-text btn-del');
	   vv.setAttribute('style','float:right;height:20px;width:20px;right:-20px;');
	   vv.qtip='删除';
	   vv.owerDiv=container;
	   vv.removeDiv=tartget;
	   vv.gridName=gridName;
	   vv.taskId=taskId;
	   vv.targetForm=form;
	   vv.pkValue=pkValue;
	   vv.delfunction=delfunction;
	   vv.onclick=function(){
	      try{
			Ext.Msg.confirm('信息确认', '您确认要删除所选记录吗？', function(btn) {
					if (btn == 'yes') {
						if(this.pkValue){
							var tableId=null;
							if(taskId){
								tableId=document.getElementById(this.gridName+'_'+this.taskId).value;
							}
							Ext.Ajax.request({
								url :__ctxPath+'/flow/delItemsProcessActivity.do',
								params : {tableId:tableId,ids : this.pkValue},method : 'POST',
								scope:this,
								success : function(response,options) {
									Ext.ux.Toast.msg('操作信息','成功删除该记录！');
									var baseParam2 = Ext.Ajax.serializeForm(this.targetForm);
             						var deParams = Ext.urlDecode(baseParam2);//取得了从表里面的数据
             						var recSum=this.owerDiv.childNodes.length-1;//记录数
             						if(this.delfunction)
             							this.delfunction.call(this,deParams,recSum);
             						
									this.owerDiv.removeChild(this.removeDiv);
								},
								failure : function(response,options) {
									Ext.ux.Toast.msg('操作信息','操作出错，请联系管理员！');
								}
							});
						}else{
						   var baseParam2 = Ext.Ajax.serializeForm(this.targetForm);
             			   var deParams = Ext.urlDecode(baseParam2);//取得了从表里面的数据
             			   var recSum=this.owerDiv.childNodes.length-1;//记录数
    						if(this.delfunction)
     							this.delfunction.call(this,deParams,recSum);
             			   
						   this.owerDiv.removeChild(this.removeDiv);
						}
					}
			},this);
	         
	      }catch(e){alert(e);}
	   };
	   span.appendChild(vv); 
       return span;
};


function $validField(element){
	       if(element.style.display=='none'){//when the element's display of style is none,cancel vailid this element
	          return true;
	       }
           var isNotNull=element.getAttribute('txtisnotnull');
       	   var xtype=element.getAttribute('xtype');
       	   var txtsize=element.getAttribute('txtsize');
       	   var dataformat=element.getAttribute('dataformat');
           var pass=true;
           var msg;
           if(isNotNull==1){
           	  if(element.value==''){
           	     msg='此选项为必填项'; 
           	     pass=false;
           	  }
           }
           if(pass&&txtsize&&element.value.toString().length>txtsize){
              msg='此项内容不得超过'+txtsize;
           	  pass=false;
           }
           if(false&&pass){
           	  var value=element.value;
           	  if(value!=''){
	              var email = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	              pass=email.test(value);
	              msg='此项内容为邮件格式：XXX@XX.com';
           	  }
           }
           if(dataformat&&pass){
           	  var value=element.value;
           	  if(value!=''){
           	  	  var reg=new RegExp(dataformat);
	              pass=reg.test(value);
	              msg='此项内容的格式不正确';
           	  }
           }
           if(xtype=='numberfield'&&pass){
           	  var value=element.value;
           	  var tvt=element.getAttribute('txtvaluetype');
           	  if(value!=''){
           	  	  if(tvt=='int'||tvt=='bigint'||tvt=='smallint'){
		              var intReg = /^[-\+]?\d+$/;
		              pass=intReg.test(value);
		              msg='此项内容应为整数';
           	  	  }else{
           	  	      var fReg=/^-?\d+\.?\d*$/;
           	  	      pass=fReg.test(value);
           	  	      msg='此项内容应为数字';
           	  	  }
           	  }
           }
           
           
           var invalidClass=' x-form-invalid';
		   var oldClass=element.getAttribute('class');
           if(!pass){
                 if(oldClass){
	                 if(oldClass.indexOf(invalidClass)==-1){
			   	  	    oldClass=oldClass+invalidClass;
			   	  	 }
                 }else{
                     oldClass=invalidClass;
                 }
		   	     element.setAttribute('class',oldClass);
		         element.qtip=msg;
		         element.qclass = 'x-form-invalid-tip';
		   	     return false;
           }else{
           	    if(oldClass){
	                 element.setAttribute('class',oldClass.replace(invalidClass,''));
			     }
                 element.qtip='';
		         element.qclass = '';
		         return true;
           }
};

function $validForm(){
       var form=this.formPanel.getForm().getEl().dom;
       var fElements = form.elements || (document.forms[form] || Ext.getDom(form)).elements;
       var isValid=true;
       Ext.each(fElements, function(element,index) {
       	   isValid=isValid&&$validField.call(this,element);
       });
       /**
        * 对子表单进行验证
        */
       var forms=form.getElementsByTagName('form');
       for(var i=0;i<forms.length;i++){
          var  f=forms[i];
          var  els=f.elements;
          Ext.each(els, function(element,index) {
       	   isValid=isValid&&$validField.call(this,element);
          });
       }
       validForm={isValid:true,messge:'',el:null};
       if(typeof checkElementFunction=='function'){
       	  var obj=checkElementFunction(this.activityName);
       	  if(typeof obj=='object'){
       	     validForm=obj;
       	  }else if(typeof obj=='boolean'){
       	     if(!obj){
       	       Ext.ux.Toast.msg('表单验证信息','有信息未填写');
       	       return false;
       	     }
       	  }else if(typeof obj=='string'){
       	     if(obj){
       	       Ext.ux.Toast.msg('表单验证信息',obj);
       	       return false;
       	     }
       	  }
       	  if(!validForm.isValid){
	       	  if(validForm.els){
	       	  	 var element=validForm.els[0];
	   	      	 var invalidClass=' x-form-invalid';
	   	      	 if(element){
			   		 var oldClass=element.getAttribute('class');
			         if(oldClass){
			             if(oldClass.indexOf(invalidClass)==-1){
				   	  	    oldClass=oldClass+invalidClass;
				   	  	 }
			         }else{
			             oldClass=invalidClass;
			         }
			   	     element.setAttribute('class',oldClass);
			         element.qtip=validForm.messge?validForm.message:validForm.errorMsg;
			         element.qclass = 'x-form-invalid-tip';
	   	      	 }
	   	         var msg=validForm.messge?validForm.message:validForm.errorMsg;
		         Ext.ux.Toast.msg('表单验证信息',msg?msg:'验证不通过！');
	       	  }
	          return false;
	       }
       }
       var vv=false;
       if(this.detailGrids){//适用于多个GRID的 验证列表
		   var grids=this.detailGrids.keys;
           for(var j=0;j<grids.length;j++){
				var detailPanel=this.detailGrids.get(grids[j]);
			    var store=detailPanel.getStore();

//				if(store.getCount()==0){
//					
//					var field=$isValidField.call(this,store,null,this.activityName);
//					if(field){
//					   Ext.ux.Toast.msg('表单验证信息',field.errorMsg?field.errorMsg:'列表验证出错');
//					}else{
//						Ext.ux.Toast.msg('表单验证信息','子表必须填写数据');
//					}
//				   vv=true;
//				   break;
//				
//				}
			    
				for(var i=0;i<store.getCount();i++){
					var record=store.getAt(i);
					var field=$isValidField.call(this,store,record,this.activityName);
					if(field){
					   detailPanel.getSelectionModel().selectRecords([record]);//select row
					   Ext.ux.Toast.msg('表单验证信息',field.errorMsg?field.errorMsg:'列表验证出错');
					   vv=true;
					   break;
					}
				}

				if(vv){
				   break;
				}
			}
			
       }
       if(vv){
          isValid=false;
       }
       if(isValid){
          return true;
       }else{
          return false;
       }
};

//验证grid的值
function $isValidField(store,record,activityName){
	  if(record!=null){
		  var field= record.fields.find(function(f){
		      if(f.allowBlank === false && Ext.isEmpty(record.data[f.name])){
		      	 f['errorMsg']=f.header+'不能为空';
		         return f;
		      }else 
		      if(typeof f.callbackfunction=='function'){
		         var c= f.callbackfunction.call(this,store,record,record.data[f.name],f.name,f.header,activityName);
		         if(c&&(c.passed==false)){
		            f.errorMsg=c.errorMsg;
		            return f;
		         }
		      }
		  },this);
	  }else{
	      var field= store.fields.find(function(f){
		      if(typeof f.callbackfunction=='function'){
		         var c= f.callbackfunction.call(this,store,null,null,f.name,f.header,activityName);
		         if(c&&(c.passed==false)){
		            f.errorMsg=c.errorMsg;
		            return f;
		         }
		      }
		  },this);
	  }
	  return field;
}

function $checkDeleValid(dom,baseParams){	
       var fElements = dom.elements || (document.forms[dom] || Ext.getDom(dom)).elements;
       var deleteName=new Array();
       Ext.each(fElements, function(element,index) {
       	   if(element.type=='radio'||element.type=='checkbox'){
       	   	     var nodeName=element.getAttribute('name');
       	   	     if(!element.checked && element.hasAttribute('isSelect')){
       	   	     	deleteName[nodeName]=1;      	   	     	
       	   	     }else if(element.checked && deleteName[nodeName]){
       	   	     	deleteName[nodeName]=0;
       	   	     }      	   	  	  
       	   }

       });
       for(var key in deleteName){
       	 if(deleteName[key]==1){
       	 	baseParams[key]='null';
       	 }
       }
}

function setHTInterval(fn,timer){
	try{clearInterval(interval);}catch(e){}
	interval=window.setInterval(fn,timer);
}
