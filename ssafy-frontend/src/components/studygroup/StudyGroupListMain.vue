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
        <template v-slot:content>
          <div class="portfolio-carousel-contents" @click="goPortfolioPage(portfolios[i - 1].portfolioid)">
            <div class="portfolio-carousel-title">{{ portfolios[i - 1].title }}</div>
            <div class="portfolio-carousel-image">
              <img :src="portfolios[i - 1].img !== 'img' ? portfolios[i - 1].img : 'https://user-images.githubusercontent.com/52685250/73320654-6328f900-4283-11ea-9f91-7bad5e7be4bb.png'">
            </div>
            <div class="portfolio-carousel-subinfo">
              <span class="portfolio-carousel-comments">Comments: <span class="comments-count">{{ portfolios[i - 1].commentcount }}</span></span>
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
</template>

<script>
import StudyGroup from '@/components/studygroup/StudyGroup'
import axios from 'axios'
import { VueperSlides, VueperSlide } from 'vueperslides'
import '../../assets/css/StudyGroupListMain.css'

export default {
	name: 'StudygroupListMain',
	data() {
		return {
      portfolios: [],
      showPortfoliosCount : 0,
		}
	},
	components: {
    StudyGroup,
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
      this.$router.push(`/studygroup/${portfolioId}`)
    }
  }
}
</script>