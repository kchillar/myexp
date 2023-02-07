import React, {Component} from 'react';

class Home extends Component {
    constructor(props)
    {
        super(props);
        this.state = {loginStateUpdater:props.statusUpdateFunction};
        console.log("props", props);
    }

    render()
    {                  
        return (
        <div>
             <p>Welcome to Home. You are logged in !!!</p>
         </div>
        );        
  }
} 
 
// Exporting the component
export default Home;