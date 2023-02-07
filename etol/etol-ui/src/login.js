import React, {Component} from 'react';

class Login extends Component {
    constructor(props)
    {
        super(props);    
        this.state = {loginStateUpdater:props.statusUpdateFunction};
        console.log("props", props);
    }
 
    handleLogin = () =>
     {
        //Read the form values and make the server call
        //Update the app state based
        console.log("The function to be invoked is: ", this.state.loginStateUpdater);        
        this.state.loginStateUpdater(1);
      }

      handleClear = () =>
      {
         //Make the server call and finally update app state
         //this.props.loginStateUpdater(1);
       }
 
    render()
    {
        return (
            <div>
         <table>
            <tbody>
             <tr><td><label>Login ID</label></td><td><input type="text"></input></td></tr>
             <tr><td><label>Password</label></td><td><input type="password"></input></td></tr>
             <tr><td><button onClick={this.handleClear}>Clear</button></td><td><button onClick={this.handleLogin}>Submit</button></td></tr>
            </tbody>
            </table>
            </div>
        );
  }
} 
 
// Exporting the component
export default Login;