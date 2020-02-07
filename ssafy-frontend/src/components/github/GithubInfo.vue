<template>
	<!-- <div class="github-info"> -->
		<div class="github-info-card">
			<div class="github-img">
        <img class="github-img" :src="this.profileImgUrl" alt="github-avatar-img">
      </div>
				<GithubDetail :githubInformation="githubInformation"></GithubDetail>
		</div>
	<!-- </div> -->
</template>

<script>
import axios from 'axios'
import GithubDetail from '@/components/github/GithubDetail'
// import GithubService from '@/services/GithubService'

export default {
	name: 'GithubInfo',
	props: {
		githubid: {type: String}
	},
	data() {
		return {
			profileImgUrl: '',
			githubInformation: null
    }
	},
	components: {
		GithubDetail,
		// GithubService
	},
	mounted() {
		axios.get(`https://api.github.com/users/${this.$store.getters.githubid}`)
			.then(response => {
				const githubData = response.data
				this.profileImgUrl = githubData['avatar_url']
				this.githubInformation = {
					githubID: this.githubid,
					name: githubData.name,
					company: githubData.company === '' ? 'No company' : githubData.company,
					blog: githubData.blog === '' ? 'No blog' : githubData.blog,
					location: githubData.location === '' ? 'No location' : githubData.location,
					publicReposCount: githubData['public_repos'],
					followers: githubData.followers,
					following: githubData.following
				}
				console.log(this.githubInformation)
			})
			.catch(error => {
				console.log(error)
			})
	},
	methods: {
		async getGithubInfo(userName) {
			console.log(userName)
			const response = await GithubService.getInfo(userName)
			console.log(repsonse)
			if(response.status !== 200) {
				return
			}
			this.tempInfo = response.data.name
			this.repositories = response.data.filter(repository => repository.private === false)
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

<style>
	.github-info-card {
		margin: 10px;
		border: 1px solid black;
		overflow: hidden;
	}

	.github-img {
		float: left;
	}

	.github-img > img {
		vertical-align: top;
		padding: 10px;
	}
</style>