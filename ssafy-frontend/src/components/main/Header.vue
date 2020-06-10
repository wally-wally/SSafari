<template>
<div class="top-navbar">
  <v-app-bar v-if="mobile()" app color="white lighten-4" tile style="height:67px">
    <div class="d-flex align-center">
      <router-link :to="{name: 'home'}" style="text-decoration: none; color: #f7b157;">
        <v-toolbar-title style="font-size: 16px; padding-right: 10px;">SSafari</v-toolbar-title>
      </router-link> &nbsp; &nbsp;
      <span v-if="!isLogin" class="d-flex align-center">Welcome, Guest</span>
      <span v-else class="d-flex align-center">Welcome, {{$store.state.username}}</span>
    </div>

    <v-spacer></v-spacer>
    <nav class="nav" style="text-decoration:none">
      <router-link v-if="mobile()" to="/board" class="nav-item d-flex align-center" style="color:#000">게시판</router-link>
      <router-link v-if="mobile()" to="/studygroup" class="nav-item d-flex align-center" style="color:#000">스터디 모임</router-link>
      <router-link v-if="mobile() && isLogin" to="/message" class="nav-item d-flex align-center" style="color:#000"><i style="font-size:20px;" class="far fa-bell"></i><div class="unreadmsg"><p align="center" justify="center">{{this.$store.state.unreadmsg}}</p></div></router-link>
      <router-link v-if="mobile() && isLogin" to="/mypage" class="nav-item d-flex align-center" style="color:#000">내 페이지</router-link>
      <a v-if="mobile() && !isLogin" class="nav-item d-flex align-center" @click.stop="loginDialog = true" style="color:#000">로그인</a>
      <a v-if="mobile() && isLogin" class="nav-item d-flex align-center" @click="$store.dispatch('logout')" style="color:#000">로그아웃</a>
      <v-app-bar-nav-icon v-if="!mobile()" @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      <span class="nav-indicator"></span>
    </nav>
  </v-app-bar>

  <v-speed-dial v-else v-model="fab" :bottom="true" :right="true" fixed>
    <template v-slot:activator>
      <v-btn v-model="fab" color="#ff8008" dark fab @click="drawer = !drawer">
        <v-icon class="fas fa-ellipsis-h"></v-icon>
      </v-btn>
    </template>
  </v-speed-dial>
  <v-navigation-drawer v-model="drawer" absolute temporary class="v-navigation-drawer--fixed">
    <v-divider></v-divider>
    <v-list dense>
      <v-list-item class="pb-3">
        <v-list-item-content>
          <v-list-item-title v-if="!isLogin">Welcome, Guest</v-list-item-title>
          <v-list-item-title v-else>Welcome, {{$store.state.username}}</v-list-item-title>
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

      <v-list-item to="/board">
        <v-list-item-icon>
          <v-icon>event</v-icon>
        </v-list-item-icon>
        <v-list-item-content>
          <v-list-item-title>BOARD</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-list-item to="/studygroup">
        <v-list-item-icon>
          <v-icon>dashboard</v-icon>
        </v-list-item-icon>
        <v-list-item-content>
          <v-list-item-title>STUDYGROUP</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
      
      <v-list-item v-if="isLogin" to="/message">
        <v-list-item-icon>
          <v-icon class="fas fa-envelope"></v-icon>
        </v-list-item-icon>
        <v-list-item-content>
          <v-list-item-title>MESSAGE BOX{{ this.$store.state.unreadmsg > 0 ? `(${this.$store.state.unreadmsg})` : ''}}</v-list-item-title>
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

  <v-dialog v-if="mobile() && loginDialog" v-model="loginDialog" max-width="900px">
    <LoginSignup v-on:update="changeLoginDialog"/>
  </v-dialog>
  <v-dialog v-if="!mobile() && loginDialog" v-model="loginDialog" max-width="500px">
    <LoginSignupMobile v-on:update="changeLoginDialog"/>
  </v-dialog>
</div>
</template>

<script>
import router from 'vue-router'
import axios from 'axios'
import LoginSignup from '@/components/login/LoginSignup.vue'
import LoginSignupMobile from '@/components/login/LoginSignupMobile.vue'
import { mapState } from 'vuex'
import '../../assets/js/snake.js'

export default {
  name: 'Header',
  components: {
    LoginSignup,
    LoginSignupMobile
  },
  data() {
    return {
      unreadmsgcnt : 0,
      drawer: false,
      collapseOnScroll: true,
      loginDialog: false,
      fab: true,
    };
  },
  computed : {
		...mapState(['isLogin'])
  },
  methods: {
    changeLoginDialog() {
      this.loginDialog = false
    },
    mobile() {
      if (this.$vuetify.breakpoint.name === "xs"||this.$vuetify.breakpoint.name === "sm") {
        return false;
      }
      return true;
    },
  },
};
</script>

<style>
.nav{
  display:inline-flex;
  position:relative;
  overflow:hidden;
  max-width:100%;
  height: 48px;
  background-color:white;
  padding:0 20px;
}

.nav-item{
  color: #83818c;
  padding:10px 20px;
  text-decoration: none;
  transition: 0.3s;
  margin: 0 7px;
  z-index: 1;
  font-family: tahoma;
  font-weight: 500;
  position: relative;
}

.nav-item:before{
  content:"";
  position:absolute;
  bottom: -7px;
  left: 0;
  width: 100%;
  height: 5px;
  background-color: #ffc837;
  border-radius: 8px 8px 0 0;
  opacity: 0;
  transition: 0.3s;
}

.nav-item:not(.is-active):hover:before{
  opacity: 1;
  bottom: 0;
}

.nav-item:not(.is-active):hover{
  color: #333;
}

.nav-indicator{
  position:absolute;
  left:0;
  bottom: 0;
  height: 4px;
  transition: 0.4s;
  height: 5px;
  z-index: 1;
  border-radius: 8px 8px 0 0;
}

.unreadmsg {
  margin-top : -10px;
  margin-left : -5px;
  background-color : brown;
  width : 15px;
  height: 15px;
  border-radius : 7.5px;
  font-size : 5px;
  color : white;
}

.top-navbar .v-speed-dial {
  z-index:2;
}

.top-navbar .v-btn--floating {
  position: relative;
  z-index:2;
}
</style>