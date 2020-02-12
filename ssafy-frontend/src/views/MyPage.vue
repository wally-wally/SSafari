<template>
  <div style="margin: 0 5%;">
    <h1 v-if="ssafyAuth === 2">오 넌 싸피 아이디가 다 있구나!</h1>
    <v-btn class="orange mx-1 my-3" :to="{name : 'MemberModify'}">회원정보 수정</v-btn>
    <v-btn v-if="!social" class="green mx-1 my-3" :to="{name : 'PasswordModify'}">비밀번호 변경</v-btn>
    <v-btn v-if="ssafyAuth === 4" class="blue mx-1 my-3" :to="{name : 'SsafyAuth'}">싸피 인증 받기</v-btn>
    <v-btn v-if="ssafyAuth === 1" class="yellow mx-1 my-3" :to="{name : 'MakeSsafyAuth'}">싸피 인증 관리</v-btn>
    <v-btn class="red mx-1 my-3" @click="memberDropOut">회원탈퇴</v-btn>
    <h1 v-if="ssafyAuth === 3" class="red">현재 싸피 인증 대기 중입니다.</h1>
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
      <h1>{{this.$store.getters.user.githubid}} 's Github</h1>
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
        githubid: this.$store.getters.user.githubid,
        token: '',
        social: '',
        ssafyAuth: '',
      }
    },
    methods: {
      memberDropOut() {
        var confirmation = confirm("회원 탈퇴 하시겠습니까?");
        if(confirmation){
          var data = {
            memberid : this.$store.state.memberid,
          }
          var token = this.$store.state.token
          axios.delete(`api/member/${this.$store.state.memberid}`, {headers: {'access-token' : token}})
            .then(response => {
              console.log(response)
            })
          this.$store.dispatch('dropout')
          this.$router.push('/')
        }
      },
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
          // this.githubid = response.data.githubid
          // console.log(response)
          })
        .catch(error => {
          })
      }
    },
    mounted() {
      if (this.$store.state.token == null){
        this.$router.push('/')
      }
      this.getmyinfo()
      // this.social = this.$store.state.social
      this.ssafyAuth = this.$store.state.auth
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