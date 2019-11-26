package com.songyinglong.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.songyinglong.cms.domain.Link;

/**
 * @author 作者:SongYinglong
 * @version 创建时间：2019年11月24日 下午7:01:20 类功能说明
 */
public interface LinkService {

	/**
	 * 
	 * @Title: selectLinks 
	 * @Description: 查询友情链接
	 * @return
	 * @return: List<Link>
	 */
	PageInfo<Link> selectLinks(Integer pageNum,Integer pageSize);

	/**
	 * 
	 * @Title: insertLink 
	 * @Description: 添加友情链接
	 * @param link
	 * @return
	 * @return: int
	 */
	int insertLink(Link link);
}
