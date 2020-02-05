<template>
  <v-container>
    <div>
    </div>
    <div class="mypage-title">
      <h1>MY POST</h1>
    </div>
    <MyPostList/>
    <v-divider/>
    <div class="mypage-title">
      <h1>MY PORTFOLIO</h1>
    </div>
    <MyPortfolioList/>
    <v-divider/>
    <div class="mypage-title">
      <h1>{{this.$store.getters.user.githubid}} 's Github</h1>
    </div>
    <RepositoryList :githubid="this.$store.getters.user.githubid" />
  </v-container>
</template>

<script>
import axios from 'axios'
import MyPortfolioList from '../components/portfolio/MyPortfolioList'
import MyPostList from '../components/post/MyPostList'
import RepositoryList from '../components/RepositoryList'
export default {
    name : "MyPage",
    components : {MyPortfolioList,MyPostList,RepositoryList},
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