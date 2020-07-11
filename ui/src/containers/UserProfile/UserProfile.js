import React, {Component} from 'react'
import classes from './UserProfile.module.css'
import PersonalInfo from "../../components/PersonalInfo/PersonalInfo";

class UserProfile extends Component {

    state = {
        username: '',
        email: '',
        firstName: '',
        lastName: '',
        birthday: ''
    }

    render() {
        return (
            <div className={classes.UserProfile}>
                <div>
                    <h1>User Profile</h1>
                    <div className={classes.PersonalInfoWrapper}>
                        <PersonalInfo/>
                    </div>
                </div>
            </div>
        );
    }
}

export default UserProfile;
