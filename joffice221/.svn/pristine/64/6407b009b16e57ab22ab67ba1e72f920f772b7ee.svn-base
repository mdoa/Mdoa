<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="curUserId" required="true" type="java.lang.Long" rtexprvalue="true"%>
<%@attribute name="taskId" required="true" type="java.lang.Long" rtexprvalue="true"%>
		<style type="text/css">
			.item{
				display: inline;
			}
			.block{
				border-bottom:solid 1px blue;
				margin-top: 2px;
				padding:4px 4px 4px 4px; 
			}
		</style>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/util/jquery.js"></script>
		<script type="text/javascript">
			//设置下一步任务及其执行人员
	//设置下一步任务及其执行人员
			function setFlowAssignId(){
					var flowAssignId=document.getElementById('flowAssignId');
					var radios=document.getElementsByName('destName');
					
					var destTaskArr=[];
					var destTaskUserIdArr=[];
					
					for(var i=0;i<radios.length;i++){
						var rd=radios[i];
						if(rd.checked){
							var uls=$(rd).siblings();
							if(uls.length>0){
								for(var j=0;j<uls.length;j++){
									//目标任务执行人ID,格式如 1,2,3
									var destTaskUserIds='';
									var ul=uls[j];
									var lis=ul.childNodes;
									var isUserExist=false;
									for(var cn=0;cn<lis.length;cn++){
										if(lis[cn].nodeName=='LI'){
											var ck=lis[cn].firstChild;
											if(ck.checked){
												if(destTaskUserIds!=''){
													destTaskUserIds+=',';
												}
												destTaskUserIds+=ck.value;
												isUserExist=true;
											}
										}
									}
									if(isUserExist){
										destTaskArr[j]=lis[1].value;
										destTaskUserIdArr[j]=destTaskUserIds;
									}
								}
							}
						}
					}
					
					var names='';
					var ids='';
					for(var k=0;k<destTaskArr.length;k++){
						if(names!=''){
							names+=':';
						}
						if(ids!=''){
							ids+=':';
						}
						if(destTaskArr[k]){
							names+=destTaskArr[k];
							ids+=destTaskUserIdArr[k];
						}
					}
					flowAssignId.value=names+'|'+ids;
			}
			
			function setJumpType(type){
				if(type==0){//正常跳转
					$('#jumpDiv').load('<%=request.getContextPath()%>/bpm/pages/bpm/jumpUsers.jsp?taskId=<%=taskId%>&curUserId=<%=curUserId%>&randId='+Math.random());
					$('#freeJumpDiv').html('');
				}else{
					$('#freeJumpDiv').load('<%=request.getContextPath()%>/bpm/pages/bpm/freeJumpCombo.jsp?taskId=<%=taskId%>&curUserId=<%=curUserId%>&randId='+Math.random());
					$('#jumpDiv').html('');
				}
			}
			
			$(function(){
				var value=$('#jumpType0').attr('checked');
				if(value){
					$('#jumpDiv').load('<%=request.getContextPath()%>/bpm/pages/bpm/jumpUsers.jsp?taskId=<%=taskId%>&curUserId=<%=curUserId%>');
				}else{
					$('#freeJumpDiv').load('<%=request.getContextPath()%>/bpm/pages/bpm/freeJumpCombo.jsp?taskId=<%=taskId%>&curUserId=<%=curUserId%>');
				}
			});
			//自由跳转下拉框的跳转
			function changeDest(combo){
				if(combo.value!=''){
					$('#jumpDiv').load('<%=request.getContextPath()%>/bpm/pages/bpm/jumpUsers.jsp',
							  {
							    	taskId:'<%=taskId%>',
							    	curUserId:'<%=curUserId%>',
							    	destName:combo.value
							  }
					);//end of load
				}
			}
			
			function jumpBack(btn){
				document.getElementById('back').setValue('true');
				btn.form.submit();
			}
			
		</script>
		<form action="<%=request.getContextPath()%>/bpm/doNextProcess.do" method="post" >
				<input type="hidden" name="taskId" value="<%=taskId%>"/>
				<input type="hidden" name="useTemplate" value="true"/>
				<!-- 是否退回 -->
				<input type="hidden" id="back" name="back" value="false"/>
				<div>
					跳转方式：&nbsp;&nbsp;
					<input type="radio" id="jumpType0" name="jumpType" onclick="setJumpType(0)" checked="checked" value="0"/> 正常跳转 &nbsp;&nbsp;
					<input type="radio" id="jumpType1" name="jumpType" onclick="setJumpType(1)" value="1"/> 自由跳转
				</div>
				<div id="freeJumpDiv">
				</div>
				<div id="jumpDiv">
					
				</div>
				<div>					
					<jsp:doBody/>
				</div>
				<div>
					审批意见:
					<textarea rows="5" cols="40" name="comments"></textarea>
				</div>
				<div>
					<!-- 用于保存流程下一步对应的节点的执行人员 ,格式为:节点1:节点2|节点1执行人IDs:节点2执行IDs,其中节点IDs值格式如1,2,3    -->
					<input type="hidden" name="flowAssignId" id="flowAssignId" value=""/>
					<input type="button"  value="执行流程下一步" onclick="setFlowAssignId();this.form.submit();">
					
					<input type="button" value="退回" onclick="jumpBack(btn)"/>
				</div>
				<div>
					流程审批历史：
					<jsp:include page="/pages/flow/processRunDetail.jsp">
						<jsp:param value="<%=taskId %>" name="taskId"/>
					</jsp:include>
					
				</div>
				<div>
					流程图:
					<img src="<%=request.getContextPath()%>/jbpmImage?taskId=<%=taskId %>"/>
				</div>
		</form>
