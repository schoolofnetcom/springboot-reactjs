import React, { Component } from 'react'
import { Col, Table, Button } from 'reactstrap'
import { Link } from 'react-router-dom'
import { findAll, remove } from './TaskAPI'

class TaskListComponent extends Component {
	constructor(props) {
		super(props)

		this.state = { tasks: [] }
		this.handleDelete = this.handleDelete.bind(this)

	}

	componentDidMount() {
		findAll().then(data => this.setState({ tasks: data }))
	}

	handleDelete(task) {
		remove(task.id).then( () => {
			return findAll().then(data => this.setState({ tasks: data }))
		})
	}

	render() {
		let { tasks } = this.state
		return (
			<div>
				<br />
				<Col xs="12" sm="12" md="12">
					<Link className="btn btn-primary" to="/task/new">Create</Link>
				</Col>
				
				<br />
				<Col xs="12" sm="12" md="12">
					<Table>
						<thead>
							<tr>
								<th>Name</th>
								<th>Description</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							{ tasks.map((task, index) => (
								<tr>
									<td>{ task.name } </td>
									<td>{ task.description }</td>
									<td>
										<Button colr="warning" onClick={ () => this.handleDelete(task) }>Delete</Button>
									</td>
								</tr>
							))}
						</tbody>
					</Table>
				</Col>
			</div>
		)
	}
}

export default TaskListComponent