<template>
  <div style="margin: 0 5%;">
    <div class="mypage-title">
      <h1>MY BOARD</h1>
    </div>
    <MyBoardList/>
    <v-divider/>
    <div class="mypage-title">
      <h1>MY STUDY GROUP</h1>
    </div>
    <MyStudyGroupList/>
    <v-divider/>
    <div class="mypage-title">
      <h1>{{this.githubid}} 's Github</h1>
    </div>
    <GithubInfo :githubid="githubid"></GithubInfo>
  </div>
</template>

<script>
import axios from 'axios'
import MyStudyGroupList from '../components/studygroup/MyStudyGroupList'
import MyBoardList from '../components/board/MyPageBoard/MyBoardList'
import GithubInfo from '../components/github/GithubInfo'

export default {
    name : "MyPage",
    components : {
      MyStudyGroupList,
      MyBoardList,
      GithubInfo
    },
    data() {
      return {
        showpost: true,
        showportfolio : true, 
        mydata : Object,
        githubid: ''
      }
    },
    methods: {
      postshow() {
        this.showpost = !this.showpost
      },
      portfolioshow() {
        this.showportfolio = !this.showportfolio
      },
      getmyinfo() {
        // axios.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest'
        // axios.defaults.headers["Access-Control-Allow-Origin"] = "*"
        // axios.defaults.headers["Access-Control-Allow-Methods"] = "GET,HEAD,OPTIONS,POST,PUT"
        // axios.defaults.headers["Access-Control-Allow-Headers"] = "Authorization, Content-Type, If-Match, If-Modified-Since, If-None-Match, If-Unmodified-Since, X-GitHub-OTP, X-Requested-With"
        // axios.defaults.headers["Access-Control-Expose-Headers"] = "ETag, Link, X-GitHub-OTP, X-RateLimit-Limit, X-RateLimit-Remaining, X-RateLimit-Reset, X-OAuth-Scopes, X-Accepted-OAuth-Scopes, X-Poll-Interval"
        axios.get(`api/member/${this.$store.getters.user.memberid}`)
        .then(response=>{
          this.githubid = response.data.githubid
          // console.log(response)
          })
        .catch(error => {
          })
      }
    },
    mounted() {
      this.getmyinfo()
    }
}
</script>

<style>
  .mypage-title {
    font-family: 'M PLUS Rounded 1c', sans-serif;
    text-align: center;
    text-shadow: 3px 3px 5px lightgray;
    padding-top: 10px;
  }
</style>