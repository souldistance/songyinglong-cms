package com.songyinglong.cms.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.songyinglong.cms.domain.Article;
import com.songyinglong.cms.domain.ArticleEnum;
import com.songyinglong.cms.domain.ArticleWithBLOBs;
import com.songyinglong.cms.domain.Category;
import com.songyinglong.cms.domain.Channel;
import com.songyinglong.cms.domain.Collect;
import com.songyinglong.cms.domain.Comment;
import com.songyinglong.cms.domain.Link;
import com.songyinglong.cms.domain.User;
import com.songyinglong.cms.exception.CMSAjaxException;
import com.songyinglong.cms.service.ArticleService;
import com.songyinglong.cms.service.CategoryService;
import com.songyinglong.cms.service.ChannelService;
import com.songyinglong.cms.service.CollectService;
import com.songyinglong.cms.service.CommentService;
import com.songyinglong.cms.service.LinkService;
import com.songyinglong.cms.util.Result;
import com.songyinglong.cms.util.ResultUtil;
import com.songyinglong.cms.vo.ArticleVO;
import com.songyinglong.common.utils.StringUtil;

/**
 * @author 作者:SongYinglong
 * @version 创建时间：2019年11月20日 下午1:17:23 类功能说明
 */
@Controller
public class IndexController {

	@Resource
	private ChannelService channelService;

	@Resource
	private CategoryService categoryService;

	@Resource
	private ArticleService articleService;

	@Resource
	private LinkService linkService;

	@Resource
	private CollectService collectService;

	@Resource
	private CommentService commentService;

	@Resource
	private RedisTemplate<String, String> redisTemplate;

	@Resource
	private KafkaTemplate<String, String> kafkaTemplate;
	/**
	 * 
	 * @Title: index
	 * @Description: 首页加载功能(多线程加载)
	 * @param model
	 * @param article
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = { "", "/", "/index" })
	public String index(Model model, Article article, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "6") Integer pageSize) {
		long s1 = System.currentTimeMillis();
		article.setStatus(1);
		article.setDeleted(0);
		// 查询全部栏目
		List<Channel> channels = channelService.selectByExample();
		model.addAttribute("channels", channels);

		// 显示最新文章
		Article newArticle = new Article();
		// 默认查询已审核和未被删除的文章
		newArticle.setStatus(1);
		newArticle.setDeleted(0);
		newArticle.setContentType(ArticleEnum.HTML.getOrdinal());
		PageInfo<Article> lastArticles = articleService.selectLastArticles(newArticle, 1, 5);
		model.addAttribute("lastArticle", lastArticles);

		// 右侧图片集
		Article pictures = new Article();
		// 默认查询已审核和未被删除的文章
		pictures.setStatus(1);
		pictures.setDeleted(0);
		pictures.setContentType(ArticleEnum.IMAGE.getOrdinal());
		PageInfo<Article> picturesInfo = articleService.selectPicturesArticles(pictures, 1, 5);
		model.addAttribute("picturesInfo", picturesInfo);

		// 右侧友情链接
		PageInfo<Link> linksInfo = linkService.selectLinks(1, 10);
		model.addAttribute("linksInfo", linksInfo);

		// 中间部分文章显示
		PageInfo<Article> pageInfo = null;
		// 文章ElasticSearch全文搜索功能 如果传入title则为高亮查询
		if(StringUtil.hasText(article.getTitle())) {
			long t1 = System.currentTimeMillis();
			pageInfo=articleService.esSelectArticles(article, pageNum, pageSize);
			long t2 = System.currentTimeMillis();
			System.out.println("ElasticSearch全文搜索耗时"+(t2-t1)+"毫秒");
		}else {
			// 当ChannelId 为空时说明当前访问的是默认的热门文章
			if (article.getChannelId() == null) {
				// 查询热门文章
				article.setHot(1);
			} else {
				// ChannelId不为空是 按栏目查询 查询该栏目下全部类别
				List<Category> categories = categoryService.selectByExample(article.getChannelId());
				model.addAttribute("categories", categories);
			}
			// 查询中间部分该显示的内容(ChannelId为空时查询热门文章 | ChannelId不为空是 按栏目查询)
			pageInfo = articleService.selectCenterArticles(article, pageNum, pageSize);
		}
		model.addAttribute("pg", pageInfo);
		model.addAttribute("article", article);
		long s2 = System.currentTimeMillis();
		System.out.println("首页访问共用时: ->>->>->>->>" + (s2 - s1) + "毫秒");
		return "/index/index";
	}

	/**
	 * 
	 * @Title: index
	 * @Description: 首页加载功能(多线程加载)
	 * @param model
	 * @param article
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = { "/index1" })
	public String index1(Model model, Article article, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "6") Integer pageSize) {
		long s1 = System.currentTimeMillis();
		// 左侧栏目显示
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				// 查询全部栏目
				List<Channel> channels = channelService.selectByExample();
				model.addAttribute("channels", channels);
			}
		});
		// 右侧显示最新文章
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				Article newArticle = new Article();
				// 默认查询已审核和未被删除的文章
				newArticle.setStatus(1);
				newArticle.setDeleted(0);
				PageInfo<Article> lastArticle = articleService.selectArticles(newArticle, 1, 5);
				model.addAttribute("lastArticle", lastArticle);
			}
		});
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				// 当ChannelId 为空时说明当前访问的是默认的热门文章
				if (article.getChannelId() == null) {
					// 查询热门文章
					article.setHot(1);
				} else {
					// ChannelId不为空是 按栏目查询 查询该栏目下全部类别
					List<Category> categories = categoryService.selectByExample(article.getChannelId());
					model.addAttribute("categories", categories);
				}
				// 查询中间部分该显示的内容(ChannelId为空时查询热门文章 | ChannelId不为空是 按栏目查询)
				PageInfo<Article> pageInfo = articleService.selectArticles(article, pageNum, pageSize);
				model.addAttribute("pg", pageInfo);
				model.addAttribute("article", article);
			}
		});
		Thread t4 = new Thread(new Runnable() {
			@Override
			public void run() {
				// 右侧图片集
				Article pictures = new Article();
				// 默认查询已审核和未被删除的文章
				pictures.setStatus(1);
				pictures.setDeleted(0);
				pictures.setContentType(ArticleEnum.IMAGE.getOrdinal());
				PageInfo<Article> picturesInfo = articleService.selectArticles(pictures, 1, 5);
				model.addAttribute("picturesInfo", picturesInfo);
			}
		});
		Thread t5 = new Thread(new Runnable() {
			@Override
			public void run() {
				// 右侧友情链接
				PageInfo<Link> linksInfo = linkService.selectLinks(1, 10);
				model.addAttribute("linksInfo", linksInfo);
			}
		});

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
			t5.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long s2 = System.currentTimeMillis();
		System.out.println("首页访问共用时: ->>->>->>->>" + (s2 - s1) + "毫秒");
		return "/index/index";
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
	public String articleDetail(Integer id, Model model, HttpServletRequest request) {
		//编写Kafka生产者 在文章详情页Controller方法里调用Kafka生产发送当前文章ID
		kafkaTemplate.sendDefault("article_addHit", id.toString());
		// 根据文章ID查询该文章详细信息 
		ArticleWithBLOBs article = articleService.selectByPrimaryKey(id);
		// 判断当前是否有用户登录
		HttpSession session = request.getSession(false);
		if (session != null) {
			User user = (User) session.getAttribute("user");
			if (user != null) {
				Collect collect = collectService.queryByTextAndUserId(article.getTitle(), user.getId());
				// 判断该文章是否被收藏
				if (collect != null) {
					model.addAttribute("collect", collect);
				}
				Comment comment = new Comment();
				comment.setUser(user);
				comment.setArticle(article);
				// 查询全部评论
				List<Comment> comments = commentService.selectComments(comment);
				model.addAttribute("comments", comments);
			}
		}
		// 如果该文章为图片集则需要解析json数组
		if (article.getContentType() == 1) {
			List<ArticleVO> pictures = JSON.parseArray(article.getContent(), ArticleVO.class);
			model.addAttribute("pictures", pictures);
			model.addAttribute("article", article);
			return "index/articlepic";
		}
		model.addAttribute("article", article);
		return "/index/article";
	}

	/**
	 * 
	 * @Title: collectArticle
	 * @Description: 收藏文章功能
	 * @param collect
	 * @param request
	 * @return
	 * @return: Result
	 */
	@PostMapping("/index/article/collect")
	@ResponseBody
	public Result collectArticle(Collect collect, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		// 判断当前是否有用户登录
		if (session == null) {
			throw new CMSAjaxException(1, "收藏失败,请先登录!");
		} else {
			User user = (User) session.getAttribute("user");
			if (user == null) {
				throw new CMSAjaxException(1, "收藏失败,请先登录!");
			} else {
				collect.setUserId(user.getId());
				collectService.insertCollect(collect);
				return ResultUtil.success();
			}
		}
	}
}
