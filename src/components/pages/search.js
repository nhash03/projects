import { Data } from "@react-google-maps/api";
import { useState, useEffect } from "react";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import Thresh from "./thresh";
import './search.css';
// import texts from "../data/texts.json";
const Seacrh = () => {
    const[txt, setText] = useState("");

    // useEffect (() => {
    //     fetch("/mem").then (
    //         res => res.json()
    //     ).then (
    //         txt => {
    //             setText(txt)
    //             console.log(txt)
    //         }
    //     )
    // }, [])

    const handleSubmit = (e) => {
        console.log('test2')
        e.preventDefault();
        const body = {txt};
        fetch("http://localhost:5000/hhh", {
            method: "POST",
            headers : {
                "Content-Type" : 'application/json'
            },
            body : JSON.stringify(body)
        })
        .then(res => res.json())
        

        
    }
    return (
        <div className="search">
            <form onSubmit={handleSubmit} >
                <label>Tell us about your situation (ex. location and your companions):</label>
                <input type="text" required value={txt}
                onChange={(e) => setText(e.target.value)} id= "txt"></input>
                <button type="submit" className="orange"> 
                submit</button>
                <button ><a href="/nn">Find My Property</a></button>
            </form>
        </div>
    )
}
export default Seacrh;