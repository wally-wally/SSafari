<template>
	<div class="d-flex post-index">
		<div class="main-post-section">
			<div class="post-header">
				<div class="region-checkbox">
					<v-container id="dropdown-region">
						<v-select :items="regions" label="Choose Region" color="#f7b157" target="#dropdown-region" v-model="selectRegion">
							<!-- v-model="region" -->
						</v-select>
					</v-container>
				</div>
				<div class="jmt-post-count">{{ postCnt }}</div>
			</div>
			<router-link v-if="this.$store.state.isLogin" :to="`/board/jmt/create`">
				<v-btn class="mb-3" color="primary">맛집 후기 남기기</v-btn>
			</router-link>
			<v-layout>
				<v-flex>
					<div v-for="restaurant in restaurants" :key="restaurant.id">
						<router-link :to="`${restaurant.id}`">
							<v-card class="mb-3">
								<v-card-title>
									{{restaurant.name }} <h6>{{restaurant.location}}</h6>
								</v-card-title>
								<v-card-subtitle>
									{{restaurant.username}}
								</v-card-subtitle>
								<v-card-subtitle>
									{{restaurant.body}}
								</v-card-subtitle>
							</v-card>
						</router-link>
					</div>
				</v-flex>
			</v-layout>
		</div>
		<SidePost :categoryId="4" :locationId="regionObject[selectRegion]"/>
	</div>
</template>

<script>
	import axios from 'axios'
	import router from '@/router.js'
	import BoardList from '@/components/board/boardCommonForm/BoardList'
	import SidePost from '../SidePost'

	export default {
		name: 'JmtBoard',
		components: {
			BoardList,
			SidePost
		},
		// props: {
		//     category: { type: String },
		// },
		data() {
			return {
				postCnt: 0,
				selectRegion: 'All', // deafult를 로그인한 유저의 지역으로 하고 싶으면 이 부분 수정
				regionObject: {'All': 0, 'Seoul': 1, 'Daejeon': 2, 'Gumi': 3, 'Gwangju': 4, 'etc': 5},
				regions: ['All', 'Seoul', 'Daejeon', 'Gumi', 'Gwangju', 'etc'],
				restaurants: '',
				showCreatePost: 0,
				annoymousStatus: false,
				currentMemberId: null,
				title: '',
				content: '',
				from: null,
				rules: [
					function (value) {
						const extension = value.name.toString().split('.')[1]
						if (!(extension === 'jpg' || extension === 'png' || extension === 'bmp')) {
							alert("Please upload image file.(available jpg, png, bmp flie)")
							document.querySelector('#file').value = '' // 실질적인 경로로 잡힌 파일 삭제
							const filename = document.querySelector('.v-file-input__text')
							filename.innerText = '' // html 문서에서 보이는 파일 이름도 삭제
						}
					}
				]
			}
		},
		beforeRouteEnter(to, from, next) {
			next((vm) => {
				vm.from = from
			})
		},
		mounted() {
			this.currentMemberId = this.$store.state.memberid
			this.getRestaurants()
		},
		methods: {
			getRestaurants() {
				axios.get('api/jmts')
				.then(response => {
								this.restaurants = response.data
								this.postCnt = this.restaurants.length
        })
      },
			onPostCount(value) {
				this.postCnt = value
			},
			hideInitPostForm() {
				this.showCreatePost = 1
			},
			create() {
				let postData = {
					title: this.title,
					body: this.content,
					memberid: this.$store.state.memberid
				}
				axios.post('api/post', postData)
					.then(response => {
						if (response.status === 200) {
							this.$router.push('/board')
						}
					})
			}
		},
		watch: {
			selectRegion: {
				handler() {
					axios.get('api/jmts')
						.then(response => {
							const jmtData = response.data
							this.restaurants = []
							let regionNumber = { 'Seoul': 1, 'Daejeon': 2, 'Gawngju': 4, 'Gumi': 3 , 'etc': 5 }
							if (this.selectRegion !== 'All') {
								let regionRestaurantData = []
								for (let i = 0; i < jmtData.length; i++) {
									if (jmtData[i]['locationid'] === regionNumber[this.selectRegion]) {
										regionRestaurantData.push(jmtData[i])
									}
								}
								this.restaurants = regionRestaurantData
							} else {
								this.restaurants = jmtData
							}
							console.log(this.restaurants)
							this.postCnt = this.restaurants.length
							// this.postCnt = (this.restaurants.length >= 5) ? 5 : this.restaurants.length  
						})
				}
			}
		}
	}
</script>

<style>
	.post-index {
		margin: 0 5%;
	}

	.post-header {
		display: flex;
		justify-content: space-between;
	}

	.jmt-post-count {
		font-size: 1.2em;
		font-weight: bold;
		font-family: 'Noto Sans KR', sans-serif;
		line-height: 4.6;
	}

	.jmt-post-count::after {
		content: '개의 맛집 게시글';
	}

	.main-post-section {
		width: 75%;
	}

	.side-post-section {
		width: 25%;
	}

	@media (max-width: 912px) {
		.main-post-section {
			width: 100%;
		}

		.side-post-section {
			display: none;
		}

		@media (max-width: 600px) {
			.jmt-post-count {
				font-size: 1.1em;
				line-height: 5.2;
			}

			.jmt-post-count::after {
				content: ' Posts';
			}
		}

		@media (max-width: 404px) {
			.jmt-post-count {
				font-size: 0.75em;
				line-height: 7.5;
			}

			.jmt-post-count::after {
				content: ' Posts';
			}
		}
	}

	#dropdown-region {
		padding-left: 0;
	}

	.init {
		background-color: #DDE1E1;
		padding: 10px 10px;
		border: 3px solid grey;
	}

	.create-post-form {
		background-color: #FFFFFF;
		padding: 10px 10px 0;
		border: 3px solid grey;
	}

	.init:hover {
		cursor: text;
	}

	.post-form-title,
	.post-form-contents {
		width: 100%;
		resize: none;
		padding-bottom: 5px;
		border-bottom: 2px solid lightgray;
	}

	.post-form-contents {
		height: 300px;
	}

	.post-form-footer-att-ann,
	.post-form-submit,
	.post-footer-attach,
	.post-attach-file {
		padding: 0;
	}

	.post-footer-annoymous {
		padding: 0 0 0 10px;
	}

	.submit-button {
		float: right;
	}

	.annoyCheck {
		margin: 0;
	}

	.popular-post,
	.best-post {
		margin-bottom: 18px;
		border: 2.5px solid grey;
		background-color: #E8E8E0;
		box-shadow: 3px 3px 5px lightgray;
	}

	.popular-title,
	.best-title {
		font-size: 1.2rem;
		font-weight: bold;
		border-bottom: 1.5px dashed #BEC4CA;
	}

	.popular-element:first-child,
	.popular-element:nth-child(2),
	.best-element:first-child,
	.best-element:nth-child(2),
	.best-element:nth-child(3),
	.best-element:nth-child(4) {
		padding-bottom: 4px;
		border-bottom: 1px solid lightgrey;
		margin-bottom: 8px;
	}

	.popular-element:first-child,
	.best-element:first-child {
		overflow: hidden;
		text-overflow: ellipsis;
		display: -webkit-box;
		-webkit-line-clamp: 1;
		-webkit-box-orient: vertical;
	}

	.popular-post-list p,
	.best-post-list p {
		font-size: 0.8em;
		margin-bottom: 1px;
	}

	.best-element>p:nth-child(3)>i {
		color: crimson;
	}
</style>