<template>
  <div class="github-subinfo d-inline">
    <div class="github-name">
      {{ githubInformation.name }}
    </div>
    <div class="github-subinfo-1">
      <p><i class="far fa-building"></i>{{ githubInformation.company }}</p>
      <p @click="goBlog(githubInformation.blog)"><i class="fas fa-blog"></i><span>{{ githubInformation.blog }}</span></p>
      <p><i class="fas fa-location-arrow"></i>{{ githubInformation.location }}</p>
    </div>
    <div class="github-subinfo-2 d-flex justify-space-between">
      <div class="repository-section text-center">
        <div>Repository Count</div>
        <div @click.stop="reposDialog = true">{{ githubInformation.publicReposCount }}</div>
      </div>
      <div class="followers-section text-center">
        <div>Followers</div>
        <div>{{ githubInformation.followers }}</div>
      </div>
      <div class="following-section text-center">
        <div>Following</div>
        <div>{{ githubInformation.following }}</div>
      </div>
    </div>

    <!-- github public repository list 부분 -->
    <v-dialog v-if="reposDialog" v-model="reposDialog" max-width="1200px">
      <v-card class="pa-3">
        <div class="repository-info">
          <div class="repository-main-title">{{ githubInformation.githubID }}'s Public Repository</div>
          <v-divider></v-divider>
          <div class="repository-information" v-for="repos in this.reposInfo" :key="repos">
            {{ repos.name }}
          </div>
          <!-- {{ this.reposInfo }} -->
        </div>
      </v-card>
    </v-dialog>

  </div>
</template>

<script>
import axios from 'axios'
import '@/assets/css/GithubDetail.css'
import '@/assets/css/GithubDetail_Repos.css'

export default {
	name: 'GithubDetail',
	props: {
		githubInformation: {type: Object}
	},
	data() {
		return {
      reposDialog: false,
      reposInfo: null
    }
  },
  methods: {
    goBlog: function(url) {
      if (url !== 'No Blog') {
        window.open(url)
      }
    }
  },
  mounted() {
    axios.get(`https://api.github.com/users/${this.$store.getters.githubid}/repos`)
      .then(response => {
        const githubReposData = response.data
        let reposArray = []
        githubReposData.forEach(function(data) {
          reposArray.push({
            'name': data.name,
            'reposUrl': data['html_url'],
            'cloneUrl': data['clone_url'],
            'description': data['description'],
            'language': data['language']
          })
        })
        this.reposInfo = reposArray
      })
      .catch(error => console.log(error))
  }
}
</script>
