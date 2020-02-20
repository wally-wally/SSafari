import Vue from 'vue'
import Vuetify from 'vuetify/lib'
import 'vuetify/dist/vuetify.min.css'
import VueSimplemde from 'vue-simplemde'
import 'simplemde/dist/simplemde.min.css'
import 'font-awesome/css/font-awesome.min.css'
import VueCodemirror from 'vue-codemirror'
import 'codemirror/lib/codemirror.css'
import ECharts from 'vue-echarts'
import App from './App.vue'
import router from './router'
import store from './store'
import './registerServiceWorker'
import vuetify from './plugins/vuetify';
import * as Vueperslides from 'vueperslides'
import 'vueperslides/dist/vueperslides.css'
import AOS from 'aos';
import 'aos/dist/aos.css'
import axios from 'axios'

Vue.prototype.$EventBus = new Vue();
Vue.config.productionTip = false

axios.defaults.baseURL = 'https://i02b101.p.ssafy.io/ssafyvue'
//axios.defaults.baseURL = 'http://192.168.31.110:8197/ssafyvue'
// 모든 요청에 추가할 헤더 설정
// axios.defaults.headers.common['Authorization'] = 'something'
// GET 요청에 추가할 헤더 설정
Vue.use(VueCodemirror, /* { 
  options: { theme: 'base16-dark', ... },
  events: ['scroll', ...]
} */)
Vue.use(VueSimplemde)
Vue.use(Vuetify)
Vue.use(Vueperslides)
Vue.use(AOS)
AOS.init()

Vue.component('v-chart', ECharts)
router.afterEach((to, from) => {
  if (store.state.isLogin){
	axios.get(`api/message` , { headers : {'access-token' : store.state.token } })
	.then(response => {
	  store.state.unreadmsg = response.data
	}).catch(error => {
	  console.log(error)
  })
}
})
new Vue({
  router,
  store,
  vuetify: new Vuetify({
      iconfont: 'fa',
      theme: {
          primary: '#3f51b5',
          secondary: '#b0bec5',
          accent: '#8c9eff',
          error: '#b71c1c'
      }
  }),
  vuetify,
  render: h => h(App)
}).$mount('#app')
