package com.songyinglong.cms.service;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.songyinglong.cms.domain.Collect;
import com.songyinglong.cms.domain.User;

/** 
* @author 作者:SongYinglong
* @version 创建时间：2019年11月25日 下午1:18:20 
* 类功能说明 
*/
public interface CollectService {
	/**
	 * 
	 * @Title: queryByTextAndUserId 
	 * @Description: 根据用户ID和标题查询收藏记录
	 * @param text
	 * @param userId
	 * @return
	 * @return: Collect
	 */
	Collect queryByTextAndUserId(@Param("text")String text,@Param("userId") Integer userId);

	/**
	 * 
	 * @Title: insertCollect 
	 * @Description: 收藏文章功能
	 * @param collect
	 * @return: int
	 */
	int insertCollect(Collect collect);

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
	PageInfo<Collect> queryCollectsByUser(Integer pageNum, Integer pageSize, User user);

	/**
	 * 
	 * @Title: deleteById 
	 * @Description: 根据ID删除收藏记录
	 * @param id
	 * @return: void
	 */
	void deleteById(Integer id);

}
