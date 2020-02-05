package com.ssafy.edu.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.edu.vue.dao.PostDaoImpl;
import com.ssafy.edu.vue.dto.Commentpost;
import com.ssafy.edu.vue.dto.Likepost;
import com.ssafy.edu.vue.dto.LocationFiltering;
import com.ssafy.edu.vue.dto.Post;


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
	public Post getPost(int postid) {
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
	public List<Post> getPosts() {
		return postdao.getPosts();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Commentpost> getCommentPost(int postid) {
		return postdao.getCommentPost(postid);
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
	public List<Post> getCategoryPosts(int boardid) {
		return postdao.getCategoryPosts(boardid);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Post> getLocationPosts(LocationFiltering locationfiltering) {
		return postdao.getLocationPosts(locationfiltering);
	}

	@Override
	public void addLikePost(Likepost likepost) {
		postdao.addLikePost(likepost);
	}

	@Override
	public void deleteLikePost(Likepost likepost) {
		postdao.deleteLikePost(likepost);
	}

	@Override
	public int getLikeCounts(int postid) {
		return postdao.getLikeCounts(postid);
	}

	@Override
	@Transactional
	public int getCommentCounts(int postid) {
		return postdao.getCommentCounts(postid);
	}

}
