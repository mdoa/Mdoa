/***************************************************kpi组分配******************************************************************/
$(function(){
	$('#KPIgroupSetdg').datagrid({
		   //url:'../../userInfo/selectUserInfo.do?getMs='+getMS(),
		   rownumbers:"true",
		   singleSelect:true,
		   pagination:true,
		   toolbar:"#KPIgroupSet .invitetop",
		   method:"post",
		   fit: true, 
		   columns:[[
		  	     //  {field:"ck",checkbox:true },					       
		  	       {field:"userName",title:"员工名字",fitColumns:true,sortable:true,align:"center",sortable:true,width:100 },
		  	       {field:"userAccount",title:"个人账户",fitColumns:true,resizable:true,align:"center",sortable:true},
		  	       {field:"phoneNum",title:"电话号码",fitColumns:true,resizable:true,align:"center",sortable:true},
		  	       {field:"accidentPhoneNum",title:"紧急联系方式",fitColumns:true,resizable:true,align:"center",sortable:true},
		  	       {field:"birthdayStr",title:"出生日期",fitColumns:true,resizable:true,align:"center",sortable:true},
		  	       {field:"idCard",title:"身份证号码",fitColumns:true,resizable:true,align:"center",sortable:true},
		  	       {field:"sex",title:"性别",fitColumns:true,resizable:true,align:"center",sortable:true},
		  	       {field:"workTimeStr",title:"工作时间",fitColumns:true,resizable:true,align:"center",sortable:true,formatter:status},
		  	       {field:"address",title:"地址",fitColumns:true,resizable:true,align:"center",sortable:true,formatter:status},
		  	       {field:"kpiGroupId",title:"kpi组状态",fitColumns:true,resizable:true,align:"center",sortable:true,formatter:function(value,row,index){
		  	    	   if(value == null){
		  	    		   return '未分配'
		  	    	   }else{
		  	    		   return '已分配'
		  	    	   }
		  	       }},
		  	       {field:"_op",title:"管理",width:100,resizable:true,align:"center",sortable:true,formatter:function(value,row,index){
		  	    	var id = "'"+row.userId+"'";
		  	    	var opera = '';
		    		  if(existPermission('admin:personnel:performanceManage:KPIgroupSet:update'))
		    				opera +='<span class="small-button edit" title="分配kpi组" onclick="updateKpiGroup('+id+')"></span>';
		    		 return opera;
		  	    	//return '<span class="small-button edit" title="分配kpi组" onclick="updateKpiGroup('+id+')"></span>';
		  	      }},
		  	  ]]
	});	
})
/************************条件查询**********************************/
$('#KPIgroupSet .invitetop .query').click(function(){
		var userName = $('#KPIgroupSet .invitetop .userName').val();
		$('#KPIgroupSetdg').datagrid({
			url:'../../userInfo/selectUserInfo.do?getMs='+getMS(),
			method:"post",
			queryParams: {
				userName : userName,
			},
		})
	});
/***********************下拉框选择kpi组***************************/
$.ajax({
	url:'../../personnel/selectKpiGroupByTime.do?getMs='+getMS(),
	type:'post',
	success:function(data){
		var str = "<option style>--请选择--</option>";
		var data = eval('(' + data + ')'); 
		for(var i=0;i<data.rows.length;i++){
			str += "<option class='kpiGroupName' value=" + data.rows[i].kpiGroupId + ">" + data.rows[i].kpiGroupName + "</option>";  
		}
	$('#KPIgroupSet .saveGroupSet .kpiGroup').html(str);
	},
	error:function(error){
		windowControl(error.status);
	}
})
/***********************给员工分配kpi组***************************/
function updateKpiGroup(id){
	$('#KPIgroupSet .saveGroupSet').css('display','block');
	$('#KPIgroupSet .saveGroupSet .confirm').click(function(){
		var kpiGroupId = $('#KPIgroupSet .saveGroupSet .kpiGroup').val();
		if(kpiGroupId =='--请选择--'|| kpiGroupId == null){			
			windowControl("kpi组不能为空");
		}else{
			$.ajax({
				data:{userId:id,kpiGroupId:kpiGroupId},
				url:'../../personnel/updateUserKpiGroup.do?getMs='+getMS(),
				type:'post',
				success:function(data){
					if(data == 200){
						$('#KPIgroupSet .saveGroupSet').css('display', 'none');
						$('#KPIgroupSet .saveGroupSet .popuparea select').val(null);
						$('#KPIgroupSetdg').datagrid({
							url:'../../userInfo/selectUserInfo.do?getMs='+getMS(),
						});
						windowControl("分配成功");
					}else{
						windowControl('分配失败');	
					}
				},
				error:function(error){
					windowControl(error.status);
				}
			});
		}	
	})
}	