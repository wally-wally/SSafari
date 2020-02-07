<template>
  <div class="github-subinfo">
    <div class="github-name">
      {{ githubInformation.name }}
    </div>
    <div class="github-subinfo-1">
      <p><i class="far fa-building"></i>{{ githubInformation.company }}</p>
      <p @click="goBlog(githubInformation.blog)"><i class="fas fa-blog"></i>{{ githubInformation.blog }}</p>
      <p><i class="fas fa-location-arrow"></i>{{ githubInformation.location }}</p>
    </div>
    <div class="github-subinfo-2 d-flex justify-space-between">
      <div class="repository-section text-center">
        <div>Repository Count</div>
        <div>{{ githubInformation.publicReposCount }}</div>
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
  </div>
</template>

<script>

import '@/assets/css/GithubDetail.css'
export default {
	name: 'GithubDetail',
	props: {
		githubInformation: {type: Object}
	},
	data() {
		return {}
  },
  methods: {
    goBlog: function(url) {
      window.open(url)
    }
  }
}
</script>
