<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
</head>
<body>
       <table class="table-info" width="460"  >
         <tr ><th colspan="6" >基本资料</th></tr>
         <tr >
           <th>  联系人编号:</th>
           <td><s:property value="phoneBook.phoneId" /></td>
           <th> 姓名:</th><td><s:property value="phoneBook.fullname" /> </td>
           <th>  昵称:</th><td><s:property value="phoneBook.nickName" /></td>
        </tr>
        <tr> 
            <th>  称谓:</th><td><s:property value="phoneBook.title" /></td>
            <th>  生日:</th><td><fmt:formatDate value="${phoneBook.birthday}" type="date"/></td>
            <th>  共享人:</th><td><s:property value="phoneBook.appUser.fullname" /> </td>
         </tr>
	    <tr><th colspan="6" > 联系方式</th></tr>
        <tr>
          <th>手机:</th><td><s:property value="phoneBook.mobile" /></td>
         <th>QQ:</th><td><s:property value="phoneBook.qqNumber" /></td>
         <th>MSN:</th><td><s:property value="phoneBook.msn" /> </td>
       </tr>
       <tr>
          <th>Email:</th><td colspan="5"><s:property value="phoneBook.email" /></td>
      </tr>      
      <tr>
       		<th colspan="6">公司情况</th>
      </tr>
      <tr>
          <th> 公司名称:</th><td colspan="5"><s:property value="phoneBook.companyName" /></td>
      </tr>
      <tr>
          <th>公司地址:</th><td colspan="5"><s:property value="phoneBook.companyAddress" /></td>
      </tr>
      <tr>
          <th> 职位:</th><td><s:property value="phoneBook.duty" /></td>
          <th>公司传真:</th><td><s:property value="phoneBook.companyFax" /> </td>
          <th>公司电话:</th><td><s:property value="phoneBook.companyPhone" /></td>
      </tr>
      <tr>
      	   <th colspan="6">家庭情况</th>
      </tr>
      <tr>
       	   <th>家庭地址:</th><td colspan="5"><s:property value="phoneBook.homeAddress" /></td>
      </tr>
      <tr>
       	  <th>家庭邮编:</th><td><s:property value="phoneBook.homeZip" /></td>
          <th>配偶:</th><td><s:property value="phoneBook.spouse" /></td>
          <th>子女:</th><td><s:property value="phoneBook.childs" /> </td>
      </tr>
        <tr><th colspan="6">备注</th></tr>
        <tr><td colspan="6"><s:property value="phoneBook.note" /></td></tr>
      </table>
  </body>
 </html>