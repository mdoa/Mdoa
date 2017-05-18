$(function(){
	var leftCellWidth = $('#checkGroupAllot .checkGroupName').width()-3;
	$('#checkGroupAllot .checkGroupAllotMain').css('height',$('#loadarea').height()-32+'px');
	$('#checkGroupAllot .checkGroupAllotMain #checkGroupNameDg').css('height',$('#loadarea').height()-32+'px');
	$('#checkGroupAllot .checkGroupAllotDetail #checkGroupAllotDetailDg').css('height',$('#loadarea').height()-64+'px');
	//左侧所有考勤组列表
	$("#checkGroupNameDg").datagrid({
		url:'../../attendance/findGroupByCondition.do?getMs='+getMS(),
		singleSelect:true,
		method:"post",
		pagination:true,
		onLoadSuccess:function(){
			$('#checkGroupAllot .checkGroupName .datagrid-cell:eq(0)').css({'height':'31px','line-height':'31px','font-size':'14px'});			
		},
		onDblClickRow:function(rowIndex, rowData){
			var selectedRow = $('#checkGroupNameDg').datagrid('getSelected');
			var groupId  = selectedRow.groupId;
			$('#checkGroupAllot .checkDetailTop input[name=groupId]').val(groupId);
			$('#checkGroupAllotDetailDg').datagrid({
				url:"../../userInfo/selectUserInfo.do?getMs="+getMS(),
				queryParams:{
					attendanceGroupId:groupId
				},
				singleSelect:true,
				method:"post",
				pagination:true,
				columns:[[
					{field:"userAccount",title:"员工编号 ",width:100,align:"center"},
					{field:"userName",title:"员工名字",width:100,align:"center"},
					{field:"idCard",title:"身份证号码",width:150,align:"center"},
					{field:"sex",title:"性别",align:"center",width:50,formatter:function(value,row,index){
					   var sex = row.sex;
					   if(sex == 1){
						   return '男';
					   }else{
						   return '女';
					   }
					}},
					{field:"birthdayStr",title:"出生日期",width:100,align:"center"},
					{field:"workTimeStr",title:"入职时间",width:100,align:"center"},
					{field:"departmentName",title:"所属部门",width:100,align:"center"},
					{field:"postName",title:"所属岗位",width:100,align:"center"},
					{field:"invite_flag",title:"在职状态",width:100,align:"center",formatter:function(value,row,index){
						   if(row.inviteFlag == '1')
							   return '在职';
						   else if(row.inviteFlag == '2')
							   return '离职无手续';
						   else if(row.inviteFlag == '3')
							   return '离职有手续';
						   else if(row.inviteFlag == '4')
							   return '试用期';
						   else if(row.inviteFlag == '5')
							   return '工伤';
					}},
					{field:"retire_flag",title:"退休状态",width:100,align:"center",formatter:function(value,row,index){
						   if(row.retireFlag == '1')
							   return '正常';
						   else if(row.retireFlag == '2')
							   return '退休';
						   else if(row.retireFlag == '3')
							   return '返聘';
					}},
					{field:"phoneNum",title:"联系方式",width:100,align:"center"},
					{field:"address",title:"地址",width:100,align:"center"},
					{
						field:"_opera",
						title:"操作",
						width:60,
						align:"center",
						formatter:function(value,row,index){
						   var opera = '';
						   var groupId = "'"+ row.attendanceGroupId +"'";
						   var userName = "'"+ row.userName +"'";
						   var userId = "'"+ row.userId +"'";
						   opera += '<div class="imgBox">'
							   opera += '<span style="float:left;" class="small-button delete" title="删除" onclick="checkGroupAllotDel('+ groupId +','+ userName +','+ userId +')"></span>';
						   opera += '</div>'
						   return opera;
						}
					}
				]]
			});
			$('#checkGroupAllot .checkDetailTop input[name=addIntoGroup]').css('display','inline');			
		},
		columns:[[
		     {field:"groupName",title:"考勤组",align:"center",width:leftCellWidth},
		]]
	});
	$('#checkGroupNameDg').datagrid('getPager').pagination({
		showPageList:false,
		showRefresh:false,
		beforePageText:'',
		afterPageText:'',
		displayMsg:''
	});
	
	//添加考勤组人员
	$('#checkGroupAllot .checkDetailTop input[name=addIntoGroup]').click(function(){
		chooseUsers();
		$('#chooseUsers .confirm').unbind();
	    $('#chooseUsers .confirm').click(function(){
	    	$('#chooseUsers').css('display','none');
	    	var selectUsers = $('#chooseUsers .popuparea .user').datagrid('getSelections');
	    	var groupId = $('#checkGroupAllot .checkDetailTop input[name=groupId]').val();
	    	var list = [];
	    	for(var i = 0 ; i < selectUsers.length ; i++ ){
	    		list.push(selectUsers[i].userId);
	    	}
	    	var obj = {
	    		groupId:groupId,
	    		userIds:"'"+list.join("\',\'")+"'"
	    	}
	    	if(selectUsers.length > 0){
	    		$.ajax({
	    			url:'../../attendance/addGroupMemberByIds.do?getMs='+getMS(),
	    			data:obj,
	    			type:'post',
	    			success:function(data){	    				
	    				if(data == 200){
	    					$('#checkGroupAllotDetailDg').datagrid('reload');
	    				}else{
	    					windowControl("添加失败！");
	    				}
	    			},
	    			error:function(){
	    				windowControl("服务器未响应");
	    			}
	    		})
	    	}
	    })
	})
	
	
})
//移除考勤组人员
function checkGroupAllotDel(groupId,userName,userId ){
	ui.confirm('确定将 '+userName+' 移除吗？',function(z){
		if(z){
			$.ajax({
    			url:'../../attendance/removeGroupMember.do?getMs='+getMS(),
    			data:{
    				attendanceGroupId:groupId,
    				userIds:"'"+userId+"'"
    			},
    			type:'post',
    			success:function(data){	    				
    				if(data == 200){
    					$('#checkGroupAllotDetailDg').datagrid('reload');
    				}else{
    					windowControl("移除失败！");
    				}
    			},
    			error:function(){
    				windowControl("服务器未响应");
    			}
    		})
		}
	},false)
}





