<template>
    <v-container>
        <h3> {{ boardtype }} 댓글 </h3>
        <v-list three-line>
            <div v-for="comment in comments" :key="comment.id">
                <comment :board="boardtype" :comment=comment />
                <v-divider />
            </div>
        </v-list>
        <v-form @submit.prevent="Createcomment">
          <div class="form-group d-flex justify-content-center">
            <textarea rows="3" v-model="new_comment" class="commentinput" v-bind:readonly="!isLogin" v-bind:placeholder="check"></textarea>
                <v-btn large type="submit" color="secondary" class="btn btn-info align-self-end mx-2">등록</v-btn>
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
        comments : {type : Array},
        boardtype : {type: String},
        postid : {type: String},
    },
    components : {Comment},
    data() {
        return {
            new_comment : '',
            check : '',
        }
    },
    computed: {
        ...mapState(['isLogin', 'isLoginError']),
    },
    mounted() {
        this.getAnswer()
    },
    methods : {
        Createcomment () {
            const data = {
                'memberid' : this.$session.get('data')['memberid'],
                'content' : this.new_comment,
            }
            data[`${this.boardtype}id`] = this.postid
            axios.post(`http://192.168.31.110:8197/ssafyvue/api/comment${this.boardtype}`,data)
                .then(response => {
                    console.log(response.data)
                    this.comments = response.data
                }).catch(error => {
                    console.log(error)
                })
            this.new_comment = ''
        },
        getAnswer() {
            if(!this.$store.state.isLogin) {
                this.check="로그인 후 작성해주세요."  
            }else {
                this.check="댓글 입력"
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
    width:80%;
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