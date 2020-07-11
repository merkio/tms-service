import React, {Component} from 'react';
import classes from './PersonalInfo.module.css';
import TextField from "../UI/TextField/TextField";
import Button from "../UI/Button/Button";

class PersonalInfo extends Component {

    state = {
        username: 'User',
        email: 'user@example.com',
        firstName: 'Name'
    }

    onChangeText(event) {
        this.setState({[event.target.name]: event.target.value});
    }

    onSave() {

    }

    onCancel() {

    }

    render() {
        return (
            <div className={classes.PersonalInfo}>
                <h2>
                    <span><strong>Personal Info</strong></span>
                </h2>
                <TextField field="Username" text={this.state.username}
                           onChangeText={event => this.onChangeText(event)}/>
                <TextField field="Email" text={this.state.email}
                           onChangeText={event => this.onChangeText(event)}/>
                <TextField field="First Name" text={this.state.firstName}
                           onChangeText={event => this.onChangeText(event)}/>

                <br/>
                <Button onClick={() => this.onSave()} type='primary'>Save</Button>
                <Button onClick={() => this.onCancel()} type="error">Cancel</Button>
            </div>
        );
    }
}

export default PersonalInfo