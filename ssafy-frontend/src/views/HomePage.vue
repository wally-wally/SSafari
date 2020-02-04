<template>
  <div class="homepage-contents" id="main">
    <div class="scroll-alert">Learn to share!<br>Share to Learn!</div>
    <section id="homepage-imgbanner"></section>
    <div class="container-fluid">
      <v-img 
              
              class="team-intro-image"
              id="team"
              aspect-ratio="2.15">
        <div class="team-introduction">
          <div class="team-intro-text">
            <div class="intro-text-1">
              <span class="teaminfo-link" @click="goTeamInfo">A5</span> Team
            </div>
            <div class="intro-text-2">
              Introduction
            </div>
          </div>
          <div class="team-intro-images">
            <img data-aos="fade-up" src="https://user-images.githubusercontent.com/52685250/73331867-93cf5980-42a8-11ea-9376-334bfa5fffe3.jpg" id="team-img">
            <img data-aos="fade-up" data-aos-delay="200" src="https://user-images.githubusercontent.com/52685250/73331868-93cf5980-42a8-11ea-8272-cb55019dbd5c.jpg" id="team-img">
            <img data-aos="fade-up" data-aos-delay="400" src="https://user-images.githubusercontent.com/52685250/73331869-93cf5980-42a8-11ea-9169-cc7b4f9aab49.jpg" id="team-img">
          </div>
          <div data-aos="fade-up" data-aos-delay="600" class="feature-on-text">
            We featured on
          </div>
          <div class="language-icon" style="margin: 20px 10%;">
            <img data-aos="fade-up" data-aos-delay="800" src="../assets/langicon/html-5.png" id="lang-icon">
            <img data-aos="fade-up" data-aos-delay="800" src="../assets/langicon/css.png" id="lang-icon">
            <img data-aos="fade-up" data-aos-delay="800" src="../assets/langicon/javascript.png" id="lang-icon">
            <img data-aos="fade-up" data-aos-delay="800" src="../assets/langicon/cpp.png" id="lang-icon">
            <img data-aos="fade-up" data-aos-delay="800" src="../assets/langicon/python.png" id="lang-icon">
            <img data-aos="fade-up" data-aos-delay="800" src="../assets/langicon/react.png" id="lang-icon">
          </div>
        </div>
      </v-img>


      <v-img class="portfolio-image"
             id="portfolio"
             aspect-ratio="2.15">
        <div class="portfolio-contents">
          <div class="portfolio-title">Portfolio</div>
          <div class="button-box">
            <div class="addButton" @click="goAddPortfolio">new portfolio</div>
          </div>
          <div data-aos="fade-up" class="portfolio-carousel">
            <PortfolioListMain></PortfolioListMain>
          </div>
        </div>
      </v-img>

      <v-img class="post-image"
             id="post"
             aspect-ratio="2.15">
        <div class="post-contents">
          <div class="post-title">Post</div>
          <!-- <div class="button-box">
            <div class="addButton" @click="goAddPost">new post</div>
          </div> -->
          <div data-aos="fade-up" class="post-contents">
            <PostListMain></PostListMain>
          </div>
        </div>
      </v-img>
    </div>
    <div id="mysidenav" class="sidenav">
      <i @click="fnMove('main')" class="fas fa-circle" id="main-icon" style="color: #f7b157;"></i><br>
      <i @click="fnMove('team')" class="fas fa-circle" id="team-icon" style="color: lightgray;"></i><br>
      <i @click="fnMove('portfolio')" class="fas fa-circle" id="portfolio-icon" style="color: lightgray;"></i><br>
      <i @click="fnMove('post')" class="fas fa-circle" id="post-icon" style="color: lightgray;"></i><br>
    </div>

  </div>
</template>
<script>
import ImgBanner from '../components/ImgBanner'
import PortfolioListMain from '../components/portfolio/PortfolioListMain'
import PostListMain from '../components/post/PostListMain'
import RepositoryList from '../components/RepositoryList'
import '../assets/css/HomePage.css'
export default {
	name: 'HomePage',
	components: {
		ImgBanner,
		PortfolioListMain,
		PostListMain,
		RepositoryList
  },
  created () {
    window.addEventListener('scroll', this.sideBarColorChange)
    window.addEventListener('resize', this.sideBarColorChange)
  },
  destroyed () {
    window.removeEventListener('scroll', this.sideBarColorChange)
    window.removeEventListener('resize', this.sideBarColorChange)
  },
	methods: {
		getImgBackgroundUrl(img) {
			return require('../../public/img/background/' + img)
    },
    goTeamInfo: function() {
      this.$router.push('/teamintro')
    },
    goAddPortfolio: function() {
      this.$router.push('portfolio/create')
    },
    goAddPost: function() {
      this.$router.push('post/create')
    },
    fnMove(seq){
      var offset = $("#" + seq).offset();
      $('html, body').animate({scrollTop : offset.top - 64}, 400);
    },
    sideBarColorChange:function() {
      // screen height variable
      let value = window.scrollY
      section.style.clipPath = 'circle(' + value + 'px at center)'
      let viewportHeight = document.documentElement.clientHeight
      let HeaderHeight = document.querySelector('Header').style.height.split('px')[0]
      let sectionUpperHalfHeight = (viewportHeight - HeaderHeight) / 2

      // section top value
      let teamIntroSectionTop = document.querySelector('.team-intro-image').getBoundingClientRect().top
      let portfolioSectionTop = document.querySelector('.portfolio-image').getBoundingClientRect().top
      let postSectionTop = document.querySelector('.post-image').getBoundingClientRect().top

      if (postSectionTop - HeaderHeight <= sectionUpperHalfHeight) {
        postIcon.style.color = colorSet[0]
        mainIcon.style.color = teamIcon.style.color = portfolioIcon.style.color = colorSet[1]
      } else if (portfolioSectionTop - HeaderHeight <= sectionUpperHalfHeight) {
        portfolioIcon.style.color = colorSet[0]
        mainIcon.style.color = teamIcon.style.color = postIcon.style.color = colorSet[1]
      } else if (teamIntroSectionTop - HeaderHeight <= sectionUpperHalfHeight) {
        teamIcon.style.color = colorSet[0]
        mainIcon.style.color = portfolioIcon.style.color = postIcon.style.color = colorSet[1]
      } else {
        mainIcon.style.color = colorSet[0]
        teamIcon.style.color = portfolioIcon.style.color = postIcon.style.color = colorSet[1]
      }
    }
	},
}
</script>
