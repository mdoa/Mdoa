$(function(){
	$('#tryPersondg').datagrid({
		 //  url:'../../personnel/selectTryUserById.do?getMs='+getMS(), 
		   rownumbers:"true",
		   singleSelect:true,
		   pagination:true,
		   method:"post",
		   toolbar:"#tryPerson .invitetop",
		   fit: true, 
		   columns:[[
		       //{field:"ck",checkbox:true },
		       {field:"userName",title:"用户名",fitColumns:true,resizable:true,align:"center",sortable:true,width:100},
		       {field:"userAccount",title:"账户",fitColumns:true,resizable:true,align:"center",sortable:true,width:80}, 
		       {field:"sex",title:"性别",fitColumns:true,resizable:true,align:"center",sortable:true,formatter:function(value,row,index){
		    	   var sex = row.sex;
		    	   if(sex == 1){
		    		   return '男';
		    	   }else{
		    		   return '女';
		    	   }
		       }},
		       {field:"idCard",title:"身份证号",fitColumns:true,resizable:true,align:"center",sortable:true,width:150}, 
		       {field:"phoneNum",title:"联系电话",fitColumns:true,resizable:true,align:"center",sortable:true,width:100}, 
		       {field:"accidentPhoneNum",title:"紧急联系电话",fitColumns:true,resizable:true,align:"center",sortable:true,width:100},  
		       {field:"address",title:"地址",fitColumns:true,resizable:true,align:"center",sortable:true,width:150}, 
		       {field:"departmentName",title:"部门名称",fitColumns:true,resizable:true,align:"center",sortable:true,width:70}, 
		       {field:"postName",title:"岗位名称",fitColumns:true,resizable:true,align:"center",sortable:true,width:70}, 
		       {field:"strTryStarTime",title:"试用开始时间",fitColumns:true,resizable:true,align:"center",sortable:true,width:100}, 
		       {field:"strTryEndTime",title:"试用结束时间",fitColumns:true,resizable:true,align:"center",sortable:true,width:100,formatter:function(value,row,index){
		    	   var id=row.strTryEndTime;
		    	   if(id == null){
		    		   return '';
		    	   }else{
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
		    	   }
		    	   
		       }},
		       {field:"_op",title:"管理",width:100,resizable:true,align:"center",sortable:true,width:100,formatter:function(value,row,index){
		    	   var id = "'"+row.userId+"'";
		    	   var opera =  '';  
		    	   if(existPermission('admin:personnel:pactManage:tryPerson:manage'))
		    			opera +='<span class="small-button edit" title="转正员工 " onclick="updateTryUser('+id+')"></span>';
		    	   return opera;
		       }},         
		]]
	});
});
/************************查询员工*****************************/
$('#tryPerson .invitetop .serBtn').click(function(){
	var userName = $('#tryPerson .invitetop .userName').val();
	var idCard =  $('#tryPerson .invitetop .idCard').val();
	var postName =  $('#tryPerson .invitetop .postName').val();
	var departmentName =  $('#tryPerson .invitetop .departmentName').val();
	$('#tryPersondg').datagrid({
		url:'../../personnel/selectTryUserById.do?getMs='+getMS(),
		method:"post",
		queryParams: {
			userName : userName,idCard:idCard,postName:postName,departmentName:departmentName,
		},
	})
});
/***************************清空按钮***************************/
$('#tryPerson .invitetop .close').click(function(){
	$('#tryPerson .invitetop .userName').val(null);
	$('#tryPerson .invitetop .idCard').val(null);
});
/************************转正员工*****************************/
function updateTryUser(id){
	ui.confirm('确定要录用该员工？',function(z){
		if(z){
			$.ajax({
				url:'../../personnel/updateTryUser.do?getMs='+getMS(), 
				data:{userId:id},
				type:'post',
				success:function(data){
					if(data == 200){
						$('#tryPersondg').datagrid('reload');
						windowControl("转正成功");
					}	
				},
				error:function(error){
					windowControl(error.status);
				}
			});		
		}
	},false);
}	