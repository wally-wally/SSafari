<template>
    <v-list-item>
        <v-list-item-content>
            <v-list-item-title style="font-size:1.3rem;">
                    <span v-if="comment.anonym" >익명</span>
                    <span v-else>{{ comment.username }}</span>
                    <small style="color:blue" v-if="comment.memberid === comment.writer">(작성자)</small>
                    <sendmessage :username="comment.anonym ? '익명' : comment.username" :id="comment.memberid"/>
                    <small style="font-size:0.7rem;">( {{comment.wdate}} )</small>
                    <span v-if="comment.memberid==$store.state.memberid" class="mt-2" style="float:right">
                        <i class="far fa-edit ml-5 edbtn" @click="commentupdate()" style="font-size:17px"></i>
                        <i class="far fa-trash-alt ml-2 edbtn" @click="commentdelete()" style="font-size:17px"></i>
                    </span>
            </v-list-item-title>
            <v-list-item-content style="white-space: pre-line" v-if="!this.update">
                {{comment.content}}
            </v-list-item-content>
            <div v-if="this.update">
                <form @submit.prevent="commentput()">
                    <textarea class="ma-3" rows="3" style="width:50%" v-model="new_comment"/>
                    <div style="text-align:right">
                        <v-checkbox dense hide-details="1" color="#ffc837" v-model="anonymousStatus" label="익명" value="1" class="commentchkbox annoyCheck"/>
                        <button class="ml-3 mr-3" type="submit"><small>등록</small></button>
                        <button @click="commentupdate()"><small>취소</small></button>
                    </div>
                </form>
            </div>
        </v-list-item-content>
</v-list-item>
</template>

<script>
import axios from 'axios'
import sendmessage from '../message/sendmessage'

export default {
    name : 'Comment',
    props : {
        comment : {type : Object},
        board : {type : String}
    },
    components : { sendmessage },
    data() {
        return {
            update: false,
            new_comment :'',
            anonymousStatus: false,
        }
    },
    mounted() {
        this.new_comment = this.comment.content
        this.anonymousStatus = this.comment.anonym
    },
    methods : {
        commentupdate() {
            this.update = !this.update
        },
        commentdelete() {
            const result = confirm("정말 삭제하시겠습니까");
            if (result) {
                this.$emit('commetdel',this.comment)
            }
        },
        commentput() {
            this.comment['content'] = this.new_comment
            this.comment['anonym'] = this.anonymousStatus ? 1 : 0
            axios.put(`api/comment${this.board}`,this.comment)
            .then(response => {
                this.comment.content = this.new_comment
                this.commentupdate()
            }).catch(error => {
                console.log(error)
                })
        }
    }

}
</script>

<style>
    .edbtn{
        color:gray;
    }
    .edbtn:hover{
        color: #ffc837;
        cursor: pointer;
    }
    .commentchkbox{
        display : inline-block;
        font-size: 12px !important
    }
</style>