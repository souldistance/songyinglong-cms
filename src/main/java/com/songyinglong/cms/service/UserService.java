package com.songyinglong.cms.service;

import java.util.List;

import org.springframework.ui.Model;

import com.github.pagehelper.PageInfo;
import com.songyinglong.cms.domain.User;
import com.songyinglong.cms.vo.UserVO;

/** 
* @author 作者:SongYinglong
* @version 创建时间：2019年11月13日 下午2:07:42 
* 类功能说明 
*/
public interface UserService {

	/**
	 * 
	 * @Title: selectByExample 
	 * @Description: 查询用户列表
	 * @param user
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @return: PageInfo<User>
	 */
	PageInfo<User> selectByExample(User user,int pageNum, int pageSize);

	/**
	 * 
	 * @Title: updateUsers 
	 * @Description: 修改用户状态
	 * @param user
	 * @return
	 * @return: boolean
	 */
	boolean updateUsers(User user);
	
	/**
	 * 
	 * @Title: selectByPrimaryKey 
	 * @Description: 根据条件查询用户
	 * @param id
	 * @return
	 * @return: List<User>
	 */
	List<User> selectByExample(User user);
	
	/**
	 * 
	 * @Title: insertSelective 
	 * @Description: 添加用户(注册用户)
	 * @param record
	 * @return
	 * @return: int
	 */
	boolean insertSelective(UserVO record);
	
	/**
	 * 
	 * @Title: selectByUsername 
	 * @Description: 根据用户名查询用户
	 * @param user
	 * @return
	 * @return: User
	 */
	User selectByUsername(User user);
	
	/**
	 * 
	 * @Title: login 
	 * @Description: 登录功能
	 * @param userVO
	 * @return
	 * @return: User
	 */
	User login(UserVO userVO);
}
