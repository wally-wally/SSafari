<template>
  <div>
    <ImgBanner imgSrc="https://user-images.githubusercontent.com/52685250/73508800-702f1f00-4420-11ea-9d9f-bf5980ce224a.jpg">
      <div style="line-height:1.2em;font-size:1.2em;" slot="text">Post</div>
    </ImgBanner>
    <v-container>
      <!-- Post -->
      <router-view>
      </router-view>
    </v-container>
  </div>
</template>

<script>
import ImgBanner from '../components/ImgBanner'


export default {
	name: 'PostPage',
	components: {
		ImgBanner,
	}
}
</script>
