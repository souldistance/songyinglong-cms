package com.songyinglong.cms.es;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.IndexQuery;

import com.songyinglong.cms.domain.Article;
import com.songyinglong.cms.domain.ArticleExample;
import com.songyinglong.cms.domain.ArticleExample.Criteria;
import com.songyinglong.cms.domain.Category;
import com.songyinglong.cms.domain.Channel;
import com.songyinglong.cms.domain.User;
import com.songyinglong.cms.mapper.ArticleMapper;
import com.songyinglong.cms.service.ArticleService;
import com.songyinglong.cms.service.impl.JunitParent;
import com.songyinglong.cms.util.ESUtils;

/**
 * @author 作者:SongYinglong
 * @version 创建时间：2019年12月20日 下午3:01:05 类功能说明
 */
public class ESTest extends JunitParent {

	@Resource
	private ElasticsearchTemplate elasticsearchTemplate;

	@Resource
	private ArticleMapper articleMapper;

	/**
	 * 
	 * @Title: batchAddtest 
	 * @Description: 把mysql中的数据同步到elasticsearch中
	 * @return: void
	 */
	@Test
	public void batchAddtest() {
		ArticleExample example = new ArticleExample();
		Criteria criteria = example.createCriteria();
		// 设置已审核文章
		criteria.andStatusEqualTo(1);
		// 设置查询未删除状态
		criteria.andDeletedEqualTo(0);
		//criteria.andUserIdEqualTo(null);
		List<Article> articles = articleMapper.selectByExample(example);
		IndexQuery query=new IndexQuery();
		for (Article article : articles) {
			query.setObject(article);
			elasticsearchTemplate.index(query);
		}
		System.out.println("存储完毕");
	}

	@Test
	public void test() {
		Class []clazz= {User.class,Channel.class,Category.class};
		AggregatedPage<Article> selectObjects = ESUtils.selectObjects(elasticsearchTemplate, Article.class, Arrays.asList(clazz), 0, 5, "id", new String[] {"title"}, "第三");
		List<Article> content = selectObjects.getContent();
		for (Article article : content) {
			System.out.println(article.getTitle());
		}
	}
}
