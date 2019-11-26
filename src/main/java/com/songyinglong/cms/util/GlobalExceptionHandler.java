package com.songyinglong.cms.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.songyinglong.cms.exception.CMSAjaxException;
import com.songyinglong.cms.exception.CMSException;

/** 
* @author 作者:SongYinglong
* @version 创建时间：2019年11月22日 下午3:11:14 
* 类功能说明  全局异常处理类  ControllerAdvice注解  根据 springmvc.xml文件中的component-scan base-package下扫描的包进行拦截 如果有异常则交给该类处理
*/
@ControllerAdvice
public class GlobalExceptionHandler {
	
	/**
	 * 
	 * @Title: CMSAjaxException 
	 * @Description: ajax异常处理
	 * @param cmsAjaxException
	 * @return
	 * @return: Result
	 */
	@ResponseBody
	@ExceptionHandler(CMSAjaxException.class)
	public Result CMSAjaxException(CMSAjaxException cmsAjaxException) {
		
		return ResultUtil.error(cmsAjaxException.getCode(), cmsAjaxException.getMessage());
	}

	/**
	 * 
	 * @Title: CMSExceptionHandler 
	 * @Description: 普通请求时 发生的异常处理
	 * @param cmsException
	 * @param request
	 * @return
	 * @return: String
	 */
	@ExceptionHandler(CMSException.class)
	public String CMSExceptionHandler(CMSException cmsException,HttpServletRequest request) {
		request.setAttribute("message", cmsException.getMessage());
		return request.getRequestURI();
	}
	
	/**
	 * 
	 * @Title: CMSExceptionHandler 
	 * @Description: 除自定义异常以外产生的异常
	 * @param exception
	 * @param request
	 * @return
	 * @return: String
	 */
	public String CMSExceptionHandler(Exception exception,HttpServletRequest request) {
		request.setAttribute("message", exception.getMessage());
		return request.getRequestURI();
	}
}
