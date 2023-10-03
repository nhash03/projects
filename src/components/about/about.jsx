import React from "react";
import './about.css';
import sample from './sample.mp4';

const About = () => {
  return (
    <div className='about'>
        <div className="overlay"></div>
        <video src={sample} autoPlay loop muted />
        <div className="content">
            <h1>About HomeShare</h1>
            <p> Unfortunately, there are many people around the world who are currently seeking immediate shelter due to natural disasters,
       war, and other issues. 
       According to the Internal Displacement Monitoring Centre, as of 2021, 17 million who were displaced due to natural disasters. 
    These individuals often have no choice but to seek refuge in temporary shelters or displacement camps, where they may face a multitude
     of challenges including inadequate living conditions, limited access to food and water, and insufficient medical care. The need for
      immediate shelter and support for those who are displaced remains a critical global issue that requires urgent attention and action. 
      that's why HomeShare Our company provides a platform that connects people in need of immediate shelter with available options in their area. 
      Using our service, users can quickly and easily search for shelters based on their location, preferred amenities, and other criteria. 
      Our user-friendly interface makes it easy for individuals to find the best available options for their specific needs, 
      ensuring that they can secure safe and secure accommodation as quickly as possible.

    We understand the urgency of finding shelter during times of crisis, and our team is committed to providing fast and reliable 
    service to those in need. By leveraging technology and our extensive network of partners, we are able to streamline the process of 
    finding and accessing available shelters, providing peace of mind to those who are displaced.</p>
        </div>
    </div>
  )
}

export default About;