package com.songyinglong.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

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
	
	/**
	 * 
	 * @Title: selectByExample 
	 * @Description: 查询全部栏目
	 * @return
	 * @return: List<Channel>
	 */
	@Override
	public List<Channel> selectByExample() {
		return channelMapper.selectByExample(null);
	}

}
