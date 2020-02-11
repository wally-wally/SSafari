<template>
    <div>
        <div class="boardlist">
            <p>자유게시판</p>
            <BoardList :category="'free'" :limits="5" :region="'All'" :load-more="true"></BoardList>
        </div>
        <div class="boardlist">
            <p>취업게시판</p>
            <BoardList :category="'job'" :limits="5" :region="'All'" :load-more="true"></BoardList>
        </div>
        <div class="boardlist">
            <p>주변맛집</p>
            <BoardList :category="'jmt'"  :limits="5" :region="'All'" :load-more="true"></BoardList>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'
    import router from '@/router.js'
    import BoardList from '@/components/board/boardCommonForm/BoardList'
    export default {
        name: 'AllBoard',
        components: {
            BoardList,
        },
        data() {
            return {
                showCreatePost: 0
            }
        }
    }
</script>

<style>
    .boardlist {
        margin: 5px 5%;
        padding-bottom: 10px;
    }

    .boardlist > p {
        font-size: 1.5rem;
        font-weight: bold;
        font-family: 'Noto Sans KR';
        text-shadow: 3px 3px 5px lightgrey;
        text-align: center;
    }
</style>