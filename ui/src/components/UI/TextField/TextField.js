import React from "react"
import classes from './TextField.module.css'


export default props => {
    return (
        <div className={classes.TextField}>
            <p>
                <span>{props.field}: </span>
            </p>
            <input type="text" value={props.text} onChange={props.onChangeText}/>
        </div>
    );
}