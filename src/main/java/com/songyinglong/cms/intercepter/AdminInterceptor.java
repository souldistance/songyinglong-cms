package com.songyinglong.cms.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/** 
* @author 作者:SongYinglong
* @version 创建时间：2019年11月19日 下午8:06:22 
* 类功能说明 
*/
public class AdminInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession(false);
		if(session!=null) {
			Object admin = session.getAttribute("admin");
			if(admin!=null) {
				return true;
			}
		}
		request.setAttribute("message", "请先登录!");
		request.getRequestDispatcher("/WEB-INF/views/passport/login.jsp").forward(request, response);
		return false;
	}
}
