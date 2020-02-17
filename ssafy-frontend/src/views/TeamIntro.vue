<template>
	<div>
		<div class="team-introduction-title-tag">
			<span>We are the Team A5</span>
		</div>
  		<div class="profiles">
			  <ProfileCard data-aos="zoom-in-up" data-aos-once="true" :imgSrc="sy.imgSrc" :name="sy.name" :position="sy.position" :intro="sy.intro" :skills="sy.skills" :gitUrl="sy.gitUrl" :email="sy.email"/>
			  <ProfileCard data-aos="zoom-in-up" data-aos-once="true" :imgSrc="ah.imgSrc" :name="ah.name" :position="ah.position" :intro="ah.intro" :skills="ah.skills" :gitUrl="ah.gitUrl" :email="ah.email"/>
			  <ProfileCard data-aos="zoom-in-up" data-aos-once="true" :imgSrc="hj.imgSrc" :name="hj.name" :position="hj.position" :intro="hj.intro" :skills="hj.skills" :gitUrl="hj.gitUrl" :email="hj.email"/>
			  <ProfileCard data-aos="zoom-in-up" data-aos-once="true" :imgSrc="gh.imgSrc" :name="gh.name" :position="gh.position" :intro="gh.intro" :skills="gh.skills" :gitUrl="gh.gitUrl" :email="gh.email"/>
		    <ProfileCard data-aos="zoom-in-up" data-aos-once="true" :imgSrc="kr.imgSrc" :name="kr.name" :position="kr.position" :intro="kr.intro" :skills="kr.skills" :gitUrl="kr.gitUrl" :email="kr.email"/>
      </div>
      <v-divider></v-divider>
  		<v-img class="team-intro-image" id="team" aspect-ratio="5.15">
        <div class="team-intro-images">
          <img data-aos="fade-up" src="https://user-images.githubusercontent.com/52685250/73331867-93cf5980-42a8-11ea-9376-334bfa5fffe3.jpg" id="team-img">
          <img data-aos="fade-up" data-aos-delay="200" src="https://user-images.githubusercontent.com/52685250/73331868-93cf5980-42a8-11ea-8272-cb55019dbd5c.jpg" id="team-img">
          <img data-aos="fade-up" data-aos-delay="400" src="https://user-images.githubusercontent.com/52685250/73331869-93cf5980-42a8-11ea-9169-cc7b4f9aab49.jpg" id="team-img">
        </div>
      </v-img>
	</div>
</template>

<script>
import ProfileCard from '@/components/teamintro/ProfileCard.vue'

export default {
		name: "TeamIntro",
		components: {
			ProfileCard
		},
    data() {
	    return {
				sy: {
					imgSrc: require('@/assets/profile/SY.jpg'),
					name: '이승열',
					position: 'Leader',
					intro: '팀의 리더. A5의 수장. 팀의 워라벨을 중시하며 팀원들에게 나쁜 소리를 하지 못함.',
					skills: ['Spring', 'Vue.js', 'MySQL'],
					gitUrl: 'https://github.com/ssong10',
					email: 'leesy1403@naver.com'
				},
				ah: {
					imgSrc: require('@/assets/profile/AH.jpg'),
					name: '김아현',
					position: 'Backend developer',
					intro: '3전공(컴퓨터, 전자, 시각디자인). 팀의 브레인. 아이디어 뱅크. 아뱅. 회식 매니아',
					skills: ['UI/UX', 'Spring', 'MySQL'],
					gitUrl: 'https://github.com/AhyeonKim',
					email: 'kah0396@naver.com'
				},
				hj: {
					imgSrc: require('@/assets/profile/HJ.jpg'),
					name: '김희주',
					position: 'Backend developer',
					intro: '월드클래스 개발자. 백엔드의 귀재. 복붙의 달인. 자몽에이드.',
					skills: ['Spring', 'MySQL', 'AWS'],
					gitUrl: 'https://github.com/khJoo5',
					email: 'delight_joo@naver.com'
				},
				gh: {
					imgSrc: require('@/assets/profile/GH.jpg'),
					name: '심규현',
					position: 'FrontEnd developer',
					intro: 'Vuetify 그 자체. 아름다운 그대. 프로 통학러. 천안에서 통학하려고 싸피다님',
					skills: ['HTML', 'CSS', 'Vue.js'],
					gitUrl: 'https://github.com/wally-wally',
					email: 'wallys0213@gmail.com'
				},
				kr: {
					imgSrc: require('@/assets/profile/KR.jpg'),
					name: '염겨레',
					position: 'FrontEnd developer',
					intro: '프로 식단러. 식단 사진 찍어서 매주 공유함. 다이어트 중.',
					skills: ['HTML', 'CSS', 'Vue.js'],
					gitUrl: 'https://github.com/yeomkyeorae',
					email: 'duarufp06@naver.com'
				}
			}
		},
}
</script>

<style>
	.profiles {
		display: flex;
		flex-wrap: wrap;
	  justify-content: center;
		align-items: center;
		margin: 15px 0;
	}
	
	.team-introduction-title-tag {
		font-family: 'Julius Sans One', sans-serif;
		font-size: 2.5em;
		text-shadow: 3px 3px 4px darkgrey;
		text-align: center;
		padding-top: 10px;
		animation-name: letter-gather;
		animation-duration: 2s;
		animation-iteration-count: 1;
	}

	@keyframes letter-gather {
		from {
			letter-spacing: 3px;
		}
		50% {
			letter-spacing: 5px;
		}
		to {
			letter-spacing: auto;
		}
	}
</style>