package com.songyinglong.cms.domain;
/** 
* @author 作者:SongYinglong
* @version 创建时间：2019年12月6日 下午1:31:13 
* 类功能说明   评论实体类
*/

import java.io.Serializable;
import java.util.Date;

public class Comment implements  Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -2141075590310281493L;

	private Integer id;
	
	private Article article;
	
	private User user;
	
	private String content;
	
	private Date created;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	
}
