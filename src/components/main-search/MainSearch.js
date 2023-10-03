import React, { Component } from "react"
import Heading from "../common/Heading"
import "./mainsearch.css"
import CityPlaces from "../pages/cityplaces"
import { useState } from 'react';
import ReactDOM from 'react-dom';
import { Link, useNavigate } from "react-router-dom";
import { Button } from 'reactstrap';
import sample from './pexels-c-technical-6133088.mp4';


function MainSearch (){


  return (
    <>
      <section className='MainSearch'>
        <video autoPlay="autoplay" loop="true" muted id="vid">
            <source src={sample} type="video/mp4"></source>
          </video>
        <div className='container'>
          
          <div  className="overlay">
          <Heading  title='Search Your Next Home ' subtitle='Find a great place to start your new life'/>
            <button className='btn1' type="submit">
              <i  className='fa fa-search'>
                  <a href="/abc">Tell us what you need!</a>
                  </i>
            </button>
        </div>
        </div>
      </section>
    </>
  )}


export default MainSearch
