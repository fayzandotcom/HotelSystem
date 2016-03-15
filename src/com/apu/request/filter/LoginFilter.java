package com.apu.request.filter;

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

import com.apu.obj.User;
import com.apu.util.Config;

public class LoginFilter implements Filter {
	
	private String[] excludedPages = null;
	private String[] adminPages = null;
	private String loginPage = null;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		try {
			String strExcludedPages = filterConfig.getInitParameter("excludedPages");
			excludedPages = strExcludedPages.split(",");
			
			String strAdminPages = filterConfig.getInitParameter("adminPages");
			adminPages = strAdminPages.split(",");
			
			Config appConfig = new Config();
			loginPage = appConfig.getAppContext() + filterConfig.getInitParameter("loginPage");
		} catch (Exception e) {
			e.printStackTrace();
		}
        
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletResponse oRes = (HttpServletResponse)res;
		HttpServletRequest oReq = (HttpServletRequest)req;
		HttpSession oSession = oReq.getSession();
		
		if(this.isExcludedPage(oReq)) {	//	request is for excluded pages, allow access
			System.out.println("request to excluded page");
			
		}else {	// request is for restricted page, check request...
			
			if(oSession.getAttribute("objUser")==null) { // session not available
				// redirect to login page
				System.out.println("redirect to login page");
				oRes.sendRedirect(loginPage);
				return;
			}
			
			if(isAdminPage(oReq)) {	// check if request is for admin page
				System.out.println("request for admin page");
				User objUser = (User) oSession.getAttribute("objUser");	// get user object
				if (!objUser.getRole().equalsIgnoreCase("admin")) { // check if role is admin
					// redirect to login page
					System.out.println("redirect to login page");
					oRes.sendRedirect(loginPage);
					return;
				}
			}
		}
		
		
		oRes.setHeader("Cache-Control","no-cache");
	    oRes.setHeader("Pragma","no-cache");
	    oRes.setDateHeader ("Expires", 0);
	    chain.doFilter(oReq, oRes);
		
	}
	
	@Override
	public void destroy() {
		
	}
	
	private boolean isExcludedPage(HttpServletRequest oReq){
		for (int i=0; i<excludedPages.length; i++) {	// loop to check if request is for excluded pages
			//to check if if it has more argument, to compare wildcard (*) Url
			String oPage = excludedPages[i].trim();
			//System.out.println(oPage);
			
			String strPagesWildCard = oPage.substring(oPage.lastIndexOf("/")+1);

			if (strPagesWildCard.equals("*")) {
				String strReqPagePrefix = oReq.getServletPath().substring(0, oReq.getServletPath().lastIndexOf("/"));
				String strExcludePagePrefix = oPage.substring(0, oPage.lastIndexOf("/"));
				//System.out.println("strReqPagePrefix:" + strReqPagePrefix);
				//System.out.println("strExcludePagePrefix:" + strExcludePagePrefix);

				if (strReqPagePrefix.equalsIgnoreCase(strExcludePagePrefix)) {
				 	return true;
				 	//System.out.println("True 1st");
				}
				
			}else {
				if(oReq.getServletPath().equals(oPage)){
						return true;
						//System.out.println("True 2nd");
				}
			}
		}
		
		return false;
	}
	
	private boolean isAdminPage(HttpServletRequest oReq){
		for (int i=0; i<adminPages.length; i++) {	// loop to check if request is for excluded pages
			//to check if if it has more argument, to compare wildcard (*) Url
			String oPage = adminPages[i].trim();
			//System.out.println(oPage);
			
			String strPagesWildCard = oPage.substring(oPage.lastIndexOf("/")+1);

			if (strPagesWildCard.equals("*")) {
				String strReqPagePrefix = oReq.getServletPath().substring(0, oReq.getServletPath().lastIndexOf("/"));
				String strExcludePagePrefix = oPage.substring(0, oPage.lastIndexOf("/"));
				//System.out.println("strReqPagePrefix:" + strReqPagePrefix);
				//System.out.println("strExcludePagePrefix:" + strExcludePagePrefix);

				if (strReqPagePrefix.equalsIgnoreCase(strExcludePagePrefix)) {
				 	return true;
				 	//System.out.println("True 1st");
				}
				
			}else {
				if(oReq.getServletPath().equals(oPage)){
						return true;
						//System.out.println("True 2nd");
				}
			}
		}
		
		return false;
	}

}
