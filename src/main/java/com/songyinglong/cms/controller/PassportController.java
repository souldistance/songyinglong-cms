package com.songyinglong.cms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.songyinglong.cms.domain.User;
import com.songyinglong.cms.exception.CMSException;
import com.songyinglong.cms.service.UserService;
import com.songyinglong.cms.vo.UserVO;
import com.songyinglong.common.utils.StringUtil;

/**
 * @author 作者:SongYinglong
 * @version 创建时间：2019年11月18日 下午7:03:45 类功能说明
 */
@RequestMapping("passport")
@Controller
public class PassportController {

	@Resource
	private UserService userService;

	@GetMapping("regist")
	public String regist() {
		return "/passport/regist";
	}

	@GetMapping("login")
	public String login() {
		return "/passport/login";
	}

	/**
	 * 
	 * @Title: regist
	 * @Description: 注册功能!
	 * @param userVO
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @return: String
	 */
	@PostMapping("regist")
	public String regist(UserVO userVO, Model model, RedirectAttributes redirectAttributes) {
		userService.insertSelective(userVO);
		redirectAttributes.addFlashAttribute("userVO", userVO);
		redirectAttributes.addFlashAttribute("message", "恭喜,注册成功!");
		return "redirect:/passport/login";

	}

	@RequestMapping("findUsername")
	@ResponseBody
	public boolean findUsername(User user) {
		User u = userService.selectByUsername(user);
		if (u != null && StringUtil.hasText(u.getUsername())) {
			return false;
		} else {
			return true;
		}
	}

	@PostMapping("login")
	public String login(UserVO userVO, Model model, HttpSession session) {
		User user = userService.login(userVO);
		if (user != null) {
			if (user.getRole().equals("1")) {
				session.setAttribute("admin", user);
				return "redirect:/admin";
			} else {
				session.setAttribute("user", user);
				return "redirect:/my";
			}
		}
		return "/passport/login";
	}

	/**
	 * 
	 * @Title: logout
	 * @Description: 注销
	 * @param request
	 * @return
	 * @return: String
	 */
	@GetMapping("logout")
	public String logout(HttpServletRequest request) {
		// false: 如果requst中没有session则不创建session
		HttpSession session = request.getSession(false);
		if (null != session)
			session.invalidate();
		return "redirect:/passport/login";
	}
}
