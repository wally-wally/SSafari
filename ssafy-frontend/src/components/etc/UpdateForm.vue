<template>
<v-container style="width: 50%">
	<v-flex justify-center>
		<div v-if="this.$store.state.memberid">
			<div id="create-form-title">
				<span id="form-title" v-if="this.$route.name === 'StudyGroupUpdate'">스터디그룹 모집 수정</span>
				<span id="form-title" v-else>게시글 수정</span>
			</div>
			<hr class="title-headline">
			<div class="title-top">
				<div class="create-title">Title</div>
				<textarea v-model="post.title" id="title-form"></textarea>
			</div>
			<div v-if="this.$route.path === `/studygroup/${post.portfolioid}/update`">

				<label for="capacity">스터디 모집 인원 : </label>
				<input v-model="post.capacity" id="capacity" type="text">명<br>
			<div class="mt-3"><label for="location">스터디 지역 : </label>
			<v-select
					style="display:inline-block;width:30%"
					:items="regions"
					item-text="name"
					item-value="val"
					color="#f7b157"
					target="#dropdown-region"
					v-model="post.locationid"
			></v-select>
			</div>
				<v-row>
					<div class="col-6">
						<v-menu v-model="menu1" :close-on-content-click="false" :nudge-right="40" transition="scale-transition"
							offset-y min-width="290px">
							<template v-slot:activator="{ on }">
								<v-text-field v-model="post.startdate" label="스터디 시작일을 입력해 주세요" prepend-icon="event" readonly v-on="on">
								</v-text-field>
							</template>
							<v-date-picker no-title v-model="post.startdate" @input="menu1=false"></v-date-picker>
						</v-menu>
					</div>
					<div class="col-6">
						<v-menu v-model="menu2" :close-on-content-click="false" :nudge-right="40" transition="scale-transition"
							offset-y min-width="290px">
							<template v-slot:activator="{ on }">
								<v-text-field v-model="post.enddate" label="스터디 종료일을 입력해 주세요" prepend-icon="event" readonly v-on="on">
								</v-text-field>
							</template>
							<v-date-picker no-title v-model="post.enddate" @click.prevent="checkdate" @input="menu2=false">
							</v-date-picker>
						</v-menu>
					</div>
				</v-row>
			</div>
			<textarea v-model="post.body" id="create-content" name="content"></textarea>
			<div v-if="this.$route.name === 'StudyGroupUpdate'">
				<v-file-input :rules="rules" v-model="imgFile" label="File input" id="file" outlined dense
					accept="image/png, image/jpeg, image/bmp"></v-file-input>
			</div>
			<v-checkbox v-model="post.anonymous" label="익명" :value="post.anonymous" class="annoyCheck" />
			<v-btn class="mr-5" color="warning" @click="update">수정</v-btn>
			<v-btn color="error" @click="goBack()">취소</v-btn>
		</div>
		<div v-else>
			<h1 style="text-align:center; color:red">접근 권한이 없습니다!</h1>
			<v-btn :to="{ name: 'home'}" style="margin:0 auto; display:block; width: 300px;" class="red">
				<h3>Home으로 이동</h3>
			</v-btn>
		</div>
	</v-flex>
	</v-container>
</template>

<script>
import axios from 'axios'
import router from '@/router.js'

export default {
	name: 'UpdateForm',
	data() {
		return {
			regions : [
				{ name : 'Seoul' , val : 1},
				{ name : 'Daejeon' , val : 2},
				{ name : 'Gumi' , val : 3},
				{ name : 'Gawngju', val : 4}
			],
			post: {},
			imgFile: null,
			from: null,
			menu1: false,
			menu2: false,
			rules: [
				function (value) {
					const extension = value.name.toString().toLowerCase().split('.')[1]
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
	props: {
		id: {
			type: String
		},
		boardname: {
			type: String
		},
	},
	beforeRouteEnter(to, from, next) {
		next((vm) => {
			vm.from = from;
		});
	},
	mounted() {
		this.getPost()
	},
	methods: {
		getPost() {
			if (this.$route.name === 'StudyGroupUpdate') {
				axios.get(`api/portfolio/${this.id}`)
					.then(response => {
						this.post = response.data.portfolio
						this.post.img = null
					})
			} else {
				axios.get(`api/post/${this.id}`)
					.then(response => {
						this.post = response.data.post
					})
			}
		},
		goBack() {
			const formTitle = document.querySelector('#form-title')
			if (formTitle.innerText === 'Board Form') {
				this.$router.push('/board')
			} else {
				this.$router.push('/studygroup')
			}
		},
		update() {
			if (this.$store.state.memberid === this.post.memberid){
			if (this.$route.name === 'StudyGroupUpdate') {
				if (this.imgFile) {
					const formData = new FormData()
					formData.append('image', this.imgFile)
					axios.post('https://api.imgur.com/3/image', formData, {
							headers: {
								Authorization: `Client-ID ${process.env.VUE_APP_IMGUR_API_KEY}`
							}
						})
						.then(response => {
							var imgLink = response.data.data.link
							this.post['img'] = imgLink
						})
				}
				axios.put('api/portfolio', this.post)
					.then(response => {
						if (response.status === 200) {
							this.$router.push(`/studygroup/${this.post.portfolioid}`)
						}
					}).catch(error => {
						console.log(error)
					})
			} else {
				axios.put('api/post', this.post)
					.then(response => {
						if (response.status === 200) {
							const routerPath = (Number(this.post.categoryid) >= 5) ?  Number(this.post.categoryid): this.$store.state.categorys[this.post.categoryid]
							this.$router.push(`/board/${routerPath}/${this.post.postid}`)
						}
					})
			}
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
	border: 1px solid black;
	box-shadow: 5px 5px 7px grey;
	margin-bottom: 20px;
}
</style>