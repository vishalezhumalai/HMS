import React, { Component } from 'react'
import EmployeeService from '../services/EmployeeService';

class UpdateEmployeeComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            firstName: '',
            lastName: '',
            emailId: '',
            doj: '',
            experience: ''
        }
        this.changeFirstNameHandler = this.changeFirstNameHandler.bind(this);
        this.changeLastNameHandler = this.changeLastNameHandler.bind(this);
        this.changeDojHandler = this.changeDojHandler.bind(this);
        this.changeExperienceHandler = this.changeExperienceHandler.bind(this);
        this.updateEmployee = this.updateEmployee.bind(this);
    }

    componentDidMount(){
        EmployeeService.getEmployeeById(this.state.id).then( (res) =>{
            let employee = res.data;
            this.setState({firstName: employee.firstName,
                lastName: employee.lastName,
                emailId : employee.emailId,
                doj : employee.doj,
                experience : employee.experience
            });
        });
    }

    updateEmployee = (e) => {
        e.preventDefault();
        let employee = {firstName: this.state.firstName, lastName: this.state.lastName, emailId: this.state.emailId,doj: this.state.doj,experience: this.state.experience};
        console.log('employee => ' + JSON.stringify(employee));
        console.log('id => ' + JSON.stringify(this.state.id));
        EmployeeService.updateEmployee(employee, this.state.id).then( res => {
            this.props.history.push('/employees');
        });
    }
    
    changeFirstNameHandler= (event) => {
        this.setState({firstName: event.target.value});
    }

    changeLastNameHandler= (event) => {
        this.setState({lastName: event.target.value});
    }


    changeDojHandler= (event) => {
        this.setState({doj: event.target.value});
    }

    changeExperienceHandler= (event) => {
        this.setState({experience: event.target.value});
    }

    changeEmailHandler= (event) => {
        this.setState({emailId: event.target.value});
    }

    cancel(){
        this.props.history.push('/employees');
    }

    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-center">Update Doctor</h3>
                                <div className = "card-body">
                                    <form>

                                        <div className = "form-group">
                                            <label> Doctor Name: </label>
                                            <input placeholder="Doctor Name" name="firstName" className="form-control" 
                                                value={this.state.firstName} onChange={this.changeFirstNameHandler}/>
                                        </div>
                                        <div className = "form-group">
                                            <label> Specialisation: </label>
                                            <input placeholder="Specialization" name="lastName" className="form-control" 
                                                value={this.state.lastName} onChange={this.changeLastNameHandler}/>
                                        </div>
                                        <div className = "form-group">
                                            <label> Designation: </label>
                                            <input placeholder="Designation" name="emailId" className="form-control" 
                                                value={this.state.emailId} onChange={this.changeEmailHandler}/>
                                        </div>
                                        
                                        <div className = "form-group">
                                            <label> Date Of Joining: </label>
                                            <input type="date" name="doj" className="form-control" 
                                                value={this.state.doj} onChange={this.changeDojHandler}/>
                                        </div>
                                        
                                        <div className = "form-group">
                                            <label> Experience: </label>
                                            <input  name="Experience in yrs" className="form-control" 
                                                value={this.state.experience} onChange={this.changeExperienceHandler}/>
                                        </div>


                                        <button className="btn btn-success" onClick={this.updateEmployee}>Save Change</button>
                                        <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                    </form>
                                </div>
                            </div>
                        </div>

                   </div>
            </div>
        )
    }
}

export default UpdateEmployeeComponent
