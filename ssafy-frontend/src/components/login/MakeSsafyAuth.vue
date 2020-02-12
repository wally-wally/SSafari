<template>
	<v-flex justify-center>
		<div v-if="currentMemberId !== null && auth === 1">
			<div>
				<div id="create-form-title">
					<span id="form-title">SSAFY인 신청 관리</span>
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
						<v-btn class="mr-3" color="blue" @click="approveSsafy(ssafyRequest.memberid)">승인</v-btn>
						<v-btn class="mr-3" color="red" @click="denySsafy(ssafyRequest.memberid)">거절</v-btn>
					</v-card>
				</div>
				<div>
					<div id="create-form-title">
						<span id="form-title">게시판 신청 관리</span>
						<hr class="title-headline">
					</div>
					<div v-for="boardRequest in boardRequests" :key="boardRequest.id">
						<v-card class="mb-3">
							<v-card-title>
								<h3>게시판 이름: {{boardRequest.name}}</h3>
							</v-card-title>
							<v-card-title>
								<h3>신청자 : {{boardRequest.username}}</h3>
							</v-card-title>
							<v-card-title>
								<h3>게시판 설명 : {{boardRequest.explanation}}</h3>
							</v-card-title>
							<v-btn class="mr-3" color="blue" @click="approveBulletin(boardRequest.id)">승인</v-btn>
							<v-btn class="mr-3" color="red" @click="denyBulletin(boardRequest.id)">거절</v-btn>
						</v-card>
					</div>
				</div>
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
				boardRequests: [],
				selectedImage: null,
				auth: '',
			}
		},
		mounted() {
			this.currentMemberId = this.$store.state.memberid
			this.auth = this.$store.state.auth
			this.getSsafyRequest()
			this.getBoardRequest()
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
						this.ssafyRequests = response.data
					})
			},
			getBoardRequest() {
				axios.get('api/boardcategory/auth')
					.then(response => {
						this.boardRequests = response.data
					})
			},
			approveSsafy(memberid) {
				var confirmation = confirm("해당 사람을 싸피인으로 인증해주시겠습니까?");
				if (confirmation) {
					var data = {
						memberid: memberid,
						flag: 1
					}
					axios.put('api/member/authrequest', data)
						.then(response => {
							this.getSsafyRequest()
						})
				}
			},
			denySsafy(memberid) {
				var confirmation = confirm("해당 사람의 승인 신청을 거절하시겠습니까?");
				if (confirmation) {
					var data = {
						memberid: memberid,
						flag: 0
					}
					axios.put('api/member/authrequest', data)
						.then(response => {
							this.getSsafyRequest()
						})
				}
			},
			approveBulletin(id) {
				var confirmation = confirm("해당 게시판을 승인하시겠습니까?");
				if (confirmation) {
					var data = {
						id: id,
						flag: 1
					}
					axios.put('api/boardcategory/auth', data)
						.then(response => {
							this.getBoardRequest()
						})
				}
			},
			denyBulletin(id) {
				var confirmation = confirm("해당 게시판을 거절하시겠습니까?");
				if (confirmation) {
					var data = {
						id: id,
						flag: 0
					}
					axios.put('api/boardcategory/auth', data)
						.then(response => {
							this.getBoardRequest()
						})
				}
			},
		}
	}
</script>

<style>

</style>