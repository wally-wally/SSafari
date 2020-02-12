<template>
    <v-list-item>
        <v-list-item-content>
            <v-list-item-title style="font-size:1.3rem;">
            <span v-if="comment.anonym" >익명</span>
            <span v-else>{{ comment.username }}</span>
            <small style="color:blue" v-if="comment.memberid === comment.writer">(작성자)</small>
            <small style="font-size:0.7rem;">( {{comment.wdate}} )</small>
            </v-list-item-title>
            <v-list-item-subtitle class="mt-2" style="font-size:1rem;" v-if="!this.update">
                {{comment.content}}
                <v-spacer class="mb-2" />
                <v-btn small rounded @click="commentupdate()" v-if="comment.memberid==$store.state.memberid">수정</v-btn>
                <v-btn small rounded @click="commentdelete()" v-if="comment.memberid==$store.state.memberid">삭제</v-btn>
            </v-list-item-subtitle>
            <div v-if="this.update">
                <form @submit.prevent="commentput()">
                    <textarea class="ma-3" rows="3" style="width:50%" v-model="new_comment"/>
                    <v-checkbox v-model="anonymousStatus" label="익명" value="1" class="annoyCheck"/>
                    <button class="mr-3" type="submit"><small>등록</small></button>
                    <button @click="commentupdate()"><small>취소</small></button>
                </form>
            </div>
        </v-list-item-content>
</v-list-item>
</template>

<script>
import axios from 'axios'
export default {
    name : 'Comment',
    props : {
        comment : {type : Object},
        board : {type : String}
    },
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

</style>