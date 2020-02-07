<template>
<v-flex justify-center>
	<h1 style="text-align:center">카페/식당을 검색해 주세요</h1>
	<div class="map_wrap">
    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
    <div id="menu_wrap" class="bg_white">
      <div class="option">
        <div>
          <!-- <form onsubmit="searchPlaces(); return false;"> -->
          카페 or 식당	 : <input type="text" v-model="keyword" id="keyword" size="15"> 
          <button type="submit" @click="createMap()">검색하기</button>
          <!-- </form> -->
        </div>
      </div>
      <hr>
      <ul id="placesList"></ul>
      <div id="pagination"></div>
    </div>
	</div>
	<div class="center" justify-center>
  	<div class="title-top mt-5">
    	<!-- <div class="title-form"> -->
    	<div class="create-title">Title</div>
    	<textarea v-model="title" class="title-form"></textarea>
  	</div>
		<div class="title-top">
			<div class="create-title">location</div>
    	<textarea v-model="location" class="title-form" disabled></textarea>
		</div>
		<div class="title-top">
			<div class="create-title">Content</div>
			<textarea v-model="content" id="create-content" name="content"></textarea>
		</div>
		<!-- <v-btn class="mr-5" color="#f7b157" @click="create">작성</v-btn> -->
    <!-- <v-btn color="error" @click="goBack()">취소</v-btn> -->
	</div>
</v-flex>
</template>

<script>
export default {
		name: 'RestaurantCreateForm',
		components: {

		},
		data() {
			return {
				keyword: '',
				daejeon: [36.355287, 127.29831],
				markers: '',
				mapContainer: '',
				map: '',
				ps: '',
				infowindow: '',
				mapOptions: '',
				dataArray: '',
				index: '',
				title: '',
				location: '',
				content: '',
			}
		},
		mounted() {
				// this.mapContainer = document.getElementById('map');
        // this.mapOptions = {
        //     center: new daum.maps.LatLng(this.daejeon[0], this.daejeon[1]),
        //     level: 5 //지도의 레벨(확대, 축소 정도)
        // };
				// this.map = new daum.maps.Map(this.mapContainer, this.mapOptions);
		},
		methods: {
				createMap() {
						this.markers = [];
						this.mapContainer = document.getElementById('map') // 지도를 표시할 div 
    				this.mapOption = {
        				center: new kakao.maps.LatLng(this.daejeon[0], this.daejeon[1]), // 지도의 중심좌표
        				level: 3 // 지도의 확대 레벨
						};
						// 지도를 생성합니다    
						this.map = new kakao.maps.Map(this.mapContainer, this.mapOption); 
						// 장소 검색 객체를 생성합니다
						this.ps = new kakao.maps.services.Places();  
						// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
						this.infowindow = new kakao.maps.InfoWindow({zIndex:1});
						// 키워드로 장소를 검색합니다
						this.searchPlaces();
				},
				searchPlaces() {
						var keyword = document.getElementById('keyword').value;

    				if (!keyword.replace(/^\s+|\s+$/g, '')) {
								alert('키워드를 입력해주세요!');
								return false;
    				}
    				// 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
    				this.ps.keywordSearch(keyword, this.placesSearchCB); 
				},
				// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
				placesSearchCB(data, status, pagination) {
    				if (status === kakao.maps.services.Status.OK) {
							data = data.filter(item => item.category_group_code === 'CE7' || item.category_group_code === 'FD6')
								console.log(data)
								if(data.length === 0){
									alert('검색 결과가 존재하지 않습니다!!!');
									return;
								}
        				// 정상적으로 검색이 완료됐으면
								// 검색 목록과 마커를 표출합니다
								this.dataArray = data
								console.log(this.data)
        				this.displayPlaces(data);

        				// 페이지 번호를 표출합니다
        				this.displayPagination(pagination);
    				} else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        				alert('검색 결과가 존재하지 않습니다.');
        				return;
    				} else if (status === kakao.maps.services.Status.ERROR) {
        				alert('검색 결과 중 오류가 발생했습니다.');
        				return;
    				}
				},
				// 검색 결과 목록과 마커를 표출하는 함수입니다
				displayPlaces(places) {
    				var listEl = document.getElementById('placesList'), 
    				menuEl = document.getElementById('menu_wrap'),
    				fragment = document.createDocumentFragment(), 
    				bounds = new kakao.maps.LatLngBounds(), 
    				listStr = '';
    
    				// 검색 결과 목록에 추가된 항목들을 제거합니다
    				this.removeAllChildNods(listEl);

    				// 지도에 표시되고 있는 마커를 제거합니다
    				this.removeMarker();
    
    				for ( var i=0; i<places.length; i++ ) {
								// 마커를 생성하고 지도에 표시합니다
        				var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
            				marker = this.addMarker(placePosition, i), 
            				itemEl = this.getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

        				// 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        				// LatLngBounds 객체에 좌표를 추가합니다
        				bounds.extend(placePosition);

								// 마커와 검색결과 항목에 mouseover 했을때
								// 해당 장소에 인포윈도우에 장소명을 표시합니다
								// mouseout 했을 때는 인포윈도우를 닫습니다
        				((marker, title) => {
            				kakao.maps.event.addListener(marker, 'mouseover', () => {
                				this.displayInfowindow(marker, title);
            				});
            				kakao.maps.event.addListener(marker, 'mouseout', () => {
                				this.infowindow.close();
            				});
            				itemEl.onmouseover = () => {
                				this.displayInfowindow(marker, title);
            				};
            				itemEl.onmouseout =  () => {
                				this.infowindow.close();
            				};
        				})(marker, places[i].place_name);
        				fragment.appendChild(itemEl);
    				}
    				// 검색결과 항목들을 검색결과 목록 Elemnet에 추가합니다
    				listEl.appendChild(fragment);
    				menuEl.scrollTop = 0;

    				// 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
    				this.map.setBounds(bounds);
				},
				// 검색결과 항목을 Element로 반환하는 함수입니다
				getListItem(index, places) {
    				var el = document.createElement('li'),
    				itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
                '<div class="infos">' +
                '   <h5>' + places.place_name + '</h5>';

    				if (places.road_address_name) {
        				itemStr += '    <span>' + places.road_address_name + '</span>' +
                    '   <span class="jibun gray">' +  places.address_name  + '</span>';
    				} else {
        				itemStr += '    <span>' +  places.address_name  + '</span>'; 
    				}
                 
      			itemStr += '  <span class="tel">' + places.phone  + '</span>' +
                '</div>';           

    				el.innerHTML = itemStr;
						el.className = 'item';

						el.addEventListener('click', () => {
								this.index = index
								this.location = this.dataArray[this.index].place_name
								
						});				

    				return el;
				},
				// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
				addMarker(position, idx, title) {
    				var imageSrc = 'http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
        		imageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기
        		imgOptions =  {
            		spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
            		spriteOrigin : new kakao.maps.Point(0, (idx*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
            		offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        		},
        		markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
            		marker = new kakao.maps.Marker({
            		position: position, // 마커의 위치
								image: markerImage,
								clickable: true,
						});

						// 인포윈도우를 생성합니다
						kakao.maps.event.addListener(marker, 'click', () => {
								this.index = idx
								this.location = this.dataArray[this.index].place_name
						});						
						
						marker.setMap(this.map); // 지도 위에 마커를 표출합니다
    				this.markers.push(marker);  // 배열에 생성된 마커를 추가합니다

    				return marker;
				},
				// 지도 위에 표시되고 있는 마커를 모두 제거합니다
				removeMarker() {
    				for ( var i = 0; i < this.markers.length; i++ ) {
        				this.markers[i].setMap(null);
    				}   
    				this.markers = [];
				},
				// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
				displayPagination(pagination) {
    				var paginationEl = document.getElementById('pagination'),
        		fragment = document.createDocumentFragment(),
        		i; 

    				// 기존에 추가된 페이지번호를 삭제합니다
    				while (paginationEl.hasChildNodes()) {
        				paginationEl.removeChild (paginationEl.lastChild);
    				}

    				for (i=1; i<=pagination.last; i++) {
        				var el = document.createElement('a');
        				el.href = "#";
        				el.innerHTML = i;

        				if (i===pagination.current) {
            				el.className = 'on';
        				} else {
            				el.onclick = (function(i) {
                				return function() {
                    				pagination.gotoPage(i);
                				}
            				})(i);
        				}

        				fragment.appendChild(el);
    				}
    				paginationEl.appendChild(fragment);
				},
				// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
				// 인포윈도우에 장소명을 표시합니다
				displayInfowindow(marker, title) {
    				var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

						this.infowindow.setContent(content);
						this.infowindow.open(this.map, marker);
				},
				// 검색결과 목록의 자식 Element를 제거하는 함수입니다
				removeAllChildNods(el) {   
    				while (el.hasChildNodes()) {
        				el.removeChild (el.lastChild);
    				}
				},
		},
}
</script>

<style>
.map_wrap, .map_wrap * {margin:0;padding:0;font-family:'Malgun Gothic',dotum,'돋움',sans-serif;font-size:12px;}
.map_wrap a, .map_wrap a:hover, .map_wrap a:active{color:#000;text-decoration: none;}
.map_wrap {margin: auto;position:relative;width:70%;height:500px;}
#menu_wrap {position:absolute;top:0;left:0;bottom:0;width:45%;margin:10px 0 30px 10px;padding:5px;overflow-y:auto;background:rgba(255, 255, 255, 0.7);z-index: 1;font-size:12px;border-radius: 10px;}
.bg_white {background:#fff;}
#menu_wrap hr {display: block; height: 1px;border: 0; border-top: 2px solid #5F5F5F;margin:3px 0;}
#menu_wrap .option{text-align: center;}
#menu_wrap .option p {margin:10px 0;}  
#menu_wrap .option button {margin-left:5px;}
#placesList li {list-style: none;}
#placesList .item {position:relative;border-bottom:1px solid #888;overflow: hidden;cursor: pointer;min-height: 65px;}
#placesList .item span {display: block;margin-top:4px;}
#placesList .item h5, #placesList .item .infos {text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
#placesList .item .infos{padding:10px 0 10px 55px;}
#placesList .infos .gray {color:#8a8a8a;}
#placesList .infos .jibun {padding-left:26px;background:url(http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png) no-repeat;}
#placesList .infos .tel {color:#009900;}
#placesList .item .markerbg {float:left;position:absolute;width:36px; height:37px;margin:10px 0 0 10px;background:url(http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png) no-repeat;}
#placesList .item .marker_1 {background-position: 0 -10px;}
#placesList .item .marker_2 {background-position: 0 -56px;}
#placesList .item .marker_3 {background-position: 0 -102px}
#placesList .item .marker_4 {background-position: 0 -148px;}
#placesList .item .marker_5 {background-position: 0 -194px;}
#placesList .item .marker_6 {background-position: 0 -240px;}
#placesList .item .marker_7 {background-position: 0 -286px;}
#placesList .item .marker_8 {background-position: 0 -332px;}
#placesList .item .marker_9 {background-position: 0 -378px;}
#placesList .item .marker_10 {background-position: 0 -423px;}
#placesList .item .marker_11 {background-position: 0 -470px;}
#placesList .item .marker_12 {background-position: 0 -516px;}
#placesList .item .marker_13 {background-position: 0 -562px;}
#placesList .item .marker_14 {background-position: 0 -608px;}
#placesList .item .marker_15 {background-position: 0 -654px;}
#pagination {margin:10px auto;text-align: center;}
#pagination a {display:inline-block;margin-right:10px;}
#pagination .on {font-weight: bold; cursor: default;color:#777;}
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
.title-form {
    border: 1px solid black;
    width: 100%;
    height: 30px;
    resize: none;
    box-shadow: 5px 5px 7px grey;
}
#content-form {
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
.center {
  margin: auto;
  width: 50%;
  padding: 10px;
}
</style>