package com.songyinglong.cms.es;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;

import com.songyinglong.cms.domain.Article;
import com.songyinglong.cms.domain.ArticleExample;
import com.songyinglong.cms.domain.ArticleExample.Criteria;
import com.songyinglong.cms.mapper.ArticleMapper;
import com.songyinglong.cms.service.ArticleService;
import com.songyinglong.cms.service.impl.JunitParent;

/** 
* @author 作者:SongYinglong
* @version 创建时间：2019年12月24日 下午2:15:50 
* 类功能说明  使用ElasticSearch将文章表所有文章建立全文索引
*/
public class EsTest extends JunitParent{

	@Resource
	private ElasticsearchTemplate elasticsearchTemplate;
	
	@Resource
	private ArticleMapper articleMapper;
	
	/**
	 * 
	 * @Title: test 
	 * @Description: 5.	使用ElasticSearch将文章表文章建立全文索引
	 * @return: void
	 */
	@Test
	public void test() {
		ArticleExample example = new ArticleExample();
		Criteria criteria = example.createCriteria();
		criteria.andDeletedEqualTo(0);
		criteria.andStatusEqualTo(1);
		List<Article> articles = articleMapper.selectByExample(example);
		for (Article article : articles) {
			IndexQuery query=new IndexQuery();
			query.setObject(article);
			elasticsearchTemplate.index(query);
		}
	}

}
