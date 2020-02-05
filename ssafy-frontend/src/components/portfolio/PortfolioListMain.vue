<template>
  <div style="padding-top: 20px; margin: 0 10%;">
    <vueper-slides
      class="no-shadow"
      :visible-slides="3"
      slide-multiple
      :gap="3"
      :slide-ratio="1 / 3"
      :dragging-distance="200"
      :breakpoints="{
        1200: { visibleSlides: 2, slideMultiple: 2, slideRatio: 1 / 2},
        800: { visibleSlides: 1, slideMultiple: 1, slideRatio: 1 / 1 }
        }">

      <vueper-slide v-for="i in this.showPortfoliosCount" :key="i"
                    class="vueper-img-slide"
                    style="background: #fff;">
        <!-- :image="portfolios[i - 1].img !== 'img' ? portfolios[i - 1].img : 'https://user-images.githubusercontent.com/52685250/73320654-6328f900-4283-11ea-9f91-7bad5e7be4bb.png'" -->
        <template v-slot:content>
          <div class="portfolio-carousel-contents" @click="goPortfolioPage(portfolios[i - 1].portfolioid)">
            <div class="portfolio-carousel-title">{{ portfolios[i - 1].title }}</div>
            <div class="portfolio-carousel-image">
              <img :src="portfolios[i - 1].img !== 'img' ? portfolios[i - 1].img : 'https://user-images.githubusercontent.com/52685250/73320654-6328f900-4283-11ea-9f91-7bad5e7be4bb.png'">
            </div>
            <div class="portfolio-carousel-subinfo">
              <span class="portfolio-carousel-comments">Comments: <span class="comments-count">{{ portfolios[i - 1].commentcount }}</span></span> <!-- 숫자 0 부분은 추후 각 포트폴리오의 댓글 개수 보여주는 코드로 변경 -->
              <span class="portfolio-carousel-etc">
                <div class="portfolio-carousel-date">
                  {{portfolios[i - 1].created_at.slice(2)}}
                </div>
                <div class="portfolio-carousel-wriiter">
                  by {{portfolios[i - 1].username}}
                </div>
                </span>
            </div>
          </div>
          <div class="carousel-img-tag">
          </div>
        </template>

      </vueper-slide>

    </vueper-slides>
  </div>
  <!-- <carousel class="carousel-wrapper" :p er-page="3">
    <slide class="slide-elements" v-for="i in this.showPortfoliosCount" :key='i'>
      <div class="portfolio-slide" style="border: 1px solid black;">
        <v-img class="image-slide"
              :src="portfolios[i - 1].img !== 'img' ? portfolios[i - 1].img : 'https://user-images.githubusercontent.com/52685250/73320654-6328f900-4283-11ea-9f91-7bad5e7be4bb.png'"
              
              :centerMode="true">
        </v-img>
        <div class="slide-contents">
          <div class="slide-title">{{ portfolios[i - 1].title }}</div>
        </div>
      </div>
    </slide>
  </carousel> -->
  <!-- <div class="card-carousel-wrapper">
    <div class="card-carousel--nav__left" @click="moveCarousel(-1)" :disabled="atHeadOfList"></div>
    <div class="card-carousel">
      <div class="card-carousel--overflow-container">
        <div class="card-carousel-cards" :style="{ transform: 'translateX' + '(' + currentOffset + 'px' + ')'}">
          <div class="card-carousel--card" v-for="i in this.showPortfoliosCount" :key="i">
            <v-img :src="portfolios[i - 1].img !== 'img' ? portfolios[i - 1].img : 'https://user-images.githubusercontent.com/52685250/73320654-6328f900-4283-11ea-9f91-7bad5e7be4bb.png'" width="200px" height="200px"></v-img>
            <div class="card-carousel--card--footer">
              <div class="portfolio-title-card">
                <p>{{ portfolios[i - 1].title }}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="card-carousel--nav__right" @click="moveCarousel(1)" :disabled="atEndOfList"></div>
  </div> -->

</template>
<script>
import Portfolio from '@/components/portfolio/Portfolio'
import axios from 'axios'
import { VueperSlides, VueperSlide } from 'vueperslides'
import '../../assets/css/PortfolioListMain.css'

export default {
	name: 'PortfoliosListMain',
	// props: {
	// 	limits: {type: Number, default: 6},
  //   	loadMore: {type: Boolean, default: false}
	// },
	data() {
		return {
      portfolios: [],
      // currentOffset: 0,
      // windowSize: 3,
      // paginationFactor: 220,
      showPortfoliosCount : 0,
		}
	},
	components: {
    Portfolio,
    VueperSlides,
    VueperSlide
	},
	mounted() {
    this.getPortfolios()
  },
	methods: {
		getPortfolios() {
			axios.get('api/portfolios')
				.then(response => {
          this.portfolios = response.data
          this.showPortfoliosCount = response.data.length
				})
    },
    goPortfolioPage(portfolioId) {
      this.$router.push(`/portfolio/${portfolioId}`)
    }
    // moveCarousel(direction) {
    //   // Find a more elegant way to express the :style. consider using props to make it truly generic
    //   if (direction === 1 && !this.atEndOfList) {
    //     this.currentOffset -= this.paginationFactor;
    //   } else if (direction === -1 && !this.atHeadOfList) {
    //     this.currentOffset += this.paginationFactor;
    //   }
    // },
  },
  computed: {
    // atEndOfList() {
    //   return this.currentOffset <= (this.paginationFactor * -1) * (this.portfolios.length - this.windowSize);
    // },
    // atHeadOfList() {
    //   return this.currentOffset === 0;
    // }
  }
}
</script>
<style>

  /* .card-carousel-wrapper {
    display: flex;
    align-items: center;
    justify-content: center;
    margin: 20px 0 40px;
    color: #666a73;
  }

  .card-carousel {
    display: flex;
    justify-content: center;
    width: 640px;
  }
  .card-carousel--overflow-container {
    overflow: hidden;
  }
  .card-carousel--nav__left, .card-carousel--nav__right {
    display: inline-block;
    width: 15px;
    height: 15px;
    padding: 10px;
    box-sizing: border-box;
    border-top: 2px solid #42b883;
    border-right: 2px solid #42b883;
    cursor: pointer;
    margin: 0 20px;
    transition: transform 150ms linear;
  }
  .card-carousel--nav__left[disabled], .card-carousel--nav__right[disabled] {
    opacity: 0.2;
    border-color: black;
  }
  .card-carousel--nav__left {
    transform: rotate(-135deg);
  }
  .card-carousel--nav__left:active {
    transform: rotate(-135deg) scale(0.9);
  }
  .card-carousel--nav__right {
    transform: rotate(45deg);
  }
  .card-carousel--nav__right:active {
    transform: rotate(45deg) scale(0.9);
  }

  .card-carousel-cards {
    display: flex;
    transition: transform 150ms ease-out;
    transform: translatex(0px);
  }
  .card-carousel-cards .card-carousel--card {
    margin: 0 10px;
    cursor: pointer;
    box-shadow: 0 4px 15px 0 rgba(40, 44, 53, 0.06), 0 2px 2px 0 rgba(40, 44, 53, 0.08);
    background-color: #fff;
    border-radius: 4px;
    z-index: 3;
    margin-bottom: 2px;
  }
  .card-carousel-cards .card-carousel--card:first-child {
    margin-left: 0;
  }
  .card-carousel-cards .card-carousel--card:last-child {
    margin-right: 0;
  }
  .card-carousel-cards .card-carousel--card img {
    vertical-align: bottom;
    border-top-left-radius: 4px;
    border-top-right-radius: 4px;
    transition: opacity 150ms linear;
    user-select: none;
  }
  .card-carousel-cards .card-carousel--card img:hover {
    opacity: 0.5;
  }
  .card-carousel-cards .card-carousel--card--footer {
    border-top: 0;
    padding: 7px 15px;
  }
  .card-carousel-cards .card-carousel--card--footer p {
    padding: 3px 0;
    margin: 0;
    margin-bottom: 2px;
    font-size: 19px;
    font-weight: 500;
    color: #2c3e50;
    user-select: none;
  }
  .card-carousel-cards .card-carousel--card--footer p.tag {
    font-size: 11px;
    font-weight: 300;
    padding: 4px;
    background: rgba(40, 44, 53, 0.06);
    display: inline-block;
    position: relative;
    margin-left: 4px;
    color: #666a73;
  }
  .card-carousel-cards .card-carousel--card--footer p.tag:before {
    content: "";
    float: left;
    position: absolute;
    top: 0;
    left: -12px;
    width: 0;
    height: 0;
    border-color: transparent rgba(40, 44, 53, 0.06) transparent transparent;
    border-style: solid;
    border-width: 8px 12px 12px 0;
  }
  .card-carousel-cards .card-carousel--card--footer p.tag.secondary {
    margin-left: 0;
    border-left: 1.45px dashed white;
  }
  .card-carousel-cards .card-carousel--card--footer p.tag.secondary:before {
    display: none !important;
  }
  .card-carousel-cards .card-carousel--card--footer p.tag:after {
    content: "";
    position: absolute;
    top: 8px;
    left: -3px;
    float: left;
    width: 4px;
    height: 4px;
    border-radius: 2px;
    background: white;
    box-shadow: -0px -0px 0px #004977;
  } */
</style>
