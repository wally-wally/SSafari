<template>
    <div class="d-flex post-index">
        <div class="main-post-section">
            <div v-if="this.showCreatePost === 0" class="wrap" style="display:block;">
                <h1 style="display: block;letter-spacing: -1px;">{{category.name}}</h1>
                <p style="display: block;">{{category.explanation}}</p>
            </div>
            <div class="post-header">
                <div class="lang-checkbox">
                    <v-container id="dropdown-lang">
                        <v-select
                            :items="languages"
                            label="Choose Language"
                            color="#f7b157"
                            target="#dropdown-lang"
                            v-model="selectLanguage"
                            >
                        </v-select>
                    </v-container>
                </div>
                <div class="code-post-count">{{ codes.length }}</div>
            </div>
            <router-link v-if="this.$store.state.isLogin" to="create">
                <div class="addButton mb-4" style="font-size:0.85em">코드 작성</div>
            </router-link>
            <v-layout>
                <v-flex>
                      <div v-for="code in codes" :key="code.id">
                          <router-link :to="`${code.id}`">
                            <v-card class="mb-3">
                                <v-card-actions>
                                    {{code.username}}
                                </v-card-actions>
                                <v-card-title>
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
        <SidePost v-if="this.mobile()" :categoryId="3" :locationId="0"/>
    </div>
</template>

<script>
    import axios from 'axios'
    import router from '@/router.js'
    import BoardList from '@/components/board/boardCommonForm/BoardList'
    import SidePost from '../SidePost'
    
    export default {
        name: 'CodeBoard',
        components: {
            BoardList,
            SidePost
        },
        // props: {
        //     category: { type: String },
        // },
        data() {
            return {
                category : {},
                codes : [],
                selectLanguage: 'All', // deafult를 로그인한 유저의 지역으로 하고 싶으면 이 부분 수정
                languages: ['All', 'Python', 'Java', 'JavaScript', 'HTML', 'C++'],
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
            this.categorydetail()
            this.getcodes()
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
                axios.get(`api/boardcategory/3`)
                .then(response=> {
                    this.category = response.data
                }).catch(error=>{console.log(error)})
            },
            getcodes() {
                axios.get('api/codes')
                .then(response => {
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
        },
        watch: {
            selectLanguage: {
                handler() {
                    axios.get('api/codes')
                        .then(response => {
                            const codesData = response.data
                            this.codes = []
                            let lang = { 'Python': 'x-python', 'Java': 'x-java', 'JavaScript': 'javascript', 'HTML': 'html', 'C++': 'x-c++src' }
                            if (this.selectLanguage !== 'All') {
                                let selectLangCodesData = []
                                for (let i = 0; i < codesData.length; i++) {
                                    if (codesData[i]['lang'].split('/')[1] === lang[this.selectLanguage]) {
                                        selectLangCodesData.push(codesData[i])
                                    }
                                }
                                this.codes = selectLangCodesData
                            } else {
                                this.codes = codesData
                            }
                        })
                }
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

    .code-post-count {
        font-size: 1.2em;
        font-weight: bold;
        font-family: 'Noto Sans KR', sans-serif;
        line-height: 4.6;
    }

    .code-post-count::after {
        content: '개의 Code Review';
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
            .code-post-count {
                font-size: 1.1em;
                line-height: 5.2;
            }

            .code-post-count::after {
                content: ' Codes';
            }
        }

        @media (max-width: 404px) {
            .code-post-count {
                font-size: 0.75em;
                line-height: 7.5;
            }

            .code-post-count::after {
                content: ' Codes';
            }
        }
    }

    #dropdown-lang {
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