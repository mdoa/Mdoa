$(function(){
	
	if(existPermission('admin:personnel:pactManage:pact:add'))
		$('#pact .maintop').append('<div><span class="maintopicon mainicon2"></span> <span class="add">添加</span></div>');
	$('#pactdg').datagrid({
	   //url:'../../personnel/selectPackByTime.do?getMs='+getMS(),
	   rownumbers:"true",
	   singleSelect:true,
	   pagination:true,
	   toolbar:"#pact .invitetop",
	   method:"post",
	   fit: true, 
	   columns:[[
	      // {field:"ck",checkbox:true },
	       {field:"userName",title:"用户名",fitColumns:true,resizable:true,align:"center",sortable:true,width:70},
	       {field:"strStarTime",title:"合同开始时间",fitColumns:true,resizable:true,align:"center",sortable:true,width:100},
	       {field:"strEndTime",title:"合同终止时间",resizable:true,align:"center",sortable:true,width:100,formatter:function(value,row,index){
	    	   var id=row.strEndTime;
	    	   var time=id.split("-");
	    	   var newDate=new Date();
	    	   var month = newDate.getMonth() + 1;
	    	   var year = newDate.getFullYear();
	    	   if(time[0] - year == 0){
	    		   if(time[1] - month <= 1){
	    			   return '<span style="color:red">'+id+'</span>';
	    		   }
	    	   }else{
	    		   return '<span>'+id+'</span>'; 
	    	   }  	   
	       }},
	       {field:"strTryStarTime",title:"试用期开始时间",fitColumns:true,resizable:true,align:"center",sortable:true,width:100},
	       {field:"strTryEndTime",title:"试用期结束时间",fitColumns:true,resizable:true,align:"center",sortable:true,width:100},
	       {field:"packFlag",title:"合同类型",resizable:true,align:"center",sortable:true,width:100,formatter:function(value,row,index){
	    	   if(value == 0){
	    		   return '初签';
	    	   }if(value == 1){
	    		   return '续签';
	    	   }if(value == 2){
	    		   return '返聘';
	    	   }
	       }},
	       {field:"_op",title:"管理",width:100,resizable:true,align:"center",sortable:true,formatter:function(value,row,index){
	    	   var id="'"+row.packId+"'";
	    	   var opera = '';
	    	   if(existPermission('admin:personnel:pactManage:pact:update'))
	    			opera +='<span class="small-button edit" title="修改合同信息" onclick="pactUpdata('+id+')"></span>';
	    	   if(existPermission('admin:personnel:pactManage:pact:update'))
	    		   opera +='<span class="small-button addbtn" title="添加合同图片" onclick="uploadPhoto('+id+')"></span>';
	    	   if(existPermission('admin:personnel:pactManage:pact:select'))
	    		   opera +='<span class="small-button look" title="查看" onclick="lookPact('+id+')"></span>';
	    	   if(existPermission('admin:personnel:pactManage:pact:delete'))
	    		   opera +='<span class="small-button delete" title="删除" onclick="pactDelete('+id+')"></span>';
	    	   return opera;
	       }},
	    ]]
	}); 
/**=============添加合同信息=======================*/
	$('#pact .maintop .add').click(function(){
		$('#pact .savePact').css("display","block");
	})
	$('#pact .popups .savePact .chooseUser').click(function(){
		chooseUser();
		 $('#chooseUser .confirm').click(function(){
	    	$('#chooseUser').css('display','none');
	    	selectUser = $('#chooseUser .popuparea .user').datagrid('getSelections');
	    	$('#pact .popups .savePact input[name=peopleName]').val(selectUser[0].userName);
	    	$('#pact .popups .savePact input[name=userId]').val(selectUser[0].userId);
	    })
	})
	$('#pact .savePact .confirm').click(function(){
		//获取输入框的内容
		var packFlag = $('#pact .savePact input[name=pactType]:checked').val();
		var userName = $('#pact .savePact input[name=peopleName]').val();
		var userId = $('#pact .savePact input[name=userId]').val();
		var startTime = $('#pact .savePact input[name=startTime]').val();
		var endTime = $('#pact .savePact input[name=endTime]').val();
		var tryStarTime = $('#pact .savePact input[name=tryStarTime]').val();
		var tryEndTime = $('#pact .savePact input[name=tryEndTime]').val();
		if(userName == null || userName == ''){
			windowControl('用户名不能为空');
			return ;
		}else if(packFlag == null || packFlag == ''){
			windowControl('合同类型不能为空');
			return ;
		}else if(startTime == null || startTime == ''){
			windowControl('合同开始时间不能为空');
			return ;
		}else if(endTime == null || endTime == ''){
			windowControl('合同结束不能为空');
			return ;
		}else{
			$.ajax({
				data:{
					userName:userName,
					packFlag:packFlag,
					userId:userId,
					strStarTime:startTime,
					strEndTime:endTime,
					strTryStarTime:tryStarTime,
					strTryEndTime:tryEndTime
					},
				type:"post",
				url:"../../personnel/insertPack?getMs="+getMS(),
				success:function(data){
					if(data == 200){
						$('#pact .savePact').css('display','none');
						$('#pact .savePact .popuparea input').val(null);
						$('#pact .savePact .popuparea text').val(null);
						$('#pact #pactdg').datagrid('reload');
						windowControl('添加合同信息成功');
					}else{
						windowControl('添加合同信息失败');
					}
					
				},
				error:function(){
			    	windowControl("请求失败!");
			    }
			})	
		}
	})	
});
function pactUpdata(id){
	$('#pact .updatePact').css('display','block');
	$.ajax({
		data:{packId:id,},
		url:'../../personnel/selectPackByTime.do?getMs='+getMS(),
		type:'post',
		success:function(data){
			var data = eval('(' + data + ')').rows[0];
			$('#pact .updatePact input[name=peopleName]').val(data.userName);
			$('#pact .updatePact input[name=startTime]').val(data.strStarTime);
			$('#pact .updatePact input[name=endTime]').val(data.strEndTime);
			$('#pact .updatePact input[name=tryStarTime]').val(data.strTryStarTime);
			$('#pact .updatePact input[name=tryEndTime]').val(data.strTryEndTime);
			//点击修改
			$('#pact .updatePact .confirm').click(function(){
				//获取输入框的内容
				var userName = $('#pact .updatePact input[name=peopleName]').val();
				var startTime = $('#pact .updatePact input[name=startTime]').val();
				var endTime = $('#pact .updatePact input[name=endTime]').val();
				var tryStarTime = $('#pact .updatePact input[name=tryStarTime]').val();
				var tryEndTime = $('#pact .updatePact input[name=tryEndTime]').val();
				if(userName == null || userName == ''){
					windowControl('用户名不能为空');
					return ;
				}else if(startTime == null || startTime == ''){
					windowControl('合同开始时间不能为空');
					return ;
				}else if(endTime == null || endTime == ''){
					windowControl('合同结束不能为空');
					return ;
				}else if(tryStarTime == null || tryStarTime == ''){
					windowControl('试用期开始时间不能为空');
					return ;
				}else if(tryEndTime == null || tryEndTime == ''){
					windowControl('试用期结束时间不能为空');
					return ;
				}else{
					$.ajax({
						data:{
							packId:id,
							userName:userName,
							strStarTime:startTime,
							strEndTime:endTime,
							strTryStarTime:tryStarTime,
							strTryEndTime:tryEndTime
							},
						type:"post",
						url:"../../personnel/updatePack?getMs="+getMS(),
						success:function(data){
							if(data == 200){
								$('#pact .updatePact').css('display','none');
								$('#pact .updatePact .popuparea input').val(null);
								$('#pact .updatePact .popuparea text').val(null);
								$('#pact #pactdg').datagrid('reload');
								windowControl('修改合同信息成功');
							}else{
								windowControl('修改合同信息失败');
							}
						},
						error:function(){
					    	windowControl("请求失败!");
					    }
					})
				}		
			})	
		},
		error:function(err){
			windowControl(err.status);
		}
	});
}
/***--------------添加合同图片弹窗关闭---------------****/
var fileHtml = $('#pact .popups .imgTable').html();
$('#pact .popups .saveAllPhoto .turnoff').click(function(){
	$('#pact .popups .imgTable').html(fileHtml);
	$('#pact .popups .saveAllPhoto').css('display','none');
});
$('#pact .popups .saveAllPhoto .cannel').click(function(){
	$('#pact .popups .imgTable').html(fileHtml);
	$('#pact .popups .saveAllPhoto').css('display','none');
});
/**=======================合同的搜索事件========================*/
$(function(){
	$('#pact .query').click(function(){
		var userName = $('#pact .invitetop .userName').val();
		$('#pact #pactdg').datagrid({
			url:'../../personnel/selectPackByTime.do?getMs='+getMS(),
			queryParams:{
				userName:userName,
			},
		})
	});
	$('#pact .clean').click(function(){
		$('#pact .invitetop .userName').val(null);
	});
})
/**********************预览图片的方法****************************/
function preview(file){
	var prevDiv = $(file).parent().parent().prev().find('.preview');  
	var reader = new FileReader();
	reader.onload = function(evt){  
		prevDiv.html('<img src="' + evt.target.result + '" style="width:100%;height:100%" />');
	}    
	reader.readAsDataURL(file.files[0]);  
}
/***-------------------删除预览图片--------------------------***/
function deletePhoto(ele){
	var dom = $(ele).parent().parent();
	var domPrev = dom.prev();
	if(domPrev.find('.preview img')){
		ui.confirm('确定要删除该图片吗？',function(z){
			if(z){
				domPrev.remove();
				dom.remove();
				var imgLen = $('#pact .saveAllPhoto .imgTable tr:nth-child(2n)').children("td:first-child");
				for(var i=0; i<imgLen.length;i++){
					imgLen.eq(i).text('第'+(i+1)+'页:'); 
				}
			}
		},false);
	}else{
		windowControl('没有图片可以删除');
	}
}
/*********************批量上传图片*****************************/
function uploadPhoto(id){
	$('#pact .saveAllPhoto').css('display','block');
	//var photoNum = 1;//记录图片选择框个数
	//点击添加图片
	$('#pact .saveAllPhoto .addPhoto').unbind('click');
	$('#pact .saveAllPhoto .addPhoto').click(function(){
		var addPhotoNum = $('#pact .saveAllPhoto .tdClass').length + 1;
		var str=""; 
		str += "<tr><td colspan='3'><div class='preview'></div></td></tr><tr><td>第"
			+addPhotoNum+"页:</td><td class='tdClass' style='text-align:left;'><input size='60' class='cleanPhoto' type='file' onchange='preview(this)'/></td>"
			+"<td><span class='deletePhoto' onclick='deletePhoto(this)'>删除此图片</span></td><td><span class='showImage'></span></td></tr>"; 
		$("#pact .saveAllPhoto .imgTable").append(str);
	});
	$('#pact .saveAllPhoto .confirm').unbind('click');
	$('#pact .saveAllPhoto .confirm').click(function(){
		//判断上传的文件是否是图片格式
		var filepath = $('.tdClass input[type=file]').val();
		var extStart = filepath.lastIndexOf(".");
		var ext = filepath.substring(extStart, filepath.length).toUpperCase();
		if (ext!=".BMP"&&ext!=".PNG"&&ext!=".GIF"&&ext!=".JPG"&&ext!=".JPEG"){
			//提示信息
			windowControl('上传的文件中有非图片格式的文件');
		}else{
			ajaxFileUpload(photoNum,$('.tdClass input[type=file]'),id);
		}
	});	
}
/****************************递归上传图片*******************************/
function ajaxFileUpload(photoNum,photo,id){
	console.log(photo);
	for(var i = 0;i<photo.length;i++){
		photo.eq(i).upload({
			url:"../../personnel/uploadImage?getMs="+getMS(),
			params: {packId:id,},
			onComplate: function (data) {
				if(data ==200){
					$('#pact .saveAllPhoto .showImage').html('<img src=../../img/index/ok.png />');
				}else{
					$('#pact .saveAllPhoto .showImage').html('<img src=../../img/index/turnoff.png />');
				}				
			}
		});
		photo.eq(i).upload("ajaxSubmit");	
	}	
}
/****************************上传图片**********************************/
/*function ajaxFileUpload(photonNum,photo,id){
		$("#uploadImgState"+num).html("<img src=../images/loading.gif />");
		photo.upload({
			url:"../../personnel/uploadImage?getMs="+getMS(),
			params: {packId:id,currentPage:photonNum},
			onComplate: function (data) {
				$('#pact .popup:eq(0)').css('display','none');
				$('#pact .popup:eq(0) input').not('.button').val(null);
				$('#pact .popup:eq(0) textarea').val(null);
				$('#pact #pactdg').datagrid('reload');
				windowControl('上传成功');
			}
		});
		photo.upload("ajaxSubmit")
}*/
/**==============当用户点击添加合同图片并上传图片===========================*/
/*function uploadPhoto(id){
	$('#pact .savePhoto').css('display','block');
	var look = document.getElementById('look');
	look.onclick = function(){
		var photoName = $("input[name=photoName]").val();
		var currentPage = $("input[name=currentPage]").val();
		$("input[name=file]").upload({
			url: "../../personnel/uploadImage?getMs="+getMS(),
			// 其他表单数据
			params: {packId:id,photoName:photoName,currentPage:currentPage},
			dataType: 'json',
			onComplate: function (data) {
				windowControl('上传成功');
				$('#pact .popup:eq(0)').css('display','none');
				$('#pact .popup:eq(0) input').not('.button').val(null);
				$('#pact .popup:eq(0) textarea').val(null);
				$('#pact #pactdg').datagrid('reload');
			}
		});
		$("input[name=file]").upload("ajaxSubmit")
	}
}*/
/***-----------------------查看合同照片----------------------------------*****/
function lookPact(id){
	$.get("../personnel/pactManage/lookPactPhoto.html",function(data){
		  includeLinkStyle('../../css/personnel/pactManage/lookPactPhoto.css');
		  $.getScript('../../js/personnel/pactManage/lookPactPhoto.js');
		  $('#loadarea').tabs('add',{
		        title:"查看合同图片信息",
			    content:data,
			    closable:true
		  });
			$.ajax({
				data:{packId:id},
				url:'../../personnel/selectPhotoByPackId.do?getMs='+getMS(),
				type:'post',
				success:function(data){
					var json = eval('('+data+')');
					var str = "";
					for(var o in json){
						var url = json[o].urlSmall;
						var bigUrl = json[o].url;
						var page = json[o].currentPage;
						var photoName = json[o].photoName;
						var photoId = "'"+json[o].photoId+"'";
						str += '<div class="photoImage">'
							+  '<tr><td><img src="../../personnel/downLoadImage.do?url='+url+'&photoName='+photoName+'"/></td></tr>'
							+  '<tr><td><a class="load" href="../../personnel/downLoadImage.do?url='+bigUrl+'&photoName='+photoName+'">下载</a></td></tr>'
							+  '<div><tr><td>第'+page+'次上传<td></tr></div>'
							+  '<div><tr><td><span class="deletePackPhoto" title="删除此照片" onclick="deletePackPhoto(this,'+photoId+')">删除照片</span></td></tr></div><hr/>'
							+  '</div>';
					}
					$('#lookPactPhotodg').append(str);
				},
				error:function(err){
					windowControl(err.status);
				}
			})
		 })
	   }
/**--------------------删除合同照片--------------***/
function deletePackPhoto(ele,photoId){
	$.ajax({
		data:{photoId:photoId},
		url:'../../personnel/deletePackPhoto.do?getMs='+getMS(),
		type:'post',
		success:function(data){
			if(data == '200'){
				$(ele).parents('.photoImage').remove();
				$("#pact #pactdg").datagrid("reload");
				windowControl("删除成功");
			}else{
				windowControl(data);
			}
		},
		error:function(err){
			windowControl(err.status);
		}
		
	})
}

/***-----------------------------删除合同信息------------------------------------***/
function pactDelete(id){
	ui.confirm('确定要删除该合同信息？',function(z){
		if(z){
			$.ajax({
				data:{packId:id},
				url:'../../personnel/deletePack.do?getMs='+getMS(),
				type:'post',
				success:function(data){
					if(data == '200'){
						$("#pact #pactdg").datagrid("reload");
						windowControl("删除成功");
					}else{
						windowControl(data);
					}
				},
				error:function(err){
					windowControl(err.status);
				}
			});
		}
	},false);
}