import Vue from 'vue'
import Router from 'vue-router'
import HomePage from './views/HomePage.vue'
import BoardPage from './views/BoardPage.vue'
import PostDetail from './components/post/PostDetail.vue'
import FreeBoard from './components/post/FreeBoard.vue'
import CodeBoard from './components/post/CodeBoard.vue'
import JobBoard from './components/post/JobBoard.vue'
import jmtBoard from './components/post/jmtBoard.vue'
import CodeCreate from  './components/CodeCreate.vue'
import Boardindex from './components/post/Boardindex.vue'
import Portfolioindex from './components/portfolio/Portfolioindex.vue'
import PortfolioPage from './views/PortfolioPage.vue'
import PortfolioDetail from './views/PortfolioDetail.vue'

import PortfolioCreateForm from './components/portfolio/PortfolioCreateForm.vue'
import PostCreateForm from './components/post/PostCreateForm.vue'
import UpdateForm from './components/UpdateForm.vue'
import TeamIntro from './views/TeamIntro.vue'
import MyPage from  './views/MyPage.vue'
import store from './store'

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
				{path : '', component : Boardindex},
				{path:'create' ,
				 component : PostCreateForm
				},
				{ path : 'free', name:"free" , component : Boardindex },
				{ path : 'codereview', name:"code", component : CodeBoard,},
				{ path : 'codereview/create', name: "codecreate", component : CodeCreate},
				{path : 'job' , name: "job", component : Boardindex },
				{path : 'jmt' , name: "jmt" ,component : Boardindex},
				{path : ':id', component : PostDetail ,props:true}
				// {path: ':category',component : Postindex, props: true },
				// {path: ':category/:id', component: PostDetail,props: true}
			]
		},
		{
			path: '/post/:id/update',
			name: 'PostUpdate',
			component: UpdateForm,
			props: true
		},
		{
			path: '/portfolio',
			name: 'portfolio',
			component: PortfolioPage,	
			children : [
				{path : '', component : Portfolioindex},
				{path:'create' , component : PortfolioCreateForm}
			]
		},
		{
			path: '/portfolio/:id',
			name: 'portfolioDetail',
			component: PortfolioDetail,
			props: true
		},
		{
			path: '/portfolio/:id/update',
			name: 'PortfolioUpdate',
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
  ]
})
