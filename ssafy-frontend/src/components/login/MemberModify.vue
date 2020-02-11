<template>
  <div class="">
    <h2>회원정보 수정</h2>
    <v-form ref="form" lazy-validation @submit.prevent="">
      <label class="label">
        <v-text-field v-model="nickname" label="닉네임"></v-text-field>
      </label>
      <label class="label">
        <v-text-field v-model="githubId" label="Github ID"></v-text-field>
      </label>
      <button type="submit" class="submit button" @click="memberModify">회원 정보 수정</button>
    </v-form>
    <v-card-actions>
      <button type="submit" class="close button" @click="back">뒤로 가기</button>
    </v-card-actions>
  </div>
</template>

<script>
  import axios from 'axios'

  export default {
    name: 'MemberModify',
    data() {
      return {
        nickname: '',
        githubId: ''
      }
    },
    methods: {
      back() {
        this.$router.push('/mypage')
      },
      memberModify() {
        var data = {
          username: this.nickname,
          githubid: this.githubId,
          memberid: this.$store.state.memberid
        }
        axios.put('api/member', data)
          .then(response => {
            console.log(response)
            alert('성공적으로 회원 정보를 수정했습니다.')
            this.$store.state.username = this.nickname
            this.$router.push('/')
          })
      }
    }
  }
</script>

<style>

</style>