<template>
  <div class="subheader-top d-flex">
    <div class="subheader-section col-3">
      <div class="subheader-section-title-1">공통 게시판</div>
      <div class="common-notice">
        <router-link to="/board/free/" :style="{ color: '#60666C'}">
          <div class="free-notice"><i class="fas fa-chalkboard"></i></div>
        </router-link>
        <router-link to="/board/job/" :style="{ color: '#60666C'}">
          <div class="job-notice"><i class="fas fa-user-tie"></i></div>
        </router-link>
        <router-link to="/board/codereview/" :style="{ color: '#60666C'}">
          <div class="code-notice"><i class="fas fa-code"></i></div>
        </router-link>
        <router-link to="/board/jmt/" :style="{ color: '#60666C'}">
          <div class="restaurant-notice"><i class="fas fa-utensils"></i></div>
        </router-link>
      </div>
    </div>
    <div class="subheader-section col-9">
      <div class="subheader-section-title-2">
        <span>
          <span class="pr-2">커스텀 게시판</span>
          <router-link v-if="this.$route.path !== '/board/create'" :to="{name : 'BoardCreate'}" :style="{ color: '#60666C'}">
            <span class="createBoardBtn" v-if="ssafyAuth === 2 || ssafyAuth === 1">게시판 생성</span>
          </router-link>
        </span>
        <span >
          <div id="notice-search-form">
            <input type="text" id="notice-search">
            <button style="padding-left: 5px;" @click="searchBoard"><i class="fas fa-search"></i></button>
          </div>
        </span>
      </div>
      <div class="custom-notices">
        <span v-for="board in boards" :key="board.id" class="mx-1">
          <router-link :to="`/board/${board.id}/`" :style="{ color: '#60666C'}">
            <i class="fas fa-chalkboard" id="custom-notice">{{ board.name }}</i>
          </router-link>
        </span>
      </div>
    </div>
    <!-- <div class="notice-kind">
      <router-link to="/board/free/">
        <span class="free-notice"><i class="fas fa-chalkboard"></i></span>
      </router-link>
      <router-link to="/board/job/">
        <span class="job-notice"><i class="fas fa-user-tie"></i></span>
      </router-link>
      <router-link to="/board/codereview/">
        <span class="code-notice"><i class="fas fa-code"></i></span>
      </router-link>
      <router-link to="/board/jmt/">
        <span class="restaurant-notice"><i class="fas fa-utensils"></i></span>
      </router-link>
      <div v-for="board in boards" :key="board.id" class="mx-1">
        <router-link :to="`/board/${board.id}/`">
          <i class="fas fa-chalkboard">{{ board.name }}</i>
        </router-link>
      </div>
      <v-btn v-if="ssafyAuth === 2 || ssafyAuth === 1" :to="{name : 'BoardCreate'}">게시판 추가 하기</v-btn>
    </div> -->
  </div>
</template>

<script>
import '@/assets/css/SubBoardHeader.css'
import axios from 'axios'

export default {
  name: 'SubBoardHeader',
  data() {
    return {
      ssafyAuth: '',
      boards: []
    }
  },
  mounted() {
    this.ssafyAuth = this.$store.state.auth
    this.getBoards()
  },
  methods: {
    getBoards() {
      axios.get('api/boardcategory')
        .then(response => {
          // console.log(response.data)
          this.boards = response.data
        })
    },
    searchBoard() {
      const searchBoardTitle = document.querySelector('#notice-search').value
      axios.get('api/boardcategory', {params: {name: searchBoardTitle}})
        .then(response => {
          console.log(response.data)
          this.boards = response.data
          document.querySelector('#notice-search').value = '' // 검색해서 게시판 리스트 가져온 후 검색창 초기화
        })
    }
  }
}
</script>