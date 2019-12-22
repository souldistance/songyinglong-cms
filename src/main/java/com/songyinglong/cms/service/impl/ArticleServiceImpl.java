package com.songyinglong.cms.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.songyinglong.cms.domain.Article;
import com.songyinglong.cms.domain.ArticleExample;
import com.songyinglong.cms.domain.ArticleExample.Criteria;
import com.songyinglong.cms.domain.ArticleWithBLOBs;
import com.songyinglong.cms.domain.Category;
import com.songyinglong.cms.domain.Channel;
import com.songyinglong.cms.domain.User;
import com.songyinglong.cms.exception.CMSAjaxException;
import com.songyinglong.cms.exception.CMSException;
import com.songyinglong.cms.mapper.ArticleMapper;
import com.songyinglong.cms.service.ArticleService;
import com.songyinglong.cms.util.ESUtils;

/**
 * @author 作者:SongYinglong
 * @version 创建时间：2019年11月14日 下午2:07:27 类功能说明
 */
@Service
public class ArticleServiceImpl implements ArticleService {

	@Resource
	private ArticleMapper articleMapper;

	@Resource
	private RedisTemplate<String, Article> articleRedisTemplate;

	@Resource
	private ElasticsearchTemplate elasticsearchTemplate;

	/**
	 * 
	 * @Title: selectInit
	 * @Description: 查询文章前需要根据传入的文章数据给查询增强类设定一些值
	 * @param article
	 * @return: ArticleExample
	 */
	public ArticleExample selectInit(Article article) {
		// 创建增强类
		ArticleExample articleExample = new ArticleExample();
		// 创建条件类
		Criteria criteria = articleExample.createCriteria();
		// 模糊查询文章标题
		if (article.getTitle() != null) {
			criteria.andTitleLike("%" + article.getTitle() + "%");
		}
		// 设置热门状态
		if (article.getHot() != null) {
			criteria.andHotEqualTo(article.getHot());
		}
		// 查询文章状态
		if (article.getStatus() != null) {
			criteria.andStatusEqualTo(article.getStatus());
		}
		// 设置查询未删除状态
		if (article.getDeleted() != null) {
			criteria.andDeletedEqualTo(article.getDeleted());
		}
		// 查询文章类型
		if (article.getContentType() != null) {
			criteria.andContentTypeEqualTo(article.getContentType());
		}
		// 如果存在userID则是查询当前用户的文章
		if (article.getUserId() != null) {
			criteria.andUserIdEqualTo(article.getUserId());
		}
		// 根据栏目查询
		if (article.getChannelId() != null) {
			criteria.andChannelIdEqualTo(article.getChannelId());
		}
		// 根据该栏目查询该栏目下的所有分类文章
		if (article.getCategoryId() != null) {
			criteria.andCategoryIdEqualTo(article.getCategoryId());
		}
		// 倒序排序
		articleExample.setOrderByClause(" updated desc ");
		return articleExample;
	}

	/**
	 * 
	 * @Title: selectArticles
	 * @Description: 查询全部文章(或根据用户Id查询该用户的文章)
	 * @param article
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @return: PageInfo<Article>
	 */
	public PageInfo<Article> selectArticles(Article article, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		ArticleExample articleExample = selectInit(article);
		List<Article> articles = articleMapper.selectByExample(articleExample);
		return new PageInfo<Article>(articles, 5);
	}

	/**
	 * 
	 * @Title: updateArticle
	 * @Description: 修改文章
	 * @param article
	 * @return
	 * @return: boolean
	 */
	@Override
	public boolean updateByPrimaryKeySelective(ArticleWithBLOBs article) {
		// 如果管理员驳回该文章 则同时设置成非热门状态
		if (article.getStatus() != null && article.getStatus() == -1) {
			article.setHot(0);
		}
		try {
			boolean flag = articleMapper.updateByPrimaryKeySelective(article) > 0;
			if (flag) {
				// 如果管理员审核一篇文章通过 则清空redis库中的key 达到下次访问主页面时重新载入最新的文章
				// (但此方法会导致管理员审核通过的非最新文章也会删除该key)
				if (article.getHot() == null) {
					// 审核文章或者删除文章时 清空redis中存储的数据 待下次访问时更新最新数据
					if (article.getContentType() != null && article.getContentType() == 0) {
						articleRedisTemplate.delete("last_Article");
						articleRedisTemplate.delete("common_Article");
					} else {
						articleRedisTemplate.delete("pictures_Article");
					}
					if (article.getStatus() != null && article.getStatus() == 1) {
						// 管理员审核文章后添加文章
						IndexQuery query = new IndexQuery();
						query.setObject(article);
						elasticsearchTemplate.index(query);
					}// 删除文章时 同时删除elasticsearch中存储的文章
					else{
						elasticsearchTemplate.delete(Article.class, article.getId().toString());
					}
				}
				articleRedisTemplate.delete("hot_Article");
			}
			return flag;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSAjaxException(1, "修改文章失败");
		}
	}

	/**
	 * 
	 * @Title: selectByPrimaryKey
	 * @Description: 根据文章ID查询文章
	 * @param id
	 * @return
	 * @return: ArticleWithBLOBs
	 */
	@Override
	public ArticleWithBLOBs selectByPrimaryKey(Integer id) {

		return articleMapper.selectByPrimaryKey(id);
	}

	/**
	 * 
	 * @Title: insertSelective
	 * @Description: 当前登录用户发布文章
	 * @param record
	 * @return
	 * @return: boolean
	 */
	@Override
	public boolean insertSelective(ArticleWithBLOBs record) {
		try {
			return articleMapper.insertSelective(record) > 0;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSAjaxException(1, "发布文章失败");
		}

	}

	/**
	 * 
	 * @Title: updateHits
	 * @Description: 访问时访问量+1
	 * @param id
	 * @return
	 * @return: boolean
	 */
	@Override
	public boolean updateHits(Integer id) {
		try {
			return articleMapper.updateHits(id) > 0;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSException("访问量更新操作失败");
		}
	}

	/**
	 * 
	 * @Title: selectlastArticles
	 * @Description: 查询最新文章
	 * @param newArticle
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @return: PageInfo<Article>
	 */
	@Override
	public PageInfo<Article> selectLastArticles(Article newArticle, int pageNum, int pageSize) {
		System.out.println("=============================查询最新文章===============================");
		ListOperations<String, Article> opsForList = articleRedisTemplate.opsForList();
		PageInfo<Article> lastArticle = null;
		if (articleRedisTemplate.hasKey("last_Article")) {
			List<Article> articles = opsForList.range("last_Article", 0, -1);
			lastArticle = new PageInfo<Article>(articles);
		} else {
			PageHelper.startPage(pageNum, pageSize);
			ArticleExample articleExample = selectInit(newArticle);
			List<Article> articles = articleMapper.selectByExample(articleExample);
			lastArticle = new PageInfo<Article>(articles);
			opsForList.rightPushAll("last_Article", lastArticle.getList());
		}
		System.out.println("=========================================================================");
		return lastArticle;
	}

	/**
	 * 
	 * @Title: selectlastArticles
	 * @Description: 查询图片集
	 * @param newArticle
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @return: PageInfo<Article>
	 */
	@Override
	public PageInfo<Article> selectPicturesArticles(Article pictures, int pageNum, int pageSize) {
		System.out.println("=============================查询最新图片集===============================");
		ListOperations<String, Article> opsForList = articleRedisTemplate.opsForList();
		PageInfo<Article> picturesArticle = null;
		if (articleRedisTemplate.hasKey("pictures_Article")) {
			List<Article> articles = opsForList.range("pictures_Article", 0, -1);
			picturesArticle = new PageInfo<Article>(articles);
		} else {
			PageHelper.startPage(pageNum, pageSize);
			ArticleExample articleExample = selectInit(pictures);
			List<Article> articles = articleMapper.selectByExample(articleExample);
			picturesArticle = new PageInfo<Article>(articles);
			opsForList.rightPushAll("pictures_Article", picturesArticle.getList());
		}
		System.out.println("===========================================================================");
		return picturesArticle;
	}

	/**
	 * 
	 * @Title: selectCenterArticles
	 * @Description: 首页中间区域查询文章功能 (热门文章 或 某栏目下某分类的全部文章)
	 * @param article
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @return: PageInfo<Article>
	 */
	@Override
	public PageInfo<Article> selectCenterArticles(Article article, Integer pageNum, Integer pageSize) {
		System.out.println("=============================查询中间区域文章===============================");
		// opsForList 里面可以存放热门文章
		ListOperations<String, Article> opsForList = articleRedisTemplate.opsForList();
		// opsForHash 里面可以存放某栏目下末分类全部文章
		HashOperations<String, String, List<Article>> opsForHash = articleRedisTemplate.opsForHash();
		PageInfo<Article> centerArticle = null;
		// 判断redis中是否已经存放过热门文章
		if (article.getHot() != null && article.getHot() == 1 && articleRedisTemplate.hasKey("hot_Article")) {
			// 从redis中获取全部文章
			List<Article> articles = opsForList.range("hot_Article", (pageNum - 1) * pageSize, pageNum * pageSize - 1);
			// 封装分页数据
			Page<Article> page = new Page<Article>(pageNum, pageSize);
			page.addAll(articles);
			page.setTotal(opsForList.size("hot_Article"));
			centerArticle = new PageInfo<Article>(page, 8);
		} // 判断redis中是否已经存储当前要访问的栏目分类下的全部文章
		else if (article.getHot() == null
				&& opsForHash.hasKey("common_Article", "common_" + article.getChannelId() + article.getCategoryId())) {
			// 获取redis中已经存储当前要访问的栏目分类下的全部文章
			Map<String, List<Article>> map = opsForHash.entries("common_Article");
			// 封装分页数据
			List<Article> articles = map.get("common_" + article.getChannelId() + article.getCategoryId());
			Page<Article> page = new Page<Article>(pageNum, pageSize);
			for (int i = (pageNum - 1) * pageSize; i < pageNum * pageSize && i < articles.size(); i++) {
				page.add(articles.get(i));
			}
			page.setTotal(articles.size());
			centerArticle = new PageInfo<Article>(page);
		} // 如果不符合上述两种情况 则说明redis中还没有存热门文章或者该栏目分类下的文章
		else {
			// 从mysql中根据条件查询文章
			ArticleExample articleExample = selectInit(article);
			List<Article> articleAll = articleMapper.selectByExample(articleExample);
			// 判断是否是热门的文章 然后存入对应的redis中
			if (article.getHot() != null && article.getHot() == 1) {
				opsForList.rightPushAll("hot_Article", articleAll);
			} else {
				opsForHash.put("common_Article", "common_" + article.getChannelId() + article.getCategoryId(),
						articleAll);
			}
			PageHelper.startPage(pageNum, pageSize);
			List<Article> articles = articleMapper.selectByExample(articleExample);
			centerArticle = new PageInfo<Article>(articles);
		}
		System.out.println("===========================================================================");
		return centerArticle;
	}

	/**
	 * 
	 * @Title: ESHighLightQuery
	 * @Description: elasticsearch高亮查询
	 * @param pageNum
	 * @param pageSize
	 * @param article
	 * @return
	 * @return: PageInfo<Article>
	 */
	@Override
	public PageInfo<Article> ESHighLightQuery(Integer pageNum, Integer pageSize, Article article) {
		Class []clazz= {User.class,Channel.class,Category.class};
		AggregatedPage<Article> selectObjects = ESUtils.selectObjects(elasticsearchTemplate, Article.class, Arrays.asList(clazz),
				pageNum - 1, pageSize, "id", new String[] { "title" }, article.getTitle());
		List<Article> articles = selectObjects.getContent();
		Page<Article> page = new Page<Article>(pageNum,pageSize);
		page.addAll(articles);
		page.setTotal(selectObjects.getTotalElements());
		return new PageInfo<Article>(page);
	}

}
