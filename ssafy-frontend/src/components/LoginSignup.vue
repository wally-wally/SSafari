<template>
    <v-card class="cont">
      <div class="form sign-in">
        <h2>Welcome to iTeacher!</h2>
        <v-form ref="form" v-model="valid" lazy-validation @submit.prevent="login">
          <label class="label">
            <v-text-field v-model="credentials.email" :rules="emailRules" label="E-mail" required></v-text-field>
          </label>
          <label class="label">
            <v-text-field v-model="credentials.password" type="password" label="Password" required></v-text-field>
          </label>
          <p class="forgot-pass">Forgot password?</p>
          <button type="submit" class="submit button">Sign In</button>
        </v-form>
        <button type="button" class="fb-btn button"><span>login as</span> Facebook</button>
        <v-card-actions>
          <button type="submit" class="close button" @click="close">close</button>
        </v-card-actions>
      </div>

      <div class="sub-cont">
        <div class="img">
          <div class="img__text m--up">
            <h2>New here?</h2>
            <p>Sign up and discover our great lectures provided by special teachers!</p>
          </div>
          <div class="img__text m--in">
            <h2>One of us?</h2>
            <p>If you already has an account, just sign in. We've missed you!</p>
          </div>
          <div class="img__btn" @click="imgBtnClick">
            <span class="m--up">Sign Up</span>
            <span class="m--in">Sign In</span>
          </div>
        </div>
        <div class="form sign-up">
          <h2>Time to learn! Join iTeacher</h2>
          <form @submit.prevent="signup" class="login-form" style="display: inline;">
            <label class="subLabel">
              <v-text-field v-model="signUpUser.email" label="E-mail" required class="my-0"></v-text-field>
            </label>
            <p class='font-weight-bold' style='color:#D32F2F;' v-for="error in errormessage.username" :key="error">{{ error }}</p>
            <label class="subLabel">
              <v-text-field v-model="signUpUser.password" type="password" label="Password" required class="my-0"></v-text-field>
            </label>
            <p class='font-weight-bold' style='color:#D32F2F;' v-for="error in errormessage.password" :key="error">{{ error }}</p>
            <label class="subLabel">
              <v-text-field v-model="signUpUser.name" type="text" label="Name" required class="my-0"></v-text-field>
            </label>
            <label class="subLabel">
              <v-text-field v-model="signUpUser.username" type="text" label="Nickname" required class="my-0"></v-text-field>
            </label>
            <button type="submit" class="submit button">Sign Up</button>
          </form>
        <button type="button" class="fb-btn button">Join with <span>facebook</span></button>
        <v-card-actions>
          <button type="submit" class="close button" @click="close">close</button>
        </v-card-actions>
      </div>
    </div>
  </v-card>
</template>

<script>
import axios from 'axios'
import {mapGetters} from 'vuex'
import router from '../router'
export default {
    name: 'LoginSignup',
    data() {
        return {
            credentials: {},
            emailRules: [
              v => !!v || 'E-mail is required',
              v => /.+@.+\..+/.test(v) || 'E-mail must be valid',
            ],
            valid: true,
            loginfailcount : 0,
            loginDialog: false,
            signUpUser: {},
            errormessage : {username: [], password: []}
        }
    },
    methods: {
        close() {
          this.credentials = {}
          this.signUpUser = {}
          this.$emit('update')
        },
        imgBtnClick() {
          document.querySelector('.cont').classList.toggle('s--signup');
        },
        login() {
          axios.post('http://192.168.31.110:8197/ssafyvue/api/login',this.credentials)
            .then(response => {
            const data = response.data
            if (data) {
            this.$session.start()
            this.$session.set('data', data)

            // vuex actions 호출 -> dispatch
            this.$store.commit('loginSuccess', response.data)
            this.loginDialog = false
            this.credentials = {}

            this.$emit('update');
          }
          else{
            this.loginfail()
          }
        })
        .catch(error => {
          console.log(error)
          this.loginfail()
        })
      this.credentials = []
    },
        loginfail() {
      this.loginfailcount ++
    },
        signup() {
            axios.post('http://192.168.31.110:8197/ssafyvue/api/member', this.signUpUser)
                .then((response) => {
                    console.log(response)
                    this.errormessage = {username: [], password: []}
                    if(response.status === 200){
                      console.log(1220)
                      this.$emit('update');
                      this.signUpUser = {}
                      document.querySelector('.cont').classList.remove('s--signup');
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
  computed : {
    ...mapGetters([
      'options',
      'user'
    ])
  }
}
</script>

<style>
*, *:before, *:after {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

body {
  font-family: 'Open Sans', Helvetica, Arial, sans-serif;
  background: #ededed;
}

.input, .button {
  border: none;
  outline: none;
  background: none;
  font-family: 'Open Sans', Helvetica, Arial, sans-serif;
}

.tip {
  font-size: 20px;
  margin: 40px auto 50px;
  text-align: center;
}

.cont {
  overflow: hidden;
  position: relative;
  width: 900px;
  height: 550px;
  margin: auto;
  background: #fff;
}

.form {
  position: relative;
  width: 640px;
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
.cont.s--signup .sub-cont {
  -webkit-transform: translate3d(-640px, 0, 0);
          transform: translate3d(-640px, 0, 0);
}

.button {
  display: block;
  margin: 0 auto;
  width: 260px;
  height: 36px;
  border-radius: 30px;
  color: #fff;
  font-size: 15px;
  cursor: pointer;
  background-color: white;
}

.img {
  overflow: hidden;
  z-index: 2;
  position: absolute;
  left: 0;
  top: 0;
  width: 260px;
  height: 100%;
  padding-top: 360px;
}
.img:before {
  content: '';
  position: absolute;
  right: 0;
  top: 0;
  width: 900px;
  height: 100%;
  background-image: url("https://s3-us-west-2.amazonaws.com/s.cdpn.io/142996/sections-3.jpg");
  background-size: cover;
  -webkit-transition: -webkit-transform 1.2s ease-in-out;
  transition: -webkit-transform 1.2s ease-in-out;
  transition: transform 1.2s ease-in-out;
  transition: transform 1.2s ease-in-out, -webkit-transform 1.2s ease-in-out;
}
.img:after {
  content: '';
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.6);
}
.cont.s--signup .img:before {
  -webkit-transform: translate3d(640px, 0, 0);
          transform: translate3d(640px, 0, 0);
}
.img__text {
  z-index: 2;
  position: absolute;
  left: 0;
  top: 50px;
  width: 100%;
  padding: 0 20px;
  text-align: center;
  color: #fff;
  -webkit-transition: -webkit-transform 1.2s ease-in-out;
  transition: -webkit-transform 1.2s ease-in-out;
  transition: transform 1.2s ease-in-out;
  transition: transform 1.2s ease-in-out, -webkit-transform 1.2s ease-in-out;
}
.img__text h2 {
  margin-bottom: 10px;
  font-weight: normal;
}
.img__text p {
  font-size: 14px;
  line-height: 1.5;
}
.cont.s--signup .img__text.m--up {
  -webkit-transform: translateX(520px);
          transform: translateX(520px);
}
.img__text.m--in {
  -webkit-transform: translateX(-520px);
          transform: translateX(-520px);
}
.cont.s--signup .img__text.m--in {
  -webkit-transform: translateX(0);
          transform: translateX(0);
}
.img__btn {
  overflow: hidden;
  z-index: 2;
  position: relative;
  width: 100px;
  height: 36px;
  margin: 0 auto;
  background: transparent;
  color: #fff;
  text-transform: uppercase;
  font-size: 15px;
  cursor: pointer;
}
.img__btn:after {
  content: '';
  z-index: 2;
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  border: 2px solid #fff;
  border-radius: 30px;
}
.img__btn span {
  position: absolute;
  left: 0;
  top: 0;
  display: -webkit-box;
  display: flex;
  -webkit-box-pack: center;
          justify-content: center;
  -webkit-box-align: center;
          align-items: center;
  width: 100%;
  height: 100%;
  -webkit-transition: -webkit-transform 1.2s;
  transition: -webkit-transform 1.2s;
  transition: transform 1.2s;
  transition: transform 1.2s, -webkit-transform 1.2s;
}
.img__btn span.m--in {
  -webkit-transform: translateY(-72px);
          transform: translateY(-72px);
}
.cont.s--signup .img__btn span.m--in {
  -webkit-transform: translateY(0);
          transform: translateY(0);
}
.cont.s--signup .img__btn span.m--up {
  -webkit-transform: translateY(72px);
          transform: translateY(72px);
}

h2 {
  width: 100%;
  font-size: 26px;
  text-align: center;
}

.label {
  display: block;
  width: 260px;
  margin: 0px auto 0;
  text-align: center;
}

.subLabel {
  display: block;
  width: 260px;
  margin: 0px auto 0;
  height: 50px;
  text-align: center;
}

.login-form {
  margin: 0px
}
.label .span {
  font-size: 12px;
  color: #cfcfcf;
  text-transform: uppercase;
}

.input {
  display: block;
  width: 100%;
  margin-top: 5px;
  padding-bottom: 5px;
  font-size: 16px;
  border-bottom: 1px solid rgba(0, 0, 0, 0.4);
  text-align: center;
  background-color: white;
}

.forgot-pass {
  margin-top: 15px;
  text-align: center;
  font-size: 12px;
  color: #cfcfcf;
}

.submit {
  margin-top: 40px;
  margin-bottom: 20px;
  background: #d4af7a;
  text-transform: uppercase;
}
.close {
  margin-top: 40px;
  margin-bottom: 20px;
  background: #F51B00;
  text-transform: uppercase;
}
.fb-btn {
  border: 2px solid #d3dae9;
  color: #8fa1c7;
}
.fb-btn span {
  font-weight: bold;
  color: #455a81;
}

.sign-in {
  -webkit-transition-timing-function: ease-out;
          transition-timing-function: ease-out;
    background-color: #E9EBEE;
}
.cont.s--signup .sign-in {
  -webkit-transition-timing-function: ease-in-out;
          transition-timing-function: ease-in-out;
  -webkit-transition-duration: 1.2s;
          transition-duration: 1.2s;
  -webkit-transform: translate3d(640px, 0, 0);
          transform: translate3d(640px, 0, 0);
}

.sign-up {
  -webkit-transform: translate3d(-900px, 0, 0);
          transform: translate3d(-900px, 0, 0);
          background-color: #E9EBEE;
}
.cont.s--signup .sign-up {
  -webkit-transform: translate3d(0, 0, 0);
          transform: translate3d(0, 0, 0);
}

.icon-link {
  position: absolute;
  left: 5px;
  bottom: 5px;
  width: 32px;
}
.icon-link img {
  width: 100%;
  vertical-align: top;
}
.icon-link--twitter {
  left: auto;
  right: 5px;
}
</style>