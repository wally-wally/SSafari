<template>
  <div class="post-wrapper" @click.stop="dialog = true" @click="changeRead">
    <v-dialog v-model="dialog" max-width="700px">
      <v-card>
        <v-card-title><span class="cuteFont" style="font-size:1.5em">제목: {{ title }}</span></v-card-title>
        <v-card-text class="cuteFont" style="font-size:1.3em">
          상대: {{ opponent }}
          <sendmessage :username="opponent" :id="opponentId" />
        </v-card-text>
        <v-divider></v-divider>
        <v-card-text class="cuteFont" style="font-size:1.3em">
          {{ content }}
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="green darken-1" text @click="dialog = false">
            닫기
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <div class="faq-section__qnas">
      <div class="qna animate">
        <div class="qna__q" style="display: flex; justify-content:space-between;" :class="{readColor: isRead}">
          <div class="post-list-title" style="margin-right: 15px;"><span style="color:#ecafb4; display:inline-block; width: 100px;"><h3><b>{{opponent}}</b></h3></span> <span style="display:inline-block; width: 100px;">{{title}}</span>
          </div>
          <div style="font-size: 0.8em; min-width: 87px;">
            <span class="board-date"
              style="margin-left: 4px; padding-left: 4px;">{{date.slice(2, 16)}}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import sendmessage from './sendmessage'
  import axios from 'axios'

  export default {
    name: 'Message',
    data() {
      return {
        dialog: false,
        isRead: this.read
      }
    },
    components: {
      sendmessage
    },
    props: {
      id: {
        type: Number
      },
      date: {
        type: String
      },
      title: {
        type: String
      },
      content: {
        type: String
      },
      opponent: {
        type: String
      },
      opponentId: {
        type: Number
      },
      read: {
        type: Number
      },
      boardType: {
        type: Number
      }
    },
    computed: {
      postYearMonth() {
        let date = new Date(this.date)
        let formatedMonth = date.getMonth() + 1 < 9 ? '0' : ''
        return `${date.getFullYear()}-${formatedMonth}${date.getMonth()+1}`
      },
      postDay() {
        let date = new Date(this.date)
        return date.getDate()
      }
    },
    methods: {
      changeRead() {
        if (this.opponentId !== this.$store.state.memberid & this.boardType) {
          this.isRead = true
          axios.get(`api/message/${this.id}`, {
              headers: {
                'access-token': this.$store.state.token
              }
            }).then(response=>{
              if (!response.data.isread) {
                this.$store.state.unreadmsg -= 1
              }
            })
        }
      }
    }
  }
</script>

<style>
.readColor{
  background-color: rgb(228, 242, 250);
}
.readColor:hover {
  background-color :  rgb(179, 227, 255);
}
</style>