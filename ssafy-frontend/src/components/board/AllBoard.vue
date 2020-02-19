<template>
    <v-container class="row board-cards">
        <div class="col-12 col-sm-6 board">
            <div class="boarddiv">
            <v-card-title class="main-board-title">자유게시판
                <router-link class="moreboard" to="board/free">
                <span style="color: #ff8008">
                    더보기
                </span>
                </router-link>
            </v-card-title>
            <v-divider></v-divider>
            <table class="freeboard">
                <thead>
                    <td>title</td>
                    <td>date</td>
                </thead>
                <tbody v-for="board in freeBoard" :key="board.postid">
                    <tr class="board-table-row" @click="goBoardDetail('free', board.postid)">
                        <td>{{ board.title }}</td>
                        <td>{{ board.created_at }}</td>
                    </tr>
                </tbody>
            </table>
            </div>
        </div>
        <div class="col-12 col-sm-6 board">
            <div class="boarddiv">
            <v-card-title class="main-board-title">취업게시판
                <router-link class="moreboard" to="board/job">
                    <span style="color: #ff8008">
                        더보기
                    </span>
                </router-link>
            </v-card-title>
            <v-divider></v-divider>
            <table class="jobboard">
                <thead>
                    <td>title</td>
                    <td>date</td>
                </thead>
                <tbody v-for="board in jobBoard" :key="board.postid">
                    <tr class="board-table-row" @click="goBoardDetail('free', board.postid)">
                        <td>{{ board.title }}</td>
                        <td>{{ board.created_at }}</td>
                    </tr>
                </tbody>
            </table>
            </div>
        </div>
        <div class="col-12 col-sm-6 board">
            <div class="boarddiv">

            <v-card-title class="main-board-title">코드리뷰
                <router-link class="moreboard" to="board/code">
                <span style="color: #ff8008">
                    더보기
                </span>
                </router-link>
            </v-card-title>
            <v-divider></v-divider>
            <table class="codeboard">
                <thead>
                    <td>title</td>
                    <td>date</td>
                </thead>
                <tbody v-for="board in codeBoard" :key="board.postid">
                    <tr class="board-table-row" @click="goBoardDetail('code', board.postid)">
                        <td>{{ board.title }}</td>
                        <td>{{ board.created_at }}</td>
                    </tr>
                </tbody>
            </table>
            </div>
        </div>
        <div class="col-12 col-sm-6 board">
            <div class="boarddiv">
            <v-card-title class="main-board-title">주변맛집
                <router-link class="moreboard" to="board/jmt">
                <span style="color: #ff8008">
                    더보기
                </span>
                </router-link>
            </v-card-title>
            <v-divider></v-divider>
            <table class="jmtboard">
                <thead>
                    <td>title</td>
                    <td>date</td>
                </thead>
                <tbody v-for="board in jmtBoard" :key="board.postid">
                    <tr class="board-table-row" @click="goBoardDetail('jmt', board.postid)">
                        <td>{{ board.title }}</td>
                        <td>{{ board.created_at }}</td>
                    </tr>
                </tbody>
            </table>
            </div>
        </div>
        <!-- <div class="col-12 col-lg-6 boardlist">
            <p>자유게시판</p>
            <BoardListAll :boards="freeBoard" :boardType="'free'"/>
        </div>
        <div class="col-12 col-lg-6 boardlist">
            <p>취업게시판</p>
            <BoardListAll :boards="jobBoard" :boardType="'job'"/>
        </div>
        <div class="col-12 col-lg-6 boardlist">
            <p>코드리뷰</p>
            <BoardListAll :boards="codeBoard" :boardType="'code'"/>
        </div>
        <div class="col-12 col-lg-6 boardlist">
            <p>주변맛집</p>
            <BoardListAll :boards="jmtBoard" :boardType="'jmt'"/>
        </div> -->
    </v-container>
</template>

<script>
    import axios from 'axios'
    import router from '@/router.js'
    import BoardListAll from '@/components/board/boardCommonForm/BoardListAll'
    export default {
        name: 'AllBoard',
        components: {
            BoardListAll,
        },
        data() {
            return {
                headers: [
                    { text: 'title', align: 'left', value: 'title' },
                    { text: 'date', value: 'created_at' }
                ],
                freeBoard: [],
                jobBoard: null,
                codeBoard: null,
                jmtBoard: null,
            }
        },
        mounted() {
            axios.get(`api/posts/1`)
                .then(response => {
                    let mainPageBoards = []
                    response.data.slice(0, 5).forEach(bdata => {
                        mainPageBoards.push({
                            'title': bdata.title,
                            'created_at': bdata.created_at.slice(2, 16),
                            'postid': bdata.postid
                        })
                    })
                    this.freeBoard = mainPageBoards
                })
            axios.get(`api/posts/2`)
                .then(response => {
                    let mainPageBoards = []
                    response.data.slice(0, 5).forEach(bdata => {
                        mainPageBoards.push({
                            'title': bdata.title,
                            'created_at': bdata.created_at.slice(2, 16),
                            'postid': bdata.postid
                        })
                    })
                    this.jobBoard = mainPageBoards
                })
            axios.get(`api/codes`)
                .then(response => {
                    let mainPageBoards = []
                    response.data.slice(0, 5).forEach(bdata => {
                        mainPageBoards.push({
                            'title': bdata.title,
                            'created_at': bdata.created_at.slice(2, 16),
                            'postid': bdata.id
                        })
                    })
                    this.codeBoard = mainPageBoards
                })
            axios.get(`api/jmts`)
                .then(response => {
                    let mainPageBoards = []
                    console.log(response)
                    response.data.slice(0, 5).forEach(bdata => {
                        mainPageBoards.push({
                            'title': bdata.name,
                            'created_at': bdata.created_at.slice(2, 16),
                            'postid': bdata.id
                        })
                    })
                    this.jmtBoard = mainPageBoards
                })
        },
        methods: {
            goBoardDetail(boardType, boardID) {
                this.$router.push(`/board/${boardType}/${boardID}`)
            }
        }
    }
</script>

<style>
    .board-cards {
        text-align: center;
        display: flex;
        margin: 0 auto;
    }
    
    .main-board-title {
        font-family: 'Noto Sans KR', sans-serif;
        font-weight: bold;
    }

    .main-board-subtitle {
        font-family: 'Nanum Myeongjo', serif;
    }

    table[class$='board'] {
        width: 100%;
        padding: 3px;
    }

    table[class$='board'] > thead > td:first-child {
        width: 63%;
    }

    table[class$='board'] > thead > td:last-child {
        width: 37%;
    }

    table[class$='board'] > tbody > .board-table-row:hover {
        background-color: lightgray;
        cursor: pointer;
    }

    table[class$='board'] > tbody > tr > td:first-child {
        font-size: 14px;
        text-align: left;
        padding-left: 5px;
        overflow: hidden;
        text-overflow: ellipsis;
        display: -webkit-box;
        -webkit-line-clamp: 1;
        -webkit-box-orient: vertical;
    }

    table[class$='board'] > tbody > tr > td:last-child {
        font-size: 14px;
    }
    .moreboard{
        margin-left:auto;
        font-size:0.9rem
    }
</style>