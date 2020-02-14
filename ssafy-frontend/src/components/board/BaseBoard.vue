<template>
    <div class="d-flex post-index">
        <div class="main-post-section">
            <div class="post-header">
                <div class="region-checkbox">
                    <v-container id="dropdown-region">
                        <v-select
                            :items="regions"
                            item-text="name"
                            item-value="val"
                            label="Choose Region"
                            color="#f7b157"
                            target="#dropdown-region"
                            v-model="selectRegion"
                            >
                        </v-select>
                    </v-container>
                </div>
                <div class="post-count">{{ boardCount }}</div>
            </div>
            <div class="d-flex justify-space-between pb-4">
                <v-flex>
                    <v-btn class="board-go-first" v-if="pageData.page >= 3" @click="changePageIndex(0)">처음</v-btn>
                    <v-btn class="board-go-prev" v-if="pageData.page >= 2" @click="changePageIndex(-1)">이전</v-btn>
                    <v-btn class="board-go-next" v-if="pagniationStatus" @click="changePageIndex(1)">다음</v-btn> <!-- v-if="pagniationStatus" -->
                    {{ pageData.page }}[page]
                </v-flex>
                <v-text-field class="pa-0 ma-0 search-board-keyword"
                    hide-details
                    single-line
                    v-model="pageData.keyword">
                </v-text-field>
                <i class="fas fa-search" @click="changePageIndex(2)"></i>
            </div>
            <div v-if="currentMemberId !== null && this.selectRegion !== 'All'" class="create-post">
                <div v-if="this.showCreatePost === 0" class="d-flex justify-space-between" @click="hideInitPostForm">
                    <div class="write-phrase d-inline">이 곳에 새 글을 작성하세요.</div>
                    <div class="write-icon d-inline"><i class="fas fa-pencil-alt"></i></div>
                </div>
                <div v-else>
                    <p><input v-model="title" name="post-form-title" placeholder="제목을 작성하세요." class="post-form-title" style="padding:15px;letter-spacing: -1px;"></p>
                    <p>
                        <textarea v-model="content" name="post-form-contents" class="post-form-contents" style="padding:15px;letter-spacing: -1px;"
                                  placeholder="여기를 눌러서 글을 작성할 수 있습니다. 
아래 내용을 모두 지켜서 게시글을 작성해주세요 :) 

[정보통신망법에 의한 홍보 게시물 작성 금지] 
1. 커뮤니티, 어플, 쇼핑몰, 카페, 블로그 홍보 및 방문 유도 
2. 토익, 한자 등 어학원 홍보 
3. 연극, 영화 티켓 할인 서비스 홍보 
4. 안과, 치과, 피부과 등 의료업체 홍보 

[커뮤니티 이용규칙에 어긋나는 행위 금지] 
1. 욕설, 비하, 음란물, 개인정보가 포함된 게시물 게시 
2. 특정인이나 단체/지역을 비방하는 행위 
3. 기타 현행법에 어긋나는 행위"></textarea>
                    </p>
                    <div class="post-form-footer d-flex">
                        <div class="post-form-footer-att-ann d-flex col-10">
                            <!-- <div class="post-footer-attach d-inline col-8">
                                <v-file-input :rules="rules" v-model="imgFile" label="File input" class="post-attach-file" outlined dense accept="image/png, image/jpeg, image/bmp"></v-file-input>
                            </div> -->
                            <div class="post-footer-annoymous d-inline col-4">
                                <v-checkbox v-model="annoymousStatus" label="익명" value="익명" class="annoyCheck"></v-checkbox>
                            </div>
                        </div>
                        <div class="post-form-submit d-inline col-2">
                            <v-btn class="submit-button" color="primary" @click="create">작성</v-btn>
                        </div> 
                    </div>
                </div>
            </div>
            <div v-else-if="this.selectRegion !== 'All'">
                <div class="d-flex justify-space-between">
                    <div class="write-phrase d-inline">글을 작성하려면 로그인을 먼저 하세요.</div>
                </div>
            </div>
            <div v-else>
                <div class="d-flex justify-space-between">
                    <div class="write-phrase d-inline">글을 작성하려면 지역을 선택해 주세요.</div>
                </div>
            </div>
            <v-layout>
                <v-flex>
                    <!-- @showPostCount="onPostCount" :limits="5" :load-more="true" -->
                    <BoardList :boardname="boardname" :boards="boards"></BoardList>
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
        name: 'BaseBoard',
        components: {
            BoardList,
        },
        props: {
            boardname: { type: String },
        },
        data() {
            return {
                regions : [
                    {name : 'All' , val : 0},
                    { name : 'Seoul' , val : 1},
                    { name : 'Daejeon' , val : 2},
                    { name : 'Gumi' , val : 3},
                    { name : 'Gawngju', val : 4}
                ],
                pageData : {
                    page: 1,
                    categoryid: (Number(this.boardname) >= 5) ?  Number(this.boardname) : Number(this.$store.state.category[this.boardname]),
                    keyword: null,
                    locationid: 0 // deafult를 로그인한 유저의 지역으로 하고 싶으면 이 부분 수정
                },
                selectRegion : 0,
                boards: [],
                searchKeyword: '',
                showCreatePost: 0,
                boardCount: 0,
                pagniationStatus: false,
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
        watch : {
            boardname() {
                this.pageData.categoryid = (Number(this.boardname) >= 5) ?  Number(this.boardname) : Number(this.$store.state.category[this.boardname])
                this.changePageIndex(0)
            },
            selectRegion: {
                handler() {
                    this.pageData.keyword = null
                    this.pageData.locationid = this.selectRegion
                    this.changePageIndex(0)
                }
            }
        },
        mounted() {
            this.changePageIndex(0)
            this.currentMemberId = this.$store.state.memberid
        },
        methods: {
            onPostCount(value) {
                this.postCnt = value
            },
            hideInitPostForm() {
                this.showCreatePost = 1
            },
            create() {
                let boardData = {
                    'title': this.title,
                    'body': this.content,
                    'anonymous': this.annoymousStatus ? 1 : 0,
                    'memberid': this.$store.state.memberid,
                    'categoryid': (Number(this.boardname) >= 5) ?  Number(this.boardname): Number(this.$store.state.category[this.boardname]),
                    'locationid': this.selectRegion
                }
                axios.post('api/post', boardData)
                    .then(response => {
                        if(response.status === 200){
                            router.go('/board/free')
                        }
                    })
            },
            changePageIndex(status) {
                if (status === 0) {
                    this.pageData.page = 1
                } else if (status === -1) {
                    this.pageData.page = this.pageData.page - 1 === 0 ? 1 : this.pageData.page - 1
                } else if (status === 1) {
                    this.pageData.page += 1
                } else {
                    if (this.pageData.keyword) {
                        this.pageData.page =1
                    } else {
                        alert('검색어를 입력하세요.')
                    }
                }
                axios.get('api/nextpage', {params:this.pageData})
                    .then(response => {
                        this.pagniationStatus = response.data
                    })

                axios.get('api/posts/page', {params:this.pageData})
                    .then(response => {
                        console.log(response)
                        this.boards = response.data
                        this.boardCount = this.boards.length
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
        content: '개의 게시글';
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
    }
    .create-post{
        position: relative;
        margin-bottom: 5px;
        border: 2px solid #d6d6d6;
        box-sizing: border-box;
    }

    .init:hover {
        cursor: text;
    }

    .post-form-title
    .post-form-contents {
        width: 100%;
        padding-bottom: 5px;
        border-bottom: 2px solid lightgray;
    }

    .post-form-title{
        width: 100%;
        line-height: 20px;
        border: 0;
        color: #292929;
        font-size: 16px;
        font-weight: bold;
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
    .v-application p {
    margin-bottom: 0px; 
    }
    textarea{
    text-rendering: auto;
    word-spacing: normal;
    text-transform: none;
    text-indent: 0px;
    text-shadow: none;
    display: inline-block;
    text-align: start;
    -webkit-appearance: textarea;
    background-color: -internal-light-dark-color(white, black);
    -webkit-rtl-ordering: logical;
    flex-direction: column;
    cursor: text;
    white-space: pre-wrap;
    overflow-wrap: break-word;
    font: 400 13.3333px Arial;
    }
</style>
