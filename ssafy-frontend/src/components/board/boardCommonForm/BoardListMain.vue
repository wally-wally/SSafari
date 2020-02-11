<template>
  <v-layout mt-5 row wrap style="margin: 0 10%">
    <v-flex v-for="i in this.showPostsCount"  :key="i" style="width: 100%;"> <!-- col-12 sm6 md3 -->
		<router-link :to="`/board/${posts[i - 1].postid}`">
			<Board  :date="posts[i - 1].created_at"
				:title="posts[i - 1].title"
				:body="posts[i - 1].body"
				:username="posts[i - 1].username"
				:memberid="posts[i - 1].memberid">
			</Board>
	  	</router-link>
    </v-flex>
  </v-layout>
</template>
<script>
import Board from '@/components/board/boardCommonForm/Board'
import axios from 'axios'

export default {
	name: 'BoardListMain',
	data() {
		return {
			posts: [],
			showPostsCount : 0, 
			morePostsIcon : true,
			hidePostsIcon : false
		}
	},
	components: {
		Board
	},
	mounted() {
		this.getPosts()
	},
	methods: {
		getPosts() {
			axios.get('api/posts/1')
				.then( response => {
					console.log(response.data)
					this.posts = response.data
					this.showPostsCount = (this.posts.length >= 5) ? 5 : this.posts.length
					console.log(showPostsCount)
				})
		}
	}
}
</script>
