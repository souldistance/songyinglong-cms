package com.songyinglong.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.songyinglong.cms.domain.Comment;
import com.songyinglong.cms.domain.User;

/** 
* @author 作者:SongYinglong
* @version 创建时间：2019年12月6日 下午2:18:00 
* 类功能说明 
*/
public interface CommentService {

	/**
	 * 
	 * @Title: addComment 
	 * @Description: 添加一条评论
	 * @param comment
	 * @return: void
	 */
	void addComment(Comment comment);

	/**
	 * 
	 * @param comment 
	 * @Title: selectComments 
	 * @Description: 查询全部评论
	 * @return
	 * @return: List<Comment>
	 */
	List<Comment> selectComments(Comment comment);

	
}
