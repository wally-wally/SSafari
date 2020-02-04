package com.ssafy.edu.vue.service;

import java.util.List;

import com.ssafy.edu.vue.dto.Commentpost;
import com.ssafy.edu.vue.dto.LocationFiltering;
import com.ssafy.edu.vue.dto.Post;


public interface IPostService {
	
	List<Post> getPostList(int memberid);
	
	Post getPost(int postid);
	
	void addPost(Post post);
	
	void updatePost(Post post);
	
	void deletePost(int postid);

	List<Post> getPosts();

	List<Commentpost> getCommentPost(int postid);

	void addCommentPost(Commentpost commentpost);

	void updateCommentPost(Commentpost commentpost);

	void deleteCommentPost(int cpostid);

	List<Post> getCategoryPosts(int boardid);

	List<Post> getLocationPosts(LocationFiltering locationfiltering);
	
}
