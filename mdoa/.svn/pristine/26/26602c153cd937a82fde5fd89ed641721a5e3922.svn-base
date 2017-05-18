$(function(){
	/**设置内部网格内容的高度**/
	$("#framework-lead .leadList").css('height',$('#loadarea').height()-63);
	
	/***设定部门的列表内容***/
	$('#framework-lead .leadList').datagrid({
	  //  url:'../../lead/getLeadLowerList.do?getMs='+getMS(),
	    method:"post",
	    columns:[[
	        {field:'leadId',title:'上下级关系Id',width:100,hidden:true},
			{field:'leadName',title:'领导名',width:100},
			{field:'leadDepartmentName',title:'领导部门',width:100,align:'right'},
			{field:'userId',title:'用户Id',width:100,align:'right',hidden:true},
			{field:'userName',title:'用户名',width:100},
			{field:'userDepartmentName',title:'用户部门名',width:100,align:'right'},
			{field:'createUserName',title:'创建人',width:100,align:'right'},
			{field:'createTimeStr',title:'创建时间',width:100,align:'right'},
			{field:'updateUserName',title:'修改人',width:100,align:'right'},
			{field:'updateTimeStr',title:'修改时间',width:100,align:'right'},
			{field:'_operation',title:'操作', width:80,align:'center',
				formatter: function(value,row,index){
					var userId = "'"+row.userId+"'";
					var userName = "'"+row.userName+"'";
					var leadId = "'"+row.leadId+"'"; 
					var opera = '';
		    		  if(existPermission('admin:personnel:framework:lead:select'))
		    			  opera +='<span class="small-button look" title="查看员工下级" onclick="selectLower('+userId+','+userName+')"/>';
		    		  if(existPermission('admin:personnel:framework:lead:delete'))
		    			  opera +='<span class="small-button delete" title="删除上级" onclick="deleteLead('+userId+','+leadId+')"/>';
		    		  if(existPermission('admin:personnel:framework:lead:update'))
		    			  opera +='<span class="small-button edit" title="选择员工" onclick="addLead('+userId+','+userName+')"/>';
		    		 return opera;
					/*return '<span class="small-button look" title="查看员工下级" onclick="selectLower('+userId+','+userName+')"/>'+
						'<span class="small-button delete" title="删除上级" onclick="deleteLead('+userId+','+leadId+')"/>'+
						'<span class="small-button edit" title="选择员工" onclick="addLead('+userId+','+userName+')"/>';*/
				}
			}
	    ]],
	    toolbar:'#framework-lead .invitetop',
	    striped:true,
	    loadMsg:'数据加载中',
	    checkbox:true,
		singleSelect:true,
	    pagination:true,
    	rownumbers:true
	});
	
	/****重新刷新员工上下级****/
	$("#framework-lead .select").click(function(){
		$('#framework-lead .leadList').datagrid({
			url:'../../lead/getLeadLowerList.do?getMs='+getMS(),
			queryParams: {
	    		userName:function(){
	    			return $('#framework-lead input[name=peopleName]').val();
	    		},
				leadName:function(){
					return $('#framework-lead input[name=leadName]').val();
				}
	    	},
		});
	})
	
	/****重新刷新员工上下级****/
	$("#framework-lead .clean").click(function(){
		$('#framework-lead input[name=peopleName]').val(null);
		$('#framework-lead input[name=leadName]').val(null);
	})
})

function addLower(id){
	chooseUsers();
	$('#chooseUsers .confirm').click(function(){
		lowerUsers = $('#chooseUsers .popuparea .user').datagrid('getSelections');
		var lowerUserIds = '';
		var lowerUserNames = '';
		for(var i = 0;i < lowerUsers.length; i++){
			lowerUserIds = lowerUserIds + '"' + lowerUsers[i].userId + '",';
			lowerUserNames = lowerUserNames + '"' + lowerUsers[i].userName + '",';
		}
		lowerUserIds = lowerUserIds.substring(0, lowerUserIds.length - 1); 
		lowerUserNames = lowerUserNames.substring(0, lowerUserNames.length - 1); 
		$.ajax({
			url:'../../lead/addLower.do?getMs='+getMS(),
			type:'post',
			data:{
				lowerIds:lowerUserIds,
				lowerNames:lowerUserNames,
				leadId:$('#framework-lead .popups .lowerList input[name=userId]').val(),
				leadName:$('#framework-lead .popups .lowerList input[name=peopleName]').val()
			},
			success:function(data){
				if(data == 200){
					windowControl('下级员工成功');
					$('#framework-lead .popups .lowerList .lower').datagrid('reload');
					$('#chooseUsers').css('display','none');
				}else{
					windowControl('下级员工失败');
				}
			}
		})
		$('#chooseUsers .confirm').unbind();
    }); 
}

function addLead(userId,userName){
	chooseUser();
	$('#chooseUser .confirm').click(function(){
		leadUser = $('#chooseUser .popuparea .user').datagrid('getSelections');
		$.ajax({
			url:'../../lead/addLeader.do?getMs='+getMS(),
			type:'post',
			data:{
				userId:userId,
				userName:userName,
				leadId:leadUser.userId,
				leadName:leadUser.leadId
			},
			success:function(data){
				if(data == 200){
					windowControl('添加上级员工成功');
					$('#framework-lead .popups .lowerList .lower').datagrid('reload');
					$('#framework-lead .leadList').datagrid('reload');
					$('#chooseUser').css('display','none');
				}else{
					windowControl('添加上级员工失败');
				}
			}
		});
		$('#chooseUser .confirm').unbind();
	});
}

function deleteLower(id){
	id = '"'+id+'"';
	$.ajax({
		url:'../../lead/deleteLower.do?getMs='+getMS(),
		data:{
			leadId:$('#framework-lead .popups .lowerList input[name=userId]').val(),
			lowerIds:id
		},
		type:'post',
		success:function(data){
			if(data == 200){
				windowControl('删除下级成功');
				$('#framework-lead .popups .lowerList .lower').datagrid('reload');
			}else{
				windowControl('删除下级失败');
			}
		}
	});
}

function deleteLead(userId, leadId){
	$.ajax({
		url:'../../lead/deleteLeader.do?getMs='+getMS(),
		data:{
			leadId:leadId,
			userId:userId
		},
		type:'post',
		success:function(data){
			if(data == 200){
				windowControl('删除上级成功');
				$('#framework-lead .popups .lowerList .lower').datagrid('reload');
			}else{
				windowControl('删除上级失败');
			}
		}
	});
}

function selectLower(userId,userName){
	$('#framework-lead .popups .lowerList input[name=userId]').val(userId);
	$('#framework-lead .popups .lowerList input[name=peopleName]').val(userName);
	$('#framework-lead .popups .lowerList').css('display','block');
	$('#framework-lead .popups .lowerList .lower').datagrid({
	    url:'../../lead/getLower.do?getMs='+getMS(),
	    columns:[[
			{field:'userName',title:'下级员工名',width:80},
			{field:'departmentName',title:'部门名',width:80},
			{field:'userId',title:'Id',width:80,hidden:true},
			{field:'_opera',title:'操作',width:80,formatter:function(value,row,index){
				var userId = "'"+row.userId+"'";
				return '<span class="small-button look" onclick="deleteLower('+userId+')"/>';
			}},
	    ]],
	    toolbar:'#framework-lead .popups .lowerList .queryForm',
	   	singleSelect:true,
	   	queryParams:{
	   		userId:userId
	   	}
    });
}