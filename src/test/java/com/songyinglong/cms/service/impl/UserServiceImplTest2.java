package com.songyinglong.cms.service.impl;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.songyinglong.cms.exception.CMSException;
import com.songyinglong.cms.service.UserService;
import com.songyinglong.cms.vo.UserVO;

/** 
* @author 作者:SongYinglong
* @version 创建时间：2019年11月19日 下午6:21:58 
* 类功能说明 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class UserServiceImplTest2 {

	@Resource
	private UserService userService;
	
	@Test
	public void testInsertSelective() {
		UserVO userVO = new UserVO();
		try {
			userVO.setUsername("22位の");
			userService.insertSelective(userVO);
		} catch (CMSException e) {
			System.out.println(e.getMessage());
		}
	}

}
