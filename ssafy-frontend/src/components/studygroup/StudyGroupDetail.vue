<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="12" sm="8">
        <v-card>
          <v-card-title class="darken-1">
            <div>
              <div>
                <v-avatar class="mr-5 mb-2">
                  <img :src='portfolio.memberimg' alt="X">
                </v-avatar><span>{{ portfolio.username}}</span>
                <sendmessage :username="portfolio.username" :id="portfolio.memberid" />
              </div>
              <div>
                <span class="headline">{{ portfolio.title }} <h6>{{ portfolio.created_at }} </h6>
                  <v-btn class="mr-1" v-if="currentMemberId === portfolio.memberid" small color="green"
                    :to="{ path : `/studygroup/${portfolio.portfolioid}/update`}">수정</v-btn>
                  <v-btn v-if="currentMemberId === portfolio.memberid || currentMemberAuth === 1" small color="error"
                    @click="deletePortfolio">삭제</v-btn>
                </span>
              </div>
            </div>
            <v-spacer></v-spacer>
          </v-card-title>
          <v-img :src="portfolio.img" height="400px"></v-img>
          <v-list>
            <v-list-item>
              <v-list-item-content>
                {{ portfolio.body }}
              </v-list-item-content>
            </v-list-item>
            <v-divider v-if="currentMemberId"></v-divider>
            <v-row v-if="currentMemberId" justify="center" class="mt-2">
              <div class="mr-4 mt-1">
                (현재/전체) {{portfolio.applicant }}명 / {{ portfolio.capacity}}명
                <div v-if="currentMemberId === portfolio.memberid">
                  <h5>[신청자 목록]</h5>
                  <v-list v-for="applicant in applicants" :key="applicant.id">
                    <v-list-item-content>
                      <h3>{{ applicant.username }}</h3>
                      <sendmessage :username="applicant.username" :id="applicant.memberid" />

                    </v-list-item-content>
                  </v-list>
                </div>
              </div>
              <div v-if="currentMemberId !== portfolio.memberid">
                <v-btn v-if="portfolio.applicant < portfolio.capacity & !myapplicate" @click="application"
                  color="primary">강의 신청하기</v-btn>
                <v-btn v-else-if="myapplicate" @click="application" color="red darken-1"> 수강 취소</v-btn>
                <v-btn v-else color="warning">신청 마감되었습니다</v-btn>
              </div>
            </v-row>
            <v-divider></v-divider>
            <v-row justify="center">
              <v-col cols="12" sm="12">
                <boardcomment :postid="this.id" boardtype="portfolio" :comments="comments" />
              </v-col>
            </v-row>
          </v-list>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
  import axios from 'axios'
  import boardcomment from '@/components/comment/boardcomment.vue'
  import sendmessage from '../message/sendmessage'
  import router from '@/router.js'

  export default {
    name: "PortfolioDetail",
    components: {
      boardcomment,
      sendmessage
    },
    data() {
      return {
        portfolio: [],
        comments: [],
        myapplicate: false,
        currentMemberId: null,
        currentMemberAuth: '',
        applicants: [],
      }
    },
    props: {
      id: {
        type: String
      },
    },
    mounted() {
      this.currentMemberId = this.$store.state.memberid
      this.currentMemberAuth = this.$store.getters.auth
      this.getPortfolio()
      this.getPortfolioComment()
    },
    methods: {
      checkmyapplicate() {
        axios.get(`api/sugang?portfolioid=${this.portfolio.portfolioid}&memberid=${this.currentMemberId}`)
          .then(response => {
            this.myapplicate = response.data
          })
      },
      getPortfolio() {
        console.log(this.id)
        axios.get(`api/portfolio/${this.id}`)
          .then(response => {
            this.portfolio = response.data.portfolio
            this.applicants = response.data.sugang
            this.checkmyapplicate()
          })
      },
      getPortfolioComment() {
        axios.get(`api/commentportfolio/${this.id}`)
          .then(response => {
            this.comments = response.data
          })
      },
      deletePortfolio() {
        axios.delete(`api/portfolio/${this.id}`)
          .then(response => {
            console.log(response.status)
            if (response.status == 200) {
              router.push({
                path: '/studygroup'
              })
            }
          })
      },
      application() {
        const data = {
          memberid: this.currentMemberId,
          portfolioid: this.portfolio.portfolioid
        }
        if (!this.myapplicate) {
          console.log('수강')
          this.portfolio.applicant += 1
          this.myapplicate = true
          axios.post(`api/sugang`, data)
            .then(response => {
              console.log(response.data)
            }).catch(error => {
              console.log(error)
            })
        } else {
          console.log('수강취소')
          this.myapplicate = false
          this.portfolio.applicant -= 1
          axios.delete(`api/sugang`, {
              'data': data
            })
            .then(response => {
              console.log(response.data)
            }).catch(error => {
              console.log(error)
            })
        }
      }
    }
  }
</script>