$(function(){
	/*设置页面高100%*/
	$('#repertoryinout').css('height',$('#loadarea').height()-31+'px');
	/*设置list的宽度*/
	$('#repertoryinout .listForm').css('width',$('#loadarea').width()-203+'px');
	/*设置表格的高度*/
	$('#repertoryinoutdg').css('height',$('#repertoryinout .listForm').height()-31+'px');
	$(window).resize(function() {
		$('#repertoryinout').css('height',$('#loadarea').height()-31+'px');
		$('#repertoryinout .listForm').css('width',$('#loadarea').width()-202+'px');
	});
	/*表格数据的加载*/
	$('#repertoryinoutdg').datagrid({
		//url:'../../repertory/selectAllInOutRecord.do?getMs='+getMS(),
		toolbar:'#repertoryinout .queryForm',
	    pagination:true,
	    onLoadSuccess:function(data){
			$.ajax({
				url:'../../repertory/findSumInOutRecord.do?getMs='+getMS(),
				data:{goodsTypeUrl:$("#repertoryinout .goodsTypeUrl").val()},
				success:function(data){
					adjustsumBar('repertoryinout');
					addsumBarInOut('repertoryinout',data);
				},
				error:function(err){
				}
			})
		},
		columns:[[
 	        {checkbox:true},
 	       {field:'operation',title:'进出库',width:50,align:'center',formatter:function(value,row,index){
				if(row.operation == "outRecord"){
					return '出库'
				}else if(row.operation == "inRecord"){
					return '入库';
				}
 	       	}},
 	        {field:'goodsName',title:'物品名称',align:'center',width:100},
	        {field:'goodsSize',title:'物品规格',align:'center',width:100},
			{field:'totalNumber',title:'数量',align:'center',width:50},
			{field:'inNumber',title:'入库/出库数量',align:'center',width:120},
			{field:'goodsPositionName',title:'仓位',align:'center',width:100},
			{field:'unit',title:'单位',align:'center',width:50},
			{field:'pretaxAmount',title:'金额(元)',align:'center',width:100},
			{field:'taxAmount',title:'无税金额(元)',align:'center',width:100},
			{field:'operateUserName',title:'操作人/领取部门',align:'center',width:120,},
			{field:'putUserName',title:'进库人/领取人',align:'center',width:120},
			{field:'inTimeStr',title:'进出库时间',align:'center',width:150},
			{field:'record',title:'备注',align:'center',width:100},
			{field:'_op',title:'操作',width:100,align:'center',
				formatter:function(value,row,index){
					var op = row.operation;
					if(op == 'inRecord'){
						var id = "'"+row.inRecordId+"'";
						var op = "'"+op+"'";
						return '<span class="small-button edit" title="修改入库明细" onclick="inStreamUpdate('+id+')"></span>'
							+'<span class="small-button delete" title="删除入库明细" onclick="inStreamDel('+id+','+op+')"></span>';
					}else{
						var id = "'"+row.inRecordId+"'";
						var op = "'"+op+"'";
						return '<span class="small-button edit" title="修改出库明细" onclick="outStreamUpdate('+id+')"></span>'
							+'<span class="small-button delete" title="删除入库明细" onclick="outStreamDel('+id+','+op+')"></span>';
					}
					
			}}
	    ]],
	});
	/*条件查询*/
	$('#repertoryinout .query').click(function(){
		var goodsName = $.trim($('#repertoryinout .goodsName').val());
		var goodsSize = $.trim($('#repertoryinout .goodsSize').val());
		var goodsNumSm = $.trim($('#repertoryinout .goodsNumSm').val());
		var goodsNumBg = $.trim($('#repertoryinout .goodsNumBg').val());
		var goodsPositionName = $.trim($('#repertoryinout .goodsPosition').val());
		var putUserName = $.trim($('#repertoryinout .putUserName').val());
		var departmentId = $('#repertoryinout .listForm .departmentName ').val();
		var repertoryId = $('#repertoryinout .listForm .repertoryName').val();
		var beginTime = $.trim($('#repertoryinout .startTime').val());
		var endTime = $.trim($('#repertoryinout .endTime').val());
		var goodsTypeUrl = $("#repertoryinout .goodsTypeUrl").val();
		var dataInfo = {
				goodsName:goodsName,
				goodsSize:goodsSize,
				beginNumber:goodsNumSm,
				endNumber:goodsNumBg,
				goodsPositionName:goodsPositionName,
				putUserName:putUserName,
				getDepartmentId:departmentId,
				repertoryId:repertoryId,
				beginTime:beginTime,
				endTime:endTime,
				goodsTypeUrl:goodsTypeUrl||'0',
			};
		$('#repertoryinoutdg').datagrid({
			url:"../../repertory/selectAllInOutRecord?getMs="+getMS(),
			queryParams:dataInfo
		});
	});
	/*修改入库明细*/
	$('#repertoryinout .popups .inUpdate .confirm').click(function(){
		var inRecordId = $('#repertoryinout .inUpdate .putUserName').attr('inRecordId');
		var putUserName = $.trim($('#repertoryinout .inUpdate .putUserName').val());
		var totalNumber = $.trim($('#repertoryinout .inUpdate .totalNumber').val());
		var inNumber = $.trim($('#repertoryinout .inUpdate .inNumber').val());
		var pretaxAmount = $.trim($('#repertoryinout .inUpdate .pretaxAmount').val());
		var taxRate = $.trim($('#repertoryinout .inUpdate .taxRate').val());
		var pretaxAverPrice = $.trim($('#repertoryinout .inUpdate .pretaxAverPrice').val());
		var taxAmount = $.trim($('#repertoryinout .inUpdate .taxAmount').val());
		var taxAverPrice = $.trim($('#repertoryinout .inUpdate .taxAverPrice').val());
		var repertoryName = $.trim($('#repertoryinout .inUpdate .repertoryName').val());
		var record = $.trim($('#repertoryinout .inUpdate .record').val());
		var supplier = $.trim($('#repertoryinout .inUpdate .supplier').val());
		var goodsId = $.trim($('#repertoryinout .inUpdate .goodsId').val());
		var goodsPositionId = $.trim($('#repertoryinout .inUpdate .goodsPositionId').val());
		if(putUserName == ''||putUserName == null){
			windowControl('入库人不能为空');
			return false;
		}else if(inNumber == ''||inNumber == null){
			windowControl('入库数量不能为空');
			return false;
		}else if(pretaxAmount == ''||pretaxAmount == null){
			windowControl('入库总额(含税)不能为空');
			return false;
		}else if(taxRate == ''||taxRate == null){
			windowControl('税率不能为空');
			return false;
		}/*else if(supplier == ''||supplier == null){
			windowControl('供应商不能为空');
			return false;
		}*/else if(record == ''||record == null){
			windowControl('入库备注不能为空');
			return false;
		}else{
			var info ={
					inRecordId:inRecordId,
					putUserName:putUserName,
					inNumber:inNumber,
					goodsId:goodsId,
					goodsPositionId:goodsPositionId,
					pretaxAmount:pretaxAmount,
					taxRate:taxRate,
					pretaxAverPrice:pretaxAverPrice,
					taxAmount:taxAmount,
					repertoryName:repertoryName,
					record:record
			}
			$.ajax({
				data:info,
				url:'../../repertory/editGoodsInRecord.do?getMs='+getMS(),
				type:'post',
				success:function(data){
					if(data==500){
						windowControl('服务器异常');
					}else if(data==400){
						windowControl('修改出库明细失败');
					}else{
						windowControl('修改出库明细成功');
						$('#repertoryinout .popups .inUpdate').css('display','none');
						$('#repertoryinout .popups .inUpdate .popuparea input').val('');
						$('#repertoryinout .popups .inUpdate .popuparea textarea').val('');
						$('#repertoryinoutdg').datagrid('reload');
					}
				},
				error:function(err){
					windowControl(err.status);
				}
			});
		}
	});
	/*修改出库明细*/
	$('#repertoryinout .popups .outUpdate .confirm').click(function (){
		var outRecordId = $('#repertoryinout .outUpdate .repertoryName').attr('outRecordId');
		var repertoryName = $.trim($('#repertoryinout .outUpdate .repertoryName').val());
		var repertoryId = $('#repertoryinout .outUpdate .repertoryName').attr('repertoryId');
		var goodsPositionId = $('#repertoryinout .outUpdate .goodsPositionName').attr('goodsPositionId');
		var goodsPositionName = $.trim($('#repertoryinout .outUpdate .goodsPositionName').val());
		var getUserName = $.trim($('#repertoryinout .outUpdate .getUserName').val());
		var getDepartmentName = $('#repertoryinout .outUpdate .getDepartmentName option:selected').val();
		var getDepartmentId = $('#repertoryinout .outUpdate .getDepartmentName option:selected').val();
		var outNumber = $.trim($('#repertoryinout .outUpdate .outNumber').val());
		var taxAmount = $.trim($('#repertoryinout .outUpdate .taxAmount').val());
		var useType = $.trim($('#repertoryinout .outUpdate .useType option:selected').val());
		var record = $.trim($('#repertoryinout .outUpdate .record').val());
		var goodsId = $.trim($('#repertoryinout .outUpdate .goodsId').val());
		if(getUserName == null||getUserName == ''){
			windowControl('领取人不能为空');
			return false;
		}else if(outNumber == null||outNumber == ''){
			windowControl('领取数量不能为空');
			return false;
		}/*else if(useType == null||useType == ''){
			windowControl('领取用途不能为空');
			return false;
		}*/else if(record == null||record == ''){
			windowControl('备注不能为空');
			return false;
		}else{
			var info = {
				outRecordId:outRecordId,
				outNumber:outNumber,
				goodsId:goodsId,
				repertoryId:repertoryId,
				repertoryName:repertoryName,
				getDepartmentId:getDepartmentId,
				getDepartmentName:getDepartmentName,
				goodsPositionName:goodsPositionName,
				goodsPositionId:goodsPositionId,
				getUserName:getUserName,
				taxAmount:taxAmount,
				useType:useType,
				record:record,
			}
			$.ajax({
				data:info,
				url:'../../repertory/editGoodsOutRecord.do?getMs='+getMS(),
				type:'post',
				success:function(data){
					if(data==500){
						windowControl('服务器异常');
						
					}else if(data==400){
						windowControl('修改出库失败');
					}else{
						windowControl('修改出库成功');
						$('#repertoryinout .popups .outUpdate').css('display','none');
						$('#repertoryinout .popups .outUpdate .popuparea input').val('');
						$('#repertoryinout .popups .outUpdate .popuparea textarea').val('');
						$('#repertoryinoutdg').datagrid("reload");
					}
				},
				error:function(err){
					windowControl('网络异常');
				}
			});
		}
	});
	/*刷新*/
	$('#repertoryinout .refresh').click(function(){
		$('#repertoryinoutdg').datagrid('reload');
		$('#repertoryinout .queryForm input').val('');
		$('#repertoryinout .query').val('查询');
		$('#repertoryinout .queryForm select option:eq(0)').attr('selected',true);
	});
	/*删除弹窗*/
	$('#repertoryinout .popups .del .confirm').click(function(){
		var op = $('#repertoryinout .popups .del h3').attr('op');
		var record = $('#repertoryinout .popups .del h3').attr('record');
		if(op == 'inRecord'){
			$.ajax({
				url:'../../repertory/deleteInRecord.do?getMs='+getMS(),
				data:{inRecordId:record},
				type:'post',
				success:function(data){
					if(data == 500){
						windowControl('服务器异常');
					}else if(data==400){
						windowControl('删除失败');
					}else{	
						windowControl('删除成功');
						$('#repertoryinoutdg').datagrid("reload");
						$('#repertoryinout .popups .del').css('display','none');
					}
				},
				error:function(err){
					windowControl(err.status);
				}
			});
		}else{
			$.ajax({
				url:'../../repertory/deleteOutRecord.do?getMs='+getMS(),
				data:{outRecordId:record},
				type:'post',
				success:function(data){
					if(data == 500){
						windowControl('服务器异常');
					}else if(data==400){
						windowControl('删除失败');
					}else{	
						windowControl('删除成功');
						$('#repertoryinoutdg').datagrid("reload");
						$('#repertoryinout .popups .del').css('display','none');
					}
				},
				error:function(err){
					windowControl(err.status);
				}
			});
		}
	});
	//显示隐藏汇总bar
	$('#repertoryinout .controlSumBar').click(function(){
		if($(this).val() == '隐藏'){
			$('#repertoryinout .sumBar').animate({
				'bottom':'-=30px',
				'opacity':0,
			},500,function(){
				$('#repertoryinout .controlSumBar').val('显示');
			});
		}else{
			$('#repertoryinout .sumBar').animate({
				'bottom':'+=30px',
				'opacity':1,
			},500,function(){
				$('#repertoryinout .controlSumBar').val('隐藏');
			});
		}
	});
	/*--------------------------------tree------------------------------*/
	var goodsTypeIds=[];
	$('#repertoryinouttg').tree({
        url: "../../treeController/queryTree.do?getMs="+getMS(),
        method:"post",
        animate: false,
        checkbox : false,//是否显示复选框  
        cascadeCheck: false,
        dnd:true,
        onClick: function(node){
        	//var goodsTypeUrl = node.id;
        	$("#repertoryinout .goodsTypeUrl").val(node.id);
        	/*$('#repertoryinoutdg').datagrid({
        		url:'../../repertory/selectAllInOutRecord.do?getMs='+getMS(),
        		queryParams:{goodsTypeUrl:goodsTypeUrl},
        	});*/
        },
        onCollapse:function(node){
        	$("#repertoryinout .goodsTypeUrl").val(node.id);
        	$.ajax({
        		data:{goodsTypeUrl:$("#goodsTypeUrl").val(),state:"onCollapse"},
    			url:"../../treeController/updateTreeState?getMs="+getMS(),
    			method:"post"
    		})
        },
        onExpand:function(node){
        	$("#repertoryinout .goodsTypeUrl").val(node.id);
        	$.ajax({
        		data:{goodsTypeUrl:$("#goodsTypeUrl").val(),state:"onExpand"},
    			url:"../../treeController/updateTreeState?getMs="+getMS(),
    			method:"post"
    		})
        },
        onDrop: function(target,source,point){
        	var targetUrl = $('#repertoryinouttg').tree('getNode', target).id;
        	var targetName = $('#repertoryinouttg').tree('getNode', target).text;
        	//console.log("targetId="+targetId+"source="+source.text+"point="+point);
        	$.ajax({
    			data:{targetUrl:targetUrl,targetName:targetName,sourceUrl:source.id,sourceName:source.text,point:point},
    			url:"../../treeController/dragRepertoryType?getMs="+getMS(),
    			method:"post",
    			success: function(data){
    				$('#repertoryinouttg').tree('reload');
    			}
    		})
        },
        onCheck: function(node,checked){
        	if(checked == true){
        		goodsTypeIds.push(node.id);
        	}else if(checked == false){
        		for(var i=0;i<goodsTypeIds.length;i++){
        			if(goodsTypeIds[i] == node.id){
        				goodsTypeIds.splice(i,1);
        			}
        		}
        	}
        },
        /*onContextMenu : function(e,node){
        	e.preventDefault();
        	var goodsTypeUrl = $('#repertoryinout .goodsTypeUrl').val(node.id);
        	$('#repertoryinouttg').tree('select', node.target);
    		$('#repertoryinoutmm').menu('show', {
    			left: e.pageX,
    			top: e.pageY
    		});
		}*/
    });
	
	//点击确定添加物品类
	/*$('#repertoryinout .addWindow .quedingadd').click(function(){
		var goodsTypeName = $('#repertoryinout .addWindow .goodsTypeName').val();
		var goodsTypeUrl = $("#repertoryinout .goodsTypeUrl").val();
		
		if(goodsTypeName == null || goodsTypeName == ""){
			alert("请输入新增类名");
		}else{
			$.ajax({
				data:{goodsTypeUrl:goodsTypeUrl,goodsTypeName:goodsTypeName},
				url:"../../treeController/addRepertoryType?getMs="+getMS(),
				method:"post",
				success: function(data){
					$('#repertoryinouttg').tree('reload');
					$('#repertoryinout .addWindow').css('display','none');
					$('#repertoryinout .addWindow input[type=text]').val('');
				}
			});
		}
	});
	//点击确定删除物品类
	$('#repertoryinout .removeWindow .quedingremove').click(function(){
		var goodsTypeUrl = $("#repertoryinout .goodsTypeUrl").val();
		$.ajax({
			data:{goodsTypeUrl:goodsTypeUrl},
			url:"../../treeController/removeRepertoryType?getMs="+getMS(),
			method:"post",
			success: function(data){
				$('#repertoryinouttg').tree('reload');
				$('#repertoryinout .removeWindow').css('display','none');
				$('#repertoryinout .removeWindow input[type=text]').val('');
			}
		})
	});
	//点击确定修改物品类
	$('#repertoryinout .editWindow .quedingedit').click(function(){
		var goodsTypeUrl = $("#repertoryinout .goodsTypeUrl").val();
		var goodsTypeName = $('#repertoryinout .editWindow .goodsTypeName').val();
		$.ajax({
			data:{goodsTypeUrl:goodsTypeUrl,goodsTypeName:goodsTypeName},
			url:"../../treeController/editRepertoryType?getMs="+getMS(),
			method:"post",
			success: function(data){
				$('#repertoryinouttg').tree('reload');
				$('#repertoryinout .editWindow').css('display','none');
				$('#repertoryinout .editWindow input[type=text]').val('');
			}
		})
	});*/
});
/*************************下拉框选择部门信息********************************/
$.ajax({
	url:'../../repertory/selectGetDepartment.do?getMs='+getMS(),
	type:'post',
	success:function(data){
		var str = "<option value=''></option>";
		var data = eval('(' + data + ')'); 
		for(var i=0;i<data.length;i++){
			str += "<option class='departmentName' value=" + data[i].departmentId + ">" + data[i].departmentName + "</option>";  
		}
	$('#repertoryinout .listForm .departmentName').html(str);
	},
	error:function(error){
		windowControl(error.status);
	}
})
/*************************下拉框选择仓库信息********************************/
$.ajax({
	url:'../../repertory/selectRepertoryType.do?getMs='+getMS(),
	type:'post',
	success:function(data){
		var str = "<option value=''></option>";
		var data = eval('(' + data + ')'); 
		for(var i=0;i<data.length;i++){
			str += "<option class='repertoryName' value=" + data[i].repertoryId + ">" + data[i].repertoryName + "</option>";  
		}
	$('#repertoryinout .listForm .repertoryName').html(str);
	},
	error:function(error){
		windowControl(error.status);
	}
})
/*---------------------------------tree函数-----------------------------------*/
/*function add(){
	$('#repertoryinout .addWindow').css('display','block');
}
function remove(){
	$('#repertoryinout .removeWindow').css('display','block');
}
function edit(){
	$('#repertoryinout .editWindow').css('display','block');
}*/
/*----------操作函数-------------*/
/*修改入库明细*/
function inStreamUpdate(id){
	$('#repertoryinout .popups .inUpdate').css('display','block');
	$.ajax({
		data:{inRecordId:id},
		url:'../../repertory/selectGoodsInRecord.do?getMs='+getMS(),
		type:'post',
		success:function(data){
			var data = $.parseJSON(data);
			for(var i=0;i<data.rows.length;i++){
				if(data.rows[i].inRecordId == id){
					$('#repertoryinout .inUpdate .putUserName').attr('inRecordId',id);
					$('#repertoryinout .inUpdate .putUserName').val(data.rows[i].putUserName);
					$('#repertoryinout .inUpdate .totalNumber').val(data.rows[i].totalNumber);
					$('#repertoryinout .inUpdate .inNumber').val(data.rows[i].inNumber);
					$('#repertoryinout .inUpdate .goodsId').val(data.rows[i].goodsId);
					$('#repertoryinout .inUpdate .goodsPositionId').val(data.rows[i].goodsPositionId);
					$('#repertoryinout .inUpdate .pretaxAmount').val(data.rows[i].pretaxAmount);
					$('#repertoryinout .inUpdate .taxRate').val(data.rows[i].taxRate);
					cal();
					$('#repertoryinout .inUpdate .goodsPositionName').attr('goodsPositionId',data.rows[i].goodsPositionId);
					$('#repertoryinout .inUpdate .goodsPositionName').val(data.rows[i].goodsPositionName);
					$('#repertoryinout .inUpdate .pretaxAverPrice').val(data.rows[i].pretaxAverPrice);
					$('#repertoryinout .inUpdate .taxAmount').val(data.rows[i].taxAmount);
					$('#repertoryinout .inUpdate .taxAverPrice').val(data.rows[i].taxAverPrice);
					$('#repertoryinout .inUpdate .repertoryName').val(data.rows[i].repertoryName);
					$('#repertoryinout .inUpdate .record').val(data.rows[i].record);
				}
			}
		},
		error:function(err){
			windowControl(err.status);
		}
	});
	/*计算总价与均价*/
	$('#repertoryinout .inUpdate .pretaxAmount').blur(function(){
		cal();
	});
	$('#repertoryinout .inUpdate .taxRate').blur(function(){
		cal();
	});
	$('#repertoryinout .inUpdate .inNumber').blur(function(){
		cal();
	});
	
}
/*删除入库明细*/
function inStreamDel(id,op){
	$('#repertoryinout .popups .del').css('display','block');
	$('#repertoryinout .popups .del h3').attr('op',op);
	$('#repertoryinout .popups .del h3').attr('record',id);
}
/*修改出库明细*/
function outStreamUpdate(id){
	$('#repertoryinout .popups .outUpdate').css('display','block');
	$.ajax({
		data:{},
		url:'../../repertory/selectGoodsOutRecord.do?getMs='+getMS(),
		type:'post',
		success:function(data){
			var data = $.parseJSON(data);
			for(var i=0;i<data.rows.length;i++){
				if(data.rows[i].outRecordId == id){
					$('#repertoryinout .outUpdate .repertoryName').attr('outRecordId',id);
					$('#repertoryinout .outUpdate .repertoryName').val(data.rows[i].repertoryName);
					$('#repertoryinout .outUpdate .goodsPositionName').attr('goodsPositionId',data.rows[i].goodsPositionId);
					$('#repertoryinout .outUpdate .goodsPositionName').val(data.rows[i].goodsPositionName);
					$('#repertoryinout .outUpdate .getUserName').val(data.rows[i].getUserName);
					$('#repertoryinout .outUpdate .outNumber').val(data.rows[i].outNumber);
					$('#repertoryinout .outUpdate .goodsId').val(data.rows[i].goodsId);
					$('#repertoryinout .outUpdate .taxAmount').val(data.rows[i].taxAmount);
					$('#repertoryinout .outUpdate .taxRate').val(data.rows[i].taxRate);
					$('#repertoryinout .outUpdate .record').val(data.rows[i].record);
					var department = $('#repertoryinout .outUpdate .getDepartmentName');
					var name = data.rows[i].getDepartmentName;
					if(data.rows[i].getDepartmentName){
						for(var i=0;i < department.find('option').length;i++){
							if(department.find('option').eq(i).val() == name){
								department.find('option').eq(i).attr('selected','true');
								return false;
							}
						}
						return false;
					}
					return false;
				}
			}
		},
		error:function(err){
			windowControl(err.status);
		}
	});
}
/*删除入库明细*/
function outStreamDel(id,op){
	$('#repertoryinout .popups .del').css('display','block');
	$('#repertoryinout .popups .del h3').attr('record',id);
}
/*计算总价与均价函数*/
function cal(){
	var pretaxAmount = $.trim($('#repertoryinout .inUpdate .pretaxAmount').val());
	var taxRate = $.trim($('#repertoryinout .inUpdate .taxRate').val());
	var inNumber = $.trim($('#repertoryinout .inUpdate .inNumber').val());
	if(pretaxAmount != '' && taxRate != ''){
		if(inNumber == ''){
			$('#repertoryinout .inUpdate .pretaxAverPrice').val(pretaxAmount);
			$('#repertoryinout .inUpdate .taxAmount').val(pretaxAmount*(1-(taxRate/100)));
			$('#repertoryinout .inUpdate .taxAverPrice').val(pretaxAmount*(1-(taxRate/100)));
		}else{
			$('#repertoryinout .inUpdate .pretaxAverPrice').val(pretaxAmount/inNumber);
			$('#repertoryinout .inUpdate .taxAmount').val(pretaxAmount*(1-(taxRate/100)));
			$('#repertoryinout .inUpdate .taxAverPrice').val((pretaxAmount*(1-(taxRate/100)))/inNumber);
		}
	}
}
/*------汇总--------*/
function addsumBarInOut(id,data){
	$('#'+id).find('.sumBar').css({
		'width':$('#'+id).find('.datagrid-header-inner:eq(1) .datagrid-htable').width()+'px',
	});
	var node = $('#repertoryinouttg').tree('getSelected');
	var str = '<table><tr>';
	data = $.parseJSON(data);
	str +='<td style="width:100px">物品类别：</td>';
	str +='<td style="width:100px">'+node.text+'</td>';
	str +='<td style="width:100px">入库数量：</td>';
	str +='<td style="width:100px">'+data.inTotalNumber+'</td>';
	str +='<td style="width:100px">入库金额(无税)：</td>';
	str +='<td style="width:100px">'+data.inTotalPretaxAmount+'</td>';
	str +='<td style="width:100px">入库金额(税后)：</td>';
	str +='<td style="width:100px">'+data.inTotalTaxAmount+'</td>';
	str +='<td style="width:100px">出库数量：</td>';
	str +='<td style="width:100px">'+data.outTotalNumber+'</td>';
	str +='<td style="width:100px">出库金额：</td>';
	str +='<td style="width:100px">'+data.outTotalTaxAmount+'</td>';
	str += '</tr></table>';
	$('#'+id).find('.sumBar').html(str);
}
