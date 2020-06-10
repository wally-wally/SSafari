<template>
	<div class="center" justify-center>
		<h1>게시판 추가 신청하기</h1>
		<div>게시판 이름</div>
		<div class="title-top">
			<textarea v-model="boardName" class="title-form"></textarea>
		</div>
		<div>게시판 설명</div>
		<div class="title-top">
			<textarea v-model="boardSubject" id="create-content" name="content"></textarea>
		</div>
		<v-btn class="mr-5" color="#f7b157" @click="create">작성</v-btn>
		<v-btn color="error" @click="back">취소</v-btn>
	</div>
</template>

<script>
import axios from 'axios'

export default {
	name: 'BoardCreate',
	data() {
		return {
			boardName: '',
			boardSubject: '',
		}
	},
	methods: {
		back() {
			this.$router.push('/mypage')
		},
		create() {
			var data = {
				name: this.boardName,
				explanation: this.boardSubject
			}
			axios.post('api/boardcategory', data, { headers: { 'access-token': this.$store.state.token }})
				.then( response => {
					if (response.status === 200){
						alert('게시판 신청이 완료되었습니다. 관리자 승인에 최대 일주일이 소요됩니다.')
						this.$router.push('/board')
					}
				})
		}
	}
}
</script>