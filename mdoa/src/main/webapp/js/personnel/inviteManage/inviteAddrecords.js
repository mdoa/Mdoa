$(function (){
	$('#inviteAddrecords').css('height',$('#loadarea').height()-32);
	/***********动态设置身份证以及一寸照img的margin-left使其规范显示************/
	$('#inviteAddrecords .basicInformation .user-message img').css('margin-left', 
			$('#inviteAddrecords .basicInformation .user-message img').parent().width() - 205);
	
	/**********添加 下一位员工 的点击事件**********/
	$('#inviteAddrecords input[name=clean-All]').click(function(){
		$('#inviteAddrecords input[name=clean-All]').css('display','none');
		$('#inviteAddrecords input[name=read-idCard]').css('display','inline');
		$('#inviteAddrecords input').not('input[type=button]').val(null);
		$('#inviteAddrecords textarea').val(null);
		$('#inviteAddrecords select').val(null);
		$('#staffRecord img').attr("src", "");
		$('#inviteAddrecords .display-work').html('');
		$('#inviteAddrecords .display-train').html('');
		$('#inviteAddrecords .display-edu').html('');
		$('#inviteAddrecords .basicInformation select').removeAttr('disabled');
		$('#inviteAddrecords .basicInformation .basic input').removeAttr('readonly');
		$('#inviteAddrecords .expsame .addMessage').css('display','inline');
	})
	
	
	/*********开启页面时，添加选择部门与岗位的点击事件*********/
	//选择部门
	$('#inviteAddrecords .basicInformation .chooseDept').click(function(){
		chooseDept();
	    $('#chooseDept .confirm').click(function(){
	    	$('#chooseDept').css('display','none');
	    	var chooseDept = $('#chooseDept .dept').tree('getSelected');
	    	$('#inviteAddrecords .basicInformation input[name=departmentName]').val(chooseDept.text);
	    	$('#inviteAddrecords .basicInformation input[name=departmentUrl]').val(chooseDept.id);
	    	$('#chooseDept .confirm').unbind();
	    })
	});
	//选择岗位
	$('#inviteAddrecords .basicInformation .choosePost').click(function(){
		choosePost();
		$('#choosePost .confirm').click(function(){
	    	$('#choosePost').css('display','none');
	    	var selectPost = $('#choosePost .popuparea .post').datagrid('getSelections');
	    	$('#inviteAddrecords .basicInformation input[name=postName]').val(selectPost[0].postName);
	    	$('#inviteAddrecords .basicInformation input[name=postId]').val(selectPost[0].postId);
	    	$('#choosePost .confirm').unbind();
	    })
	});
	//选择上级
	$('#inviteAddrecords .basicInformation .chooseUser').click(function(){
		chooseUser();
	    $('#chooseUser .confirm').click(function(){
	    	$('#chooseUser').css('display','none');
	    	selectUsers = $('#chooseUser .popuparea .user').datagrid('getSelections');
	    	$('#inviteAddrecords .basicInformation input[name=leadName]').val(selectUsers[0].userName);
	    	$('#inviteAddrecords .basicInformation input[name=leadId]').val(selectUsers[0].userId);
	    	$('#chooseUser .confirm').unbind();
	    });
	});
//	//选择角色
//	$('#inviteAddrecords .basicInformation .chooseRole').click(function(){
//		chooseRoles();
//    	$('#chooseRoles .confirm').click(function(){
//    		$('#chooseRoles').css('display','none');
//    		var roleIds = '';
//    		var roleNames = '';
//        	var selectRoles = $('#chooseRoles .popuparea .role').datagrid('getSelections');
//        	for(var i = 0 ; i<selectRoles.length ; i++ ){
//        		roleIds = roleIds + '"' + selectRoles[i].roleId + '",';
//        		roleNames = roleNames + '"' + selectRoles[i].roleName + '",';
//        	}
//        	roleIds = roleIds.substring(0, roleIds.length - 1);
//        	roleNames = roleNames.substring(0, roleNames.length - 1);
//        	$('#inviteAddrecords .basicInformation input[name=roleIds]').val(roleIds);
//        	$('#inviteAddrecords .basicInformation input[name=roleNames]').val(roleNames);
//        	$('#choosePost .confirm').unbind();
//        })
//	});
	
	/********当用户输入了员工的身份证号码的时候进行触发**********/
	$('#inviteAddrecords .basicInformation input[name="idCard"]').blur(function(){
		inviteRecordValidate()
	});
	/*********************身份证图片上传*****************************/
	function idCardUpload(id,photo,idCard){
		$.ajax({
			data:{userId:id,base:photo,idCard:idCard},
			url:"../../userInfo/updateUserInfoPhoto?getMs="+getMS(),
			type:'post',
			success:function(data){
				
			},
			error:function(err){
				windowControl(err.status);
			}
		})
	}
	function idCardUpImage(id,photo1,idCard){
		$.ajax({
			data:{userId:id,base:photo1,idCard:idCard},
			url:"../../userInfo/updateUserInfoUpImage?getMs="+getMS(),
			type:'post',
			success:function(data){
				
			},
			error:function(err){
				windowControl(err.status);
			}
		})
	}
	function idCardDownImage(id,photo2,idCard){
		$.ajax({
			data:{userId:id,base:photo2,idCard:idCard},
			url:"../../userInfo/updateUserInfoDownImage?getMs="+getMS(),
			type:'post',
			success:function(data){
				
			},
			error:function(err){
				windowControl(err.status);
			}
		})
	}
	/********当用户点击保存基本信息时的点击事件*******/
	$('#inviteAddrecords .expsame .addMessage').click(function(){
		var userAccount = $('#inviteAddrecords .basicInformation input[name="userAccount"]').val();
		var userName = $('#inviteAddrecords .basicInformation input[name="peopleName"]').val();
		var departmentName = $('#inviteAddrecords .basicInformation input[name="departmentName"]').val();
		var departmentUrl = $('#inviteAddrecords .basicInformation input[name="departmentUrl"]').val();
		var postName = $('#inviteAddrecords .basicInformation input[name="postName"]').val();
		var postId = $('#inviteAddrecords .basicInformation input[name="postId"]').val();
		var leadName = $('#inviteAddrecords .basicInformation input[name="leadName"]').val();
		var leadId = $('#inviteAddrecords .basicInformation input[name="leadId"]').val();
		var roleNames = $('#inviteAddrecords .basicInformation input[name="roleNames"]').val();
		var roleIds = $('#inviteAddrecords .basicInformation input[name="roleIds"]').val();
		var phoneNum = $('#inviteAddrecords .basicInformation input[name="phoneNum"]').val();
		var accidentPhoneNum = $('#inviteAddrecords .basicInformation input[name="accidentPhoneNum"]').val();
		var birthday = $('#inviteAddrecords .basicInformation input[name="birthday"]').val();
		var idCard = $('#inviteAddrecords .basicInformation input[name="idCard"]').val();
		var sex = $('#inviteAddrecords .basicInformation select[name="sex"]').val();
		var workTime = $('#inviteAddrecords .basicInformation input[name="workTime"]').val();
		var address = $('#inviteAddrecords .basicInformation input[name="address"]').val();
		var leaderFlag = $('#inviteAddrecords .basicInformation select[name="leaderFlag"]').val();
		var education = $('#inviteAddrecords .basicInformation select[name="education"]').val();
		var volk = $('#inviteAddrecords .basicInformation select[name="volk"]').val();
		var nativePlace = $('#inviteAddrecords .basicInformation input[name="nativePlace"]').val();
		var politicalStatus = $('#inviteAddrecords .basicInformation select[name="politicalStatus"]').val();
		var remark = $('#inviteAddrecords .basicInformation textarea[name="remark"]').val();
		var marriageFlag = $('#inviteAddrecords .basicInformation select[name="marriageFlag"]').val();
		var photo  = $('#inviteAddrecords .basicInformation .image1').val();
		var photo1 = $('#inviteAddrecords .basicInformation .image2').val();
		var photo2 = $('#inviteAddrecords .basicInformation .image3').val();
		console.log("部门="+departmentUrl);
		if(userName == null || userName ==''){
			windowControl('员工姓名不能为空');
			return ;
		}else if(idCard == null || !(idCard.length == 18 ||idCard.length == 15)){
			windowControl('请输入18位或15位身份证号');
			return ;
		}else if(birthday == null || birthday ==''){
			windowControl('出生日期不能为空');
			return ;
		}else if(education == null || education ==''){
			windowControl('学历不能为空');
			return ;
		}else if(address == null || address ==''){
			windowControl('家庭住址不能为空');
			return ;
		}else if(marriageFlag == null || marriageFlag == ''){
			windowControl('婚姻状态不能为空');
			return ;
		}else if(nativePlace == null || nativePlace == ''){
			windowControl('籍贯不能为空');
			return ;
		}else if(politicalStatus == null || politicalStatus == ''){
			windowControl('政治面貌不能为空');
			return ;
		}else if(phoneNum == null || phoneNum ==''){
			windowControl('联系方式不能为空');
			return ;
		}else if(accidentPhoneNum == null || accidentPhoneNum ==''){
			windowControl('紧急联系方式不能为空');
			return ;
		}else if(userAccount == null || userAccount ==''){
			windowControl('员工编号不能为空');
			return ;
		}else if(workTime == null || workTime ==''){
			windowControl('入职时间不能为空');
			return ;
		}else if(departmentUrl == null || departmentUrl ==''){
			windowControl('所属部门不能为空');
			return ;
		}else if(postId == null || postId ==''){
			windowControl('所属岗位不能为空');
			return ;
		}
		$.ajax({
			url:'../../userInfo/saveUserInfo.do?getMs='+getMS(),
			data:{
				userAccount:userAccount,
				userName:userName,
				departmentName:departmentName,
				departmentUrl:departmentUrl,
				postName:postName,
				postId:postId,
				leadName:leadName,
				leadId:leadId,
				phoneNum:phoneNum,
				accidentPhoneNum:accidentPhoneNum,
				birthdayStr:birthday,
				idCard:idCard,
				sex:sex,
				workTimeStr:workTime,
				address:address,
				leaderFlag:leaderFlag,
				education:education,
				volk:volk,
				inviteFlag:'0',
				retireFlag:'1',
				politicalStatus:politicalStatus,
				nativePlace:nativePlace,
				marriageFlag:marriageFlag
			},
			type:'post',
			success:function(data){
				if(data == 400){
					windowControl("数据添加失败");
				}else if(data == 500){
					windowControl("数据添加失败");
				}else{
					idCardUpload(data,photo,idCard);
					idCardUpImage(data,photo1,idCard);
					idCardDownImage(data,photo2,idCard);
					windowControl("添加数据成功");
					$('#inviteAddrecords .basicInformation select').attr('disabled','disabled');
					$('#inviteAddrecords .basicInformation .basic input').attr('readonly','readonly');
					$('#inviteAddrecords .expsame .addMessage').css('display','none');
					$('#inviteAddrecords input[name=clean-All]').css('display','inline');
					$('#inviteAddrecords input[name=read-idCard]').css('display','none');
					$('#inviteAddrecords input[name=userId]').val(data);
				}
			}
		});
	});
	
	/******保存工作经历按钮点击事件，保存工作经历，并且显示出保存成功的工作经历******/
	$('#inviteAddrecords .work .addexp').click(function(){
		var userId = $('#inviteAddrecords input[name=userId]').val();
		var postName = $('#inviteAddrecords .work input[name=postName]').val();
		var componyName = $('#inviteAddrecords .work input[name=componyName]').val();
		var startTime = $('#inviteAddrecords .work input[name=startTime]').val();
		var endTime = $('#inviteAddrecords .work input[name=endTime]').val();
		var workDescribe = $('#inviteAddrecords .work textarea[name=workDescribe]').val();
		if(userId == null || userId == ''){
			windowControl("请先保存员工基本信息");
			return ;
		}else if(postName == null || postName == ''){
			windowControl('岗位名称不能为空');
			return ;
		}else if(componyName == null || componyName == ''){
			windowControl('公司名称不能为空');
			return ;
		}else if(startTime == null || startTime == ''){
			windowControl('开始工作时间不能为空');
			return ;
		}else if(endTime == null || endTime == ''){
			windowControl('结束工作时间不能为空');
			return ;
		}else if(workDescribe == null || workDescribe == ''){
			windowControl('工作详情不能为空');
			return ;
		}
		$.ajax({
			url:'../../userInfo/saveWork.do?getMs='+getMS(),
			type:'post',
			data:{
				userId:userId,
				postName:postName,
				componyName:componyName,
				startTimeStr:startTime,
				endTimeStr:endTime,
				workDescribe:workDescribe
			},
			success:function(data){
				if(data == 400){
					windowControl('工作经验添加失败');
				}else if(data == 500){
					windowControl('工作经验添加失败');
				}else{
					var html = '<table>'
						+'<tr><td width="240px"></td><td width="800px"></td><td width="800px"></td><td width="800px"></td></tr>'
						+'<tr height="40px"><td colspan="1" style="text-align: right;">公司名称：</td><td colspan="2">'+componyName+'</td>'
						+'<td style="text-align:right"><input style="display:none"/>'
						+'<label>修改<span class="small-button edit"></span></label><label>删除<span class="small-button delete"></span></label></td></tr>'
						+'<tr height="40px"><td style="text-align: right;">入职时间：</td><td>'+startTime+'</td><td style="text-align: right;">离职时间：</td><td>'+endTime+'</td></tr>'
						+'<tr height="40px"><td colspan="1" style="text-align: right;">岗位名称：</td><td colspan="3">'+postName+'</td></tr>'
						+'<tr height="40px"><td colspan="1"  style="text-align: right;">岗位描述：</td>'
						+'<td colspan="3"><div style="width:100%">'+workDescribe+'</div></td></tr>'
						+'</table><hr/>';
					$('#inviteAddrecords .work .display-work').append(html);
					$('#inviteAddrecords .work .work-table input').val(null);
					$('#inviteAddrecords .work .work-table textarea').val(null);
					$('#inviteAddrecords .work .display-work').find("table:last input").val(data);
					//添加删除修改点击事件
					inviteAddrecordsAddWorkEven();
					
				}
			}
		});
	});
	
	/******修改工作经历按钮点击事件，修改工作经历，并且显示出保存成功的工作经历******/
	$('#inviteAddrecords .work .updateexp').click(function(){
		var workId = $('#inviteAddrecords input[name=workId]').val();
		var userId = $('#inviteAddrecords input[name=userId]').val();
		var postName = $('#inviteAddrecords .work input[name=postName]').val();
		var componyName = $('#inviteAddrecords .work input[name=componyName]').val();
		var startTime = $('#inviteAddrecords .work input[name=startTime]').val();
		var endTime = $('#inviteAddrecords .work input[name=endTime]').val();
		var workDescribe = $('#inviteAddrecords .work textarea[name=workDescribe]').val();
		if(userId == null || userId == ''){
			windowControl("请先保存员工基本信息");
			return ;
		}else if(postName == null || postName == ''){
			windowControl('岗位名称不能为空');
			return ;
		}else if(componyName == null || componyName == ''){
			windowControl('公司名称不能为空');
			return ;
		}else if(startTime == null || startTime == ''){
			windowControl('开始工作时间不能为空');
			return ;
		}else if(endTime == null || endTime == ''){
			windowControl('结束工作时间不能为空');
			return ;
		}else if(workDescribe == null || workDescribe == ''){
			windowControl('工作详情不能为空');
			return ;
		}
		$.ajax({
			url:'../../userInfo/updateWork.do?getMs='+getMS(),
			data:{
				userId:userId,
				postName:postName,
				componyName:componyName,
				startTimeStr:startTime,
				endTimeStr:endTime,
				workDescribe:workDescribe,
				aliveFlag:1,
				workId:workId
			},
			type:'post',
			success:function(data){
				if(data == 400 || data == 500){
					windowControl('修改员工工作信息失败');
					return ;
				}
				var html = '<table>'
					+'<tr><td width="240px"></td><td width="800px"></td><td width="800px"></td><td width="800px"></td></tr>'
					+'<tr height="40px"><td colspan="1" style="text-align: right;">公司名称：</td><td colspan="2">'+componyName+'</td>'
					+'<td style="text-align:right"><input style="display:none"/>'
					+'<label>修改<span class="small-button edit"></span></label><label>删除<span class="small-button delete"></span></label></td></tr>'
					+'<tr height="40px"><td style="text-align: right;">入职时间：</td><td>'+startTime+'</td><td style="text-align: right;">离职时间：</td><td>'+endTime+'</td></tr>'
					+'<tr height="40px"><td colspan="1" style="text-align: right;">岗位名称：</td><td colspan="3">'+postName+'</td></tr>'
					+'<tr height="40px"><td colspan="1"  style="text-align: right;">岗位描述：</td>'
					+'<td colspan="3"><div style="width:100%">'+workDescribe+'</div></td></tr>'
					+'</table><hr/>';
				$('#inviteAddrecords .work .display-work').append(html);
				$('#inviteAddrecords .work .work-table input').val(null);
				$('#inviteAddrecords .work .work-table textarea').val(null);
				$('#inviteAddrecords .work .display-work').find("table:last input").val(workId);
				$('#inviteAddrecords .work .addexp').parent().css('display','block');
				$('#inviteAddrecords .work .updateexp').parent().css('display','none');
				//添加删除修改点击事件
				inviteAddrecordsAddWorkEven();
			}
		})
	});
	
	/******保存培训经历按钮点击事件，保存培训经历，并且显示出保存成功的培训经历******/
	$('#inviteAddrecords .train .addexp').click(function(){
		var userId = $('#inviteAddrecords input[name=userId]').val();
		var trainName = $('#inviteAddrecords .train input[name=trainName]').val();
		var trainCompany = $('#inviteAddrecords .train input[name=trainCompany]').val();
		var startTime = $('#inviteAddrecords .train input[name=startTime]').val();
		var endTime = $('#inviteAddrecords .train input[name=endTime]').val();
		var trainDescribe = $('#inviteAddrecords .train textarea[name=trainDescribe]').val();
		if(userId == null || userId == ''){
			windowControl("请先保存员工基本信息");
			return ;
		}else if(trainName == null || trainName == ''){
			windowControl('培训名称不能为空');
			return ;
		}else if(trainCompany == null || trainCompany == ''){
			windowControl('培训公司名称不能为空');
			return ;
		}else if(startTime == null || startTime == ''){
			windowControl('开始工作时间不能为空');
			return ;
		}else if(endTime == null || endTime == ''){
			windowControl('结束工作时间不能为空');
			return ;
		}else if(trainDescribe == null || trainDescribe == ''){
			windowControl('培训详情不能为空');
			return ;
		}
		$.ajax({
			url:'../../userInfo/saveTrain.do?getMs='+getMS(),
			type:'post',
			data:{
				userId:userId,
				trainName:trainName,
				trainCompany:trainCompany,
				startTimeStr:startTime,
				endTimeStr:endTime,
				trainDescribe:trainDescribe
			},
			success:function(data){
				if(data == 400 || data == 500){
					windowControl('修改信息失败');
					return ;
				}else{
					var html = '<table>'
						+'<tr><td width="240px"></td><td width="800px"></td><td width="800px"></td><td width="800px"></td></tr>'
						+'<tr height="40px"><td colspan="1" style="text-align: right;">培训名称：</td><td colspan="3">'+trainName+'</td></tr>'
						+'<tr height="40px"><td colspan="1" style="text-align: right;">公司名称：</td><td colspan="2">'+trainCompany+'</td>'
						+'<td style="text-align:right"><input style="display:none"/>'
						+'<label>修改<span class="small-button edit"></span></label><label>删除<span class="small-button delete"></span></label></td></tr>'
						+'<tr height="40px"><td style="text-align: right;">开始时间：</td><td>'+startTime+'</td><td style="text-align: right;">结束时间：</td><td>'+endTime+'</td></tr>'
						+'<tr height="40px"><td colspan="1"  style="text-align: right;">培训描述：</td>'
						+'<td colspan="3"><div style="width:100%">'+trainDescribe+'</div></td></tr>'
						+'</table><hr/>';
					$('#inviteAddrecords .train .display-train').append(html);
					$('#inviteAddrecords .train .train-table input').val(null);
					$('#inviteAddrecords .train .train-table textarea').val(null);
					$('#inviteAddrecords .train .display-train').find("table:last input").val(data);
					//添加删除修改点击事件
					inviteAddrecordsAddTrainEven();
					
				}
			}
		});
	})
	
	/*******修改培训经历按钮点击事件，修改培训经历，并且显示出保存成功的培训经历*************/
	$('#inviteAddrecords .train .updateexp').click(function(){
		var trainId = $('#inviteAddrecords .train input[name=trainId]').val();
		var userId = $('#inviteAddrecords input[name=userId]').val();
		var trainName = $('#inviteAddrecords .train input[name=trainName]').val();
		var trainCompany = $('#inviteAddrecords .train input[name=trainCompany]').val();
		var startTime = $('#inviteAddrecords .train input[name=startTime]').val();
		var endTime = $('#inviteAddrecords .train input[name=endTime]').val();
		var trainDescribe = $('#inviteAddrecords .train textarea[name=trainDescribe]').val();
		if(userId == null || userId == ''){
			windowControl("请先保存员工基本信息");
			return ;
		}else if(trainName == null || trainName == ''){
			windowControl('培训名称不能为空');
			return ;
		}else if(trainCompany == null || trainCompany == ''){
			windowControl('培训公司名称不能为空');
			return ;
		}else if(startTime == null || startTime == ''){
			windowControl('开始工作时间不能为空');
			return ;
		}else if(endTime == null || endTime == ''){
			windowControl('结束工作时间不能为空');
			return ;
		}else if(trainDescribe == null || trainDescribe == ''){
			windowControl('培训详情不能为空');
			return ;
		}
		$.ajax({
			url:'../../userInfo/updateTrain.do?getMs='+getMS(),
			data:{
				trainDocId:trainId,
				userId:userId,
				trainName:trainName,
				trainCompany:trainCompany,
				startTimeStr:startTime,
				endTimeStr:endTime,
				trainDescribe:trainDescribe
			},
			success:function(data){
				if(data == 400 || data == 500){
					windowControl('修改信息失败');
					return ;
				}
				var html = '<table>'
					+'<tr><td width="240px"></td><td width="800px"></td><td width="800px"></td><td width="800px"></td></tr>'
					+'<tr height="40px"><td colspan="1" style="text-align: right;">培训名称：</td><td colspan="3">'+trainName+'</td></tr>'
					+'<tr height="40px"><td colspan="1" style="text-align: right;">公司名称：</td><td colspan="2">'+trainCompany+'</td>'
					+'<td style="text-align:right"><input style="display:none"/>'
					+'<label>修改<span class="small-button edit"></span></label><label>删除<span class="small-button delete"></span></label></td></tr>'
					+'<tr height="40px"><td style="text-align: right;">开始时间：</td><td>'+startTime+'</td><td style="text-align: right;">结束时间：</td><td>'+endTime+'</td></tr>'
					+'<tr height="40px"><td colspan="1"  style="text-align: right;">培训描述：</td>'
					+'<td colspan="3"><div style="width:100%">'+trainDescribe+'</div></td></tr>'
					+'</table><hr/>';
				$('#inviteAddrecords .train .display-train').append(html);
				$('#inviteAddrecords .train .train-table input').val(null);
				$('#inviteAddrecords .train .train-table textarea').val(null);
				$('#inviteAddrecords .train .display-train').find("table:last input").val(trainId);
				$('#inviteAddrecords .train .addexp').parent().css('display','block');
				$('#inviteAddrecords .train .updateexp').parent().css('display','none');
				//添加删除修改点击事件
				inviteAddrecordsAddTrainEven();
			}
		})
	})
	
	/******教育经历按钮点击事件，保存工教育经历，并且显示出保存成功的教育经历******/
	$('#inviteAddrecords .edu .addexp').click(function(){
		var userId = $('#inviteAddrecords input[name=userId]').val();
		var schoolName = $('#inviteAddrecords .edu input[name=schoolName]').val();
		var startTime = $('#inviteAddrecords .edu input[name=startTime]').val();
		var endTime = $('#inviteAddrecords .edu input[name=endTime]').val();
		var specialty = $('#inviteAddrecords .edu input[name=specialty]').val();
		if(userId == null || userId == ''){
			windowControl("请先保存员工基本信息");
			return ;
		}else if(schoolName == null || schoolName == ''){
			windowControl('学校名称不能为空');
			return ;
		}else if(startTime == null || startTime == ''){
			windowControl('入学时间不能为空');
			return ;
		}else if(endTime == null || endTime == ''){
			windowControl('毕业时间不能为空');
			return ;
		}else if(specialty == null || specialty == ''){
			windowControl('学习专业不能为空');
			return ;
		}
		$.ajax({
			url:'../../userInfo/saveEdu.do?getMs='+getMS(),
			type:'post',
			data:{
				userId:userId,
				schoolName:schoolName,
				startTimeStr:startTime,
				endTimeStr:endTime,
				specialty:specialty
			},
			success:function(data){
				if(data == 500 || data == 400){
					windowControl('添加教育经历失败');
				}else{	
					var html = '<table>'
						+'<tr><td width="240px"></td><td width="800px"></td><td width="800px"></td><td width="800px"></td></tr>'
						+'<tr height="40px"><td colspan="1" style="text-align: right;">学校名称：</td><td colspan="2">'+schoolName+'</td>'
						+'<td style="text-align:right"><input style="display:none"/>'
						+'<label>修改<span class="small-button edit"></span></label><label>删除<span class="small-button delete"></span></label></td></tr>'
						+'<tr height="40px"><td style="text-align: right;">入学时间：</td><td>'+startTime+'</td><td style="text-align: right;">毕业时间：</td><td>'+endTime+'</td></tr>'
						+'<tr height="40px"><td colspan="1" style="text-align: right;">专业名称：</td><td colspan="3">'+specialty+'</td></tr>'
						+'</table><hr/>';
					$('#inviteAddrecords .edu .display-edu').append(html);
					$('#inviteAddrecords .edu .edu-table input').val(null);
					$('#inviteAddrecords .edu .display-edu').find("table:last input").val(data);
					//添加删除修改点击事件
					inviteAddrecordsAddEduEven();
				}
			}
		});
	});
	
	
	/******教育经历按钮点击事件，修改工教育经历，并且显示出修改成功的教育经历******/
	$('#inviteAddrecords .edu .updateexp').click(function(){
		var userId = $('#inviteAddrecords input[name=userId]').val();
		var schoolName = $('#inviteAddrecords .edu input[name=schoolName]').val();
		var startTime = $('#inviteAddrecords .edu input[name=startTime]').val();
		var endTime = $('#inviteAddrecords .edu input[name=endTime]').val();
		var specialty = $('#inviteAddrecords .edu input[name=specialty]').val();
		var educationId = $('#inviteAddrecords .edu input[name=eduId]').val();
		if(userId == null || userId == ''){
			windowControl("请先保存员工基本信息");
			return ;
		}
		$.ajax({
			url:'../../userInfo/updateEdu.do?getMs='+getMS(),
			type:'post',
			data:{
				educationId:educationId,
				schoolName:schoolName,
				startTimeStr:startTime,
				endTimeStr:endTime,
				specialty:specialty
			},
			success:function(data){
				if(data == 500 || data == 400){
					windowControl('修改教育经历失败');
					return ;
				}else{
					var html = '<table>'
						+'<tr><td width="240px"></td><td width="800px"></td><td width="800px"></td><td width="800px"></td></tr>'
						+'<tr height="40px"><td colspan="1" style="text-align: right;">学校名称：</td><td colspan="2">'+schoolName+'</td>'
						+'<td style="text-align:right"><input style="display:none"/>'
						+'<label>修改<span class="small-button edit"></span></label><label>删除<span class="small-button delete"></span></label></td></tr>'
						+'<tr height="40px"><td style="text-align: right;">入学时间：</td><td>'+startTime+'</td><td style="text-align: right;">毕业时间：</td><td>'+endTime+'</td></tr>'
						+'<tr height="40px"><td colspan="1" style="text-align: right;">专业名称：</td><td colspan="3">'+specialty+'</td></tr>'
						+'</table><hr/>';
					$('#inviteAddrecords .edu .display-edu').append(html);
					$('#inviteAddrecords .edu .edu-table input').val(null);
					$('#inviteAddrecords .edu .display-edu').find("table:last input").val(educationId);
					$('#inviteAddrecords .edu .addexp').parent().css('display','block');
					$('#inviteAddrecords .edu .updateexp').parent().css('display','none');
					//添加删除修改点击事件
					inviteAddrecordsAddEduEven();
				}
			}
		});
	})
	
	/**************volk****************/
	$('#inviteAddrecords select[name=volk]').html(getDataBySelectKey("volk"));
	/**************education****************/
	$('#inviteAddrecords select[name=education]').html(getDataBySelectKey("education"));
	/**************marriageFlag****************/
	$('#inviteAddrecords select[name=marriageFlag]').html(getDataBySelectKey("marriage_flag"));
	/**************politicalStatus****************/
	$('#inviteAddrecords select[name=politicalStatus]').html(getDataBySelectKey("political_status"));
	/**************leaderFlag****************/
	$('#inviteAddrecords select[name=leaderFlag]').html(getDataBySelectKey("leader_flag"));
	
})
/**********************验证用户是否工作过，如果工作过，则导入************************/
function inviteRecordValidate(){
	
	var idCard = $('#inviteAddrecords .basicInformation input[name="idCard"]').val();
	if(idCard == ''|| idCard == null ){
		return ;
	}
	$.ajax({
		url:'../../userInfo/selectUserInfo.do?getMs='+getMS(),
		type:'post',
		data:{
			idCard:idCard
		},
		success:function(data){
			var id = '';
			var obj = eval('('+data+')').rows;
			if(obj.length > 0){
				windowControl('该员工曾在本公司工作或面试，档案已自动导入');
				id = obj[0].userId;
				$('#inviteAddrecords .basicInformation input[name="userAccount"]').val(obj[0].userAccount);
				$('#inviteAddrecords .basicInformation input[name="peopleName"]').val(obj[0].userName);
				$('#inviteAddrecords .basicInformation input[name="departmentName"]').val(obj.departmentName);
				$('#inviteAddrecords .basicInformation input[name="departmentUrl"]').val(obj.departmentUrl);
				$('#inviteAddrecords .basicInformation input[name="postName"]').val(obj.postName);
				$('#inviteAddrecords .basicInformation input[name="postId"]').val(obj.postId);
				$('#inviteAddrecords .basicInformation input[name="phoneNum"]').val(obj[0].phoneNum);
				$('#inviteAddrecords .basicInformation input[name="accidentPhoneNum"]').val(obj[0].accidentPhoneNum);
				$('#inviteAddrecords .basicInformation input[name="birthday"]').val(obj[0].birthdayStr);
				$('#inviteAddrecords .basicInformation input[name="idCard"]').val(obj[0].idCard);
				$('#inviteAddrecords .basicInformation select[name="sex"]').val(obj[0].sex);
				$('#inviteAddrecords .basicInformation input[name="workTime"]').val(obj[0].workTimeStr);
				$('#inviteAddrecords .basicInformation input[name="address"]').val(obj[0].address);
				$('#inviteAddrecords .basicInformation select[name="leaderFlag"]').val(obj[0].leaderFlag);
				$('#inviteAddrecords .basicInformation select[name="education"]').val(obj[0].education);
				$('#inviteAddrecords .basicInformation select[name="volk"]').val(obj[0].volk);
				
				/*******获取员工上级********/
				$.ajax({
					url:'../../lead/getLeader.do?getMs='+getMS(),
					type:'post',
					data:{
						userId:id
					},
					success:function(data){
						var obj = eval("("+data+")");
						$('#inviteAddrecords .basicInformation input[name=leadId]').val(obj.userId);
			        	$('#inviteAddrecords .basicInformation input[name=leadName]').val(obj.userName);
					}
				})
				
				/*******获取员工角色********/
				$.ajax({
					url:'../../role/queryUserRoleByUserId.do?getMs='+getMS(),
					type:'post',
					data:{
						userId:id
					},
					success:function(data){
						var obj = eval("("+data+")");
						var roleIds = '';
			    		var roleNames = '';
			        	for(var i = 0 ; i<obj.length ; i++ ){
			        		roleIds = roleIds + '"' + obj[i].roleId + '",';
			        		roleNames = roleNames + '"' + obj[i].roleName + '",';
			        	}
			        	roleIds = roleIds.substring(0, roleIds.length - 1);
			        	roleNames = roleNames.substring(0, roleNames.length - 1);
			        	$('#inviteAddrecords .basicInformation input[name=roleIds]').val(roleIds);
			        	$('#inviteAddrecords .basicInformation input[name=roleNames]').val(roleNames);
					}
				})
				
				/***获取工作信息****/
				$.ajax({
					url:'../../userInfo/selectWork.do?getMs='+getMS(),
					data:{
						userId:id
					},
					type:'post',
					success:function(data){
						var obj = eval('('+data+')').rows;
						for(var i = 0;i<obj.length;i++){
							var html = '<table>'
								+'<tr><td width="240px"></td><td width="800px"></td><td width="800px"></td><td width="800px"></td></tr>'
								+'<tr height="40px"><td colspan="1" style="text-align: right;">公司名称：</td><td colspan="2">'+obj[i].componyName+'</td>'
								+'<td style="text-align:right"><input style="display:none"/>'
								+'<label>修改<span class="small-button edit"></span></label><label>删除<span class="small-button delete"></span></label></td></tr>'
								+'<tr height="40px"><td style="text-align: right;">入职时间：</td><td>'+obj[i].startTimeStr+'</td><td style="text-align: right;">离职时间：</td><td>'+obj[i].endTimeStr+'</td></tr>'
								+'<tr height="40px"><td colspan="1" style="text-align: right;">岗位名称：</td><td colspan="3">'+obj[i].postName+'</td></tr>'
								+'<tr height="40px"><td colspan="1"  style="text-align: right;">岗位描述：</td>'
								+'<td colspan="3"><div style="width:100%">'+obj[i].workDescribe+'</div></td></tr>'
								+'</table>';
							html = html + '<hr/>';
							$('#inviteAddrecords .work .display-work').append(html);
							$('#inviteAddrecords .work .display-work').find("table:last input").val(obj[i].workId);
						}
						inviteAddrecordsAddWorkEven();
					}
				});
				
				/***获取培训信息****/
				$.ajax({
					url:'../../userInfo/selectTrain.do?getMs='+getMS(),
					data:{
						userId:id
					},
					type:'post',
					success:function(data){
						var obj = eval('('+data+')').rows;
						for(var i = 0;i<obj.length;i++){
							var html = '<table>'
								+'<tr><td width="240px"></td><td width="800px"></td><td width="800px"></td><td width="800px"></td></tr>'
								+'<tr height="40px"><td colspan="1" style="text-align: right;">培训名称：</td><td colspan="3">'+obj[i].trainName+'</td></tr>'
								+'<tr height="40px"><td colspan="1" style="text-align: right;">公司名称：</td><td colspan="2">'+obj[i].trainCompany+'</td>'
								+'<td style="text-align:right"><input style="display:none"/>'
								+'<label>修改<span class="small-button edit"></span></label><label>删除<span class="small-button delete"></span></label></td></tr>'
								+'<tr height="40px"><td style="text-align: right;">开始时间：</td><td>'+obj[i].startTimeStr+'</td><td style="text-align: right;">结束时间：</td><td>'+obj[i].endTimeStr+'</td></tr>'
								+'<tr height="40px"><td colspan="1"  style="text-align: right;">培训描述：</td>'
								+'<td colspan="3"><div style="width:100%">'+obj[i].trainDescribe+'</div></td></tr>'
								+'</table>';
							html = html + '<hr/>';
							$('#inviteAddrecords .train .display-train').append(html);
							$('#inviteAddrecords .train .display-train').find("table:last input").val(obj[i].trainDocId);
						}
						inviteAddrecordsAddTrainEven();
					}
				});
				
				/***获取教育信息****/
				$.ajax({
					url:'../../userInfo/selectEdu.do?getMs='+getMS(),
					data:{
						userId:id
					},
					type:'post',
					success:function(data){
						var obj = eval('('+data+')').rows;
						for(var i = 0;i<obj.length;i++){
							var html = '<table>'
								+'<tr><td width="240px"></td><td width="800px"></td><td width="800px"></td><td width="800px"></td></tr>'
								+'<tr height="40px"><td colspan="1" style="text-align: right;">学校名称：</td><td colspan="2">'+obj[i].schoolName+'</td>'
								+'<td style="text-align:right"><input style="display:none"/>'
								+'<label>修改<span class="small-button edit"></span></label><label>删除<span class="small-button delete"></span></label></td></tr>'
								+'<tr height="40px"><td style="text-align: right;">入学时间：</td><td>'+obj[i].startTimeStr+'</td><td style="text-align: right;">毕业时间：</td><td>'+obj[i].endTimeStr+'</td></tr>'
								+'<tr height="40px"><td colspan="1" style="text-align: right;">专业名称：</td><td colspan="3">'+obj[i].specialty+'</td></tr>'
								+'</table>';
							html = html + '<hr/>';
							$('#inviteAddrecords .edu .display-edu').append(html);
							$('#inviteAddrecords .edu .display-edu').find("table:last input").val(obj[i].educationId);
						}
						inviteAddrecordsAddEduEven();
					}
				});
			}
		}
	});
	
	
}

//为工作信息添加点击事件
function inviteAddrecordsAddWorkEven(){
	/************************修改点击事件***********************/
	$('#inviteAddrecords .work .display-work table label .edit').click(function(){
		var id = $(this).parent().parent().find('input').val();
		var thisDom = $(this);
		$.ajax({
			url:'../../userInfo/selectWork.do?getMs='+getMS(),
			type:"post",
			data:{
				workId:id
			},
			success:function(data){
				var obj = eval('('+data+')').rows[0];
				$('#inviteAddrecords .work input[name=workId]').val(obj.workId);
				$('#inviteAddrecords input[name=userId]').val(obj.userId);
				$('#inviteAddrecords .work input[name=postName]').val(obj.postName);
				$('#inviteAddrecords .work input[name=componyName]').val(obj.componyName);
				$('#inviteAddrecords .work input[name=startTime]').val(obj.startTimeStr);
				$('#inviteAddrecords .work input[name=endTime]').val(obj.endTimeStr);
				$('#inviteAddrecords .work textarea[name=workDescribe]').val(obj.workDescribe);
				$('#inviteAddrecords .work .addexp').parent().css('display','none');
				$('#inviteAddrecords .work .updateexp').parent().css('display','block');
				thisDom.parents('table').next().remove();
				thisDom.parents('table').remove();
			}
		});
	});
	
	/************************删除工作 点击事件***********************/
	$('#inviteAddrecords .work .display-work table label .delete').click(function(){
		var id = $(this).parent().parent().find('input').val();
		var thisDom = $(this);
		$.ajax({
			url:'../../userInfo/updateWork.do?getMs='+getMS(),
			type:'post',
			data:{
				workId:id,
				aliveFlag:'0'
			},
			success:function(data){
				if(data == 400 || data == 500){
					windowControl('删除工作信息失败');
					return ;
				}
				thisDom.parents('table').next().remove();
				thisDom.parents('table').remove();
			}
		});
	});
}

//为培训信息添加点击事件
function inviteAddrecordsAddTrainEven(){
	/************************修改点击事件***********************/
	$('#inviteAddrecords .train .display-train table label .edit').click(function(){
		var id = $(this).parent().parent().find('input').val();
		var thisDom = $(this);
		$.ajax({
			url:'../../userInfo/selectTrain.do?getMs='+getMS(),
			data:{
				trainDocId:id
			},
			type:'post',
			success:function(data){
				var obj = eval('('+data+')').rows[0];
				$('#inviteAddrecords .train input[name=trainId]').val(obj.trainDocId);
				$('#inviteAddrecords input[name=userId]').val(obj.userId);
				$('#inviteAddrecords .train input[name=trainName]').val(obj.trainName);
				$('#inviteAddrecords .train input[name=trainCompany]').val(obj.trainCompany);
				$('#inviteAddrecords .train input[name=startTime]').val(obj.startTimeStr);
				$('#inviteAddrecords .train input[name=endTime]').val(obj.endTimeStr);
				$('#inviteAddrecords .train textarea[name=trainDescribe]').val(obj.trainDescribe);
				$('#inviteAddrecords .train .addexp').parent().css('display','none');
				$('#inviteAddrecords .train .updateexp').parent().css('display','block');
				thisDom.parents('table').next().remove();
				thisDom.parents('table').remove();
			}
		})
	});
	
	/************************删除培训信息***********************/
	$('#inviteAddrecords .train .display-train table label .delete').click(function(){
		var id = $(this).parent().parent().find('input').val();
		var thisDom = $(this);
		$.ajax({
			url:'../../userInfo/updateTrain.do?getMs='+getMS(),
			type:'post',
			data:{
				trainDocId:id,
				aliveFlag:'0'
			},
			success:function(data){
				if(data == 400 || data == 500){
					windowControl('删除培训信息失败');
					return ;
				}
				thisDom.parents('table').next().remove();
				thisDom.parents('table').remove();
			}
		});
	});
}
//为教育信息添加点击事件
function inviteAddrecordsAddEduEven(){
	/************************修改点击事件***********************/
	$('#inviteAddrecords .edu .display-edu table label .edit').click(function(){
		var id = $(this).parent().parent().find('input').val();
		var thisDom = $(this);
		$.ajax({
			url:'../../userInfo/selectEdu.do?getMs='+getMS(),
			data:{
				educationId:id
			},
			type:'post',
			success:function(data){
				if(data == 400 || data == 500){
					windowControl('修改信息失败');
					return ;
				}
				var obj = eval('('+data+')').rows[0];
				$('#inviteAddrecords .edu input[name=eduId]').val(id);
				$('#inviteAddrecords .edu input[name=schoolName]').val(obj.schoolName);
				$('#inviteAddrecords .edu input[name=specialty]').val(obj.specialty);
				$('#inviteAddrecords .edu input[name=startTime]').val(obj.startTimeStr);
				$('#inviteAddrecords .edu input[name=endTime]').val(obj.endTimeStr);
				$('#inviteAddrecords .edu .addexp').parent().css('display','none');
				$('#inviteAddrecords .edu .updateexp').parent().css('display','block');
				thisDom.parents('table').next().remove();
				thisDom.parents('table').remove();
			},
		})
	});
	
	/************************删除教育 信息***********************/
	$('#inviteAddrecords .edu .display-edu table label .delete').click(function(){
		var id = $(this).parent().parent().find('input').val();
		var thisDom = $(this);
		$.ajax({
			url:'../../userInfo/updateEdu.do?getMs='+getMS(),
			type:'post',
			data:{
				educationId:id,
				aliveFlag:'0'
			},
			success:function(data){
				if(data == 400 || data == 500){
					windowControl('删除教育信息失败');
					return ;
				}
				thisDom.parents('table').next().remove();
				thisDom.parents('table').remove();
			}
		});
	});
}

/************当点击读取身份证时，对身份证中的信息进行读取**************/
$(function(){
	$('#inviteAddrecords input[name=read-idCard]').click(function(){
		var result;
		//注意：第一个参数为对应的设备端口，USB型为1001-1016，串口型为1至16
		//第二个参数为文件保存路径及文件名，当为“”时，保存在系统临时目录(由API GetTempPath获得)
		//ReadCard函数调用示例如下：
		result=WidControl.ReadCard(1001,"C:\\wid\\photo.bmp");
		if (result==1){
			$('#inviteAddrecords .basicInformation input[name="peopleName"]').val(WidControl.GetName());
			$('#inviteAddrecords .basicInformation select[name="sex"]').val(WidControl.GetSexN());
			var idCard = WidControl.GetCode();
			var birthday = idCard.substring(6, 10) + '-' + idCard.substring(10, 12) + '-' +idCard.substring(12, 14);
			$('#inviteAddrecords .basicInformation input[name="birthday"]').val(birthday);
			$('#inviteAddrecords .basicInformation input[name="idCard"]').val(idCard);
			inviteRecordValidate();
			$('#inviteAddrecords .basicInformation input[name="address"]').val(WidControl.GetAddress());
			$('#inviteAddrecords .basicInformation input[name="nativePlace"]').val(WidControl.GetAddress());
			if(WidControl.GetFolk() == '汉'){
				var volk = $('#inviteAddrecords .basicInformation select[name="volk"]').val(1);
			}else{
				$('#inviteAddrecords .basicInformation select[name="volk"]').val(2);
			}
			$('#inviteAddrecords .basicInformation .pic1').attr("src",'data:image/bmp;base64,' + WidControl.GetPhotobuf());
			$('#inviteAddrecords .basicInformation .pic2').attr("src",'data:image/bmp;base64,' + WidControl.GetFrontJPGBuf());
			$('#inviteAddrecords .basicInformation .pic3').attr("src",'data:image/bmp;base64,' + WidControl.GetBackJPGBuf());
			$('#inviteAddrecords .basicInformation input[name=pic1]').val(WidControl.GetPhotobuf());
			$('#inviteAddrecords .basicInformation input[name=pic2]').val(WidControl.GetFrontJPGBuf());
			$('#inviteAddrecords .basicInformation input[name=pic3]').val(WidControl.GetBackJPGBuf());
		}else{
			if (result==-1)
				windowControl("端口初始化失败！");
			if (result==-2)
				windowControl("请重新将卡片放到读卡器上！");
			if (result==-3)
				windowControl("读取数据失败！");
			if (result==-4)
				windowControl("生成照片文件失败，请检查设定路径和磁盘空间！");
			if(result == -5)
				windowControl("加载动态库失败");
		}
	});
})



