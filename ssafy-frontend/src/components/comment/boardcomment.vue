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
                <textarea rows="2" v-model="new_comment" class="commentinput" v-bind:readonly="!isLogin" v-bind:placeholder="check"></textarea>
                <div class="row justify-end mr-3">
                    <v-checkbox dense hide-details="1" color="#ffc837" v-model="anonymousStatus" label="익명" value="익명" class="annoyCheck"/>
                    <v-btn type="submit" style="height:30px;color:#4c4c4c;" class="ml-3 mb-2 submit-button" color="#d8d8d8" @click="create">작성</v-btn>
                </div>
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
                        console.log(response)
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
            console.log(data)
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