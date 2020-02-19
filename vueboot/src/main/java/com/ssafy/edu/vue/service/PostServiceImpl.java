package com.ssafy.edu.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.edu.vue.dao.PostDaoImpl;
import com.ssafy.edu.vue.dto.Category;
import com.ssafy.edu.vue.dto.CategoryPost;
import com.ssafy.edu.vue.dto.Code;
import com.ssafy.edu.vue.dto.Commentpost;
import com.ssafy.edu.vue.dto.LocationFiltering;
import com.ssafy.edu.vue.dto.Popular;
import com.ssafy.edu.vue.dto.Post;
import com.ssafy.edu.vue.dto.PostPaging;
import com.ssafy.edu.vue.dto.Postinfo;


@Service
public class PostServiceImpl implements IPostService {
	
	@Autowired
	private PostDaoImpl postdao;

	@Override
	@Transactional(readOnly=true)
	public List<Post> getPostList(int memberid) {
		return postdao.getPostList(memberid);
	}

	@Override
	@Transactional(readOnly=true)
	public Post getPost(Postinfo postid) {
		return postdao.getPost(postid);
	}

	@Override
	@Transactional
	public void addPost(Post post) {
		postdao.addPost(post);
	}

	@Override
	@Transactional
	public void updatePost(Post post) {
		postdao.updatePost(post);
	}

	@Override
	@Transactional
	public void deletePost(int postid) {
		postdao.deletePost(postid);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Post> getPosts(int memberid) {
		return postdao.getPosts(memberid);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Commentpost> getCommentPost(Postinfo postinfo) {
		return postdao.getCommentPost(postinfo);
	}

	@Override
	@Transactional
	public void addCommentPost(Commentpost commentpost) {
		postdao.addCommentPost(commentpost);
	}

	@Override
	@Transactional
	public void updateCommentPost(Commentpost commentpost) {
		postdao.updateCommentPost(commentpost);
	}

	@Override
	@Transactional
	public void deleteCommentPost(int cpostid) {
		postdao.deleteCommentPost(cpostid);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Post> getCategoryPosts(CategoryPost categorypost) {
		return postdao.getCategoryPosts(categorypost);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Post> getLocationPosts(LocationFiltering locationfiltering) {
		return postdao.getLocationPosts(locationfiltering);
	}

	@Override
	public void addLikePost(Postinfo likepost) {
		postdao.addLikePost(likepost);
	}

	@Override
	public void deleteLikePost(Postinfo likepost) {
		postdao.deleteLikePost(likepost);
	}

	@Override
	@Transactional
	public int getLikeCounts(Postinfo likepost) {
		return postdao.getLikeCounts(likepost);
	}

	@Override
	@Transactional
	public int getCommentCounts(Postinfo likepost) {
		return postdao.getCommentCounts(likepost);

	}
	
	@Override
	@Transactional
	public void addBoardCategory(Category category) {
		postdao.addBoardCategory(category);
	}

	@Override
	@Transactional
	public int isLike(Postinfo likepost) {
		return postdao.isLike(likepost);
	}

	@Override
	@Transactional
	public void addCode(Code code) {
		postdao.addCode(code);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Commentpost> getCommentCode(Postinfo postinfo) {
		return postdao.getCommentCode(postinfo);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Post> getPostsPaging(PostPaging postpaging) {
		return postdao.getPostsPaging(postpaging);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Commentpost> getCommentJMT(Postinfo postinfo) {
		return postdao.getCommentJMT(postinfo);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Category> getBoardCategory() {
		return postdao.getBoardCategory();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Category> getBoardCategoryAuth() {
		return postdao.getBoardCategoryAuth();
	}

	@Override
	@Transactional(readOnly=true)
	public Category getBoardCategoryOne(int id) {
		return postdao.getBoardCategoryOne(id);
	}

	@Override
	@Transactional
	public void deleteBoardCategoryAuth(int id) {
		postdao.deleteBoardCategoryAuth(id);
	}

	@Override
	@Transactional
	public void addBoardCategoryAuth(Category category) {
		postdao.addBoardCategoryAuth(category);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Category> getBoardSearch(String keyword) {
		return postdao.getBoardSearch(keyword);
	}

	@Override
	@Transactional(readOnly=true)
	public int getTotalPost(PostPaging postpaging) {
		return postdao.getTotalPost(postpaging);
	}

	@Override
	@Transactional(readOnly=true)
	public int getPostCategory(int postid) {
		return postdao.getPostCategory(postid);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Post> getPopularComment(Popular pop) {
		return postdao.getPopularComment(pop);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Post> getPopularLikes(Popular pop) {
		return postdao.getPopularLikes(pop);
	}

	@Override
	@Transactional(readOnly=true)
	public int getAdminCount() {
		return postdao.getAdminCount();
	}

	@Override
	@Transactional(readOnly=true)
	public int getSsafyCount() {
		return postdao.getSsafyCount();
	}

	@Override
	@Transactional(readOnly=true)
	public int getUserCount() {
		return postdao.getUserCount();
	}

	@Override
	@Transactional(readOnly=true)
	public int getPostCount() {
		return postdao.getPostCount();
	}


	@Override
	@Transactional(readOnly=true)
	public int getPortfolioCount() {
		return postdao.getPortfolioCount();
	}


}
