<template>
  <v-container>
  <v-row justify="center">
    <v-col cols="12" sm="8">
      <v-card>
        <v-card-title class="pink darken-1">
          <span class="headline white--text">{{ post.title }} <h6>{{ post.created_at }} | {{ post.username }}</h6></span>
          <v-spacer></v-spacer>
          <div class="my-2 mx-2">
            <v-btn v-if="currentMemberId === post.memberid" small color="orange" :to="{ name: 'PostUpdate', params: { id: id, title: post.title, content: post.body, memberId: post.memberid }}">수정</v-btn>
          </div>
          <div class="my-2">
            <v-btn v-if="currentMemberId === post.memberid || currentMemberAuth === 1" @click="deletePost" small color="error">삭제</v-btn>
          </div>
        </v-card-title>
        <v-list>
          <v-divider inset></v-divider>
          <v-list-item>
            <v-list-item-action>
							<v-list-item-title>내용</v-list-item-title>
            </v-list-item-action>
            <v-list-item-content>
              {{ post.body }}
            </v-list-item-content>
          </v-list-item>
        <v-divider inset></v-divider>
        </v-list>
      </v-card>
    </v-col>
  </v-row>
  <v-row justify="center">
    <v-col cols="12" sm="8">
      <boardcomment :postid="this.id" boardtype="post" :comments="comments"/>
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
            axios.get(`http://192.168.31.110:8197/ssafyvue/api/post/${this.id}`)
                .then(response => { 
                    this.post = response.data
          })
        },
        getPostComment() {
          axios.get(`http://192.168.31.110:8197/ssafyvue/api/commentpost/${this.id}`)
            .then(response => {
              this.comments = response.data
            })
        },
        deletePost() {
          axios.delete(`http://192.168.31.110:8197/ssafyvue/api/post/${this.id}`)
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