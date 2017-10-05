import axios from 'axios'
import { getAuthToken } from './../login/AuthStorage'

//backend
const URL = 'http://localhost:8080'
let config = { 'Authorization': getAuthToken() }

const create = (name, description) => {
	//http://localhost:8080/tasks
	const url = `${URL}/tasks`

	return axios.post(url, { name, description }, { headers: config }).then(response => response.data)
}

const findAll = () => {
	//http://localhost:8080/tasks
	const url = `${URL}/tasks`
	
	return axios.get(url, { headers: config }).then(response => response.data)
}

const remove = (id) => {
	//http://localhost:8080/tasks/123456456
	const url = `${URL}/tasks/${id}`
	
	return axios.delete(url, { headers: config }).then(response => response.data)
}

export {
	create,
	findAll,
	remove
}