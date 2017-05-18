$(function(){
	/*设置页面高100%*/
	$('#goodsreceiveForm').css('height',$('#loadarea').height()-31+'px');
	/*设置list的宽度*/
	$('#goodsreceiveForm .listForm').css('width',$('#loadarea').width()-202+'px');
	/*刷新*/
	$('#goodsreceiveForm .refresh').click(function(){
		$('#goodsreceiveForm .queryForm input').val('');
		$('#goodsreceiveForm .queryForm .query').val('查询');
		$('#goodsreceiveForm .queryForm select option:eq(0)').attr('selected',true);
		$('#goodsreceiveForm #goodsget').html('');
	});
	/*查询*/
	$('#goodsreceiveForm .query').click(function(){
		var startTime = $('#goodsreceiveForm .startTime').val();
		var endTime = $('#goodsreceiveForm .endTime').val();
		var departmentIds = '';
		var urls = goodsTypeIds;
		var goodsTypeUrls = '';
		for(var i=0;i<urls.length;i++){
			if(i == urls.length-1){
				goodsTypeUrls += urls[i];
			}else{
				goodsTypeUrls = goodsTypeUrls + urls[i] + ","
			}
		}
		goodsTypeUrls = $.trim(goodsTypeUrls);
		for(var i=0;i<$('#goodsreceiveForm .departmentBox').length;i++){
			var str = $('#goodsreceiveForm .departmentBox').eq(i).attr('dpId');
			departmentIds += str+',';
			if($('#goodsreceiveForm .departmentBox').eq(i).attr('class') == 'departmentBox alldepartment'){
				departmentIds = '';
			}
		}
		if(departmentIds == ''){
			var info = {
					goodsTypeUrls:goodsTypeUrls,
					startTime:startTime,
					endTime:endTime
				}
		}else{
			var info = {
					goodsTypeUrls:goodsTypeUrls,
					departmentIds:departmentIds,
					startTime:startTime,
					endTime:endTime
				}
		}
		//console.log(departmentIds);
		if((startTime == '' && endTime != '') || (startTime != '' && endTime == '')){
			windowControl('所选时间区间不能为空');
		}else{
			$.ajax({
				data:info,
				url:"../../reportForms/findGoodsReportByCondition?getMs="+getMS(),
				success:function(data){
					var jsonObject = JSON.parse(data);
					var data = jsonObject.datas,
					categories = jsonObject.categories,
					colors = Highcharts.getOptions().colors,
					departmentData = [],
				    goodsTypeData = [],
				    i,
				    j,
				    dataLen = data.length,
				    drillDataLen,
				    brightness;
					// 构建数据数组
					for (i = 0; i < dataLen; i += 1) {
					    // 添加部门数据
					    departmentData.push({
					        name: categories[i],
					        y: data[i].y,
					        color: colors[i]
					    });
					    // 添加物品类别数据
					    drillDataLen = data[i].drilldown.datas.length;
					    for (j = 0; j < drillDataLen; j += 1) {
					        brightness = 0.2 - (j / drillDataLen) / 5;
					        goodsTypeData.push({
					            name: data[i].drilldown.categories[j],
					            y: data[i].drilldown.datas[j],
					            color: Highcharts.Color(colors[i]).brighten(brightness).get()
					        });
					    }
					}
					// 创建图表
					$('#goodsget').highcharts({
						credits: { 
							enabled: false //不显示LOGO 
							},
					    chart: {
					        type: 'pie'
					    },
					    title: {
					        text: '该时间段内部门物品使用饼图'
					    },
					    subtitle: {
					        text: '内环为部门占比，外环为部门使用物品总额占比'
					    },
					    yAxis: {
					        title: {
					            text: '总百分比'
					        }
					    },
					    plotOptions: {
					        pie: {
					            shadow: false,
					            center: ['50%', '50%']
					        }
					    },
					    tooltip: {
					        valueSuffix: '元'
					    },
					    series: [{
					        name: '部门',
					        data: departmentData,
					        size: '60%',
					        dataLabels: {
					            formatter: function () {
					                return this.y > 5 ? this.point.name : null;
					            },
					            color: 'white',
					            distance: -30
					        }
					    }, {
					        name: '物品类别',
					        data: goodsTypeData,
					        size: '80%',
					        innerSize: '60%',
					        dataLabels: {
					            formatter: function () {
					                // 大于1则显示
					                return this.y > 1 ? '<b>' + this.point.name + ':</b> ' + this.y + '元'  : null;
					            }
					        }
					    }]
					}); 
				},
				fail:function(){
					windowControl("请求失败");
				}
			});
		}
	});
	/*选择部门*/
	$('#goodsreceiveForm .department').change(function(){
		var departmentName = $('#goodsreceiveForm .department option:selected').val();
		var departmentId = $('#goodsreceiveForm .department option:selected').attr('dpId');
		var str = '<span dpId="'+departmentId+'" class="departmentBox"><img src="../../img/index/turnoff.png" class="turnoff"/>'+departmentName+'</span>';
		if(departmentName == '全部部门'){
			str = '<span class="departmentBox alldepartment" dpId="'+departmentId+'"><img src="../../img/index/turnoff.png" class="turnoff"/>'+departmentName+'</span>';
			$('#goodsreceiveForm .department').parent().find('span').remove();
		}else if(departmentName != '全部部门'){
			$('#goodsreceiveForm .department').parent().find('.alldepartment').remove();
		}
		var box = $('#goodsreceiveForm .querybar .departmentBox');
		for(var i = 0;i<=box.length;i++){
			if((departmentName == box.eq(i).text())&&(i=box.length)){
				str = '';
			}
		}
		$('#goodsreceiveForm .department').parent().append(str);
		departmentBoxRemove();
	});
	/*移除全部部门*/
	departmentBoxRemove();
	/*--------------------------------tree------------------------------*/
	var goodsTypeIds=[];
	$('#goodsreceiveFormtg').tree({
        url: "../../treeController/queryTree.do?getMs="+getMS(),
        method:"post",
        animate: false,
        //checkbox : true,//是否显示复选框  
        cascadeCheck: false,
        dnd:true,
        onClick: function(node){
        	var goodsTypeUrl = $("#goodsreceiveForm .goodsTypeUrl").val(node.id);
        },
        onCollapse:function(node){
        	$("#goodsTypeUrl").val(node.id);
        	$.ajax({
        		data:{goodsTypeUrl:$("#goodsTypeUrl").val(),state:"onCollapse"},
    			url:"../../treeController/updateTreeState?getMs="+getMS(),
    			method:"post"
    		})
        },
        onExpand:function(node){
        	$("#goodsTypeUrl").val(node.id);
        	$.ajax({
        		data:{goodsTypeUrl:$("#goodsTypeUrl").val(),state:"onExpand"},
    			url:"../../treeController/updateTreeState?getMs="+getMS(),
    			method:"post"
    		})
        },
        onDrop: function(target,source,point){
        	var targetUrl = $('#goodsreceiveFormtg').tree('getNode', target).id;
        	var targetName = $('#goodsreceiveFormtg').tree('getNode', target).text;
        	//console.log("targetId="+targetId+"source="+source.text+"point="+point);
        	$.ajax({
    			data:{targetUrl:targetUrl,targetName:targetName,sourceUrl:source.id,sourceName:source.text,point:point},
    			url:"../../treeController/dragRepertoryType?getMs="+getMS(),
    			method:"post",
    			success: function(data){
    				$('#goodsreceiveFormtg').tree('reload');
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
       /* onContextMenu : function(e,node){
        	e.preventDefault();
        	var goodsTypeUrl = $('#goodsreceiveForm .goodsTypeUrl').val(node.id);
        	$('#goodsreceiveFormtg').tree('select', node.target);
    		$('#goodsreceiveFormmm').menu('show', {
    			left: e.pageX,
    			top: e.pageY
    		});
		}*/
    });
	
	//点击确定添加物品类
	/*$('#goodsreceiveForm .addWindow .quedingadd').click(function(){
		var goodsTypeName = $('#goodsreceiveForm .addWindow .goodsTypeName').val();
		var goodsTypeUrl = $("#goodsreceiveForm .goodsTypeUrl").val();
		
		if(goodsTypeName == null || goodsTypeName == ""){
			alert("请输入新增类名");
		}else{
			$.ajax({
				data:{goodsTypeUrl:goodsTypeUrl,goodsTypeName:goodsTypeName},
				url:"../../treeController/addRepertoryType?getMs="+getMS(),
				method:"post",
				success: function(data){
					$('#goodsreceiveFormtg').tree('reload');
					$('#goodsreceiveForm .addWindow').css('display','none');
					$('#goodsreceiveForm .addWindow input[type=text]').val('');
				}
			});
		}
	});
	//点击确定删除物品类
	$('#goodsreceiveForm .removeWindow .quedingremove').click(function(){
		var goodsTypeUrl = $("#goodsreceiveForm .goodsTypeUrl").val();
		$.ajax({
			data:{goodsTypeUrl:goodsTypeUrl},
			url:"../../treeController/removeRepertoryType?getMs="+getMS(),
			method:"post",
			success: function(data){
				$('#goodsreceiveFormtg').tree('reload');
				$('#goodsreceiveForm .removeWindow').css('display','none');
				$('#goodsreceiveForm .removeWindow input[type=text]').val('');
			}
		})
	});
	//点击确定修改物品类
	$('#goodsreceiveForm .editWindow .quedingedit').click(function(){
		var goodsTypeUrl = $("#goodsreceiveForm .goodsTypeUrl").val();
		var goodsTypeName = $('#goodsreceiveForm .editWindow .goodsTypeName').val();
		$.ajax({
			data:{goodsTypeUrl:goodsTypeUrl,goodsTypeName:goodsTypeName},
			url:"../../treeController/editRepertoryType?getMs="+getMS(),
			method:"post",
			success: function(data){
				$('#goodsreceiveFormtg').tree('reload');
				$('#goodsreceiveForm .editWindow').css('display','none');
				$('#goodsreceiveForm .editWindow input[type=text]').val('');
			}
		})
	});*/
});
/*---------------------------------tree函数-----------------------------------*/
/*function add(){
	$('#goodsreceiveForm .addWindow').css('display','block');
}
function remove(){
	$('#goodsreceiveForm .removeWindow').css('display','block');
}
function edit(){
	$('#goodsreceiveForm .editWindow').css('display','block');
}*/
/*显示所有部门*/
$.ajax({
	data:{},
	url:'../../repertory/selectGetDepartment.do?getMs='+getMS(),
	type:'post',
	success:function(data){
		var data = $.parseJSON(data);
		var str = '<option>全部部门</option>';
		for(var i=0;i<data.length;i++){
			str +='<option dpId="'+data[i].departmentId+'">'+data[i].departmentName+'</option>';
		}
		$('#goodsreceiveForm .department').html(str);
	},
	error:function(err){
		windowControl(err.status);
	}
})
/*移除所选部门*/
function departmentBoxRemove(){
	$('#goodsreceiveForm .querybar .departmentBox').hover(function(){
		$(this).find('.turnoff').css('display','block');
	},function(){
		$(this).find('.turnoff').css('display','none');
	});
	$('#goodsreceiveForm .querybar .departmentBox .turnoff').click(function(){
		$(this).parent().remove();
	});
}