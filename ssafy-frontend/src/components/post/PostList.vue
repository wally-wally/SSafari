<template>
  <v-layout mt-5 row wrap>
    <v-flex v-for="i in this.showPostsCount"  :key="i" style="width: 100%;"> <!-- col-12 sm6 md3 -->
      <router-link :to="`/board/${posts[i - 1].postid}`">
	  <Post :date="posts[i - 1].created_at"
          :title="posts[i - 1].title"
          :body="posts[i - 1].body"
					:username="posts[i - 1].username"
					:memberid="posts[i - 1].memberid"
					:like="posts[i - 1].like">
	  </Post>
	  </router-link>
    </v-flex>
    <v-flex round my-5 v-if="loadMore">
      <v-btn class="loadMoreIconSection" v-if="this.posts.length > 5 && this.morePostsIcon" color="#f7b157" dark v-on:click="loadMorePosts"><v-icon size="25" class="mr-2">fa-plus</v-icon> 더 보기</v-btn>&nbsp; &nbsp;
			<v-btn class="loadMoreIconSection" v-if="this.hidePostsIcon" color="red" dark v-on:click="hidePosts"><v-icon size="25" class="mr-2">fa-minus</v-icon> 숨기기</v-btn>
		</v-flex>
  </v-layout>
</template>
<script>
import Post from '@/components/post/Post'
import axios from 'axios'

export default {
	name: 'PostList',
	props: {
		column: {type: Number, default: 1},
		limits: {type: Number, default: 5},
		loadMore: {type: Boolean, default: false},
		category : {type: String, default : null}
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
		Post
	},
	mounted(){
		getPost()
	},
	watch: {
		category: {
			handler() {
				this.getPosts()
			}
		}
	},
	methods: {
		getPosts() {
			if (this.category){
				console.log(this.category)
			axios.get(`api/posts/${this.$store.state.category[this.category]}`)
				.then( response => {
					console.log(response.data)
					this.posts = response.data
					this.showPostsCount = (this.posts.length >= 5) ? 5 : this.posts.length  
					this.$emit('showPostCount', this.posts.length)
				})
			} else {
			axios.get(`api/posts`)
				.then( response => {
					console.log(response.data)
					this.posts = response.data
					this.showPostsCount = (this.posts.length >= 5) ? 5 : this.posts.length  
					this.$emit('showPostCount', this.posts.length)
				})
			}
		},
		loadMorePosts() {
			let adjustCount = this.showPostsCount + 5 < this.posts.length ? this.showPostsCount + 5 : this.posts.length
			this.showPostsCount = adjustCount
			this.morePostsIcon = adjustCount < this.posts.length ? true : false
			this.hidePostsIcon = true
    },
		hidePosts() {
			let adjustCount2 = this.showPostsCount - 5 > 5 ? this.showPostsCount - 5 : 5
			this.showPostsCount = adjustCount2
			this.hidePostsIcon = adjustCount2 === 5 ? false : true
			this.morePostsIcon = true
		}
	}
}
</script>

<style>
	.loadMoreIconSection {
		margin-left: 10px;
	}
</style>