<template>
  <div>
    <p class="text-center" style="font-size: 150px;"><i class="fas fa-sign-out-alt"></i></p>
    <p class="text-center pb-6" style="font-size: 28px; font-family: 'Noto Sans KR', sans-serif; font-weight: 600;">회원탈퇴를 정말로 하시겠습니까?</p>
    <p class="text-center" style="font-size: 15px; font-family: 'Noto Sans KR', sans-serif;">하단 '회원탈퇴' 버튼을 누르면 회원탈퇴가 진행되어 계정이 삭제됩니다.</p>
    <button type="submit" class="submit button" @click="memberDropOut" style="background-color: crimson;">회원 탈퇴</button>
  </div>
</template>

<script>
export default {
  name: 'DropOut',
  data() {
    return {

    }
  },
  methods: {
    memberDropOut() {
      var confirmation = confirm(`회원 탈퇴 하시겠습니까?
(확인을 누르면 계정이 바로 삭제됩니다.)`);
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
  }
}
</script>

<style>

</style>