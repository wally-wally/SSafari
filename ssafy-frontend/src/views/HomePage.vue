<template>
  <div class="homepage-contents" id="main">
    <div class="scroll-alert">Learn to share!<br>Share to Learn!</div>
    <section id="homepage-imgbanner"></section>
    <div class="container-fluid">
      <v-img class="post-image"
             id="post"
             aspect-ratio="2.15">
        <div class="post-contents">
          <div class="post-title">게시판</div>
          <div data-aos="fade-up" class="post-contents">
            <!-- <BoardListMain></BoardListMain> -->
            <div>
              <AllBoard></AllBoard>
            </div>
          </div>
        </div>
      </v-img>

      <v-img class="portfolio-image"
             id="portfolio"
             aspect-ratio="2.15">
        <div class="portfolio-contents">
          <div class="portfolio-title">스터디 모임</div>
          <div class="button-box">
            <div class="addButton" @click="goAddStudyGroup">스터디 모집하기</div>
          </div>
          <div data-aos="fade-up" class="portfolio-carousel">
            <StudyGroupListMain></StudyGroupListMain>
          </div>
        </div>
      </v-img>  
    </div>
    <div id="mysidenav" class="sidenav">
      <i @click="fnMove('main')" class="fas fa-circle" id="main-icon" style="color: #f7b157;"></i><br>
      <i @click="fnMove('portfolio')" class="fas fa-circle" id="portfolio-icon" style="color: lightgray;"></i><br>
      <i @click="fnMove('post')" class="fas fa-circle" id="post-icon" style="color: lightgray;"></i><br>
    </div>

  </div>
</template>
<script>
import ImgBanner from '../components/etc/ImgBanner'
import StudyGroupListMain from '../components/studygroup/StudyGroupListMain'
import BoardListMain from '../components/board/boardCommonForm/BoardListMain'
import AllBoard from '../components/board/AllBoard'
import '../assets/css/HomePage.css'
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
      let portfolioSectionTop = document.querySelector('.portfolio-image').getBoundingClientRect().top
      let postSectionTop = document.querySelector('.post-image').getBoundingClientRect().top

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
