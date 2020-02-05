<template>
  <div>
    <ImgBanner imgSrc="https://user-images.githubusercontent.com/52685250/73508592-c485cf00-441f-11ea-87c5-b6df34601bfa.jpg">
      <div style="line-height:1.2em;font-size:1.2em;" slot="text">스터디 모임</div>
    </ImgBanner>
    <v-container>
      <!-- Portfolio -->
      <router-view>
      </router-view>
    </v-container>
  </div>
</template>

<script>
import ImgBanner from '../components/ImgBanner'
import PortfolioList from '../components/portfolio/PortfolioList'

export default {
	name: 'PortfolioPage',
	components: {
		ImgBanner,
		PortfolioList,
	},
}
</script>
