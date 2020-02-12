package com.ssafy.edu.vue.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.edu.vue.dto.AuthRequest;
import com.ssafy.edu.vue.dto.Category;
import com.ssafy.edu.vue.dto.CategoryPost;
import com.ssafy.edu.vue.dto.Code;
import com.ssafy.edu.vue.dto.Commentpost;
import com.ssafy.edu.vue.dto.Likepost;
import com.ssafy.edu.vue.dto.LocationFiltering;
import com.ssafy.edu.vue.dto.Member;
import com.ssafy.edu.vue.dto.Portfolio;
import com.ssafy.edu.vue.dto.Post;
import com.ssafy.edu.vue.dto.PostPaging;
import com.ssafy.edu.vue.dto.Postinfo;
import com.ssafy.edu.vue.help.BoolResult;
import com.ssafy.edu.vue.service.IPostService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:8197/humans/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Api(value = "SSAFY", description = "A5 Resouces Management 2020")
public class PostController {

	public static final Logger logger = LoggerFactory.getLogger(PostController.class);

	@Autowired
	private IPostService postservice;
	
	@ApiOperation(value = "post 전체 보기", response = List.class)
	@RequestMapping(value = "/posts", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> getPosts(HttpServletRequest rs) throws Exception {
		logger.info("1-------------getPosts-----------------------------" + new Date());
		
		int memberid = 0;
		if(rs.getAttribute("loginMember")!=null) {
			Member member = (Member) rs.getAttribute("loginMember");
			memberid = member.getMemberid();
		}
		List<Post> posts = postservice.getPosts(memberid);
		if (posts == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	
	@ApiOperation(value = "post category별 전체 보기", response = List.class)
	@RequestMapping(value = "/posts/{categoryid}", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> getCategoryPosts(@PathVariable int categoryid, HttpServletRequest rs) throws Exception {
		logger.info("1-------------getCategoryPosts-----------------------------" + new Date());
		int memberid = 0;
		if(rs.getAttribute("loginMember")!=null) {
			Member member = (Member) rs.getAttribute("loginMember");
			memberid = member.getMemberid();
		}
		List<Post> posts = postservice.getCategoryPosts(new CategoryPost(categoryid, memberid));
		if (posts == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	
	@ApiOperation(value = "post category 중 지역별 전체 보기", response = List.class)
	@RequestMapping(value = "/postslocation", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> getLocationPosts(@ModelAttribute LocationFiltering locationfiltering, HttpServletRequest rs) throws Exception {
		logger.info("1-------------getLocationPosts-----------------------------" + new Date());
		List<Post> posts;
		int memberid = 0;
		if(rs.getAttribute("loginMember")!=null) {
			Member member = (Member) rs.getAttribute("loginMember");
			memberid = member.getMemberid();
		}
		if(locationfiltering.getLocationid()==0) {
			posts = postservice.getCategoryPosts(new CategoryPost(locationfiltering.getCategoryid(),memberid));
		}else {
			locationfiltering.setMemberid(memberid);
			posts = postservice.getLocationPosts(locationfiltering);
		}
		if (posts == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	
	@ApiOperation(value = "post 멤버별 전체 보기", response = List.class)
	@RequestMapping(value = "/postlist/{memberid}", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> getPostList(@PathVariable int memberid) throws Exception {
		logger.info("1-------------getPostList-----------------------------" + new Date());
		List<Post> posts = postservice.getPostList(memberid);
		if (posts == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	
	@ApiOperation(value = "post 상세 보기", response = List.class)
	@RequestMapping(value = "/post/{postid}", method = RequestMethod.GET)
	public ResponseEntity<Post> getPost(@PathVariable int postid,HttpServletRequest rs) throws Exception {
		logger.info("1-------------getPost-----------------------------" + new Date());
		Post post = postservice.getPost(postid);
		if (post == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Post>(post, HttpStatus.OK);
	}
	
	@ApiOperation(value = "post 추가", response = List.class)
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public ResponseEntity<BoolResult> addPost(@RequestBody Post post) throws Exception {
		logger.info("1-------------addPost-----------------------------" + new Date());
		postservice.addPost(post);
		BoolResult nr=new BoolResult();
   		nr.setName("addPost");
   		nr.setState("succ");
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}
	
	@ApiOperation(value = "post 수정", response = BoolResult.class)
	@RequestMapping(value = "/post", method = RequestMethod.PUT)
	public ResponseEntity<BoolResult> updatePost(@RequestBody Post post) throws Exception {
		logger.info("1-------------updatePost-----------------------------" + new Date());
		postservice.updatePost(post);
		BoolResult nr=new BoolResult();
   		nr.setName("updatePost");
   		nr.setState("succ");
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}
	
	@ApiOperation(value = "post 삭제", response = BoolResult.class)
	@RequestMapping(value = "/post/{postid}", method = RequestMethod.DELETE)
	public ResponseEntity<BoolResult> deletePost(@PathVariable int postid) throws Exception {
		logger.info("1-------------deletePost-----------------------------" + new Date());
		postservice.deletePost(postid);
		BoolResult nr=new BoolResult();
   		nr.setName("deletePost");
   		nr.setState("succ");
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}
	
	@ApiOperation(value = "post Comment 전체 보기", response = List.class)
	@RequestMapping(value = "/commentpost", method = RequestMethod.GET)
	public ResponseEntity<List<Commentpost>> getCommentPost(@ModelAttribute Postinfo postinfo, HttpServletRequest rs) throws Exception {
		logger.info("1-------------getCommentPost-----------------------------" + new Date());
		logger.info("2--------"+postinfo);
		int memberid = 0;
		if(rs.getAttribute("loginMember")!=null) {
			Member member = (Member) rs.getAttribute("loginMember");
			memberid = member.getMemberid();
		}
		postinfo.setMemberid(memberid);
		List<Commentpost> posts;
		if(postinfo.getCategoryid()==3) {
			posts = postservice.getCommentCode(postinfo);
		}else if(postinfo.getCategoryid()==4){
			posts = postservice.getCommentJMT(postinfo);
		}
		else {
			posts = postservice.getCommentPost(postinfo);
		}
		return new ResponseEntity<List<Commentpost>>(posts, HttpStatus.OK);
	}
	
	@ApiOperation(value = "post Comment 추가", response = List.class)
	@RequestMapping(value = "/commentpost", method = RequestMethod.POST)
	public ResponseEntity<List<Commentpost>> addCommentPost(@RequestBody Commentpost commentpost) throws Exception {
		logger.info("1-------------addCommentPost-----------------------------" + new Date());
		postservice.addCommentPost(commentpost);
		Postinfo postinfo = new Postinfo(commentpost.getCategoryid(),commentpost.getPostid());
		List<Commentpost> posts;
		if(commentpost.getCategoryid()==3) {
			posts = postservice.getCommentCode(postinfo);
		}else if(commentpost.getCategoryid()==4){
			posts = postservice.getCommentJMT(postinfo);
		}
		else {
			posts = postservice.getCommentPost(postinfo);
		}
		return new ResponseEntity<List<Commentpost>>(posts, HttpStatus.OK);
	}
	
	@ApiOperation(value = "post Comment 수정", response = BoolResult.class)
	@RequestMapping(value = "/commentpost", method = RequestMethod.PUT)
	public ResponseEntity<List<Commentpost>> updateCommentPost(@RequestBody Commentpost commentpost) throws Exception {
		logger.info("1-------------updateCommentPost-----------------------------" + new Date());
		postservice.updateCommentPost(commentpost);
		Postinfo postinfo = new Postinfo(commentpost.getCategoryid(),commentpost.getPostid());
		List<Commentpost> posts;
		if(commentpost.getCategoryid()==3) {
			posts = postservice.getCommentCode(postinfo);
		}else if(commentpost.getCategoryid()==4){
			posts = postservice.getCommentJMT(postinfo);
		}
		else {
			posts = postservice.getCommentPost(postinfo);
		}
		return new ResponseEntity<List<Commentpost>>(posts, HttpStatus.OK);
	}
	
	@ApiOperation(value = "post Comment 삭제", response = BoolResult.class)
	@RequestMapping(value = "/commentpost", method = RequestMethod.DELETE)
	public ResponseEntity<List<Commentpost>> deleteCommentPost(@RequestBody Commentpost commentpost) throws Exception {
		logger.info("1-------------deleteCommentPost-----------------------------" + new Date());
		postservice.deleteCommentPost(commentpost.getCpostid());
		Postinfo postinfo = new Postinfo(commentpost.getCategoryid(),commentpost.getPostid());
		List<Commentpost> posts;
		if(commentpost.getCategoryid()==3) {
			posts = postservice.getCommentCode(postinfo);
		}else if(commentpost.getCategoryid()==4){
			posts = postservice.getCommentJMT(postinfo);
		}
		else {
			posts = postservice.getCommentPost(postinfo);
		}
		return new ResponseEntity<List<Commentpost>>(posts, HttpStatus.OK);
	}
	
	@ApiOperation(value = "post Like 추가 (like count up)", response = List.class)
	@RequestMapping(value = "/likepost", method = RequestMethod.POST)
	public ResponseEntity<BoolResult> addLikePost(@RequestBody Likepost likepost) throws Exception {
		logger.info("1-------------addLikePost-----------------------------" + new Date());
		postservice.addLikePost(likepost);
		BoolResult nr=new BoolResult();
   		nr.setName("addLikePost");
   		nr.setState("succ");
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}
	
	@ApiOperation(value = "post Like 삭제 (like count down)", response = List.class)
	@RequestMapping(value = "/likepost", method = RequestMethod.DELETE)
	public ResponseEntity<BoolResult> deleteLikePost(@RequestBody Likepost likepost) throws Exception {
		logger.info("1-------------deleteLikePost-----------------------------" + new Date());
		postservice.deleteLikePost(likepost);
		BoolResult nr=new BoolResult();
   		nr.setName("deleteLikePost");
   		nr.setState("succ");
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}
	
	@ApiOperation(value = "post 좋아요 수 출력", response = BoolResult.class)
	@RequestMapping(value = "/likecounts", method = RequestMethod.GET)
	public ResponseEntity<Integer> getLikeCounts(@ModelAttribute Likepost likepost) throws Exception {
		logger.info("1-------------getLikeCounts-----------------------------" + new Date());
		int counts = postservice.getLikeCounts(likepost);
		return new ResponseEntity<Integer>(counts, HttpStatus.OK);
	}
	
	@ApiOperation(value = "post 댓글 수 출력", response = BoolResult.class)
	@RequestMapping(value = "/commentcounts", method = RequestMethod.GET)
	public ResponseEntity<Integer> getCommentCounts(@ModelAttribute Likepost likepost) throws Exception {
		logger.info("1-------------getCommentCounts-----------------------------" + new Date());
		int counts = postservice.getCommentCounts(likepost);
		return new ResponseEntity<Integer>(counts, HttpStatus.OK);
	}
	
	@ApiOperation(value = "post category 추가 (게시판 추가)", response = List.class)
	@RequestMapping(value = "/boardcategory", method = RequestMethod.POST)
	public ResponseEntity<BoolResult> addBoardCategory(@RequestBody Category category, HttpServletRequest rs) throws Exception {
		logger.info("1-------------addBoardCategory-----------------------------" + new Date());
		logger.info("2------------" + category);
		logger.info("3-----"+rs.getAttribute("loginMember"));
		BoolResult nr=new BoolResult();
		int loginid = 0;
		if (rs.getAttribute("loginMember") != null) {
			Member member = (Member) rs.getAttribute("loginMember");
			loginid = member.getMemberid();
		}else {
			nr.setName("로그인 필요");
			return new ResponseEntity<BoolResult>(nr, HttpStatus.BAD_REQUEST);
		}
		category.setMemberid(loginid);
		postservice.addBoardCategoryAuth(category);
   		nr.setName("addBoardCategory");
   		nr.setState("succ");
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}
	
	@ApiOperation(value = "post category 목록 (게시판 목록)", response = List.class)
	@RequestMapping(value = "/boardcategory", method = RequestMethod.GET)
	public ResponseEntity<List<Category>> getBoardCategory() throws Exception {
		logger.info("1-------------getBoardCategory-----------------------------" + new Date());
		List<Category> list = postservice.getBoardCategory();
		return new ResponseEntity<List<Category>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "post category 인증 요청 목록 (게시판 인증 요청 목록)", response = List.class)
	@RequestMapping(value = "/boardcategory/auth", method = RequestMethod.GET)
	public ResponseEntity<List<Category>> getBoardCategoryAuth() throws Exception {
		logger.info("1-------------getBoardCategoryAuth-----------------------------" + new Date());
		List<Category> list = postservice.getBoardCategoryAuth();
		return new ResponseEntity<List<Category>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "post category 인증 승인 (게시판 인증 승인)", response = List.class)
	@RequestMapping(value = "/boardcategory/auth", method = RequestMethod.PUT)
	public ResponseEntity<BoolResult> checkBoardCategoryAuth(@RequestBody Category request) throws Exception {
		logger.info("1-------------checkBoardCategoryAuth-----------------------------" + new Date());
		BoolResult nr=new BoolResult();
		if(request.getFlag()==1) {
			Category category = postservice.getBoardCategoryOne(request.getId());
			postservice.addBoardCategory(category);
			nr.setName("인증 승인");
		}else {
			nr.setName("인증 거절");
		}
		postservice.deleteBoardCategoryAuth(request.getId());
	
   		nr.setState("succ");
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}
	
	@ApiOperation(value = "좋아요 클릭 여부 확인", response = List.class)
	@RequestMapping(value = "/like", method = RequestMethod.GET)
	public ResponseEntity<Integer> isLike(@ModelAttribute("data") Likepost likepost) throws Exception {
		logger.info("1-------------isLike-----------------------------" + new Date());
		int result = postservice.isLike(likepost);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "post pagination", response = List.class)
	@RequestMapping(value = "/posts/page", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> getPostsPaging(@ModelAttribute PostPaging postpaging, HttpServletRequest rs) throws Exception {
		logger.info("1-------------getPostsPaging-----------------------------" + new Date());
		logger.info("2-------------"+postpaging);
		int memberid = 0;
		if(rs.getAttribute("loginMember")!=null) {
			Member member = (Member) rs.getAttribute("loginMember");
			memberid = member.getMemberid();
		}
		postpaging.setMemberid(memberid);
		String keyword;
		if(postpaging.getKeyword()==null) {
			keyword="";
		}else {
			keyword = postpaging.getKeyword();
		}
		postpaging.setKeyword("%"+keyword+"%");
		postpaging.setPage((postpaging.getPage()-1)*20);
		List<Post> posts = postservice.getPostsPaging(postpaging);
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
}
