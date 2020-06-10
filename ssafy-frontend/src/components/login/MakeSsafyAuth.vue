<template>
	<v-flex justify-center>
		<div v-if="currentMemberId !== null && auth === 1">
			<div>
				<div class="ssafy-request-auth-title mt-2 mb-5" id="form-title">SSAFY 회원 인증 신청 관리</div>
				<hr class="title-headline">
				<div v-for="ssafyRequest in ssafyRequests" :key="ssafyRequest.id" style="width: 90%; margin: 0 auto;">
					<div class="ssafy-request-img">
						<v-dialog v-model="showRequestImage" max-width="1000px">
							<v-card>
								<p class="text-center" style="padding-top: 30px; padding-bottom: 6px;">
									<img :src="selectedImage" alt="" width="85%" >
								</p>
								<v-card-actions>
									<v-spacer></v-spacer>
									<v-btn color="#f7b157" text @click="showRequestImage = false">CLOSE</v-btn>
								</v-card-actions>
							</v-card>
						</v-dialog>
						<hr>
						<v-img :src="ssafyRequest.img" width="200px" @click="ssafyReqDialog(ssafyRequest.img)"></v-img>
					</div>
					<div class="ssafy-request-info">
						<p>{{ ssafyRequest.name }}</p>
						<p>{{ ssafyRequest.location }} | {{ ssafyRequest.unit }}기</p>
					</div>
					<div class="ssafy-request-btn">
						<v-btn class="mr-3 d-block mt-4 mb-2" color="blue" @click="approveSsafy(ssafyRequest.memberid)"><span style="color: white;">승인</span></v-btn>
						<v-btn class="mr-3" color="red" @click="denySsafy(ssafyRequest.memberid)"><span style="color: white;">거절</span></v-btn>
					</div>
					<v-divider class="divide-line"></v-divider>
				</div>
			</div>
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
import '@/assets/css/MakeSsafyAuth.css'

export default {
	name: 'MakeSsafyAuth',
	data() {
		return {
			currentMemberId: '',
			ssafyRequests: [],
			boardRequests: [],
			selectedImage: null,
			showRequestImage: false,
			auth: '',
			headers: [
				{
					text: '게시판 이름',
					align: 'left',
					value: 'name'
				},
				{ text: '게시판 설명', value: 'explanation' },
				{ text: '신청자', value: 'username' }
			],
			selectedBoardRequests: []
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
		ssafyReqDialog(imgUrl) {
			this.showRequestImage = true
			this.selectedImage = imgUrl
		},
		getSsafyRequest() {
			axios.get('api/member/authrequest')
				.then(response => {
					this.ssafyRequests = response.data
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
		}
	}
}
</script>