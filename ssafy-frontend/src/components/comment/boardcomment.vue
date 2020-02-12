<template>
    <v-container>
        <h3> {{ boardtype }} 댓글 </h3>
        <v-list three-line>
            <div v-for="comment in comments" :key="comment.id">
                <comment @commetdel="commentdelete(comment)" :board="boardtype" :comment=comment />
                <v-divider />
            </div>
        </v-list>
        <v-form @submit.prevent="Createcomment" clas>
            <v-row class="form-group d-flex">
                <v-col cols="7">
                    <textarea rows="3" v-model="new_comment" class="commentinput" v-bind:readonly="!isLogin" v-bind:placeholder="check"></textarea>
                </v-col>
                <v-col cols="2">
                    <v-checkbox v-model="anonymousStatus" label="익명" value="익명" class="annoyCheck"/>
                </v-col>
                <v-col cols="1">
                    <v-btn type="submit" color="secondary" class="btn btn-info align-self-end mx-2 mb-1">등록</v-btn>
                </v-col>
            </v-row>
        </v-form>
    </v-container>
</template>
<script>
import axios from 'axios'
import Comment from '../comment/Comment'
import router from '@/router.js'
import { mapState} from 'vuex'

export default {
    name : 'boardcomment',
    props : {
        categoryid : {type : String},
        boardtype : {type: String},
        postid : {type: String},
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
                const data = {
                    'postid' : this.postid,
                }
                axios.get(`api/comment/`, {params: data})
                .then(response =>{
                    this.comments = response.data
                })
            }
            if (this.$route.name==="codedetail"){
                const data = {
                    'postid' : this.postid,
                    'categoryid' : 3,
                }
                axios.get(`api/commentpost/`, {params: data})
                .then(response =>{
                    this.comments = response.data
                })
            } else if (this.$route.name==="jmtdetail"){
                const data = {
                    'postid' : this.postid,
                    'categoryid' : 4,
                }
                axios.get(`api/commentpost/`, {params: data})
                .then(response =>{
                    console.log(response.data)
                    this.comments = response.data
                })
            } else {
                const data = {'postid':this.postid, 'categoryid' : this.categoryid}
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
    background-color:white;
    margin-top:2rem;
}
/* input, textarea {
    background: #fff;
    border: none;
    -webkit-box-shadow: 0px 1px 4px 2px rgba(0,0,0,0.16);
    -moz-box-shadow: 0px 1px 4px 2px rgba(0,0,0,0.16);
    box-shadow: 0px 1px 4px 2px rgba(0,0,0,0.16);
    border-radius: 15px;
}  */
footer button.btn {
    display: flex;
    padding-left: 20px;
    padding-right: 20px;
    border-radius: 15px;    
}
</style>