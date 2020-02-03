<template>
  <v-container>
  <v-row justify="center">
    <v-col cols="12" sm="8">
      <v-card>
        <v-card-title class="blue darken-1">
          <span class="headline white--text">{{ portfolio.title }} <h6>{{ portfolio.created_at }} | {{ portfolio.username}}</h6></span>
          <v-spacer></v-spacer>
          <div class="my-2 mx-2">
            <v-btn v-if="currentMemberId === portfolio.memberid" small color="green" :to="{ name: 'PortfolioUpdate', params: { id: id, title: portfolio.title, content: portfolio.body, imgSrc: portfolio.img, memberId: portfolio.memberid }}">수정</v-btn>
          </div>
          <div class="my-2">
            <v-btn v-if="currentMemberId === portfolio.memberid || currentMemberAuth === 1" small color="error" @click="deletePortfolio">삭제</v-btn>
          </div>
        </v-card-title>
                <v-img
          :src="portfolio.img"
          height="400px"
        ></v-img>
        <v-list>
          <v-divider inset></v-divider>
          <v-list-item>
            <v-list-item-action>
							<v-list-item-title>내용</v-list-item-title>
            </v-list-item-action>
            <v-list-item-content>
              {{ portfolio.body }}
            </v-list-item-content>
          </v-list-item>
        <v-divider width="500px;"></v-divider>
        </v-list>
      </v-card>
    </v-col>
  </v-row>
  <v-row v-if="currentMemberId !== portfolio.memberid" justify="center">
    <div class="mr-4">
      {{portfolio.applicant }} 명 / {{ portfolio.capacity}} 명 
    </div>
    <v-btn v-if="portfolio.applicant < portfolio.capacity & !myapplicate" @click="application" color="primary">강의 신청하기</v-btn>
    <v-btn v-else-if="myapplicate" @click="application" color="red darken-1">수강 취소</v-btn>
    <v-btn v-else color="warning">신청 마감되었습니다</v-btn>
    </v-row>
  <v-row justify="center">
    <v-col cols="12" sm="8">
      <boardcomment :postid="this.id" boardtype="portfolio" :comments="comments"/>
    </v-col>
  </v-row>
  </v-container>

</template>

<script>
import axios from 'axios'
import boardcomment from '@/components/comment/boardcomment.vue'
import router from '@/router.js'

export default {
		name: "PortfolioDetail",
		components : {boardcomment},
    data() {
	    return {
						portfolio: [],
            comments : [],
            myapplicate : false,
            currentMemberId: '',
            currentMemberAuth: ''
		}
	},
    props: {
        id: { type: String },
    },
    mounted() {
        this.currentMemberId = this.$session.get('data')['memberid']
        this.currentMemberAuth = this.$session.get('data')['auth']
        this.getPortfolio()
        this.getPortfolioComment()
    },
    methods: {
      checkmyapplicate() {
        axios.get(`http://192.168.31.110:8197/ssafyvue/api/sugang?portfolioid=${this.portfolio.portfolioid}&memberid=${this.currentMemberId}`)
        .then(response => {
          this.myapplicate = response.data
          console.log('듣고있나요', this.myapplicate)
        })
      },
      getPortfolio() {
        console.log(this.id)
          axios.get(`http://192.168.31.110:8197/ssafyvue/api/portfolio/${this.id}`)
              .then( response => {
                console.log(response.data)
                  this.portfolio = response.data
                  this.checkmyapplicate()
        })
      },
      getPortfolioComment() {
        axios.get(`http://192.168.31.110:8197/ssafyvue/api/commentportfolio/${this.id}`)
          .then(response => {
            this.comments = response.data
        })  
      },
      deletePortfolio() {
          axios.delete(`http://192.168.31.110:8197/ssafyvue/api/portfolio/${this.id}`)
            .then(response => {
              console.log(response.status)
              if(response.status == 200){
                router.push({ path: '/portfolio' })
              }
            })
        },
        application(){
          const data = {
            memberid : this.currentMemberId,
            portfolioid : this.portfolio.portfolioid
          }
          if (!this.myapplicate){
            console.log('수강')
            this.portfolio.applicant += 1
            this.myapplicate = true
            axios.post(`http://192.168.31.110:8197/ssafyvue/api/sugang`,data)
            .then(response=> {
              console.log(response.data)
            }).catch(error => {
              console.log(error)
            })
          }else {
            console.log('수강취소')
            this.myapplicate = false
            this.portfolio.applicant -= 1
            axios.delete(`http://192.168.31.110:8197/ssafyvue/api/sugang`,{'data':data})
            .then(response=> {
              console.log(response.data)
            }).catch(error => {
              console.log(error)
            })
            }
        }
    }
}
</script>