<template>
	<v-flex justify-center>
		<div v-if="currentMemberId !== null && auth === 1">
			<div>
				<div class="ssafy-request-auth-title" id="form-title">SSAFY 회원 인증 신청 관리</div>
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
				<!-- <div v-for="ssafyRequest in ssafyRequests" :key="ssafyRequest.id">
					<v-card class="mb-3">
						<div class="ssafyauth-img-wrapper d-inline">
							<div v-if="selectedImage" max-width="85vw">
								<img :src="selectedImage" alt="" width="70%" @click.stop="selectedImage = null">
								<hr>
							</div>
							<v-img :src="ssafyRequest.img" width="200px" @click="zoom(ssafyRequest.img)"></v-img>
						</div>
						<div class="d-inline">
							<v-card-title>
								<h3>{{ssafyRequest.name}}</h3>
							</v-card-title>
							<v-card-title>
								<h3>지역 : {{ssafyRequest.location}}</h3>
							</v-card-title>
							<v-card-subtitle>
								<h3>{{ssafyRequest.unit}}기</h3>
							</v-card-subtitle>
						</div>
						<v-btn class="mr-3" color="blue" @click="approveSsafy(ssafyRequest.memberid)">승인</v-btn>
						<v-btn class="mr-3" color="red" @click="denySsafy(ssafyRequest.memberid)">거절</v-btn>
					</v-card>
				</div> -->
				
				<!-- 이 부분이 게시판 신청 관리 -->
				<!-- <div>
					<div class="ssafy-request-board-title" style="font-size: 35px; font-family: 'Do Hyeon'; text-align: center;">게시판 신청 관리</div>
					<hr class="title-headline">
							<v-data-table
								v-model="selectedBoardRequests"
								:headers="headers"
								:items="boardRequests"
								:items-per-page="10"
								show-select
								class="elevation-1"
								style="width: 90%; margin: 0 auto;"
							></v-data-table>
						<div class="d-flex justify-space-between pt-3" style="margin: 0 5%;">
							<span>👉선택된 항목에 대해 수행할 동작 선택</span>
							<div>
								<v-btn class="ml-3" color="blue" @click="approveBulletin(selectedBoardRequests)"><span style="color: white;">승인</span></v-btn>
								<v-btn class="ml-3" color="red" @click="denyBulletin(selectedBoardRequests)"><span style="color: white;">거절</span></v-btn>
							</div>
						</div>
				</div> -->



					<!-- <div v-for="boardRequest in boardRequests" :key="boardRequest.id">
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
					</div> -->
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
			// zoom(url) {
			// },
			getSsafyRequest() {
				axios.get('api/member/authrequest')
					.then(response => {
						this.ssafyRequests = response.data
					})
			},
			// getBoardRequest() {
			// 	axios.get('api/boardcategory/auth')
			// 		.then(response => {
			// 			this.boardRequests = response.data
			// 		})
			// },
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
			// approveBulletin(reqs) {
			// 	let confirmation = confirm('해당 게시판들을 승인하시겠습니까?')
			// 	if (confirmation) {
			// 		this.selectedBoardRequests.forEach(request => {
			// 			let sendData = {
			// 				id: request.id,
			// 				flag: 1
			// 			}
			// 			axios.put('api/boardcategory/auth', sendData)
			// 				.then(response => {
			// 					this.getBoardRequest()
			// 				})
			// 		})
			// 	}
			// },
			// denyBulletin(reqs) {
			// 	let confirmation = confirm('해당 게시판을 거절하시겠습니까?')
			// 	if (confirmation) {
			// 		this.selectedBoardRequests.forEach(request => {
			// 			let sendData = {
			// 				id: request.id,
			// 				flag: 0
			// 			}
			// 			axios.put('api/boardcategory/auth', sendData)
			// 			.then(response => {
			// 				this.getBoardRequest()
			// 			})
			// 		})
			// 	}
			// }




			// approveBulletin(id) {
			// 	var confirmation = confirm("해당 게시판을 승인하시겠습니까?");
			// 	if (confirmation) {
			// 		var data = {
			// 			id: id,
			// 			flag: 1
			// 		}
			// 		axios.put('api/boardcategory/auth', data)
			// 			.then(response => {
			// 				this.getBoardRequest()
			// 			})
			// 	}
			// },
			// denyBulletin(id) {
			// 	var confirmation = confirm("해당 게시판을 거절하시겠습니까?");
			// 	if (confirmation) {
			// 		var data = {
			// 			id: id,
			// 			flag: 0
			// 		}
			// 		axios.put('api/boardcategory/auth', data)
			// 			.then(response => {
			// 				this.getBoardRequest()
			// 			})
			// 	}
			// },
		}
	}
</script>