import React, { useState } from "react"
import { Prompt } from "react-router-dom"
import Popup from "./popup"
import prompt from "../data/data.json"
import "./host.css"

const Thresh = () => {
    var cond = prompt.city + ', up to '+prompt.num+' people, up to a week stay'
    var about = 'Elementary school teacher for 25 years, originally from Japan now he resides in '+prompt.city+' with his family. Also he enjoys meeting new people and making new friends!'
    const [buttonPopup, setButtonPopup] = useState(false)

    const handleClick = () => {
        console.log('test3')
        const body = {cond};
        fetch("http://localhost:5000/bbb", {
            method: "POST",
            headers : {
                "Content-Type" : 'application/json'
            },
            body : JSON.stringify(body)
    })
        setButtonPopup('true')
        .then(res => console.log(res))
    }

    return(
        <div className="Host">
            <div className="Header">
                <h1>We found the perfect Home for you!</h1>
            </div>
            <div className="box">
            <div className="upper-container">
                <img src="https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxjb2xsZWN0aW9uLXBhZ2V8MXw3NjA4Mjc3NHx8ZW58MHx8fHw%3D&w=1000&q=80" alt='' height= "100px" width="100px"/>
                <h3>Mr.Blah</h3>
            </div>
            <div className="lower-container">
                <h4>{cond}</h4>
                <p>{about}</p>
                <button onClick={handleClick}>Notify Host</button>
                <Popup trigger = {buttonPopup} setTrigger = {setButtonPopup}>
                    <h3 className="label">Sent the request successfully!</h3>
                </Popup>
            </div>
            </div>
        </div>
        
        
    )
}
export default Thresh;
