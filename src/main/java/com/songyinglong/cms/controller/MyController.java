package com.songyinglong.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.songyinglong.cms.domain.Article;
import com.songyinglong.cms.domain.ArticleEnum;
import com.songyinglong.cms.domain.ArticleWithBLOBs;
import com.songyinglong.cms.domain.Category;
import com.songyinglong.cms.domain.Channel;
import com.songyinglong.cms.domain.Collect;
import com.songyinglong.cms.domain.User;
import com.songyinglong.cms.exception.CMSAjaxException;
import com.songyinglong.cms.service.ArticleService;
import com.songyinglong.cms.service.CategoryService;
import com.songyinglong.cms.service.ChannelService;
import com.songyinglong.cms.service.CollectService;
import com.songyinglong.cms.service.UserService;
import com.songyinglong.cms.util.Result;
import com.songyinglong.cms.util.ResultUtil;
import com.songyinglong.cms.vo.ArticleVO;

/**
 * @author 作者:SongYinglong
 * @version 创建时间：2019年11月13日 下午2:37:39 类功能说明
 */
@Controller
@RequestMapping("my")
public class MyController {

	@Resource
	private UserService userService;

	@Resource
	private ArticleService articleService;

	@Resource
	private ChannelService channelService;

	@Resource
	private CategoryService categoryService;

	@Resource
	private CollectService collectService;
	/**
	 * 
	 * @Title: myIndex
	 * @Description: 跳转到个人中心首页
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = { "", "/", "/index" })
	public String myIndex() {
		return "/my/index";
	}

	/**
	 * 
	 * @Title: toPublish
	 * @Description: 查询全部栏目 跳转发布页面
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("article/topublish")
	public String toPublish(Model model) {
		List<Channel> channels = channelService.selectByExample();
		model.addAttribute("channels", channels);
		return "/my/publish";
	}

	/**
	 * 
	 * @Title: toPublish
	 * @Description: 跳转发布图片集
	 * @param model
	 * @return
	 * @return: String
	 */
	@GetMapping("articlepic/publishpic")
	public String publishpic() {
		return "/my/publishpic";
	}

	/**
	 * 
	 * @Title: selectCategoriesBychannerlId
	 * @Description: 根据栏目ID查询该栏目下的所有分类
	 * @param channelId
	 * @return
	 * @return: List<Category>
	 */
	@ResponseBody
	@RequestMapping("selectCategoriesBychannelId")
	public Result selectCategoriesBychannerlId(Integer channelId) {
		List<Category> Categories = categoryService.selectByExample(channelId);
		return ResultUtil.success(Categories);
	}

	/**
	 * 
	 * @Title: publish
	 * @Description: 发布文章功能
	 * @param channelId
	 * @return
	 * @return: List<Category>
	 */
	@ResponseBody
	@RequestMapping("publish")
	public Result publish(HttpServletRequest request, MultipartFile file, ArticleWithBLOBs record) {
		if (!file.isEmpty()) {
			String path = request.getSession().getServletContext().getRealPath("/resource/pic/");
			String filename = UUID.randomUUID()
					+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			File file2 = new File(path, filename);
			try {
				// 写入到文件中
				file.transferTo(file2);
				record.setPicture(filename);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		record.setHits(0); // 点击量
		record.setHot(0); // 1表示热门
		record.setStatus(0); // 0为未审核 1表示审核通过 -1表示驳回
		record.setDeleted(0); // 0为正常状态 1表示文章被删除
		record.setCreated(new Date());
		record.setUpdated(new Date());
		record.setContentType(ArticleEnum.HTML.getOrdinal());// 0普通文章上传 1为图片集上传
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		record.setUserId(user.getId());
		articleService.insertSelective(record);
		return ResultUtil.success();
	}

	/**
	 * 
	 * @Title: publish
	 * @Description: 发布图片集功能
	 * @param channelId
	 * @return
	 * @return: List<Category>
	 */
	@ResponseBody
	@RequestMapping("publishpic")
	public Result publishpic(HttpServletRequest request, MultipartFile[] files, String[] desc,
			ArticleWithBLOBs article) {
		if (files != null && files.length > 0) {
			String filename = "";
			String path = request.getSession().getServletContext().getRealPath("/resource/pic/");
			List<ArticleVO> articleVOs = new ArrayList<ArticleVO>();
			int i = 0;
			for (MultipartFile file : files) {
				if (!file.isEmpty()) {
					filename = UUID.randomUUID()
							+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
					File file2 = new File(path, filename);
					ArticleVO articleVO = new ArticleVO();
					articleVO.setDesc(desc[i++]);
					articleVO.setUrl(filename);
					articleVOs.add(articleVO);
					try {
						// 写入到文件中
						file.transferTo(file2);
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			// 文章内容 为json数组
			article.setContent(JSON.toJSONString(articleVOs));
			article.setPicture(filename);
			article.setHits(0); // 点击量
			article.setHot(0); // 1表示热门
			article.setStatus(0); // 0为未审核 1表示审核通过 -1表示驳回
			article.setDeleted(0); // 0为正常状态 1表示文章被删除
			article.setCreated(new Date());
			article.setUpdated(new Date());
			article.setContentType(ArticleEnum.IMAGE.getOrdinal());// 0普通文章上传 1为图片集上传
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			article.setUserId(user.getId());
			articleService.insertSelective(article);
			return ResultUtil.success();
		} else {
			throw new CMSAjaxException(1, "至少上传一个图片");
		}
	}

	/**
	 * 
	 * @Title: collects
	 * @Description: 我的收藏
	 * @return
	 * @return: String
	 */
	@GetMapping("collects")
	public String collects(HttpServletRequest request, Model model, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "5") Integer pageSize) {
		HttpSession session = request.getSession(false);

		User user = (User) session.getAttribute("user");

		PageInfo<Collect> info = collectService.queryCollectsByUser(pageNum, pageSize, user);

		model.addAttribute("pg", info);
		return "/my/collect/collects";

	}
	
	/**
	 * 
	 * @Title: deleteCollect 
	 * @Description: 移除收藏
	 * @param id
	 * @return
	 * @return: Result<Collect>
	 */
	@ResponseBody
	@PostMapping("deleteCollect")
	public Result deleteCollect(Integer id){
		collectService.deleteById(id);
		return ResultUtil.success();
	}
	/**
	 * 
	 * @Title: articles
	 * @Description: 查询当前登录者全部文章
	 * @param article
	 * @param pageNum
	 * @param pageSize
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = { "/article", "/article/articles" })
	public String articles(HttpServletRequest request, Article article,
			@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "3") Integer pageSize,
			Model model) {
		HttpSession session = request.getSession(false);
		if (null != session) {
			User user = (User) session.getAttribute("user");
			article.setUserId(user.getId());
			PageInfo<Article> pageInfo = articleService.selectArticles(article, pageNum, pageSize);
			model.addAttribute("pg", pageInfo);
			model.addAttribute("article", article);
			return "/my/article/articles";
		} else {
			return "redirect:/passport/login";
		}

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
	 * @Description: 根据ID查询文章
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/article/detail")
	public String articleDetail(Integer id, Model model) {
		// 查询前访问量加一
		articleService.updateHits(id);
		ArticleWithBLOBs article = articleService.selectByPrimaryKey(id);
		// 如果文件类型为1则该文章为图片集 需要解析json数组
		if (article.getContentType() == 1) {
			List<ArticleVO> pictures = JSON.parseArray(article.getContent(), ArticleVO.class);
			model.addAttribute("pictures", pictures);
			model.addAttribute("article", article);
			return "my/article/articlepic";
		}
		model.addAttribute("article", article);
		return "/my/article/detail";
	}

	 
}
