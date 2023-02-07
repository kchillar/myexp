import React from 'react';
import './index.css';

class Table extends React.Component{
    render(){
      return (
        <><div>
          <table>
            <tr><th>Col1</th><th>Col2</th><th>Col3</th></tr>
            <tr><td>d11</td><td>d12</td><td>d13</td></tr>
            <tr><td>d12</td><td>d22</td><td>d23</td></tr>
          </table>
        </div><div>
            <button type="button">Prev</button>
            <button type="button">Next</button>
          </div></>
      );
    }
  }

  export default Table;
  