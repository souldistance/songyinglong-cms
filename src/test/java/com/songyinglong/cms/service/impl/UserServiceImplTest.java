package com.songyinglong.cms.service.impl;

import javax.annotation.Resource;

import org.junit.Test;

import com.github.pagehelper.PageInfo;
import com.songyinglong.cms.domain.User;
import com.songyinglong.cms.domain.UserExample;
import com.songyinglong.cms.service.UserService;

/** 
* @author 作者:SongYinglong
* @version 创建时间：2019年11月13日 下午2:22:41 
* 类功能说明 
*/
public class UserServiceImplTest extends JunitParent{

	@Resource
	private UserService userService;
	@Test
	public void testSelectByExample() {
		User user=new User();
		PageInfo<User> pageInfo = userService.selectByExample(user, 7, 2);
		for (User u : pageInfo.getList()) {
			System.out.println(u.getUsername());
		}
		System.out.println("-------------");
		
		int[] nums = pageInfo.getNavigatepageNums();
		for (int i : nums) {
			System.out.println(i);
		}
		System.out.println(pageInfo.getNavigateFirstPage());
	}

}
