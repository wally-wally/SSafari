<template>
  <div class="admin-wrapper">
    <div>

    </div>
    <hr>
    <h1>전체 회원 명단</h1>
    <v-data-table
    v-model="selectedMember"
    :headers="headers"
    :items="memberList"
    :items-per-page="10"
    class="elevation-1">
  </v-data-table>
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
        {
          text: '이름',
          align: 'left',
          value: 'name'
        },
        { text: '닉네임', value: 'username' },
        { text: '이메일', value: 'email' },
        { text: '지역', value: 'locationid' },
        { text: '기수', value: 'unit' },
        { text: '가입일', value: 'signupdate' },
        { text: '게시글수', value: 'postcount' },
        { text: '스터디모임수', value: 'portfoliocount' },
        { text: '신청스터디모임수', value: 'sugangcount' },
      ]
    }
  },
  mounted() {
    this.getMemberList()
  },
  methods: {
    getMemberList() {
      axios.get('api/memberlist')
        .then(response => {
          // this.memberList = response.data
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
    }
  },
  // watch: {
  //   showAdminPage() {
  //     this.getMemberList()
  //   }
  // }
}
</script>

<style>

</style>