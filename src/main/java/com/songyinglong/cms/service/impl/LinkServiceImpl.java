package com.songyinglong.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.songyinglong.cms.domain.Link;
import com.songyinglong.cms.exception.CMSAjaxException;
import com.songyinglong.cms.mapper.LinkMapper;
import com.songyinglong.cms.service.LinkService;
import com.songyinglong.common.utils.StringUtil;

/** 
* @author 作者:SongYinglong
* @version 创建时间：2019年11月24日 下午7:04:14 
* 类功能说明 
*/
@Service
public class LinkServiceImpl implements LinkService {

	@Resource
	private LinkMapper linkMapper;
	
	@Resource
	private RedisTemplate<String, Link> redisTemplate;
	/**
	 * 
	 * @Title: selectLinks 
	 * @Description: 查询友情链接
	 * @return
	 * @return: List<Link>
	 */
	@Override
	public PageInfo<Link> selectLinks(Integer pageNum,Integer pageSize) {
		System.out.println("==============================查询友情链接==================================");
		PageInfo<Link> pageInfo =null;
		ListOperations<String, Link> opsForList = redisTemplate.opsForList();
		if(redisTemplate.hasKey("CMS_links")) {
			List<Link> range = opsForList.range("CMS_links", (pageNum-1)*pageSize, pageNum*pageSize-1);
			Page<Link> page=new Page<Link>(pageNum, pageSize);
			page.setTotal(opsForList.size("CMS_links"));
			page.addAll(range);
			pageInfo=new PageInfo<Link>(page);
		}else {
			List<Link> AllLink = linkMapper.selectLinks();
			opsForList.rightPushAll("CMS_links", AllLink);
			PageHelper.startPage(pageNum, pageSize);
			List<Link> links = linkMapper.selectLinks();
			pageInfo = new PageInfo<Link>(links);
		}
		System.out.println("=============================================================================");
		return pageInfo;
	}

	/**
	 * 
	 * @Title: insertLink 
	 * @Description: 添加友情链接
	 * @param link
	 * @return
	 * @return: int
	 */
	@Override
	public int insertLink(Link link) {
		System.out.println(link.getUrl());
		try {
			if(link.getUrl()==null || !StringUtil.isHttpUrl(link.getUrl())) {
				throw new CMSAjaxException(1, "无效链接");
			}
			return linkMapper.insertLink(link);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSAjaxException(1, "添加失败");
		}
	}

}
