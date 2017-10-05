import React, { Component } from 'react'
import 'bootstrap/dist/css/bootstrap.css'
import { BrowserRouter as Router, Route, Redirect } from 'react-router-dom'
import { Container, Row } from 'reactstrap'
import HomeComponent from './components/home/HomeComponent'
import HeaderComponent from './components/header/HeaderComponent'
import TaskCreateComponent from './components/task/TaskCreateComponent'
import TaskListComponent from './components/task/TaskListComponent'
import LoginComponent from './components/login/LoginComponent'
import { isLoggedIn } from './components/login/AuthStorage'


class App extends Component {
  constructor(props) {
    super(props)
  }

  render() {
    return (
      <main>
        <HeaderComponent></HeaderComponent>
        <Router>
          <Container>
            <Row>
                <Route exact path="/login" component={LoginComponent} />
                <Route exact path="/" render={ () => ( !isLoggedIn() ? <Redirect to="/login" /> : <HomeComponent /> ) }/>
                <Route exact path="/task" render={ () => ( !isLoggedIn() ? <Redirect to="/login" /> : <TaskListComponent /> ) } />                
                <Route path="/task/new" render={ () => ( !isLoggedIn() ? <Redirect to="/login" /> : <TaskCreateComponent /> ) }  />                                
            </Row>
          </Container>
        </Router>
      </main>
    )
  }
}

export default App
