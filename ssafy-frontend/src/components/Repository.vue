<template>
  <div class="py-3">
    <v-layout>

      <v-flex xs8>
        <h2 class="font-weight-regular title">{{repos.name}}</h2>
        <p class="subheading mb-1 grey--text text--darken-1 font-weight-light">{{repos.description}}</p>
      </v-flex>

    </v-layout>
  </div>
</template>

<script>
import GithubService from '@/services/GithubService'

export default {
	name: 'Repository',
	props: {
		repos: {type: null}
	},
	data() {
		return {
			stats: {}
		}
	},
  mounted() {
  },
	methods: {
	}
}
</script>
<style>
.title {
   overflow: hidden;
   text-overflow: ellipsis;
   display: -webkit-box;
   -webkit-line-clamp: 1; /* number of lines to show */
   -webkit-box-orient: vertical;
}
</style>
