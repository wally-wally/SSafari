<template>
  <v-container>
  <v-row justify="center">
    <v-col cols="12" sm="8">
      <v-card>
        <v-card-title class="darken-1">
          <div>
            <div>
              <v-avatar class="mr-5 mb-2">
						    <img src="https://user-images.githubusercontent.com/52685250/73902320-c72d6c00-48d8-11ea-82b4-eb9bfebfe9fb.png" alt="profile">
    			    </v-avatar>
              <span>{{ post.username }}</span>
            </div>
            <v-spacer></v-spacer>
            <div class="my-2 mx-2">
              <span class="headline">{{ post.title }} <h6>{{ post.created_at }} </h6>
                <v-btn class="mr-1" v-if="currentMemberId === post.memberid" small color="green" :to="{ name: 'BoardUpdate', params: { id: id, title: post.title, content: post.body, memberId: post.memberid }}">수정</v-btn>
                <v-btn v-if="currentMemberId === post.memberid || currentMemberAuth === 1" @click="deletePost" small color="error">삭제</v-btn>
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
            <boardcomment :postid="this.id" boardtype="post" :comments="comments"/>
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
      currentMemberId: '',
      currentMemberAuth: '',
      }
    },
    props: {
        id: { type: String },
    },
    mounted() {
        this.getPost()
        this.currentMemberId = this.$store.state.memberid
        this.currentMemberAuth = this.$store.getters.user.auth
    },
    methods: {
        getPost() {
            axios.get(`api/post/${this.id}`)
                .then(response => { 
                  this.post = response.data
                  this.getPostComment()
          })
        },
        getPostComment() {
          axios.get(`api/commentpost/`, {params : {'postid':this.id, 'categoryid' : this.post.categoryid}} , this.$store.getters.options)
            .then(response => {
              console.log(response)
              this.comments = response.data
            }).catch(error=> {
              console.log(error)
            })
        },
        deletePost() {
          axios.delete(`api/post/${this.id}`)
            .then(response => {
              if(response.status == 200){
                router.push({ path: '/board' })
              }
            })
        }
    }
}
</script>

<style>

</style>