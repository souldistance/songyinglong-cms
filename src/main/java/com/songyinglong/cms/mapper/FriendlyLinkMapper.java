package com.songyinglong.cms.mapper;

import java.util.List;

import com.songyinglong.cms.domain.FriendlyLink;

/** 
* @author 作者:SongYinglong
* @version 创建时间：2019年11月27日 上午8:45:40 
* 类功能说明 
*/
public interface FriendlyLinkMapper {

	/**
	 * 
	 * @Title: insertFriendLink 
	 * @Description: 添加友情链接
	 * @param friendlyLink
	 * @return
	 * @return: int
	 */
	int insertFriendLink(FriendlyLink friendlyLink);
	
	/**
	 * 
	 * @Title: queryFriendLinks 
	 * @Description: 查询友情链接
	 * @return
	 * @return: List<FriendlyLink>
	 */
	List<FriendlyLink> queryFriendLinks();
}
