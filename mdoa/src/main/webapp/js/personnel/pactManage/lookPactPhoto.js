$(function(){
	$('#lookPactPhoto .serBtn').click(function(){
		var createTime1=$('#lookPactPhoto .createTime').val();
		console.log(createTime1)
		$('#lookPactPhotodg').datagrid({
			url:'../../personnel/selectPackPhotoByTime.do?getMs='+getMS(),
			method:"post",
			queryParams:{
				createTime:createTime1,
			},
		})
	});
	
	$('#lookPactPhoto .close').click(function(){
		$('.createTime').attr('value','');
	});
	
})
/*----------------------------------pact--------------------------------*/
function pactUpdata(ele,id){
  $.get("../personnel/pactManage/pactPhoto.html",function(data){
	  includeLinkStyle('../../css/personnel/pactManage/pactPhoto.css');
	  $.getScript('../../js/personnel/pactManage/pactPhoto.js');
	   $('#loadarea').tabs('add',{
	        title:"修改合同图片信息",
		    content:data,
		    closable:true
		});
		$.ajax({
			data:{},
			url:'../../personnel/updatePack.do?getMs='+getMS(),
			type:'post',
			success:function(data){
				var str = '';
				for (var i=0;i<=2;i++){
					str += '<div><img src="../../img/personnel/socialSecurity/banner_3.jpg"><button class="removeBtn" style="float:right;margin:5px 10px;">删除</button></div>';
				}
				$('#pactPhotodg').append(str);
				$('.removeBtn').click(function(){
					var that = $(this);
					$.ajax({
	                    data:{packId:id,photoId:id},
	                    url:'../../personnel/deletePackPhotoByPackId.do?getMs='+getMS(),
	                    type:'post',
	                    success:function(data){
		                     if(data){
		                    	 that.parent('div').remove();
		                    	 windowControl("删除成功");
		                     }else{
			                    windowControl(data);
		                     }
	                    },
	                    error:function(err){
		                 windowControl(err.status);
	                    }
                    })
				})
			},
			error:function(err){
				windowControl(err.status);
			}
		})
	 })
}