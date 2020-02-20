<template>
  <div class="mypage">
    <div class="d-none d-md-block col-md-2 mypage-menu">

      <h2>My Page</h2>
      <div class="member-management">
        <div class="member-management-title">회원관리</div>
        <ul class="member-management-menu">
          <li @click="showMyPageSection(0)" class="user-edit"><i class="fas fa-user-edit"></i>회원정보 수정</li>
          <li @click="showMyPageSection(1)" class="change-password"><i class="fas fa-key"></i>비밀번호 변경</li>
          <li @click="showMyPageSection(2)" class="ssafy-auth" v-if="ssafyAuth === 1"><i class="fas fa-id-card-alt"></i>SSAFY Admin</li>
          <li @click="showMyPageSection(2)" class="ssafy-auth" v-else><i class="fas fa-id-card-alt"></i>SSAFY 인증</li>
          <li @click="showMyPageSection(3)" class="github-page"><i class="fab fa-github"></i>github 연동</li>
          <li @click="showMyPageSection(4)" class="dropout-page"><i class="fas fa-sign-out-alt"></i>회원탈퇴</li>
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

      <div class="mypage-description" @click.stop="showMyPageDialog = true">My Page 설명서</div>
      <div @click="showMyPageSection(8)" class="admin-page" v-if="ssafyAuth === 1" style="font-weight: bold;">Admin Page</div>
      <v-dialog v-model="showMyPageDialog" max-width="700">
        <v-card>
          <v-card-title>My Page Description</v-card-title>
          <v-divider></v-divider>
          <v-card-text>
            <div class="mypage-description-section pt-5">
              <ul>
                <li>회원정보 수정 : 닉네임, Github ID, 프로필 사진 업로드</li>
                <li>비밀번호 변경 : 새로운 비밀번호로 변경</li>
                <li>SSAFY 인증</li>
                <ul>
                  <li>비SSAFY인 : SSAFY인 인증 페이지가 보여지고 신청하면 관리자의 승인을 기다리라는 페이지가 보여짐</li>
                  <li>SSAFY인 : SSAFY인으로 승인 됐다는 문구가 적힌 페이지가 보여짐</li>
                  <li>관리자 : SSAFY인 인증을 신청한 내역들이 나옴</li>
                </ul>
                <li>github 연동 : '회원정보 수정'에서 Github ID를 등록한 후에 이용 가능, github 기본 정보와 repository 관련 내용을 볼 수 있음</li>
                <li>회원탈퇴 : 클릭 시 정말로 회원탈퇴를 진행하는 것이 맞는지 확인하는 페이지가 나옴</li>
                <li>게시글 관리 : 게시판, 스터디 모임에 내가 작성한 게시글을 볼 수 있고 'Likes'는 내가 좋아요를 누른 게시글들을 볼 수 있음</li>
              </ul>
            </div>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="#f7b157" text @click="showMyPageDialog = false">
              CLOSE
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>

    <div class="d-block d-md-none mypage-menu-small">
      <div class="d-flex justify-space-between">
        <div class="mypage-menu-title d-inline">Select Menu</div>
        <div class="mypage-menu-select d-inline">
          <v-select
              class="d-inline"
              :items="smallMenu"
              item-text="name"
              item-value="val"
              label="Choose Menu"
              color="#f7b157"
              hide-details
              v-model="selectMenuItem"
              >
          </v-select>
        </div>
      </div>
    </div>

    <div class="d-block col-md-10 mypage-section">
      <div v-if="selectMenuItem === -1">
        <div class="ma-8">
        <p class="mypage-profile-img" style="text-align: center;">
					<img :src="this.$store.state.img" alt="mypage-profile-img" id="mypage-profile-image" style="margin-top: 80px;margin-left: auto; margin-right: auto; display: block; width: 180px; height: 180px;border-radius: 50%;">
				</p>
        <table class="profile-table" style="margin: 0 auto; padding-top: 35px;">
          <tr>
            <td class="sort" style="padding: 5px 30px; border-right: 1.3px solid black; font-family: 'Noto Sans KR', sans-serif; font-size: 18px; font-weight: 600;">지역</td>
            <td class="region-value" style="padding: 5px 30px; font-family: 'Nanum Gothic', sans-serif; font-size: 18px; font-weight: 600;">{{this.$store.state.locations[this.$store.state.locationid]}}</td>
          </tr>
          <tr>
            <td class="py-2"></td>
            <td class="py-2"></td>
          </tr>
          <tr>
            <td class="sort" style="padding: 5px 30px; border-right: 1.3px solid black; font-family: 'Noto Sans KR', sans-serif; font-size: 18px; font-weight: 600;">기수</td>
            <td v-if="this.$store.state.unit > 0" class="seires-value" style="padding: 5px 30px; font-family: 'Nanum Gothic', sans-serif; font-size: 18px; font-weight: 600;">{{this.$store.state.unit}}기</td>
            <td v-else class="seires-value" style="padding: 5px 30px; font-family: 'Nanum Gothic', sans-serif; font-size: 18px; font-weight: 600;">싸피 미인증</td>
          </tr>
        </table>
        </div>
        <!-- <v-card class="mx-auto" max-width="434" tile>
          <v-img height="100%" src="https://cdn.vuetifyjs.com/images/cards/server-room.jpg">
            <v-row align="end" class="fill-height">
              <v-col align-self="start" class="pa-0" cols="12">
                <v-avatar class="profile" color="grey" size="164" tile>
                  <v-img :src="this.$store.state.img"></v-img>
                </v-avatar>
              </v-col>
              <v-col class="py-0">
                <v-list-item color="rgba(0, 0, 0, .4)" dark>
                  <v-list-item-content>
                    <v-list-item-title class="title">{{currentUsername}}</v-list-item-title>
                    <v-list-item-subtitle>지역: {{this.$store.state.locations[this.$store.state.locationid]}}
                    </v-list-item-subtitle>
                    <v-list-item-subtitle>기수: {{this.$store.state.unit}}</v-list-item-subtitle>
                  </v-list-item-content>
                </v-list-item>
              </v-col>
            </v-row>
          </v-img>
        </v-card> -->
      </div>
      <MemberModify v-if="smallMenu.indexOf(selectMenuItem) === 0"/>
      <PasswordModify v-if="smallMenu.indexOf(selectMenuItem) === 1"/>
      <MakeSsafyAuth v-if="smallMenu.indexOf(selectMenuItem) === 2 && this.ssafyAuth === 1"/>
      <SsafyAuth v-if="smallMenu.indexOf(selectMenuItem) === 2"/>
      <GithubInfo v-if="smallMenu.indexOf(selectMenuItem) === 3" :githubid="githubid"/>
      <DropOut v-if="smallMenu.indexOf(selectMenuItem) === 4"/>
      <MyBoardList v-if="smallMenu.indexOf(selectMenuItem) === 5"/>
      <MyStudyGroup v-if="smallMenu.indexOf(selectMenuItem) === 6"/>
      <MyLikeList v-if="smallMenu.indexOf(selectMenuItem) === 7"/>
      <AdminPage v-if="smallMenu.indexOf(selectMenuItem) === 8"/>
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
import SsafyAuth from '../components/login/SsafyAuth'
import MyStudyGroup from '../components/studygroup/MyStudyGroup'
import MyBoardList from '../components/board/MyPageBoard/MyBoardList'
import MyLikeList from '../components/board/MyPageBoard/MyLikeList'
import GithubInfo from '../components/github/GithubInfo'
import AdminPage from '../components/login/AdminPage'
import DropOut from '../components/login/DropOut'
import '@/assets/css/MyPage.css'

export default {
    name : "MyPage",
    components : {
      MemberModify,
      PasswordModify,
      MakeSsafyAuth,
      SsafyAuth,
      MyStudyGroup,
      MyBoardList,
      MyLikeList,
      GithubInfo,
      AdminPage,
      DropOut
    },
    data() {
      return {
        showpost: true,
        showportfolio : true, 
        mydata : Object,
        githubid: this.$store.getters.user.githubid,
        token: '',
        social: '',
        ssafyAuth: this.$store.state.auth,
        myPageStatus: null,
        smallMenu: [],
        selectMenuItem: -1,
        showMyPageDialog: false,
        showAdminPage: 0
      }
    },
    methods: {
      // memberDropOut() {
      //   var confirmation = confirm("회원 탈퇴 하시겠습니까?");
      //   if(confirmation){
      //     var data = {
      //       memberid : this.$store.state.memberid,
      //     }
      //     var token = this.$store.state.token
      //     axios.delete(`api/member/${this.$store.state.memberid}`, {headers: {'access-token' : token}})
      //       .then(response => {
      //         console.log(response)
      //       })
      //     this.$store.dispatch('dropout')
      //     this.$router.push('/')
      //   }
      // },
      postshow() {
        this.showpost = !this.showpost
      },
      portfolioshow() {
        this.showportfolio = !this.showportfolio
      },
      showMyPageSection(status) {
        this.myPageStatus = status
        this.selectMenuItem = this.smallMenu[status]
        const userEditMenu = document.querySelector('.user-edit')
        const changePasswordMenu = document.querySelector('.change-password')
        const ssafyAuthMenu = document.querySelector('.ssafy-auth')
        const githubPageMenu = document.querySelector('.github-page')
        const dropoutPageMenu = document.querySelector('.dropout-page')
        const myBoardMenu = document.querySelector('.myboard')
        const myStudyGroupMenu = document.querySelector('.mystudygroup')
        const myLikesMenu = document.querySelector('.mylikes')
        const adminPageMenu = document.querySelector('.admin-page')
        let menus = [
          userEditMenu, changePasswordMenu, ssafyAuthMenu, githubPageMenu, dropoutPageMenu,
          myBoardMenu, myStudyGroupMenu, myLikesMenu
        ]
        if (this.$store.state.auth === 1) {
          menus.push(adminPageMenu)
        }
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
        // if (this.myPageStatus === 2) {
        //   if (this.ssafyAuth === 2) {
        //     alert('오 넌 싸피 아이디가 다 있구나!')
        //   } else if (this.ssafyAuth === 3) {
        //     alert('SSAFY 인증 대기 상태입니다.')
        //   }
        // }
      }
    },
    mounted() {
      if (this.$store.state.token == null){
        this.$router.push('/')
      }
      // this.social = this.$store.state.social
      this.ssafyAuth = this.$store.state.auth
      if (this.ssafyAuth === 1) {
        this.smallMenu = ['회원정보 수정', '비밀번호 변경', 'SSAFY Admin', 'github 연동', '회원탈퇴', '게시판', '스터디 모임', 'Likes', 'Admin Page']
      } else {
        this.smallMenu = ['회원정보 수정', '비밀번호 변경', 'SSAFY 인증', 'github 연동', '회원탈퇴', '게시판', '스터디 모임', 'Likes']
      }
    },
    watch: {
      selectMenuItem: {
        handler() {
          const menuIdx = this.smallMenu.indexOf(this.selectMenuItem)
          console.log(menuIdx)
          this.showMyPageSection(menuIdx)
        }
      }
    }
}
</script>

<style>
  /* .mypage-title {
    font-family: 'M PLUS Rounded 1c', sans-serif;
    text-align: center;
    text-shadow: 3px 3px 5px lightgray;
    padding-top: 10px;
  } */
</style>