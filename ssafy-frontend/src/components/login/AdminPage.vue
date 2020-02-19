<template>
  <div class="admin-wrapper">
    <div class="ssafari-user-data">
      <h1 class="text-center mb-3" style="font-family: 'Gothic A1;"><strong>SSafari User Data</strong></h1>
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
      <h1 class="text-center mb-3" style="font-family: 'Gothic A1;"><strong>전체 회원 명단</strong></h1>
      <v-data-table
      :headers="headers"
      :items="memberList"
      :items-per-page="10"
      :single-expand="singleExpand"
      :expanded.sync="expanded"
      item-key="name"
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
            <div class="my-3">
              <p>작성 게시글 수 : {{ item.postcount }}</p>
              <p>작성 스터디모임 게시글 수 : {{ item.portfoliocount }}</p>
              <p>신청한 스터디모임 수 : {{ item.sugangcount }}</p>
            </div>
          </td>
        </template>
      </v-data-table>
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
      memberList: [],
      headers: [
        { text: '이름', align: 'start', value: 'name' },
        { text: '닉네임',  align: 'start', value: 'username' },
        { text: '이메일',  align: 'start', value: 'email' },
        { text: '지역',  align: 'start', value: 'locationid' },
        { text: '기수',  align: 'start', value: 'unit' },
        { text: '가입일',  align: 'start', value: 'signupdate' }
      ],
      infoCountList: [],
      singleExpand: false
    }
  },
  mounted() {
    this.getMemberList()
    this.getInfoCount()
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
          response.data.forEach(memberData => {
            this.memberList.push({
              name: memberData.name,
              username: memberData.username,
              email: memberData.email,
              locationid: memberData.locationid === 0 ? '미인증' : this.$store.state.locations[memberData.locationid],
              unit: memberData.unit === 0 ? '미인증' : `${memberData.unit}기`,
              signupdate: memberData.signupdate,
              postcount: memberData.postcount,
              portfoliocount: memberData.portfoliocount,
              sugangcount: memberData.sugangcount
            })
          })
        })
    },
    getInfoCount() {
      axios.get('api/infocount')
        .then(response => {
          this.infoCountList = response.data
        })
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