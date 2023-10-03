import React from "react";
import Heading from "../common/Heading";
import { location } from "../data/Data"
import "../locations/locations.css"

const Locations = () => {
    return (
        <>
        <section className="location">
            <div className="container">
                <div className="title">
                <Heading title='Explore By Locations' subtitle='' />
                </div>
                <div className="boxes">
                    {location.map((item, index) => (
                        <div className='box' key={index}>
                            <img src={item.cover} alt='blah' />
                            <div className='overlay'>
                                <h5>{item.name}</h5>
                            <p>
                                <label>{item.hosts}</label>
                            </p>
                            </div>
                        </div> ))}
                    </div>
            </div>
        </section>
        </>
    )
}
export default Locations