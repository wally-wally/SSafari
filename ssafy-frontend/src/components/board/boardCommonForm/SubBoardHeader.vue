<template>
  <div class="subheader-top">
    <div class="notice-kind">
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
    </div>
  </div>
</template>

<script>
  import axios from 'axios'

  export default {
    name: 'SubBoardHeader',
    data() {
      return {
        ssafyAuth: '',
        boards: [],
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
            console.log(response.data)
            this.boards = response.data
          })
      }
    }
  }
</script>

<style>
  a {
    text-decoration: none;
  }

  .subheader-top {
    height: 80px;
    background-color: lightgray;
    margin-bottom: 15px;
  }

  .notice-kind {
    display: flex;
    line-height: 80px;
    justify-content: space-evenly;
  }

  .notice-kind>span {
    font-size: 1.2em;
    font-family: 'Nanum Gothic', sans-serif;
  }

  .notice-kind>span:first-child {
    padding-right: 20px;
  }

  .notice-kind>span:last-child {
    padding-left: 20px;
  }

  .notice-kind>span:nth-child(2),
  .notice-kind>span:nth-child(3),
  .notice-kind>span:nth-child(4) {
    padding: 0 20px;
  }

  @media (min-width: 540px) {
    .free-notice::after {
      content: ' 자유게시판';
    }

    .love-notice::after {
      content: ' 사랑의 오작교';
    }

    .job-notice::after {
      content: ' 취업 게시판';
    }

    .code-notice::after {
      content: ' 코드리뷰';
    }

    .restaurant-notice::after {
      content: ' 주변맛집';
    }

    @media (min-width: 680px) and (max-width: 880px) {
      .notice-kind>span {
        padding: 0 10px;
        font-size: 1em;
      }

      .notice-kind>span:first-child {
        padding-right: 15px;
      }

      .notice-kind>span:last-child {
        padding-left: 15px;
      }

      .notice-kind>span:nth-child(2),
      .notice-kind>span:nth-child(3),
      .notice-kind>span:nth-child(4) {
        padding: 0 15px;
      }
    }

    @media (min-width: 540px) and (max-width: 679px) {
      .notice-kind>span {
        padding: 0 5px;
        font-size: 0.8em;
      }

      .notice-kind>span:first-child {
        padding-right: 10px;
      }

      .notice-kind>span:last-child {
        padding-left: 10px;
      }

      .notice-kind>span:nth-child(2),
      .notice-kind>span:nth-child(3),
      .notice-kind>span:nth-child(4) {
        padding: 0 10px;
      }
    }
  }
</style>