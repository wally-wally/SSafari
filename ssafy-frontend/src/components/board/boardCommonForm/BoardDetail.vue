<template>
  <v-container>
  <v-row justify="center">
    <v-col cols="12" sm="8">
      <v-card>
        <v-card-title class="darken-1">
          <div>
            <div>
              <v-avatar class="mr-5 mb-2">
						    <img :src="post.img" alt="X">
    			    </v-avatar>
              <span>{{ post.username }}</span>
            </div>
            <v-spacer></v-spacer>
            <div class="my-2 mx-2">
              <span class="headline">{{ post.title }} <h6>{{ post.created_at }} </h6>
                <div v-if="this.$store.state.memberid === post.memberid">
                <v-btn class="mr-1" small color="green" :to="{ path : `/board/${(Number(post.categoryid) >= 5) ? post.categoryid : this.$store.state.category[post.categoryid]}/${post.postid}/update`}">수정</v-btn>
                <v-btn @click="deletePost" small color="error">삭제</v-btn>
                </div>
              </span>
            </div>
          </div>
        </v-card-title>
        <v-list>
          <v-divider></v-divider>
          <v-list-item>
            <v-list-item-content>
              {{ post.body }}
            </v-list-item-content>
          </v-list-item>
        <v-divider></v-divider>
          <v-col cols="12" sm="12">
            <boardcomment :categoryid="(Number(post.categoryid) >= 5) ? post.categoryid : this.$store.state.category[post.categoryid]" :postid="this.id" boardtype="post"/>
          </v-col>
        </v-list>
      </v-card>
    </v-col>
  </v-row>
  </v-container>
</template>

<script>
import axios from 'axios'
import boardcomment from '@/components/comment/boardcomment.vue'
import router from '@/router.js'

export default {
    name: "BoardDetail",
    components : {boardcomment},
    data() {
	    return {
        post: [],
        comments: [],
      }
    },
    props: {
      boardname : {type: String},
      id: { type: String },
    },
    mounted() {
        this.getPost()
    },
    methods: {
        getPost() {
            axios.get(`api/post/${this.id}`)
                .then(response => { 
                  this.post = response.data
          })
        },
        deletePost() {
          if (confirm('정말로 삭제하시겠습니까?')) {
            axios.delete(`api/post/${this.id}`)
              .then(response => {
                if(response.status == 200){
                  router.push({ path: `/board/${(Number(this.boardname) >= 5) ?  this.boardname : this.$store.state.category[this.boardname]}` })
                }
              })
          }
        }
    }
}
</script>

<style>

</style>