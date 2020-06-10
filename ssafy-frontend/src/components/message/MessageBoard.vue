<template>
	<div class="mt-5">
		<div v-if="currentMemberId !== null">
			<v-container>
				<div class="mb-6">
					<v-btn @click="changeMessageFlag(true)" class="mx-3">받은 메세지함</v-btn>
					<v-btn @click="changeMessageFlag(false)" class="mx-3">보낸 메세지함</v-btn>
				</div>
				<MessageReceived v-if="messageFlag"/>
				<MessageSent v-else/>
			</v-container>
		</div>
		<div v-else class="mt-10">
			<h1 style="text-align:center; color:red">접근 권한이 없습니다</h1>
			<v-btn :to="{ name: 'home'}" style="margin:0 auto; display:block; width: 300px;" class="red white--text">
				<h3>Home으로 이동</h3>
			</v-btn>
		</div>
	</div>
</template>

<script>
import MessageReceived from './MessageReceived'
import MessageSent from './MessageSent'

export default {
	name: 'MessageBoard',
	components: {
		MessageReceived,
		MessageSent
	},
	data() {
		return {
			messageFlag: true,
			currentMemberId: null
		}
	},
	mounted() {
		this.currentMemberId = this.$store.state.memberid
	},
	methods: {
		changeMessageFlag(flag) {
			this.messageFlag = flag
		}
	}
}
</script>