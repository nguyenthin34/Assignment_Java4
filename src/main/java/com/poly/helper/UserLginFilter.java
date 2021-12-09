package com.poly.helper;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poly.entity.User;
@WebFilter({ "/UserServlet/*"})
public class UserLginFilter implements Filter{
	 @Override
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
	        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
	        HttpSession session = httpServletRequest.getSession();
	        String uri = httpServletRequest.getRequestURI();
	        User user = (User) session.getAttribute("user");
	        if (user == null){
//	        	if(uri.contains("UserServlet")) {
	            httpServletRequest.setAttribute("message", "Bạn chưa đăng nhập");
	            httpServletRequest.setAttribute("views", "signin.jsp");
	            httpServletRequest.getRequestDispatcher("/views/home.jsp").forward(httpServletRequest, httpServletResponse);
//	        	}else {
//	            	httpServletRequest.setAttribute("views", "signin.jsp");
//	                httpServletRequest.getRequestDispatcher("/admin/home.jsp").forward(httpServletRequest, httpServletResponse);
//	        	}
	        }else {
	            if(uri.contains("/AdminServlet/")){
	                if (user.getAdmins()){
	                    chain.doFilter(request, response);
	                }else {
	                	httpServletRequest.setAttribute("message", "You Can't Login Here!!!");
	                	httpServletRequest.setAttribute("views", "signin.jsp");
	                    httpServletRequest.getRequestDispatcher("/admin/home.jsp").forward(httpServletRequest, httpServletResponse);
	                }
	            }else {
	                chain.doFilter(request, response);
	            }
	        }
	    }

	    @Override
	    public void init(FilterConfig filterConfig) throws ServletException {
	        Filter.super.init(filterConfig);
	    }

	    @Override
	    public void destroy() {
	        Filter.super.destroy();
	    }
}
