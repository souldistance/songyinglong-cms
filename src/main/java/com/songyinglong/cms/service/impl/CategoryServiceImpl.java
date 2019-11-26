package com.songyinglong.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.songyinglong.cms.domain.Category;
import com.songyinglong.cms.domain.CategoryExample;
import com.songyinglong.cms.domain.CategoryExample.Criteria;
import com.songyinglong.cms.mapper.CategoryMapper;
import com.songyinglong.cms.service.CategoryService;

/** 
* @author 作者:SongYinglong
* @version 创建时间：2019年11月15日 下午7:39:42 
* 类功能说明 
*/
@Service
public class CategoryServiceImpl implements CategoryService {

	@Resource
	private CategoryMapper categoryMapper;
	/**
	 * 
	 * @Title: selectByExample 
	 * @Description: 根据栏目ID查询全部分类
	 * @param example
	 * @return
	 * @return: List<Category>
	 */
	@Override 
	public List<Category> selectByExample(Integer channelId) {
		CategoryExample example=new CategoryExample();
		example.createCriteria().andChannelIdEqualTo(channelId);
		return categoryMapper.selectByExample(example);
	}

}
