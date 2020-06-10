<template>
    <v-container>
        <v-list three-line>
            <div v-for="comment in comments" :key="comment.id">
                <comment @commetdel="commentdelete(comment)" :board="boardtype" :comment=comment />
                <v-divider style="margin:0"/>
            </div>
        </v-list>
        <v-form @submit.prevent="Createcomment">
                <textarea rows="2" v-model="new_comment" class="commentinput" v-bind:readonly="!isLogin" v-bind:placeholder="check"></textarea>
                <div class="row justify-end mr-3">
                    <v-checkbox dense hide-details="1" color="#ffc837" v-model="anonymousStatus" label="익명" value="익명" class="annoyCheck"/>
                    <v-btn type="submit" style="height:30px;color:#4c4c4c;" class="ml-3 mb-2 submit-button" color="#d8d8d8">작성</v-btn>
                </div>
        </v-form>
    </v-container>
</template>

<script>
import axios from 'axios'
import Comment from '../comment/Comment'
import router from '@/router.js'
import { mapState } from 'vuex'

export default {
    name : 'boardcomment',
    props : {
        boardname : {type : String},
        boardtype : {type: String},
        postid : {type: String},
        categoryid : {type: Number}
    },
    components : {Comment},
    data() {
        return {
            comments : [],
            anonymousStatus : '',
            new_comment : '',
            check : '',
        }
    },
    computed: {
        ...mapState(['isLogin', 'isLoginError']),
    },
    mounted() {
        this.getAnswer()
        this.getcomments()
    },
    methods : {
        commentdelete(comment){
            axios.delete(`api/comment${this.boardtype}`,{data:comment})
                .then(response => {
                    this.comments = response.data
                }).catch(error => {
                    console.log(error)
            })
        },
        getcomments() {
            if (this.$route.name==="StudygroupDetail"){
                axios.get(`api/commentportfolio/${this.postid}` )
                    .then(response => {
                        this.comments = response.data
                    }).catch(error=> {
                        console.log(error)
                    })
            } else{
            const data = {'postid':this.postid, 'categoryid' : (Number(this.boardname) >= 5) ?  this.boardname : this.$store.state.category[this.boardname] }
                axios.get(`api/commentpost/`, {params : data})
                    .then(response => {
                    this.comments = response.data
                    }).catch(error=> {
                    console.log(error)
                    })
            }
        },
        Createcomment () {
            if (this.new_comment){
            const data = {
                'memberid' : this.$store.state.memberid,
                'content' : this.new_comment,
                'anonym' : this.anonymousStatus ? 1 : 0,
            }
            data['categoryid'] = this.categoryid
            data[`${this.boardtype}id`] = this.postid
            axios.post(`api/comment${this.boardtype}`,data)
                .then(response => {
                    this.comments = response.data
                }).catch(error => {
                    console.log(error)
                })
            this.new_comment = ''
            }
        },
        getAnswer() {
            if(!this.$store.state.isLogin) {
                this.check="로그인 후 작성해주세요."  
            }else {
                this.check="댓글을 입력하세요."
            }
        },
    },
    watch: {
        isLogin: function (newcheck) {
            this.getAnswer()
        }
    }
}
</script>

<style>
.commentinput {
    width: 100%;
    height: 50px;
    background-color:white;
    margin-top:2rem;
    resize: none;
    border: 1px solid lightgray;
}

footer button.btn {
    display: flex;
    padding-left: 20px;
    padding-right: 20px;
    border-radius: 15px;    
}
</style>