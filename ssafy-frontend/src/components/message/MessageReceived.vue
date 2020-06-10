<template>
	<v-layout mt-5 row wrap style="margin: 20px;">
		<h1 class="mb-3" style="font-family: 'Noto Sans KR'; font-weight: bold;">받은 메세지함</h1>
		<v-flex v-for="i in this.showMessagesCount" :key="i" style="width: 100%;">
			<Message
				:date="receivedMessages[i - 1].created_at" 
				:title="receivedMessages[i - 1].title"
				:content="receivedMessages[i - 1].content"
				:opponent="receivedMessages[i - 1].username"
				:opponentId="receivedMessages[i - 1].fromid"
				:id="receivedMessages[i - 1].id"
				:read="receivedMessages[i - 1].isread"
				:boardType=1
			></Message>
		</v-flex>
		<v-flex xs12 text-xs-center round my-5 v-if="loadMore">
			<v-btn v-if="this.receivedMessages.length > 6 && this.moreMessagesIcon" color="#f7b157" dark v-on:click="loadMoreMessages">
				<v-icon size="25" class="mr-2">fa-plus</v-icon> 더 보기
			</v-btn>&nbsp; &nbsp;
			<v-btn v-if="this.hideMessagesIcon" color="red" dark v-on:click="hideMessage">
				<v-icon size="25" class="mr-2">fa-minus</v-icon> 숨기기
			</v-btn>
		</v-flex>
	</v-layout>
</template>

<script>
import axios from 'axios'
import Message from './Message'

export default {
	name: 'MessageReceived',
	components: {
		Message
	},
	data() {
		return {
			receivedMessages: [],
			loadMore: false,
			showMessagesCount: 0,
			moreMessagesIcon: true,
			hideMessagesIcon: false,
		}
	},
	mounted() {
		this.getReceivedMessages()
	},
	methods: {
		getReceivedMessages() {
			axios.get('api/messages', {
				headers: {
					'access-token': this.$store.state.token
				}
			})
			.then(response => {
				this.receivedMessages = response.data
				this.showMessagesCount = (this.receivedMessages.length >= 6) ? 6 : this.receivedMessages.length
				this.loadMore = (this.receivedMessages.length > 6) ? true : false
			})
		},
		loadMoreMessages() {
			let adjustCount = this.showMessagesCount + 6 < this.receivedMessages.length ? this.showMessagesCount + 6 : this.receivedMessages.length
			this.showMessagesCount = adjustCount
			this.moreMessagesIcon = adjustCount < this.receivedMessages.length ? true : false
			this.hideMessagesIcon = true
		},
		hideMessage() {
			let adjustCount2 = this.showMessagesCount - 6 > 6 ? this.showMessagesCount - 6 : 6
			this.showMessagesCount = adjustCount2
			this.hideMessagesIcon = adjustCount2 === 6 ? false : true
			this.moreMessagesIcon = true
		}
	}
}
</script>