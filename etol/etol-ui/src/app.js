import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Link, Routes } from "react-router-dom";

import './index.css';
import Home from './home.js';
import ProfileDetails from './profile.js';
import History from './history.js';
import Login from './login.js';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = { isLogged: 0 };
  }

  setLoginState = (newState) => {
    this.setState({ isLogged: newState });
  }

  handleLogout = () =>
  {
     //Read the form values and make the server call
     //Update the app state based
     this.setState({ isLogged:0});             
   }

  createLayout() {
    return (
      <Router>
        <div>
          <div className="topnav">
            <Link to="/home">Home</Link>
            <Link to="/profile">Profile</Link>
            <Link to="/history">History</Link>
            <Link to="/logout" onClick={this.handleLogout} >Logout</Link>
          </div>
          <hr />
          <Routes>
            <Route path="/home" element={<Home statusUpdateFunction={this.setLoginState} />} />
            <Route path="/profile" element={<ProfileDetails />} />
            <Route path="/history" element={<History />} />
            <Route path="/logout" element={<Login statusUpdateFunction={this.setLoginState} />} />
          </Routes>
        </div>
      </Router>);
  }

  render() {
    let tmp;
    if (this.state.isLogged === 1) {
      tmp = this.createLayout();
    }
    else {
      tmp = <Login statusUpdateFunction={this.setLoginState} />;
    }
    return (tmp);
  }
}

export default App;