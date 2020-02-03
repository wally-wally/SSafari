<template>
<v-layout>
  <v-app-bar v-if="mobile()" app color="white lighten-4" tile>
    <div class="d-flex align-center">
      <a href="/" style="text-decoration: none; color: #f7b157;">
        <v-toolbar-title style="font-size: 16px; padding-right: 10px;">iTeacher</v-toolbar-title>
      </a> &nbsp; &nbsp;
      <span v-if="userName === ''" class="d-flex align-center">Welcome, Guest</span>
      <span v-else class="d-flex align-center">Welcome, {{ this.$store.state.userName }}</span>
    </div>

    <v-spacer></v-spacer>
    <v-btn v-if="mobile() && isLogin" color="white" depressed tile to="/mypage">MYPAGE</v-btn>
    <v-btn v-if="mobile()" color="white" depressed tile to="/post">POST</v-btn>
    <v-btn v-if="mobile()" color="white" depressed tile to="/portfolio">PORTFOLIO</v-btn>
    <v-btn v-if="mobile() && !isLogin" color="white" @click.stop="loginDialog = true" depressed tile>LOGIN</v-btn>
    <v-btn v-if="mobile() && isLogin" color="white" depressed tile @click="$store.dispatch('logout')">LOGOUT</v-btn>
    <v-app-bar-nav-icon v-if="!mobile()" @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
  
  </v-app-bar>

  <v-app-bar v-else app :collapse="!collapseOnScroll" :collapse-on-scroll="collapseOnScroll" color="white" @click.stop="drawer = !drawer">
    <div class="d-flex align-center">
      <a href="/" style="text-decoration: none; color: #f7b157;"> <v-toolbar-title>iTeacher</v-toolbar-title></a>
    </div>
    <v-spacer></v-spacer>
    <v-app-bar-nav-icon></v-app-bar-nav-icon>
  </v-app-bar>

  <!-- <v-icon v-else @click.stop="drawer = !drawer"></v-icon> -->
  <v-navigation-drawer v-model="drawer" absolute temporary class="v-navigation-drawer--fixed">
    <v-divider></v-divider>

    <v-list dense>

      <v-list-item class="pb-3">
        <v-list-item-content>
          <v-list-item-title v-if="userName === ''">Welcome, Guest</v-list-item-title>
          <v-list-item-title v-else>Welcome, {{ this.$store.state.userName }}</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-divider></v-divider>

      <v-list-item to="/" class="pt-3">
        <v-list-item-icon>
          <v-icon>home</v-icon>
        </v-list-item-icon>
        <v-list-item-content>
          <v-list-item-title>HOME</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-list-item to="/post">
        <v-list-item-icon>
          <v-icon>event</v-icon>
        </v-list-item-icon>
        <v-list-item-content>
          <v-list-item-title>POST</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-list-item to="/portfolio">
        <v-list-item-icon>
          <v-icon>dashboard</v-icon>
        </v-list-item-icon>
        <v-list-item-content>
          <v-list-item-title>PORTFOLIO</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-list-item v-if="isLogin" to="/mypage">
        <v-list-item-icon>
        <v-icon class="fas fa-user"></v-icon>
       </v-list-item-icon>
        <v-list-item-content>
          <v-list-item-title>MYPAGE</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-list-item v-if="!isLogin" @click.stop="loginDialog = true">
        <v-list-item-icon>
          <v-icon>info</v-icon>
        </v-list-item-icon>
        <v-list-item-content>
          <v-list-item-title>LOGIN</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-list-item v-if="isLogin" @click="$store.dispatch('logout')">
        <v-list-item-icon>
          <v-icon>info</v-icon>
        </v-list-item-icon>
        <v-list-item-content>
          <v-list-item-title>LOGOUT</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

    </v-list>

  </v-navigation-drawer>

  <v-dialog v-if="mobile()" v-model="loginDialog" max-width="900px">
    <LoginSignup v-on:update="changeLoginDialog"/>
  </v-dialog>
  <v-dialog v-else v-model="loginDialog" max-width="500px">
    <LoginSignupMobile v-on:update="changeLoginDialog"/>
  </v-dialog>
</v-layout>
</template>

<script>
import router from 'vue-router'
import axios from 'axios'
import FirebaseService from '@/services/FirebaseService'
import { mapState, mapActions, mapMutations } from 'vuex'
import LoginSignup from '@/components/LoginSignup.vue'
import LoginSignupMobile from '@/components/LoginSignupMobile.vue'

export default {
  name: 'Header',
  components: {
    LoginSignup,
    LoginSignupMobile
  },
  data() {
    return {
      loginfailcount : 0,
      drawer: null,
      collapseOnScroll: true,
      loginDialog: false,
      valid: true,
      credentials: {},
      emailRules: [
        v => !!v || 'E-mail is required',
        v => /.+@.+\..+/.test(v) || 'E-mail must be valid',
      ],
    };
  },
  computed: {
    ...mapState(['isLogin', 'isLoginError']),
    userName: function() {
      return this.$store.state.userName
    }
  },
  methods: {
    changeLoginDialog() {
      this.loginDialog = false
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
    ...mapActions(['loginSuccess', 'logout']),
    mobile() {
      if (this.$vuetify.breakpoint.name === "xs") {
        return false;
      }
      return true;
    },
    loginWithGoogle() {

    }
  }
};
</script>
