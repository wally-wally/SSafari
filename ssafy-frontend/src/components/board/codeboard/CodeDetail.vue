<template>
<v-container>
	<div class="code-detail-header">
		<div class="code-detail-header-1 d-inline"><v-chip class="mr-2" :color="getColor(code.lang)" style="color: white;">{{ this.langchange[code.lang] }}</v-chip> | <span class="ml-2 code-title">{{ code.title }}</span></div>
		<span v-if="this.$store.state.memberid===code.memberid">
        <v-btn class="mx-3" @click="recode()" small color="warning" dark>수정</v-btn>
        <v-btn small @click="codedelete()" color="error">삭제</v-btn>
    </span>
		<div class="code-detail-header-2 my-2 d-flex justify-space-between">
			<div>작성자 : {{ code.username }} <sendmessage :username="code.username" :id="code.memberid"/></div>
			<div>{{ code.created_at }}</div>
		</div>
	</div>
	<div class="code-detail-content">
		<div class="code-body my-5">
			{{ code.body }}
		</div>
		<div class="code-codemirror">
			<codemirror
        :options="showOptions"
        :value="code.code"
  		></codemirror>
		</div>
	</div>
	<div class="code-like">
		<div v-if="this.$store.state.isLogin" align="center">
			<v-btn v-if="!likeFlag" @click="clickLike" text icon color="#d3d3d3">
				<v-icon>mdi-thumb-up</v-icon>
				<h3>{{ count }}</h3>
			</v-btn>
			<v-btn v-else @click="clickLike" text icon color="deep-orange">
				<v-icon>mdi-thumb-up</v-icon>
				<h3>{{ count }}</h3>
			</v-btn>
		</div>
	</div>
	<v-divider></v-divider>
	<div class="code-comments">
		<boardcomment boardname="code" :postid="this.id" categoryid="3" boardtype="post"/>
	</div>

	<!-- <v-divider></v-divider>
    <v-row>
        <v-col cols="3">
            제목 : {{code.title}}
        </v-col>
        <v-spacer />
        <v-col >
            {{code.created_at}}
        </v-col>
    </v-row>
    <v-row>
        <v-col>
        내용 : {{code.body}}
        </v-col>
    </v-row>
    <v-row>
        <v-col>
            작성자 : {{code.username}}
			<sendmessage :username="code.username" :id="code.memberid"/>
        </v-col>
        <v-col>
            언어 : {{this.langchange[code.lang]}}
        </v-col>
    </v-row>
    <v-row class="mb-3" v-if="this.$store.state.memberid===code.memberid">
        <v-btn class="mx-3" @click="recode()" small color="warning" dark>수정</v-btn>
        <v-btn small @click="codedelete()" color="error">삭제</v-btn>
    </v-row>
    <codemirror
        :options="showOptions"
        :value="code.code"
  ></codemirror>
  		<div v-if="this.$store.state.isLogin" align="center">
			<v-btn v-if="!likeFlag" @click="clickLike" text icon color="#d3d3d3">
				<v-icon>mdi-thumb-up</v-icon>
				<h3>{{ count }}</h3>
			</v-btn>
			<v-btn v-else @click="clickLike" text icon color="deep-orange">
				<v-icon>mdi-thumb-up</v-icon>
				<h3>{{ count }}</h3>
			</v-btn>
		</div>
  <v-divider/>
    <boardcomment boardname="code" :postid="this.id" categoryid="3" boardtype="post"/> -->
	
</v-container>
</template>

<script>
  import sendmessage from '../../message/sendmessage'

	import axios from 'axios'
	import Comment from '../../comment/Comment'
	import router from '@/router'
	import CodeMirror from 'codemirror'
	import {
		codemirror
	} from 'vue-codemirror'
	import 'codemirror/lib/codemirror.css'
	import 'codemirror/mode/xml/xml.js'
	import 'codemirror/mode/javascript/javascript.js'
	import 'codemirror/theme/base16-dark.css'
	import 'codemirror/mode/python/python.js'
	import boardcomment from '@/components/comment/boardcomment'

	import '@/assets/css/CodeDetail.css'

export default {
    name : 'CodeDetail',
    components: {codemirror,Comment,boardcomment,sendmessage},
    props :{
      id : {type: String},
      boardname : {type : String}
    },
	data() {
		return {
			comments: [],
			commentbody: '',
			langchange: {
				'text/x-python': 'Python',
				'text/x-java': 'Java',
				'text/javascript': 'JavaScript',
				'text/html': 'Html',
				'text/x-c++src': 'C++'
			},
			code: {},
			showOptions: {
				tabsize: 4,
				mode: 'text/x-python',
				lineNumbers: true,
				readOnly: true,
				line: true,
				lineSeparator: '\n'
			},
			likeFlag: false,
			count: 0,
			categoryid: 3,
			memberid: '',
		}
	},
    methods : {
		clickLike() {
				this.likeFlag = !this.likeFlag
				var data = {
					postid: this.code.id,
					memberid: this.memberid,
					categoryid: this.categoryid
				}
				if (this.likeFlag) {
					this.count += 1
					axios.post('api/likepost', data)
						.then(response => {
							console.log(response)
						})
				} else {
					this.count -= 1
					axios.delete('api/likepost', {
							data: data
						})
						.then(response => {
							console.log(response)
						})
				}
			},
			getcode() {
				axios.get(`api/code/${this.id}`, { headers: { 'access-token': this.$store.state.token }})
					.then(response => {
						console.log(response.data)
						this.code = response.data.code
						this.count = response.data.count
						this.likeFlag = (response.data.flag === 0) ? false : true
					})
			},
			codedelete() {
				const chk = confirm("게시글을 삭제하시겠습니까?")
				if (chk) {
					axios.delete(`api/code/${this.id}`)
						.then(response => {
							console.log(response.data)
							router.push({
								path: '/board/code/'
							})
						})
				}
			},
			recode() {
				router.push({
					path: `/board/code/${this.id}/edit`
				})
			},
			getColor(language) {
				if (language === 'text/x-python') {
					return 'blue'
				} else if (language === 'text/x-java') {
					return 'orange'
				} else if (language === 'text/javascript') {
					return 'yellow'
				} else if (language === 'text/html') {
					return '#EF6C00'
				} else {
					return '#283593'
				}
			}
		},
		mounted() {
			this.getcode()
			this.memberid = this.$store.state.memberid
		}
	}
</script>

<style>
	.CodeMirror {
		height: 100%;
		/* Necessary so the scrollbar can be absolutely positioned within the wrapper on Lion. */
		position: relative;
		/* This prevents unwanted scrollbars from showing up on the body and wrapper in IE. */
	}
</style>