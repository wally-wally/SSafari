<template>
	<div>
		<h1 class="mt-2 mb-5 text-center" style="font-family: 'Noto Sans KR';">내가 '좋아요' 누른 게시글 리스트</h1>
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
			showLikesCount : 0, 
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
				})
		},
		goToBoardDetail(categoryID, postID) {
			this.$router.push(`/board/${(categoryID >= 5) ? categoryID : this.$store.state.categorys[categoryID]}/${postID}`)
		}
	}
}
</script>
