import React, { Component } from 'react'
import { Col, Form, FormGroup, Input, Label, Button } from 'reactstrap'
import { create } from './TaskAPI'

class TaskCreateComponent extends Component {
	constructor(props) {
		super(props)
		
		this.state = { name: '', description: '' }

		this.handleSubmit = this.handleSubmit.bind(this)
		this.handleTaskDescription = this.handleTaskDescription.bind(this)
		this.handleTaskName = this.handleTaskName.bind(this)
	}

	handleTaskDescription(e) {
		return this.setState({
			description: e.target.value
		})
	}

	handleTaskName(e) {
		return this.setState({
			name: e.target.value
		})
	}

	handleSubmit() {
		let { name, description } = this.state
		
		create(name, description).then(() => {
			this.props.history.push('/task')
		})
	}

	render() {
		return (
			<Col>
				<Form>
					<FormGroup>
						<Label>Name:</Label>
						<Input type="text" name="name" placeholder="Tak name: " onChange={this.handleTaskName} />
					</FormGroup>
					<FormGroup>
						<Label>Description:</Label>						
						<Input type="text" name="description" placeholder="Task description: " onChange={this.handleTaskDescription} />
					</FormGroup>
					<FormGroup>
						<Button color="primary" onClick={this.handleSubmit}>Create</Button>
					</FormGroup>
				</Form>
			</Col>
		)
	}
}

export default TaskCreateComponent