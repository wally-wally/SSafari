<template>
  <v-layout mt-5 row wrap>
    <v-flex v-for="board in this.boards"  :key="board.id" style="width: 100%;"> <!-- col-12 sm6 md3 -->
		<router-link :to="`/board/${$store.state.categorys[board.categoryid]}/${board.postid}`">
	  <Board :date="board.created_at"
          :title="board.title"
          :body="board.body"
					:username="board.username"
					:memberid="board.memberid"
					:like="board.like">
	  </Board>
	  </router-link>
    </v-flex>
  </v-layout>
</template>
<script>
import Board from '@/components/board/boardCommonForm/Board'
import axios from 'axios'

export default {
	name: 'BoardtList',
	props: {
		boards: {type: Array}
	},
	data() {
		return {}
	},
	components: {
		Board
	}
}
</script>

<style>
	.loadMoreIconSection {
		margin-left: 10px;
	}
</style>