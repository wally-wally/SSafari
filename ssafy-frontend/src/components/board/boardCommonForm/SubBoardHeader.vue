<template>
  <div class="subheader-top d-flex">
    <div class="subheader-section col-3">
      <div class="common-notice">
        <v-row align="center">
          <v-col col-6>
          <router-link to="/board/free/" :style="{ color: '#60666C'}">
            <div class="free-notice"><i class="fas fa-chalkboard"></i></div>
          </router-link>
          </v-col>
          <v-col col-6>
            <router-link to="/board/job/" :style="{ color: '#60666C'}">
              <div class="job-notice"><i class="fas fa-user-tie"></i></div>
            </router-link>
          </v-col>
        </v-row>
        <v-row align="center">
          <v-col col-6>
            <router-link to="/board/code/" :style="{ color: '#60666C'}">
              <div class="code-notice"><i class="fas fa-code"></i></div>
            </router-link>
          </v-col>
          <v-col col-6>
          <router-link to="/board/jmt/" :style="{ color: '#60666C'}">
            <div class="restaurant-notice"><i class="fas fa-utensils"></i></div>
          </router-link>
          </v-col>
        </v-row>
      </div>
    </div>
    <div class="subheader-section col-9">
      <v-row v-for="i in 2" :key="i">
        <v-col v-for="j in boardrowcnt" :key="j">
          <div align="center" v-if="i*boardrowcnt + j -boardrowcnt-1 < boards.length && i*boardrowcnt + j -boardrowcnt-1 < boardrowcnt *2 -1">
            <router-link :to="`/board/${boards[i*boardrowcnt + j -boardrowcnt-1].id}/`" :style="{ color: '#60666C'}">
              {{ boards[i*boardrowcnt + j -boardrowcnt-1].name }}
            </router-link>
          </div>
          <v-col align="center" v-if="i===2 && j ===boardrowcnt && $route.name!=='boardsearch'">
            <div>
            <i @click="makesearchmodal" class="fas fa-plus"/>
            <v-dialog max-width="600px" v-if="makesearch" v-model="makesearch">
              <v-card class="pa-3">
                  <div class="mb-4">
                  <div class="ma-5">
                  <router-link style="display:inline-block" :to="{name : 'BoardCreate'}" :style="{ color: '#60666C'}">
                    <v-btn class="mr-3" v-if="ssafyAuth === 2 || ssafyAuth === 1" @click="makesearchmodal">게시판 만들기</v-btn><br>
                  </router-link>
                  <router-link style="display:inline-block" :to="`/board/search/`">
                  <v-btn @click="makesearchmodal">전체 게시판 보기</v-btn><br>
                  </router-link>
                  </div>
                  <div id="notice-search-form">
                    <form @submit.prevent="search">
                    <v-text-field placeholder="게시판을 검색합니다" style="display:inline-block;width:50%" class="ml-5" v-model="searchTitle" />
                    <i @click="search" class="fas fa-search"></i>
                    </form>
              </div>
                  <v-card-actions>
                    <button class="close button" @click="makesearchmodal">닫기</button>
                  </v-card-actions>
                </div>
              </v-card>
            </v-dialog>
          </div>
          </v-col>
        </v-col>
      </v-row>
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
      makesearch : false,
      ssafyAuth: '',
      boards: [],
      searchTitle: '',
      boardrowcnt : 6,
    }
  },
  mounted() {
    this.ssafyAuth = this.$store.state.auth
    this.getBoards()
    this.onResize()
    window.addEventListener('resize', this.onResize, { passive: true })
  },
  beforeDestroy () {
    if (typeof window !== 'undefined') {
      window.removeEventListener('resize', this.onResize, { passive: true })
    }
  },
  methods: {
    onResize () {
      if (window.innerWidth < 600) {
        this.boardrowcnt = 3
      } else if (window.innerWidth < 900) {
        this.boardrowcnt = 4
      } else {
        this.boardrowcnt = 6
      }
    },
    getBoards() {
      axios.get('api/boardcategory')
        .then(response => {
          this.boards = response.data.slice(0,11)
        })
    },
    makesearchmodal() {
      this.makesearch = !this.makesearch
      this.searchTitle = ''
    },
    search(){
      this.$router.push(`/board/search/${this.searchTitle}`)
      this.makesearchmodal()
    }
  }
}
</script>