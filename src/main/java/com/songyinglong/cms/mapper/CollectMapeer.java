package com.songyinglong.cms.mapper;
/** 
* @author 作者:SongYinglong
* @version 创建时间：2019年11月25日 下午1:06:22 
* 类功能说明 
*/

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.songyinglong.cms.domain.Collect;
import com.songyinglong.cms.domain.User;

public interface CollectMapeer {

	/**
	 * 
	 * @Title: queryByTextAndUserId 
	 * @Description: 根据当前用户Id和标题查询收藏记录
	 * @param text
	 * @param userId
	 * @return
	 * @return: Collect
	 */
	Collect queryByTextAndUserId(@Param("text")String text,@Param("userId") Integer userId);

	/**
	 * 
	 * @Title: insertCollect 
	 * @Description: 添加收藏
	 * @param collect
	 * @return
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
	 * @return: List<Collect>
	 */
	List<Collect> queryCollectsByUser(User user);

	/**
	 * 
	 * @Title: deleteById 
	 * @Description: 根据ID删除收藏记录
	 * @param id
	 * @return: void
	 */
	void deleteById(@Param("id")Integer id);
}
