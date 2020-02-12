package com.ssafy.edu.vue.service;

import java.util.List;

import com.ssafy.edu.vue.dto.Category;
import com.ssafy.edu.vue.dto.CategoryPost;
import com.ssafy.edu.vue.dto.Code;
import com.ssafy.edu.vue.dto.Commentpost;
import com.ssafy.edu.vue.dto.Likepost;
import com.ssafy.edu.vue.dto.LocationFiltering;
import com.ssafy.edu.vue.dto.Post;
import com.ssafy.edu.vue.dto.PostPaging;
import com.ssafy.edu.vue.dto.Postinfo;


public interface IPostService {
	
	List<Post> getPostList(int memberid);
	
	Post getPost(int postid);
	
	void addPost(Post post);
	
	void updatePost(Post post);
	
	void deletePost(int postid);

	List<Post> getPosts(int memberid);

	List<Commentpost> getCommentPost(Postinfo postinfo);

	void addCommentPost(Commentpost commentpost);

	void updateCommentPost(Commentpost commentpost);

	void deleteCommentPost(int cpostid);

	List<Post> getCategoryPosts(CategoryPost categoryPost);

	List<Post> getLocationPosts(LocationFiltering locationfiltering);

	void addLikePost(Likepost likepost);

	void deleteLikePost(Likepost likepost);

	int getLikeCounts(Likepost likepost);

	int getCommentCounts(Likepost likepost);

	void addBoardCategory(Category category);

	int isLike(Likepost likepost);

	void addCode(Code code);

	List<Commentpost> getCommentCode(Postinfo postinfo);

	List<Post> getPostsPaging(PostPaging postpaging);

	List<Commentpost> getCommentJMT(Postinfo postinfo);
	
}
