<template>
  <v-layout mt-3 row wrap>
    <v-flex v-for="board in this.boards"  :key="board.postid" style="width: 100%;height:80px;">
	<router-link :to="`/board/${boardname}/${board.postid}`">
	  <Board
	  	:board='board'>
	  </Board>
	  </router-link>
    </v-flex>
  </v-layout>
</template>

<script>
import Board from '@/components/board/boardCommonForm/Board'
import axios from 'axios'

export default {
	name: 'BoardList',
	props: {
		boards: {type: Array},
		boardname : {type: String}
	},
	data() {
		return {
		}
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