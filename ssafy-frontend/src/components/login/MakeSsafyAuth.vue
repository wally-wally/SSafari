<template>
	<v-flex justify-center>
		<div v-if="currentMemberId !== null">
			<div id="create-form-title">
				<span id="form-title">SSAFY 인증 관리</span>
			</div>
			<hr class="title-headline">
			<div v-for="ssafyRequest in ssafyRequests" :key="ssafyRequest.id">
				<v-card class="mb-3">
					<div v-if="selectedImage" max-width="85vw">
						<img :src="selectedImage" alt="" width="80%" @click.stop="selectedImage = null">
						<hr>
					</div>
					<v-img :src="ssafyRequest.img" width="300px" @click="zoom(ssafyRequest.img)"></v-img>
					<v-card-title>
						<h3>{{ssafyRequest.name}}</h3>
					</v-card-title>
					<v-card-title>
						<h3>지역 : {{ssafyRequest.location}}</h3>
					</v-card-title>
					<v-card-subtitle>
						<h3>{{ssafyRequest.unit}}기</h3>
					</v-card-subtitle>
					<v-btn class="mr-5" color="blue" @click="makeCompleted">승인</v-btn>
					<v-btn class="mr-5" color="red" @click="makeDenied">거절</v-btn>
				</v-card>
			</div>
			<v-btn color="error" @click="back">돌아가기</v-btn>
		</div>

		<div v-else class="mt-10">
			<h1 style="text-align:center; color:red">접근 권한이 없습니다</h1>
			<v-btn :to="{ name: 'home'}" style="margin:0 auto; display:block; width: 300px;" class="red white--text">
				<h3>Home으로 이동</h3>
			</v-btn>
		</div>
	</v-flex>
</template>

<script>
	import axios from 'axios'
	export default {
		name: 'MakeSsafyAuth',
		data() {
			return {
				currentMemberId: '',
				ssafyRequests: [],
				selectedImage: null,
			}
		},
		mounted() {
			this.currentMemberId = this.$store.state.memberid
			this.getSsafyRequest()
		},
		methods: {
			back() {
				this.$router.push('/mypage')
			},
			zoom(url) {
				this.selectedImage = url;
			},
			getSsafyRequest() {
				axios.get('api/member/authrequest')
					.then(response => {
						console.log('MakeSsafyAuth: ', response.data)
						this.ssafyRequests = response.data
					})
			},
			makeCompleted() {
				var confirmation = confirm("해당 사람을 싸피인으로 인증해주시겠습니까?");
				if (confirmation) {

				}
			},
			makeDenied() {
				var confirmation = confirm("해당 사람의 승인 신청을 거절하시겠습니까?");
			}
		}
	}
</script>

<style>

</style>