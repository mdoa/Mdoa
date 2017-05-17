<%@tag import="org.apache.commons.lang.StringUtils"%>
<%@tag import="com.htsoft.oa.model.flow.NodeNodeUserMapping"%>
<%@tag import="java.util.List"%>
<%@tag import="com.htsoft.core.util.AppUtil"%>
<%@tag import="com.htsoft.oa.service.flow.ProcessService"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="curUserId" required="true" type="java.lang.Long" rtexprvalue="true"%>
<%@attribute name="defId" required="true" type="java.lang.Long" rtexprvalue="true"%>
<%@attribute name="startNext" required="true" type="java.lang.String" rtexprvalue="true"%>
<%
	boolean isStartNext=false;
	if("true".equals(startNext)){
		isStartNext=true;
	}
	ProcessService processService=(ProcessService)AppUtil.getBean("processService");
	System.out.println("defId:"+defId + " curUserId:" + curUserId);
	List<NodeNodeUserMapping> nodeList= processService.getStartDefNodeUserMapping(defId,curUserId,isStartNext);
	request.setAttribute("nodeList",nodeList);
%>

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
											if(destTaskUserIds!=''){
												destTaskUserIds+=',';
											}
											var ck=lis[cn].firstChild;
											if(ck.checked){
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
			
			function validateForm(){
				var radios=document.getElementsByName('destName');
				if(radios!=null && radios.length>0){
					for(var i=0;i<radios.length;i++){
						if(radios[i].checked){
							return true;
						}
					}
				}
				return false;
			}
			
		</script>
		<form action="<%=request.getContextPath()%>/bpm/doStartProcess.do" method="post" onsubmit="return validateForm();">
				<input type="hidden" name="startNext" value="<%=startNext%>" />
				<input type="hidden" name="defId" value="<%=defId%>"/>
				<input type="hidden" name="startFlow" value="true"/>
				<input type="hidden" name="useTemplate" value="true"/>
				<c:forEach items="${nodeList}" var="nodeMap" varStatus="i">
				跳转路径:<br/>
					<div class="block">
						<input type="radio" name="destName" value="${nodeMap.nodeName}" <c:if test="${i.count==1}">checked="checked"</c:if>/> ${nodeMap.nodeName}<!-- 跳转的目标节点 -->
						<c:forEach items="${nodeMap.nodeUserMapping}" var="nodeUserMap">
							<ul>[${nodeUserMap.key}]
							<input type="hidden" name="destTaskName" value="${nodeUserMap.key}"/>
							<c:forEach items="${nodeUserMap.value}" var="appUser">
								<li class="item"><input type="checkbox" name="userId" checked="checked" value="${appUser.userId}"/>${appUser.fullname}</li>
							</c:forEach>
							</ul>
						</c:forEach> 
					</div>
				</c:forEach>
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
					<input type="button"  value="提交并启动流程表单" onclick="setFlowAssignId();this.form.submit();">
				</div>
				<div>
					流程示意图:
					<img src="<%=request.getContextPath()%>/jbpmImage?defId=<%=defId %>"/>
				</div>
		</form>
