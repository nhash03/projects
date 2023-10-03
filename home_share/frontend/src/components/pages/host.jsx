import React, {useState} from "react";
import './host.css'

function Host(){
    const [name,setName]=useState('Ben Joe');
    const [cond,setCond]=useState('Vancouver, Canada BC, up to 10 people, up to a week stay');
    const [about, setAbout] = useState('Elementary school teacher for 25 years, originally from Japan now he resides in Vancouver with his family. Also he enjoys meeting new people and making new friends!')

    return (
       
        <div className="Host">
                         <div className="Header">We found the perfect Home for you!</div>

            <div className="uppper-container">
                <div className = "image-container">
                    <img src= "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxjb2xsZWN0aW9uLXBhZ2V8MXw3NjA4Mjc3NHx8ZW58MHx8fHw%3D&w=1000&q=80" alt='' height= "100px" width="100px"/>
                    <h3> { name } </h3>
                </div>
            </div>
            <div className="lower-container">
               
                <h4> {cond} </h4>
                <p> {about} </p>
                <button>Notify Host</button>
            </div>
        </div>
    )



}
export default Host; 