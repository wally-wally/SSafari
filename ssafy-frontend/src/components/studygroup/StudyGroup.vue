<template>
  <v-card id="portfolio-card">
    <v-img :src="imgSrc" height="200px">
    </v-img>
    <v-card-title primary-title>
      <div class="headline">
        <strong id="title">{{title}}</strong>
        <hr class="separate-line" align="center" color="lightgrey">
      </div>
    </v-card-title>
    <v-card-text>
      <span class="grey--text" id="card-content">{{body}}</span>
    </v-card-text>
    <v-card-subtitle>
      <div id="writter-date">
        <p>{{ username }}</p>
      </div>
    </v-card-subtitle>
  </v-card>
</template>

<script>
export default {
	name: 'StudyGroup',
	props: {
		date: {type: String},
		title: {type: String},
		body: {type: String},
    imgSrc: {type: String},
    username: {type: String},
    memberid: {type: Number}
	},
	data() {
		return {
			//
		}
	}
}
</script>
<style>
  #portfolio-card:hover {
    transition-duration: 0.4s;
    box-shadow: 0px 0px 7px rebeccapurple;
  }

  #title {
    font-size: 1.3rem;
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 1; /* number of lines to show */
    -webkit-box-orient: vertical;
  }

  #card-content {
    font-family: 'Noto Sans KR', sans-serif;
    white-space: normal;
		line-height: 1.3;
		height: 3.9em;
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 3; /* number of lines to show */
    -webkit-box-orient: vertical;
  }

  #writter-date {
    display: flex;
    justify-content: space-between;
  }

  #writter-date > p {
    display: inline;
  }
</style>
