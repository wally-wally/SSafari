import Vue from 'vue'
import Router from 'vue-router'
import HomePage from './views/HomePage.vue'
import PostPage from './views/PostPage.vue'
import PostDetail from './views/PostDetail.vue'
import Postindex from './components/post/Postindex.vue'
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
			path: '/post',
			name: 'post',
			component: PostPage,
			children : [
				{path : '', component : Postindex},
				{path:'create' , component : PostCreateForm}
			]
		},
		{
			path: '/post/:id',
			name: 'postDetail',
			component: PostDetail,
			props: true
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
		}
  ]
})
