<template>
	<div class="myboardlist-t">
		<div class="myboardlist-table" style="padding-top:50px">
			<div class="myboardlist-table-row" style="font-weight:bold; height:50px; font-size:17px;">
				내가 작성한 게시물
				<!-- <div class="myboardlist-table-col col-header table-post-boardtype">
					<p>종류</p>
				</div>
				<div class="myboardlist-table-col col-header table-post-title">
					<p>제목</p>
				</div>
				<div class="myboardlist-table-col col-header table-post-created_at">
					<p>작성일</p>
				</div> -->
			</div>
			<div class="myboardlist-table-row" v-for="post in posts" :key="`${post.categoryid}-${post.postid}`" @click="goToBoardDetail(post.categoryid, post.postid)">
				<div class="myboardlist-table-col table-post-boardtype"><p class="text-center">{{ post.categoryname }}</p></div>
				<div class="myboardlist-table-col table-post-title"><p>{{ post.title }}</p></div>
				<div class="myboardlist-table-col table-post-created_at"><p class="text-center">{{ post.created_at.slice(2, 16) }}</p></div>
			</div>
		</div>

		<div class="myboardlist-table-mobile">
			<div class="myboardlist-table-row-mobile">
				<span>내가 쓴 게시글 List</span><span>(게시판 종류-제목-작성일 순)</span>
			</div>
			<div class="myboardlist-table-row" v-for="post in posts" :key="`${post.categoryid}-${post.postid}`" @click="goToBoardDetail(post.categoryid, post.postid)">
				<div class="table-mobile-1">{{ post.categoryname }} | {{ post.title }}</div>
				<div class="table-mobile-2">{{ post.created_at.slice(2, 16) }}</div>
			</div>
		</div>
	</div>
		<!-- <v-data-table
			v-model="selectedPostsList"
			:headers="myPostheaders"
			:items="posts"
			:items-per-page="10"
			:single-select="true"
			show-select
			class="elevation-1">
		</v-data-table> -->
		<!-- <div class="mt-3" style="float: right;"> -->
			<!-- <span>선택한 게시글로 이동하기</span> -->
			<!-- <v-btn class="ml-3" color="blue" @click="goToBoardDetail(selectedPostsList[0].categoryid, selectedPostsList[0].postid)"><span style="color: white;">MOVE</span></v-btn>  -->
	   <!-- @click="goToBoardDetail(selectedPostsList[0].categoryid, selectedPostsList[0].postid)" -->
		<!-- </div> -->
  <!-- <v-layout mt-5 row wrap style="margin: 20px;">
    <v-flex v-for="i in this.showPostsCount" :key="i" style="width: 100%;">
	  <router-link :to="`/board/${(posts[i-1].categoryid >= 5) ? posts[i-1].categoryid :$store.state.categorys[posts[i-1].categoryid]}/${posts[i - 1].postid}`">
				<MyBoard 
									:date="posts[i - 1].created_at"
									:title="posts[i - 1].title"
									:body="posts[i - 1].body"
						:username="posts[i - 1].username"
						:memberid="posts[i - 1].memberid"
						:categoryName="posts[i - 1].categoryname">
				</MyBoard>
	  </router-link>

    </v-flex>
    <v-flex xs12 text-xs-center round my-5 v-if="loadMore">
      <v-btn v-if="this.posts.length > 6 && this.morePostsIcon" color="#f7b157" dark v-on:click="loadMorePosts"><v-icon size="25" class="mr-2">fa-plus</v-icon> 더 보기</v-btn>&nbsp; &nbsp;
			<v-btn v-if="this.hidePostsIcon" color="red" dark v-on:click="hidePosts"><v-icon size="25" class="mr-2">fa-minus</v-icon> 숨기기</v-btn>
		</v-flex>
  </v-layout> -->
</template>
<script>
import MyBoard from '@/components/board/MyPageBoard/MyBoard'
import axios from 'axios'
import '@/assets/css/MyBoardList.css'

export default {
	name: 'MyBoardList',
	props: {
		column: {type: Number, default: 1},
		limits: {type: Number, default: 6},
	},
	data() {
		return {
			loadMore: false,
			posts: [],
			showPostsCount : 0, 
			morePostsIcon : true,
			hidePostsIcon : false,
			myPostheaders: [
				{
					text: '종류',
					align: 'left',
					value: 'categoryname'
				},
				{ text: '제목', value: 'title' },
				{ text: '작성일', value: 'created_at' }
			],
			selectedPostsList: []
		}
	},
	components: {
		MyBoard
	},
	mounted() {
		this.getPosts()
	},
	methods: {
		getPosts() {
			axios.get(`api/postlist/${ this.$store.getters.user.memberid }`)
				.then( response => {
					this.posts = response.data
					// console.log(response.data)
					// response.data.forEach(postData => {
					// 	this.posts.push({
					// 		categoryname: postData.categoryname,
					// 		title: postData.title,
					// 		created_at: postData['created_at']
					// 	})
					// })
					// console.log(this.posts)
					// this.showPostsCount = (this.posts.length >= 6) ? 6 : this.posts.length
					// this.loadMore = (this.posts.length > 6) ? true : false
				})
		},
		goToBoardDetail(categoryID, postID) {
			this.$router.push(`/board/${(categoryID >= 5) ? categoryID : this.$store.state.categorys[categoryID]}/${postID}`)
		}
		// loadMorePosts() {
		// 	let adjustCount = this.showPostsCount + 6 < this.posts.length ? this.showPostsCount + 6 : this.posts.length
		// 	this.showPostsCount = adjustCount
		// 	this.morePostsIcon = adjustCount < this.posts.length ? true : false
		// 	this.hidePostsIcon = true
    // },
		// hidePosts() {
		// 	let adjustCount2 = this.showPostsCount - 6 > 6 ? this.showPostsCount - 6 : 6
		// 	this.showPostsCount = adjustCount2
		// 	this.hidePostsIcon = adjustCount2 === 6 ? false : true
		// 	this.morePostsIcon = true
		// }
	}
}
</script>
