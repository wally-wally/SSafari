<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="12" sm="8">
        <v-card>
          <v-card-title class="pb-0 darken-1">
              <div class="ma-2 mb-5" justify="center">
                  <img class="avatar" :src="post.img" alt="X">
                <div style="display:inline-block">
                  <span style="display:block">
                    {{ post.username }}
                    <sendmessage :username="post.username" :id="post.memberid"/>
                  </span>
                  <small style="display:inline-block">{{ post.created_at }} </small>
                </div>
              </div>
          </v-card-title>
              <div class="ma-2 mt-5">
                <div align="left" class="ml-3 headline">{{ post.title }}
                <div class="delrevise" v-if="this.$store.state.memberid === post.memberid">
                    <v-btn class="mr-1" small color="warning" style="color:white"
                      :to="{ path : `/board/${this.boardname}/${post.postid}/update`}">수정</v-btn>
                    <v-btn @click="deletePost" small color="error">삭제</v-btn>
                  </div>
                </div>
              </div>
          <v-list>
            <v-list-item>
              <v-list-item-content class="ml-2 postcontent mb-5">
                {{post.body}}
              </v-list-item-content>
            </v-list-item>
            
            <div class="mt-5" align="center">
                  <v-btn v-if="((!likeFlag) && isLogin && memberid)" @click="clickLike" text icon color="#d3d3d3">
                    <v-icon>mdi-thumb-up</v-icon>
                    <h3>{{ count }}</h3>
                  </v-btn>
                  <v-btn v-if="((likeFlag) && isLogin)" @click="clickLike" text icon color="deep-orange">
                    <v-icon>mdi-thumb-up</v-icon>
                    <h3>{{ count }}</h3>
                  </v-btn>
                  <v-btn v-if="!isLogin" text icon disable>
                    <v-icon>mdi-thumb-up</v-icon>
                    <h3>{{ count }}</h3>
                  </v-btn>
            </div>
            <v-divider/>
            <v-col cols="12" sm="12">
              <boardcomment :categoryid="post.categoryid" :boardname="this.boardname" :postid="this.id"
                boardtype="post" />
            </v-col>
          </v-list>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
  import sendmessage from '../../message/sendmessage'
  import axios from 'axios'
  import boardcomment from '@/components/comment/boardcomment.vue'
  import router from '@/router.js'

  export default {
    name: "BoardDetail",
    components: {
      boardcomment,
      sendmessage
    },
    data() {
      return {
        post: [],
        comments: [],
        likeFlag: false,
				updateFlag: false,
        isLogin: false,
        memberid: null,
        categoryid: '',
        count: 0,
      }
    },
    props: {
      boardname: {
        type: String
      },
      id: {
        type: String
      },
    },
    mounted() {
      this.getPost()
			this.memberid = this.$store.state.memberid
			var token = this.$store.state.token
			if (token) {
				this.isLogin = true
      }
      if (this.boardname == 'free'){
        this.categoryid = '1'
      } else if(this.boardname == 'job'){
        this.categoryid = '2'
      } else {
        this.categoryid = this.boardname
      }
    },
    methods: {
			clickUpdate() {
				this.updateFlag = true
			},
			clickLike() {
				this.likeFlag = !this.likeFlag
				var data = {
					postid: this.post.postid,
					memberid: this.memberid,
					categoryid: this.categoryid
				}
				if (this.likeFlag) {
          this.count += 1
					axios.post('api/likepost', data)
						.then(response => {
							console.log(response)
						})
				} else {
					this.count -= 1
					axios.delete('api/likepost', {
							data: data
						})
						.then(response => {
							console.log(response)
						})
				}
			},
      getPost() {
        axios.get(`api/post/${this.id}`, { headers: { 'access-token': this.$store.state.token }})
          .then(response => {
            this.post = response.data.post
            this.count = response.data.count
            this.likeFlag = (response.data.flag === 0) ? false : true
          })
      },
      deletePost() {
        if (confirm('정말로 삭제하시겠습니까?')) {
            axios.delete(`api/post/${this.id}`)
            .then(response => {
              if (response.status == 200) {
                var path = this.$route.path
                var res = path.split('/')
                res = res.splice(0, res.length - 1)
                var routePath = res.join('/')
                router.push(routePath)
              }
            })
        }
      }
    },
    watch : {
      post() {
        const body = document.querySelector('.postcontent').innerText;
      }
    }
  }
</script>

<style>
.postcontent{
  white-space: pre-line
}
.avatar {
  width:52px;
  min-width:52px;
  height:52px;
  border-radius : 52px;
  margin-right: 10px;
}
.delrevise{
  display:inline-block;
  float : right;  
  margin-right: 20px;
}
</style>
