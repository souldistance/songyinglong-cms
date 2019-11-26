package com.songyinglong.cms.vo;

import com.songyinglong.cms.domain.User;

/** 
* @author 作者:SongYinglong
* @version 创建时间：2019年11月19日 下午5:34:38 
* 类功能说明 
*/
public class UserVO extends User {

	private String repassword;

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	
	
}
