<template>
  <div>
    <v-img :src="imgSrc" class="banner-img">
      <v-layout align-center justify-center row fill-height>
        <v-flex text-xs-center>
          <span :style="fontSize()" class="text-shadow font-weight-light">
            <slot name="text"/>
          </span>
        </v-flex>
      </v-layout>
    </v-img>
  </div>
</template>

<script>

export default {
	name: 'ImgBanner',
	props: {
		imgSrc: {type: String},
		text: {type: String}
	},
	methods: {
    fontSize() {
      return 'font-size:4.2vw; line-height:1.2em; text-align:center; color:white; text-shadow:4px 4px 6px black;'
    }
	},
}
</script>
<style>
  .text-shadow {
    text-shadow: 0 0 15px rgb(255,255,255);
  }

  .banner-img {
    height: 300px;
    animation: banner;
    animation-duration: 2.5s;
  }

  @media (min-width: 600px) and (max-width: 959px) {
    .banner-img {
      height: 240px;
    }
  }

  @media (max-width: 599px) {
    .banner-img {
      height: 180px;
    }
  }

  @keyframes banner {
    0% {
      padding-bottom: 50px;
    }
    50% {
      padding-bottom: 0px;
      letter-spacing: 10px;
    }
    100% {
      letter-spacing: auto;
    }
  }
</style>
