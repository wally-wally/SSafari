<template>
	<v-flex justify-center>
		<div v-if="this.$store.state.memberid">
			<div id="create-form-title">
				<span id="form-title">스터디 등록 페이지</span>
			</div>
			<hr class="title-headline">
			<div class="title-top mb-5">
				<div class="create-title">Title</div>
				<textarea v-model="title" id="title-form" style="border: 1px solid lightgray; border-radius: 6px; box-shadow: 0px 0px;"></textarea>
			</div>
			<div class="mt-5">
			<label for="capacity">스터디 모집 인원 : </label>
			<input v-model="capacity" id="capacity" type="number" min="1" style="border: 1px solid lightgray; border-radius: 6px;" class="mr-1">명<br>
			</div>	
			<div class="mt-3"><label for="location">스터디 지역 : </label>
			<v-select
				style="display:inline-block;width:30%"
				:items="regions"
				item-text="name"
				item-value="val"
				color="#f7b157"
				target="#dropdown-region"
				v-model="location"
			>
			</v-select>
			</div>
			<v-row>
				<div class="col-6">
					<v-menu v-model="menu1" :close-on-content-click="false" :nudge-right="40" transition="scale-transition"
						offset-y min-width="290px">
						<template v-slot:activator="{ on }">
							<v-text-field color="#f7b157" v-model="startdate" label="스터디 시작일을 입력해 주세요" prepend-icon="event" readonly v-on="on">
							</v-text-field>
						</template>
						<v-date-picker no-title color="#f7b157" v-model="startdate" @input="menu1=false"></v-date-picker>
					</v-menu>
				</div>
				<div class="col-6">
					<v-menu v-model="menu2" :close-on-content-click="false" :nudge-right="40" transition="scale-transition"
						offset-y min-width="290px">
						<template v-slot:activator="{ on }">
							<v-text-field color="#f7b157" v-model="enddate" label="스터디 종료일을 입력해 주세요" prepend-icon="event" readonly v-on="on">
							</v-text-field>
						</template>
						<v-date-picker no-title color="#f7b157" v-model="enddate" :min="startdate" @click.prevent="checkdate" @input="menu2=false"></v-date-picker>
					</v-menu>
				</div>
			</v-row>

			<textarea v-model="content" id="create-content" name="content" style="box-shadow: 0px 0px; border: 1px solid lightgray; border-radius: 6px;"></textarea>
			<v-file-input :rules="rules" v-model="imgFile" label="스터디를 설명하는 이미지를 업로드해주세요(필수)" id="file" outlined dense
				accept="image/png, image/jpeg, image/bmp"></v-file-input>
			<v-btn class="mr-5" color="#f7b157" @click="create">작성</v-btn>
			<v-btn color="error" @click="goBack()">취소</v-btn>
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
import router from '@/router.js'

export default {
	data() {
		return {
			regions: [
				{ name : 'Seoul' , val : 1},
				{ name : 'Daejeon' , val : 2},
				{ name : 'Gumi' , val : 3},
				{ name : 'Gawngju', val : 4}
			],
			currentMemberId: null,
			title: '',
			content: '',
			capacity: '',
			location: '',
			menu1: false,
			menu2: false,
			startdate: new Date().toISOString().substr(0, 10),
			enddate: '',
			imgFile: null,
			from: null,
			message : '',
			rules: [
				function (value) {
					if (value) {
						const extension = value.name.toString().split('.')[1]
						extension = extension.toLowerCase()
						if (!(extension === 'jpg' || extension === 'png' || extension === 'bmp')) {
							alert("Please upload image file.(available jpg, png, bmp flie)")
							document.querySelector('#file').value = ''
							const filename = document.querySelector('.v-file-input__text')
							filename.innerText = ''
						}
						return true
					} else {
						return ''
					}
				}
			]
		}
	},
	beforeRouteEnter(to, from, next) {
		next((vm) => {
			vm.from = from;
		});
	},
	mounted() {
		this.currentMemberId = this.$store.state.memberid
	},
	methods: {
		goBack() {
			const formTitle = document.querySelector('#form-title')
			this.$router.push('/studygroup')
		},
		create() {
			const formData = new FormData()
			if (!this.location){
				this.message = '지역을 설정해 주세요'
			} else if (!this.enddate) {
				this.message = '마감일을 설정해 주세요'
			} else if (!this.imgFile) {
				this.message = '이미지를 필수로 입력해주세요'
			} else {
			formData.append('image', this.imgFile)
			axios.post('https://api.imgur.com/3/image', formData, {
					headers: {
						Authorization: `Client-ID ${process.env.VUE_APP_IMGUR_APK_KEY}`
					}
				})
				.then(response => {
					var imgLink = response.data.data.link
					var portfolioData = {
						title: this.title,
						body: this.content,
						memberid: this.$store.state.memberid,
						img: imgLink,
						capacity: this.capacity,
						locationid: this.location,
						startdate: this.startdate,
						enddate: this.enddate,
					}
					axios.post('api/portfolio', portfolioData)
						.then(response => {
							if (response.status === 200) {
								this.$router.push('/studygroup')
							}
						})
				})
				.catch(error => {
					console.log(error)
				})
			}
		}
	}
}
</script>

<style>
#create-form-title {
	font-size: 2em;
	font-family: 'Do Hyeon', sans-serif;
	text-align: center;
	padding-top: 6px;
	padding-bottom: 10px;
}

.title-headline {
	border: none;
	border: 1px dashed lightgrey;
	margin-bottom: 20px;
}

.title-top {
	display: flex;
	margin-bottom: 20px;
}

.create-title {
	font-size: 20px;
	font-weight: bold;
	text-align: center;
	width: 15%;
}

@media (min-width: 555px) and (max-width: 880px) {
	.create-title {
		width: 10%;
	}
}

@media (min-width: 881px) {
	.create-title {
		width: 6%;
	}
}

#title-form {
	border: 1px solid black;
	width: 100%;
	height: 30px;
	resize: none;
	box-shadow: 5px 5px 7px grey;
}

#create-content {
	width: 100%;
	height: 300px;
	resize: none;
	border: 1px solid lightgray;
	box-shadow: 5px 5px 7px grey;
	margin-bottom: 20px;
}
</style>