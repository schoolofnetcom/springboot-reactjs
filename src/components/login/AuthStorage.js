const TOKEN = ''

const setAuthToken = (token) => {
	localStorage.setItem(TOKEN, token)
}

const getAuthToken = () => {
	return localStorage.getItem(TOKEN)
}

const isLoggedIn = () => {
	return !!localStorage.getItem(TOKEN)
}

const clearAuthToken = () => {
	return localStorage.removeItem(TOKEN)
}

export {
	setAuthToken,
	getAuthToken,
	isLoggedIn,
	clearAuthToken
}