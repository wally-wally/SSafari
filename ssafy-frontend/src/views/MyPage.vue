<template>
  <v-container>
    <div class="mypage-title">
      <h1>MY POST</h1>
    </div>
    <MyPostList/>
    <v-divider/>
    <div class="mypage-title">
      <h1>MY PORTFOLIO</h1>
    </div>
    <MyPortfolioList/>
  </v-container>
</template>

<script>
import axios from 'axios'
import MyPortfolioList from '../components/portfolio/MyPortfolioList'
import MyPostList from '../components/post/MyPostList'

export default {
    name : "MyPage",
    components : {MyPortfolioList,MyPostList},
    date() {
      return {
        showpost: true,
        showportfolio : true, 
        mydata: ''
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
        axios.get(`http://192.168.31.110:8197/ssafyvue/api/member/${this.$session.get('data')['memberid']}`)
        .then(response=>{
          console.log(response.data)
          this.mydata = response.data
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