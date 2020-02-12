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
      <v-file-input :rules="rules" v-model="imgFile" label="File input" id="file" outlined dense
        accept="image/png, image/jpeg, image/bmp"></v-file-input>
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
        githubId: '',
        imgFile: null,
        rules: [
          function (value) {
            if(value) {
              var extension = value.name.toString().split('.')[1]
              extension = extension.toLowerCase()
              if (!(extension === 'jpg' || extension === 'png' || extension === 'bmp')) {
                alert("Please upload image file.(available jpg, png, bmp flie)")
                document.querySelector('#file').value = ''
                const filename = document.querySelector('.v-file-input__text')
                filename.innerText = ''
              }
              return true
            }else{
              return ''
            }
          }
        ]
      }
    },
    methods: {
      back() {
        this.$router.push('/mypage')
      },
      memberModify() {
        if (this.imgFile === null) {
          var data = {
            username: this.nickname,
            githubid: this.githubId,
            memberid: this.$store.state.memberid
          }
          axios.put('api/member', data)
            .then(response => {
              console.log(response)
              if (response.status === 200) {
                alert('성공적으로 회원 정보를 수정했습니다.')
                if (this.nickname !== null) {
                  this.$store.state.username = this.nickname
                }
                // const token = response.headers['access-token']
                // if (token) {
                //   this.$session.start()
                //   this.$session.set('token', token)
                //   // vuex actions 호출 -> dispatch
                //   this.$store.dispatch('login', token)
                //   this.loginDialog = false
                //   this.credentials = {}
                //   this.$emit('update');
                // }
                this.$router.push('/')
              }
            })
        } else {
          const formData = new FormData()
          formData.append('image', this.imgFile)
          axios.post('https://api.imgur.com/3/image', formData, {
              headers: {
                Authorization: 'Client-ID 347364b9fa38df3'
              }
            })
            .then(response => {
              var imgLink = response.data.data.link
              var data = {
                username: this.nickname,
                githubid: this.githubId,
                memberid: this.$store.state.memberid,
                img: imgLink,
              }
              console.log(999234234123123123123123, imgLink)
              // var token = this.$store.state.token
              // axios.put('api/member', data)
              //   .then(response => {
              //     if (response.status === 200) {
              //       alert('성공적으로 회원 정보를 수정했습니다.')
              //       if (this.nickname !== null) {
              //         this.$store.state.username = this.nickname
              //       }
              //       this.$router.push('/')
              //     }
              //   })
            })
            .catch(error => {
              console.log(error)
            })
        }
      }
    }
  }
</script>

<style>

</style>