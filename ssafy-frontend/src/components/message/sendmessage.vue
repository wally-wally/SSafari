<template>
  <v-div v-if="this.$store.state.isLogin && this.id !== this.$store.state.memberid" >
    <v-div >
      <i @click="modal" class="messageicon mx-2 far fa-envelope"></i>
    </v-div>
    <v-dialog max-width="600px" v-if="send" v-model="send">
      <v-card class="messagecont pa-3">
        <div class="messageform">
          <h2>메세지</h2>
          <br>
          <div class="mb-4">
          {{username}} 에게
          </div> 
            <v-form @submit.prevent="sendmessage()">
              <v-text-field v-model="title" label="제목" required></v-text-field>
              <v-textarea v-model="content" label="내용"  required/>
              <button type="submit" class="submit button">메세지</button>
            </v-form>
          <v-card-actions>
            <button class="close button" @click="modal">닫기</button>
          </v-card-actions>
        </div>
      </v-card>
    </v-dialog>
</v-div>
</template>

<script>
import axios from 'axios'
export default {
    name : 'sendmessage',
    props : {
      id : {type : Number},
      username : {type : String},
    },
    data() {
        return {
          send : false,
          title : '',
          content : '',
        }
    },
    methods : {
      modal() {
            this.send = !this.send  
            this.title = ''
            this.content = ''
        },
      sendmessage(){
        const chk = confirm('메시지 보내시겠습니까')
        if (chk){
          const message = {
            title : this.title,
            content : this.content,
            toid : this.id,
            fromid : this.$store.state.memberid
          }
          console.log(message)
          axios.post('api/message' ,message )
          .then(response => {
            console.log(response.data)
            this.modal()
          })
        }
      }
    }
}
</script>

<style scoped> 

*, *:before, *:after {
  margin: 0;
  padding: 0;
}


.messageicon:hover {
  cursor:pointer;
}

</style>