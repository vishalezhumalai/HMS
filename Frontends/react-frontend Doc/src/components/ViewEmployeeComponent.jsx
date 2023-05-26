import React, { Component } from 'react';
import EmployeeService from '../services/EmployeeService';

class ViewEmployeeComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {
      id: this.props.match.params.id,
      employee: {}
    };
  }

  componentDidMount() {
    EmployeeService.getEmployeeById(this.state.id).then((res) => {
      this.setState({ employee: res.data });
    });
  }

  handlePrint = () => {
    window.print();
  };

  render() {
    return (
      <div>
        <br />
        <div className="card col-md-6 offset-md-3">
          <h3 className="text-center">View Employee Details</h3>
          <div className="card-body">
            <div className="row">
              <label>Doctor Name:</label>
              <div>{this.state.employee.firstName}</div>
            </div>
            <div className="row">
              <label>Specialisation:</label>
              <div>{this.state.employee.lastName}</div>
            </div>
            <div className="row">
              <label>Designation:</label>
              <div>{this.state.employee.emailId}</div>
            </div>
            
            <div className="row">
              <label>Date of Joining:</label>
              <div>{this.state.employee.doj}</div>
            </div>
            <div className="row">
              <label>Experience:</label>
              <div>{this.state.employee.experience}</div>
            </div>
            
          </div>
        </div>
      </div>
    );
  }
}

export default ViewEmployeeComponent;
