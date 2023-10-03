import React from "react"
import Heading from "../common/Heading"
import { team } from "../data/Data"
import "./tophosts.css"
import {FaBeer} from 'react-icons/fa'
import {BsFacebook} from 'react-icons/bs'
import {CiLinkedin, CiTwitter, CiInstagram} from 'react-icons/ci';

function TopHosts  ()  {
    return (
        <>
            <section className="top background">
                <div className="TopHosts">
                    <Heading title="The Hosts of the Month" subtitle="" />
                    <div className="content mtop grid3">
                        {team.map((val, index) => (
                            <div className='box' key={index}>
                                <button className='btn3'>{val.list} Listings</button>
                                <div className='details'>
                                    <div className='img'>
                                        <img src={val.cover} alt='' />
                                        <i className='fa-solid fa-circle-check'></i>
                                    </div>
                                    <i className='fa fa-location-dot'></i>
                                    <label>{val.address}</label>
                                    <h4>{val.name}</h4>

                                    <ul>
                                        {val.icon.map((icon, index) => (
                                            <li key={index}>{icon}</li>
                                        ))}
                                    </ul>
                                    <div className='button flex'>
                                        <button>
                                            <i className='fa fa-envelope'></i>
                                            Profile
                                        </button>
                                        <button className='btn4'>
                                            contact
                                            <i className='fa fa-phone-alt'></i>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>
            </section>
        </>
    )
}
export default TopHosts