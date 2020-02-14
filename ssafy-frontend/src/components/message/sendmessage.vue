<template>
    <v-card class="cont">
      <div class="form">
        <h2>메세지</h2>
        <br>
        {{comment.username}} 에게
          <v-form @submit.prevent="sendmessage()">
            <v-text-field v-model="message.title" label="제목" required></v-text-field>
            <v-textarea v-model="message.content" label="내용"  required/>
            <button type="submit" class="submit button">메세지</button>
          </v-form>
        <v-card-actions>
          <button type="submit" class="close button" @click="close">닫기</button>
        </v-card-actions>
    </div>
  </v-card>
</template>

<script>
import axios from 'axios'
export default {
    name : 'sendmessage',
    props : {
      comment : {type : Object}
    },
    data() {
        return {
          message : {
            title : '',
            content : '',
            toid : this.comment.memberid,
            fromid : this.$store.state.memberid
          }
        }
    },
    methods : {
      sendmessage(){
        const chk = confirm('메시지 보내시겠습니까')
        if (chk){
          console.log(this.message)
          axios.post('api/message' ,this.message )
          .then(response => {
            console.log(response.data)
          })
          this.close()
        }
      },
      close() {
        this.$emit('close')
      }
    },
    mounted(){
      if (this.comment.anonym) {
        this.comment.username = "익명"
      }
    }
}
</script>

<style>


body {
  font-family: 'Open Sans', Helvetica, Arial, sans-serif;
  background: #ededed;
}

.cont {
  overflow: hidden;
  position: relative;
  width: 900px;
  height: 550px;
  margin: auto;
  background: #fff;
}

</style>