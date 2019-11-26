package com.songyinglong.cms.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.songyinglong.cms.domain.Article;
import com.songyinglong.cms.domain.ArticleExample;
import com.songyinglong.cms.domain.ArticleExample.Criteria;
import com.songyinglong.cms.domain.ArticleWithBLOBs;
import com.songyinglong.cms.domain.User;
import com.songyinglong.cms.exception.CMSAjaxException;
import com.songyinglong.cms.exception.CMSException;
import com.songyinglong.cms.mapper.ArticleMapper;
import com.songyinglong.cms.service.ArticleService;

/**
 * @author 作者:SongYinglong
 * @version 创建时间：2019年11月14日 下午2:07:27 类功能说明
 */
@Service
public class ArticleServiceImpl implements ArticleService {

	@Resource
	private ArticleMapper articleMapper;

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
	@Override
	public PageInfo<Article> selectArticles(Article article, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		//创建增强类
		ArticleExample articleExample = new ArticleExample();
		//创建条件类
		Criteria criteria = articleExample.createCriteria();
		//模糊查询文章标题
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
		//查询文章类型
		if(article.getContentType()!=null) {
			criteria.andContentTypeEqualTo(article.getContentType());
		}
		// 倒序排序
		articleExample.setOrderByClause(" updated desc ");
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
		//如果管理员驳回该文章 则同时设置成非热门状态
		if (article.getStatus() != null && article.getStatus() == -1) {
			article.setHot(0);
		}
		try {
			return articleMapper.updateByPrimaryKeySelective(article) > 0;
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
	public boolean insertSelective( ArticleWithBLOBs record) {
		try {
			return articleMapper.insertSelective(record) > 0;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSAjaxException(1,"发布文章失败");
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

}
