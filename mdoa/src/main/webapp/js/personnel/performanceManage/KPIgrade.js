/***********************************************************kpi打分********************************************************************/
if(existPermission('admin:personnel:performanceManage:KPIgrade:add'))
		$('#KPIgrade .maintop').append('<div><span class="maintopicon mainicon2"></span> <span class="add">添加</span></div>');

$(function(){
	$('#KPIgradedg').datagrid({
		  // url:'../../personnel/selectKpiRecordByTime.do?getMs='+getMS(), 
		   rownumbers:"true",
		   singleSelect:true,
		   pagination:true,
		   method:"post",
		   toolbar:"#KPIgrade .invitetop",
		   fit: true, 
		   columns:[[
		       //{field:"ck",checkbox:true },
		       {field:"userName",title:"用户名",fitColumns:true,resizable:true,align:"center",sortable:true,width:80},
		       {field:"kpiGroupName",title:"KPI组名字",fitColumns:true,sortable:true,align:"center",sortable:true,width:100},
		       {field:"markMonth",title:"打分月份",fitColumns:true,resizable:true,align:"center",sortable:true,width:70},
		       {field:"scoreUser",title:"用户分数",fitColumns:true,resizable:true,align:"center",sortable:true,width:60},
		       {field:"scoreStandard",title:"满分",fitColumns:true,resizable:true,align:"center",sortable:true,width:60},
		       {field:"markUserName",title:"打分人",fitColumns:true,resizable:true,align:"center",sortable:true,width:100},
		       {field:"strCreateTime",title:"创建时间",fitColumns:true,sortable:true,align:"center",sortable:true,width:125},
		       {field:"_op",title:"管理",width:100,resizable:true,align:"center",sortable:true,width:100,formatter:function(value,row,index){
		    	   var id = "'"+row.userId+"'";
		    	   var groupId = "'"+row.kpiGroupId+"'";
		    	   var time = "'"+row.markMonth+"'";
		    	   var groupRecordId = "'" +row.groupRecordId+"'";
		    	   var scoreUser = row.scoreUser;
		    	   var opera = '';
		    	   if(scoreUser == null){
		    		   if(existPermission('admin:personnel:performanceManage:KPIgrade:update'))
		    				opera +='<span class="small-button edit" title="员工打分" onclick="editKPIgrade('+id+','+groupId+','+time+','+groupRecordId+')"></span>';
		    		  if(existPermission('admin:personnel:performanceManage:KPIgrade:delete'))
		    			  opera +='<span class="small-button delete" title="删除" onclick="deleteKPIgrade('+groupRecordId+')"></span>';
		    	   }else{
		    		   if(existPermission('admin:personnel:performanceManage:KPIgrade:delete'))
			    			  opera +='<span class="small-button delete" title="删除" onclick="deleteKPIgrade('+groupRecordId+')"></span>';
			    	   if(existPermission('admin:personnel:performanceManage:KPIgrade:select'))
			    			  opera +='<span class="small-button look" title="查看" onclick="lookKPIgrade('+id+','+groupId+','+time+')"></span>';
		    	   }   
		    	   return opera;
		       }},
		  ]]
	}); 
});
/***********************************条件查询打分记录************************************************/
	$('#KPIgrade .invitetop .query').click(function(){
		var markMonth = $("#KPIgrade .invitetop .markMonth").val();
		var kpiGroupName = $("#KPIgrade .invitetop .kpiGroupName").val();
		var userName = $("#KPIgrade .invitetop .userName").val();
		$('#KPIgradedg').datagrid({
			url:'../../personnel/selectKpiRecordByTime.do?getMs='+getMS(),
			method:"post",
			queryParams: {
				markMonth : markMonth,
				kpiGroupName : kpiGroupName,
				userName : userName,
			},
		})
	});
/*******************添加打分组记录点击事件*************************************/
	$('#KPIgrade .maintop .add').click(function(){
		$('#KPIgrade .saveKpiGrade').css('display','block');
	});
/***************************选择员工*****************************/
$('#KPIgrade .popups .saveKpiGrade .chooseUser').click(function(){
	chooseUser();
    $('#chooseUser .confirm').click(function(){
    	$('#chooseUser').css('display','none');
    	selectUser = $('#chooseUser .popuparea .user').datagrid('getSelections');
    	$('#KPIgrade .popups .saveKpiGrade input[name=peopleName]').val(selectUser[0].userName);
    	$('#KPIgrade .popups .saveKpiGrade input[name=userId]').val(selectUser[0].userId);
    	$('#KPIgrade .popups .saveKpiGrade input[name=kpiGroupId]').val(selectUser[0].kpiGroupId);
    })
})

$('#KPIgrade .saveKpiGrade .confirm').click(function(){
	var username = $('#KPIgrade .saveKpiGrade input[name=peopleName]').val();
	var userId = $('#KPIgrade .saveKpiGrade input[name=userId]').val();
	var kpiGroupId = $('#KPIgrade .saveKpiGrade input[name=kpiGroupId]').val();
	var markmonth = $('#KPIgrade .saveKpiGrade .markMonth').val();
	if(markmonth==''|| markmonth==null){
		windowControl("打分月份不能为空");
	}
	else if(username==''|| username==null){
		windowControl("用户名不能为空");
	}else{
		$.ajax({
			data:{userName:username,userId:userId,markMonth:markmonth,kpiGroupId:kpiGroupId,},
			url:'../../personnel/insertKpiRecordGroup.do?getMs='+getMS(),
			type:'post',
			success:function(data){
				if(data == 200){
					$('#KPIgrade .saveKpiGrade').css('display', 'none');
					$('#KPIgrade .saveKpiGrade .popuparea input').val(null);
					$('#KPIgrade .saveKpiGrade .popuparea select').val(null);
					$('#KPIgradedg').datagrid({
						url:'../../personnel/selectKpiRecordByTime.do?getMs='+getMS(),
					});
					windowControl('增加打分记录成功');
				}else{
					windowControl('增加打分记录失败');
				}	
			},
			error:function(err) {
				windowControl(err.status);
			}	
		})
	}
})
/**********************************给员工打分**********************************/
function editKPIgrade(id,groupId,time,groupRecordId){
	$.ajax({
		data:{userId:id,kpiGroupId:groupId,},
		url:'../../personnel/selectKpiGroupByUserId.do?getMs='+getMS(),
		type:'post',
		success:function(data){
			var content = $.parseJSON(data);
			if(content.length == 0){
				windowControl('此KPI组没有分配kpi考核标准');
			}else{
				$('#KPIgrade .editKPIgrade').css('display','block');
				var str ='';// tableStr;
				$("#KPIgrade .editKPIgrade .staffName").attr('readonly','true');
				$("#KPIgrade .editKPIgrade .department").attr('readonly','true');
				$("#KPIgrade .editKPIgrade .KPIgroupName").attr('readonly','true');
				$("#KPIgrade .editKPIgrade .fullmark").attr('readonly','true');
				$("#KPIgrade .editKPIgrade .condition").attr('readonly','true');
				for(var i=0;i<content.length;i++){
					
					str +='<table><tr><td style="width:80px;">kpi标准名字</td><td style="width:180px;"><input type="text" value ="'+content[i].kpiName+'" readonly=""/></td></tr>'
					+'<tr><td><input type="hidden" class="kpiId" value ="'+content[i].kpiId+'" /></td></tr>'
					+'<tr><td>kpi描述</td><td><input type="text" value ="'+content[i].text+'" readonly=""/></td></tr>'
					+'<tr><td>打分</td><td><input type="text" class="realmark"/></td></tr>'
					+'<tr><td>此标准满分</td><td><input type="text" class="standardScore" value ="'+content[i].scoreStandard+'" readonly=""/></td></tr>'
					+'<tr><td>评分原因</td><td><input type="text" style="padding-bottom:24px;" class="reason"/></td></tr><table/><hr style="width:328%;"/>';
				}
				$("#KPIgrade .editKPIgrade table tbody").html(str);
				$("#KPIgrade .editKPIgrade .same").css("display","none");
				$("#KPIgrade .editKPIgrade").css("display","block");
				/**********************************评分操作********************************/
				//当输入分数失去焦点时kpi分数相加，kpi组的总分数页相加
				$('#KPIgrade .realmark').blur(function(){
					var num = $('#KPIgrade .realmark').length;
					var realgrade = 0;
					var score = 0;
					for(var i= 0;i<num;i++){
					realgrade = realgrade +Number($('#KPIgrade .realmark').eq(i).val());
					score = score +Number($('#KPIgrade .standardScore').eq(i).val());
					}
					$('#KPIgrade .editKPIgrade .realgrade').val(realgrade);
					$('#KPIgrade .editKPIgrade .scoreStandard').val(score);
				});
				$('#KPIgrade .editKPIgrade .confirm').unbind('click');
				//当点击保存的时候
				$('#KPIgrade .editKPIgrade .confirm').click(function(){
					var KPIgroupName = $('#KPIgrade .KPIgroupName').val();
					var len = $('#KPIgrade .realmark').length;
					var kpiList = [];
					var turnoff = true;
					for(var i=0;i<len;i++){
						var kpiId = $('#KPIgrade .kpiId').eq(i).val();
						var userScore = $('#KPIgrade .realmark').eq(i).val();
						var standardScore = $('#KPIgrade .standardScore').eq(i).val();
						var reason = $('#KPIgrade .reason').eq(i).val();
						var scoreUser = $('#KPIgrade .editKPIgrade .realgrade').val();
						var scoreStandard = $('#KPIgrade .editKPIgrade .scoreStandard').val();
						if(i == 0){
							var info = {
								kpiId:kpiId,
								userScore:userScore,
								standardScore:standardScore,
								reason:reason,
								scoreUser:scoreUser,
								scoreStandard:scoreStandard,
								groupRecordId:groupRecordId,
							}
						}else{
							var info = {
								kpiId:kpiId,
								userScore:userScore,
								standardScore:standardScore,
								reason:reason,
							}
						}
						if(userScore == ''){
							windowControl('第'+(i+1)+'个打分内容为空');
							turnoff = false;
						}else{
							kpiList.push(info);
							console.log(kpiList);
							var json = JSON.stringify(kpiList);
							console.log(json);
						}
					}
						$.ajax({
							data:{json:json},
							url:'../../personnel/insertKpiRecord.do?getMs='+getMS(),
							success:function(data){
								if(data == 200){
									windowControl('员工打分成功');
									$("#KPIgrade .editKPIgrade").css("display","none");
									$('#KPIgrade .KPIarea').remove();
									$('#KPIgrade .editKPIgrade .popuparea input').val(null);
									$('#KPIgradedg').datagrid('reload');
								}else{
									windowControl('员工打分失败');
								}
							},
							error:function(error){
								windowControl(error.status);
							}
						});
				})
			}
		
		},
		error:function(error){
			windowControl(error.status);
		}
	});
}
/*********************************查看kpi打分******************************************/
function lookKPIgrade(id,groupId,time){
	$.ajax({
		data:{userId:id,kpiGroupId:groupId,},
		url:'../../personnel/selectKpiRecordByGroup.do?getMs='+getMS(),
		type:'post',
		success:function(data){
			$('#KPIgrade .lookKPIgrade').css('display','block');
			var data = eval('(' + data + ')'); 
			var str ='';// tableStr;
			$("#KPIgrade .lookKPIgrade .staffName").attr('readonly','true');
			$("#KPIgrade .lookKPIgrade .department").attr('readonly','true');
			$("#KPIgrade .lookKPIgrade .KPIgroupName").attr('readonly','true');
			$("#KPIgrade .lookKPIgrade .fullmark").attr('readonly','true');
			$("#KPIgrade .lookKPIgrade .condition").attr('readonly','true');
			for(var i=0;i<data.rows.length;i++){
				str +='<table><tr><td>kpi标准名字</td><td><input type="text" value ="'+ data.rows[i].kpiName+'" readonly=""/></td></tr>'
				+'<tr><td><input type="hidden" class="kpiId" value ="'+ data.rows[i].kpiId+'" readonly=""/></td></tr>'
				+'<tr><td>kpi描述</td><td><input type="text" value ="'+ data.rows[i].text+'" readonly=""/></td></tr>'
				+'<tr><td>kpi分数</td><td><input type="text" class="realmark" value ="'+ data.rows[i].userScore+'" readonly="" /></td></tr>'
				+'<tr><td>kpi满分满分</td><td><input type="text" class="standardScore" value ="'+ data.rows[i].scoreStandard+'" readonly=""/></td></tr>'
				+'<tr><td>评分原因</td><td><input type="text" style="padding-bottom:24px;" class="reason" value ="'+ data.rows[i].reason+'" readonly=""/></td></tr><table/><hr/>';
			}
			$("#KPIgrade .lookKPIgrade table tbody").html(str);
			$("#KPIgrade .lookKPIgrade .same").css("display","none");
			$("#KPIgrade .lookKPIgrade").css("display","block");
		},
		error:function(error){
			windowControl(error.status);
		}
	});
}
/***********************************删除查询打分记录************************************************/	
function deleteKPIgrade(groupRecordId){
	ui.confirm('确定要删除KPI打分记录？',function(z){
		if(z){
			$.ajax({
				data:{groupRecordId:groupRecordId,},
				url:'../../personnel/deleteKpiGroupRecord.do?getMs='+getMS(),
				type:'post',
				success:function(data){
					if(data == 200){
						$('#KPIgradedg').datagrid('reload');
						windowControl('删除打分记录成功');
					}else{
						windowControl('删除打分记录失败');
					}
				},
				error:function(error){
					windowControl(error.status);
				}
			});	
		}
	},false);
}

