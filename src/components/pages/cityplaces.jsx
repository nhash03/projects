import React from "react"
import { BrowserRouter as Router, Switch, Route } from "react-router-dom"
import Header from "../common/header/Header"
import Footer from "../common/footer/Footer"
import Heading from "../common/Heading"
import HouseCards from "./housecards"

function CityPlaces  ()  {
    return (
        <div>
            <Heading title="city" subtitle = "" />
            <HouseCards />
            <Footer />
        </div>
    )
}
export default CityPlaces