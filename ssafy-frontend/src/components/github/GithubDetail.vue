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
    <v-dialog v-if="reposDialog" v-model="reposDialog" persistent max-width="1000px">
      <v-card class="pa-3">
        <div class="repository-info mx-4">
          <div class="repository-main-title d-inline">{{ githubInformation.githubID }}'s Public Repository</div>
          <div class="repository-dialog-close-btn d-inline ml-2"><v-btn color="#f7b157" text @click="reposDialog = false">Close</v-btn></div>
          <hr class="my-5">
          <div class="repository-information" v-for="repos in githubReposInfo" :key="repos.cloneUrl">
            <div class="repository-info-header d-flex justify-space-between">
              <span class="repository-name">{{ repos.name }}</span>
              <span class="repository-stars"><i class="fas fa-star"></i> {{ repos.starCount }}</span>
            </div>
            <div class="repository-info-contents d-flex justify-space-between">
              <div class="repository-info-contents-1">
                <div class="repository-description-title">Description</div>
                <div class="repository-description">{{ repos.description }}</div>
                <div class="repository-clone-title">Clone with HTTPS</div>
                <div class="repository-clone">
                  <span class="repository-clone-url">{{ repos.cloneUrl }}</span>
                  <span class="repository-clone-button"><i class="fas fa-copy"></i></span>
                </div>
              </div>
              <div class="repository-info-contents-2">
                <GithubReposChart v-if="repos.reposChart.datasets.length > 0" :datacollection="repos.reposChart" :width="300" :height="200"/>
                <span v-else>No Language!</span>
              </div>
            </div>
            <v-divider></v-divider>
          </div>
        </div>
      </v-card>
    </v-dialog>

  </div>
</template>

<script>
import { mapGetters } from 'vuex'
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
      reposDialog: false
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
    }
  },
  computed: {
    ...mapGetters([
      'githubReposInfo'
    ])
  }
}
</script>
