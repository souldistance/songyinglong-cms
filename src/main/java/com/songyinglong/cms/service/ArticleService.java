package com.songyinglong.cms.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.songyinglong.cms.domain.Article;
import com.songyinglong.cms.domain.ArticleWithBLOBs;

/** 
* @author 作者:SongYinglong
* @version 创建时间：2019年11月14日 下午2:07:27 
* 类功能说明 
*/
public interface ArticleService {

	/**
	 * 
	 * @Title: selectArticles 
	 * @Description: 查询文章
	 * @param article
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @return: PageInfo<Article>
	 */
	PageInfo<Article> selectArticles(Article article,int pageNum, int pageSize);
	
	

	/**
	 * 
	 * @Title: updateArticle 
	 * @Description: 修改文章
	 * @param article
	 * @return
	 * @return: boolean
	 */
	boolean updateByPrimaryKeySelective(ArticleWithBLOBs article);

	/**
	 * 
	 * @Title: selectByPrimaryKey 
	 * @Description: 根据文章ID查询文章
	 * @param id
	 * @return
	 * @return: ArticleWithBLOBs
	 */
	ArticleWithBLOBs selectByPrimaryKey(Integer id);
	
	/**
	 * 
	 * @Title: insertSelective 
	 * @Description: 发布文章
	 * @param record
	 * @return
	 * @return: boolean
	 */
    boolean insertSelective(ArticleWithBLOBs record);

    



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
	PageInfo<Article> selectLastArticles(Article newArticle, int pageNum, int pageSize);


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
	PageInfo<Article> selectPicturesArticles(Article pictures, int i, int j);


	/**
	 * 
	 * @Title: selectCenterArticles 
	 * @Description: 首页中间区域查询文章功能
	 * @param article
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @return: PageInfo<Article>
	 */
	PageInfo<Article> selectCenterArticles(Article article, Integer pageNum, Integer pageSize);


	/**
	 * 
	 * @Title: esSelectArticles 
	 * @Description: 文章ElasticSearch全文搜索功能
	 * @param article
	 * @param pageSize 
	 * @param pageNum 
	 * @return
	 * @return: PageInfo<Article>
	 */
	PageInfo<Article> esSelectArticles(Article article, Integer pageNum, Integer pageSize);


	/**
	 * 
	 * @Title: addHit 
	 * @Description: 访问文章时增加点击量
	 * @param parseInt
	 * @return: void
	 */
	void addHit(int parseInt);


	
}
