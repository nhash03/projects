import React from "react"
import { BrowserRouter as Router, Switch, Route } from "react-router-dom"
import { Link } from 'react-router-dom'
import { footer } from "../../data/Data"
import "./footer.css"

function Footer ()  {
// const linkList = document.getElementById('linkList');
// const links = linkList.getElementsByTagName('li');

// for (let i = 0; i < links.length; i++) {
//   const linkUrl = links[i].textContent;
//   const link = document.createElement('a');
//   link.href = linkUrl;
//   link.textContent = linkUrl;
//   links[i].innerHTML = '';
//   links[i].appendChild(link);
// }

  return (
    <>
      <section className='footerContact'>
        <div className='container'>
          <div className='send flex'>
            <div className='text'>
              <h1>Do You Have Questions ?</h1>
              {/* <p>We'll help you have a new start.</p> */}
            </div>
            <button className='btn5'>Contact Us Today</button>
          </div>
        </div>
      </section>

      <footer>
        <div className='container'>
          <div className='box'>
            <div className='logo'>
              <h2>Do You Need Help With Anything?</h2>
              <p>Recieve the latest news about houses and providers or become a life provider in just a click.</p>

              <div className='input flex'>
                
                <button>Register</button>
              </div>
            </div>
          </div>

          {footer.map((val) => (
            <div key={val.title} className='box'>
              <h3>{val.title}</h3>
              <ul id = "linkList">
                {val.text.map((items) => (
                  <li key={items.list[0]}> <a href={items.list[1]}> {items.list[0]}</a></li>
                ))}
              </ul>
            </div>
          ))}
        </div>
      </footer>
      <div className='legal'>
        <span>© 2023 ShelterFinder. Designd By Negin and Setare.</span>
      </div>
    </>
  )
}

export default Footer
