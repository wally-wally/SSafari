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
                <span>{{ post.username }}
                  <sendmessage :username="post.username" :id="post.memberid"/>
                </span>
              </div>
              <v-spacer></v-spacer>
              <div class="my-2 mx-2">
                <span class="headline">{{ post.title }}
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
                  <h6>{{ post.created_at }} </h6>
                  <div v-if="this.$store.state.memberid === post.memberid">
                    <v-btn class="mr-1" small color="green"
                      :to="{ path : `/board/${this.boardname}/${post.postid}/update`}">수정</v-btn>
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
                router.push({
                  path: `/board/${(Number(this.boardname) >= 5) ?  this.boardname : this.$store.state.category[this.boardname]}`
                })
              }
            })
        }
      }
    }
  }
</script>

<style>

</style>