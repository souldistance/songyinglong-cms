package com.songyinglong.cms.domain;
/** 
* @author 作者:SongYinglong
* @version 创建时间：2019年11月27日 上午8:43:17 
* 类功能说明  友情链接实体类
*/

import java.io.Serializable;
import java.util.Date;

public class FriendlyLink implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String text;
	
	private String url;
	
	private Date created;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	
}
