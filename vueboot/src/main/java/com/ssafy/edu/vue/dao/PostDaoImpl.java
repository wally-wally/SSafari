package com.ssafy.edu.vue.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.edu.vue.dto.Category;
import com.ssafy.edu.vue.dto.CategoryPost;
import com.ssafy.edu.vue.dto.Code;
import com.ssafy.edu.vue.dto.Commentpost;
import com.ssafy.edu.vue.dto.LocationFiltering;
import com.ssafy.edu.vue.dto.Post;
import com.ssafy.edu.vue.dto.PostPaging;
import com.ssafy.edu.vue.dto.Postinfo;

@Repository
public class PostDaoImpl {

	String ns = "ssafy.post.";
	@Autowired
	private SqlSession sqlSession;
	
	public List<Post> getPostList(int memberid) {
		return sqlSession.selectList(ns+"getPostList",memberid);
	}

	public Post getPost(int postid) {
		return sqlSession.selectOne(ns+"getPost", postid);
	}

	public void addPost(Post post) {
		sqlSession.insert(ns+"addPost",post);
	}

	public void updatePost(Post post) {
		sqlSession.update(ns+"updatePost",post);
	}

	public void deletePost(int postid) {
		sqlSession.delete(ns+"deletePost",postid);
	}

	public List<Post> getPosts(int memberid) {
		return sqlSession.selectList(ns+"getPosts",memberid);
	}

	public List<Commentpost> getCommentPost(Postinfo postinfo) {
		return sqlSession.selectList(ns+"getCommentPost",postinfo);
	}

	public void addCommentPost(Commentpost commentpost) {
		sqlSession.insert(ns+"addCommentPost",commentpost);
	}

	public void updateCommentPost(Commentpost commentpost) {
		sqlSession.update(ns+"updateCommentPost",commentpost);
	}

	public void deleteCommentPost(int cpostid) {
		sqlSession.delete(ns+"deleteCommentPost",cpostid);
	}

	public List<Post> getCategoryPosts(CategoryPost categorypost) {
		return sqlSession.selectList(ns+"getCategoryPosts",categorypost);
	}

	public List<Post> getLocationPosts(LocationFiltering locationfiltering) {
		return sqlSession.selectList(ns+"getLocationPosts",locationfiltering);
	}

	public void addLikePost(Postinfo likepost) {
		sqlSession.insert(ns+"addLikePost",likepost);
	}

	public void deleteLikePost(Postinfo likepost) {
		sqlSession.delete(ns+"deleteLikePost",likepost);
	}

	public int getLikeCounts(Postinfo likepost) {
		return sqlSession.selectOne(ns+"getLikeCounts", likepost);
	}

	public int getCommentCounts(Postinfo likepost) {
		return sqlSession.selectOne(ns+"getCommentCounts", likepost);
	}

	public void addBoardCategory(Category category) {
		sqlSession.insert(ns+"addBoardCategory",category);
	}

	public int isLike(Postinfo likepost) {
		return sqlSession.selectOne(ns+"isLike",likepost);
	}

	public void addCode(Code code) {
		sqlSession.insert(ns+"addCode",code);
	}

	public List<Commentpost> getCommentCode(Postinfo postinfo) {
		return sqlSession.selectList(ns+"getCommentCode",postinfo);
	}
	
	public List<Commentpost> getCommentJMT(Postinfo postinfo) {
		return sqlSession.selectList(ns+"getCommentJMT",postinfo);
	}

	public List<Post> getPostsPaging(PostPaging postpaging) {
		return sqlSession.selectList(ns+"getPostsPaging",postpaging);
	}

	public List<Category> getBoardCategory() {
		return sqlSession.selectList(ns+"getBoardCategory");
	}

	public List<Category> getBoardCategoryAuth() {
		return sqlSession.selectList(ns+"getBoardCategoryAuth");
	}

	public Category getBoardCategoryOne(int id) {
		return sqlSession.selectOne(ns+"getBoardCategoryOne",id);
	}

	public void deleteBoardCategoryAuth(int id) {
		sqlSession.delete(ns+"deleteBoardCategoryAuth",id);
	}

	public void addBoardCategoryAuth(Category category) {
		sqlSession.insert(ns+"addBoardCategoryAuth",category);
	}

	public List<Category> getBoardSearch(String keyword) {
		return sqlSession.selectList(ns+"getBoardSearch",keyword);
	}
		
	public int getTotalPost(PostPaging postpaging) {
		return sqlSession.selectOne(ns+"getTotalPost",postpaging);
	}

}
