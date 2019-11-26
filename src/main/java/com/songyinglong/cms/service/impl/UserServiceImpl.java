package com.songyinglong.cms.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.songyinglong.cms.domain.User;
import com.songyinglong.cms.domain.UserExample;
import com.songyinglong.cms.domain.UserExample.Criteria;
import com.songyinglong.cms.exception.CMSAjaxException;
import com.songyinglong.cms.exception.CMSException;
import com.songyinglong.cms.mapper.UserMapper;
import com.songyinglong.cms.service.UserService;
import com.songyinglong.cms.util.Md5Util;
import com.songyinglong.cms.vo.UserVO;
import com.songyinglong.common.utils.StringUtil;

/**
 * @author 作者:SongYinglong
 * @version 创建时间：2019年11月13日 下午2:17:27 类功能说明
 */
@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;

	@Override
	public PageInfo<User> selectByExample(User user, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		UserExample userExample = new UserExample();
		if (user.getUsername() != null) {
			userExample.createCriteria().andUsernameLike("%" + user.getUsername() + "%");
		}
		List<User> users = userMapper.selectByExample(userExample);
		return new PageInfo<User>(users,5);
	}

	/**
	 * 
	 * @Title: updateUsers
	 * @Description: 修改用户状态
	 * @param user
	 * @return
	 * @return: boolean
	 */
	@Override
	public boolean updateUsers(User user) {
		try {
			return userMapper.updateByPrimaryKeySelective(user) > 0;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSAjaxException(1,"修改失败");
		}

	}

	/**
	 * 
	 * @Title: selectByPrimaryKey
	 * @Description: 根据条件查询用户
	 * @param id
	 * @return
	 * @return: List<User>
	 */
	@Override
	public List<User> selectByExample(User user) {
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		if (user.getUsername() != null) {
			criteria.andUsernameEqualTo(user.getUsername());
		}
		try {
			return userMapper.selectByExample(userExample);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("查询用户失败!");
		}
	}

	@Override
	public boolean insertSelective(UserVO userVO) {
		// 如果用户为null. 说明没有传值
		if (null == userVO) {
			throw new CMSException("用户名或密码必须输入");
		}
		if (!StringUtil.hasText(userVO.getUsername())) {
			throw new CMSException("用户名不能为空!");
		}
		if (!(userVO.getUsername().length() >= 2 && userVO.getUsername().length() <= 6)) {
			throw new CMSException("用户名长度为2-6之间!");
		}
		if (!userVO.getUsername().matches("^[\\u4e00-\\u94a5]{2,6}$")) {
			throw new CMSException("用户名必须为中文!");
		}
		if (!StringUtil.hasText(userVO.getPassword())) {
			throw new CMSException("密码不能为空!");
		}
		if (!(userVO.getPassword().length() >= 2 && userVO.getPassword().length() <= 10)) {
			throw new CMSException("密码长度为2-10之间!");
		}
		if (userVO.getRepassword() != null && !userVO.getPassword().equals(userVO.getRepassword())) {
			throw new CMSException("两次密码不一致!");
		}
		userVO.setPassword(Md5Util.md5Encoding(userVO.getRepassword()));
		userVO.setCreated(new Date());
		userVO.setLocked(0);
		userVO.setRole("1");
		userVO.setUpdated(new Date());
		return userMapper.insert(userVO) > 0;
	}

	/**
	 * 
	 * @Title: selectByUsername
	 * @Description: 根据用户名查询用户
	 * @param user
	 * @return
	 * @return: User
	 */
	@Override
	public User selectByUsername(User user) {
		try {
			return userMapper.selectByUsername(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("查询用户失败!");
		}
	}

	@Override
	public User login(UserVO userVO) {
		// 如果用户为null. 说明没有传值
		if (null == userVO) {
			throw new CMSException("用户名或密码必须输入");
		}
		if (!StringUtil.hasText(userVO.getUsername())) {
			throw new CMSException("用户名不能为空!");
		}
		if (!StringUtil.hasText(userVO.getPassword())) {
			throw new CMSException("密码不能为空!");
		}
		User user = userMapper.selectByUsername(userVO);
		if(user==null) {
			throw new CMSException("没有该用户!");
		}
		if(!user.getPassword().equals(Md5Util.md5Encoding(userVO.getPassword()))) {
			throw new CMSException("密码错误!");
		}
		if(user.getLocked()==1) {
			throw new CMSException("用户已被禁用!");
		}
		return user;
	}

}
