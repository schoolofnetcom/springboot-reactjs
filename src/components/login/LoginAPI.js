import axios from 'axios'

//backend
const URL = 'http://localhost:8080'

const login = (username, password) => {
	//http://localhost:8080/login
	const url = `${URL}/login`

	return axios.post(url, { username, password }).then(response => response)
}


export {
	login
}