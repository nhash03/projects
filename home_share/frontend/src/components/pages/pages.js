import React from "react"
import { BrowserRouter as Router, Switch, Route } from "react-router-dom"
import Header from "../common/header/Header"
import MainSearch from "../main-search/MainSearch"
import Nearby from "../nearby/Nearby"
import Locations from "../locations/Locations"
import TopHosts from "../tophosts/TopHosts"
import Footer from "../common/footer/Footer"

const Pages = () =>{
  return (
    <>
        <MainSearch />
        <Nearby />
        <Locations />
        <TopHosts />
    </>
  )
}

export default Pages