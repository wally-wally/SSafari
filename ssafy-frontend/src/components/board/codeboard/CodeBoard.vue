<template>
    <div class="d-flex post-index">
        <div class="main-post-section">
            <div class="post-header">
                <h1>Code Review</h1>
                <div class="post-count">{{ codes.length }}</div>
            </div>
            <router-link to="create"><v-btn class="mb-3" color="primary">코드 작성</v-btn></router-link>
            <v-layout>
                <v-flex>
                      <div v-for="code in codes" :key="code.id">
                          <router-link :to="`${code.id}`">
                            <v-card class="mb-3">
                                <v-card-title>
                                {{code.memberid}}
                                {{code.title}}
                                </v-card-title>
                            <v-card-subtitle>
                                {{code.body}}
                            </v-card-subtitle>
                            </v-card>
                          </router-link>
                        </div>
                </v-flex>
            </v-layout>
        </div>
        <div class="side-post-section" style="margin-left: 3%;">
            <div class="popular-post">
                <div class="popular-title pb-2 ma-2">실시간 인기 TOP3</div>
                <div class="popular-post-list pt-2 ma-2">
                    <div class="popular-element popular-1 d-block">
                        <p>제목</p>
                        <p>작성자</p>
                    </div>
                    <div class="popular-element popular-2 d-block">
                        <p>제목</p>
                        <p>작성자</p>
                    </div>
                    <div class="popular-element popular-3 d-block">
                        <p>제목</p>
                        <p>작성자</p>
                    </div>
                </div>
            </div>
            <div class="best-post">
                <div class="best-title pb-2 ma-2">BEST 게시물</div>
                <div class="best-post-list pt-2 ma-2">
                    <div class="best-element best-1">
                        <p class="d-block">제목</p>
                        <p style="display: inline;">작성자</p>
                        <p style="display: inline;"><i class="far fa-heart" style="float: right;">100</i></p>
                    </div>
                    <div class="best-element best-2 d-block">
                        <p>제목</p>
                        <p style="display: inline;">작성자</p>
                        <p style="display: inline;"><i class="far fa-heart" style="float: right;">99</i></p>
                    </div>
                    <div class="best-element best-3 d-block">
                        <p>제목</p>
                        <p style="display: inline;">작성자</p>
                        <p style="display: inline;"><i class="far fa-heart" style="float: right;">76</i></p>
                    </div>
                    <div class="best-element best-4 d-block">
                        <p>제목</p>
                        <p style="display: inline;">작성자</p>
                        <p style="display: inline;"><i class="far fa-heart" style="float: right;">54</i></p>
                    </div>
                    <div class="best-element best-5 d-block">
                        <p>제목</p>
                       <p style="display: inline;">작성자</p>
                        <p style="display: inline;"><i class="far fa-heart" style="float: right;">32</i></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'
    import router from '@/router.js'
    import BoardList from '@/components/board/boardCommonForm/BoardList'
    export default {
        name: 'CodeBoard',
        components: {
            BoardList
        },
        // props: {
        //     category: { type: String },
        // },
        data() {
            return {
                codes : [],
                postCnt: 0,
                region: 'All', // deafult를 로그인한 유저의 지역으로 하고 싶으면 이 부분 수정
                regions: ['All', 'Seoul', 'Daejeon', 'Gawngju', 'Gumi'],
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
            this.getcodes()
            this.currentMemberId = this.$store.state.memberid
        },
        methods: {
            getcodes() {
                axios.get('api/codes')
                .then(response => {
                    console.log(response.data)
                    this.codes = response.data
                })
            },

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
                    memberid: this.$store.state.memberid,
                }
                axios.post('api/post', postData)
                    .then(response => {
                        if(response.status === 200){
                            this.$router.push('/board')
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
        line-height: 4.6;
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

    @media (max-width: 912px) {
        .main-post-section {
            width: 100%;
        }

        .side-post-section {
            display: none;
        }

        @media (max-width: 600px) {
            .post-count {
                font-size: 1.1em;
                line-height: 5.2;
            }

            .post-count::after {
                content: ' Posts';
            }
        }

        @media (max-width: 404px) {
            .post-count {
                font-size: 0.75em;
                line-height: 7.5;
            }

            .post-count::after {
                content: ' Posts';
            }
        }
    }

    #dropdown-region {
        padding-left: 0;
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

    .popular-post,
    .best-post {
        margin-bottom: 18px;
        border: 2.5px solid grey; 
        background-color: #E8E8E0;
        box-shadow: 3px 3px 5px lightgray;
    }

    .popular-title,
    .best-title {
        font-size: 1.2rem;
        font-weight: bold;
        border-bottom: 1.5px dashed #BEC4CA;
    }

    .popular-element:first-child,
    .popular-element:nth-child(2),
    .best-element:first-child,
    .best-element:nth-child(2),
    .best-element:nth-child(3),
    .best-element:nth-child(4) {
        padding-bottom: 4px;
        border-bottom: 1px solid lightgrey;
        margin-bottom: 8px;
    }

    .popular-element:first-child,
    .best-element:first-child {
        overflow: hidden;
        text-overflow: ellipsis;
        display: -webkit-box;
        -webkit-line-clamp: 1;
        -webkit-box-orient: vertical;
    }

    .popular-post-list p,
    .best-post-list p {
        font-size: 0.8em;
        margin-bottom: 1px;
    }

    .best-element > p:nth-child(3) > i {
        color: crimson;
    }
</style>