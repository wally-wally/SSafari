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
        <div @click="showReposMoreInfo">{{ githubInformation.publicReposCount }}</div>
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
    <v-dialog v-if="reposDialog" v-model="reposDialog" max-width="1000px">
      <v-card class="pa-3">
        <div class="repository-info mx-4">
          <div class="repository-main-title">{{ githubInformation.githubID }}'s Public Repository</div>
          <hr class="my-5">
          <div class="repository-information" v-for="repos in this.reposInfo" :key="repos.cloneUrl">
            <div class="repository-info-header d-flex justify-space-between">
              <span class="repository-name">{{ repos.name }}</span>
              <span class="repository-stars"><i class="fas fa-star"></i> {{ repos.starCount }}</span>
            </div>
            <div class="repository-info-contents d-flex justify-space-between">
              <div class="repository-info-contents-1" style="max-width: 60%;">
                <div class="repository-description-title">Description</div>
                <div class="repository-description">{{ repos.description }}</div>
                <div class="repository-clone-title">Clone with HTTPS</div>
                <div class="repository-clone">
                  <span class="repository-clone-url">{{ repos.cloneUrl }}</span>
                  <span class="repository-clone-button"><i class="fas fa-copy"></i></span>
                </div>
              </div>
              <div class="repository-info-contents-2">
                <GithubReposChart v-if="repos.reposChart.datasets.length > 0" :datacollection="repos.reposChart"/>
                <span v-else>No Language!</span>
              </div>
            </div>
            <v-divider></v-divider>
          </div>
          <!-- {{ this.reposInfo }} -->
        </div>
      </v-card>
    </v-dialog>

  </div>
</template>

<script>
import axios from 'axios'
import GithubReposChart from '@/components/github/GithubReposChart'
import '@/assets/css/GithubDetail.css'
import '@/assets/css/GithubDetail_Repos.css'

export default {
  name: 'GithubDetail',
	props: {
		githubInformation: {type: Object}
  },
  components: {
    GithubReposChart
  },
	data() {
		return {
      reposDialog: false,
      reposInfo: null,
    }
  },
  methods: {
    goBlog: function(url) {
      if (url !== 'No Blog') {
        window.open(url)
      }
    },
    showReposMoreInfo: function() {
      this.reposDialog = !(this.respoDialog) 
      let myGithubID = this.githubInformation.githubID
      let githubBaseUrl = `https://api.github.com/users/${myGithubID}`
      axios.get(`${githubBaseUrl}/repos`)
        .then(response => {
          const githubReposData = response.data
          let reposArray = []
          githubReposData.forEach(function(data) {
            let repoInfo = {
              'name': data.name,
              'starCount': data['stargazers_count'],
              'reposUrl': data['html_url'],
              'cloneUrl': data['clone_url'],
              'description': data['description']
            }
            axios.get(`https://api.github.com/repos/${myGithubID}/${repoInfo['name']}/languages`)
              .then(res => {
                // make vue-chartjs
                let langs = res.data
                let chartData = {
                  labels: [],
                  datasets: []
                }
                if (langs.length != {}) {
                  function langSum(obj) {
                    return Object.keys(obj).reduce((sum, key) => sum + obj[key], 0)
                  }
                  let langSumValue = langSum(langs)
                  // let flagIdx = 0
                  for (let lang in langs) {
                    // if (flagIdx === 3) { break }
                    chartData.labels.push(lang)
                    chartData.datasets.push({
                      label: `${lang}`,
                      background: '#f87979',
                      pointBackgroundColor: 'white',
                      borderWidth: 1,
                      pointBorderColor: '#249ebf',
                      data: [`${((langs[lang] / langSumValue) * 100).toFixed(2)}`]
                    })
                    // flagIdx += 1
                    // console.log(flagIdx)
                  }
                  // console.log(chartData)
                }
                  repoInfo['reposChart'] = chartData
              })
            // console.log(repoInfo)
            reposArray.push(repoInfo)
          })
          this.reposInfo = reposArray
        })
        .catch(error => console.log(error))
    }
  }
}
</script>
