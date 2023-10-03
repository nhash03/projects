import './App.css';
import Header from './components/common/header/Header';
import Manage from './components/pages/manage';
import Pages from './components/pages/pages';
import Footer from './components/common/footer/Footer';
import {BrowserRouter, BrowserRouter as Route, Redirect, Router, Switch } from 'react-router-dom';
import MainSearch from './components/main-search/MainSearch';
import Seacrh from './components/pages/search';
import Thresh from './components/pages/thresh';
import About from "./components/about/about.jsx";
import Donate from "./components/about/donate";
// import './blah.html';

function App() {
  return (
    <BrowserRouter>
      <div className='App'>
        <Header/>
        <div className='content'>
          <Switch>
            <Route exact path="/">
              <Pages />
            </Route>
            <Route path="/abc">
              <Seacrh />
            </Route>
            <Route path = "/nn">
              <Thresh />
            </Route>
            <Route path = "/About">
              <About />
            </Route>
            <Route path = "/Donate">
              <Donate />
            </Route>
          </Switch>
        </div>
        <Footer/>
      </div>
    </BrowserRouter>
  );
}

export default App;
