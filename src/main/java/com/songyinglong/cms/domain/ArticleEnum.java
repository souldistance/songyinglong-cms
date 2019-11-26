package com.songyinglong.cms.domain;
/** 
* @author 作者:SongYinglong
* @version 创建时间：2019年11月24日 下午1:28:27 
* 类功能说明 
*/
public enum ArticleEnum {

	HTML("html"),IMAGE("image");

	private String displayName;
	
	
	public String getDisplayName() {
		return displayName;
	}


	ArticleEnum(String displayName) {
		this.displayName=displayName;
	}
	
	public String getName() {
		return this.name();
	}
	
	public Integer getOrdinal() {
		return this.ordinal();
	}
	
	public static void main(String[] args) {
		System.out.println(IMAGE.getOrdinal());
		System.out.println(HTML.getOrdinal());
	}
}
