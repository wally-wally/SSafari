<template>
	<div>
		<h1 class="my-5 text-center" style="font-family: 'Noto Sans KR';">내가 '좋아요' 누른 게시글 리스트</h1>
		<v-data-table
			:headers="likeHeaders"
			:items="likes"
			:items-per-page="10"
			item-key="postid"
			class="elevation-1">
			<template v-slot:item.action="{ item }">
				<v-icon small class="mr-2" @click="goToBoardDetail(item.categoryid, item.postid)">
					edit
				</v-icon>
			</template>
		</v-data-table>
	</div>
  <!-- <v-layout mt-5 row wrap style="margin: 20px;">
    <v-flex v-for="i in this.showLikesCount" :key="i" style="width: 100%;">
	  <router-link :to="`/board/${(likes[i-1].categoryid >= 5) ? likes[i-1].categoryid :$store.state.categorys[likes[i-1].categoryid]}/${likes[i - 1].postid}`">
				<MyLike 
									:date="likes[i - 1].created_at"
									:title="likes[i - 1].title"
						:creator="`${(currentMemberId === likes[i - 1].memberid ? true : false) ? likes[i - 1].username : (likes[i - 1].anonymous === 1) ? '익명' : likes[i - 1].username}`"
						:categoryName="likes[i - 1].categoryname">
				</MyLike>
	  </router-link>

    </v-flex>
    <v-flex xs12 text-xs-center round my-5 v-if="loadMore">
      <v-btn v-if="this.likes.length > 6 && this.moreLikesIcon" color="#f7b157" dark v-on:click="loadMoreLikes"><v-icon size="25" class="mr-2">fa-plus</v-icon> 더 보기</v-btn>&nbsp; &nbsp;
			<v-btn v-if="this.hideLikesIcon" color="red" dark v-on:click="hidePosts"><v-icon size="25" class="mr-2">fa-minus</v-icon> 숨기기</v-btn>
		</v-flex>
  </v-layout> -->
</template>
<script>
import MyLike from '@/components/board/MyPageBoard/MyLike.vue'
import axios from 'axios'

export default {
	name: 'MyLikeList',
	props: {
		column: {type: Number, default: 1},
		limits: {type: Number, default: 6},
	},
	data() {
		return {
			likes: [],
			// loadMore: false,
			showLikesCount : 0, 
			// moreLikesIcon : true,
			// hideLikesIcon : false,
			currentMemberId: '',
			likeHeaders: [
        {
					text: '종류',
					align: 'left',
					value: 'categoryname'
				},
				{ text: '제목', value: 'title' },
				{ text: '작성일', value: 'created_at' },
				{ text: '게시글 상세보기', value: 'action', sortable: false }
      ],
		}
	},
	components: {
		MyLike
	},
	mounted() {
		this.getMyLikes()
		this.currentMemberId =  this.$store.state.memberid
	},
	methods: {
		getMyLikes() {
			axios.get('api/member/likepost', { headers: { 'access-token': this.$store.state.token }})
				.then( response => {
					this.likes = response.data
					console.log(this.likes)
					// this.showLikesCount = (this.likes.length >= 6) ? 6 : this.likes.length
					// this.loadMore = (this.likes.length > 6) ? true : false
				})
		},
		goToBoardDetail(categoryID, postID) {
			this.$router.push(`/board/${(categoryID >= 5) ? categoryID : this.$store.state.categorys[categoryID]}/${postID}`)
		}
		// loadMoreLikes() {
		// 	let adjustCount = this.showLikesCount + 6 < this.likes.length ? this.showLikesCount + 6 : this.likes.length
		// 	this.showLikesCount = adjustCount
		// 	this.moreLikesIcon = adjustCount < this.likes.length ? true : false
		// 	this.hideLikesIcon = true
    // },
		// hidePosts() {
		// 	let adjustCount2 = this.showLikesCount - 6 > 6 ? this.showLikesCount - 6 : 6
		// 	this.showLikesCount = adjustCount2
		// 	this.hideLikesIcon = adjustCount2 === 6 ? false : true
		// 	this.moreLikesIcon = true
		// }
	}
}
</script>
