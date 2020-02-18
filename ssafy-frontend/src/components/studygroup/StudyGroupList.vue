<template>
  <v-layout mt-5 wrap>
    <div v-for="i in this.showPortfoliosCount" :key="i" class="col-12 col-sm-6 col-lg-4 col-xl-3">
	  <router-link :to="`/studygroup/${studyGroups[i - 1].portfolioid}`">
      <StudyGroup class="ma-3"
								:date="studyGroups[i - 1].created_at.toString()"
								:title="studyGroups[i - 1].title"
								:body="studyGroups[i - 1].body"
								:imgSrc="studyGroups[i - 1].img"
								:username="studyGroups[i - 1].username"
								:memberid="studyGroups[i - 1].memberid"
      ></StudyGroup>
	  </router-link>
    </div>

    <v-flex xs12 text-xs-center round my-5>
      <v-btn v-if="this.studyGroups.length > 6 && this.morePortfoliosIcon" color="#f7b157" dark @click="loadMorePortfolios"><v-icon size="25" class="mr-2">fa-plus</v-icon> 더 보기</v-btn>&nbsp; &nbsp;
			<v-btn v-if="this.hidePortfoliosIcon" color="red" dark @click="hidePortfolios"><v-icon size="25" class="mr-2">fa-minus</v-icon> 숨기기</v-btn>
		</v-flex>
  </v-layout>
</template>
<script>
import StudyGroup from '@/components/studygroup/StudyGroup'
import axios from 'axios'

export default {
	name: 'StudyGroupList',
	props: {
		studyGroups: {type: Array}	
	},
	data() {
		return {
			showPortfoliosCount : 0,
			morePortfoliosIcon : true,
			hidePortfoliosIcon : false
		}
	},
	components: {
		StudyGroup
	},
	mounted() {
		this.showPortfoliosCount = (this.studyGroups.length >= 6) ? 6 : this.studyGroups.length
	},
	methods: {
		getPortfolios() {
			this.showPortfoliosCount = (this.studyGroups.length >= 6) ? 6 : this.studyGroups.length
		},
		loadMorePortfolios() {
			let adjustCount = this.showPortfoliosCount + 6 < this.studyGroups.length ? this.showPortfoliosCount + 6 : this.studyGroups.length
			this.showPortfoliosCount = adjustCount
			this.morePortfoliosIcon = adjustCount < this.studyGroups.length ? true : false
			this.hidePortfoliosIcon = true
    	},
		hidePortfolios() {
			let adjustCount2 = this.showPortfoliosCount - 6 > 6 ? this.showPortfoliosCount - 6 : 6
			this.showPortfoliosCount = adjustCount2
			this.hidePortfoliosIcon = adjustCount2 === 6 ? false : true
			this.morePortfoliosIcon = true
		}
	},
	watch: {
		studyGroups() {
			this.getPortfolios()
		}
	}
}
</script>