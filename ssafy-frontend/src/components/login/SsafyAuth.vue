<template>
	<v-flex justify-center>
		<div v-if="currentMemberId !== null">
			<div v-if="this.$store.state.auth === 4">
				<div id="create-form-title">
					<span id="form-title">SSAFY 인증 페이지</span>
				</div>
				<v-divider></v-divider>
				<div class="upload-example">
					<h3><i class="fas fa-exclamation-circle" style="color:#ff8008"></i>&nbsp;아래와 같이 사진을 업로드해 주세요</h3>
					<img class="upload-img-example" src="../../../public/img/authExample.jpg">
					<h3>싸피 인증 관리자가 3일 이내에 인증 승인/거부를 결정합니다.</h3>
				</div>
				<hr class="title-headline">
				<div class="ml-0">
					<div class="title-top">
						<div class="create-title pt-2" style="font-size:14px">지역</div>
						<div class="region-checkbox">
							<v-container class="py-0" id="dropdown-region">
								<v-select v-model="region" :items="regions" item-text="text" item-value="val" label="Choose Region"
									color="#f7b157" target="#dropdown-region">
								</v-select>
							</v-container>
						</div>
					</div>
					<div class="title-top">
						<div class="create-title pt-2" style="font-size:14px">기수</div>
						<div class="region-checkbox-2">
							<v-container class="py-0" id="dropdown-region">
								<v-select v-model="classSsafy" :items="classes" item-text="text" item-value="val" label="Choose class"
									color="#f7b157" target="#dropdown-region">
								</v-select>
							</v-container>
						</div>
					</div>
				</div>
				<div>
					<v-file-input class="mx-2" :rules="rules" v-model="imgFile" label="File input" id="file" outlined dense
						accept="image/png, image/jpeg, image/bmp" color="#ffc837"></v-file-input>
				</div>
				<div style="text-align:center">
					<v-btn class="ml-3 mr-5" color="#f5f5f5" @click="create">인증 신청</v-btn>
					<v-btn color="f5f5f5" @click="back">돌아가기</v-btn>
				</div>
			</div>
			<div v-else-if="this.$store.state.auth === 3">
				<p class="text-center" style="font-size: 150px;"><i class="far fa-pause-circle"></i></p>
				<p class="text-center pb-6" style="font-size: 28px; font-family: 'Noto Sans KR', sans-serif; font-weight: 600;">SSAFY 인증 대기 상태입니다.</p>
				<p class="text-center" style="font-size: 15px; font-family: 'Noto Sans KR', sans-serif;">관리자의 승인을 받을 때까지 기다려주세요.</p>
				<p class="text-center" style="font-size: 15px; font-family: 'Noto Sans KR', sans-serif;">승인이 거절되면 'SSAFY 인증 페이지'로 바뀝니다.</p>
			</div>
			<div v-else-if="this.$store.state.auth === 2">
				<p class="text-center" style="font-size: 150px;"><i class="far fa-play-circle"></i></p>
				<p class="text-center pb-6" style="font-size: 28px; font-family: 'Noto Sans KR', sans-serif; font-weight: 600;">SSAFY 인증 회원입니다.</p>
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
import '@/assets/css/SsafyAuth.css'

export default {
	name: 'SsafyAuth',
	data() {
		return {
			currentMemberId: '',
			region: '',
			classSsafy: '',
			regions: [{
					text: 'Seoul',
					val: 1
				},
				{
					text: 'Daejeon',
					val: 2
				},
				{
					text: 'Gumi',
					val: 3
				},
				{
					text: 'Gwangju',
					val: 4
				}
			],
			classes: [{
					text: '1기',
					val: 1
				},
				{
					text: '2기',
					val: 2
				},
				{
					text: '3기',
					val: 3
				},
			],
			imgFile: null,
			rules: [
				function (value) {
					var extension = value.name.toString().split('.')[1]
					extension = extension.toLowerCase()
					if (!(extension === 'jpg' || extension === 'png' || extension === 'bmp')) {
						alert("Please upload image file.(available jpg, png, bmp flie)")
						document.querySelector('#file').value = ''
						const filename = document.querySelector('.v-file-input__text')
						filename.innerText = ''
					}
				}
			]
		}
	},
	mounted() {
		this.currentMemberId = this.$store.state.memberid
	},
	methods: {
		back() {
			this.$router.push('/mypage')
		},
		create() {
			if (this.region == null || this.classSsafy == null) {
				alert('지역과 기수를 입력해주세요!')
				return
			}
			const formData = new FormData()
			formData.append('image', this.imgFile)
			axios.post('https://api.imgur.com/3/image', formData, {
					headers: {
						Authorization: 'Client-ID 347364b9fa38df3'
					}
				})
				.then(response => {
					var imgLink = response.data.data.link
					var ssafyAuthData = {
						img: imgLink,
						locationid: this.region,
						unit: this.classSsafy
					}
					var token = this.$store.state.token
					axios.post('api/member/authrequest', ssafyAuthData, {
							headers: {
								'access-token': token
							}
						})
						.then(response => {
							if (response.status === 200) {
								alert('싸피 인증 신청이 완료되었습니다!')
								this.$store.state.auth = 3
								this.$router.push('/mypage')
							}
						})
				})
				.catch(error => {
					console.log(error)
				})
		}
	}
}
</script>