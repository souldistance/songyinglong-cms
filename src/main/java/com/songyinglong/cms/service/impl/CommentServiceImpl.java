package com.songyinglong.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.songyinglong.cms.domain.Comment;
import com.songyinglong.cms.mapper.CommentMapper;
import com.songyinglong.cms.service.CommentService;

/** 
* @author 作者:SongYinglong
* @version 创建时间：2019年12月6日 下午2:19:41 
* 类功能说明 
*/
@Service
public class CommentServiceImpl implements CommentService {

	@Resource
	private CommentMapper commentMapper;
	
	/**
	 * 
	 * @Title: addComment 
	 * @Description: 添加一条评论
	 * @param comment
	 * @return: void
	 */
	@Override
	public void addComment(Comment comment) {
		commentMapper.addComment(comment);
	}

	/**
	 * 
	 * @Title: selectComments 
	 * @Description: 查询全部评论
	 * @return
	 * @return: List<Comment>
	 */
	@Override
	public List<Comment> selectComments(Comment comment) {
		
		return commentMapper.selectComments( comment);
	}

}
