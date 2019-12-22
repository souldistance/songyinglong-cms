package com.songyinglong.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.songyinglong.cms.domain.Channel;
import com.songyinglong.cms.mapper.ChannelMapper;
import com.songyinglong.cms.service.ChannelService;

/** 
* @author 作者:SongYinglong
* @version 创建时间：2019年11月15日 下午7:35:26 
* 类功能说明 
*/
@Service
public class ChannelServiceImpl implements ChannelService {

	@Resource
	private ChannelMapper channelMapper;
	
	@Resource
	private RedisTemplate<String, Channel> redisTemplate;
	/**
	 * 
	 * @Title: selectByExample 
	 * @Description: 查询全部栏目
	 * @return
	 * @return: List<Channel>
	 */
	@Override
	public List<Channel> selectByExample() {
		System.out.println("=================查询全部栏目===================");
		ListOperations<String, Channel> opsForList = redisTemplate.opsForList();
		List<Channel> channels=null;
		if(redisTemplate.hasKey("CMS_channel")) {
			channels = opsForList.range("CMS_channel", 0, -1);
		}else {
			channels=channelMapper.selectByExample(null);
			opsForList.rightPushAll("CMS_channel", channels);
		}
		System.out.println("=================================================");
		return channels;
	}

}
