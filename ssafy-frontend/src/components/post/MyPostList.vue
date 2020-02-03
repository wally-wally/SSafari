<template>
  <v-layout mt-5 row wrap style="margin: 20px">
    <v-flex v-for="i in this.showPostsCount"  :key="i"> <!-- col-12 sm6 md3 -->
      <router-link :to="`/post/${posts[i - 1].postid}`">
	  <MyPost 
              :date="posts[i - 1].created_at"
              :title="posts[i - 1].title"
              :body="posts[i - 1].body"
			  :username="posts[i - 1].username"
			  :memberid="posts[i - 1].memberid">
	  </MyPost>
	  </router-link>

    </v-flex>
    <v-flex xs12 text-xs-center round my-5 v-if="loadMore">
      <v-btn v-if="this.posts.length > 6 && this.morePostsIcon" color="#f7b157" dark v-on:click="loadMorePosts"><v-icon size="25" class="mr-2">fa-plus</v-icon> 더 보기</v-btn>&nbsp; &nbsp;
			<v-btn v-if="this.hidePostsIcon" color="red" dark v-on:click="hidePosts"><v-icon size="25" class="mr-2">fa-minus</v-icon> 숨기기</v-btn>
		</v-flex>
  </v-layout>
</template>
<script>
import MyPost from '@/components/post/MyPost'
import axios from 'axios'

export default {
	name: 'MyPostList',
	props: {
		column: {type: Number, default: 1},
		limits: {type: Number, default: 6},
		loadMore: {type: Boolean, default: false}
	},
	data() {
		return {
			posts: [],
			showPostsCount : 0, 
			morePostsIcon : true,
			hidePostsIcon : false
		}
	},
	components: {
		MyPost
	},
	mounted() {
		this.getPosts()
	},
	methods: {
		getPosts() {
			axios.get(`http://192.168.31.110:8197/ssafyvue/api/postlist/${this.$session.get('data')['memberid']}`)
				.then( response => {
					this.posts = response.data
					this.showPostsCount = (this.posts.length >= 6) ? 6 : this.posts.length  
				})
		},
		loadMorePosts() {
			let adjustCount = this.showPostsCount + 6 < this.posts.length ? this.showPostsCount + 6 : this.posts.length
			this.showPostsCount = adjustCount
			this.morePostsIcon = adjustCount < this.posts.length ? true : false
			this.hidePostsIcon = true
    },
		hidePosts() {
			let adjustCount2 = this.showPostsCount - 6 > 6 ? this.showPostsCount - 6 : 6
			this.showPostsCount = adjustCount2
			this.hidePostsIcon = adjustCount2 === 6 ? false : true
			this.morePostsIcon = true
		}
	}
}
</script>
<style>
  .mw-700 {
    max-width: 700px;
    margin: auto;
  }
</style>
