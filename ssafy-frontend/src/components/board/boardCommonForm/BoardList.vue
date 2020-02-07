<template>
  <v-layout mt-5 row wrap>
    <v-flex v-for="i in this.showPostsCount"  :key="i" style="width: 100%;"> <!-- col-12 sm6 md3 -->
      <router-link :to="`/board/${posts[i - 1].postid}`">
	  <Board :date="posts[i - 1].created_at"
          :title="posts[i - 1].title"
          :body="posts[i - 1].body"
					:username="posts[i - 1].username"
					:memberid="posts[i - 1].memberid"
					:like="posts[i - 1].like">
	  </Board>
	  </router-link>
    </v-flex>
    <v-flex round my-5 v-if="loadMore">
      <v-btn class="loadMoreIconSection" v-if="this.posts.length > 5 && this.morePostsIcon" color="#f7b157" dark v-on:click="loadMorePosts"><v-icon size="25" class="mr-2">fa-plus</v-icon> 더 보기</v-btn>&nbsp; &nbsp;
			<v-btn class="loadMoreIconSection" v-if="this.hidePostsIcon" color="red" dark v-on:click="hidePosts"><v-icon size="25" class="mr-2">fa-minus</v-icon> 숨기기</v-btn>
		</v-flex>
  </v-layout>
</template>
<script>
import Board from '@/components/board/boardCommonForm/Board'
import axios from 'axios'

export default {
	name: 'BoardtList',
	props: {
		column: {type: Number, default: 1},
		limits: {type: Number, default: 5},
		loadMore: {type: Boolean, default: false},
		category : {type: String, default : null},
		region: {type: String, default: 'All'}
	},
	data() {
		return { 
			boards: [],
			showPostsCount : 0, 
			morePostsIcon : true,
			hidePostsIcon : false
		}
	},
	components: {
		Board
	},
	mounted(){
		this.getPosts(),
		this,getLikeCounts(),
		this.getCommentsCounts()
	},
	watch: {
		category: {
			handler() {
				this.getPosts()
			}
		}
	},
	// methods: {
	// 	getPosts() {
	// 		axios.get('http://192.168.31.110:8197/ssafyvue/api/posts')
	// 			.then( response => {
	// 				this.posts = response.data
	// 				this.showPostsCount = (this.posts.length >= 5) ? 5 : this.posts.length  
	// 				this.$emit('showPostCount', this.posts.length)
	// 			})
	// 	},
	// 	getLikeCounts() {
	// 		axios.get('http://192.168.31.110:8197/ssafyvue/api/likecounts')
	// 			.then( response => {
	// 				console.log(response.data)
	// 			})
	// 	},
	// 	getCommentsCounts() {
	// 		axios.get('http://192.168.31.110:8197/ssafyvue/api/commentcounts')
	// 			.then( response => {
	// 				console.log(response.data)
	// 			})
	// 	},
	// },
	methods: {
		getPosts() {
			axios.get(`api/posts/${this.$store.state.category[this.category]}`)
				.then( response => {
					// console.log(response.data)
					this.posts = response.data
					this.showPostsCount = (this.posts.length >= 5) ? 5 : this.posts.length  
					this.$emit('showPostCount', this.posts.length)
				})
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
	},
	watch: {
		region: {
			handler() {
				axios.get('api/posts')
					.then( response => {
						this.posts = [] // posts 리스트 초기화 작업
						let regionNumber = { 'Seoul': 0, 'Daejeon': 1, 'Gawngju': 2, 'Gumi': 3 }
						if (this.region !== 'All') {
							let regionPostData = []
							for (let i = 0; i < response.data.length; i++) {
								if (response.data[i]['locationid'] == regionNumber[this.region]) {
									regionPostData.push(response.data[i])
								}
							}
							this.posts = regionPostData
						} else {
							this.posts = response.data
						}
						this.showPostsCount = (this.posts.length >= 5) ? 5 : this.posts.length  
						this.$emit('showPostCount', this.posts.length)
					})
			}
		}
	}
}
</script>

<style>
	.loadMoreIconSection {
		margin-left: 10px;
	}
</style>