<template>
  <div class="boards-list">
    <div class="boards" v-for="board in boards" :key="`${board.postid}-${board['created_at']}`">
      <div class="board-element d-flex justify-space-between" @click="goBoardDetail(board.postid)">
        <strong>
          <span v-if="boardType === 'jmt'" class="board-title" @click="goBoardDetail(board.postid)">{{ board.name }}</span>
          <span v-else class="board-title">{{ board.title }}</span>
        </strong>
        <span v-if="boardType === 'jmt'" class="board-info">{{ board.location }}</span>
        <span v-else class="board-info">{{ board.created_at.slice(2, 16) }} | {{ board.username }}</span>
      </div>
    </div>
  </div>
</template>

<script>
import '@/assets/css/BoardListAll.css'
export default {
  name: 'BoardListAll',
  props: {
    boards: { type: Array },
    boardType: { type: String }
  },
  data() {
    return {}
  },
  methods: {
    goBoardDetail(postID) {
      this.$router.push(`board/${this.boardType}/${postID}`)
    }
  }
}
</script>

<style>

</style>