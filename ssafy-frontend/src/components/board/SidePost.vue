<template>
	<div class="side-post-section" style="margin-left: 3%;">
		<div class="popular-post">
			<div class="popular-title pb-2 ma-2">이번주 인기 TOP3</div>
			<div v-for="comment in popularComments" :key="comment.id" class="popular-post-list pt-2 ma-2">
				<router-link :to="`${comment.postid}`">
					<div class="popular-element popular-1 d-block">
						<p>{{comment.title}}</p>
						<p>{{comment.username}}</p>
						<span class="pl-1"><i class="far fa-comments" style="color: skyblue;">{{ comment.comments }}</i></span>
					</div>
				</router-link>
			</div>
		</div>
		<div class="best-post">
			<div class="best-title pb-2 ma-2">이번주 BEST 게시물</div>
			<div v-for="like in popularLikes" :key="like.id" class="best-post-list pt-2 ma-2">
				<router-link :to="`${like.postid}`">
					<div class="best-element best-1">
						<p class="d-block">{{like.title}}</p>
						<p style="display: inline;">{{like.username}}</p>
						<p style="display: inline;"><i class="far fa-heart" style="float: right;">{{like.likes}}</i></p>
					</div>
				</router-link>
			</div>
		</div>
	</div>
</template>

<script>
	import axios from 'axios'

	export default {
		name: 'SidePost',
		props: {
			categoryId: {
				type: String
			},
			locationId: {
				type: String
			}
		},
		data() {
			return {
				popularComments: {},
				popularLikes: {}
			}
		},
		mounted() {
			this.getTopThreeComments()
			this.getTopFiveLikes()
		},
		methods: {
			getTopThreeComments() {
				axios.get(`api/popular/comment`, {
						params: {
							locationid: this.locationId,
							categoryid: this.categoryId
						}
					})
					.then(response => {
						console.log(response.data, 111)
						this.popularComments = response.data
					})
			},
			getTopFiveLikes() {
				axios.get('api/popular/likes', {
						params: {
							locationid: this.locationId,
							categoryid: this.categoryId
						}
					})
					.then(response => {
						console.log(response.data, 222)
						this.popularLikes = response.data
					})
			}
		},
		watch: {
			categoryId: function () {
				this.getTopThreeComments()
				this.getTopFiveLikes()
			},
			locationId: function () {
				this.getTopThreeComments()
				this.getTopFiveLikes()
			}
		},

	}
</script>

<style>

</style>