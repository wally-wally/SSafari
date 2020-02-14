<template>
	<v-container>
		<v-btn v-if="!likeFlag" @click="clickLike" text icon color="#d3d3d3">
			<v-icon>mdi-thumb-up</v-icon>
			<h3>{{ count }}</h3>
		</v-btn>
		<v-btn v-else @click="clickLike" text icon color="deep-orange">
			<v-icon>mdi-thumb-up</v-icon>
			<h3>{{ count }}</h3>
		</v-btn>
		<v-row>
			<v-col cols="3">
				제목 : {{code.title}}
			</v-col>
			<v-spacer />
			<v-col>
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
			</v-col>
			<v-col>
				언어 : {{this.langchange[code.lang]}}
			</v-col>
		</v-row>
		<v-row class="mb-3" v-if="this.$store.state.memberid===code.memberid">
			<v-btn class="mx-3" @click="recode()" small color="warning" dark>수정</v-btn>
			<v-btn small @click="codedelete()" color="error">삭제</v-btn>
		</v-row>
		<codemirror :options="showOptions" :value="code.code"></codemirror>
		<boardcomment :postid="this.id" categoryid="3" boardtype="post" />
	</v-container>
</template>

<script>
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

	export default {
		name: 'CodeDetail',
		components: {
			codemirror,
			Comment,
			boardcomment
		},
		props: {
			id: {
				type: String
			}
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
		methods: {
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
				const chk = confirm("진짜?")
				if (chk) {
					axios.delete(`api/code/${this.id}`)
						.then(response => {
							console.log(response.data)
							router.push({
								path: '/board/codereview/'
							})
						})
				}
			},
			recode() {
				router.push({
					path: `/board/codereview/${this.id}/edit`
				})
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