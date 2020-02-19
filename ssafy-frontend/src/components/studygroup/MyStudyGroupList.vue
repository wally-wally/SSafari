<template>
  <div mt-5 class="d-flex">
    <div v-for="i in this.showPortfoliosCount" :key="i" class="col-12 col-sm-6 col-lg-4 col-xl-3 pa-2">
	  <router-link :to="`/studygroup/${portfolios[i - 1].portfolioid}`">
      <StudyGroup class="ma-3"
			:date="portfolios[i - 1].created_at.toString()"
			:title="portfolios[i - 1].title"
			:body="portfolios[i - 1].body"
			:imgSrc="portfolios[i - 1].img"
			:username="portfolios[i - 1].username"
			:memberid="portfolios[i - 1].memberid"
      ></StudyGroup>
	  </router-link>
    </div>

    <v-flex xs12 text-xs-center round my-5 v-if="loadMore">
      <v-btn v-if="this.portfolios.length > 6 && this.morePortfoliosIcon" color="#f7b157" dark v-on:click="loadMorePortfolios"><v-icon size="25" class="mr-2">fa-plus</v-icon> 더 보기</v-btn>&nbsp; &nbsp;
			<v-btn v-if="this.hidePortfoliosIcon" color="red" dark v-on:click="hidePortfolios"><v-icon size="25" class="mr-2">fa-minus</v-icon> 숨기기</v-btn>
		</v-flex>
  </div>
</template>
<script>
import StudyGroup from '@/components/studygroup/StudyGroup'
import axios from 'axios'

export default {
	name: 'MyStudyGroupList',
	props: {
		limits: {type: Number, default: 6},
    loadMore: {type: Boolean, default: false}
	},
	data() {
		return {
			portfolios: [],
			showPortfoliosCount : 0,
			morePortfoliosIcon : true,
			hidePortfoliosIcon : false
		}
	},
	components: {
		StudyGroup
	},
	mounted() {
		this.getPortfolios()
	},
	methods: {
		getPortfolios() {
			axios.get(`api/portfoliolist/${ this.$store.getters.user.memberid }`)
				.then(response => {
					this.portfolios = response.data
					this.showPortfoliosCount = (this.portfolios.length >= 6) ? 6 : this.portfolios.length 
				}).catch(error =>
				console.log(error)
				)
		},
		loadMorePortfolios() {
			let adjustCount = this.showPortfoliosCount + 6 < this.portfolios.length ? this.showPortfoliosCount + 6 : this.portfolios.length
			this.showPortfoliosCount = adjustCount
			this.morePortfoliosIcon = adjustCount < this.portfolios.length ? true : false
			this.hidePortfoliosIcon = true
    	},
		hidePortfolios() {
			let adjustCount2 = this.showPortfoliosCount - 6 > 6 ? this.showPortfoliosCount - 6 : 6
			this.showPortfoliosCount = adjustCount2
			this.hidePortfoliosIcon = adjustCount2 === 6 ? false : true
			this.morePortfoliosIcon = true
		}
	},
}
</script>
