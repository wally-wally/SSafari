import Vue from 'vue'
import Vuex from 'vuex'
import VueSession from 'vue-session'
import jwtDecode from 'jwt-decode'

Vue.use(VueSession)

Vue.use(Vuex)

export default new Vuex.Store({
    state : {         
        category : {
            'free' : '1',
            'job' : '2',
            'jmt' : '4',
        },
        token: null,
        isLogin : false,
        memberid : null,
        username : null,
    },
// mutations : state를 변화시키기 위한 메서드(함수)
 mutations : {
    // 첫번째 인자는 state
    // 이후 인자는 payload(즉, 매개변수)
    login(state, token) {
        state.token = token
        state.memberid = jwtDecode(state.token)['access-Token'].memberid
        state.username = jwtDecode(state.token)['access-Token'].username
        state.isLogin = true
    },
    logout(state){
        const result = confirm('로그아웃 하시겠습니까')
        if (result) {
            state.token = null
            state.memberid = null
            state.username = null
            state.isLogin = false
        }
    }
},
 actions : {
    // 첫번째 인자는 context (다양한)
    // 이후 인자는 payload(매개변수)
    login(context, token) {
        // mutation 호출 -> commit
        context.commit('login',token)
    },
    logout(context) {
        context.commit('logout')
        sessionStorage.clear()
    }
},
 getters : {
        githubid(state) {
            return jwtDecode(state.token)['access-Token'].githubid
        },
        user(state) {
            console.log('---------------')
            console.log(jwtDecode(state.token))
            return jwtDecode(state.token)['access-Token']
        },
        options(state) {
            return {
                headers : {
                    Authorization : state.token,
                }
            }
        }
    }
})