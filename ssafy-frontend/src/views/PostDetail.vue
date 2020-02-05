<template>
  <v-container>
  <v-row justify="center">
    <v-col cols="12" sm="8">
      <v-card>
        <v-card-title class="darken-1">
          <div>
            <div>
              <v-avatar class="mr-5 mb-2">
						    <img src="../../public/img/profile.png" alt="profile">
    			    </v-avatar>
              <span>{{ post.username }}</span>
            </div>
            <v-spacer></v-spacer>
            <div class="my-2 mx-2">
              <span class="headline">{{ post.title }} <h6>{{ post.created_at }} </h6>
                <v-btn class="mr-1" v-if="currentMemberId === post.memberid" small color="green" :to="{ name: 'PostUpdate', params: { id: id, title: post.title, content: post.body, memberId: post.memberid }}">수정</v-btn>
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
    name: "postDetail",
    components : {boardcomment},
    data() {
	    return {
      post: [],
      comments: [],
      currentMemberId: '',
      currentMemberAuth: ''
      }
    },
    props: {
        id: { type: String },
    },
    mounted() {
        this.getPost()
        this.getPostComment()
        this.currentMemberId = this.$session.get('data')['memberid']
        this.currentMemberAuth = this.$session.get('data')['auth']
    },
    methods: {
        getPost() {
            axios.get(`api/post/${this.id}`)
                .then(response => { 
                    this.post = response.data
          })
        },
        getPostComment() {
          axios.get(`api/commentpost/${this.id}`)
            .then(response => {
              this.comments = response.data
            })
        },
        deletePost() {
          axios.delete(`api/post/${this.id}`)
            .then(response => {
              if(response.status == 200){
                router.push({ path: '/post' })
              }
            })
        }
    }
}
</script>

<style>

</style>