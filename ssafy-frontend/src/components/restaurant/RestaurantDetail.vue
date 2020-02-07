<template>
	<!-- <div class="map_wrap">
		<br>
		<div id="map" style="width:100%;height:350px;"></div>
	</div> -->
	<v-container>
		<v-row justify="center">
			<v-col cols="12" sm="8">
				<v-card>
					<v-card-title class="darken-1">
						<div>
							<div>
								<v-avatar class="mr-5 mb-2">
									<img src="" alt="profile">
								</v-avatar><span>curry</span>
							</div>
							<div>
								<span class="headline">제목입니다 <h6>2020-02-07 </h6>
									<v-btn class="mr-1" small color="green">수정</v-btn>
									<v-btn small color="error">삭제</v-btn>
								</span>
							</div>
						</div>
						<v-spacer></v-spacer>
					</v-card-title>
						<div class="map_wrap_detail">
		<br>
		<div id="map" style="width:100%;height:350px;"></div>
	</div>
					<v-list>
						<v-list-item>
							<v-list-item-content>
								본문 내용입니다.
							</v-list-item-content>
						</v-list-item>
						<v-divider></v-divider>
						<v-row justify="center">
							<v-col cols="12" sm="12">
								<boardcomment :postid="3" boardtype="맛집" :comments="comments" />
							</v-col>
						</v-row>
					</v-list>
				</v-card>
			</v-col>
		</v-row>
	</v-container>
</template>

<script>
import boardcomment from '@/components/comment/boardcomment.vue'
	
	export default {
		name: 'RestaurantDetail',
		components: {
			boardcomment
		},
		data() {
			return {
				comments: [],
			}
		},
		mounted() {
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
			geocoder.addressSearch('제주특별자치도 제주시 첨단로 242', function (result, status) {
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
						content: '<div style="width:150px;text-align:center;padding:6px 0;">우리회사</div>'
					});
					infowindow.open(map, marker);
					// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
					map.setCenter(coords);
				}
			});
		}
	}
</script>
<style>
	.map_wrap_detail {
		margin: auto;
		position: relative;
		width: 100%;
		height: 80%;
	}
</style>