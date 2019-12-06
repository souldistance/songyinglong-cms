package com.songyinglong.cms.mapper;

import java.util.List;

import com.songyinglong.cms.domain.Comment;

/** 
* @author 作者:SongYinglong
* @version 创建时间：2019年12月6日 下午2:23:53 
* 类功能说明 
*/
public interface CommentMapper {

	void addComment(Comment comment);

	List<Comment> selectComments(Comment comment);

}
