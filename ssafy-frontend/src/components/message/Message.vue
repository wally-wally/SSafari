<template>
  <div class="post-wrapper" @click.stop="dialog = true" @click="changeRead">
    <v-dialog v-model="dialog" max-width="290">
      <v-card>
        <v-card-title class="headline">제목: {{ title }}</v-card-title>
        <v-card-text>
          상대: {{ opponent }}
          <sendmessage v-if="opponentId !== 1" :username="opponent" :id="opponentId" />
        </v-card-text>
        <v-divider></v-divider>
        <v-card-text>
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
        <div class="qna__q" style="display: flex; justify-content:space-between;">
          <div class="post-list-title" style="margin-right: 15px;"><span style="color:#F67280">{{opponent}}</span> | {{title}}</div>
          <div v-if="isRead" style="color:red">
            <b>읽었당</b>
          </div>
          <div style="font-size: 0.8em; min-width: 87px;">
            <span class="board-date"
              style="margin-left: 8px; border-left: 1px solid black; padding-left: 8px;">{{date.slice(2, 16)}}</span>
          </div>
        </div>
        <div class="qna__a"></div>
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
        console.log(this.opponentId, this.$store.state.memberid)
        if (this.opponentId !== this.$store.state.memberid) {
          this.isRead = true
          axios.get(`api/message/${this.id}`, {
              headers: {
                'access-token': this.$store.state.token
              }
            })
        }
      }
    }
  }
</script>

<style>

</style>