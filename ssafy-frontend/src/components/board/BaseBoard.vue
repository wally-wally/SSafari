<template>
    <div class="d-flex post-index">
        <div class="main-post-section">
            <div v-if="this.showCreatePost === 0" class="wrap" style="display:block;">
                    <h1 style="display: block;letter-spacing: -1px;">{{category.name}}</h1>
                    <p style="display: block;">{{category.explanation}}</p>
            </div>
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
            <div class="mt-4" style="display:inline-block;">
                <v-text-field style="display:inline-block" class="pa-0 ma-0 search-board-keyword"
                    hide-details
                    single-line
                    v-model="pageData.keyword"
                    color="#ffc837"
                    placeholder="검색어를 입력해주세요"
                    >
                </v-text-field>
                <i class="fas fa-search" @click="changePageIndex(2)"></i>
            </div>
            </div>
            <div v-if="currentMemberId !== null && this.selectRegion !== 'All'" class="create-post">
                <div v-if="this.showCreatePost === 0" id="writeArticleButton" class="d-flex justify-space-between" style="padding:15px" @click="hideInitPostForm">
                    이 곳에 새 글을 작성하세요.
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
                    <div class="row justify-end mr-3">
                            <v-checkbox dense hide-details="1" color="#ffc837" v-model="annoymousStatus" label="익명" value="익명" class="annoyCheck"></v-checkbox>
                            <v-btn style="height:30px;color:#4c4c4c;" class="ml-3 mb-2 submit-button" color="#ffc837" @click="create">작성</v-btn>
                    </div>
                </div>
            </div>
            <div v-else-if="this.selectRegion !== '0'">
                <div class="d-flex justify-space-between" id="writeArticleButton" style="padding:15px">
                    글을 작성하려면 로그인을 먼저 하세요.
                </div>
            </div>
            <div v-else>
                <div class="d-flex justify-space-between" id="writeArticleButton" style="padding:15px">
                    글을 작성하려면 지역을 선택해 주세요.
                </div>
            </div>
            <v-layout>
                <v-flex>
                    <!-- @showPostCount="onPostCount" :limits="5" :load-more="true" -->
                    <BoardList :category="category" :boardname="boardname" :boards="boards"></BoardList>
                </v-flex>
            </v-layout>
            <div align="center" class="mt-3 mb-5">
                <i class="v-icon mdi mdi-chevron-left" @click="changePageIndex(-1)" :class="{icondeactive:(this.pageData.page >= 2) ? 0: 1}"></i>
                <span class="mx-5">{{ pageData.page }}</span>
                <i class="v-icon mdi mdi-chevron-right" @click="changePageIndex(1)" :class="{icondeactive:!this.pagniationStatus}"></i>
            </div>
        </div>
        <SidePost v-if="mobile()" :categoryId="pageData.categoryid" :locationId="0"/>
    </div>
</template>

<script>
    import axios from 'axios'
    import router from '@/router.js'
    import BoardList from '@/components/board/boardCommonForm/BoardList'
    import SidePost from './SidePost'

    export default {
        name: 'BaseBoard',
        components: {
            BoardList,
            SidePost
        },
        props: {
            boardname: { type: String },
        },
        data() {
            return {
                category  : {},
                regions : [
                    { name : 'All' , val : 0},
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
                this.categorydetail()
                this.showCreatePost = 0
                this.title = ''
                this.content = ''
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
            this.categorydetail()
            this.changePageIndex(0)
            this.currentMemberId = this.$store.state.memberid
        },
        methods: {
            mobile() {
                if (this.$vuetify.breakpoint.name === "xs"||this.$vuetify.breakpoint.name === "sm") {
                    return false;
                }
                return true;
                },
            categorydetail() {
                axios.get(`api/boardcategory/${this.pageData.categoryid}`)
                .then(response=> {
                    this.category = response.data
                }).catch(error=>{console.log(error)})
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
                } else if (status === -1 ) {
                    this.pageData.page = this.pageData.page - 1 === 0 ? 1 : this.pageData.page - 1
                } else if (status === 1 && this.pagniationStatus) {
                    this.pageData.page += 1
                } else if (status === 1) {

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
        font-size : 16px;
        height: 300px;

    }

    .post-form-title{
        width: 100%;
        line-height: 20px;
        border: 0;
        color: #292929;
        font-size: 16px;
        font-weight: bold;
    }
textarea { font-size: 180px; }

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
        float: right;
        margin-right: 15px;
    }

    .submit-button {
        float: right;
        background-image: url(/images/new/container.articles.write.submit.png);
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
    textarea::placeholder {
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

    textarea {
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
    font: 400 20px Arial;
    }

    #writeArticleButton {
    cursor: text;
    color: #a6a6a6;
    font-size: 14px;
    background: #f9f9f9
    }
    div.wrap {
    margin-top: 25px;
    }
    .icondeactive {
        color : #d6d6d6;
    }
    .icondeactive:hover {
        cursor : default;
    }
</style>
