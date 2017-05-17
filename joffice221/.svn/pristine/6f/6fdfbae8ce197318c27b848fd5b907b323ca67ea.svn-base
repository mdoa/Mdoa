<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.htsoft.core.util.AppUtil"%>
<%@page import="com.htsoft.oa.service.info.NewsService"%>
<%@page import="com.htsoft.oa.service.info.impl.NewsServiceImpl"%>
<%@page import="com.htsoft.core.web.paging.PagingBean"%>
<%@page import="com.htsoft.oa.model.info.News"%>
<%@page import="com.htsoft.core.command.QueryFilter"%>
<%@page import="java.util.List"%>
<%
	Long newsId = null;
	String strId = request.getParameter("newsId");
	NewsService newsService = (NewsService) AppUtil.getBean("newsService");
	//通过id得到News
	News news = null;
	if (strId != null && !"".equals(strId)) {
		newsId = new Long(strId);
	}
	
	//使用页面的方法实现获取上一条,下一条的记录
	news = newsService.get(newsId);
	
	request.setAttribute("news",news);
	request.setAttribute("ctxPath",request.getContextPath());
	//浏览后的新闻浏览次数加1
	if(news.getViewCounts() == null){
		news.setViewCounts(0);
	}
	news.setViewCounts(news.getViewCounts()+1);
	newsService.save(news);
%>

<table width="98%" cellpadding="0" cellspacing="1" style="border: 5px 5px 5px 5px;">
	<tr>
		<td align="center" style="font:2.0em 宋体  ;color:black;font-weight: bold;padding:10px 0px 10px 0px; ">
			${news.subject }
			<input type="hidden" value="${news.newsId }" id="__curNewsId"/>
		</td>
	</tr>
	<tr>
		<td align="center" style="padding:0px 0px 10px 0px;">
			<font color="red">
				<fmt:formatDate value="${news.createtime}" pattern="yyyy-MM-dd HH:mm"/>
			</font>
			&nbsp;&nbsp;所属栏目:
			<font color="green">
				${news.section.sectionName}
				
			</font>
			&nbsp;&nbsp;浏览次数:
			<font color="red">
				${news.viewCounts }
			</font>
			&nbsp;&nbsp;回复次数:
			<font  color="red">
				<span id="replyCounts">${news.replyCounts}</span>
			</font>
		</td>
	</tr>
	
	<tr>
		<td style="border-top:dashed 1px #ccc;" height="28">
			
		</td>
	</tr>
	<c:if test="${news.isDeskImage==1}">
	<tr>
		<td align="center">
			<img src="${ctxPath}/attachFiles/${news.subjectIcon}"/>
		</td>
	</tr>
	</c:if>
	<tr >
		<td style="font:13px 宋体;color: black;line-height:24px;">
			${news.content}
		</td>
	</tr>
	<tr>
		<td align="right">
				(作者:
			<font color="green">
				${news.author}
			</font>
				&nbsp;&nbsp;发布人:
			<font color="green">
				${news.issuer}
			</font>
				 )
		</td>
	</tr>
	
</table>
