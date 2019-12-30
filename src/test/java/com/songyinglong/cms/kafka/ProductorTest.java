package com.songyinglong.cms.kafka;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.kafka.core.KafkaTemplate;

import com.alibaba.fastjson.JSON;
import com.songyinglong.cms.domain.ArticleWithBLOBs;
import com.songyinglong.cms.domain.Category;
import com.songyinglong.cms.domain.Channel;
import com.songyinglong.cms.service.CategoryService;
import com.songyinglong.cms.service.ChannelService;
import com.songyinglong.cms.service.impl.JunitParent;
import com.songyinglong.common.utils.DateUtil;
import com.songyinglong.common.utils.FileUtil;
import com.songyinglong.common.utils.RandomUtil;
import com.songyinglong.common.utils.StreamUtil;

/** 
* @author 作者:SongYinglong
* @version 创建时间：2019年12月24日 下午1:41:27 
* 类功能说明 
*/
public class ProductorTest extends JunitParent{

	@Resource
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Resource
	private ChannelService channelService;
	
	@Resource
	private CategoryService categoryService;
	
	/**
	 * 
	 * @Title: sendMsgtest 
	 * @Description:使用流工具类文本文件读取方法读取1000个文本文件 将生成Article对象通过Kafka发送到消费端
	 * @return: void
	 */
	@Test
	public void sendMsgtest() {
		//读取文件夹
		File file=new File("D:\\article1");
		File[] listFiles = file.listFiles();
		//文章个数
		System.out.println(listFiles.length);
		for (File f : listFiles) {
			//文章标题
			String title = f.getName().replace(".txt", "");
			ArticleWithBLOBs article = new ArticleWithBLOBs();
			article.setTitle(title);
			//读取文章内容
			String content = StreamUtil.readTextFile(f);
			String summary=content;
			if(content.length()>140) {
				//在文本内容中截取前140个字作为摘要
				summary=content.substring(0, 140);
			}
			article.setSummary(summary);
			//“点击量”和“是否热门”、“频道”字段要使用随机值
			article.setHits(RandomUtil.random(0, 10000));
			article.setHot(RandomUtil.random(0, 1));
			List<Channel> channels = channelService.selectByExample();
			int channel = RandomUtil.random(0, channels.size()-1);
			article.setChannelId(channels.get(channel).getId());
			List<Category> categories = categoryService.selectByExample(channels.get(channel).getId());
			int category = RandomUtil.random(0, categories.size()-1);
			article.setCategoryId(categories.get(category).getId());
			//文章发布日期从2019年1月1日模拟到今天
			article.setCreated(DateUtil.randomDate("2019-01-01", DateUtil.getDateOfString(new Date(), "yyyy-MM-dd")));
			article.setUpdated(article.getCreated());
			article.setContent(content);
			article.setContentType(RandomUtil.random(0, 1));
			article.setDeleted(0);
			//编写Kafka生产者，然后将生成Article对象通过Kafka发送到消费端
			String jsonString = JSON.toJSONString(article);
			kafkaTemplate.sendDefault("cms_addArticle", jsonString);
		}
	}

}
