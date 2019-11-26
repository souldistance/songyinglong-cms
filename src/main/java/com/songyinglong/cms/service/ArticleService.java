package com.songyinglong.cms.service;

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
	 * @Description: 查询全部文章
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
     * @Title: updateHits 
     * @Description: 访问时访问量+1
     * @param id
     * @return
     * @return: boolean
     */
    boolean updateHits(Integer id);
}
