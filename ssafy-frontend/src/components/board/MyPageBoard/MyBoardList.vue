<template>
	<div>
		<h1 class="mt-2 mb-5 text-center" style="font-family: 'Noto Sans KR';">내가 작성한 게시글 리스트</h1>
		<v-data-table
			v-model="selectedPostsList"
			:headers="myPostheaders"
			:items="posts"
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
				{ text: '작성일', value: 'created_at' },
				{ text: '좋아요 수', value: 'likes' },
				{ text: '댓글 수', value: 'comments' },
				{ text: '게시글 상세보기', value: 'action', sortable: false }
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
				})
		},
		goToBoardDetail(categoryID, postID) {
			this.$router.push(`/board/${(categoryID >= 5) ? categoryID : this.$store.state.categorys[categoryID]}/${postID}`)
		}
	}
}
</script>
