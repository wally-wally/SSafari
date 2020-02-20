<template>
  <div mt-5 class="d-flex">
		<div v-if="this.portfolios.length === 0" class="ma-10 text-center" style="font-family: 'Nanum Gothic', sans-serif; font-weight: bold;">
			신청한 스터디모임이 없습니다.
		</div>
    <div v-else v-for="i in this.showPortfoliosCount" :key="i" class="col-12 col-sm-6 col-lg-4 col-xl-3 pa-2">
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
		
    <v-flex xs12 text-xs-center round my-5>
      <v-btn v-if="this.portfolios.length > 4 && this.morePortfoliosIcon" color="#f7b157" dark v-on:click="loadMorePortfolios"><v-icon size="25" class="mr-2">fa-plus</v-icon> 더 보기</v-btn>&nbsp; &nbsp;
			<v-btn v-if="this.hidePortfoliosIcon" color="red" dark v-on:click="hidePortfolios"><v-icon size="25" class="mr-2">fa-minus</v-icon> 숨기기</v-btn>
		</v-flex>
  </div>
</template>
<script>
import StudyGroup from '@/components/studygroup/StudyGroup'
import axios from 'axios'

export default {
	name: 'MyStudyParticipated',
	props: {
		limits: {type: Number, default: 4},
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
			axios.get(`api/memberportfolio/${ this.$store.state.memberid }`)
				.then(response => {
					this.portfolios = response.data
					this.showPortfoliosCount = (this.portfolios.length >= 4) ? 4 : this.portfolios.length
					this.loadMore = (this.likes.portfolios > 4) ? true : false
				}).catch(error =>
				    console.log(error)
				)
		},
		loadMorePortfolios() {
			let adjustCount = this.showPortfoliosCount + 4 < this.portfolios.length ? this.showPortfoliosCount + 4 : this.portfolios.length
			this.showPortfoliosCount = adjustCount
			this.morePortfoliosIcon = adjustCount < this.portfolios.length ? true : false
			this.hidePortfoliosIcon = true
    	},
		hidePortfolios() {
			let adjustCount2 = this.showPortfoliosCount - 4 > 4 ? this.showPortfoliosCount - 4 : 4
			this.showPortfoliosCount = adjustCount2
			this.hidePortfoliosIcon = adjustCount2 === 4 ? false : true
			this.morePortfoliosIcon = true
		}
	},
}
</script>
