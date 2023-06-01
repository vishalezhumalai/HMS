import React, { Component } from 'react'
import '../assets/header.css'
import logo from '../assets/img/logo hms.png'

class HeaderComponent extends Component {
    
  
    constructor(props) {
        super(props)

        this.state = {
                 
        }
    }

    render() {
        return<>
            <header className="header">
        <div className="container">
            <div className="nav__wrapper">
                <div className="logo">
                    <div className="logo__img"><img src={logo} alt="" /></div>
                    <h2>HMS</h2>
                </div>
    
                <div className="navigation">
                  <h4>way to digital healthy Lifestyle...</h4>
                </div>
                <div className="nav__right">
                <button className="register__btn"><a href="http://localhost:3000">Patient Login</a></button>
                  <span className="mobile__menu">
                    <i class="ri-menu-line"></i>
                  </span>
                </div>
                <div className="nav__right">
                <button className="register__btn"><a href="http://localhost:3001">Doctor Login</a></button>
                  <span className="mobile__menu">
                    <i class="ri-menu-line"></i>
                  </span>
                </div>
            </div>
        </div>
      </header>
        </> 
        
    }
}

export default HeaderComponent


