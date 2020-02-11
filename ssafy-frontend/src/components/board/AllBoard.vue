<template>
    <div class="row mx-4">
        <div class="col-12 col-lg-6 boardlist">
            <p>자유게시판</p>
            <BoardListAll :boards="freeBoard" :boardType="'free'"/>
        </div>
        <div class="col-12 col-lg-6 boardlist">
            <p>취업게시판</p>
            <BoardListAll :boards="jobBoard" :boardType="'job'"/>
        </div>
        <div class="col-12 col-lg-6 boardlist">
            <p>코드리뷰</p>
            <BoardListAll :boards="codeBoard" :boardType="'codereview'"/>
        </div>
        <div class="col-12 col-lg-6 boardlist">
            <p>주변맛집</p>
            <BoardListAll :boards="jmtBoard" :boardType="'jmt'"/>
        </div>
    </div>
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
                freeBoard: null,
                jobBoard: null,
                codeBoard: null,
                jmtBoard: null,
            }
        },
        mounted() {
            axios.get(`api/posts/1`)
                .then(response => {
                    this.freeBoard = response.data.slice(0, 5)
                })
            axios.get(`api/posts/2`)
                .then(response => {
                    this.jobBoard = response.data.slice(0, 5)
                })
            axios.get(`api/codes`)
                .then(response => {
                    this.codeBoard = response.data.slice(0, 5)
                })
            axios.get(`api/jmts`)
                .then(response => {
                    this.jmtBoard = response.data.slice(0, 5)
                })
        }
    }
</script>

<style>
    .boardlist > p {
        font-size: 1.5rem;
        font-weight: bold;
        font-family: 'Noto Sans KR';
        text-shadow: 3px 3px 5px lightgrey;
        text-align: center;
    }
</style>