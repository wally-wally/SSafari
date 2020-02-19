import Vue from 'vue'
import Vuex from 'vuex'
import VueSession from 'vue-session'
import jwtDecode from 'jwt-decode'
import axios from 'axios'

Vue.use(VueSession)

Vue.use(Vuex)

var tempgithubID = null
export default new Vuex.Store({
    state : {       
      unreadmsg : 0,  
        category : {
            'free' : '1',
            'job' : '2',
            'jmt' : '4',
            'code' : '3',
        },
        categorys : {
            '1' : 'free',
            '2' : 'job',
            '3' : 'code',
            '4' : 'jmt'
         },
        region: {
            'All': '0',
            'Seoul': '1',
            'Daejeon': '2',
            'Gumi': '3',
            'Gawngju': '4',
            'etc': '5'
        },
        locations: {
          '0': '싸피 미인증',
          '1': '서울',
          '2': '대전',
          '3': '구미',
          '4': '광주'
        },
        token: null,
        isLogin : false,
        memberid : null,
        username : null,
        social: null,
        auth: null,
        img: null,
        unit: null
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
        state.social = jwtDecode(state.token)['access-Token'].social
        state.auth = jwtDecode(state.token)['access-Token'].auth
        state.img = jwtDecode(state.token)['access-Token'].img
        state.unit = jwtDecode(state.token)['access-Token'].unit
        state.locationid = jwtDecode(state.token)['access-Token'].locationid
    },
    logout(state){
        const result = confirm('로그아웃 하시겠습니까??')
        if (result) {
          console.log('hello world')
            state.token = null
            state.memberid = null
            state.username = null
            state.isLogin = false
            state.social = null
            state.auth = null
            state.img = null
            state.unit = null
            state.locationid = null
            window.location.replace('http://localhost:8080/')
        }
    },
    dropout(state){
        state.token = null
        state.memberid = null
        state.username = null
        state.isLogin = false
        state.social = null
        state.auth = null
        state.img = null
        state.unit = null
        state.locationid = null
    },
    memberChange(state) {
      state.token = token
      state.memberid = jwtDecode(state.token)['access-Token'].memberid
      state.username = jwtDecode(state.token)['access-Token'].username
      state.isLogin = true
      state.social = jwtDecode(state.token)['access-Token'].social
      state.auth = jwtDecode(state.token)['access-Token'].auth
      state.img = jwtDecode(state.token)['access-Token'].img
      state.unit = jwtDecode(state.token)['access-Token'].unit
      state.locationid = jwtDecode(state.token)['access-Token'].locationid
  },
  setunreadmsg(state,num) {
    state.unreadmsg = num
  }
},
 actions : {
    // 첫번째 인자는 context (다양한)
    // 이후 인자는 payload(매개변수)
    login(context, token) {
        // mutation 호출 -> commit
        context.commit('login',token)
        axios.get(`api/message` , { headers : {'access-token' : token } })
        .then(response => {
          context.commit('setunreadmsg',response.data)
        }).catch(error => {
          console.log(error)
        })
    },
    logout(context) {
        context.commit('logout')
        sessionStorage.clear()
    },
    dropout(context) {
        context.commit('dropout')
        sessionStorage.clear()
    }
},
 getters : {
        githubid(state) {
          tempgithubID = jwtDecode(state.token)['access-Token'].githubid
          return tempgithubID
        },
        user(state) {
            return jwtDecode(state.token)['access-Token']
        },
        options(state) {
            return {
                headers : {
                    'access-token' : state.token,
                }
            }
        },
        githubReposInfo(state) {
            let myGithubID = tempgithubID
            let githubBaseUrl = `https://api.github.com/users/${myGithubID}`
            let reposArray = []
            axios.get(`${githubBaseUrl}/repos`)
              .then(response => {
                const githubReposData = response.data
                githubReposData.forEach(function(data) {
                  let repoInfo = {
                    'name': data.name,
                    'starCount': data['stargazers_count'],
                    'reposUrl': data['html_url'],
                    'cloneUrl': data['clone_url'],
                    'description': data['description']
                  }
                  axios.get(`https://api.github.com/repos/${myGithubID}/${repoInfo['name']}/languages`)
                    .then(res => {
                      // make vue-chartjs
                      let langs = res.data
                      console.log(langs)
                      let chartData = {
                        labels: [],
                        datasets: []
                      }
                      if (Object.keys(langs).length !== 0) {
                        function langSum(obj) {
                          return Object.keys(obj).reduce((sum, key) => sum + obj[key], 0)
                        }
                        let langSumValue = langSum(langs)
                        let flagIdx = 0
                        for (let lang in langs) {
                          if (flagIdx === 3) { break }
                          chartData.labels.push(lang)
                          chartData.datasets.push({
                            label: `${lang}`,
                            background: '#f87979',
                            pointBackgroundColor: 'white',
                            borderWidth: 1,
                            pointBorderColor: '#249ebf',
                            data: [`${((langs[lang] / langSumValue) * 100).toFixed(2)}`]
                          })
                          flagIdx += 1
                        }
                      }
                        repoInfo['reposChart'] = chartData
                    })
                  reposArray.push(repoInfo)
                })
                console.log(reposArray)
              })
              .catch(error => console.log(error))
            return reposArray
          }
    }
})