import Vue from 'vue'
import Router from 'vue-router'
import HomePage from './views/HomePage.vue'
import TeamIntro from './views/TeamIntro.vue'

// Board(게시판) import 구문
import BoardPage from './views/BoardPage.vue'

import AllBoard from './components/board/AllBoard.vue'
import BoardDetail from './components/board/boardCommonForm/BoardDetail.vue'
import BoardCreate from '@/components/board/boardCommonForm/BoardCreate.vue'
import BaseBoard from './components/board/BaseBoard.vue'
import CodeBoard from './components/board/codeboard/CodeBoard.vue'
import JmtBoard from './components/board/Jmtboard/JmtBoard.vue'
import CodeDetail from './components/board/codeboard/CodeDetail.vue'
// StudyGroup(스터디 모임) import 구문
import StudyGroupPage from './views/StudyGroupPage.vue'
import StudyGroupindex from './components/studygroup/StudyGroupindex.vue'
import StudyGroupDetail from './components/studygroup/StudyGroupDetail.vue'
import CodeCreate from  './components/board/codeboard/CodeCreate.vue'

import StudyGroupCreateForm from './components/studygroup/StudyGroupCreateForm.vue'
import UpdateForm from './components/etc/UpdateForm.vue'
import MemberModify from '@/components/login/MemberModify.vue'
import PasswordModify from '@/components/login/PasswordModify.vue'
import SsafyAuth from '@/components/login/SsafyAuth.vue'
import MakeSsafyAuth from '@/components/login/MakeSsafyAuth.vue'
import MyPage from  './views/MyPage.vue'
import store from './store'
 
import RestaurantCreateForm from '@/components/board/Jmtboard/RestaurantCreateForm.vue'
import RestaurantDetail from '@/components/board/Jmtboard/RestaurantDetail.vue'

Vue.use(Router)

const rejectAuthUser = (to, from, next) => {
	if(store.state.isLogin === true) {
		alert('이미 로그인 하였습니다.')
		next('/')
	}else {
		next('/login')
	}
}
export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
		{
			path: '/',
			name: 'home',
			component: HomePage
		},
		{
			path: '/board',
			name: 'board',
			component: BoardPage,
			children : [
				{ path : 'create', component: BoardCreate, name: 'BoardCreate'},
				{ path : '', component : AllBoard },
				{ path : 'free/:id', name: "free", component : BoardDetail , props:true},
				{ path : 'job/:id', name: "job", component : BoardDetail , props:true},
				{ path : 'codereview', name:"code", component : CodeBoard,},
				{ path : 'codereview/create', name: "codecreate", component : CodeCreate},
				{ path : 'codereview/:id', name : "codedetail", component : CodeDetail , props : true},
				{ path : 'codereview/:id/edit', name : "codeedit", component : CodeCreate , props : true},
				{ path : 'jmt' , name: "jmt" ,component : JmtBoard },
				{ path : 'jmt/create', name: 'jmtcreate', component: RestaurantCreateForm},
				{ path : 'jmt/:id', name : "jmtdetail", component : RestaurantDetail , props : true},
				{ path : ':boardname', component: BaseBoard, props:true},
				{ path : ':boardname/:id' , component: BoardDetail, props:true}
			]
		},
		{
			path: '/board/:boardname/:id/update',
			name: 'BoardUpdate',
			component: UpdateForm,
			props: true
		},
		{
			path: '/studygroup',
			name: 'Studygroup',
			component: StudyGroupPage,	
			children : [
				{ path : '', component : StudyGroupindex },
				{ path:'create' , component : StudyGroupCreateForm }
			]
		},
		{
			path: '/studygroup/:id',
			name: 'StudygroupDetail',
			component: StudyGroupDetail,
			props: true
		},
		{
			path: '/studygroup/:id/update',
			name: 'StudyGroupUpdate',
			component: UpdateForm,
			props: true
		},
		{
			path: '/teamIntro',
			name: 'teamIntro',
			component: TeamIntro
		},
		{
			path: '/mypage',
			name : 'MyPage',
			component: MyPage
		},
		{
			path: '/mypage/member',
			name: 'MemberModify',
			component: MemberModify
		},
		{
			path: '/mypage/password',
			name: 'PasswordModify',
			component: PasswordModify
		},
		{
			path: '/mypage/ssafyauth',
			name: 'SsafyAuth',
			component: SsafyAuth
		},
		{
			path: '/mypage/makessafyauth',
			name: 'MakeSsafyAuth',
			component: MakeSsafyAuth
		}
  ]
})
