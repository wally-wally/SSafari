<template>
  <div class="mypage d-flex">
    <div class="d-none d-md-block col-md-2 mypage-menu">

      <h2>My Page</h2>
      <div class="member-management">
        <div class="member-management-title">회원관리</div>
        <ul class="member-management-menu">
          <li @click="showMyPageSection(0)" class="user-edit"><i class="fas fa-user-edit"></i>회원정보 수정</li>
          <li @click="showMyPageSection(1)" class="change-password"><i class="fas fa-key"></i>비밀번호 변경</li>
          <li @click="showMyPageSection(2)" class="ssafy-auth"><i class="fas fa-id-card-alt"></i>SSAFY 인증</li>
          <li @click="showMyPageSection(3)" class="github-page"><i class="fab fa-github"></i>github 연동</li>
          <li @click="showMyPageSection(4)" class="gitlab-page"><i class="fab fa-gitlab"></i>gitlab 연동</li>
          <li @click="memberDropOut"><i class="fas fa-sign-out-alt"></i>회원탈퇴</li>
        </ul>
      </div>

      <div class="separate-section"></div>

      <div class="mypost">
        <div class="mypost-title">게시글 관리</div>
        <ul class="mypost-menu">
          <li @click="showMyPageSection(5)" class="myboard"><i class="fas fa-chalkboard"></i>게시판</li>
          <li @click="showMyPageSection(6)" class="mystudygroup"><i class="fas fa-users"></i>스터디 모임</li>
          <li @click="showMyPageSection(7)" class="mylikes"><i class="fas fa-heart"></i>Likes</li>
        </ul>
      </div>

      <div class="mypage-description">My Page 설명서</div>
    </div>
    <div class="col-12 col-md-10 mypage-section">
      <MemberModify v-if="myPageStatus === 0"/>
      <PasswordModify v-if="myPageStatus === 1"/>
      <MakeSsafyAuth v-if="myPageStatus === 2"/>
      <GithubInfo v-if="myPageStatus === 3" :githubid="githubid"/>
      <MyBoardList v-if="myPageStatus === 5"/>
      <MyStudyGroupList v-if="myPageStatus === 6"/>
      <MyLikeList v-if="myPageStatus === 7"/>
    </div>
  </div>
    <!-- <h1 v-if="ssafyAuth === 2">오 넌 싸피 아이디가 다 있구나!</h1>
    <v-btn class="orange mx-1 my-3" :to="{name : 'MemberModify'}">회원정보 수정</v-btn>
    <v-btn v-if="!social" class="green mx-1 my-3" :to="{name : 'PasswordModify'}">비밀번호 변경</v-btn>
    <v-btn v-if="ssafyAuth === 4" class="blue mx-1 my-3" :to="{name : 'SsafyAuth'}">싸피 인증 받기</v-btn>
    <v-btn v-if="ssafyAuth === 1" class="yellow mx-1 my-3" :to="{name : 'MakeSsafyAuth'}">싸피 인증 관리</v-btn>
    <v-btn class="red mx-1 my-3" @click="memberDropOut">회원탈퇴</v-btn>
    <h1 v-if="ssafyAuth === 3" class="red">현재 싸피 인증 대기 중입니다.</h1>
    <div class="mypage-title">
      <h1>MY LIKES</h1>
    </div>
    <MyLikeList/>
    <v-divider/>
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
    <GithubInfo :githubid="githubid"></GithubInfo> -->
</template>

<script>
import axios from 'axios'
import MemberModify from '../components/login/MemberModify'
import PasswordModify from '../components/login/PasswordModify'
import MakeSsafyAuth from '../components/login/MakeSsafyAuth'
import MyStudyGroupList from '../components/studygroup/MyStudyGroupList'
import MyBoardList from '../components/board/MyPageBoard/MyBoardList'
import MyLikeList from '../components/board/MyPageBoard/MyLikeList.vue'
import GithubInfo from '../components/github/GithubInfo'

export default {
    name : "MyPage",
    components : {
      MemberModify,
      PasswordModify,
      MakeSsafyAuth,
      MyStudyGroupList,
      MyBoardList,
      MyLikeList,
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
        myPageStatus: 0
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
      showMyPageSection(status) {
        this.myPageStatus = status
        const userEditMenu = document.querySelector('.user-edit')
        const changePasswordMenu = document.querySelector('.change-password')
        const ssafyAuthMenu = document.querySelector('.ssafy-auth')
        const githubPageMenu = document.querySelector('.github-page')
        const gitlabPageMenu = document.querySelector('.gitlab-page')
        const myBoardMenu = document.querySelector('.myboard')
        const myStudyGroupMenu = document.querySelector('.mystudygroup')
        const myLikesMenu = document.querySelector('.mylikes')
        let menus = [
          userEditMenu, changePasswordMenu, ssafyAuthMenu, githubPageMenu,
          gitlabPageMenu, myBoardMenu, myStudyGroupMenu, myLikesMenu
        ]
        menus.forEach(menu => {
          menu.style.color = 'black'
          menu.style.fontWeight = '100'
        })

        menus[status].style.color = '#DD660A'
        menus[status].style.fontWeight = 'bold'

        // if (status === 0) {
        //   userEditMenu.style.color = '#DD660A'
        //   userEditMenu.style.fontWeight = 'bold'
        // }
        if (status === 3 && this.ssafyAuth === 2) {
          alert('오 넌 싸피 아이디가 다 있구나!')
        }
      }
    },
    mounted() {
      if (this.$store.state.token == null){
        this.$router.push('/')
      }
      // this.social = this.$store.state.social
      this.ssafyAuth = this.$store.state.auth
    }
}
</script>

<style>
  .mypage {
    margin: 0;
    padding: 0;
    height: 100%;
    box-sizing: border-box;
    font-family: 'Sarabun', sans-serif;
  }

  .mypage .mypage-menu {
    background-color: #F9F3ED;
    height: 100%;
  }

  .mypage-menu h2 {
    font-family: 'Gothic A1', sans-serif;
    font-weight: bold;
    color: #FF8008;
    text-transform: uppercase;
    text-align: center;
    margin-bottom: 25px;
  }

  .member-management-title,
  .mypost-title {
    font-size: 18px;
    font-weight: bold;
    padding-left: 14px;
    padding-bottom: 5px;
    font-family: 'Noto Sans KR', sans-serif;
  }

  ul[class='member-management-menu'],
  ul[class='mypost-menu'] {
    padding-left: 5px;
  }

  ul[class='member-management-menu'] > li,
  ul[class='mypost-menu'] > li {
    font-family: 'Nanum Gothic', sans-serif;
    list-style: none;
    text-decoration: none;
    padding: 10px;
    border-bottom: 1px solid rgba(0, 0, 0, 0.05);
    border-top: 1px solid rgba(225, 225, 225, 0.05);
  }

  ul[class='member-management-menu'] > li > .fas,
  ul[class='member-management-menu'] > li > .fab,
  ul[class='mypost-menu'] > li > .fas {
    width: 30px;
  }

  .separate-section {
    height: 2px;
    margin: 20px 0;
  }

  ul[class='member-management-menu'] > li:hover,
  ul[class='mypost-menu'] > li:hover {
    font-weight: 800;
    background: #FFF7E2;
    cursor: pointer;
  }

  .mypage-menu > .mypage-description {
    font-size: 18px;
    font-weight: bold;
    font-family: 'Nanum Gothic', sans-serif;
    padding-left: 14px;
    margin-top: 30px;
  }

  .mypage-menu > .mypage-description:hover {
    background: #FFF7E2;
    cursor: pointer;
  }

  /* .mypage-title {
    font-family: 'M PLUS Rounded 1c', sans-serif;
    text-align: center;
    text-shadow: 3px 3px 5px lightgray;
    padding-top: 10px;
  } */
</style>