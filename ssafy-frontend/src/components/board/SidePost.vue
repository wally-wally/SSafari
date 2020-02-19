<template>
	<div style="margin-left: 3%;width:20em;">
		<div class="card">
            <div class="board">
                <h3><a>실시간 인기 글</a></h3>
                <a class="articleb" v-for="comment in popularComments" :key="comment.id">
				<router-link :to="`${comment.postid}`">
                <p class="titleb">{{comment.title}}</p>
                <p class="small">{{comment.body}}</p>
                <h4>{{comment.categoryname}}</h4>
                <ul class="status"><li class="vote active">{{ comment.likes }}</li><li class="comment active">{{ comment.comments }}</li></ul><hr>
                </router-link>
				</a>
			</div>
		</div>
		<div class="card">
            <div class="board">
                <h3><a>이번 주 BEST 게시물</a></h3>
                <a class="articleb" v-for="like in popularLikes" :key="like.id">
				<router-link :to="`${like.postid}`">
                <p class="titleb">{{like.title}}</p>
                <p class="small">{{like.body}}</p>
                <h4>{{like.categoryname}}</h4>
                <ul class="status"><li class="vote active">{{ like.likes }}</li><li class="comment active">{{ like.comments }}</li></ul><hr>
                </router-link>
				</a>
			</div>
		</div>
	</div>
</template>

<script>
	import axios from 'axios'

	export default {
		name: 'SidePost',
		props: {
			categoryId: {
				type: Number
			},
			locationId: {
				type: Number
			}
		},
		data() {
			return {
				popularComments: {},
				popularLikes: {}
			}
		},
		mounted() {
			this.getTopThreeComments()
			this.getTopFiveLikes()
		},
		methods: {
			getTopThreeComments() {
				axios.get(`api/popular/comment`, {
						params: {
							locationid: this.locationId,
							categoryid: this.categoryId
						}
					})
					.then(response => {
						this.popularComments = response.data
					})
			},
			getTopFiveLikes() {
				axios.get('api/popular/likes', {
						params: {
							locationid: this.locationId,
							categoryid: this.categoryId
						}
					})
					.then(response => {
						this.popularLikes = response.data
					})
			}
		},
		watch: {
			categoryId: function () {
				this.getTopThreeComments()
				this.getTopFiveLikes()
			},
			locationId: function () {
				this.getTopThreeComments()
				this.getTopFiveLikes()
			}
		},

	}
</script>

<style>
    .card{
        margin-bottom: 5px;
        border: 1px solid #d6d6d6;
        background-color: #f9f9f9;
        display: block;
    }
    div.card h3 {
        padding: 10px;
    }
    div.card h3 > a {
        color: #3744a5;
        line-height: 20px;
        font-size: 14px;
        font-weight: bold;
        letter-spacing: -1px;
    }
    div.board > a {
        display: block;
        padding: 10px;
        border-top: 1px solid #e3e3e3;
        color: #666;
        text-decoration: none;
        -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
    }

    
    div.card > div.board > a.articleb > a > p.titleb {
        height: 18px;
        font-weight: bold;
        white-space: nowrap;
        text-overflow: ellipsis;
        line-height: 1.2 !important;
        
    }
    div.card > div.board > a.articleb > a > p { 
        margin-bottom: 5px;
        line-height: 16px;
        overflow: hidden;
        color: #4c4c4c;
        font-size: 12px;
        display: block;
        line-height: 1.2 !important;
    }
    div.card > div.board > a.articleb > a > p.small {
        margin-bottom: 5px;
        line-height: 16px;
        overflow: hidden;
        color: #4c4c4c;
        font-size: 12px;
        display: block;
		text-overflow: ellipsis;
		word-wrap: break-word;
		white-space: pre;
		display: -webkit-box;
		-webkit-line-clamp: 2;
		-webkit-box-orient: vertical;
    }
    div.card > div.board > a.articleb > a > h4 {
        float: left;
        margin-right: 10px;
        height: 14px;
        line-height: 14px;
        color: #a6a6a6;
        font-size: 11px;
        font-weight: bold;
        letter-spacing: 0;
        display: block;
        margin-inline-start: 0px;
        margin-inline-end: 0px;
    }
    div.card > div.board > a.articleb > a > ul.status {
        float: left;
		display: block;
        margin-inline-start: 0px;
        margin-inline-end: 0px;
        padding-inline-start: 40px;
        list-style: none;
    }
    a.articleb > a > ul.status > li {
        float: left;
        margin-right: 5px;
        padding-left: 15px;
        height: 14px;
        line-height: 14px;
        font-size: 11px;
        letter-spacing: 0;
        background-repeat: no-repeat;
        background-size: 12px 12px;
        background-position: left center;
    }
    hr {
        clear: both;
        width: 100%;
        height: 0;
        border: 0;
        display: block;
        unicode-bidi: isolate;
        margin-block-start: 0.5em;
        margin-block-end: 0.5em;
        margin-inline-start: auto;
        margin-inline-end: auto;
        overflow: hidden;
    }
    div.card > div.board > a.articleb > a > ul.status > li.vote.active {
        color: #c62917;
        background-image: url(https://everytime.kr/images/new/container.articles.vote.active.png);
    }
    div.card > div.board > a.articleb > a > ul.status > li.comment.active {
        color: #0ca5af;
        background-image: url(https://everytime.kr/images/new/container.articles.comment.active.png);
    }
</style>