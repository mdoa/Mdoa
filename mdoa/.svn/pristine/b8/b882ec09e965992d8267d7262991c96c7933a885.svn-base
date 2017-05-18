package com.mdoa.framework.dao;

import java.util.HashMap;
import java.util.List;

import com.mdoa.framework.bo.PostForm;
import com.mdoa.framework.bo.UserPostForm;
import com.mdoa.framework.model.Post;
import com.mdoa.user.model.UserInfo;

public interface PostDao {
	/**
	 * 根据Id删除岗位
	 * @param params 删除人，删除人Id， 岗位 Id
	 * @return 是否成功
	 */
	boolean deletePost(HashMap params);
	
	/**
	 * 修改岗位信息
	 * @param post 岗位信息
	 * @return 是否成功
	 */
	boolean updatePost(Post post);
	/**
	 * 修改岗位信息，userInfo表中的岗位也要删除 
	 * @param post
	 * @return
	 */
	boolean updateUserPost(Post post);
	/**
	 * 添加岗位信息
	 * @param post 岗位信息
 	 * @return 是否成功
	 */
	boolean insertPost(Post post);
	
	/**
	 * 查询岗位列表
	 * @param form 查询条件，创建时间，岗位名称
	 * @return 岗位列表
	 */
	List<Post> selectPostList(PostForm form);
	
	/**
	 * 查询岗位的详细信息
	 * @param params 查询岗位所使用的参数
	 * @return 岗位信息
	 */
	Post selectPostInfo(HashMap params);
	
	/**
	 * 添加用户与岗位之间的关联
	 * @param form 其中包含了创建人Id， 用户Id， 岗位ID
	 * @return 是否添加成功
	 */
	boolean addUserPost(UserPostForm form);
	
	/**
	 * 根据用户岗位关系Id对用户的岗位进行删除
	 * @param form 包含用户岗位关系ID
	 */
	boolean deleteUserPost(UserPostForm form);
	
	/**
	 * 根据用户的岗位来查询用户信息
	 * @param form 包含表单的各种信息
	 */
	List<UserInfo> selectUserByPost(PostForm form);
}
