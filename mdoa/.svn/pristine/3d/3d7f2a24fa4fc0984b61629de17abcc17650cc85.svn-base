package com.mdoa.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mdoa.user.model.UserInfo;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		//获取访问的url
		String url = req.getRequestURL().toString();
		System.out.println("用户访问:"+url);
		//获取url中的最后一段
		String lastUrl = url.substring(url.lastIndexOf("/"));
		//如果访问的页面为登录页面，则放行
		if(lastUrl.equals("/login.html") || lastUrl.equals("/login.do")){
			chain.doFilter(request, response);
			return ;
		}
		if(lastUrl.equals("/login")){
			System.out.println("用户登录访问--ip:"+req.getHeader("x-forwarded-for"));
			String respUrl = "html/user/login.html";
			int length = url.split("/").length;
			for(int i = 0; i< length - 5 ; i++){
				respUrl = "../" + respUrl;
			}
			resp.sendRedirect(respUrl);
			return ;
		}
		//判断最后访问的是否是html，如果是html则进行拦截
		lastUrl = lastUrl.substring(lastUrl.lastIndexOf("."));
		if(lastUrl.equals(".html") || lastUrl.equals(".do")){
			HttpSession session = req.getSession();
			UserInfo user = (UserInfo)session.getAttribute("userInfo");
			if(user == null || user.getUserId() == null){
				System.out.println("用户非法访问--ip:"+req.getHeader("x-forwarded-for"));
				System.out.println("用户非法访问--url:"+url);
				//定义跳转的页面Url
				String respUrl = "html/user/login.html";
				int length = url.split("/").length;
				for(int i = 0; i< length - 5 ; i++){
					respUrl = "../" + respUrl;
				}
				resp.sendRedirect(respUrl);
				return ;
			}
		}
		chain.doFilter(request, response);
	}
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}
	
	
}
