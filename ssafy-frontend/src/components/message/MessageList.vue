<template>
	<v-layout mt-5 row wrap style="margin: 20px;">
		<h1 v-if="messageFlag">받은 메세지함</h1>
		<h1 v-else>보낸 메세지함</h1>
		<v-flex v-for="i in this.showMessagesCount" :key="i" style="width: 100%;">
			<!-- col-12 sm6 md3 -->
			<Message :date="messages[i - 1].created_at" :body="messages[i - 1].body"
				:from="messages[i - 1].from">
			</Message>
		</v-flex>
		<v-flex xs12 text-xs-center round my-5 v-if="loadMore">
			<v-btn v-if="this.messages.length > 6 && this.moreMessagesIcon" color="#f7b157" dark v-on:click="loadMoreMessages">
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
		name: 'ReceivedMessage',
		components: {
			Message
		},
		props: {
			messageFlag: {type: Boolean}
		},
		data() {
			return {
				messages: [],
				loadMore: false,
				showMessagesCount: 0,
				moreMessagesIcon: true,
				hideMessagesIcon: false,
			}
		},
		mounted() {
			this.getMessages()
		},
		methods: {
			getMessages() {
				axios.get('', {
						headers: {
							'access-token': this.$store.state.token
						}
					})
					.then(response => {
						this.messages = response.data
						this.showMessagesCount = (this.messages.length >= 6) ? 6 : this.messages.length
						this.loadMore = (this.messages.length > 6) ? true : false
					})
			},
			loadMoreMessages() {
				let adjustCount = this.showMessagesCount + 6 < this.messages.length ? this.showMessagesCount + 6 : this.messages.length
				this.showMessagesCount = adjustCount
				this.moreMessagesIcon = adjustCount < this.messages.length ? true : false
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

<style>

</style>