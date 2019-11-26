package com.songyinglong.cms.service;

import java.util.List;

import com.songyinglong.cms.domain.Channel;

/** 
* @author 作者:SongYinglong
* @version 创建时间：2019年11月15日 下午7:33:45 
* 类功能说明 
*/
public interface ChannelService {

	/**
	 * 
	 * @Title: selectByExample 
	 * @Description: 查询全部栏目
	 * @return
	 * @return: List<Channel>
	 */
	List<Channel> selectByExample();
}
