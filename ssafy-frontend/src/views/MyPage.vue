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
      <h1>{{this.$store.getters.user.githubid}} 's Github</h1>
    </div>
    <RepositoryList :githubid="this.$store.getters.user.githubid" />
  </div>
</template>

<script>
import axios from 'axios'
import MyStudyGroupList from '../components/studygroup/MyStudyGroupList'
import MyBoardList from '../components/board/MyPageBoard/MyBoardList'
import RepositoryList from '../components/repository/RepositoryList'
export default {
    name : "MyPage",
    components : {MyStudyGroupList,MyBoardList,RepositoryList},
    date() {
      return {
        showpost: true,
        showportfolio : true, 
        mydata : Object
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
        axios.get(`api/member/8`)
        .then(response=>{
          this.mydata = response.data
          console.log(this.mydata)
        }).catch(error => {
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