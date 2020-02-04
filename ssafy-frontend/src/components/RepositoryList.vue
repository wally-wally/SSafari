<template>
  <v-layout column px-4>
    <v-flex v-for="i in this.showReposCount" :key="i">
      <v-divider v-if="i === 1"></v-divider>
      <Repository :repos="repositories[i - 1]"></Repository>
      <v-divider></v-divider>
    </v-flex>
		<v-flex xs12 text-xs-center round my-5>
      <v-btn v-if="this.repositories.length > 4 && this.moreReposIcon" color="info" dark @click="loadMoreRepos"><v-icon size="25" class="mr-2">fa-plus</v-icon> 더 보기</v-btn>&nbsp; &nbsp;
			<v-btn v-if="this.hideReposIcon" color="red" dark @click="hideRepos"><v-icon size="25" class="mr-2">fa-minus</v-icon> 숨기기</v-btn>
		</v-flex>
  </v-layout>
</template>

<script>
import Repository from '@/components/Repository'
import GithubService from '@/services/GithubService'

export default {
	name: 'RepositoryList',
	props: {
		limits: {type: Number},
		loadMore: {type: Boolean, default: false},
		githubid: {type: String}
	},
	data() {
		return {
			repositories: [],
			showReposCount: 6,
			moreReposIcon: true,
			hideReposIcon: false
    }
	},
	components: {
		Repository
	},
	mounted() {
		this.getGithubRepos(this.githubid)
	},
	methods: {
		async getGithubRepos(userName) {
			const response = await GithubService.getRepos(userName)
			if(response.status !== 200) {
				return
			}
			console.log(response)
			this.repositories = response.data
		},
		loadMoreRepos() {
			let adjustCount = this.showReposCount + 6 < this.repositories.length ? this.showReposCount + 6 : this.repositories.length
			this.showReposCount = adjustCount
			this.moreReposIcon = adjustCount < this.repositories.length ? true : false
			this.hideReposIcon = true
		},
		hideRepos() {
			let adjustCount2 = this.showReposCount - 6 > 6 ? this.showReposCount - 6 : 6
			this.showReposCount = adjustCount2
			this.hideReposIcon = adjustCount2 === 6 ? false : true
			this.moreReposIcon = true
		}
	}
}
</script>
