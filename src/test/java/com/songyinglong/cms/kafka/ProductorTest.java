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
import com.songyinglong.common.utils.RandomUtil;
import com.songyinglong.common.utils.StreamUtil;

/** 
* @author 作者:SongYinglong
* @version 创建时间：2019年12月19日 下午7:35:32 
* 类功能说明 
*/
public class ProductorTest extends JunitParent{

	@Resource
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Resource
	private ChannelService channelService;
	
	@Resource
	private CategoryService categoryService;
	
	@Test
	public void sendMsgTest() {
		File file=new File("D:\\article1");
		//获取已经爬取好的文章
		File[] listFiles = file.listFiles();
		for (File f : listFiles) {
			//读取文章内容
			String content = StreamUtil.readTextFile(f);
			//获取文章标题
			String title=f.getName().replace(".txt", "");
			//封装数据到文章类
			ArticleWithBLOBs article=new ArticleWithBLOBs();
			article.setTitle(title);
			article.setContent(content);
			//截取文章内容前140字为文章摘要
			String summary=content;
			if(content.length()>140) {
				summary = content.substring(0, 140);
			}
			article.setSummary(summary);
			//用随机值为点击量赋值
			article.setHits(RandomUtil.random(0, 10000));
			//随机热门状态
			article.setHot(RandomUtil.random(0, 1));
			//设置随机栏目
			List<Channel> channels = channelService.selectByExample();
			int channelNum = RandomUtil.random(0, channels.size()-1);
			article.setChannelId(channels.get(channelNum).getId());
			//设置随机类别
			List<Category> categories = categoryService.selectByExample(channels.get(channelNum).getId());
			int categoryNum = RandomUtil.random(0, categories.size()-1);
			article.setCategoryId(categories.get(categoryNum).getId());
			//设置随机创建日期
			article.setCreated(DateUtil.randomDate(DateUtil.getDateByString("2019-01-01", "yyyy-MM-dd"), new Date()));
			article.setContentType(0);
			article.setDeleted(0);
			article.setStatus(1);
			String jsonString = JSON.toJSONString(article);
			kafkaTemplate.sendDefault("article_add", jsonString);
		}
	}

}
