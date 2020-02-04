<template>
	<!-- 메인 페이지에서 보여지는 Post List는 추후 좋아요 개수가 가장 많은 순서로 5~6개 정도 보여지는 것으로 수정 -->
  <v-layout mt-5 row wrap style="margin: 0 10%">
    <v-flex v-for="i in this.showPostsCount"  :key="i" style="width: 100%;"> <!-- col-12 sm6 md3 -->
      <router-link :to="`/post/${posts[i - 1].postid}`">
	  <Post 
              :date="posts[i - 1].created_at"
              :title="posts[i - 1].title"
              :body="posts[i - 1].body"
			  :username="posts[i - 1].username"
			  :memberid="posts[i - 1].memberid">
	  </Post>
	  </router-link>

    </v-flex>
  </v-layout>
</template>
<script>
import Post from '@/components/post/Post'
import axios from 'axios'

export default {
	name: 'PostList',
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
	mounted() {
		this.getPosts()
	},
	methods: {
		getPosts() {
			axios.get('http://192.168.31.110:8197/ssafyvue/api/posts')
				.then( response => {
					this.posts = response.data
					this.showPostsCount = (this.posts.length >= 6) ? 6 : this.posts.length  
					this.$emit('showPostCount', this.posts.length)
				})
		}
	}
}
</script>
