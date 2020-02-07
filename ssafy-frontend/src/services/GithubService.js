import Api from '@/services/Api'

const BASE_URL = 'https://api.github.com'

export default {
	getInfo(userName) {
		return Api(BASE_URL).get(`/users/${userName}`)
	}
}
