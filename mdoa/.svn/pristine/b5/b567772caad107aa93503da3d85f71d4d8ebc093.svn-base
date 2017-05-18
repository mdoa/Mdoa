package com.mdoa.framework.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.mdoa.base.controller.BaseController;
import com.mdoa.constant.Constant;
import com.mdoa.framework.bo.PostForm;
import com.mdoa.framework.bo.UserPostForm;
import com.mdoa.framework.model.Post;
import com.mdoa.framework.service.PostService;
import com.mdoa.user.model.UserInfo;
import com.mdoa.util.PageModel;

@RestController
@RequestMapping("post")
public class PostController extends BaseController{
	
	@Autowired
	private PostService postService;
	
	/**
	 * 根据岗位Id删除岗位信息
	 * @param postId 岗位Id
	 * @return 是否删除成功标识
	 */
	@RequestMapping("deletePost.do")
	public String deletePost(String postId, HttpServletRequest request){
		try{
			UserInfo user = getUser(request);
			postService.deletePost(postId, user.getUserId(), user.getUserName());
			
			return Constant.SUCCESS_CODE;
		}catch(RuntimeException e){
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 修改岗位的信息
	 * @param post 包含着所有的岗位相关信息
	 * @return 是否添加成功标识
	 */
	@RequestMapping("updatePost.do")
	public String updatePost(Post post, HttpServletRequest request){
		try{
			UserInfo user = getUser(request);
			post.setUpdateUserId(user.getUserId());
			post.setUpdateUserName(user.getUserName());
			postService.updatePost(post);
			
			return Constant.SUCCESS_CODE;
		}catch(RuntimeException e){
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 添加岗位信息
	 * @param post 岗位信息
	 * @return 是否添加成功标识
	 */
	@RequestMapping("insertPost.do")
	public String insertPost(Post post, HttpServletRequest request){
		try{
			UserInfo user = getUser(request);
			post.setCreateUserId(user.getUserId());
			post.setCreateUserName(user.getUserName());
			postService.insertPost(post);
			
			return Constant.SUCCESS_CODE;
		}catch(RuntimeException e){
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 根据相应的条件查询所有的岗位信息，带有分页
	 * @param form 里面包含了创建时间查询，岗位名称查询
	 * @return 岗位列表的json串
	 */
	@RequestMapping("selectPostList.do")
	public String selectPostList(PostForm form){
		try{
			PageModel<Post> posts = postService.selectPostList(form);
			Gson gson = new Gson();
			return gson.toJson(posts);
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 根据岗位Id 查询岗位的详细信息
	 * @param postId 岗位Id
	 * @return 岗位的全部信息Json串
	 */
	@RequestMapping("selectPostInfo.do")
	public String selectPostInfo(String postId){
		try{
			Post post = postService.selectPostInfo(postId);
			Gson gson = new Gson();
			
			return gson.toJson(post);
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	
	/**
	 * 添加用户与岗位之间的关联
	 * @param form 其中包含了创建人Id， 用户Id， 岗位ID
	 * @return 成功标志位
	 */
	@RequestMapping("addUserPost.do")
	public String addUserPost(UserPostForm form, HttpServletRequest request){
		try{
			UserInfo user = getUser(request);
			form.setCreateUserId(user.getUserId());
			form.setCreateUserName(user.getUserName());
			postService.addUserPost(form);
			return Constant.SUCCESS_CODE;
		}catch(RuntimeException e){
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 根据用户岗位关系Id对用户的岗位进行删除
	 * @param form 包含用户岗位关系ID
	 * @return 成功标志位
	 */
	@RequestMapping("deleteUserPost.do")
	public String deleteUserPost(UserPostForm form, HttpServletRequest request){
		try{
			UserInfo user = getUser(request);
			form.setUpdateUserId(user.getUserId());
			form.setUpdateUserName(user.getUserName());
			postService.deleteUserPost(form);
			return Constant.SUCCESS_CODE;
		}catch(RuntimeException e){
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
}
