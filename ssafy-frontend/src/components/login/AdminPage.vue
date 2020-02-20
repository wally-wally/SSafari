<template>
  <div class="admin-wrapper">

    <nav class="admin-nav">
      <ul>
        <li @click="showAdminSection(0)"><span class="on" id="admin-user-menu">User Management</span></li>
        <li @click="showAdminSection(1)"><span class="" id="admin-board-menu">Board Management</span></li>
      </ul>
    </nav>
    <div v-if="selectAdminSection === 0">
      <div class="ssafari-user-data">
        <h1 class="text-center mb-3 mt-5" style="font-family: 'Gothic A1;"><strong>SSafari User Data</strong></h1>
        <div class="row user-count-wrapper">
          <div class="col-12 col-sm-6 col-md-3">
            <div class="user-count-box mx-1 pa-3">
              <p>전체 유저</p>
              <p>{{ totalUserCount }}</p>
            </div>
          </div>
          <div class="col-12 col-sm-6 col-md-3">
            <div class="user-count-box mx-1 pa-3">
              <p>SSAFY</p>
              <p>{{ infoCountList.ssafy }}</p>
            </div>
          </div>
          <div class="col-12 col-sm-6 col-md-3">
            <div class="user-count-box mx-1 pa-3">
              <p>일반 회원</p>
              <p>{{ infoCountList.user }}</p>
            </div>
          </div>
          <div class="col-12 col-sm-6 col-md-3">
            <div class="user-count-box mx-1 pa-3">
              <p>관리자</p>
              <p>{{ infoCountList.admin }}</p>
            </div>
          </div>
        </div>
      </div>

      <div class="ssafari-user-table">
        <h1 class="text-center mb-3 mt-3" style="font-family: 'Gothic A1;"><strong>전체 회원 명단</strong></h1>
        <v-text-field
          v-model="searchUsername"
          append-icon="search"
          label="회원 닉네임 검색"
          single-line
          hide-details
          class="mb-6"
          color="#f7b157"
        ></v-text-field>
        <v-data-table
        :headers="headers"
        :items="memberList"
        :items-per-page="10"
        :single-expand="singleExpand"
        :expanded.sync="expanded"
        :search="searchUsername"
        item-key="memberid"
        show-expand
        class="elevation-1">
          <template v-slot:top>
            <v-toolbar flat>
              <v-spacer></v-spacer>
              <v-switch v-model="singleExpand" label="Single expand" class="mt-2"></v-switch>
            </v-toolbar>
          </template>
          <template v-slot:expanded-item="{ headers, item }">
            <td :colspan="headers.length">
              <div class="my-3" style="float: left;">
                <img :src="item.imgUrl" alt="프로필 이미지" width=100 height=100 style="margin-right: 10px;">
              </div>
              <div class="my-6">
                <p>작성 게시글 수 : {{ item.postcount }}</p>
                <p>작성 스터디모임 게시글 수 : {{ item.portfoliocount }}</p>
                <p>신청한 스터디모임 수 : {{ item.sugangcount }}</p>
              </div>
            </td>
          </template>
          <template v-slot:item.auth="{ item }">
            <v-chip :color="getColor(item.auth)" dark>{{ item.auth }}</v-chip>
          </template>
        </v-data-table>
      </div>
    </div>

    <div v-else>
      <div>
					<div class="ssafy-request-board-title mt-5" style="font-size: 30px; font-family: 'Gothic A1'; text-align: center; font-weight: bold;">게시판 신청 관리</div>
					<hr class="title-headline">
							<v-data-table
								v-model="selectedBoardRequests"
								:headers="boardHeaders"
								:items="boardRequests"
								:items-per-page="10"
								show-select
								class="elevation-1"
								style="width: 90%; margin: 0 auto;"
							></v-data-table>
						<div class="d-flex justify-space-between pt-3" style="margin: 0 5%;">
							<span>👉선택된 항목에 대해 수행할 동작 선택</span>
							<div>
								<v-btn class="ml-3" color="blue" @click="approveBulletin(selectedBoardRequests)"><span style="color: white;">승인</span></v-btn> <!-- @click="approveBulletin(boardRequest.id)" -->
								<v-btn class="ml-3" color="red" @click="denyBulletin(selectedBoardRequests)"><span style="color: white;">거절</span></v-btn> <!--  @click="denyBulletin(boardRequest.id)" -->
							</div>
						</div>
					<!-- <div v-for="boardRequest in boardRequests" :key="boardRequest.id">
						<v-card class="mb-3">
							<v-card-title>
								<h3>게시판 이름: {{boardRequest.name}}</h3>
							</v-card-title>
							<v-card-title>
								<h3>신청자 : {{boardRequest.username}}</h3>
							</v-card-title>
							<v-card-title>
								<h3>게시판 설명 : {{boardRequest.explanation}}</h3>
							</v-card-title>
							<v-btn class="mr-3" color="blue" @click="approveBulletin(boardRequest.id)">승인</v-btn>
							<v-btn class="mr-3" color="red" @click="denyBulletin(boardRequest.id)">거절</v-btn>
						</v-card>
					</div> -->
				</div>
    </div>
  </div>
</template>

<script>
import '@/assets/css/AdminPage.css'
import axios from 'axios'
export default {
  name: 'AdminPage',
  data() {
    return {
      expanded: [],
      searchUsername: '',
      memberList: [],
      headers: [
        { text: '구분', align: 'start', value: 'auth' },
        { text: '이름', align: 'start', value: 'name' },
        { text: '닉네임',  align: 'start', value: 'username' },
        { text: '이메일',  align: 'start', value: 'email' },
        { text: '지역',  align: 'start', value: 'locationid' },
        { text: '기수',  align: 'start', value: 'unit' },
        { text: '가입일',  align: 'start', value: 'signupdate' }
      ],
      infoCountList: [],
      singleExpand: false,
      selectAdminSection: 0,
      boardRequests: [],
      boardHeaders: [
        {
          text: '게시판 이름',
          align: 'left',
          value: 'name'
        },
        { text: '게시판 설명', value: 'explanation' },
        { text: '신청자', value: 'username' }
      ],
      selectedBoardRequests: []
    }
  },
  mounted() {
    this.getMemberList()
    this.getInfoCount()
    this.getBoardRequest()
  },
  methods: {
    getMemberList() {
      axios.get('api/memberlist')
        .then(response => {
          const ssafyAuth = function(status) {
            if (status === 1) {
              return '관리자'
            } else if (status === 2) {
              return 'SSAFY'
            } else if (status === 3) {
              return '인증 대기'
            } else {
              return '일반'
            }
          }
          console.log(response.data)
          response.data.forEach(memberData => {
            this.memberList.push({
              memberid: memberData.memberid,
              name: memberData.name,
              username: memberData.username,
              email: memberData.email,
              locationid: memberData.locationid === 0 ? '미인증' : this.$store.state.locations[memberData.locationid],
              unit: memberData.unit === 0 ? '미인증' : `${memberData.unit}기`,
              signupdate: memberData.signupdate,
              postcount: memberData.postcount,
              portfoliocount: memberData.portfoliocount,
              sugangcount: memberData.sugangcount,
              auth: ssafyAuth(memberData.auth),
              imgUrl: memberData.img
            })
          })
        })
    },
    getInfoCount() {
      axios.get('api/infocount')
        .then(response => {
          this.infoCountList = response.data
        })
    },
    showAdminSection(selectTabIdx) {
      const adminUserMenuTab = document.querySelector('#admin-user-menu')
      const adminBoardMenuTab = document.querySelector('#admin-board-menu')
      if (selectTabIdx === 0) {
        this.selectAdminSection = selectTabIdx
        adminUserMenuTab.setAttribute('class', 'on')
        adminBoardMenuTab.setAttribute('class', '')
      } else {
        this.selectAdminSection = selectTabIdx
        adminUserMenuTab.setAttribute('class', '')
        adminBoardMenuTab.setAttribute('class', 'on')
      }
    },
    getBoardRequest() {
      axios.get('api/boardcategory/auth')
        .then(response => {
          this.boardRequests = response.data
        })
    },
    approveBulletin(reqs) {
      let confirmation = confirm('해당 게시판들을 승인하시겠습니까?')
      if (confirmation) {
        this.selectedBoardRequests.forEach(request => {
          let sendData = {
            id: request.id,
            flag: 1
          }
          axios.put('api/boardcategory/auth', sendData)
            .then(response => {
              this.getBoardRequest()
            })
        })
      }
    },
    denyBulletin(reqs) {
      let confirmation = confirm('해당 게시판을 거절하시겠습니까?')
      if (confirmation) {
        this.selectedBoardRequests.forEach(request => {
          let sendData = {
            id: request.id,
            flag: 0
          }
          axios.put('api/boardcategory/auth', sendData)
          .then(response => {
            this.getBoardRequest()
          })
        })
      }
    },
    getColor(authLevel) {
        if (authLevel === '관리자') {
          return 'red'
        } else if (authLevel === 'SSAFY') {
          return 'blue'
        } else if (authLevel === '인증 대기') {
          return 'gren'
        } else {
          return 'lightgray'
        }
    }
  },
  computed: {
    totalUserCount: function() {
      return this.infoCountList.admin + this.infoCountList.ssafy + this.infoCountList.user
    }
  }
}
</script>

<style>

</style>