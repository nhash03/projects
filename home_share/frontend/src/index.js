import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import Pages from './components/pages/pages';
import CityPlaces from './components/pages/cityplaces';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
// import './components/main-search/abc.html'

import reportWebVitals from './reportWebVitals';

{/* <script src="https://unpkg.com/react-router-dom/umd/react-router-dom.js"></script> */}
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);

// root.render(

//     <Router>
//        <Routes>
// 		      <Route exact path="/" element={<App/>}/>
// 				{/* <Route exact path="/" element={<CityPlaces /> }/> */}
        
// 	    </Routes>
//     </Router>,
// );
// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
