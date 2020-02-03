<template>
  <v-layout mt-5 wrap>
    <v-flex v-for="i in this.showPortfoliosCount" :key="i" col-12 sm6 md3>
	  <router-link :to="`/portfolio/${portfolios[i - 1].portfolioid}`">
      <Portfolio class="ma-3"
								:date="portfolios[i - 1].created_at.toString()"
								:title="portfolios[i - 1].title"
								:body="portfolios[i - 1].body"
								:imgSrc="portfolios[i - 1].img"
								:username="portfolios[i - 1].username"
								:memberid="portfolios[i - 1].memberid"
      ></Portfolio>
	  </router-link>
    </v-flex>

    <v-flex xs12 text-xs-center round my-5 v-if="loadMore">
      <v-btn v-if="this.portfolios.length > 6 && this.morePortfoliosIcon" color="#f7b157" dark v-on:click="loadMorePortfolios"><v-icon size="25" class="mr-2">fa-plus</v-icon> 더 보기</v-btn>&nbsp; &nbsp;
			<v-btn v-if="this.hidePortfoliosIcon" color="red" dark v-on:click="hidePortfolios"><v-icon size="25" class="mr-2">fa-minus</v-icon> 숨기기</v-btn>
		</v-flex>
  </v-layout>
</template>
<script>
import Portfolio from '@/components/portfolio/Portfolio'
import axios from 'axios'

export default {
	name: 'PortfoliosList',
	props: {
		limits: {type: Number, default: 8},
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
		Portfolio
	},
	mounted() {
		this.getPortfolios()
	},
	methods: {
		getPortfolios() {
			axios.get('http://192.168.31.110:8197/ssafyvue/api/portfolios')
				.then(response => {
					console.log(response.data)
					this.portfolios = response.data
					this.showPortfoliosCount = (this.portfolios.length >= 6) ? 6 : this.portfolios.length 
					this.$emit('showPortfolioCount', this.portfolios.length)
				})
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
<style>
  .mw-700 {
    max-width: 700px;
    margin: auto;
  }
</style>
