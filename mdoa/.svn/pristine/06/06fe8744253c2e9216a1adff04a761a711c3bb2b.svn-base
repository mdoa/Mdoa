/**================查询packPhoto的信息===============*/
$(function(){
	$('#pactPhoto .serBtn').click(function(){
		var createTime1=$('#pactPhoto .createTime').val();
		console.log(createTime1)
		$('#pactPhotodg').datagrid({
			url:'../../personnel/selectPackPhotoByTime.do?getMs='+getMS(),
			method:"post",
			queryParams:{
				createTime:createTime1,
			},
		})
	});

/**===============下载图片的点击事件===================*/
	$('#pactPhoto .downBtn').click(function(){
		$.ajax({
			data:{},
			url:'../../personnel/downLoadImage.do?getMs='+getMS(),
			type:'post',
			success:function(data){
				console.log(data);
			},
			error:function(err){
				windowControl(err.status);
			}
		})
		
	})
	
	$('#pactPhoto .close').click(function(){
		$('.createTime').attr('value','');
	});

	$('#pactPhotodg').parent().css('height',$('#loadarea').height()-$('.tabs-header').height()-$(".maintop").height()-$(".invitetop").height()-14);
})