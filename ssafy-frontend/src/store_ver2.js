import Vue from 'vue'
import Vuex from 'vuex'
import VueSession from 'vue-session'
import FirebaseService from '@/services/FirebaseService'
import router from "./router"
Vue.use(VueSession)

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
		accessToken: '',
    userName: '',
    isLogin:false,
    isLoginError:false,
    memberid : '',
    name :'',
    auth : '',
  },
  mutations: {
    loginSuccess(state,info) {
      // console.log(`${name}------`)
      state.isLogin = true
      state.isLoginError = false
      // state.accessToken=info['tokenValue']
      state.userName=info['username']
      state.memberid = info['memberid']
      state.name = info['name']
      state.auth = info['auth']
    },
    loginError(state) {
      state.isLogin = false
      state.isLoginError = ture
    },
    logout(state) {
      state.isLogin=false
      state.isLoginError=false
      state.userName=''
      state.accessToken= ''
      state.memberid = ''
      state.name =''
      state.auth = ''
    }
  },
  actions: {
    async loginWithGoogle({dispatch, commit}) {
      let result = await FirebaseService.loginWithGoogle()
      let googleUserName = result.user.displayName
      let userInfo = {
        tokenValue: result.credential.accessToken,
        googleName: googleUserName
      }
      commit('loginSuccess', userInfo)
      sessionStorage.setItem("accessToken",userInfo['tokenValue'])
      sessionStorage.setItem("userName", userInfo['googleName']) // result['user']['displayName']
      dispatch("getMemberInfo")
      router.push("/")
      location.reload()
    },
    logout({commit}) {
      const result = confirm('로그아웃 하시겠습니까')
      if (result) {
        sessionStorage.clear()
        commit('logout')
        router.push("/")
      }
    },
    getMemberInfo({ commit }) {
      let token = sessionStorage.getItem("accessToken")
      let name = sessionStorage.getItem("userName")
      let getInfo = {
        tokenValue: token,
        googleName: name
      }
      if(token===null && name===null) return
      commit('loginSuccess', getInfo)
    }
  }
})
