<template>
    <v-flex justify-center>
        {{this.post}}
        <div v-if="this.$store.state.memberid === post.memberid">
            <div id="create-form-title">
                <span id="form-title" v-if="this.$route.path === `/studygroup/${post.postid}/update`">Studygroup Form</span>
                <span id="form-title" v-else>Board Form</span>
            </div>
            <hr class="title-headline">
            <div class="title-top">
                <div class="create-title">Title</div>
                <textarea v-model="post.title" id="title-form"></textarea>
            </div>
            <textarea v-model="post.body" id="create-content" name="content"></textarea>
            <v-file-input :rules="rules" v-if="this.$route.path === `/studygroup/${post.postid}/update`" v-model="imgFile" label="File input" id="file" outlined dense accept="image/png, image/jpeg, image/bmp"></v-file-input>
            <v-checkbox v-model="post.anonymous" label="익명" value="1" class="annoyCheck"/>

            <v-btn class="mr-5" color="primary" @click="update">수정</v-btn>
            <v-btn color="error" @click="goBack()">취소</v-btn>
            </div>
        <div v-else>
            <h1 style="text-align:center; color:red">접근 권한이 없습니다!</h1>
            <v-btn :to="{ name: 'home'}" style="margin:0 auto; display:block; width: 300px;" class="red"><h3>Home으로 이동</h3></v-btn>
        </div>
    </v-flex>
</template>

<script>
import axios from 'axios'
import router from '@/router.js'

export default {
    name: 'UpdateForm',
    data() {
        return {
            post : {},
            anonymousStatus : false,
            titleData: '',
            contentData: '',
            imgFile : null,
            from : null,
            rules: [
                function(value) {
                    const extension = value.name.toString().toLowerCase().split('.')[1]
                    if (!(extension === 'jpg' || extension === 'png' || extension === 'bmp')) {
                        alert("Please upload image file.(available jpg, png, bmp flie)")
                        document.querySelector('#file').value = '' // 실질적인 경로로 잡힌 파일 삭제
                        const filename = document.querySelector('.v-file-input__text')
                        filename.innerText = '' // html 문서에서 보이는 파일 이름도 삭제
                    }
                }
            ]
        }
    },
    props : {
        id : {type:String},
        boardname : {type:String},
    },
    beforeRouteEnter(to, from, next) {
        next((vm) => {
            vm.from = from;
        });
    },
    mounted() {
        this.getPost()
    },
    methods: {
        getPost() {
            axios.get(`api/post/${this.id}`)
                .then(response => { 
                this.post = response.data
          })
        },
        goBack() {
            const formTitle = document.querySelector('#form-title')
            if (formTitle.innerText === 'Board Form') {
                this.$router.push('/board')
            } else {
                this.$router.push('/studygroup')
            }
        },
        update() {
            if (this.$route.path === `/studygroup/${this.post.postid}/update`){
                var portfolioData = {
                    portfolioid: this.post.postid,
                    title: this.titleData,
                    body: this.contentData,
                    memberid: this.$store.state.memberid,
                    img: this.imgSrc
                }
                if(this.imgFile === null){
                    axios.put('api/portfolio', portfolioData)
                        .then(response => {
                            if(response.status === 200){
                                this.$router.push(`/studygroup/${this.post.postid}`)
                            }
                        })
                }
                else{
                    const formData = new FormData()
                    formData.append('image',this.imgFile)
                    axios.post('https://api.imgur.com/3/image', formData, {headers: { Authorization: 'Client-ID 347364b9fa38df3' }})
                        .then(response=>{
                        var imgLink = response.data.data.link
                        portfolioData['img'] = imgLink
                        axios.put('api/portfolio', portfolioData)
                            .then(response => {
                                if(response.status === 200){
                                    this.$router.push('/studygroup')
                                }
                            })
                        })
                        .catch(error =>{
                            console.log(error)
                        })
                }
            }else {
                axios.put('api/post', this.post)
                    .then(response => {
                        console.log(response.status)
                        if(response.status === 200){
                            this.$router.push(`/board/${this.post.categoryid}/${this.post.postid}`)
                        }
                    })
            }
        }
    }
}
</script>

<style>
#create-form-title {
    font-size: 2em;
    font-family: 'Do Hyeon', sans-serif;
    text-align: center;
    padding-top: 6px;
    padding-bottom: 10px;
}

.title-headline {
    border: none;
    border: 1px dashed lightgrey;
    margin-bottom: 20px;
}

.title-top {
    display: flex;
    margin-bottom: 20px;
}

.create-title {
    font-size: 20px;
    font-weight: bold;
    text-align: center;
    width: 15%;
}

@media (min-width: 555px) and (max-width: 880px) {
    .create-title {
        width: 10%;
    }
}

@media (min-width: 881px) {
    .create-title {
        width: 6%;
    }
}

#title-form {
    border: 1px solid black;
    width: 100%;
    height: 30px;
    resize: none;
    box-shadow: 5px 5px 7px grey;
}

#create-content {
    width: 100%;
    height: 300px;
    resize: none;
    border: 1px solid black;
    box-shadow: 5px 5px 7px grey;
    margin-bottom: 20px;
}
</style>