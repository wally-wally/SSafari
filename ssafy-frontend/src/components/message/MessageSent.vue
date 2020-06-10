<template>
	<v-layout mt-5 row wrap style="margin: 20px;">
		<h1 class="mb-3" style="font-family: 'Noto Sans KR'; font-weight: bold;">보낸 메세지함</h1>
		<v-flex v-for="i in this.showMessagesCount" :key="i" style="width: 100%;">
			<Message
				:date="sentMessages[i - 1].created_at" 
				:title="sentMessages[i - 1].title"
				:content="sentMessages[i - 1].content"
				:opponent="sentMessages[i - 1].username"
				:opponentId="sentMessages[i - 1].toid"
				:id="sentMessages[i - 1].id"
				:read="sentMessages[i - 1].isread"
				:boardType=0
			></Message>
		</v-flex>
		<v-flex xs12 text-xs-center round my-5 v-if="loadMore">
			<v-btn v-if="this.sentMessages.length > 6 && this.moreMessagesIcon" color="#f7b157" dark v-on:click="loadMoreMessages">
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
	name: 'MessageSent',
	components: {
		Message
	},
	data() {
		return {
			sentMessages: [],
			loadMore: false,
			showMessagesCount: 0,
			moreMessagesIcon: true,
			hideMessagesIcon: false,
		}
	},
	mounted() {
		this.getSentMessages()
	},
	methods: {
		getSentMessages() {
			axios.get('api/messages/send', {
				headers: {
					'access-token': this.$store.state.token
				}
			})
			.then(response => {
				this.sentMessages = response.data
				this.showMessagesCount = (this.sentMessages.length >= 6) ? 6 : this.sentMessages.length
				this.loadMore = (this.sentMessages.length > 6) ? true : false
			})
		},
		loadMoreMessages() {
			let adjustCount = this.showMessagesCount + 6 < this.sentMessages.length ? this.showMessagesCount + 6 : this.sentMessages.length
			this.showMessagesCount = adjustCount
			this.moreMessagesIcon = adjustCount < this.sentMessages.length ? true : false
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