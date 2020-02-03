import Api from '@/services/Api'

const BASE_URL = 'https://api.github.com'

export default {
	getRepos(userName) {
		return Api(BASE_URL).get(`/users/${userName}/repos`)
	},
	// getCommits(fullName) {
	// 	let d = new Date()
	// 	d.setMonth(d.getMonth() - 1)

	// 	return Api(BASE_URL).get(`/projects/${fullName}/repository/commits?since=${d.toISOString()}`)
	// }
}
