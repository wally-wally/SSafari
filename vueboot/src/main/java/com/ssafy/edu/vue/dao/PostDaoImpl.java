package com.ssafy.edu.vue.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.edu.vue.dto.Commentpost;
import com.ssafy.edu.vue.dto.Likepost;
import com.ssafy.edu.vue.dto.LocationFiltering;
import com.ssafy.edu.vue.dto.Post;

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

	public List<Post> getPosts() {
		return sqlSession.selectList(ns+"getPosts");
	}

	public List<Commentpost> getCommentPost(int postid) {
		return sqlSession.selectList(ns+"getCommentPost",postid);
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

	public List<Post> getCategoryPosts(int categoryid) {
		return sqlSession.selectList(ns+"getCategoryPosts",categoryid);
	}

	public List<Post> getLocationPosts(LocationFiltering locationfiltering) {
		return sqlSession.selectList(ns+"getLocationPosts",locationfiltering);
	}

	public void addLikePost(Likepost likepost) {
		sqlSession.insert(ns+"addLikePost",likepost);
	}

	public void deleteLikePost(Likepost likepost) {
		sqlSession.delete(ns+"deleteLikePost",likepost);
	}

	public int getLikeCounts(int postid) {
		return sqlSession.selectOne(ns+"getLikeCounts", postid);
	}

}
