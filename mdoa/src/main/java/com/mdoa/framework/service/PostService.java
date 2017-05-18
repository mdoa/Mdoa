package com.mdoa.framework.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mdoa.base.service.BaseService;
import com.mdoa.framework.bo.PostForm;
import com.mdoa.framework.bo.UserPostForm;
import com.mdoa.framework.dao.PostDao;
import com.mdoa.framework.model.Post;
import com.mdoa.user.model.UserInfo;
import com.mdoa.util.PageModel;
import com.mdoa.util.StringUtil;

@Service
@Transactional
public class PostService extends BaseService{
	
	@Autowired
	private PostDao postDao;
	
	/**
	 * 根据Id来删除岗位
	 * @param postId 删除的岗位Id
	 * @param userId 删除人Id
	 * @param userName 删除人姓名
	 */
	public void deletePost(String postId, String userId, String userName){
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("postId", postId);
		params.put("userName", userName);
		if(!postDao.deletePost(params)){
			throw new RuntimeException("删除岗位失败");
		}
	}
	
	/**
	 * 修改岗位的相关信息
	 * @param post 其中包含各种岗位相关信息，包括修改人，修改人Id
	 */
	public void updatePost(Post post){
		if(!postDao.updatePost(post)){
			throw new RuntimeException("修改岗位信息失败");
		}
		if(!postDao.updateUserPost(post)){
			throw new RuntimeException("修改员工岗位信息失败");
		}
	}
	
	/**
	 * 添加岗位信息
	 * @param post 其中包含各种岗位相关信息，包括添加人，添加人Id
	 */
	public void insertPost(Post post){
		if(!postDao.insertPost(post)){
			throw new RuntimeException("添加岗位信息失败");
		}
	}
	
	/**
	 * 根据指定的条件，包括岗位名与创建时间来查询岗位相关信息
	 * @param form 岗位名，创建时间开始 ， 创建时间结束
	 * @return 岗位的列表信息，带分页
	 */
	public PageModel<Post> selectPostList(PostForm form){
		form.setPostName(StringUtil.toLikeAll(form.getPostName()));
		form.setRoleName(StringUtil.toLikeRight(form.getRoleName()));
		PageHelper.startPage(form.getPage(), form.getRows());
		List<Post> list = postDao.selectPostList(form);
		PageModel<Post> page = new PageModel<Post>((Page<Post>)list);
		return page;
	}
	
	/**
	 * 根据岗位的Id来查询岗位的所有信息
	 * @param postId 用于查询的岗位Id
	 * @return 岗位的信息
	 */
	public Post selectPostInfo(String postId){
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("postId", postId);
		return postDao.selectPostInfo(params);
	}
	
	/**
	 * 添加用户与岗位之间的关联
	 * @param form 其中包含了创建人Id， 用户Id， 岗位ID
	 */
	public void addUserPost(UserPostForm form){
		if(!postDao.addUserPost(form)){
			throw new RuntimeException("添加岗位关系失败");
		}
	}
	
	/**
	 * 根据用户岗位关系Id对用户的岗位进行删除
	 * @param form 包含用户岗位关系ID
	 */
	public void deleteUserPost(UserPostForm form){
		if(!postDao.deleteUserPost(form)){
			throw new RuntimeException("删除岗位关系失败");
		}
	}
	
	/**
	 * 根据用户的岗位来查询用户信息 带有分页
 	 * @param form 包含表单的各种信息
	 */
	public PageInfo<UserInfo> selectUserByPost(PostForm form){
		PageHelper.startPage(form.getPage(), form.getRows());
		List<UserInfo> users = postDao.selectUserByPost(form);
		return new PageInfo<UserInfo>(users);
	}
}
