import Vue from 'vue'
import Router from 'vue-router'
import HomePage from './views/HomePage.vue'
import TeamIntro from './views/TeamIntro.vue'
import BoardPage from './views/BoardPage.vue'
import AllBoard from './components/board/AllBoard.vue'
import BoardDetail from './components/board/boardCommonForm/BoardDetail.vue'
import BoardCreate from '@/components/board/boardCommonForm/BoardCreate.vue'
import BaseBoard from './components/board/BaseBoard.vue'
import CodeBoard from './components/board/codeboard/CodeBoard.vue'
import JmtBoard from './components/board/Jmtboard/JmtBoard.vue'
import CodeDetail from './components/board/codeboard/CodeDetail.vue'
import StudyGroupPage from './views/StudyGroupPage.vue'
import StudyGroupindex from './components/studygroup/StudyGroupindex.vue'
import StudyGroupDetail from './components/studygroup/StudyGroupDetail.vue'
import CodeCreate from  './components/board/codeboard/CodeCreate.vue'
import boardsearch from './components/board/boardCommonForm/boardsearch.vue'
import StudyGroupCreateForm from './components/studygroup/StudyGroupCreateForm.vue'
import UpdateForm from './components/etc/UpdateForm.vue'
import MemberModify from '@/components/login/MemberModify.vue'
import PasswordModify from '@/components/login/PasswordModify.vue'
import SsafyAuth from '@/components/login/SsafyAuth.vue'
import MakeSsafyAuth from '@/components/login/MakeSsafyAuth.vue'
import MyPage from  './views/MyPage.vue'

import MessageBoard from '@/components/message/MessageBoard.vue'
import RestaurantCreateForm from '@/components/board/Jmtboard/RestaurantCreateForm.vue'
import RestaurantDetail from '@/components/board/Jmtboard/RestaurantDetail.vue'

Vue.use(Router)

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
				{ path : 'search' , name:"boardsearch", component: boardsearch , props:true},
				{ path : 'search/:q' , name:"boardsearch" , component: boardsearch , props:true},
				{ path : 'create', component: BoardCreate, name: 'BoardCreate'},
				{ path : '', component : AllBoard },
				{ path : 'code', name:"code", component : CodeBoard,},
				{ path : 'code/create', name: "codecreate", component : CodeCreate},
				{ path : 'code/:id', name : "codedetail", component : CodeDetail , props : true},
				{ path : 'code/:id/edit', name : "codeedit", component : CodeCreate , props : true},
				{ path : 'jmt' , name: "jmt" ,component : JmtBoard },
				{ path : 'jmt/create', name: 'jmtcreate', component: RestaurantCreateForm},
				{ path : 'jmt/:id', name : "jmtdetail", component : RestaurantDetail , props : true},
				{ path : ':boardname', component: BaseBoard, props:true},
				{ path : ':boardname/:id' , component: BoardDetail, props:true},
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
		},
		{
			path: '/message',
			name: 'MessageBoard',
			component: MessageBoard
		}
  ]
})