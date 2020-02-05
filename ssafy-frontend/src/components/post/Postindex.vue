<template>
    <div class="d-flex post-index">
        <div class="main-post-section">
            <div class="post-header">
                <div class="region-checkbox">
                    <v-radio-group v-model="region" row>
                        <v-radio label="전체" value="All"></v-radio>
                        <v-radio label="서울" value="Seoul"></v-radio>
                        <v-radio label="대전" value="Daejeon"></v-radio>
                        <v-radio label="광주" value="Gawngju"></v-radio>
                        <v-radio label="구미" value="Gumi"></v-radio>
                    </v-radio-group>
                </div>
                <div class="post-count">{{ postCnt }}</div>
                <!-- <span class="post-btn">
                    <router-link to="post/create">
                        <v-flex xs12 text-xs-center round my-5 style="display: inline;">
                            <v-btn color="#f7b157" dark>Post 작성</v-btn>
                        </v-flex>
                    </router-link>
                </span> -->
            </div>
            <div v-if="currentMemberId !== null" class="create-post">
                <div v-if="this.showCreatePost === 0" class="init d-flex justify-space-between" @click="hideInitPostForm">
                    <div class="write-phrase d-inline">이 곳에 새 글을 작성하세요.</div>
                    <div class="write-icon d-inline"><i class="fas fa-pencil-alt"></i></div>
                </div>
                <form v-else class="create-post-form">
                    <p><input v-model="title" name="post-form-title" placeholder="제목을 작성하세요." class="post-form-title"></p>
                    <p>
                        <textarea v-model="content" name="post-form-contents" class="post-form-contents"
                                  placeholder="이 곳에 내용을 작성하세요."></textarea>
                    </p>
                    <div class="post-form-footer d-flex">
                        <div class="post-form-footer-att-ann d-flex col-10">
                            <div class="post-footer-attach d-inline col-8">
                                <v-file-input :rules="rules" v-model="imgFile" label="File input" class="post-attach-file" outlined dense accept="image/png, image/jpeg, image/bmp"></v-file-input>
                            </div>
                            <div class="post-footer-annoymous d-inline col-4">
                                <v-checkbox v-model="annoymousStatus" label="익명" value="익명" class="annoyCheck"></v-checkbox>
                            </div>
                        </div>
                        <div class="post-form-submit d-inline col-2">
                            <v-btn class="submit-button" color="primary" @click="create">작성</v-btn>
                        </div> 
                    </div>
                </form>
            </div>
            <div v-else>
                <div class="init d-flex justify-space-between">
                    <div class="write-phrase d-inline">글을 작성하려면 로그인을 먼저 하세요.</div>
                </div>
            </div>
            <v-layout>
                <v-flex>
                    <PostList @showPostCount="onPostCount" :limits="6" :load-more="true"></PostList>
                </v-flex>
            </v-layout>
        </div>
        <div class="side-post-section" style="margin-left: 3%;">
            <div class="popular-post">TEST</div>
            <div class="best-post">TEST</div>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'
    import router from '@/router.js'
    import PostList from '@/components/post/PostList'
    export default {
        name: 'Postindex',
        components: {
            PostList,
        },
        data() {
            return {
                postCnt: 0,
                region: 'All', // deafult를 로그인한 유저의 지역으로 하고 싶으면 이 부분 수정
                showCreatePost: 0,
                annoymousStatus: false,
                currentMemberId: null,
                title: '',
                content: '',
                from: null,
                rules: [
                    function(value) {
                        const extension = value.name.toString().split('.')[1]
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
        beforeRouteEnter(to, from, next) {
            next((vm) => {
                vm.from = from
            })
        },
        mounted() {
            this.currentMemberId = this.$session.get('data')['memberid']
        },
        methods: {
            onPostCount(value) {
                this.postCnt = value
            },
            hideInitPostForm() {
                this.showCreatePost = 1
            },
            create() {
                let postData = {
                    title: this.title,
                    body: this.content,
                    memberid: this.$session.get('data')['memberid']
                }
                axios.post('api/post', postData)
                    .then(response => {
                        if(response.status === 200){
                            this.$router.push('/post')
                        }
                    })
            }
        }
    }
</script>

<style>
    .post-index {
        margin: 0 5%;
    }

    .post-header {
        display: flex;
        justify-content: space-between;
    }

    .post-count {
        font-size: 1.2em;
        font-weight: bold;
        font-family: 'Noto Sans KR', sans-serif;
        line-height: 3.3;
    }

    @media (max-width: 528px) {
        .post-count {
            display: none;
        }
    }

    .post-count::after {
        content: '개의 Post 게시글';
    }

    .main-post-section {
        width: 75%;
    }

    .side-post-section {
        width: 25%;
    }

    @media (max-width: 949px) {
        .post-count {
            font-size: 1em;
            line-height: 3.8;
        }

        .post-count::after {
            content: ' Posts';
        }

        .main-post-section {
            width: 100%;
        }

        .side-post-section {
            display: none;
        }
    }

    .init {
        background-color: #DDE1E1; 
        padding: 10px 10px;
        border: 3px solid grey;
    }

    .create-post-form {
        background-color: #FFFFFF; 
        padding: 10px 10px 0;
        border: 3px solid grey;
    }

    .init:hover {
        cursor: text;
    }

    .post-form-title,
    .post-form-contents {
        width: 100%;
        resize: none;
        padding-bottom: 5px;
        border-bottom: 2px solid lightgray;
    }

    .post-form-contents {
        height: 300px;
    }

    .post-form-footer-att-ann,
    .post-form-submit,
    .post-footer-attach,
    .post-attach-file {
        padding: 0;
    }

    .post-footer-annoymous {
        padding: 0 0 0 10px;
    }

    .submit-button {
        float: right;
    }

    .annoyCheck {
        margin: 0;
    }
    
    /* side-post-section 테스트를 위한 css 구문 */
    .popular-post,
    .best-post {
        height: 250px;
        margin-bottom: 50px;
        border: 3px solid orange; 
        background-color: lightgoldenrodyellow;
    }
</style>