<template>
	<v-container fluid>
    <v-row>
      <v-col cols="12">
        <div class="mb-9">
          <label class="label" style="width: 60%;">
						<v-text-field v-model="password1" :rules="minRules" type="password" label="비밀번호"></v-text-field>
					</label>
        </div>
        <div class="mb-12">
          <label class="label" style="width: 60%;">
						<v-text-field v-model="password2" type="password" label="비밀번호 재확인"></v-text-field>
					</label>
        </div>
        <button type="submit" class="submit button" @click="passwordModify">비밀번호 수정</button>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
	import axios from 'axios'

	export default {
		name: 'PasswordModify',
		data() {
			return {
				minRules: [v => v.length >= 8 || 'Min 8 characters'],
				password1: '',
				password2: ''
			}
		},
		methods: {
			back() {
				this.$router.push('/mypage')
			},
			passwordModify() {
				if (this.password1.length >= 8) {
					if (this.password1 !== this.password2) {
						alert('두 비밀번호가 다릅니다.')
					} else {
						var data = {
							password: this.password1,
							memberid: this.$store.state.memberid
						}
						axios.put('api/member/password', data)
							.then(response => {
								console.log(response)
								alert('비밀번호가 변경되었습니다.')
								this.$router.push('/mypage')
							})
					}
				}
			}
		}
	}
</script>

<style>

</style>