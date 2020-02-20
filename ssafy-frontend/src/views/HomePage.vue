<template>
  <div class="homepage-contents" id="main">
    <div class="scroll-alert">
      <div class="container-box">
        <div class="row">
          <div class="col-12 col-md-6 short-page-intro">
            <div class="page-title animated fadeInLeft">SSafari</div>
            <div class="page-introduction-short">
              <p class="pb-2 animated fadeInLeft">SSAFY Community Webpage made by Team A5.</p>
              <p class="pb-5 animated fadeInLeft">A variety of Board, Study Group.</p>
              <button type="button" class="team-intro-button animated jackInTheBox delay-2s" @click="goTeamIntro()">Team A5 Introduction</button>
            </div>
          </div>
          <div class="col-12 col-md-6">
            <div class="img-box">
              <img src="../assets/images/ssafari_logo-01.png" class="ssafari-logo-one animated fadeInRight" alt="ssafari-logo--one-image">
              <img src="../assets/images/blog.png" class="ssafari-logo-three animated zoomIn delay-1s" alt="ssafari-logo-three-image">
              <img src="../assets/images/code.png" class="ssafari-logo-four animated zoomIn delay-1s" alt="ssafari-logo-four-image">
              <img src="../assets/images/collaboration.png" class="ssafari-logo-five animated zoomIn delay-1s" alt="ssafari-logo-five-image">
              <img src="../assets/images/laptop.png" class="ssafari-logo-six animated zoomIn delay-1s" alt="ssafari-logo-six-image">
              <img src="../assets/images/speech_bubble.png" class="ssafari-logo-seven animated zoomIn delay-1s" alt="ssafari-logo-seven-image">
            </div>
          </div>
        </div>
      </div>
    </div>
    <section id="homepage-imgbanner"></section>
    <div class="container-fluid">
      <v-img class="post-image"
             id="post"
             aspect-ratio="2.15">
        <div class="post-contents mt-10">
          <div class="separator"></div>
          <div class="post-title section-title">게시판</div>
          <div data-aos="fade-up" class="post-contents">
            <!-- <BoardListMain></BoardListMain> -->
            <div class="mt-10">
              <AllBoard></AllBoard>
            </div>
          </div>
        </div>
      </v-img>

      <v-img class="portfolio-image"
             id="portfolio"
             aspect-ratio="2.15">
        <div class="portfolio-contents mt-10">
          <div class="separator"></div>
          <div class="portfolio-title section-title">스터디 모임</div>
          <div class="button-box">
            <div class="addButton section-btn" @click="goAddStudyGroup">스터디 모집하기</div>
          </div>
          <div data-aos="fade-up" class="portfolio-carousel mt-10">
            <StudyGroupListMain></StudyGroupListMain>
          </div>
        </div>
      </v-img>  
    </div>
    <div id="mysidenav" class="sidenav">
      <i @click="fnMove('main')" class="fas fa-circle" id="main-icon" style="color: #f7b157;"></i><br>
      <i @click="fnMove('post')" class="fas fa-circle" id="portfolio-icon" style="color: lightgray;"></i><br>
      <i @click="fnMove('portfolio')" class="fas fa-circle" id="post-icon" style="color: lightgray;"></i><br>
    </div>

  </div>
</template>
<script>
import ImgBanner from '../components/etc/ImgBanner'
import StudyGroupListMain from '../components/studygroup/StudyGroupListMain'
import BoardListMain from '../components/board/boardCommonForm/BoardListMain'
import AllBoard from '../components/board/AllBoard'
import '../assets/css/HomePage.css'
import '../assets/css/animate.min.css'
export default {
	name: 'HomePage',
	components: {
		ImgBanner,
		StudyGroupListMain,
		BoardListMain,
    AllBoard
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
    goAddStudyGroup: function() {
      this.$router.push('studygroup/create')
    },
    goAddPost: function() {
      this.$router.push('post/create')
    },
    goTeamIntro() {
      this.$router.push('/teamintro')
    },
    fnMove(seq){
      var offset = $("#" + seq).offset();
      $('html, body').animate({scrollTop : offset.top - 64}, 400);
    },
    sideBarColorChange:function() {
      // screen height variable
      let value = window.scrollY
      section.style.clipPath = 'circle(' + value + 'px at 50% 70%)'
      let viewportHeight = document.documentElement.clientHeight
      let HeaderHeight = document.querySelector('Header').style.height.split('px')[0]
      let sectionUpperHalfHeight = (viewportHeight - HeaderHeight) / 2

      // section top value
      let portfolioSectionTop = document.querySelector('.post-image').getBoundingClientRect().top
      let postSectionTop = document.querySelector('.portfolio-image').getBoundingClientRect().top

      if (postSectionTop - HeaderHeight <= sectionUpperHalfHeight) {
        postIcon.style.color = colorSet[0]
        mainIcon.style.color = portfolioIcon.style.color = colorSet[1]
      } else if (portfolioSectionTop - HeaderHeight <= sectionUpperHalfHeight) {
        portfolioIcon.style.color = colorSet[0]
        mainIcon.style.color = postIcon.style.color = colorSet[1]
      } else {
        mainIcon.style.color = colorSet[0]
        portfolioIcon.style.color = postIcon.style.color = colorSet[1]
      }
    }
	},
}
</script>
<style>
.separator {
	width: 70px;
	height: 1.5px;
	background-color: #222222;
	margin: 25px;
  margin-left: 10%;
	margin-bottom: 10px;
}
.section-title {
    color: #231f20;
    text-align: center;
}
</style>