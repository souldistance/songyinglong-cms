package com.songyinglong.cms.mapper;
/** 
* @author 作者:SongYinglong
* @version 创建时间：2019年11月24日 下午6:41:53 
* 类功能说明 
*/

import java.util.List;

import com.songyinglong.cms.domain.Link;

public interface LinkMapper {
	
	List<Link> selectLinks();
	
	int insertLink(Link link);
}
