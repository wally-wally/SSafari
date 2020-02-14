package com.ssafy.edu.vue.service;

import java.util.List;

import com.ssafy.edu.vue.dto.Category;
import com.ssafy.edu.vue.dto.CategoryPost;
import com.ssafy.edu.vue.dto.Code;
import com.ssafy.edu.vue.dto.Commentpost;
import com.ssafy.edu.vue.dto.LocationFiltering;
import com.ssafy.edu.vue.dto.Post;
import com.ssafy.edu.vue.dto.PostPaging;
import com.ssafy.edu.vue.dto.Postinfo;


public interface IPostService {
	
	List<Post> getPostList(int memberid);
	
	Post getPost(Postinfo postinfo);
	
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

	void addLikePost(Postinfo likepost);

	void deleteLikePost(Postinfo likepost);

	int getLikeCounts(Postinfo likepost);

	int getCommentCounts(Postinfo likepost);

	void addBoardCategory(Category category);

	int isLike(Postinfo likepost);

	void addCode(Code code);

	List<Commentpost> getCommentCode(Postinfo postinfo);

	List<Commentpost> getCommentJMT(Postinfo postinfo);

	List<Post> getPostsPaging(PostPaging postpaging);

	List<Category> getBoardCategory();

	List<Category> getBoardCategoryAuth();

	Category getBoardCategoryOne(int id);

	void deleteBoardCategoryAuth(int id);

	void addBoardCategoryAuth(Category category);

	List<Category> getBoardSearch(String keyword);
	
	int getTotalPost(PostPaging postpaging);

	int getPostCategory(int postid);

}
