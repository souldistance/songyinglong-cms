package com.songyinglong.cms.exception;

/** 
* @author 作者:SongYinglong
* @version 创建时间：2019年11月19日 下午5:43:05 
* 类功能说明 
*/
public class CMSException extends RuntimeException {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CMSException(String message) {
		super(message);
		this.message = message;
	}

	public CMSException() {
		super();
	}
}
