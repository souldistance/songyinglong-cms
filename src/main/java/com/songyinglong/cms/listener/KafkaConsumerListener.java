package com.songyinglong.cms.listener;

import javax.annotation.Resource;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.songyinglong.cms.domain.ArticleWithBLOBs;
import com.songyinglong.cms.service.ArticleService;

/** 
* @author 作者:SongYinglong
* @version 创建时间：2019年12月24日 下午1:49:36 
* 类功能说明   Kafka消费者 将接到的数据保存到CMS项目数据库
*/
@Component
public class KafkaConsumerListener implements MessageListener<String, String>{

	@Resource
	private ArticleService articleService;
	
	
	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		String key = data.key();
		if(key!=null) {
			if(key.equals("cms_addArticle")) {
				//将接到的数据保存到CMS项目数据库
				String value = data.value();
				ArticleWithBLOBs article = JSON.parseObject(value, ArticleWithBLOBs.class);
				articleService.insertSelective(article);  
			}
			//编写Kafka消费者，接收文章ID
			if(key.equals("article_addHit")) {
				String value = data.value();
				//Kafka消费端执行数据库加1操作 
				articleService.addHit(Integer.parseInt(value));
			}
		}
	}

}
