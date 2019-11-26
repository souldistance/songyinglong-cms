package com.songyinglong.cms.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.songyinglong.cms.domain.Collect;
import com.songyinglong.cms.domain.User;
import com.songyinglong.cms.mapper.CollectMapeer;
import com.songyinglong.cms.service.CollectService;

/** 
* @author 作者:SongYinglong
* @version 创建时间：2019年11月25日 下午1:19:31 
* 类功能说明 
*/
@Service
public class CollectServiceImpl implements CollectService {

	@Resource
	private CollectMapeer collectMapeer;
	/**
	 * 
	 * @Title: queryByTextAndUserId 
	 * @Description: 根据用户ID和标题查询收藏记录
	 * @param text
	 * @param userId
	 * @return
	 * @return: Collect
	 */
	@Override
	public Collect queryByTextAndUserId(String text, Integer userId) {
		
		return collectMapeer.queryByTextAndUserId(text, userId);
	}
	/**
	 * 
	 * @Title: insertCollect 
	 * @Description: 收藏文章功能
	 * @param collect
	 * @return: int
	 */
	@Override
	public int insertCollect(Collect collect) {
		collect.setCreated(new Date());
		return collectMapeer.insertCollect( collect);
	}
	
	/**
	 * 
	 * @Title: queryCollectsByUser 
	 * @Description: 根据当前登录人查询所有文章
	 * @param pageNum
	 * @param pageSize
	 * @param user
	 * @return
	 * @return: PageInfo<Collect>
	 */
	@Override
	public PageInfo<Collect> queryCollectsByUser(Integer pageNum, Integer pageSize, User user) {
		PageHelper.startPage(pageNum, pageSize);
		List<Collect> collects= collectMapeer.queryCollectsByUser(user);
		return new PageInfo<Collect>(collects);
	}
	
	/**
	 * 
	 * @Title: deleteById 
	 * @Description: 根据ID删除收藏记录
	 * @param id
	 * @return: void
	 */
	@Override
	public void deleteById(Integer id) {
		collectMapeer.deleteById( id);
	}

}
