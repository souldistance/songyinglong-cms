package com.songyinglong.cms.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.songyinglong.cms.domain.Article;
import com.songyinglong.cms.domain.ArticleWithBLOBs;
import com.songyinglong.cms.domain.Link;
import com.songyinglong.cms.domain.User;
import com.songyinglong.cms.service.ArticleService;
import com.songyinglong.cms.service.LinkService;
import com.songyinglong.cms.service.UserService;
import com.songyinglong.cms.util.Result;
import com.songyinglong.cms.util.ResultUtil;
import com.songyinglong.cms.vo.ArticleVO;

/**
 * @author 作者:SongYinglong
 * @version 创建时间：2019年11月13日 下午2:37:39 类功能说明
 */
@Controller
@RequestMapping("admin")
public class AdminController {

	@Resource
	private UserService userService;

	@Resource
	private ArticleService articleService;

	@Resource
	private LinkService linkService;


	/**
	 * 
	 * @Title: adminIndex
	 * @Description: 跳转到用户首页
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = { "", "/", "/index" })
	public String adminIndex() {

		return "/admin/index";
	}

	/**
	 * 
	 * @Title: selectUsers
	 * @Description: 查询用户全部信息
	 * @param user
	 * @param pageNum
	 * @param pageSize
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = { "/user", "/user/users" })
	public String selectUsers(User user, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "3") Integer pageSize, Model model) {
		PageInfo<User> pageInfo = userService.selectByExample(user, pageNum, pageSize);
		model.addAttribute("pg", pageInfo);
		model.addAttribute("user", user);
		return "/admin/user/users";
	}

	/**
	 * 
	 * @Title: updateUsers
	 * @Description: 根据id修改用户信息
	 * @param user
	 * @return
	 * @return: boolean
	 */
	@RequestMapping("/user/update")
	@ResponseBody
	public Result updateUser(User user) {
		userService.updateUsers(user);
		return ResultUtil.success();
	}

	/**
	 * 
	 * @Title: articles
	 * @Description: 查询全部文章
	 * @param article
	 * @param pageNum
	 * @param pageSize
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = { "/article", "/article/articles" })
	public String articles(Article article, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "3") Integer pageSize, Model model) {
		// 管理员查询文章默认查询待审文章
		if (article.getStatus() == null) {
			article.setStatus(0);
		} else if (article.getStatus() == 2) {
			article.setStatus(null);
		}
		PageInfo<Article> pageInfo = articleService.selectArticles(article, pageNum, pageSize);
		model.addAttribute("pg", pageInfo);
		model.addAttribute("article", article);
		return "/admin/article/articles";
	}

	/**
	 * 
	 * @Title: updateArticle
	 * @Description: 修改文章
	 * @param article
	 * @return
	 * @return: boolean
	 */
	@RequestMapping("/article/update")
	@ResponseBody
	public Result updateArticle(ArticleWithBLOBs article) {
		articleService.updateByPrimaryKeySelective(article);
		return ResultUtil.success();
	}

	/**
	 * 
	 * @Title: articleDetail
	 * @Description: 管理员查看文章详情
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/article/detail")
	public String articleDetail(Integer id, Model model) {
		ArticleWithBLOBs article = articleService.selectByPrimaryKey(id);
		// 如果文件类型为1则该文章为图片集 需要解析json数组
		if (article.getContentType() == 1) {
			List<ArticleVO> pictures = JSON.parseArray(article.getContent(), ArticleVO.class);
			model.addAttribute("pictures", pictures);
			model.addAttribute("article", article);
			return "admin/article/articlepic";
		}
		model.addAttribute("article", article);
		return "admin/article/detail";
	}

	/**
	 * 
	 * @Title: queryLinks
	 * @Description: 查询10条友情链接
	 * @param pageNum
	 * @param pageSize
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/link/links")
	public String queryLinks(@RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10") Integer pageSize, Model model) {
		PageInfo<Link> pageInfo = linkService.selectLinks(1, 10);
		model.addAttribute("pg", pageInfo);
		return "admin/link/links";
	}

	/**
	 * 
	 * @Title: insertLink
	 * @Description: 去添加链接页面
	 * @return
	 * @return: String
	 */
	@GetMapping("/link/insertLink")
	public String insertLink() {
		return "admin/link/insertLink";
	}

	/**
	 * 
	 * @Title: insertLink
	 * @Description: 添加链接
	 * @param link
	 * @param model
	 * @return
	 * @return: Result
	 */
	@PostMapping("/link/insertLink")
	@ResponseBody
	public Result insertLink(Link link, Model model) {
		link.setCreated(new Date());
		linkService.insertLink(link);
		return ResultUtil.success();
	}
}
