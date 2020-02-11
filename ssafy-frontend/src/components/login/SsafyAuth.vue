<template>
	<v-flex justify-center>
		<div v-if="currentMemberId !== null">
			<div id="create-form-title">
				<span id="form-title">SSAFY 인증 페이지</span>
			</div>
			<hr class="title-headline">
			<div class="title-top">
				<div class="create-title">지역</div>
				<div class="region-checkbox">
					<v-container id="dropdown-region">
						<v-select v-model="region" :items="regions" item-text="text" item-value="val" label="Choose Region" color="#f7b157" target="#dropdown-region">
						</v-select>
					</v-container>
				</div>
			</div>
			<div class="title-top">
				<div class="create-title">기수</div>
				<div class="region-checkbox">
					<v-container id="dropdown-region">
						<v-select v-model="classSsafy" :items="classes" item-text="text" item-value="val" label="Choose class" color="#f7b157"
							target="#dropdown-region">
						</v-select>
					</v-container>
				</div>
			</div>
			<v-file-input :rules="rules" v-model="imgFile" label="File input" id="file" outlined dense
				accept="image/png, image/jpeg, image/bmp"></v-file-input>
			<v-btn class="mr-5" color="#f7b157" @click="create">인증 신청</v-btn>
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
		name: 'SsafyAuth',
		data() {
			return {
				currentMemberId: '',
				region: '',
				classSsafy: '',
				regions: [
					{ text: 'Seoul', val: 1},
					{ text: 'Daejeon', val: 2},
					{ text: 'Gumi', val: 3},
					{ text: 'Gwangju', val: 4}
					],
				classes: [
					{ text: '1기', val: 1},
					{ text: '2기', val: 2},
					{ text: '3기', val: 3},
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
				if(this.region == null || this.classSsafy == null) {
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
						console.log(response.data.data)
						var imgLink = response.data.data.link
						var ssafyAuthData = {
							img: imgLink,
							locationid: this.region,
							unit: this.classSsafy
						}
						var token = this.$store.state.token
						console.log(token)
						console.log('----------------------')
						axios.post('api/member/authrequest', ssafyAuthData, {headers: {'access-token': token}})
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

<style>

</style>