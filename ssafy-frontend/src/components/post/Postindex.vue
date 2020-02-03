<template>
<div>
    <div class="post-header" style="padding: 0 10%;">
        <span class="post-count">{{ postCnt }}개의 Post 게시글</span>
        <span class="post-btn">
            <router-link to="post/create">
                <v-flex xs12 text-xs-center round my-5 style="display: inline;">
                <v-btn color="#f7b157" dark>Post 작성</v-btn>
                </v-flex>
            </router-link>
        </span>
    </div>
    <v-layout>
        <v-flex xs12>
        <PostList @showPostCount="onPostCount" :limits="6" :load-more="true"></PostList>
        </v-flex>
    </v-layout>
</div>
</template>

<script>
import PostList from '@/components/post/PostList'
export default {
    name : 'Postindex',
    components: {
		PostList,
    },
    data() {
        return {
            postCnt: 0
        }
    },
    methods: {
        onPostCount(value) {
            this.postCnt = value
        }
    }
}
</script>

<style>
    .post-header {
        display: flex;
        justify-content: space-between;
    }

    .post-count {
        font-size: 1.2em;
        font-weight: bold;
        font-family: 'Noto Sans KR', sans-serif;
        line-height: 2.4;
    }
</style>