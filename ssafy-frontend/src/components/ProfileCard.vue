<template>
    <v-card
    :loading="loading"
    class="mx-auto my-12 profile-card"
    max-width="300"
  >
    <v-img
      height="250"
      :src="imgSrc"
    ></v-img>
    <v-card-title><strong class="profile-name">{{ name }}</strong></v-card-title>

    <v-card-text>

      <div class="my-4 subtitle-1 black--text">
        {{ position }}
      </div>

      <div> {{ intro }}</div>
    </v-card-text>

    <v-divider class="mx-4"></v-divider>

    <v-card-title>Main Skills</v-card-title>

    <v-card-text class="skills">
        <v-chip v-for="i in this.skills.length" :key="i" class="ma-1" :color="fontColor[skills[i - 1]]">{{ skills[i - 1] }}</v-chip>
    </v-card-text>

    <v-card-actions>
      <!-- <v-btn
        color="deep-purple accent-4"
        text
      >
        <a :href="`${gitUrl}`" target="_blank"><i class="fab fa-github-square fa-3x"></i></a>
      </v-btn> -->
    <div class="sns-button">
      <a class="btn" :href="`${gitUrl}`" target="_blank">
        <i class="fab fa-github"></i>
      </a>
      <a class="btn" :href="`mailto:${email}`">
        <i class="fas fa-envelope-square"></i>
      </a>
    </div>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
	name: 'ProfileCard',
	data() {
		return {
			fontColor: {
				'MySQL': 'primary',
				'Spring': 'orange',
				'HTML': 'blue',
				'AWS': 'purple',
				'Vue.js': 'green',
				'CSS': 'pink',
				'UI/UX': 'lime'
			}
		}
	},
	props: {
		imgSrc: { type: String},
		name: { type: String },
		position: { type: String},
		intro: { type: String},
		skills: { type: Object},
    gitUrl: { type: String},
    email: { type: String}
	}
}
</script>

<style>
  .profile-name {
    padding-top: 5px;
    font-size: 1.2em;
  }

  .profile-card:hover {
    box-shadow: 3px 3px 5px lightsalmon;
  }

  .skills {
    margin-bottom: 90px;
  }

  .sns-button {
    position: absolute;
    top: 91%;
    transform: translateY(-50%);
    width: 100%;
    text-align: center;
  }

  .btn {
    display: inline-block;
    width: 80px;
    height: 80px;
    background: #f1f1f1;
    margin: 10px;
    border-radius: 30%;
    box-shadow: 0 5px 15px -15px #00000070;
    color: #f7b157;
    overflow: hidden;
    position: relative;
  }

  .btn i {
    line-height: 80px;
    font-size: 26px;
    transition: 0.2s linear;
    color: #f7b157;
  }

  .btn:hover i{
    transform: scale(1.3);
    color: #f1f1f1;
  }

  .btn::before{
    content: "";
    position: absolute;
    width: 120%;
    height: 120%;
    background: #f7b157;
    transform: rotate(45deg);
    left: -110%;
    top: 90%;
  }

  .btn:hover::before {
    animation: rint 0.7s 1;
    top: -10%;
    left: -10%;
  }

  @keyframes rint {
    0% {
      left: -110%;
      top: 90%;
    }

    50% {
      left: 10%;
      top: -30%;
    }

    100% {
      left: -10%;
      top: -10%;
    }
  }
</style>