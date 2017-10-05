import React, { Component } from 'react'
import { Col, Form, FormGroup, Input, Label, Button } from 'reactstrap'
import { login } from './LoginAPI'
import { setAuthToken } from './AuthStorage'

class LoginComponent extends  Component {
	constructor(props) {
		super(props)

		this.state = { username : '', password: '' }
		this.handleUsername = this.handleUsername.bind(this)
		this.handlePassword = this.handlePassword.bind(this)
		this.handleSubmit = this.handleSubmit.bind(this)
	}

	handleUsername(e) {
		return this.setState({
			username: e.target.value
		})
	}

	handlePassword(e) {
		return this.setState({
			password: e.target.value
		})
	}

	handleSubmit() {
		let { username, password } = this.state
		login(username, password).then((res) => {
			console.log(res.headers)
			setAuthToken(res.headers.authorization)
			this.props.history.push('/')
		})
	}

	render() {
		return(
			<Col>
				<Form>
					<FormGroup>
						<Label>Username:</Label>
						<Input type="text" name="username" placeholder="Username: " onChange={this.handleUsername} />
					</FormGroup>
					<FormGroup>
						<Label>Password:</Label>						
						<Input type="password" name="password" placeholder="Password: " onChange={this.handlePassword} />
					</FormGroup>
					<FormGroup>
						<Button color="primary" onClick={this.handleSubmit}>Login</Button>
					</FormGroup>
				</Form>
		</Col>
		)
	}
}

export default LoginComponent