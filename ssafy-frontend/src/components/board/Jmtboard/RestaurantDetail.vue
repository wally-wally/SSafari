<template>
	<v-container>
		<v-row v-if="!updateFlag" justify="center">
			<v-col cols="12" sm="8">
			<div class="mb-5 wrap" style="display:block;">
				<h1 style="display: block;letter-spacing: -1px;">{{category.name}}</h1>
				<p style="display: block;">{{category.explanation}}</p>
            </div>
				<v-card>
					<v-card-title class="darken-1">
						<div class="ma-2" justify="center">
							<img class="avatar" :src="restaurant.img" alt="X">
							<div style="display:inline-block">
							<span style="display:block">
								{{ restaurant.username }}
								<sendmessage :username="restaurant.username" :id="restaurant.memberid"/>
                  </span>
                  <small style="display:inline-block">{{ restaurant.created_at }} </small>
                </div>
              </div>
					</v-card-title>
					<div class="ma-2 mb-5">
						<div class="delrevise" v-if="this.$store.state.memberid === restaurant.memberid">
							<v-btn v-if="memberid === restaurant.memberid" class="mr-1" small color="warning" style="white" @click="clickUpdate">수정</v-btn>
							<v-btn v-if="memberid === restaurant.memberid" small color="error" @click="deleteRestaurant">삭제</v-btn>
						</div>
					</div>
					<div class="map_wrap_detail">
						<br>
						<div id="map" style="width:100%;height:350px;"></div>
						<div class="mt-3 mr-5" align="right">
						<h6>{{ restaurant.location }}
						</h6>
						</div>
					</div>
					<v-list>
						<v-list-item>
							<v-list-item-content>
								{{ restaurant.body }}
							</v-list-item-content>
						</v-list-item>
						        <v-spacer />

						<div class="mt-5" align="center"  v-if="this.$store.state.isLogin">
									<v-btn v-if="((!likeFlag) && isLogin)" @click="clickLike" text icon color="#d3d3d3">
										<v-icon>mdi-thumb-up</v-icon>
										<h3>{{ count }}</h3>
									</v-btn>
									<v-btn v-if="((likeFlag) && isLogin)" @click="clickLike" text icon color="deep-orange">
										<v-icon>mdi-thumb-up</v-icon>
										<h3>{{ count }}</h3>
									</v-btn>
									<v-btn v-if="!isLogin" text icon disable>
										<v-icon>mdi-thumb-up</v-icon>
										<h3>{{ count }}</h3>
									</v-btn>
						</div>
						<v-divider></v-divider>
						<v-row justify="center">
							<v-col cols="12" sm="12">
								<boardcomment boardname="jmt" :postid="this.id" categoryid="4" boardtype="post"/>
							</v-col>
						</v-row>
					</v-list>
				</v-card>
			</v-col>
		</v-row>
		<RestaurantCreateFrom v-else :restaurant="this.restaurant"></RestaurantCreateFrom>
	</v-container>
</template>

<script>
  import sendmessage from '../../message/sendmessage'

	import boardcomment from '@/components/comment/boardcomment.vue'
	import RestaurantCreateFrom from './RestaurantCreateForm.vue'
	import axios from 'axios'
	export default {
		name: 'RestaurantDetail',
		components: {
			boardcomment,
			RestaurantCreateFrom,
			sendmessage
		},
		props: {
			id: {
				type: String
			}
		},
		data() {
			return {
				category :{},
				comments: [],
				restaurant: {},
				likeFlag: false,
				count: 0,
				memberid: '',
				categoryid: 4,
				updateFlag: false,
				isLogin: false,
			}
		},
		methods: {
			clickUpdate() {
				this.updateFlag = true
			},
			clickLike() {
				this.likeFlag = !this.likeFlag
				var data = {
					postid: this.restaurant.id,
					memberid: this.memberid,
					categoryid: this.categoryid
				}
				if (this.likeFlag) {
					this.count += 1
					axios.post('api/likepost', data)
						.then(response => {
							console.log(response)
						})
				} else {
					this.count -= 1
					axios.delete('api/likepost', {
							data: data
						})
						.then(response => {
							console.log(response)
						})
				}
			},
			getRestaurant() {
				axios.get(`api/jmt/${this.id}`, { headers: { 'access-token': this.$store.state.token }})
					.then(response => {
						console.log(response)
						this.restaurant = response.data.jmt
						this.count = response.data.count
						this.likeFlag = (response.data.flag == 0) ? false : true
						this.setMap(this.restaurant.location, this.restaurant.name)
					})
			},
			deleteRestaurant() {
				var confirmation = confirm('삭제하시겠습니까?')
				if (confirmation) {
					axios.delete(`api/jmt/${this.id}`)
						.then(response => {
							if (response.status == 200) {
								alert('삭제되었습니다.')
								this.$router.push('/board/jmt/')
							}
						})
				}
			},
			setMap(address, name) {
				var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
					mapOption = {
						center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
						level: 3 // 지도의 확대 레벨
					};
				// 지도를 생성합니다    
				var map = new kakao.maps.Map(mapContainer, mapOption);
				// 주소-좌표 변환 객체를 생성합니다
				var geocoder = new kakao.maps.services.Geocoder();
				// 주소로 좌표를 검색합니다
				geocoder.addressSearch(address, function (result, status) {
					// 정상적으로 검색이 완료됐으면 
					if (status === kakao.maps.services.Status.OK) {
						var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
						// 결과값으로 받은 위치를 마커로 표시합니다
						var marker = new kakao.maps.Marker({
							map: map,
							position: coords
						});
						// 인포윈도우로 장소에 대한 설명을 표시합니다
						var infowindow = new kakao.maps.InfoWindow({
							content: `<div style="width:150px;text-align:center;padding:6px 0;">${ name }</div>`
						});
						infowindow.open(map, marker);
						// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
						map.setCenter(coords);
					}
				});
			}
		},
		mounted() {
			this.getRestaurant()
			this.memberid = this.$store.state.memberid
			var token = this.$store.state.token
			if (token) {
				this.isLogin = true
			}
			axios.get(`api/boardcategory/4`)
			.then(response=> {
				this.category = response.data
			}).catch(error=>{console.log(error)})
			}
	}
</script>
<style>
	.map_wrap_detail {
		margin: auto;
		position: relative;
		width: 100%;
		height: 80%;
		font-style: 'Cute Font', cursive;
	}
</style>