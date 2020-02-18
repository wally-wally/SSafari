<template>
  <v-card class="contMobile">
    <div v-if="signFlag" class="formMobile responsiveCard">
      <h2>로그인</h2>
      <v-form ref="form" v-model="valid" lazy-validation @submit.prevent="login">
        <label class="label">
          <v-text-field v-model="credentials.email" :rules="emailRules" label="이메일" required></v-text-field>
        </label>
        <label class="label">
          <v-text-field v-model="credentials.password" :rules="minRules" type="password" label="비밀번호" required>
          </v-text-field>
        </label>
        <p class="forgot-pass" @click="changeSignInUp" style="text-decoration:underline; cursor:pointer">회원가입하기</p>
        <button type="submit" class="submit button">로그인</button>
      </v-form>
      <VFacebookLogin class="button" style="margin:0 auto; display:block;" :app-id="apiKey" @login="FBlogin">
      </VFacebookLogin>
      <v-card-actions>
        <button type="submit" class="close button" @click="close">닫기</button>
      </v-card-actions>
    </div>

    <div v-else class="formMobile responsiveCard">
        <h2>회원가입</h2>
        <v-form v-model="signupvalid" @submit.prevent="signup" class="login-form" style="display: inline;">
          <label class="label">
            <v-text-field v-model="signUpUser.email" :rules="emailRules" label="이메일" required class=""></v-text-field>
          </label>
          <p class='font-weight-bold' style='color:#D32F2F;' v-for="error in errormessage.username" :key="error">
            {{ error }}</p>
          <label class="label">
            <v-text-field v-model="signUpUser.password" :rules="minRules" type="password" label="비밀번호" required
              class=""></v-text-field>
          </label>
          <p class='font-weight-bold' style='color:#D32F2F;' v-for="error in errormessage.password" :key="error">
            {{ error }}</p>
          <label class="label">
            <v-text-field v-model="signUpUser.name" type="text" label="이름" required class=""></v-text-field>
          </label>
          <label class="label">
            <v-text-field v-model="signUpUser.username" type="text" label="닉네임" required class=""></v-text-field>
          </label>
          <p class="forgot-pass" @click="changeSignInUp" style="text-decoration:underline; cursor:pointer">로그인 하기</p>
          <button type="submit" class="submit button">회원가입</button>
        </v-form>
        <v-card-actions>
          <button type="submit" class="close button" @click="close">닫기</button>
        </v-card-actions>
    </div>
  </v-card>
</template>

<script>
  import axios from 'axios'
  import {
    mapGetters
  } from 'vuex'
  import router from '../../router'
  import VFacebookLogin from 'vue-facebook-login-component'

  export default {
    name: 'LoginSignupMobile',
    components: {
      VFacebookLogin
    },
    data() {
      return {
        signFlag: '',
        signInUp: '',
        credentials: {},
        minRules: [v => v.length >= 8 || 'Min 8 characters'],
        emailRules: [
          v => !!v || 'E-mail is required',
          v => /.+@.+\..+/.test(v) || 'E-mail must be valid',
        ],
        valid: true,
        signupvalid: true,
        loginvalid: true,
        loginfailcount: 0,
        loginDialog: false,
        signUpUser: {},
        errormessage: {
          username: [],
          password: []
        }
      }
    },
    mounted() {
      this.signFlag = true
      this.signInUp = 'Sign In'
    },
    methods: {
      FBlogin(response) {
        FB.login(response => {
          if (response.authResponse) {
            FB.api('/me', {
              fields: 'email, name'
            }, response => {
              var FBcredentials = {
                email: response.email,
                id: response.id,
                name: response.name
              }
              axios.post('api/member/facebook', FBcredentials)
                .then(response => {
                  var token = response.headers['access-token']
                  if (token) {
                    this.$session.start()
                    this.$session.set('token', token)
                    this.$store.dispatch('login', token)
                    this.loginDialog = false
                    this.credentials = {}
                    this.$emit('update');
                  }
                })
            })
          } else {
            console.log('User cancelled login or did not fully authorize.');
          }
        })
      },
      changeSignInUp() {
        this.signFlag = !this.signFlag
        if (this.signInUp === 'Sign In') {
          this.signInUp = 'Sign Up'
        } else {
          this.signInUp = 'Sign In'
        }
      },
      close() {
        this.credentials = {}
        this.signUpUser = {}
        this.$emit('update')
      },
      imgBtnClick() {
        document.querySelector('.contMobile').classList.toggle('s--signup');
      },
      login() {
        if (this.loginvalid) {
          axios.post('api/login', this.credentials)
            .then(response => {
              console.log(response)
              if (response.data.delflag === 1) {
                alert('탈퇴한 회원입니다.')
                return;
              }
              if (response.data.state == "이미 존재하는 email 입니다.") {
                alert(response.data.state)
                return
              }
              if (response.data.state == "이미 존재하는 username 입니다.") {
                alert(response.data.state)
                return
              }
              if (response.data.state == "이미 존재하는 email, username 입니다.") {
                alert(response.data.state)
                return
              }
              const token = response.headers['access-token']
              if (token) {
                this.$session.start()
                this.$session.set('token', token)
                // vuex actions 호출 -> dispatch
                this.$store.dispatch('login', token)
                this.loginDialog = false
                this.credentials = {}
                this.$emit('update');
              } else {
                this.loginfail()
              }
            })
            .catch(error => {
              this.loginfail()
            })
          this.credentials = {}
        }
      },
      loginfail() {
        this.loginfailcount++
      },
      signup() {
        axios.post('api/member', this.signUpUser)
          .then((response) => {
            console.log(response)
            this.errormessage = {
              username: [],
              password: []
            }
            if (response.status === 200) {
              this.$emit('update');
              this.signUpUser = {}
              document.querySelector('.contMobile').classList.remove('s--signup');
            }
            // router.push({name:'home'})
          })
          .catch(error => {
            console.log(error)
            this.errormessage.username = error.response.data.username
            this.errormessage.password = error.response.data.password
          })
        this.signUpUser = {}
      },
      clear() {
        this.credentials.username = ''
        this.credentials.password = ''
      },
    },
    computed: {
      ...mapGetters([
        'options',
        'user'
      ])
    }
  }
</script>

<style>
  .contMobile {
    overflow: hidden;
    position: relative;
    width: 500px;
    height: 550px;
    margin: auto;
    background: #fff;
  }

  .formMobile {
    position: relative;
    width: 450px;
    height: 100%;
    -webkit-transition: -webkit-transform 1.2s ease-in-out;
    transition: -webkit-transform 1.2s ease-in-out;
    transition: transform 1.2s ease-in-out;
    transition: transform 1.2s ease-in-out, -webkit-transform 1.2s ease-in-out;
    padding: 50px 30px 0;
  }

  .sub-cont {
    overflow: hidden;
    position: absolute;
    left: 640px;
    top: 0;
    width: 900px;
    height: 100%;
    padding-left: 260px;
    background: #fff;
    -webkit-transition: -webkit-transform 1.2s ease-in-out;
    transition: -webkit-transform 1.2s ease-in-out;
    transition: transform 1.2s ease-in-out;
    transition: transform 1.2s ease-in-out, -webkit-transform 1.2s ease-in-out;
  }
  .subLabel {
    display: block;
    width: 260px;
    margin: 0px auto 0;
    height: 50px;
    text-align: center;
  }
  .submit {
    margin-top: 20px;
    margin-bottom: 20px;
    background: #d4af7a;
    text-transform: uppercase;
  }
  .responsiveCard {
    display: inline-block;
    position: relative;
    width: 100%;
}
</style>