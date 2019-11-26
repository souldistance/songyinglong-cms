package com.songyinglong.cms.service;

import java.util.List;

import com.songyinglong.cms.domain.Category;

/** 
* @author 作者:SongYinglong
* @version 创建时间：2019年11月15日 下午7:38:04 
* 类功能说明 
*/
public interface CategoryService {

	/**
	 * 
	 * @Title: selectByExample 
	 * @Description: 根据栏目ID查询全部分类
	 * @param example
	 * @return
	 * @return: List<Category>
	 */
	List<Category> selectByExample(Integer channelId);
}
